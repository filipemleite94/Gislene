package BerkeleyBD;

import java.util.ArrayList;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.LockMode;
import com.sleepycat.je.Transaction;
import com.sleepycat.persist.EntityCursor;
import com.sleepycat.persist.PrimaryIndex;

import COMM.IComm;
import COMM.IProxy;

public class GenericAdm<T extends IProxy> implements IComm<T>{
	private PrimaryIndex<Long, T> key;
	
	private Transaction txn = null;
	private Environment env = null;
	
	GenericAdm(PrimaryIndex<Long, T> primaryIndex, Environment env){
		this.key = primaryIndex;
		this.env = env;
	}
	
	
	private void getTransaction() throws DatabaseException{
		if(txn == null){
			txn = env.beginTransaction(null, null);
		}
		return;
	}
    
    public void abort() throws DatabaseException{
    	if(txn != null){
    		txn.abort();
    		txn = null;
    	}
    	return;
    }
    
    public void terminate() throws DatabaseException{
    	if(txn != null){
    		txn.commit();
    		txn = null;
    	}
    	return;
    }
    
    public void put(T thing) throws DatabaseException{
    	getTransaction();
    	key.put(txn, thing);
    	return;
    }
    
    public T get(Long ID) throws DatabaseException{
    	getTransaction();
    	return key.get(txn, ID, LockMode.READ_COMMITTED);
    }
    
    public ArrayList<T> getAll() throws DatabaseException{
    	getTransaction();
    	ArrayList<T> ans = new ArrayList<T>();
    	EntityCursor<T> cursor = key.entities(txn, null);
    	for(T ite: cursor){
    		ans.add(ite);
    	}
    	cursor.close();
    	return ans;
    }
    
    public void deleteAll() throws DatabaseException{
    	getTransaction();
    	EntityCursor<T> cursor = key.entities(txn, null);
    	for(T ite: cursor){
    		cursor.delete();
    	}
    	cursor.close();
    	return;
    }
    
    public boolean contains(Long key) throws DatabaseException{
    	return this.key.contains(key);
    }


	@Override
	public ArrayList<T> getEverythingFromTheDatabase() throws DatabaseException{
		ArrayList<T> asn = getAll();
		terminate();
		return getAll();
	}


	@Override
	public void persistInDatabase(T proxy) throws DatabaseException {
		put(proxy);
		terminate();
	}


	@Override
	public T getUpdateFromDataBase(T proxy) throws DatabaseException {
		T ans = get(proxy.getID()); 
		terminate();
		return ans;
	}

}

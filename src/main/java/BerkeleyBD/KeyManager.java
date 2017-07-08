package BerkeleyBD;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.PrimaryIndex;
import com.sleepycat.persist.StoreConfig;

import AOM.Objeto;
import AOM.Type;
import COMM.IComm;
import COMM.ICommManager;
import COMM.IProxy;

@SuppressWarnings(value = "unchecked")
public class KeyManager implements ICommManager{
	private HashMap<PrimaryIndex<Long, ? extends IProxy>, GenericAdm<? extends IProxy>> keySet;
	
	public PrimaryIndex<String, Type> typeByName;
	public PrimaryIndex<String, Objeto> objetoByName;
	
	private Environment env = null;
	private EnvironmentConfig envConfig = null;
	private EntityStore store = null;
	
	public KeyManager(){};

	@Override
	public void InitiateComm(File f, String Name) throws DatabaseException {
		envConfig = new EnvironmentConfig();
		envConfig.setAllowCreate(true);
		envConfig.setTransactional(true);
		env = new Environment(f, envConfig);
		StoreConfig storeConfig = new StoreConfig();
		storeConfig.setAllowCreate(true);
		storeConfig.setTransactional(true);
		store = new EntityStore(env, "Teste", storeConfig);
		keySet = new HashMap<PrimaryIndex<Long, ? extends IProxy>, GenericAdm<? extends IProxy>>();
	
	}
	
	@Override
	public <T extends IProxy> IComm<T> getIComm(Class<T> c) throws DatabaseException {
		PrimaryIndex<Long, T> key = (PrimaryIndex<Long, T>) store.getPrimaryIndex(Long.class, c);
		if(!keySet.containsKey(key)){
			keySet.put(key, new GenericAdm<T>(key, env));
		}
		return (IComm<T>) keySet.get(key);
	}

	@Override
	public void terminateComm() throws DatabaseException {
		Iterator<GenericAdm<? extends IProxy>> ite = keySet.values().iterator();
		while(ite.hasNext()){
			ite.next().abort();
		}
		store.close();
		env.close();
	}
}

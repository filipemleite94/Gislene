package COMM;

import java.util.ArrayList;
import java.util.HashMap;

import com.sleepycat.je.DatabaseException;

public class Map<P extends IProxy, O extends IStorableObject>{
	HashMap<P, O> mapObject = new HashMap<P,O>();
	HashMap<O, P> mapProxy = new HashMap<O, P>();
	HashMap<Long, O> mapKeyObject = new HashMap<Long, O>();
	IComm comm;
	
	public Map(IComm comm) throws DatabaseException, ClassNotFoundException{
		ArrayList<P> everything = comm.getEverythingFromTheDatabase();
		O object;
		this.comm = comm;
		for(P iterator : everything){
			this.constructObject(iterator);
			object = (O) iterator.construct();
			mapObject.put(iterator, object);
			mapKeyObject.put(iterator.getID(), object);
			mapProxy.put(object, iterator);
		}
	}
	
	public O getObject(P proxy){
		return mapObject.get(proxy);
	}
	
	public O getObject(Long key) throws DatabaseException{
		return mapKeyObject.get(key);
	}
	
	public P getProxy(O object){
		return mapProxy.get(object);
	}
	
	public O constructObject(P proxy) throws ClassNotFoundException, DatabaseException{
		O object;
		object = (O)proxy.construct();
		if(mapObject.get(proxy) == null){
			mapObject.put(proxy, object);
			mapProxy.put(object, proxy);
		}
		return object;
	}
	
	public void store(O object){
		P proxy;
		proxy = mapProxy.get(object);
		if(proxy == null){
			proxy = (P) eProxyClassMap.getNewProxy(object);
			mapObject.put(proxy, object);
			mapProxy.put(object, proxy);
		}
	}
	
	public Long getNewKey() throws DatabaseException{
		return comm.getUnusedKey();
	}
}
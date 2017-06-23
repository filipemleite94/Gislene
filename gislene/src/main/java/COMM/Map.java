package COMM;

import java.util.ArrayList;
import java.util.HashMap;

import com.sleepycat.je.DatabaseException;

public class Map<P extends IProxy, O extends IStorableObject>{
	HashMap<P, O> mapObject = new HashMap<P,O>();
	HashMap<O, P> mapProxy = new HashMap<O, P>();
	
	public Map(IComm comm) throws DatabaseException{
		ArrayList<P> everything = comm.getEverythingFromTheDatabase();
		for(P iterator : everything){
			this.constructObject(iterator);
		}
	}
	
	public O getObject(P proxy){
		return mapObject.get(proxy);
	}
	
	public P getProxy(O object){
		return mapProxy.get(object);
	}
	
	public O constructObject(P proxy){
		O object;
		object = (O)proxy.costruct();
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
}

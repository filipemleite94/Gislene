package COMM;

import com.sleepycat.je.DatabaseException;

import AOM.ITypePatternListener;
import AOM.Objeto;
import AOM.Type;

public abstract class aInterfacesMap {
	public static long getITypePatterListenerKey(ITypePatternListener iTypePatternListener){
		if(iTypePatternListener.getClass() == Type.class){
			return aProxyClassMap.typeMap.getProxy((Type) iTypePatternListener).getID();
		}
		if(iTypePatternListener.getClass() == Objeto.class){
			return aProxyClassMap.objetoMap.getProxy((Objeto) iTypePatternListener).getID();
		}
		return 0;
	}
	
	public static ITypePatternListener getITypePatternListener(long key){
		try {
			return aProxyClassMap.typeMap.getObject(key);
		} catch (DatabaseException e) {
			try {
				return aProxyClassMap.objetoMap.getObject(key);
			} catch (DatabaseException e1) {
				e.printStackTrace();
				e1.printStackTrace();
				return null;
			}
		}
	}
}

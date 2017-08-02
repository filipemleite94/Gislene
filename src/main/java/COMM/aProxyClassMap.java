package COMM;

import java.io.IOException;

import com.sleepycat.je.DatabaseException;

import AOM.Accountability;
import AOM.AccountabilityType;
import AOM.Category;
import AOM.Objeto;
import AOM.Property;
import AOM.PropertyType;
import AOM.Type;
import Proxies.PAccountability;
import Proxies.PAccountabilityType;
import Proxies.PCategory;
import Proxies.PObjeto;
import Proxies.PProperty;
import Proxies.PPropertyType;
import Proxies.PType;

public abstract class aProxyClassMap {
	public static Map<PAccountability, Accountability> accountabilityMap;
	public static Map<PAccountabilityType, AccountabilityType> accountabilityTypeMap;
	public static Map<PCategory,Category> categoryMap;
	public static Map<PObjeto,Objeto> objetoMap;
	public static Map<PProperty,Property> propertyMap;
	public static Map<PPropertyType, PropertyType> propertyTypeMap;
	public static Map<PType, Type> typeMap;
	
	public static IProxy getNewProxy(IStorableObject object){
		if(object.getClass() == Accountability.class){
			return new PAccountability();
		}
		if(object.getClass() == AccountabilityType.class){
			return new PAccountabilityType();
		}
		if(object.getClass() == Category.class){
			return new PCategory();
		}
		if(object.getClass() == Objeto.class){
			return new PObjeto();
		}
		if(object.getClass() == Property.class){
			return new PProperty();
		}
		if(object.getClass() == PropertyType.class){
			return new PPropertyType();
		}
		if(object.getClass() == Type.class){
			return new PType();
		}
		return null;
	}
	
	public static void eraseMaps(){
		accountabilityMap = null;
		accountabilityTypeMap = null;
		categoryMap = null;
		objetoMap = null;
		propertyMap = null;
		propertyTypeMap = null;
		typeMap = null;
	}
	
	public static void restart(ICommManager manager) throws DatabaseException, ClassNotFoundException, IOException{		
		accountabilityMap = new Map<PAccountability, Accountability>(manager.getIComm(PAccountability.class));
		accountabilityTypeMap = new Map<PAccountabilityType, AccountabilityType>(manager.getIComm(PAccountabilityType.class));
		categoryMap = new Map<PCategory,Category>(manager.getIComm(PCategory.class));
		objetoMap = new Map<PObjeto,Objeto>(manager.getIComm(PObjeto.class));
		propertyMap = new Map<PProperty,Property>(manager.getIComm(PProperty.class));
		propertyTypeMap = new Map<PPropertyType, PropertyType>(manager.getIComm(PPropertyType.class));
		typeMap = new Map<PType, Type>(manager.getIComm(PType.class));
	}
}

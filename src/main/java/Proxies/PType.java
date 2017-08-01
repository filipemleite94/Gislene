package Proxies;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.persist.model.DeleteAction;
import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;

import AOM.Accountability;
import AOM.AccountabilityType;
import AOM.Category;
import AOM.ITypePatternListener;
import AOM.Objeto;
import AOM.Property;
import AOM.PropertyType;
import AOM.Type;
import COMM.IProxy;
import COMM.IStorableObject;
import COMM.KeyGenerator;
import COMM.Map;
import COMM.eProxyClassMap;

@Entity
public class PType implements IProxy{
	@PrimaryKey
	private Long ID;
	@SecondaryKey(relate = Relationship.MANY_TO_ONE, relatedEntity = PCategory.class
			, onRelatedEntityDelete = DeleteAction.NULLIFY)
	private Long category;
	@SecondaryKey(relate = Relationship.MANY_TO_ONE, relatedEntity = PProperty.class
			, onRelatedEntityDelete = DeleteAction.NULLIFY)
	private Set<Long> properties;
	@SecondaryKey(relate = Relationship.ONE_TO_MANY, relatedEntity = PPropertyType.class
			, onRelatedEntityDelete = DeleteAction.NULLIFY)
	private Set<Long> propertyTypes = new HashSet<Long>();
	@SecondaryKey(relate = Relationship.ONE_TO_MANY, relatedEntity = PAccountability.class
			, onRelatedEntityDelete = DeleteAction.NULLIFY)
	private Set<Long> accountabilities = new HashSet<Long>();
	@SecondaryKey(relate = Relationship.ONE_TO_MANY, relatedEntity = PAccountabilityType.class
			, onRelatedEntityDelete = DeleteAction.NULLIFY)
	private Set<Long> accountabilityTypes = new HashSet<Long>();
	@SecondaryKey(relate = Relationship.ONE_TO_MANY, relatedEntity = PObjeto.class
			, onRelatedEntityDelete = DeleteAction.NULLIFY)
	private Set<Long> objetos = new HashSet<Long>();
	
	private String name;
	
	@Override
	public Long getID(){
		return ID;
	};
	
	@Override
	public void setID(){
		this.ID = KeyGenerator.getKey();
	}
	
	public PType(){
		if(ID==null){
			setID();
		}
	}
	
	public PType (Type type){
		Map<PPropertyType, PropertyType> pTypeMap = eProxyClassMap.propertyTypeMap;
		Map<PAccountabilityType, AccountabilityType> aTypeMap = eProxyClassMap.accountabilityTypeMap;
		Map<PObjeto, Objeto> objetoMap = eProxyClassMap.objetoMap;
		Map<PProperty, Property> propertyMap = eProxyClassMap.propertyMap;
		Map<PAccountability, Accountability> accountabilityMap = eProxyClassMap.accountabilityMap;
		
		name = type.getName();
		category = eProxyClassMap.categoryMap.getProxy((Category)type.getContainer()).getID();
		for(Property iterator:type.getProperties()){
			properties.add(propertyMap.getProxy(iterator).getID());
		}
		for(Accountability iterator:type.getAccountabilities()){
			accountabilities.add(accountabilityMap.getProxy(iterator).getID());
		}
		for(PropertyType iterator:type.getPropertyTypes()){
			propertyTypes.add(pTypeMap.getProxy(iterator).getID());
		}
		for(AccountabilityType iterator: type.getAccountabilityTypes()){
			accountabilityTypes.add(aTypeMap.getProxy(iterator).getID());
		}
		for(ITypePatternListener iterator: type.getTypeListeners()){
			objetos.add(objetoMap.getProxy((Objeto)iterator).getID());
		}
		
		setID();
	}

	@Override
	public IStorableObject construct() throws DatabaseException {
		Map<PPropertyType, PropertyType> pTypeMap = eProxyClassMap.propertyTypeMap;
		Map<PAccountabilityType, AccountabilityType> aTypeMap = eProxyClassMap.accountabilityTypeMap;
		Map<PObjeto, Objeto> objetoMap = eProxyClassMap.objetoMap;
		Map<PProperty, Property> propertyMap = eProxyClassMap.propertyMap;
		Map<PAccountability, Accountability> accountabilityMap = eProxyClassMap.accountabilityMap;
		
		Type type = new Type(name, eProxyClassMap.categoryMap.getObject(category));
		for(long iterator:properties){
			type.addProperty(propertyMap.getObject(iterator));
		}
		for(long iterator:accountabilities){
			type.addAccountability(accountabilityMap.getObject(iterator));
		}
		for(long iterator:propertyTypes){
			type.addPropertyType(pTypeMap.getObject(iterator));
		}
		for(long iterator: accountabilityTypes){
			type.addAccountabilityType(aTypeMap.getObject(iterator));
		}
		for(long iterator: objetos){
			type.addListener(objetoMap.getObject(iterator));
		}		
		
		return type;
	}

	@Override
	public boolean store(IStorableObject object) {
		Type type = (Type) object;
		Map<PPropertyType, PropertyType> pTypeMap = eProxyClassMap.propertyTypeMap;
		Map<PAccountabilityType, AccountabilityType> aTypeMap = eProxyClassMap.accountabilityTypeMap;
		Map<PObjeto, Objeto> objetoMap = eProxyClassMap.objetoMap;
		Map<PProperty, Property> propertyMap = eProxyClassMap.propertyMap;
		Map<PAccountability, Accountability> accountabilityMap = eProxyClassMap.accountabilityMap;
		
		name = type.getName();
		
		for(Property iterator:type.getProperties()){
			properties.add(propertyMap.getProxy(iterator).getID());
		}
		for(Accountability iterator:type.getAccountabilities()){
			accountabilities.add(accountabilityMap.getProxy(iterator).getID());
		}
		for(PropertyType iterator:type.getPropertyTypes()){
			propertyTypes.add(pTypeMap.getProxy(iterator).getID());
		}
		for(AccountabilityType iterator: type.getAccountabilityTypes()){
			accountabilityTypes.add(aTypeMap.getProxy(iterator).getID());
		}
		for(ITypePatternListener iterator: type.getTypeListeners()){
			objetos.add(objetoMap.getProxy((Objeto)iterator).getID());
		}
		
		return true;
	}
}

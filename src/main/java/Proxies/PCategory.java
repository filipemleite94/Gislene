package Proxies;

import java.util.HashSet;
import java.util.Set;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.persist.model.DeleteAction;
import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;

import AOM.AccountabilityType;
import AOM.Category;
import AOM.ITypePatternListener;
import AOM.PropertyType;
import AOM.Type;
import COMM.IProxy;
import COMM.IStorableObject;
import COMM.KeyGenerator;
import COMM.Map;
import COMM.eProxyClassMap;

@Entity
public class PCategory implements IProxy{
	@PrimaryKey
	private Long ID;
	@SecondaryKey(relate = Relationship.ONE_TO_MANY, relatedEntity = PPropertyType.class
			, onRelatedEntityDelete = DeleteAction.NULLIFY)
	private Set<Long> propertyTypes = new HashSet<Long>();
	@SecondaryKey(relate = Relationship.ONE_TO_MANY, relatedEntity = PType.class
			, onRelatedEntityDelete = DeleteAction.NULLIFY)
	private Set<Long> accountabilityTypes = new HashSet<Long>();
	@SecondaryKey(relate = Relationship.ONE_TO_MANY, relatedEntity = PType.class
			, onRelatedEntityDelete = DeleteAction.NULLIFY)
	private Set<Long> types = new HashSet<Long>();	
	
	private String name;
	
	@Override
	public Long getID(){
		return ID;
	};
	
	@Override
	public void setID(){
		this.ID = KeyGenerator.getKey();
	}
	
	public PCategory(){
		if(ID==null){
			setID();
		}
	}
	
	public PCategory(Category category){
		Map<PPropertyType, PropertyType> pTypeMap = eProxyClassMap.propertyTypeMap;
		Map<PAccountabilityType, AccountabilityType> aTypeMap = eProxyClassMap.accountabilityTypeMap;
		Map<PType, Type> typeMap = eProxyClassMap.typeMap;
		
		name = category.getName();
		for(PropertyType iterator:category.getPropertyTypes()){
			propertyTypes.add(pTypeMap.getProxy(iterator).getID());
		}
		for(AccountabilityType iterator:category.getAccountabilityTypes()){
			accountabilityTypes.add(aTypeMap.getProxy(iterator).getID());
		}
		for(ITypePatternListener iterator: category.getTypeListeners()){
			types.add(typeMap.getProxy((Type)iterator).getID());
		}
		setID();
	}
	
	@Override
	public IStorableObject construct() throws DatabaseException {
		Category category = null;
		Map<PPropertyType, PropertyType> pTypeMap = eProxyClassMap.propertyTypeMap;
		Map<PAccountabilityType, AccountabilityType> aTypeMap = eProxyClassMap.accountabilityTypeMap;
		Map<PType, Type> typeMap = eProxyClassMap.typeMap;
		
		category = new Category(name);
		
		for(long iterator : propertyTypes){
			category.addPropertyType(pTypeMap.getObject(iterator));
		}
		for(long iterator : accountabilityTypes){
			category.addAccountabilityType(aTypeMap.getObject(iterator));
		}
		for(long iterator : types){
			category.addListener(typeMap.getObject(iterator));
		}
		
		return category;
	}

	@Override
	public boolean store(IStorableObject object) {
		Map<PPropertyType, PropertyType> pTypeMap = eProxyClassMap.propertyTypeMap;
		Map<PAccountabilityType, AccountabilityType> aTypeMap = eProxyClassMap.accountabilityTypeMap;
		Map<PType, Type> typeMap = eProxyClassMap.typeMap;
		Category category = (Category) object;
		name = category.getName();
		for(PropertyType iterator:category.getPropertyTypes()){
			propertyTypes.add(pTypeMap.getProxy(iterator).getID());
		}
		for(AccountabilityType iterator:category.getAccountabilityTypes()){
			accountabilityTypes.add(aTypeMap.getProxy(iterator).getID());
		}
		for(ITypePatternListener iterator: category.getTypeListeners()){
			types.add(typeMap.getProxy((Type)iterator).getID());
		}
		
		return true;
	}
}

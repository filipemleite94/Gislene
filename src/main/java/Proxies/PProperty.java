package Proxies;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.persist.model.DeleteAction;
import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;

import AOM.Property;
import AOM.PropertyType;
import COMM.IProxy;
import COMM.IStorableObject;
import COMM.KeyGenerator;
import COMM.eProxyClassMap;

@Entity
public class PProperty implements IProxy{
	@PrimaryKey
	private Long ID;
	@SecondaryKey(relate = Relationship.MANY_TO_ONE, relatedEntity = PPropertyType.class
			, onRelatedEntityDelete = DeleteAction.NULLIFY)
	private Long propertyType;
	
	String value;
	
	@Override
	public Long getID(){
		return ID;
	};
	
	@Override
	public void setID(){
		this.ID = KeyGenerator.getKey();
	}
	
	public PProperty(){
		if(ID==null){
			setID();
		}
	}

	public PProperty(Property property){
		setID();
		propertyType = eProxyClassMap.propertyTypeMap.getProxy(property.getPropertyType()).getID();
		value = property.getValue().toString();
	}
	
	@Override
	public IStorableObject construct() throws DatabaseException {
		PropertyType pType = eProxyClassMap.propertyTypeMap.getObject(propertyType);
		if(pType!=null){
			return new Property(pType);
		}
		throw new DatabaseException("PropertyType relacionada não foi gravada");
	}

	@Override
	public boolean store(IStorableObject object) {
		Property property = (Property) object;
		propertyType = eProxyClassMap.propertyTypeMap.getProxy(property.getPropertyType()).getID();
		value = property.getValue().toString();
		return true;
	};
}

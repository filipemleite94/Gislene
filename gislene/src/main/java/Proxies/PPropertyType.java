package Proxies;

import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;

import AOM.PropertyType;
import COMM.IProxy;
import COMM.IStorableObject;

@Entity
public class PPropertyType implements IProxy {
	@PrimaryKey(sequence = "seq")
	private Long ID;
	
	private String name;
	private String className;
	
	public Long getID(){
		return ID;
	};
	
	public PPropertyType(){}
	
	public PPropertyType(PropertyType propertyType){
		name = propertyType.getNameVariable();
		className = propertyType.getClassName();
	}

	@Override
	public IStorableObject construct() throws ClassNotFoundException {
		PropertyType pType = new PropertyType(name, className);
		return pType;
	}

	@Override
	public boolean store(IStorableObject object) {
		PropertyType pType = (PropertyType) object;
		name = pType.getNameVariable();
		className = pType.getClassName();
		return true;
	}
}

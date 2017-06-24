package Proxies;

import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;

import AOM.PropertyType;
import COMM.IProxy;
import COMM.IStorableObject;
import COMM.KeyGenerator;

@Entity
public class PPropertyType implements IProxy {
	@PrimaryKey
	private Long ID;
	
	private String name;
	private String className;
	
	@Override
	public Long getID(){
		return ID;
	};
	
	@Override
	public void setID(){
		this.ID = KeyGenerator.getKey();
	}
	
	public PPropertyType(){
		if(ID==null){
			setID();
		}
	}
	
	public PPropertyType(PropertyType propertyType){
		setID();
		name = propertyType.getName();
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
		name = pType.getName();
		className = pType.getClassName();
		return true;
	}
}

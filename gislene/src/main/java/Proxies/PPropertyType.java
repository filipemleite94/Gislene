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
		
	}

	@Override
	public IStorableObject costruct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void store(IStorableObject object) {
		// TODO Auto-generated method stub
		
	}
}

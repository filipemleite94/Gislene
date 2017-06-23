package Proxies;

import com.sleepycat.persist.model.DeleteAction;
import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;

import AOM.Property;
import COMM.IProxy;
import COMM.IStorableObject;

@Entity
public class PProperty implements IProxy{
	@PrimaryKey(sequence = "seq")
	private Long ID;
	@SecondaryKey(relate = Relationship.MANY_TO_ONE, relatedEntity = PPropertyType.class
			, onRelatedEntityDelete = DeleteAction.NULLIFY)
	private Long propertyType;
	
	String value;
	
	public Long getID(){
		return ID;
	};
	
	public PProperty(){}

	public PProperty(Property property){
		
	}
	
	@Override
	public IStorableObject costruct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void store(IStorableObject object) {
		// TODO Auto-generated method stub
		
	};
}

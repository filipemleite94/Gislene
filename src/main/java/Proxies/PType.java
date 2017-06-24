package Proxies;

import java.util.HashSet;
import java.util.Set;

import com.sleepycat.persist.model.DeleteAction;
import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;

import AOM.Type;
import COMM.IProxy;
import COMM.IStorableObject;
import COMM.KeyGenerator;

@Entity
public class PType implements IProxy{
	@PrimaryKey
	private Long ID;
	
	@SecondaryKey(relate = Relationship.MANY_TO_ONE, relatedEntity = PProperty.class
			, onRelatedEntityDelete = DeleteAction.NULLIFY)
	private Long properties;
	@SecondaryKey(relate = Relationship.ONE_TO_MANY, relatedEntity = PPropertyType.class
			, onRelatedEntityDelete = DeleteAction.NULLIFY)
	private Set<Long> propertyTypes = new HashSet<Long>();
	
	@SecondaryKey(relate = Relationship.ONE_TO_MANY, relatedEntity = PAccountability.class
			, onRelatedEntityDelete = DeleteAction.NULLIFY)
	private Set<Long> accountabilities = new HashSet<Long>();
	
	@SecondaryKey(relate = Relationship.ONE_TO_MANY, relatedEntity = PAccountabilityType.class
			, onRelatedEntityDelete = DeleteAction.NULLIFY)
	private Set<Long> accountabilityTypes = new HashSet<Long>();
	
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
		setID();
	}

	@Override
	public IStorableObject construct() {
		return null;
	}

	@Override
	public boolean store(IStorableObject object) {
		return false;
	}
}

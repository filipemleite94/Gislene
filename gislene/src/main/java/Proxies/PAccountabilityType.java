package Proxies;

import com.sleepycat.persist.model.DeleteAction;
import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;

import AOM.AccountabilityType;
import COMM.IProxy;
import COMM.IStorableObject;

@Entity
public class PAccountabilityType implements IProxy {
	@PrimaryKey(sequence = "seq")
	private Long ID;
	
	@SecondaryKey(relate = Relationship.MANY_TO_MANY, relatedEntity = PAccountability.class
			, onRelatedEntityDelete = DeleteAction.NULLIFY)
	private Long reciprocal;
	
	private String name;
	
	public Long getID(){
		return ID;
	};
	
	public PAccountabilityType(){}
	
	public PAccountabilityType(AccountabilityType accountabilityType){
		
	};
	
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

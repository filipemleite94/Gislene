package Proxies;

import com.sleepycat.persist.model.DeleteAction;
import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;

import AOM.AccountabilityType;
import COMM.IProxy;
import COMM.IStorableObject;
import COMM.eProxyClassMap;

@Entity
public class PAccountabilityType implements IProxy {
	@PrimaryKey(sequence = "seq")
	private Long ID;
	
	@SecondaryKey(relate = Relationship.ONE_TO_ONE, relatedEntity = PAccountability.class
			, onRelatedEntityDelete = DeleteAction.NULLIFY)
	private Long reciprocal;
	
	private String name;
	
	public Long getID(){
		return ID;
	};
	
	public PAccountabilityType(){}
	
	public PAccountabilityType(AccountabilityType accountabilityType){
		reciprocal = eProxyClassMap.accountabilityTypeMap.getProxy(accountabilityType).getID();
		name = accountabilityType.getNameVariable();
	};
	
	@Override
	public IStorableObject construct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean store(IStorableObject object) {
		return false;
	};
}

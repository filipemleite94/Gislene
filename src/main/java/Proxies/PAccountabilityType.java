package Proxies;

import com.sleepycat.persist.model.DeleteAction;
import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;

import AOM.AccountabilityType;
import COMM.IProxy;
import COMM.IStorableObject;
import COMM.KeyGenerator;
import COMM.eProxyClassMap;

@Entity
@SuppressWarnings(value = "unused")
public class PAccountabilityType implements IProxy {
	@PrimaryKey
	private Long ID;
	
	@SecondaryKey(relate = Relationship.ONE_TO_ONE, relatedEntity = PAccountability.class
			, onRelatedEntityDelete = DeleteAction.NULLIFY)
	private Long reciprocal;
	
	private String name;
	
	@Override
	public Long getID(){
		return ID;
	};
	
	@Override
	public void setID(){
		this.ID = KeyGenerator.getKey();
	}
	
	public PAccountabilityType(){
		if(ID==null){
			setID();
		}
	}
	
	public PAccountabilityType(AccountabilityType accountabilityType){
		setID();
		reciprocal = eProxyClassMap.accountabilityTypeMap.getProxy(accountabilityType).getID();
		name = accountabilityType.getName();
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

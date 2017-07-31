package Proxies;

import java.util.HashSet;
import java.util.Set;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.persist.model.DeleteAction;
import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;

import AOM.Accountability;
import AOM.AccountabilityType;
import AOM.ITypePatternListener;
import AOM.Objeto;
import AOM.Type;
import COMM.IProxy;
import COMM.IStorableObject;
import COMM.KeyGenerator;
import COMM.Map;
import COMM.aInterfacesMap;
import COMM.eProxyClassMap;

@Entity
public class PAccountability implements IProxy{
	@PrimaryKey
	private Long ID;
	
	@SecondaryKey(relate = Relationship.MANY_TO_ONE, relatedEntity = PAccountabilityType.class,
			onRelatedEntityDelete = DeleteAction.NULLIFY)
	private Long accountabilityType;
	@SecondaryKey(relate = Relationship.ONE_TO_MANY, relatedEntity = Object.class,
			onRelatedEntityDelete = DeleteAction.NULLIFY)
	private Set<Long> child = new HashSet<Long>();
	@SecondaryKey(relate = Relationship.ONE_TO_ONE)
	private Long parent;
	
	@Override
	public Long getID(){
		return ID;
	};
	
	@Override
	public void setID(){
		this.ID = KeyGenerator.getKey();
	};
	
	public PAccountability(){
		if(ID == null){
			setID();
		}
	};
	
	public PAccountability(Accountability account){
		long i, size;
		Map<PAccountabilityType, AccountabilityType> accountTypeMap = eProxyClassMap.accountabilityTypeMap;
		accountabilityType = accountTypeMap.getProxy
				((AccountabilityType)account.getAccountabilityType()).getID();
		//For now, it will only be one son
		size = 1;
		for(i = 0; i < size; i++){
			child.add(aInterfacesMap.getITypePatterListenerKey(account.getChild()));
		}
		parent = aInterfacesMap.getITypePatterListenerKey(account.getParent());
		setID();
	};

	@Override
	public IStorableObject construct() throws DatabaseException {
		AccountabilityType accountType;
		ITypePatternListener child, parent;
		Accountability account;
		accountType = eProxyClassMap.accountabilityTypeMap.getObject(accountabilityType);
		child = aInterfacesMap.getITypePatternListener(this.child.iterator().next());
		parent = aInterfacesMap.getITypePatternListener(this.parent);
		account = new Accountability(accountType, parent);
		account.setChild(child);
		return account;
	};

	@Override
	public boolean store(IStorableObject object) {
		long i, size;
		Accountability account = (Accountability) object;
		Map<PAccountabilityType, AccountabilityType> accountTypeMap = eProxyClassMap.accountabilityTypeMap;
		
		accountabilityType = accountTypeMap.getProxy
				((AccountabilityType)account.getAccountabilityType()).getID();
		//For now, it will only be one son
		size = 1;
		child = new HashSet<Long>();
		for(i = 0; i < size; i++){
			child.add(aInterfacesMap.getITypePatterListenerKey(account.getChild()));
		}
		parent = aInterfacesMap.getITypePatterListenerKey(account.getParent());
		return true;
	};
}

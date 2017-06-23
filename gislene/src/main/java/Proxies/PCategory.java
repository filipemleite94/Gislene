package Proxies;

import java.util.HashSet;
import java.util.Set;

import com.sleepycat.persist.model.DeleteAction;
import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;

import AOM.Category;
import COMM.IProxy;
import COMM.IStorableObject;

@Entity
public class PCategory implements IProxy{
	@PrimaryKey(sequence = "seq")
	private Long ID;
	@SecondaryKey(relate = Relationship.ONE_TO_MANY, relatedEntity = PPropertyType.class
			, onRelatedEntityDelete = DeleteAction.NULLIFY)
	private Set<Long> propertyTypes = new HashSet<Long>();
	@SecondaryKey(relate = Relationship.ONE_TO_MANY, relatedEntity = PType.class
			, onRelatedEntityDelete = DeleteAction.NULLIFY)
	private Set<Long> types = new HashSet<Long>();
	
	public Long getID(){
		return ID;
	};
	
	public PCategory(){}
	
	public PCategory(Category category){
		
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

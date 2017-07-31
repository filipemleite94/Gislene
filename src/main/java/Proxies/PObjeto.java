package Proxies;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.persist.model.DeleteAction;
import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;

import AOM.Accountability;
import AOM.Objeto;
import AOM.Property;
import COMM.IProxy;
import COMM.IStorableObject;
import COMM.KeyGenerator;
import COMM.Map;
import COMM.eProxyClassMap;

@Entity
public class PObjeto implements IProxy{
	@PrimaryKey
	Long ID;
	@SecondaryKey(relate = Relationship.MANY_TO_ONE, relatedEntity = PType.class
			, onRelatedEntityDelete = DeleteAction.NULLIFY)
	private Long type;
	@SecondaryKey(relate = Relationship.ONE_TO_MANY, relatedEntity = PProperty.class 
			, onRelatedEntityDelete = DeleteAction.NULLIFY)
	private Set<Long> properties = new HashSet<Long>();
	@SecondaryKey(relate = Relationship.ONE_TO_MANY, relatedEntity = PAccountability.class
			, onRelatedEntityDelete = DeleteAction.NULLIFY)
	private Set<Long> accountabilities;
	
	private String name;
	private String posicao;
	
	@Override
	public Long getID(){
		return ID;
	};
	
	@Override
	public void setID(){
		this.ID = KeyGenerator.getKey();
	}
	
	public PObjeto(){
		if(ID==null){
			setID();
		}
	}
	
	public PObjeto(Objeto objeto) throws IOException{
		Map<PProperty, Property> propertyMap = eProxyClassMap.propertyMap;
		Map<PAccountability, Accountability> accountabilityMap = eProxyClassMap.accountabilityMap;
		HashSet<Property> propertiesSet = objeto.getProperties();
		HashSet<Accountability> accountabilitiesSet = objeto.getAccountabilities();
		
		name = objeto.getName();
		posicao = objeto.getGeo().getPointsString();
		type = eProxyClassMap.objetoMap.getProxy(objeto).getID();
		
		for(Property iterator : propertiesSet){
			properties.add(propertyMap.getProxy(iterator).getID());
		}
		for(Accountability iterator : accountabilitiesSet){
			accountabilities.add(accountabilityMap.getProxy(iterator).getID());
		}
		setID();
	}

	@Override
	public IStorableObject construct() throws IOException, DatabaseException {
		Objeto objeto = null;
		Map<PProperty, Property> propertyMap = eProxyClassMap.propertyMap;
		Map<PAccountability, Accountability> accountabilityMap = eProxyClassMap.accountabilityMap;
		objeto = new Objeto(name, posicao, eProxyClassMap.typeMap.getObject(type));
		for(long iterator: properties){
			objeto.addProperty(propertyMap.getObject(iterator));
		}
		for(long iterator: accountabilities){
			objeto.addAccountability(accountabilityMap.getObject(iterator));
		}
		return objeto;
	}

	@Override
	public boolean store(IStorableObject object) {
		Objeto objeto = (Objeto) object;
		Map<PProperty, Property> propertyMap = eProxyClassMap.propertyMap;
		Map<PAccountability, Accountability> accountabilityMap = eProxyClassMap.accountabilityMap;
		HashSet<Property> propertiesSet = objeto.getProperties();
		HashSet<Accountability> accountabilitiesSet = objeto.getAccountabilities();
		
		name = objeto.getName();
		try{
			posicao = objeto.getGeo().getPointsString();
		}catch(IOException e){
			e.printStackTrace();
		}
		type = eProxyClassMap.objetoMap.getProxy(objeto).getID();
		
		for(Property iterator : propertiesSet){
			properties.add(propertyMap.getProxy(iterator).getID());
		}
		for(Accountability iterator : accountabilitiesSet){
			accountabilities.add(accountabilityMap.getProxy(iterator).getID());
		}
		return true;
	}
}

package AOM;

public class Type extends PropertyTypeContainer implements PropertyTypeListener {

	public Type(String name) {
		super(name);
	}
	
	@Override
	public void erase() {
		super.deleteContainer();
	}

	@Override
	public boolean addProperty(PropertyType pType, Object value) {
		return false;
	}

	@Override
	public boolean removeProperty(PropertyType pType) {
		return false;
	}
}

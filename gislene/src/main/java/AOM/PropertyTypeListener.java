package AOM;

public interface PropertyTypeListener {
	public boolean addProperty(PropertyType pType, Object value);
	public boolean removeProperty(PropertyType pType);
	public void erase();
}

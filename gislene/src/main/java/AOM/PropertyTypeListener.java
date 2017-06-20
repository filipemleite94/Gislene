package AOM;

import java.io.IOException;

public interface PropertyTypeListener {
	//Property receberá um valor default;
	public void addProperty(PropertyType pType);
	public void setProperty(PropertyType pType, Object value) throws IOException;
	public void addProperty(PropertyType pType, Object value) throws IOException;
	public void removeProperty(PropertyType pType) throws IOException;
	public void erase();
}

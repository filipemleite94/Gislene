package AOM;

import java.io.IOException;

public class Type extends PropertyTypeContainer implements PropertyTypeListener {

	public Type(String name) {
		super(name);
	}
	
	@Override
	public void erase() {
		super.deleteContainer();
	}

	@Override
	public void addProperty(PropertyType pType, Object value) throws IOException{
		return;
	}

	@Override
	public void removeProperty(PropertyType pType) throws IOException{
		return;
	}

	@Override
	public void addProperty(PropertyType pType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setProperty(PropertyType pType, Object value) throws IOException {
		// TODO Auto-generated method stub
		
	}
}

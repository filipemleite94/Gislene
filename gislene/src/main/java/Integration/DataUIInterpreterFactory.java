package Integration;

import java.io.IOException;

public class DataUIInterpreterFactory {
	String UIKey;
	
	final String JSONKey = "JSONKey";
	
	DataUIInterpreterFactory(String UIKey){
		this.UIKey = UIKey;
	}
	
	public void setUIKey(String UIKey){
		this.UIKey = UIKey;
	}
	
	public IDataUIInterpreter getDataUIInterpreter() throws IOException{
		if(UIKey.equals(JSONKey)){
			return new JSONInterpreter();
		}
		throw new IOException("UI nameKey nao reconhecida");
	}
}

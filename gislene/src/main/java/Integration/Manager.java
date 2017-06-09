package Integration;

import java.io.IOException;

public class Manager {
	final String UIKey = "JSONKey";
	final String DBKey = "firebird";
	
	final IDataBaseComm DB;
	final IDataUIInterpreter UI;
	
	public Manager() throws IOException{
		DB = (new DataBaseCommFactory(DBKey)).getDataBaseComm();
		UI = (new DataUIInterpreterFactory(UIKey)).getDataUIInterpreter();
		DB.initiateDataBaseComm();
	}
	
	public boolean isValidChange(){
		return true;
	}
	
	public void receiveUIUpdate(){
		if(!isValidChange()){
			throw new RuntimeException("Update can't be made");
		}
		return;
	}
	
	public void doUpdate(){
		
	}
}

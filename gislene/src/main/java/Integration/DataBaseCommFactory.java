package Integration;

import java.io.IOException;

public class DataBaseCommFactory {
	String DBKey;
	
	final String firebirdKey = "firebird";
	
	DataBaseCommFactory(String DBKey){
		this.DBKey = DBKey;
	}
	
	public void setDataBaseKey(String DBKey){
		this.DBKey = DBKey;
	}
	
	public IDataBaseComm getDataBaseComm() throws IOException{
		if(DBKey.equals(firebirdKey)){
			return new FireBirdComm();
		}
		throw new IOException("Database nameKey nao reconhecida");
	}
}

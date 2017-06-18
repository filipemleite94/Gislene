package Integration;

import gislene.GPSManager;

public class FireBirdComm implements IDataBaseComm {
	GPSManager firebirdDB;
	String DBName = "Gislene";
	
	public FireBirdComm(){
		firebirdDB = new GPSManager();
		
	}
	
	public void initiateDataBaseComm() {
		
	}

	public boolean addDataContent(String content, String key) {
		return false;
	}

	public boolean removeDataContent(String key) {
		return false;
	}

	public void getAllDataBaseContent() {		
	}

}

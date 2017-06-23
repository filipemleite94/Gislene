package Integration;

public interface IDataBaseComm {
	void initiateDataBaseComm();
	boolean addDataContent(String content, String key);
	boolean removeDataContent(String key);
	void getAllDataBaseContent();
}

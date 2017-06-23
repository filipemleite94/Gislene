package COMM;

import java.util.ArrayList;

import com.sleepycat.je.DatabaseException;

public interface IComm<T extends IProxy> {
	public ArrayList<T> getEverythingFromTheDatabase() throws DatabaseException;
	public void persistInDatabase(T proxy) throws DatabaseException;
	public T getUpdateFromDataBase(T proxy) throws DatabaseException;
	public T getFromID(Long key) throws DatabaseException;
	public Long getUnusedKey() throws DatabaseException;
	void deleteFromDB(Long key) throws DatabaseException;
	void deleteEverything() throws DatabaseException;
	void storeInDBNoOverwrite(T proxy) throws DatabaseException;
}

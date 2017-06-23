package COMM;

import java.util.ArrayList;

import com.sleepycat.je.DatabaseException;

public interface IComm<T extends IProxy> {
	public ArrayList<T> getEverythingFromTheDatabase() throws DatabaseException;
	public void persistInDatabase(T proxy) throws DatabaseException;
	public T getUpdateFromDataBase(T proxy) throws DatabaseException;
}

package COMM;

import java.io.File;

import com.sleepycat.je.DatabaseException;

public interface ICommManager {
	public void InitiateComm(File f, String Name) throws DatabaseException;
	public <T extends IProxy> IComm<T> getIComm(Class<T> c) throws DatabaseException;
	public void terminateComm() throws DatabaseException;
}

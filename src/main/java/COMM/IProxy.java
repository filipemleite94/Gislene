package COMM;

import java.io.IOException;

import com.sleepycat.je.DatabaseException;

public interface IProxy {
	public Long getID();
	public IStorableObject construct() throws ClassNotFoundException, DatabaseException, IOException;
	public boolean store(IStorableObject object);
	public void setID();
}

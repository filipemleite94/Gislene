package COMM;

import com.sleepycat.je.DatabaseException;

public interface IProxy {
	public Long getID();
	public IStorableObject construct() throws ClassNotFoundException, DatabaseException;
	public boolean store(IStorableObject object);
}

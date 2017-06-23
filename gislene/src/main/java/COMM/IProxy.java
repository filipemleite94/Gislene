package COMM;

public interface IProxy {
	public Long getID();
	public IStorableObject costruct();
	public void store(IStorableObject object);
}

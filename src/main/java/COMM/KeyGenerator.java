package COMM;

public abstract class KeyGenerator {
	private static long ID = 1;
	private static long limit = Long.MAX_VALUE;
	public static void setID(Long storedID){
		if(ID > ID){
			ID = storedID;
		}
	}
	
	public static long getKey(){
		long ans = ID;
		ID = (ID +1)%(limit);
		return ans;
	}
}

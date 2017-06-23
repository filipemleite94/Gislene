package Integration;

public class JSONInterpreter implements IDataUIInterpreter{
	JSONParser parser;
	
	public boolean initiateUIInterpreter() {
		parser = new JSONParser();
		return false;
	}

}

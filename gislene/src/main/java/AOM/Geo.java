package AOM;

import java.io.IOException;

import com.sleepycat.persist.model.Persistent;

public class Geo {
	private String wayPoints;
	
	public Geo(){
		//falta implementar direito
		wayPoints = "0°0\"0\' 0°0\"0\'";
	}
	
	public Geo(String input) throws IOException{
		//falta implementar direito
		this.wayPoints = input;
	}
	
	public String getPointsString(){
		return wayPoints;
	}
}

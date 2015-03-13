package my.first.game.utils;

import my.first.game.beans.ConnectionBean;
import my.first.game.beans.LocationBean;

public class ConnectionGenerator {

	private static ConnectionGenerator connectionGenerator = null;

	private ConnectionGenerator(){}

	public static ConnectionGenerator getInstance(){
		if(ConnectionGenerator.connectionGenerator == null){
			ConnectionGenerator.connectionGenerator = new ConnectionGenerator();
		}
		return ConnectionGenerator.connectionGenerator;
	}

	public void createConnection(LocationBean locationA, LocationBean locationB, int distance){
		locationA.addConnection(new ConnectionBean(locationB, distance));
		locationB.addConnection(new ConnectionBean(locationA, distance));
	}
}

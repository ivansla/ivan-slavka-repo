package my.first.game.beans;

public class ConnectionBean {

	private LocationBean location;
	private int distance;

	public ConnectionBean(LocationBean location, int distance){
		this.location = location;
		this.distance = distance;
	}

	public LocationBean getLocationBean(){
		return this.location;
	}
}

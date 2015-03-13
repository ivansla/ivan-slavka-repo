package my.first.game.beans;

import java.util.ArrayList;
import java.util.List;

import my.first.game.enums.LocationEnum;
import my.first.game.enums.LocationServiceEnum;

public class LocationBean {

	private LocationEnum locationName;
	private List<LocationEnum> possibleLocationList = new ArrayList<LocationEnum>();
	private List<ConnectionBean> connectionList = new ArrayList<ConnectionBean>();
	private List<LocationServiceBean> locationServiceList = new ArrayList<LocationServiceBean>();

	private int minMonsterLevel;
	private int maxMonsterLevel;
	private boolean hasTransport;

	public LocationBean(LocationEnum locationName){
		this.locationName = locationName;
	}

	public void addConnection(ConnectionBean connection){
		this.connectionList.add(connection);
		this.possibleLocationList.add(connection.getLocationBean().getLocationName());
	}

	public void addLocationService(LocationServiceEnum locationService){
		this.locationServiceList.add(new LocationServiceBean(locationService));
	}

	public List<LocationEnum> getPossibleLocationList(){
		return this.possibleLocationList;
	}

	public LocationEnum getLocationName(){
		return this.locationName;
	}

	public List<String> getLocationServicesList(){
		// TODO: this needs to be optimized
		List<String> list = new ArrayList<String>();
		for(LocationServiceBean locationService : this.locationServiceList){
			list.add(locationService.getServiceName().toString());
		}

		return list;
	}
}

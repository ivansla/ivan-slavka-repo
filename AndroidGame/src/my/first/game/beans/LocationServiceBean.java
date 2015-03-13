package my.first.game.beans;

import my.first.game.enums.LocationServiceEnum;

public class LocationServiceBean {

	private LocationServiceEnum serviceName;

	public LocationServiceBean(LocationServiceEnum serviceName){
		this.serviceName = serviceName;
	}

	public LocationServiceEnum getServiceName() {
		return this.serviceName;
	}

	public void setServiceName(LocationServiceEnum serviceName) {
		this.serviceName = serviceName;
	}
}

package my.first.game.beans;

import java.util.HashMap;

import my.first.game.enums.LocationEnum;
import my.first.game.enums.LocationServiceEnum;
import my.first.game.utils.ConnectionGenerator;

public class WorldGenerator {

	private static WorldGenerator world = null;

	private ConnectionGenerator connectionGenerator = ConnectionGenerator.getInstance();
	private HashMap<LocationEnum, LocationBean> regionMap = new HashMap<LocationEnum, LocationBean>();

	private WorldGenerator(){

		// TODO: toto sa bude musiet uplne zmenit aby sa svet vytvaral z nejakej sablony alebo nieco

		// Ruzinov
		LocationBean astronomicka = new LocationBean(LocationEnum.RUZINOV_ASTRONOMICKA);
		this.regionMap.put(LocationEnum.RUZINOV_ASTRONOMICKA, astronomicka);
		LocationBean strkovec = new LocationBean(LocationEnum.RUZINOV_STRKOVEC);
		this.regionMap.put(LocationEnum.RUZINOV_STRKOVEC, strkovec);
		LocationBean saleziani = new LocationBean(LocationEnum.RUZINOV_SALEZIANI);
		this.regionMap.put(LocationEnum.RUZINOV_SALEZIANI, saleziani);

		// Nove Mesto
		LocationBean trnavskeMyto = new LocationBean(LocationEnum.NOVE_MESTO_TRNAVSKE_MYTO);
		this.regionMap.put(LocationEnum.NOVE_MESTO_TRNAVSKE_MYTO, trnavskeMyto);
		LocationBean kuchajda = new LocationBean(LocationEnum.NOVE_MESTO_KUCHAJDA);
		this.regionMap.put(LocationEnum.NOVE_MESTO_KUCHAJDA, kuchajda);
		LocationBean racianskeMyto = new LocationBean(LocationEnum.NOVE_MESTO_RACIANSKE_MYTO);
		this.regionMap.put(LocationEnum.NOVE_MESTO_RACIANSKE_MYTO, racianskeMyto);

		// Stare Mesto
		LocationBean kamenneNamestie = new LocationBean(LocationEnum.STARE_MESTO_KAMENNE_NAMESTIE);
		this.regionMap.put(LocationEnum.STARE_MESTO_KAMENNE_NAMESTIE, kamenneNamestie);
		LocationBean stefanka = new LocationBean(LocationEnum.STARE_MESTO_STEFANKA);
		this.regionMap.put(LocationEnum.STARE_MESTO_STEFANKA, stefanka);
		LocationBean hrad = new LocationBean(LocationEnum.STARE_MESTO_HRAD);
		this.regionMap.put(LocationEnum.STARE_MESTO_HRAD, hrad);

		// Connections
		this.connectionGenerator.createConnection(astronomicka, strkovec, 1);
		this.connectionGenerator.createConnection(strkovec, saleziani, 1);
		this.connectionGenerator.createConnection(strkovec, kuchajda, 1);
		this.connectionGenerator.createConnection(trnavskeMyto, racianskeMyto, 1);
		this.connectionGenerator.createConnection(trnavskeMyto, kuchajda, 1);
		this.connectionGenerator.createConnection(trnavskeMyto, kamenneNamestie, 1);
		this.connectionGenerator.createConnection(kamenneNamestie, stefanka, 1);
		this.connectionGenerator.createConnection(kamenneNamestie, hrad, 1);

		// Temporary location services in all locations
		for(LocationBean location : this.regionMap.values()){
			location.addLocationService(LocationServiceEnum.WEAPON_MERCHANT);
			location.addLocationService(LocationServiceEnum.ARMOR_MERCHANT);
		}
	}

	public static WorldGenerator getInstance(){
		if(WorldGenerator.world == null){
			WorldGenerator.world = new WorldGenerator();
		}
		return WorldGenerator.world;
	}

	public LocationBean getLocation(LocationEnum location){
		return this.regionMap.get(location);
	}
}

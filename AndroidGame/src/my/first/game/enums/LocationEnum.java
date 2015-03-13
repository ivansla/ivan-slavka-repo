package my.first.game.enums;

import java.util.ArrayList;
import java.util.List;

public enum LocationEnum {

	RUZINOV_ASTRONOMICKA(0),
	RUZINOV_STRKOVEC(1),
	RUZINOV_SALEZIANI(2),

	NOVE_MESTO_TRNAVSKE_MYTO(10),
	NOVE_MESTO_KUCHAJDA(11),
	NOVE_MESTO_RACIANSKE_MYTO(12),

	STARE_MESTO_KAMENNE_NAMESTIE(20),
	STARE_MESTO_STEFANKA(21),
	STARE_MESTO_HRAD(22);

	private int value;
	private static List<String> locationList = null;

	private LocationEnum(int value){
		this.value = value;
	}

	public static List<String> getLocationStringList(){
		if(LocationEnum.locationList == null){
			LocationEnum.locationList = new ArrayList<String>();
			for(LocationEnum location : LocationEnum.values()){
				LocationEnum.locationList.add(location.toString());
			}
		}
		return LocationEnum.locationList;
	}

	@Override
	public String toString(){
		switch(this.value){
		case 0:
			return "Astronomicka";
		case 1:
			return "Strkovec";
		case 2:
			return "Saleziani";
		case 10:
			return "Trnavske Myto";
		case 11:
			return "Kuchajda";
		case 12:
			return "Racianske Myto";
		case 20:
			return "Kamenne Namestie";
		case 21:
			return "Stefanka";
		case 22:
			return "Hrad";
		default:
			return "Lost!!!";
		}
	}

	public static LocationEnum toEnum(String stringToEnum){

		if(stringToEnum.equals("Astronomicka")){
			return RUZINOV_ASTRONOMICKA;
		} else if(stringToEnum.equals("Strkovec")){
			return RUZINOV_STRKOVEC;
		} else if(stringToEnum.equals("Saleziani")){
			return RUZINOV_SALEZIANI;
		} else if(stringToEnum.equals("Trnavske Myto")){
			return NOVE_MESTO_TRNAVSKE_MYTO;
		} else if(stringToEnum.equals("Kuchajda")){
			return NOVE_MESTO_KUCHAJDA;
		} else if(stringToEnum.equals("Racianske Myto")){
			return NOVE_MESTO_RACIANSKE_MYTO;
		} else if(stringToEnum.equals("Kamenne Namestie")){
			return STARE_MESTO_KAMENNE_NAMESTIE;
		} else if(stringToEnum.equals("Stefanka")){
			return STARE_MESTO_STEFANKA;
		} else if(stringToEnum.equals("Hrad")){
			return STARE_MESTO_HRAD;
		} else {
			return RUZINOV_ASTRONOMICKA;
		}
	}
}

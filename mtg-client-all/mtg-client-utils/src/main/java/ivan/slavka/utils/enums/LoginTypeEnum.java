package ivan.slavka.utils.enums;

import ivan.slavka.utils.exceptions.ParseEnumException;

import java.util.ArrayList;
import java.util.List;

public enum LoginTypeEnum {

	LOGIN,
	LOGOUT;

	public static LoginTypeEnum parse(String loginType) throws ParseEnumException{
		if(loginType.equalsIgnoreCase("login")){
			return LOGIN;
		} else if(loginType.equalsIgnoreCase("logout")){
			return LOGOUT;
		} else {
			throw new ParseEnumException(getEnumValues());
		}
	}

	private static List<String> getEnumValues(){
		List<String> permitedEnumValues = new ArrayList<String>();
		for(LoginTypeEnum loginTypeEnum : LoginTypeEnum.values()){
			permitedEnumValues.add(loginTypeEnum.toString());
		}
		return permitedEnumValues;
	}
}

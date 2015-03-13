package ivan.slavka.utils.enums;

import ivan.slavka.utils.exceptions.ParseEnumException;

import java.util.ArrayList;
import java.util.List;

public enum InvitationTypeEnum {

	REQUEST,
	RESPONSE;

	public static InvitationTypeEnum parse(String answer) throws ParseEnumException{
		if(answer.equalsIgnoreCase("request")){
			return REQUEST;
		} else if(answer.equalsIgnoreCase("response")){
			return RESPONSE;
		} else {
			throw new ParseEnumException(getEnumValues());
		}
	}

	private static List<String> getEnumValues(){
		List<String> permitedEnumValues = new ArrayList<String>();
		for(InvitationTypeEnum invitationAnswerEnum : InvitationTypeEnum.values()){
			permitedEnumValues.add(invitationAnswerEnum.toString());
		}
		return permitedEnumValues;
	}
}

package ivan.slavka.utils.enums;

import ivan.slavka.utils.exceptions.ParseEnumException;

import java.util.ArrayList;
import java.util.List;

public enum InvitationAnswerEnum {

	YES,
	NO;

	public static InvitationAnswerEnum parse(String answer) throws ParseEnumException{
		if(answer.equalsIgnoreCase("yes")){
			return YES;
		} else if(answer.equalsIgnoreCase("no")){
			return NO;
		} else {
			throw new ParseEnumException(getEnumValues());
		}
	}

	private static List<String> getEnumValues(){
		List<String> permitedEnumValues = new ArrayList<String>();
		for(InvitationAnswerEnum invitationAnswerEnum : InvitationAnswerEnum.values()){
			permitedEnumValues.add(invitationAnswerEnum.toString());
		}
		return permitedEnumValues;
	}
}

package ivan.slavka.utils.exceptions;

import java.util.List;

public class ParseEnumException extends Exception{

	List<String> permitedEnumValues;

	public ParseEnumException(List<String> permitedEnumValues){
		this.permitedEnumValues = permitedEnumValues;
	}

	@Override
	public String getMessage(){
		String message = "Error while parsing into enumerator. Permited enumerator values are: ";

		for(String permitedEnum : this.permitedEnumValues){
			message += "[" + permitedEnum + "]";
		}
		return message;
	}
}

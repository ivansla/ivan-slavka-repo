package ivan.slavka.utils.exceptions;


public class MessageValidationException extends Exception{

	private String errorMessage = "### ERROR ### - ";

	public MessageValidationException(String message){
		this.errorMessage += message;
	}

	@Override
	public String getMessage(){
		return this.errorMessage;
	}
}

package exceptions;

//insufficient Bank Account funds exception.
public class InsufficientFundsException extends Throwable{
	public String msg; // error message
	
	public InsufficientFundsException(){
		this.msg = "Error: Insufficient Bank Account Funds!";
	}
	
	public InsufficientFundsException(String msg){
		this.msg = msg;
	}
}
package exceptions;

public class InvalidRomanException extends RuntimeException  {
	
	public InvalidRomanException() {
		super("N�mero de letras(X,I,M ou C) inv�lido");
	}
	

}

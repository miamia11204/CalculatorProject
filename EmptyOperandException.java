/**
 * Program Name: EmptyOperandException.java
 * Purpose: Handle Empty Operand Exception
 * Coder: Ngoc Minh Anh Nguyen & Hanh Bui
 * Date: Jul 9, 2023
 */

public class EmptyOperandException extends Exception
{
	public EmptyOperandException() 
	{
		super("Operand is empty!");
	}
}
//end class
/**
 * Program Name: InvalidOperandException.java
 * Purpose: Handle Invalid Operand Exception
 * Coder: Ngoc Minh Anh Nguyen & Hanh Bui
 * Date: Jul 9, 2023
 */

public class InvalidOperandException extends Exception
{
	public InvalidOperandException() 
	{
		super("Operand is empty!");
	}
}
//end class
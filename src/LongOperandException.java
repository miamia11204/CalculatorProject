/**
 * Program Name: LongOperandException.java
 * Purpose: Handle Long Operand Exception
 * Coder: Ngoc Minh Anh Nguyen & Hanh Bui
 * Date: Jul 9, 2023
 */

public class LongOperandException extends Exception
{
	public LongOperandException() 
	{
		super("Operand is too long for the text field display area!");
	}
}
//end class
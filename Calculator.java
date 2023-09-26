import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Program Name: Calculator.java
 * Purpose: A class to be used to create a Calculator object
 * Coder: Ngoc Minh Anh Nguyen & Hanh Bui
 * Date: Jul 9, 2023
 */

public class Calculator
{
	//Declare properties
	private String operand;
	private String operator;
	private boolean decimalPressed ;
	private ArrayList <String> list;
	
	//this boolean is to determine if the new operand is the result of a previous calculation
	//if it is, the method buildOperand will not be in effect, just the operators are in effect
	private boolean isOperandResult = false;
	
	//For the no-arg constructor, initialize all fields to their default values
	public Calculator()
	{
		this.operand = "";
		this.operator = "";
		this.decimalPressed = false;
		this.list = new ArrayList <String>();
	}
	
	//Setter and Getter methods
	public void setOperand(String operand)
	{
		this.operand = operand;
	}
	public void setOperator(String operator)
	{
		this.operator = operator;
	}
	public void setDecimalPressed(boolean decimalPressed )
	{
		this.decimalPressed  = decimalPressed ;
	}
	
	public String getOperand()
	{
		return this.operand;
	}
	public String getOperator()
	{
		return this.operator;
	}
	public boolean getDecimalPressed()
	{
		return this.decimalPressed;
	}
	
	
	public boolean isOperandResult()
	{
		return isOperandResult;
	}

	public void setOperandResult(boolean isOperandResult)
	{
		this.isOperandResult = isOperandResult;
	}

	/*
	 *   Method Name:  clear()
	 *       Purpose:  re-set all fields to their default values
	 *       Accepts:  nothing
	 *       Returns:  void
	 */
	public void clear() 
	{
		this.operand = "";
		this.operator = "";
		this.decimalPressed = false;
		this.list = new ArrayList <String>();
		this.isOperandResult = false;
	}
	
	/*
	 *   Method Name:  backspace()
	 *       Purpose:  Return value with the last number or decimal removed, if value is not empty
	 *       Accepts:  String
	 *       Returns:  String
	 */
	public String backspace(String value) throws EmptyOperandException
	{
		if (value.isEmpty())
		{
			throw new EmptyOperandException();
		}
		if(value != "0.0") {
			if(value.charAt(value.length() - 1) == '.') 
			{
				this.decimalPressed = false;
			}
			value = value.substring(0, value.length()-1);
		}
		return value;
	}
	
	//decimal format object to display double values
	DecimalFormat df = new DecimalFormat("0.0############");
	
	/*
	 *   Method Name:  findPercentage()
	 *       Purpose:  Return the current value as a percentage in decimal format, if value is not empty
	 *       Accepts:  String
	 *       Returns:  String
	 */
	public String findPercentage(String value) throws EmptyOperandException
	{
		if (value.isEmpty())
		{
			throw new EmptyOperandException();
		}

		double doubleVal = Double.parseDouble(value);
		String formatted = df.format(doubleVal / 100);
		return formatted;
	}
	
	
	/*
	 *   Method Name:  findPercentage()
	 *       Purpose:  Add “-“ to the beginning of operand or remove “-“ from the operand, 
	 *			 depending on the	value of flag
	 *       Accepts:  boolean
	 *       Returns:  String
	 */
	public void togglePlusMinus(boolean flag) throws EmptyOperandException 
	{	
		if (this.operand.isEmpty())
		{
			throw new EmptyOperandException();
		}
		//suppose when there is a '-' sign at the beginning, flag is true
		if(flag == true) {
			this.operand = this.operand.substring(1);
			flag = false;
		}
		else {
			this.operand = "-" + this.operand;
			flag = true;
		}
	}
	
	/*
	 *   Method Name:  findSquared()
	 *       Purpose:  Return the square of the current value
	 *       Accepts:  String
	 *       Returns:  String
	 */
	public String findSquared(String value) throws EmptyOperandException
	{
		if (value.isEmpty())
		{
			throw new EmptyOperandException();
		}
		double doubleVal = Double.parseDouble(value);
		String formatted = df.format(Math.pow(doubleVal, 2));
		return formatted;
	}
	
	/*
	 *   Method Name:  findSquareRoot()
	 *       Purpose:  Return the square root of the current value
	 *       Accepts:  String
	 *       Returns:  String
	 */
	public String findSquareRoot(String value) throws EmptyOperandException
	{
		if (value.isEmpty())
		{
			throw new EmptyOperandException();
		}
		double doubleVal = Double.parseDouble(value);
		String formatted = df.format(Math.sqrt(doubleVal));
		return formatted;
	}
	
	
	/*
	 *   Method Name:  buildOperand()
	 *       Purpose:  Concatenate the current value to operand
	 *       						Ensure that an operand doesn’t become too long
	 *       Accepts:  String
	 *       Returns:  String
	 */
	public void buildOperand(String value) throws LongOperandException
	{
		if(this.isOperandResult == false) {
			if (this.operand.length() > 17)
			{
				throw new LongOperandException();
			}
			if (value == ".")
			{
				//in case if there is already one "." in the list, dont add
				if (!this.decimalPressed)
				{
					this.decimalPressed = true;
					this.operand += value;
				}
			}
			else 
			{
				this.operand += value;
			}
		}
		//if isOperandResult == true
		else
		{
			this.operand = value;
			if (value == ".")
			{
				this.decimalPressed = true;
			}
			else
			{
				this.decimalPressed = false;
			}
			this.isOperandResult = false;
		}
	}
	
	/*
	 *   Method Name:  buildExpression()
	 *       Purpose:  assign the value to operator and add both the operand and operator to the list
	 *       						clear fields
	 *       Accepts:  String
	 *       Returns:  void
	 */
	public void buildExpression(String value) throws EmptyOperandException
	{
			
			if (value.isEmpty())
			{
				throw new EmptyOperandException();
			}
			this.operator = value;
			this.decimalPressed = false;
			this.list.add(this.operand); 
			this.list.add(this.operator); 
			this.operand = "";
			this.operator = "";
			this.isOperandResult = false;
	}
	
	/*
	 *   Method Name:  calculate()
	 *       Purpose:   1. add the current operand to the list
	 *									2. calculate the answer, using the values in the list applying the rules of BEDMAS
	 *									3. clear fields
	 *									4. return the answer to the expression
	 *       Accepts:  nothing
	 *       Returns:  double
	 */
	double calculate() throws EmptyOperandException, ArithmeticException
	{		
		if (this.operand.isEmpty())
		{
			throw new EmptyOperandException();
		}
		list.add(this.operand);
    double result = 0.0;
    int listSize = list.size();
    
    // first round, perform * or /
    for (int i = 1; i < listSize - 1; ++i) {
        if (list.get(i).equals("*") || list.get(i).equals("/")) {
            if (list.get(i).equals("*")) {
                result = Double.parseDouble(list.get(i - 1)) * Double.parseDouble(list.get(i + 1));
            } else {
            	  if(Double.parseDouble(list.get(i + 1)) == 0) 
            	  {
            	  	throw new ArithmeticException();
            	  }
                result = Double.parseDouble(list.get(i - 1)) / Double.parseDouble(list.get(i + 1));
            }

            // assign the result to the left operand
            list.set(i - 1, Double.toString(result));
            // remove the operator and the right operand from the list, after the calc
            list.remove(i); // remove the operator
            list.remove(i); // remove the right operand
            listSize -= 2;
            i -= 2; // adjust the index
        }
    }

    // second round, perform + or -
    for (int i = 1; i < listSize - 1; ++i) {
        if (list.get(i).equals("+") || list.get(i).equals("-")) {
            if (list.get(i).equals("+")) {
                result = Double.parseDouble(list.get(i - 1)) + Double.parseDouble(list.get(i + 1));
            } else {
                result = Double.parseDouble(list.get(i - 1)) - Double.parseDouble(list.get(i + 1));
            }

            // assign the result to the left operand
            list.set(i - 1, Double.toString(result));
            // remove the operator and the right operand from the list, after the calc
            list.remove(i); // remove the operator
            list.remove(i); // remove the right operand
            listSize -= 2;
            i -= 2; // adjust the index
        }
    }
    this.clear();
		String formatted = df.format(result);
    return Double.parseDouble(formatted);
	}//end method
	
	/*
	 *   Method Name:  convertHex()
	 *       Purpose:  Return value converted to its hexadecimal equivalent
	 *       Accepts:  String
	 *       Returns:  String
	 */
	public String convertHex(String value) throws EmptyOperandException,	LongOperandException
	{
		if (this.operand.length() > 17)
		{
			throw new LongOperandException();
		}
		
		if (this.operand.isEmpty()) {
			throw new EmptyOperandException();
		}
		
		int dec = (int) Math.round(Double.parseDouble(value));
		// Special case for zero
		if (dec == 0) 
		{
      return "0"; 
		}
		StringBuilder hexBuilder = new StringBuilder(); 
		
    while (dec > 0) 
    {
        int remainder = dec % 16; // Get the remainder
        char hexChar;
        // Convert remainder to a hexadecimal character
        if (remainder >= 0 && remainder <= 9)
        {
        	hexChar = (char) (remainder + '0');
        }
        else
        {
        	hexChar = (char) (remainder - 10 + 'A');
        }
        hexBuilder.insert(0, hexChar); // Insert the hexadecimal character at the beginning of the string
        dec /= 16; //next iteration
    }
    return hexBuilder.toString();
	}
	
  /*
	 *   Method Name:  convertBin()
	 *       Purpose:  Return value converted to its binary equivalent
	 *       Accepts:  String
	 *       Returns:  String
	 */
	public String convertBin(String value) throws EmptyOperandException, LongOperandException
	{
		if (this.operand.length() > 17)
		{
			throw new LongOperandException();
		}
		
		if (this.operand.isEmpty()) {
			throw new EmptyOperandException();
		}
		
		int dec = (int) Math.round(Double.parseDouble(value));
    // Special case for zero
    if (dec == 0) {
        return "0";
    }
    StringBuilder binaryBuilder = new StringBuilder();
    
    while (dec > 0) {
        int remainder = dec % 2; // Get the remainder 
        binaryBuilder.insert(0, remainder); // Insert the binary digit
        dec /= 2; //next iteration
    }
    
    for (int i = binaryBuilder.length(); i >= 0; i -= 4) {
    	binaryBuilder.insert(i,  " ");
    }
    
    return binaryBuilder.toString();
	}
	
  /*
	 *   Method Name:  convertOct()
	 *       Purpose:  Return value converted to its octal equivalent
	 *       Accepts:  String
	 *       Returns:  String
	 */
	public String convertOct(String value) throws EmptyOperandException, LongOperandException
	{
		if (this.operand.length() > 17)
		{
			throw new LongOperandException();
		}
		
		if (this.operand.isEmpty()) {
			throw new EmptyOperandException();
		}
		
		int dec = (int) Math.round(Double.parseDouble(value));
    // Special case for zero
    if (dec == 0) {
        return "0";
    }
    StringBuilder octalBuilder = new StringBuilder(); 
    
    while (dec > 0) {
        int remainder = dec % 8; // Get the remainder
        octalBuilder.insert(0, remainder); // Insert the octal digit
        dec /= 8; // next iteration
    }
    return octalBuilder.toString();
	}
	
  /*
	 *   Method Name:  toString()
	 *       Purpose:  Return a String representation of a Calculator object
	 *       Accepts:  nothing
	 *       Returns:  String
	 */
  @Override
  public String toString() 
  {
  	String listToPrint = "";
  	for (String element : this.list)
  	{
  		listToPrint += element;
  	}
      return "This calculator is calculating this: " + listToPrint;
  }
	
	
}
//end class
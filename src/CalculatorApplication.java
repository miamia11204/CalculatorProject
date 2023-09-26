import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Program Name: CalculatorApplication.java
 * Purpose: An application class which instantiates a Calculator object and performs calculator operations on that object
 * Coder: Ngoc Minh Anh Nguyen & Hanh Bui
 * Date: Jul 9, 2023
 */

public class CalculatorApplication extends JFrame
{
	MainCalculator calculator;

	JMenuBar menuBar;
	JMenu file, convert, help;
	JRadioButtonMenuItem  hex, dec, oct, bin;
	JMenuItem exit, about, howToUse;
	JTextField screen;
	JPanel btnContainer, scrnContainer;
	JButton clearBtn, backspaceBtn;
	JButton percentageBtn, changeSignBtn, squareBtn, squareRootBtn, divisionBtn, subtractBtn, addBtn, multiBtn;
	JButton blankBtn, anoBlankBtn;
	JButton zeroBtn, oneBtn, twoBtn, threeBtn, fourBtn, fiveBtn, sixBtn, sevenBtn, eightBtn, nineBtn;
	JButton dotBtn;
	JButton equalBtn;
	
	//Constructor
	public CalculatorApplication() 
	{
		super("Calculator");

		calculator = new MainCalculator();
		
		menuBar = new JMenuBar();
		screen = new JTextField();
		btnContainer = new JPanel();
		
		clearBtn = new JButton("C");
		backspaceBtn = new JButton("⌫");
		percentageBtn = new JButton("%");
		changeSignBtn = new JButton("±");
		squareBtn = new JButton("x²");
		squareRootBtn = new JButton("√");
		multiBtn = new JButton("*");
		divisionBtn = new JButton("/");
		subtractBtn = new JButton("-");
		addBtn = new JButton("+");
		blankBtn = new JButton("");
		anoBlankBtn = new JButton("");
		zeroBtn = new JButton("0");
		oneBtn = new JButton("1");
		twoBtn = new JButton("2");
		threeBtn = new JButton("3");
		fourBtn = new JButton("4");
		fiveBtn = new JButton("5");
		sixBtn = new JButton("6");
		sevenBtn = new JButton("7");
		eightBtn = new JButton("8");
		nineBtn = new JButton("9");
		dotBtn = new JButton(".");
		equalBtn = new JButton("=");
		
		//create a Font object for both the text field and the buttons
		Font font = new Font("SansSerif", Font.PLAIN, 22);
		screen.setFont(font);
		zeroBtn.setFont(font);
		oneBtn.setFont(font);
		twoBtn.setFont(font);
		threeBtn.setFont(font);
		fourBtn.setFont(font);
		fiveBtn.setFont(font);
		sixBtn.setFont(font);
		sevenBtn.setFont(font);
		eightBtn.setFont(font);
		nineBtn.setFont(font);
		dotBtn.setFont(font);
		
		
		Font font1 = new Font("SansSerif", Font.BOLD, 24);
		clearBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 28));
		backspaceBtn.setFont(font1);
		
		percentageBtn.setFont(font1);
		changeSignBtn.setFont(font1);
		squareBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		squareRootBtn.setFont(font1);
		equalBtn.setFont(font1);
		multiBtn.setFont(font1);
		divisionBtn.setFont(font1);
		subtractBtn.setFont(font1);
		addBtn.setFont(font1);
		
		applyButtonStyles(clearBtn, backspaceBtn, percentageBtn, changeSignBtn, squareBtn, squareRootBtn, divisionBtn, subtractBtn, addBtn, multiBtn,
				blankBtn, anoBlankBtn, zeroBtn, oneBtn, twoBtn, threeBtn, fourBtn, fiveBtn, sixBtn, sevenBtn, eightBtn, nineBtn, dotBtn, equalBtn);

		screen.setText("0.0");
		screen.setBackground(Color.white);
		screen.setEditable(false);
		screen.setFont(font);
		screen.setHorizontalAlignment(JTextField.RIGHT);
		
		btnContainer.add(clearBtn);
		btnContainer.add(backspaceBtn);
		btnContainer.add(percentageBtn);
		btnContainer.add(changeSignBtn);
		
		btnContainer.add(squareBtn);
		btnContainer.add(squareRootBtn);
		btnContainer.add(blankBtn);
		btnContainer.add(divisionBtn);
		
		btnContainer.add(sevenBtn);
		btnContainer.add(eightBtn);
		btnContainer.add(nineBtn);
		btnContainer.add(multiBtn);
		
		btnContainer.add(fourBtn);
		btnContainer.add(fiveBtn);
		btnContainer.add(sixBtn);
		btnContainer.add(subtractBtn);
		
		btnContainer.add(oneBtn);
		btnContainer.add(twoBtn);
		btnContainer.add(threeBtn);
		btnContainer.add(addBtn);
		
		btnContainer.add(anoBlankBtn);
		btnContainer.add(zeroBtn);
		btnContainer.add(dotBtn);
		btnContainer.add(equalBtn);
		
		GridBagLayout gridBag = new GridBagLayout();
		this.setLayout(gridBag);
		btnContainer.setLayout(new GridLayout(6, 4, 8, 8));
		screen.setSize(380,100);
		menuBar.setPreferredSize(new Dimension(280, 26));
		
		Insets padding = new Insets(5, 5, 5, 5);
    //set GridBagConstraints
    GridBagConstraints gridBagConst = new GridBagConstraints();
    //for menu
    gridBagConst.gridx = 0;
    gridBagConst.gridy = 0;
    gridBagConst.fill = GridBagConstraints.BOTH;
    gridBagConst.insets = padding;
    gridBag.setConstraints(menuBar, gridBagConst);

    //for text field
    gridBagConst.gridx = 0;
    gridBagConst.gridy = 1;
    gridBagConst.fill = GridBagConstraints.BOTH;
    gridBagConst.insets = padding;
    gridBag.setConstraints(screen, gridBagConst);
		
		//for btn
		gridBagConst.gridx = 0;
		gridBagConst.gridy = 2;
		gridBagConst.fill = GridBagConstraints.BOTH;
		gridBag.setConstraints(btnContainer, gridBagConst);
		
		this.setJMenuBar(menuBar);
		this.add(screen);
		this.add(btnContainer);
		
		//File: Exit
		file = new JMenu ("Field");
		exit = new JMenuItem ("Exit");
		file.add(exit);
		menuBar.add(file);

		
		//Convert: Hex, Dec, Oct, Bin
		convert = new JMenu ("Convert");
		hex = new JRadioButtonMenuItem ("Hex");
		dec = new JRadioButtonMenuItem ("Dec");
		dec.setSelected(true);
		oct = new JRadioButtonMenuItem ("Oct");
		bin = new JRadioButtonMenuItem ("Bin");
		
		
		convert.add(hex);
		convert.add(dec);
		convert.add(oct);
		convert.add(bin);
		menuBar.add(convert);
		//Radio buttons are mutually exclusive
		ButtonGroup convertGrp = new ButtonGroup();
		convertGrp.add(hex);
		convertGrp.add(dec);
		convertGrp.add(oct);
		convertGrp.add(bin);
		
		//About: How to Use,About
		help = new JMenu("Help");
		howToUse = new JMenuItem("How to Use");
		about = new JMenuItem("About");
		help.add(howToUse); 
		help.add(about);
		menuBar.add(help);
		
		this.setSize(300, 410);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add ActionListeners to the menu items
		//Exit
		exit.addActionListener((ActionEvent ae) -> this.dispose());
		//Display instructions
		howToUse.addActionListener((ActionEvent ev) -> 
		JOptionPane.showMessageDialog(this, "1. Perform Calculations by clicking on the numbers and operators\n"
				+ "2. If you make a mistake, use the \"C\" button to clear\n"
				+ "Other Functions are in the menu:\n"
				+ "• Exit: Exit the application\n"
				+ "• Hex, Dec, Oct, Bin: Convert the current display to hex, dec, bin, and oct numbers\n"
				+ "• How to Use: Display instruction\n"
				+ "• About: About the application")
		);
		//Display about
		about.addActionListener((ActionEvent ev) -> 
		JOptionPane.showMessageDialog(this, "This calculator this created by Hanh Bui and Nguyen Anh.\n"
				+ "Calculator is a user-friendly and versatile calculator application designed to ultimate your\nmathematical calculations on your PC. "
				+ "\nWhether you need to perform basic arithmetic, handle complex equations, or access advanced\nfunctions, Calculator has you covered with its intuitive interface and robust capabilities.")
		);
		
		
		//clear button
		clearBtn.addActionListener((ActionEvent ev) ->
		{
		 calculator.clear();
		 screen.setText("0.0");
		 dec.setSelected(true);
		});
		
		
		//back space
		backspaceBtn.addActionListener((ActionEvent ev) ->
		{
			try 
			{
				String newString = calculator.backspace(calculator.getOperand());
				calculator.setOperand(newString);
				if (calculator.getOperand().isEmpty())
        {
            screen.setText("0.0");
        }
        else
        {
            screen.setText(newString);
        }
			}
			catch (InvalidOperandException ex)
			{
				screen.setText("0.0");
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}
		});
		
		
		//find percentage
		percentageBtn.addActionListener((ActionEvent ev) ->
		{
			try 
			{
				String newString = calculator.findPercentage(calculator.getOperand());
				calculator.setOperand(newString);
				screen.setText(newString);
			}
			catch (InvalidOperandException ex)
			{
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}
		});
		
		
		//toggle Plus/Minus
		changeSignBtn.addActionListener((ActionEvent ev) ->
		{
			try 
			{
			//suppose when there is a '-' sign at the beginning, flag is true
				boolean flag = true;
				String newString = calculator.getOperand();
				if(!newString.isEmpty() && newString.charAt(0) != '-')
				{
					flag = false;
				}
				calculator.togglePlusMinus(flag);
				screen.setText(calculator.getOperand());
			}
			catch (InvalidOperandException ex)
			{
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}
		});
		
		
		//find square value
		squareBtn.addActionListener((ActionEvent ev) ->
		{
			try 
			{
				String newString = calculator.findSquared(calculator.getOperand());
				calculator.setOperand(newString);
				screen.setText(newString);
			}
			catch (InvalidOperandException ex)
			{
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}
		});
		
		
		//find square root value
		squareRootBtn.addActionListener((ActionEvent ev) ->
		{
			try 
			{
				String newString = calculator.findSquareRoot(calculator.getOperand());
				calculator.setOperand(newString);
				screen.setText(newString);
			}
			catch (InvalidOperandException ex)
			{
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}
		});
		
		//Add an ActionListener to each of the number buttons and the 
		//decimal button to call Calculator’s buildOperand method  
		
		//Add an ActionListener to each of the number buttons and 
		//the decimal button to call Calculator’s buildOperand method 
    numberButtonListener(zeroBtn);
    numberButtonListener(oneBtn);
    numberButtonListener(twoBtn);
    numberButtonListener(threeBtn);
    numberButtonListener(fourBtn);
    numberButtonListener(fiveBtn);
    numberButtonListener(sixBtn);
    numberButtonListener(sevenBtn);
    numberButtonListener(eightBtn);
    numberButtonListener(nineBtn);
    numberButtonListener(dotBtn);
    
    //Add an ActionListener to each of the operator buttons to call Calculator’s buildExpression() method 
    //Ensure that an operator can only be clicked once for a binary expression
    operatorButtonListener(subtractBtn);
    operatorButtonListener(addBtn);
    operatorButtonListener(multiBtn);
    operatorButtonListener(divisionBtn);
  
    //hex. bin, oct converters
    bin.addActionListener((ActionEvent ev) -> 
    {
    	try {
    		screen.setText(calculator.convertBin(calculator.getOperand()));
    	} catch (InvalidOperandException ex) {
    		JOptionPane.showMessageDialog(this, ex.getMessage());
    		
			} catch (LongOperandException lx) {
				JOptionPane.showMessageDialog(this, lx.getMessage());
				calculator.clear();
				screen.setText("0.0");
			}
    }
    );
    
    
    hex.addActionListener((ActionEvent ev) -> 
    {
    	try {
    		screen.setText(calculator.convertHex(calculator.getOperand()));
    	} catch (InvalidOperandException ex) {
    		JOptionPane.showMessageDialog(this, ex.getMessage());
    		
			} catch (LongOperandException lx) {
				JOptionPane.showMessageDialog(this, lx.getMessage());
				calculator.clear();
				screen.setText("0.0");
			}
    }
    );
    
    
    oct.addActionListener((ActionEvent ev) -> 
    {
    	try {
    		screen.setText(calculator.convertOct(calculator.getOperand()));
    	} catch (InvalidOperandException ex) {
    		JOptionPane.showMessageDialog(this, ex.getMessage());
    		
			} catch (LongOperandException lx) {
				JOptionPane.showMessageDialog(this, lx.getMessage());
				calculator.clear();
				screen.setText("0.0");
			}
    }
    );
    
    
    dec.addActionListener((ActionEvent ev) -> {
    	if(!calculator.getOperand().isEmpty()) {
    		screen.setText(calculator.getOperand());
    	} else {
    		screen.setText("0.0");
    	}
    });
    
    
    
    //add action listener to the equal button and call the Calculator's calculate method
    equalBtn.addActionListener((ActionEvent ev) ->
		{
			try 
			{
				double result = calculator.calculate();
				calculator.setOperand(Double.toString(result));
				
				//if the operand already has a decimal '.' in it, the setDecimalPressed will become true, to prevent adding one more
				if(calculator.getOperand().indexOf('.') != -1) {
					calculator.setDecimalPressed(true);
				}
				screen.setText(Double.toString(result));
				
				//tell the flag that the operand is the result of the previous calculation
				//therefore do not accept any digits into the operand
				calculator.setOperandResult(true);
				
			}
			catch (InvalidOperandException ex)
			{
				JOptionPane.showMessageDialog(this, ex.getMessage());
				calculator.clear();
				screen.setText("0.0");
			}
			catch (ArithmeticException ex)
			{
				calculator.clear();
				JOptionPane.showMessageDialog(this, "You cannot divide by zero!!!");
				screen.setText("0.0");
			}
		});
    
		
}//end constructor




	/*
	 *   Method Name:  numberButtonListener()
	 *       Purpose:  call buildOperand for each number button
	 *       Accepts:  String
	 *       Returns:  void
	 */
	
	private void numberButtonListener(JButton button) {
	  button.addActionListener((ActionEvent ev) -> {
	  	
	  	if(calculator.isOperandResult() == true) {
	  		
	  	}
	  	
	      String buttonValue = button.getText();
	      try {
	      	
	          calculator.buildOperand(buttonValue);
	          screen.setText(calculator.getOperand());
	      } catch (LongOperandException lx) {
	          JOptionPane.showMessageDialog(this, lx.getMessage());
	          calculator.clear();
	          screen.setText("0.0");
	      }
	  });
	}

	/*
	*   Method Name:  operatorButtonListener()
	*       Purpose:  call buildOperand for each number button
	*       Accepts:  String
	*       Returns:  void
	*/
	private void operatorButtonListener(JButton button) {
	  button.addActionListener((ActionEvent ev) -> {
	  	if(!calculator.getOperand().isEmpty()) {
	  		String buttonValue = button.getText();
	      try {
	          calculator.buildExpression(buttonValue);
	          screen.setText(calculator.getOperator());
	      } catch (InvalidOperandException ex) {
	          JOptionPane.showMessageDialog(this, ex.getMessage());
	      }
	  	}
	  });
	} // end method

	
	/*
	*   Method Name:  applyButtonStyles
	*       Purpose:  to create a set of styles that will be used for the buttons passed in
	*       Accepts:  JButton objects
	*       Returns:  void
	*/
	private void applyButtonStyles(JButton... buttons) {
    for (JButton button : buttons) {
    	 // Create a rounded border with a specified corner radius
      Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
      Border emptyBorder = BorderFactory.createEmptyBorder(3, 10, 3, 10);
      CompoundBorder compoundBorder = new CompoundBorder(lineBorder, emptyBorder);
      button.setBorder(compoundBorder);
      button.setBackground(Color.WHITE);
      button.setFocusable(false);
  }
    
}

	public static void main(String[] args) 
	{
		CalculatorApplication calculatorApp = new CalculatorApplication();
	}
}//end class

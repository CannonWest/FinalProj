import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

public class AddPersonScreen extends JPanel {
    private JRadioButton empBut;
    private JRadioButton cusBut;
    private JLabel jcomp3;
    private JLabel jcomp4;
    private JLabel jcomp5;
    private JTextField jcompEmail;
    private JTextField jcompID;
    private JTextField jcompName;
    private JTextField jcompPhone;
    private JLabel jcomp10;
    private JTextField jcomp11;
    private JLabel jcomp12;
    
    private JButton addPerson;
    
    private static JFrame frame;

    public AddPersonScreen(JFrame frameM) {
        this.frame = frameM;
    	//construct components
        empBut = new JRadioButton ("Employee");
        cusBut = new JRadioButton ("Customer");
        jcomp3 = new JLabel ("ID");
        jcomp4 = new JLabel ("Name");
        jcomp5 = new JLabel ("Number");
        jcompEmail = new JTextField (5);
        jcompID = new JTextField (5);
        jcompName = new JTextField (5);
        jcompPhone = new JTextField (5);
        jcomp10 = new JLabel ("Email");
        jcomp11 = new JTextField (5);
        jcomp12 = new JLabel ("Salary");
        addPerson = new JButton ("Add Person");

        ButtonGroup buttonG = new ButtonGroup();
        buttonG.add(empBut);
        buttonG.add(cusBut);
        
        empBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	jcomp11.setEnabled(true);
            	jcomp12.setEnabled(true);
            }
        });
        
        cusBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	jcomp11.setEnabled(false);
            	jcomp12.setEnabled(false);
            }
        });
        
        addPerson.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	List<JTextField> list = new ArrayList<JTextField>();
        
            	list.add(jcompID);
            	list.add(jcompName);
            	list.add(jcompPhone);
            	list.add(jcompEmail);
            	
            	boolean cont = true;
            	for(JTextField tf : list)	{
            		if (tf.getText().equals(""))
            			cont = false;
            	}
            	if(!cont)	{}
            	else	{
	            	
	            	if (empBut.isSelected())	{
	            		list.add(jcomp11);
	            		for(JTextField tf : list)	{
	                		if (tf.getText().equals(""))
	                			cont = false;
	                	}
	            		if(!cont)	{}
	            		else	{
	            			addEmployeeToDB(list);
	            			frame.dispose();
	            		}
	            	}
	        
	            	if (cusBut.isSelected())	{
	            		addCustomerToDB(list);
	            		frame.dispose();
	            	}
            	}
            }
        });
        
        //adjust size and set layout
        setPreferredSize (new Dimension (944, 563));
        setLayout (null);

        //add components
        add (empBut);
        add (cusBut);
        add (jcomp3);
        add (jcomp4);
        add (jcomp5);
        add (jcompEmail);
        add (jcompID);
        add (jcompName);
        add (jcompPhone);
        add (jcomp10);
        add (jcomp11);
        add (jcomp12);
        add (addPerson);

        //set component bounds (only needed by Absolute Positioning)
        empBut.setBounds (10, 10, 100, 25);
        cusBut.setBounds (10, 35, 100, 25);
        jcomp3.setBounds (150, 5, 100, 25);
        jcomp4.setBounds (280, 5, 100, 25);
        jcomp5.setBounds (410, 5, 100, 25);
        jcompEmail.setBounds (565, 30, 130, 25);
        jcompID.setBounds (150, 30, 100, 25);
        jcompName.setBounds (280, 30, 100, 25);
        jcompPhone.setBounds (410, 30, 120, 25);
        jcomp10.setBounds (565, 5, 100, 25);
        jcomp11.setBounds (725, 30, 130, 25);
        jcomp12.setBounds (725, 5, 100, 25);
        addPerson.setBounds(10, 70, 100, 25);
    }

    private void addEmployeeToDB(List<JTextField> list)	{
    	SQLConnection sqlCon = new SQLConnection();
    	sqlCon.openConnection();
    	ArrayList<String> arrList= new ArrayList<String>();
    	for(JTextField jtf : list)	{
    		arrList.add(jtf.getText());
    	}
    	sqlCon.insertEmployee(arrList);
    	sqlCon.closeConnection();
    }
    
    private void addCustomerToDB(List<JTextField> list)	{
    	SQLConnection sqlCon = new SQLConnection();
    	sqlCon.openConnection();
    	ArrayList<String> arrList= new ArrayList<String>();
    	for(JTextField jtf : list)	{
    		arrList.add(jtf.getText());
    	}
    	sqlCon.insertCustomer(arrList);
    
    	sqlCon.closeConnection();
    }

	public static void main (String[] args) {
        frame = new JFrame ("AddPersonScreen");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new AddPersonScreen(frame));
        frame.pack();
        frame.setVisible (true);
    }
}

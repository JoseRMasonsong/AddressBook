import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import java.awt.event.*;
import java.awt.print.*;

public class AddressBook extends JFrame implements Printable, ActionListener{

	public static final int WIDTH = 800;
	public static final int HEIGHT = 450;
	public static int personID;
	public JInternalFrame mFrame;
	public JInternalFrame cFrame;
	
	public String firstname;
	public String lastname;
	public String address1;
	public String address2;
	public String city;
	public String state;
	public String zipcode;
	public String phone;
	public String email;
	
	public JTextField f1;
	public JTextField l1;
	public JTextField a1;
	public JTextField a2;
	public JTextField c1;
	public JTextField s1;
	public JTextField z1;
	public JTextField p1;
	public JTextField e1;
	
	
	public AddressBook()
	{
		super();
		setSize(WIDTH, HEIGHT);
		setLocation(80,80); // setting the location on the screen
		setTitle("");
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.gray);
		addWindowListener(new WindowDestroyer());

		JDesktopPane dtp = new JDesktopPane();

	   	 	setContentPane(dtp);

	    	mFrame = new JInternalFrame("Address Book", true,true, true, true);
	    	mFrame.getContentPane().setLayout(null);
	    	
	    	JLabel fname = new JLabel("First Name:");
	    	fname.setBounds(142, 19, 136, 13);
	    	mFrame.getContentPane().add(fname);
	    	
	    	JLabel lname = new JLabel("Last Name:");
	    	lname.setBounds(322, 19, 136, 13);
	    	mFrame.getContentPane().add(lname);
	    	
	    	JLabel address = new JLabel("Address 1:");
	    	address.setBounds(48, 61, 136, 13);
	    	mFrame.getContentPane().add(address);
	    	
	    	JLabel address2 = new JLabel("Address 2:");
	    	address2.setBounds(235, 61, 136, 13);
	    	mFrame.getContentPane().add(address2);
	    	
	    	JLabel city = new JLabel("City:");
	    	city.setBounds(428, 58, 136, 16);
	    	mFrame.getContentPane().add(city);
	    	
	    	JLabel state = new JLabel("State:");
	    	state.setBounds(59, 102, 136, 13);
	    	mFrame.getContentPane().add(state);
	    	
	    	JLabel zipcode = new JLabel("Zipcode:");
	    	zipcode.setBounds(239, 102, 136, 13);
	    	mFrame.getContentPane().add(zipcode);
	    	
	    	JLabel phone = new JLabel("Phone:");
	    	phone.setBounds(425, 102, 136, 13);
	    	mFrame.getContentPane().add(phone);
	    	
	    	JLabel email = new JLabel("Email:");
	    	email.setBounds(246, 143, 136, 13);
	    	mFrame.getContentPane().add(email);
	    	
	    	f1 = new JTextField();
	    	f1.setBounds(103, 32, 136, 21);
	    	mFrame.getContentPane().add(f1);
	    	f1.setColumns(10);
	    	
	    	l1 = new JTextField();
	    	l1.setBounds(286, 32, 136, 21);
	    	mFrame.getContentPane().add(l1);
	    	l1.setColumns(10);
	    	
	    	a1 = new JTextField();
	    	a1.setColumns(10);
	    	a1.setBounds(10, 73, 136, 21);
	    	mFrame.getContentPane().add(a1);
	    	
	    	a2 = new JTextField();
	    	a2.setColumns(10);
	    	a2.setBounds(194, 73, 136, 21);
	    	mFrame.getContentPane().add(a2);
	    	
	    	c1 = new JTextField();
	    	c1.setColumns(10);
	    	c1.setBounds(375, 73, 136, 21);
	    	mFrame.getContentPane().add(c1);
	    	
	    	s1 = new JTextField();
	    	s1.setColumns(10);
	    	s1.setBounds(10, 114, 136, 21);
	    	mFrame.getContentPane().add(s1);
	    	
	    	z1 = new JTextField();
	    	z1.setColumns(10);
	    	z1.setBounds(194, 114, 136, 21);
	    	mFrame.getContentPane().add(z1);
	    	
	    	p1 = new JTextField();
	    	p1.setColumns(10);
	    	p1.setBounds(375, 114, 136, 21);
	    	mFrame.getContentPane().add(p1);
	    	
	    	e1 = new JTextField();
	    	e1.setColumns(10);
	    	e1.setBounds(187, 156, 155, 26);
	    	mFrame.getContentPane().add(e1);
	    	
	    	JButton insertButton = new JButton("Insert");
	    	insertButton.setBounds(110, 220, 85, 26);
	    	insertButton.addActionListener(this);
	    	mFrame.getContentPane().add(insertButton);
	    	
	    	JButton searchButton = new JButton("Search");
	    	searchButton.setBounds(220, 220, 85, 26);
	    	searchButton.addActionListener(this);
	    	mFrame.getContentPane().add(searchButton);
	    	
	    	JButton updateButton = new JButton("Update");
	    	updateButton.setBounds(328, 220, 85, 26);
	    	updateButton.addActionListener(this);
	    	mFrame.getContentPane().add(updateButton);
	    	
	    	JButton deleteButton = new JButton("Delete");
	    	deleteButton.setBounds(110, 265, 85, 26);
	    	deleteButton.addActionListener(this);
	    	mFrame.getContentPane().add(deleteButton);
	    	
	    	JButton printButton = new JButton("Print");
	    	printButton.setBounds(220, 265, 85, 26);
	    	printButton.addActionListener(this);
	    	mFrame.getContentPane().add(printButton);
	    	
	    	JButton clearButton = new JButton("Clear");
	    	clearButton.setBounds(328, 265, 85, 26);
	    	clearButton.addActionListener(this);
	    	mFrame.getContentPane().add(clearButton);
	    	
	    	mFrame.setSize(532, 345);
	    	mFrame.setLocation(45, 20);
	    	mFrame.setVisible(true);
	    	dtp.add(mFrame);

	    }
	
	public int print(Graphics g, PageFormat pf, int page) throws
    PrinterException {

		if (page > 0) { 
			return NO_SUCH_PAGE;
		}
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());

		mFrame.printAll(g);

		return PAGE_EXISTS;
}	

	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Insert"))
		{
		try {
		Class.forName("org.sqlite.JDBC");
		 Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\DB Browser for SQLite\\addressbook.db");
		
		 Statement statement = connection.createStatement();
		 
		 firstname = f1.getText();
		 lastname = l1.getText();
		 address1 = a1.getText();
		 address2 = a2.getText();
		 city = c1.getText();
		 state = s1.getText();
		 zipcode = z1.getText();
		 phone = p1.getText();
		 email = e1.getText();
		 
		 
		 if(firstname.equals("") && lastname.equals("")) {
			 JOptionPane.showMessageDialog(null, "Please enter the person's name.");
		 }else {
		 statement.executeUpdate("INSERT INTO names (firstName, lastName) VALUES ('"+firstname+"','"+lastname+"')");
		 
		 ResultSet rs = statement.executeQuery("SELECT personID from names where firstname = '"+firstname+"' and lastname = '"+lastname+"'");
		 while(rs.next()) 
		 {
			 personID = rs.getInt(1);
		 }
		 statement.executeUpdate("INSERT INTO addresses (personID, address1, address2, city, state, zipcode) "
		 		+ "VALUES ('"+ personID +"','"+ address1 +"','"+ address2 +"','"+ city +"','"+ state +"','"+ zipcode +"')");
		 statement.executeUpdate("INSERT INTO emailAddress (personID, emailAddress) VALUES ('"+personID+"','"+email+"')");
		 statement.executeUpdate("INSERT INTO phoneNumbers (personID, phoneNumbers) VALUES ('"+personID+"','"+phone+"')");
		 
		 JOptionPane.showMessageDialog(null, "Person Saved");
		 
		 statement.close();
		 connection.close();
		 }
		}
		catch ( SQLException sqlException ) {
	         JOptionPane.showMessageDialog( null,
	            sqlException.getMessage(), "Database Error",
	            JOptionPane.ERROR_MESSAGE );
	         System.exit( 1 );
	      }
		catch(ClassNotFoundException cnfex) {
	          System.out.println("Problem in loading or "
	                  + "registering MS Access JDBC driver");
	          cnfex.printStackTrace();
	      }
		}
		
		
		else if(e.getActionCommand().equals("Search"))
		{
			try {
				Class.forName("org.sqlite.JDBC");
				 Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\DB Browser for SQLite\\addressbook.db");
				
				 Statement statement = connection.createStatement();
				 
				 firstname = f1.getText();
				 lastname = l1.getText();
				 address1 = a1.getText();
				 address2 = a2.getText();
				 city = c1.getText();
				 state = s1.getText();
				 zipcode = z1.getText();
				 phone = p1.getText();
				 email = e1.getText();
		
				
				
					
				 ResultSet ps = statement.executeQuery("SELECT personID FROM names WHERE firstname = '"+firstname+"' and lastname = '"+lastname+"'");
				 while(ps.next()) 
				 {
					personID = ps.getInt(1); 
				 }
				 ResultSet rs = statement.executeQuery("SELECT * FROM ((names INNER JOIN addresses ON names.personID = addresses.personID)"
																		   + "INNER JOIN emailAddress ON names.personID = emailAddress.personID)"
																		   + "INNER JOIN phoneNumbers ON names.personID = phoneNumbers.personID "
																		   + "WHERE firstName = '"+firstname+"' OR lastName = '"+lastname+"'");
				if(rs.next()) 
				{
					f1.setText(rs.getString("firstName"));
					l1.setText(rs.getString("lastName"));
					a1.setText(rs.getString("address1"));
					a2.setText(rs.getString("address2"));
					c1.setText(rs.getString("city"));
					s1.setText(rs.getString("state"));
					z1.setText(rs.getString("zipcode"));
					p1.setText(rs.getString("phoneNumbers"));
					e1.setText(rs.getString("emailAddress"));
					JOptionPane.showMessageDialog(null, "Name Found!" );
				}else {
					JOptionPane.showMessageDialog(null, "Name not Found.");
				}
				
				 
				 statement.close();
				 connection.close();
				
				}
				 
			
				catch ( SQLException sqlException ) {
			         JOptionPane.showMessageDialog( null,
			            sqlException.getMessage(), "Database Error",
			            JOptionPane.ERROR_MESSAGE );
			         System.exit( 1 );
			      }
				catch(ClassNotFoundException cnfex) {
			          System.out.println("Problem in loading or "
			                  + "registering MS Access JDBC driver");
			          cnfex.printStackTrace();
			      }
				}
		
		else if(e.getActionCommand().equals("Update"))
		{
			try {
				Class.forName("org.sqlite.JDBC");
				 Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\DB Browser for SQLite\\addressbook.db");
				
				 Statement statement = connection.createStatement();
				 
				 firstname = f1.getText();
				 lastname = l1.getText();
				 address1 = a1.getText();
				 address2 = a2.getText();
				 city = c1.getText();
				 state = s1.getText();
				 zipcode = z1.getText();
				 phone = p1.getText();
				 email = e1.getText();
				 
				 statement.executeQuery("SELECT firstName, lastName FROM names WHERE firstname = '"+firstname+"' and lastname = '"+lastname+"'");
				 ResultSet rs = statement.executeQuery("SELECT personID from names WHERE firstname = '"+firstname+"' and lastname = '"+lastname+"'");
				 while(rs.next()) 
				 {
					personID = rs.getInt(1); 
				 }
				 statement.executeUpdate("UPDATE names SET firstName = '"+f1.getText()+"', lastName = '"+l1.getText()+"' WHERE personID = '"+personID+"'");
				 statement.executeUpdate("UPDATE addresses SET address1 = '"+address1+"', address2 = '"+address2+"', city = '"+city+"', state = '"+state+"', zipcode = '"+zipcode+"' WHERE personID = '"+personID+"'");
				 statement.executeUpdate("UPDATE emailAddress SET emailAddress = '"+email+"' WHERE personID = '"+personID+"'");
				 statement.executeUpdate("UPDATE phoneNumbers SET phoneNumbers = '"+phone+"' WHERE personID = '"+personID+"'");
				 
				 JOptionPane.showMessageDialog(null, "Updated Successfully!");
				 
				 }
			catch ( SQLException sqlException ) {
		         JOptionPane.showMessageDialog( null,
		            sqlException.getMessage(), "Database Error",
		            JOptionPane.ERROR_MESSAGE );
		         System.exit( 1 );
		      }
			catch(ClassNotFoundException cnfex) {
		          System.out.println("Problem in loading or "
		                  + "registering MS Access JDBC driver");
		          cnfex.printStackTrace();
		      }
		}
		
		else if(e.getActionCommand().equals("Delete"))
		{
			try {
				Class.forName("org.sqlite.JDBC");
				 Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\DB Browser for SQLite\\addressbook.db");
				
				 Statement statement = connection.createStatement();
				 
				 
				 firstname = f1.getText();
				 lastname = l1.getText();
				 address1 = a1.getText();
				 address2 = a2.getText();
				 city = c1.getText();
				 state = s1.getText();
				 zipcode = z1.getText();
				 phone = p1.getText();
				 email = e1.getText();
				 
				 statement.executeQuery("SELECT firstName, lastName FROM names");
				 ResultSet rs = statement.executeQuery("SELECT personID FROM names WHERE firstName = '"+firstname+"' and lastName = '"+lastname+"'");
				 while(rs.next()) 
				 {
					personID = rs.getInt(1); 
				 }
				 statement.executeUpdate("DELETE FROM names where personID = '"+personID+"'");
				 statement.executeUpdate("DELETE FROM addresses where personID = '"+personID+"'");
				 statement.executeUpdate("DELETE FROM emailAddress where personID = '"+personID+"'");
				 statement.executeUpdate("DELETE FROM phoneNumbers where personID = '"+personID+"'");
				 
				 
				 JOptionPane.showMessageDialog(null, "Deleted Successfully!");
			
			
			}
			catch ( SQLException sqlException ) {
		         JOptionPane.showMessageDialog( null,
		            sqlException.getMessage(), "Database Error",
		            JOptionPane.ERROR_MESSAGE );
		         System.exit( 1 );
		      }
			catch(ClassNotFoundException cnfex) {
		          System.out.println("Problem in loading or "
		                  + "registering MS Access JDBC driver");
		          cnfex.printStackTrace();
		      }
		}
		
		else if(e.getActionCommand().equals("Print")) 
		{
			PrinterJob job = PrinterJob.getPrinterJob();
				job.setPrintable(this);
				boolean ok = job.printDialog();
				if(ok) 
				{
					try {
						job.print();
					} catch (PrinterException ex) {
					  JOptionPane.showMessageDialog(null, "Did not successfully complete");
					}
				}else 
					System.out.println("Error in button interface");
				
		}
		
		
		else if(e.getActionCommand().equals("Clear"))
		{
			try {
				Class.forName("org.sqlite.JDBC");
				 Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\DB Browser for SQLite\\addressbook.db");
				
				 
				 f1.setText("");
				 l1.setText("");
				 a1.setText("");
				 a2.setText("");
				 c1.setText("");
				 s1.setText("");
				 z1.setText("");
				 p1.setText("");
				 e1.setText("");
				 
				
			}
			catch ( SQLException sqlException ) {
		         JOptionPane.showMessageDialog( null,
		            sqlException.getMessage(), "Database Error",
		            JOptionPane.ERROR_MESSAGE );
		         System.exit( 1 );
		      }
			catch(ClassNotFoundException cnfex) {
		          System.out.println("Problem in loading or "
		                  + "registering MS Access JDBC driver");
		          cnfex.printStackTrace();
		      }
		}
		}
	
		
	

	public static void main(String[] args)
	{
		AddressBook buttonGui = new AddressBook();
		buttonGui.setVisible(true);
	}
}

class WindowDestroyer extends WindowAdapter
{
	public void windowClosing(WindowEvent e)
	{
	    System.exit(0);
	}

}


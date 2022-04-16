package com.register.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet ("/registration")

	public class RegisterServlet extends HttpServlet{
	   
    private static final String INSERT_QUERY = "INSERT INTO USER(FIRSTNAME,LASTNAME,EMAIL,PHONENO,PASSWORD)VALUES (?,?,?,?,?)";
	 
	private static final long serialVersionUID = 1L;

		protected void doGet (HttpServletRequest req, HttpServletResponse res)
				throws IOException, ServletException {
			
			//get PrintWriter
		   
		try {
			   PrintWriter pw = res.getWriter();
		   
	        //set Content type
	        res.setContentType("text/hmtl");
	        //read the form values
	        String firstname = req.getParameter("firstname");
	        String lastname = req.getParameter ("lastname");
	        String email = req.getParameter ("email");
	        String phoneno = req.getParameter("phoneno");
	       
	        
	        System.out.println("firstname " +firstname);
	        System.out.println("lastname " +lastname);
	        System.out.println("email " +email);
	        System.out.println("phoneno "+phoneno);
	      try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	      }
	      catch(ClassNotFoundException e) {
	    	  e.printStackTrace();
	      }
	      try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/hmsdb","roo","root123");
	
	    	   PreparedStatement ps = con.prepareStatement(INSERT_QUERY);){
	           //set values
	    	  ps.setString(1, firstname);
	    	  ps.setString(2, lastname);
	    	  ps.setString(3, email);
	    	  ps.setString(4, phoneno);
	    	 //ps.setString(5, password);
	    	  
	    	  //execute the query
	    	  int count =  ps.executeUpdate();
	    	  if(count==0)
	    	  {
	    		   pw.println("Record Not Submitted");
	    	  }
	    	  else
	    	  {
	    		  pw.println("Record Submitted");
	    	  }
	      }
	      catch(SQLException se)
	      {
	    	pw.println(se.getMessage());
	    	se.printStackTrace();
	      }
	      catch(Exception e)
	      {
	    	  pw.println(e.getMessage());
	    	  e.printStackTrace();
	      }
	         pw.close();
		}
		   
		   catch(Exception e){
			  
			   e.printStackTrace();
			   //System.out.println("error aa gyi");
		   }
		  
}
@Override
protected void doPost (HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
   // TODO Auto-generated method stub
    doGet (req, res);
}
}

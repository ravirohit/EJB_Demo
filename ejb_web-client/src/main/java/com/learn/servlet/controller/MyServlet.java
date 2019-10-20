package com.learn.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.learn.ejbinterface.FirstDemoEjbRemote;

public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public MyServlet() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try{
			InitialContext context = new InitialContext();
			
			    /* if we want to make a EJB call to on remote machine then below lines of code required.
				Properties jndiProps = new Properties();
				jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
				jndiProps.put(Context.PROVIDER_URL,"remote://localhost:4447");
				// username  // optional
				jndiProps.put(Context.SECURITY_PRINCIPAL, "rohit");
				// password // optional
				jndiProps.put(Context.SECURITY_CREDENTIALS, "pwd");
				// create a context passing these properties
				Context ctx = new InitialContext(jndiProps);
				// lookup
				ctx.lookup("foo/bar");*/
			FirstDemoEjbRemote obj =(FirstDemoEjbRemote) context.lookup("java:global/ejb_web_remote/FirstDemoEjb");   // JNDI Lookup
		    System.out.println("Looked up object:"+obj);
		    String str = obj.testDemo("req object from client");
		    System.out.println("response from EJB response:"+str);
		}
		catch(Exception e){
			System.out.println("exception:"+e);
			out.println(e.getStackTrace());
		}
		
		out.print("doGet method get called");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

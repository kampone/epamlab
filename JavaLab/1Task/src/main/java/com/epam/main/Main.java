package com.epam.main;

import java.sql.Connection;
import java.sql.SQLException;

import com.epam.connectionpool.exception.ConnectionPoolException;
import com.epam.connectionpool.pool.ConnectionPool;

public class Main {

	public static void main(String[] args){
		
	ConnectionPool pool = ConnectionPool.getInstance();
	try {
		Connection conn = pool.takeConnection();
		conn.createStatement().execute("Insert into AUTHORS (AUTHOR_ID,AUTHOR_NAME,EXPIRED) values (1,'Ivan Ivanov', null)");
		conn.createStatement().execute("Select * from AUTHORs");
	} catch (ConnectionPoolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
}

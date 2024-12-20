package connection;

import java.sql.*;

public class DbConnection {
	
	private static Connection connection = null;
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
        if(connection == null){
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/solemate","root","EECS4413");
            System.out.print("connected");
        }
        return connection;
    }

}

package connection;

import java.sql.*;
import javax.servlet.ServletContext;

public class DbConnection {
	
	private static Connection connection = null;

	public static Connection getConnection(ServletContext context) throws ClassNotFoundException, SQLException{
        if(connection == null){
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
            // Provide the connection string to a SQLite database file using the real path
            String realPath = context.getRealPath("/WEB-INF/SoleMate.db");
            connection = DriverManager.getConnection("jdbc:sqlite:" + realPath);
            System.out.print("Connected");
        }
        return connection;
    }
}

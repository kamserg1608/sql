package jdbc;

import sun.awt.image.IntegerInterleavedRaster;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String userName = "postgres";
        String password = "root";
        String connectionURl = "jdbc:postgresql://localhost:5432/test";
        Class.forName("org.postgresql.Driver");
        try(Connection connection = DriverManager.getConnection(connectionURl, userName, password);
            Statement statement= connection.createStatement()) {
            System.out.println("We're connected!!!!");
            statement.executeUpdate("DROP TABLE Users");
            statement.executeUpdate("CREATE TABLE Users (id SERIAL, name VARCHAR(30) NOT NULL , password VARCHAR(30) NOT NULL, PRIMARY KEY (id) )");
            statement.executeUpdate("INSERT INTO Users (name, password) VALUES ('max', '123')");
            statement.executeUpdate("INSERT INTO Users (name, password) VALUES ('otherGuy', '312')");
//            ResultSet resultSet = statement.executeQuery("SELECT  * FROM books");

    //region data for request
            String userID = "1";
//            sql injection
//            https://www.w3schools.com/sql/sql_injection.asp
//            String userID = "1' or 1 = '1 ";
    //endregion

/*    //region sql injection
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users WHERE id = '" + userID + " ' ");
            while (resultSet.next()){
                System.out.println("userName: " + resultSet.getString("name"));
                System.out.println("userPassword: " + resultSet.getString("password"));
            }
    //endregion*/

//            System.out.println("-------------------------");




    // region defend of sql injection
            int user_ID = 1;
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users WHERE id = ? and name = ?");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users WHERE id = ?");
//            preparedStatement.setString(2,"userName");
            preparedStatement.setInt(1,user_ID);
            ResultSet resultSet1 = preparedStatement.executeQuery();
            while (resultSet1.next()){
                System.out.println("userName: " + resultSet1.getString("name"));
                System.out.println("userPassword: " + resultSet1.getString("password"));
            }
    //endregion

        }
    }
}

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
            statement.executeUpdate("DROP TABLE Books");
            statement.executeUpdate("CREATE TABLE Books (id SERIAL, name VARCHAR(30) NOT NULL, dt DATE, PRIMARY KEY (id) )");

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO books (name, dt) VALUES ('someName', ?) ");
            preparedStatement.setDate(1, new Date( System.currentTimeMillis() ));
            preparedStatement.execute();

            System.out.println(preparedStatement);
//            see escape sequence https://www.postgresql.org/docs/current/sql-syntax-lexical.html
            statement.executeUpdate("INSERT INTO books (name, dt) VALUES ('someName', {d'2019-07-11 +03'}) ");

            ResultSet resultSet = statement.executeQuery("SELECT * FROM Books");
            while(resultSet.next()){
                System.out.println(resultSet.getDate("dt"));
            }

        }
    }
}

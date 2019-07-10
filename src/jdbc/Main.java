package jdbc;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String userName = "postgres";
        String password = "root";
        String connectionURl = "jdbc:postgresql://localhost:5432/test";
        Class.forName("org.postgresql.Driver");
        try(Connection connection = DriverManager.getConnection(connectionURl, userName, password)){
            System.out.println("We're connected!!!!");
        }

    }
}

package root;

import com.mysql.jdbc.Driver;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class DataBase {

    private Connection connection;

    public DataBase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DriverManager.registerDriver(new Driver());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webchat?serverTimezone=Europe/Moscow", "root", "0000");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("Driver Registration error");
        }
    }

    private Set<AuthService.Record> getDataBase() {
        Set<AuthService.Record> records = new HashSet<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM RECORDS");
            while (resultSet.next()) {
                records.add(new AuthService.Record(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("login"),
                                resultSet.getString("password"),
                                resultSet.getString("avatar")
                        )
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("Statement error");
        }
        return records;
    }

    public Set<AuthService.Record> getRecords() {
        return getDataBase();
    }

    public AuthService.Record getUser(String login, String password) {
        for (AuthService.Record r : getDataBase()) {
            if (r.getLogin().equals(login) && r.getPassword().equals(password)) return r;
        }
        return null;
    }

    public void setUser(String name, String login, String password, String avatar) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO RECORDS (name, login, password, avatar) VALUES (?, ?, ?, ?)");
            statement.setString(1, name);
            statement.setString(2, login);
            statement.setString(3, password);
            statement.setString(4, avatar);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

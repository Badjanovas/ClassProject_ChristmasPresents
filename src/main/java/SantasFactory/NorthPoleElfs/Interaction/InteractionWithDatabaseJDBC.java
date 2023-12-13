package SantasFactory.NorthPoleElfs.Interaction;

import SantasFactory.Gifts.GoodKid;
import SantasFactory.Gifts.Present;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InteractionWithDatabaseJDBC {
    /*
     * Fetches the most expensive present from the database and prints its details.
     * Uses SQL to order presents by price in descending order and limits the result to 1.
     */

    public void fetchMostExpensivePresent() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
        String sql = "SELECT * FROM present ORDER BY price DESC LIMIT 1";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            Present mostExpensivePresent = new Present();
            mostExpensivePresent.setId(resultSet.getLong("id"));
            mostExpensivePresent.setName(resultSet.getString("name"));
            mostExpensivePresent.setPrice(resultSet.getInt("price"));
            mostExpensivePresent.setColor(resultSet.getString("color"));
            mostExpensivePresent.setDescription(resultSet.getString("description"));
            mostExpensivePresent.setWeight(resultSet.getDouble("weight"));

            System.out.println("Most expensive present: " + mostExpensivePresent.getName());
        } else {
            System.out.println("No presents found.");
        }

        resultSet.close();
        statement.close();
        connection.close();
    }


    /*Fetches presents from the database based on the provided color.
     * Uses SQL to select presents with a specified color using a prepared statement.
     * Populates a list with the retrieved presents and prints the count and details.*/


    public void fetchPresentsByGivenColor(String color) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
        String sql = "SELECT * FROM present WHERE color = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, color);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Present> presentsByColor = new ArrayList<>();
        while (resultSet.next()) {
            Present present = new Present();

            present.setId(resultSet.getLong("id"));
            present.setName(resultSet.getString("name"));
            present.setPrice(resultSet.getInt("price"));
            present.setColor(resultSet.getString("color"));
            present.setDescription(resultSet.getString("description"));
            present.setWeight(resultSet.getDouble("weight"));

            if (present.getColor().equals(color)) {
                presentsByColor.add(present);
            }
        }
        System.out.println("There are " + presentsByColor.size() + " presents of " + color + " color");
        System.out.println(presentsByColor);
        resultSet.close();
        connection.close();
    }

    /*
     * This method allows you to filter and retrieve presents from the database that are heavier than a specified weight.*/
    public void fetchPresentsHeavierThanGivenWeight(double weight) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
        String sql = "SELECT * FROM present WHERE weight > ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDouble(1, weight);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Present> heavierPresents = new ArrayList<>();
        while (resultSet.next()) {
            Present present = new Present();

            present.setId(resultSet.getLong("id"));
            present.setName(resultSet.getString("name"));
            present.setPrice(resultSet.getInt("price"));
            present.setColor(resultSet.getString("color"));
            present.setDescription(resultSet.getString("description"));
            present.setWeight(resultSet.getDouble("weight"));

            if (present.getWeight() > weight) {
                heavierPresents.add(present);
            }
        }

        System.out.println("There are " + heavierPresents.size() + " presents heavier than: " + weight);
        System.out.println(heavierPresents);

        connection.close();
        resultSet.close();
    }

    /*This method allows you to retrieve and print the information of the youngest receiver from the goodkid table in the database. */
    public void fetchYoungestReceiver() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
        String sql = "SELECT * FROM goodkid ORDER BY age ASC LIMIT 1";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            GoodKid goodKid = new GoodKid();

            goodKid.setName(resultSet.getString("name"));
            goodKid.setLastName(resultSet.getString("lastname"));
            goodKid.setAge(resultSet.getInt("age"));
            goodKid.setId(resultSet.getLong("id"));

            System.out.println("Youngest receiver: " + goodKid);
        } else {
            System.out.println("No kids found");
        }
        resultSet.close();
        connection.close();
        statement.close();
    }

    /*This method allows you to retrieve and print the information of a receiver based on their last name from the goodkid table in the database.*/
    public void fetchReceiverByLastName(String lastName) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
        String sql = "SELECT * FROM goodkid WHERE lastname = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, lastName);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            GoodKid goodKid = new GoodKid();

            goodKid.setName(resultSet.getString("name"));
            goodKid.setLastName(resultSet.getString("lastname"));
            goodKid.setAge(resultSet.getInt("age"));
            goodKid.setId(resultSet.getLong("id"));
            System.out.println(goodKid);
        } else {
            System.out.println("Receiver doesn't exist");
        }

        resultSet.close();
        connection.close();
        preparedStatement.close();
    }

    /*This method allows you to retrieve and print the names and addresses of all receivers from the goodkid table in the database.*/
    public void fetchAllReceiversAddresses() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
        String sql = "SELECT name, address FROM goodkid";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<GoodKid> goodKids = new ArrayList<>();

        while (resultSet.next()) {
            GoodKid goodKid = new GoodKid();
            goodKid.setName(resultSet.getString("name"));
            goodKid.setAddress(resultSet.getString("address"));
            goodKids.add(goodKid);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        System.out.println(goodKids);
    }
}

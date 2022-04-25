package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExecutor {

  public static void main(String[] args) {
    DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
        "hplussport", "dvdhyh22", "password123");
    try {
      Connection connection = dcm.getConnection();
      CustomerDAO customerDAO = new CustomerDAO(connection);
//      Customer customer = customerDAO.findById(10000);

      Customer customer = new Customer();
      customer.setFirstName("Jake");
      customer.setLastName("Peralta");
      customer.setEmail("jp@gmail.com");
      customer.setPhone("(123) 456-7890");
      customer.setAddress("99 Brooklyn st");
      customer.setCity("New York City");
      customer.setState("NY");
      customer.setZipCode("11211");

      Customer dbCustomer = customerDAO.create(customer);
      System.out.println(dbCustomer);
      dbCustomer = customerDAO.findById(dbCustomer.getId());
      System.out.println(dbCustomer);
      dbCustomer.setEmail("jp@gmail.ca");
      dbCustomer = customerDAO.update(dbCustomer);
      customerDAO.delete(dbCustomer.getId());
//
//      System.out.println(customer.getFirstName() + " " + customer.getLastName() + " " +
//          customer.getEmail());
//      customer.setEmail("Mlepclaynos@gmail.ca");
//      customer = customerDAO.update(customer);
//      System.out.println(customer.getFirstName() + " " + customer.getLastName() + " " +
//          customer.getEmail());


//      customer.setFirstName("Jerry");
//      customer.setLastName("Barfralatistan");
//      customer.setEmail("Mlepclaynos@gmail.com");
//      customer.setPhone("(123) 123-4567");
//      customer.setAddress("99 Brooklyn st");
//      customer.setCity("New York City");
//      customer.setState("NY");
//      customer.setZipCode("22122");
//      customerDAO.create(customer);


//      Statement statement = connection.createStatement();
//      ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM CUSTOMER");
//      while (resultSet.next()){
//        System.out.println(resultSet.getInt(1));
//      }
    } catch (SQLException e){
      e.printStackTrace();
    }
  }
}

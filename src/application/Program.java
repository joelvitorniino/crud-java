package application;

import db.DB;
import db.DbException;

import java.sql.*;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        System.out.println("Welcome to CRUD!");

        try {
            conn = DB.getConnection();

            System.out.print("Type your option: (C/R/U/D) ");
            char option = sc.nextLine().charAt(0);

            if(option == 'C') {
                System.out.print("Name: ");
                String name = sc.nextLine();

                System.out.println("Email: ");
                String email = sc.nextLine();

                st = conn.prepareStatement(
                        "INSERT INTO "
                                + "register(name, email) "
                                + "VALUES "
                                + "(?, ?)"
                );

                st.setString(1, name);
                st.setString(2, email);

                st.executeUpdate();

                System.out.println("Created!");
            } else if(option == 'R') {

                System.out.println("Which ID: ");
                int id = sc.nextInt();

                Statement statement = conn.createStatement();
                rs = statement.executeQuery("SELECT * FROM register WHERE id = " + id);

                if(rs.next()) {
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnsNumber = rsmd.getColumnCount();

                    for(int i = 1; i <= columnsNumber; i++) {
                        if(i > 1) System.out.println(" ");

                        String columnValue = rs.getString(i);
                        System.out.print(columnValue + " ");
                    }
                } else {
                    System.out.println("ID is not exists!");
                }
            } else if(option == 'U') {
                System.out.println("Which ID to update data? ");
                int id = sc.nextInt();

                sc.nextLine();

                System.out.println("Name: ");
                String name = sc.nextLine();

                System.out.println("Email: ");
                String email = sc.nextLine();

                st = conn.prepareStatement(
                        "UPDATE register "
                        + "SET name = ?, email = ? "
                        + "WHERE id = ?"
                );

                st.setInt(3, id);
                st.setString(1, name);
                st.setString(2, email);

                st.executeUpdate();

                System.out.println("Data updated!");
            } else if(option == 'D') {
                System.out.println("Which ID to delete data? ");
                int id = sc.nextInt();

                st = conn.prepareStatement(
                        "DELETE FROM register "
                                + "WHERE id = ?"
                );

                st.setInt(1, id);

                st.executeUpdate();

                System.out.println("Data deleted!");
            }
        } catch(SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
            DB.closeConnection();
        }
    }
}

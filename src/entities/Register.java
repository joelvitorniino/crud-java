package entities;

import db.DB;
import db.DbException;

import java.sql.*;

public class Register {
    private static Connection conn = null;
    private static PreparedStatement st = null;
    private static ResultSet rs = null;

    private static void getConnection() {
        conn = DB.getConnection();
    }

    public static void createData(String name, String email) {
        try {
            getConnection();

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
        } catch(SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public static void readData(int id) {
        try {
            getConnection();

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
        } catch(SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public static void updateData(int id, String name, String email) {
        try {
            getConnection();

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
        } catch(SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public static void deleteData(int id) {
        try {
            getConnection();

            st = conn.prepareStatement(
                    "DELETE FROM register "
                            + "WHERE id = ?"
            );

            st.setInt(1, id);

            st.executeUpdate();

            System.out.println("Data deleted!");
        } catch(SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
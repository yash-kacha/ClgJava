package javaapplication2;

import java.sql.*;
import java.util.Scanner;

public class JavaApplication2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        for (;;) {
            System.out.print(
                    "1 = Select ||  2 = Insert || 3 = Update || 4 = Delete || 5 = Find || 6 = UpdateCollumeName || 0 = Exit  :: ");
            int temp = sc.nextInt();

            if (temp == 0) {
                break;
            }
            switch (temp) {
                case 1:
                    Select();
                    break;
                case 2:
                    Insert(sc);
                    break;
                case 3:
                    Update(sc);
                    break;
                case 4:
                    Delete(sc);
                    break;
                case 5:
                    Find(sc);
                    break;
                case 6:
                    UpdateCollumeName(sc);
                    break;
                default:
                    System.out.println("Enter a valid input");
            }
        }

        sc.close();
    }

    // Centralized method to get the database connection
    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL";
            String user = "root";
            String password = "root";

            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Connection failed: " + e);
            return null;
        }
    }

    public static void UpdateCollumeName(Scanner sc) {
        try (Connection con = getConnection(); Statement ps = con.createStatement()) {

            sc.nextLine();
            // Adjust SQL syntax based on your database
            System.out.print("Enter previous name : ");
            String oldn = sc.nextLine();

            System.out.print("Enter new name : ");
            String newn = sc.nextLine();

            String sql = "ALTER TABLE employee RENAME COLUMN "+oldn+" TO " +newn; // For PostgreSQL
            // String sql = "ALTER TABLE employee CHANGE name name1 VARCHAR(255)"; // For MySQL

            int rows = ps.executeUpdate(sql);
            System.out.println(rows + " row(s) updated.");

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
//   RENAME COLUMN table-Name.simple-Column-Name TO simple-Column-Name Examples

    public static void Select() {
        try {
            Connection con = getConnection();
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("SELECT * FROM employee");

//            for (int i = 1; i <= 1; i++) {
//                rs.next();
//            }
//            rs.updateInt(1, 1);
//            rs.updateString(2, "a");
//            rs.updateInt(3, 1);
//
//            rs.updateRow();
//
//            rs.beforeFirst();
            System.out.println("  ID  |  Name  |  Salary |");

            while (rs.next()) {
                System.out.print("  " + rs.getInt(1) + " | ");
                System.out.print(rs.getString(2) + "  |  ");
                System.out.println(rs.getInt(3) + "  |  ");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void Insert(Scanner sc) {
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO employee (id, name, salary) VALUES (?, ?, ?)");

            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            System.out.print("Enter Name: ");
            String name = sc.next();
            System.out.print("Enter Salary: ");
            int salary = sc.nextInt();

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, salary);

            int rows = ps.executeUpdate();
            System.out.println(rows + " row(s) inserted.");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void Update(Scanner sc) {
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE employee SET name = ?, salary = ? WHERE id = ?");

            System.out.print("Enter ID to update: ");
            int id = sc.nextInt();
            System.out.print("Enter new Name: ");
            String name = sc.next();
            System.out.print("Enter new Salary: ");
            int salary = sc.nextInt();

            ps.setString(1, name);
            ps.setInt(2, salary);
            ps.setInt(3, id);

            int rows = ps.executeUpdate();
            System.out.println(rows + " row(s) updated.");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void Delete(Scanner sc) {
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM employee WHERE id = ?");

            System.out.print("Enter ID to delete: ");
            int id = sc.nextInt();

            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            System.out.println(rows + " row(s) deleted.");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void Find(Scanner sc) {
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM employee WHERE id = ?");

            System.out.print("Enter ID to find: ");
            int id = sc.nextInt();

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Record Found:");
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Salary: " + rs.getInt("salary"));
            } else {
                System.out.println("No record found with ID: " + id);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

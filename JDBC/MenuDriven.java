import java.sql.*;
import java.util.Scanner;

public class Second {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        for (;;) {
            System.out.print(
                    "1 = Select ||  2 = Insert || 3 = Update || 4 = Delete || 0 = Exit  :: ");
            int temp = sc.nextInt();

            if (temp == 0) break;
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

    public static void Select() {
        try (Connection con = getConnection(); Statement st = con.createStatement()) {

            ResultSet rs = st.executeQuery("SELECT * FROM employee");

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
}

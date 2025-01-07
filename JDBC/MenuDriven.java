import java.sql.*;
import java.util.Scanner;

public class Second {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        for (;;) {
            System.out.print(
                    "1 = Select ||  2 = Insert || 3 = Update || 4 = Delete  :: ");
            int temp = sc.nextInt();

            if(temp==0)break;
            switch (temp) {
                case 1:
                    Select();
                    break;
                case 2:
                    Insert();
                    break;
                case 3:
                    Update();
                    break;
                case 4:
                    Delete();
                    break;
                default:
                    System.out.println("Enter a valid input");;
            }

        }

    }

    public static void Select() {
        try {
            String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL";
            String user = "root";
            String password = "root";

            Connection con = DriverManager.getConnection(url, user, password);
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from employee");

            System.out.println("  ID  |  Name  | Salary |");
            while (rs.next()) {
                System.out.print("  " + rs.getInt(1) + " | ");
                System.out.print(rs.getString(2) + "  |  ");
                System.out.println(rs.getInt(3) + "  |  ");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void Insert() {

    }

    public static void Update() {

    }

    public static void Delete() {

    }

}

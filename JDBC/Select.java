import java.sql.*;

public class First {

    public static void main(String[] args) {

        try {
            String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL";
            String user = "root";
            String password = "root";

            Connection con = DriverManager.getConnection(url, user, password);
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from employee");

            
            System.out.println("  ID  |  Name  | Salary |");
            while (rs.next()) 
            {
                System.out.print("  "+rs.getInt(1)+" | ");
                System.out.print(rs.getString(2)+"  |  ");
                System.out.println(rs.getInt(3)+"  |  ");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}

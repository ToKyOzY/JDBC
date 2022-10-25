package jdbc;

import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","*****");
        Statement st=con.createStatement();

        //1. Örnek: companies tablosundan en yüksek ikinci number_of_employees
        // değeri olan company ve number_of_employees değerlerini çağırın.
        //1. Yol: OFFSET ve FETCH NEXT kullanarak
        String sql1="select company, number_of_employees\n" +
                "from companies\n" +
                "order by number_of_employees desc\n" +
                "offset 1 row\n" +
                "fetch next 1 row only";
        ResultSet result1=st.executeQuery(sql1);
        while (result1.next()) {
            System.out.println(result1.getString(1)+"--"+result1.getInt(2));

        }

        // Yol: Subquery kullanarak
        String sql2="select company, number_of_employees\n" +
                     "from companies\n" +
                     "where number_of_employees=(select max(number_of_employees)\n" +
                     "  from companies\n" +
                     "   where number_of_employees< (select max(number_of_employees)\n" +
                     "  from companies))";
        ResultSet result2= st.executeQuery(sql2);
        while (result2.next()) {
            System.out.println(result2.getString("company")+"--"+result2.getInt("number_of_employees"));
        }
        con.close();
        st.close();
        result1.close();
        result2.close();
    }
}

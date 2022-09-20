package jdbc;

import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","06Ankara06");
        Statement st=con.createStatement();

        //1. Örnek: Prepared statement kullanarak company adı IBM olan
        // number_of_employees değerini 9999 olarak güncelleyin.

        //1. Adım: Prepared statement Query'sini oluştur
        String sql1="UPDATE companies SET number_of_employees =? WHERE company = ?";

        //2. Adım: PreparedStatement objesini oluştur
        PreparedStatement pst1=  con.prepareStatement(sql1);

        //3. Adım: Set...() methodlari ile soru işaretleri için deger gir
        pst1.setInt(1, 9999);
        pst1.setString(2,"IBM");

        //4. Adım: Execute query
        int updaterowSayisi=pst1.executeUpdate();
        System.out.println(updaterowSayisi +" satır güncellendi");


        String sql2="Select * from companies";
        ResultSet result1=st.executeQuery(sql2);

        while (result1.next()) {
            System.out.println(result1.getInt(1)+"--"+result1.getString(2)+"--"+result1.getInt(3));

        }


        //Google için degisiklik
        pst1.setInt(1, 15000);
        pst1.setString(2,"GOOGLE");

        //4. Adım: Execute query
        int updaterowSayisi2=pst1.executeUpdate();
        System.out.println(updaterowSayisi +" satır güncellendi");


        String sql3="Select * from companies";
        ResultSet result2=st.executeQuery(sql3);

        while (result2.next()) {
            System.out.println(result2.getInt(1)+"--"+result2.getString(2)+"--"+result2.getInt(3));

        }

        //2. Örnek: "SELECT * FRoM <table name>" query'sini prepared statement ile kullanın

        System.out.println("==============");
         read_data(con,"companies");

        //     String sql4="SELECT * FROM ? ";

  //     PreparedStatement pst2=con.prepareStatement(sql4);
  //     pst2.setString(1,"companies");
  //     ResultSet result3=pst2.executeQuery(sql4);
  //     while (result3.next()) {
  //         System.out.println(result3.getInt(1)+"--"+result3.getString(2)+"--"+result3.getInt(3));

   //     }


    }
    //bir tablonun istenilen datasını prapared statement ile çağırmak için kullanılan method
    public static void read_data(Connection con, String tableName){
       try {
           String query = String.format("SELECT * FROM %s", tableName) ;
           //sql query'yi çalıştırır
           Statement statement=con.createStatement();
            ResultSet rs=statement.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getInt(1)+"--"+rs.getString(2)+"--"+rs.getInt(3));
            }
       }catch (Exception e){
           System.out.println(e);
       }







        
    }
}

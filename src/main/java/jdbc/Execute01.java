package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1. adım: Driver'a kaydol
        Class.forName("org.postgresql.Driver");

        //2. adım: Database'e baglan
        Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","06Ankara06");

        //3. adım: Statement olustur
        Statement st= con.createStatement();

        //4.adım: Query çalıştır

        //1.Example: "workers" adında bir table oluşturup
        // "worker_id,worker_name, worker_salary" sütunlarını ekleyin.
        String sql1="CREATE TABLE workers (worker_id VARCHAR(50),worker_name VARCHAR(50),worker_salary INT)";
        boolean result=st.execute(sql1);
        System.out.println(result); //false verir çunku data cagırmadık

        //2. Example:  Alter table by adding worker_address column into the workers table
        String sql2="Alter table workers ADD worker_address varchar(80)";
        st.execute(sql2);

        //3. Example: Drop workers able
        String sql3="Drop table workers";
        st.execute(sql3);

        //5.adım: Baglantı ve Statement'i kapat
        con.close();
        st.close();

    }
}

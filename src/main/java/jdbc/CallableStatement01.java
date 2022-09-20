package jdbc;

import java.sql.*;

public class CallableStatement01 {
    /*
    Java'da methodlar,  return type ahibi olsa da void olsa da method olarak adlandırılır
    SQL'de ise data return ediyorsa buna function denir.Return yapmıyorsa procedure denir


     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","06Ankara06");
        Statement st=con.createStatement();

        //Ornek 1:iki parametre ile çalışıp bu parametreleri toplayarak return yapan bir function oluşturun
        //1. Adim: Fonksiyon kodunu yaz

        String sql1="CREATE OR REPLACE FUNCTION toplamaF(x NUMERIC, y NUMERIC)\n" +
                    "RETURNS NUMERIC\n" +
                    "LANGUAGE plpgsql\n" +
                    "AS\n" +
                    "$$\n" +
                    "BEGIN\n" +
                    "\n" +
                    "RETURN x+y;\n" +
                    "\n" +
                    "END\n" +
                    "$$";

        //2. Adim: Fonksiyonu çalıştır
        st.execute(sql1);
        //3. Adim: Fonksiyonu çagır
        CallableStatement cst1=con.prepareCall("{?= call toplamaF(?, ?)}");
        //4. Adim: Return için registerOutParameter() method'unu, parametreler için set() methodlarından uygun olanları kullan.
        cst1.registerOutParameter(1,Types.NUMERIC);
        cst1.setInt(2,15);
        cst1.setInt(3,25);
        //5. Adim: Fonksiyonu çalıştırmak için execute() method'unu kullan
        cst1.execute();
        //6. Adim: Sonucu cagırmak için return data tipine göre get methodlarından uygun olanı kullan.
        System.out.println(cst1.getBigDecimal(1));


        //   ================================================================

        //Ornek 2: Koninin hacmini hesaplayan bir function yazınız

        //1. Adim: Fonksiyon kodunu yaz

        String sql2="CREATE OR REPLACE FUNCTION koniHacmi(r NUMERIC, h NUMERIC)\n" +
                "RETURNS NUMERIC\n" +
                "LANGUAGE plpgsql\n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN 3.14*r*r*h/3;\n" +
                "\n" +
                "END\n" +
                "$$";

        //2. Adim: Fonksiyonu çalıştır
        st.execute(sql2);
        //3. Adim: Fonksiyonu çagır
        CallableStatement cst2=con.prepareCall("{?= call koniHacmi(?, ?)}");
        //4. Adim: Return için registerOutParameter() method'unu, parametreler için set() methodlarından uygun olanları kullan.
        cst2.registerOutParameter(1,Types.NUMERIC);
        cst2.setInt(2,3);
        cst2.setInt(3,5);
        //5. Adim: Fonksiyonu çalıştırmak için execute() method'unu kullan
        cst2.execute();
        //6. Adim: Sonucu cagırmak için return data tipine göre get methodlarından uygun olanı kullan.
        System.out.println(cst2.getBigDecimal(1));




    }


}

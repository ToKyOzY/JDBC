package jdbc;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        //DBWork objesi oluştur
        DBWork db= new DBWork();

        //Connection methodunu cagır
       Connection con= db.connect_to_db("techproed", "postgres","*****");

        //Yeni table olusturma methodunu cagır
        db.createTable(con, "employees");

    }
}

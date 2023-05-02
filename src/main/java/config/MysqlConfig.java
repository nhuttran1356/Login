package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlConfig {
    public static final String url = "jdbc:mysql://localhost:3308/login";
    public static final String username = "root";
    public static final String password = "admin123";

    public static Connection getConnection(){
        Connection connection = null;
        try{
            //Chi dinh driver su dung
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Tao kết nối cơ so dữ liệu
            connection = DriverManager.getConnection(url,username,password);

        }catch (Exception e){
            System.out.println("Lỗi kết nối tới cơ sở dữ liệu " + e.getMessage());
        }

        return connection;
    }

}

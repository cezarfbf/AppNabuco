package app.appnabuco.bd;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by cezar on 30/05/2015.
 */
public class ConexaoBD {
    private static Connection conn;
    private static String host = "192.168.25.105";
    private static String bd = "appnabuco";
    private static String porta = "5432";
    private static String user = "postgres";
    private static String pass = "postgres";
    private static String url = "jdbc:postgresql://"+host+":"+porta+"/"+bd;

    public static Connection getConnection() {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, pass);
            conn.setHoldability(ResultSet.HOLD_CURSORS_OVER_COMMIT);
        } catch (Exception e) {
            e.printStackTrace();

            System.out.println("Nao conseguiu conectar!!"+e.getMessage());
        }

        return conn;
    }

    public static Statement getStatement() {
        Statement stmt = null;
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            stmt = getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;

    }


    public static PreparedStatement getPreparedStatement(String sql) {
        PreparedStatement pst = null;

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            pst = ConexaoBD.getConnection().prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pst;
    }

}

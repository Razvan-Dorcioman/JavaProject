import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {
            Connection con = DBUtil.getConnection(DBType.MYSQLDB);

            //Selectam datele din tabel
            String sql_select = "select * from email";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql_select);

            while(rs.next()){
                System.out.println(rs.getString(1) + " " + rs.getString(2));
            }
    }

}

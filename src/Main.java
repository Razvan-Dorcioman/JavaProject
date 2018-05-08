import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
            Connection con = DBUtil.getConnection(DBType.MYSQLDB);

        Menu f = new Menu("Java Project");
        f.setVisible(true);
    }

}

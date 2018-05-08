import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//        QueryBuilder query  = new QueryBuilder();
//        query.insertFromFile();
//        query.Inactivare();
//        query.StergeAdresaInactiva();
//        String dept = "HR";
//        query.SortareAdrese(dept);
//        String email = "ex@example.com";
//        String pass = "password";
//        query.ChangePass(email,pass);

public class QueryBuilder {

    public static Statement stmt ;

    public QueryBuilder() throws SQLException {
        Connection con = DBUtil.getConnection(DBType.MYSQLDB);
        stmt = con.createStatement();
    }

    public static void insertFromFile() throws SQLException {
                    String sql_insert = "insert into email values ('Sandu', 'Bogdan', 'ex@example.com', 'pass', 'HR', 'manager', 'employee', 1, '2018-02-6')";
                    int result_insert = stmt.executeUpdate(sql_insert);
            if(result_insert > 0)
                System.out.println("Insert cu succes!");
            else
                System.out.println("Insert fara succes!");
    }

    public static void Inactivare() throws SQLException {
        String sql_update = "update email set active = 0 where DATEDIFF(sysdate(),last_login)>=90";
        stmt.addBatch(sql_update);
        sql_update = "update email set active = 1 where DATEDIFF(sysdate(),last_login)<90";
        stmt.addBatch(sql_update);

        int[] result_update = stmt.executeBatch();
            if(result_update[0] > 0)
                System.out.println("Update inactiv cu succes!");
            else
                System.out.println("Update inactiv fara succes!");

                if(result_update[1] > 0)
            System.out.println("Update activ cu succes!");
        else
            System.out.println("Update activ fara succes!");
    }

    public static void StergeAdresaInactiva() throws SQLException {
        String sql_delete = "delete from email where active = 0";
        int result_delete = stmt.executeUpdate(sql_delete);
        if(result_delete > 0)
            System.out.println("Delete cu succes!");
        else
            System.out.println("Delete fara succes!");
    }

    public static void SortareAdrese(String dept) throws SQLException {
//        String dept = "HR";
        String sql_select = "select * from email where department = '" + dept + "' order by first_name, last_name";
        ResultSet rs = stmt.executeQuery(sql_select);

            while(rs.next()){
                System.out.println(rs.getString(1) + " " + rs.getString(2));
            }
    }


    public void ChangePass(String email, String pass) throws SQLException {


        String sql_update = "update email set password = '" + pass + "' where email like '" + email + "'";
        int result_update = stmt.executeUpdate(sql_update);
        if(result_update > 0)
            System.out.println("Update inactiv cu succes!");
        else
            System.out.println("Update inactiv fara succes!");
    }

}

import javax.swing.*;
import java.sql.*;

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
    public static Connection con;

    public QueryBuilder() throws SQLException {
        con = DBUtil.getConnection(DBType.MYSQLDB);
        stmt = con.createStatement();
    }

    public static void insertIntoTable(String[] word) throws SQLException {
                    String sql_insert = "insert into email values ('" + word[0] + "', '" + word[1] + "', '" + word[2] + "', '" + word[3] + "', '" + word[4] + "', '" + word[5] + "', '" + word[6] + "', 1, sysdate())";
                    int result_insert = stmt.executeUpdate(sql_insert);
            if(result_insert > 0)
                System.out.println("Insert cu succes!");
            else
                System.out.println("Insert fara succes!");
    }

    public static void  addEmail(JTextField[] fields) throws SQLException {
        String firstname = fields[0].getText();
        String lastname = fields[1].getText();
        String email = fields[2].getText();
        String pass = fields[3].getText();
        for(int i=0; i<4;i++)
            System.out.println(fields[i].getText());
        String sql_insert = "insert into email values ('" + firstname +"','" + lastname +"','" + email +"','" + pass +"','HRR','administrator','employer',1,'2018-01-09')";
        int result_insert = stmt.executeUpdate(sql_insert);
        if(result_insert > 0)
            System.out.println("Insert cu succes!");
        else
            System.out.println("Insert fara succes!");

        JOptionPane.showMessageDialog(null, "row successfully inserted", "Insert", JOptionPane.INFORMATION_MESSAGE);

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

    public ResultSet selectAll() throws SQLException {
        String sql_select = "select * from email";
        ResultSet rs = stmt.executeQuery(sql_select);
        return rs;
    }

}

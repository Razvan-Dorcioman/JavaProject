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

    public static Statement stmt;
    public static Connection con;

    public QueryBuilder() throws SQLException {
        con = DBUtil.getConnection(DBType.MYSQLDB);
        stmt = con.createStatement();
    }

    public static void insertIntoTable(String[] word) throws SQLException {
        String sql_insert = "insert into email values ('" + word[0] + "', '" + word[1] + "', '" + word[2] + "', '" + word[3] + "', '" + word[4] + "', '" + word[5] + "', '" + word[6] + "', 1, sysdate())";
        int result_insert = stmt.executeUpdate(sql_insert);
    }

    public static void addEmail(JTextField firstName, JTextField lastName, JTextField Email, JTextField password, JComboBox department, JComboBox function, JComboBox accoutType) throws SQLException {
        String firstname = firstName.getText();
        String lastname = lastName.getText();
        String email = Email.getText();
        String pass = password.getText();
        String dept = department.getSelectedItem().toString();
        String func = function.getSelectedItem().toString();
        String accounttype = accoutType.getSelectedItem().toString();
        System.out.println(dept);
        System.out.println(func);
        System.out.println(accounttype);

        String sql_insert = "insert into email values ('" + firstname + "','" + lastname + "','" + email + "','" + pass + "','" + dept + "','" + func + "','" + accounttype + "',1,'2018-01-09')";
        int result_insert = stmt.executeUpdate(sql_insert);
        if (result_insert > 0)
            JOptionPane.showMessageDialog(null, "Update a new employer succesful", "Add email", JOptionPane.INFORMATION_MESSAGE);

    }

    public static void SetInactive() throws SQLException {
        String sql_update = "update email set active = 0 where DATEDIFF(sysdate(),last_login)>=90";
        stmt.addBatch(sql_update);
        sql_update = "update email set active = 1 where DATEDIFF(sysdate(),last_login)<90";
        stmt.addBatch(sql_update);

        int[] result_update = stmt.executeBatch();
        JOptionPane.showMessageDialog(null, "All the addresses which were not used for 3 months are inactives!", "Inactive emails", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void DeleteInactiveEmails() throws SQLException {
        String sql_delete = "delete from email where active = 0";
        int result_delete = stmt.executeUpdate(sql_delete);
        JOptionPane.showMessageDialog(null, "Addresses inactives had been deleted!", "Delete inactive emails", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void SortEmails(String dept) throws SQLException {
//        String dept = "HR";
        String sql_select = "select * from email where department = '" + dept + "' order by first_name, last_name";
        ResultSet rs = stmt.executeQuery(sql_select);

        while (rs.next()) {
            System.out.println(rs.getString(1) + " " + rs.getString(2));
        }
    }


    public void ChangePass(String email, String pass) throws SQLException {


        String sql_update = "update email set password = '" + pass + "' where email like '" + email + "'";
        int result_update = stmt.executeUpdate(sql_update);
        JOptionPane.showMessageDialog(null, "The password was modified!", "Modify password", JOptionPane.INFORMATION_MESSAGE);
    }

    public ResultSet selectAll() throws SQLException {
        String sql_select = "select * from email";
        ResultSet rs = stmt.executeQuery(sql_select);
        JOptionPane.showMessageDialog(null, "Rows inserted in file from database succesful!", "Save emails in file", JOptionPane.INFORMATION_MESSAGE);
        return rs;
    }

}

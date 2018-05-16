import javax.swing.*;
import java.sql.*;

public class QueryBuilder {

    public static Statement stmt;
    public static Connection con;

    public QueryBuilder() throws SQLException {
        con = DBUtil.getConnection(DBType.MYSQLDB);
        stmt = con.createStatement();
    }

    public static ResultSet getDepartments() throws SQLException {
        String sql_select = "select name_department from departments";
        ResultSet rs = stmt.executeQuery(sql_select);

        return rs;
    }

    public static ResultSet getFunctions() throws SQLException {
        String sql_select = "select name_function from functions";
        ResultSet rs = stmt.executeQuery(sql_select);

        return rs;
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
        JOptionPane.showMessageDialog(null, "All the addresses which were not used for 3 months are now inactives!", "Inactive emails", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void DeleteInactiveEmails() throws SQLException {
        String sql_delete = "delete from email where active = 0";
        int result_delete = stmt.executeUpdate(sql_delete);
        JOptionPane.showMessageDialog(null, "Inactive addresses has been deleted!", "Delete inactive emails", JOptionPane.INFORMATION_MESSAGE);
    }

    public static ResultSet SortEmails(String dept) throws SQLException {
        String sql_select = "select * from email where departament = '" + dept + "' order by first_name, last_name";
        ResultSet rs = stmt.executeQuery(sql_select);

      return rs;
    }

    public int VerifyEmailPass(JTextField Email, JTextField oldPass) throws SQLException {
        String email = Email.getText();
        String pass = oldPass.getText();
        String sql_select = "select email, password from email where email = '" + email + "' and password = '" + pass + "'";
        ResultSet rs = stmt.executeQuery(sql_select);
        int c = 0;
        if(rs.next())
            c++;
        System.out.println(c);
        return c;
    }

    public void ChangePass(JTextField Email,JTextField password) throws SQLException {
        String email = Email.getText();
        String pass = password.getText();

        String sql_update = "update email set password = '" + pass + "' where email like '" + email + "'";
        int result_update = stmt.executeUpdate(sql_update);
        JOptionPane.showMessageDialog(null, "The password was modified!", "Modify password", JOptionPane.INFORMATION_MESSAGE);
    }

    public void addDepartment(String dept,int n) throws SQLException {
        String sql_insert = "insert into departments values ('" + n + "','" + dept + "')";
        int result_insert = stmt.executeUpdate(sql_insert);
        JOptionPane.showMessageDialog(null, "A new department was inserted", "Add department", JOptionPane.INFORMATION_MESSAGE);
    }

    public ResultSet selectAll() throws SQLException {
        String sql_select = "select * from email";
        ResultSet rs = stmt.executeQuery(sql_select);
        return rs;
    }

}

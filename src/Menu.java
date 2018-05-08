import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Menu extends JFrame {

    private JButton []buttons;
    String[] labels = { "First Name", "Last Name", "Email Address", "Password", "Department", "Function", "Account Type" };
    char[] mnemonics = { 'F', 'L', 'E', 'P', 'D', 'F', 'A' };
    int[] widths = { 15, 15, 15, 15, 15, 15, 15};
    String[] descs = { "First Name", "Last Name", "Email Address", "Password", "Department", "Function", "Account Type" };

    final AddEmail form = new AddEmail(labels, mnemonics, widths, descs);
    final ChangePassword box = new ChangePassword("Change Password Box");


    public Menu(String title){
        super(title);
        setSize(400,400);

        setLocation(100,100);
        setLayout(null);
        setResizable(false);

        Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridLayout(9,1,5,5));



        buttons = new JButton[9];

        buttons[0] = new JButton("Update from file");
        buttons[1] = new JButton("Add email");
        buttons[2] = new JButton("Inactive emails");
        buttons[3] = new JButton("Delete inactive emails");
        buttons[4] = new JButton("Sort by name");
        buttons[5] = new JButton("Modify password");
        buttons[6] = new JButton("Add department");
        buttons[7] = new JButton("Save emails in file");
        buttons[8] = new JButton("EXIT");

        for(int i = 0 ; i < 9 ; i++)
            contentPane.add(buttons[i]);

        initEvent();
    }

    private void initEvent(){
        buttons[8].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Have a nice day!", "Exit Message", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        });

        buttons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Complete all fields!", "Add Email Message", JOptionPane.INFORMATION_MESSAGE);
                form.setVisible(true);
            }
        });

        buttons[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                box.setVisible(true);
            }
        });

    }

    private void updateDBfunction(ActionEvent e){
        JOptionPane.showMessageDialog(null, "Update Database from file succesful!!!", "Update Database", JOptionPane.INFORMATION_MESSAGE);
    }

//    private void insert(ActionEvent e){
//        try{
//            String numeField = nume.getText();
//            Connection con = DBUtil.getConnection(DBType.MYSQLDB);
//            String sql = "insert into email values(?,?,?,?,?,?,?,?,?)";
//            PreparedStatement pstmt = con.prepareStatement(sql);
//            pstmt.setString(1, numeField);
//            pstmt.setString(2, "");
//            pstmt.setString(3, "");
//            pstmt.setString(4, "");
//            pstmt.setString(5, "");
//            pstmt.setString(6, "");
//            pstmt.setString(7, "");
//            pstmt.setInt(8, 1);
//            pstmt.setDate(9, Date.valueOf(""));
//            pstmt.execute();
//
//            JOptionPane.showMessageDialog(null, "row successfully inserted", "Insert", JOptionPane.INFORMATION_MESSAGE);
//        }
//        catch (Exception ex){
//            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
}

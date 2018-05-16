import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;

public class Menu extends JFrame {

    private JButton []buttons;


    final AddEmail form = new AddEmail();
    final ChangePassword box = new ChangePassword("Change Password Box");


    public Menu(String title) throws SQLException {
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

        buttons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateDBfunction(e);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        buttons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                form.setVisible(true);
            }
        });

        buttons[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    QueryBuilder query = new QueryBuilder();
                    query.SetInactive();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        buttons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    QueryBuilder query = new QueryBuilder();
                    query.DeleteInactiveEmails();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        buttons[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                box.setVisible(true);
            }
        });

        buttons[6].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        buttons[7].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    exportDB(e);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        buttons[8].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Au revoir, mes amis!", "Exit Message", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        });

    }

    private void updateDBfunction(ActionEvent e) throws IOException {
        FileInputStream fstream = new FileInputStream("inputFile.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String line;
        while ((line = br.readLine()) != null)   {
            String[] word = line.split(" ");
            try {
                QueryBuilder query = new QueryBuilder();
                query.insertIntoTable(word);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        JOptionPane.showMessageDialog(null, "Update Database from file succesful!!!", "Update Database", JOptionPane.INFORMATION_MESSAGE);
    }

    private void exportDB(ActionEvent e) throws FileNotFoundException, UnsupportedEncodingException, SQLException {
        PrintWriter writer = new PrintWriter("outputFile.txt", "UTF-8");
        QueryBuilder query = new QueryBuilder();
        ResultSet rs = query.selectAll();
        while(rs.next()) {
            writer.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6) + " " + rs.getString(7) + " " + rs.getString(8) + " " + rs.getString(9));
        }
        writer.close();
    }

}

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DisplayDB extends JFrame {

    String[] columnNames = {"First Name", "Last Name", "Email", "Password", "Department", "Function", "Account Type", "Active", "Last Login"};
    String[][] data = new String[25][9];

    QueryBuilder query;
    JTable table;

    String[] messageDepartment = {"Select Department...", "HR", "Marketing", "Finance"};
    JComboBox messageListDepartment;

    public DisplayDB() throws SQLException {
        super("Display Data Base");
        setSize(1200, 720);

        setLocation(100, 100);
        setLayout(null);
        setResizable(false);

        messageListDepartment = new JComboBox(messageDepartment);
        messageListDepartment.setBounds(5, 5, 140, 30);
        messageListDepartment.setSelectedIndex(0);
        messageListDepartment.setVisible(true);
        add(messageListDepartment);

        query = new QueryBuilder();

        initEvent();
    }

    private void getDept(String msg) throws SQLException {

        for(int i = 0 ; i < data.length; i++)
            for(int j = 0 ; j < data[i].length; j++)
                data[i][j] = "    ";

        ResultSet rs;
        if(msg == "Select Department...")
            rs = query.selectAll();
        else
        rs = query.SortEmails(msg);
        int k = 0;
        while(rs.next()){
            for(int i = 1; i <= 9 ; i++){
                data[k][i - 1] = rs.getString(i);
            }
            k++;
        }

        table = new JTable(data, columnNames);

        table.setBounds(5,120,1190,500);
        table.setVisible(true);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(5,120,1190,500);

        sp.setVisible(true);
        add(sp);
    }


    private void initEvent() {
        messageListDepartment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == messageListDepartment) {
                    JComboBox cb = (JComboBox) e.getSource();
                    String msg = (String) cb.getSelectedItem();

                    try {
                        getDept(msg);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                }
            }
    });
    }
}

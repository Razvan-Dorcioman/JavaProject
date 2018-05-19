import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddDepartment extends JFrame {

    private JButton add = new JButton("Add");
    private JLabel name = new JLabel("Department Name: ");
    private JTextField nameField = new JTextField();

    AddDepartment(){
        super("Add Department Box");
        setSize(300, 150);

        setLocation(600, 300);
        setLayout(null);
        setResizable(false);

        name.setBounds(10, 10, 120, 20);
        nameField.setBounds(140, 10 ,100, 20);
        add.setBounds(100, 50, 80, 25);

        add(add);
        add(name);
        add(nameField);

        initEvent();

    }

    private void initEvent() {
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    QueryBuilder query = new QueryBuilder();

                    int check = query.VerifyDepartments(nameField.getText());

                    if(check != 0){
                        JOptionPane.showMessageDialog(null, "This department already exist!", "Add department", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        query.addDepartment(nameField.getText());
                    }

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}

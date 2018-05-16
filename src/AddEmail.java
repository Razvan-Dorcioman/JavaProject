import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddEmail extends JFrame {

    ArrayList<String> messageFunction = new ArrayList<String>();
    JComboBox messageListFunction;
    ArrayList<String> messageDepartment = new ArrayList<String>();
    JComboBox messageListDepartment ;
    String[] messageAccountType = {"Select Account Type...", "Member", "VIP", "Admin"};
    JComboBox messageListAccountType = new JComboBox(messageAccountType);
    private JButton submit = new JButton("Submit!");

    private JTextField firstName = new JTextField();
    private JLabel firstNameLabel = new JLabel("First Name: ");
    private JTextField lastName = new JTextField();
    private JLabel lastNameLabel = new JLabel("Last Name: ");
    private JTextField email = new JTextField();
    private JLabel emailLabel = new JLabel("Email Address: ");
    private JTextField password = new JTextField();
    private JLabel passwordLabel = new JLabel("Password: ");
    private JLabel departmentLabel = new JLabel("Department: ");
    private JLabel functionLabel = new JLabel("Function: ");
    private JLabel accountTypeLabel = new JLabel("Account Type: ");


    // Create a form with the specified labels, tooltips, and sizes.
    public AddEmail() throws SQLException {
        super("Add Email Box");
        setSize(400, 300);

        setLocation(600, 300);
        setLayout(null);
        setResizable(false);

        messageFunction.add("Select Function...");
        QueryBuilder query = new QueryBuilder();
        ResultSet rs1 = query.getFunctions();
        while (rs1.next()) {
            messageFunction.add(rs1.getString(1));
        }

        messageDepartment.add("Select Department...");
        ResultSet rs2 = query.getDepartments();
        while (rs2.next()) {
            messageDepartment.add(rs2.getString(1));
        }

        String[] FunctionString = messageFunction.toArray(new String[messageFunction.size()]);
        messageListFunction = new JComboBox(FunctionString);

        String[] DEpartmentString = messageDepartment.toArray(new String[messageDepartment.size()]);
        messageListDepartment = new JComboBox(DEpartmentString);

        initComponent();
        initEvent();
    }

    private void initComponent() {
        submit.setBounds(300, 230, 80, 25);

        add(submit);

        firstName.setBounds(120, 10, 150, 20);
        firstNameLabel.setBounds(20, 10, 100, 20);

        add(firstName);
        add(firstNameLabel);

        lastName.setBounds(120, 40, 150, 20);
        lastNameLabel.setBounds(20, 40, 100, 20);

        add(lastName);
        add(lastNameLabel);

        email.setBounds(120, 70, 150, 20);
        emailLabel.setBounds(20, 70, 100, 20);

        add(email);
        add(emailLabel);

        password.setBounds(120, 100, 150, 20);
        passwordLabel.setBounds(20, 100, 100, 20);

        add(password);
        add(passwordLabel);

        messageListDepartment.setBounds(120, 130, 150, 20);
        departmentLabel.setBounds(20, 130, 100, 20);
        messageListDepartment.setSelectedIndex(0);

        add(messageListDepartment);
        add(departmentLabel);

        messageListFunction.setBounds(120, 160, 150, 20);
        functionLabel.setBounds(20, 160, 100, 20);
        messageListFunction.setSelectedIndex(0);

        add(messageListFunction);
        add(functionLabel);

        messageListAccountType.setBounds(120, 190, 150, 20);
        accountTypeLabel.setBounds(20, 190, 100, 20);
        messageListAccountType.setSelectedIndex(0);

        add(messageListAccountType);
        add(accountTypeLabel);
    }

    private void initEvent() {
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                try {
                    QueryBuilder query = new QueryBuilder();
                    if (!firstName.getText().equals("") && !lastName.getText().equals("") && !email.getText().equals("") && !password.getText().equals(""))
                        if (messageListDepartment.getSelectedItem().toString() != "Select Department..." && messageListFunction.getSelectedItem().toString() != "Select Function..." && messageListAccountType.getSelectedItem().toString() != "Select Account Type...")
                            query.addEmail(firstName, lastName, email, password, messageListDepartment, messageListFunction, messageListAccountType);
                        else
                            JOptionPane.showMessageDialog(null, "Select an option for each!", "Advertisment", JOptionPane.INFORMATION_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(null, "Complete all fields!", "Advertisment", JOptionPane.INFORMATION_MESSAGE);


                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

    }


}

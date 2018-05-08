import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;

public class ChangePassword extends JFrame {

    private JButton update = new JButton("Update!");

    private JTextField email = new JTextField();

    private JLabel emailName = new JLabel("Email: ");

    private JTextField oldPass = new JTextField();

    private JLabel oldPassName = new JLabel("Old Password: ");

    private JTextField newPass = new JTextField();

    private JLabel newPassName = new JLabel("New Password: ");

    public ChangePassword(String title){
        super(title);
        setSize(400,200);

        setLocation(600,400);
        setLayout(null);
        setResizable(false);

        initComponent();
        initEvent();
    }
    private void initComponent(){
        update.setBounds(300,130,80,25);

        add(update);

        email.setBounds(120,10, 150,20);
        emailName.setBounds(20,10,100,20);

        add(email);
        add(emailName);

        oldPass.setBounds(120,40, 150,20);
        oldPassName.setBounds(20,40,100,20);

        add(oldPass);
        add(oldPassName);

        newPass.setBounds(120,70, 150,20);
        newPassName.setBounds(20,70,100,20);

        add(newPass);
        add(newPassName);
    }

    private void initEvent(){

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Update-ul a functionat");
            }
        });
    }
}

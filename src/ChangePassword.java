import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;

public class ChangePassword extends JFrame {

    private JButton exit = new JButton("exit");
    private JButton insert = new JButton("insert");

    private JTextField nume = new JTextField();

    private JLabel numeLabel = new JLabel("Nume: ");

    public ChangePassword(String title){
        super(title);
        setSize(400,200);

        setLocation(100,100);
        setLayout(null);
        setResizable(false);

        initComponent();
        initEvent();
    }
    private void initComponent(){
        exit.setBounds(300,130,80,25);
        insert.setBounds(300,100,80,25);

        add(exit);
        add(insert);

        nume.setBounds(100,10, 100,20);
        numeLabel.setBounds(20,10,100,20);

        add(nume);
        add(numeLabel);
    }

    private void initEvent(){
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Test");
            }
        });
    }
}

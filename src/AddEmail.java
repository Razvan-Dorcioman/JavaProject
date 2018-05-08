import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddEmail extends JFrame {

    private JTextField[] fields;
    JButton submit;

    // Create a form with the specified labels, tooltips, and sizes.
    public AddEmail(String[] labels, char[] mnemonics, int[] widths, String[] tips) {
        super("Add Email Box");
        setSize(400,250);
        setLocation(600,400);
        setResizable(false);

        submit =  new JButton("Submit Form");
        JPanel labelPanel = new JPanel(new GridLayout(labels.length, 1));
        JPanel fieldPanel = new JPanel(new GridLayout(labels.length, 1));
        add(labelPanel, BorderLayout.WEST);
        add(fieldPanel, BorderLayout.CENTER);
        fields = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i += 1) {
            fields[i] = new JTextField();
            if (i < tips.length)
                fields[i].setToolTipText(tips[i]);
            if (i < widths.length)
                fields[i].setColumns(widths[i]);

            JLabel lab = new JLabel(labels[i], JLabel.RIGHT);
            lab.setLabelFor(fields[i]);
            if (i < mnemonics.length)
                lab.setDisplayedMnemonic(mnemonics[i]);

            labelPanel.add(lab);
            JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
            p.add(fields[i]);
            fieldPanel.add(p);
            if(i == labels.length-1)
                p.add(submit);
        }

        initEvent();
    }

    private void initEvent(){
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    QueryBuilder query = new QueryBuilder();
                    query.addEmail(fields);

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "Have a nice day!", "Exit Message", JOptionPane.INFORMATION_MESSAGE);
//                System.exit(0);
                //form.setVisible(false);
            }
        });
    }

}


import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author p.petropoulos
 */
public class MyGUI extends JFrame {

    private JPanel r1, r2, r3, r4;
    private JTextField amount, date, token;
    private JLabel a, d, t;
    private JButton submit, get, insert, remains;

    public MyGUI() {

        r1 = new JPanel();
        r2 = new JPanel();
        r3 = new JPanel();
        r4 = new JPanel();

        setSize(900, 500);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        amount = new JTextField(20);
        date = new JTextField(20);
        token = new JTextField(20);
        submit = new JButton("submit");
        get = new JButton("retrieveAll");
        insert = new JButton("insertIncome");
        remains = new JButton("get remains per day");
        a = new JLabel("amount: ");
        d = new JLabel("Date: ");
        t = new JLabel("token: ");
        Container pane = getContentPane();

        GridLayout glayout = new GridLayout(3, 4);
        pane.setLayout(glayout);
        FlowLayout layout = new FlowLayout();
        r1.setLayout(layout);
        r1.add(a);
        r1.add(amount);
        pane.add(r1);
        r2.setLayout(layout);
        r2.add(d);
        r2.add(date);
        pane.add(r2);
        r3.setLayout(layout);
        r3.add(submit);
        r3.add(get);
        r3.add(insert);
        r3.add(remains);
        pane.add(r3);
        pack();

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Double am = Double.parseDouble(amount.getText());
                Outgoing o = new Outgoing(am, date.getText());
            }
        });

        get.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                Outgoing o = new Outgoing();
                o.getRequest();
            }
        });

        insert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                Double am = Double.parseDouble(amount.getText());
                Income i = new Income(am, date.getText());
            }
        });

        remains.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                
                Income i = new Income();
                i.getRequest();
            }
        });

    }
}

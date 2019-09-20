
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author p.petropoulos
 */
public class Login extends JFrame {

    private String token;
    private JPanel r1, r2;
    private JTextField mail;
    private JLabel a;
    private JButton submit;

    public Login() {
        super();
        r1 = new JPanel();
        r2 = new JPanel();
        setSize(900, 500);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        mail = new JTextField(20);
        submit = new JButton("submit");
        a = new JLabel("email: ");
        Container pane = getContentPane();

        GridLayout glayout = new GridLayout(2, 2);
        pane.setLayout(glayout);
        FlowLayout layout = new FlowLayout();
        r1.setLayout(layout);
        r1.add(a);
        r1.add(mail);
        pane.add(r1);
        r2.setLayout(layout);
        r2.add(submit);
        pane.add(r2);

        pack();

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String to = mail.getText().toString();
                SendMail s = new SendMail(to);

            }
        });
    }

}

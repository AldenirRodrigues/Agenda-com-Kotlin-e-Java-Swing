import business.ContactBusiness;

import javax.swing.*;
import java.awt.*;

public class ContactForm extends JFrame {

    private JPanel rootPainel;
    private JTextField textName;
    private JTextField textPhone;
    private JButton btnSave;
    private JButton btnCancel;
    private ContactBusiness mContactBisness;


    public ContactForm() {
        setContentPane(rootPainel);
        setSize(500, 250);
        setVisible(true);

        Dimension dimen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dimen.width / 2 - getSize().width / 2, dimen.height / 2 - getSize().height / 2);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mContactBisness = new ContactBusiness();

        setListeners();
    }

    private void setListeners() {

        btnSave.addActionListener(e -> {

            try{
                String name = textName.getText();
                String phone = textPhone.getText();

                mContactBisness.save(name, phone);

                new MainForm();
                dispose();
            }catch (Exception exception){
                JOptionPane.showMessageDialog(new JFrame(), exception.getMessage());
            }

        });

        btnCancel.addActionListener(e -> {
            new MainForm();
            dispose();
        });
    }
}

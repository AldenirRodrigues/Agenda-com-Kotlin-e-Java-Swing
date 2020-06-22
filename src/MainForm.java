import business.ContactBusiness;
import entity.ContactEntity;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainForm extends JFrame {

    private JButton btnNewContact;
    private JButton btnRemove;
    private JTable tableContacts;
    private JPanel rootPainel;
    private JLabel labelContactCount;
    private final ContactBusiness mBusiness;
    private String mName = "";
    private String mPhone = "";

    public MainForm() {
        setContentPane(rootPainel);
        setSize(500, 250);
        setVisible(true);

        Dimension dimen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dimen.width / 2 - getSize().width / 2, dimen.height / 2 - getSize().height / 2);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mBusiness = new ContactBusiness();

        setListeners();
        loadContacts();

    }

    private void loadContacts() {
        List<ContactEntity> contactList = mBusiness.getList();
        String[] columnNames = {"Nome", "Telefone"};
        DefaultTableModel tableModel = new DefaultTableModel(new Object[0][0], columnNames);

        for (ContactEntity i : contactList) {
            Object[] object = new Object[2];

            object[0] = i.getName();
            object[1] = i.getPhone();

            tableModel.addRow(object);
        }
        tableContacts.clearSelection();
        tableContacts.setModel(tableModel);
        labelContactCount.setText(mBusiness.getContactCountDescription());

    }

    private void setListeners() {
        btnNewContact.addActionListener(e -> {
            new ContactForm();
            dispose();
        });


        tableContacts.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    if (tableContacts.getSelectedRow() != -1) {
                        mName = tableContacts.getValueAt(tableContacts.getSelectedRow(), 0).toString();
                        mPhone = tableContacts.getValueAt(tableContacts.getSelectedRow(), 1).toString();

                    }
                }
            }
        });
        btnRemove.addActionListener(e -> {
            try{
                mBusiness.delete(mName, mPhone);
                loadContacts();
                mName = "";
                mPhone = "";
            }catch (Exception exception){
                JOptionPane.showMessageDialog(new JFrame(), exception.getMessage());
            }


        });
    }


}

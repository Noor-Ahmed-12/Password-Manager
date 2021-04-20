/*
  Semester project of DSA: 'Password Manager'
       Features:
            1)Strong and secure Password Generator
            2)Advanced Password Encryption(hashing)
            3)Password Storing
            4)Password Searching
            5)Password Deletion


  @Developer (Noor Ahmed shaikh)
 * @roll_no (19sw24)
 * @version (1.0)
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordManager implements ActionListener {

    //Store password class reference
    HashtablePassword data = new HashtablePassword(15,0.5F,0);

    JFrame frame;
    JFrame frame2;
    Container conn1,conn2;
    JLabel lAcc,lPass;
    JTextArea encryptPasswdArea, genePassArea, searchPassArea;
    JButton PassGeneBtn,PassEncryptBtn, PassStoreBtn, PassSearchBtn, AccAddBtn, PassDeleteBtn;
    JTextField tAcc,tPass;
    @Override
    public void actionPerformed(ActionEvent e) { }

    //Frame settings
    public static void FrameGUI(JFrame frame){
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
    }

    //container settings
    public static void ContainerGUI(Container conn){
        conn.setVisible(true);
        conn.setBackground(Color.getHSBColor(20.4f, 10.5f, 12.9f));
        conn.setLayout(null);
    }


    // buttons settings
    public void GUIButtonsSetting(JButton btn){
        btn.setBackground(Color.LIGHT_GRAY);
        btn.setForeground(Color.BLACK);
        Font fn = new Font("Arial", Font.PLAIN, 15);
        btn.setFont(fn);
        Cursor crs = new Cursor(Cursor.HAND_CURSOR);
        btn.setCursor(crs);
    }

    //GUI of Store password
    public void StoringGUI()
    {
        frame2 = new JFrame("Store your passwords");
        frame2.setBounds(1400, 700, 600, 500);
        frame2.setSize(500,400);
        FrameGUI(frame2);
        conn2 = frame2.getContentPane();
        ContainerGUI(conn2);
        Font fn = new Font("Arial", Font.BOLD, 20);

        //Account textFiled and label
        lAcc = new JLabel("ENTER ACCOUNT NAME");
        lAcc.setBounds(100, 23, 480, 50);
        lAcc.setFont(fn);
        conn2.add(lAcc);

        tAcc = new JTextField();
        tAcc.setBounds(100,70,300,80);
        tAcc.setFont(fn);
        tAcc.setForeground(Color.DARK_GRAY);
        conn2.add(tAcc);

        //Account password textField and label
        lPass = new JLabel("ENTER ACCOUNT PASSWORD");
        lPass.setBounds(100, 160, 480, 50);
        lPass.setFont(fn);
        conn2.add(lPass);

        tPass = new JTextField();
        tPass.setBounds(100,200,300,80);
        tPass.setFont(fn);
        tPass.setForeground(Color.DARK_GRAY);
        conn2.add(tPass);

        AccAddBtn = new JButton("STORE");
        AccAddBtn.setBounds(170, 290, 150, 50);
        conn2.add(AccAddBtn);
        GUIButtonsSetting(AccAddBtn);
    }

    //for password generator and encryption
    public void textArea(String Pass,JTextArea TA){
        TA.setText(Pass);
        Font fn = new Font("Arial", Font.BOLD, 20);
        TA.setWrapStyleWord(true);
        TA.setLineWrap(true);
        TA.setCaretPosition(0);
        TA.setEditable(false);
        TA.setFont(fn);

    }
    PasswordManager() {
        frame = new JFrame("Password Manager");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("img/icon.png"));
//        frame.setBounds(300, 100, 700, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(580,630);
        FrameGUI(frame);

        conn1 = frame.getContentPane();
        ContainerGUI(conn1);


        //Generator buttons settings
        PassGeneBtn = new JButton("GENERATE PASSWORD");
        PassGeneBtn.setBounds(160, 80, 220, 70);
        conn1.add(PassGeneBtn);
        GUIButtonsSetting(PassGeneBtn);
//----------------------------------------------------------------------------------------------------------------------
        //generating password
        PassGeneBtn.addActionListener(e -> {
        if(PassGeneBtn ==e.getSource())
        {
            try{
                int len = Integer.parseInt(JOptionPane.showInputDialog("Enter the password length"));
                if(len>8)
                {
                    //  password generator class reference
                    PasswordGenerator pass = new PasswordGenerator();
                    String passwd = pass.generatePassword(len);
                    genePassArea = new JTextArea(5,4);
                    textArea(passwd,genePassArea);
                    JOptionPane.showMessageDialog(conn1,new JScrollPane(genePassArea),"Copy your password",JOptionPane.INFORMATION_MESSAGE);

                }
                else JOptionPane.showMessageDialog (conn1,"Password length must be greater than 8!","Invalid Input Error",JOptionPane.WARNING_MESSAGE);

            }
            catch(Exception ex){JOptionPane.showMessageDialog(conn1,ex.getMessage(),"EXIT!",JOptionPane.ERROR_MESSAGE);}
        }
    }
    );
 //----------------------------------------------------------------------------------------------------------------------
        //Encryption Button
        PassEncryptBtn = new JButton("ENCRYPT PASSWORD");
        GUIButtonsSetting(PassEncryptBtn);
        PassEncryptBtn.setBounds(160, 180, 220, 70);
        conn1.add(PassEncryptBtn);
        PassEncryptBtn.addActionListener(e -> {
                    if (PassEncryptBtn == e.getSource()) {
                        try {
                            String simplePasswd = JOptionPane.showInputDialog("Enter your Password");
                            if (!simplePasswd.isEmpty()) {
                                byte[] salt = passwordEncryption.getSalt();
                                String encPass = passwordEncryption.get_SHA_1_SecurePassword(simplePasswd, salt);
                                //txtArea adding in the panel
                                encryptPasswdArea = new JTextArea(7, 4);
                                textArea(encPass, encryptPasswdArea);
                                JOptionPane.showMessageDialog(conn1, new JScrollPane(encryptPasswdArea), "Copy your Encrypted password", JOptionPane.INFORMATION_MESSAGE);
                            } else JOptionPane.showMessageDialog(conn1, "Please enter password!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(conn1, ex.getMessage(), "EXIT", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
        );

//----------------------------------------------------------------------------------------------------------------------

        //storing password using hashtable
        PassStoreBtn = new JButton("STORE PASSWORD");
        PassStoreBtn.setBounds(160, 280, 220, 70);
        conn1.add(PassStoreBtn);
        GUIButtonsSetting(PassStoreBtn);
        //Store password action
        PassStoreBtn.addActionListener(e -> {
            if(PassStoreBtn ==e.getSource())
            {
                try{
                    StoringGUI();
                    // action on the Store btn
                    AccAddBtn.addActionListener(e4 -> {
                        if (AccAddBtn == e4.getSource()) {
                            String account_name = tAcc.getText();
                            String acc_pass = tPass.getText();
                            if (account_name.isEmpty() && acc_pass.isEmpty()) {
                                JOptionPane.showMessageDialog(conn2,"unable to store your password!","ERROR",JOptionPane.ERROR_MESSAGE);
                            }
                            else{
                                //calling put method of the hashtablePassword class
                                data.add_Acc(account_name,acc_pass);
                                JOptionPane.showMessageDialog(conn2, "Account added Successfully !");
                                tAcc.setText(null);
                                tPass.setText(null);
                            }
                        }
                      }
                    );
                }
           catch(Exception ex) {JOptionPane.showMessageDialog(conn2,ex.getMessage(),"EXIT",JOptionPane.ERROR_MESSAGE);}
            }
        }
        );

//----------------------------------------------------------------------------------------------------------------------
        //searching password
        PassSearchBtn = new JButton("SEARCH PASSWORD");
        GUIButtonsSetting(PassSearchBtn);
        PassSearchBtn.setBounds(160, 380, 220, 70);
        conn1.add(PassSearchBtn);
        PassSearchBtn.addActionListener(e ->{
            if (PassSearchBtn ==e.getSource()){
                try{
                    String acc_name = JOptionPane.showInputDialog("Enter your Account Name");
                    if (!acc_name.isBlank()) {
                        Object pass = data.get_Acc(acc_name.toLowerCase());
                        if(pass!=null) {
                            searchPassArea = new JTextArea(4,5);
                            textArea(String.valueOf(pass), searchPassArea);
                            JOptionPane.showMessageDialog(conn1, new JScrollPane(searchPassArea), "Copy your password", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else JOptionPane.showMessageDialog(conn1, "Account not Found!");
                    }
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(conn1,ex.getMessage(),"EXIT",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        );
//----------------------------------------------------------------------------------------------------------------------
        // deleting password
        PassDeleteBtn = new JButton("DELETE PASSWORD");
        GUIButtonsSetting(PassDeleteBtn);
        PassDeleteBtn.setBounds(160, 480, 220, 70);
        conn1.add(PassDeleteBtn);
        PassDeleteBtn.addActionListener(e -> {
            if (PassDeleteBtn == e.getSource()) {
                try {
                    String acc_name = JOptionPane.showInputDialog("Enter the Account Name");
                    if (!acc_name.isBlank()) {
                        data.remove_Acc(acc_name.toLowerCase());
                        JOptionPane.showMessageDialog(conn1, "Delete successfully!");
                    }
                    else JOptionPane.showMessageDialog(conn1, "Account not found!", "INFO", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(conn1, ex.getMessage(), "EXIT", JOptionPane.ERROR_MESSAGE);
                }
            }

        }
        );

    }
//----------------------------------------------------------------------------------------------------------------------------
    // main method
    public static void main(String[] args) {
        //loading screen class
        new SplashScreen();
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
            new PasswordManager();
        }catch (Exception ex) { ex.printStackTrace(); }
 }
}
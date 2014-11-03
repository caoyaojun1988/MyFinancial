package com.cao.main;

import com.cao.util.Util;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UI {


    // frame
    private static JFrame jf                        = null;
    // panle
    private        JPanel jpurl                     = null;
    private        JPanel jpurlForTemplate          = null;
    private        JPanel jpurlOrgForTotalAccount   = null;
    private        JPanel jpurlOrgForCurrentAccount = null;
    private        JPanel jpurlOrgForCurrentDetail  = null;
    private        JPanel jpurlDesc                 = null;

    private JPanel       jpcrotor                         = null;
    // label
    private JLabel       jlFileForTemplate                = null;
    private JLabel       jlFileOrgForTotalAccount         = null; // 历史总账表
    private JLabel       jlFileOrgForTotalCurrentAccount  = null; //当月总账表
    private JLabel       jlFileOrgForTotalCurrentDetail   = null; //当月明细
    private JLabel       jlFileDes                        = null;
    // button
    private JButton      jbToProcess                      = null;
    private JButton      jbFOrTemplate                    = null;
    private JButton      jbOpenOrgForTotalAccount         = null;
    private JButton      jbOpenOrgForTotalCurrentAccount  = null;
    private JButton      jbOpenOrgForTotalCurrentDetail   = null;
    private JButton      jbOpenDesc                       = null;
    // TextField
    private JTextField   jtfFileForTemplate               = null;
    private JTextField   jtfFileOrgForTotalAccount        = null;
    private JTextField   jtfFileOrgForCurrentAccount      = null;
    private JTextField   jtfFileOrgForCurrentDetail       = null;
    private JTextField   jtfFileDes                       = null;
    //
    private JFileChooser jFileChooserForTemplate          = null;
    private JFileChooser jFileChooserOrgForTotalAccount   = null;
    private JFileChooser jFileChooserOrgForCurrentAccount = null;
    private JFileChooser jFileChooserOrgForCurrentDetail  = null;
    private JFileChooser jFileChooserDes                  = null;


    static File fileFlag = new File("");
    //
    private Main   main;
    private String fileName;

    public UI() {
        jf = new JFrame("RANDINCE");// frame
        jpurl = new JPanel();

        jpurlForTemplate = new JPanel();
        jpurlOrgForTotalAccount = new JPanel();
        jpurlOrgForCurrentAccount = new JPanel();
        jpurlOrgForCurrentDetail = new JPanel();

        jpurlDesc = new JPanel();
        jpcrotor = new JPanel();

        jlFileForTemplate = new JLabel("模块文件");
        jlFileOrgForTotalAccount = new JLabel("历史总账表");
        jlFileOrgForTotalCurrentAccount = new JLabel("当月总账表");
        jlFileOrgForTotalCurrentDetail = new JLabel("当月明细表");

        jlFileDes = new JLabel("请选择保存目录");

        jbFOrTemplate = new JButton("请选择");
        jbOpenOrgForTotalAccount = new JButton("请选择");
        jbOpenOrgForTotalCurrentAccount = new JButton("请选择");
        jbOpenOrgForTotalCurrentDetail = new JButton("请选择");

        jbOpenDesc = new JButton("请选择");
        jbToProcess = new JButton("运行");

        jtfFileForTemplate = new JTextField("", 25);
        jtfFileOrgForTotalAccount = new JTextField("", 25);
        jtfFileOrgForCurrentAccount = new JTextField("", 25);
        jtfFileOrgForCurrentDetail = new JTextField("", 25);

        jtfFileDes = new JTextField("", 25);

        jFileChooserForTemplate = new JFileChooser();
        jFileChooserOrgForTotalAccount = new JFileChooser();
        jFileChooserOrgForCurrentAccount = new JFileChooser();
        jFileChooserOrgForCurrentDetail = new JFileChooser();

        jFileChooserDes = new JFileChooser();

        main = new Main();

        jpurlForTemplate.setLayout(new FlowLayout());
        jpurlForTemplate.add(jlFileForTemplate);
        jpurlForTemplate.add(jtfFileForTemplate);
        jpurlForTemplate.add(jbFOrTemplate);

        jpurlOrgForTotalAccount.setLayout(new FlowLayout());
        jpurlOrgForTotalAccount.add(jlFileOrgForTotalAccount);
        jpurlOrgForTotalAccount.add(jtfFileOrgForTotalAccount);
        jpurlOrgForTotalAccount.add(jbOpenOrgForTotalAccount);

        jpurlOrgForCurrentAccount.setLayout(new FlowLayout());
        jpurlOrgForCurrentAccount.add(jlFileOrgForTotalCurrentAccount);
        jpurlOrgForCurrentAccount.add(jtfFileOrgForCurrentAccount);
        jpurlOrgForCurrentAccount.add(jbOpenOrgForTotalCurrentAccount);

        jpurlOrgForCurrentDetail.setLayout(new FlowLayout());
        jpurlOrgForCurrentDetail.add(jlFileOrgForTotalCurrentDetail);
        jpurlOrgForCurrentDetail.add(jtfFileOrgForCurrentDetail);
        jpurlOrgForCurrentDetail.add(jbOpenOrgForTotalCurrentDetail);

        jpurlDesc.setLayout(new FlowLayout());
        jpurlDesc.add(jlFileDes);
        jpurlDesc.add(jtfFileDes);
        jpurlDesc.add(jbOpenDesc);

        jpurl.setLayout(new GridLayout(5, 1));
        jpurl.add(jpurlForTemplate);
        jpurl.add(jpurlOrgForTotalAccount);
        jpurl.add(jpurlOrgForCurrentAccount);
        jpurl.add(jpurlOrgForCurrentDetail);
        jpurl.add(jpurlDesc);

        jpcrotor.setLayout(new FlowLayout());
        jpcrotor.add(jbToProcess);

        jf.setLayout(new BorderLayout());
        jf.add(jpurl, "Center");
        jf.add(jpcrotor, "South");
        jf.setSize(100, 100);
        jf.setVisible(true);
        jf.pack();

        jf.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        jbFOrTemplate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int n = jFileChooserForTemplate.showOpenDialog(null);
                String filename = jFileChooserForTemplate.getSelectedFile().toString();
                if (n == JFileChooser.APPROVE_OPTION) {
                    jtfFileForTemplate.setText(filename);
                    //  fileFlag = new File(filename);
                }
            }
        });

        jbOpenOrgForTotalAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int n = jFileChooserOrgForTotalAccount.showOpenDialog(null);
                String filename = jFileChooserOrgForTotalAccount.getSelectedFile().toString();
                if (n == JFileChooser.APPROVE_OPTION) {
                    jtfFileOrgForTotalAccount.setText(filename);
                    fileFlag = new File(filename);
                }
            }
        });

        jbOpenOrgForTotalCurrentAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int n = jFileChooserOrgForCurrentAccount.showOpenDialog(null);
                String filename = jFileChooserOrgForCurrentAccount.getSelectedFile().toString();
                if (n == JFileChooser.APPROVE_OPTION) {
                    jtfFileOrgForCurrentAccount.setText(filename);
                    //fileFlag = new File(filename);
                }
            }
        });

        jbOpenOrgForTotalCurrentDetail.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int n = jFileChooserOrgForCurrentDetail.showOpenDialog(null);
                String filename = jFileChooserOrgForCurrentDetail.getSelectedFile().toString();
                if (n == JFileChooser.APPROVE_OPTION) {
                    jtfFileOrgForCurrentDetail.setText(filename);
                    //fileFlag = new File(filename);
                }
            }
        });

        jbOpenDesc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                jFileChooserDes.setCurrentDirectory(fileFlag);
                jFileChooserDes.setSelectedFile(fileFlag);
                int n = jFileChooserDes.showSaveDialog(null);
                String filename2 = jFileChooserDes.getSelectedFile().toString();
                if (filename2.indexOf(".") != -1) {
                    filename2 = filename2.substring(0, filename2.indexOf("."));
                }
                int flag = jtfFileOrgForTotalAccount.getText().lastIndexOf(".");
                String kuozhan = jtfFileOrgForTotalAccount.getText().substring(flag);
                String str = "00";
                if (n == JFileChooser.APPROVE_OPTION) {
                    jtfFileDes.setText(filename2 + str + kuozhan);
                }
            }
        });

        jbToProcess.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (jtfFileForTemplate.getText().trim().equals("")) {
                    showMessage("请选择模块文件");
                } else if (jtfFileOrgForTotalAccount.getText().trim().equals("")) {
                    showMessage("请选择历史总账表");
                } else if (jtfFileOrgForCurrentAccount.getText().trim().equals("")) {
                    showMessage("请选择当期总账表");
                } else if (jtfFileOrgForCurrentDetail.getText().trim().equals("")) {
                    showMessage("请选择当期明细");
                } else if (jtfFileDes.getText().trim().equals("")) {
                    showMessage("请选择输入目录");
                } else {
                    String template = jtfFileForTemplate.getText().trim();
                    String orgurlForTotalAccount = jtfFileOrgForTotalAccount.getText().trim();
                    String orgurlForCurrentAccount = jtfFileOrgForCurrentAccount.getText().trim();
                    String orgurlForCurrentDetail = jtfFileOrgForCurrentDetail.getText().trim();
                    String desurl = jtfFileDes.getText().trim();
                    String date =
                      desurl = desurl.replaceAll("00", Util.getDateString() + "02");
                    main.ToOpen(template, orgurlForTotalAccount, orgurlForCurrentAccount, orgurlForCurrentDetail, desurl);
                    showMessage("成功");

                }
            }
        });

    }

    public static void showMessage(String mess) {
        JOptionPane.showMessageDialog(jf, mess);
    }

    public static void showException(String mess) {
        JOptionPane.showMessageDialog(jf, mess);
        System.exit(0);

    }
    // /home/caoyaojun/gitspace/test.excel/src/test/resources/template-new.xls
    // /home/caoyaojun/gitspace/test.excel/src/test/resources/10-accountitem.xls
    // /home/caoyaojun/gitspace/test.excel/src/test/resources/10-accountitem.xls
    // /home/caoyaojun/gitspace/test.excel/src/test/resources/9yf.xls
    // /home/caoyaojun/gitspace/test.excel/src/test/resources/test00.xls
    public static void main(String[] args) {
        new UI();
    }
}

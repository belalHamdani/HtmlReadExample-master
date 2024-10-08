
import com.sun.xml.internal.ws.addressing.WsaActionUtil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;


import javax.swing.*;


public class project implements ActionListener {
    private JFrame mainFrame;
    private JLabel statusLabel;
    private JMenuItem cut, copy, paste, selectAll;
    private JTextArea ta; //typing area
    public JTextArea tya;
    public JTextArea po;
    private int WIDTH=800;
    private int HEIGHT=700;


    public project() {
        prepareGUI();
    }

    public static void main(String[] args) {
        project Project = new project();
        Project.showEventDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Reader");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new GridLayout(2,2));

        ta =new JTextArea("one");
        tya=new JTextArea("input link");
        po=new JTextArea("three");

        statusLabel = new JLabel("Label", JLabel.CENTER);
        statusLabel.setSize(350, 100);
        //menu at top
        cut = new JMenuItem("cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");
        selectAll = new JMenuItem("selectAll");
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);


        //end menu at top





        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        mainFrame.setVisible(true);
    }

    private void showEventDemo() {

        JButton StartButton = new JButton("Start");


        StartButton.setActionCommand("Start");
        StartButton.addActionListener(new ButtonClickListener());


        mainFrame.add(StartButton, BorderLayout.SOUTH);
        mainFrame.add(ta);
        mainFrame.add(tya);
        mainFrame.add(po);




        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cut)
            ta.cut();
        if (e.getSource() == paste)
            ta.paste();
        if (e.getSource() == copy)
            ta.copy();
        if (e.getSource() == selectAll)
            ta.selectAll();
    }
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("Start")) {
                System.out.println("Start program");
                try {
                    System.out.println();
                    System.out.print("hello \n");
                    URL url = new URL("https://www.milton.edu/");
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(url.openStream())
                    );

                    String line;

                    while( (line = reader.readLine()) != null )  {

                        if(line.contains("href")) {

                            int start = line.indexOf("href") +6;

                            // System.out.println(line);
                            String link = line.substring(start);
                            //int end = line.indexOf("\"");

                            int end = link.indexOf("\"");
                            if (end ==-1){
                                end = link.indexOf("'");
                            }

                            if (end==-1){
                                end = link.indexOf("--");
                            }


                           // System.out.println(start + ","+end);
                            System.out.print(line.substring(start, start+end)+ "\n");
                            ta.append(line.substring(start, start+end)+"\n");
                            System.out.println(tya.getText());

                        }

                    }
                    reader.close();
                } catch(Exception ex) {
                    System.out.println(ex);
                    System.out.println("double check your url and start again");
                }

            }


        }

    }
}








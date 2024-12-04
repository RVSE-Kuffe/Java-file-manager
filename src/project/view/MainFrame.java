package project.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import project.FileChooser;
import project.strategies.CSVConverter;
import project.Controller;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame{
    public MainFrame(){
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);    //Basic settings for the window
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setTitle("Epic Kevenet CSV Converter");
        this.setResizable(false);

        //Creating a Label for the background
        JLabel background = new JLabel(new ImageIcon("C:/csv_converter/src/project/resources/images/kevenet1.jpg"));
        background.setSize(500, 500);
        background.setLayout(null);

        //Creating and locating the buttons
        JButton CSVConvertButton = new JButton("CSV File konvertálása");
        CSVConvertButton.setBounds(50, 50, 175, 50);
        CSVConvertButton.addActionListener(new ActionListener() {   //Adding ActionListener for the not blind button
            public void actionPerformed(ActionEvent ae){
                CSVConvert();   
            }
        });

        //Blind buttons for possible future functions
        JButton func1Button = new JButton("(fejlesztés alatt)");
        func1Button.setBounds(275, 50, 175, 50); 
        JButton func2Button = new JButton("(fejlesztés alatt)");
        func2Button.setBounds(50, 200, 175, 50);
        JButton func3Button = new JButton("(fejlesztés alatt)");
        func3Button.setBounds(275, 200, 175, 50);

        //Adding the buttons to the Label
        background.add(CSVConvertButton);
        background.add(func1Button);
        background.add(func2Button);
        background.add(func3Button);

        //Adding the background
        this.add(background);

        this.revalidate();
        this.repaint();

        setVisible(true);
    }

    public void CSVConvert(){
        new Controller(new FileChooser("csv"), new CSVConverter()).chooseAndConvert();
    }
}

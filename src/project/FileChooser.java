package project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.awt.Dimension;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FileChooser {
    private String extension;   //A file chooser can only choose one kind of file. It gets the file type from dependency injection
    private File previouslyOpened;

    public FileChooser(String s){
        extension = s;
        try(BufferedReader br = new BufferedReader(new FileReader("C:/csv_converter/src/project/resources/text_files/root.txt"))){
            previouslyOpened = new File(br.readLine());
            br.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public File choose(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setPreferredSize(new Dimension(500, 500));
        fileChooser.setCurrentDirectory(previouslyOpened);

        int result = fileChooser.showOpenDialog(null);

        if(result == JFileChooser.APPROVE_OPTION){  //Chooses a file
            File selectedFile = fileChooser.getSelectedFile();
            if(checkFileType(selectedFile)){
                try(BufferedWriter br = new BufferedWriter(new FileWriter("C:/csv_converter/src/project/resources/text_files/root.txt"))){
                    br.write(selectedFile.getAbsolutePath());
                    br.close();
                }
                catch(Exception e){
                    System.out.println(e);
                }
                return selectedFile;
            }
            else{
                JOptionPane.showMessageDialog(null, "A kiválasztott file nem csv kiterjesztésű", "Hiba", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        return null;
    }   

    public boolean checkFileType(File f){
        String fileName = f.getName();
        int indexOfDot = fileName.lastIndexOf(".");
        String fileExtension = null;
        if(indexOfDot >= 0 && indexOfDot < fileName.length() - 1){
            fileExtension = fileName.substring(indexOfDot + 1).toLowerCase();
        }
        return extension.toLowerCase().equals(fileExtension);
    }
}

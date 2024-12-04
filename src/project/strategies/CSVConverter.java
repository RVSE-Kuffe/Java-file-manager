package project.strategies;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import javax.swing.JOptionPane;

import project.interfaces.IFileConverter;

public class CSVConverter implements IFileConverter {
    public void convert(File f) {
        if(f == null){
            return;
        }

        String line;
        String fileName = createFileName(f);
        File newFile = new File(fileName);
        
        try {
            newFile.createNewFile();
            
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), StandardCharsets.ISO_8859_1));
                 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile), StandardCharsets.ISO_8859_1))) {
                 
                bw.write("pole1;pole2;pole3;pole4;pole5\n");
                int i = 0;
                
                while ((line = br.readLine()) != null) {
                    if (i > 0) {
                        String columns[] = line.split(";");
                        if (columns[14].equals("Jóváírás")) {
                            String outPut = columns[20] + ";" + columns[12] + ";" + columns[13].substring(4) + ";" + dateChanger(columns[6]) + ";" + columns[15] + "\n";
                            bw.write(outPut);
                        }
                    }
                    i++;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        Object[] options = {"igen", "nem"};
        int answer = JOptionPane.showOptionDialog(null, "Szeretnéd megnyitni a keletkezett file-t?", "kérdés", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if(answer == 0){
            openFile(newFile);
        } 
    }

    public String dateChanger(String date) {
        String[] parts = date.split("\\.");
        return parts[2] + "." + parts[1] + "." + parts[0];
    }

    public String createFileName(File f) {
        String[] path = f.getAbsolutePath().split("\\\\");
        String lastPart = "ISP_" + path[path.length - 1];
        StringBuilder toReturn = new StringBuilder();
        
        for (int i = 0; i < path.length - 1; i++) {
            toReturn.append(path[i]).append(File.separator);
        }
        toReturn.append(lastPart);
        return toReturn.toString();
    }

    public void openFile(File f){
        if(!Desktop.isDesktopSupported() || f == null){
            JOptionPane.showMessageDialog(null, "Nem lehet megnyitni a file-t. Próbálja a filekezelőből!", "Hiba", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Desktop desktop = Desktop.getDesktop();
        try{
            desktop.open(f);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
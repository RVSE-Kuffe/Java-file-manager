package project;

import java.io.File;

import project.interfaces.IFileConverter;

public class Controller {
    private FileChooser fileChooser;
    private IFileConverter fileConverter;

    public Controller(FileChooser fch, IFileConverter fcv){
        fileChooser = fch;
        fileConverter = fcv;
    }

    public void chooseAndConvert(){
        File toConvert = fileChooser.choose();
        if(toConvert != null){
            fileConverter.convert(toConvert);
        }
    }
}

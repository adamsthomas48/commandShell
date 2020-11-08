import java.awt.*;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cd {
    Cd(){};

    static String moveUp(String path){
        String[] splitPath = path.split("\\\\");
        String updatedPath = "";

        for(int i = 0; i < splitPath.length - 1; i++){
            updatedPath += splitPath[i] + "\\";
        }

        return updatedPath.substring(0, updatedPath.length() - 1);

    }

    static String moveDown(String path, String fName){


        File f = new File(path);
        File[] files = f.listFiles();
        boolean fileExists = false;
        assert files != null;
        for(File file : files){
            if(file.getName().equals(fName)){
                fileExists = true;
                break;
            }
        }

        if(fileExists){
            String updatedPath = "";
            updatedPath = path + "\\" + fName;
            return updatedPath;
        }
        else{
            System.out.println("Error: File Doesn't Exist Or Is Not A Valid Directory!");
            return path;
        }

    }

}

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mdir {
    Mdir(){};

    void makeDirectory(String path, String name){
        System.out.println(name);
        File file = new File(path, name);
        if(file.mkdir()){
            System.out.println("Created Successfully");
        }
        else {
            System.out.println("File could not be created");
        }
    }


}

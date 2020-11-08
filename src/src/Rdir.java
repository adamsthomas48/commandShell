import java.io.File;

public class Rdir {
    static void removeDirectory(String path, String name){
        System.out.println(name);
        File file = new File(path, name);
        if(file.delete()){
            System.out.println("Deleted Successfully");
        }
        else {
            System.out.println("File could not be deleted");
        }
    }
}

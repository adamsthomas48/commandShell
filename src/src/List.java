import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class List {
    List(){};
    void getList(String path){
        File f = new File(path);
        File[] files = f.listFiles();
        assert files != null;
        for(File file : files){
            String d, r, w, x;
            if(file.isDirectory()){ d = "d"; }
            else {d = "-";}

            if(file.canRead()){ r = "r"; }
            else { r = "-"; }

            if (file.canWrite()){ w = "w"; }
            else{ w = "-"; }

            if (file.canExecute()){ x = "x"; }
            else{ x = "-"; }




            System.out.print(d + r + w  + x + " ");
            System.out.print(String.format("%10s", file.length()));
            System.out.print("    " + new SimpleDateFormat("MMM d yyyy h:mm").format(new Date(file.lastModified())));
            System.out.print("     " + file.getName());
            System.out.println();
        }
        System.out.println();
    }
}

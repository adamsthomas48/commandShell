import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    //$ java -jar build/libs/Main.jar
    public static void main(String[] args) throws IOException, InterruptedException {
        History history = new History();
        String currentDirectory = System.getProperty("user.dir");
        float ptime = 0;

        label:
        while (true) {
            System.out.println("[" + currentDirectory + "]:");
            Scanner input = new Scanner(System.in);
            String inputString = input.nextLine();

            String[] commands = inputString.split("\\s+");


            if(commands[0].startsWith("^")){

                if(commands.length > 1){
                    int index = Integer.parseInt(commands[1]);
                    ArrayList<String> currHistory = history.getCmdHistory();
                    commands = currHistory.get(index - 1).split("\\s+");

                    System.out.println(commands[0]);
                }
                else{
                    System.out.println("Error: Not enough commands");
                }
                inputString = "";
                for(String string : commands){
                    inputString += string + " ";
                }


            }

            switch (commands[0]) {
                case "history":
                case "History":
                    history.printHistory();
                    break;
                case ("exit"):
                    break label;
                case "cd":
                    Cd cd = new Cd();
                    if(commands.length < 2){
                        System.out.println("Error: Not Enough Commands");
                    }
                    else if(commands[1].equals("..")) {
                        currentDirectory = Cd.moveUp(currentDirectory);
                    }
                    else{
                        CreateString createString = new CreateString();
                        String name = createString.create(commands);
                        currentDirectory = Cd.moveDown(currentDirectory, name);
                    }
                    history.addHistory(inputString);
                    break;
                case "mdir":
                    if (commands.length >= 2) {
                        Mdir mdir = new Mdir();
                        CreateString createString = new CreateString();
                        String name = createString.create(commands);
                        mdir.makeDirectory(currentDirectory, name);

                    }
                    history.addHistory(inputString);
                    break;
                case "list":
                    List list = new List();
                    list.getList(currentDirectory);
                    history.addHistory(inputString);
                    break;
                case "here":
                    System.out.println(currentDirectory + '\n');
                    history.addHistory(inputString);
                    break;
                case "rdir":
                    CreateString createString = new CreateString();
                    String name = createString.create(commands);
                    Rdir.removeDirectory(currentDirectory, name);
                    break;
                case "ptime":
                    ptime = ptime / 1000;
                    System.out.print("Time spent on child processes: ");
                    System.out.printf("%.4f", ptime);
                    System.out.println(" seconds.");
                    break;
                default:
                    if(inputString.contains("|")){
                        boolean hasSplit = false;
                        ArrayList<String> commands1 = new ArrayList<>();
                        ArrayList<String> commands2 = new ArrayList<>();
                        int i = 0;
                        for(String string : commands){

                            if(string.equals("|")){
                                hasSplit = true;
                            }
                            else if(!hasSplit){
                                commands1.add(commands[i]);
                            }
                            else {
                                commands2.add(commands[i]);
                            }
                            i++;
                        }


                        try {
                            ProcessBuilder left = new ProcessBuilder(commands1);
                            ProcessBuilder right = new ProcessBuilder(commands2);

                            left.redirectInput(ProcessBuilder.Redirect.INHERIT);
                            //left.redirectOutput(ProcessBuilder.Redirect.INHERIT);
                            left.redirectOutput(right.redirectInput());
                            right.redirectOutput(ProcessBuilder.Redirect.INHERIT);

                            long startTime = System.currentTimeMillis();

                            Process l = left.start();
                            Process r = right.start();

                            l.getInputStream().transferTo(r.getOutputStream());

                            r.getOutputStream().close();

                            l.waitFor();
                            r.waitFor();

                            long endTime = System.currentTimeMillis();
                            ptime += endTime - startTime;
                        }
                        catch(IOException e){
                            System.out.println("Error: Unknown command used!");
                        }


                    }
                    else {
                        long startTime = System.currentTimeMillis();

                        ProcessBuilder pb = new ProcessBuilder(commands);
                        pb.redirectInput(ProcessBuilder.Redirect.INHERIT);
                        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
                        try {
                            Process p = pb.start();
                            p.waitFor();
                        }
                        catch (IOException e){
                            System.out.println("Error: Not a known command");
                        }
                        long endTime = System.currentTimeMillis();
                        ptime += endTime - startTime;
                    }
                    break;
            }




        }




    }

}

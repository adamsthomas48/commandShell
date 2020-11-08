import java.util.ArrayList;

class History {
    private ArrayList<String> cmdHistory = new ArrayList<String>();

    History(){}

    void addHistory(String cmd){
        cmdHistory.add(cmdHistory.size(),cmd);
        //System.out.println(cmdHistory.size());

    }

    void printHistory(){
        System.out.println("-- Command History --");
        int i = 1;
        for (String s : cmdHistory) {
            System.out.println(i + ". " + s);
            i++;
        }
    }

    void revisit(int index){
        String currCommand = cmdHistory.get(index - 1);
        String[] command = currCommand.split("\\s+");

    }

    public ArrayList<String> getCmdHistory() {
        return cmdHistory;
    }
}


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateString {
    CreateString(){};

    String create(String[] commands){
        String string = "";
        if(commands.length > 1){
            for(int i = 1; i < commands.length; i ++){
                if(i == commands.length - 1) {
                    string += commands[i];
                }
                else {
                    string += commands[i] + " ";
                }
            }
        }

        commands = splitCommand(string);

        return commands[0];
    }

    public static String[] splitCommand(String command) {
        java.util.List<String> matchList = new java.util.ArrayList<>();

        Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");
        Matcher regexMatcher = regex.matcher(command);
        while (regexMatcher.find()) {
            if (regexMatcher.group(1) != null) {
                // Add double-quoted string without the quotes
                matchList.add(regexMatcher.group(1));
            } else if (regexMatcher.group(2) != null) {
                // Add single-quoted string without the quotes
                matchList.add(regexMatcher.group(2));
            } else {
                // Add unquoted word
                matchList.add(regexMatcher.group());
            }
        }


        return matchList.toArray(new String[matchList.size()]);
    }
}

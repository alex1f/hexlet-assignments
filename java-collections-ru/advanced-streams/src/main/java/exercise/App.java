package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
public class App {

    public static String getForwardedVariables(String configuration){
        int currentIndex = -1;
        List<String> configurationBlocks = new ArrayList<>();
        while (true){
            currentIndex = configuration.indexOf("environment=", currentIndex+1);
            if (currentIndex < 0){
                break;
            }
            int startEnvConfig = currentIndex + 12;
            int endConfig = configuration.indexOf("\"", startEnvConfig + 1);
            if (endConfig > 0){
                StringBuilder block = new StringBuilder(configuration.substring(startEnvConfig, endConfig));
                while (block.charAt(block.length()-1) == ' ' || block.charAt(block.length()-1) == ','){
                    block.deleteCharAt(block.length()-1);
                }
                while (block.charAt(0) == '"'){
                    block.deleteCharAt(0);
                }
                configurationBlocks.add(block.toString());
            }
        }

        List<String> output = new ArrayList<>();
        for (String block : configurationBlocks){
            String[] elements = block.split(",");
            for (String element : elements){
                if (element.startsWith("X_FORWARDED_")){
                    output.add(element.substring(12));
                }
            }
        }

        return String.join(",", output);
    }
}
//END

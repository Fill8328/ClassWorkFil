package fill.jma;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static final String DELIMETER = "_";
    private static final char EXIT_COMMAND = '1';
    private static final char ADD_COMMAND = '2';
    private static final char GET_BY_KEY_COMMAND = '3';
    private static final char GET_ALL_COMMAND = '4';
    private static final String AVAILABLE_COMMANDS = """
            Available commands list
            exit: 1
            add key value pair: 2_key_value
            get value by key: 3_key
            get all key value set: 4
            """;

    public static void main(String[] args) throws IOException {
        System.out.println(AVAILABLE_COMMANDS);
        Map<String, String> map = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("Type next command please.");

            String line = null;
            try {
                line = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException("Cannpt read line from console");
            }
            if (!line.matches("(0)" +
                    "|(1_[a-zA-Z0-9]{1,10}_[a-zA-Z0-9]{1,10})" +
                    "|(2_[a-zA-Z0-9]{1,10})" +
                    "|(3)")) {
                System.out.println("Command is invalid!");
                System.out.println(AVAILABLE_COMMANDS);
                continue;
            }
            if (line.isBlank()) {
                System.out.println("Command must be not empty!");
                continue;
            }
            
            char command = line.charAt(0);
            switch (command) {
                case EXIT_COMMAND: {
                    System.out.println("Goodbye.");
                    return;
                }
                case ADD_COMMAND: {
                    String[] splittedLine = line.split(DELIMETER);
                    String key = splittedLine[1];
                    String value = splittedLine[2];
                    map.put(key, value);
                    break;
                }
                case GET_BY_KEY_COMMAND: {
                    String[] splittedLine = line.split(DELIMETER);
                    String key = splittedLine[1];
                    System.out.println(map.get(key));
                    break;
                }
                case GET_ALL_COMMAND: {
                    System.out.println(map);
                    break;
                }
                default: {
                    System.out.println("No such command!");
                }
            }
        }
    }
}

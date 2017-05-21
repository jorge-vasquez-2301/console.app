import com.example.Command;
import com.example.ConsoleClientApp;
import com.example.PetManagementSystemClient;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

/**
 * The Main class.
 * This class starts the console client application.
 */
public class Main {
    /* constants for properties. */
    private static final String PROPERTIES_FILE = "src/main/resources/console.properties";
    private static final String ADDRESS = "address";
    private static final String DEFAULT_ADDRESS = "localhost";
    private static final String PORT = "port";
    private static final String DEFAULT_PORT = "9000";

    public static void main(String[] args) throws IOException {
        if (args.length == 2 || args.length == 3) {
            PetManagementSystemClient petManagementSystemClient = getClient();
            ConsoleClientApp consoleClientApp = new ConsoleClientApp(petManagementSystemClient);
            consoleClientApp.processCsv(args[0]);
            String[] tmpCommand1 = args[1].split("=");
            Command command1 = new Command(tmpCommand1[0], tmpCommand1[1]);
            Command command2 = null;
            if (args.length == 3) {
                String[] tmpCommand2 = args[2].split("=");
                command2 = new Command(tmpCommand2[0], tmpCommand2[1]);
            }
            consoleClientApp.processCommands(command1, command2);
        } else {
            System.out.println("Null arguments");
        }
    }

    /**
     * Parses the properties file for creating a PetManagementSystemClient.
     * @return the PetManagementSystemClient
     * @throws IOException
     */
    private static PetManagementSystemClient getClient() throws IOException {
        try (Reader reader = new FileReader(PROPERTIES_FILE)) {
            Properties properties = new Properties();
            properties.load(reader);

            String address = properties.getProperty(ADDRESS, DEFAULT_ADDRESS);
            short port = Short.parseShort(properties.getProperty(PORT, DEFAULT_PORT));
            return new PetManagementSystemClient(address, port);
        }
    }
}

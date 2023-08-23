package util;

import java.io.FileWriter;
import java.io.IOException;

public class UserWriterAux {
    private int nif;
    private String password;
    private String type;

    public UserWriterAux(int nif, String password, String type) {
        this.nif = nif;
        this.password = password;
        this.type = type;
    }

    public void writeToFile() {
        String content = "NIF: " + nif + " Password: " + password + " Type: " + type;

        try (FileWriter writer = new FileWriter("users.txt", true)) {
            writer.write(content + "\n");
            System.out.println("User data written to users.txt");
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:oracle:thin:@//localhost:1521/orcl.docker.internal";
        String username = "user1";
        String password = "pass";
        Authenticate authenticate = new Authenticate(jdbcUrl, username, password);
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter password: ");
            String pass = scanner.nextLine();
            String status = authenticate.authenticate(id, pass);
            if (!Objects.equals(status, "level0")) {
                Privilege privilege = new Privilege(id);
                privilege.getOperations(status);
            }
        } catch (InputMismatchException e) {
            System.out.println("Exception caught: " + e);
        }
    }
}
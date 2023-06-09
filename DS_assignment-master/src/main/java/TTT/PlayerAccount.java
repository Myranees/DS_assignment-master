package TTT;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class PlayerAccount implements Serializable {
    private String username;
    private String password;
    private List<Player> leaderboard;
    private static List<PlayerAccount> accounts = new ArrayList<>();
    private static final Scanner in = new Scanner(System.in);

    public PlayerAccount() {
        leaderboard = new ArrayList<>();
    }

    public PlayerAccount(String name, String enteredPassword) {
        username = name;
        password = enteredPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void createAccount() {
        System.out.print("Username: ");
        username = in.next();

        System.out.print("Password: ");
        password = in.next();

        password = hashPassword(password); // Hash the password

        loadAccountSignup(username, password);
    }

    public void login() {
        System.out.print("Username: ");
        String username = in.next();

        System.out.print("Password: ");
        String enteredPassword = in.next();

        loadAccountLogin(username, enteredPassword); // Access account in the txt file
    }

    public void saveAccount(String username, String password) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("player_account.txt", true))) {
            writer.println(username);
            writer.println(password);
            System.out.println("Player account saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving the player account.");
        }
    }

    public void loadAccountSignup(String enteredUsername, String enteredPassword) {
        File file = new File("player_account.txt");

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            boolean usernameExists = false;
            while ((line = reader.readLine()) != null) {
                String name = line;
                if (name.equals(enteredUsername)) {
                    usernameExists = true;
                    break;
                }
            }
            if (!usernameExists) {
                leaderboard.add(new Player(enteredUsername, 0));
                System.out.println("Account created successfully.");
                saveAccount(enteredUsername, enteredPassword); // Save the account into txt file
                System.out.println("Please Log In: ");
                login();
            } else {
                System.out.println("The username has already been used by another account!");
                System.out.println("1. Log in if it's your account.");
                System.out.println("2. Sign up using another username");
                System.out.print("Enter your choice: ");
                int choice = in.nextInt();
                if (choice == 1) {
                    login();
                } else if (choice == 2) {
                    createAccount();
                } else {
                    System.out.println("Invalid choice. Please try again.");
                    createAccount();
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading the player account.");
        }
    }

    public boolean loadAccountLogin(String username, String enteredPassword) {
        File file = new File("player_account.txt");

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = reader.readLine()) != null) {
                String name = line;
                String password = reader.readLine();
                PlayerAccount account = new PlayerAccount(name, password);
                account.setUsername(name);
                accounts.add(account);
                if (account.getUsername().equals(username)) {
                    if (account.getUsername().equals(username)
                            && account.getPassword().equals(hashPassword(enteredPassword))) {
                        System.out.println("Login successful!");
                        return true;
                    } else if (account.getUsername().equals(username) && !password.equals(enteredPassword)) {
                        System.out.println("The username and/or password is invalid. Please try again.");
                        login();
                        return true;
                    }

                }
            }

            while (true) {
                try {
                    System.out.println("No username found. Do you wish to Sign up or Log in (0 : Sign up, 1 : Log in)");
                    int num = in.nextInt();
                    if (num == 0) {
                        createAccount();
                        return false;
                    } else if (num == 1) {
                        login();
                        return false;
                    }
                } catch (NumberFormatException | InputMismatchException e) {
                    System.out.println("Please enter either 1 or 0! ");
                    in.nextLine();
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading the player account.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input!");
        }
        return false;
    }

    public void updateLeaderboard(String name, int score) {
        for (Player player : leaderboard) {
            if (player.getUsername().equals(name)) {
                player.setScore(player.getScore() + score);
                return;
            }
        }
        leaderboard.add(new Player(name, score));
    }

    public void displayLeaderboard() {
        System.out.println("\nLeaderboard:\n");
        // Sort the leaderboard in descending order based on the scores
        leaderboard.sort(Comparator.comparingInt(Player::getScore).reversed());

        int rank = 1;
        System.out.println("+--------------------------------+");
        System.out.printf("| %-4s | %-10s | %-8s |%n", "Rank", "Username", "Total Wins");
        System.out.println("+--------------------------------+");

        for (Player player : leaderboard) {
            System.out.printf("| %-4d | %-10s | %-10d |%n", rank, player.getUsername(), player.getScore());
            rank++;
        }

        System.out.println("+--------------------------------+");
    }

    public void saveLeaderboard() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("playerLeaderboard.txt"), true)) {
            for (Player player : leaderboard) {
                writer.println(player.getUsername());
                writer.println(player.getScore());
            }
            System.out.println("Leaderboard saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving the leaderboard.");
        }
    }

    public void loadLeaderboard() {
        try (Scanner scanner = new Scanner(new FileReader("playerLeaderboard.txt"))) {
            while (scanner.hasNextLine()) {
                String name = scanner.nextLine();
                int score = Integer.parseInt(scanner.nextLine());
                leaderboard.add(new Player(name, score));
            }
            System.out.println("Leaderboard loaded successfully!");
        } catch (IOException e) {
            System.out.println("Error loading the leaderboard.");
        }
    }

    public String hashPassword(String password) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("RIN-21");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to get RIN-21", e);
        }
        byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodedhash);
    }

    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}

class Player implements Serializable {
    private String username;
    private int score;

    public Player(String username, int score) {
        this.username = username;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}



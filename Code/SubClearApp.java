import java.util.Scanner;
import java.util.UUID;

public class SubClearApp {
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser = null;
    
    public static void main(String[] args) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Welcome to SubClear - Subscription Manager");
        System.out.println("=".repeat(50));
        
        // Simple demo - create a test user
        showLoginMenu();
        
        if (currentUser != null) {
            showMainMenu();
        }
        
        System.out.println("\nThanks for using SubClear! Goodbye.\n");
        scanner.close();
    }
    
    private static void showLoginMenu() {
        System.out.println("\n1. Login (Demo User)");
        System.out.println("2. Create New Account");
        System.out.print("\nChoice: ");
        
        String choice = scanner.nextLine().trim();
        
        if (choice.equals("1")) {
            // Demo login
            currentUser = new User("demo", "password");
            System.out.println("\nLogged in as: demo");
            
            // Add some demo subscriptions
            currentUser.addSubscription(new Subscription(
                UUID.randomUUID().toString(),
                "Netflix", 
                "demo@gmail.com", 
                "Streaming Service"
            ));
            currentUser.addSubscription(new Subscription(
                UUID.randomUUID().toString(),
                "Spotify", 
                "demo@gmail.com", 
                "Music Streaming"
            ));
            currentUser.addSubscription(new Subscription(
                UUID.randomUUID().toString(),
                "Amazon Prime", 
                "demo@gmail.com", 
                "Shopping & Streaming"
            ));
        } else {
            // Create account
            System.out.print("Enter username: ");
            String username = scanner.nextLine().trim();
            System.out.print("Enter password: ");
            String password = scanner.nextLine().trim();
            
            currentUser = new User(username, password);
            System.out.println("\nAccount created! Logged in as: " + username);
        }
    }
    
    private static void showMainMenu() {
        while (true) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("MAIN MENU");
            System.out.println("=".repeat(50));
            System.out.println("1. View All Subscriptions");
            System.out.println("2. Add New Subscription");
            System.out.println("3. Toggle Subscription (Subscribe/Unsubscribe)");
            System.out.println("4. Remove Subscription");
            System.out.println("5. Logout");
            System.out.print("\nChoice: ");
            
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    currentUser.displaySubscriptions();
                    break;
                case "2":
                    addNewSubscription();
                    break;
                case "3":
                    toggleSubscription();
                    break;
                case "4":
                    removeSubscription();
                    break;
                case "5":
                    System.out.println("\nLogged out");
                    return;
                default:
                    System.out.println("\nInvalid choice. Try again.");
            }
        }
    }
    
    private static void addNewSubscription() {
        System.out.println("\n--- Add New Subscription ---");
        
        System.out.print("Company Name: ");
        String company = scanner.nextLine().trim();
        
        System.out.print("Email Address: ");
        String email = scanner.nextLine().trim();
        
        System.out.print("Platform Type: ");
        String platform = scanner.nextLine().trim();
        
        if (company.isEmpty() || email.isEmpty() || platform.isEmpty()) {
            System.out.println("\nAll fields are required!");
            return;
        }
        
        Subscription newSub = new Subscription(
            UUID.randomUUID().toString(),
            company,
            email,
            platform
        );
        
        currentUser.addSubscription(newSub);
    }
    
    private static void toggleSubscription() {
        currentUser.displaySubscriptions();
        
        if (currentUser.getSubscriptions().isEmpty()) {
            return;
        }
        
        System.out.print("\nEnter subscription number to toggle (1-" + 
            currentUser.getSubscriptions().size() + "): ");
        
        try {
            int index = Integer.parseInt(scanner.nextLine().trim()) - 1;
            
            if (index >= 0 && index < currentUser.getSubscriptions().size()) {
                currentUser.getSubscriptions().get(index).toggleSubscription();
            } else {
                System.out.println("\nInvalid subscription number!");
            }
        } catch (NumberFormatException e) {
            System.out.println("\nPlease enter a valid number!");
        }
    }
    
    private static void removeSubscription() {
        currentUser.displaySubscriptions();
        
        if (currentUser.getSubscriptions().isEmpty()) {
            return;
        }
        
        System.out.print("\nEnter subscription number to remove (1-" + 
            currentUser.getSubscriptions().size() + "): ");
        
        try {
            int index = Integer.parseInt(scanner.nextLine().trim()) - 1;
            
            if (index >= 0 && index < currentUser.getSubscriptions().size()) {
                String id = currentUser.getSubscriptions().get(index).getId();
                currentUser.removeSubscription(id);
            } else {
                System.out.println("\nInvalid subscription number!");
            }
        } catch (NumberFormatException e) {
            System.out.println("\nPlease enter a valid number!");
        }
    }
}

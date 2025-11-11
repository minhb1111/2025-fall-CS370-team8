import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private List<Subscription> subscriptions;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.subscriptions = new ArrayList<>();
    }
    
    public String getUsername() { return username; }
    
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
    
    public void addSubscription(Subscription subscription) {
        subscriptions.add(subscription);
        System.out.println("Added: " + subscription.getCompanyName());
    }
    
    public void removeSubscription(String subscriptionId) {
        subscriptions.removeIf(sub -> sub.getId().equals(subscriptionId));
        System.out.println("Subscription removed");
    }
    
    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }
    
    public void displaySubscriptions() {
        if (subscriptions.isEmpty()) {
            System.out.println("\n📭 No subscriptions yet. Add some to get started!");
            return;
        }
        
        System.out.println("\n" + "=".repeat(90));
        System.out.println("YOUR SUBSCRIPTIONS (" + subscriptions.size() + " total)");
        System.out.println("=".repeat(90));
        System.out.printf("%-20s | %-25s | %-20s | %s\n", 
            "COMPANY", "EMAIL", "PLATFORM", "STATUS");
        System.out.println("-".repeat(90));
        
        for (Subscription sub : subscriptions) {
            System.out.println(sub);
        }
        System.out.println("=".repeat(90));
    }
}

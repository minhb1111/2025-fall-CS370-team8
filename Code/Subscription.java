public class Subscription {
    private String id;
    private String companyName;
    private String email;
    private String platform;
    private boolean isActive;
    
    public Subscription(String id, String companyName, String email, String platform) {
        this.id = id;
        this.companyName = companyName;
        this.email = email;
        this.platform = platform;
        this.isActive = true; // Active by default
    }
    
    // Getters and Setters
    public String getId() { return id; }
    public String getCompanyName() { return companyName; }
    public String getEmail() { return email; }
    public String getPlatform() { return platform; }
    public boolean isActive() { return isActive; }
    
    public void setActive(boolean active) { 
        this.isActive = active; 
    }
    
    public void toggleSubscription() {
        this.isActive = !this.isActive;
        System.out.println(companyName + " is now " + 
            (isActive ? "SUBSCRIBED" : "UNSUBSCRIBED"));
    }
    
    @Override
    public String toString() {
        String status = isActive ? "SUBSCRIBED" : "UNSUBSCRIBED";
        return String.format("%-20s | %-25s | %-20s | %s", 
            companyName, email, platform, status);
    }
}

# Usage
  Login:
    Option 1: demo user (preloaded subscriptions)
    Option 2: Create your own account
    
  Main menu:
    Option 1: View all subscriptions - show a list of available subscription services linked to your account
    Option 2: Add new subscription - manually add a service to your account
    Option 3: Toggle subscription - choose from the list which service you would like to unsubscribe or re-subsribe
    Option 4: Remove subscription - this will remove the service completely. Won't be able to re-subscribe 
    Option 5: Log out - exit the program. ATM it doesn't have a server so all data would be reset each run

# Structure
Subscription.java: subscription class 
  - Store company, email, platform, status
  - Boolean "isActive" for subscribe/unsubscribe
  - Toggle status with "toggleSubscription()"
  - 
User.java: user class
  - Simple password authentication
  - Manages a list of subscriptions
  - Can add, remove, and display subscriptions

SubClearApp.java: main application
  - CLI using Scanner
  - Handles user input and calls appropriate methods

README.md: this file

# TO-DO
  - Add more subscription details (cost, renewal date)
  - File persistence (save to text file/possibly local run server using UDP?)
  - Add search/filter functionality?
  - Upgrade to GUI version (Swing or JavaFX?)
  - Possibly a web version? (insufficient knowledge)

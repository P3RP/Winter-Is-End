package main.java.com.userManager;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class User {
    private String name;
    private String id;
    private String encryptedPassword;

    public User(String id, String pw, String name) {
        this.id = id;
        this.name = name;
        
        this.setPassword(pw);
    }

    // Getters & Setters
    public String getName() {
        return this.name;
    }
    public String getId() {
        return this.id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setId(String id) {
        this.id = id;
    }

    // Public Methods
    public boolean equals(Object o) {
        if (!(o instanceof User)) return false;

        User u = (User)o;
        return this.id.equals(u.getId());
    }
    public boolean comparePassword(String pw) {
        return BCrypt.checkpw(this.encryptedPassword, pw);
    }

    // Private Methods
    private void setPassword(String pw) {
        this.encryptedPassword = BCrypt.hashpw(pw, BCrypt.gensalt());
    }

}

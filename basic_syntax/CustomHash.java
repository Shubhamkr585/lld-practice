import java.util.*;

class User {
    String name;
    int id;

    public User(int id, String name){
        this.id = id;
        this.name = name;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public int hashCode() {
        int hash = Objects.hash(id, name);
        System.out.println("hashCode() called for " + name + " -> " + hash);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("equals() called");
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User u = (User) o;
        return id == u.id && Objects.equals(name, u.name);
    }
}
public class CustomHash {
    public static void main(String[] args) {

        HashMap<User, String> map = new HashMap<>();

        User u1 = new User(1, "A");
        User u2 = new User(2, "B");

        map.put(u1, "Admin");
        map.put(u2, "Manager");

        System.out.println("---- Accessing ----");

        map.get(u1);
        System.out.println(u1);
        System.out.println("Stored hashCode of key u1: " + u1.hashCode());
    }
}

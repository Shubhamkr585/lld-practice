public class Game{
    public static void main(String[] args){
        GameCharacter player = new GameCharacter();

// 1. Initially use a sword
        player.setStrategy(new SwordFight());
        player.performAttack(); // Output: Swinging Sword!

// 2. SUDDEN CHANGE: Player picks up a magic item
        player.setStrategy(new MagicFight());
        player.performAttack(); // Output: Casting Spell!
    }
}

interface FightStrategy {
    void useWeapon();
}

class SwordFight implements FightStrategy {
    public void useWeapon() { System.out.println("Swinging Sword!"); }
}

class MagicFight implements FightStrategy {
    public void useWeapon() { System.out.println("Casting Spell!"); }
}

class GameCharacter {
    private FightStrategy strategy; // This is the "Plug"

    public void setStrategy(FightStrategy strategy) {
        this.strategy = strategy; // This is the "Switch"
    }

    public void performAttack() {
        strategy.useWeapon();
    }
}

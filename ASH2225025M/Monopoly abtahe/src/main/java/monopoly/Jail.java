package monopoly;

import java.io.Serializable;

public class Jail implements Border, Serializable {
    public static final int IN_JAIL = 10;
    public static final int GOTO_JAIL = 30;
    private final int position;
    private final String name;
    private final JailType type;

    public Jail(int position, String name, JailType type) {
        this.position = position;
        this.name = name;
        this.type = type;
    }

    public JailType getType() {
        return this.type;
    }

    @Override
    public int position() {
        return this.position;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public boolean isOwnable() {
        return false;
    }

    @Override
    public boolean isOwned() {
        return false;
    }
    @Override
    public int cost() {
        return 0;
    }

    @Override
    public void purchase(Player player) {
    }
    @Override
    public int rent(int value) {
        return 0;
    }
    @Override
    public Player owner() {
        return null;
    }
    public enum JailType {
        JUST_VISITING, IN_JAIL, GOTO_JAIL
    }
}

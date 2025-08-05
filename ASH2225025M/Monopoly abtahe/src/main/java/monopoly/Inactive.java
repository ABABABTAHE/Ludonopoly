package monopoly;

import java.io.Serializable;

public class Inactive implements Border, Serializable {
    private final int position;
    private final String name;

    public Inactive(int position, String name) {
        this.position = position;
        this.name = name;
    }

    @Override
    public int position() {
        return this.position;
    }

    @Override
    public String name() {
        return this.name;
    }

    public boolean isOwnable() {
        return false;
    }
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
}

package monopoly;

import java.io.Serializable;

public class Railroad implements Border, Serializable {
    public static final int RAILROAD_A_POSITION = 5;
    public static final int RAILROAD_B_POSITION = 15;
    public static final int RAILROAD_C_POSITION = 25;
    public static final int RAILROAD_D_POSITION = 35;
    private final int COST = 200;
    private final int position;
    private final String name;
    private final Railroad[] others;
    private int numOwned;
    private Player owner;
    private boolean owned;
    public Railroad(int position, String name) {
        this.position = position;
        this.name = name;
        this.others = new Railroad[3];
        this.owned = false;
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
        return true;
    }

    @Override
    public boolean isOwned() {
        return owned;
    }

    @Override
    public int cost() {
        return this.COST;
    }

    @Override
    public void purchase(Player player) {
        owned = true;
        owner = player;
        updateOwners();
    }
    @Override
    public int rent(int value) {
        updateOwners();
        return switch (numOwned) {
            case 1 -> 25;
            case 2 -> 50;
            case 3 -> 100;
            case 4 -> 200;
            default -> 0;
        };
    }
    @Override
    public Player owner() {
        return owner;
    }
    public void setGroup(Railroad a, Railroad b, Railroad c) {
        this.others[0] = a;
        this.others[1] = b;
        this.others[2] = c;
    }
    private void updateOwners() {
        numOwned = 1;
        for (Railroad r : others) {
            if (r.isOwned() && r.owner().equals(owner)) {
                numOwned++;
            }
        }
    }
}

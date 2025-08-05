package monopoly;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

public class Property implements Border, Serializable {
    private final int rent;
    private final int oneHouse;
    private final int twoHouse;
    private final int threeHouse;
    private final int fourHouse;
    private final int hotel;

    private final int position;
    private final String name;
    private Player owner;
    private boolean owned;
    private final Property[] others; 
    private final int propertyCost;  
    private final int houseCost;     
    private int buildings;           
    private boolean monopoly;        
    public Property(int position, String name, int rent, int oneHouse, int twoHouse, int threeHouse, int fourHouse,
                    int hotel, int propertyCost, int houseCost) {
        this.position = position;
        this.name = name;
        this.rent = rent;
        this.oneHouse = oneHouse;
        this.twoHouse = twoHouse;
        this.threeHouse = threeHouse;
        this.fourHouse = fourHouse;
        this.hotel = hotel;
        this.propertyCost = propertyCost;
        this.houseCost = houseCost;
        this.others = new Property[2];
        this.buildings = 0;
        this.monopoly = false;
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
        return this.owned;
    }
    @Override
    public int cost() {
        return this.propertyCost;
    }
    @Override
    public void purchase(Player player) {
        owned = true;
        owner = player;
        updateMonopoly(player);
    }

    @Override
    public int rent(int value) {
        switch (buildings) {
            case 0:
                if (this.monopoly) {
                    return 2 * this.rent;
                } else {
                    return this.rent;
                }
            case 1: return oneHouse;
            case 2: return twoHouse;
            case 3: return threeHouse;
            case 4: return fourHouse;
            case 5: return hotel;
            default: return 0;
        }
    }

    @Override
    public Player owner() {
        return this.owner;
    }

    public boolean isMonopoly() {
        return this.monopoly;
    }

    public boolean setMonopoly() {
        return this.monopoly = true;
    }

    public boolean breakMonopoly() {
        return this.monopoly = false;
    }

    public void updateMonopoly(Player player) {
        boolean setA = false;
        boolean setB = others[1] == null;

        Queue<Property> properties = new LinkedList<>();
        for (Border square : player.properties()) {
            if (square instanceof Property) {
                properties.add((Property) square);
            }
        }
        for (Property property : properties) {
            if (others[0] != null && property.name().equals(others[0].name())) {
                setA = true;
            }
            if (others[1] != null && property.name().equals(others[1].name())) {
                setB = true;
            }
        }
        if (setA && setB) {
            setMonopoly();
            if (others[0] != null) {
                others[0].setMonopoly();
            }
            if (others[1] != null) {
                others[1].setMonopoly();
            }
        } else {
            breakMonopoly();
            if (others[0] != null) {
                others[0].breakMonopoly();
            }
            if (others[1] != null) {
                others[1].breakMonopoly();
            }
        }
    }
    public int getHouseCost() {
        return this.houseCost;
    }
    public void setGroup(Property propertyA) {
        this.setGroup(propertyA, null);
    }
    public void setGroup(Property propertyA, Property propertyB) {
        this.others[0] = propertyA;
        this.others[1] = propertyB;
    }

    public int getBuildings(){
        return buildings;
    }

    public void build() {
        if (buildings == 5) {
            throw new IllegalArgumentException("Cannot build past a hotel");
        } else {
            buildings++;
        }
    }
    public boolean evenRule() {
        if (!monopoly) {
            return false;
        }

        int diff_A = this.others[0].getBuildings() - getBuildings();

        boolean confirmA = diff_A == 0 || diff_A == 1;
        if (this.others[1] == null)
            return confirmA;

        int diff_B = this.others[1].getBuildings() - getBuildings();
        boolean confirmB = diff_B == 0 || diff_B == 1;

        return  confirmA && confirmB;
    }
}


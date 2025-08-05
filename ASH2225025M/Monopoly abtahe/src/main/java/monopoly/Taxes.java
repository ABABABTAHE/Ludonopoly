package monopoly;

import java.io.Serializable;

public class Taxes implements Border, Serializable {
    public static final int INCOME_TAX_POSITION = 4;
    public static final int SUPER_TAX_POSITION = 38;
    public static final int FIX_INCOME_TAX = 200;
    public static final int FIX_SUPER_TAX = 100;

    private final int position;
    private final String name;
    private final int fixTax;
    private final double varTax;


    public Taxes(int position, String name) {
        this.position = position;
        this.name = name;
        if (name.equals(JsonFile.ToJSON(INCOME_TAX_POSITION, String.valueOf(Versions.BD)))) {
            fixTax = FIX_INCOME_TAX;        
            varTax = 0.1;                 
        } else {
            fixTax = FIX_SUPER_TAX;         
            varTax = 0;
        }
    }
    public int getTax() {
        return fixTax;
    }
    public int getTax(int value) {
        if (varTax == 0) {
            return fixTax;
        } else {
            return (int) (value * varTax);
        }
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
}

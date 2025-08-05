package monopoly;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

import static monopoly.GameBoardCreate.BOARD_SIZE;
public class Playeron implements Player, Serializable {
    private final Collection<Border> properties;
    private final String playerName;
    private int money;
    private int position;
    private int jailTurns;
    private boolean inJail;
    public Playeron(String name) {
        this.properties = new LinkedList<>();
        this.playerName = "CPU " + name;
        this.money = 1500;
        this.position = 0;
        this.jailTurns = 0;
        this.inJail = false;
    }
    @Override
    public void move(int numTiles) {
        this.position += numTiles;
        if (position >= BOARD_SIZE && !inJail) {
            position -= BOARD_SIZE;
            exchangeMoney(PASS_GO_COLLECT);
        }
    }
    @Override
    public void moveTo(int newPosition) {
        if (newPosition < position) {
            exchangeMoney(PASS_GO_COLLECT);
        }
        position = newPosition;
    }
    @Override
    public int getPosition() {
        return this.position;
    }
    @Override
    public void setInJail(boolean bool) {
        inJail = bool;
    }
    @Override
    public Collection<Border> properties() {
        return  this.properties;
    }
    @Override
    public String name() {
        return this.playerName;
    }
    @Override
    public int getMoney() {
        return this.money;
    }
    @Override
    public void exchangeMoney(int money) {
        this.money = money;
    }
    @Override
    public void addProperty(Border square) {
        if (!square.isOwnable()) {
            throw new IllegalArgumentException("This property can not be purchased");
        } else {
            this.properties.add(square);
            square.purchase(this);
        }
    }
    @Override
    public void removeProperty(Border square) {
        this.properties.remove(square);
        this.money += square.cost();
    }
    @Override
    public boolean inputBool(GameState state) {
        return switch (state.decisionState) {
            case NONE -> handleNone();
            case BUY_PROPERTY -> handleBuyProperty();
            case BUY_HOUSE -> handleBuyHouse();
            case TURN_ACTION -> handleTurnAction();
            case TAX -> handleIncomeTax();
            case IN_JAIL -> false;
        };
    }
    @Override
    public int inputInt(GameState state) {
        throw new IllegalArgumentException("No implementation use case for CPU to input integer");
    }
    @Override
    public int inputDecision(GameState state, String[] choices) {
        return 0;
    }

    @Override
    public void addJailTurn() {
        jailTurns++;
    }
    @Override
    public void setJailTurns(int turns) {
        this.jailTurns = turns;
    }
    @Override
    public int getJailTurns() {
        return this.jailTurns;
    }
    @Override
    public Property getProperty(String propName) {
        for(Border prop: properties){
            if(propName.equals(prop.name())) {
                return (Property) prop;
            }
        }
        return null;
    }
    public boolean handleNone() {
        return false;
    }
    public boolean handleBuyProperty() {
        return true;
    }
    public boolean handleBuyHouse() {
        return true;
    }
    public boolean handleTurnAction() {
        return true;
    }
    public boolean handleIncomeTax() {
        return true;
    }
}

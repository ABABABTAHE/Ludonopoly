package monopoly;

import java.util.Collection;

public interface Player {
    int PASS_GO_COLLECT = 200;
    void move(int numTiles);
    void moveTo(int newPosition);
    int getPosition();
    void setInJail(boolean bool);
    Collection<Border> properties();
    String name();
    int getMoney();
    void exchangeMoney(int money);
    void addProperty(Border square);
    void removeProperty(Border square);
    boolean inputBool(GameState state);
    int inputInt(GameState state);
    int inputDecision(GameState state, String[] choices);
    void addJailTurn();
    void setJailTurns(int turns);
    int getJailTurns();
    Property getProperty(String propName);
}

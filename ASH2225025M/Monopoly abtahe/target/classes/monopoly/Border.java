package monopoly;
public interface Border {

    int position();

    String name();

    boolean isOwnable();

    boolean isOwned();
    int cost();
    void purchase(Player player);

    int rent(int value);
    Player owner();
    String toString();
}

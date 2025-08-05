package monopoly;
public interface DiceAll {
    int getNumDice();
    int getNumSides();
    Roll rollDice();
    class Roll {
        public int dieValue1;
        public int dieValue2;
        public int value;
        public boolean isDouble;
    }
}

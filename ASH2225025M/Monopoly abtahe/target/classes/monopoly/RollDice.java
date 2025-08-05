package monopoly;

import java.io.Serializable;
import java.util.Random;

public class RollDice implements DiceAll, Serializable {
    private final int NUM_DICE;     
    private final int NUM_SIDES;    
    public RollDice() {
        this.NUM_DICE = 2;
        this.NUM_SIDES = 6;
    }

    @Override
    public int getNumDice() {
        return this.NUM_DICE;
    }
    @Override
    public int getNumSides() {
        return this.NUM_SIDES;
    }
    @Override
    public Roll rollDice() {
        Roll roll = new Roll();
        int die1 = new Random().nextInt(this.NUM_SIDES) + 1;
        int die2 = new Random().nextInt(this.NUM_SIDES) + 1;
        roll.dieValue1 = die1;
        roll.dieValue2 = die2;
        roll.value = die1 + die2;
        roll.isDouble = die1 == die2;
        return roll;
    }
}

package monopoly;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Collection;

public class PlayerGUI extends JPanel implements Serializable {
    private final Player player;
    private final int playerNumber;
    private static int totalPlayers = 0;
    private int currentSquareNumber = 0;

    private final int[] x_axisLoc = {550, 495, 445, 395, 345, 295, 245, 195, 145, 95, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 95, 145, 195, 245, 295, 345, 395, 445, 495, 550,
            550, 550, 550, 550, 550, 550, 550, 550, 550};

    private final int[] y_axisloc = {620, 620, 620, 620, 620, 620, 620, 620, 620, 620, 620,
            525, 475, 425, 375, 325, 275, 225, 175, 125,
            20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20,
            125, 175, 225, 275, 325, 375, 425, 475, 525};

    
    public PlayerGUI(Color color, String playerName) {
        player = new AllPlayer(playerName);
        totalPlayers++;
        this.playerNumber = totalPlayers;
        this.setBackground(color);
        JLabel labelPlayerNumber = new JLabel(String.valueOf(playerNumber));
        labelPlayerNumber.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        labelPlayerNumber.setForeground(Color.WHITE);
        this.add(labelPlayerNumber);
        this.setBounds((15+this.playerNumber*5+x_axisLoc[0]), y_axisloc[0], 18, 28);
    }

    public void resetTotalPlayers() {
        totalPlayers = 0;
    }
    public void move(int diceValue) {
        GameBoardCreate gameBoard = new GameBoardCreate();
        int size = gameBoard.size()-1;

        if (currentSquareNumber + diceValue >= size) {
            this.exchangeMoney(200);
        }
        int targetSquare = (currentSquareNumber + diceValue) % size;
        currentSquareNumber = targetSquare;

        this.setLocation((15+this.playerNumber*5+x_axisLoc[targetSquare]), y_axisloc[targetSquare]);
    }

    public void moveTo(int position) {
        currentSquareNumber = position;

        this.setLocation((15+this.playerNumber*5+x_axisLoc[position]), y_axisloc[position]);
    }

    public Collection<Border> getProperties() {
        return this.player.properties();
    }

    public int getPlayerMoney() {
        return this.player.getMoney();
    }
    public Player getPlayer() { return this.player; }
    public void exchangeMoney(int money) {
        this.player.exchangeMoney(money);
    }
    public int getCurrentSquareNumber() {
        return this.currentSquareNumber;
    }
}

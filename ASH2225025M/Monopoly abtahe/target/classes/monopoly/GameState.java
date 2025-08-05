package monopoly;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class GameState implements Serializable {//encapsulate
    public Monopoly.DecisionState decisionState;
    public LinkedList<Player> players;
    public ArrayList<PlayerGUI> playersGUI;
    public GameBoardConvertintoGUI gameBoardGUI;
    public GameBoardCreate gameBoard;
    public Player currentPlayer;
    public int currentPlayerOrder;
    public int currentSquareNumber;
}
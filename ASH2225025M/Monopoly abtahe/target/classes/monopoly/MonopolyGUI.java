
 package monopoly;

 import javax.swing.*;
 import javax.swing.border.EmptyBorder;
 import javax.swing.border.LineBorder;
 import java.awt.*;
 import java.awt.event.ActionEvent;
 import java.util.ArrayList;
 import java.util.Collection;
 import java.util.LinkedList;
 
 import static monopoly.GameBoardCreate.BOARD_SIZE;

 public class MonopolyGUI extends JPanel {
     private Monopoly monopoly;
     private ArrayList<PlayerGUI> playersGUI;                            
     private LinkedList<Player> playersList;                           
     private boolean isDouble;
     private int currentPlayerOrder;
     private int currentSquareNumber;
     private int doubles = 0;
     private GameBoardConvertintoGUI gameBoardGUI;
     private DiceCOnverttoGUI die1;
     private DiceCOnverttoGUI die2;
     private JPanel playerAssetsPanel;
     private JLayeredPane rightLayeredPane;
     private JLayeredPane leftLayeredPane;
     private JTextArea panelPlayerTextArea;
     private final CardLayout cardLayout = new CardLayout();
     private static JTextArea infoConsole;
     private JButton buttonRollDice;
     private JButton buttonNextTurn;
     private JButton buttonPayRent;
     private JButton buttonBuy;
     private JButton buttonBuyHouse;
     private JButton buttonRunCPU;
     private JButton buttonPayBail;
 
     public final Color[] playerTokenColors = {
         Color.RED,
         Color.BLUE,
         Color.GREEN,
         Color.ORANGE,
         Color.YELLOW,
         Color.MAGENTA,
         Color.GRAY,
         Color.PINK
     };
 
     public MonopolyGUI() {
         this.monopoly = new Monopoly();
         this.playersGUI = monopoly.getPlayerGUI();
         this.playersList = monopoly.getPlayers();
         this.currentPlayerOrder = monopoly.getCurrentPlayerOrder();
         this.currentSquareNumber = monopoly.getCurrentSquareNumber();
         this.gameBoardGUI = monopoly.getGameBoard1();
         this.isDouble = monopoly.isBankrupt();
         this.monopoly.play();                            
         this.leftLayeredPane = new JLayeredPane();
     }
 
     public void setupMonopolyBoard() {
         setupBoard();
         setupDice();
         initPlayerToken();
         setupPlayerStatusWindow();
         setupConsoleLog();
         setupMonopolyButtons();
     }

     public void setPlayers(LinkedList<Player> players) {
         playersList.addAll(players);
     }

     public GameBoardConvertintoGUI getGameBoard1() {

         return this.gameBoardGUI;
     }
 
     public void saveGame() {
         monopoly.setCurrentPlayerOrder(currentPlayerOrder);
         monopoly.setCurrentSquareNumber(currentSquareNumber);
         monopoly.setGameBoard1(gameBoardGUI);
         monopoly.exportGame(monopoly);
         JOptionPane.showMessageDialog(null, "Game has been saved");
     }

     public MonopolyGUI setGame(Monopoly newMonopoly) {
         this.newGame();
         this.monopoly = newMonopoly;
         this.gameBoardGUI = monopoly.getGameBoard1();
         this.playersGUI = monopoly.getPlayerGUI();
         this.playersList = monopoly.getPlayers();
         this.currentPlayerOrder = monopoly.getCurrentPlayerOrder();
         this.currentSquareNumber = monopoly.getCurrentSquareNumber();
 
         setupBoard();
         setupDice();
         setPlayerTokens();
         setupPlayerStatusWindow();
         setupConsoleLog();
         setupMonopolyButtons();
 
         CardLayout cardLayout = (CardLayout) playerAssetsPanel.getLayout();
         cardLayout.show(playerAssetsPanel, String.valueOf(currentPlayerOrder));
 
         JOptionPane.showMessageDialog(null, "Game has been loaded");
 
         return this;
     }

     private void setPlayerTokens() {
         PlayerGUI playerGUI;
         for (PlayerGUI gui : playersGUI) {
             playerGUI = gui;
             playerGUI.moveTo(gui.getCurrentSquareNumber());
             leftLayeredPane.add(playerGUI, Integer.valueOf(1));
         }
     }

     public void newGame() {
         this.monopoly = new Monopoly();
         this.playersGUI = new ArrayList<>();
         this.playersList = new LinkedList<>();
         this.gameBoardGUI = new GameBoardConvertintoGUI(5,5,670,670);
         this.currentPlayerOrder = 0;
         this.currentSquareNumber = 0;
         isDouble = false;
         doubles = 0;
     }

     public void setupBoard() {
         this.setBorder(new EmptyBorder(5, 5, 5, 5));
         this.setLayout(null);
         this.setSize(new Dimension(1080,740));
         this.setBackground(Color.white);
         rightLayeredPane = new JLayeredPane();
         rightLayeredPane.setBackground(Color.LIGHT_GRAY);
         rightLayeredPane.setBorder(new LineBorder(new Color(0, 0, 0)));
         rightLayeredPane.setBounds(680, 5, 430, 670);
         rightLayeredPane.setLayout(null);
         this.add(rightLayeredPane);
         leftLayeredPane.setBorder(new LineBorder(new Color(0, 0, 0)));
         leftLayeredPane.setBounds(5, 5, 670, 670);
         this.add(leftLayeredPane);
         gameBoardGUI.setBackground(new Color(50, 255, 155));
         leftLayeredPane.add(gameBoardGUI, Integer.valueOf(0));
     }
 
     public void setGameBoard1(GameBoardConvertintoGUI gameBoardGUI) {
         this.gameBoardGUI = gameBoardGUI;
     }
     public void setupDice() {
         die1 = new DiceCOnverttoGUI(350, 450, 40, 40);
         leftLayeredPane.add(die1, Integer.valueOf(1));
 
         die2 = new DiceCOnverttoGUI(400, 450, 40, 40);
         leftLayeredPane.add(die2, Integer.valueOf(1));
     }
     public void setupMonopolyButtons() {
         buttonRollDice = new JButton("Roll Dice");
         buttonRollDice.addActionListener(this::rollDiceAction);
         buttonRollDice.setBounds(80, 420, 250, 40);
         rightLayeredPane.add(buttonRollDice);
         buttonBuy = new JButton("Buy Property");
         buttonBuy.addActionListener(this::buyAction);
         buttonBuy.setBounds(80, 470, 115, 40);
         buttonBuy.setEnabled(false);
         rightLayeredPane.add(buttonBuy);
         buttonPayRent = new JButton("Pay Rent");
         buttonPayRent.addActionListener(this::payRentAction);
         buttonPayRent.setBounds(215, 470, 115, 40);
         buttonPayRent.setEnabled(false);
         rightLayeredPane.add(buttonPayRent);
         buttonBuyHouse = new JButton("Buy House");
         buttonBuyHouse.addActionListener(this::buyHouseAction);
         buttonBuyHouse.setBounds(80, 520, 115, 40);
         buttonBuyHouse.setEnabled(false);
         rightLayeredPane.add(buttonBuyHouse);
         buttonRunCPU = new JButton("CPU's Turn");
         buttonRunCPU.addActionListener(this::runCPUAction);
         buttonRunCPU.setBounds(80, 570, 115, 40);
         buttonRunCPU.setEnabled(false);
         rightLayeredPane.add(buttonRunCPU);
         buttonNextTurn = new JButton("Next Turn");
         buttonNextTurn.addActionListener(this::nextTurnAction);
         buttonNextTurn.setBounds(215, 570, 115, 40);
         buttonNextTurn.setEnabled(false);
         rightLayeredPane.add(buttonNextTurn);
         buttonPayBail = new JButton("Pay Bail");
         buttonPayBail.addActionListener(this::payBailAction);
         buttonPayBail.setBounds(215, 520, 115, 40);
         buttonPayBail.setEnabled(false);
         rightLayeredPane.add(buttonPayBail);
     }
     public void initPlayerToken() {
         PlayerGUI playerGUI = null;
         for (int i = 0; i < playersList.size(); i++) {
 
             playerGUI = new PlayerGUI(playerTokenColors[i], playersList.get(i).name());
             playersGUI.add(playerGUI);
             leftLayeredPane.add(playerGUI, Integer.valueOf(1));
         }
         assert playerGUI != null;
         playerGUI.resetTotalPlayers();
     }
     public void setupConsoleLog() {
         JPanel consolePanel = new JPanel();
         consolePanel.setBounds(80, 300, 250, 100);
         consolePanel.setBackground(Color.BLACK);
         consolePanel.setLayout(null);
         rightLayeredPane.add(consolePanel, String.valueOf(1));
 
         infoConsole = new JTextArea();
         infoConsole.setColumns(20);
         infoConsole.setRows(5);
         infoConsole.setBounds(5, 5, 240, 90);
         infoConsole.setLineWrap(true);
         infoConsole.setEditable(false);
         infoConsole.setText("Starts the game! \nClick Roll Dice!");
         consolePanel.add(infoConsole);
     }
     private JPanel playerStatusPanel(int playerNumber, Color color) {
         JPanel panelPlayer = new JPanel();
         panelPlayer.setBackground(color);
         panelPlayer.setLayout(null);
         JLabel panelPlayerTitle = new JLabel("Player " + playersList.get(playerNumber-1).name() + " Status");
         panelPlayerTitle.setForeground(Color.WHITE);
         panelPlayerTitle.setHorizontalAlignment(SwingConstants.CENTER);
         panelPlayerTitle.setBounds(0, 5, 240, 15);
         panelPlayer.add(panelPlayerTitle);
         return panelPlayer;
     }
     public void setupPlayerStatusWindow() {
         playerAssetsPanel = new JPanel();
         playerAssetsPanel.setBounds(80, 40, 250, 250);
         playerAssetsPanel.setLayout(cardLayout);
 
         for (int i = 0; i < playersList.size(); i++) {
             JPanel playerStatusPanel = playerStatusPanel(i+1, playerTokenColors[i]);
             playerAssetsPanel.add(playerStatusPanel, String.valueOf(i));
         }
 
         panelPlayerTextArea = new JTextArea();
         panelPlayerTextArea.setBounds(90, 70, 230, 210);
         panelPlayerTextArea.setEditable(false);
 
         rightLayeredPane.add(playerAssetsPanel, String.valueOf(1));
         rightLayeredPane.add(panelPlayerTextArea, String.valueOf(2));
 
         updatePlayerStatusTextArea();
     }

     private void updatePlayerStatusTextArea() {
         StringBuilder output = new StringBuilder();
         PlayerGUI currentPlayer =  playersGUI.get(currentPlayerOrder);
         int playerMoney = currentPlayer.getPlayerMoney();
         Collection<Border> properties = currentPlayer.getProperties();
         output.append("Current Balance: $").append(playerMoney).append("\n");
         output.append("Property titles owned:\n");
         for (Border sq : properties) {
             output.append("> ").append(sq.name()).append("\n");
         }
         panelPlayerTextArea.setText(output.toString());
     }
     private void isRollDouble(int currentPlayerOrder) {
         int nextPlayerIndex = (currentPlayerOrder + 1) % playersList.size();
         if (isDouble && doubles < 3) { // a player can't have more than 3 rolls
             infoConsole.append("\nDoubles! Click Roll Dice again, player " + playersList.get(currentPlayerOrder).name());
             buttonNextTurn.setEnabled(false);
             doubles++;
         } else {
             infoConsole.append("\nClick Next Turn to allow player " + playersList.get(nextPlayerIndex).name() + " to Roll Dice");
             buttonRollDice.setEnabled(false);
             buttonNextTurn.setEnabled(true);
             doubles = 0;
         }
     }

     private void rollDiceAction(ActionEvent actionEvent) {
         rollDiceLogic();
     }
     private void nextTurnAction(ActionEvent actionEvent) {
         infoConsole.setText("Next Turn!\n");
         if (isDouble) {
             isDouble = false;
         }
         currentPlayerOrder = (currentPlayerOrder + 1) % playersList.size();
         int currentPlayerIndex = (currentPlayerOrder % playersList.size()) + 1;
         currentPlayerOrder %= playersList.size();
         cardLayout.show(playerAssetsPanel, String.valueOf(currentPlayerOrder));
         infoConsole.append("It's now player "+ playersList.get(currentPlayerIndex - 1).name() +"'s turn!\n");
 
         handleCPUTurn();
         updatePlayerStatusTextArea();
     }

     private void handleCPUTurn() {
         PlayerGUI currentPlayer = this.playersGUI.get(currentPlayerOrder);
         if (playersList.get(currentPlayerOrder) instanceof Playeron) {
             buttonRunCPU.setEnabled(true);
             buttonRollDice.setEnabled(false);
         } else {
             buttonRunCPU.setEnabled(false);
             buttonRollDice.setEnabled(true);
         }
         buttonNextTurn.setEnabled(false);
         buttonPayRent.setEnabled(false);
         buttonBuy.setEnabled(false);
         buttonBuyHouse.setEnabled(false);
 
         if (currentPlayer.getPlayer().getJailTurns() > 0) {
             buttonPayBail.setEnabled(true);
         }
         buttonPayBail.setEnabled(false);
     }
     private void buyAction(ActionEvent actionEvent) {
         PlayerGUI currentPlayer = this.playersGUI.get(currentPlayerOrder);
         Border currentSquare = this.gameBoardGUI.getSquare(currentSquareNumber);
         int roll = die1.getFaceValue() + die2.getFaceValue();
         if (currentSquare.isOwnable() && !currentSquare.isOwned() && currentPlayer.getPlayerMoney() >= currentSquare.cost()) {
             infoConsole.setText("You bought property:\n" + currentSquare.name() +
                     "\nPurchase cost: " + currentSquare.cost());
         } else {
             infoConsole.setText("You don't have enough money to buy: \n" + currentSquare.name());
         }
         monopoly.handleSquare(currentPlayer.getPlayer(), currentSquare, roll);
         buttonBuy.setEnabled(false);
         updatePlayerStatusTextArea();
     }

     private void payRentAction(ActionEvent actionEvent) {
         handlePayRent();
         handlePlayerRollDoubles();
         updatePlayerStatusTextArea();
     }

     private void handlePayRent() {
         PlayerGUI currentPlayer = this.playersGUI.get(currentPlayerOrder);
         Border currentSquare = this.gameBoardGUI.getSquare(currentSquareNumber);
         int roll = die1.getFaceValue() + die2.getFaceValue();
         monopoly.handleSquare(currentPlayer.getPlayer(), currentSquare, roll);
         if (currentSquare.isOwnable() && currentSquare.isOwned()) {
             infoConsole.setText("You paid rent on:\n" + currentSquare.name() +
                     "\nRent cost: " + currentSquare.rent(roll));
             if (currentSquare instanceof Railroad) {
                 infoConsole.setText("You paid rent on:\n" + currentSquare.name() +
                         "\nRent cost: " + (2*currentSquare.rent(roll)));
             }
         }
         buttonPayRent.setEnabled(false);
     }
     private void handlePlayerRollDoubles() {
         if (isDouble) {
             buttonRollDice.setEnabled(true);
             buttonNextTurn.setEnabled(false);
         } else {
             buttonNextTurn.setEnabled(true);
         }
         buttonBuyHouse.setEnabled(false);
     }
     private void buyHouseAction(ActionEvent actionEvent) {
         JPanel panel = new JPanel(new GridLayout(0, 4));
         for (Border sq : playersGUI.get(currentPlayerOrder).getPlayer().properties()) {
             Property property;
             if (sq instanceof Property && ((Property) sq).isMonopoly()) {
                 property = (Property) sq;
                 JButton propButton = new JButton(property.name());
                 propButton.addActionListener(this::propertyButtonAction);
                 panel.add(propButton);
             }
         }
         JOptionPane.showMessageDialog(rightLayeredPane, panel, "Current houses you can purchase", JOptionPane.INFORMATION_MESSAGE);
     }

     private void propertyButtonAction(ActionEvent actionEvent) {
         String propName = ((JButton)actionEvent.getSource()).getText();
         Property property = playersGUI.get(currentPlayerOrder).getPlayer().getProperty(propName);
 
         boolean purchased = monopoly.buyHouses(playersGUI.get(currentPlayerOrder).getPlayer(), property);
         if (purchased) {
             infoConsole.setText("Bought House for $" + property.getHouseCost());
             if (property.getBuildings() == 5) {
                 infoConsole.append("\nYou have a hotel for " + property.name());
             } else {
                 infoConsole.append("\nYou have " + property.getBuildings() + " houses for " + property.name());
             }
         } else {
             infoConsole.setText("You can't afford anymore houses for " + property.name());
         }
         updatePlayerStatusTextArea();
     }
     private void runCPUAction(ActionEvent actionEvent) {
         rollDiceLogic();
         handleCPUSquare();
         handleButtons();
     }
     //CPU Player  
     private void handleButtons() {
         buttonRunCPU.setEnabled(false);
         buttonRollDice.setEnabled(false);
         buttonBuyHouse.setEnabled(false);
         buttonPayRent.setEnabled(false);
         buttonBuy.setEnabled(false);
         buttonPayBail.setEnabled(false);
         buttonNextTurn.setEnabled(true);
     }
     private void handleCPUSquare() {
         int roll = die1.getFaceValue() + die2.getFaceValue();
         if (playersList.get(currentPlayerOrder) instanceof Playeron) {
             PlayerGUI currentPlayer = this.playersGUI.get(currentPlayerOrder);
             Border currentSquare = this.gameBoardGUI.getSquare(currentSquareNumber);
             if (currentSquare.isOwnable() && currentSquare.isOwned() && currentPlayer.getPlayer() != currentSquare.owner()) {
                 infoConsole.setText("You paid rent on:\n" + currentSquare.name() +
                         "\nRent cost: " + currentSquare.rent(roll));
                 if (currentSquare instanceof Railroad) {
                     infoConsole.setText("You paid rent on:\n" + currentSquare.name() +
                             "\nRent cost: " + (2*currentSquare.rent(roll)));
                 }
             }
             else if (currentSquare.isOwnable() && !currentSquare.isOwned() && currentPlayer.getPlayerMoney() >= currentSquare.cost()) {
                 infoConsole.setText("You bought property:\n" + currentSquare.name() +
                         "\nPurchase cost: " + currentSquare.cost());
             } else if (currentPlayer.getPlayerMoney() <= currentSquare.cost()) {
                 infoConsole.setText("You don't have enough money to buy: \n" + currentSquare.name());
             }
             monopoly.handleSquare(currentPlayer.getPlayer(), currentSquare, roll);
             updatePlayerStatusTextArea();
             handleCPURollDouble();
         }
     }
     private void handleCPURollDouble() {
         if (isDouble) {
             buttonRunCPU.setEnabled(true);
             buttonNextTurn.setEnabled(false);
         } else {
             buttonNextTurn.setEnabled(true);
         }
     }
 //done
     private void payBailAction(ActionEvent actionEvent) {
         PlayerGUI currentPlayer = this.playersGUI.get(currentPlayerOrder);
         currentPlayer.getPlayer().setJailTurns(monopoly.leaveJail(currentPlayer.getPlayer()));
 
         infoConsole.setText("You paid $50 to get out of jail\n");
         infoConsole.append("You are now out of Jail!");
 
         handleButtons();
         updatePlayerStatusTextArea();
     }
     private void rollDiceLogic() {
         die1.rollDice();
         die2.rollDice();
         isDouble = die1.getFaceValue() == die2.getFaceValue();
         int diceValue = die1.getFaceValue() + die2.getFaceValue();
         PlayerGUI currentPlayer = this.playersGUI.get(currentPlayerOrder);
         Border currentSquare = this.gameBoardGUI.getSquare(Jail.IN_JAIL); 
 
         if (currentPlayer.getPlayer().getJailTurns() == 0) {
             currentSquareNumber = (this.playersGUI.get(currentPlayerOrder).getCurrentSquareNumber() + diceValue) % BOARD_SIZE;
             currentPlayer.move(diceValue);
             currentSquare = this.gameBoardGUI.getSquare(currentSquareNumber);
         }
 
        
         leftLayeredPane.remove(gameBoardGUI);
         leftLayeredPane.add(gameBoardGUI, Integer.valueOf(0));
 
         int prevSquare = currentSquareNumber - diceValue;
 
         infoConsole.setText("");

         if (currentSquareNumber < 12 && prevSquare < 0) {
             infoConsole.setText("You passed Go! You get $200!\n");
         }
         handleSquareGUI(currentPlayer, currentSquare, diceValue);
         updatePlayerStatusTextArea();
     }
     private void handleSquareGUI(PlayerGUI currentPlayer, Border currentSquare, int diceValue) {
         if (currentSquare.isOwnable() && !currentSquare.isOwned()) {
             infoConsole.append("You landed on " + currentSquare.name() +
                     "\nProperty Cost: $" + currentSquare.cost());
             isRollDouble(currentPlayerOrder);
             buttonBuy.setEnabled(true);
             buttonBuyHouse.setEnabled(true);
         } else if (currentSquare.isOwnable()) {
             if (currentSquare.owner().name().equals(currentPlayer.getPlayer().name())) {
                 buttonBuy.setEnabled(false);
                 buttonPayRent.setEnabled(false);
                 buttonBuyHouse.setEnabled(true);
                 infoConsole.append("You landed on " + currentSquare.name()
                         + "\nYou already own " + currentSquare.name());
             } else if (currentSquare instanceof Property || currentSquare instanceof Utility) {
                 infoConsole.append("Property: You landed on:" + currentSquare.name() +
                         "\nRent: $" + currentSquare.rent(diceValue));
                 handleButtonsSpecialSquares();
             } else if (currentSquare instanceof Railroad) {
                 infoConsole.append("Station: You landed on " + currentSquare.name() +
                         "\nRent: $" + (2*currentSquare.rent(diceValue)));
                 handleButtonsSpecialSquares();
             }
         } else {
             if (currentSquare instanceof Taxes) {
                 infoConsole.append("Taxes: You landed on " + currentSquare.name() +
                         "\nTax: $" + ((Taxes) currentSquare).getTax());
                 handleButtonsSpecialSquares();
             } else if(currentSquare instanceof Jail) {
                 handleJail(currentPlayer, currentSquare, diceValue);
             } else {
                 infoConsole.append("Non-purchasable: You landed on: \n" + currentSquare.name());
                 isRollDouble(currentPlayerOrder);
                 buttonBuy.setEnabled(false);
                 buttonPayRent.setEnabled(false);
             }
             buttonBuyHouse.setEnabled(false);
         }
     }

     private void handleButtonsSpecialSquares() {
         buttonPayRent.setEnabled(true);
         buttonRollDice.setEnabled(false);
         buttonNextTurn.setEnabled(false);
         buttonBuy.setEnabled(false);
         buttonBuyHouse.setEnabled(false);
     }

     private void handleJail(PlayerGUI currentPlayer, Border currentSquare, int diceValue) {
         if(((Jail) currentSquare).getType() == Jail.JailType.GOTO_JAIL) {
             handleButtons();
             infoConsole.setText("You have landed on:\n" + currentSquare.name());
             infoConsole.append("\nYou are now in Jail.");
             updateJailTurn(currentPlayer, currentSquare, diceValue);
         } else if(currentPlayer.getPlayer().getJailTurns() > 0) {
             updateJailTurn(currentPlayer, currentSquare, diceValue);
             handleRollJail(currentPlayer);
         } else {
             infoConsole.setText("Non-purchasable: You landed on: \n" + currentSquare.name());
             isRollDouble(currentPlayerOrder);
         }
     }

     private void updateJailTurn(PlayerGUI currentPlayer, Border currentSquare, int diceValue) {
         ArrayList<Integer> positionAndJailTurns = monopoly.handleSquare(currentPlayer.getPlayer(), currentSquare,
                 diceValue);
 
         int newJailPosition = positionAndJailTurns.get(0);
         int jailTurns = positionAndJailTurns.get(1);
 
         currentPlayer.moveTo(newJailPosition);
         currentPlayer.getPlayer().setJailTurns(jailTurns);
     }

     private void handleRollJail(PlayerGUI currentPlayer) {
         if(isDouble) {
             buttonRollDice.setEnabled(true);
             buttonNextTurn.setEnabled(false);
             infoConsole.setText("You rolled doubles. You are now out of jail!\nRoll again!");
         } else if (currentPlayer.getPlayer().getJailTurns() == 0) {
             buttonRollDice.setEnabled(false);
             buttonNextTurn.setEnabled(true);
             infoConsole.setText("You did not roll doubles.\n");
             infoConsole.append("You have been in Jail for 3 turns.\nYou are now out of jail\n");
         } else {
             buttonRollDice.setEnabled(false);
             buttonNextTurn.setEnabled(true);
             buttonPayBail.setEnabled(true);
             infoConsole.setText("You did not roll doubles.\nYou are still in Jail\n");
             infoConsole.append("Bail out of Jail for $50 by pressing Pay Bail");
         }
     }
 }
 
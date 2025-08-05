import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

public class SnakeLadderGame extends JFrame implements ActionListener {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private JPanel startPanel;
    private JPanel playerSelectionPanel;
    private JPanel gamePanel;
    private JPanel gameRightPanel;

    private JButton startButton;
    private JButton submitPlayersButton;
    private JButton rollDiceButton;
    private JButton restartButton;
    private JButton exitButton;

    private JTextField[] playerNameFields;
    private JLabel[] playerLabels;
    private JLabel statusLabel;
    private JLabel diceLabel; 

    private int currentPlayerIndex;
    private int[] playerPositions;
    private String[] playerNames;

    private Random random;
    private HashMap<Integer, Integer> snakes;
    private HashMap<Integer, Integer> ladders;

    private Timer timer; 
    private int diceRoll; 
    private int steps; 
    private int currentStep; 

    public SnakeLadderGame() {
        setTitle("Snake and Ladder Game");
        setSize(980, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
    
        initializeStartPanel();
        initializePlayerSelectionPanel();
        initializeGamePanel();
    
        add(mainPanel);
    }
    

    private void initializeStartPanel() {
        startPanel = new JPanel(new BorderLayout());
        JLabel background = new JLabel(new ImageIcon("ABC.png")); 
        background.setLayout(new BorderLayout());
        startPanel.add(background, BorderLayout.CENTER);
        startButton = new JButton("Start Game");
        startButton.setPreferredSize(new Dimension(150, 50));
        startButton.setFont(new Font("Arial", Font.BOLD, 18));
        startButton.addActionListener(e -> cardLayout.show(mainPanel, "PlayerSelection"));
    
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); 
        buttonPanel.add(startButton);
        background.add(buttonPanel, BorderLayout.SOUTH);
        
        mainPanel.add(startPanel, "Start");
    }
    

    private void initializePlayerSelectionPanel() {
        playerSelectionPanel = new JPanel();
        playerSelectionPanel.setLayout(new BoxLayout(playerSelectionPanel, BoxLayout.Y_AXIS));
    
        // Center alignment
        playerSelectionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        JLabel titleLabel = new JLabel("Enter Player Names");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerSelectionPanel.add(titleLabel);
    
        Integer[] playerOptions = {2, 3, 4};
        JComboBox<Integer> playerCountComboBox = new JComboBox<>(playerOptions);
        playerCountComboBox.setMaximumSize(new Dimension(200, 30)); 
        playerCountComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerSelectionPanel.add(playerCountComboBox);
    
        playerNameFields = new JTextField[4];
        for (int i = 0; i < 4; i++) {
            playerNameFields[i] = new JTextField();
            playerNameFields[i].setMaximumSize(new Dimension(200, 30));
            playerNameFields[i].setEnabled(i < 2);
            playerSelectionPanel.add(playerNameFields[i]);
        }
    
        playerCountComboBox.addActionListener(e -> {
            int selectedCount = (Integer) playerCountComboBox.getSelectedItem();
            for (int i = 0; i < 4; i++) {
                playerNameFields[i].setEnabled(i < selectedCount);
            }
        });
    
        submitPlayersButton = new JButton("Submit");
        submitPlayersButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitPlayersButton.addActionListener(e -> {
            int playerCount = (Integer) playerCountComboBox.getSelectedItem();
            playerNames = new String[playerCount];
            playerPositions = new int[playerCount];
            playerLabels = new JLabel[playerCount];
    
            for (int i = 0; i < playerCount; i++) {
                playerNames[i] = playerNameFields[i].getText();
                playerPositions[i] = 1;
            }
            currentPlayerIndex = 0;
    
            cardLayout.show(mainPanel, "Game");
            updatePlayerLabels();
            statusLabel.setText(playerNames[currentPlayerIndex] + "'s turn. Roll the dice.");
        });
    
        playerSelectionPanel.add(submitPlayersButton);
    
        mainPanel.add(playerSelectionPanel, "PlayerSelection");
    }
    
    

    private void initializeGamePanel() {
        gamePanel = new JPanel(new BorderLayout());
        JPanel boardPanel = new BoardPanel();
        gamePanel.add(boardPanel, BorderLayout.CENTER);
    
        gameRightPanel = new JPanel();
        gameRightPanel.setLayout(new BoxLayout(gameRightPanel, BoxLayout.Y_AXIS));
        gamePanel.add(gameRightPanel, BorderLayout.EAST);
    
        statusLabel = new JLabel("Start the game!");
        gamePanel.add(statusLabel, BorderLayout.NORTH);
    
        diceLabel = new JLabel("Dice: 0");
        gamePanel.add(diceLabel, BorderLayout.WEST);
    
      
        JPanel buttonPanel = new JPanel(new FlowLayout());
        rollDiceButton = new JButton("Roll Dice");
        rollDiceButton.addActionListener(this);
        buttonPanel.add(rollDiceButton);
    
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "PlayerSelection"));
        buttonPanel.add(backButton);
    
        gamePanel.add(buttonPanel, BorderLayout.SOUTH);
    
        mainPanel.add(gamePanel, "Game");
    
        random = new Random();
        initializeSnakesAndLadders();
    }
    
    

    private void updatePlayerLabels() {
        gameRightPanel.removeAll();
        Dimension labelSize = new Dimension(200, 30);
        for (int i = 0; i < playerNames.length; i++) {
            playerLabels[i] = new JLabel(playerNames[i], SwingConstants.CENTER);
            playerLabels[i].setOpaque(true);
            playerLabels[i].setBackground(getPlayerColor(i));
            playerLabels[i].setPreferredSize(labelSize);
            playerLabels[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            gameRightPanel.add(playerLabels[i]);
        }
    
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
           
            cardLayout.show(mainPanel, "PlayerSelection");
        });
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT); 
        gameRightPanel.add(Box.createVerticalStrut(20));
        gameRightPanel.add(backButton);
    
        gameRightPanel.revalidate();
        gameRightPanel.repaint();
    }
    

    private Color getPlayerColor(int playerIndex) {
        switch (playerIndex) {
            case 0: return Color.RED;
            case 1: return Color.BLUE;
            case 2: return Color.GREEN;
            case 3: return Color.YELLOW;
            default: return Color.BLACK;
        }
    }

    private void initializeSnakesAndLadders() {
        snakes = new HashMap<>();
        ladders = new HashMap<>();

        snakes.put(87, 49);
        snakes.put(84, 58);
        snakes.put(43, 17);
        snakes.put(56, 8);
        snakes.put(73, 15);
        snakes.put(50, 5);
        snakes.put(98, 40);

        ladders.put(2, 23);
        ladders.put(20, 59);
        ladders.put(6, 45);
        ladders.put(52, 72);
        ladders.put(57, 96);
        ladders.put(71, 92);
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rollDiceButton) {
            diceRoll = random.nextInt(6) + 1;
            diceLabel.setText("Dice: " + diceRoll);
            statusLabel.setText(playerNames[currentPlayerIndex] + " rolled a " + diceRoll);
    
            steps = diceRoll; 
            currentStep = 0;
    
            timer = new Timer(300, new ActionListener() { 
                @Override
                public void actionPerformed(ActionEvent evt) {
                    if (currentStep < steps) {
                        playerPositions[currentPlayerIndex]++;
                        if (playerPositions[currentPlayerIndex] > 100) {
                           
                            playerPositions[currentPlayerIndex] = 100 - (playerPositions[currentPlayerIndex] - 100);
                        }
                        updatePlayerPosition(); 
                        currentStep++;
                    } else {
                        timer.stop(); 
                        checkSnakeOrLadder(); 
                    }
                }
            });
            timer.start(); 
        }
    }

    private void checkSnakeOrLadder() {
        
        boolean positionChanged = false;
        if (snakes.containsKey(playerPositions[currentPlayerIndex])) {
            int oldPosition = playerPositions[currentPlayerIndex];
            playerPositions[currentPlayerIndex] = snakes.get(oldPosition);
            statusLabel.setText(playerNames[currentPlayerIndex] + " landed on a snake! Move from " + oldPosition + " to " + playerPositions[currentPlayerIndex]);
            positionChanged = true;
        } else if (ladders.containsKey(playerPositions[currentPlayerIndex])) {
            int oldPosition = playerPositions[currentPlayerIndex];
            playerPositions[currentPlayerIndex] = ladders.get(oldPosition);
            statusLabel.setText(playerNames[currentPlayerIndex] + " climbed a ladder! Move from " + oldPosition + " to " + playerPositions[currentPlayerIndex]);
            positionChanged = true;
        }
    
        updatePlayerPosition();
    
        if (playerPositions[currentPlayerIndex] == 100) {
           
            statusLabel.setText(playerNames[currentPlayerIndex] + " wins the game!");
            rollDiceButton.setEnabled(false);
            showEndGameOptions();
        } else if (positionChanged) {
           
            checkSnakeOrLadder();
        } else {
         
            currentPlayerIndex = (currentPlayerIndex + 1) % playerNames.length;
            statusLabel.setText(playerNames[currentPlayerIndex] + "'s turn. Roll the dice.");
        }
    }
    
    

    private void updatePlayerPosition() {
        repaint(); 
    }

    private void showEndGameOptions() {
        JPanel endGamePanel = new JPanel();
        exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        endGamePanel.add(exitButton);

        restartButton = new JButton("Restart");
        restartButton.addActionListener(e -> {
          

            currentPlayerIndex = 0;
            for (int i = 0; i < playerPositions.length; i++) {
                playerPositions[i] = 1;
            }

            cardLayout.show(mainPanel, "PlayerSelection");
        });
        endGamePanel.add(restartButton);

        gamePanel.add(endGamePanel, BorderLayout.CENTER);
        gamePanel.revalidate();
        gamePanel.repaint();
    }

    class BoardPanel extends JPanel {
        private Image boardImage;
    
        public BoardPanel() {
        

            boardImage = new ImageIcon("board.jpg").getImage();
        }
    
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
    

            g.drawImage(boardImage, 0, 0, getWidth(), getHeight(), this);
    
          
            drawPlayers(g);
        }
    
        private Point getPosition(int cell) {
            int row = (cell - 1) / 10;
            int col = (cell - 1) % 10;
    
            if (row % 2 == 1) {
                col = 9 - col;
            }
    
            int cellSize = getWidth() / 10; 

            return new Point(col * cellSize + cellSize / 2, (9 - row) * cellSize + cellSize / 2);
        }
    
        private void drawPlayers(Graphics g) {
            int cellSize = getWidth() / 10;
            for (int i = 0; i < playerNames.length; i++) {
                Point position = getPosition(playerPositions[i]);
                g.setColor(getPlayerColor(i));
                g.fillOval(position.x - cellSize / 4, position.y - cellSize / 4, cellSize / 2, cellSize / 2);
            }
        }
    }
    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SnakeLadderGame game = new SnakeLadderGame();
            game.setVisible(true);
        });
    }
}

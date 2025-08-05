package monopoly;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

import static monopoly.Monopoly.MAX_PLAYERS;
import static monopoly.Monopoly.MIN_PLAYERS;

public class MonopolyInitGUI extends JFrame {
    private MonopolyGUI monopolyGUI;
    private final LinkedList<Player> playersList;                             
    private JButton startButton;
    private JButton playButton;
    private JButton addPlayer;

    private JTextField playerNameInput;

    private JPanel playerNameList;
    private JPanel titleBackground;
    private JPanel messagePanel;
    private JPanel chooseVersionPanel;
    private JPanel playerInitPanel;                                     
    private JPanel startPanel;                                         
    private JPanel versionsPanel;
    private JPanel monopolyPanel;                                      
    private final JPanel switchPanels = new JPanel(new CardLayout());   

    private JMenuItem saveMenuItem;
    private JMenuItem loadMenuItem;
    private JMenuItem newMenuItem;

    private final Color[] playerTokenColors;
    private Font playerFont;
    private JComboBox<String> versionsList;

    public MonopolyInitGUI() {
        monopolyGUI = new MonopolyGUI();
        playersList = new LinkedList<>();
        playerTokenColors = monopolyGUI.playerTokenColors;
        initFrame();
        initPanelComponents();
        setupSwitchPanel();
        displayGUI();
    }

    private void initFrame() {
        this.setTitle("MONOPOLY");
        playerInitPanel = new JPanel(new GridBagLayout());
        startPanel = new JPanel(new GridBagLayout());
        versionsPanel = new JPanel(new GridBagLayout());
        monopolyPanel = new JPanel();

        this.setBounds(100, 100, 450, 300);
        this.setSize(1080,740);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        saveMenuItem = new JMenuItem("Save Game");
        loadMenuItem = new JMenuItem("Load Game");
        newMenuItem = new JMenuItem("New Game");

        saveMenuItem.addActionListener(actionEvent1 -> monopolyGUI.saveGame());
        loadMenuItem.addActionListener(this::loadGame);
        newMenuItem.addActionListener(actionEvent -> monopolyGUI.newGame());
        newMenuItem.addActionListener(this::newGamePanel);

        menu.add(saveMenuItem);
        menu.add(loadMenuItem);
        menu.add(newMenuItem);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);
    }

    public void loadGame(ActionEvent actionEvent) {
        Monopoly monopoly = new Monopoly();
        monopolyGUI = new MonopolyGUI();
        monopolyPanel.removeAll();
        monopolyGUI = monopolyGUI.setGame(monopoly.importGame());
        monopolyPanel.add(monopolyGUI);
        setGamePanel();
    }

    private void setGamePanel() {
        CardLayout cl = (CardLayout) (switchPanels.getLayout());
        cl.show(switchPanels, "MonopolyPanel");
    }

    private void newGamePanel(ActionEvent actionEvent) {
        CardLayout cl = (CardLayout) (switchPanels.getLayout());
        cl.show(switchPanels, "StartPanel");

        monopolyGUI = new MonopolyGUI();

        while (!playersList.isEmpty()) {
            playersList.removeFirst();
        }

        initFrame();
        initPanelComponents();
        setupSwitchPanel();
    }

    private void initPanelComponents() {
        startButton = new JButton("Start Game");
        playButton = new JButton("Play Game!");
        addPlayer = new JButton("Add Player");
       // addCPUPlayer = new JButton("Add CPU Player");
        playerNameInput = new JTextField("");
        playerNameList = new JPanel(new GridLayout(0,2));
        playerFont = new Font("Lucida Grande", Font.PLAIN, 20);
        titleBackground = new JPanel();
        messagePanel = new JPanel();
        chooseVersionPanel = new JPanel();
    }
    private void setupSwitchPanel() {
        Font font = new Font("Lucida Grande", Font.BOLD, 60);

        titleBackground.setPreferredSize(new Dimension(450, 90));
        titleBackground.setBackground(Color.RED);

        setupPanels();
        setupLayouts();
        JLabel title = new JLabel("MONOPOLY!");
        title.setFont(font);
        title.setOpaque(true);
        title.setBackground(Color.RED);
        title.setForeground(Color.WHITE);
        titleBackground.add(title);
        String[] versions = new String[]{"BD", "NK"};
        versionsList = new JComboBox<>(versions);
        versionsList.setSelectedIndex(0);
        DefaultListCellRenderer listRenderer;
        listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        versionsList.setRenderer(listRenderer);

        versionsList.setPreferredSize(new Dimension(75, 25));

        JLabel chooseVersion = new JLabel("Choose Monopoly version: ");
        chooseVersionPanel.add(chooseVersion);
        chooseVersionPanel.add(versionsList);
        chooseVersionPanel.setBackground(new Color(50, 200, 155));

        JLabel message = new JLabel("Enter Player Name Then Click Add Player (2-6 Player)");
        messagePanel.add(message);
        messagePanel.setBackground(new Color(50, 200, 155));

        switchPanels.add(startPanel, "StartPanel");
        switchPanels.add(versionsPanel, "VersionsPanel");
        switchPanels.add(playerInitPanel, "PlayerInitializePanel");
        switchPanels.add(monopolyPanel, "MonopolyPanel");

        this.add(switchPanels);
    }
    private void setupPanels() {
        this.setBounds(100, 100, 450, 300);
        this.setSize(1080,740);

        startPanel.setSize(new Dimension(250, 250));
        startPanel.setBackground(new Color(50, 255, 155));
        startPanel.setBorder(new LineBorder(Color.WHITE, 10, true));

        versionsPanel.setSize(new Dimension(250, 250));
        versionsPanel.setBackground(new Color(50, 255, 155));
        versionsPanel.setBorder(new LineBorder(Color.WHITE, 10, true));

        playerInitPanel.setSize(new Dimension(250, 250));
        playerInitPanel.setBackground(new Color(50, 255, 155));
        playerInitPanel.setBorder(new LineBorder(Color.WHITE, 10, true));

        monopolyPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        monopolyPanel.setLayout(null);
        monopolyPanel.setSize(new Dimension(250, 250));
        monopolyPanel.setBackground(Color.white);

        playerNameList.setPreferredSize(new Dimension(400, 240));
        playerNameList.setBackground(Color.RED);

        setupButtons();
    }

    private void setupButtons() {
        startButton.setPreferredSize(new Dimension(175, 50));
        playerNameInput.setPreferredSize(new Dimension(175, 50));
        addPlayer.setPreferredSize(new Dimension(175, 50));
       // addCPUPlayer.setPreferredSize(new Dimension(175, 50));
        playButton.setPreferredSize(new Dimension(175, 50));
        playButton.setEnabled(false);

        startButton.addActionListener(this::startAction);
        addPlayer.addActionListener(this::addPlayerAction);
       // addCPUPlayer.addActionListener(this::addPlayerAction);
        playButton.addActionListener(this::playAction);
    }

    private void setupLayouts() {
        GridBagConstraints gbagConstraintsTitle = new GridBagConstraints();
        gbagConstraintsTitle.gridx = 1;
        gbagConstraintsTitle.gridy = 1;
        gbagConstraintsTitle.insets = new Insets(0, 0, 20, 0);

        GridBagConstraints gbagConstraintsStartButton = new GridBagConstraints();
        gbagConstraintsStartButton.gridx = 1;
        gbagConstraintsStartButton.gridy = 2;
        gbagConstraintsStartButton.insets = new Insets(40, 0, 0, 0);

        GridBagConstraints gbagConstraintsPlayerNameList = new GridBagConstraints();
        gbagConstraintsPlayerNameList.gridx = 1;
        gbagConstraintsPlayerNameList.gridy = 1;
        gbagConstraintsPlayerNameList.gridwidth = 2;
        gbagConstraintsPlayerNameList.gridheight = 7;
        gbagConstraintsPlayerNameList.insets = new Insets(0, 0, 10, 0);

        GridBagConstraints gbagConstraintsPlayerNameInput = new GridBagConstraints();
        gbagConstraintsPlayerNameInput.gridx = 1;
        gbagConstraintsPlayerNameInput.gridy = 8;
        gbagConstraintsPlayerNameInput.gridwidth = 2;
        gbagConstraintsPlayerNameInput.insets = new Insets(5, 0, 10, 0);

        GridBagConstraints gbagConstraintsAddPlayerButton = new GridBagConstraints();
        gbagConstraintsAddPlayerButton.gridx = 1;
        gbagConstraintsAddPlayerButton.gridy = 9;
        gbagConstraintsAddPlayerButton.gridwidth = 1;
        gbagConstraintsAddPlayerButton.insets = new Insets(0, 15, 20, 0);

        GridBagConstraints gbagConstraintsAddCPUPlayerButton = new GridBagConstraints();
        gbagConstraintsAddCPUPlayerButton.gridx = 2;
        gbagConstraintsAddCPUPlayerButton.gridy = 9;
        gbagConstraintsAddCPUPlayerButton.gridwidth = 2;
        gbagConstraintsAddCPUPlayerButton.insets = new Insets(0, 0, 20, 0);

        GridBagConstraints gbagConstraintsChooseVersions = new GridBagConstraints();
        gbagConstraintsChooseVersions.gridx = 1;
        gbagConstraintsChooseVersions.gridy = 10;
        gbagConstraintsChooseVersions.gridwidth = 2;
        gbagConstraintsChooseVersions.insets = new Insets(20, 0, 0, 0);

        GridBagConstraints gbagConstraintsPlayButton = new GridBagConstraints();
        gbagConstraintsPlayButton.gridx = 1;
        gbagConstraintsPlayButton.gridy = 11;
        gbagConstraintsPlayButton.gridwidth = 2;
        gbagConstraintsPlayButton.insets = new Insets(20, 0, 0, 0);

        GridBagConstraints gbagConstraintsMessage = new GridBagConstraints();
        gbagConstraintsMessage.gridx = 1;
        gbagConstraintsMessage.gridy = 12;
        gbagConstraintsMessage.gridwidth = 2;
        gbagConstraintsMessage.insets = new Insets(40, 0, 0, 0);

        // Add the buttons, panels and labels to the frame
        startPanel.add(titleBackground, gbagConstraintsTitle);
        startPanel.add(startButton, gbagConstraintsStartButton);

        playerInitPanel.add(playerNameList, gbagConstraintsPlayerNameList);
        playerInitPanel.add(playerNameInput, gbagConstraintsPlayerNameInput);
        playerInitPanel.add(addPlayer, gbagConstraintsAddPlayerButton);
       // playerInitPanel.add(addCPUPlayer, gbagConstraintsAddCPUPlayerButton);
        playerInitPanel.add(chooseVersionPanel, gbagConstraintsChooseVersions);
        playerInitPanel.add(playButton, gbagConstraintsPlayButton);
        playerInitPanel.add(messagePanel, gbagConstraintsMessage);
    }
    private void addPlayerAction(ActionEvent actionEvent) {
        if (playersList.size() < MAX_PLAYERS && playerNameInput.getText().matches(".*\\w.*")) {
            Player newPlayer;
            if (actionEvent.getActionCommand().equals("Add Player")) {
                newPlayer = new AllPlayer(playerNameInput.getText());
            } else {
                newPlayer = new Playeron(playerNameInput.getText());
            }
            addNewPlayerPanel(newPlayer);
        } else if (!playerNameInput.getText().matches(".*\\w.*")) { 
            JOptionPane.showMessageDialog(playerInitPanel, "Type a name in the text box!");
        }
        else {
            JOptionPane.showMessageDialog(playerInitPanel, "You can't have more than 6 players.\nPress Play Game!");
        }
    }

    private void addNewPlayerPanel(Player newPlayer) {
        playersList.add(newPlayer);
        JLabel playerNumber = new JLabel();
        playerNumber.setFont(playerFont);
        playerNumber.setOpaque(true);
        playerNumber.setBackground(playerTokenColors[playersList.indexOf(newPlayer)]);
        playerNumber.setForeground(Color.WHITE);

        JLabel newPlayerLabel = new JLabel();
        newPlayerLabel.setFont(playerFont);
        newPlayerLabel.setOpaque(true);
        newPlayerLabel.setBackground(playerTokenColors[playersList.indexOf(newPlayer)]);
        newPlayerLabel.setForeground(Color.WHITE);

        playerNumber.setText("Player " + (playersList.indexOf(newPlayer) + 1) + ": ");
        newPlayerLabel.setText(newPlayer.name());

        JPanel tempPanelNumber = new JPanel();
        tempPanelNumber.setPreferredSize(new Dimension(150, 40));
        tempPanelNumber.setBackground(playerTokenColors[playersList.indexOf(newPlayer)]);
        tempPanelNumber.add(playerNumber);

        JPanel tempPanelName = new JPanel();
        tempPanelName.setPreferredSize(new Dimension(250, 40));
        tempPanelName.setBackground(playerTokenColors[playersList.indexOf(newPlayer)]);
        tempPanelName.add(newPlayerLabel);

        playerNameList.add(tempPanelNumber);
        playerNameList.add(tempPanelName);
        playerNameList.revalidate();
        playerNameList.repaint();

        if (playersList.size() >= MIN_PLAYERS && playersList.size() <= MAX_PLAYERS) {
            playButton.setEnabled(true);
        }
        playerNameInput.setText("");
    }

    private void startAction(ActionEvent actionEvent) {
        CardLayout cl = (CardLayout) (switchPanels.getLayout());
        cl.show(switchPanels, "PlayerInitializePanel");
    }
    private void playAction(ActionEvent actionEvent) {
        CardLayout cl = (CardLayout) (switchPanels.getLayout());
        cl.show(switchPanels, "MonopolyPanel");

        Versions version;
        if(versionsList.getSelectedIndex() == 0) {
            version = Versions.BD;
        } else {
            version = Versions.NK;
        }

        GameBoardConvertintoGUI gameBoardGUI = monopolyGUI.getGameBoard1();
        gameBoardGUI.setVersion(version);
        monopolyGUI.setPlayers(playersList);
        monopolyGUI.setGameBoard1(gameBoardGUI);
        monopolyGUI.setupMonopolyBoard();
        monopolyGUI.setGameBoard1(gameBoardGUI);
        monopolyPanel.add(monopolyGUI);
    }

    private void displayGUI() {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?")
                        == JOptionPane.OK_OPTION) {
                    setVisible(false);
                    dispose();
                }
            }
        });
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MonopolyInitGUI();
    }
}

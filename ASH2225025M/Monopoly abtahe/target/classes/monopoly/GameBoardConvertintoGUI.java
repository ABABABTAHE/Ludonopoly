
package monopoly;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class GameBoardConvertintoGUI extends JPanel {
    private GameBoardCreate gameBoard;
    private Versions language;

    public GameBoardConvertintoGUI(int x, int y, int width, int height) {
        this.setBorder(new LineBorder(Color.BLACK));
        this.setBounds(x, y, width, height);
        this.language = Versions.NK;
        this.setLayout(null);
    }

   
    public void setVersion(Versions language){
        this.gameBoard = new GameBoardCreate(language);
        this.language = language;
        initializeSquarePanels();
    }

    public Versions getVersion(){
        return language;
    }
    public Border getSquare(int squareNumber) {
        return this.gameBoard.square(squareNumber);
    }

    private void initializeSquarePanels() {
        ArrayList<String> squareNames = new ArrayList<>();
        for (Border sq : gameBoard.getBoard()) {
            squareNames.add(sq.name());
        }

        BorderGUI square;
        int xOffset = 5;
        int yOffset = 5;
        int maxX = 550;
        int maxY = 550;
        int x = xOffset + maxX;
        int y = yOffset + maxY;

        // create right bottom corner square, "Go"
        square = new BorderGUI(x, y,100,100, squareNames.get(0),-45);
        this.add(square);

        // create bottom square
        for (int i = 0; i < 9; i++) {
            x -= 50;
            square = new BorderGUI(x, y,50,100, squareNames.get(i+1),0);
            this.add(square);
        }

        // create left bottom corner square, "JAiL"
        square = new BorderGUI(xOffset, y,100,100, squareNames.get(10),45);
        this.add(square);

        // create left square
        for (int i = 0; i < 9; i++) {
            y -= 50;
            square = new BorderGUI(xOffset, y,100,50, squareNames.get(i+11),90);
            this.add(square);
        }

        // create top left corner square, "FREE PARKING"
        square = new BorderGUI(xOffset, yOffset,100,100, squareNames.get(20),135);
        this.add(square);

        // create top square
        for (int i = 0; i < 9; i++) {
            square = new BorderGUI(x, yOffset,50,100, squareNames.get(i+21),180);
            x += 50;
            this.add(square);
        }

        // create top right corner square, "GO TO JAIL"
        square = new BorderGUI(555,5,100,100, squareNames.get(30),-135);
        this.add(square);

        // create right squares
        for (int i = 0; i < 9; i++) {
            square = new BorderGUI(x, y,100,50, squareNames.get(i+31),-90);
            y += 50;
            this.add(square);
        }

        // create center ofgame board
        JLabel centerLabel = new JLabel("MONOPOLY") {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D)g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                AffineTransform aT = g2.getTransform();
                Shape oldShape = g2.getClip();
                double x = getWidth()/2.0;
                double y = getHeight()/2.0;
                aT.rotate(Math.toRadians(-45), x, y);
                g2.setTransform(aT);
                g2.setClip(oldShape);
                super.paintComponent(g);
            }
        };
        centerLabel.setForeground(Color.WHITE);
        centerLabel.setBackground(Color.RED);
        centerLabel.setOpaque(true);// enable to the background color (otherwise it would be transparent).
        centerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        centerLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
        centerLabel.setBounds(200, 280, 265, 55);
        this.add(centerLabel);
    }
}

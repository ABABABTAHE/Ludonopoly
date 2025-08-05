package monopoly;

import java.io.Serializable;
import java.util.Objects;

public class GameBoardCreate implements Serializable {
    public static final int BOARD_SIZE = 40;
    private final Border[] board; // represent of the game board
    private final Versions versions;


    public GameBoardCreate() {
        this(Versions.NK);
    }

 
    public GameBoardCreate(Versions version) {
        this.versions = version;
        this.board = new Border[BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i] = makeSquare(i);
        }
        groupProperties();
        groupRailroads();
        groupUtilities();
    }

    public int size() {
        return BOARD_SIZE;
    }

    public Border[] getBoard() {
        return this.board;
    }

    public Border square(int position) {
        return board[position];
    }

    private Border makeSquare(int position) {
        return switch (position) {  
            case 0 -> square0(0);   
            case 1 -> square1(1);    
            case 2 -> square2(2);    
            case 3 -> square3(3);    
            case 4 -> square4(4);   
            case 5 -> square5(5);    
            case 6 -> square6(6);    
            case 7 -> square7(7);    
            case 8 -> square8(8);   
            case 9 -> square9(9);   
            case 10 -> square10(10); 
            case 11 -> square11(11); 
            case 12 -> square12(12); 
            case 13 -> square13(13); 
            case 14 -> square14(14);  
            case 15 -> square15(15); 
            case 16 -> square16(16);
            case 17 -> square17(17);
            case 18 -> square18(18);
            case 19 -> square19(19);
            case 20 -> square20(20); 
            case 21 -> square21(21);
            case 22 -> square22(22); 
            case 23 -> square23(23);  
            case 24 -> square24(24);  
            case 25 -> square25(25);  
            case 26 -> square26(26);  
            case 27 -> square27(27);  
            case 28 -> square28(28);  
            case 29 -> square29(29);  
            case 30 -> square30(30);  
            case 31 -> square31(31); 
            case 32 -> square32(32);  
            case 33 -> square33(33);  
            case 34 -> square34(34); 
            case 35 -> square35(35);
            case 36 -> square36(36);  
            case 37 -> square37(37);
            case 38 -> square38(38);  
            case 39 -> square39(39); 
            default -> null;
        };
    }
    private void groupProperties() {
        Property brownA = (Property) square(1);
        Property brownB = (Property) square(3);
        Property skyA = (Property) square(6);
        Property skyB = (Property) square(8);
        Property skyC = (Property) square(9);
        Property pinkA = (Property) square(11);
        Property pinkB = (Property) square(13);
        Property pinkC = (Property) square(14);
        Property orangeA = (Property) square(16);
        Property orangeB = (Property) square(18);
        Property orangeC = (Property) square(19);
        Property redA = (Property) square(21);
        Property redB = (Property) square(23);
        Property redC = (Property) square(24);
        Property yellowA = (Property) square(26);
        Property yellowB = (Property) square(27);
        Property yellowC = (Property) square(29);
        Property greenA = (Property) square(31);
        Property greenB = (Property) square(32);
        Property greenC = (Property) square(34);
        Property blueA = (Property) square(37);
        Property blueB = (Property) square(39);
    brownA.setGroup(brownB);
        brownB.setGroup(brownA);
    skyA.setGroup(skyB, skyC);
        skyB.setGroup(skyA, skyC);
        skyC.setGroup(skyA, skyB);
    pinkA.setGroup(pinkB, pinkC);
        pinkB.setGroup(pinkA, pinkC);
        pinkC.setGroup(pinkA, pinkB);
    orangeA.setGroup(orangeB, orangeC);
        orangeB.setGroup(orangeA, orangeC);
        orangeC.setGroup(orangeA, orangeB);

    redA.setGroup(redB, redC);
        redB.setGroup(redA, redC);
        redC.setGroup(redA, redB);
     yellowA.setGroup(yellowB, yellowC);
        yellowB.setGroup(yellowA, yellowC);
        yellowC.setGroup(yellowA, yellowB);
    greenA.setGroup(greenB, greenC);
        greenB.setGroup(greenA, greenC);
        greenC.setGroup(greenA, greenB);

    blueA.setGroup(blueB);
        blueB.setGroup(blueA);
    }

    private void groupRailroads() {
        Railroad a = (Railroad) square(5);  
        Railroad b = (Railroad) square(15); 
        Railroad c = (Railroad) square(25);
        Railroad d = (Railroad) square(35);

        a.setGroup(b, c, d);
        b.setGroup(a, c, d);
        c.setGroup(a, b, d);
        d.setGroup(a, b, c);
    }
    private void groupUtilities() {
        Utility a = (Utility) square(12); 
        Utility b = (Utility) square(28);

        a.setGroup(b);
        b.setGroup(a);
    }

    public Border square0(int position) {
        return new Inactive(position, JsonFile.ToJSON(position, String.valueOf(this.versions)));
    }

    public Border square1(int position) {
        int rent = 2;
        int oneHouse = 10;
        int twoHouse = 30;
        int threeHouse = 90;
        int fourHouse = 160;
        int hotel = 250;
        int propertyCost = 60;
        int houses = 50;
        return new Property(position, JsonFile.ToJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Border square2(int position) {
        return new Inactive(position, JsonFile.ToJSON(position, String.valueOf(this.versions)));
    }
    public Border square3(int position) {
        int rent = 4;
        int oneHouse = 20;
        int twoHouse = 60;
        int threeHouse = 180;
        int fourHouse = 320;
        int hotel = 450;
        int propertyCost = 60;
        int houses = 50;
        return new Property(position, JsonFile.ToJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Border square4(int position) {
        return new Taxes(position, Objects.requireNonNull(JsonFile.ToJSON(position, String.valueOf(this.versions))));

    }
    public Border square5(int position) {
        return new Railroad(position, JsonFile.ToJSON(position, String.valueOf(this.versions)));

    }

    public Border square6(int position) {
        int rent = 6;
        int oneHouse = 30;
        int twoHouse = 90;
        int threeHouse = 270;
        int fourHouse = 400;
        int hotel = 550;
        int propertyCost = 100;
        int houses = 50;
        return new Property(position, JsonFile.ToJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Border square7(int position) {
        return new Inactive(position, JsonFile.ToJSON(position, String.valueOf(this.versions)));

    }

    public Border square8(int position) {
        int rent = 6;
        int oneHouse = 30;
        int twoHouse = 90;
        int threeHouse = 270;
        int fourHouse = 400;
        int hotel = 550;
        int propertyCost = 100;
        int houses = 50;
        return new Property(position, JsonFile.ToJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }
    public Border square9(int position) {
        int rent = 6;
        int oneHouse = 30;
        int twoHouse = 90;
        int threeHouse = 270;
        int fourHouse = 400;
        int hotel = 550;
        int propertyCost = 120;
        int houses = 50;
        return new Property(position, JsonFile.ToJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Border square10(int position) {
        return new Jail(position, JsonFile.ToJSON(position, String.valueOf(this.versions)), Jail.JailType.JUST_VISITING);
    }

    public Border square11(int position) {
        int rent = 10;
        int oneHouse = 50;
        int twoHouse = 150;
        int threeHouse = 450;
        int fourHouse = 625;
        int hotel = 750;
        int propertyCost = 140;
        int houses = 100;
        return new Property(position, JsonFile.ToJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Border square12(int position) {
        return new Utility(position, JsonFile.ToJSON(position, String.valueOf(this.versions)));
    }

    public Border square13(int position) {
        int rent = 10;
        int oneHouse = 50;
        int twoHouse = 150;
        int threeHouse = 450;
        int fourHouse = 625;
        int hotel = 750;
        int propertyCost = 140;
        int houses = 100;
        return new Property(position, JsonFile.ToJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Border square14(int position) {
        int rent = 10;
        int oneHouse = 50;
        int twoHouse = 150;
        int threeHouse = 450;
        int fourHouse = 625;
        int hotel = 750;
        int propertyCost = 160;
        int houses = 100;
        return new Property(position, JsonFile.ToJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Border square15(int position) {
        return new Railroad(position, JsonFile.ToJSON(position, String.valueOf(this.versions)));
    }
    public Border square16(int position) {
        int rent = 14;
        int oneHouse = 70;
        int twoHouse = 200;
        int threeHouse = 550;
        int fourHouse = 750;
        int hotel = 750;
        int propertyCost = 180;
        int houses = 100;
        return new Property(position, JsonFile.ToJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }
    public Border square17(int position) {
        return new Inactive(position, JsonFile.ToJSON(position, String.valueOf(this.versions)));
    }
    public Border square18(int position) {
        int rent = 14;
        int oneHouse = 70;
        int twoHouse = 200;
        int threeHouse = 550;
        int fourHouse = 750;
        int hotel = 950;
        int propertyCost = 180;
        int houses = 100;
        return new Property(position, JsonFile.ToJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Border square19(int position) {
        int rent = 16;
        int oneHouse = 80;
        int twoHouse = 220;
        int threeHouse = 600;
        int fourHouse = 800;
        int hotel = 1000;
        int propertyCost = 200;
        int houses = 100;
        return new Property(position, JsonFile.ToJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }
    public Border square20(int position) {
        return new Inactive(position, JsonFile.ToJSON(position, String.valueOf(this.versions)));
    }
    public Border square21(int position) {
        int rent = 18;
        int oneHouse = 90;
        int twoHouse = 250;
        int threeHouse = 700;
        int fourHouse = 875;
        int hotel = 1050;
        int propertyCost = 220;
        int houses = 150;
        return new Property(position, JsonFile.ToJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }
    public Border square22(int position) {
        return new Inactive(position, JsonFile.ToJSON(position, String.valueOf(this.versions)));
    }

    public Border square23(int position) {
        int rent = 18;
        int oneHouse = 90;
        int twoHouse = 250;
        int threeHouse = 700;
        int fourHouse = 875;
        int hotel = 1050;
        int propertyCost = 220;
        int houses = 150;
        return new Property(position, JsonFile.ToJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Border square24(int position) {
        int rent = 20;
        int oneHouse = 100;
        int twoHouse = 300;
        int threeHouse = 750;
        int fourHouse = 925;
        int hotel = 1100;
        int propertyCost = 260;
        int houses = 150;
        return new Property(position, JsonFile.ToJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Border square25(int position) {
        return new Railroad(position, JsonFile.ToJSON(position, String.valueOf(this.versions)));
    }

    public Border square26(int position) {
        int rent = 22;
        int oneHouse = 110;
        int twoHouse = 330;
        int threeHouse = 800;
        int fourHouse = 975;
        int hotel = 1150;
        int propertyCost = 260;
        int houses = 150;
        return new Property(position, JsonFile.ToJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Border square27(int position) {
        int rent = 22;
        int oneHouse = 110;
        int twoHouse = 330;
        int threeHouse = 800;
        int fourHouse = 975;
        int hotel = 1150;
        int propertyCost = 280;
        int houses = 150;
        return new Property(position, JsonFile.ToJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Border square28(int position) {
        return new Utility(position, JsonFile.ToJSON(position, String.valueOf(this.versions)));
    }
    public Border square29(int position) {
        int rent = 24;
        int oneHouse = 120;
        int twoHouse = 360;
        int threeHouse = 850;
        int fourHouse = 1025;
        int hotel = 1200;
        int propertyCost = 280;
        int houses = 150;
        return new Property(position, JsonFile.ToJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Border square30(int position) {
        return new Jail(position, JsonFile.ToJSON(position, String.valueOf(this.versions)), Jail.JailType.GOTO_JAIL);
    }

    public Border square31(int position) {
        int rent = 26;
        int oneHouse = 130;
        int twoHouse = 390;
        int threeHouse = 900;
        int fourHouse = 1100;
        int hotel = 1250;
        int propertyCost = 300;
        int houses = 200;
        return new Property(position, JsonFile.ToJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }
    public Border square32(int position) {
        int rent = 26;
        int oneHouse = 130;
        int twoHouse = 390;
        int threeHouse = 900;
        int fourHouse = 1100;
        int hotel = 1275;
        int propertyCost = 300;
        int houses = 200;
        return new Property(position, JsonFile.ToJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }
    public Border square33(int position) {
        return new Inactive(position, JsonFile.ToJSON(position, String.valueOf(this.versions)));
    }
    public Border square34(int position) {
        int rent = 26;
        int oneHouse = 130;
        int twoHouse = 390;
        int threeHouse = 900;
        int fourHouse = 1100;
        int hotel = 1275;
        int propertyCost = 320;
        int houses = 200;
        return new Property(position, JsonFile.ToJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Border square35(int position) {
        return new Railroad(position, JsonFile.ToJSON(position, String.valueOf(this.versions)));
    }

    public Border square36(int position) {
        return new Inactive(position, JsonFile.ToJSON(position, String.valueOf(this.versions)));
    }
    public Border square37(int position) {
        int rent = 35;
        int oneHouse = 175;
        int twoHouse = 500;
        int threeHouse = 1100;
        int fourHouse = 1500;
        int hotel = 1500;
        int propertyCost = 350;
        int houses = 200;
        return new Property(position, JsonFile.ToJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }
    public Border square38(int position) {
        return new Taxes(position, Objects.requireNonNull(JsonFile.ToJSON(position, String.valueOf(this.versions))));
    }
    public Border square39(int position) {
        int rent = 35;
        int oneHouse = 175;
        int twoHouse = 500;
        int threeHouse = 1100;
        int fourHouse = 1300;
        int hotel = 1500;
        int propertyCost = 400;
        int houses = 200;
        return new Property(position, JsonFile.ToJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }
}

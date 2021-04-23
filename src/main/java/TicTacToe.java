import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
  static List playerPositions = new ArrayList();
  static List cpuPositions = new ArrayList();
  static ArrayList<List> winning;

  public static void main(String[] args) {
    winning = generateWinning();
    char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
        {'-', '+', '-', '+', '-'},
        {' ', '|', ' ', '|', ' '},
        {'-', '+', '-', '+', '-'},
        {' ', '|', ' ', '|', ' '}};

    printBoard(gameBoard);
    playGame(gameBoard);
  }

  public static void playGame(char[][] gameBoard) {
    int result = 4;
    while(true) {
      Scanner scan = new Scanner(System.in);
      System.out.println("Enter your placement 1 - 9");
      int posn = scan.nextInt();
      while(playerPositions.contains(posn) || cpuPositions.contains(posn)) {
        System.out.println("Please enter a position that hasn't already been taken");
        posn = scan.nextInt();
      }
      playerPositions.add(posn);
      placePiece(gameBoard, posn, "player");
      result = checkWinner();
      if (result != 4) {
        printBoard(gameBoard);
        break;
      }
      Random rand = new Random();
      int cpuPosn = rand.nextInt(9) + 1;
      while(playerPositions.contains(cpuPosn) || cpuPositions.contains(cpuPosn)) {
        cpuPosn = rand.nextInt(9) + 1;
      }
      cpuPositions.add(cpuPosn);
      placePiece(gameBoard, cpuPosn, "cpu");
      result = checkWinner();
      if (result != 4) {
        printBoard(gameBoard);
        break;
      }
      printBoard(gameBoard);
    }
  }

  public static ArrayList generateWinning() {
    List topRow = Arrays.asList(1,2,3);
    List middleRow = Arrays.asList(4,5,6);
    List bottomRow = Arrays.asList(7,8,9);
    List leftCol = Arrays.asList(1,4,7);
    List midCol = Arrays.asList(2,5,8);
    List rightCol = Arrays.asList(3,6,9);
    List cross1 = Arrays.asList(1,5,9);
    List cross2 = Arrays.asList(3,5,7);
    ArrayList<List> winning = new ArrayList<List>();
    winning.add(topRow);
    winning.add(middleRow);
    winning.add(bottomRow);
    winning.add(leftCol);
    winning.add(midCol);
    winning.add(rightCol);
    winning.add(cross1);
    winning.add(cross2);

    return winning;
  }

  public static int checkWinner() {
    for (List l : winning) {
      if(playerPositions.containsAll(l)) {
        System.out.println("You won!");
        return 1;
      } else if(cpuPositions.containsAll(l)) {
        System.out.println("You lost!");
        return 2;
      }
    }
    if (cpuPositions.size() + playerPositions.size() == 9){
      System.out.println("Tied!");
      return 3;
    }
    return 4;
  }

  public static void printBoard(char[][] gameBoard) {
    for(char[] row : gameBoard) {
      for(char c : row) {
        System.out.print(c);
      }
      System.out.println();
    }
  }

  public static void placePiece(char[][] gameBoard, int posn, String user) {
    char symbol = 'O';
    if(user.equals("player")) {
      symbol = 'X';
    }

    switch(posn) {
      case 1:
        gameBoard[0][0] = symbol;
        break;
      case 2:
        gameBoard[0][2] = symbol;
        break;
      case 3:
        gameBoard[0][4] = symbol;
        break;
      case 4:
        gameBoard[2][0] = symbol;
        break;
      case 5:
        gameBoard[2][2] = symbol;
        break;
      case 6:
        gameBoard[2][4] = symbol;
        break;
      case 7:
        gameBoard[4][0] = symbol;
        break;
      case 8:
        gameBoard[4][2] = symbol;
        break;
      case 9:
        gameBoard[4][4] = symbol;
        break;
    }
  }
}

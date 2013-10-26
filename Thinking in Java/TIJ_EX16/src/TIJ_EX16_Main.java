class Game {
  Game(int i) {
    System.out.println("Game constructor"+" "+i);
  }
}

class BoardGame extends Game {
  BoardGame(int i) {
    super(i);
    System.out.println("BoardGame constructor");
  }
}

public class TIJ_EX16_Main extends BoardGame {
  TIJ_EX16_Main() {
    super(11);
    System.out.println("TIJ_EX16_Main constructor");
  }
  public static void main(String[] args) {
    TIJ_EX16_Main x = new TIJ_EX16_Main();
  }
} ///:~ 
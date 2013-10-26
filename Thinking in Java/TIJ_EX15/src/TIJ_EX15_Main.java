class Art {
  Art() {
    System.out.println("Art constructor");
  }
}

class Drawing extends Art {
  Drawing() {
    System.out.println("Drawing constructor");
  }
}

public class TIJ_EX15_Main extends Drawing {
  TIJ_EX15_Main() {
    System.out.println("TIJ_EX15_Main constructor");
  }
  public static void main(String[] args) {
    TIJ_EX15_Main x = new TIJ_EX15_Main();
  }
} 
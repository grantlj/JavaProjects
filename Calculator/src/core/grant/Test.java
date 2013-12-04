package core.grant;

public class Test {
  public static void main(String[] args)
  {
	  System.out.println(new SuffixCalc(new MidToSuffix("3.1*((20-5)+(4.5*20)").getSuffix(),2).getAns());
  }
}

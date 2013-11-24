package priqueue2;

public class Dlinknode {

public Dlinknode prev=null,next=null;
  public char val;
  public int pri;
  public int level;    
 
  /*level:   is to describe the privilege of a node.
   *level 0: is the head.
   *level 1: is the tail.
   *level 2: is in the middle.
   */
  
  public Dlinknode(char val,int pri,int level)
  {
	  this.val=val;
	  this.pri=pri;
	  this.level=level;
  }
  public Dlinknode()
  {
	  
  }
}

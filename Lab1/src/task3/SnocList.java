package task3;

public class SnocList<T> {
  /*
   * c: The element in former SnocList.
   * l: The next SnocList's handler.
   */
  T c;
  @SuppressWarnings("rawtypes")
  SnocList l=null;
 
  @SuppressWarnings("rawtypes")
//Constructor for the middle SnocList.
  SnocList(T c,SnocList l)
  {
	  this.c=c;
	  this.l=l;
  }
  
  //Constructor for the head.
  SnocList(T c)
  {
	  this.c=c;
  }
}

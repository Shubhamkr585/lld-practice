// the caretaker

import java.util.*;
class History{
  private Stack<Momento> stack=new Stack<>();

  public void save(Momento m){
      stack.push(m);
  }
  public Momento undo(){
      return stack.pop();
  }
}
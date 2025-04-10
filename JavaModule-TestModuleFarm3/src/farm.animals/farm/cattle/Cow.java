package farm.cattle;

import farm.Animal;

public class Cow extends Animal{
//  private String tagNumber;

  public Cow(String tagNumber){
    super(tagNumber);
  }
//  public String getTagNumber(){
//    return tagNumber;
//  }

  @Override
  public boolean equals(Object o){
    if(o instanceof Cow){
       Cow c = (Cow) o;
       if(c.getTagNumber().equalsIgnoreCase(this.getTagNumber()))
          return true;
    }
    return false;
  }
  @Override
  public String toString(){
    return "The tag number is "+this.getTagNumber();
  }
}

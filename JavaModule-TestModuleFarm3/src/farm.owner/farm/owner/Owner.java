package farm.owner;

import farm.Animal;
import farm.cattle.Cow;   // singular
import farm.cattle.Cattle;// plural

public class Owner {
    public Animal getAnimal(String tag){
      for(Cow cow : new Cattle().getCattleTagNumbers()){
        if(cow.getTagNumber().equalsIgnoreCase(tag)){
          return cow;
        }
      }
       return null;
    }
    public String getName(){
        return "SK";
    }
}


/*

public class Owner {
    public String getName(){
        return "SK";
    }
    // In its API, Owner is exposing a type i.e. Animal from a different module (farm.animals)
    //  => transitive i.e. requires transitive farm.animals; // in module-info.java for farm.owner
    public Animal getAnimal(String tag){
      System.out.println("looking for tag: "+tag);
      for(Cow cow : new Cattle().getCattleTagNumbers()){
        System.out.println("tag : "+cow.getTagNumber());
        if(cow.getTagNumber().equalsIgnoreCase(tag)){
          System.out.println("Found cow! "+cow);
          return cow;
        }
      }
       return null;
    }
}

*/

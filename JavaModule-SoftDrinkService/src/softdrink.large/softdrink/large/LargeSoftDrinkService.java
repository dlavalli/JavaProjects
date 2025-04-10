package softdrink.large;

import softdrink.api.SoftDrinkService;

public class LargeSoftDrinkService implements SoftDrinkService{
    @Override
     public String getSize(){
       return "Large";
     }
}

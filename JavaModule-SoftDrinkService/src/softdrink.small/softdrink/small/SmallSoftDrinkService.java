package softdrink.small;

import softdrink.api.SoftDrinkService;

public class SmallSoftDrinkService implements SoftDrinkService{
    @Override
     public String getSize(){
       return "Small";
     }
}

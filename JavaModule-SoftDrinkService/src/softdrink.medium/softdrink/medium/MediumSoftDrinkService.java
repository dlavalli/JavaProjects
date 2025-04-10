package softdrink.medium;

import softdrink.api.SoftDrinkService;

public class MediumSoftDrinkService implements SoftDrinkService{
    @Override
     public String getSize(){
       return "Medium";
     }
}

package farm.cattle;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import farm.cattle.Cow;

public class Cattle {
    private List<Cow> cattleTagNumbers = Arrays.asList(new Cow("C1"), new Cow("C2"), new Cow("C3"));

    public int getTotal() {
        return cattleTagNumbers.size();
    }
    public List<Cow> getCattleTagNumbers(){
      return new ArrayList<>(cattleTagNumbers); // defensive copying
    }
}

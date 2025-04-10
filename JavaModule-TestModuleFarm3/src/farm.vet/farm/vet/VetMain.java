package farm.vet;

import farm.owner.Owner;
import farm.cattle.Cattle;

public class VetMain {
    public static void main(String[] args) {
        // dealing with Owner
        Owner owner = new Owner();
        System.out.println(owner.getAnimal("C2"));

        Cattle cattle = new Cattle();
        // do Cattle stuff...
        System.out.println("There are "+cattle.getTotal()+" cattle in total");
    }
}

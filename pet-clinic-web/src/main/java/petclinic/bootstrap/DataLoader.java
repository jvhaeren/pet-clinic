package petclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import petclinic.model.Owner;
import petclinic.model.Vet;
import petclinic.services.OwnerService;
import petclinic.services.VetService;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }


    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setFirstName("Sean");
        owner1.setLastName("Penn");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Patrick");
        owner2.setLastName("Swayze");

        ownerService.save(owner2);

        System.out.println("Loaded owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Michael");
        vet1.setLastName("Keaton");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Tom");
        vet2.setLastName("Cruise");

        vetService.save(vet2);

        System.out.println("Loaded vets....");

    }
}

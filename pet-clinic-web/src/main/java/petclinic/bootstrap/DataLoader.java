package petclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import petclinic.model.Owner;
import petclinic.model.Pet;
import petclinic.model.PetType;
import petclinic.model.Vet;
import petclinic.services.OwnerService;
import petclinic.services.PetTypeService;
import petclinic.services.VetService;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }


    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Sean");
        owner1.setLastName("Penn");
        owner1.setAddress("Van Nelleweg 1");
        owner1.setCity("Rotterdam");
        owner1.setTelephone("123123123");

        Pet seansPet = new Pet();
        seansPet.setPetType(savedDogType);
        seansPet.setOwner(owner1);
        seansPet.setBirthDate(LocalDate.now());
        seansPet.setName("Max");
        owner1.getPets().add(seansPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Patrick");
        owner2.setLastName("Swayze");
        owner2.setAddress("Straatweg 256");
        owner2.setCity("Breukelen");
        owner2.setTelephone("234234234");

        Pet patsCat = new Pet();
        patsCat.setPetType(savedCatType);
        patsCat.setOwner(owner2);
        patsCat.setBirthDate(LocalDate.now());
        patsCat.setName("Poesie");
        owner2.getPets().add(patsCat);

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

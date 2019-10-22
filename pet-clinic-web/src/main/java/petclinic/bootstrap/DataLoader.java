package petclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import petclinic.model.*;
import petclinic.services.*;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if(count == 0){
            loadData();
        }

    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);

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

        Visit catVisit = new Visit();
        catVisit.setPet(patsCat);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("sneezing kitty");
        visitService.save(catVisit);

        System.out.println("Loaded owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Michael");
        vet1.setLastName("Keaton");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Tom");
        vet2.setLastName("Cruise");
        vet2.getSpecialities().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded vets....");
    }
}

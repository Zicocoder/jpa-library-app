package se.lexicon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;
import se.lexicon.entity.AppUser;
import se.lexicon.entity.Details;
import se.lexicon.repository.AppUserRepository;
import se.lexicon.repository.DetailsRepository;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDate.of;


@Component

public class AppCommandLineRunner implements CommandLineRunner {

   AppUserRepository appUserRepository;
   DetailsRepository detailsRepository;

   @Autowired
   public AppCommandLineRunner(AppUserRepository appUserRepository, DetailsRepository detailsRepository) {
       this.appUserRepository = appUserRepository;
       this.detailsRepository = detailsRepository;
   }

    @Override
    public void run(String... args) throws Exception {

        Details details =  detailsRepository.save(new Details("Azzoug@gmail.com", "Zack", of(1995, 03, 13)));
       appUserRepository.save(new AppUser("Zack", "123456", LocalDate.now(), details));
        System.out.println("Saved and good");


       Optional<AppUser> appUser = appUserRepository.findByUsername("Zack");
       appUser.ifPresent(System.out::println);
        LocalDate regDateAfter = LocalDate.of(2000, 01, 01);
        LocalDate regDateBefore = LocalDate.of(2026, 05, 01);
        List<AppUser> appUsers = appUserRepository.findByRegDateBetween(regDateBefore,regDateAfter);
        System.out.println(appUsers);

        List<AppUser> appUsers1 = appUserRepository.findByUserDetails_Id(1);
        System.out.println(appUsers1);

        Optional<AppUser> appUsers2 =appUserRepository.findByEmailIgnoreCase("Azzoug@gmail.com");
        appUsers2.ifPresent(System.out::println);

        Optional<Details> details1=detailsRepository.findByEmail("Azzoug@gmail.com");
        details1.ifPresent(System.out::println);

        List<Details> detailsList=detailsRepository.findByNameContains("Zack");
        System.out.println(detailsList);

        List<Details> detailsList1=detailsRepository.findByNameIgnoreCase("Zack");
        System.out.println(detailsList1);


    }

}

package se.lexicon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.entity.AppUser;
import se.lexicon.entity.Details;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppUserRepository  extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findByUsername(String username);
    List<AppUser> findByRegDateBetween(LocalDate regDateAfter, LocalDate regDateBefore);
    List<AppUser> findByUserDetails_Id(int userDetailsId);
          List<AppUser> findByUserDetailsEmailIgnoreCase(String email);
    @Query("SELECT u FROM AppUser u WHERE LOWER(u.userDetails.email) = LOWER(:email)")
    Optional<AppUser> findByEmailIgnoreCase(@Param("email") String email);
}

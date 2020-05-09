package pl.sgnit.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.sgnit.charity.model.Donation;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query(value = "select sum(d.quantity) from Donation d")
    Long sumQuantity();
}

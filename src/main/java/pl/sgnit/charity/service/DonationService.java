package pl.sgnit.charity.service;

import org.springframework.stereotype.Service;
import pl.sgnit.charity.model.Donation;
import pl.sgnit.charity.repository.DonationRepository;

@Service
public class DonationService {

    private final DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public Long countDonations() {
        return donationRepository.count();
    }

    public Long sumQuantity() {
        Long result;

        result = donationRepository.sumQuantity();
        return result == null ? 0l : result;
    }

    public void save(Donation donation) {
        donationRepository.save(donation);
    }
}

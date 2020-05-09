package pl.sgnit.charity.restcontroller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.sgnit.charity.model.Donation;
import pl.sgnit.charity.service.DonationService;

@RestController
public class DonationRestController {

    private final DonationService donationService;

    public DonationRestController(DonationService donationService) {
        this.donationService = donationService;
    }

    @PostMapping("/donation")
    public String saveDonation(@RequestBody Donation donation) {

        donationService.save(donation);
        return "OK";
    }
}

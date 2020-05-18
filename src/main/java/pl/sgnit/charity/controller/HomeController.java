package pl.sgnit.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sgnit.charity.service.DonationService;
import pl.sgnit.charity.service.InstitutionService;
import pl.sgnit.charity.util.EmailService;

@Controller
public class HomeController {

    private final DonationService donationService;
    private final InstitutionService institutionService;

    public HomeController(DonationService donationService, InstitutionService institutionService, EmailService emailService) {
        this.donationService = donationService;
        this.institutionService = institutionService;
    }

    @RequestMapping("/")
    public String homeAction(Model model){
        model.addAttribute("donationsCount", donationService.countDonations());
        model.addAttribute("sumQuantity", donationService.sumQuantity());
        model.addAttribute("allInstitution", institutionService.findAll());
        return "index";
    }
}

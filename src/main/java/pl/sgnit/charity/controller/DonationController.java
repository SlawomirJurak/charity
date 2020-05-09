package pl.sgnit.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sgnit.charity.model.Category;
import pl.sgnit.charity.model.Donation;
import pl.sgnit.charity.model.Institution;
import pl.sgnit.charity.service.CategoryService;
import pl.sgnit.charity.service.DonationService;
import pl.sgnit.charity.service.InstitutionService;

import java.util.List;

@Controller
public class DonationController {

    private final CategoryService categoryService;
    private final DonationService donationService;
    private final InstitutionService institutionService;

    public DonationController(CategoryService categoryService, DonationService donationService, InstitutionService institutionService) {
        this.categoryService = categoryService;
        this.donationService = donationService;
        this.institutionService = institutionService;
    }

    @GetMapping("/donation")
    public String initDonation(Model model) {
        List<Category> categories = categoryService.findAll();
        List<Institution> institutions = institutionService.findAll();
        Donation donation = new Donation();

        model.addAttribute("categories", categories);
        model.addAttribute("institutions", institutions);
        model.addAttribute("donation", donation);
        return "form";
    }

    @PostMapping("/donation/confirmation")
    public String confirmation() {
        return "form-confirmation";
    }
}

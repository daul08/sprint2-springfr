package bitlab.g1.sprint2.controller;

import bitlab.g1.sprint2.entity.ApplicationRequest;
import bitlab.g1.sprint2.service.ApplicationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ApplicationRequestService applicationRequestService;

    @GetMapping("/")
    public String homePage(Model model) {
        List<ApplicationRequest> applicationRequests = applicationRequestService.getApplicationRequests();
        model.addAttribute("zayavki", applicationRequests);
        return "home";
    }

    @GetMapping("/app-req/handled")
    public String handledApplicationRequests(Model model) {
        List<ApplicationRequest> applicationRequests = applicationRequestService.getHandledAppRequests();
        model.addAttribute("zayavki", applicationRequests);
        return "home";
    }

    @GetMapping("/app-req/notHandled")
    public String notHandledApplicationRequests(Model model) {
        List<ApplicationRequest> applicationRequests = applicationRequestService.getNotHandledAppRequests();
        model.addAttribute("zayavki", applicationRequests);
        return "home";
    }

    @GetMapping("/app-req/addApplication")
    public String addApplication() {
        return "addApplication";
    }

    @PostMapping("/app-req/addApplication")
    public String addApplication(ApplicationRequest applicationRequest) {
        var result = applicationRequestService.addApplication(applicationRequest);
        return "redirect:/";
    }

    @GetMapping("/app-req/{id}")
    public String detailsPage(@PathVariable Long id, Model model) {
        var result = applicationRequestService.getApplicationById(id);
        model.addAttribute("Application", result);
        return "detailsPage";
    }

    @PostMapping("/app-req/setHandled")
    public String setHandled(ApplicationRequest applicationRequest) {
        var result = applicationRequestService.setHandled(applicationRequest.getId());
        return "redirect:/app-req/" + applicationRequest.getId();
    }

}

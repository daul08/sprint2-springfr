package bitlab.g1.sprint2.service;

import bitlab.g1.sprint2.entity.ApplicationRequest;
import bitlab.g1.sprint2.repository.ApplicationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationRequestService {
    @Autowired
    private ApplicationRequestRepository applicationRequestRepository;

    public List<ApplicationRequest> getApplicationRequests() {
        return applicationRequestRepository.findAll();
    }

    public List<ApplicationRequest> getHandledAppRequests() {
        return applicationRequestRepository.findAll().stream()
                .filter(ApplicationRequest::isHandled)
                .toList();
    }

    public List<ApplicationRequest> getNotHandledAppRequests() {
        return applicationRequestRepository.findAll().stream()
                .filter(x -> !x.isHandled())
                .toList();
    }

    public ApplicationRequest addApplication(ApplicationRequest applicationRequest) {
        return applicationRequestRepository.save(applicationRequest);
    }

    public ApplicationRequest getApplicationById(Long id) {
        return applicationRequestRepository.getById(id);
    }
    public ApplicationRequest setHandled(Long id) {
        var result = getApplicationById(id);
        result.setHandled(true);
        return applicationRequestRepository.save(result);
    }

    public void deleteApplication(ApplicationRequest applicationRequest) {
        applicationRequestRepository.delete(applicationRequest);
    }
}

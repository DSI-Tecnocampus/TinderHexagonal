package cat.tecnocampus.tinder.application.service;

import cat.tecnocampus.tinder.application.dto.ProfileDTO;
import cat.tecnocampus.tinder.application.portIN.AddProfileUseCase;
import cat.tecnocampus.tinder.application.portOut.ProfileDAO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddProfileService implements AddProfileUseCase {

    private ProfileDAO profileDAO;

    public AddProfileService(ProfileDAO profileDAO) {
        this.profileDAO = profileDAO;
    }

    @Override
    public ProfileDTO addProfile(ProfileDTO profile) {
        profile.setId(UUID.randomUUID().toString());
        return profileDAO.addProfile(profile);
    }

}

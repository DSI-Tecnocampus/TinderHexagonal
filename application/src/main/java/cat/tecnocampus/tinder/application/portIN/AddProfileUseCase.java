package cat.tecnocampus.tinder.application.portIN;

import cat.tecnocampus.tinder.application.dto.ProfileDTO;

public interface AddProfileUseCase {
    ProfileDTO addProfile(ProfileDTO profile);
}

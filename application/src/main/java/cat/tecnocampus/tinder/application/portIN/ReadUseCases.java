package cat.tecnocampus.tinder.application.portIN;

import cat.tecnocampus.tinder.application.dto.ProfileDTO;

import java.util.List;

public interface ReadUseCases {
    ProfileDTO getProfileLazy(String id);

    List<ProfileDTO> getProfilesLazy();

    ProfileDTO getProfileEager(String id);

    ProfileDTO getProfileByNameEager(String name);

    List<ProfileDTO> getProfilesEager();

    List<ProfileDTO> getCandidates(String id);

    List<ProfileDTO> getCandidatesByName(String name);

}

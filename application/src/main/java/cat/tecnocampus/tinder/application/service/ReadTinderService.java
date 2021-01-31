package cat.tecnocampus.tinder.application.service;

import cat.tecnocampus.tinder.application.dto.ProfileDTO;
import cat.tecnocampus.tinder.application.portIN.ReadUseCases;
import cat.tecnocampus.tinder.application.portOut.ProfileDAO;
import cat.tecnocampus.tinder.domain.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReadTinderService implements ReadUseCases {
	private ProfileDAO profileDAO;

	public ReadTinderService(ProfileDAO profileDAO) {
		this.profileDAO = profileDAO;
	}

	@Override
	public ProfileDTO getProfileLazy(String id) {
		return profileDAO.getProfileLazy(id);
	}

	private ProfileDTO getProfileByNameLazy(String name) {
		return profileDAO.getProfileByNameLazy(name);
	}

	@Override
	public List<ProfileDTO> getProfilesLazy() {
		return profileDAO.getProfilesLazy();
	}

	@Override
	public ProfileDTO getProfileEager(String id) {
		return profileDAO.getProfile(id);
	}

	@Override
	public ProfileDTO getProfileByNameEager(String name) {
		return profileDAO.getProfileByName(name);
	}

	@Override
	public List<ProfileDTO> getProfilesEager() {
		return profileDAO.getProfiles();
	}

	@Override
	public List<ProfileDTO> getCandidates(String id) {
		ProfileDTO userDTO = this.getProfileLazy(id);
		return getProfileDTOS(userDTO);
	}

	@Override
	public List<ProfileDTO> getCandidatesByName(String name) {
		ProfileDTO userDTO = this.getProfileByNameLazy(name);
		return getProfileDTOS(userDTO);
	}

	private List<ProfileDTO> getProfileDTOS(ProfileDTO userDTO) {
		Profile user = DTOsMapper.profileDTOtoProfile(userDTO);
		return this.getProfilesLazy().stream()
				.map(DTOsMapper::profileDTOtoProfile)
				.filter(user::isCompatible)
				.map(DTOsMapper::profileToProfileDTO)
				.collect(Collectors.toList());
	}
}

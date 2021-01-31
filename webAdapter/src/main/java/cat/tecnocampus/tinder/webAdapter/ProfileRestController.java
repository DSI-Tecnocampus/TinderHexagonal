package cat.tecnocampus.tinder.webAdapter;

import cat.tecnocampus.tinder.application.portIN.AddProfileUseCase;
import cat.tecnocampus.tinder.application.portIN.NewLikesUseCase;
import cat.tecnocampus.tinder.application.portIN.ReadUseCases;
import cat.tecnocampus.tinder.application.service.AddProfileService;
import cat.tecnocampus.tinder.application.service.NewLikesService;
import cat.tecnocampus.tinder.webAdapter.frontendException.IncorrectRESTParameter;
import cat.tecnocampus.tinder.application.service.ReadTinderService;
import cat.tecnocampus.tinder.application.dto.ProfileDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ProfileRestController {

	private ReadUseCases readTinderUseCases;
	private NewLikesUseCase newLikesUseCases;
	private AddProfileUseCase addProfileUseCase;


	public ProfileRestController(ReadTinderService readTinderUseCases, NewLikesService newLikesUseCase, AddProfileService addProfileUseCase) {
		this.readTinderUseCases = readTinderUseCases;
		this.newLikesUseCases = newLikesUseCase;
		this.addProfileUseCase = addProfileUseCase;
	}

	@GetMapping("/profiles/{id}")
	public ProfileDTO getProfile(@PathVariable String id, @RequestParam(defaultValue = "lazy") String mode) throws Exception {
		ProfileDTO user;
		if (mode.equalsIgnoreCase("lazy"))
			user = readTinderUseCases.getProfileLazy(id);
		else {
			if (mode.equalsIgnoreCase("eager"))
				user = readTinderUseCases.getProfileEager(id);
			else throw new IncorrectRESTParameter("mode", mode);
		}
		return user;
	}

	@GetMapping("/profiles")
	public List<ProfileDTO> getProfiles(@RequestParam(defaultValue = "lazy") String mode) {
		if (mode.equalsIgnoreCase("lazy"))
			return readTinderUseCases.getProfilesLazy();
		else {
			if (mode.equalsIgnoreCase("eager"))
				return readTinderUseCases.getProfilesEager();
			else throw new IncorrectRESTParameter("mode", mode);
		}
	}

	@GetMapping("profiles/me")
	public ProfileDTO getMyProfile(Principal principal) {
		return readTinderUseCases.getProfileByNameEager(principal.getName());
	}

	//Returns profiles that match the user (id) preferences
	@GetMapping("profiles/{id}/candidates")
	public List<ProfileDTO> getCandidates(@PathVariable(name="id") String whatever) {
		return readTinderUseCases.getCandidates(whatever);
	}

	@GetMapping("profiles/me/candidates")
	public List<ProfileDTO> getMeCandidates(Principal principal) {
		return readTinderUseCases.getCandidatesByName(principal.getName());
	}

	@PostMapping("/profiles")
	public ProfileDTO addProfile(@RequestBody @Valid ProfileDTO profile) {
		return addProfileUseCase.addProfile(profile);
	}

	@PostMapping("/likes")
	public void addLikes(@RequestBody NewLikesUseCase.NewLikesCommand likes) {
		newLikesUseCases.newLikes(likes);
	}
}

package cat.tecnocampus.tinder.application.service;

import cat.tecnocampus.tinder.application.dto.ProfileDTO;
import cat.tecnocampus.tinder.application.portIN.NewLikesUseCase;
import cat.tecnocampus.tinder.application.portOut.ProfileDAO;
import cat.tecnocampus.tinder.domain.Like;
import cat.tecnocampus.tinder.domain.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewLikesService implements NewLikesUseCase {
    private ProfileDAO profileDAO;

    public NewLikesService(ProfileDAO profileDAO) {
        this.profileDAO = profileDAO;
    }

    @Override
    public int newLikes(NewLikesCommand newLikesCommand) {
        ProfileDTO originDTO = profileDAO.getProfile(newLikesCommand.getOrigin()); //check it exists in DDBB
        Profile origin = DTOsMapper.profileDTOtoProfile(originDTO);

        List<Like> likes =
                newLikesCommand.getTargets().stream()
                        .map(profileDAO::getProfile)    //check it exists in DDBB
                        .map(DTOsMapper::profileDTOtoProfile)    //convert to domain profile
                        .filter(origin::isCompatible)            //make sure it is compatible
                        .map(origin::createAndMatchLike)        //create likes
                        .collect(Collectors.toList());

        updateLikesPersistence(likes, newLikesCommand.getOrigin());
        return likes.size();
    }

    private void updateLikesPersistence(List<Like> likes, String originId) {
        //origin likes
        profileDAO.saveLikes(originId, likes);

        //target matched likes
        likes.stream().filter(l -> l.isMatched()).forEach(l -> profileDAO.updateLikeToMatch(l.getTarget().getId(), originId, l.getMatchDate()));
    }
}

package cat.tecnocampus.tinder.application.service;

import cat.tecnocampus.tinder.application.dto.LikeDTO;
import cat.tecnocampus.tinder.application.dto.ProfileDTO;
import cat.tecnocampus.tinder.domain.Like;
import cat.tecnocampus.tinder.domain.Profile;

import java.util.stream.Collectors;

public class DTOsMapper {

    public static Profile profileDTOtoProfile(ProfileDTO profileDTO) {
        Profile result = new Profile();
        result.setId(profileDTO.getId());
        result.setEmail(profileDTO.getEmail());
        result.setPassion(profileDTO.getPassion());
        result.setNickname(profileDTO.getNickname());
        result.setAttraction(profileDTO.getAttraction());
        result.setGender(profileDTO.getGender());
        result.setLikes(profileDTO.getLikes().stream().map(DTOsMapper::LikeDTOtoLike).collect(Collectors.toList()));

        return result;
    }

    public static ProfileDTO profileToProfileDTO(Profile profile) {
        ProfileDTO result = new ProfileDTO();
        result.setId(profile.getId());
        result.setEmail(profile.getEmail());
        result.setPassion(profile.getPassion());
        result.setNickname(profile.getNickname());
        result.setAttraction(profile.getAttraction());
        result.setGender(profile.getGender());
        result.setLikes(profile.getLikes().stream().map(DTOsMapper::LikeToLikeDTO).collect(Collectors.toList()));

        return result;
    }

    public static Like LikeDTOtoLike(LikeDTO likeDTO) {
        Like like = new Like();
        like.setTarget(profileDTOtoProfile(likeDTO.getTarget()));
        like.setCreationDate(likeDTO.getCreationDate());
        like.setMatchDate(likeDTO.getMatchDate());
        like.setMatched(likeDTO.isMatched());

        return like;
    }

    public static LikeDTO LikeToLikeDTO(Like like) {
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setTarget(profileToProfileDTO(like.getTarget()));
        likeDTO.setCreationDate(like.getCreationDate());
        likeDTO.setMatchDate(like.getMatchDate());
        likeDTO.setMatched(like.isMatched());

        return likeDTO;
    }

}

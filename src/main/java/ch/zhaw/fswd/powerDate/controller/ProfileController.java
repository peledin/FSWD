package ch.zhaw.fswd.powerDate.controller;

import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.zhaw.fswd.powerDate.dto.ProfileDTO;
import ch.zhaw.fswd.powerDate.entity.Profile;
import ch.zhaw.fswd.powerDate.entity.ProfileRepository;
import ch.zhaw.fswd.powerDate.entity.User;
import ch.zhaw.fswd.powerDate.entity.UserRepository;
import ch.zhaw.fswd.powerDate.exceptions.UUIDNotFoundException;
import ch.zhaw.fswd.powerDate.exceptions.UserNotFoundException;

@Service
public class ProfileController {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ProfileDTO> getPublicProfiles() {
        return profileRepository.findAll().stream().map(p -> new ProfileDTO(p)).toList();
    }

    public ProfileDTO getPublicProfile(String uuid) {
        return new ProfileDTO(
                profileRepository.findById(
                        UUID.fromString(uuid))
                        .orElseThrow(() -> new UUIDNotFoundException()));
    }

    public byte[] getPNGProfileImage(String uuid) {
        return Base64.getDecoder().decode(
                profileRepository.findById(UUID.fromString(uuid)).orElseThrow(
                        () -> new UUIDNotFoundException()).getRawPNGImageData()
                        .replaceAll("\r", "")
                        .replaceAll("\n", "")
                        .replaceAll(" ", "")
                );
    }

    public ProfileDTO getProfileByLoginName(String loginName) {
        User u = userRepository.findById(loginName).orElseThrow(() -> new UserNotFoundException());
        return new ProfileDTO(u.getProfile());
    }

    public void updateMyProfile(String loginName, ProfileDTO profileDTO) {
        User u = userRepository.findById(loginName).orElseThrow(() -> new UserNotFoundException());        
        Profile p = u.getProfile();
        profileDTO.updateEntity(p);
        profileRepository.save(p);
    }

}

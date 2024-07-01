package ch.zhaw.fswd.powerDate.boundary;

import java.security.Principal;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.fswd.powerDate.controller.ProfileController;
import ch.zhaw.fswd.powerDate.dto.ProfileDTO;

@RestController
@RequestMapping("/api/profile")
public class ProfileEndpointREST {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    private ProfileController profileController;

    @GetMapping
    public List<ProfileDTO> getAllProfiles() {
        return profileController.getPublicProfiles();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ProfileDTO> getProfile(@PathVariable String uuid) {
        try {
            return ResponseEntity.ok().body(profileController.getPublicProfile(uuid));
        } catch (Exception e) {
            logger.warning(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/me")
    public ResponseEntity<ProfileDTO> getMyProfile(Principal principal) {
        try {
            return ResponseEntity.ok().body(profileController.getProfileByLoginName(principal.getName()));
        } catch (Exception e) {
            logger.warning(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/me")
    public void updateMyProfile(Principal principal, @RequestBody ProfileDTO profile) {
        profileController.updateMyProfile(principal.getName(), profile);
    }


    @GetMapping("{uuid}/profileImage")
    public ResponseEntity<byte[]> getProfileImage(@PathVariable String uuid) {
        try {
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.IMAGE_PNG);
            return ResponseEntity.ok().headers(header).body(profileController.getPNGProfileImage(uuid));
        } catch (Exception e) {
            logger.warning(e.getStackTrace().toString());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

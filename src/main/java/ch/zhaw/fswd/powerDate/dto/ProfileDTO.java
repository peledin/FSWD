package ch.zhaw.fswd.powerDate.dto;

import java.util.UUID;

import ch.zhaw.fswd.powerDate.entity.EGender;
import ch.zhaw.fswd.powerDate.entity.Profile;
import lombok.Data;

@Data
public class ProfileDTO {
    private UUID uuid;
    private String displayName;
    private String age;
    private EGender gender;
    private String biography;
    private String location;
    private String occupation;
    private String interests;
    private String personalityTraits;
    private String lifestyle;

    public ProfileDTO() {

    }

    public ProfileDTO(Profile profile) {
        this.uuid = profile.getUuid();
        this.displayName = profile.getDisplayName();
        this.age = profile.getAge();
        this.gender = profile.getGender();
        this.biography = profile.getBiography();

        this.location = profile.getLocation();
        this.occupation = profile.getOccupation();
        this.interests = profile.getInterests();
        this.personalityTraits = profile.getPersonalityTraits();
        this.lifestyle = profile.getLifestyle();
    }

    public void updateEntity(Profile profile) {
        profile.setUuid(this.uuid);
        profile.setDisplayName(this.displayName);
        profile.setAge(this.age);
        profile.setGender(this.gender);
        profile.setBiography( this.biography);
        profile.setLocation(this.location);
        profile.setOccupation( this.occupation);
        profile.setInterests(this.interests);
        profile.setPersonalityTraits(this.personalityTraits);
        profile.setLifestyle(this.lifestyle);
    }
}

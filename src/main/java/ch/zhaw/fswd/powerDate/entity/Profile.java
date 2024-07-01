package ch.zhaw.fswd.powerDate.entity;

import java.util.UUID;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Data
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String displayName;
    private String age;
    @Enumerated(EnumType.STRING)
    private EGender gender;

    private String location;
    private String occupation; 
    private String biography;
    private String interests; 
    private String personalityTraits;
    private String lifestyle;

    @Lob
    @Basic
    @Column(name = "RAW_PNG_IMAGE_DATA", columnDefinition = "BLOB")
    private String rawPNGImageData;
    
}

package ch.zhaw.fswd.powerDate.entity;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, UUID>{
    
}

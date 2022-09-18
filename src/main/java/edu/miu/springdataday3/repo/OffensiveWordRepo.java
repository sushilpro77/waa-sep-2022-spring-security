package edu.miu.springdataday3.repo;

import edu.miu.springdataday3.aspect.offensive.OffensiveCount;
import edu.miu.springdataday3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OffensiveWordRepo extends JpaRepository<OffensiveCount, Long> {
    Optional<OffensiveCount> findByUser(User user);
}

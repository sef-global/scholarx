package org.sefglobal.scholarx.repository;

import org.sefglobal.scholarx.model.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface MentorRepository extends JpaRepository<Mentor, Long> {

    List<Mentor> findAllByProgramId(long id);
    Optional<Mentor> findByProfileIdAndProgramId(long profileId, long programId);

    @Modifying
    @Query(
            value = "DELETE " +
                    "FROM mentor " +
                    "WHERE program_id = :id",
            nativeQuery = true
    )
    void deleteByProgramId(long id);
}

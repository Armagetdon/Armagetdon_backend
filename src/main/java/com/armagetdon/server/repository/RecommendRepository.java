package com.armagetdon.server.repository;

import com.armagetdon.server.domain.Member;
import com.armagetdon.server.domain.Post;
import com.armagetdon.server.domain.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommendRepository extends JpaRepository<Recommend, Long> {
    Optional<Recommend> findByPostAndMember(Post post, Member member);

    Long countByPostAndIsActiveTrue(Post post);
}

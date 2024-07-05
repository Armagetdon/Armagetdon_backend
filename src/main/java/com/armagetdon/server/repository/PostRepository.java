package com.armagetdon.server.repository;

import com.armagetdon.server.domain.Member;
import com.armagetdon.server.domain.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p LEFT JOIN p.recommend r WHERE r.isActive = true GROUP BY p ORDER BY COUNT(r) DESC")
    List<Post> findTop5PostsByRecommendCount(Pageable pageable);

    List<Post> findAllByMember(Member member);

}

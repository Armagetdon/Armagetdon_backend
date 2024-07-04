package com.armagetdon.server.repository;

import com.armagetdon.server.domain.Member;
import com.armagetdon.server.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page<Post> findAllByMemberOrderByComplainDesc(Member member, Pageable pageable);
    List<Post> findTop5ByOrderByPostRecommendCountDesc();
}

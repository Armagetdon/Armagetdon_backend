package com.armagetdon.server.repository;

import com.armagetdon.server.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByNickname(String nickname);

    Page<Member> findAll(Pageable pageable);
}

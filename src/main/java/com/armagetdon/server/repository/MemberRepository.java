package com.armagetdon.server.repository;

import com.armagetdon.server.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.JavaBean;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}

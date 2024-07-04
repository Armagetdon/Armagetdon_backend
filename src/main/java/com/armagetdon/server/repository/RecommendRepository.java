package com.armagetdon.server.repository;

import com.armagetdon.server.domain.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendRepository extends JpaRepository<Recommend, Long> {
}

package com.armagetdon.server.repository;

import com.armagetdon.server.domain.Complain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplainRepository extends JpaRepository<Complain, Long> {

}

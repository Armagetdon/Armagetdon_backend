package com.armagetdon.server.repository;

import com.armagetdon.server.domain.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<PostImage, Long> {
}

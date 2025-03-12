package org.wild.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wild.myblog.model.Image;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

}

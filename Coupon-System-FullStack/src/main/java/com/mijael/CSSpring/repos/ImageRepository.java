package com.mijael.CSSpring.repos;

import com.mijael.CSSpring.beans.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Integer> {
}

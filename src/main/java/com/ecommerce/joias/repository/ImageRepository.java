package com.ecommerce.joias.repository;

import com.ecommerce.joias.entity.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    void deleteByParentIdAndParentType(Integer parentId, String parentType);

    Page<Image> findAllByParentIdAndParentType(Integer parentId, String parentType, Pageable pageable);
}

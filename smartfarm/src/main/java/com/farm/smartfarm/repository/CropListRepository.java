package com.farm.smartfarm.repository;

import com.farm.smartfarm.domain.CropList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropListRepository extends JpaRepository<CropList, Long> {
}

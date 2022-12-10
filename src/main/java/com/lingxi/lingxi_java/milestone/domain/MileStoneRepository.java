package com.lingxi.lingxi_java.milestone.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MileStoneRepository  extends JpaRepository<MileStone, Long> {

}

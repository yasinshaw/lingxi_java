package com.lingxi.lingxi_java.milestone.application;

import com.lingxi.lingxi_java.milestone.MileStoneMapper;
import com.lingxi.lingxi_java.milestone.domain.MileStoneRepository;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MileStoneQueryService {
    @Resource
    private MileStoneRepository mileStoneRepository;

    public MileStoneResponse queryById(Long id) {
        return mileStoneRepository.findById(id).map(MileStoneMapper.mapper::mileStoneEntity2Response).orElse(null);
    }

    public Page<MileStoneResponse> queryByList(Pageable pageable) {
        return mileStoneRepository.findAll(pageable).map(MileStoneMapper.mapper::mileStoneEntity2Response);
    }

}

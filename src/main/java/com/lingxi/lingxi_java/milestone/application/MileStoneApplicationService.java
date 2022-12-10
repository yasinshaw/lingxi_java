package com.lingxi.lingxi_java.milestone.application;

import com.lingxi.lingxi_java.common.ResponseCode;
import com.lingxi.lingxi_java.common.exceptions.BizException;
import com.lingxi.lingxi_java.milestone.domain.MileStone;
import com.lingxi.lingxi_java.milestone.domain.MileStoneRepository;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MileStoneApplicationService {

    @Resource
    private MileStoneRepository mileStoneRepository;

    @Transactional
    public Long createMileStone(CreateMileStoneRequest request) {
        MileStone mileStone = MileStone.create(request.getTitle(), request.getDescription(), request.getTime(), request.getPicUrls(), request.getPortalPublic());
        return mileStoneRepository.save(mileStone).getId();
    }

    @Transactional
    public void updateMileStone(UpdateMileStoneRequest request) {
        MileStone mileStone = mileStoneRepository.findById(request.getId()).orElseThrow(() -> new BizException(ResponseCode.MILESTONE_NOT_EXIST));
        mileStone.update(request.getTitle(), request.getDescription(), request.getTime(), request.getPicUrls(), request.getPortalPublic());
        mileStoneRepository.save(mileStone);
    }

    @Transactional
    public void deleteMileStone(Long id) {
        mileStoneRepository.deleteById(id);
    }
}

package com.lingxi.lingxi_java.milestone.gateway.north;

import com.lingxi.lingxi_java.milestone.application.*;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/milestone")
public class MileStoneController {
    @Resource
    private MileStoneQueryService mileStoneQueryService;
    @Resource
    private MileStoneApplicationService mileStoneApplicationService;

    @PostMapping("/create")
    public Long createMileStone(CreateMileStoneRequest request) {
        return mileStoneApplicationService.createMileStone(request);
    }

    @PostMapping("/update")
    public void updateMileStone(UpdateMileStoneRequest request) {
        mileStoneApplicationService.updateMileStone(request);
    }

    @PostMapping("/delete")
    public void deleteMileStone(Long id) {
        mileStoneApplicationService.deleteMileStone(id);
    }

    @PostMapping("/queryById")
    public MileStoneResponse queryById(Long id) {
        return mileStoneQueryService.queryById(id);
    }

    @PostMapping("/queryByList")
    public Page<MileStoneResponse> queryByList(Pageable pageable) {
        return mileStoneQueryService.queryByList(pageable);
    }

}

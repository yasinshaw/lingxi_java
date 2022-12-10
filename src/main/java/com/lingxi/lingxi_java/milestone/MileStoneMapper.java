package com.lingxi.lingxi_java.milestone;

import com.lingxi.lingxi_java.milestone.application.MileStoneResponse;
import com.lingxi.lingxi_java.milestone.domain.MileStone;
import org.mapstruct.factory.Mappers;

public interface MileStoneMapper {
    MileStoneMapper mapper
            = Mappers.getMapper(MileStoneMapper.class);

    MileStoneResponse mileStoneEntity2Response(MileStone mileStone);
}

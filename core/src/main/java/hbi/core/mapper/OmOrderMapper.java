package hbi.core.mapper;

import com.hand.hap.mybatis.common.Mapper;
import hbi.core.dto.OmOrder;

import java.util.List;

public interface OmOrderMapper extends Mapper<OmOrder> {
    List<OmOrder> selectOnCondition(OmOrder dto);
}

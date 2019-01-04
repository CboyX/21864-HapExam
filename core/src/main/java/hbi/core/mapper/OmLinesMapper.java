package hbi.core.mapper;

import com.hand.hap.mybatis.common.Mapper;
import hbi.core.dto.OmLines;

import java.util.List;

public interface OmLinesMapper extends Mapper<OmLines> {
    List<OmLines> selectLines(OmLines condition);
}

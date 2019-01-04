package hbi.core.mapper;

import com.hand.hap.mybatis.common.Mapper;
import hbi.core.dto.OrgCompanys;

import java.util.List;

public interface OrgCompanysMapper extends Mapper<OrgCompanys>{
    List<OrgCompanys> selectLov(OrgCompanys dto);
}
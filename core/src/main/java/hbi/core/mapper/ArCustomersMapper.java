package hbi.core.mapper;

import com.hand.hap.mybatis.common.Mapper;
import hbi.core.dto.ArCustomers;

import java.util.List;

public interface ArCustomersMapper extends Mapper<ArCustomers>{

    List<ArCustomers> selectLov(ArCustomers dto);
}
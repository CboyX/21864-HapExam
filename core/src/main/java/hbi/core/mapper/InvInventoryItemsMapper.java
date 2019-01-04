package hbi.core.mapper;

import com.hand.hap.mybatis.common.Mapper;
import hbi.core.dto.InvInventoryItems;

import java.util.List;

public interface InvInventoryItemsMapper extends Mapper<InvInventoryItems>{

    List<InvInventoryItems> selectLov(InvInventoryItems dto);
}
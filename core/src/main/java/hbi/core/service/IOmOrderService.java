package hbi.core.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hbi.core.dto.OmOrder;
import org.apache.poi.ss.formula.functions.Irr;

import java.util.List;


public interface IOmOrderService extends IBaseService<OmOrder>,ProxySelf<IOmOrderService> {
    List<OmOrder> selectOnCondition(IRequest request, OmOrder dto, int pageNum, int pageSize);//头条件查询
    OmOrder createHead(OmOrder head);
    OmOrder updateHead(OmOrder head);
    int omDelete(OmOrder dto);//头行删除
}

package hbi.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
/*import hbi.core.om.dto.OmLines;
import hbi.core.om.dto.OmOrder;
import hbi.core.om.dto.OmOrderHeaders;
import hbi.core.om.dto.OmOrderLines;
import hbi.core.om.mapper.OmLinesMapper;
import hbi.core.om.mapper.OmOrderHeadersMapper;
import hbi.core.om.mapper.OmOrderLinesMapper;
import hbi.core.om.mapper.OmOrderMapper;
import hbi.core.om.service.IOmOrderService;*/

import hbi.core.dto.OmLines;
import hbi.core.dto.OmOrder;
import hbi.core.dto.OmOrderHeaders;
import hbi.core.dto.OmOrderLines;
import hbi.core.mapper.OmLinesMapper;
import hbi.core.mapper.OmOrderHeadersMapper;
import hbi.core.mapper.OmOrderLinesMapper;
import hbi.core.mapper.OmOrderMapper;
import hbi.core.service.IOmOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * @author:yanghang
 *
 *
 *
 * */
@Service
@Transactional(rollbackFor = Exception.class)
public class OmOrderServiceImpl extends BaseServiceImpl<OmOrder> implements IOmOrderService {
    private OmOrderMapper omOrderMapper;
    private OmOrderLinesMapper omOrderLinesMapper;
    private OmOrderHeadersMapper omOrderHeadersMapper;
    private OmLinesMapper omLinesMapper;
    @Autowired
    public void setOmOrderMapper(OmOrderMapper omOrderMapper) {
        this.omOrderMapper = omOrderMapper;
    }
    @Autowired
    public void setOmOrderLinesMapper(OmOrderLinesMapper omOrderLinesMapper) {
        this.omOrderLinesMapper = omOrderLinesMapper;
    }
    @Autowired
    public void setOmOrderHeadersMapper(OmOrderHeadersMapper omOrderHeadersMapper) {
        this.omOrderHeadersMapper = omOrderHeadersMapper;
    }
    @Autowired
    public void setOmLinesMapper(OmLinesMapper omLinesMapper) {
        this.omLinesMapper = omLinesMapper;
    }

    @Override
    public List<OmOrder> selectOnCondition(IRequest request, OmOrder dto, int pageNum, int pageSize) {
        OmLines condition = new OmLines();
        System.out.println(pageNum);
        System.out.println(pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<OmOrder> orderList = omOrderMapper.selectOnCondition(dto);

        for(OmOrder elementOrder:orderList){
            condition.setHeaderId(elementOrder.getHeaderId());
            List<OmLines> linesList=omLinesMapper.selectLines(condition);
            double tmpTotalBill=0;
            for(OmLines elementLine:linesList){
                tmpTotalBill+=elementLine.getOrderdQuantity()*elementLine.getUnitSellingPrice();
            }
            elementOrder.setTotalBill(tmpTotalBill);
        }
        //填充金额
        return orderList;
    }

    @Override
    public int omDelete(OmOrder dto) {
        OmOrderHeaders header=omOrderHeadersMapper.selectByPrimaryKey(dto.getHeaderId());
        if(header==null){
            return -1;
        }
        //删除行
        OmOrderLines condition=new OmOrderLines();
        condition.setHeaderId(header.getHeaderId());
        omOrderLinesMapper.delete(condition);
        //删除头
        omOrderHeadersMapper.delete(header);
        return 0;
    }

    @Override
    public List<OmOrder>batchUpdate(IRequest request, List<OmOrder>list) {
        for (OmOrder head : list) {
            if (head.getHeaderId()==null) {
                this.createHead(head);
            } else {
                this.updateHead(head);
            }
        }
        return list;
    }
    @Override
    public int batchDelete(List<OmOrder> list){
        for(OmOrder order:list){
            for(OmOrderLines element :order.getLines()){
                omOrderLinesMapper.deleteByPrimaryKey(element);
            }
        }
        return 1;
    }

    @Override
    public OmOrder createHead(OmOrder head) {

        omOrderHeadersMapper.insertSelective(omOrderToHeader(head));
        //拿到生成的headerId
        head.setHeaderId(omOrderHeadersMapper.select(omOrderToHeader(head)).get(0).getHeaderId());
        if (head.getLines() != null) {
            this.processLines(head);
        }
        return head;
    }

    @Override
    public OmOrder updateHead(OmOrder head) {
        omOrderHeadersMapper.updateByPrimaryKey(omOrderToHeader(head));
        if (head.getLines() != null) {
            this.processLines(head);
        }
        return head;
    }

    private void processLines(OmOrder head) {
        for (OmOrderLines line : head.getLines()) {
            if (line.getLineId() == null) {
                line.setHeaderId(head.getHeaderId());
                omOrderLinesMapper.insertSelective(line);
            } else {
                omOrderLinesMapper.updateByPrimaryKey(line);
            }
        }
    }
    private OmOrderHeaders omOrderToHeader(OmOrder head){
        OmOrderHeaders omOrderHeaders=new OmOrderHeaders();
        omOrderHeaders.setHeaderId(head.getHeaderId());
        omOrderHeaders.setCompanyId(head.getCompanyId());
        omOrderHeaders.setCustomerId(head.getCustomerId());
        omOrderHeaders.setOrderNumber(head.getOrderNumber());
        omOrderHeaders.setOrderDate(head.getOrderDate());
        omOrderHeaders.setOrderStatus(head.getOrderStatus());
        return  omOrderHeaders;
    }


}

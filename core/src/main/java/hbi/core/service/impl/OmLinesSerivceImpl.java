package hbi.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;

import hbi.core.dto.OmLines;
import hbi.core.mapper.OmLinesMapper;
import hbi.core.service.IOmLinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class OmLinesSerivceImpl extends BaseServiceImpl<OmLines> implements IOmLinesService {
    @Autowired
    private OmLinesMapper omLinesMapper;
    @Override
    public List<OmLines> selectLines(IRequest request, OmLines condition, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<OmLines> list = omLinesMapper.selectLines(condition);
            for (OmLines item : list) {
            item.setBill(item.getUnitSellingPrice() * item.getOrderdQuantity());
        }
        return list;
    }
}

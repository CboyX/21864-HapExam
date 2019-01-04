package hbi.core.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hbi.core.dto.OmLines;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IOmLinesService extends IBaseService<OmLines>,ProxySelf<IOmLinesService> {
    List<OmLines> selectLines(IRequest request, OmLines condition, int pageNum, int pageSize);//
}

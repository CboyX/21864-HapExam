package hbi.core.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import hbi.core.dto.OmLines;
import hbi.core.dto.OmOrderLines;
import hbi.core.service.IOmLinesService;
import hbi.core.service.IOmOrderLinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 */
@Controller
public class OmLinesController extends BaseController {
    @Autowired
    IOmLinesService service;
    @Autowired
    IOmOrderLinesService linesService;

    /**
     *
     * @param dto
     * @param page
     * @param pageSize
     * @param request
     * @return
     */
    @RequestMapping(value = "hap/om/order/linesforOm")
    @ResponseBody
    public ResponseData linesQuery(OmLines dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                   @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        //页码操作
        IRequest requestContext = createRequestContext(request);
        return new ResponseData((service.selectLines(requestContext, dto, page, pageSize)));
    }

    /**
     *
     * @param request
     * @param dtos
     * @return
     */
    @RequestMapping(value = "/hap/om/order/removeLines")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<OmOrderLines> dtos){
        linesService.batchDelete(dtos);
        return new ResponseData();
    }

}



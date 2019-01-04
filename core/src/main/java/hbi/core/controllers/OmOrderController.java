package hbi.core.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import hbi.core.dto.OmOrder;
import hbi.core.service.IOmOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class OmOrderController extends BaseController {
    @Autowired
    IOmOrderService service;

    @RequestMapping(value = "/hap/om/order/query")
    @ResponseBody
    public ResponseData query(OmOrder dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.selectOnCondition(requestContext, dto, page, pageSize));
    }

    @RequestMapping(value = "/hap/om/order/submit")
    @ResponseBody
    public ResponseData submit(@RequestBody List<OmOrder> dtos, BindingResult result, HttpServletRequest request) {
        getValidator().validate(dtos, result);
        if (result.hasErrors()) {
            ResponseData responseData = new ResponseData(false);
            responseData.setMessage(getErrorMessage(result, request));
            return responseData;
        }
        IRequest requestContext = this.createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestContext, dtos));
    }

    @RequestMapping(value = "/hap/om/order/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<OmOrder> dtos){
        service.batchDelete(dtos);
        return new ResponseData();
    }

    @RequestMapping(value = "/hap/om/order/removeOm")
    @ResponseBody
    public ResponseData deleteOm(HttpServletRequest request,OmOrder dto){
        //判断header合法性
        if(dto.getHeaderId()!=null){
            service.omDelete(dto);
        }
        return new ResponseData();
    }

}



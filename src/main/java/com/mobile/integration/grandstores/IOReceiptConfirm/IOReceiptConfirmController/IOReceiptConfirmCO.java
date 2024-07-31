package com.mobile.integration.grandstores.IOReceiptConfirm.IOReceiptConfirmController;
import java.text.ParseException;

import com.mobile.integration.grandstores.IOReceiptConfirm.IOReceiptConfirmEntity.IOReceiptConfirmEO;
import com.mobile.integration.grandstores.IOReceiptConfirm.IOReceiptConfirmService.IOReceiptConfirmSO;
import com.mobile.integration.grandstores.RMAConfirm.RMAConfirmController.RMAConfirmCO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/module/io/receiptconfirm")
@Api(
    tags = {"IO Receipt Confirm"}, 
    description = "Grandstores-Bin Transfer", 
    // value = "HRMS Module", 
    produces = "application/json"
    )
public class IOReceiptConfirmCO {
 

    @Autowired
    private IOReceiptConfirmSO ioReceiptConfirmSO;  
    private static final Logger logger = LoggerFactory.getLogger(IOReceiptConfirmCO.class); 


    //Insert PI Count Services
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> insertBinTransfer(@RequestBody Iterable<IOReceiptConfirmEO> bodydtl) throws ParseException{
        logger.debug("Entering the insertBinTransfer method; "+bodydtl);
        return ioReceiptConfirmSO.insertReceiptConfirm(bodydtl);
    }

    //Get PI Count Services
    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    public ResponseEntity<APIResponse> getBinTransfer()throws ParseException{
        return ioReceiptConfirmSO.getReceiptConfirm();
    }



}
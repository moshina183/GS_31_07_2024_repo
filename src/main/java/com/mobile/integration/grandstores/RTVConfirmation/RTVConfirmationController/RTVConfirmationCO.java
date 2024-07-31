package com.mobile.integration.grandstores.RTVConfirmation.RTVConfirmationController;
import java.text.ParseException;
import java.util.Map;

import com.mobile.integration.grandstores.RTVConfirmation.RTVConfirmationEntity.RTVConfirmationEO;
import com.mobile.integration.grandstores.RTVConfirmation.RTVConfirmationService.RTVConfirmationSO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/module/rtvconfirmation")
@Api(
    tags = {"RTV Confirmation"}, 
    description = "RTV Confirmation", 
    // value = "HRMS Module", 
    produces = "application/json"
    )
public class RTVConfirmationCO {
 

    @Autowired
    private RTVConfirmationSO rtvconfirmationso;   


    //insertRTVConfirmation
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> insertRTVConfirmation(@RequestBody Iterable<RTVConfirmationEO> bodydtl) throws ParseException{
        System.out.println("hello RTV CO: ");
        return rtvconfirmationso.insertRTVConfirmation(bodydtl);
    }

    //UPDATE_RTV_REQ_LINES
     @RequestMapping(value = "/updatertvreqlines", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> UPDATE_RTV_REQ_LINES(@RequestBody Iterable<RTVConfirmationEO> bodydtl) throws ParseException{
            
            System.out.println("inside UPDATE_RTV_REQ_LINES co");
            return rtvconfirmationso.updateRtvReqLines(bodydtl);
     }

}
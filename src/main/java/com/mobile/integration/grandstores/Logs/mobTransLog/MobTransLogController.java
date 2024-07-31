package com.mobile.integration.grandstores.Logs.mobTransLog;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.mobile.integration.grandstores.IOReceiptConfirm.IOReceiptConfirmEntity.IOReceiptConfirmEO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@RestController
@RequestMapping(value = "/module/logs/")
@Api(
    tags = {"Mobile Transaction Logs"}, 
    description = "Grandstores-Mobile Transaction Logs",
    produces = "application/json"
    )
public class MobTransLogController {

    @Autowired
    MobTransLogSO mobTransLogSO;

    //Insert Mob Logs
    @RequestMapping(value = "/insertMobLogs", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> insertBinTransfer(@RequestBody Iterable<MobTransLogEO> bodydtl) throws ParseException{
        return mobTransLogSO.insertMobLogs(bodydtl);
    }

    public void insertData(MobTransLogEO mobTransLog){
        mobTransLogSO.insertData(mobTransLog);

    }

    

    //Get PI Count Services
    @RequestMapping(value = "/getAllLogs", method = RequestMethod.GET)
    public ResponseEntity<APIResponse> getBinTransfer()throws ParseException{
        System.out.println("he controll");
        return mobTransLogSO.getAllLogs();
    }
    

   /* @GetMapping("/getLogsById/{id}")
    public MobTransLogEO getLogsById(@PathVariable BigDecimal id) {
        System.out.println("inside the getLogsById"+id);
        int c = mobTransLogRO.recordCountt(id.toString());
        System.out.println("count: "+c);
        MobTransLogEO logResult = null;
        int size = mobTransLogRO.findByLogId(id).size();
        System.out.println("size: "+size);
        return logResult;
    }*/
    
    
}

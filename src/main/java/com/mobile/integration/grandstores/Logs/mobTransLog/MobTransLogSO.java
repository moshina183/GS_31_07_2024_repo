package com.mobile.integration.grandstores.Logs.mobTransLog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mobile.integration.grandstores.IOReceiptConfirm.IOReceiptConfirmEntity.IOReceiptConfirmEO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

@Service
public class MobTransLogSO {

    @Autowired
    MobTransLogRO mobTransLogRo;


    public ResponseEntity<APIResponse> getAllLogs() {
        List<MobTransLogEO> ls=mobTransLogRo.findAll();
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);    
    }

    public ResponseEntity<APIResponse> insertMobLogs(Iterable<MobTransLogEO> bodydtl) {
        Iterable<MobTransLogEO> ls=mobTransLogRo.saveAll(bodydtl);
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
    }

    public void insertData(MobTransLogEO mobTransLog) {
        System.out.println("inside insert log data");
        mobTransLogRo.save(mobTransLog);
    }
    
}

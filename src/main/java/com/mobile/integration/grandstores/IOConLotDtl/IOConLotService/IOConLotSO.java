package com.mobile.integration.grandstores.IOConLotDtl.IOConLotService;
// import java.util.Map;
import java.util.List;

import com.mobile.integration.grandstores.IOConLotDtl.IOConLotEntity.IOConLotEO;
import com.mobile.integration.grandstores.IOConLotDtl.IOConLotRepository.IOConLotRO;


import com.mobile.integration.grandstores.Utils.Generic.DateUtils;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class IOConLotSO {

    @Autowired
    private IOConLotRO ioConLotRO;

    @Autowired
    private DateUtils dateUtils;

    public ResponseEntity<APIResponse> insertIOConLot(Iterable<IOConLotEO> bodydtl) {
        //------------------ 
        // for( PiLotDetailsEO element : bodyCountDetail ){
        //     System.out.println("1===>"+element.getLotExpiryDate() );
        //     System.out.println("2===>"+element.getCreationDate() );
        //     System.out.println("3===>"+element.getLastUpdateDate() );
        // }
        //------------------
        Iterable<IOConLotEO> ls=ioConLotRO.saveAll(bodydtl);
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
    }

    public ResponseEntity<APIResponse> getIOConLot() {
        List<IOConLotEO> ls=ioConLotRO.findAll();
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);    
    }
    
}
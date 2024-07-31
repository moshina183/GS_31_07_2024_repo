package com.mobile.integration.grandstores.InvCountConfirm.InvCountConfirmService;
// import java.util.Map;
import java.util.List;

import com.mobile.integration.grandstores.InvCountConfirm.InvCountConfirmEntity.InvCountConfirmEO;
import com.mobile.integration.grandstores.InvCountConfirm.InvCountConfirmRepository.InvCountConfirmRO;
import com.mobile.integration.grandstores.Utils.Generic.DateUtils;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InvCountConfirmSO {

    @Autowired
    private InvCountConfirmRO invCountConfirmRo;

    @Autowired
    private DateUtils dateUtils;

    public ResponseEntity<APIResponse> insertInvCountConfirm(Iterable<InvCountConfirmEO> bodydtl) {
        //------------------ 
        // for( PiLotDetailsEO element : bodyCountDetail ){
        //     System.out.println("1===>"+element.getLotExpiryDate() );
        //     System.out.println("2===>"+element.getCreationDate() );
        //     System.out.println("3===>"+element.getLastUpdateDate() );
        // }
        //------------------
        System.out.println("==>+");
        Iterable<InvCountConfirmEO> ls=invCountConfirmRo.saveAll(bodydtl);
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
    }

    public ResponseEntity<APIResponse> getinvCountConfirm() {
        List<InvCountConfirmEO> ls=invCountConfirmRo.findAll();
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);    
    }
    
}
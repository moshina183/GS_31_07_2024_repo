package com.mobile.integration.grandstores.BinTransfer.BinTransferService;

import java.util.Map;

import com.mobile.integration.grandstores.PackageCalling.BinTransferPackage;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;


@Service
public class BinTransferSO extends NamedParameterJdbcDaoSupport {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(JdbcTemplate jdbcTemplate) {
        super.setDataSource(jdbcTemplate.getDataSource());
    }
 
    @Autowired
    private BinTransferPackage binTransferPkg;

    // GET_ITEM_DETAILS Services
    public ResponseEntity<APIResponse> getItemDetail(Map<String, Object> content) {
        String P_ORGANIZATION_CODE=content.get("P_ORGANIZATION_CODE")==null?"":content.get("P_ORGANIZATION_CODE").toString();
        String P_DEPARTMENT=content.get("P_DEPARTMENT")==null?"":content.get("P_DEPARTMENT").toString();
        String P_SUB_INV_CODE=content.get("P_SUB_INV_CODE")==null?"":content.get("P_SUB_INV_CODE").toString();
        String P_WITH_LOT_FLAG=content.get("P_WITH_LOT_FLAG")==null?"":content.get("P_WITH_LOT_FLAG").toString();
    
        Map<String, Object> ls=binTransferPkg.GET_ITEM_DETAILS(P_ORGANIZATION_CODE, P_DEPARTMENT, P_SUB_INV_CODE, P_WITH_LOT_FLAG);

        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
    }  

        // GET_ITEM_CROSS_REF Services
    public ResponseEntity<APIResponse> getItemCrossref(Map<String, Object> content) {
        String P_ORGANIZATION_CODE=content.get("P_ORGANIZATION_CODE")==null?"":content.get("P_ORGANIZATION_CODE").toString();
        String P_DEPARTMENT=content.get("P_DEPARTMENT")==null?"":content.get("P_DEPARTMENT").toString();
        String P_SUB_INV_CODE=content.get("P_SUB_INV_CODE")==null?"":content.get("P_SUB_INV_CODE").toString();
    
        Map<String, Object> ls=binTransferPkg.GET_ITEM_CROSSREF_DTLS(P_ORGANIZATION_CODE, P_DEPARTMENT, P_SUB_INV_CODE);

        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
    }
}
package com.mobile.integration.grandstores.ReturnToVendor.ReturntoVendorController;

import java.text.ParseException;
import java.util.Map;

import com.mobile.integration.grandstores.IOReceipt.IORecService.IOReceiptSO;
import com.mobile.integration.grandstores.ReturnToVendor.ReturntoVendorService.ReturnVendorSO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/module/returnvendor/")
@Api(
    tags = {"Return To Vendor Services"}, 
    description = "Return To Vendor Services Grandstores-WMS Integration with LCM & Mobile App", 
    // value = "HRMS Module", 
    produces = "application/json"
    )
public class ReturnVendorCO {

    @Autowired
    private ReturnVendorSO returnVendorSO;


        //PO Receipt Details for getRTVReceiptNumber
        @RequestMapping(value = "/rtvreceiptnumber", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> getRTVReceiptNumber(@RequestBody  Map<String, Object> content) throws ParseException{
            return returnVendorSO.getRTVReceiptNumber(content);
        }


      //Get PO Shipment Details Services
      @RequestMapping(value = "/rtvshipmentNumber", method = RequestMethod.POST)
      public ResponseEntity<APIResponse> getRTVShipmentNumber(@RequestBody  Map<String, Object> content) throws ParseException{
          return returnVendorSO.getRTVShipmentNumber(content);
      }


      //getRTVPONumber Services
      @RequestMapping(value = "/rtvponumber", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> getRTVPONumber(@RequestBody  Map<String, Object> content) throws ParseException{
            return returnVendorSO.getRTVPONumber(content);
      }


       //Get getRTVReleaseNum
       @RequestMapping(value = "/rtvreleasenum", method = RequestMethod.POST)
       public ResponseEntity<APIResponse> getRTVReleaseNum(@RequestBody  Map<String, Object> content) throws ParseException{
           return returnVendorSO.getRTVRelease_num(content);
       }


        //getRtvItemDetail Services
        @RequestMapping(value = "/rtvitemdetail", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> getRtvItemDetail(@RequestBody  Map<String, Object> content) throws ParseException{
            return returnVendorSO.getRtvItemDetail(content);
        }

        //getRtvItemDetailCr Services
        @RequestMapping(value = "/rtvitemdetailcr", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> getRtvItemDetailCr(@RequestBody  Map<String, Object> content) throws ParseException{
            return returnVendorSO.getRtvItemDetailCr(content);
        }

                //get_rtv_item_code Services
        @RequestMapping(value = "/getrtvitemcode", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> get_rtv_item_code(@RequestBody  Map<String, Object> content) throws ParseException{
            return returnVendorSO.get_rtv_item_code(content);
        }

        
}

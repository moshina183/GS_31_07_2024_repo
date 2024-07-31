package com.mobile.integration.grandstores.IOReceiptConfirm.IOReceiptConfirmService;
import java.util.ArrayList;
import java.util.Iterator;
// import java.util.Map;
import java.util.List;

import com.mobile.integration.grandstores.IOReceiptConfirm.IOReceiptConfirmEntity.IOReceiptConfirmEO;
import com.mobile.integration.grandstores.IOReceiptConfirm.IOReceiptConfirmRepository.IOReceiptConfirmRO;

import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class IOReceiptConfirmSO {

    @Autowired
    private IOReceiptConfirmRO ioReceiptConfirmro;


    public ResponseEntity<APIResponse> insertReceiptConfirm(Iterable<IOReceiptConfirmEO> bodydtl) {
        //DUPALL IO receipt: Shipment Number: IS518-10229
        List<String> myList = new ArrayList<>();
        String comb = "";
        List<IOReceiptConfirmEO> listFromIterator = new ArrayList<>();
        Iterator<IOReceiptConfirmEO> iterator1 = bodydtl.iterator();
        while (iterator1.hasNext()) {
            IOReceiptConfirmEO current = iterator1.next();
            System.out.println("current.getTransactionType().toString(): "+current.getTransactionType().toString());
            if(current.getTransactionType().toString().equalsIgnoreCase("IO_RECEIPT")){
                comb = current.getLineNum().toString() +
                    "-" +current.getItemId().toString()+ 
                    "-" +current.getShipmentHeaderId().toString()+
                    "-" +current.getStatus().toString()+    
                    "-" +current.getShipmentLineId().toString();
                System.out.println("inside the iterator: "+comb);
                if(myList.contains(comb)){
                    iterator1.remove();
                    System.out.println("removed");
                }
                else{
                    System.out.println("else part");
                    int countD = recordCheckforIoReceipt(current.getLineNum().toString(), 
                                            current.getItemId().toString(), current.getShipmentHeaderId().toString(),
                                            current.getStatus().toString(), 
                                            current.getShipmentLineId().toString());
                    System.out.println("countD: "+countD);
                    if(countD == 0){
                        System.out.println("inside the countD = 0");
                        listFromIterator.add(current);
                        myList.add(comb);
                    }
                }
                  
            }else if(current.getTransactionType().toString().equalsIgnoreCase("IO_DELIVERY")){
                comb = current.getLineNum().toString() +
                    "-" +current.getItemId().toString()+ 
                    "-" +current.getShipmentHeaderId().toString()+
                    "-" +current.getReceiptNum().toString()+
                    "-" +current.getStatus().toString()+    
                    "-" +current.getDelivLocator().toString()+
                    "-" +current.getShipmentLineId().toString();
                System.out.println("inside the iterator: "+comb);
                if(myList.contains(comb)){
                    iterator1.remove();
                    System.out.println("removed");
                }
                else{
                    System.out.println("else part");
                    int countD = recordCheckforIoDelivery(current.getLineNum().toString(), 
                                            current.getItemId().toString(), current.getShipmentHeaderId().toString(),
                                            current.getReceiptNum().toString(), current.getStatus().toString(), 
                                            current.getDelivLocator().toString(), current.getShipmentLineId().toString());
                    System.out.println("countD: "+countD);
                    if(countD == 0){
                        System.out.println("inside the countD = 0");
                        listFromIterator.add(current);
                        myList.add(comb);
                    }
                }
                  
            }    
        }               
        System.out.println("myL size: " +myList.size());
        System.out.println("listFromIterator: " +listFromIterator.size());
        Iterable<IOReceiptConfirmEO> ls=ioReceiptConfirmro.saveAll(listFromIterator);
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
    }

    private int recordCheckforIoReceipt(String lineNum, String itemId, String supHdrId, String status, 
                                    String supLineId) {
        System.out.println("inside IoReceipt");
        int c = ioReceiptConfirmro.recordCountIOReceiptConfirm(lineNum, itemId, supHdrId, status, supLineId);
        return c;
    }

    private int recordCheckforIoDelivery(String lineNum, String itemId, String supHdrId, String receiptNum, String status, 
                                    String delivLoc, String supLineId) {
        System.out.println("inside IO Delivery");
        int c = ioReceiptConfirmro.recordCountIODeliveryConfirm(lineNum, itemId, supHdrId, receiptNum, status, delivLoc, supLineId);
        return c;
    }

    public ResponseEntity<APIResponse> getReceiptConfirm() {
        List<IOReceiptConfirmEO> ls=ioReceiptConfirmro.findAll();
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);    
    }
    
}
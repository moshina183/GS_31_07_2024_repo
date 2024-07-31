package com.mobile.integration.grandstores.RMAConfirm.RMAConfirmService;
import java.util.ArrayList;
import java.util.Iterator;
// import java.util.Map;
import java.util.List;

import com.mobile.integration.grandstores.Logs.mobTransLog.MobTransLogEO;
import com.mobile.integration.grandstores.MoConfirm.MoConfirmEntity.MoConfirmEO;
import com.mobile.integration.grandstores.MoConfirm.MoConfirmEntity.PickConfirmEO;
import com.mobile.integration.grandstores.MoConfirm.MoConfirmService.PickConfirmSO;
import com.mobile.integration.grandstores.RMAConfirm.RMAConfirmEntity.RMAConfirmEO;
import com.mobile.integration.grandstores.RMAConfirm.RMAConfirmEntity.RMAConfirmMO;
import com.mobile.integration.grandstores.RMAConfirm.RMAConfirmRepository.RMAConfirmRO;
import com.mobile.integration.grandstores.Utils.Generic.DateUtils;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RMAConfirmSO {

    @Autowired
    private RMAConfirmRO rmaConfirmro;

    @Autowired
    private DateUtils dateUtils;

    
    @Autowired
    private PickConfirmSO pickConfirmso; 

    public ResponseEntity<APIResponse> insertRMAConfirmRO(Iterable<RMAConfirmEO> bodydtl) {
        List<String> myList = new ArrayList<>();
        String comb = "";
        List<RMAConfirmEO> listFromIterator = new ArrayList<>();
        Iterator<RMAConfirmEO> iterator1 = bodydtl.iterator();
        while (iterator1.hasNext()) {
            RMAConfirmEO current = iterator1.next();
            System.out.println("current.getTransactionType().toString(): "+current.getTransactionType().toString());
            if(current.getTransactionType().toString().equalsIgnoreCase("RMA_DELIVERY")){
                comb = current.getTransactionType().toString() + 
                    "-" +current.getReceiptNumber().toString() + 
                    "-" +current.getLineNumber().toString()+ 
                    "-" +current.getOrderHeaderId().toString()+
                    "-" +current.getOrderlineId().toString()+
                    "-" +current.getAttribute3().toString()+
                    "-" +current.getAttribute10().toString()+
                    "-" +current.getItemId().toString()+
                    "-" +current.getStatus().toString();
                System.out.println("inside the iterator: "+comb);
                if(myList.contains(comb)){
                    iterator1.remove();
                    System.out.println("removed");
                }
                else{
                    System.out.println("else part");
                    int countD = recordCheckforDelivery(current.getTransactionType().toString(), 
                                            current.getReceiptNumber().toString(), current.getLineNumber().toString(),
                                            current.getOrderHeaderId().toString(), current.getOrderlineId().toString(),
                                            current.getAttribute3().toString(),current.getAttribute10().toString(),
                                            current.getItemId().toString(),current.getStatus().toString());
                    System.out.println("countD: "+countD);
                    if(countD == 0){
                        System.out.println("inside the countD = 0");
                        listFromIterator.add(current);
                        myList.add(comb);
                    }
                }
                  
            }
            if(current.getTransactionType().toString().equalsIgnoreCase("RMA_RECEIPT")){
                comb = current.getTransactionType().toString() + 
                "-" +current.getLineNumber().toString()+ 
                "-" +current.getOrderHeaderId().toString()+
                "-" +current.getOrderlineId().toString()+
                "-" +current.getAttribute3().toString()+
                "-" +current.getItemId().toString()+
                "-" +current.getStatus().toString();
            System.out.println("inside the iterator: "+comb);
            if(myList.contains(comb)){
                iterator1.remove();
                System.out.println("removed");
            }
            else{
                System.out.println("else part");
                int countR = recordCheckforReceipt(current.getTransactionType().toString(), 
                                        current.getLineNumber().toString(),
                                        current.getOrderHeaderId().toString(), current.getOrderlineId().toString(),
                                        current.getAttribute3().toString(), current.getItemId().toString(),
                                        current.getStatus().toString());
                System.out.println("countR: "+countR);
                if(countR == 0){
                    System.out.println("inside the countR = 0");
                    listFromIterator.add(current);
                    myList.add(comb);
                }
            }
            }  
            
            
        }               
        System.out.println("myL size: " +myList.size());
        System.out.println("listFromIterator: " +listFromIterator.size());
        //moConfirmSO.insertMoConfirmRO(listFromIterator);
        Iterable<RMAConfirmEO> ls=rmaConfirmro.saveAll(listFromIterator);
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
    }

    public int recordCheckforReceipt(String t, String l, String oh, String ol, String att3, String itemId, String status){
        System.out.println("inside recordCheckforReceipt method: ");
        int c = rmaConfirmro.recordCountForReceipt(t, l, oh, ol, att3, itemId, status);
        return c;
    }

    public int recordCheckforDelivery(String t, String d, String i, String oh, String o, String att3, String att10, String itemId, String status){
        System.out.println("inside recordCheckforDelivery method: ");
        int c = rmaConfirmro.recordCountForDelivery(t, d, i, oh, o, att3, att10, itemId, status);
        return c;
    }

    public ResponseEntity<APIResponse> getRMAConfirmRO() {
        List<RMAConfirmEO> ls=rmaConfirmro.findAll();
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);    
    }

    public ResponseEntity<APIResponse> insertRMAConfirmMO(RMAConfirmMO bodydtl) {
        
        //RMAconfirm 
        Iterable<RMAConfirmEO> rmaDetails = bodydtl.getRmaConfirm();
        Iterator<RMAConfirmEO> rmaIterator = rmaDetails.iterator();
        List<String> myList = new ArrayList<>();
        String comb = "";
        List<RMAConfirmEO> listFromIterator = new ArrayList<>();
        while (rmaIterator.hasNext()) {
            RMAConfirmEO current = rmaIterator.next();
            System.out.println("current.getTransactionType().toString(): "+current.getTransactionType().toString());
            if(current.getTransactionType().toString().equalsIgnoreCase("RMA_DELIVERY")){
                comb = current.getTransactionType().toString() + 
                    "-" +current.getReceiptNumber().toString() + 
                    "-" +current.getLineNumber().toString()+ 
                    "-" +current.getOrderHeaderId().toString()+
                    "-" +current.getOrderlineId().toString()+
                    "-" +current.getAttribute3().toString()+
                    "-" +current.getAttribute10().toString()+
                    "-" +current.getItemId().toString()+
                    "-" +current.getStatus().toString();
                System.out.println("inside the iterator: "+comb);
                if(myList.contains(comb)){
                    rmaIterator.remove();
                    System.out.println("removed");
                }
                else{
                    System.out.println("else part");
                    int countD = recordCheckforDelivery(current.getTransactionType().toString(), 
                                            current.getReceiptNumber().toString(), current.getLineNumber().toString(),
                                            current.getOrderHeaderId().toString(), current.getOrderlineId().toString(),
                                            current.getAttribute3().toString(),current.getAttribute10().toString(),
                                            current.getItemId().toString(),current.getStatus().toString());
                    System.out.println("countD: "+countD);
                    if(countD == 0){
                        System.out.println("inside the countD = 0");
                        listFromIterator.add(current);
                        myList.add(comb);
                    }
                }
                  
            }
            if(current.getTransactionType().toString().equalsIgnoreCase("RMA_RECEIPT")){
                comb = current.getTransactionType().toString() + 
                "-" +current.getLineNumber().toString()+ 
                "-" +current.getOrderHeaderId().toString()+
                "-" +current.getOrderlineId().toString()+
                "-" +current.getAttribute3().toString()+
                "-" +current.getItemId().toString()+
                "-" +current.getStatus().toString();
                System.out.println("inside the iterator: "+comb);
                if(myList.contains(comb)){
                    rmaIterator.remove();
                    System.out.println("removed");
                }
                else{
                    System.out.println("else part");
                    int countR = recordCheckforReceipt(current.getTransactionType().toString(), 
                                            current.getLineNumber().toString(),
                                            current.getOrderHeaderId().toString(), current.getOrderlineId().toString(),
                                            current.getAttribute3().toString(), current.getItemId().toString(),
                                            current.getStatus().toString());
                    System.out.println("countR: "+countR);
                    if(countR == 0){
                        System.out.println("inside the countR = 0");
                        listFromIterator.add(current);
                        myList.add(comb);
                    }
                }
            }  
        }
        Iterable<RMAConfirmEO> ls = rmaConfirmro.saveAll(listFromIterator);
        



        //insertPick
        Iterable<PickConfirmEO> pickDetails = bodydtl.getPickConfirm();
        Iterator<PickConfirmEO> pickIterator = pickDetails.iterator();
        List<String> myPoList = new ArrayList<>();
        String poComb = "";
        List<PickConfirmEO> poListFromIterator = new ArrayList<>();
        List<String> poListFailed = new ArrayList<>();
        while (pickIterator.hasNext()) {
            PickConfirmEO poCurrent = pickIterator.next();
            System.out.println("current.getFromSerialNumber.toString(): "+poCurrent.getFromSerialNumber().toString());
            
            System.out.println("current.getTrans.toString(): "+poCurrent.getTransactionType().toString());
            if(poCurrent.getTransactionType().toString().equalsIgnoreCase("Out Bound Picking")){
                poComb = poCurrent.getDeliveryDetailId().toString() + "-" +poCurrent.getFromSerialNumber().toString() + "-" +poCurrent.getAttribute1().toString() +"-" 
                        +poCurrent.getAttribute2().toString() +"-" +poCurrent.getAttribute3().toString() + "-" +poCurrent.getAttribute4().toString();
                System.out.println("inside the po iterator: "+poComb);
                if(myPoList.contains(poComb)){
                    pickIterator.remove();
                }
                else{
                    System.out.println("else part");
                    int countR = pickConfirmso.poRecordCountService(poCurrent.getDeliveryDetailId().toString(), 
                    poCurrent.getFromSerialNumber().toString(), poCurrent.getAttribute1().toString(),
                    poCurrent.getAttribute2().toString(), poCurrent.getAttribute3().toString(),
                    poCurrent.getAttribute4().toString());
                    System.out.println("countR: "+countR);
                    if(countR == 0){
                        System.out.println("inside the countR = 0 po");
                        poListFromIterator.add(poCurrent);
                        myPoList.add(poComb);
                    }else{
                        poListFailed.add(poComb); 
                        System.out.println("po failed : "+poComb);
                    }
                }
            }
            //RMA_DELIVERY
            if(poCurrent.getTransactionType().toString().equalsIgnoreCase("RMA_DELIVERY")){
                System.out.println("inside the po RMA_DELIVERY iterator: ");
                poComb = poCurrent.getFromSerialNumber().toString() + 
                        "-" +poCurrent.getAttribute2().toString() +
                        "-" +poCurrent.getAttribute3().toString() + 
                        "-" +poCurrent.getTransactionType().toString();
                System.out.println("inside the po RMA_DELIVERY iterator: "+poComb);
                if(myPoList.contains(poComb)){
                    pickIterator.remove();
                }
                else{
                    System.out.println("else part");
                    int countR = pickConfirmso.poRecordCountDelivery(
                        poCurrent.getFromSerialNumber().toString(), 
                        poCurrent.getAttribute2().toString(), 
                        poCurrent.getAttribute3().toString(),
                        poCurrent.getTransactionType().toString());
                    System.out.println("countR: "+countR);
                    if(countR == 0){
                        System.out.println("inside the countR = 0 po");
                        poListFromIterator.add(poCurrent);
                        myPoList.add(poComb);
                    }else{
                        poListFailed.add(poComb); 
                        System.out.println("po failed : "+poComb);
                    }
                }
            }
            
        }   
        System.out.println("poListFromIterator: " +poListFromIterator.size());
        System.out.println("my po list failed size: " +poListFailed.size());
        
        if(poListFromIterator.size() > 0){
            return pickConfirmso.insertPickConfirm(poListFromIterator);
        }else{
            APIResponse api = new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api);
        }
        
    }
    
}
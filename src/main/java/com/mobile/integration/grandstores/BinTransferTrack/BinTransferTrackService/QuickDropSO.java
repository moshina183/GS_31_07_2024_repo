package com.mobile.integration.grandstores.BinTransferTrack.BinTransferTrackService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mobile.integration.grandstores.BinTransferTrack.BinTransferTrackEntity.BinTransferTrackDropEO;
import com.mobile.integration.grandstores.BinTransferTrack.BinTransferTrackEntity.BinTransferTrackHdrEO;
import com.mobile.integration.grandstores.BinTransferTrack.BinTransferTrackEntity.BinTransferTrackPickEO;
import com.mobile.integration.grandstores.BinTransferTrack.BinTransferTrackEntity.QuickDropEO;
import com.mobile.integration.grandstores.BinTransferTrack.BinTransferTrackRepository.BinTransferTrackDropRO;
import com.mobile.integration.grandstores.BinTransferTrack.BinTransferTrackRepository.BinTransferTrackHdrRO;
import com.mobile.integration.grandstores.BinTransferTrack.BinTransferTrackRepository.BinTransferTrackPickRO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

@Service
public class QuickDropSO {

    @Autowired
    private BinTransferTrackHdrRO binTransferTrackHdrro;

    @Autowired
    private BinTransferTrackPickRO binTransPickro;

    @Autowired
    private BinTransferTrackDropRO binTranTrackDropro;

    public ResponseEntity<APIResponse> insertQuickDrop(QuickDropEO bodydtl) {
        // Header Table insertion
        List<BigDecimal> LineIdList = new ArrayList<>();
        BigDecimal headerId = new BigDecimal(0);
        Iterable<BinTransferTrackHdrEO> hr = bodydtl.getHeader();
        Iterable<BinTransferTrackHdrEO> lshdr = binTransferTrackHdrro.saveAll(hr);
        Iterator<BinTransferTrackHdrEO> hrIter = lshdr.iterator();
        while (hrIter.hasNext()) {
            BinTransferTrackHdrEO hdrdetail = hrIter.next();
            System.out.println("Header info saved successfully: " + hdrdetail.getHeaderId());
            headerId = hdrdetail.getHeaderId();
        }
        System.out.println("Generated Header Id: " + headerId);

        // Pick Table insertion
        Iterator<BinTransferTrackPickEO> pickItr = bodydtl.getPick().iterator();
        while (pickItr.hasNext()) {
            BinTransferTrackPickEO pickDetail = pickItr.next();
            pickDetail.setHeaderId(headerId);
            BinTransferTrackPickEO result = binTransPickro.save(pickDetail);
            LineIdList.add(result.getLineId());
            System.out.println("New Line Id:" + result.getLineId());
        }
        int count = 0;
        // Drop Table insertion
        Iterator<BinTransferTrackDropEO> dropItr = bodydtl.getDrop().iterator();
        while (dropItr.hasNext()) {
            LineIdList.get(count);
            BinTransferTrackDropEO dropDetail = dropItr.next();
            System.out.println("LineIdList.get(count)" + LineIdList.get(count));
            dropDetail.setLineId(LineIdList.get(count));
            BinTransferTrackDropEO result = binTranTrackDropro.save(dropDetail);
            System.out.println("New Drop Id:" + result.getDropId());
            count++;
        }
        APIResponse api = new APIResponse();
        api.setData(lshdr);
        api.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok().body(api);
    }

}

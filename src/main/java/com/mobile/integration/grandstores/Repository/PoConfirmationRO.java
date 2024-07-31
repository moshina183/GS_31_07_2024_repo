package com.mobile.integration.grandstores.Repository;

import java.math.BigDecimal;
import java.sql.Date;

import com.mobile.integration.grandstores.Entity.PoConfirmationEO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PoConfirmationRO  extends JpaRepository<PoConfirmationEO ,BigDecimal>{

    // duplicate line filtration - 03/05/24 
    String rawQuery="Select count(*) from XXGS_PO_CONFIRMATION where "+
    "PO_HEADER_ID = :poHeaderId "+
    "AND RELEASE_NUM = :releaseNum "+
    "AND ITEM_ID = :itemId "+
    "AND SUP_INV_NUM = :supInvNum "+
    "AND SUP_INV_DATE = :supInvDate "+
    "AND STATUS = :status "+
    "AND ATTRIBUTE8 = :attribute8 ";

    @Query(nativeQuery = true, value = rawQuery)
    int recordCountPOReceipt(
    @Param("poHeaderId") String poHeaderId,
    @Param("releaseNum") String releaseNum,
    @Param("itemId") String itemId,
    @Param("supInvNum") String supInvNum,
    @Param("supInvDate") Date supInvDate,
    @Param("status") String status,
    @Param("attribute8") String attribute8
    );

    String rawQuery1="Select count(*) from XXGS_PO_CONFIRMATION where "+
    "PO_HEADER_ID = :poHeaderId "+
    "AND RELEASE_NUM = :releaseNum "+
    "AND ITEM_ID = :itemId "+
    "AND DELIV_LOCATOR = :delivLocator "+
    "AND STATUS = :status "+
    "AND RECEIPT_NUM = :receiptNum "+
    "AND ATTRIBUTE8 = :attribute8 ";

    @Query(nativeQuery = true, value = rawQuery1)
    int recordCountPODelivery(
    @Param("poHeaderId") String poHeaderId,
    @Param("releaseNum") String releaseNum,
    @Param("itemId") String itemId,
    @Param("delivLocator") String delivLocator,
    @Param("status") String status,
    @Param("receiptNum") String receiptNum,
    @Param("attribute8") String attribute8
    );
}
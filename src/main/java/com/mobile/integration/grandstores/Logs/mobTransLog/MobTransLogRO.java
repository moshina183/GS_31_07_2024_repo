package com.mobile.integration.grandstores.Logs.mobTransLog;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;



@Repository
public interface MobTransLogRO extends JpaRepository<MobTransLogEO , BigDecimal>{

    public List<MobTransLogEO> findByLogId(BigDecimal logId);

    String rawQuery="Select count(*) from XXGS_MOB_TRANSACTION_LOGS where "+
    " LOG_ID = :logId ";

    @Query(nativeQuery = true, value = rawQuery)
    int recordCountt(
    @Param("logId") String  logId
    );

}

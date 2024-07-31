package com.mobile.integration.grandstores.Logs.mobTransLog;

import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "XXGS_MOB_TRANSACTION_LOGS")
public class MobTransLogEO {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mob_trans_log_seq")
    @SequenceGenerator(name = "mob_trans_log_seq", sequenceName = "XXGS_MOB_TRANSACTION_ID_S", allocationSize = 1)
    @Column(name = "LOG_ID")
    public BigDecimal logId;

    @Column(name = "MODULE_NAME")
    public String moduleName;

    @Lob
    @Column(name = "REQUEST", columnDefinition = "CLOB")
    private java.sql.Clob request;

    @Lob
    @Column(name = "RESPONSE", columnDefinition = "CLOB")
    private java.sql.Clob response;

    @Column(name = "PROCESSED_TIME")
    public Date processedTime;

    @Column(name = "ATTRIBUTE_CATEGORY")
    public String attributeCategory;
    
    @Column(name = "ATTRIBUTE1")
    public String attribute1;
    
    @Column(name = "ATTRIBUTE2")
    public String attribute2;
    
    @Column(name = "ATTRIBUTE3")
    public String attribute3;
    
    @Column(name = "ATTRIBUTE4")
    public String attribute4;
    
    @Column(name = "ATTRIBUTE5")
    public String attribute5;
    
    @Column(name = "ATTRIBUTE6")
    public String attribute6;
    
    @Column(name = "ATTRIBUTE7")
    public String attribute7;
    
    @Column(name = "ATTRIBUTE8")
    public String attribute8;
    
    @Column(name = "ATTRIBUTE9")
    public String attribute9;
    
    @Column(name = "ATTRIBUTE10")
    public String attribute10;
    
    @Column(name = "LAST_UPDATE_DATE")
    public Date lastUpdateDate;
    
    @Column(name = "LAST_UPDATED_BY")
    public BigDecimal lastUpdatedBy;
    
    @Column(name = "CREATION_DATE")
    public Date creationDate;
    
    @Column(name = "CREATED_BY")
    public BigDecimal createdBy;
    
    @Column(name = "LAST_UPDATE_LOGIN")
    public BigDecimal lastUpdateLogin;

    public BigDecimal getLogId() {
        return logId;
    }

    public void setLogId(BigDecimal logId) {
        this.logId = logId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public java.sql.Clob getRequest() {
        return request;
    }

    public void setRequest(java.sql.Clob request) {
        this.request = request;
    }

    public java.sql.Clob getResponse() {
        return response;
    }

    public void setResponse(java.sql.Clob response) {
        this.response = response;
    }

    public Date getProcessedTime() {
        return processedTime;
    }

    public void setProcessedTime(Date processedTime) {
        this.processedTime = processedTime;
    }

    public String getAttributeCategory() {
        return attributeCategory;
    }

    public void setAttributeCategory(String attributeCategory) {
        this.attributeCategory = attributeCategory;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    public String getAttribute6() {
        return attribute6;
    }

    public void setAttribute6(String attribute6) {
        this.attribute6 = attribute6;
    }

    public String getAttribute7() {
        return attribute7;
    }

    public void setAttribute7(String attribute7) {
        this.attribute7 = attribute7;
    }

    public String getAttribute8() {
        return attribute8;
    }

    public void setAttribute8(String attribute8) {
        this.attribute8 = attribute8;
    }

    public String getAttribute9() {
        return attribute9;
    }

    public void setAttribute9(String attribute9) {
        this.attribute9 = attribute9;
    }

    public String getAttribute10() {
        return attribute10;
    }

    public void setAttribute10(String attribute10) {
        this.attribute10 = attribute10;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public BigDecimal getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(BigDecimal lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(BigDecimal createdBy) {
        this.createdBy = createdBy;
    }

    public BigDecimal getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(BigDecimal lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public MobTransLogEO(BigDecimal logId, String moduleName, Clob request, Clob response, Date processedTime,
            String attributeCategory, String attribute1, String attribute2, String attribute3, String attribute4,
            String attribute5, String attribute6, String attribute7, String attribute8, String attribute9,
            String attribute10, Date lastUpdateDate, BigDecimal lastUpdatedBy, Date creationDate, BigDecimal createdBy,
            BigDecimal lastUpdateLogin) {
        this.logId = logId;
        this.moduleName = moduleName;
        this.request = request;
        this.response = response;
        this.processedTime = processedTime;
        this.attributeCategory = attributeCategory;
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
        this.attribute3 = attribute3;
        this.attribute4 = attribute4;
        this.attribute5 = attribute5;
        this.attribute6 = attribute6;
        this.attribute7 = attribute7;
        this.attribute8 = attribute8;
        this.attribute9 = attribute9;
        this.attribute10 = attribute10;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.lastUpdateLogin = lastUpdateLogin;
    }

    
    public MobTransLogEO() {
    }

    @Override
    public String toString() {
        return "MobTransLogEO [logId=" + logId + ", moduleName=" + moduleName + ", request=" + request + ", response="
                + response + ", processedTime=" + processedTime + ", attributeCategory=" + attributeCategory
                + ", attribute1=" + attribute1 + ", attribute2=" + attribute2 + ", attribute3=" + attribute3
                + ", attribute4=" + attribute4 + ", attribute5=" + attribute5 + ", attribute6=" + attribute6
                + ", attribute7=" + attribute7 + ", attribute8=" + attribute8 + ", attribute9=" + attribute9
                + ", attribute10=" + attribute10 + ", lastUpdateDate=" + lastUpdateDate + ", lastUpdatedBy="
                + lastUpdatedBy + ", creationDate=" + creationDate + ", createdBy=" + createdBy + ", lastUpdateLogin="
                + lastUpdateLogin + "]";
    }

    

}

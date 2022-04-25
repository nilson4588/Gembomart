/*
 * package com.rest.model;
 * 
 * import java.io.Serializable;
 * 
 * import javax.persistence.Column; import javax.persistence.Entity; import
 * javax.persistence.GeneratedValue; import javax.persistence.Id; import
 * javax.persistence.Table;
 * 
 * import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
 * 
 * @Entity
 * 
 * @Table(name = "tbl_financial_year")
 * 
 * @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) public class
 * FinanceYear implements Serializable {
 * 
 * private static final long serialVersionUID = 1L;
 * 
 * @Id
 * 
 * @GeneratedValue
 * 
 * @Column(name ="financial_year_id") private int financialYearId;
 * 
 * @Column(name ="financial_year_start") private String financialYearStart;
 * 
 * @Column(name ="financial_year_end") private String financialYearEnd;
 * 
 * @Column(name ="financial_year") private String financialYear;
 * 
 * @Column(name ="financial_year_active") private int financialYearActive;
 * 
 * public int getFinancialYearId() { return financialYearId; }
 * 
 * public void setFinancialYearId(int financialYearId) { this.financialYearId =
 * financialYearId; }
 * 
 * public String getFinancialYearStart() { return financialYearStart; }
 * 
 * public void setFinancialYearStart(String financialYearStart) {
 * this.financialYearStart = financialYearStart; }
 * 
 * public String getFinancialYearEnd() { return financialYearEnd; }
 * 
 * public void setFinancialYearEnd(String financialYearEnd) {
 * this.financialYearEnd = financialYearEnd; }
 * 
 * public String getFinancialYear() { return financialYear; }
 * 
 * public void setFinancialYear(String financialYear) { this.financialYear =
 * financialYear; }
 * 
 * public int getFinancialYearActive() { return financialYearActive; }
 * 
 * public void setFinancialYearActive(int financialYearActive) {
 * this.financialYearActive = financialYearActive; }
 * 
 * public static long getSerialversionuid() { return serialVersionUID; } }
 */
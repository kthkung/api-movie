package org.sso.base.reports;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.sf.jasperreports.engine.JRDataSource;
import org.sso.base.exception.BaseException;

import java.util.HashMap;

public interface FirReportRunnable extends Runnable {

    String getPdfTemplate();
    String getExcelTemplate();

    //call when report is scheduled
    void validateFilter(String filter) throws JsonProcessingException, BaseException;

    //call before run();
    void setFilter(String filter);

    //call after run();
    HashMap<String, Object> getResultParameters();
    JRDataSource getResultDataStore();
    String getCsvOutput(String separator);
}

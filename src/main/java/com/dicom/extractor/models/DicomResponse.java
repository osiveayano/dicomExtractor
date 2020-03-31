package com.dicom.extractor.models;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.VR;

import java.io.StringReader;

public class DicomResponse {

    private String serverFilePath;
    private String dicomData;

    public String getServerFilePath() {
        return serverFilePath;
    }

    public void setServerFilePath(String serverFilePath) {
        this.serverFilePath = serverFilePath;
    }

    public String getDicomData() {
        return dicomData;
    }

    public void setDicomData(String dicomData) {
        this.dicomData = dicomData;
    }
}

package com.dicom.extractor.helpers;

import java.sql.Timestamp;
import java.time.Instant;

public class Utils {

    private final static String fileBasePath = "dicoms/";

    public static String getServerPathForDicomFile(String fileName){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Instant instant = timestamp.toInstant();
        String result = fileBasePath+ instant.toString()+"/"+ fileName;

        return result;

    }
}

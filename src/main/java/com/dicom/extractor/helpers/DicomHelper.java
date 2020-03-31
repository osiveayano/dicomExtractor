package com.dicom.extractor.helpers;

import com.dicom.extractor.enums.IncludeFileMetaInformation;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.io.DicomInputStream;
import org.dcm4che3.json.JSONWriter;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;


public class DicomHelper {

    public static Attributes readFileMetaInformation(Path dicomFile) throws IOException {
        return read(dicomFile, IncludeFileMetaInformation.FILE_META_INFORMATION_ONLY, DicomInputStream.IncludeBulkData.NO);
    }

    public static Attributes read(Path dicomFile, IncludeFileMetaInformation includeFileMetaInformation, DicomInputStream.IncludeBulkData includeBulkData) throws IOException {
        return read(new FileInputStream(dicomFile.toFile()), includeFileMetaInformation, includeBulkData);
    }

    public static Attributes read(InputStream binaryDicomObjectStream, IncludeFileMetaInformation includeFileMetaInformation, DicomInputStream.IncludeBulkData includeBulkData) throws IOException {
        try (DicomInputStream dicomIn = new DicomInputStream(binaryDicomObjectStream)) {

            dicomIn.setIncludeBulkData(includeBulkData);

            Attributes fmi = dicomIn.readFileMetaInformation();

            if (includeFileMetaInformation == IncludeFileMetaInformation.FILE_META_INFORMATION_ONLY) {
                return fmi;
            } else {
                Attributes dataset = dicomIn.readDataset(-1, -1);

                if (includeFileMetaInformation == IncludeFileMetaInformation.DATASET_MERGED_WITH_FILE_META_INFORMATION) {
                    if (fmi != null) {
                        dataset.addAll(fmi);
                    }
                }

                return dataset;
            }
        }
    }

    public static String readFileMetaInformationJSON(Path dicomFile) throws IOException {
        return readJSON(dicomFile, IncludeFileMetaInformation.FILE_META_INFORMATION_ONLY, DicomInputStream.IncludeBulkData.NO);
    }

    public static String readJSON(Path dicomFile, IncludeFileMetaInformation includeFileMetaInformation, DicomInputStream.IncludeBulkData includeBulkData) throws IOException {
        return readJSON(new FileInputStream(dicomFile.toFile()), includeFileMetaInformation, includeBulkData);
    }

    public static String readJSON(InputStream binaryDicomObjectStream, IncludeFileMetaInformation includeFileMetaInformation, DicomInputStream.IncludeBulkData includeBulkData) throws IOException {

        String result = "";
        try (DicomInputStream dicomIn = new DicomInputStream(binaryDicomObjectStream)) {

            dicomIn.setIncludeBulkData(includeBulkData);

            result = getJsonFromDicomInputStream(dicomIn);
        }

        return result;
    }

    public static String getJsonFromDicomInputStream(DicomInputStream dicomIn) throws IOException {
        String result = "";

        StringWriter sw = new StringWriter();
        Map<String, ?> conf = new HashMap<String, Object>(2);
//        conf.put(JsonGenerator.PRETTY_PRINTING, null);
        JsonGenerator jsonGen = Json.createGeneratorFactory(conf).createGenerator(sw);

        JSONWriter jsonWriter = new JSONWriter(jsonGen);
        dicomIn.setDicomInputHandler(jsonWriter);
        dicomIn.readDataset(-1, -1);

        return sw.toString();
    }
}

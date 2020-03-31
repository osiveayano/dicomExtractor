package com.dicom.extractor.services;

import com.dicom.extractor.helpers.DicomHelper;
import com.dicom.extractor.helpers.Utils;
import com.dicom.extractor.models.DicomResponse;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.json.JSONWriter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
public class DicomService {

    public DicomResponse processDicomFile(MultipartFile file) throws IOException {
        DicomResponse response = new DicomResponse();

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String serverDir = Utils.getServerPathForDicomFile(fileName);
        Path filePath = Paths.get(serverDir);

        Files.createDirectories(filePath.getParent());
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        String dicomAttributeString = DicomHelper.readFileMetaInformationJSON(filePath);
        response.setDicomData(dicomAttributeString);
        response.setServerFilePath(filePath.toAbsolutePath().toString());

        return response;
    }


}

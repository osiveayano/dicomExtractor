package com.dicom.extractor.controllers;

import com.dicom.extractor.models.DicomResponse;
import com.dicom.extractor.models.error.ErrorResponse;
import com.dicom.extractor.services.DicomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/dicom")
public class DicomController {

    @Autowired
    DicomService dicomService;

    @PostMapping("/process")
    public ResponseEntity uploadToLocalFileSystem(@RequestParam("file") MultipartFile file) {

        ResponseEntity responseEntity;
        try {
            DicomResponse dicomResponse = dicomService.processDicomFile(file);
            responseEntity = ResponseEntity.ok(dicomResponse);
        }catch (Exception e){
            e.printStackTrace();
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage(e.getMessage());

            responseEntity = new ResponseEntity(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }
}

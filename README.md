# dicomExtractor
caMicroscope GSoC 2020 -  Real-time DICOM Metadata Extractor ( Sample code snippet for GSOC 2020)


![Architecture](https://github.com/osiveayano/dicomExtractor/blob/master/architecture.jpg)

#### Steps to run via command line 
**Jar file is present in this repository
1. java -jar com.dicom.extractor-0.0.1-SNAPSHOT.jar


#### Below is the format of the API call 
curl --location --request POST 'http://localhost:8080/api/dicom/process' \
--header 'Content-Type: multipart/form-data; boundary=--------------------------256516553745026345934793' \
--form 'file=@/home/username/path2DICOMfile/sample_dicom.DCM'

#### or via postman
![Postman](https://github.com/osiveayano/mongoDataRest-CRUD/blob/master/postmanScreenshot.jpg)


This repo is based on Springboot, one needs to execute the code in Intellij - 
![Springboot Project Execution](https://github.com/osiveayano/dicomExtractor/blob/master/springboot-run.jpg)



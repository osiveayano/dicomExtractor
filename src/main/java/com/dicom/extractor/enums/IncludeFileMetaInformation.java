package com.dicom.extractor.enums;

public enum IncludeFileMetaInformation {
    /**
     * Only read the DICOM dataset without file meta information.
     */
    DATASET_ONLY,

    /**
     * Only read the file meta information.
     */
    FILE_META_INFORMATION_ONLY,

    /**
     * Read both file meta information and the dataset, and merge the two.
     */
    DATASET_MERGED_WITH_FILE_META_INFORMATION
}
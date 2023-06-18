package com.reactapp.springjwt.models;

import javax.persistence.Column;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "file_upload")
@NoArgsConstructor
public class FileUpload {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column private String fileName;
  @Column private String fileUri;
  @Column private String fileDownloadUri;
  @Column private long fileSize;
  @Column private String uploaderName;
  @Column private int pointid;
  @Column private int routeid;
  

  public FileUpload(String fileName, String fileUri, String fileDownloadUri, long fileSize, String uploaderName,  int routeid, int pointid) {
    
    this.fileName = fileName;
    this.fileUri = fileUri;
    this.fileDownloadUri = fileDownloadUri;
    this.fileSize = fileSize;
    this.uploaderName = uploaderName;
    this.routeid = routeid;
    this.pointid = pointid;
  }

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getFileName() {
	return fileName;
}

public void setFileName(String fileName) {
	this.fileName = fileName;
}

public String getFileUri() {
	return fileUri;
}

public void setFileUri(String fileUri) {
	this.fileUri = fileUri;
}

public String getFileDownloadUri() {
	return fileDownloadUri;
}

public void setFileDownloadUri(String fileDownloadUri) {
	this.fileDownloadUri = fileDownloadUri;
}

public long getFileSize() {
	return fileSize;
}

public void setFileSize(long fileSize) {
	this.fileSize = fileSize;
}

public String getUploaderName() {
	return uploaderName;
}

public void setUploaderName(String uploaderName) {
	this.uploaderName = uploaderName;
}

public int getPointid() {
	return pointid;
}

public void setPointid(int pointid) {
	this.pointid = pointid;
}

public int getRouteid() {
	return routeid;
}

public void setRouteid(int routeid) {
	this.routeid = routeid;
}
  
  
  
}



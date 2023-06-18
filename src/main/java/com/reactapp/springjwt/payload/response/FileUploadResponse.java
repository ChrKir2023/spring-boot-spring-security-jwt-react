package com.reactapp.springjwt.payload.response;

import javax.persistence.Column;

public class FileUploadResponse {

	public FileUploadResponse(int id, String fileName, String fileUri, String fileDownloadUri, long fileSize,
			String uploaderName) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.fileUri = fileUri;
		this.fileDownloadUri = fileDownloadUri;
		this.fileSize = fileSize;
		this.uploaderName = uploaderName;
	}
	private int id;

	private String fileName;
	private String fileUri;
	private String fileDownloadUri;
	private long fileSize;
	private String uploaderName;
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
	
	
	
	
	
}

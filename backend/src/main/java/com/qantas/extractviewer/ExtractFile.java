package com.qantas.extractviewer;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class ExtractFile {
	
	private String name;
	private String extractType;
	private String crewGroup;
	private String base;
	private DateTime creationDate;

	public ExtractFile(String name, String type, String crewGroup, String base, DateTime creationDate) {
		this.name = name;
		this.extractType = type;
		this.crewGroup = crewGroup;
		this.base = base;
		this.creationDate = creationDate;
	}
	
	public ExtractFile(ExtractFile clone) {
		this.name = clone.getName();
		this.extractType = clone.getExtractType();
		this.crewGroup = clone.getCrewGroup();
		this.base = clone.getBase();
		this.creationDate = clone.getRawCreationDate();
	}
	
	private DateTime getRawCreationDate() {
		return creationDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExtractType() {
		return extractType;
	}

	public void setExtractType(String type) {
		this.extractType = type;
	}

	public String getCrewGroup() {
		return crewGroup;
	}

	public void setCrewGroup(String crewGroup) {
		this.crewGroup = crewGroup;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getCreationDate() {
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
		return fmt.print(creationDate);
	}

	public void setCreationDate(DateTime creationDate) {
		this.creationDate = creationDate;
	}

}

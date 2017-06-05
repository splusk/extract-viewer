package com.qantas.extractviewer;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

@Service
public class MySimpleFileVisitor extends SimpleFileVisitor<Path> {

	private List<ExtractFile> files = new ArrayList<ExtractFile>();

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		
		String fileName = file.getFileName().toString();
		
		DateTime dateTime = new DateTime().minusMonths(1);
		if(fileName.startsWith("InterbidsExtract") && attrs.creationTime().toMillis() >= dateTime.getMillis()) {
			createExtractFile(fileName);	
		}
		return FileVisitResult.CONTINUE;
		
	}

	private void createExtractFile(String fileName) {
		String[] fileNameSplit = fileName.split("_");

		String name = fileName.toString();
		String type = fileNameSplit.length >= 1 ? fileNameSplit[0] : null;
		String crewGroup = fileNameSplit.length >= 2  ? getCrewGroupName(fileNameSplit[1]) : null;
		String base = fileNameSplit.length >= 3  ? fileNameSplit[2] : null;
		
		DateTime creationDate = getCreationDate(fileNameSplit[fileNameSplit.length - 1]);
		
		files.add(new ExtractFile(name, type, crewGroup, base, creationDate));

	}

	private DateTime getCreationDate(String stringDate) {
		//Get rid of file extension
		String[] datePart = stringDate.split("\\.", -1);
		//String date should come like this: 201705161520
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyyMMddHHmm");
		DateTime dateTime = new DateTime(fmt.parseDateTime(datePart[0]));
		
		return dateTime;
	}

	private String getCrewGroupName(String string) {
		if (string.equals("P")) {
			return "SHCC";
		} else if (string.equals("T")) {
			return "LHCC";
		} else if (string.equals("D")) {
			return "SHCC";
		} else if (string.equals("C")) {
			return "LHCC";
		} else {
			return string;
		}
	}

	public List<ExtractFile> getResult() {
		List<ExtractFile> cloneResult = cloneResult();
		resetList();
		Collections.reverse(cloneResult);
		return cloneResult;
	}

	private List<ExtractFile> cloneResult() {
		List<ExtractFile> clonedList = new ArrayList<ExtractFile>();
		for (ExtractFile extractFile : files) {
			clonedList.add(new ExtractFile(extractFile));
		}
		return clonedList;
	}

	public void resetList() {
		files.clear();
	}

}

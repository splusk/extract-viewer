package com.qantas.extractviewer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class ExtractViewerController {

	private String target = "/opt/Jeppesen/share/qfa/home/ska58/tmp/dir/";
	private String path = "/opt/Jeppesen/share/qfa/data/jcp/prod/qfa_data/FROM_QF/CCR/.arms_completed";

	private final Logger logger = LoggerFactory.getLogger(ExtractViewerController.class);

	@Autowired
	MySimpleFileVisitor fileVistor;

	@RequestMapping("/files")
	public Map<String, List<ExtractFile>> index() {
		Path p = Paths.get(path);
		try {
			Files.walkFileTree(p, fileVistor);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<ExtractFile> result = fileVistor.getResult();
		//TODO: Sort list based on creationDate
		return Collections.singletonMap("data", result);
	}

}

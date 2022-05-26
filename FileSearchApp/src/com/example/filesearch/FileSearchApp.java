package com.example.filesearch;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

public class FileSearchApp {
	String path;
	String regex;
	String zipFileName;
	

	public static void main(String[] args) {
		FileSearchApp app = new FileSearchApp();
		switch(Math.min(args.length, 3)) {
		case 0 :
			System.out.println("USAGE : FileSearchApp path [regex][zipfile]");
			return;
		case 3: app.setZipFileName(args[2]);
		case 2: app.setRegex(args[1]);
		case 1: app.setPath(args[0]);
		}
		try {
			app.walkDirectoryJava8(app.getPath());
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}


	private void walkDirectoryJava6(String path) throws IOException {
		// TODO Auto-generated method stub
		File dir = new File (path);
		File[] files = dir.listFiles();
		
		for(File file : files) {
			if(file.isDirectory()) {
				walkDirectoryJava6(file.getAbsolutePath());
			} else {
				processFile(file);
			}
		}
		
		System.out.println("walkDirectory : " + path);	
		searchFile(null);
		addFileToZip(null);
	}
	

	private void walkDirectoryJava7(String path) throws IOException {
		Files.walkFileTree(Paths.get(path),new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file,BasicFileAttributes attrs) throws IOException {
				processFile(file.toFile());
				return FileVisitResult.CONTINUE;
			}
		});
	}
	
	private void walkDirectoryJava8(String path) throws IOException {
		// TODO Auto-generated method stub
		Files.walk(Paths.get(path))
		    .forEach(f -> processFile(f.toFile()));
	}
	
	private void processFile(File file) {
		if ( searchFile(file) ) {
			addFileToZip(file);	
		}
		
	}


	private void addFileToZip(File file) {
		System.out.println("addFile :" + file);
		
	}


	private boolean searchFile(File file) {
		System.out.println("searchFile :" + file);
		return true;
		// TODO Auto-generated method stub
		
	}
	//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
	private boolean searchFileJava6(File file) throws IOException {
		boolean found = false;
		Scanner scanner = new Scanner(file,"UTF-8");
		while(scanner.hasNextLine()) {
			found = searchText(scanner.nextLine());
			if(found) { break; }
		}
		scanner.close();
		return found;		
	}


	private boolean searchText(String nextLine) {
		// TODO Auto-generated method stub
		return false;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public String getRegex() {
		return regex;
	}


	public void setRegex(String regex) {
		this.regex = regex;
	}


	public String getZipFileName() {
		return zipFileName;
	}


	public void setZipFileName(String zipFileName) {
		this.zipFileName = zipFileName;
	}

}

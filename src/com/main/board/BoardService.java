package com.main.board;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BoardService {
	private boardDAO fuploadDAO = null;
	//public static String SAVE_PATH="C:/workspace/JSP/Project/upload"; //경로 변경해줘야 할듯함.
	
	public BoardService() {
		fuploadDAO = new boardDAO();
	}
	
	public boolean fileUpload(String email, String name, String title, String content, int hit,  String file1, String file2, String file3, String file4, String file5, String filepath) {
		boardDTO boardDTO = new boardDTO();
		
		boardDTO.setEmail(email);
		boardDTO.setName(name);
		boardDTO.setTitle(title);
		boardDTO.setContent(content);
		boardDTO.setHit(hit);
		boardDTO.setFile1(file1);
		boardDTO.setFile2(file2);
		boardDTO.setFile3(file3);
		boardDTO.setFile4(file4);
		boardDTO.setFile5(file5);
		boardDTO.setFilepath(filepath);
		
		boardDTO = fuploadDAO.insertFile(boardDTO);
		
		if(boardDTO!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	//이 메소드가 어디에 쓰이는지 이해 못했음.
//	public boolean fileUploadidx(String name, String category,  int price, String comment, String uploadpath, String filename1, String filepath) {	
//		boardDTO boardDTO = new boardDTO();
//		
//		boardDTO.setName(name);
//		boardDTO.setCategory(category);
//		boardDTO.setPrice(price);
//		boardDTO.setComment(comment);
//	
//		
//		//boardDTO.setUploadpath(uploadpath);
//		
//		boardDTO.setFilename1(filename1);
//		boardDTO.setFilepath(filepath);
//		//boardDTO.setFilename2(filename2);
//		//boardDTO.setFilename3(filename3);
//		//boardDTO.setFilename4(filename4);
//		//boardDTO.setFilename5(filename5);
//		
//		
//		
//		boardDTO = fuploadDAO.insertFile(boardDTO);
//	
//		if(boardDTO!=null) {
//			return true;
//		}else {
//			return false;
//		}
//	}

/*getToday()와 moveFile()쪽 기능은 imgup.jsp에서 구현 후 저장시켜놓음
 * 
 * 
 * 날짜
 * public static String getToday() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyMMdd");
		return sdf.format(cal.getTime());
	}
	
	
	파일명 날짜 포함해서 하는 곳인듯
	public void moveFile(boardDTO boardDTO, File file) {
		File dir = new File(SAVE_PATH + "/" +getToday());
		if(!dir.isDirectory()) {
			dir.mkdir();
		}
		if(file.isFile()) {
			File newFile = new File(SAVE_PATH + boardDTO.getFilepath());
			file.renameTo(newFile);
		}
	}*/
}

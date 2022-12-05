<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.List"%>
<%@ page import="org.apache.commons.fileupload.FileItem"%>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@ page
	import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ page import="com.sun.xml.internal.txw2.Document"%>
<%@ page isELIgnored="false" %>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.Iterator"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.io.File"%>
<%@ page import="dangn.town.ttown.FileService"%>

<script src="js/get_count.js"></script>

<%
	FileService fs = new FileService();
	String imgName = "";
	int maxSize = 10 * 1024 * 1024;
	String uploadUri = "/upload";
	String dir ="";
	String t_name = "";
	String t_select1 = "";
	String t_select2 = "";
	String sample6_address = "";
	String sample6_detailAddress = "";
	String t_hp = "";
	String t_web = "";
	String t_content = "";
	String t_pic1 = "";
	String t_pic2 = "";
	String t_pic3 = "";
	String t_pic4 = "";
	String t_pic5 = "";

	int count=0;

	int size = 1024 * 1024 * 10;
	
	boolean isMultipart = ServletFileUpload.isMultipartContent(request);

	try {
		if(isMultipart){
			//메모리나 파일로 업로드 파일 보관하는 FileItem의 Factory설정
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(4096);//메모리에 저장할 최대 size
			//업로드 요청을 처리하는 ServletFileUpload 생성
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(maxSize);
			
			//업로드 요청 파싱해서 FileItem 목록 구함
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> iter = items.iterator();
		
			while(iter.hasNext()){
				FileItem item = iter.next();
				if(item.isFormField()){	//텍스트 입력인 경우
					String name = item.getFieldName();	//태그 name
					String value = item.getString("UTF-8");
					System.out.println("폼 필드 : " + name + " - " + value);
  					if(name.equals("t_name")){t_name=value;	/* product.setTitle(value); */}
					if(name.equals("t_select1")){t_select1=value;}
					if(name.equals("t_select2")){t_select2=value;	}
					if(name.equals("sample6_address")){sample6_address=value;}
					if(name.equals("sample6_detailAddress")){sample6_detailAddress=value;}
					if(name.equals("t_hp")){t_hp=value;}
					if(name.equals("t_web")){t_web=value;}
					if(name.equals("t_content")){t_content=value;}
				}else{
					String name = item.getFieldName();
					String fileName = item.getName();	//파일 이름
					String contentType = item.getContentType();
					boolean isInMemory = item.isInMemory();
					long sizeInBytes = item.getSize();	//파일 사이즈
					System.out.println("파일이름 : " + fileName);
					
					String now = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());  //현재시간
					
					imgName = now+"-"+fileName;	//저장하고자 하는 파일의 이름
					dir = request.getSession().getServletContext().getRealPath(uploadUri);	//물리적 경로
					System.out.println(uploadUri+"의 물리적 경로 : "+dir);
					
					switch(count){
						case 0 : t_pic1 = imgName;
						//데이터 저장 File(위치, 파일명)
						//만들어 놓은 웹컨텐츠 /upload 이곳에 저장하기 위해 경로를 지정한것. item.write(new File(dir, imgName));
							item.write(new File(dir, t_pic1));
							break;
						case 1 : t_pic2 = imgName;
							item.write(new File(dir, t_pic2));
							break;
						case 2 : t_pic3 = imgName;
							item.write(new File(dir, t_pic3));
							break;
						case 3 : t_pic4 = imgName;
							item.write(new File(dir, t_pic4));
							break;
						case 4 : t_pic5 = imgName;
							item.write(new File(dir, t_pic5));
							break;
					}					
					count++;
				}
			}
			System.out.println(t_name);
			System.out.println(t_select1);
			System.out.println(t_select2);
			System.out.println(sample6_address);
			System.out.println(sample6_detailAddress);
			System.out.println(t_hp);
			System.out.println(t_web);
			System.out.println(t_content);
			System.out.println(t_pic1);
			System.out.println(t_pic2);
			System.out.println(t_pic3);
			System.out.println(t_pic4);
			System.out.println(t_pic5);
			System.out.println(dir);
			if(fs.fileUpload(t_name, t_select1, t_select2, sample6_address, sample6_detailAddress, t_hp, t_web, t_content, t_pic1, t_pic2, t_pic3, t_pic4, t_pic5, dir)){
				out.print("<script>alert('등록 완료');location.href='./maintown.jsp';</script>");
			}else{
				out.print("<script>alert('등록 실패');history.back();</script>");
			}
		}
	}catch(Exception e){
		e.printStackTrace();
	}	
%>
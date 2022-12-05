package dangn.town.ttown;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.main.fileupload.fileuploadDTO;

import dangn.town.ttown.TtownDAO;
import dangn.town.ttown.TtownDTO;

public class FileService {
	private TtownDAO ttownDAO = null;
	
	public FileService() {
		ttownDAO = new TtownDAO();
	}
	
	public boolean fileUpload(String name, String select1,String select2, String address1,String address2, String hp, String web, String content, String pic1, String pic2, String pic3, String pic4, String pic5, String filepath) {
		TtownDTO ttownDTO = new TtownDTO();
		ttownDTO.setName(name);
		ttownDTO.setSelect1(select1);
		ttownDTO.setSelect2(select2);
		ttownDTO.setAddress(address1);
		ttownDTO.setDetailAddress(address2);
		ttownDTO.setHp(hp);
		ttownDTO.setWeb(web);
		ttownDTO.setContent(content);
		ttownDTO.setPic1(pic1);	// 원본 파일명
		ttownDTO.setPic2(pic2);
		ttownDTO.setPic3(pic3);
		ttownDTO.setPic4(pic4);
		ttownDTO.setPic5(pic5);
		ttownDTO.setFilepath(filepath);
		
		ttownDTO = ttownDAO.insertFile(ttownDTO);
		if(ttownDTO!=null) {
			return true;
		}else {
			return false;
		}
	}
	
}


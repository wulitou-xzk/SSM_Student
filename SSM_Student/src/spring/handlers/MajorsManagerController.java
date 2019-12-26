package spring.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.service.IMajorsService;

@Controller
@RequestMapping("/major")
public class MajorsManagerController {
	
	@Autowired(required=false)
	@Qualifier("majorService")
	private IMajorsService service;
	public void setService(IMajorsService service) {
		this.service = service;
	}
	
	// 检查某专业是否已经存在
	public int exitMajor(String major) {
		return service.exitMajor(major).getId();
	}
	

}

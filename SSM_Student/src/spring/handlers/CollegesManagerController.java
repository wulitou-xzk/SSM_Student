package spring.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.service.ICollegesService;

@Controller
@RequestMapping("/college")
public class CollegesManagerController {
	
	@Autowired(required=false)
	@Qualifier("collegeService")
	private ICollegesService service;

	public void setService(ICollegesService service) {
		this.service = service;
	}

	// ����ѧԺ���ж��Ƿ���ڸ�ѧԺ
	public int exitCollege(String college) {
		return service.exitCollege(college);
	}

}

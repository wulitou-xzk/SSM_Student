package spring.service;

import java.util.List;

import spring.beans.Majors;

public interface IMajorsService {
	Majors exitMajor(String major);

	Majors findMajById(int majorId);

	List<Majors> findAllMajor();

	List<Majors> findByColId(Integer collegeId);

}

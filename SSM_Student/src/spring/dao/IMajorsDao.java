package spring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import spring.beans.Majors;

@Repository("IMajorsDao")
public interface IMajorsDao {
	Majors selectMajorIdByName(String major);

	Majors selectMajorById(int majorId);

	List<Majors> selectAllMajors();

	List<Majors> selectMajorByColId(int collegeId);

}

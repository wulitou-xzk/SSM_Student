package spring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import spring.beans.Colleges;

@Repository("ICollegesDao")
public interface ICollegesDao {

	Colleges selectCollegeById(int colId);

	Colleges selectCollegeByName(String college);

	List<Colleges> selectAllColleges();
}

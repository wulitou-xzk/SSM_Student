package spring.service;

import java.util.List;

import spring.beans.Colleges;

public interface ICollegesService {
	int exitCollege(String college);

	Colleges findCollById(int colId);

	Colleges findCollByName(String college);

	List<Colleges> findAllColl();

}

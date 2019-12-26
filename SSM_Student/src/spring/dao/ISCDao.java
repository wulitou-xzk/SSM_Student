package spring.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import spring.beans.Student_Course;

@Repository("ISCDao")
public interface ISCDao {

	void deleteSC(@Param("sid")int sid);

//	void insertSC(Student_Course sc);
	void insertSC(@Param("sid")int sid, @Param("cid")int cid,@Param("tid")int tid, @Param("score")double score);

//	void updateSC(Student_Course sc);
	void updateSC(@Param("id")int id, @Param("sid")int sid, @Param("cid")int cid,@Param("tid")int tid, @Param("score")double score);

	void deleteSCBySidCid(@Param("sid")int sid, @Param("cid")int cid);
	
	void deleteSCByCid(@Param("cid")int cid);

	List<Student_Course> selectRevisedCourses(@Param("sid")int sid);

	Student_Course selectBySidCid(@Param("sid")int sid, @Param("cid")int cid);

	int countRevised(@Param("sid")int sid);
	
}

package com.li.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.li.entity.Student;

@Mapper
public interface StudentMapper {
	@Select("select * from tb_stu")
	List<Student> list();

}

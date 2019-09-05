package com.li.service;

import com.github.pagehelper.PageInfo;
import com.li.domain.Student;

public interface StudentService {

	PageInfo<Student> list(int pageNum, int pageSize);

}

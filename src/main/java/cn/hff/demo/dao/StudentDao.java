package cn.hff.demo.dao;

import cn.hff.demo.domain.Student;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentDao extends PagingAndSortingRepository<Student, Integer> {
}

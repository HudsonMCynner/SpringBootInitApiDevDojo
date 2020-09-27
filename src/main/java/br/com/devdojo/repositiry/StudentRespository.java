package br.com.devdojo.repositiry;

import br.com.devdojo.model.Student;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface StudentRespository extends PagingAndSortingRepository<Student, Long> {
    List<Student> findByNameIgnoreCaseContaining(String name);
}

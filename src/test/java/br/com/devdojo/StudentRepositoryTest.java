package br.com.devdojo;

import br.com.devdojo.model.Student;
import br.com.devdojo.repositiry.StudentRespository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTest {
    @Autowired
    private StudentRespository studentRespository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void createShouldPersistData () {
        Student student = new Student("hudson", "hudInTest@gmail.com");
        this.studentRespository.save(student);
        assertThat(student.getId()).isNotNull();
        assertThat(student.getName()).isEqualTo("hudson");
        assertThat(student.getEmail()).isEqualTo("hudInTest@gmail.com");
    }

    @Test
    public void deleteShouldRemoveData () {
        Student student = new Student("hudson", "hudInTest@gmail.com");
        this.studentRespository.save(student);
        studentRespository.delete(student);
        assertThat(studentRespository.findOne(student.getId())).isNull();
    }

    @Test
    public void updateShouldUpdateData () {
        Student student = new Student("hudson", "hudInTest@gmail.com");
        this.studentRespository.save(student);
        student.setName("blablabla");
        student.setEmail("blablabla@gmail.com");
        this.studentRespository.save(student);
        student = this.studentRespository.findOne(student.getId());
        assertThat(student.getName()).isEqualTo("blablabla");
        assertThat(student.getEmail()).isEqualTo("blablabla@gmail.com");
    }

    @Test
    public void findByNameIgnoreCaseContainingShouldIgnoreCase() {
        Student student = new Student("hudson", "hudInTest@gmail.com");
        Student student2 = new Student("hudson222", "hudInTest222@gmail.com");
        this.studentRespository.save(student);
        this.studentRespository.save(student2);
        List<Student> studentList = studentRespository.findByNameIgnoreCaseContaining("hudson");
        assertThat(studentList.size()).isEqualTo(2);
    }

    @Test
    public void createWhenNameIsNullShouldThrowConstraintViolationException () {
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("O campo nome do estudante é obrigatório");
        this.studentRespository.save(new Student());
    }

    @Test
    public void createWhenEmailIsNullShouldThrowConstraintViolationException () {
        thrown.expect(ConstraintViolationException.class);
        Student student = new Student();
        student.setName("hudson");
        this.studentRespository.save(student);
    }

    @Test
    public void createWhenEmailIsNotValidShouldThrowConstraintViolationException () {
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("Digite um email válido");
        Student student = new Student();
        student.setName("hudson");
        student.setEmail("hudson");
        this.studentRespository.save(student);
    }
}

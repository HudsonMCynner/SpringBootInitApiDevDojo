package br.com.devdojo.endpoint;

import br.com.devdojo.error.ResourceNotFoundException;
import br.com.devdojo.model.Student;
import br.com.devdojo.repositiry.StudentRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("v1")
public class StudentEndPoint {

    private final StudentRespository studentDAO;

    @Autowired
    public StudentEndPoint(StudentRespository studentDAO) {
        this.studentDAO = studentDAO;
    }

    @GetMapping(path = "protected/students")
    public ResponseEntity<?> listAll(Pageable pageable) {
        return new ResponseEntity<>(studentDAO.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "protected/students/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        System.out.println(userDetails);
        verifyIfStudentExist(id);
        return new ResponseEntity<>(studentDAO.findOne(id), HttpStatus.OK);
    }

    @PostMapping(path = "admin/students")
    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<?> save(@Valid @RequestBody Student student) {
        return new ResponseEntity<>(studentDAO.save(student), HttpStatus.OK);
    }

    @DeleteMapping(path = "admin/students/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        verifyIfStudentExist(id);
        studentDAO.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "admin/students")
    public ResponseEntity<?> update(@RequestBody Student student) {
        verifyIfStudentExist(student.getId());
        studentDAO.save(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("protected/students/findByName/{name}")
    public ResponseEntity<?> findStudentsByName (@PathVariable String name) {
        return new ResponseEntity<>(studentDAO.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
    }

    private void verifyIfStudentExist (Long id) {
        if (!studentDAO.exists(id))
            throw new ResourceNotFoundException("Student not found for ID: " + id);
    }
}

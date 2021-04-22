package cn.hff.demo.controller;

import cn.hff.demo.dao.StudentDao;
import cn.hff.demo.domain.Gender;
import cn.hff.demo.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Random;

@Controller
public class StudentController {

    @Autowired
    private StudentDao studentDao;

    @PostConstruct
    public void initData() {
        Random random = new Random();
        studentDao.save(Student.builder().name("小明").age(18).gender(Gender.MALE).birthday(LocalDate.now().minusYears(18).minusDays(random.nextInt(90))).build());
        studentDao.save(Student.builder().name("小红").age(19).gender(Gender.FEMALE).birthday(LocalDate.now().minusYears(19).minusDays(random.nextInt(90))).build());
        studentDao.save(Student.builder().name("小虎").age(20).gender(Gender.MALE).birthday(LocalDate.now().minusYears(20).minusDays(random.nextInt(90))).build());
        studentDao.save(Student.builder().name("小光").age(20).gender(Gender.MALE).birthday(LocalDate.now().minusYears(20).minusDays(random.nextInt(90))).build());
        studentDao.save(Student.builder().name("张三").age(18).gender(Gender.MALE).birthday(LocalDate.now().minusYears(18).minusDays(random.nextInt(90))).build());
        studentDao.save(Student.builder().name("李四").age(20).gender(Gender.MALE).birthday(LocalDate.now().minusYears(20).minusDays(random.nextInt(90))).build());
        studentDao.save(Student.builder().name("王五").age(20).gender(Gender.MALE).birthday(LocalDate.now().minusYears(20).minusDays(random.nextInt(90))).build());
        studentDao.save(Student.builder().name("赵六").age(20).gender(Gender.FEMALE).birthday(LocalDate.now().minusYears(20).minusDays(random.nextInt(90))).build());
    }


    @RequestMapping("/")
    public String index(Pageable pageable, Model model) {
        Page<Student> page = studentDao.findAll(pageable);
        model.addAttribute("students", page.toList());
        model.addAttribute("pager", page);
        return "listStudent";
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam(required = false) Integer id, Model model) {
        Student student = new Student();
        if (id != null) {
            student = studentDao.findById(id).orElse(student);
        }
        model.addAttribute("student", student);
        return "editStudent";
    }

    @RequestMapping("/delete")
    public String delete(Integer id) {
        studentDao.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/save")
    public String save(Student student) {
        studentDao.save(student);
        return "redirect:/";
    }

}

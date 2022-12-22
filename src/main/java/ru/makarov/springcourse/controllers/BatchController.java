package ru.makarov.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.makarov.springcourse.dao.PersonDAO;

/**
 * @author Andrey Makarov on 22.12.2022
 */
@Controller
@RequestMapping("/test-batch-update")
public class BatchController {

    private final PersonDAO personDAO;

    @Autowired
    public BatchController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public  String index() {
        return "batch/index";
    }

    @GetMapping("/without")
    public String whithoutBatch() {
        personDAO.testMultipleUpdate();
        return "redirect:/people";
    }

    @GetMapping("/with")
    public String whithBatch() {
        personDAO.testBatchUpdate();
        return "redirect:/people";
    }


}

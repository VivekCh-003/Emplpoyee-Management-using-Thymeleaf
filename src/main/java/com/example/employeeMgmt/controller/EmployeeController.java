package com.example.employeeMgmt.controller;

import com.example.employeeMgmt.entity.Employee;
import com.example.employeeMgmt.service.EmployeeService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
/*
    @GetMapping("/employees")
    private List<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("employees/{id}")
    private Optional<Employee> findById(@PathVariable int id){
        return employeeService.findById(id);
    }

    @PostMapping("employees")
    private Employee add(@RequestBody Employee employee){
        employee.setId(0);
        return employeeService.save(employee);
    }

    @PutMapping("employees")
    private Employee update(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @DeleteMapping("employees/{id}")
    private String delete(@PathVariable int id){
        Optional<Employee> theEmployee = employeeService.findById(id);

        if(theEmployee == null){
            throw new RuntimeException("No employee found");
        }
        employeeService.deleteById(id);

        return "Employee deleted";
    }
 */

    @GetMapping("/list")
    public String listEmployees(Model theModel){
        List<Employee> employeesList = employeeService.findAll();

        theModel.addAttribute("employees",employeesList);

        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        Employee theEmployee = new Employee();

        theModel.addAttribute("employee",theEmployee);

        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){
        employeeService.save(theEmployee);

        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    public String updateEmployee(@RequestParam("employeeId")int id, Model theModel){
        Optional<Employee> theEmployee = employeeService.findById(id);

        theModel.addAttribute("employee",theEmployee);

        return "employees/employee-form";

    }


    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int id){
        employeeService.deleteById(id);

        return "redirect:/employees/list";
    }

}

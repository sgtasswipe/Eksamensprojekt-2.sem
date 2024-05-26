package com.example.eksamensprojektbilabonnement.services;

import com.example.eksamensprojektbilabonnement.models.Employee;
import com.example.eksamensprojektbilabonnement.repositories.EmployeeRepository;
import com.example.eksamensprojektbilabonnement.utilities.EmployeePageMapping;
import com.example.eksamensprojektbilabonnement.utilities.EmployeeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeeService {

    private final EmployeePageMapping employeePageMapping;
    //Reads from a HashMap (which is what employeePages is)
    // are thread-safe as long as no writes are happening simultaneously.
    // Since the common operations here (getPage) are read-only,
    // there are no threading issues.


    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeePageMapping employeePageMapping) {
        this.employeePageMapping =  employeePageMapping;
    }

    public String checkPassword(String email, String password) {
        String dbPassword = employeeRepository.checkPass(email);
        if(password.equals(dbPassword)){
            return "UserApproved";
        } else if (dbPassword.equals("UserNotFound")){
            return "NoUserFound";
        } else {
            return "WrongPassWord";
        }
    }

    public String getPageForEmployee(EmployeeType type) {
        return employeePageMapping.getPage(type);
    }

    public Employee getEmployee(String email){
        return employeeRepository.getEmployee(email);
    }


}

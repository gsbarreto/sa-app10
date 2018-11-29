package utfpr.edu.br;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import lombok.Data;

public class Function {

    ArrayList<Employee> list;

    public Function() {
        list = new ArrayList<Employee>();
    }

    @FunctionName("create-new-employee")
    public Employee createFuncionario(@HttpTrigger(name = "create-new-employee", methods = {
        HttpMethod.POST}, authLevel = AuthorizationLevel.FUNCTION, route = "employee") Employee employee) {

        Employee newEmployee = new Employee();
        newEmployee.setId(employee.getId());
        newEmployee.setName(employee.getName());
        newEmployee.setAge(employee.getAge());
        newEmployee.setSalary(employee.getSalary());

        return newEmployee;
    }

    @FunctionName("read-all-employees")
    public ArrayList<Employee> readAllEmployee(@HttpTrigger(name = "create-new-employee", methods = {
        HttpMethod.POST}, authLevel = AuthorizationLevel.FUNCTION, route = "employee") String x) {
        return list;
    }

    @FunctionName("remove-employee")
    public ArrayList<Employee> deleteEmplyee(@HttpTrigger(name = "remove-employee", methods = {
        HttpMethod.DELETE}, authLevel = AuthorizationLevel.FUNCTION, route = "employee") Employee employee) {

        for(Employee i: list){
            if(i.getId() == employee.getId()){
                list.remove(list.indexOf(i));
            }
        }

        return list;
    }
    
    @FunctionName("update-employee")
    public ArrayList<Employee> updateEmployee(@HttpTrigger(name = "update-employee", methods = {
        HttpMethod.PUT}, authLevel = AuthorizationLevel.FUNCTION, route = "employee") Employee employee) {

        for(Employee i: list){
            if(i.getId() == employee.getId()){
                list.add(list.indexOf(i),employee);
            }
        }

        return list;
    }
}

@Data
class Employee {

    private Long id;
    private String name;
    private int age;
    private Double salary;
    
}

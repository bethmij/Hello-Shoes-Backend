package lk.ijse.gdse66.HelloShoes.service;

import lk.ijse.gdse66.HelloShoes.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeDetails(String code);

    EmployeeDTO saveEmployee(EmployeeDTO EmployeeDTO);

    void updateEmployee(EmployeeDTO EmployeeDTO);

    void deleteEmployee(String id);

    List<String> getEmployeeCodes();

    String getNameByID(String id);

    String getEmployeeCode();
}

package lk.ijse.gdse66.HelloShoes.service.impl;

import lk.ijse.gdse66.HelloShoes.dto.EmployeeDTO;
import lk.ijse.gdse66.HelloShoes.repository.EmployeeRepo;
import lk.ijse.gdse66.HelloShoes.service.EmployeeService;
import lk.ijse.gdse66.HelloShoes.service.exception.NotFoundException;
import lk.ijse.gdse66.HelloShoes.service.util.GenerateID;
import lk.ijse.gdse66.HelloShoes.service.util.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    Transformer transformer;

    @Autowired
    GenerateID generateID;


    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepo.findAll().stream()
                .map(employee -> transformer.fromEmployeeEntity(employee))
                .toList();
    }

    @Override
    public EmployeeDTO getEmployeeDetails(String code) {
        if(!employeeRepo.existsById(code)){
            throw new NotFoundException("Employee Code: " + code + " does not exist");
        }

        return transformer.fromEmployeeEntity(employeeRepo.findById(code).get());

    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        employeeDTO.setEmployeeCode(generateID.generateEmployeeCode());

        return transformer.fromEmployeeEntity(
                employeeRepo.save(
                        transformer.toEmployeeEntity(employeeDTO)));
    }

    @Override
    public void updateEmployee(EmployeeDTO employeeDTO) {
        if(!employeeRepo.existsById(employeeDTO.getEmployeeCode())){
            throw new NotFoundException("Employee Code: " + employeeDTO.getEmployeeCode() + " does not exist");
        }

        employeeRepo.save(transformer.toEmployeeEntity(employeeDTO));
    }

    @Override
    public void deleteEmployee(String id) {
        if(!employeeRepo.existsById(id)){
            throw new NotFoundException("Employee Code: " + id + " does not exist");
        }
        employeeRepo.deleteById(id);

    }

//    @Override
//    public List<String> GetSupplierCode() {
//        return null;
//    }
}

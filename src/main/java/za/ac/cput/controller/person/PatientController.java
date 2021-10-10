package za.ac.cput.controller.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.entity.person.Patient;
import za.ac.cput.factory.person.PatientFactory;
import za.ac.cput.services.person.PatientService;

import java.util.Set;

@RestController
    @RequestMapping("/patient")
    public class PatientController {
        @Autowired
        private PatientService patientService;

        @RequestMapping(value = "/create", method = RequestMethod.POST)
        public Patient create(@RequestBody Patient patient){
            Patient newPatient = PatientFactory.build(patient.getFirstName(),patient.getLastName(),patient.getContactNumber(),patient.getAddressNumber());
            return patientService.create(newPatient);
        }

        @RequestMapping("/read/{id}")
        public Patient read(@PathVariable String id){
            return patientService.read(id);
        }

        @PostMapping("/update")
        public Patient update(@RequestBody Patient patient){
            return patientService.update(patient);
        }

        @DeleteMapping("/delete/{id}")
        public boolean delete(@PathVariable String id){
            return patientService.delete(id);
        }

        @GetMapping("/getall")
        public Set<Patient> getPatients(){
            return patientService.getAll();
        }
    }

package za.ac.cput.controller.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.entity.person.Doctor;
import za.ac.cput.factory.person.DoctorFactory;
import za.ac.cput.services.person.DoctorService;

import java.util.Set;

/*
 * Doctor Entity
 * Name :Mpumelelo Magagula
 * Student Number:218136021
 * Description: This entity display all the records of doctors working in the clinic and their occupation level and status.
 * Date:November 2021
 * */
@RestController
@RequestMapping("/Doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    //@PostMapping("/create")
    public Doctor create(@RequestBody Doctor doctor){
        Doctor newDoctor = DoctorFactory.createDoctor(doctor.getFirstName(), doctor.getLastName(), doctor.getEmailAddress(), doctor.getContactNumber());
        return doctorService.create(newDoctor);

    }
    @GetMapping("/read/{id}")
    public  Doctor read(@PathVariable String id){

        return doctorService.read(id);
    }

    @PostMapping("/update")
    public Doctor update(@RequestBody Doctor doctor){
        return doctorService.update(doctor);
    }


    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable String id){
        return doctorService.delete(id);
    }


    @GetMapping ("/getall")
    public Set<Doctor> getAll(){
        return doctorService.getAll();
    }

//    @GetMapping("/getallwitha")
//    public Set<Doctor> getallwitha(){
//        return DoctorService.getAllDoctorsStartWithA();
//    }


}

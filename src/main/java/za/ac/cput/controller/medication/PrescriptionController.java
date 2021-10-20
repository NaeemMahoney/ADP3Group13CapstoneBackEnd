package za.ac.cput.controller.medication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.entity.medication.Prescription;
import za.ac.cput.factory.medication.PrescriptionFactory;
import za.ac.cput.services.medication.PrescriptionService;

import java.util.Set;

@RestController
@RequestMapping("/prescription")
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;

   // @PostMapping("/create")
   @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Prescription create(@RequestBody Prescription prescription) {
        Prescription newPrescription = PrescriptionFactory.buildPrescription(prescription.getName(), prescription.getPatientNumber(), prescription.getBill(), prescription.getDosage());
        return prescriptionService.create(newPrescription);
    }

    @GetMapping("/read/{prescriptionID}")
    public Prescription read(@PathVariable String prescriptionID) {
        return prescriptionService.read(prescriptionID);
    }
    @PostMapping("/update")
    public Prescription update(@RequestBody Prescription prescription) {return prescriptionService.update(prescription);}

    @DeleteMapping("/delete/{prescriptionID}")
    public boolean delete(@PathVariable String prescriptionID) {
        return prescriptionService.delete(prescriptionID);}

    @GetMapping("/getall")
    public Set<Prescription> getAll() {
        return prescriptionService.getAll();
    }

    @GetMapping("/getallwithp")
    public Set<Prescription> getAllwithp() {
        return prescriptionService.getAllPrescriptionStartsWithP();

    }

}


package za.ac.cput.controller.general;
/* AppointmentController.java
   controller class for appointment
   Author: Nolusindiso Makosa (219023557)
   Due Date: 20 October 2021
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.entity.general.Appointment;
import za.ac.cput.factory.general.AppointmentFactory;
import za.ac.cput.services.general.AppointmentService;

import java.util.Set;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    //@RequestMapping(value = "create", method = RequestMethod.POST)
    @PostMapping("/create")
    public Appointment create(@RequestBody Appointment appointment) {
        Appointment newAppointment = AppointmentFactory.build(appointment.getDate(), appointment.getTime(),
                appointment.getReason(), appointment.getPatientNumber(), appointment.getDoctorNumber(), appointment.getReceptionistNumber());
        return appointmentService.create(newAppointment);
    }

    @GetMapping("/read{appointmentID}")
    public Appointment read(@PathVariable String appointmentID) { return appointmentService.read(appointmentID); }

    @PostMapping("/update")
    public Appointment update(@RequestBody Appointment appointment) {
        return appointmentService.update(appointment);
    }

    @PostMapping("/delete/{appointmentID}")
    public boolean delete(@PathVariable String appointmentID) {
        return appointmentService.delete(appointmentID);
    }

    @GetMapping("/getAll")
    public Set<Appointment> getAll(){
        return appointmentService.getAll();
    }
}

package za.ac.cput.services.general;

import za.ac.cput.entity.general.Appointment;
import za.ac.cput.services.iService;

import java.util.Set;

public interface IAppointmentService extends iService<Appointment, String> {
    public Set<Appointment> getAll();
//    public Appointment getAppointmentByReason(String reason);
//    public Appointment getAppointmentByDate(String date);
}

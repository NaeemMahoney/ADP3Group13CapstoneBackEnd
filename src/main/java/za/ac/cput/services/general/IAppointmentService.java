package za.ac.cput.services.general;
/* IAppointmentService.java
   Appointment service interface class
   Author: Nolusindiso Makosa (219023557)
   Due Date: 20 October 2021
*/
import za.ac.cput.entity.general.Appointment;
import za.ac.cput.services.iService;

import java.util.Set;

public interface IAppointmentService extends iService<Appointment, String> {
    public Set<Appointment> getAll();
//    public Appointment getAppointmentByReason(String reason);
//    public Appointment getAppointmentByDate(String date);
}

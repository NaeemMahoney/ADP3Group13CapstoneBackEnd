package za.ac.cput.services.general;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.entity.general.Appointment;
import za.ac.cput.repository.general.AppointmentRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AppointmentService implements IAppointmentService{
    private static AppointmentService service = null;

    @Autowired
    private AppointmentRepository repository;

    @Override
    public Appointment create(Appointment appointment){ return this.repository.save(appointment); }

    @Override
    public Appointment read(String appointmentID){ return this.repository.findById(appointmentID).orElse(null); }

    @Override
    public Appointment update(Appointment appointment){
        if(this.repository.existsById(appointment.getAppointmentID()))
            return this.repository.save(appointment);
        return null;
    }

    @Override
    public boolean delete(String appointmentID){
        this.repository.deleteById(appointmentID);
        if(this.repository.existsById(appointmentID)){
            System.out.println("Appointment: "+appointmentID+" not Deleted");
            return false;
        }
        else{
            System.out.println("Appointment Deleted");
            return true;
        }
    }

    @Override
    public Set<Appointment> getAll() {
        return this.repository.findAll().stream().collect(Collectors.toSet());
    }

   /* @Override
    public Appointment getAppointmentByReason(String reason) {

        return null;
    }

    @Override
    public Appointment getAppointmentByDate(String date) {
        return null;
    }*/
}

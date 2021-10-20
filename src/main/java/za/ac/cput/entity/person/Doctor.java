package za.ac.cput.entity.person;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/*
 * Doctor Entity
 * Name :Mpumelelo Magagula
 * Student Number:218136021
 * Description: This entity display all the records of doctors working in the clinic and their occupation level and status.
 * Date:November 2021
 * */
@Entity
//@Table(name="Doctor")
public class Doctor implements Serializable {

    @Id
    private String id,  firstName, lastName, emailAddress, contactNumber;

    public Doctor() {

    }
    
    private Doctor(Builder builder) {
        this.id =builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.emailAddress = builder.emailAddress;
        this.contactNumber = builder.ContactNumber;
    }



    @Override
    public String toString() {
        return "Builder{" +
                "doctorID='" + id    + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", ContactNumber='" + contactNumber + '\'' +
                '}';
    }

    public String getID(){
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }


    public  static class Builder{
        private String id, firstName, lastName, emailAddress, ContactNumber;

        public Builder setDoctorNumber(String id) {
            this.id = id;

            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;

            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;

            return this;
        }

        public Builder setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;

            return this;
        }

        public Builder setContactNumber(String contactNumber) {
            ContactNumber = contactNumber;

            return this;
        }

        public Doctor Build(){
            return new Doctor(this ) ;
        }

        public  Builder copy(Doctor doctor){
            this.id = doctor.id;
            this.firstName = doctor.firstName;
            this.lastName = doctor.lastName;
            this.emailAddress = doctor.emailAddress;
            this.ContactNumber = doctor.contactNumber;

            return  this;
        }

    }
}

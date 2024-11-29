package application;

public class RegularAppointment extends Appointment {

    // Constructor
    public RegularAppointment(String customerName, String contactInfo, String barber, String date, String time, String barbershop) {
        super(customerName, contactInfo, barber, date, time, barbershop, 0.0);  // Regular appointments have no discount
    }
}

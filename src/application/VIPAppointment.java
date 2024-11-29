package application;

public class VIPAppointment extends Appointment {
    private int discount;

   
    public VIPAppointment(String customerName, String contactInfo, String barber, String date, String time, String barbershop, int discount) {
        super(customerName, contactInfo, barber, date, time, barbershop, discount); 
        this.discount = discount;
    }

    
    @Override
    public double getDiscount() {
        return discount / 100.0;  
    }

    @Override
    public String toString() {
        return super.getCustomerName() + " (" + discount + "% Discount)";
    }
}

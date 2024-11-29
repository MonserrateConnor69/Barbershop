package application;

public class VIPAppointment extends Appointment {
    private int discount;

    // Constructor
    public VIPAppointment(String customerName, String contactInfo, String barber, String date, String time, String barbershop, int discount) {
        super(customerName, contactInfo, barber, date, time, barbershop, discount); // Call the parent constructor
        this.discount = discount;
    }

    // Make sure getDiscount() returns a double
    @Override
    public double getDiscount() {
        return discount / 100.0;  // Convert discount to a percentage
    }

    @Override
    public String toString() {
        return super.getCustomerName() + " (" + discount + "% Discount)";
    }
}

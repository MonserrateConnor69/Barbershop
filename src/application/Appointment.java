package application;

public class Appointment {
    private String customerName;
    private String contactInfo;
    private String barber;
    private String date;
    private String time;
    private String barbershop;
    private double discount;

   
    public Appointment(String customerName, String contactInfo, String barber, String date, String time, String barbershop, double discount) {
        this.customerName = customerName;
        this.contactInfo = contactInfo;
        this.barber = barber;
        this.date = date;
        this.time = time;
        this.barbershop = barbershop;
        this.discount = discount;
    }

    // Getters
    public String getCustomerName() {
        return customerName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getBarber() {
        return barber;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getBarbershop() {
        return barbershop;
    }

  
    public double getDiscount() {
        return discount;
    }
}

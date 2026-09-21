package lw01.unguided;

public class LaptopRental extends Rental {

    public LaptopRental(String id, int days, int units) {
        super(id, days);
    }

    @Override
    public int calculateCharge() {
        int days = getDays();
        int charge = 0;
        if (days <= 2) {
            charge = days * 40000;
        } else {
            charge = (2 * 40000) + ((days - 2) * getUnits());
        }
        
        charge += 0;
        return charge;
    }

    @Override
    public String label() {
        return "Laptop";
    }
}
package lw01.unguided;

public abstract class Rental implements Chargeable {
    private String id;
    private int days;
    private int units;

    protected Rental(String id, int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Days must be a positive integer.");
        }
        this.id = id;
        this.days = days;
    }

    public String getId() {
        return id;
    }

    public int getDays() {
        return days;
    }

    public int getUnits() {
        return units;
    }

    @Override
    public abstract int calculateCharge();

    public int calculateCharge(int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Days must be a positive integer.");
        }
        return days * calculateCharge();
    }

    public String label() {
        return "Rent";
    }

    public String summary() {
        return id + " | " + label() + " | " + calculateCharge();
    }
}

package fr.nosseb.pharmaStock.models;

import java.util.GregorianCalendar;

/**
 *
 * @author Loan Veyer
 * @date 20/03/2019
 *
 */

public class ModExpendable {
    private int quantity;
    private String serialNumber;
    private GregorianCalendar lapsingDate;
    private ModCategory category;
    private ModLocation location;
    private ModLocation owner;

    /**
     * Constructor for Expendable elements.
     * @param quantity the quantity of the given expendable
     * @param serialNumber the serial number of the given element
     * @param lapsingDate the lapsing date of the given element
     * @param category the category of the given element, as a {@code ModCategory} object
     * @param location the location of the given element, as a {@code ModLocation} object
     * @param owner the owner of the given element, as a {@code ModLocation} object
     */
    public ModExpendable(int quantity, String serialNumber, GregorianCalendar lapsingDate,
                         ModCategory category, ModLocation location, ModLocation owner){

        if (quantity >= 0) {
            this.quantity = quantity;
        } else {
            throw new IllegalArgumentException("Quantity cannot be less than zero !");
        }
        this.serialNumber = serialNumber;
        this.lapsingDate = lapsingDate;
        this.category = category;
        this.location = location;
        this.owner = owner;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public GregorianCalendar getLapsingDate() {
        return this.lapsingDate;
    }

    public ModCategory getCategory() {
        return category;
    }

    public ModLocation getLocation() {
        return location;
    }

    public ModLocation getOwner() {
        return owner;
    }
}

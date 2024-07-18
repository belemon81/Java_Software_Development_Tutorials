package a1_2001040208;

import common.PatronType;

import java.util.Date;
import java.util.Objects;

// A library patron
public class Patron {
    // supportive attributes
    private static int PATRON_NO = 0;
    private final String patronID;
    private final String name;
    private final Date dob;
    private final String email;
    private final String phoneNo;
    private final PatronType patronType;

    public Patron(String name, Date dob, String email, String phoneNo, PatronType patronType) {
        if (!validateName(name)) {
            throw new IllegalArgumentException("Patron.init: Invalid name!");
        } else if (!validateDob(dob)) {
            throw new IllegalArgumentException("Patron.init: Invalid date of birth!");
        } else if (!validateEmail(email)) {
            throw new IllegalArgumentException("Patron.init: Invalid email!");
        } else if (!validatePhoneNo(phoneNo)) {
            throw new IllegalArgumentException("Patron.init: Invalid phone number!");
        } else if (!validatePatronType(patronType)) {
            throw new IllegalArgumentException("Patron.init: Invalid patron type!");
        } else {
            PATRON_NO++;

            this.patronID = generatePatronID();
            this.patronType = patronType;

            this.name = name;
            this.dob = dob;
            this.email = email;
            this.phoneNo = phoneNo;
        }
    }

    public String getPatronID() {
        return patronID;
    }

    public PatronType getPatronType() {
        return patronType;
    }

    private boolean validateName(String name) {
        if (name == null || name.length() < 2) return false;
        // perhaps the patron name should not contain numbers...?
        for (char c : name.toCharArray()) {
            if (Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private boolean validateDob(Date dob) {
        return dob != null;
    }

    private boolean validateEmail(String email) {
        // min of 3 chars: A@B
        return email != null && email.length() >= 3 && email.contains("@");
    }

    private boolean validatePhoneNo(String phoneNo) {
        if (phoneNo == null || phoneNo.length() == 0) return false;
        for (char c : phoneNo.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private boolean validatePatronType(PatronType patronType) {
        return patronType != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patron patron = (Patron) o;
        return Objects.equals(patronID, patron.patronID)
                && Objects.equals(name, patron.name)
                && Objects.equals(dob, patron.dob)
                && Objects.equals(email, patron.email)
                && Objects.equals(phoneNo, patron.phoneNo) && patronType == patron.patronType;
    }

    // TODO:  generatePatronID()
    //      Generate a unique ID for each Patron with number auto-incremented from 1
    //      e.g. P001, ..., P100, ...
    public String generatePatronID() {
        if (PATRON_NO < 10) {
            return "P00" + PATRON_NO;
        } else if (PATRON_NO < 100) {
            return "P0" + PATRON_NO;
        } else {
            return "P" + PATRON_NO;
        }
    }

//    public static void main(String[] args) {
//        Patron patron1 = new Patron("Amber Valentine", new Date(98, Calendar.OCTOBER, 22), "amber@gmail.com", "0123456789", PatronType.PREMIUM);
//        Patron patron2 = new Patron("Amber Valentine", new Date(98, Calendar.OCTOBER, 22), "amber@gmail.com", "0123456789", PatronType.PREMIUM);
//        System.out.println(patron2.patronID);
//    }
}

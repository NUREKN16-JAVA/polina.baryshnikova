package ua.nure.pbaryshnikova.usermanagement;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

class User implements Serializable{
    private Long id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;

    public User() {
    }

    public User(Long id, String firstName, String lastName, Date dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Calculates user's age
     * Uses ChronoUnit static method to calculate difference between current date and birth day
     * No parameters
     * @return age (years)
     */
    public int getAge() {
        LocalDate birthLocalDate = dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentLocalDate = LocalDate.now(ZoneId.systemDefault());
        return (int) ChronoUnit.YEARS.between(birthLocalDate, currentLocalDate);
    }



    public String getFullName() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(lastName)
                .append(", ")
                .append(firstName);
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(dateOfBirth, user.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, dateOfBirth);
    }
}
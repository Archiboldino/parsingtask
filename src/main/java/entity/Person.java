package entity;

import java.util.Objects;

public class Person {
    private Integer id;
    private String name;
    private String address;
    private String education;
    private Integer cash;

    public Person(String name, String address, Integer cash) {
        this.name = name;
        this.address = address;
        this.cash = cash;
    }

    public Person(Integer id, String name, String address, Integer cash, String education) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.cash = cash;
        this.education = education;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCash() {
        return cash;
    }

    public void setCash(Integer cash) {
        this.cash = cash;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(name, person.name) &&
                Objects.equals(address, person.address) &&
                Objects.equals(education, person.education) &&
                Objects.equals(cash, person.cash);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, address, education, cash);
    }

    @Override
    public String toString() {
        return String.format("Person={id=%d, name=%s, address=%s, cash=%s, education=%s}",
                id, name, address, cash, education);
    }
}

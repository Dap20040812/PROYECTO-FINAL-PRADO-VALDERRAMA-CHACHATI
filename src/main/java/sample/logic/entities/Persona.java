package sample.logic.entities;

import sample.logic.PersonaException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Persona extends Exportable implements Serializable {

    private String name;
    private String lastName;
    private int age;
    private String profession;
    private String role;
    private String status;
    private String description;

    public Persona(String name, String lastName, String age, String profession, String role, String status, String description) throws PersonaException {
        this.name = name;
        this.lastName = lastName;
        this.profession = profession;
        this.role = role;
        this.status = status;
        this.description = description;
        this.setAge(age);
    }


    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName1){this.lastName = lastName1;}

    public String getRole() {
        return role;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public void setAge(String ageInput) throws PersonaException {
        try {
            int age = Integer.parseInt(ageInput);

            if (age < 0) throw new PersonaException(PersonaException.BAD_AGE_LOWER);
            if (age > 120) throw new PersonaException(PersonaException.BAD_AGE_UPPER);

            this.age = age;
        } catch (NumberFormatException er) {
            throw new PersonaException(PersonaException.BAD_AGE + " : " + er.getMessage());
        }
    }

    public String getAge() {
        return ""+this.age;
    }

    public String getProfession() {
        return profession;
    }


    @Override
    public String toString() {

        return String.format("Name = %s, LastName = %s, Age = %s, Profession = %s, Role = %s, Status = %s, Description = %s", this.name, this.lastName, this.age, this.profession,this.role,this.status,this.description);
    }

    @Override
    public List<String> toListString() {
        List<String> result = new ArrayList<>();
        result.add(this.name);
        result.add(this.lastName);
        result.add(this.getAge());
        result.add(this.profession);
        result.add(this.role);
        result.add(this.status);
        result.add(this.description);

        return result;
    }

    @Override
    public String getHeader() {

        return "name,LastName,age,profession,role,status,description";
    }
}

package sample.logic.entities;

import javafx.scene.image.Image;
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
    private String photo;
    private String responsable;

    public Persona(String name, String lastName, String age, String profession, String role, String status, String description, String photo, String responsable) throws PersonaException {
        this.name = name;
        this.lastName = lastName;
        this.profession = profession;
        this.role = role;
        this.status = status;
        this.description = description;
        this.photo = photo;
        this.setAge(age);
        this.responsable = responsable;
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

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    /**
     * Reviza que la edad de la persona sea real
     * @param ageInput
     * @throws PersonaException
     */
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

        return String.format("Name = %s, LastName = %s, Age = %s, Profession = %s, Role = %s, Status = %s, Description = %s, Photo = %s, responsable = %s", this.name, this.lastName, this.age, this.profession,this.role,this.status,this.description,this.photo,this.responsable);
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
        result.add(this.photo);
        result.add(this.responsable);

        return result;
    }

    @Override
    public String getHeader() {

        return "name,LastName,age,profession,role,status,description,photo,responsable";
    }
}

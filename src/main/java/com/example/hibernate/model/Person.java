package com.example.hibernate.model;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name="people"
)
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Serializable {
    
    private Integer personId;
     private String firstName;
     private String middleName;
     private String lastName;
     private LocalDate birthDate;


      public Person() {
    }

	
    public Person(String firstName) {
        this.firstName = firstName;
    }
    public Person(String firstName, String middleName, String lastName, LocalDate birthDate) {
       this.firstName = firstName;
       this.middleName = middleName;
       this.lastName = lastName;
       this.birthDate = birthDate;
      
    }
   
     public Person(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }


     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="person_Id", unique=true, nullable=false)
    public Integer getPersonId() {
        return this.personId;
    }
    
    public void setPersonId(Integer authorId) {
        this.personId = authorId;
    }

    
    @Column(name="first_name", nullable=false, length=100)
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    
    @Column(name="middle_name", length=50)
    public String getMiddleName() {
        return this.middleName;
    }
    
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    
    @Column(name="last_name", length=100)
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Column(name="birth_date", length=10)
    public LocalDate getBirthDate() {
        return this.birthDate;
    }
    
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }


    @Override
    public String toString() {
        return "Person [personId=" + personId + ", firstName=" + firstName + ", middleName=" + middleName
                + ", lastName=" + lastName + ", birthDate=" + birthDate + "]";
    }




  


}

package io.dropwizard.core;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "people")
@NamedQueries({
    @NamedQuery(
        name = "io.dropwizard.core.Person.findAll",
        query = "Select p From Person p"
    )
})
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullName", nullable = false)
    private String fullName;

    @Column(name = "jobTitle", nullable = false)
    private String jobTitle;

    @Column(name = "yearBorn")
    @Min(value = 0)
    @Max(value = 999)
    private int yearBorn;

    public Person() {
    }

    public Person(String fullName, String jobTitle, int yearBorn) {
        this.fullName = fullName;
        this.jobTitle = jobTitle;
        this.yearBorn = yearBorn;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public int getYearBorn() {
        return yearBorn;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setYearBorn(int yearBorn) {
        this.yearBorn = yearBorn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return yearBorn == person.yearBorn
               && id.equals(person.id)
               && Objects.equals(fullName, person.fullName)
               && Objects.equals(jobTitle, person.jobTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, jobTitle, yearBorn);
    }

    @Override
    public String toString() {
        return "Person{" +
               "id=" + id +
               ", fullName='" + fullName + '\'' +
               ", jobTitle='" + jobTitle + '\'' +
               ", yearBorn=" + yearBorn +
               '}';
    }
}

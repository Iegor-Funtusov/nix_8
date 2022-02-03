package ua.com.alevel.final_project.persistence.entity.books;

import com.neovisionaries.i18n.CountryCode;
import ua.com.alevel.final_project.persistence.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "publishers")
public class Publisher extends BaseEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    private CountryCode country;

    public Publisher() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryCode getCountry() {
        return country;
    }

    public void setCountry(CountryCode country) {
        this.country = country;
    }
}

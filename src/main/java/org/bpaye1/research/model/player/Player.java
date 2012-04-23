package org.bpaye1.research.model.player;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="PLAYER")
public class Player {
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Long id;
	
	@NotEmpty()
	@Column(name="LAST_NAME")
	private String lastName;
	
	@NotEmpty()
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Pattern(regexp="[A-Za-z0-9._%=-]+@[A-za-z0-9.-]+\\.[A-Za-z]{2,4}")
	@Column(name="EMAIL")
	private String email;
	
	@NotNull
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Past
	@Column(name="DATE_OF_BIRTH")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate dateOfBirth;
	
	@Embedded
	private Address address;
	
	@Column(name="PHONE_NUMBER")
	private String phoneNumber;
	
	@NotNull()
	@Column(name="JERSEY_NUMBER")
	private Integer jerseyNumber;
	
	@Column(name="HOME_TOWN")
	private String homeTown;
	
	@Enumerated(EnumType.STRING)
	@Column(name="POSITION")
	private Position position;
	
	@Enumerated(EnumType.STRING)
	@Column(name="STATUS")
	private Status status;
	
	public Player(){
		this.status = Status.ACTIVE;
	}

	public Player(String lastName, String firstName, LocalDate dateOfBirth,
			Integer jerseyNumber) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.dateOfBirth = dateOfBirth;
		this.jerseyNumber = jerseyNumber;
		this.status = Status.ACTIVE;
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

    public String getFullName(){
        return firstName + " " + lastName;
    }
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getJerseyNumber() {
		return jerseyNumber;
	}
	
	public void setJerseyNumber(Integer jerseyNumber) {
		this.jerseyNumber = jerseyNumber;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public String getHomeTown() {
		return homeTown;
	}

	public void setHomeTown(String homeTown) {
		this.homeTown = homeTown;
	}
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Long getId() {
		return id;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (dateOfBirth != null ? !dateOfBirth.equals(player.dateOfBirth) : player.dateOfBirth != null) return false;
        if (email != null ? !email.equals(player.email) : player.email != null) return false;
        if (firstName != null ? !firstName.equals(player.firstName) : player.firstName != null) return false;
        if (lastName != null ? !lastName.equals(player.lastName) : player.lastName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lastName != null ? lastName.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        return result;
    }

    @Override
	public String toString() {
		return "Player [id=" + id + ", lastName=" + lastName + ", firstName="
				+ firstName + ", dateOfBirth=" + dateOfBirth
				+ ", jerseyNumber=" + jerseyNumber + "]";
	}
}

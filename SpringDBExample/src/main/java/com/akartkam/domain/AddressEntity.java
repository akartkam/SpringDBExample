package com.akartkam.domain;

import java.io.Serializable;

import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * The address of a User.
 *
 * An instance of this class is always associated with only
 * one <tt>User</tt> and depends on that parent objects lifecycle,
 * it is a component. Of course, other entity classes can also
 * embed addresses.
 *
 * @see User
 * @author Christian Bauer
 */

@Entity
@Table(name = "ADDRESS_ENT")
public class AddressEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue
	//@GeneratedValue(generator = "myForeignGenerator")
	//@org.hibernate.annotations.GenericGenerator(
	//name = "myForeignGenerator",
	//strategy = "foreign",
	//parameters = @Parameter(name = "property", value = "user")
	//)
	@Column(name = "ADDRESS_ENT_ID")
	private Long id;
	
	@OneToOne(mappedBy = "shippingAddress", cascade = CascadeType.ALL)
	private User user;
	
	
	@Column(name="STREET_NAME")
	private String street;
	@Column(name="ZIP_CODE")
	private String zipcode;
	@Column(name="CITY_NAME")
	private String city;

	/**
	 * No-arg constructor for JavaBean tools
	 */
	public AddressEntity() {}

	/**
	 * Full constructor
	 */
	public AddressEntity(String street, String zipcode, String city) {
		this.street = street;
		this.zipcode = zipcode;
		this.city = city;
	}

	// ********************** Accessor Methods ********************** //
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStreet() { return street; }
	public void setStreet(String street) { this.street = street; }

	public String getZipcode() { return zipcode; }
	public void setZipcode(String zipcode) { this.zipcode = zipcode; }

	public String getCity() { return city; }
	public void setCity(String city) { this.city = city; }

	// ********************** Common Methods ********************** //

	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AddressEntity)) return false;

		final AddressEntity address = (AddressEntity) o;

		if (!city.equals(address.city)) return false;
		if (!street.equals(address.street)) return false;
		if (!zipcode.equals(address.zipcode)) return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = street.hashCode();
		result = 29 * result + zipcode.hashCode();
		result = 29 * result + city.hashCode();
		return result;
	}

	public String toString() {
		return  "Street: '" + getStreet() + "', " +
				"Zipcode: '" + getZipcode() + "', " +
				"City: '" + getCity() + "'";
	}

	// ********************** Business Methods ********************** //

}

package com.ecommerce.slasa.Model;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DiscriminatorOptions;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ecommerce.slasa.Enums.Roles;
import com.ecommerce.slasa.Enums.Status;
import com.ecommerce.slasa.Enums.UserPermission;
import com.ecommerce.slasa.Validation.UserValidValues;




@Entity
@Table(name="UserModel")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorOptions(force = true)
@DiscriminatorColumn(name = "UTYPE")
@UserValidValues
public class UserModel implements UserDetailsService,Serializable {
	
	
	private static final long serialVersionUID = -4718909590916979703L;
	

	public static UserModel getUserModel(Roles role){
        switch (role){

            case ADMIN: return new Admin();
            case USER: return new User();
           
           
            default:
                throw new IllegalArgumentException("Unsupported user. You input: " + role);
        } 
    }


	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "user_id", updatable = false, nullable = false)
    private String userId;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    
    @Column(name="email")
    private String email;
    @Column(name="mobile")
    private String mobile;
    
    @Column(name="firstname")
    private String firstname;
    @Column(name="lastname")
    private String lastname;
    
	 @Column(name="country")
     private String country;
	 @Column(name="countrycode")
	 private String countryCode;
	 
	 @Column(name="whatsapp_number")
	private String whatsappnumber;
	private String wpcountrycode;
	    
	 
	   
	    
	    
	    @ElementCollection(targetClass = UserPermission.class)
	    @Enumerated(EnumType.STRING)
	    @CollectionTable(name = "user_permissions", joinColumns = @JoinColumn(name = "user_id"))
	    @Column(name = "permission")
	    private Set<UserPermission> permissions = new HashSet<>();
    
   
		
		public String getWhatsappnumber() {
			return whatsappnumber;
		}
		public void setWhatsappnumber(String whatsappnumber) {
			this.whatsappnumber = whatsappnumber;
		}
		public String getWpcountrycode() {
			return wpcountrycode;
		}
		public void setWpcountrycode(String wpcountrycode) {
			this.wpcountrycode = wpcountrycode;
		}



	@UpdateTimestamp
	 private Timestamp lastUpatedDate;
	    
	 @CreationTimestamp
	 private Timestamp createdDate;
	 
    
     @Column(name = "role")
     @Enumerated(EnumType.STRING)
     private Roles role;
     
     @Column(name="is_verified")
     @Enumerated(EnumType.STRING)
     private Status isVerified;
     
     @Transient
     private Boolean flag;
     
     
     


	
	public Status getIsVerified() {
		return isVerified;
	}
	public void setIsVerified(Status isVerified) {
		this.isVerified = isVerified;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Roles getRole() {
		return role;
	}
	public void setRole(Roles role) {
		this.role = role;
	}
	public Timestamp getLastUpatedDate() {
		return lastUpatedDate;
	}
	public void setLastUpatedDate(Timestamp lastUpatedDate) {
		this.lastUpatedDate = lastUpatedDate;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}



	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public GrantedAuthority getAuthorities() {
		GrantedAuthority authorities=new SimpleGrantedAuthority(this.role.toString());
		return authorities;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	public UserModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Set<UserPermission> getPermissions() {
		return permissions;
	}
	public void setPermissions(Set<UserPermission> permissions) {
		this.permissions = permissions;
	}
	
	  public boolean hasPermission(UserPermission permission) {
	        return permissions.contains(permission);
	    }
	public UserModel(String id, String email, String firstname, String lastname, String country, Roles role,
			Status isVerified) {
		super();
		this.userId = id;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		
		this.role = role;
		this.isVerified = isVerified;
	}
	
	public UserModel(String username, String password, String email, String firstname, String lastname,
			String country) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		
	}
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}


	
	
	
	
}


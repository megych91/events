package com.codingdojo.BeltExam.models;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=2,max=255,message="First name cannot be blank.")
	private String fname;

	@Size(min=2,max=255,message="Last name cannot be blank.")
    private String lname;
    
    @Size(min=1,max=255,message="Email cannot be blank.")
    private String email;

    @Size(min=2,max=255,message="City cannot be blank.")
	private String city;
    
    @Size(message="State cannot be blank.")
	private String state;
    
    @Size(min=5,max=255,message="Password cannot be blank.")
	private String password;
	
	@Transient  // transient is a variables modifier used in serialization. At the time of serialization, if we don’t want to save value of a particular variable in a file or a database
	private String passwordConfirm;
    
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
	public User() {
	}
	
	public User(String fname, String lname, String email, String city, String state, String password, String passwordConfirm) {
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.city = city;
		this.state = state;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
    }

    @ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="users_events",
		joinColumns=@JoinColumn(name="user_id"),
		inverseJoinColumns=@JoinColumn(name="event_id")
	)
    private List<Event> joinedEvents;
    
    @OneToMany(mappedBy="hoster", fetch = FetchType.LAZY)
    private List<Event> hostedEvents;

    @OneToMany(mappedBy="commenter", fetch = FetchType.LAZY)
    private List<Message> commentedMsg;

    //GETTERS//
	public Long getId() {
		return id;
	}
	public String getFname() {
		return fname;
    }
    public String getLname() {
		return lname;
	}
	public String getEmail() {
		return email;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getPassword() {
		return password;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
    }
    public Date getCreatedAt() {
		return createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public List<Event> getJoinedEvents() {
		return joinedEvents;
    }
    public List<Event> getHostedEvents() {
		return hostedEvents;
	}

    //SETTERS//
	public void setId(Long id) {
		this.id = id;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public void setJoinedEvents(List<Event> joinedEvents) {
		this.joinedEvents = joinedEvents;
	}
	public void setHostedEvents(List<Event> hostedEvents) {
		this.hostedEvents = hostedEvents;
    }
    
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }   
}

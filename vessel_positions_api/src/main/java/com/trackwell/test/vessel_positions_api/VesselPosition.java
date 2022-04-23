package com.trackwell.test.vessel_positions_api;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;


@Entity
class VesselPosition {

  @Id
  @GeneratedValue 
  private Long id;
  
  @JsonProperty("vessel")
  @JsonView(Views.VesselPositionResponseView.class)
  @OneToOne(targetEntity = Vessel.class, cascade = CascadeType.ALL)
  private Vessel vessel;

  @JsonProperty("position")
  @JsonView(Views.VesselPositionResponseView.class)
  @OneToOne(targetEntity = Position.class, cascade = CascadeType.ALL)
  private Position position;

  VesselPosition() {}

  VesselPosition(Vessel vessel, Position position) {

    this.vessel = vessel;
    this.position = position;
  }

  // Getters
  public Vessel getVessel() {
    return this.vessel;
  }

  public Position getPosition() {
    return this.position;
  }

  // Setters
  public void setVessel(Vessel vessel) {
    this.vessel = vessel;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  @Override
  public String toString() {
    return "VesselPosition{" + this.vessel + ", " + this.position + "}";
  }
}

@Entity
class Vessel {

  @Id
  @GeneratedValue
  private Long id;

  @JsonView(Views.VesselPositionResponseView.class)
  private String name;

  private String country;

  Vessel() {}

  Vessel(String name, String country) {

    this.name = name;
    this.country = country;
  }

  // Getters
  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getCountry() {
    return this.country;
  }

  // Setters
  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  @Override
  public String toString() {
    return "Vessel{name=\"" + this.name + "\", country=\"" + this.country + "\"}";
  }
}

@Entity
class Position {

  @Id
  @GeneratedValue
  private Long id;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss a z")
  @JsonView(Views.VesselPositionResponseView.class)
  private Date date;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss a z")
  @JsonView(Views.VesselPositionResponseView.class)
  private Date receivedDate;

  @JsonView(Views.VesselPositionResponseView.class)
  private float latitude;

  @JsonView(Views.VesselPositionResponseView.class)
  private float longitude;

  @JsonView(Views.VesselPositionResponseView.class)
  private float speed;

  Position() {}

  Position(Date date, float latitude, float longitude, float speed) {

    this.date = date;
    this.receivedDate = new Date();
    this.latitude = latitude;
    this.longitude = longitude;
    this.speed = speed;
  }

  // Getters
  public Long getId() {
    return this.id;
  }

  public Date getDate() {
    return this.date;
  }

  public Date getReceivedDate() {
    return this.receivedDate;
  }

  public float getLatitude() {
    return this.latitude;
  }

  public float getLongitude() {
    return this.longitude;
  }

  public float getSpeed() {
    return this.speed;
  }

  // Setters
  public void setId(Long id) {
    this.id = id;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public void setReceivedDate() {
    this.receivedDate = new Date();
  }

  public void setLatitude(float latitude) {
    this.latitude = convertDegreesToRadians(latitude);
  }

  public void setLongitude(float longitude) {
    this.longitude = convertDegreesToRadians(longitude);
  }

  public void setSpeed(float speed) {
    this.speed = convertKnotsToMetersPerSecond(speed);
  }

  // Converts input latitude and longitude Degrees to Radians
  public float convertDegreesToRadians(float Degrees) {
    return Degrees * (float) Math.PI / 180;
  }

  // Converts input speedKnots to speedMetersPerSecond
  public float convertKnotsToMetersPerSecond(float speedKnots) {
    return speedKnots * (float) 0.514444;
  }

  @Override
  public String toString() {
    return "Position{date=\"" + this.date + "\", receivedDate = \"" + this.receivedDate + "\", " +
    "latitude=" + this.latitude + ", longitude=" + this.longitude + ", speed=" + this.speed + "}";
  }
}
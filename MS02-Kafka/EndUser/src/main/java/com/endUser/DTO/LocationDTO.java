package com.endUser.DTO;

import java.time.LocalDateTime;

public class LocationDTO 
{
	private Long driverId;
	private double latitude;
	private double longitude;
	private LocalDateTime timestamp;
	
	public LocationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LocationDTO(Long driverId, double latitude, double longitude, LocalDateTime timestamp) {
		super();
		this.driverId = driverId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.timestamp = timestamp;
	}

	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "LocationDTO [driverId=" + driverId + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", timestamp=" + timestamp + "]";
	}
	
}

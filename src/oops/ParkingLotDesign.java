package oops;

import java.time.LocalDateTime;
import java.util.HashMap;

enum VehicleType {
	CAR, BIKE, TRUCK
}

enum ParkingSpotType {
	LARGE, MEDIUM, SMALL
}

abstract class Vehicle {

	private String vehicleNumber;
	private VehicleType type;
	private ParkingTicket ticket;

	public Vehicle(VehicleType type) {
		this.type = type;
	}

	public void assignTicket(ParkingTicket ticket) {
		this.ticket = ticket;
	}

}

class Car extends Vehicle {
	public Car() {
		super(VehicleType.CAR);
	}
}

class Bike extends Vehicle {
	public Bike() {
		super(VehicleType.BIKE);
	}
}

class Truck extends Vehicle {
	public Truck() {
		super(VehicleType.TRUCK);
	}
}

class ParkingTicket {
	private int ticketNumber;
	private LocalDateTime timestamp;

	public int getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}

class ParkingSpot {
	private int spotId;
	private boolean free;
	private Vehicle vehicle;
	private ParkingSpotType spotType;

	public int getSpotId() {
		return spotId;
	}

	public void setSpotId(int spotId) {
		this.spotId = spotId;
	}

	public ParkingSpotType getSpotType() {
		return spotType;
	}

	public void setSpotType(ParkingSpotType spotType) {
		this.spotType = spotType;
	}

	public ParkingSpot(ParkingSpotType spotType) {
		this.spotType = spotType;
	}

	public boolean isFree() {
		return free;
	}

	public boolean assignVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
		free = false;
		return true;
	}

	public boolean removeVehicle() {
		this.vehicle = null;
		free = true;
		return true;
	}
}

class BikeSpot extends ParkingSpot {
	BikeSpot() {
		super(ParkingSpotType.SMALL);
	}
}

class CarSpot extends ParkingSpot {
	CarSpot() {
		super(ParkingSpotType.MEDIUM);
	}
}

class TruckSpot extends ParkingSpot {
	TruckSpot() {
		super(ParkingSpotType.LARGE);
	}
}

class ParkingFloor {
	private String name;
	private HashMap<Integer, ParkingSpot> bikeSpot;
	private HashMap<Integer, ParkingSpot> carSpot;
	private HashMap<Integer, ParkingSpot> truckSpot;

	ParkingFloor(String name) {
		this.name = name;
	}

	public void addParkingSpot(ParkingSpot spot) {
		switch (spot.getSpotType()) {
		case SMALL:
			bikeSpot.put(spot.getSpotId(), spot);
			break;

		case MEDIUM:
			carSpot.put(spot.getSpotId(), spot);
			break;

		case LARGE:
			truckSpot.put(spot.getSpotId(), spot);
			break;

		default:
			System.out.println("Wrong Parking spot");

		}
	}
}

class ParkingLot {
}

public class ParkingLotDesign {

}

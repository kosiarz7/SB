package systemy.bankowe.flows.insuranceadd;

import java.io.Serializable;

public class CarUsageStub implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 944535411891652083L;

	private Integer purchaseYear;

	public enum AverageMillage {
		VERY_LOW("Poni¿ej 5000 km", -100.0),
		LOW("5001-10000 km", 0.0),
		AVERAGE("10001-15000 km", 0.0),
		HIGH("15001-50000 km", 200.0),
		VERY_HIGH("Powy¿ej 50000 km", 300.0);
		
		private Double price;
		private String label;
		
		AverageMillage(String label, Double price) {
			this.label = label;
			this.price = price;
		}
		
	    public String getLabel() {
	        return label;
	    }
	    
	    public Double getPrice() {
	    	return price;
	    }
	};
	
	private AverageMillage averageYearMillage;
	
	public enum ParkingPlace {
		GARAGE("Gara¿", -100.0),
		GUARDED_PARKING("Parking p³atny", -100.0),
		STREET("Ulica", 300.0),
		PARKING("Parking", 100.0),
		NEXTDOOR("Podjazd", 0.0);
		
		private String label;
		private Double price;
		
		ParkingPlace(String label, Double price) {
			this.label = label;
			this.price = price;
		}
		
		public String getLabel() {
			return label;
		}
		
		public Double getPrice() {
			return price;
		}
	};
	
	private ParkingPlace parkingPlace;
	
	private String parkingPostalCode;

	public CarUsageStub() {
		
	}
	
	public CarUsageStub(Integer purchaseYear, AverageMillage averageYearMillage,
			ParkingPlace parkingPlace, String parkingPostalCode) {
		this.purchaseYear = purchaseYear;
		this.averageYearMillage = averageYearMillage;
		this.parkingPlace = parkingPlace;
		this.parkingPostalCode = parkingPostalCode;
	}
	
	public Integer getPurchaseYear() {
		return purchaseYear;
	}

	public void setPurchaseYear(Integer purchaseYear) {
		this.purchaseYear = purchaseYear;
	}

	public AverageMillage getAverageYearMillage() {
		return averageYearMillage;
	}

	public void setAverageYearMillage(AverageMillage averageYearMillage) {
		this.averageYearMillage = averageYearMillage;
	}

	public ParkingPlace getParkingPlace() {
		return parkingPlace;
	}

	public void setParkingPlace(ParkingPlace parkingPlace) {
		this.parkingPlace = parkingPlace;
	}

	public String getParkingPostalCode() {
		return parkingPostalCode;
	}

	public void setParkingPostalCode(String parkingPostalCode) {
		this.parkingPostalCode = parkingPostalCode;
	}
	
	public AverageMillage[] getAverageMillageList() {
		return AverageMillage.values();
	}
	
	public ParkingPlace[] getParkingPlaceList() {
		return ParkingPlace.values();
	}
}

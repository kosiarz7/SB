package systemy.bankowe.flows.insuranceadd;

import java.io.Serializable;

public class CarBasicInfoStub implements Serializable {	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5234122138714179160L;

	private String productionYear;
	
	public enum Producent {
		Alfa_Romeo("Alfa Romeo", 20000.0),
		Fiat("Fiat", 15000.0),
		Ford("Ford", 35000.0),
		Mazda("Mazda", 22000.0),
		Citroen("Citroen", 17000.0),
		Toyota("Toyota", 28000.0),
		Kia("Kia", 16500.0),
		Hyundai("Hyundai", 18000.0),
		Honda("Honda", 25500.0),
		Suzuki("Suzuki", 23000.0),
		Audi("Audi", 26500.0),
		Mercedes("Mercedes", 38000.0),
		Peugeot("Peugeot", 20000.0),
		Porshe("Porshe", 150000.0),
		Skoda("Skoda", 17500.0),
		Volvo("Volvo", 23000.0),
		Volkswagen("Volkswagen", 25000.0),
		Ferrari("Ferrari", 100000.0),
		Bugatti("Bugatti", 1000000.0),
		Opel("Opel", 24000.0),
		Dacia("Dacia", 15000.0),
		Lancia("Lancia", 24000.0);
		
		private String label;
		private Double price;
		
		Producent(String label, Double price) {
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
	
	
	public enum FuelType {
		Benzyna(0.0),
		Diesel(300.0);
		
		private Double price;
		
		FuelType(Double price) {
			this.price = price;
		}
	    
	    public Double getPrice() {
	    	return price;
	    }
		
	}
	
	public enum BodyType {
		Hatchback(0.0),
		Sedan(0.0),
		Kabriolet(300.0),
		Kombi(200.0),
		VAN(100.0),
		SUV(150.0);
		
		private Double price;
		
		BodyType(Double price) {
			this.price = price;
		}
	    
	    public Double getPrice() {
	    	return price;
	    }
	}
	
	private Producent producent;
	
	private String model;
	
	private FuelType fuelType;
	
	private Boolean lpgInstalled;
	
	private Float engine_size;
	
	private BodyType bodyType;

	public CarBasicInfoStub() {
		
	}
	
	public CarBasicInfoStub(String productionYear, Producent producent,
			String model, FuelType fuelType, Boolean lpgInstalled,
			Float engine_size, BodyType bodyType) {
		this.productionYear = productionYear;
		this.producent = producent;
		this.model = model;
		this.fuelType = fuelType;
		this.lpgInstalled = lpgInstalled;
		this.engine_size = engine_size;
		this.bodyType = bodyType;
	}
	
	public String getProductionYear() {
		return productionYear;
	}

	public void setProductionYear(String productionYear) {
		this.productionYear = productionYear;
	}

	public Producent getProducent() {
		return producent;
	}

	public void setProducent(Producent producent) {
		this.producent = producent;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public FuelType getFuelType() {
		return fuelType;
	}

	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}

	public Boolean getLpgInstalled() {
		return lpgInstalled;
	}

	public void setLpgInstalled(Boolean lpgInstalled) {
		this.lpgInstalled = lpgInstalled;
	}

	public Float getEngine_size() {
		return engine_size;
	}

	public void setEngine_size(Float engine_size) {
		this.engine_size = engine_size;
	}

	public BodyType getBodyType() {
		return bodyType;
	}

	public void setBodyType(BodyType bodyType) {
		this.bodyType = bodyType;
	}
	
	public Producent[] getProducentList() {
		return Producent.values();
	}
	
	public FuelType[] getFuelTypeList() {
		return FuelType.values();
	}
	
	public BodyType[] getBodyTypeList() {
		return BodyType.values();
	}
}

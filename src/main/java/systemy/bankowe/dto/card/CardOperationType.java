package systemy.bankowe.dto.card;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;


public class CardOperationType {
	
	public final static String CASH = "CASH";
	public final static String NON_CASH = "NON_CASH";
	public final static String PROXIMITY = "PROXIMITY";
	
	private final static Map<String, String> allTypes;
	static {
		Map<String, String> map= new LinkedHashMap<String, String>();
		map.put("Gotówkowa", CASH);
		map.put("Bezgotówkowa", NON_CASH);
		map.put("Zbliżeniowa", PROXIMITY);
		allTypes = Collections.unmodifiableMap(map);
	}
	
	private CardOperationType() {}
	
	public static Map<String, String> getAllTypes() {
		
		return allTypes;
	}
}

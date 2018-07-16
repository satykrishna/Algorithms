package example.fp.dpvsfp;

public class TrainJourney {

	public int price;
	
	public TrainJourney onward;

	public TrainJourney(int price2, TrainJourney append) {
		// TODO Auto-generated constructor stub
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public TrainJourney getOnward() {
		return onward;
	}

	public void setOnward(TrainJourney onward) {
		this.onward = onward;
	}
	
	public TrainJourney link(TrainJourney a, TrainJourney b) {
		
		if(a == null) {
			return b;
		}
		
		TrainJourney temp = a;
		
		while(temp.onward != null ) {
			temp = temp.onward;
		}
		
		return null;
	}
	
	public TrainJourney append (TrainJourney a, TrainJourney b) {
		return a == null ? b: new TrainJourney(a.price, append(a.onward, b));
	}
	
}

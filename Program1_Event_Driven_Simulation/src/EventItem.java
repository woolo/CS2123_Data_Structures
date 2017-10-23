public class EventItem {
	private int time_of_day;
	private int service_time;
	private int type_of_event;   // -1 means arrival and 0...9 is a departure 
								//from the indicated teller: 0...9
	
	// default constructor
	public EventItem(){
		this.time_of_day = 0;
		this.service_time = 0;
		this.type_of_event = 0;
	}
	
	
	// constructor that can generate EventItem with customized parameters
	public EventItem(int time_of_day, int service_time, int type_of_event ){
		this.time_of_day = time_of_day;
		this.service_time = service_time;
		this.type_of_event = type_of_event;
	}
	
	public int getTimeOfDay(){
		return time_of_day;
	}
	
	public int getServiceTime(){
		return service_time;
	}
	
	public int getTypeOfEvent(){
		return type_of_event;
	}
}

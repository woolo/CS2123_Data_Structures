public class TellerQueue {
	private int current_number;
	private int total_idle_time;
	private int max_length = 0;
	private int current_length = 0;
	
	private QueueReferenceBased<EventItem> Q;
	
	public TellerQueue(){
		current_number = 0;
		total_idle_time = 0;
		max_length = 0;
		Q = new QueueReferenceBased<EventItem>();
	}
	
	public void enqueue(EventItem item){
		current_length++;
		Q.enqueue(item);
	}
	
	public EventItem dequeue(){
		current_length--;
		return Q.dequeue();
	}
	
	public EventItem peek(){
		return Q.peek();
	}
	
	public boolean isEmpty() {
	    return Q.isEmpty();
	}  // end isEmpty
	
	public void updateIdle(int idleTime){
		total_idle_time += idleTime;
	}
	
	public int getLength(){
		return current_length;
	}
	
	public int getMaxLength(){
		return max_length;
	}
	
	public int getTotalIdelTime(){
		return total_idle_time;
	}
	
	public void setLength(int length){
		max_length = length;
	}
	
	public void updateMaxLength(){
		if(max_length < current_length){
			max_length = current_length;
		}
	}


}

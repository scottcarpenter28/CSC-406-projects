package carpenterScott;

public class TimerCarpenter01 {
	private long beforeTime;
	private long afterTime;
	
	public void start() {
		beforeTime= System.currentTimeMillis();
	}
	
	public void stop() {
		afterTime=System.currentTimeMillis();
	}
	
	public long getDuration() {
		return afterTime-beforeTime;
	}

}

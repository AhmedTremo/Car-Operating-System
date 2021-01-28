package carX1;

import java.io.IOException;

public class CPU {
	static boolean isWorking;
	public Process activeProcess;
	public Process[] processArray;
	static scheduler scheduler;
	static long totalTime=0;
	

	public CPU() {
		isWorking = false;
			
	}
public static void execute(Process p) throws IOException {
		if(!isWorking) {
			System.out.println("not Working");
			OperatingSystem.readyQueue.add(p);
			OperatingSystem.info.write(p.type+" Is Now In The Ready Queue");
			OperatingSystem.info.newLine();
			
			scheduler = new scheduler();
	       	scheduler.start();
		}else {
			System.out.println("Working");
		    if(p.priority==3) {
				Move.usebreak=true;
			}else if(p.priority==4) {
				Move.carState=true;
			}else if(p.priority==2) {
				Move.transimition=true;
			}
			OperatingSystem.readyQueue.add(p);
			OperatingSystem.info.newLine();
			OperatingSystem.info.write(p.type+" Is Now In The Ready Queue");
			OperatingSystem.info.newLine();
		}
	}

	public void stopWorking () {
		isWorking=false;
	}

	public boolean isWorking() {
		return isWorking;
	}

	public void terminate() {
		try {
			activeProcess.terminate();
			activeProcess = null;
			isWorking = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

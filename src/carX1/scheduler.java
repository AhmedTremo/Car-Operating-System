package carX1;

import java.io.IOException;
import java.util.*;

public class scheduler extends Thread {
	static Process ActiveProcess;

//	ArrayList<Process> SuspendedProcesses = new ArrayList<Process>();
//
//	@SuppressWarnings("deprecation")
//	public void test() {
//		CPU.isWorking = true;
//		while (true) {
//			if (!(OperatingSystem.readyQueue.isEmpty())) {
//				System.out.println("THE PROCESS IS NOW RUNNING !");
//				Process newProcess = OperatingSystem.readyQueue.poll();
//				
//				System.out.println(newProcess.priority+"  this is the process priority");
//			    
//				if (SuspendedProcesses.contains(newProcess)) {
//					System.out.println("this process was suspended");
//					if (!(newProcess.isAlive())) {
//						System.out.println("the process is dead  "+ newProcess.priority);
//						SuspendedProcesses.remove(newProcess);
//						OperatingSystem.readyQueue.remove(newProcess);
//						if (!(OperatingSystem.readyQueue.isEmpty())) {
//							newProcess = OperatingSystem.readyQueue.poll();
//						} else {
//							return;
//						}
//					}else {
//					
//					newProcess.resume();
//					newProcess.State = "Running";}
//				} else {
//					newProcess.start();
//					newProcess.State = "Running";
//				}
//				try {
//					this.sleep(4000);
//						System.out.println("i got here");
//					newProcess.suspend();
//					newProcess.State="Blocked";
//					SuspendedProcesses.add(newProcess);
//					OperatingSystem.readyQueue.add(newProcess);
//				} catch (InterruptedException e) {
//
//					e.printStackTrace();
//				}
//			
//			}
//		}
//	}
//public void test() {
//	CPU.isWorking=true;
//while(true) {
//  	Process newProcess= max();
//  	if(newProcess!=null) {
//  		if(SuspendedProcesses.contains(newProcess)) {
//  			if (!(newProcess.isAlive())) {
//				System.out.println("the process is dead  "+ newProcess.priority);
//				SuspendedProcesses.remove(newProcess);
//				OperatingSystem.readyQueue.remove(newProcess);
//				if (!(OperatingSystem.readyQueue.isEmpty())) {
//					newProcess = max();
//				}
//				else {
//					return;
//				}
//  		}else {
//  			newProcess.resume();
//  			newProcess.State="Running";
//  		}
//  		}else {	
//  		newProcess.start();
//  		newProcess.State="Running";}
//  		
//  		while(newProcess.isAlive()) {
//  			Process getmax = max();
//  			//System.out.println(getmax==null);
//  			if(getmax!=null) {
//  			 if(getmax.priority>newProcess.priority) {
//  				newProcess.suspend();
//				newProcess.State="Blocked";
//				SuspendedProcesses.add(newProcess);
//				OperatingSystem.readyQueue.add(newProcess);
//  				
//  			}
//  			}
//  		}
//  	
//  	}
//	}
//}
public  void run() {
	 ActiveProcess=null;
	CPU.isWorking=true;
	
	while(true) {
		System.out.println(" --------------------- ");
	//sortqueue();
	
	ActiveProcess= OperatingSystem.readyQueue.poll();
	
	//System.out.println(ActiveProcess.priority+" this is the pr");
	if(ActiveProcess==null) {
//		View2.pnlBig.setText(View2.pnlBig.getText() + "\r\n" + "No Process Is In the Ready Queue");
		System.out.println("No Process Is In the Ready Queue");
	    CPU.isWorking=false;
		break;
	}else {
		try {
			OperatingSystem.info.newLine();
			OperatingSystem.info.write(ActiveProcess.type+" Is Now In The  CPU Scheduler");
			OperatingSystem.info.newLine();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("this is the active process "+ActiveProcess.type);
//		View2.pnlBig.setText(
//				View2.pnlBig.getText() + "\r\n" + "this is the active process" + ActiveProcess.type);
		ActiveProcess.start();
		long start = System.currentTimeMillis();
		while(ActiveProcess.isAlive()) {
			//System.out.println("the scheduler is sleeping");
			try {
				this.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		CPU.totalTime= CPU.totalTime+(System.currentTimeMillis()-start);
		
	}
	
	}
}
	
public static void sortqueue() {
	

//	System.out.println("before");
//	for(int i=0;i<OperatingSystem.readyQueue.size();i++) {
//		Process x = OperatingSystem.readyQueue.poll();
//		System.out.print(x.priority+" , ");
//		OperatingSystem.readyQueue.add(x);
//		
//	}
	int size =OperatingSystem.readyQueue.size();
	Object[]processArray=new Object[size];
	for(int i=0;i<size;i++) {
		processArray[i]=OperatingSystem.readyQueue.poll();
	}
	int n = processArray.length;
	for (int i = 0; i < n - 1; i++)
		for (int j = 0; j < n - i - 1; j++) {
			if ((((Process) processArray[j]).priority)<((((Process) processArray[j + 1]).priority))) {
				
				Object temp = processArray[j];
				processArray[j] = processArray[j + 1];
				processArray[j + 1] = temp;
			}
		}
	for(int i=0;i<size;i++) {
		OperatingSystem.readyQueue.add((Process)processArray[i]);
	}
	System.out.println("after");
	for(int i=0;i<OperatingSystem.readyQueue.size();i++) {
		Process x = OperatingSystem.readyQueue.poll();
		System.out.println(x.priority+" <=p ");
		OperatingSystem.readyQueue.add(x);
		
	}
}

}

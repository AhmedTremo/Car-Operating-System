package carX1;

public class CarState extends Move {
static int number3=0;
	public CarState() {
		priority = 4;
		uniqueProcess = 4;
		number3++;
		type="carState"+" "+number3;
	}

	public void startStop() throws Exception {
		carState = true;
		if (state == false) {
			state = true;
			OperatingSystem.info.newLine();
			OperatingSystem.info.write("This is the Turn Around Time of "+type+"==>>"+(System.currentTimeMillis()-OperatingSystem.start));
		OperatingSystem.info.newLine();
		OperatingSystem.info.write("-------------newProcess----------");
		OperatingSystem.info.newLine();
		}
		else {
			state = false;
			OperatingSystem.info.newLine();
			OperatingSystem.info.write("This is the Turn Around Time of "+type+"==>>"+(System.currentTimeMillis()-OperatingSystem.start));
			OperatingSystem.info.newLine();
			OperatingSystem.info.write("-------------------The Operating System Is Terminated-----------------");
			OperatingSystem.info.newLine();
		
			OperatingSystem.view.holder3.setText((((double)CPU.totalTime)/(System.currentTimeMillis()-OperatingSystem.cpuUtilization))*100+"%   this is the cpu utilization");
            scheduler.ActiveProcess=null;
			CPU.scheduler.stop();
			OperatingSystem.info.close();
			OperatingSystem.view.removeAll();
		}
		Memory.removeFromMemory(this);
		//System.out.println((System.currentTimeMillis()-OperatingSystem.cpuUtilization)+"   this is the cpu utilization");
//		View2.pnlBig.setText(View2.pnlBig.getText()+"\r\n"+"Entered Car State Run, Car State: " + state);
		System.out.println("Entered Car State Run, Car State: " + state);
		carState = false;
		

		// System.out.println(CPU.isWorking = false);
	}
}

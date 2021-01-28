package carX1;

import java.util.concurrent.TimeUnit;

public class Break extends Move {
	boolean breaks;
  static  int number2 =0;
	public Break() {
		priority = 3;
		uniqueProcess = 3;
		number2++;
		breaks = false;
		type= "break"+" "+number2;
	}

	public void speedDown() throws Exception {

		breaks = true;
         
		if (state == true) {
			usebreak=true;
			while (breaks == true&&!carState) {
		
					// delay on second
					TimeUnit.SECONDS.sleep(1);
					if(state ==true) {
					if (speed > 0 ) {
						speed -= 20;
//						View2.pnlBig.setText(View2.pnlBig.getText()+"\r\n"+"Your speed is " + speed);
						System.out.println("Your speed is " + speed);
					} else {
						breaks = false;
//						View2.pnlBig.setText(View2.pnlBig.getText()+"\r\n"+"YOU ARE NOT MOVING");
						System.out.println("YOU ARE NOT MOVING");
					}
					}else {
						terminate();
//						View2.pnlBig.setText(View2.pnlBig.getText()+"\r\n"+"The Car Is Not Running");
						System.out.println("The Car Is Not Running");
						return ;
					}
				
			}
			usebreak=false;
		} else {
			System.out.println("Car Is Not Running");}
		Memory.removeFromMemory(this);
		OperatingSystem.info.newLine();
		OperatingSystem.info.write("This is the Turn Around Time of "+type+"==>>"+(System.currentTimeMillis()-OperatingSystem.start));
		OperatingSystem.info.newLine();
		OperatingSystem.info.write("-------------newProcess----------");
	}

	public void terminate() {
		breaks = false;
	}
}

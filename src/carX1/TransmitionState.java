package carX1;

import java.io.IOException;

public class TransmitionState extends Move {
	static int number4 = 0;

	public TransmitionState() {
		priority = 2;
		uniqueProcess = 2;
		number4++;
		type = "transmitionState" + " " + number4;
	}

	public void changeTrans() throws IOException {
		if (state == true && usebreak == false) {
			transimition = true;
			if ((transCounter % 3) == 0) {
				transCounter++;
				trans_state = 'P';
			} else if ((transCounter % 3) == 1) {
				transCounter++;
				trans_state = 'D';
			} else if ((transCounter % 3) == 2) {
				transCounter++;
				trans_state = 'R';
			}
		} else {
			System.out.println("The Car Is Not Running");
//			View2.pnlBig.setText(View2.pnlBig.getText()+"\r\n"+"The Car Is Not Running");
		}
		Memory.removeFromMemory(this);
		System.out.println("Entered Transmission Run, Transmissino State: " + trans_state);
//		View2.pnlBig.setText(View2.pnlBig.getText()+"\r\n"+" Entered Transmission Run, Transmissino State: " + trans_state);
		transimition = false;
		OperatingSystem.info.newLine();
		OperatingSystem.info.write("This is the Turn Around Time of " + type + "==>>"
				+ (System.currentTimeMillis() - OperatingSystem.start));
		OperatingSystem.info.newLine();
		OperatingSystem.info.write("-------------newProcess----------");

	}

	public void terminate() {

	}
}

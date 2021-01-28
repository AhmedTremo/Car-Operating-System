package carX1;

import java.util.*;

public class Process extends Thread implements Comparable {

	static int id = 0;
	String type="";
	String State;
	int uniqueProcess;
	char trans;
	int priority;

	public Process() {
		id = id++;
		State = "NEW";
	}

	public void ready() {
		try {
			OperatingSystem.readyQueue.add(this);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void run() {
		System.out.println("Process runned");
		try {
			int check = this.uniqueProcess;
			switch (check) {
			case 1:
				((Drive) this).speedUp();
				break;
			case 2:
				((TransmitionState) this).changeTrans();
				break;
			case 3:
				((Break) this).speedDown();
				break;
			case 4:
				((CarState) this).startStop();
				break;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void block() {
		OperatingSystem.blockedQueue.add(this);
	}

	public void terminate() throws Exception {
		int check = this.uniqueProcess;
		switch (check) {
		case 1:
			((Drive) this).terminate();
			break;
		case 2:
			((TransmitionState) this).changeTrans();
			break;
		case 3:
			((Break) this).terminate();
			break;
		case 4:
			((CarState) this).startStop();
			break;
		}
	}

	@Override

	public int compareTo(Object process) {
		if (this.priority > ((Process) process).priority) {
			return -1;
		} else if (this.priority < ((Process) process).priority) {
			return 1;
		} else {
			return 0;
		}
	}

}

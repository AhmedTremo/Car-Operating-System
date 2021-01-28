package carX1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class OperatingSystem {

	Memory memory;
	CPU cpu;
	public static PriorityQueue<Process> readyQueue;
	public static Queue<Process> blockedQueue;
	static long start;
	static long cpuUtilization;
	static view view;
	static View2 gui;

	static FileWriter fileWriter;
	static BufferedWriter info;

	public OperatingSystem(Memory memory) throws Exception {
		fileWriter = new FileWriter("processInfo.txt");
		info = new BufferedWriter(fileWriter);
		this.memory = memory;
		readyQueue = new PriorityQueue<Process>();
		blockedQueue = new LinkedList<Process>();
		// this.memory = memory;
		CPU cpu = new CPU();
		view = new view();
		gui = new View2();
		cpuUtilization = System.currentTimeMillis();
	}

	public void execProcess(Process p) throws IOException {
		CPU.execute(p);

	}

	public void blockExecutingProcess() throws Exception {
		cpu.activeProcess.terminate();
		OperatingSystem.blockedQueue.add(cpu.activeProcess);
	}

	public void unblockProcess() {
		OperatingSystem.readyQueue.add(OperatingSystem.blockedQueue.poll());
	}

	public void terminateProcess() {
		cpu.terminate();
	}

	public void ready(Process newProcess) throws IOException {

		info.newLine();
		info.write(newProcess.type + " Is Now In The Memory");
		info.newLine();
		memory.addToMemory(newProcess);
		execProcess(memory.memory.get((memory.lastEmptyAddress) - 1));
	}

	public static void main(String[] args) throws Exception {
		// Creating a Memory, CPU, OS
		Memory memory = new Memory(64);

		OperatingSystem operatingSystem = new OperatingSystem(memory);

		// Loading Processes from Memory

		// Executing Processes
		start = System.currentTimeMillis();
		operatingSystem.ready(new CarState());
		TimeUnit.SECONDS.sleep(1);
		System.out.println("this is the memory utilization " + memory.memoryUtilization() + "%");
		operatingSystem.ready(new TransmitionState());
		TimeUnit.SECONDS.sleep(1);
		operatingSystem.ready(new Drive());
		TimeUnit.SECONDS.sleep(2);
		operatingSystem.ready(new Break());
		System.out.println("this is the memory utilization " + memory.memoryUtilization() + "%");
		TimeUnit.SECONDS.sleep(7);
		operatingSystem.ready(new Drive());
		TimeUnit.SECONDS.sleep(20);
		operatingSystem.ready(new Break());
		TimeUnit.SECONDS.sleep(10);
		System.out.println("this is the memory utilization " + memory.memoryUtilization() + "%");
		TimeUnit.SECONDS.sleep(1);
		operatingSystem.ready(new TransmitionState());
		TimeUnit.SECONDS.sleep(1);
		operatingSystem.ready(new TransmitionState());
		operatingSystem.ready(new Drive());
		System.out.println("this is the memory utilization " + memory.memoryUtilization() + "%");
		TimeUnit.SECONDS.sleep(9);
		operatingSystem.ready(new Drive());
		TimeUnit.SECONDS.sleep(2);
		operatingSystem.ready(new Break());
		System.out.println("this is the memory utilization " + memory.memoryUtilization() + "%");
		TimeUnit.SECONDS.sleep(7);
		operatingSystem.ready(new TransmitionState());
		TimeUnit.SECONDS.sleep(1);
		operatingSystem.ready(new Drive());
		TimeUnit.SECONDS.sleep(10);
		operatingSystem.ready(new Break());
		TimeUnit.SECONDS.sleep(2);
		operatingSystem.ready(new CarState());
		// operatingSystem.ready(carState);

	}
}

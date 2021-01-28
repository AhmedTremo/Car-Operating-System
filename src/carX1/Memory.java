package carX1;

import java.util.ArrayList;

public class Memory {
	static ArrayList<Process> memory;
	static int lastEmptyAddress=0;
	static int size;
	public Memory(int size) {
		
		memory = new ArrayList<Process>(size);
		this.size=size;
	}
	public static  void addToMemory(Process p ) {
	     memory.add(p);
		lastEmptyAddress++;
	}
	public static void removeFromMemory(Process p ) {
		for(int i=0;i<lastEmptyAddress;i++) {
			if(memory.get(i).id==p.id) {
				lastEmptyAddress--;
				 memory.remove(i);
				
			}
		}
	}
	public static  double memoryUtilization() {
		return (lastEmptyAddress/((double)size))*100;
	}
}

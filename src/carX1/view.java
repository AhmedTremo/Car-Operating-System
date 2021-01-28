package carX1;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.PriorityQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class view extends JFrame {
	JPanel container;
	GridLayout application;
	Timer timer;
	JTextArea holder1;
	JTextArea holder2;
	JTextArea holder3;

	public view() {
		container = new JPanel();

		application = new GridLayout(3, 1);
		setSize(1200, 1500);

		holder1 = new JTextArea();
		holder2 = new JTextArea();
		holder3 = new JTextArea();
		holder1.setBorder(BorderFactory.createTitledBorder("Memory"));
		holder2.setBorder(BorderFactory.createTitledBorder("Ready Queue"));
		holder3.setBorder(BorderFactory.createTitledBorder("CPU"));
		holder1.setEditable(false);
		holder1.setFont(new Font("", Font.ITALIC, 35));
		holder2.setEditable(false);
		holder2.setFont(new Font("", Font.ITALIC, 35));
		holder3.setEditable(false);
		holder3.setFont(new Font("", Font.ITALIC, 35));
		container.setLayout(application);
		setContentPane(container);
		container.add(holder1, BorderLayout.NORTH);
		container.add(holder2, BorderLayout.CENTER);
		container.add(holder3, BorderLayout.SOUTH);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		timer = new Timer(100, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				holder1.setText("");
				
				String[] process = new String[Memory.memory.size()];
				for (int i = 0; i < Memory.memory.size(); i++) {
					if(Memory.memory.get(i)!=null)
					process[i] = Memory.memory.get(i).type;
				}
				for (int i = 0; i < process.length; i++) {
					holder1.append(" " + process[i] + "\n");
				}
				holder1.append("__________utilization___________\n");
				holder1.append("Memory Utilization ==>>" + Memory.memoryUtilization());
				PriorityQueue<Process> Copy = new PriorityQueue<Process>();
				Copy = OperatingSystem.readyQueue;
				int size2=OperatingSystem.readyQueue.size();
				holder2.setText("");
				for(int i =0;i<size2;i++) {
					Process Polled = Copy.poll();
					holder2.append(Polled.type);
					Copy.add(Polled);
					
				}
				holder3.setText("");
				if (scheduler.ActiveProcess != null) {
					
					holder3.append(scheduler.ActiveProcess.type);
				}
			}
		});
		timer.start();
	}

	public static void main(String[] args) {
		view v = new view();

	}
}

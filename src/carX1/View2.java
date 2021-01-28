package carX1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class View2 extends JFrame implements ActionListener {
	private OperatingSystem os;
	private Memory memory;
	private CPU cpu;
	private static JTextField area;
	private JProgressBar ld2;
	private JComboBox List1;
	private JLabel player;
	private JTextArea txt;
	private JButton START;
	private JButton Drive;
	private JButton Break;
	private JButton Trans;
	static JLabel pnlBig;

	private int x;
	private int i;

	private JLabel end;

	public View2() {
		this.setLayout(new BorderLayout());
		ImageIcon photo = new ImageIcon(this.getClass().getResource("carinside.jpg"));
		ImageIcon resize = resizeIcon(photo, 1920, 1100);
		setContentPane(new JLabel(resize));
		this.setLayout(new FlowLayout());
		setBackground(Color.white);
		this.setSize(1960, 1200);
		this.setLocation(0, 0);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		ImageIcon photo1 = new ImageIcon(this.getClass().getResource("carinside.jpg"));
		ImageIcon resize1 = resizeIcon(photo1, 280, 250);
		START = new JButton(resize1);
		START.setText("PLEASE START YOUR CAR");
		START.setBounds(0, 800, 250, 250);
		START.setBackground(Color.GRAY);
		START.setForeground(Color.black);
		this.add(START);
		START.addActionListener(this);
		this.setVisible(true);
	}

	public void operatingsystem() {

		memory = new Memory(64);
		try {
			os = new OperatingSystem(memory);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pnlBig = new JLabel();
		JLabel pnlSmall = new JLabel();
		area = new JTextField();
		this.getContentPane().add(pnlBig);
		this.setSize(1960, 1100);
		this.setLocation(0, 0);
		this.getContentPane().add(pnlBig);
		pnlBig.setSize(1960, 1100);
		pnlBig.setVisible(true);
		pnlBig.setBackground(Color.black);
		pnlBig.setLayout(null);
		ImageIcon photo1 = new ImageIcon(this.getClass().getResource("drive.jpg"));
		ImageIcon resize1 = resizeIcon(photo1, 280, 250);
		ImageIcon photo2 = new ImageIcon(this.getClass().getResource("brakes.jpg"));
		ImageIcon resize2 = resizeIcon(photo2, 280, 250);
		Drive = new JButton(resize1);
		Drive.setText("Drive");
		Drive.setBounds(0, 800, 250, 250);
		Drive.setBackground(Color.red);
		Drive.setForeground(Color.black);
		Break = new JButton(resize2);
		Break.setText("Break");
		Break.setBounds(250, 800, 250, 250);
		Break.setBackground(Color.red);
		Break.setForeground(Color.black);
		ImageIcon photo = new ImageIcon(this.getClass().getResource("carinside.jpg"));
		ImageIcon resize = resizeIcon(photo, 1920, 1100);
		ImageIcon photo3 = new ImageIcon(this.getClass().getResource("3910559730.jpg"));
		ImageIcon resize3 = resizeIcon(photo3, 280, 250);
		Trans = new JButton(resize3);
		Trans.setText("Trans");
		Trans.setBounds(500, 800, 250, 250);
		Trans.setBackground(Color.gray);
		Trans.setForeground(Color.black);
		pnlBig.add(Drive);
		pnlBig.add(Break);
		pnlBig.add(Trans);
		pnlBig.setFont(getFont().deriveFont(30f));
		pnlBig.setForeground(Color.white);
		pnlBig.setText("Our Operationg system");

		Drive.addActionListener(this);
		Break.addActionListener(this);
		Trans.addActionListener(this);
		pnlBig.add(area);

	}

	public static void main(String[] args) {
		View2 gui = new View2();
		gui.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(START)) {
			this.remove(START);
			music("start");
			operatingsystem();
			try {
				os.ready(new CarState());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				TimeUnit.SECONDS.sleep(1);
				pnlBig.setText(View2.pnlBig.getText() + "\r\n" + "this is the memory utilization "
						+ memory.memoryUtilization() + "%");
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (e.getSource().equals(Drive)) {
			try {
				os.ready(new Drive());
				try {
					TimeUnit.SECONDS.sleep(2);
					pnlBig.setText(View2.pnlBig.getText() + "\r\n" + "this is the memory utilization "
							+ memory.memoryUtilization() + "%");
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource().equals(Trans)) {
			try {
				os.ready(new TransmitionState());
				try {
					TimeUnit.SECONDS.sleep(1);
					pnlBig.setText(View2.pnlBig.getText() + "this is the memory utilization "
							+ memory.memoryUtilization() + "%");
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource().equals(Break)) {
			try {
				os.ready(new Break());
				try {
					TimeUnit.SECONDS.sleep(10);
					pnlBig.setText(View2.pnlBig.getText() + "\r\n" + "this is the memory utilization "
							+ memory.memoryUtilization() + "%");
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	private static ImageIcon resizeIcon(ImageIcon photo, int width, int height) {
		Image image = photo.getImage();
		Image resize = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(resize);

	}

	public void music(String x) {
		AudioPlayer a = AudioPlayer.player;
		AudioStream S;
		AudioData D;
		ContinuousAudioDataStream c = null;
		AudioStream audioStream = null;
		if (x.equals("start")) {
			try {

				String soundFile = "C:\\Users\\Eng. Lotfy\\Desktop\\car_sound.wav";
				InputStream in = new FileInputStream(soundFile);
				audioStream = new AudioStream(in);
				AudioPlayer.player.start(audioStream);

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			AudioPlayer.player.stop(audioStream);
		}
	}

	public static void addtext(String x) {
		area.setText(x);
	}

}
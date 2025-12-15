
public class Main extends Thread {
public void run() {
	int a=0;
	while(a<5) {
		System.out.println("hi");
		a++;
	}
}
	public static void main(String[] args) {
		Main t= new Main();
		t.start();
		for(int a=0;a<1000;a++) {System.out.println(a);}
	

	}

}

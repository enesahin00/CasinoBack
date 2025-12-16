
public class Test extends Thread {
public void run() {
	int a=0;
	while(a<5) {
		System.out.println("hi");
		a++;
	}
}
	public static void main(String[] args) {
		Test t= new Test();
		t.start();
		for(int a=0;a<1000;a++) {System.out.println(a);}
	

	}

}

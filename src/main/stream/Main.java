package main.stream;
import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] argv) throws IOException {
		new Main().run();
	}

	PrintWriter pw;
	Scanner sc;

	public void run() throws IOException {
		String way =  "C:\\Users\\Wise\\DimaShit\\";
		String out = "output.txt";
		String in = "input.txt";
		sc = new Scanner(new File(way+in));
		pw = new PrintWriter(new File(way+out));
		while(sc.hasNext())
		{		
			System.out.println(sc.next());
		
		}
		while(sc.hasNext())
		{
			pw.println(sc.next());
		}
		//pw.println("����� ������ ����� ��������");
		//pw.println("���� ����");
		pw.close();
	}
}

// ���� � ����� ������������ �����:
//
// Scanner in=new Scanner(System.in);
//
// in.useLocale(new Locale("US")); // ���� ������������� ����� � ������ � ��
// �������
//
// double x=in.nextDouble();
//
// System.out.printf(Locale.US,"%.2f\n",a[i]); //�������������� �����
// ������������ ����� � ������
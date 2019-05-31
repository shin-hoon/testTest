package com.boram.myPage.run;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class TestRun {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 잘 작동하는지 확인할것.
			int result = 0;// 임시변수확인!!
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("TEST.txt"))) {
				
				
			} catch (EOFException e) {
				// e.printStackTrace();
				System.out.println("불러오기 완료.");
			} catch(FileNotFoundException e) {
				
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	

}

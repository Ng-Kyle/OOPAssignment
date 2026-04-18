package main;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileIO {
	public static void writeFile(String content, String filePath) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
			writer.write(content);
		}catch(IOException e) {
			System.out.println("Could not write to file.");
		}
	}
	public static ArrayList<String> readFile(String filePath) {
		ArrayList<String> contents = new ArrayList<>();
		try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
			String line;
			while((line =  reader.readLine()) != null) {
				contents.add(line);
			}
			return contents;
		}catch(IOException e) {
			System.out.println("Could not read from file.");
			return contents;
		}
	}
}

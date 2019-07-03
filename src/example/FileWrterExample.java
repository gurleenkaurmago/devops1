package example;

/*import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;*/
import java.io.*;
import java.sql.*;

/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;*/

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


//import org.apache.poi.hssf.*;


public class FileWrterExample {

	 void writeToFile()
	{
		
		try
		{
			FileWriter fw = new FileWriter("C:\\Users\\welcome\\Desktop\\out.txt",true);
			BufferedWriter bw =new BufferedWriter(fw);
			
			bw.write("this is first write code");
			bw.newLine();
			
			System.out.println("data is saved..");
			
			bw.close();
			fw.close();
		}
		catch (Exception e) {
			
		}
	}
	
	static void ReadExcel() throws IOException
	{
		//.txt or .csv	
		FileReader f1 = new FileReader("C:\\Users\\welcome\\Desktop\\Book1.xls");
		//BufferedReader fs = new BufferedReader(f1);
		FileInputStream fs =new FileInputStream("C:\\Users\\welcome\\Desktop\\Book1.xls");
		HSSFWorkbook book = new HSSFWorkbook(new FileInputStream("C:\\Users\\welcome\\Desktop\\Book1.xls"));
		
		HSSFSheet sheet = book.getSheet("Sheet1");
						//book.getSheetAt(0)
		
		//write to file
		/*
		HSSFRow rr =  sheet.createRow(0);
		HSSFCell cell =  rr.createCell(0);
		cell.setCellValue("test");
		*/
		
		
		int sc,rc,cc;
		sc =book.getNumberOfSheets();
		
		rc = sheet.getPhysicalNumberOfRows();
		
		//test code
		cc = sheet.getRow(0).getPhysicalNumberOfCells();
		
		
		//hello
		System.out.println("no of work sheets : "+sc);
		System.out.println("no of rows : "+rc);
		System.out.println("no of columns  : "+cc);
		
		for(int i=1;i<rc;i++)
		{
			HSSFRow row = sheet.getRow(i);
			
			HSSFCell cel = row.getCell(0);
			System.out.print(cel.getNumericCellValue());
						
			cel = row.getCell(1);
			System.out.print(cel.getStringCellValue());
						
			cel = row.getCell(2);
			System.out.println(cel.getStringCellValue());
			
		}
		
	}


	static void ReadToDB()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_test", "root", "root");
			
			String n="Raman";
			//PreparedStatement prp = con.prepareStatement("insert into emp(id,name) values(?,'"+n+"')");
			PreparedStatement prp = con.prepareStatement("select* from emp");
			
			ResultSet rs = prp.executeQuery();//select
			
			while(rs.next())
			{
				System.out.println(rs.getString(1)+"\t"+rs.getString(2));
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		}
	static void WriteToDB()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_test", "root", "root");
			
			String n="Raman";
			PreparedStatement prp = con.prepareStatement("insert into emp(id,name) values('32','kiran')");
			//PreparedStatement prp = con.prepareStatement("insert into emp(id,name) values(?,?)");
			
		//	prp.setInt(1, 100);
		//	prp.setString(2, n);

			prp.executeUpdate();//insert, udpate, delete
			
			System.out.println("data is saved..");
			
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	public static void main(String[] args) throws IOException {
		
		//writeToFile();

		//ReadExcel();
		WriteToDB();
		
		ReadToDB();
	}

}

package Banking;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.FileNotFoundException;

public class Account 
{
	protected String name;
	protected String phone_no;
	protected String city;
	protected String username;
	protected long password;
	protected long amount;
	protected int account_ID;
	
	void createAccount()
	{
		System.out.println("\nCREATE ACCOUNT FORM: ");
		System.out.println("********************************************************************");
		System.out.println();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter name: ");
		name = sc.nextLine();
		System.out.println("Enter city : ");
		city = sc.next();
		
		//to validate phone number
		do
		{
			try
			{
				System.out.println("Enter contact number : ");
				phone_no = sc.next();
				int flag=0;
				int len = phone_no.length();
				for(int i=0;i<len;i++)
				{
					char ch=phone_no.charAt(i);
					if(!Character.isDigit(ch))
					{
						flag++;
						break;
					}
					
				}
				if(flag==0&&len==10)
				{
					break;
				}
				else
					throw new Exception(" ");
			}
			catch(Exception e)
			{
				System.out.println("\tInvalid phone number");
			}
		}while(true);
		
		//username
		do{
			try{
				System.out.println("Enter username : ");
				username = sc.next();
				int count=0;
				String line="";
				RandomAccessFile raf=new RandomAccessFile("C:\\Users\\Pavitra\\eclipse-workspace\\Project- Banking System\\src\\Banking\\accounts.txt","rw");
				while((line=raf.readLine())!=null)
				{
					String words[]=line.split(":");
					if(words[4].equals(username))
						count++;
				}
				raf.close();
				if(count!=0)
				{
					throw new Exception(" ");
				}
				else
					break;
			}
			catch(Exception e)
			{
				System.out.println("\tThe username is already taken.");
			}
		}while(true);
		
		//enter password
		System.out.println("Set a password (only digits): ");
		password = sc.nextLong();
		
		//to re-confirm password
		do
		{
		try
		{
			System.out.println("Re-confirm password: ");
			long temp_pass = sc.nextLong();
			if(password == temp_pass) 
			{
				break; 
			}
			else
			{
				throw new Exception(" ");
			}
		}
		catch(Exception e)
		{
			System.out.println("\tThe passwords don't match");
        }
		}while(true);
		
		//to enter minimum amount
		do
		{
		try
		{
			System.out.println("Enter initial amount to deposited: ");
			amount = sc.nextLong();
			if(amount>10000) 
				break; 
			else
				throw new Exception(" ");
		}
		catch(Exception e)
		{
			System.out.println("\tThe minimum amount must be Rs.10,000");
        }
		}while(true);
		System.out.println();
		saveAccount();
	} //closing createAccount
	
	//generate account number
	void generateAcNo()
	{
		try{
			int countl=0;
			RandomAccessFile raf=new RandomAccessFile("C:\\Users\\Pavitra\\eclipse-workspace\\Project- Banking System\\src\\Banking\\accounts.txt","rw");
			while((raf.readLine())!=null)
			{
				countl++;
			}
			account_ID=(++countl);
			raf.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	//saving the account
	void saveAccount()
	{
		try
		{
			FileWriter fw=new FileWriter("C:\\Users\\Pavitra\\eclipse-workspace\\Project- Banking System\\src\\Banking\\accounts.txt",true);
			PrintWriter pw=new PrintWriter(fw);
			generateAcNo();
			pw.print(account_ID); pw.print(":");
			pw.print(name);pw.print(":");
			pw.print(city);pw.print(":");
			pw.print(phone_no);pw.print(":");
			pw.print(username);pw.print(":");
			pw.print(password);pw.print(":");
			pw.println(amount);
			fw.close();
			System.out.println("ACCOUNT IS SUCCESSFULLY CREATED");
			System.out.println("Your account number is: "+account_ID);
			System.out.println("********************************************************************");
			System.out.println();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	//login
	int login()
	{
		System.out.println("\nLOGIN FORM: ");
		System.out.println("********************************************************************");
		System.out.println();
		Scanner sc=new Scanner(System.in);
		try
		{
			System.out.print("Username: ");
			username=sc.next();
			String line="";
			int flag=0;
			RandomAccessFile raf=new RandomAccessFile("C:\\Users\\Pavitra\\eclipse-workspace\\Project- Banking System\\src\\Banking\\accounts.txt","rw");
			while((line=raf.readLine())!=null)
			{
				String words[]=line.split(":");
				if(words[4].equals(username))
					flag=1;
			}
			raf.close();
			if(flag==0)
			{
				throw new Exception(" ");
			}
		}
		catch(Exception e)
		{
			System.out.println("\tUsername does not exist. ");
			return 0;
		}
		
		//enter password
		do
		{
			try
			{
				System.out.print("Password: ");
				password=sc.nextLong();
				String line="";
				RandomAccessFile raf=new RandomAccessFile("C:\\Users\\Pavitra\\eclipse-workspace\\Project- Banking System\\src\\Banking\\accounts.txt","rw");
				while((line=raf.readLine())!=null)
				{
					String words[]=line.split(":");
					if(words[4].equals(username))
					{
						if(Long.toString(password).equals(words[5]))
						{
							System.out.println("\nLOGIN SUCCESSFUL!!");
							System.out.println("Welcome "+words[1]+"!");
							return Integer.parseInt(words[0]);
						}
						else
							throw new Exception(" ");
					}
				}
			}
			catch(Exception e)
			{
				System.out.println("\tIncorrect password.");
			}
		}while(true);
	}
	
	void update(File f, File ftemp)
	{
		try {
		String nline="";
		RandomAccessFile raf=new RandomAccessFile(ftemp,"rw");
		FileWriter fw=new FileWriter(f,false);
		PrintWriter pw=new PrintWriter(fw);
		while((nline=raf.readLine())!=null)
		{
			pw.println(nline);
		}
		raf.close();
		pw.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	int compute(int acc_no, long amt, int op)
	{
		File f=new File("C:\\Users\\Pavitra\\eclipse-workspace\\Project- Banking System\\src\\Banking\\accounts.txt");
		File ftemp=new File("C:\\Users\\Pavitra\\eclipse-workspace\\Project- Banking System\\src\\Banking\\accountstemp.txt");
		try {
		String line="";
		RandomAccessFile raf=new RandomAccessFile(f,"rw");
		FileWriter fw=new FileWriter(ftemp,false);
		PrintWriter pw=new PrintWriter(fw);
		while((line=raf.readLine())!=null)
		{
			String words[]=line.split(":");
			if(Integer.toString(acc_no).equals(words[0]))
			{
				if(op==1)
				{
					amt=amt+Long.parseLong(words[6]);
				}
				else
				{
					amt=Long.parseLong(words[6])-amt;
					if(amt<10000)
					{
						throw new Exception(" ");
					}
				}
				for(int i=0;i<6;i++)
				{
					pw.print(words[i]);
					pw.print(":");
				}
				pw.println(amt);
			}
			else
			{
				pw.println(line);
			}
		}
		raf.close();
		fw.close();
		update(f,ftemp);
		return 1;
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
			return 0;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return 0;
		}
		catch(Exception e)
		{
			System.out.println("Minimum balance of Rs.10,000 must be maintained.\n");
			return 0;
		}
	}
	
	//depositing
	void deposit(int acc_no)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("\nDEPOSIT FORM: ");
		System.out.println("********************************************************************");
		System.out.println();
		System.out.println("Account Number: "+acc_no);
		System.out.println();
		System.out.println("Enter the amount to be deposited: ");
		long amt=sc.nextLong();
		int res=compute(acc_no,amt,1);
		if(res==1)
			System.out.println("The amount has been succesfully deposited. ");
		
	}
	
	void withdraw(int acc_no)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("\nWITHDRAWAL FORM: ");
		System.out.println("********************************************************************");
		System.out.println();
		System.out.println("Account Number: "+acc_no);
		System.out.println();
		System.out.println("Enter the amount to be withdrawn: ");
		long amt=sc.nextLong();
		int res=compute(acc_no,amt,0);
		if(res==1)
			System.out.println("The amount has been successfully withdrawn.");
	}
	
	void transfer(int acc_no)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("\nTRANSFER FORM: ");
		System.out.println("********************************************************************");
		System.out.println();
		System.out.println("Account Number: "+acc_no);
		System.out.println();
		int tan;
		try 
		{
			System.out.print("Transfer to Account Number: ");
			tan=sc.nextInt();
			String line="";
			int flag=0;
			RandomAccessFile raf=new RandomAccessFile("C:\\Users\\Pavitra\\eclipse-workspace\\Project- Banking System\\src\\Banking\\accounts.txt","rw");
			while((line=raf.readLine())!=null)
			{
				String words[]=line.split(":");
				if(tan==Integer.parseInt(words[0])) {
					flag=1;
					break; }
	
			}
			raf.close();
			if(flag==0)
			{
				throw new Exception(" ");
			}
		}
		catch(Exception e)
		{
			System.out.println("\tAccount number does not exist. ");
			return;
		}
		System.out.print("Enter the amount to be transferred: ");
		long tamt=sc.nextLong();
		int res1=compute(acc_no,tamt,0);
		if(res1==0)
		{
			System.out.println("Transfer is unsuccessful.");
		}
		else
		{
			int res2=compute(tan,tamt,1);
			if(res2==1)
			{
				System.out.println("The money has been successfully transferred.");
			}
		}
	}
	
	//check balance
	void check_balance(int acc_no)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("\nCHECK BALANCE FORM: ");
		System.out.println("********************************************************************");
		System.out.println();
		File f=new File("C:\\Users\\Pavitra\\eclipse-workspace\\Project- Banking System\\src\\Banking\\accounts.txt");
		File ftemp=new File("C:\\Users\\Pavitra\\eclipse-workspace\\Project- Banking System\\src\\Banking\\accountstemp.txt");
		try
		{
		    String line="";
		    RandomAccessFile raf=new RandomAccessFile(f,"rw");
		    FileWriter fw=new FileWriter(ftemp,false);
		    PrintWriter pw=new PrintWriter(fw);
		    while((line=raf.readLine())!=null)
		    {
			    String words[]=line.split(":");
			    if(Integer.toString(acc_no).equals(words[0]))
			    {
			    	System.out.println("Account number:\t"+words[0]+"\nName:\t\t"+words[1]+"\nUsername:\t"+words[4]+"\nCity:\t\t"+words[2]+"\nPhone number:\t"+words[3]+"\nBalance:\t"+words[6]);
			    	System.out.println("********************************************************************");
			    	System.out.println();
			    }
			}
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	//logout
	void logout(int acc_no)
	{
		System.out.println();
		System.out.println("Account number: "+acc_no);
		System.out.println("LOGGED OUT SUCCESFULLY!!");
		System.out.println("********************************************************************");
		System.out.println();
	}
}

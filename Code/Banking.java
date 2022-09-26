//main function class

package Banking;
import Banking.Account;
import java.util.*;

public class Banking 
{
	public static void main(String[] args) 
	{
		System.out.println("********************************************************************");
		System.out.println("*****MONEY BANK*****");
		System.out.println("********************************************************************");
		System.out.println();
		Scanner sc=new Scanner(System.in);
		int choice1;
		do
		{
			System.out.println("\n1. CREATE AN ACCOUNT \n2. LOGIN TO AN EXISTING ACCOUNT\n3. EXIT");
			System.out.print("  ENTER YOUR CHOICE: ");
			choice1=sc.nextInt();
			switch(choice1)
			{
				case 1: 
					Account a=new Account();
					a.createAccount();
					break;
				case 2:
					Account ac=new Account();
					int res=ac.login();
					while(res!=0)
					{
						int choice2; int log=0;
						System.out.println("\n1. DEPOSIT \n2. WITHDRAW\n3. TRANSFER\n4. CHECK BALANCE\n5. LOGOUT");
						System.out.print("  ENTER YOUR CHOICE: ");
						choice2=sc.nextInt();
						Account acc=new Account();
						switch(choice2)
						{
							case 1: 
								acc.deposit(res);
								break;
							case 2:
								acc.withdraw(res);
								break;
							case 3:
								acc.transfer(res);
								break;
							case 4:
								acc.check_balance(res);
								break;
							case 5:
								acc.logout(res);
								log=1;
								break;
							default:
								System.out.println("Invalid Entry.");
						}
						if(log==1)
							break;
					}
					break;
				case 3:
					System.out.println("Thank you for choosing Money Bank. Visit again.");
					System.exit(0);
				default:
					System.out.println("Invalid Entry.");
			}
		}while(true);
	}
}

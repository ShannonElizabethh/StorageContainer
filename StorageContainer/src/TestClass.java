import java.io.File;
import java.io.IOException;
import java.util.*;

public class TestClass {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
				
		
        File file = new File("storageFile.dat");
        if (file.length() < 5) {
        	caseStatment();
        }
        
		//load containers
		StorageContainers.loadContainers();
		
		//call method to ask user what to do
		caseStatment();

		
	}//main
	
	//method to ask user what task to perform
	public static void caseStatment() {		
		
		ArrayList<Container> listOfContainers = new ArrayList<Container>();			
		String conName;
		String itemName;
		String itemQuantity;
		
		//print out task options
		System.out.println("\nEnter Selection: ");
		System.out.println("	1: Add Container \n	2: Delete Container\n	3: Print Container List\n	4: Add Item\n	"
				+ "5: List/Find Item\n	6: Delete Item\n	0: Exit");
		
		Scanner scn = new Scanner(System.in);
		int userNum = scn.nextInt();
		scn.nextLine();
		
		switch(userNum) {
		
			//add a container
			case 1:
				//get container name
				System.out.println("Enter container name: ");
				conName = scn.nextLine();
				
				//call method to add container
				StorageContainers.addContainer(conName);					
				
				break;
				
			//delete container
			case 2:
				//get container name
				System.out.println("Enter container name: ");
				conName = scn.nextLine();
				
				//call method to delete container
				StorageContainers.deleteContainer(conName);
				
				break;
				
			//print containers
			case 3:
				//print container list		
				listOfContainers = StorageContainers.getContainerList();
				System.out.println("\nThe following are all containers.");
				for(Container item : listOfContainers) {
					System.out.println("Container: " + item.getContainerName());
				}
				
				break;
				
			//add item
			case 4:
				//get container name to add the item to
				System.out.println("Enter container name: ");
				conName = scn.nextLine();
				
				//get item name
				System.out.println("Enter item name: ");
				itemName = scn.nextLine();
				
				//get quantity of that item
				System.out.println("Enter item quantity: ");
				itemQuantity = scn.nextLine();
				
				//call method to add the item
				StorageContainers.addContainerItem(conName, itemName, itemQuantity);
				
				break;
			//print items	
			case 5:
				//call method to print the items
				StorageContainers.printItems();
				
				break;
				
			//delete item
			case 6:
				//call method to delete the item
				StorageContainers.deleteItem();
				
				break;
				
			//exit
			case 0:
				System.out.println("Goodbye");
				StorageContainers.saveContainers();
				System.exit(0);
				
			default:
				System.out.println("Error\nNot a valid entry.\n");
		}
		
		caseStatment();
		scn.close();
		
	}

}//class

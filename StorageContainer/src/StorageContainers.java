import java.io.*;
import java.util.*;


public class StorageContainers {
	//list of containers by name
	static ArrayList<String> containerNames = new ArrayList<String>();
	
	//list of container objects
	static ArrayList<Container> listOfContainers = new ArrayList<Container>();
	
	//create a new container
	public static void addContainer(String conName) {

		//loop through containers to make sure no duplicate is added
		for (int i = 0; i < containerNames.size(); i++) {
			if(conName.equals(containerNames.get(i))) {
				System.out.println("ERROR");
				System.out.println("Another container has this name.");
				return;
			}
		}
			
		//create the new container
		Container newContainer = new Container();
		newContainer.setContainerName(conName);
		containerNames.add(conName);
		listOfContainers.add(newContainer);
		System.out.println("Container Added");
		
	}
	
	//delete a container
	public static void deleteContainer(String conName) {
		boolean containerDeleted = false;
		
		//find the container using the name
		for (int i = 0; i < containerNames.size(); i++) {
			if(conName.equals(containerNames.get(i))) {
				containerNames.remove(i);
				listOfContainers.remove(i);
				containerDeleted = true;
				System.out.println("Container Deleted");
			}
		}
		
		//if no container found return error message
		if (containerDeleted == false) {
			System.out.println("No Container by the name " + conName + " was found.");
		}
	}
	
	//add item to container
	public static void addContainerItem(String conName, String itemName, String itemQuantity) {
		Scanner scn = new Scanner(System.in);
		
		boolean containerFound = false;
		boolean itemExists = false;
		int itemQuant = Integer.parseInt(itemQuantity);
		
		for (int i = 0; i < listOfContainers.size(); i++) {
			
			//find the correct container
			if (conName.equals(containerNames.get(i))) {
				
				//get the list of items for that container 
				ArrayList <Item> itemList = listOfContainers.get(i).getItemList();
				
				//check to see if item already exists
				for (int j = 0; j < itemList.size(); j++) {
					
					if (itemName.equals(itemList.get(j).getItemName())) {
						System.out.println("Item already exists. Would you like to add to the quantity? y or n");
						String userIn = scn.nextLine();
						
						//add to that item quantity if user chooses to
						if (userIn.equals("y")) {
							System.out.println("Enter quantity you are adding.");
							String userNum = scn.nextLine();
							int numAdd = Integer.parseInt(userNum);
							
							//add to the current quantity
							numAdd = numAdd + itemList.get(j).getItemQuantity();
							
							//set the new number
							itemList.get(j).setItemQuantity(numAdd);
							itemExists = true;

						} else if (userIn.equals("n")) {
							TestClass.caseStatment();
							
						} else {
							System.out.println("Error");
							TestClass.caseStatment();
						}
					}
				}
				
				//add the item if it dosn't already exists
				Item newItem = new Item();

				if (itemExists == false) {
					newItem.setItemName(itemName);
					newItem.setItemQuantity(itemQuant);
					itemList.add(newItem);
				}
				
				containerFound = true;
				
				//set the list to corrects container
				listOfContainers.get(i).setItemList(itemList);
				itemList = new ArrayList<Item>();
			}
		}
			
		//if no container with that name is found, return error message
		if (containerFound == false) {
			System.out.println("No Container by the name " + conName + " was found.");
			TestClass.caseStatment();
		}
		
		//continue to add items to that container
		System.out.println("Would you like to add another item?");
		String userInput = scn.nextLine();
						
		if(userInput.equals("yes") || userInput.equals("Yes") || userInput.equals("y")) { 
			System.out.println("Enter item name: ");
			itemName = scn.nextLine();
				
			System.out.println("Enter item quantity: ");
			itemQuantity = scn.nextLine();
				
			addContainerItem(conName, itemName, itemQuantity);
				
		} else {
			TestClass.caseStatment();
		}
			
		scn.close();
	}
	
	//delete an item from a container 
	public static void deleteItem() {
		
		ArrayList <Container> foundItems = new ArrayList<Container>();
		int numItems = 0;
		
		Scanner scn = new Scanner(System.in);
		String itemName;
		
		//get the name of the item
		System.out.println("Enter item name.");
		itemName = scn.nextLine();
		
		//find container (could be multiple)
		for (int i = 0; i < listOfContainers.size(); i++) {
			//get the list of items for that container 
			ArrayList <Item> itemList = listOfContainers.get(i).getItemList();
			
			//for each item in the container check if there is a match
			for (int j = 0; j < itemList.size(); j++) {
				if (itemName.equals(itemList.get(j).getItemName())) {
					//add the container to the list of containers holding that item
					foundItems.add(listOfContainers.get(i));
					numItems++;
				}
			}
			
			
		}
		
		//ask which container to remove from if there are multiple
		if (numItems > 1) {
			System.out.println("Item is located in the folowing containers: ");
			for (int i = 0; i < foundItems.size(); i++) {
				System.out.println("	" + foundItems.get(i).getContainerName());
			}
			
			System.out.println("Enter container name to remove item from.");
			String conName = scn.next();
			
			//remove container from the list leaving only the container we want to remove from
			for (int i = 0; i < foundItems.size(); i++) {
				if(!conName.equals(foundItems.get(i).getContainerName())) {
					foundItems.remove(foundItems.get(i));
				}
			}
		} else if(numItems == 0) {
			System.out.println("No item by that name was found");
			TestClass.caseStatment();
		}
		
		//get the list of items from the container
		ArrayList <Item> finalItemList = foundItems.get(0).getItemList();
		int numToDelete;
		
		//ask how many to delete
		for (int i = 0; i < finalItemList.size(); i++) {
			//locate correct item
			if (itemName.equals(finalItemList.get(i).getItemName())) {
				//get current quantity
				int numItem = finalItemList.get(i).getItemQuantity();
				
				//ask how many to remove
				System.out.println("The current quantity of this item is " + numItem + ". How many would you like to delete?");
				String deleteNum = scn.next();
				
				numToDelete = Integer.parseInt(deleteNum);
				
				//calculate new quantity 
				int newNum = numItem - numToDelete;
				
				//if no item left delete it from the list				
				if (newNum == 0) {
					finalItemList.remove(finalItemList.get(i));
					
				//else update the item quantity
				} else {
					finalItemList.get(i).setItemQuantity(newNum);
				}
				
			}
		}
		
		TestClass.caseStatment();
		
		scn.close();
		
	}
	
	//print items by container or all
	public static void printItems() {
		 @SuppressWarnings("resource")
		Scanner scn = new Scanner(System.in);
		 
		 //ask user what they need
		 System.out.println("1: Items by specific container\n2: All items\n3: Search item");
		 String userInput = scn.nextLine();
		 
		 switch (userInput) {
		 
		 case "1":
			 
			 System.out.println("Enter container name.");
			 String conName = scn.nextLine();
			 
			 //find the right container
			 for (int i = 0; i < listOfContainers.size(); i++) {
				 if (conName.equals(containerNames.get(i))) {
					 
					 //get the items in the container
					 ArrayList <Item> itemList = listOfContainers.get(i).getItemList();
					 itemList = listOfContainers.get(i).getItemList();
					 
					 //print items in container
					 for (int j = 0; j < itemList.size(); j++	) {
						 System.out.println("Item: " + itemList.get(j).getItemName() + ". Quantity: " + itemList.get(j).getItemQuantity());
					 }
				 }
			 }
			 
			 break;
			 
		 case "2":
			 
			 //loop through all containers
			 for (int i = 0; i < listOfContainers.size(); i++) {
				 System.out.println("\nContainer " + containerNames.get(i) + " contains: ");
				 
				//get the items in the container
				 ArrayList <Item> itemList = listOfContainers.get(i).getItemList();
				 itemList = listOfContainers.get(i).getItemList();
				 
				 //print items for each container
				 for(int j = 0; j < itemList.size(); j++) {
					 System.out.print("    Item: " + itemList.get(j).getItemName() + ". Quantity: " + itemList.get(j).getItemQuantity());
				 }
				 
			 }
			 
			 break;
			 
		 case "3":
			 
			 ArrayList<String> saveName = new ArrayList<String>();
			 boolean itemFound = false;
			 
			 //get item name
			 System.out.println("Enter item name.");
			 String itemName = scn.nextLine();
			 
			 //loop through all containers
			 for (int i = 0; i < listOfContainers.size(); i++) {
				 
				//get the items in the container
				 ArrayList <Item> itemList = listOfContainers.get(i).getItemList();
				 itemList = listOfContainers.get(i).getItemList();
				 
				 //save name for containers that have the item
				 for(int j = 0; j < itemList.size(); j++) {
					 if (itemName.equals(itemList.get(j).getItemName())){
						 saveName.add(listOfContainers.get(i).getContainerName());
						 itemFound = true;
					 }
				 }
				 
			 }
			 
			 if (itemFound == false) {
				 System.out.println("No Item found");
			 } else {
				 System.out.println("Item found in Container(s):");
				 for (int i = 0; i < saveName.size(); i++) {
					 System.out.println("	- " + saveName.get(i) + "\n");
				 }
					
			 }
			 
			 break;
			 
		 default:
			System.out.println("Error. Invalid Entry.");
			printItems();
		 
		 }
	}
	
	
	//save Containers
	public static void saveContainers() {
		int numContainers = listOfContainers.size();
		
		try {
			//open the file to save containers to
			FileOutputStream fileOut = new FileOutputStream("storageFile.dat");
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	          
	        //save all containers
	        for (int i = 0; i < listOfContainers.size(); i++) {
	        	listOfContainers.get(i).setNumContainers(numContainers);
	        	out.writeObject(listOfContainers.get(i));
	        }
	          
	        out.close();
	        fileOut.close();
	        
		} catch (IOException ioe) {
			System.err.println("Error");
		}
	}
	
	//load containers
	public static void loadContainers() throws ClassNotFoundException {
		
		//create container to save to
		Container newContainer;
		int size;
		
	    try {
	    	
	    	//open file
	    	FileInputStream fileIn = new FileInputStream("storageFile.dat");
	        ObjectInputStream objIn = new ObjectInputStream(fileIn);
	        
	        //get the first container object
	        newContainer = (Container) objIn.readObject();
        	
	        //save container to the list
    	    listOfContainers.add(newContainer);
    	    containerNames.add(newContainer.getContainerName());
    	    
    	    //get the total number of containers
    	    size = newContainer.getNumContainers();
	        
    	    //save the remaining containers 
	        for(int i = 1; i < size; i++) {
	        	newContainer = (Container) objIn.readObject();
	        	
	    	    listOfContainers.add(newContainer);
	    	    containerNames.add(newContainer.getContainerName());
	        }
	        
	        objIn.close();
	        fileIn.close();
	          
	    } catch (IOException ioe) {
	    	System.err.println("Error");
	    } catch (ClassNotFoundException cnfe) {
	    	System.err.println("Error");
	       }
	}
	
	//get container name list
	public static ArrayList<String> getContainerName() {
		return containerNames;
	}
	
	//set container name list
	public void setContainerName(ArrayList<String> setList) {
		containerNames = setList;
	}
	
	//get container list
	public static ArrayList<Container> getContainerList() {
		return listOfContainers;
	}
	
	//set container list
	public void setContainerList(ArrayList<Container> setList) {
		listOfContainers = setList;
	}
	

	
}//class

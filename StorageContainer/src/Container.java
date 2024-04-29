import java.util.*;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Container implements Serializable {

	private String containerName;
	
	ArrayList<Item> containerItems = new ArrayList<Item>();
	
	int numContainers;
	
	//get hasNext
	public int getNumContainers() {
		return numContainers;
	}
	//set hasNext
	public void setNumContainers(int setNum) {
		numContainers = setNum;
	}
	
	//get the name of the container
	public String getContainerName() {
		return containerName;
	}
	
	//set the name of the container
	public void setContainerName(String setName) {
		containerName = setName;
	}
	
	//get item list
	public ArrayList<Item> getItemList(){
		return containerItems;
	}	
	
	//set item list
	public void setItemList(ArrayList<Item> setList) {
		containerItems = setList;
	}
	
	
	
}

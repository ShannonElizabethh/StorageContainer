import java.io.Serializable;

@SuppressWarnings("serial")
public class Item implements Serializable {

	private String itemName;
	private int itemQuantity;
	
	//get item name
	public String getItemName() {
		return itemName;
	}
	
	//set item name
	public void setItemName(String setItemName) {
		itemName = setItemName;
	}
	
	//get item quantity
	public int getItemQuantity() {
		return itemQuantity;
	}
	
	//set item quantity
	public void setItemQuantity(int setItemQuantity) {
		itemQuantity = setItemQuantity;
	}
	
}

package facade;

/*
Flow an order fulfillment process. When an order is placed:
	Inventory service: Checks the availability of the product.
	Shipping service: Ships a product.
 */


class Product {
	private int id;

	Product(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}

class InventoryService {
    
	public static boolean isAvail(Product product){
        return true;
    }
}

class ShippingService {
    public static void shipProduct(Product product){
    }
}

// for D of SOLID
interface OrderServiceFacade {
    boolean placeOrder(int productId);
}

// Facade
class OrderServiceFacadeImpl implements OrderServiceFacade{

	@Override
	public boolean placeOrder(int pId) {
		Product product = new Product(pId);
		
		if(InventoryService.isAvail(product)){
			ShippingService.shipProduct(product);
		}
		
		return false;
	}
	
}



public class Demo {
	public static void main(String args[]){
		OrderServiceFacadeImpl facade = new OrderServiceFacadeImpl();
		// client is not aware of Inventory and Shipping Service
		facade.placeOrder(0);
	}
}

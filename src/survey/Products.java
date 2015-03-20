package survey;

public enum Products {
	iphone6s("iphone6s",0),SamsungGalaxy7("Samsung Galaxy 7",1),NokiaLumia532("Nokia Lumia 532",2);
	private String name;
	private int index;

	public int getIndex() {
		return index;
	}

	private Products(String name, int index) {
		this.name = name;
		this.index = index;
	}
	
	public static String getName(int index){
		for (Products p : Products.values()) {
				if (p.getIndex() == index) {return p.name;}
			}
			return "";
	}
}

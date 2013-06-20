package com.example.demogooglemapsv2;

public class Owner {
	public String Name;
	public String Address;
	public String Phone;
	public String Price;

	public Owner()
	{
		
	}
	
	public Owner(String Name,String Address,String Phone,String Price)
	{
		this.Name = Name;
		this.Address =Address;
		this.Phone = Phone;
		this.Price =Price;
	}
    public String getName() {
        return this.Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    
    public String getAddress() {
        return this.Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }
    
    public String getPhone() {
        return this.Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }
    
    public String getPrice() {
        return this.Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

	
}

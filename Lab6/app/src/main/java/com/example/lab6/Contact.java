package com.example.lab6;

public class Contact {
    private long id;
    private String name;
    private String lastname;
    private String phone_number;
    public Contact() {

    }
    public Contact(String name, String lastname, String phone_number) {
        this.name = name;
        this.lastname = lastname;
        this.phone_number = phone_number;
    }

    public long getId()
    {
        return id;
    }
    public void setId(long i)
    {
        id = i;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String n)
    {
        name = n;
    }
    public String getLastname()
    {
        return lastname;
    }
    public void setLastname(String l)
    {
        lastname = l;
    }
    public String getPhone_number()
    {
        return phone_number;
    }
    public void setPhone_number(String p)
    {
        phone_number = p;
    }
}

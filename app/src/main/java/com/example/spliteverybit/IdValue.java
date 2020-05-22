package com.example.spliteverybit;

public class IdValue {

    public String amount,parentid;
    public IdValue()
    {

    }

    public IdValue(String amount,String parentid) {

        this.amount = amount;
        this.parentid=parentid;
    }


    public String getAmount() { return amount; }
    public  String getParentid() {return parentid ;}
}

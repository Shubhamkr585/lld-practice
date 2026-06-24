class CashPayment implements IPaymentStrategy{
    public void pay(int amount){
        System.out.println("Paid "+ amount + " using cash");
    }
}

class CardPayment implements IPaymentStrategy{
    public void pay(int amount){
        System.out.println("Payment "+ amount + " using card");
    }
}

class UpiPayment implements IPaymentStrategy{
    public  void pay(int amount){
        System.out.println("Payment "+ amount + " using upi");
    }
}
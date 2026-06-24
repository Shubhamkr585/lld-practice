class PayementFactory{
    static IPaymentStrategy get(PaymentType type){
        switch (type){
            case CASH : return new CashPayment();
            case CARD : return new CardPayment();
            case UPI  : return new UpiPayment();
            default : throw new IllegalArgumentException("Unknown Payment Type");
        }
    }
}
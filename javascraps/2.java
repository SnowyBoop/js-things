package de.uniba.wiai.dsg.ajp.assignment3;

public class Coupon {
    public int CouponCode;
    public int Sum;

    public Coupon(int code, int sumn) {
        if(CouponCode > 0 && Sum > 0) {
            this.CouponCode = code;
            this.Sum = sumn;
        } else {
            System.out.println("Neither Code or Sumn can be 0!");
        }

    }

    public int getCouponCode() {
        return CouponCode;
    }

    public int getCouponSum() {
        return Sum;
    }
}

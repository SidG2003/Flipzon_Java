import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
public class Customer {
    public int custId;
    public String custName;
    public String custPwd;
    public float wallet=1000;
    public String custStatus="NORMAL";

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustPwd() {
        return custPwd;
    }

    public void setCustPwd(String custPwd) {
        this.custPwd = custPwd;
    }

    public float getWallet() {
        return wallet;
    }

    public void setWallet(float wallet) {
        this.wallet = wallet;
    }

    public String getCustStatus() {
        return custStatus;
    }

    public void setCustStatus(String custStatus) {
        this.custStatus = custStatus;
    }

    public General_Product gift;
//    public int coupons[]=new int[20];
    //add cart later
    public ArrayList<CouponValue> coupons=new ArrayList<CouponValue>();
    public Cart custCart= new Cart();
    public Customer(String name,String pwd){
        this.custName=name;
        this.custPwd=pwd;
    }
    public Customer(){
        this.custName="";
        this.custPwd="";
    }
    public int getMaxCoupon(){
        int maxC=0;
        for(int i=0;i<coupons.size();i++){
            CouponValue c =coupons.get(i);
            if(c.disc>maxC){
                maxC=c.disc;
            }
        }
        return maxC;
    }
    public void issueCoupons(){
        if(this.custStatus.equals("NORMAL")){
        }
        if(this.custStatus.equals("PRIME")){
            int no_coupons = ThreadLocalRandom.current().nextInt(1,3);
            for(int i=0;i<no_coupons;i++){
                int disc=ThreadLocalRandom.current().nextInt(5,16);
                CouponValue cv=new CouponValue();
                cv.disc=disc;
                coupons.add(cv);
                System.out.println("you have won a coupon of "+disc+"%");
            }
//            Arrays.sort(this.coupons);
        }
        if(this.custStatus.equals("ELITE")){
            int no_coupons = ThreadLocalRandom.current().nextInt(3,5);
            for(int i=0;i<no_coupons;i++){
                int disc=ThreadLocalRandom.current().nextInt(5,16);
                CouponValue cv=new CouponValue();
                cv.disc=disc;
                coupons.add(cv);
                System.out.println("you have won a coupon of "+disc+"%");
            }
//            Arrays.sort(this.coupons);
        }
    }

//    public static void signUpCustomer(){
//        this.
//    }
}

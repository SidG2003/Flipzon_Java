import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

interface cart_interface{
    public float calcTotAmt(Customer c);
    public void addItem(CartItem c);
    public void checkout(Customer c);
    public boolean stockCheck();
    public void stockReduce();
    public General_Product surpriseGift();
    public void printOrder(Customer cust,String msg);
}

public class Cart implements cart_interface{

    public ArrayList<CartItem> cartItemsList=new ArrayList<CartItem>();
    public float totalAmount;

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public float calcTotAmt(Customer c){
        for(int i=0;i<cartItemsList.size();i++){
            CartItem ci=cartItemsList.get(i);
            totalAmount+=ci.calcProdAmt(c);
        }
        if (c.custStatus=="ELITE"){
            totalAmount=totalAmount+100;
        }
        else if(c.custStatus=="PRIME"){
            totalAmount=totalAmount+100+(float)1.02*totalAmount;
        }
        else if(c.custStatus=="NORMAL"){
            totalAmount=totalAmount+100+(float)1.05*totalAmount;
        }
        return totalAmount;
    }
    public void addItem(CartItem c){
        cartItemsList.add(c);
    }
    public void checkout(Customer c){
        String del_msg="Your order will be delivered in 7-10 days";
        float tot=calcTotAmt(c);
        boolean flag_coupon_used=false;
        if(tot<=c.wallet){
            if(stockCheck()){
                stockReduce();
                for(int i=0;i<cartItemsList.size();i++) {
                    CartItem ci = cartItemsList.get(i);
                    if(ci.maxCouponDisc>0){
                        flag_coupon_used=true;
                        break;
                    }
                }
                if(flag_coupon_used){
                    int max_index = 0;
                    int max_val = 0;
                    for(int i=0;i<c.coupons.size();i++){
                        CouponValue cv =c.coupons.get(i);
                        if(cv.disc>max_val){
                            max_val=cv.disc;
                            max_index=i;
                        }
                    }
                    c.coupons.remove(max_index);
//                    delCoupon();
                }//max_disc==max_coup_disc, del coupon
                if(c.custStatus.equals("ELITE")){
                    del_msg="Your order will be delivered in 2 days";
                    c.gift=surpriseGift();
                    if(totalAmount>=5000){
                        c.issueCoupons();
                    }
                }
                if(c.custStatus.equals("PRIME")){
                    del_msg="Your order will be delivered in 3-6 days";
                    if(totalAmount>=5000){
                        c.issueCoupons();
                    }
                }
                printOrder(c,del_msg);
                cartItemsList.clear();
            }
        }
    }
    public boolean stockCheck(){
        for(int i=0;i<cartItemsList.size();i++) {
            CartItem ci = cartItemsList.get(i);
            General_Product p=ci.added_prod;
            if(ci.quantity>= p.stock){
                System.out.println("Some products in insufficient stock");
                return false;
            }
        }
        return true;
    }

    public void stockReduce(){
        for(int i=0;i<cartItemsList.size();i++) {
            CartItem ci = cartItemsList.get(i);
            General_Product p = ci.added_prod;
            p.stock-=ci.quantity;
        }
    }

    public General_Product surpriseGift(){
        int indx= ThreadLocalRandom.current().nextInt(0,Flipzon.productList.size());
        General_Product p=Flipzon.productList.get(indx);
        return p;
    }

    public void printOrder(Customer cust,String msg){
        float totAmt = 0;
        float totDiscountedAmt=0;
        float totDisc = 0;
        System.out.println("________Your order_______");
        if(cust.custCart.cartItemsList.size()==0){
            System.out.println("Cart empty");
        }
        else{
            for(int i =0;i<cust.custCart.cartItemsList.size();i++){
                CartItem c =cust.custCart.cartItemsList.get(i);
                System.out.println("\nproduct id: "+c.added_prod.productId);
                System.out.println("product name: "+c.added_prod.prodName);
                System.out.println("product price: "+c.added_prod.price);
                System.out.println("product quantity: "+c.quantity);
                System.out.println("product final discounted amount: "+c.amount);
                if(c.dealId!=0){
                    System.out.println("Deal id: "+c.dealId);
                    System.out.println("Individual deal amt: "+c.dealAmt);
                }
                System.out.println("------------------------------");
                totAmt+=c.added_prod.price*c.quantity;
                totDiscountedAmt+=c.amount;
            }
            totDisc=totAmt-totDiscountedAmt;
        }
        if(cust.custStatus.equals("ELITE")){
            System.out.println("Delivery charges: Rs 100");
            System.out.println("Discount: "+totDisc);
            System.out.println("Total cost: "+(totDiscountedAmt+100));
            System.out.println("YOU WON A GIFT: "+cust.gift.prodName);
            System.out.println(msg);
        }
        else if(cust.custStatus.equals("PRIME")){
            System.out.println("Delivery charges: Rs 100 + 2% of "+totDiscountedAmt +" = "+totDiscountedAmt*(float)0.02+(float)100 );
            System.out.println("Discount: "+totDisc);
            System.out.println("Total cost: "+totDiscountedAmt*(float)1.02+(float)100 );
            System.out.println(msg);
        }
        else if(cust.custStatus.equals("NORMAL")){
            System.out.println("Delivery charges: Rs 100 + 5% of "+totDiscountedAmt +" = "+totDiscountedAmt*(float)0.05+(float)100 );
            System.out.println("Discount: "+totDisc);
            System.out.println("Total cost: "+totDiscountedAmt*(float)1.05+(float)100);
            System.out.println(msg);
        }
    }

}

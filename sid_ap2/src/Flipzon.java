import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Flipzon{
    public static ArrayList<Customer> customerList = new ArrayList<Customer>();
    public static ArrayList<Category> categoryList = new ArrayList<Category>();
    public static ArrayList<General_Product> productList = new ArrayList<General_Product>();
    public static ArrayList<Deals> dealList = new ArrayList<Deals>();
    public static final int eliteMemberDisc=10;
    public static final int primeMemberDisc=5;
    public static final int normalMemberDisc=0;
    Random random = new Random();
    //    public static Admin adm = new Admin();
    static Scanner sc = new Scanner(System.in);
    public static void main(String args[]){
        boolean flag=true;
        while(flag==true){
            flag=mainMenu();
        }
    }
    public static boolean mainMenu(){
        System.out.println("\nWELCOME TO FLIPZON, select option\n" +
                "1) Enter as Admin\n" +
                "2) Explore the Product Catalog\n" +
                "3) Show Available Deals\n" +
                "4) Enter as Customer\n" +
                "5) Exit the Application");
        String query=sc.nextLine();
        if(query.equals("1")){
            adminEntryMenu();
            return true;
        }
        else if(query.equals("2")){
            browseCatalog();
            return true;
        }
        else if(query.equals("3")){
            browseDeals();
            return true;
        }
        else if(query.equals("4")){
            customerMenu();
            return true;
        }
        else if(query.equals("5")){
            System.out.println("Thanks for using Flipzon");
            return false;
        }
        else{
            System.out.println("Enter valid choice");
            return true;
        }
    }

    public static void browseCatalog(){
        for(int i=0;i<categoryList.size();i++){
            Category cat=categoryList.get(i);
            System.out.println("\n*************CatId: "+cat.catId+" CatName:" +cat.catName+" ****************");
            for(int j=0;j<productList.size();j++){
                General_Product prod=productList.get(j);
                if(prod.categoryId== cat.catId){
                    System.out.println("\n");
                    System.out.println("product cat id: "+prod.categoryId);
                    System.out.println("product id: "+prod.productId);
                    System.out.println("product name: "+prod.prodName);
                    System.out.println("product price: "+prod.price);
                    System.out.println("elite product discount: "+prod.eliteDisc);
                    System.out.println("prime product discount: "+prod.primeDisc);
                    System.out.println("normal product discount: "+prod.normalDisc);
                    for(int k=0;k<prod.attributeName.length;k++){
                        if(prod.attributeName[k]!=null){
                            System.out.println(prod.attributeName[k]+": "+prod.attributeVal[k]);
                        }
                    }
                }
            }

        }
    }
    public static void browseDeals(){
        System.out.println("Available deals!");
        if(dealList.size()!=0){
            for(int i=0;i<dealList.size();i++){
                Deals d=dealList.get(i);
                System.out.println("\n_____Deal____");
                System.out.println("Deal id: "+d.dealId);
                System.out.println("Product1 name: "+d.prod1.prodName+" id: "+d.prod1.productId);
                System.out.println("Product2 name: "+d.prod2.prodName+" id: "+d.prod2.productId);
//                System.out.println("Deal id: "+d.dealId);
                System.out.println("ELite deal price: "+d.elitePrice);
                System.out.println("Prime deal price: "+d.primePrice);
                System.out.println("Normal deal price: "+d.normalPrice);
            }
        }
        else{
            System.out.println("No deals yet");
        }
    }

    public static void adminEntryMenu(){
        boolean flag2=true;
        System.out.println("Enter admin username: ");
        String admUsername=sc.nextLine();
        System.out.println("Enter admin pwd: ");
        String admPwd=sc.nextLine();
        if((admUsername.equals(Admin.getName1()) && admPwd.equals(Admin.getPwd1())) || (admUsername.equals(Admin.getName2()) && admPwd.equals(Admin.getPwd2()))){
            while(flag2==true){
                flag2=adminMenu();
            }
        }
        else{
            System.out.println("Wrong username/pwd");
        }
    }
    public static boolean adminMenu(){
        System.out.println("Welcome Admin");
        System.out.println("Please choose any one of the following actions:\n" +
                "1) Add category\n" +
                "2) Delete category\n" +
                "3) Add Product\n" +
                "4) Delete Product\n" +
                "5) Set Discount on Product\n" +
                "6) Add giveaway deal\n" +
                "7) Back");
        String query=sc.nextLine();
        if(query.equals("1")){
            Admin.addCategory();
            return true;
        }
        else if(query.equals("2")){
            Admin.delCategory();
            return true;
        }
        else if(query.equals("3")){
            Admin.addProduct();
            return true;
        }
        else if(query.equals("4")){
            Admin.delProduct();
            return true;
        }
        else if(query.equals("5")){
            Admin.setProdDisc();
            return true;
        }
        else if(query.equals("6")){
            Admin.addDeal();
            return true;
        }
        else if(query.equals("7")){
            return false;
        }
        else{
            System.out.println("Enter valid choice");
            return true;
        }
    }

    public static void customerMenu(){
        System.out.println("Welcome Customer");
        System.out.println("1) Sign up\n" +
                "2) Log in\n" +
                "3) Back");
        String query = sc.next();

        if(query.equals("1")){
            System.out.println("Enter your name: ");
            String custName=sc.next();
            System.out.println("Create password: ");
            String custPwd=sc.next();
//            System.out.println("Make customer id(int): ");
//            int custId=sc.nextInt();
            if(custName.equals("")){
                Customer new_cust=new Customer();
                new_cust.custName=custName;
                new_cust.custPwd=custPwd;
                customerList.add(new_cust);
            }
            else{
                Customer new_cust=new Customer(custName,custPwd);
                new_cust.custName=custName;
                new_cust.custPwd=custPwd;
                customerList.add(new_cust);
            }
//            new_cust.custId=custId;
//            new_cust.custName=custName;
//            new_cust.custPwd=custPwd;
//            customerList.add(new_cust);
            System.out.println("Registered!");
            customerMenu();
        }
        else if(query.equals("2")){
            boolean flag_cust_reg=false;
            System.out.println("Enter name: ");
            String custName=sc.next();
            System.out.println("Enter password: ");
            String custPwd=sc.next();
            for(int i=0;i<customerList.size();i++){
                Customer cust1 = customerList.get(i);
                if(cust1.custName.equals(custName) && cust1.custPwd.equals(custPwd)){
                    flag_cust_reg=true;
                    System.out.println("Logged in!");
                    loggedInCustMenu(cust1);
                    break;
                }
            }
            if(!flag_cust_reg){
                System.out.println("Customer not registered or wrong name/pwd");
                customerMenu();
            }
        }
        else if(query.equals("3")){
            mainMenu();
        }

    }

    public static void loggedInCustMenu(Customer cust){
        System.out.println("\nWelcome "+cust.custName+" !");
        System.out.println("1) browse products\n" +
                "2) browse deals\n" +
                "3) add a product to cart\n" +
                "4) add products in deal to cart\n" +
                "5) view coupons\n" +
                "6) check account balance\n" +
                "7) view cart\n" +
                "8) empty cart\n" +
                "9) checkout cart\n" +
                "10) upgrade customer status\n" +
                "11) Add amount to wallet\n" +
                "12) back");
        Scanner sc1=new Scanner(System.in);
        String query=sc1.nextLine();
        if(query.equals("1")){
            for(int i=0;i<categoryList.size();i++){
                Category cat=categoryList.get(i);
                System.out.println("\n*************CatId: "+cat.catId+" CatName:" +cat.catName+" ****************");
                for(int j=0;j<productList.size();j++){
                    General_Product prod=productList.get(j);
                    if(prod.categoryId== cat.catId){
                        System.out.println("\n");
                        System.out.println("product cat id: "+prod.categoryId);
                        System.out.println("product id: "+prod.productId);
                        System.out.println("product name: "+prod.prodName);
                        System.out.println("product price: "+prod.price);
                        System.out.println("elite product discount: "+prod.eliteDisc);
                        System.out.println("prime product discount: "+prod.primeDisc);
                        System.out.println("normal product discount: "+prod.normalDisc);
                        for(int k=0;k<prod.attributeName.length;k++){
                            if(prod.attributeName[k]!=null){
                                System.out.println(prod.attributeName[k]+": "+prod.attributeVal[k]);
                            }
                        }
                    }
                }
            }
            loggedInCustMenu(cust);
        }
        else if(query.equals("2")){
//            cust.issueCoupons();
            browseDeals();
            loggedInCustMenu(cust);
        }
        else if(query.equals("3")){
            boolean flag_prod_present=false;
            System.out.println("Enter product id: ");
            float pId=sc1.nextFloat();
            System.out.println("Enter quantity: ");
            int quan=sc1.nextInt();
            for(int j=0;j<productList.size();j++) {
                General_Product prod = productList.get(j);
                if(prod.productId==pId){
                    flag_prod_present=true;
                    if (prod.stock<quan) {
                        System.out.println("Not enough stock available");
                        loggedInCustMenu(cust);
                    }
                    else{
                        CartItem item=new CartItem();
                        item.added_prod=prod;
                        item.quantity=quan;
//                        set-amt
                        cust.custCart.addItem(item);
                    }
                    break;
                }
            }
            if(!flag_prod_present){
                System.out.println("Product not found");
//                loggedInCustMenu(cust);
            }
            loggedInCustMenu(cust);
        }
        else if(query.equals("4")){
            General_Product p1 = null;
            General_Product p2 = null;
            Deals d=null;
            System.out.println("Enter deal id: ");
            int dealId=sc1.nextInt();
            System.out.println("Enter quantity: ");
            int quan=sc1.nextInt();
            for(int j=0;j<dealList.size();j++) {
                d = dealList.get(j);
                if(d.dealId==dealId){
                    p1=d.prod1;
                    p2=d.prod2;
                    break;
                }
            }
            if(quan>p1.stock || quan>p2.stock){
                System.out.println("Not enough quantity available");
            }
            else{
                CartItem c1=new CartItem();
                CartItem c2=new CartItem();
                c1.added_prod=p1;
                c1.quantity=quan;
                c1.dealId=dealId;
                c2.added_prod=p2;
                c2.quantity=quan;
                c2.dealId=dealId;
                c2.dealAmt=0;
                if(cust.custStatus.equals("ELITE")){
                    c1.dealAmt=d.elitePrice*quan;
                }
                else if(cust.custStatus.equals("PRIME")){
                    c1.dealAmt=d.primePrice*quan;
                }
                else if(cust.custStatus.equals("NORMAL")) {
                    c1.dealAmt = d.normalPrice*quan;
                }
                cust.custCart.addItem(c1);
                cust.custCart.addItem(c2);
            }
            loggedInCustMenu(cust);

        }
        else if(query.equals("5")){
            if(cust.coupons.size()==0){
                System.out.println("No coupons yet");
            }
            else{
                for(int i=0;i<cust.coupons.size();i++){
                    CouponValue cv=cust.coupons.get(i);
                    System.out.println("Coupon value: "+cv.disc+"%");
                }
            }
            loggedInCustMenu(cust);
        }
        else if(query.equals("6")){
            System.out.println("Account wallet balance:");
            System.out.println("Rs."+cust.wallet);
            loggedInCustMenu(cust);
        }
        else if(query.equals("7")){
            System.out.println("________Your cart_______");
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
                    if(c.dealId!=0){
                        System.out.println("Deal id: "+c.dealId);
                        System.out.println("Individual deal amt: "+c.dealAmt);
                    }
                    System.out.println("------------------------------");
                }
            }
            loggedInCustMenu(cust);
        }
        else if(query.equals("8")){
            cust.custCart.cartItemsList.clear();
            loggedInCustMenu(cust);

        }
        else if(query.equals("9")){
            cust.custCart.checkout(cust);
            loggedInCustMenu(cust);

        }
        else if(query.equals("10")){
            System.out.println("Current Status: "+cust.custStatus);
            System.out.println("Choose status to upgrade to:\n1)PRIME\n2)ELITE");
            String staNum=sc1.next();
            if(staNum.equals("1")){
                if(cust.wallet>=200){
                    cust.custStatus="PRIME";
                    cust.wallet-=200;
                    System.out.println("Upgraded to prime");
                }
                else{
                    System.out.println("Insufficient balance");
                }
            }
            else if(staNum.equals("2")){
                if(cust.wallet>=300){
                    cust.custStatus="ELITE";
                    cust.wallet-=300;
                    System.out.println("Upgraded to elite");
                }
                else{
                    System.out.println("Insufficient balance");
                }
            }
            loggedInCustMenu(cust);
//            CHECK IF SOMETHING ELSE TO BE UPDATED WITH CHANGE IN STATUS
        }
        else if(query.equals("11")){
            System.out.println("Give amount to add:");
            float amt =sc1.nextFloat();
            cust.wallet+=amt;
            System.out.println("Amount added successfully");
            loggedInCustMenu(cust);

        }
        else if(query.equals("12")){
            customerMenu();
        }

    }

}

import java.util.Scanner;

public class Admin {
    static Scanner sc =new Scanner(System.in);
    private static String name1="bj";
    private static String pwd1="1";

    private static String name2="gb";
    private static String pwd2="2";

    public static String getName1() {
        return name1;
    }

    public static String getPwd1() {
        return pwd1;
    }

    public static String getName2() {
        return name2;
    }

    public static String getPwd2() {
        return pwd2;
    }
    public static void addCategory(){
        boolean flag_alr_cat=false;
        System.out.println("Enter Category ID:" );
        int catId=sc.nextInt();
        System.out.println("Enter Category name:" );
        String catName=sc.next();
        for(int i=0;i<Flipzon.categoryList.size();i++){
            Category cat = Flipzon.categoryList.get(i);
            if(cat.catId==catId){
                System.out.println("Enter unique ID" );
                flag_alr_cat=true;
                break;
            }
        }
        if(flag_alr_cat==false){
            Category new_cat=new Category();
            new_cat.catId=catId;
            new_cat.catName=catName;
            Flipzon.categoryList.add(new_cat);
//            call add-product
            addProduct();
        }


    }
    public static void delCategory(){
//        Category cat = null;
        System.out.println("Choose category(id) to delete:");
        for(int i=0;i<Flipzon.categoryList.size();i++){
            Category cat1 = Flipzon.categoryList.get(i);
            System.out.println(cat1.catId+")"+cat1.catName);
        }
        Scanner sc_delCat=new Scanner(System.in);
        int catId=sc_delCat.nextInt();
        for(int i=0;i<Flipzon.productList.size();i++){
            General_Product prod = Flipzon.productList.get(i);
            if(prod.productId==catId){
                Flipzon.productList.remove(prod);
            }
        }
        for(int i=0;i<Flipzon.categoryList.size();i++){
            Category cat = Flipzon.categoryList.get(i);
            if(cat.catId==catId){
                Flipzon.categoryList.remove(cat);
                break;
            }
        }
    }
    public static void addProduct(){
        Scanner sc1 =new Scanner(System.in);
        boolean flag_alr_prodId=false;
        System.out.println("Add product");
        System.out.println("Enter Category ID:" );
        int catId=sc1.nextInt();
        System.out.println("Enter Product name:" );
        String prodName=sc1.next();
        System.out.println("Enter Product ID:" );
        float prodId=sc1.nextFloat();

        for(int i=0;i<Flipzon.productList.size();i++){
            General_Product prod = Flipzon.productList.get(i);
            if(prod.productId==prodId){
                System.out.println("Enter unique ID" );
                flag_alr_prodId=true;
                break;
            }
        }
        if(flag_alr_prodId==false){
            General_Product new_prod = null;
            if (catId==1){
                new_prod=new speceficProduct1();
            } else if (catId==2) {
                new_prod=new speceficProduct2();
            } else if (catId==3) {
                new_prod=new speceficProduct3();
            }

            System.out.println("Enter Product price:" );
            float prodPrice=sc1.nextFloat();
            System.out.println("Enter stock quantity:" );
            int prodQuan=sc1.nextInt();

            for(int i=0;i<new_prod.attributeName.length;i++){
                if(new_prod.attributeName[i]!=null){
                    System.out.println(new_prod.attributeName[i]+": ");
                    String val=sc1.next();
                    new_prod.attributeVal[i]=val;
                }
            }
            new_prod.categoryId=catId;
            new_prod.prodName=prodName;
            new_prod.productId=prodId;
            new_prod.price=prodPrice;
            new_prod.stock=prodQuan;
            Flipzon.productList.add(new_prod);
//            call add-product
        }

    }
    public static void delProduct(){
        int no_prod_in_cat=0;
        General_Product prod;
        System.out.println("Choose product(id) to delete:");
        for(int i=0;i<Flipzon.productList.size();i++){
            General_Product prod2 = Flipzon.productList.get(i);
            System.out.println(prod2.productId+")"+prod2.prodName);
        }
        Scanner sc_delProduct=new Scanner(System.in);
        float prodId=sc_delProduct.nextFloat();
        int prod_catId=(int)prodId;
        for(int i=0;i<Flipzon.productList.size();i++){
            General_Product prod1 = Flipzon.productList.get(i);
            if(prod1.productId==prod_catId){
                no_prod_in_cat+=1;
            }
        }
        if(no_prod_in_cat==1){
            for(int i=0;i<Flipzon.categoryList.size();i++){
                Category cat = Flipzon.categoryList.get(i);
                if(cat.catId==prod_catId){
                    Flipzon.categoryList.remove(cat);
                }
            }
        }
        for(int i=0;i<Flipzon.productList.size();i++){
            prod = Flipzon.productList.get(i);
            if(prod.productId==prodId){
                Flipzon.productList.remove(prod);
                break;
            }
        }
    }
    public static void setProdDisc(){
        General_Product prod = null;
        System.out.println("Choose product(id) to add discount:");
        for(int i=0;i<Flipzon.productList.size();i++){
            General_Product prod2 = Flipzon.productList.get(i);
            System.out.println(prod2.productId+")"+prod2.prodName);
        }
        Scanner sc_addProdDisc=new Scanner(System.in);
        float prodId=sc_addProdDisc.nextFloat();
        for(int i=0;i<Flipzon.productList.size();i++){
            prod = Flipzon.productList.get(i);
            if(prod.productId==prodId){
                break;
            }
        }
        System.out.println("Enter product discount for elite members (in %):");
        int eliteDisc=sc_addProdDisc.nextInt();
        System.out.println("Enter product discount for prime members (in %):");
        int primeDisc=sc_addProdDisc.nextInt();
        System.out.println("Enter product discount for normal members (in %):");
        int normalDisc=sc_addProdDisc.nextInt();
        prod.eliteDisc=eliteDisc;
        prod.primeDisc=primeDisc;
        prod.normalDisc=normalDisc;
    }
    public static void addDeal(){
        General_Product p1 = null,p2 = null;
        Scanner sc_deal=new Scanner(System.in);
        System.out.println("Enter product1 id:");
        float pId1=sc_deal.nextFloat();
        System.out.println("Enter product2 id:");
        float pId2=sc_deal.nextFloat();
        System.out.println("Create deal id:");
        int dId=sc_deal.nextInt();

        for(int j=0;j<Flipzon.productList.size();j++) {
            General_Product prod = Flipzon.productList.get(j);
            if(prod.productId==pId1){
                p1=prod;
            }
            else if(prod.productId==pId2){
                p2=prod;
            }
        }
        float emp=p1.price*((float)1-(float)p1.eliteDisc/100)+p2.price*((float)1-(float)p2.eliteDisc/100);
        float pmp=p1.price*((float)1-(float)p1.primeDisc/100)+p2.price*((float)1-(float)p2.primeDisc/100);
        float nmp=p1.price*((float)1-(float)p1.normalDisc/100)+p2.price*((float)1-(float)p2.normalDisc/100);
//        float pmp=p1.price*(1.0-p1.primeDisc/100)+p2.price*(1.0-p2.primeDisc/100);
//        float nmp=p1.price*(1.0-p1.normalDisc/100)+p2.price*(1.0-p2.normalDisc/100);
        System.out.println("Enter elite deal price(less than " + emp+ "): ");
        float ep=sc_deal.nextFloat();
        System.out.println("Enter prime deal price(less than " + pmp+ "): ");
        float pp=sc_deal.nextFloat();
        System.out.println("Enter normal deal price(less than " + nmp+ "): ");
        float np=sc_deal.nextFloat();
        Deals d=new Deals();
        d.prod1=p1;
        d.prod2=p2;
        d.dealId=dId;
        d.elitePrice=ep;
        d.primePrice=pp;
        d.normalPrice=np;
        Flipzon.dealList.add(d);
    }
}

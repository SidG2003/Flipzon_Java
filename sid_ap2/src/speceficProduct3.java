//Groceries and Vegetables
public class speceficProduct3 extends General_Product {
    public String items;
    public int serving_size;
    public String expiry;

    public speceficProduct3(){
        attributeName[0]="Items";
        attributeName[1]="Serving size";
        attributeName[2]="expiry";

    }
    public speceficProduct3(String items,String size,String exp){
        attributeName[0]=items;
        attributeName[1]=size;
        attributeName[2]=exp;

    }
}

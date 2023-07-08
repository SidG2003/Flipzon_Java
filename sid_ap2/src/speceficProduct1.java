
//Home Appliances
public class speceficProduct1 extends General_Product {
//    public int capacity; //in litres
//    public int powerConsumption; //in KW

    public speceficProduct1(){
        this.attributeName[0]="capacity";
        this.attributeName[1]="powerConsumption";
    }
    public speceficProduct1(String cap,String pc){
        this.attributeName[0]=cap;
        this.attributeName[1]=pc;
    }
}

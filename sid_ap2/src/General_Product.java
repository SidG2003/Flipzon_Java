
interface gen_prod_interface{
    int checkAvailability();
    void reduceItemStock(int num);
}

public class General_Product implements gen_prod_interface{
    public String attributeName[] =new String[3];
    public String attributeVal[]=new String[3];

    public int categoryId;
    public float productId;
    public String prodName;
    public float price;
    public int stock;
    public int eliteDisc=0;
    public int primeDisc=0;
    public int normalDisc=0;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public float getProductId() {
        return productId;
    }

    public void setProductId(float productId) {
        this.productId = productId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getEliteDisc() {
        return eliteDisc;
    }

    public void setEliteDisc(int eliteDisc) {
        this.eliteDisc = eliteDisc;
    }

    public int getPrimeDisc() {
        return primeDisc;
    }

    public void setPrimeDisc(int primeDisc) {
        this.primeDisc = primeDisc;
    }

    public int getNormalDisc() {
        return normalDisc;
    }

    public void setNormalDisc(int normalDisc) {
        this.normalDisc = normalDisc;
    }

    public int checkAvailability(){
        return stock;
    }

    public void reduceItemStock(int num){
        stock-=num;
    }
}


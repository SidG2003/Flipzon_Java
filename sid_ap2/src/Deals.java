public class Deals {
    int dealId;
    public General_Product prod1;
    public General_Product prod2;
    public float elitePrice;
    public float primePrice;
    public float normalPrice;

    public int getDealId() {
        return dealId;
    }

    public void setDealId(int dealId) {
        this.dealId = dealId;
    }

    public float getElitePrice() {
        return elitePrice;
    }

    public void setElitePrice(float elitePrice) {
        this.elitePrice = elitePrice;
    }

    public float getPrimePrice() {
        return primePrice;
    }

    public void setPrimePrice(float primePrice) {
        this.primePrice = primePrice;
    }

    public float getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(float normalPrice) {
        this.normalPrice = normalPrice;
    }
}

public class CartItem {
    General_Product added_prod =new General_Product();
    int quantity;
    float amount;
    int dealId;
    float dealAmt;
    int maxCouponDisc;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getDealId() {
        return dealId;
    }

    public void setDealId(int dealId) {
        this.dealId = dealId;
    }

    public float getDealAmt() {
        return dealAmt;
    }

    public void setDealAmt(float dealAmt) {
        this.dealAmt = dealAmt;
    }

    public int getMaxCouponDisc() {
        return maxCouponDisc;
    }

    public void setMaxCouponDisc(int maxCouponDisc) {
        this.maxCouponDisc = maxCouponDisc;
    }

    public float calcProdAmt(Customer c){
        if(dealId!=0){
            amount=dealAmt;
            return amount;
        }
        else{
            int max_disc=0;
            if (c.custStatus=="ELITE"){
                int eMemDisc=Flipzon.eliteMemberDisc;
                int eProdDisc=added_prod.eliteDisc;
                int eCouponDisc=c.getMaxCoupon();
                if(eMemDisc>=eProdDisc && eMemDisc>=eCouponDisc){
                    max_disc=eMemDisc;
                }
                else if(eProdDisc>=eMemDisc && eProdDisc>=eCouponDisc){
                    max_disc=eProdDisc;
                }
                else{
                    max_disc=eCouponDisc;
                    maxCouponDisc=max_disc;
                }

            }
            else if(c.custStatus=="PRIME"){
                int pMemDisc=Flipzon.primeMemberDisc;
                int pProdDisc=added_prod.primeDisc;
                int pCouponDisc=c.getMaxCoupon();
                if(pMemDisc>=pProdDisc && pMemDisc>=pCouponDisc){
                    max_disc=pMemDisc;
                }
                else if(pProdDisc>=pMemDisc && pProdDisc>=pCouponDisc){
                    max_disc=pProdDisc;
                }
                else{
                    max_disc=pCouponDisc;
                    maxCouponDisc=max_disc;
                }
            }
            else if(c.custStatus=="NORMAL"){
                int nMemDisc=Flipzon.normalMemberDisc;
                int nProdDisc=added_prod.normalDisc;
                int nCouponDisc=0;
                if(nMemDisc>=nProdDisc && nMemDisc>=nCouponDisc){
                    max_disc=nMemDisc;
                }
                else if(nProdDisc>=nMemDisc && nProdDisc>=nCouponDisc){
                    max_disc=nProdDisc;
                }
                else{
                    max_disc=nCouponDisc;
                    maxCouponDisc=max_disc;
                }
            }
            amount=added_prod.price*quantity*((float)1-(float)max_disc/100);
            return amount;
        }
    }
}

package app.models;

import java.io.InputStream;
import java.io.Serializable;

public class Lot implements Serializable {
    private int id;
    private int bid;
    private int bidQuantity;
    private int sellerId;
    private int buyerId;
    private int condition;
    private String status;
    private String name;
    private String image;
    private String imageContent;
    private String startTime;
    private String finishTime;
    private InputStream imageInputStream;

    public Lot(){
        id = 0;
        bid = 0;
        bidQuantity = 0;
        sellerId = 0;
        buyerId = 0;
        condition = -1;
        status = "";
        name = "";
        image = "";
        imageContent = "";
        startTime = "";
        finishTime = "";
        imageInputStream = null;
    }

    public int getLotId(){
        return id;
    }
    public void setLotId(int id){
        this.id = id;
    }

    public int getBid(){
        return bid;
    }
    public void setBid(int bid){
        this.bid = bid;
    }

    public int getBidQuantity(){
        return bidQuantity;
    }
    public void setBidQuantity(int bidQuantity){
        this.bidQuantity = bidQuantity;
    }

    public int getSellerId(){
        return sellerId;
    }
    public void setSellerId(int sellerId){
        this.sellerId = sellerId;
    }

    public int getBuyerId(){
        return buyerId;
    }
    public void setBuyerId(int buyerId){
        this.buyerId = buyerId;
    }

    public int getCondition(){
        return condition;
    }
    public void setCondition(int condition){
        this.condition = condition;
    }

    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }

    public String getLotName(){
        return name;
    }
    public void setLotName(String name){
        this.name = name;
    }

    public String getImageName(){
        return image;
    }
    public void setImageName(String image){
        this.image = image;
    }

    public String getImageContent(){
        return imageContent;
    }
    public void setImageContent(String imageContent){
        this.imageContent = imageContent;
    }



    public InputStream getImageInputStream(){
        return imageInputStream;
    }
    public void setImageInputStream(InputStream imageInputStream){
        this.imageInputStream = imageInputStream;
    }



    public String getStartTime(){
        return startTime;
    }
    public void setStartTime(String startTime){
        this.startTime = startTime;
    }

    public String getFinishTime(){
        return finishTime;
    }
    public void setFinishTime(String finishTime){
        this.finishTime = finishTime;
    }
}

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
    private int status;
    private String name;
    private String image;
    private String imageContent;
    private String startTime;
    private String finishTime;
    private InputStream fileContent;
    //private byte[] fileContent;

    public Lot(){
        id = 0;
        bid = 0;
        bidQuantity = 0;
        sellerId = 0;
        buyerId = 0;
        condition = -1;
        status = -1;
        name = "";
        image = "";
        imageContent = "";
        startTime = "";
        finishTime = "";
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

    public int getStatus(){
        return status;
    }
    public void setStatus(int status){
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

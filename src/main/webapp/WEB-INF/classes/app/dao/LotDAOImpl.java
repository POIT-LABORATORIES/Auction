package app.dao;

import app.dao.exception.DAOException;
import app.data.DB;
import app.data.DBUtil;
import app.models.Lot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class LotDAOImpl implements LotDAO {
    @Override
    public List<Lot> getActiveLots() throws DAOException {
        PreparedStatement ps = null;
        try {
            List<Lot> lotList = new ArrayList<>();
            try(Connection connection = DB.getConnection()){
                String selectQuery = "SELECT * FROM items WHERE status = 'ACTIVE'";
                ps = connection.prepareStatement(selectQuery);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Lot lot = new Lot();
                    lot.setLotId(rs.getInt("item_id"));
                    lot.setLotName(rs.getString("item_name"));
                    lot.setCondition(rs.getInt("item_condition"));
                    lot.setStatus(rs.getString("status"));
                    lot.setBid(rs.getInt("bid"));
                    lot.setBidQuantity(rs.getInt("bid_quantity"));
                    lot.setImageName(rs.getString("main_image_name"));
                    lot.setImageInputStream(rs.getBinaryStream("image_content"));
                    lot.setSellerId(rs.getInt("seller_id"));
                    lot.setBuyerId(rs.getInt("buyer_id"));
                    lot.setStartTime(rs.getTimestamp("start_time").toString());
                    lot.setFinishTime(rs.getTimestamp("finish_time").toString());
                    lotList.add(lot);
                }
            }
            return lotList;
        } catch (Exception e) {
            throw new DAOException(e);
        } finally{
            DBUtil.closeStatement(ps);
        }
    }

    @Override
    public List<Lot> getActiveLots(int sellerId) throws DAOException {
        return null;
    }

    @Override
    public List<Lot> getExpiredLots(int sellerId) throws DAOException {
        return null;
    }

    @Override
    public List<Lot> getSoldLots(int sellerId) throws DAOException {
        return null;
    }

    /*
    private int id;
    private int bid;
    private int bidQuantity;
    private int sellerId;
    private int buyerId;
    private int condition;
    private int status;
    private String name;
    private String image;
    private String startTime;
    private String finishTime;
    */
    @Override
    public void create(Lot lot) throws DAOException {
        PreparedStatement ps = null;
        try {
            try(Connection connection = DB.getConnection()){
                /*
                int id = lot.getLotId();
                int bid = lot.getBid();
                int bidQuantity = lot.getBidQuantity();
                int sellerId = lot.getSellerId();
                int buyerId = lot.getBuyerId();
                int condition = lot.getCondition();
                int status = lot.getStatus();
                String name = lot.getLotName();
                String image = lot.getImageName();
                String imageContent = lot.getImageContent();
                String finishTime = lot.getFinishTime();
                */

                /*
                String selectQuery = "INSERT INTO items " +
                        "(item_id, item_name, item_condition, status," +
                        " bid, bid_quantity, main_image_name, image_content, seller_id, " +
                        "buyer_id, start_time, finish_time) " +
                        "VALUES (NULL, ?, ?, NULL, ?, NULL, ?, ?, ?, 1, NULL, ?);";
                */
                String selectQuery = "INSERT INTO items " +
                        "(item_name, item_condition," +
                        " bid, main_image_name, image_content, seller_id, " +
                        "buyer_id, finish_time) " +
                        "VALUES (?, ?, ?, ?, ?, ?, 1, ?);";
                ps = connection.prepareStatement(selectQuery);
                ps.setString(1, lot.getLotName());
                ps.setInt(2, lot.getCondition());
                ps.setInt(3, lot.getBid());
                ps.setString(4, lot.getImageName());
                ps.setBinaryStream(5, lot.getImageInputStream());
                //ps.setInt(6, lot.getSellerId());
                ps.setInt(6, 1);
                ps.setTimestamp(7, Timestamp.valueOf(lot.getFinishTime()));
                if (ps.executeUpdate() == 0){
                    throw new DAOException("Cannot add new lot to database");
                }
            }
        } catch (Exception e) {
            throw new DAOException(e);
        } finally{
            DBUtil.closeStatement(ps);
        }
    }

    @Override
    public Lot retrieve(int id) throws DAOException {
        return null;
    }

    @Override
    public void update(Lot lot) throws DAOException {

    }

    @Override
    public void delete(Lot lot) throws DAOException {

    }
}

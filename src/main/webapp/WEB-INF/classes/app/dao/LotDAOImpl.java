package app.dao;

import app.dao.exception.DAOException;
import app.data.DB;
import app.data.DBUtil;
import app.models.Lot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class LotDAOImpl implements LotDAO {
    @Override
    public List<Lot> getActiveLots() throws DAOException {
        return null;
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
                int id = lot.getLotId();
                int bid = lot.getBid();
                int bidQuantity = lot.getBidQuantity();
                int sellerId = lot.getSellerId();
                int buyerId = lot.getBuyerId();
                int condition = lot.getCondition();
                int status = lot.getStatus();
                String name = lot.getLotName();
                String image = lot.getImage();
                String finishTime = lot.getFinishTime();


                String selectQuery = "INSERT INTO users " +
                        "(item_id, item_name, item_condition, status," +
                        " bid, bid_quantity, main_image_name, seller_id, " +
                        "buyer_id, start_time, finish_time) " +
                        "VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, NULL, NULL, ?);";
                ps = connection.prepareStatement(selectQuery);
                ps.setInt(1, id);
                if (ps.executeUpdate() == 0){
                    throw new DAOException("Cannot add new user to database");
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

package app.dao;

import app.dao.exception.DAOException;
import app.data.DB;
import app.data.DBUtil;
import app.models.Lot;
import app.service.exception.ServiceException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class LotDAOImpl implements LotDAO {
    private String convertToBase64(InputStream inputStream) throws ServiceException {
        String base64Image;
        try{
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[5242880];
            int bytesRead = -1;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            byte[] imageBytes = outputStream.toByteArray();
            base64Image = Base64.getEncoder().encodeToString(imageBytes);

            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            throw new ServiceException(e);
        }
        return base64Image;
    }

    private Lot assembleLotObject(ResultSet rs)
            throws SQLException, ServiceException {
        Lot lot = new Lot();
        lot.setLotId(rs.getInt("item_id"));
        lot.setLotName(rs.getString("item_name"));
        lot.setCondition(rs.getInt("item_condition"));
        lot.setStatus(rs.getString("status"));
        lot.setBid(rs.getInt("bid"));
        lot.setBidQuantity(rs.getInt("bid_quantity"));
        lot.setImageName(rs.getString("main_image_name"));
        lot.setImageInputStream(rs.getBinaryStream("image_content"));

        lot.setImageContent(convertToBase64(lot.getImageInputStream()));

        lot.setSellerId(rs.getInt("seller_id"));
        lot.setBuyerId(rs.getInt("buyer_id"));
        lot.setStartTime(rs.getTimestamp("start_time").toString());
        lot.setFinishTime(rs.getTimestamp("finish_time").toString());
        return lot;
    }

    @Override
    public List<Lot> getUserLots(int userId) throws DAOException {
        PreparedStatement ps = null;
        try {
            List<Lot> lotList = new ArrayList<>();
            try(Connection connection = DB.getConnection()){
                String selectQuery = "SELECT * FROM items WHERE seller_id = ?";
                ps = connection.prepareStatement(selectQuery);
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    lotList.add(assembleLotObject(rs));
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
    public List<Lot> getActiveLots() throws DAOException {
        PreparedStatement ps = null;
        try {
            List<Lot> lotList = new ArrayList<>();
            try(Connection connection = DB.getConnection()){
                String selectQuery = "SELECT * FROM items WHERE status = 'ACTIVE'";
                ps = connection.prepareStatement(selectQuery);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    lotList.add(assembleLotObject(rs));
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
        PreparedStatement ps = null;
        try {
            List<Lot> lotList = new ArrayList<>();
            try(Connection connection = DB.getConnection()){
                String selectQuery = "SELECT * FROM items " +
                        "WHERE status = 'ACTIVE' AND seller_id = ?";
                ps = connection.prepareStatement(selectQuery);
                ps.setInt(1, sellerId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    lotList.add(assembleLotObject(rs));
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
    public List<Lot> getExpiredLots(int sellerId) throws DAOException {
        return null;
    }

    @Override
    public List<Lot> getSoldLots(int sellerId) throws DAOException {
        return null;
    }

    @Override
    public void create(Lot lot) throws DAOException {
        PreparedStatement ps = null;
        try {
            try(Connection connection = DB.getConnection()){
                String selectQuery = "INSERT INTO items " +
                        "(item_name, item_condition," +
                        " bid, main_image_name, image_content, seller_id, " +
                        "buyer_id, finish_time) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
                ps = connection.prepareStatement(selectQuery);
                ps.setString(1, lot.getLotName());
                ps.setInt(2, lot.getCondition());
                ps.setInt(3, lot.getBid());
                ps.setString(4, lot.getImageName());
                ps.setBinaryStream(5, lot.getImageInputStream());
                ps.setInt(6, lot.getSellerId());
                ps.setInt(7, lot.getBuyerId());
                ps.setTimestamp(8, Timestamp.valueOf(lot.getFinishTime()));
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
        PreparedStatement ps = null;
        Lot lot = null;
        try {
            try(Connection connection = DB.getConnection()){
                String selectQuery = "SELECT * FROM items WHERE item_id = ?";
                ps = connection.prepareStatement(selectQuery);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    lot = assembleLotObject(rs);
                } else{
                    throw new DAOException(String.format(
                            "There is no lot with id = %d", id));
                }
            }
            return lot;
        } catch (Exception e) {
            throw new DAOException(e);
        } finally{
            DBUtil.closeStatement(ps);
        }
    }

    @Override
    public void update(Lot lot) throws DAOException {
        PreparedStatement ps = null;
        try {
            try(Connection connection = DB.getConnection()){
                String updateQuery = "UPDATE items SET " +
                        "item_name = ?, item_condition = ?, status = ?, " +
                        "bid = ?, bid_quantity = ?, main_image_name = ?, " +
                        "image_content = ?, seller_id = ?, buyer_id = ?, " +
                        "start_time = ?, finish_time = ? WHERE item_id = ?;";
                ps = connection.prepareStatement(updateQuery);
                ps.setString(1, lot.getLotName());
                ps.setInt(2, lot.getCondition());
                ps.setString(3, lot.getStatus());
                ps.setInt(4, lot.getBid());
                ps.setInt(5, lot.getBidQuantity());
                ps.setString(6, lot.getImageName());
                ps.setBinaryStream(7, lot.getImageInputStream());
                ps.setInt(8, lot.getSellerId());
                ps.setInt(9, lot.getBuyerId());
                ps.setTimestamp(10, Timestamp.valueOf(lot.getStartTime()));
                ps.setTimestamp(11, Timestamp.valueOf(lot.getFinishTime()));
                ps.setInt(12, lot.getLotId());
                if (ps.executeUpdate() == 0){
                    throw new DAOException("Cannot update user in database"); // ВЫДАЕТ ИСКЛЮЧЕНИЕ
                }
            }
        } catch (Exception e) {
            throw new DAOException(e);
        } finally{
            DBUtil.closeStatement(ps);
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        PreparedStatement ps = null;
        try {
            try(Connection connection = DB.getConnection()){
                String deleteQuery = "DELETE FROM items WHERE item_id = ?";
                ps = connection.prepareStatement(deleteQuery);
                ps.setInt(1, id);
                if (ps.executeUpdate() == 0){
                    throw new DAOException("Cannot delete lot in database");
                }
            }
        } catch (Exception e) {
            throw new DAOException(e);
        } finally{
            DBUtil.closeStatement(ps);
        }
    }
}

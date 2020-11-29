package app.dao;

import app.dao.exception.DAOException;
import app.models.Lot;

import java.util.List;

public interface LotDAO extends DAO<Lot> {
    List<Lot> getActiveLots() throws DAOException;
    List<Lot> getActiveLots(int sellerId) throws DAOException;
    List<Lot> getExpiredLots(int sellerId) throws DAOException;
    List<Lot> getSoldLots(int sellerId) throws DAOException;
}

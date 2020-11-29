package app.service;


import app.dao.DAOFactory;
import app.dao.LotDAO;
import app.dao.exception.DAOException;
import app.models.Lot;;
import app.service.exception.ServiceException;

import java.util.List;

public class LotServiceImpl implements LotService {
    private static LotDAO lotDAO;

    public LotServiceImpl(){
        DAOFactory daoObjectFactory = DAOFactory.getInstance();
        lotDAO = daoObjectFactory.getLotDAO();
    }

    @Override
    public List<Lot> getAllActiveLots() throws ServiceException {
        try {
            return lotDAO.getActiveLots();
        } catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void addLot(Lot lot) throws ServiceException {
        try {
            if (lot != null){
                lotDAO.create(lot);
            }
        } catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteLot(Lot lot) throws ServiceException {
        try {
            if (lot != null){
                lotDAO.delete(lot);
            }
        } catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void editLot(Lot lot) throws ServiceException {
        try {
            if (lot != null){
                lotDAO.update(lot);
            }
        } catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public Lot getLotById(int id) throws ServiceException {
        try {
            return lotDAO.retrieve(id);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
    }
}

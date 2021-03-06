package app.service;

import app.models.Lot;
import app.service.exception.ServiceException;

import java.io.InputStream;
import java.util.List;

public interface LotService {
    List<Lot> getAllActiveLots() throws ServiceException;
    List<Lot> getUserLots(int userId) throws ServiceException;
    void addLot(Lot lot) throws ServiceException;
    void deleteLot(int id) throws ServiceException;
    void editLot(Lot lot) throws ServiceException;
    Lot getLotById(int id) throws ServiceException;
    //String convertToBase64(InputStream fileContent) throws ServiceException;
}

package app.service;

import app.dao.DAOFactory;
import app.dao.LotDAO;
import app.dao.exception.DAOException;
import app.models.Lot;;
import app.service.exception.ServiceException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
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
            List<Lot> lotList = lotDAO.getActiveLots();
            for (Lot lot: lotList){
                lot.setImageContent(convertToBase64(lot.getImageInputStream()));
            }
            return lotList;
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

    @Override
    public String convertToBase64(InputStream inputStream) throws ServiceException {
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
}

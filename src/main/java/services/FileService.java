package services;

import dao.FileDAO;
import dao.FileDAOImpl;
import models.File;

import java.util.List;

public class FileService {

    private FileDAO filesDao = new FileDAOImpl();

    public void saveFile(File file) {
        filesDao.save(file);
    }

    public void deleteFile(File file) {
        filesDao.delete(file);
    }

    public List<File> findAllFilesInMessage(int no) {
        return filesDao.findAllFilesInMessage(no);
    }
}

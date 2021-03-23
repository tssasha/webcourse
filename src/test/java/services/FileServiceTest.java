package services;

import models.File;
import models.Message;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FileServiceTest {
    @Test
    public void testSaveFile() {
        FileService fileService = new FileService();
        File new_file = new File("path", 7);
        fileService.saveFile(new_file);

        List<File> check_file = fileService.findAllFilesInMessage(7);
        Assert.assertEquals(new_file.getFilePath(), check_file.get(check_file.size() - 1).getFilePath());

        fileService.deleteFile(new_file);
    }

    @Test
    public void testDeleteFile() {
        FileService fileService = new FileService();
        File new_file = new File("path", 7);
        fileService.saveFile(new_file);

        List<File> check_file1 = fileService.findAllFilesInMessage(7);
        fileService.deleteFile(new_file);

        List<File> check_file2 = fileService.findAllFilesInMessage(7);
        Assert.assertEquals(check_file1.size() - 1, check_file2.size());
    }

    @Test
    public void testFindAllFilesInMessage() {

    }
}

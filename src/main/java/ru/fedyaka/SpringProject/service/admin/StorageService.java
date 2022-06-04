package ru.fedyaka.SpringProject.service.admin;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDateTime;

@Service
public class StorageService {

    //Сохраняет в localPath файл, генерируя ему уникальное имя.
    //Возвращает конечную директории, куда была выгружен файл с названием файла ("example/nameFile.type").
    public String store(MultipartFile file, String localPath){
        if (file.isEmpty()){
            throw new NullPointerException();
        }

        //Генерируем имя файла (example.type)
        String generateName = generateName(file);

        //путь localPath с названием файла
        String pathFile = localPath + "/" + generateName;

        //Выгрузка файла в localPath
        try(BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(pathFile))){

            // Определяем, существует ли файл родительского каталога
            File dest = new File(pathFile);
            if(!dest.getParentFile().exists()){
                dest.getParentFile().mkdir();
            }

            //Выгрузка файла
            outputStream.write(file.getBytes());

        } catch (FileNotFoundException e) {
            return "";
        } catch (IOException e) {
            return "";
        }

        //парсинг и вытаскивание последней директории
        String[] splitDir = localPath.split("/");
        String dir = "";
        if (splitDir.length != 0) {
            dir = splitDir[splitDir.length - 1];
        }

        //Возвращение конечной директории, куда была выгружен файл с названием файла ("example/nameFile.type")
        return "/" + dir + "/" + generateName;
    }

    private String generateName(MultipartFile file) {
        String generateKey = DigestUtils.md5DigestAsHex(
                (file.getOriginalFilename() + LocalDateTime.now().toString())
                        .getBytes());
        String fileType = "." + file.getContentType().replace("image/", "");
        return generateKey + fileType;
    }

    public boolean delete(String pathFile, String localPath){
        File file = new File(pathFile);
        String fileName = file.getName();
        File localPathFile = new File(localPath + "/" + fileName);
        if (localPathFile.exists()){
            return localPathFile.delete();
        }
        return false;
    }

    public String update(String oldPathFile, MultipartFile newFile, String localPath){
        delete(oldPathFile, localPath);
        return store(newFile, localPath);
    }
}

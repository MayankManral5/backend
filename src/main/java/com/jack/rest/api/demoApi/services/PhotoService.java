package com.jack.rest.api.demoApi.services;

import com.jack.rest.api.demoApi.documents.Photo;
import com.jack.rest.api.demoApi.repositories.PhotoRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PhotoService {
    @Autowired
    private PhotoRepository photoRepo;

    public int addPhoto(int id, String title, MultipartFile file) throws IOException {
        Binary img = new Binary(BsonBinarySubType.BINARY, file.getBytes());
        Photo photo = new Photo(id, title, img);

//        int value = photo.getId();
//        System.out.println("###############"+value);
         photoRepo.insert(photo);
        return photo.getId();
    }

    public Photo getPhoto(int id) {
        return photoRepo.findById(id).get();
    }
}

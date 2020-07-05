package com.jack.rest.api.demoApi.resource;


import com.jack.rest.api.demoApi.documents.Photo;
import com.jack.rest.api.demoApi.services.PhotoService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RestController
public class PhotoResource {

    private PhotoService photoService;

    @PostMapping("/photos/add")
    public String addPhoto(@RequestParam("id") int id,
                           @RequestParam("title") String title,
                           @RequestParam("image") MultipartFile image, Model model)
            throws IOException {
        int value = photoService.addPhoto(id, title, image);
        return "done";
    }

    @GetMapping("/photos/{id}")
    public int getPhoto(@PathVariable int id, Model model) {
        Photo photo = photoService.getPhoto(id);
        model.addAttribute("title", photo.getTitle());
        model.addAttribute("image",
                Base64.getEncoder().encodeToString(photo.getImage().getData()));
        return 1;
    }
}

package com.cpe.springboot.card.service;

import com.cpe.springboot.card.model.ImageData;
import com.cpe.springboot.card.repository.ImageDataRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cpe.springboot.card.util.ImageUtil;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageDataService {
    @Autowired
    private ImageDataRepository imageDataRepository;

    public String uploadImage(MultipartFile file) throws IOException {
        imageDataRepository.save(ImageData.builder()
                .name(file.getOriginalFilename()).type(file.getContentType())
                .imageData(ImageUtil.compressImage(file.getBytes())).build());
        return "Image uploaded successfully" + file.getOriginalFilename();
    }

    @Transactional
    public ImageData getInfoByImageByName(String name) {
        Optional<ImageData> dbImage = imageDataRepository.findByName(name);

        return ImageData.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .imageData(ImageUtil.decompressImage(dbImage.get().getImageData())).build();

    }

    @Transactional
    public byte[] getImage(String name) {
        Optional<ImageData> dbImage = imageDataRepository.findByName(name);
        byte[] image = ImageUtil.decompressImage(dbImage.get().getImageData());
        return image;
    }
}

package com.petcam.admin.controller.upload;

import com.petcam.admin.dto.upload.UploadResultDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/file")
@Log4j2
public class UploadController {

    @Value("C:\\Users\\AIA")
    private String path;

    @ResponseBody
    @GetMapping(value = "/down")
    public ResponseEntity<byte[]> down(String file){

        log.info("--------------------down: " + file);

        File target = new File(path, file);

        String mimeType = null;
        try {
            mimeType = Files.probeContentType(target.toPath());

            if(mimeType.startsWith("image") == false){
                mimeType ="application/octet-stream";
            }

            byte[] arr = FileCopyUtils.copyToByteArray(target);

            return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).body(arr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    @ResponseBody
    @PostMapping(value ="/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UploadResultDTO> upload(MultipartFile[] files){

        log.warn(path);
        log.info("========컨트롤러들어옴");
        List<UploadResultDTO> result = new ArrayList<>();

        for (MultipartFile file:files) {
            log.info("========포문들어옴");
            log.warn(file);

            String fileName = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();

            File outFile = new File(path, uuid+"_"+fileName );
            File thumbFile = new File(path, "s_"+uuid+"_"+fileName );

            try {
                log.info("========트라이들어옴");
                InputStream fin = file.getInputStream(); //< -- 변경
                Files.copy(fin, outFile.toPath()); // <-- 변경

                BufferedImage originalImage = ImageIO.read(outFile);

                BufferedImage thumbnail = Thumbnails.of(originalImage)
                        .size(100, 100)
                        .asBufferedImage();

                ImageIO.write(thumbnail, "JPG", thumbFile);

                fin.close(); // <-- 추가


            } catch (IOException e) {
                e.printStackTrace();
            }
            log.info("========트라이캐치 마지막 빌더쪽 들어옴");
            result.add(UploadResultDTO.builder().uuid(uuid).fileName(fileName).build());
        }//end for

        log.info(result + "==========결과======");
        return result;
    }
}

package com.petcam.admin.controller.upload;

import com.petcam.admin.dto.upload.AdminDTO;
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

        List<UploadResultDTO> result = new ArrayList<>(); // 새로운 값 들어오면 점점 늘어날 수 있게

        for (MultipartFile file:files) {    // files에 있는 것 하나씩 꺼내담기
            log.info("========포문들어옴");
            log.warn(file);

            String fileName = file.getOriginalFilename();   // 파일명 추출
            String uuid = UUID.randomUUID().toString();     // 랜덤값으로 매번 바뀌게

            File outFile = new File(path, uuid+"_"+fileName );
            File thumbFile = new File(path, "s_"+uuid+"_"+fileName );   //썸네일 파일경로

            try {
                log.info("========트라이들어옴");
                InputStream fin = file.getInputStream(); //< -- 변경
                Files.copy(fin, outFile.toPath()); // 새 파일에 기존 파일 복사

                BufferedImage originalImage = ImageIO.read(outFile);

                BufferedImage thumbnail = Thumbnails.of(originalImage)
                        .size(120, 100)     // 사이즈 지정
                        .asBufferedImage();

                ImageIO.write(thumbnail, "JPG", thumbFile); // 이미지 파일 생성

                fin.close(); // <-- 추가


            } catch (IOException e) {
                e.printStackTrace();
            }
            log.info("========트라이캐치 마지막 빌더쪽 들어옴");
            result.add(UploadResultDTO.builder().uuid(uuid).filename(fileName).build());
        }

        log.info(result + "==========결과======");
        return result;
    }





    @PostMapping("/")
    public ResponseEntity<Long> BoardRegister(@RequestBody AdminDTO dto) {
        log.info(dto);






        return null;
    }
}

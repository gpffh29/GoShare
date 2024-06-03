package com.GoShare.service;


import com.GoShare.entity.BoardImage;
import com.GoShare.repository.ImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardImageService {

    @Value("${imgLocation}")
    private String imgLocation;

    private final ImgRepository imgRepository;

    private final FileService fileService;

    public void saveImage(BoardImage boardImage, MultipartFile imgFile) throws Exception{
        String oriImgName = imgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        //파일 업로드
        if(!StringUtils.isEmpty(oriImgName)){
            imgName = fileService.uploadFile(imgLocation, oriImgName, imgFile.getBytes());
            imgUrl = "/images/cars/" + imgName;
        }

        //이미지 정보 저장
        boardImage.updateImg(oriImgName, imgName, imgUrl);
        imgRepository.save(boardImage);
    }
}

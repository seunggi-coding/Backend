package com.farm.smartfarm.service;

import com.farm.smartfarm.domain.CropList;
import com.farm.smartfarm.repository.CropListRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CropListService {
    private final CropListRepository cropListRepository;

    public CropListService(CropListRepository cropListRepository) {
        this.cropListRepository = cropListRepository;
    }

    public CropList saveCrop(CropList cropList, String writer) {
        ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        LocalDateTime localDateTimeInKorea = nowInKorea.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = localDateTimeInKorea.format(formatter);
        cropList.setWrite_date(formattedDate);
        cropList.setWriter(writer);

        return cropListRepository.save(cropList);
    }

    public List<CropList> findAll() {
        return cropListRepository.findAll();
    }

    public void deleteCropList(Long id) {
        cropListRepository.deleteById(id);
    }
}

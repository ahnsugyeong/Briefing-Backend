package com.example.briefingapi.briefing.presentation.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.example.briefingcommon.entity.enums.BriefingType;
import com.example.briefingcommon.entity.enums.GptModel;
import com.example.briefingcommon.entity.enums.TimeOfDay;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BriefingResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ArticleResponseDTO {
        Long id;
        String press;
        String title;
        String url;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BriefingPreviewDTOV2 {
        Long id;
        Integer ranks;
        String title;
        String subtitle;
        @Builder.Default Integer scrapCount = 0;
        @Builder.Default Integer viewCount = 0;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BriefingPreviewDTO {
        Long id;
        Integer ranks;
        String title;
        String subtitle;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BriefingDetailDTO {
        Long id;
        Integer ranks;
        String title;
        String subtitle;
        String content;
        LocalDate date;
        List<ArticleResponseDTO> articles;
        Boolean isScrap;
        Boolean isBriefingOpen;
        Boolean isWarning;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BriefingDetailDTOV2 {
        Long id;
        Integer ranks;
        String title;
        String subtitle;
        String content;
        LocalDate date;
        List<ArticleResponseDTO> articles;
        Boolean isScrap;
        Boolean isBriefingOpen;
        Boolean isWarning;
        @Builder.Default Integer scrapCount = 0;
        @Builder.Default
        GptModel gptModel = GptModel.GPT_3_5_TURBO;
        @Builder.Default
        TimeOfDay timeOfDay = TimeOfDay.MORNING;
        @Builder.Default
        BriefingType type = BriefingType.KOREA;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BriefingPreviewListDTOV2 {
        LocalDateTime createdAt;
        List<BriefingPreviewDTOV2> briefings;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BriefingPreviewListDTO {
        LocalDateTime createdAt;
        List<BriefingPreviewDTO> briefings;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BriefingV2PreviewListDTO {
        LocalDateTime createdAt;
        String type;
        List<BriefingPreviewV2TempDTO> briefings;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BriefingPreviewV2TempDTO {
        Long id;
        Integer ranks;
        String title;
        String subtitle;
        Integer scrapCount;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BriefingUpdateDTO {
        String title;
        String subTitle;
        String content;
    }
}

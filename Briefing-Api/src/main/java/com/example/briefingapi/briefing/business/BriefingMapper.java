package com.example.briefingapi.briefing.business;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.example.briefingapi.briefing.presentation.dto.BriefingRequestDTO;
import com.example.briefingapi.briefing.presentation.dto.BriefingResponseDTO;
import com.example.briefingcommon.entity.Article;
import com.example.briefingcommon.entity.Briefing;
import com.example.briefingcommon.entity.enums.BriefingType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BriefingMapper {

    public static BriefingResponseDTO.BriefingPreviewDTOV2 toBriefingPreviewDTOV2(
            Briefing briefing) {
        return BriefingResponseDTO.BriefingPreviewDTOV2.builder()
                .id(briefing.getId())
                .ranks(briefing.getRanks())
                .title(briefing.getTitle())
                .subtitle(briefing.getSubtitle())
                .scrapCount(briefing.getScrapCount())
                .viewCount(briefing.getViewCount())
                .build();
    }

    public static BriefingResponseDTO.BriefingPreviewDTO toBriefingPreviewDTO(Briefing briefing) {
        return BriefingResponseDTO.BriefingPreviewDTO.builder()
                .id(briefing.getId())
                .ranks(briefing.getRanks())
                .title(briefing.getTitle())
                .subtitle(briefing.getSubtitle())
                .build();
    }

    private static LocalDateTime getPreviewListDTOCreatedAt(
            final LocalDate date, List<Briefing> briefingList) {
        if (!briefingList.isEmpty()) {
            return briefingList.get(0).getCreatedAt();
        }
        if (date != null) {
            return date.atTime(3, 0);
        }
        return LocalDateTime.now();
    }

    public static BriefingResponseDTO.BriefingPreviewListDTOV2 toBriefingPreviewListDTOV2(
            final LocalDate date, List<Briefing> briefingList) {
        final List<BriefingResponseDTO.BriefingPreviewDTOV2> briefingPreviewDTOList =
                briefingList.stream().map(BriefingMapper::toBriefingPreviewDTOV2).toList();

        return BriefingResponseDTO.BriefingPreviewListDTOV2.builder()
                .createdAt(getPreviewListDTOCreatedAt(date, briefingList))
                .briefings(briefingPreviewDTOList)
                .build();
    }

    public static BriefingResponseDTO.BriefingPreviewListDTO toBriefingPreviewListDTO(
            final LocalDate date, List<Briefing> briefingList) {
        final List<BriefingResponseDTO.BriefingPreviewDTO> briefingPreviewDTOList =
                briefingList.stream().map(BriefingMapper::toBriefingPreviewDTO).toList();

        return BriefingResponseDTO.BriefingPreviewListDTO.builder()
                .createdAt(getPreviewListDTOCreatedAt(date, briefingList))
                .briefings(briefingPreviewDTOList)
                .build();
    }

    public static BriefingResponseDTO.ArticleResponseDTO toArticleResponseDTO(
            final Article article) {
        return BriefingResponseDTO.ArticleResponseDTO.builder()
                .id(article.getId())
                .press(article.getPress())
                .title(article.getTitle())
                .url(article.getUrl())
                .build();
    }

    public static BriefingResponseDTO.BriefingDetailDTO toBriefingDetailDTO(
            Briefing briefing, Boolean isScrap, Boolean isBriefingOpen, Boolean isWarning) {

        List<BriefingResponseDTO.ArticleResponseDTO> articleResponseDTOList =
                briefing.getArticles().stream()
                        .map(BriefingMapper::toArticleResponseDTO)
                        .toList();

        return BriefingResponseDTO.BriefingDetailDTO.builder()
                .id(briefing.getId())
                .ranks(briefing.getRanks())
                .title(briefing.getTitle())
                .subtitle(briefing.getSubtitle())
                .content(briefing.getContent())
                .date(briefing.getCreatedAt().toLocalDate())
                .articles(articleResponseDTOList)
                .isScrap(isScrap)
                .isBriefingOpen(isBriefingOpen)
                .isWarning(isWarning)
                .build();
    }

    public static BriefingResponseDTO.BriefingDetailDTOV2 toBriefingDetailDTOV2(
            Briefing briefing, Boolean isScrap, Boolean isBriefingOpen, Boolean isWarning) {

        List<BriefingResponseDTO.ArticleResponseDTO> articleResponseDTOList =
                briefing.getArticles().stream()
                        .map(BriefingMapper::toArticleResponseDTO)
                        .toList();

        return BriefingResponseDTO.BriefingDetailDTOV2.builder()
                .id(briefing.getId())
                .ranks(briefing.getRanks())
                .title(briefing.getTitle())
                .subtitle(briefing.getSubtitle())
                .content(briefing.getContent())
                .date(briefing.getCreatedAt().toLocalDate())
                .articles(articleResponseDTOList)
                .isScrap(isScrap)
                .isBriefingOpen(isBriefingOpen)
                .isWarning(isWarning)
                .scrapCount(briefing.getScrapCount())
                .gptModel(briefing.getGptModel())
                .timeOfDay(briefing.getTimeOfDay())
                .type(briefing.getType())
                .build();
    }

    public static Briefing toBriefing(BriefingRequestDTO.BriefingCreate request) {
        return Briefing.builder()
                .type(request.getBriefingType())
                .ranks(request.getRanks())
                .title(request.getTitle())
                .subtitle(request.getSubtitle())
                .content(request.getContent())
                .gptModel(request.getGptModel())
                .timeOfDay(request.getTimeOfDay())
                .build();
    }

    public static Article toArticle(BriefingRequestDTO.ArticleCreateDTO request) {
        return Article.builder()
                .press(request.getPress())
                .title(request.getTitle())
                .url(request.getUrl())
                .build();
    }

    public static BriefingResponseDTO.BriefingPreviewV2TempDTO toBriefingPreviewV2TempDTO(Long id) {
        Integer rank = null;
        String title = null;
        String subTitle = null;
        Integer scrapCount = null;

        if (id.equals(346L)) {
            rank = 1;
            title = "소셜 1";
            subTitle = "브리핑 부제목 1";
            scrapCount = 1234;
        } else if (id.equals(347L)) {
            rank = 2;
            title = "소셜 2";
            subTitle = "브리핑 부제목 2";
            scrapCount = 123;
        } else if (id.equals(348L)) {
            rank = 3;
            title = "소셜 3";
            subTitle = "브리핑 부제목 3";
            scrapCount = 13;
        } else if (id.equals(349L)) {
            rank = 4;
            title = "소셜 4";
            subTitle = "브리핑 부제목 4";
            scrapCount = 12323;
        } else if (id.equals(350L)) {
            rank = 5;
            title = "소셜 5";
            subTitle = "브리핑 부제목 5";
            scrapCount = 123;
        }

        return BriefingResponseDTO.BriefingPreviewV2TempDTO.builder()
                .id(id)
                .ranks(rank)
                .title(title)
                .subtitle(subTitle)
                .scrapCount(scrapCount)
                .build();
    }

    public static BriefingResponseDTO.BriefingV2PreviewListDTO toBriefingPreviewV2TempListDTO(
            final LocalDate date, List<Long> temp, BriefingType briefingType) {
        List<BriefingResponseDTO.BriefingPreviewV2TempDTO> tempDTOList =
                temp.stream().map(BriefingMapper::toBriefingPreviewV2TempDTO).toList();

        return BriefingResponseDTO.BriefingV2PreviewListDTO.builder()
                .createdAt(date.atTime(3, 0))
                .type(briefingType.getValue())
                .briefings(tempDTOList)
                .build();
    }

    public static BriefingResponseDTO.BriefingUpdateDTO toBriefingUpdateDTO(Briefing briefing) {
        return BriefingResponseDTO.BriefingUpdateDTO.builder()
                .title(briefing.getTitle())
                .subTitle(briefing.getSubtitle())
                .content(briefing.getContent())
                .build();
    }
}

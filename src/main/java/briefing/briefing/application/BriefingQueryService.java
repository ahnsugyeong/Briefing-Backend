package briefing.briefing.application;

import briefing.briefing.application.dto.BriefingDetailResponse;
import briefing.briefing.application.dto.BriefingsResponse;
import briefing.briefing.domain.Briefing;
import briefing.briefing.domain.BriefingType;
import briefing.briefing.domain.repository.BriefingRepository;
import briefing.briefing.exception.BriefingException;
import briefing.briefing.exception.BriefingExceptionType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BriefingQueryService {

  private final BriefingRepository briefingRepository;

  public BriefingsResponse findBriefings(final BriefingType type, final LocalDate date) {
    final LocalDateTime startDateTime = date.atStartOfDay();
    final LocalDateTime endDateTime = date.atTime(LocalTime.MAX);

    final List<Briefing> briefings = briefingRepository.findAllByTypeAndCreatedAtBetweenOrderByRanks(
        type, startDateTime, endDateTime);

    return BriefingsResponse.from(date, briefings);
  }

  public BriefingDetailResponse findBriefing(final Long id) {
    final Briefing briefing = briefingRepository.findById(id)
        .orElseThrow(() -> new BriefingException(BriefingExceptionType.NOT_FOUND_BRIEFING));

    return BriefingDetailResponse.from(briefing);
  }
}

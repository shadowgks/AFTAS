package com.example.appgcm.services.impls;

import com.example.appgcm.models.entity.Competition;
import com.example.appgcm.repositories.CompetitionRepository;
import com.example.appgcm.dtos.CompetitionDto.CompetitionDto;
import com.example.appgcm.dtos.RegisterMemberOnCompetitionDto;
import com.example.appgcm.models.entity.AppUser;
import com.example.appgcm.models.entity.Ranking;
import com.example.appgcm.models.entity.embedded.MemberCompetition;
import com.example.appgcm.repositories.RankingRepository;
import com.example.appgcm.repositories.UserRepository;
import com.example.appgcm.services.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CompetitionServiceImpl implements CompetitionService {
    private final UserRepository userRepository;
    private final CompetitionRepository competitionRepository;
    private final RankingRepository rankingRepository;

    @Override
    public Page<Competition> findAllCompetitionPageble(String location, Integer numPage, Integer size) {
       Page<Competition> competitionList = competitionRepository.findByLocationContaining(location, PageRequest.of(numPage, size));
        if (competitionList.isEmpty()) {
            throw new IllegalArgumentException("Not Found Any Competitions");
        }
        return competitionList;
    }

    @Override
    public List<Competition> findAllCompetition() {
        List<Competition> competitionList = competitionRepository.findAll();
        if(competitionList.isEmpty()){
            throw new IllegalArgumentException("Not Found list Any Competitions");
        }
        return competitionList;
    }

    @Override
    public Competition findByDateCompetition(LocalDate date) {
        return competitionRepository.findByDate(date)
                .orElseThrow(() -> new IllegalArgumentException("Not found Competition By Date " +date));
    }

    @Override
    public Competition findByCodeCompetition(String code) {
        return competitionRepository.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Not found Competition By Code " +code));
    }

    @Override
    public Competition saveCompetition(CompetitionDto reqDto) {
        // Check date competition
        Optional<Competition> optionalCompetition = competitionRepository.findByDate(reqDto.date());
        if(optionalCompetition.isPresent()){
            throw new IllegalArgumentException("Date deja exist");
        }

        // Substring location
        String locationSplit = reqDto.location().substring(0,3).toLowerCase();

        // Format the date
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("-yy-MM-dd");
        String formattedDate = reqDto.date().format(outputFormatter);
        String codeDone = locationSplit.concat(formattedDate);

        // Check Time
        if(reqDto.endTime().isBefore(reqDto.startTime())){
            throw new IllegalArgumentException("You must check start time and end time");
        }

        //Builder Competition
        Competition competition = Competition.builder()
                .amount(reqDto.amount())
                .code(codeDone)
                .location(reqDto.location())
                .endTime(reqDto.endTime())
                .startTime(reqDto.startTime())
                .numberOfParticipants(reqDto.numberOfParticipants())
                .date(reqDto.date())
                .build();
        return competitionRepository.save(competition);
    }

    @Override
    public void deleteCompetition(Long id) {
        Optional<Competition> competition = Optional.ofNullable(competitionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found Competition")));
        competition.ifPresent(get -> competitionRepository.deleteById(id));
    }

    @Override
    public Competition updateCompetition(Long id, CompetitionDto reqDto) {
        Competition competition = competitionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found this Competition!"));

        // Substring location
        String locationSplit = reqDto.location().substring(0,3).toLowerCase();

        // Format the date
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("-yy-MM-dd");
        String formattedDate = reqDto.date().format(outputFormatter);
        String codeDone = locationSplit.concat(formattedDate);

        //Builder Competition
        Competition competition1 = Competition.builder()
                .id(competition.getId())
                .code(codeDone)
                .amount(reqDto.amount())
                .date(reqDto.date())
                .startTime(reqDto.startTime())
                .endTime(reqDto.endTime())
                .numberOfParticipants(reqDto.numberOfParticipants())
                .location(reqDto.location())
                .build();
        return competitionRepository.save(competition1);
    }

    @Override
    public Ranking registerMember(RegisterMemberOnCompetitionDto reqDto) {
        // Check Competition and member if exists
        Competition competition = competitionRepository.findByCode(reqDto.competitionCode())
                .orElseThrow(() -> new IllegalArgumentException("Sorry this competition not exists!"));
        AppUser user = userRepository.findByIdentityNumber(reqDto.memberIdentity())
                .orElseThrow(() -> new IllegalArgumentException("Sorry this member not exists!"));

        // Check number participant
        Integer numP = competition.getNumberOfParticipants();
        Integer countAllByCompetition = rankingRepository.countByCompetition(competition);
        if (countAllByCompetition >= numP){
            throw new IllegalArgumentException("Competition It has arrived maximum number of members: "+ numP);
        }

        if(competition.getDate().isBefore(LocalDate.now())){
            throw new IllegalArgumentException("You can not registered in this competition because date of competition is expired");
        }

        // Check if member already registered on competition
        Optional<Ranking> ranking = rankingRepository.findByUserAndCompetition(user, competition);
        if (ranking.isPresent()){
            throw new IllegalArgumentException("This member is already registered for this competition!");
        }

        // Check date registered before 24h in competition
        if (checkRegisterBefore24(competition)){
            // Now create ranking
            Ranking ranking1 = Ranking.builder()
                    .id(MemberCompetition.builder()
                            .competitionID(user.getId())
                            .userID(competition.getId())
                            .build())
                    .user(user)
                    .competition(competition)
                    .rankk(0)
                    .score(0)
                    .build();
            return rankingRepository.save(ranking1);
        }else{
            throw new IllegalArgumentException("The time has passed to register on this competition");
        }

    }

    public boolean checkRegisterBefore24(Competition competition){
        if(competition.getDate().isEqual(LocalDate.now())){
            LocalTime timeStartCompetition = competition.getStartTime().minusHours(24);
            return LocalTime.now().isBefore(timeStartCompetition);
        }
        return true;
    }
}

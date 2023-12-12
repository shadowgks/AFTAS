package com.example.appgcm.services.impls;

import com.example.appgcm.dtos.CompetitionDto;
import com.example.appgcm.models.entity.Competition;
import com.example.appgcm.models.entity.Member;
import com.example.appgcm.repositories.CompetitionRepository;
import com.example.appgcm.services.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;

    @Override
    public List<Competition> findAllCompetition() {
        List<Competition> competitionList = competitionRepository.findAll();
        if (competitionList.isEmpty()) {
            throw new IllegalArgumentException("Not Found Any Competition");
        }
        return competitionList;
    }

    @Override
    public Competition findByDateCompetition(LocalDate date) {
        Optional<Competition> competition = Optional.ofNullable(competitionRepository.findByDate(date)
                .orElseThrow(() -> new IllegalArgumentException("Not found Competition By Date " +date)));
        return competition.get();
    }

    @Override
    public Competition findByCodeCompetition(String code) {
         Optional<Competition> competition = Optional.ofNullable(competitionRepository.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Not found Competition By Code " +code)));
        return competition.get();
    }

    @Override
    public Competition saveCompetition(CompetitionDto reqDto) {
        // Substring location
        String locationSplit = reqDto.location().substring(0,3).toLowerCase();

        // Format the date
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("-yy-MM-dd");
        String formattedDate = reqDto.date().format(outputFormatter);
        String codeDone = locationSplit.concat(formattedDate);

        // Check Time
        if(reqDto.endTime().isBefore(reqDto.startTime())){
            throw new IllegalArgumentException("Check start time and end time");
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
        Optional<Competition> competition = Optional.of(competitionRepository.findById(id))
                .orElseThrow(() -> new IllegalArgumentException("Not found Competition"));

        // Substring location
        String locationSplit = reqDto.location().substring(0,3).toLowerCase();

        // Format the date
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("-yy-MM-dd");
        String formattedDate = reqDto.date().format(outputFormatter);
        String codeDone = locationSplit.concat(formattedDate);

        //Builder Competition
        Competition competition1 = Competition.builder()
                .id(competition.get().getId())
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
}

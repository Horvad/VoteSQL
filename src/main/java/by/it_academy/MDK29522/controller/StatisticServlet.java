package by.it_academy.MDK29522.controller;

import by.it_academy.MDK29522.core.dto.StatisticDTOArtists;
import by.it_academy.MDK29522.core.dto.StatisticDTOGenres;
import by.it_academy.MDK29522.core.dto.VoteDTOIdTime;
import by.it_academy.MDK29522.service.api.IStatisticService;
import by.it_academy.MDK29522.service.fabrics.StatisticServiceSingleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "StatisticServlet", urlPatterns = "/statistic")
public class StatisticServlet extends HttpServlet {
    private final IStatisticService statisticService;
    public StatisticServlet() {
        this.statisticService = StatisticServiceSingleton.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        List<StatisticDTOArtists> artists = statisticService.getArtists();
        writer.write("<p>Артисты:</p>");
        for (StatisticDTOArtists artist : artists){
            writer.write("<p>id: "+artist.getIdArtists()+
                    ", Название: "+artist.getName()+
                    ", Колличество голосов: "+artist.getCount()+
                    "</p>");
        }

        List<StatisticDTOGenres> genres = statisticService.getGenres();
        writer.write("<p>Жанры:</p>");
        for (StatisticDTOGenres genre : genres){
            writer.write("<p>id: "+genre.getIdGenre()+
                    ", Название: "+genre.getName()+
                    ", Колличество голосов: "+genre.getCount()+
                    "</p>");
        }

        List<VoteDTOIdTime> votes = statisticService.getVotes();
        writer.write("<p></p>");
        for (VoteDTOIdTime vote : votes){
            writer.write("<p>id: "+vote.getId()+
                    ", О себе: "+vote.getVoteDTO().getAbout()+
                    ", дата голосования: "+vote.getTimeCreate()+
                    "</p>");
        }
    }
}

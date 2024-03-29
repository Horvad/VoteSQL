package by.it_academy.MDK29522.controller;

import by.it_academy.MDK29522.core.dto.VoteDTO;
import by.it_academy.MDK29522.service.api.IVoteService;
import by.it_academy.MDK29522.service.fabrics.VoteServiceSingleton;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "VoteServlet", urlPatterns = "/vote")
public class VoteServlet extends HttpServlet {
    private final String PARAM_GENRE_NAME = "genre";
    private final String PARAM_ARTIST_NAME = "artist";
    private final String PARAM_ABOUT_NAME = "about";
    private final String PARAM_EMAIL_NAME = "email";

    private final IVoteService service;

    public VoteServlet() {
        this.service = VoteServiceSingleton.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html, charset=UTF-8");

        Map<String,String[]> paramMap = req.getParameterMap();

        String[] artists = paramMap.get(PARAM_ARTIST_NAME);
        if(artists.length==0)
            throw new IllegalArgumentException("Не переданы артисты за которых голосуют");
        if(artists==null||artists[0]==null)
            throw new IllegalArgumentException("Не переданы артисты за которых голосуют");
        if(artists.length>1)
            throw new IllegalArgumentException("Голосовать можно только за 1 артиста");
        if(!NumberUtils.isParsable(artists[0]))
            throw new IllegalArgumentException("Необходимо вводти id артиста");
        int artistID = Integer.parseInt(artists[0]);

        String[] genres = paramMap.get(PARAM_GENRE_NAME);
        if(genres==null)
            throw new IllegalArgumentException("Не переданы жанры");
        if(genres.length==0)
            throw new IllegalArgumentException("Не переданы жанры");
        if(genres.length<3||genres.length>5){
            throw new IllegalArgumentException("Жанров должно быть от 3 до 5");
        }
        Set<Long> genresId = new HashSet<>();
        for(String genre : genres){
            if(!NumberUtils.isParsable(genre)){
                throw new IllegalArgumentException("Необходимо вводить id жанров");
            }
            genresId.add(Long.parseLong(genre));
        }

        String[] about = paramMap.get(PARAM_ABOUT_NAME);
        if(about==null||about.length==0)
            throw new IllegalArgumentException("Не введено о себе");
        if(about[0]==null)
            throw new IllegalArgumentException("Не введено о себе");
        if(about[0].isBlank()){
            throw new IllegalArgumentException("Поле о себе не может быть пустым");
        }
        String[] email = paramMap.get(PARAM_EMAIL_NAME);
        if(email==null){
            throw new IllegalArgumentException("Не введен email");
        }
        if(email.length==0)
            throw new IllegalArgumentException("Не введен email");
        if(email[0]==null)
            throw new IllegalArgumentException("Не введен email");

        VoteDTO vote = new VoteDTO();
        vote.setArtisVote(artistID);
        vote.setGenreDTOSet(genresId);
        vote.setAbout(about[0]);
        vote.setEmail(email[0]);
        service.saveVote(vote);
        resp.sendRedirect("/statistic");
    }
}

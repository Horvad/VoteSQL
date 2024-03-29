package by.it_academy.MDK29522.controller;

import by.it_academy.MDK29522.core.dto.ArtistID;
import by.it_academy.MDK29522.core.dto.GenreID;
import by.it_academy.MDK29522.service.api.IArtistService;
import by.it_academy.MDK29522.service.api.IGenreService;
import by.it_academy.MDK29522.service.fabrics.ArtistServiceSingleton;
import by.it_academy.MDK29522.service.fabrics.GenreServiceSingleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "NominationsServlet", urlPatterns = "/nomination")
public class NominationsServlet extends HttpServlet {
    private final IArtistService artistService;
    private final IGenreService genreService;

    public NominationsServlet() {
        this.artistService = ArtistServiceSingleton.getInstance();
        this.genreService = GenreServiceSingleton.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();
        writer.write("<p>Номинированные артисты:</p>");
        List<ArtistID> artists = artistService.getArtists();

        for(ArtistID artistID : artists){
            writer.write("<p>"+artistID.getId()+" "+artistID.getArtist().getName()+"</p>");
        }

        List<GenreID> genres = genreService.getGenres();
        writer.write("<p></p><p>Номинированные жанры:</p>");
        for(GenreID genreID : genres){
            writer.write("</p>"+genreID.getId()+" "+genreID.getGenre().getName()+"</p>");
        }
    }
}

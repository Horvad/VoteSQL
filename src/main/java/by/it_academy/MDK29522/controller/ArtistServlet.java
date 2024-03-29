package by.it_academy.MDK29522.controller;

import by.it_academy.MDK29522.core.dto.ArtistDTO;
import by.it_academy.MDK29522.core.dto.ArtistID;
import by.it_academy.MDK29522.service.api.IArtistService;
import by.it_academy.MDK29522.service.fabrics.ArtistServiceSingleton;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ArtistServlet", urlPatterns = "/artist")
public class ArtistServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();
    private final IArtistService artistService;
    private final String ARTIST_PARAM_NAME = "artist";

    public ArtistServlet() {
        this.artistService = ArtistServiceSingleton.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");

        List<ArtistID> artists = artistService.getArtists();
        PrintWriter writer = resp.getWriter();

            writer.write(objectMapper.writeValueAsString(artists));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        Map<String, String[]> parametrMap = req.getParameterMap();

        String[] artists = parametrMap.get(ARTIST_PARAM_NAME);
        if(artists==null) throw new IllegalArgumentException("Ничего не передано");
        if(artists.length == 0) throw new IllegalArgumentException("Ничего не передано");
        boolean save = false;
        for(String artist : artists){
            if(!artist.isBlank()){
                artistService.save(new ArtistDTO(artist));
                save = true;
            }
        }
        if(!save) throw new IllegalArgumentException("Переданы пустые имена");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        Map<String, String[]> mapParameters = req.getParameterMap();
        try {
            if(!mapParameters.containsKey("id")||!mapParameters.containsKey("name")||mapParameters==null){
                throw new IllegalArgumentException("Ничего не передано");
            }
            String[] pudId = mapParameters.get("id");
            if(pudId==null){
                throw new IllegalArgumentException("Не введен id");
            }
            if(pudId[0]==null){
                throw new IllegalArgumentException("Не введен id");
            }
            String[] putName = mapParameters.get("name");
            if(putName==null){
                throw new IllegalArgumentException("Не введено имя");
            }
            if(putName.length==0){
                throw new IllegalArgumentException("Не введено имя");
            }
            if(putName[0]==null){
                throw new IllegalArgumentException("Не введено имя");
            }
            if(artistService.exist(putName[0])){
                throw new IllegalArgumentException("Такое имя существует");
            }
            long id = Long.parseLong(pudId[0]);
            artistService.edit(id,putName[0]);
        } catch (IllegalArgumentException e){
            writer.write("<p>" + e.getMessage() + "</p>");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        Map<String, String[]> mapParameters = req.getParameterMap();
        String[] deleteArtist = mapParameters.get("id");

        try{
            if (!mapParameters.containsKey("id") || deleteArtist == null ) {
                throw new IllegalArgumentException("Ключ операции не передан или передан не правильно");
            }

            long idForDelete = Integer.parseInt(deleteArtist[0]);

            artistService.delete(idForDelete);
            writer.write("<p>Вы удалили исполнителя по id: " + idForDelete + "</p>");
        }catch(RuntimeException e){
            writer.write("<p>" + e.getMessage() + "</p>");
        }
    }
}

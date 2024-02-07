package com.example.Task8;

import jakarta.persistence.ManyToMany;
import jdk.jfr.Category;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/albums")
//@Table("album_controller")
public class AlbumController {

    private AlbumRepository albumRepository;
    private ArtistRepository artistRepository;
    private GenreRepository genreRepository;

    public AlbumController(AlbumRepository albumRepository,
                           ArtistRepository artistRepository,
                           GenreRepository genreRepository){

        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.genreRepository = genreRepository;
    }

    @GetMapping("/get")
    public Iterable<Album> getAlbums() {
        return albumRepository.findAll();
    }

    @ManyToMany
    private Set<Artist> artists;

    @PostMapping("/add")
    public Album addAlbum(@RequestParam String album_name,
                           @RequestParam int year,
                           @RequestParam double price,
                           @RequestParam int number_of_songs,
                           @RequestParam Long genre_id,
                           @RequestParam Long artist_id){
        Album album = new Album();
        album.setAlbumName(album_name);
        album.setYear(year);
        album.setPrice(price);
        album.setNumberOfSongs(number_of_songs);

        Optional<Genre> optionalGenre = genreRepository.findById(genre_id);
        if(optionalGenre.isPresent()){
            album.setGenre(optionalGenre.get());
        }

        // Това не е правилно, защото няма да мога да добавя повече от един артист...
        Optional<Artist> optionalArtist = artistRepository.findById(artist_id);
        if(optionalArtist.isPresent()){
            album.setArtist(optionalArtist.get());
        }
        return album;
    }
}

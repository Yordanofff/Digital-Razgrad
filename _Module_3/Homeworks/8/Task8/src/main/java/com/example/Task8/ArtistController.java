package com.example.Task8;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/artists")
public class ArtistController {
    private ArtistRepository artistRepository;

    public ArtistController(ArtistRepository artistRepository){
        this.artistRepository = artistRepository;
    }

    @GetMapping("/get")
    public Iterable<Artist> getArtist(){
        return artistRepository.findAll();
    }

    @PostMapping("/add")
    public Artist addArtist(@RequestParam String name, @RequestParam String nationality, @RequestParam int year_born){
        Artist artist = new Artist(name, nationality, year_born);
        artistRepository.save(artist);
        return artist;
    }

}

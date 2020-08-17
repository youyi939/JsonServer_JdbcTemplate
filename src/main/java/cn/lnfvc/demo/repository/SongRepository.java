package cn.lnfvc.demo.repository;

import cn.lnfvc.demo.pojo.Song;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface SongRepository {
    List<Song> showSongs();
    int create(int id,String name,String singer,String img);
    int del(Song song);
    int update(Song song);
    Song findById(int id);
    List<Song> limit (int num,int size);

}

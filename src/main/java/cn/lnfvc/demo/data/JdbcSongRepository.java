package cn.lnfvc.demo.data;

import cn.lnfvc.demo.pojo.Song;
import cn.lnfvc.demo.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcSongRepository implements SongRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcSongRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Song> showSongs() {
        List<Song> songList = jdbcTemplate.query("select id,name,singer,img from Song",this::RowToUser);
        return songList;
    }

    @Override
    public int create(int id, String name, String singer, String img) {
        String sql = "insert into Song(id,name,singer,img) values(?,?,?,?)";
        return jdbcTemplate.update(sql,id,name,singer,img);
    }

    @Override
    public int del(Song song) {
        String sql = "delete from Song where id = ?";
        return jdbcTemplate.update(sql,song.getId());
    }

    @Override
    public int update(Song song) {
        String sql = "update Song set name = ?,singer = ?,img = ? where id = ?";
        return jdbcTemplate.update(sql,song.getName(),song.getSinger(),song.getImg(),song.getId());
    }

    @Override
    public Song findById(int id) {
        String sql = "select * from Song where id = " + id;
        List<Song> songList = jdbcTemplate.query(sql,this::RowToUser);
        return songList.get(0);
    }

    @Override
    public List<Song> limit(int num, int size) {
        int page = size*num;
        String sql = "select * from Song limit " + page +","+size;
        List<Song> songList = jdbcTemplate.query(sql,this::RowToUser);
        return songList;
    }


    private Song RowToUser(ResultSet resultSet, int rowNum)throws SQLException {
        Song song = new Song();
        song.setId(resultSet.getInt("id"));
        song.setName(resultSet.getString("name"));
        song.setSinger(resultSet.getString("singer"));
        song.setImg(resultSet.getString("img"));
        return song;
    }

}

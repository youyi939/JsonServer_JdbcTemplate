package cn.lnfvc.demo.controller;

import cn.lnfvc.demo.data.JdbcSongRepository;
import cn.lnfvc.demo.pojo.Song;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@Slf4j
@Controller
@RequestMapping("song")
public class SongController {
    private final JdbcSongRepository jdbcSongRepository;

    @Autowired
    public SongController(JdbcSongRepository jdbcSongRepository) {
        this.jdbcSongRepository = jdbcSongRepository;
    }

    /*
    * 查询全部
    * 返回List
    * */
    @RequestMapping("/findAll")
    @ResponseBody
    public List showSong(){
        List<Song> song = jdbcSongRepository.showSongs();
        return song;
    }

    /*
    * 增加操作
    * */
    @RequestMapping("/create")
    @ResponseBody
    public Song create(Song song){
        jdbcSongRepository.create(song.getId(),song.getName(),song.getSinger(),song.getImg());
        return song;
    }

    /*
    * 删除操作
    * id
    * */
    @RequestMapping("/del")
    @ResponseBody
    public String del(Song song){
        int temp = jdbcSongRepository.del(song);
        if (temp > 0) {
            return "删除成功，删除内容为：" + song;
        }
        return "删除失败，此ID可能为空";
    }

    /*
    * 根据ID查找后进行修改
    * */
    @RequestMapping("/upd")
    @ResponseBody
    public String updSong(Song song){
        int temp = jdbcSongRepository.update(song);
        if (temp > 0){
            return "修改成功，更新内容如下："+song;
        }
        return "修改错误，此ID可能为空";
    }


    /*
    * 根据ID进行查找
    * */
    @RequestMapping("/findById")
    @ResponseBody
    public Song findById(int id){
        Song song = jdbcSongRepository.findById(id);
        return song;
    }


    /*
    * 分页查询
    * num：页数
    * size：每页包含几个数据
    * */
    @RequestMapping("/limit")
    @ResponseBody
    public List<Song> limit(int num,int size){
        List<Song> songList = jdbcSongRepository.limit(num,size);
            return songList;
    }

}

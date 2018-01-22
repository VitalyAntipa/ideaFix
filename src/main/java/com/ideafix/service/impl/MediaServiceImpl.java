package com.ideafix.service.impl;

import com.ideafix.dao.MediaDAO;
import com.ideafix.model.dto.MediaDTO;
import com.ideafix.model.pojo.Idea;
import com.ideafix.model.pojo.Media;
import com.ideafix.service.MediaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MediaServiceImpl implements MediaService {

    private MediaDAO mediaDAO;

    public MediaServiceImpl(MediaDAO mediaDAO) {
        this.mediaDAO = mediaDAO;
    }

    @Override
    public List<Media> attachMedia(List<MediaDTO> listOfMedia, Idea idea) {
        List<Media> medias = new ArrayList<>(listOfMedia.size());

        deleteMediaByIdeaId(idea.getId());

        for (MediaDTO mediaDTO : listOfMedia) {
            Media tmpMedia = new Media(mediaDTO.getMedia_url());
            tmpMedia.setIdea(idea);
            medias.add(mediaDAO.saveAndFlush(tmpMedia));
        }

        return medias;
    }

    private void deleteMediaByIdeaId(long ideaId) {
        mediaDAO.deleteAllByIdeaId(ideaId);
    }
}

package com.ideafix.service;

import com.ideafix.model.dto.MediaDTO;
import com.ideafix.model.pojo.Idea;

import java.util.List;

public interface MediaService {

    public void attachMedia(List<MediaDTO> listOfMedia, Idea idea);
}

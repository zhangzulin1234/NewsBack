package com.news.news_back.service.impl;

import com.news.news_back.dataobject.NewsInfo;
import com.news.news_back.enums.NewsReviewEnum;
import com.news.news_back.repository.NewsInfoRepository;
import com.news.news_back.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsInfoRepository repository;

    @Autowired
    public NewsServiceImpl(NewsInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    public NewsInfo findOne(Integer newsId) {
        return repository.findByNewsId(newsId);
    }

    @Override
    public List<NewsInfo> findUpAll() {
        return repository.findByNewsIsreview(NewsReviewEnum.YES.getCode());
    }


    @Override
    public Page<NewsInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public NewsInfo save(NewsInfo newsInfo) {
        return repository.save(newsInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByNewsId(Integer newsId) {
        repository.delete(newsId);
    }


    @Override
    public Page<NewsInfo> findList(Integer category, Pageable pageable) {
        Page<NewsInfo> newsInfoPage = repository.findByNewsCategory(category,pageable);
        return newsInfoPage;
    }


}

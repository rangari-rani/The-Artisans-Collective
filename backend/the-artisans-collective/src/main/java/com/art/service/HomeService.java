package com.art.service;

import com.art.modal.Home;
import com.art.modal.HomeCategory;

import java.util.List;

public interface HomeService {
    public Home createHomePageData(List<HomeCategory> allCategories);
}

package com.casestudy.service.hastag;

import com.casestudy.model.Hastag;
import com.casestudy.service.IGeneralService;

public interface IHastagService extends IGeneralService<Hastag> {
    Iterable<Hastag> getTheMostUsedHashtags();
}

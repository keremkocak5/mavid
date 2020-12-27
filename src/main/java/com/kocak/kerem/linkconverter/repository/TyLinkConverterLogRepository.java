package com.kocak.kerem.linkconverter.repository;

import com.kocak.kerem.linkconverter.model.trendyoltest.TyLinkConverterLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TyLinkConverterLogRepository extends CrudRepository<TyLinkConverterLog, Integer> {

}

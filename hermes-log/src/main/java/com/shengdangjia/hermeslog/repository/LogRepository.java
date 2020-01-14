package com.shengdangjia.hermeslog.repository;

import com.shengdangjia.hermeslog.entity.Log;
import org.springframework.data.repository.CrudRepository;

public interface LogRepository extends CrudRepository<Log, String> {
}

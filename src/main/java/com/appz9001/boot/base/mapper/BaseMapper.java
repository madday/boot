package com.appz9001.boot.base.mapper;

import tk.mybatis.mapper.common.base.BaseDeleteMapper;
import tk.mybatis.mapper.common.base.BaseInsertMapper;
import tk.mybatis.mapper.common.base.BaseSelectMapper;
import tk.mybatis.mapper.common.base.BaseUpdateMapper;

public interface BaseMapper<T> extends BaseInsertMapper<T> , BaseSelectMapper , BaseUpdateMapper, BaseDeleteMapper {

}

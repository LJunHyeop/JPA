package com.green.greengram.admin;

import com.green.greengram.admin.model.GetApiAdminProvider;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    List<GetApiAdminProvider> GetApiAdmin(GetApiAdminProvider p);

}

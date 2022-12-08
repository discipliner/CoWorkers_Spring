package com.crunch.mapper;

import com.crunch.domain.UserInfoDTO;

import java.util.ArrayList;

public interface UserInfoMapper {



    void userInfoInsert(UserInfoDTO userInfoDTO);

    String userInfoCompareID(String accountID);

    String userInfoComparePW(String accountPassword);

    ArrayList<UserInfoDTO> userInfoSelect(String accountID);
}

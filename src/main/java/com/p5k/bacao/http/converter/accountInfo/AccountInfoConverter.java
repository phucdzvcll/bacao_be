package com.p5k.bacao.http.converter.accountInfo;

import com.p5k.bacao.http.core.base.IConverter;
import com.p5k.bacao.http.dto.accountInfo.AccountDetailDto;
import com.p5k.bacao.http.entity.accountInfo.AccountInfoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountInfoConverter extends IConverter<AccountDetailDto, AccountInfoEntity> {

    AccountInfoConverter INSTANCE = Mappers.getMapper(AccountInfoConverter.class);


}

package com.p5k.bacao.http.core.xtools;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.p5k.bacao.http.core.base.GenericDto;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class XColsUtils extends CollectionUtils {

    private static ObjectMapper om = new ObjectMapper();

    public static <K,V,D> Map<K,V> dtoToMap(D d){
        Map<K, V> m = om.convertValue(d, Map.class);
        return m;
    }

    public static <K,V,D> List<Map<K,V>> dtoToMap(List<D> dtoList){
        List<Map<K,V>> mapList = new ArrayList<>();
        dtoList.forEach(d->{
            mapList.add(dtoToMap(d));
        });
        return mapList;
    }

    public static <K,V,D> GenericDto<D> mapToDto(Map<K,V> m){
        return om.convertValue(m, GenericDto.class);
    }

    public static Map getAsMap(final Map inParams, String dsKey){
        return XMapUtils.get(inParams, dsKey, HashMap.class);
    }


}

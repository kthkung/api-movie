package com.api.movie.base.helperUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Tuple;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MappingHelper {
    @SuppressWarnings("unchecked")
	public static <T> T mapperQueryTupleToModel(Tuple p, Class<T> className) {
			Map<String, Object> maps = new HashMap<String, Object>();
			p.getElements().forEach(te -> {
				maps.put(te.getAlias(), p.get(te.getAlias()));
			});
			return (T) getMapper(maps, className);
	}

    @SuppressWarnings("unchecked")
    public static <T> List<T> mapperQueryTupleToModel(List<Tuple> tupleList , Class<T> className) {
        return tupleList.stream().map(p-> 
                {
					return mapperQueryTupleToModel(p, className);
            }
        ).collect(Collectors.toList());
    }

	@SuppressWarnings("unchecked")
    public static <T> List<T> mapperQueryTupleToModelCamelcasify(List<Tuple> tupleList , Class<T> className) {
        return tupleList.stream().map(p-> 
                {
					return mapperQueryTupleToModelCamelcasify(p, className);
            }
        ).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
	public static <T> T mapperQueryTupleToModelCamelcasify(Tuple p, Class<T> className) {
		Map<String, Object> maps = new HashMap<String, Object>();
		p.getElements().forEach(te -> {
			maps.put(camelcasify(te.getAlias()), p.get(te.getAlias()));
		});
		return (T) getMapper(maps, className);
	}
	
	public static String camelcasify(String in) {
        StringBuilder sb = new StringBuilder();
        boolean capitalizeNext = false;
        for (char c : in.toLowerCase().toCharArray()) {
            if (c == '_') {
                capitalizeNext = true;
            } else {
                if (capitalizeNext) {
                    sb.append(Character.toUpperCase(c));
                    capitalizeNext = false;
                } else {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
	}

    @SuppressWarnings("unchecked")
	public static <Any> Any getMapper(Object object, Class classMap) {

		final ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// mapper.convertValue(object, classMap);
		return ((Any) mapper.convertValue(object, classMap));
	}
}


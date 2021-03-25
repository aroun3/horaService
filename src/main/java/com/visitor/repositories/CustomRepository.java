/*package com.visitor.repositories;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Transactional
@Component
public class CustomRepository<T> {

    @Autowired
    private EntityManager em;

    private ObjectMapper mapper = new ObjectMapper();

    public List<T> getResultOfQuery(String argQueryString,Class<T> valueType) {
        try {
            Query query = em.createNativeQuery(argQueryString);
            NativeQueryImpl nativeQuery = (NativeQueryImpl) query;
            nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
            List<Map<String,Object>> result = nativeQuery.getResultList();
            return result.stream()
                    .map(o -> {
                        try {
                            return mapper.readValue(mapper.writeValueAsString(o),valueType);
                        } catch (Exception e) {
                            //ApplicationLogger.logger.error(e.getMessage(),e);
                        }
                        return null;
                    }).collect(Collectors.toList());
            
        } catch (Exception ex) {
            ApplicationLogger.logger.error(ex.getMessage(),ex);
            throw ex;
        }
    }
}*/
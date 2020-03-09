package com.escola.api.graphql;

import com.escola.api.model.Turma;
import com.escola.api.service.TurmaService;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@GraphQLApi
public class TurmaGraphQLQuery {

    @Autowired
    private TurmaService service;

    @GraphQLQuery
    public Turma getTurma(Long id){
        return this.service.findById(id);
    }

}

package com.ownprojects.backend.utils.specification;

import lombok.ToString;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ToString
public class Group {
    private String query;

    private List<Predicate> predicates;
    private List<Group> groups;
    private List<Connector> connectors;

    private Integer startIndex;
    private Integer endIndex;

    private CriteriaBuilder cb;
    private Root root;

    public Group(String query, Root root, CriteriaBuilder cb) {
        this.query = query;
        this.startIndex = null;
        this.endIndex = null;

        this.predicates = new ArrayList<Predicate>();
        this.groups = new ArrayList<Group>();
        this.connectors = new ArrayList<Connector>();

        this.root = root;
        this.cb = cb;
    }

    public Group(String query, Integer startIndex, Integer endIndex, Root root, CriteriaBuilder cb) {
        this.query = query;
        this.startIndex = startIndex;
        this.endIndex = endIndex;

        this.predicates = new ArrayList<Predicate>();
        this.groups = new ArrayList<Group>();
        this.connectors = new ArrayList<Connector>();

        this.root = root;
        this.cb = cb;
    }

    private void convertInPredicateDeep() {
        this.build();

        while(this.thereAreGroups()) {
            Group group = this.getGroupOnTheFarLeft();
            group.convertInPredicateDeep();

            Predicate predicate = group.getPredicate();
            this.predicates.add(predicate);
            this.groups.remove(group);
        }

        this.convertInPredicate();
    }

    private boolean thereAreGroups() {
        return this.groups.size() != 0;
    }

    private Group getGroupOnTheFarLeft() {
        int indexStart = this.groups.get(0).getStartIndex();
        int indexGroupOnTheFarLeft = 0;

        for(int i = 0; i<this.groups.size(); i++){
            if( indexStart < this.groups.get(i).getStartIndex()) {
                indexStart = this.groups.get(i).getStartIndex();
                indexGroupOnTheFarLeft = i;
            }
        }

        return this.groups.get(indexGroupOnTheFarLeft);
    }

    private Predicate getPredicate() {
        return this.predicates.get(0);
    }

    private void convertInPredicate() {
        while(this.thereAreConnectors()){
            Connector connector = this.getConnectorOnTheFarLeft();

            Integer predicateStartIndex = connector.getEndIndex() + 1;
            Predicate predicate2 = this.getPredicateByStartIndex(predicateStartIndex);

            Predicate predicateResult = null;

            if(connector.isNegation()){
                predicateResult = new Predicate(predicate2,connector);
            }else{
                Integer predicateEndIndex = connector.getStartIndex() - 1;
                Predicate predicate1 = this.getPredicateByEndIndex(predicateEndIndex);
                predicateResult = new Predicate(predicate1, predicate2, connector,this.cb);

                this.predicates.remove(predicate1);
            }

            this.predicates.add(predicateResult);
            this.predicates.remove(predicate2);
            this.connectors.remove(connector);
        }

        this.predicates.get(0).setStartIndex(this.startIndex);
        this.predicates.get(0).setEndIndex(this.endIndex);
    }

    private boolean thereAreConnectors() {
        return this.connectors.size() != 0;
    }

    private Predicate getPredicateByStartIndex(Integer startIndex){
        for (int i = 0; i<predicates.size(); i++){
            Predicate predicate = predicates.get(i);
            if(predicate.isStartIndex(startIndex)){
                return predicate;
            }
        }
        return null;
    }

    private Predicate getPredicateByEndIndex(Integer endIndex){
        for (int i = 0; i<predicates.size(); i++){
            Predicate predicate = predicates.get(i);
            if(predicate.isEndIndex(endIndex)){
                return predicate;
            }
        }
        return null;
    }

    private Connector getConnectorOnTheFarLeft() {
        int indexStart = this.connectors.get(0).getStartIndex();
        int indexConnectorOnTheFarLeft = 0;

        for(int i = 0; i<this.connectors.size(); i++){
            if(this.connectors.get(i).getStartIndex() < indexStart) {
                indexStart = this.connectors.get(i).getStartIndex();
                indexConnectorOnTheFarLeft = i;
            }
        }

        return this.connectors.get(indexConnectorOnTheFarLeft);
    }

    public javax.persistence.criteria.Predicate getAsJavaxPredicate() {
        this.convertInPredicateDeep();
        return this.getPredicate().getJavaxPredicate();
    }

    private void build() {
        this.buildGroups();
        this.buildConnectors();
        this.buildPredicates();
    }

    private void buildGroups() {
        int groups = 0;
        List<Integer> startIndexes = new ArrayList<Integer>();
        List<Integer> endIndexes = new ArrayList<Integer>();

        for (int i = 0; i < this.query.length(); i++){
            if(this.query.charAt(i) == '(') {
                groups++;
                if(groups == 1) {
                    startIndexes.add(i);
                }
            }

            if(this.query.charAt(i) == ')') {
                groups--;
                if(groups == 0) {
                    endIndexes.add(i);
                }
            }
        }

        for (int i = 0; i < startIndexes.size(); i++) {
            Integer startIndex = startIndexes.get(i);
            Integer endIndex = endIndexes.get(i);

            String queryAuxiliar = this.query.substring(startIndex + 1, endIndex);

            Group myGroup = new Group(queryAuxiliar, startIndex, endIndex, this.root, this.cb);
            this.groups.add(myGroup);
        }
    }

    private void buildConnectors() {
        int connectors = 0;
        List<Integer> startIndexes = new ArrayList<Integer>();
        List<Integer> endIndexes = new ArrayList<Integer>();

        for (int i = 0; i < this.query.length(); i++){
            Integer endIndexOfBusyGroup = getEndIndexOfBusyGroup(i);

            if (endIndexOfBusyGroup != null){
                i = endIndexOfBusyGroup;
            }else{
                if(this.query.charAt(i)== ':' && connectors == 0){
                    connectors++;
                    startIndexes.add(i);
                    continue;
                }

                if(this.query.charAt(i)== ':' && connectors == 1){
                    connectors--;
                    endIndexes.add(i);
                }
            }
        }

        /*System.out.println("Connectors");
        System.out.println(startIndexes);
        System.out.println(endIndexes);*/

        for (int i = 0; i < startIndexes.size(); i++) {
            Integer startIndex = startIndexes.get(i);
            Integer endIndex = endIndexes.get(i);

            String queryAuxiliar = this.query.substring(startIndex + 1 , endIndex);

            Connector myConnector = new Connector(queryAuxiliar, startIndex, endIndex);
            this.connectors.add(myConnector);
        }
    }

    private Integer getEndIndexOfBusyGroup(Integer i){
        for (Group myGroup: groups) {
            if(i <= myGroup.getEndIndex() && i >= myGroup.getStartIndex()){
                return myGroup.getEndIndex();
            }
        }

        return null;
    }

    private void buildPredicates() {
        List<Integer> indexes = new ArrayList<Integer>();

        List<Integer> startIndexes = new ArrayList<Integer>();
        List<Integer> endIndexes = new ArrayList<Integer>();

        Integer startIndex = 0;
        Integer endIndex = this.query.length() - 1;

        for (Group myGroup: this.groups) {
            indexes.add(myGroup.getStartIndex());
            indexes.add(myGroup.getEndIndex());
        }

        for (Connector myConnector: this.connectors) {
            indexes.add(myConnector.getStartIndex());
            indexes.add(myConnector.getEndIndex());
        }
        indexes.add(startIndex);
        indexes.add(endIndex);

        Collections.sort(indexes);

        /*System.out.println("Indexes");
        System.out.println(indexes);*/

        for (int i = 0; i < indexes.size(); i = i +2){
            Integer endIndexAux = indexes.get(i);
            Integer startIndexAux = indexes.get(i+1);

            Integer subtraction = startIndexAux - endIndexAux;

            if(subtraction > 1) {
                if(endIndexAux != startIndex) {
                    endIndexAux = endIndexAux + 1;
                }

                if(startIndexAux != endIndex) {
                    startIndexAux = startIndexAux - 1;
                }

                startIndexes.add(endIndexAux);
                endIndexes.add(startIndexAux);
            }
        }

        for (int i = 0; i < startIndexes.size(); i++){
            Integer startIndexPredicate = startIndexes.get(i);
            Integer endIndexPredicate = endIndexes.get(i);

            String queryAuxiliar = this.query.substring(startIndexPredicate, endIndexPredicate + 1);

            Predicate myPredicate = new Predicate(queryAuxiliar, startIndexPredicate, endIndexPredicate, this.root, this.cb);
            this.predicates.add(myPredicate);
        }
    }

    private Integer getStartIndex() {
        return this.startIndex;
    }

    private Integer getEndIndex() {
        return this.endIndex;
    }
}

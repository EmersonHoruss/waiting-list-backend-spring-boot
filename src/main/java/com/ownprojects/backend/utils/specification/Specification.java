package com.ownprojects.backend.utils.specification;

import com.ownprojects.backend.entities.Base;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Getter
@Setter
public class Specification<E extends Base> implements org.springframework.data.jpa.domain.Specification<E> {
    private String query;

    public Specification(String query) {
        this.query = query;
    }

    public void show(){
        /*Group group = new Group(this.query,);
        group.build();
        System.out.println(group);*/
    }

    @Override
    public javax.persistence.criteria.Predicate toPredicate(Root<E> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        if (query==null || query.length()==0){
            return cb.and();
        }
        return new Group(this.query, root, cb).getAsJavaxPredicate();


        //Predicate predicate = new Predicate("manyToOne.name<ct>10", null, null, root, cb);

        /*javax.persistence.criteria.Predicate predicate1 = cb.like(root.get("name"),"%10%");
        javax.persistence.criteria.Predicate predicate2 = cb.like(root.get("name"),"%2%");
        javax.persistence.criteria.Predicate predicate3 = cb.equal(root.join("manyToOne").get("number"),9);

        javax.persistence.criteria.Predicate predicate12 = cb.or(predicate1,predicate2);
        javax.persistence.criteria.Predicate predicate123 = cb.and(predicate12,predicate3);


        String query = "(name<ct>10:or:name<ct>2):and:(number<eq>9:or:number<eq>1)";
        //return predicate123;
        return new Group(query, root, cb).getAsJavaxPredicate();
        // return cb.like(root.join("manyToOne").get("name"),"%1%");*/
    }
}

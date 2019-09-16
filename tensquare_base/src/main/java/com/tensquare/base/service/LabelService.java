package com.tensquare.base.service;


import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 标签业务逻辑类
 */
@Service
public class LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部标签
     */
    public List<Label> findAll(){
        return labelDao.findAll();
    }

    /**
     * 根据id查询标签
     * @param id
     * @return
     */
    public Label findById(String id){
        return labelDao.findById(id).get();
    }

    /**
     * 添加标签
     * @param label
     */
    public void add(Label label){
        label.setId(idWorker.nextId()+"");//设置ID
        labelDao.save(label);
    }

    /**
     * 修改标签
     * @param label
     */
    public void update(Label label){
        labelDao.save(label);
    }

    /**
     * 删除标签
     * @param id
     */
    public void deleteById(String id){
        labelDao.deleteById(id);
    }

    /**
     * 查询方法抽取
     */
    public Specification<Label> createSpecification(Label label){
        return new Specification<Label>() {
            /**
             * @param root 根对象，用来封装条件
             * @param query  封装查询的关键字 如：group by ，order by等
             * @param cb   用来封装跟对象，如果为null，表示不需要任何条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //new 一个集合，存放所有条件
                List<Predicate> list = new ArrayList<>();
                if(label.getLabelname() != null && !"".equals(label.getLabelname())){
                    Predicate labelname = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");//where labelname like "%哈%"
                    list.add(labelname);
                }
                if(label.getState() != null && !"".equals(label.getState())){
                    Predicate state = cb.like(root.get("state").as(String.class), label.getState());//where state = "1"
                    list.add(state);
                }
                Predicate[] prr = new Predicate[list.size()];
                prr = list.toArray(prr);
                return cb.and(prr);
            }
        };
    }

    /**
     * 条件查询
     * @return
     */
    public List<Label> findSearch(Specification<Label> specification) {
        return labelDao.findAll(specification);
    }

    /**
     * 条件分页查询
     * @param label
     * @param page
     * @param size
     * @return
     */
    public Page<Label> pageQuery(Specification<Label> specification,PageRequest pageRequest) {
        return labelDao.findAll(specification,pageRequest);
    }
}

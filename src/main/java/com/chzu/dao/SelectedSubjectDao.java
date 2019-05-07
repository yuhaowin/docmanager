package com.chzu.dao;

import com.chzu.entity.ClassSubject;
import com.chzu.entity.Course;
import com.chzu.entity.PagingVO;
import com.chzu.entity.SelectedSubject;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SelectedSubjectDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 保存选课信息
     * @param selectedSubject
     */
    public void selectSubject(SelectedSubject selectedSubject){
        Session session = sessionFactory.openSession();
        session.save(selectedSubject);
        session.close();
    }

    /**
     * 查询选课信息
     * @param selectedSubject
     * @param pagingVO
     * @return
     */
    public List<SelectedSubject> getSelectedSubjectList(SelectedSubject selectedSubject, PagingVO pagingVO){
        Session session = sessionFactory.openSession();

        DetachedCriteria dc = DetachedCriteria.forClass(SelectedSubject.class);
        if (selectedSubject.getStudentId() != null) {
            dc.add(Restrictions.eq("studentId", selectedSubject.getStudentId()));
        }
        if (selectedSubject.getSubjectId() != null) {
            dc.add(Restrictions.eq("subjectId", selectedSubject.getSubjectId()));
        }
        Criteria c = dc.getExecutableCriteria(session);
        if(pagingVO != null){
            c.setProjection(null);
            c.setResultTransformer(Criteria.ROOT_ENTITY);
            c.setFirstResult(pagingVO.getTopageNo());
            c.setMaxResults(pagingVO.getPageSize());
        }
        List<SelectedSubject> selectedSubjectList = c.list();
        session.close();
        return selectedSubjectList;
    }

    /**
     * 获取已选课程详情
     * @param subjectIds
     * @param pagingVO
     * @return
     */
    public List<ClassSubject> getSubject(List<Integer> subjectIds, PagingVO pagingVO){
        Session session = sessionFactory.openSession();
        List<ClassSubject> list = new ArrayList<>();
        if (subjectIds.size() >0){
            Query query = session.createQuery("from ClassSubject where subjectId in (:ids)")
                    .setParameterList("ids", subjectIds);
            if(pagingVO != null){
                query.setFirstResult(pagingVO.getTopageNo())
                        .setMaxResults(pagingVO.getPageSize());
            }
            list = query.list();
        }
        session.close();
        return list;
    }

    public void deleteSubject(Integer studentId,Integer subjectId){
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("delete from selected_subject where  student_id = ? and subject_id = ? ");
        query.setParameter(0, studentId);
        query.setParameter(1, subjectId);
        query.executeUpdate();
        session.close();
    }
}

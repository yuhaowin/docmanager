package com.chzu.dao;

import com.chzu.entity.ExcelDTO;
import com.chzu.entity.PagingVO;
import com.chzu.entity.SelectedCourse;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SelectedCourseDao {


    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 根据 courseId 计数
     *
     * @param courseId
     * @return
     */
    public Integer countById(Integer courseId) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("select count(*) from selected_course where course_id = ?");
        query.setParameter(0, courseId);
        Integer count = Integer.parseInt(query.list().get(0).toString());
        session.close();
        return count;
    }

    /**
     * 根据 courseId 和 studentId 删除
     *
     * @param selectedCourse
     */
    public void delete(SelectedCourse selectedCourse) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("delete from selected_course where  course_id = ? and student_id = ? ");
        query.setParameter(0, selectedCourse.getCourseId());
        query.setParameter(1, selectedCourse.getStudentId());
        query.executeUpdate();
        session.close();
    }

    /**
     * 新增
     *
     * @param selectedCourse
     */
    public void insert(SelectedCourse selectedCourse) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(selectedCourse);
        session.getTransaction().commit();
        session.close();
    }

    public List<SelectedCourse> selectByExample(SelectedCourse selectedCourse){
        return selectByExample(selectedCourse, null);
    }

    /**
     * 查询
     *
     * @param selectedCourse
     * @return
     */
    public List<SelectedCourse> selectByExample(SelectedCourse selectedCourse, PagingVO pagingVO) {
        Session session = sessionFactory.openSession();
        DetachedCriteria dc = DetachedCriteria.forClass(SelectedCourse.class);
        if (selectedCourse.getCourseId() != null) {
            dc.add(Restrictions.eq("courseId", selectedCourse.getCourseId()));
        }
        if (selectedCourse.getStudentId() != null) {
            dc.add(Restrictions.eq("studentId", selectedCourse.getStudentId()));
        }
        Criteria c = dc.getExecutableCriteria(session);
        if(pagingVO != null){
            c.setProjection(null);
            c.setResultTransformer(Criteria.ROOT_ENTITY);
            c.setFirstResult(pagingVO.getTopageNo());
            c.setMaxResults(pagingVO.getPageSize());
        }
        List<SelectedCourse> selectedCourses = c.list();
        session.close();
        return selectedCourses;
    }

    public void updateByExample(SelectedCourse selectedCourse) {
        String hql = "update selected_course set mark = ? where  course_id = ? and student_id =?";
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, selectedCourse.getMark());
        query.setParameter(1, selectedCourse.getCourseId());
        query.setParameter(2, selectedCourse.getStudentId());
        query.executeUpdate();
        session.close();
    }

    public List<Integer> getByStudentId(Integer studentId) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("select course_id from selected_course where student_id = ?");

        DetachedCriteria dc = DetachedCriteria.forClass(SelectedCourse.class);
        dc.add(Restrictions.eq("studentId", studentId));
        query.setParameter(0, studentId);
        List<Integer> list = query.list();
        session.close();
        return list;
    }


    /**
     * 获取某门课程学生的打分
     *
     * @param courseId
     * @return
     */
    public List<ExcelDTO> getStudentsMark(Integer courseId) {
        String sql = "SELECT " +
                " d.`user_id`, c.`course_name`,d.`user_name`,c.`mark`  " +
                " FROM " +
                " (SELECT  " +
                "  a.*, b.`course_name` " +
                "  FROM " +
                "   ( SELECT `course_id`, `student_id`, `mark` FROM selected_course WHERE course_id = ? ) a " +
                "   LEFT JOIN course b ON a.`course_id` = b.`course_id` " +
                " ) c " +
                "LEFT JOIN student d ON c.`student_id` = d.user_id";
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery(sql).addEntity(ExcelDTO.class);
        query.setParameter(0, courseId);
        List<ExcelDTO> excelDTOList = query.list();
        session.close();
        return excelDTOList;
    }
}

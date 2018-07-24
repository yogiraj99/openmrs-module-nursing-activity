package org.openmrs.module.nursingactivity.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Patient;
import org.openmrs.module.nursingactivity.dao.NursingActivityScheduleDao;
import org.openmrs.module.nursingactivity.model.NursingActivitySchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class NursingActivityScheduleDaoImpl implements NursingActivityScheduleDao {

  private SessionFactory sessionFactory;

  @Autowired
  public NursingActivityScheduleDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public List<NursingActivitySchedule> getScheduleEntriesForPatient(Patient patient, Date startDate, Date endDate) {
    Criteria criteria = sessionFactory.getCurrentSession().createCriteria(NursingActivitySchedule.class);
    criteria.add(Restrictions.eq("patient", patient));
    criteria.add(Restrictions.between("scheduleTime", startDate, endDate));
    return criteria.list();
  }

}

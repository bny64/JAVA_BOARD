package com.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.web.hibernate.util.NOTUSED_HibernateUtil;

public class HibernateTest {
	public static void main(String[] args) {

		 try(Session session = NOTUSED_HibernateUtil.getSessionFactory().openSession()) {
				// Check MySQL database version
				String sql = "select version()";
				String result = (String) session.createNativeQuery(sql).getSingleResult();
				System.out.println("MySql Database Version is:::");
				System.out.println(result);
			} catch (HibernateException e) {
				e.printStackTrace();
			}	

	}
}

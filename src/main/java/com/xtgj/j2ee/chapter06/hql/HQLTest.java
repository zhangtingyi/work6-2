package com.xtgj.j2ee.chapter06.hql;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import com.xtgj.j2ee.chapter06.basedao.BaseHibernateDAO;
import com.xtgj.j2ee.chapter06.entity.Fwxx;
import com.xtgj.j2ee.chapter06.entity.JD;



public class HQLTest extends BaseHibernateDAO {
	public static void main(String[] args) {
		new HQLTest().testBaseQuery();
	}

	public void testBaseQuery() {
		Session session = this.getSession(); // 得到session
		String hql = "FROM Fwxx"; // 编写HQL语句
		Query query = session.createQuery(hql);// 创建Query对象
		List list = query.list(); // 执行查询，得到结果
		printFwxxList(list);
	}
	
	public void testAssociatedQuery() {
		Session session = this.getSession();
		String hql = " select jd " + "from JD jd,F fw "
				+ "where fw.jd=jd and fw.lxr='伊先生' ";
		Query query = session.createQuery(hql);
		List list = query.list();
		printJdList(list);
	}

public void printJdList(List list) {
		Iterator it = list.iterator();
		while (it.hasNext()) {
			JD item = (JD) it.next();
			System.out.println(item.getJd());
		}
}

	private void printFwxxList(List list) {
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Fwxx item = (Fwxx) it.next();
			System.out.println(item.getLxr() + "\t[" + item.getDate() + "]\t"
					+ item.getTitle() + "(" + item.getZj() + ")");
		}
	}
}



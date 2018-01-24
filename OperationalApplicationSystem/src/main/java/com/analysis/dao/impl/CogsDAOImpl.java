package com.analysis.dao.impl;

import java.text.MessageFormat;
import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.analysis.dao.CogsDAO;
import com.analysis.model.COGS_Details;
import com.kime.infoenum.Message;

@Repository
public class CogsDAOImpl extends HibernateDaoSupport implements CogsDAO{

	@Autowired
    private SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    }
	
	@Override
	public List Query(String where) {
		Session session=this.getSessionFactory().openSession();
		session.beginTransaction(); 
		//String sql="delete from t_irrecoverable_vat "+where; 
		StringBuilder sb=new StringBuilder();
		
		sb.append("select '' AS Month,                                                                                                                                                     ");
		sb.append("'' AS Customer,                                                                                                                                                         ");
		sb.append("'' AS ProductMix,                                                                                                                                                       ");
		sb.append("'' AS FramePoReport,                                                                                                                                                    ");
		sb.append("'' AS FrameProjection,                                                                                                                                                    ");
		sb.append("a.Project_Code AS projectNO,                                                                                                                                            ");
		sb.append("b.sales,                                                                                                                                                                ");
		sb.append("'1' AS COGS,                                                                                                                                                            ");
		sb.append("b.sales/1 AS COGS_Sales,                                                                                                                                                ");
		sb.append("'' as GrossProfit,                                                                                                                                                      ");
		sb.append("'' as OperatingExpense,                                                                                                                                                 ");
		sb.append("'' as OperatingProfit,                                                                                                                                                  ");
		sb.append("c.IrrecoverableVAT,                                                                                                                                                     ");
		sb.append("a.Labour_Cost_50,                                                                                                                                                       ");
		sb.append("a.Material_50,                                                                                                                                                          ");
		sb.append("a.Consumption_50,                                                                                                                                                       ");
		sb.append("a.Goods_Transport_50,                                                                                                                                                   ");
		sb.append("a.Other_50,                                                                                                                                                             ");
		sb.append("a.Indirect_Labour_Cost_50,                                                                                                                                              ");
		sb.append("a.General_MFG_Expenses_50,                                                                                                                                              ");
		sb.append("a.Research_Development_50,                                                                                                                                              ");
		sb.append("a.Factory_Amortization_50,                                                                                                                                              ");
		sb.append("a.Sales_Marketing_50,                                                                                                                                                   ");
		sb.append("a.General_Administration_Cost_50,                                                                                                                                       ");
		sb.append("a.Labour_Cost_60,                                                                                                                                                       ");
		sb.append("a.Material_60,                                                                                                                                                          ");
		sb.append("a.Consumption_60,                                                                                                                                                       ");
		sb.append("a.Goods_Transport_60,                                                                                                                                                   ");
		sb.append("a.Other_60,                                                                                                                                                             ");
		sb.append("a.Indirect_Labour_Cost_60,                                                                                                                                              ");
		sb.append("a.General_MFG_Expenses_60,                                                                                                                                              ");
		sb.append("a.Research_Development_60,                                                                                                                                              ");
		sb.append("a.Factory_Amortization_60,                                                                                                                                              ");
		sb.append("a.Sales_Marketing_60,                                                                                                                                                   ");
		sb.append("a.General_Administration_Cost_60,                                                                                                                                       ");
		sb.append("'' as Notes,                                                                                                                                                            ");
		sb.append("a.Labour_Cost_70,                                                                                                                                                       ");
		sb.append("a.Material_70,                                                                                                                                                          ");
		sb.append("a.Consumption_70,                                                                                                                                                       ");
		sb.append("a.Goods_Transport_70,                                                                                                                                                   ");
		sb.append("a.Other_70,                                                                                                                                                             ");
		sb.append("a.Indirect_Labour_Cost_70,                                                                                                                                              ");
		sb.append("a.General_MFG_Expenses_70,                                                                                                                                              ");
		sb.append("a.Research_Development_70,                                                                                                                                              ");
		sb.append("a.Factory_Amortization_70,                                                                                                                                              ");
		sb.append("a.Sales_Marketing_70,                                                                                                                                                   ");
		sb.append("a.General_Administration_Cost_70                                                                                                                                        ");
		sb.append(" from t_source a                                                                                                                                                        ");
		sb.append("Left join (select sum(LT_1_Amount) AS sales, Managrl_Code_1 AS ProjectNO from t_sales GROUP BY Managrl_Code_1) b on a.Project_Code=b.ProjectNO                          ");
		sb.append("left join (select sum(LT_1_Amount) AS IrrecoverableVAT, Managrl_Code_1 AS ProjectNO from t_irrecoverable_vat GROUP BY Managrl_Code_1)  c on a.Project_Code=c.ProjectNO  ");
		sb.append(where);

		return session.createSQLQuery(sb.toString()).addEntity(new COGS_Details().getClass()).list();

	}

}

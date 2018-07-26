package com.yarm.hadoop.hive.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yarm.base.pojo.CompanyInfoPojo;
import com.yarm.hadoop.hive.service.HiveService;
import com.yarm.hadoop.hive.util.HiveUtil;

@Service
public class HiveServiceImpl implements HiveService {

	@Override
	public List<CompanyInfoPojo> getHiveData() {
		List<CompanyInfoPojo> companyInfoPojoList = new ArrayList<>();
		Connection con = HiveUtil.getCon("ystest_database");
		
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			
			StringBuffer sbf = new StringBuffer();
			sbf.append("select cha_bra_com_typename ,")
			.append(" channel_name            ,")
			.append(" nvl(area_name,'小计')      as        area_name     ,")
			.append(" sum(stores_records)         as stores_records         , " )
			.append(" sum(stores_area1)           as stores_area1           , ")
			.append(" sum(stores_area50)          as stores_area50           ,") 
			.append(" sum(stores_area100)         as stores_area100         , ")
			.append(" sum(stores_area200)         as stores_area200          ,") 
			.append(" sum(stores_area300)         as stores_area300         , ")
			.append(" sum(stores_area500)         as stores_area500         , ")
			.append(" sum(stores_area_sum)        as stores_area_sum         ,") 
			.append(" sum(stores_area_p)          as stores_area_p           ,") 
			.append(" sum(route_stores_area1)     as route_stores_area1     , ")
			.append(" sum(route_stores_area50)    as route_stores_area50     ,") 
			.append(" sum(route_stores_area100)   as route_stores_area100    ,") 
			.append(" sum(route_stores_area200)   as route_stores_area200    ,") 
			.append(" sum(route_stores_area300)   as route_stores_area300    ,") 
			.append(" sum(route_stores_area500)   as route_stores_area500    ,") 
			.append(" sum(route_stores_area_sum)  as route_stores_area_sum   ,") 
			.append(" sum(route_stores_area_p)    as route_stores_area_p     ,") 
			.append(" case when sum(stores_area_sum)<>0 then concat(round(sum(route_stores_area_sum)/sum(stores_area_sum),2)*100.00,'%')" )
			.append(" else null end             as route_stores_rate       ,") 
			.append(" sum(sd_area1)               as sd_area1                ,") 
			.append(" sum(sd_area100)             as sd_area100              ,") 
			.append(" sum(sd_area200)             as sd_area200              ,") 
			.append(" sum(sd_area500)             as sd_area500              ,") 
			.append(" sum(sd_area1000)            as sd_area1000             ,") 
			.append(" sum(sd_area_sum)            as sd_area_sum             ,") 
			.append(" case when sum(stores_area_sum)<>0 then concat(round(sum(sd_area_sum)/sum(stores_area_sum),2)*100.00,'%')"   )
			.append(" else null end             as sd_area_rate            ,") 
			.append(" sum(division_stores_all)    as division_stores_all"  )    
			.append(" from ystest_database.T_STA6_TERMINAL_REP"  )
			.append(" where yearmonth='201612'" )
			.append(" and cha_bra_com_typeno=3 " )
			.append(" and channel_ID not in('CD8','CCB')")
			.append(" and company_id='C21'"   )
			.append(" group by cha_bra_com_typename,channel_name, area_name")

			.append(" union all")
			.append(" select cha_bra_com_typename ,")
			.append("  channel_name            ,")
			.append(" '小计'     as        area_name     ,")
			.append("sum(stores_records)         as stores_records          ,") 
			.append("sum(stores_area1)           as stores_area1            ,") 
			.append("sum(stores_area50)          as stores_area50           ,") 
			.append("sum(stores_area100)         as stores_area100          ,") 
			.append("sum(stores_area200)         as stores_area200          ,") 
			.append("sum(stores_area300)         as stores_area300          ,") 
			.append("sum(stores_area500)         as stores_area500          ,") 
			.append("sum(stores_area_sum)        as stores_area_sum         ,") 
			.append("sum(stores_area_p)          as stores_area_p           ,") 
			.append("sum(route_stores_area1)     as route_stores_area1      ,") 
			.append("sum(route_stores_area50)    as route_stores_area50     ,") 
			.append("sum(route_stores_area100)   as route_stores_area100    ,") 
			.append("sum(route_stores_area200)   as route_stores_area200    ,") 
			.append("sum(route_stores_area300)   as route_stores_area300    ,") 
			.append("sum(route_stores_area500)   as route_stores_area500    ,") 
			.append("sum(route_stores_area_sum)  as route_stores_area_sum   ,") 
			.append("sum(route_stores_area_p)    as route_stores_area_p     ,") 
			.append("case when sum(stores_area_sum)<>0 then concat(round(sum(route_stores_area_sum)/sum(stores_area_sum),2)*100.00,'%')" )
			.append("else null end             as route_stores_rate      , ")
			.append("sum(sd_area1)               as sd_area1               , ")
			.append("sum(sd_area100)             as sd_area100             , ")
			.append("sum(sd_area200)             as sd_area200              ,") 
			.append(" sum(sd_area500)             as sd_area500              ,") 
			.append("sum(sd_area1000)            as sd_area1000             ,") 
			.append("sum(sd_area_sum)            as sd_area_sum             ,") 
			.append("case when sum(stores_area_sum)<>0 then concat(round(sum(sd_area_sum)/sum(stores_area_sum),2)*100.00,'%')"  ) 
			.append("  else null end             as sd_area_rate            ,") 
			.append(" sum(division_stores_all)    as division_stores_all" )
			.append(" from ystest_database.T_STA6_TERMINAL_REP"  )
			.append(" where yearmonth='201612' ")
			.append(" and cha_bra_com_typeno=3" )
			.append(" and channel_ID not in('CD8','CCB')")
			.append(" and company_id='C21'"   )
			.append(" group by cha_bra_com_typename,channel_name")
			.append(" union all")
			.append(" select cha_bra_com_typename ,")
			.append(" '现渠直营'  as  channel_name            ,")
			.append(" '现渠直营' as   area_name     ,")
			.append(" sum(stores_records)         as stores_records          ,") 
			.append(" sum(stores_area1)           as stores_area1            ,") 
			.append(" sum(stores_area50)          as stores_area50           ,") 
			.append(" sum(stores_area100)         as stores_area100          ,") 
			.append(" sum(stores_area200)         as stores_area200          ,") 
			.append(" sum(stores_area300)         as stores_area300          ,") 
			.append(" sum(stores_area500)         as stores_area500          ,") 
			.append(" sum(stores_area_sum)        as stores_area_sum         ,") 
			.append(" sum(stores_area_p)          as stores_area_p           ,") 
			.append(" sum(route_stores_area1)     as route_stores_area1      ,") 
			.append(" sum(route_stores_area50)    as route_stores_area50     ,") 
			.append(" sum(route_stores_area100)   as route_stores_area100    ,") 
			.append(" sum(route_stores_area200)   as route_stores_area200    ,") 
			.append(" sum(route_stores_area300)   as route_stores_area300    ,") 
			.append(" sum(route_stores_area500)   as route_stores_area500    ,") 
			.append(" sum(route_stores_area_sum)  as route_stores_area_sum  , ")
			.append(" sum(route_stores_area_p)    as route_stores_area_p     ,") 
			.append(" case when sum(stores_area_sum)<>0 then  concat(round(sum(route_stores_area_sum)/sum(stores_area_sum),2)*100.00,'%') ")
			.append("  else null end             as route_stores_rate      , ")
			.append(" sum(sd_area1)               as sd_area1                ,") 
			.append(" sum(sd_area100)             as sd_area100              ,") 
			.append(" sum(sd_area200)             as sd_area200              ,") 
			.append(" sum(sd_area500)             as sd_area500              ,") 
			.append(" sum(sd_area1000)            as sd_area1000             ,") 
			.append(" sum(sd_area_sum)            as sd_area_sum             ,") 
			.append("  case when sum(stores_area_sum)<>0 then  concat(round(sum(sd_area_sum)/sum(stores_area_sum),2)*100.00,'%') " )  
			.append("  else null end             as sd_area_rate            ,") 
			.append(" sum(division_stores_all)    as division_stores_all"      )
			.append(" from ystest_database.T_STA6_TERMINAL_REP " )
			.append(" where yearmonth='201612'" )
			.append(" and cha_bra_com_typeno=3" )
			.append(" and channel_ID  in('CD8','CCB')")
			.append(" and company_id='C21' "  )
			.append(" group by cha_bra_com_typename")

			.append(" union all")
			.append(" select cha_bra_com_typename ,")
			.append(" branch_name as  channel_name            ,")
			.append(" branch_name  as area_name                 ,")
			.append(" stores_records        as stores_records        , ")
			.append(" stores_area1          as stores_area1           ,") 
			.append(" stores_area50         as stores_area50          ,") 
			.append(" stores_area100        as stores_area100         ,") 
			.append(" stores_area200        as stores_area200         ,") 
			.append(" stores_area300        as stores_area300         ,") 
			.append(" stores_area500        as stores_area500         ,") 
			.append(" stores_area_sum       as stores_area_sum        ,") 
			.append(" stores_area_p         as stores_area_p         , ")
			.append(" route_stores_area1    as route_stores_area1     ,") 
			.append(" route_stores_area50   as route_stores_area50    ,") 
			.append(" route_stores_area100  as route_stores_area100   ,") 
			.append(" route_stores_area200  as route_stores_area200   ,") 
			.append(" route_stores_area300  as route_stores_area300   ,") 
			.append(" route_stores_area500  as route_stores_area500   ,") 
			.append(" route_stores_area_sum as route_stores_area_sum  ,") 
			.append(" route_stores_area_p   as route_stores_area_p    ,") 
			.append(" case when route_stores_rate    is null then null else   concat(route_stores_rate*100.00,'%') end  as route_stores_rate  ,") 
			.append(" sd_area1       as sd_area1             ,") 
			.append("sd_area100     as sd_area100           ,") 
			.append("sd_area200     as sd_area200          , ")
			.append("sd_area500     as sd_area500           ,") 
			.append("sd_area1000    as sd_area1000          ,") 
			.append("sd_area_sum    as sd_area_sum          ,") 
			.append(" case when sd_area_rate     is null then null else  concat(sd_area_rate*100.00,'%') end as sd_area_rate     ,") 
			.append(" division_stores_all as division_stores_all"      )
			.append(" from ystest_database.T_STA6_TERMINAL_REP " )
			.append(" where yearmonth='201612' ")
			.append(" and cha_bra_com_typeno=2" )
			.append(" and company_id='C21'"  )

			.append(" union all")
			.append(" select '分公司合计' as  cha_bra_com_typename ,")
			.append(" '分公司合计' as  channel_name          ,")
			.append(" '分公司合计' as  area_name             ,")
			.append(" sum(stores_records)         as stores_records         , ")
			.append(" sum(stores_area1)           as stores_area1            ,") 
			.append(" sum(stores_area50)          as stores_area50           ,") 
			.append(" sum(stores_area100)         as stores_area100          ,") 
			.append(" sum(stores_area200)         as stores_area200          ,") 
			.append(" sum(stores_area300)         as stores_area300          ,") 
			.append(" sum(stores_area500)         as stores_area500          ,") 
			.append(" sum(stores_area_sum)        as stores_area_sum         ,") 
			.append(" sum(stores_area_p)          as stores_area_p           ,") 
			.append(" sum(route_stores_area1)     as route_stores_area1      ,") 
			.append(" sum(route_stores_area50)    as route_stores_area50     ,") 
			.append(" sum(route_stores_area100)   as route_stores_area100    ,") 
			.append(" sum(route_stores_area200)   as route_stores_area200    ,") 
			.append(" sum(route_stores_area300)   as route_stores_area300    ,") 
			.append(" sum(route_stores_area500)   as route_stores_area500    ,") 
			.append(" sum(route_stores_area_sum)  as route_stores_area_sum   ,") 
			.append(" sum(route_stores_area_p)    as route_stores_area_p     ,") 
			.append(" case when sum(stores_area_sum)<>0 then  concat(round(sum(route_stores_area_sum)/sum(stores_area_sum),2)*100.00,'%')" )
			.append("  else null end             as route_stores_rate       ,") 
			.append(" sum(sd_area1)               as sd_area1                ,") 
			.append(" sum(sd_area100)             as sd_area100             , ")
			.append(" sum(sd_area200)             as sd_area200              ,") 
			.append(" sum(sd_area500)             as sd_area500              ,") 
			.append(" sum(sd_area1000)            as sd_area1000            , ")
			.append(" sum(sd_area_sum)            as sd_area_sum             ,") 
			.append(" case when sum(stores_area_sum)<>0 then concat(round(sum(sd_area_sum)/sum(stores_area_sum),2)*100.00,'%')" )
			.append("  else null end             as sd_area_rate            ,")  
			.append(" sum(division_stores_all)    as division_stores_all " )     
			.append(" from ystest_database.T_STA6_TERMINAL_REP"  ) 
			.append(" where yearmonth='201612'")  
			.append(" and cha_bra_com_typeno=2 ") 
			.append(" and company_id='C21' ");
			
			
			//查询hive
			ResultSet r = stmt.executeQuery(sbf.toString());
			while (r.next()) {
				CompanyInfoPojo companyInfoPojo = new CompanyInfoPojo();
				companyInfoPojo.setResultStr1(r.getString(1));
				companyInfoPojo.setResultStr2(r.getString(2));
				companyInfoPojo.setResultStr3(r.getString(3));
				companyInfoPojo.setResultStr4(r.getString(4));
				companyInfoPojo.setResultStr5(r.getString(5));
				companyInfoPojo.setResultStr6(r.getString(6));
				companyInfoPojo.setResultStr7(r.getString(7));
				companyInfoPojo.setResultStr8(r.getString(8));
				companyInfoPojo.setResultStr9(r.getString(9));
				companyInfoPojo.setResultStr10(r.getString(10));
				companyInfoPojo.setResultStr11(r.getString(11));
				companyInfoPojo.setResultStr12(r.getString(12));
				companyInfoPojo.setResultStr13(r.getString(13));
				companyInfoPojo.setResultStr14(r.getString(14));
				companyInfoPojo.setResultStr15(r.getString(15));
				companyInfoPojo.setResultStr16(r.getString(16));
				companyInfoPojo.setResultStr17(r.getString(17));
				companyInfoPojo.setResultStr18(r.getString(18));
				companyInfoPojo.setResultStr19(r.getString(19));
				companyInfoPojo.setResultStr20(r.getString(20));
				companyInfoPojo.setResultStr21(r.getString(21));
				companyInfoPojo.setResultStr22(r.getString(22));
				companyInfoPojo.setResultStr23(r.getString(23));
				companyInfoPojo.setResultStr24(r.getString(24));
				companyInfoPojo.setResultStr25(r.getString(25));
				companyInfoPojo.setResultStr26(r.getString(26));
				companyInfoPojo.setResultStr27(r.getString(27));
				companyInfoPojo.setResultStr28(r.getString(28));
				companyInfoPojo.setResultStr29(r.getString(29));
				
				companyInfoPojoList.add(companyInfoPojo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return companyInfoPojoList;
	}
}

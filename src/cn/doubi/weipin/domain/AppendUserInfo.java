package cn.doubi.weipin.domain;

import java.io.Serializable;
/**
 * 求职用户附加信息
 * 信息说明:
	   	用户编号
		学历
		毕业院校
		邮箱
		婚姻状态 
		户口 
		出生年月
		身高
		国家
		民族
		求职地区
		其他描述
		工作类型
		是否可调整
		创建时间
		修改时间
		说明: user_edu学历(0-小学 1-初中 2-高中/中专 3-专科 4-本科 5-硕士研究生 6-博士研究生)
　　　user_worktype工作类型(0-临时,1-长期);
	 info_status是够可调整(0-不可调整,1-可以调整)
 */
public class AppendUserInfo implements Serializable
{

	private static final long serialVersionUID = 1L;
	private String user_id;
	private String user_edu;
	private String user_school;
	private String user_email;
	private String user_marry;
	private String user_population;
	private String user_birth;
	private String user_stature;
	private String user_country;
	private String user_race;
	private String user_workarea;
	private String user_description;
	private String user_worktype;
	private String info_status;
	private String create_time;
	private String modify_time;
	public String getUser_id()
	{
		return user_id;
	}
	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}
	public String getUser_edu()
	{
		return user_edu;
	}
	public void setUser_edu(String user_edu)
	{
		this.user_edu = user_edu;
	}
	public String getUser_school()
	{
		return user_school;
	}
	public void setUser_school(String user_school)
	{
		this.user_school = user_school;
	}
	public String getUser_email()
	{
		return user_email;
	}
	public void setUser_email(String user_email)
	{
		this.user_email = user_email;
	}
	public String getUser_marry()
	{
		return user_marry;
	}
	public void setUser_marry(String user_marry)
	{
		this.user_marry = user_marry;
	}
	public String getUser_population()
	{
		return user_population;
	}
	public void setUser_population(String user_population)
	{
		this.user_population = user_population;
	}
	public String getUser_birth()
	{
		return user_birth;
	}
	public void setUser_birth(String user_birth)
	{
		this.user_birth = user_birth;
	}
	public String getUser_stature()
	{
		return user_stature;
	}
	public void setUser_stature(String user_stature)
	{
		this.user_stature = user_stature;
	}
	public String getUser_country()
	{
		return user_country;
	}
	public void setUser_country(String user_country)
	{
		this.user_country = user_country;
	}
	public String getUser_race()
	{
		return user_race;
	}
	public void setUser_race(String user_race)
	{
		this.user_race = user_race;
	}
	public String getUser_workarea()
	{
		return user_workarea;
	}
	public void setUser_workarea(String user_workarea)
	{
		this.user_workarea = user_workarea;
	}
	public String getUser_description()
	{
		return user_description;
	}
	public void setUser_description(String user_description)
	{
		this.user_description = user_description;
	}
	public String getUser_worktype()
	{
		return user_worktype;
	}
	public void setUser_worktype(String user_worktype)
	{
		this.user_worktype = user_worktype;
	}
	public String getInfo_status()
	{
		return info_status;
	}
	public void setInfo_status(String info_status)
	{
		this.info_status = info_status;
	}
	public String getCreate_time()
	{
		return create_time;
	}
	public void setCreate_time(String create_time)
	{
		this.create_time = create_time;
	}
	public String getModify_time()
	{
		return modify_time;
	}
	public void setModify_time(String modify_time)
	{
		this.modify_time = modify_time;
	}
	@Override
	public String toString()
	{
		return "AppendUserInfo [user_id=" + user_id + ", user_edu=" + user_edu
				+ ", user_school=" + user_school + ", user_email=" + user_email
				+ ", user_marry=" + user_marry + ", user_population="
				+ user_population + ", user_birth=" + user_birth
				+ ", user_stature=" + user_stature + ", user_country="
				+ user_country + ", user_race=" + user_race
				+ ", user_workarea=" + user_workarea + ", user_description="
				+ user_description + ", user_worktype=" + user_worktype
				+ ", info_status=" + info_status + ", create_time="
				+ create_time + ", modify_time=" + modify_time + "]";
	}
	

}


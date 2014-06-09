package cn.doubi.weipin.domain;

import java.io.Serializable;
/**
 * 用户评分
 * 信息说明：
	   	用户编号
		电话面试分数
		实体面试分数
		首月评级分数
 */
public class UserScore implements Serializable
{

	private static final long serialVersionUID = 1L;
	private float scoreTel;
	private float scoreOral;
	private float scoreWork;
	public float getScore_tel()
	{
		return scoreTel;
	}
	public void setScore_tel(float score_tel)
	{
		this.scoreTel = score_tel;
	}
	public float getScore_oral()
	{
		return scoreOral;
	}
	public void setScore_oral(float score_oral)
	{
		this.scoreOral = score_oral;
	}
	public float getScore_work()
	{
		return scoreWork;
	}
	public void setScore_work(float score_work)
	{
		this.scoreWork = score_work;
	}
	@Override
	public String toString()
	{
		return "UserScore [user_id=score_tel=" + scoreTel
				+ ", score_oral=" + scoreOral + ", score_work=" + scoreWork
				+ "]";
	}
	
}


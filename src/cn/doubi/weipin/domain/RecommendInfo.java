package cn.doubi.weipin.domain;

import java.io.Serializable;
/**
 * 推荐信息
 * 信息说明:
	   	推荐人编号
		被推荐人编号
		创建时间
		修改时间
 */
public class RecommendInfo implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private String recom_id;
	private String recomed_id;
	private String create_time;
	private String modify_time;
	public String getRecom_id()
	{
		return recom_id;
	}
	public void setRecom_id(String recom_id)
	{
		this.recom_id = recom_id;
	}
	public String getRecomed_id()
	{
		return recomed_id;
	}
	public void setRecomed_id(String recomed_id)
	{
		this.recomed_id = recomed_id;
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
		return "RecommendInfo [recom_id=" + recom_id + ", recomed_id="
				+ recomed_id + ", create_time=" + create_time
				+ ", modify_time=" + modify_time + "]";
	}

}


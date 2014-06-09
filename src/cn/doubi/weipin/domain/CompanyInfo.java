package cn.doubi.weipin.domain;

import java.io.Serializable;
/**
 * 企业信息
 * 信息说明：
	   	企业编号
		企业名称
		企业性质
		所属行业
		所在区域
		详细地址
		联系人
		联系电话
		联系邮箱
		创建时间
		修改时间
 */
public class CompanyInfo implements Serializable
{

	private static final long serialVersionUID = 1L;
	private String com_id;
	private String com_name;
	private String com_type;
	private String com_trade;
	private String com_area;
	private String com_address;
	private String com_linkman;
	private String com_tel;
	private String com_email;
	private String create_time;
	private String modify_time;
	public String getCom_id()
	{
		return com_id;
	}
	public void setCom_id(String com_id)
	{
		this.com_id = com_id;
	}
	public String getCom_name()
	{
		return com_name;
	}
	public void setCom_name(String com_name)
	{
		this.com_name = com_name;
	}
	public String getCom_type()
	{
		return com_type;
	}
	public void setCom_type(String com_type)
	{
		this.com_type = com_type;
	}
	public String getCom_trade()
	{
		return com_trade;
	}
	public void setCom_trade(String com_trade)
	{
		this.com_trade = com_trade;
	}
	public String getCom_area()
	{
		return com_area;
	}
	public void setCom_area(String com_area)
	{
		this.com_area = com_area;
	}
	public String getCom_address()
	{
		return com_address;
	}
	public void setCom_address(String com_address)
	{
		this.com_address = com_address;
	}
	public String getCom_linkman()
	{
		return com_linkman;
	}
	public void setCom_linkman(String com_linkman)
	{
		this.com_linkman = com_linkman;
	}
	public String getCom_tel()
	{
		return com_tel;
	}
	public void setCom_tel(String com_tel)
	{
		this.com_tel = com_tel;
	}
	public String getCom_email()
	{
		return com_email;
	}
	public void setCom_email(String com_email)
	{
		this.com_email = com_email;
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
		return "CompanyInfo [com_id=" + com_id + ", com_name=" + com_name
				+ ", com_type=" + com_type + ", com_trade=" + com_trade
				+ ", com_area=" + com_area + ", com_address=" + com_address
				+ ", com_linkman=" + com_linkman + ", com_tel=" + com_tel
				+ ", com_email=" + com_email + ", create_time=" + create_time
				+ ", modify_time=" + modify_time + "]";
	}

	
}


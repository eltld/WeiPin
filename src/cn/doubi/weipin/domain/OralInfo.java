package cn.doubi.weipin.domain;

import java.io.Serializable;

/**
 * 面试信息
 * 信息说明
	   	面试信息id
		用户编号
		招聘信息编号
		面试结果
		创建时间
		修改时间
		说明: oral_rst面试结果(0-未通过,1-通过,2-未参加面试,3-取消面试)
 */
public class OralInfo implements Serializable
{

	private static final long serialVersionUID = 1L;

	private String oralId;//面试ID
	private String userId;
	private String hireId;//
	private String oralRst;//面试状态
	private String createTime;//创建时间
	private String modifyTime;//修改时间
	private String companyName;   //公司名称
	private String companyAddress;//公司地址
	private String hireTitle;//
	private String linkTel;//联系电话
	private String salay; //薪资
	private String oralTime;//面试时间
	private String workType;//工作类型
	private String linkMan;//联系人
	private String offerTime;//入职时间
	public String getOral_id()
	{
		return oralId;
	}
	public void setOral_id(String oral_id)
	{
		this.oralId = oral_id;
	}
	public String getUser_id()
	{
		return userId;
	}
	public void setUser_id(String user_id)
	{
		this.userId = user_id;
	}
	public String getHire_id()
	{
		return hireId;
	}
	public void setHire_id(String hire_id)
	{
		this.hireId = hire_id;
	}
	public String getOral_rst()
	{
		return oralRst;
	}
	public void setOral_rst(String oral_rst)
	{
		this.oralRst = oral_rst;
	}
	public String getCreate_time()
	{
		return createTime;
	}
	public void setCreate_time(String create_time)
	{
		this.createTime = create_time;
	}
	public String getModify_time()
	{
		return modifyTime;
	}
	public void setModify_time(String modify_time)
	{
		this.modifyTime = modify_time;
	}
	@Override
	public String toString()
	{
		return "OralInfo [oral_id=" + oralId + ", user_id=" + userId
				+ ", hire_id=" + hireId + ", oral_rst=" + oralRst
				+ ", create_time=" + createTime + ", modify_time="
				+ modifyTime + "]";
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getHireTitle() {
		return hireTitle;
	}
	public void setHireTitle(String hireTitle) {
		this.hireTitle = hireTitle;
	}
	public String getLinkTel() {
		return linkTel;
	}
	public void setLinkTel(String linkTel) {
		this.linkTel = linkTel;
	}
	public String getOralRst() {
		return oralRst;
	}
	public void setOralRst(String oralRst) {
		this.oralRst = oralRst;
	}
	public String getSalay() {
		return salay;
	}
	public void setSalay(String salay) {
		this.salay = salay;
	}
	public String getOfferTime() {
		return offerTime;
	}
	public void setOfferTime(String offerTime) {
		this.offerTime = offerTime;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	public String getOralTime() {
		return oralTime;
	}
	public void setOralTime(String oralTime) {
		this.oralTime = oralTime;
	}
	
}


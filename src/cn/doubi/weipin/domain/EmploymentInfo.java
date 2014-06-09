package cn.doubi.weipin.domain;

import java.io.Serializable;
/**
 * 招聘信息
 * 信息说明
	  	招聘信息编号
		企业编号
		招聘人数
		工作性质
		用人地区
		用人岗位
		工作年限
		联系人
		联系电话
		联系邮箱
		学历
		薪酬
		其他
		创建时间
		修改时间
		说明: hire_type工作性质(0-临时,1-长期)
　　　	hire_edu学历(0-小学 1-初中 2-高中/中专 3-专科 4-本科 5-硕士研究生 6-博士研究生
 */
public class EmploymentInfo implements Serializable
{

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "EmploymentInfo [hire_id=" + hire_id + ", com_id=" + com_id
				+ ", hire_num=" + hire_num + ", hire_type=" + hire_type
				+ ", hire_area=" + hire_area + ", hire_work=" + hire_work
				+ ", hire_years=" + hire_years + ", com_linkman=" + com_linkman
				+ ", com_tel=" + com_tel + ", com_emial=" + com_emial
				+ ", com_edu=" + com_edu + ", com_pay=" + com_pay
				+ ", com_other=" + com_other + ", ccreate_time=" + ccreate_time
				+ ", modify_time=" + modify_time + ", companyName="
				+ companyName + ", companyaddress=" + companyAddress
				+ ", hireTitle=" + hireTitle + ", ismailing=" + ismailing
				+ ", isaudition=" + isaudition + ", isacross=" + isacross + "]";
	}
	private String hire_id;
	private String com_id;
	private String hire_num;
	private String hire_type;
	private String hire_area;
	private String hire_work;
	private String hire_years;
	private String com_linkman;
	private String com_tel;
	private String com_emial;
	private String com_edu;
	private String com_pay;
	private String com_other;
	private String ccreate_time;
	private String modify_time;
	private String companyName;   
	private String companyAddress;
	private String hireTitle;
	private int ismailing;
	private int isaudition;
	private int isacross;
	private String oralRst;
	public String getHire_id()
	{
		return hire_id;
	}
	public void setHire_id(String hire_id)
	{
		this.hire_id = hire_id;
	}
	public String getHire_num()
	{
		return hire_num;
	}
	public void setHire_num(String hire_num)
	{
		this.hire_num = hire_num;
	}
	public String getHire_type()
	{
		return hire_type;
	}
	public void setHire_type(String hire_type)
	{
		this.hire_type = hire_type;
	}
	public String getHire_area()
	{
		return hire_area;
	}
	public void setHire_area(String hire_area)
	{
		this.hire_area = hire_area;
	}
	public String getHire_work()
	{
		return hire_work;
	}
	public void setHire_work(String hire_work)
	{
		this.hire_work = hire_work;
	}
	public String getHire_years()
	{
		return hire_years;
	}
	public void setHire_years(String hire_years)
	{
		this.hire_years = hire_years;
	}
	public String getCom_edu()
	{
		return com_edu;
	}
	public void setCom_edu(String com_edu)
	{
		this.com_edu = com_edu;
	}
	public String getCom_pay()
	{
		return com_pay;
	}
	public void setCom_pay(String com_pay)
	{
		this.com_pay = com_pay;
	}
	public String getCom_other()
	{
		return com_other;
	}
	public void setCom_other(String com_other)
	{
		this.com_other = com_other;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getHireTitle() {
		return hireTitle;
	}
	public void setHireTitle(String hireTitle) {
		this.hireTitle = hireTitle;
	}
	public int getIsmailing() {
		return ismailing;
	}
	public void setIsmailing(int ismailing) {
		this.ismailing = ismailing;
	}
	public int getIsaudition() {
		return isaudition;
	}
	public void setIsaudition(int isaudition) {
		this.isaudition = isaudition;
	}
	public int getIsacross() {
		return isacross;
	}
	public void setIsacross(int isacross) {
		this.isacross = isacross;
	}

	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	public String getCom_linkman() {
		return com_linkman;
	}
	public void setCom_linkman(String com_linkman) {
		this.com_linkman = com_linkman;
	}
	public String getCom_tel() {
		return com_tel;
	}
	public void setCom_tel(String com_tel) {
		this.com_tel = com_tel;
	}
	public String getCom_emial() {
		return com_emial;
	}
	public void setCom_emial(String com_emial) {
		this.com_emial = com_emial;
	}
	public String getCcreate_time() {
		return ccreate_time;
	}
	public void setCcreate_time(String ccreate_time) {
		this.ccreate_time = ccreate_time;
	}
	public String getModify_time() {
		return modify_time;
	}
	public void setModify_time(String modify_time) {
		this.modify_time = modify_time;
	}
	public String getCompanyaddress() {
		return companyAddress;
	}
	public void setCompanyaddress(String companyaddress) {
		this.companyAddress = companyaddress;
	}
	public String getOralRst() {
		return oralRst;
	}
	public void setOralRst(String oralRst) {
		this.oralRst = oralRst;
	}	
}


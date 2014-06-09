package cn.doubi.weipin.domain;
/**
 * 版本更新的数据对象
 * 更新标题
 * 版本号
 * 下载地址
 */
public class UpdateObj
{

	private String updateTitle;
	private int versionCode;
	private String downloadUrl;
	public String getUpdateTitle()
	{
		return updateTitle;
	}
	public void setUpdateTitle(String updateTitle)
	{
		this.updateTitle = updateTitle;
	}
	public int getVersonCode()
	{
		return versionCode;
	}
	public void setVersonCode(int versonCode)
	{
		this.versionCode = versonCode;
	}
	public String getDownloadUrl()
	{
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl)
	{
		this.downloadUrl = downloadUrl;
	}
}


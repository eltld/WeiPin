package cn.doubi.weipin.sqlite;

import android.content.Context;

/**
 * 数据库的基本功能实现
 */
public class SqliteImplements implements SqliteInterface
{

	private DouBiSqlite mDouBiSqlite;
	public DouBiSqlite getDb(){
		return mDouBiSqlite;
	}
	/**
	 * 通过BaseUserInfo创建一个默认的数据库
	 * 
	 */
	public SqliteImplements(Context context)
	{
		mDouBiSqlite = new DouBiSqlite(context);
		mDouBiSqlite.getWritableDatabase();
	}
	/**
	 * 通过一个对象来创建一张表
	 */
	@Override
	public void createTableWidthObj(Object obj ,String tableName)
	{
		mDouBiSqlite.createTableWidthObj(obj, tableName);
	}

}


package cn.doubi.weipin.sqlite;

import java.lang.reflect.Field;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import cn.doubi.weipin.domain.BaseUserInfo;

/**
 * 创建数据库
 */
public class DouBiSqlite extends SQLiteOpenHelper
{

	public static final String SQLITE_NAME = "weipindatabase.db";
	public static final String BASE_USERINFO_TABLENAME = "baseuserinfo";
	private static int mVersonCode = 1;
	public DouBiSqlite(Context context)
	{
		super(context, SQLITE_NAME, null, mVersonCode);
	}

	
	public void createTableWidthObj(Object obj , String tableName) {
		if(obj == null) {
			throw new RuntimeException("obj is null from createTableWidthObj(...)");
		}
		createTableWidthNameAndObj(getWritableDatabase(), obj, tableName);
	}
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		createTableWidthNameAndObj(db,new BaseUserInfo(),BASE_USERINFO_TABLENAME);
	}


	private void createTableWidthNameAndObj(SQLiteDatabase db , Object obj , String tableName)
	{
		
		String sql = "";
		try
		{
			sql = "CREATE TABLE IF NOT EXISTS "+tableName;
			StringBuffer sb = new StringBuffer();
			String l = "(";
			String r = ")";
			String d = ",";
			String _id = " _id integer primary key, ";
			String varchar = "VARCHAR(200)"+d;
			//得到对象中的所有字段名称
			Field[] fields =  obj.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				String fieldName = field.getName()+" ";//得到要构造的字段名称
				sb.append(fieldName+varchar);
			}
			//得到构造好的 字段字符串
			//去掉结尾的","得到 "name VARCHAR(200),age VARCHAR(200)"
			sb.deleteCharAt(sb.lastIndexOf(","));
			sql = sql + l + _id + sb.toString() + r; //得到构造好的sql语句
			//创建这个表
			db.execSQL(sql);
		}
		catch (SecurityException e)
		{
			
			e.printStackTrace();
		}
		catch (IllegalArgumentException e)
		{
			
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{

	}

}


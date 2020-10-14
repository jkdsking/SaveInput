package com.jkds.saveinput;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 王金珂
 * @date 2020/7/01
 */
public class SPUtils {

    private static final String SP_NAME = "share_data";
    private volatile static SharedPreferences mSharedPreferences;

    private SPUtils() {
    }

    public static SharedPreferences getInstance(Context context) {
        if (mSharedPreferences == null) {
            synchronized (SPUtils.class) {
                if (mSharedPreferences == null) {
                    mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
                }
            }
        }
        return mSharedPreferences;
    }

    /**
     * 保存List
     *
     * @param tag
     * @param datalist
     */
    public static <T> void setDataListpz(String tag, List<T> datalist, Context context) {
        if (null == datalist || datalist.size() <= 0) {
            return;
        }
        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
        SharedPreferences sp = context.getSharedPreferences("buyuser", Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.clear();
        editor.putString(tag, strJson);
        editor.commit();

    }

    /**
     * 获取List
     *
     * @param tag
     * @return
     */
    public static <T> List<T> getDataListpz(String tag, Context context) {
        List<T> datalist = new ArrayList<T>();
        SharedPreferences sp = context.getSharedPreferences("buyuser", Context.MODE_PRIVATE);
        String strJson = sp.getString(tag, null);
        if (null == strJson) {
            return datalist;
        }
        Gson gson = new Gson();
        datalist = gson.fromJson(strJson, new TypeToken<List<T>>() {
        }.getType());
        return datalist;
    }


    /**
     * 保存数据 根据数据类型自动拆箱
     *
     * @param key    键名
     * @param values Object类型数据 但仅限于(String/int/float/boolean/long)
     */

    public static void save(String key, Object values) {
        Editor editor = mSharedPreferences.edit();
        if (values instanceof String) {
            editor.putString(key, (String) values);

        } else if (values instanceof Integer) {
            editor.putInt(key, (Integer) values);

        } else if (values instanceof Long) {
            editor.putLong(key, (Long) values);

        } else if (values instanceof Boolean) {
            editor.putBoolean(key, (Boolean) values);

        } else if (values instanceof Float) {
            editor.putFloat(key, (Float) values);
        }
        editor.commit();
    }

    /**
     * 获取Object类型数据 根据接收类型自动拆箱
     *
     * @param key          键名
     * @param defaultValue 根据key获取不到是默认值仅限于(String/int/float/boolean/long)
     */
    public static Object get(String key, Object defaultValue) {
        SharedPreferences sp = mSharedPreferences;
        if (defaultValue instanceof String) {
            return sp.getString(key, (String) defaultValue);
        } else if (defaultValue instanceof Integer) {
            return sp.getInt(key, (Integer) defaultValue);
        } else if (defaultValue instanceof Long) {
            return sp.getLong(key, (Long) defaultValue);
        } else if (defaultValue instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultValue);
        } else if (defaultValue instanceof Float) {
            return sp.getFloat(key, (Float) defaultValue);
        }

        return null;
    }


    /**
     * 清除保存的数据
     */
    public static void delete() {
        Editor editor = mSharedPreferences.edit();
        editor.clear().commit();
    }

    /**
     * 根据key删除value
     */
    public static void deleteBykey(String key) {
        Editor editor = mSharedPreferences.edit();
        editor.remove(key).commit();
    }


}

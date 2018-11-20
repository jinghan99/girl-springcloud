package com.yf.utils.entiy;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 查询条件
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2017年8月11日 下午12:13:58
 */
public class Query extends HashMap<String, Object> {
	
	private static final long serialVersionUID = 1L;
	
	public Query() {
		super();
	}

	public Query(Map<String, Object> params){
        this.putAll(params);
    }
    public Query(HttpServletRequest request){
        Map properties = request.getParameterMap();
        HashMap returnMap = new HashMap();
        Iterator entries = properties.entrySet().iterator();
        Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if(null == valueObj){
                value = "";
            }else if(valueObj instanceof String[]){
                String[] values = (String[])valueObj;
                for(int i=0;i<values.length;i++){
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length()-1);
            }else{
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        this.putAll(returnMap);
    }
	public Double getAsDouble(String name) {
        Object value = this.get(name);
        if (value != null) {
            return Double.valueOf(value.toString());
        }
        return null;
    }

    public Float getAsFloat(String name){
        Object value = this.get(name);
        if (value != null) {
            return Float.valueOf(value.toString());
        }
        return null;
    }

    public String getAsString(String name) {
        Object value = this.get(name);
        if (value != null) {
            return this.get(name).toString();
        }
        return null;
    }

    public Long getAsLong(String name) {
        Object value = this.get(name);
        if (value != null) {
            return Long.valueOf(value.toString());
        }
        return null;
    }

    public Integer getAsInt(String name) {
        Object value = this.get(name);
        if (value != null) {
            return Integer.valueOf(value.toString());
        }
        return null;
    }

    public Boolean getAsBoolean(String name) {
        Object value = this.get(name);
        if (value != null) {
            return Boolean.valueOf(value.toString());
        }
        return null;
    }

    /**
     * 格式value :2017-08-12
     * @param name
     * @return
     */
    public java.util.Date getAsDateDay(String name){
        Object value = this.get(name);
        if (value != null) {
            return Date.valueOf(value.toString());
        }
        return null;
    }

    /**
     * 格式value :2017-08-12 10:32:21
     * @param name
     * @return
     */
    public  java.util.Date getAsDate(String name) {
    	Object value = this.get(name);
        if (value != null) {
            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                return formatter.parse(value.toString());
            } catch (ParseException e) {
                return null;
            }
        }
        return null;
    }

    public Object getObj(String name) {
        return this.get(name);
    }

    public static void main(String[] args) throws ParseException {
        Query query = new Query();
        query.put("data","2017-08-07 18:54:32");
        String str = query.getAsString("123");
        System.out.println(str);
    }
}

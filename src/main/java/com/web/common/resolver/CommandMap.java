package com.web.common.resolver;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * request�� ����ִ� �Ķ���͸� Map�� ����ִ� ������ �ϴ� Ŭ������.
public ModelAndView main(Map<String,Object> commandMap) throws Exception{} ��� ������ �߾���. 
���⼭ Map<String,Object> commandMap�� ����ڰ� �Ѱ��� �Ķ���Ͱ� ����Ǿ� �ִ�. (�̴� ������ �׷��� �ϰڴٴ� �ǹ��̰�, ����� ������� �ʴ´�.)
�׷��� ���⼭ ������ HandlerMethodArgumentResolver�� ��Ʈ�ѷ��� �Ķ���Ͱ� Map �����̸� �������� �ʴ´�. 
������ �����ϸ�, ������ 3.1���� HandlerMethodArgumentResolver�� �̿��Ͽ� �׷��� ����� �������, ��Ʈ�ѷ��� �Ķ���Ͱ� Map �����̸� �츮�� ������ Ŭ������ �ƴ�, ���������� �⺻������ ������ ArgumentResolver�� ��ġ�� �ȴ�.
�׻� �׷��� �����ϴ°��� �ƴϰ�, �������� <mvc:annotation-driven/>�� �����ϰ� �Ǹ� ������ �̾߱��Ѱ�ó�� �����ϰ� �ȴ�. (������ ó���� �̰��� ���� ��¥ � ������ �����ߴ�.)
���� <mvc:annotation-driven/>�� �����Ϸ��� Map�� �״�� ����� �� ����, �������� ������ ������ ����. �׷����� ������ �������� �����߿��� <mvc:annotation-driven/>�� �����ؾ� �ϴ� ��찡 �ֱ⶧����, ���⼭�� Map�� ����� CommandMap�� �ۼ��Ѵ�.
 * */

public class CommandMap {
	 
    Map<String,Object> map = new HashMap<String,Object>();
     
    public void put(String key, Object value) {
    	map.put(key, value);
    }
     
    public Object get(String key) {
        return map.get(key);
    }
     
    public boolean isEmpty() {
        return map.isEmpty();
    }
     
    public Map<String, Object> getMap(){
        return map;
    }
     
    public void clear() {
    	map.clear();
    }
     
    public boolean containsKey(String key) {
        return map.containsKey(key);
    }
     
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }
     
    public Object remove(String key) {
        return map.remove(key);
    }
     
    public Set<Entry<String, Object>> entrySet(){
        return map.entrySet();
    }
     
    public Set<String> keySet(){
        return map.keySet();
    }
     
    public void putAll(Map<? extends String, ? extends Object> m) {
    	map.putAll(m);
    }
}

package com.web.common.resolver;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * request에 담겨있는 파라미터를 Map에 담아주는 역할을 하는 클래스다.
public ModelAndView main(Map<String,Object> commandMap) throws Exception{} 라고 선언을 했었다. 
여기서 Map<String,Object> commandMap에 사용자가 넘겨준 파라미터가 저장되어 있다. (이는 앞으로 그렇게 하겠다는 의미이고, 현재는 저장되지 않는다.)
그런데 여기서 문제는 HandlerMethodArgumentResolver는 컨트롤러의 파라미터가 Map 형식이면 동작하지 않는다. 
엄밀히 말을하면, 스프링 3.1에서 HandlerMethodArgumentResolver를 이용하여 그러한 기능을 만들더라도, 컨트롤러의 파라미터가 Map 형식이면 우리가 설정한 클래스가 아닌, 스프링에서 기본적으로 설정된 ArgumentResolver를 거치게 된다.
항상 그렇게 동작하는것은 아니고, 스프링의 <mvc:annotation-driven/>을 선언하게 되면 위에서 이야기한것처럼 동작하게 된다. (본인은 처음에 이것을 몰라서 진짜 몇날 몇일을 삽질했다.)
따라서 <mvc:annotation-driven/>을 선언하려면 Map을 그대로 사용할 수 없고, 선언하지 않으면 문제는 없다. 그렇지만 앞으로 포스팅할 내용중에는 <mvc:annotation-driven/>을 선언해야 하는 경우가 있기때문에, 여기서는 Map을 대신할 CommandMap을 작성한다.
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

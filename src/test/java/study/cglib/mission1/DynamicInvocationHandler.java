package study.cglib.mission1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class DynamicInvocationHandler implements InvocationHandler {

    private Object target;
    private final Map<String, Method> methods = new HashMap<>();

    public DynamicInvocationHandler(Object target) {
        this.target = target;
        for (Method method : target.getClass().getDeclaredMethods()) {
            methods.put(method.getName(), method);
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        var targetMethod = methods.get(method.getName());

        var result = targetMethod.invoke(target, args);
        return ((String)result).toUpperCase();
    }
}

package by.dm13y.core.cglib;

import by.dm13y.core.cglib.service.ResourceService;
import by.dm13y.core.cglib.service.impl.ResourceServiceImpl;
import lombok.val;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class ProxyExecutor {

    @SuppressWarnings("")
    public static void main(String[] args) {
        ResourceService resourceService = new ResourceServiceImpl(List.of("Resource1", "Resource2"));
        ClassLoader loader = ProxyExecutor.class.getClassLoader();

        System.out.println("Create an interface proxy");
        System.out.println("=====");

        val resourceServiceProxy = (ResourceService) Proxy.newProxyInstance(loader, new Class<?>[]{ResourceService.class}, new ProxyInvocationHandler(resourceService));
        System.out.println("Service name: " + resourceServiceProxy.getServiceName());
        System.out.println("Type: " + resourceServiceProxy.getType());
        System.out.println("Count items: " + resourceServiceProxy.getCountItems());

        System.out.println("Create a class proxy");
        System.out.println("=====");

        try {
            Proxy.newProxyInstance(loader, new Class<?>[]{ResourceServiceImpl.class}, new ProxyInvocationHandler(resourceService));
        } catch (Exception e) {
            System.out.println("Ups proxy work only with interfaces");
        }
    }

    static class ProxyInvocationHandler implements InvocationHandler {
        private final ResourceService resourceService;

        public ProxyInvocationHandler(ResourceService resourceService) {
            this.resourceService = resourceService;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return method.invoke(resourceService, args);
        }
    }
}

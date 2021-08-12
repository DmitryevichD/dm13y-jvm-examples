package by.dm13y.core.cglib;

import by.dm13y.core.cglib.service.ResourceService;
import by.dm13y.core.cglib.service.impl.ResourceServiceImpl;
import lombok.val;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * https://stackoverflow.com/questions/54628483/caching-annotation-on-interface-methods
 * <p>
 * Spring Team recommends that you only annotate concrete classes (and methods of concrete classes) with the @Cache* annotation, as opposed to annotating interfaces.
 * You certainly can place the @Cache* annotation on an interface (or an interface method), but this works only as you would expect it to if you are using interface-based proxies.
 * The fact that Java annotations are not inherited from interfaces means that if you are using class-based proxies (proxy-target-class="true") or the weaving-based aspect (mode="aspectj"),
 * then the caching settings are not recognized by the proxying and weaving infrastructure, and the object will not be wrapped in a caching proxy, which would be decidedly bad.
 */

public class CgLibExecutor {

    @SuppressWarnings("")
    public static void main(String[] args) {
        ResourceService resourceService = new ResourceServiceImpl(List.of("Resource1", "Resource2"));

        System.out.println("Create an interface proxy");
        System.out.println("=====");

        val resourceServiceProxy = (ResourceService) Enhancer.create(ResourceService.class, new ProxyMethodHandler(resourceService));
        System.out.println("Service name: " + resourceServiceProxy.getServiceName());
        System.out.println("Type: " + resourceServiceProxy.getType());
        System.out.println("Count items: " + resourceServiceProxy.getCountItems());

        System.out.println("Create a class proxy");
        System.out.println("=====");

        val resourceServiceImplProxy = (ResourceServiceImpl) Enhancer.create(ResourceServiceImpl.class, new ProxyMethodHandler(resourceService));
        System.out.println("Service name: " + resourceServiceImplProxy.getServiceName());
        System.out.println("Type: " + resourceServiceImplProxy.getType());
        System.out.println("Count items: " + resourceServiceImplProxy.getCountItems());
        System.out.println("Additional info: " + resourceServiceImplProxy.getAdditionalInfo());
    }

    static class ProxyMethodHandler implements MethodInterceptor {
        private final ResourceService resourceService;

        public ProxyMethodHandler(ResourceService resourceService) {
            this.resourceService = resourceService;
        }

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            if (method.getReturnType().equals(String.class) && (obj instanceof ResourceServiceImpl)) {
                return "PROXY_CLASS: " + proxy.invoke(resourceService, args);
            }

            if (method.getReturnType().equals(String.class)) {
                return "PROXY_INTERFACE: " + proxy.invoke(resourceService, args);
            }

            return proxy.invoke(resourceService, args);
        }
    }
}

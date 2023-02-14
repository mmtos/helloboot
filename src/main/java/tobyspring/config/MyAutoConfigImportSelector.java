package tobyspring.config;

import java.util.stream.StreamSupport;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigImportSelector implements DeferredImportSelector, BeanClassLoaderAware {

    private ClassLoader classLoader;

    // BeanClassLoaderAware 인터페이스를 구현한 경우 setter로 BeanClassLoader를 주입받을 수 있다.
    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

//    // BeanClassLoaderAware을 구현하지 않고 생성자로 받아도 됨
//    public MyAutoConfigImportSelector(ClassLoader classLoader) {
//        this.classLoader = classLoader;
//    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Iterable<String> candidates = ImportCandidates.load(MyAutoConfiguration.class, classLoader);
        return StreamSupport
                .stream(candidates.spliterator(),false)
                .toArray(String[]::new);
    }
}

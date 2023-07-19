package de.flammenfuchs.javalib.reflect;

import com.google.common.reflect.ClassPath;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ClassScanner {

    private final String packageName;

    @Setter
    private ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    @Getter
    private List<String> ignoredPackages = new ArrayList<>();

    public ClassScanner(String mainClass) {
        this.packageName = mainClass;
    }

    public ClassScanner(Object mainClass) {
        this.packageName = mainClass.getClass().getPackageName();
    }

    @SneakyThrows
    public List<? extends Class<?>> scan() {
        return ClassPath.from(classLoader).getTopLevelClassesRecursive(packageName).stream()
                .filter(info -> !ignoredPackages.contains(info.getPackageName()))
                .map(info -> info.load())
                .toList();
    }

    public void addIgnoredPackage(String pkg) {
        this.ignoredPackages.add(pkg);
    }

    public void addIgnoredPackages(String... packages) {
        this.ignoredPackages.addAll(List.of(packages));
    }

    public void addIgnoredPackages(Collection<String> packages) {
        this.ignoredPackages.addAll(packages);
    }


}

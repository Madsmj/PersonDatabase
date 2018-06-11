package com;



import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/** MavenProjectsHelper contains methods that help with code that <it>knows</it> it is inside a Maven project and needs to
 * access a file or other resources not on the classpath.
 */

public class MavenProjectsHelper {


    /**
     * A launcher may have to locate a given path in a Maven project. We traverse from the directory the byte code for the given class
     * is loaded from to to the root using getParent() looking for the path.  If not found, throw runtime exception.
     *
     * @param clazz      Class which is located somewhere in the project "beneath" the path to find.
     * @param pathToFind relative path name which must resolve when traversing towards the root.
     * @return
     */
    public static Path getRequiredPathTowardsRoot(Class<?> clazz, String pathToFind) {
        try {
            // http://stackoverflow.com/a/320595/53897
            URI uri = clazz.getProtectionDomain().getCodeSource().getLocation().toURI();
            Path startFolder = Paths.get(uri);
            return getRequiredPathTowardsRoot(startFolder, pathToFind);
        } catch (URISyntaxException e) {
            throw new RuntimeException("JVM will not tell location for " + clazz.getCanonicalName(), e);
        }
    }

    /**
     * A launcher may have to locate a given path in a Maven project. We traverse from the directory the byte code for the given
     * object is loaded from to the root using getParent() looking for the path.  If not found, throw runtime exception.
     *
     * @param o          Object which class is located somewhere in the project "beneath" the path to find.
     * @param pathToFind relative path name which must resolve when traversing towards the root.
     * @return
     */
    public static Path getRequiredPathTowardsRoot(Object o, String pathToFind) {
        return getRequiredPathTowardsRoot(o.getClass(), pathToFind);
    }
}


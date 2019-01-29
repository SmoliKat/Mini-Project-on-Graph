
import sun.security.provider.certpath.Vertex;

import java.util.Collection;
import java.util.Set;

public interface ClusterInterface {

    Vertex getVertex(int vertexId);

    boolean addVertex(Vertex var1);
    boolean addVertices(Set<Vertex> add);

    boolean containsVertex(Vertex var1);

    boolean removeAllVertixes(Collection<? extends Vertex> var1);

    boolean removeVertex(Vertex var1);

    Set<Vertex> getVertices();

    String toString();

    int numOfVertices();

}

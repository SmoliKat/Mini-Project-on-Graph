import java.util.*;

public class  Cluster implements ClusterInterface{
    protected Set<SuperVertex> vertices ;
    protected String name;

    public Cluster(String name){
        this.vertices = new HashSet<SuperVertex>();
        this.name = name;
    }

    public Cluster(String name,  Set<SuperVertex> vertices) {
        this.vertices = new HashSet<SuperVertex>();
        this.addVertices(vertices);
        this.name = name;

    }

    public SuperVertex getVertex(int vertexId){
        Iterator verticesIter = vertices.iterator();
        while (verticesIter.hasNext()) {
            SuperVertex v = (SuperVertex) verticesIter.next();
            if (v.getId() == vertexId)
                return v;
        }
        return null;
    }

    protected SuperVertex getVertex(SuperVertex ver){
        Iterator verticesIter = vertices.iterator();
        while (verticesIter.hasNext()) {
            SuperVertex v = (SuperVertex) verticesIter.next();
            if (v.equals(ver))
                return v;
        }
        return null;
    }

    public SuperVertex getRandomVertex(){
        Random rand = new Random();
        Iterator<SuperVertex> v = vertices.iterator();
        SuperVertex iVertex = null;
        int randIndex = rand.nextInt(vertices.size());
        for(int i = 0; (v.hasNext() &&(i<=randIndex)); i++)
            iVertex = v.next();
        return iVertex;
    }

    protected Set<SuperVertex> getVertices(){
        Set<SuperVertex> ret = new HashSet<SuperVertex>();
        Iterator iter = this.vertices.iterator();
        while (iter.hasNext()) {
            SuperVertex v = ((SuperVertex) iter.next());
            ret.add(v);
        }
        return ret;
    }

    public Set<SuperVertex> getVerticesClone(){
        Set<SuperVertex> ret = new HashSet<SuperVertex>();
        Iterator iter = this.vertices.iterator();
        while (iter.hasNext()) {
            SuperVertex v = ((SuperVertex) iter.next()).clone();
            ret.add(v);
        }
        return ret;
    }

    public boolean addVertex(SuperVertex toAdd) {//adding vertex while checking for dupes
        if(vertices.contains(toAdd)) return false;
        boolean res = true;
        Iterator verticesIter = vertices.iterator();
        while (verticesIter.hasNext()) {
            SuperVertex v = (SuperVertex) verticesIter.next();
            res &= (!toAdd.equals(v));
        }
        if(res)
            vertices.add(toAdd);
        return res;
    }

    public boolean addVertices(Set<SuperVertex> toAdd){
        boolean res = true;
        Iterator iter = toAdd.iterator();
        while (iter.hasNext()) {
            SuperVertex v = ((SuperVertex) iter.next());
            res &= this.addVertex(v);
        }
        return res;
    }

    public String getName(){
        return this.name;
    }

    public boolean containsVertex(SuperVertex vert){
        return this.vertices.contains(vert);
    }

    public boolean containsAllVertices(Collection<? extends SuperVertex> vert){
        try{
            if(vert == null) {
                throw new InputException("There are no vertices in the collection.");
            }
            return this.vertices.containsAll(vert);
        }
        catch (InputException e) {
            System.out.println("There are no vertices in the collection.");
            //e.printStackTrace();
            return false;
        }

    }

    public Set<SuperVertex> cutting(Cluster OtherCluster){
        Set<SuperVertex> res = new HashSet<SuperVertex>();
        try{
            if(OtherCluster == null) {
                throw new InputException("There are no vertices in the collection.");
            }
            Set<SuperVertex> toRemove = this.getVertices();
            toRemove.removeAll(OtherCluster.getVertices());
            res = this.getVertices();
            res.removeAll(toRemove);
            return res;
        }
        catch (InputException e) {
            System.out.println("There are no vertices in the collection.");
            //e.printStackTrace();
            return res;
        }
    }

    public boolean removeAllVertices(Collection<? extends SuperVertex> vars){
        try {
            if (vars == null) {
                throw new InputException("There are no vertices in the collection.");
            }
            boolean res = true;
            Iterator verticesIter = vars.iterator();
            while (verticesIter.hasNext()) {
                SuperVertex toRemove = (SuperVertex) verticesIter.next();
                res &= this.removeVertex(toRemove);
            }
            return res;
        }
        catch (InputException e) {
            System.out.println("There are no vertices in the collection.");
            //e.printStackTrace();
            return false;
        }
    }

    public boolean removeAllVerticesExceptFrom(Collection<? extends SuperVertex> vert){
        try {
            if (vert == null) {
                throw new InputException("There are no vertices in the collection.");
            }
            else {
                Set<SuperVertex> toRemove = this.getVertices();
                toRemove.removeAll(vert);
                return this.removeAllVertices(toRemove);
            }
        }
        catch (InputException e) {
            System.out.println("There are no vertices in the collection.");
            //e.printStackTrace();
            return false;
        }
    }

    public boolean removeVertex(SuperVertex vert){
        Iterator verticesIter = vertices.iterator();
        while (verticesIter.hasNext()) {
            SuperVertex v = (SuperVertex) verticesIter.next();
            if(v.equals(vert)) {
                vertices.remove(v);
                return true;
            }
        }
        System.out.println("the vertex:"+vert+" does not exist in this Object:"+this);
        return false;
    }

    public String toString(){
        return "{" + this.getName() + ": Vertices:" + this.getVertices() + "}";
    }

    public int numOfVertices(){
        return this.vertices.size();
    }

}

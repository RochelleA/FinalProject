package core;


import java.util.HashSet;

public interface IMyLabelling {

public HashSet<MyVertex> getInVertices();

public void addInVertex(MyVertex v);

public HashSet<MyVertex> getOutVertices();

public void addOutVertex(MyVertex v);

public HashSet<MyVertex> getUndecVertices();

public void addUndecVertex(MyVertex v);
}

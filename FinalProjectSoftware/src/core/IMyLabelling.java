package core;


import java.util.HashSet;

public interface IMyLabelling {

public HashSet<MyVertex> getInVertices();

public boolean addInVertex(MyVertex v);

public HashSet<MyVertex> getOutVertices();

public boolean addOutVertex(MyVertex v);

public HashSet<MyVertex> getUndecVertices();

public boolean addUndecVertex(MyVertex v);
}

package core;


import java.util.HashSet;

public interface IMyLabelling {

public HashSet<MyArgument> getInVertices();

public boolean addInVertex(MyArgument v);

public HashSet<MyArgument> getOutVertices();

public boolean addOutVertex(MyArgument v);

public HashSet<MyArgument> getUndecVertices();

public boolean addUndecVertex(MyArgument v);
}

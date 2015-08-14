package core;


import java.util.HashSet;

public interface IMyLabelling {

public HashSet<MyArg> getInArgs();

public boolean addInArg(MyArg v);

public HashSet<MyArg> getOutArgs();

public boolean addOutArg(MyArg v);

public HashSet<MyArg> getUndecArgs();

public boolean addUndecArg(MyArg v);
}

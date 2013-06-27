package com.github.lmm.core;

import java.util.HashSet;
import java.util.Set;

public class ThreadLocalRegistry {

public static ThreadLocal<?>[] captureSnapshot() {
Set<ThreadLocal<?>> threadLocalSet = _threadLocalSet.get();
return threadLocalSet.toArray(new ThreadLocal<?>[threadLocalSet.size()]);
}

public static void registerThreadLocal(ThreadLocal<?> threadLocal) {
Set<ThreadLocal<?>> threadLocalSet = _threadLocalSet.get();
threadLocalSet.add(threadLocal);
}
public static void resetThreadLocals() {
Set<ThreadLocal<?>> threadLocalSet = _threadLocalSet.get();
for (ThreadLocal<?> threadLocal : threadLocalSet) {
threadLocal.remove();
}
//_threadLocalSet.get().clear();
}
private static ThreadLocal<Set<ThreadLocal<?>>> _threadLocalSet =new ThreadLocal<Set<ThreadLocal<?>>>(){
@Override
protected Set<ThreadLocal<?>> initialValue() {

return new HashSet<ThreadLocal<?>>();
}
};
 
}
package androidx.car.app.managers;

import com.sun.jna.Native$$ExternalSyntheticBUOutline0;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class ManagerCache {
    private final Map<String, Class<?>> mClassByName;
    private final Map<Class<?>, RuntimeException> mExceptions;
    private final Map<Class<?>, ManagerFactory<? extends Manager>> mFactories;
    private final Map<Class<?>, String> mNameByClass;
    private final Map<Class<?>, Manager> mValues;

    public ManagerCache() {
        this.mValues = new HashMap();
        this.mExceptions = new HashMap();
        this.mFactories = new HashMap();
        this.mClassByName = new HashMap();
        this.mNameByClass = new HashMap();
    }

    public <T extends Manager> void addFactory(Class<T> r2, String r3, ManagerFactory<T> r4) {
        this.mFactories.put(r2, r4);
        if (r3 == null) goto L6;
        this.mClassByName.put(r3, r2);
        this.mNameByClass.put(r2, r3);
        return;
    }

    public <T> T getOrCreate(Class<T> r3) {
        RuntimeException r0 = this.mExceptions.get(r3);
        if (r0 != null) goto L16;
        T r02 = (T) this.mValues.get(r3);
        if (r02 == null) goto L7;
        return r02;
    L7:
        ManagerFactory<? extends Manager> r03 = this.mFactories.get(r3);
        if (r03 != null) goto L17;
        Native$$ExternalSyntheticBUOutline0.m("The class '", r3, "' does not correspond to a car service");
        return null;
    L17:
        T r04 = (T) r03.create();
        this.mValues.put(r3, r04);     // Catch: RuntimeException -> L11
        return r04;
    L11:
        e = move-exception;
        this.mExceptions.put(r3, e);
        throw e;
    L16:
        throw r0;
    }
}

package com.chaquo.python;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
class MethodCache {
    private Map<String, PyObject> cache;
    private PyObject obj;

    public MethodCache(PyObject r2) {
        this.cache = new HashMap();
        this.obj = r2;
    }

    public PyObject get(String r3) {
        PyObject r0 = this.cache.get(r3);
        if (r0 != null) goto L10;
        PyObject r02 = this.obj.get(r3);
        if (r02 == null) goto L9;
        this.cache.put(r3, r02);
        return r02;
    L9:
        throw new UnsupportedOperationException(String.format("'%s' object has no attribute '%s'", new Object[]{this.obj.type().get("__name__"), r3}));
    L10:
        return r0;
    }
}

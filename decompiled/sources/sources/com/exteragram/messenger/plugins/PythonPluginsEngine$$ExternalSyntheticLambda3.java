package com.exteragram.messenger.plugins;

import com.chaquo.python.PyObject;
import com.exteragram.messenger.plugins.PythonPluginsEngine;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class PythonPluginsEngine$$ExternalSyntheticLambda3 implements PythonPluginsEngine.PyMethodCaller {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ PythonPluginsEngine$$ExternalSyntheticLambda3(String r1, int r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // com.exteragram.messenger.plugins.PythonPluginsEngine.PyMethodCaller
    public final PyObject call(PyObject r2, Object r3) {
        return PythonPluginsEngine.m1559$r8$lambda$0E5o7fqS78X1iCQH_4FWQg9Gbs(this.f$0, this.f$1, r2, (TLRPC.Update) r3);
    }
}

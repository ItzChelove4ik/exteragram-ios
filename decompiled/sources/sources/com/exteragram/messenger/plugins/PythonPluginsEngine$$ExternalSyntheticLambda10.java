package com.exteragram.messenger.plugins;

import com.chaquo.python.PyObject;
import com.exteragram.messenger.plugins.PythonPluginsEngine;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class PythonPluginsEngine$$ExternalSyntheticLambda10 implements PythonPluginsEngine.PyMethodCaller {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ PythonPluginsEngine$$ExternalSyntheticLambda10(String r1, int r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // com.exteragram.messenger.plugins.PythonPluginsEngine.PyMethodCaller
    public final PyObject call(PyObject r2, Object r3) {
        return PythonPluginsEngine.$r8$lambda$oSwq0KS9WeQUq_sw29d6K3u_EmA(this.f$0, this.f$1, r2, (TLRPC.Updates) r3);
    }
}

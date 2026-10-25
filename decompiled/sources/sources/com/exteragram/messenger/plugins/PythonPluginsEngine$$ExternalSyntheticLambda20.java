package com.exteragram.messenger.plugins;

import com.chaquo.python.PyObject;
import com.exteragram.messenger.plugins.PythonPluginsEngine;
import org.telegram.messenger.SendMessagesHelper;

/* loaded from: classes4.dex */
public final /* synthetic */ class PythonPluginsEngine$$ExternalSyntheticLambda20 implements PythonPluginsEngine.PyMethodCaller {
    public final /* synthetic */ int f$0;

    public /* synthetic */ PythonPluginsEngine$$ExternalSyntheticLambda20(int r1) {
        this.f$0 = r1;
    }

    @Override // com.exteragram.messenger.plugins.PythonPluginsEngine.PyMethodCaller
    public final PyObject call(PyObject r1, Object r2) {
        return PythonPluginsEngine.m1566$r8$lambda$cwkMqqPOrk5Iq87zec6AWD3pb0(this.f$0, r1, (SendMessagesHelper.SendMessageParams) r2);
    }
}

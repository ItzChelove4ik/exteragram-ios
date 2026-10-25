package com.google.firebase.tracing;

import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.ComponentRegistrarProcessor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class ComponentMonitor implements ComponentRegistrarProcessor {
    public static /* synthetic */ Object $r8$lambda$VxUPTnF8OKnnbKKjQblzb1_PJuw(String r0, Component r1, ComponentContainer r2) {
        FirebaseTrace.pushTrace(r0);     // Catch: Throwable -> L5
        Object r02 = r1.getFactory().create(r2);     // Catch: Throwable -> L5
        FirebaseTrace.popTrace();
        return r02;
    L5:
        th = move-exception;
        FirebaseTrace.popTrace();
        throw th;
    }

    public ComponentMonitor() {
    }

    @Override // com.google.firebase.components.ComponentRegistrarProcessor
    public List<Component<?>> processRegistrar(ComponentRegistrar r4) {
        ArrayList r3 = new ArrayList();
        Iterator<Component<?>> r42 = r4.getComponents().iterator();
    L4:
        if (r42.hasNext() == false) goto L9;
        Component<?> r0 = r42.next();
        String r1 = r0.getName();
        if (r1 == null) goto L8;
        r0 = r0.withFactory(new ComponentMonitor$$ExternalSyntheticLambda0(r1, r0));
    L8:
        r3.add(r0);
        goto L4
    L9:
        return r3;
    }
}

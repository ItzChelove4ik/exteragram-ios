package com.google.android.gms.common.api.internal;

import java.util.Objects;
import java.util.concurrent.locks.Lock;

/* loaded from: classes4.dex */
abstract class zaaq implements Runnable {
    final /* synthetic */ zaar zab;

    public /* synthetic */ zaaq(zaar r1, byte[] r2) {
        Objects.requireNonNull(r1);
        this.zab = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        /*  JADX ERROR: Simple mode code generation failed
            java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
            	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100)
            	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
            	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
            	at java.base/java.util.Objects.checkIndex(Objects.java:385)
            	at java.base/java.util.ArrayList.get(ArrayList.java:427)
            	at jadx.core.codegen.MethodGen.generateSimpleCode(MethodGen.java:361)
            	at jadx.core.codegen.MethodGen.addSimpleMethodCode(MethodGen.java:323)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:286)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:410)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
            */
        /*
            this = this;
            com.google.android.gms.common.api.internal.zaar r0 = r2.zab
            java.util.concurrent.locks.Lock r1 = r0.zas()
            r1.lock()
            boolean r1 = java.lang.Thread.interrupted()     // Catch: java.lang.Throwable -> L1b java.lang.RuntimeException -> L1d
            if (r1 == 0) goto L17
            java.util.concurrent.locks.Lock r2 = r0.zas()
        L13:
            r2.unlock()
            return
        L17:
            r2.zaa()     // Catch: java.lang.Throwable -> L1b java.lang.RuntimeException -> L1d
            goto L27
        L1b:
            r0 = move-exception
            goto L2e
        L1d:
            r0 = move-exception
            com.google.android.gms.common.api.internal.zaar r1 = r2.zab     // Catch: java.lang.Throwable -> L1b
            com.google.android.gms.common.api.internal.zabd r1 = r1.zar()     // Catch: java.lang.Throwable -> L1b
            r1.zas(r0)     // Catch: java.lang.Throwable -> L1b
        L27:
            com.google.android.gms.common.api.internal.zaar r2 = r2.zab
            java.util.concurrent.locks.Lock r2 = r2.zas()
            goto L13
        L2e:
            com.google.android.gms.common.api.internal.zaar r2 = r2.zab
            java.util.concurrent.locks.Lock r2 = r2.zas()
            r2.unlock()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zaaq.run():void");
    }

    public abstract void zaa();
}

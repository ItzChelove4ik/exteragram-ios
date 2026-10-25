package androidx.work.impl;

import androidx.work.impl.model.WorkGenerationalId;

/* loaded from: classes.dex */
public interface ExecutionListener {
    void onExecuted(WorkGenerationalId r1, boolean r2);
}

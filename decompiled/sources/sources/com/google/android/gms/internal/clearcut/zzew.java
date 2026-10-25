package com.google.android.gms.internal.clearcut;

import java.util.List;

/* loaded from: classes4.dex */
public final class zzew extends RuntimeException {
    private final List<String> zzoy;

    public zzew(zzdo r1) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
        this.zzoy = null;
    }
}

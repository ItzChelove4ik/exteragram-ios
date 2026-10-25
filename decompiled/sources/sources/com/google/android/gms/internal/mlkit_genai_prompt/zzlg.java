package com.google.android.gms.internal.mlkit_genai_prompt;

/* loaded from: classes4.dex */
abstract class zzlg implements zznf {
    public zzlg() {
    }

    public final boolean equals(Object r2) {
        if (r2 != this) goto L6;
        return true;
    L6:
        if ((r2 instanceof zznf) == true) goto L8;
        return false;
    L8:
        return zzc().equals(((zznf) r2).zzc());
    }

    public final int hashCode() {
        return zzc().hashCode();
    }

    public final String toString() {
        return zzc().toString();
    }
}

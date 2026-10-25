package com.google.android.play.core.integrity;

import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import java.util.Locale;

/* loaded from: classes5.dex */
public class StandardIntegrityException extends ApiException {

    /* renamed from: a, reason: collision with root package name */
    private final Throwable f40a;

    public StandardIntegrityException(int r5, Throwable r6) {
        super(new Status(r5, String.format(Locale.ROOT, "Standard Integrity API error (%d): %s.", new Object[]{Integer.valueOf(r5), com.google.android.play.core.integrity.model.b.a(r5)})));
        if (r5 == 0) goto L6;
        this.f40a = r6;
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("ErrorCode should not be 0.");
        throw null;
    }

    @Override // java.lang.Throwable
    public final synchronized Throwable getCause() {
        monitor-enter(this);
        Throwable r0 = this.f40a;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public int getErrorCode() {
        return super.getStatusCode();
    }
}

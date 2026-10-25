package com.google.android.gms.tasks;

/* loaded from: classes5.dex */
public final class DuplicateTaskCompletionException extends IllegalStateException {
    private DuplicateTaskCompletionException(String r1, Throwable r2) {
        super(r1, r2);
    }

    public static IllegalStateException of(Task<?> r3) {
        if (r3.isComplete() == false) goto L5;
        Exception r0 = r3.getException();
        if (r0 == null) goto L10;
        String r32 = "failure";
    L17:
        return new DuplicateTaskCompletionException("Complete with: ".concat(r32), r0);
    L10:
        if (r3.isSuccessful() == false) goto L13;
        r32 = "result ".concat(String.valueOf(r3.getResult()));
        goto L17
    L13:
        if (r3.isCanceled() == false) goto L15;
        r32 = "cancellation";
        goto L17
    L15:
        r32 = "unknown issue";
        goto L17
    L5:
        return new IllegalStateException("DuplicateTaskCompletionException can only be created from completed Task.");
    }
}

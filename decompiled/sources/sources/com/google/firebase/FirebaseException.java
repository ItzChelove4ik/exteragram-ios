package com.google.firebase;

import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes.dex */
public class FirebaseException extends Exception {
    @Deprecated
    public FirebaseException() {
    }

    public FirebaseException(String r2) {
        Preconditions.checkNotEmpty(r2, "Detail message must not be empty");
        super(r2);
    }

    public FirebaseException(String r2, Throwable r3) {
        Preconditions.checkNotEmpty(r2, "Detail message must not be empty");
        super(r2, r3);
    }
}

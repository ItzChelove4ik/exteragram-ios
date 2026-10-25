package com.stripe.android;

import com.stripe.android.model.Token;

/* loaded from: classes5.dex */
public interface TokenCallback {
    void onError(Exception r1);

    void onSuccess(Token r1);
}

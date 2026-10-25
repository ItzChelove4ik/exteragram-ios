package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;

/* loaded from: classes4.dex */
public interface LifecycleFragment {
    void addCallback(String r1, LifecycleCallback r2);

    <T extends LifecycleCallback> T getCallbackOrNull(String r1, Class<T> r2);

    Activity getLifecycleActivity();

    void startActivityForResult(Intent r1, int r2);
}

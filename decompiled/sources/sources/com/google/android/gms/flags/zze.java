package com.google.android.gms.flags;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes4.dex */
public interface zze extends IInterface {
    boolean getBooleanFlagValue(String r1, boolean r2, int r3);

    int getIntFlagValue(String r1, int r2, int r3);

    long getLongFlagValue(String r1, long r2, int r4);

    String getStringFlagValue(String r1, String r2, int r3);

    void init(IObjectWrapper r1);
}

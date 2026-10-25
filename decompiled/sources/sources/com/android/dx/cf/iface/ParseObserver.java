package com.android.dx.cf.iface;

import com.android.dx.util.ByteArray;

/* loaded from: classes4.dex */
public interface ParseObserver {
    void changeIndent(int r1);

    void endParsingMember(ByteArray r1, int r2, String r3, String r4, Member r5);

    void parsed(ByteArray r1, int r2, int r3, String r4);

    void startParsingMember(ByteArray r1, int r2, String r3, String r4);
}

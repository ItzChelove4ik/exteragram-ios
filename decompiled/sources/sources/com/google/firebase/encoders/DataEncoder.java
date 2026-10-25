package com.google.firebase.encoders;

import java.io.Writer;

/* loaded from: classes.dex */
public interface DataEncoder {
    String encode(Object r1);

    void encode(Object r1, Writer r2);
}

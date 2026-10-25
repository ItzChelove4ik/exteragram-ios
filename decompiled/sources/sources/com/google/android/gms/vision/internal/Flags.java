package com.google.android.gms.vision.internal;

import androidx.annotation.Keep;
import com.google.android.gms.flags.Flag;

@Keep
/* loaded from: classes5.dex */
public class Flags {
    private static final Flag<Boolean> zza = null;

    static {
        zza = Flag.define(0, "vision:product_barcode_value_logging_enabled", Boolean.TRUE);
    }

    private Flags() {
    }
}

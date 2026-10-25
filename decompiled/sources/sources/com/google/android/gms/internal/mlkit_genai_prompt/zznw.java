package com.google.android.gms.internal.mlkit_genai_prompt;

import java.math.RoundingMode;

/* loaded from: classes4.dex */
abstract /* synthetic */ class zznw {
    static final /* synthetic */ int[] zza = null;

    static {
        int[] r0 = new int[RoundingMode.values().length];
        zza = r0;
        r0[RoundingMode.UNNECESSARY.ordinal()] = 1;     // Catch: NoSuchFieldError -> L12
    L20:
        zza[RoundingMode.FLOOR.ordinal()] = 2;     // Catch: NoSuchFieldError -> L13
    L24:
        zza[RoundingMode.CEILING.ordinal()] = 3;     // Catch: NoSuchFieldError -> L14
    L34:
        zza[RoundingMode.DOWN.ordinal()] = 4;     // Catch: NoSuchFieldError -> L15
    L22:
        zza[RoundingMode.UP.ordinal()] = 5;     // Catch: NoSuchFieldError -> L16
    L26:
        zza[RoundingMode.HALF_EVEN.ordinal()] = 6;     // Catch: NoSuchFieldError -> L17
    L28:
        zza[RoundingMode.HALF_UP.ordinal()] = 7;     // Catch: NoSuchFieldError -> L18
    L30:
        zza[RoundingMode.HALF_DOWN.ordinal()] = 8;     // Catch: NoSuchFieldError -> L19
        return;
    }
}

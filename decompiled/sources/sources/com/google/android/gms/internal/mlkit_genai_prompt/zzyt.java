package com.google.android.gms.internal.mlkit_genai_prompt;

import android.content.Context;
import com.google.mlkit.genai.prompt.GenerationConfig;
import com.google.mlkit.genai.prompt.ModelConfig;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* loaded from: classes4.dex */
public final class zzyt {
    public /* synthetic */ zzyt(DefaultConstructorMarker r1) {
    }

    public static final /* synthetic */ Context zza(zzyt r0) {
        return (Context) zzzy.zzp().getValue();
    }

    public static final int zzb(GenerationConfig r6, zzye r7) {
        ModelConfig r62 = r6.getModelConfig();
        int r0 = r62.getReleaseStage();
        if (r0 == 0) goto L26;
        if (r0 == 1) goto L6;
        return -1;
    L6:
        int r63 = r62.getPreference();
        if (r63 == 1) goto L18;
        if (r63 == 2) goto L10;
        return -1;
    L10:
        zzye r64 = zzye.zza;
        int r65 = r7.ordinal();
        if (r65 == 0) goto L16;
        if (r65 == 3) goto L14;
        return -1;
    L14:
        return 652;
    L16:
        return 647;
    L18:
        zzye r66 = zzye.zza;
        int r67 = r7.ordinal();
        if (r67 == 0) goto L24;
        if (r67 == 3) goto L22;
        return -1;
    L22:
        return 653;
    L24:
        return 646;
    L26:
        int r68 = r62.getPreference();
        if (r68 == 1) goto L48;
        if (r68 == 2) goto L30;
        return -1;
    L30:
        zzye r69 = zzye.zza;
        int r610 = r7.ordinal();
        if (r610 == 0) goto L46;
        if (r610 == 1) goto L44;
        if (r610 == 2) goto L42;
        if (r610 == 3) goto L40;
        if (r610 != 4) goto L38;
        return 654;
    L38:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L40:
        return 648;
    L42:
        return 644;
    L44:
        return 643;
    L46:
        return 636;
    L48:
        zzye r611 = zzye.zza;
        int r612 = r7.ordinal();
        if (r612 == 0) goto L64;
        if (r612 == 1) goto L62;
        if (r612 == 2) goto L60;
        if (r612 == 3) goto L58;
        if (r612 != 4) goto L56;
        return 655;
    L56:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L58:
        return 651;
    L60:
        return 650;
    L62:
        return 649;
    L64:
        return 645;
    }
}

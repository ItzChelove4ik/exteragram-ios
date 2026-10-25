package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import kotlin.text.Typography;
import org.mvel2.asm.signature.SignatureVisitor;

/* loaded from: classes4.dex */
public final class zzzc implements zzyk {
    private static final Set zza = null;
    private final String zzb;
    private final String zzc;
    private final StringBuilder zzd;
    private boolean zze;

    static {
        zza = new HashSet(Arrays.asList(new Class[]{Boolean.class, Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class}));
    }

    public zzzc(String r1, String r2, StringBuilder r3) {
        this.zze = false;
        this.zzb = "[CONTEXT ";
        this.zzc = " ]";
        this.zzd = r3;
    }

    private static int zzc(String r2, int r3) {
    L3:
        if (r3 >= r2.length()) goto L13;
        char r0 = r2.charAt(r3);
        if (r0 < ' ') goto L12;
        if (r0 == '\"') goto L12;
        if (r0 == '\\') goto L12;
        r3 = r3 + 1;
    L12:
        return r3;
    L13:
        return -1;
    }

    @Override // com.google.android.gms.internal.measurement.zzyk
    public final void zza(String r8, Object r9) {
        boolean r0 = this.zze;
        StringBuilder r1 = this.zzd;
        char r3 = ' ';
        if (r0 == false) goto L6;
        r1.append(' ');
    L14:
        StringBuilder r7 = this.zzd;
        r7.append(r8);
        r7.append(SignatureVisitor.INSTANCEOF);
        if (r9 != null) goto L19;
        r7.append(true);
        return;
    L19:
        if (zza.contains(r9.getClass()) == false) goto L22;
        r7.append(r9);
        return;
    L22:
        r7.append(Typography.quote);
        String r92 = r9.toString();
        int r02 = 0;
    L23:
        int r12 = zzc(r92, r02);
        if (r12 == (-1)) goto L38;
        r7.append(r92, r02, r12);
        r02 = r12 + 1;
        char r13 = r92.charAt(r12);
        if (r13 == '\t') goto L36;
        if (r13 == '\n') goto L35;
        if (r13 == '\r') goto L34;
        if (r13 == '\"') goto L37;
        if (r13 == '\\') goto L37;
        r7.append(65533);
    L37:
        r7.append("\\");
        r7.append(r13);
        goto L23
    L34:
        r13 = 'r';
        goto L37
    L35:
        r13 = 'n';
        goto L37
    L36:
        r13 = 't';
        goto L37
    L38:
        r7.append(r92, r02, r92.length());
        r7.append(Typography.quote);
        return;
    L6:
        if (r1.length() > 0) goto L8;
    L13:
        r1.append(this.zzb);
        this.zze = true;
        goto L14
    L8:
        if (r1.length() <= 1000) goto L10;
    L11:
        r3 = '\n';
    L12:
        r1.append(r3);
        goto L13
    L10:
        if (r1.indexOf("\n") == (-1)) goto L12;
        goto L11
    }

    public final void zzb() {
        if (this.zze == false) goto L6;
        this.zzd.append(this.zzc);
        return;
    }
}

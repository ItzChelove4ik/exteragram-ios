package com.google.android.gms.internal.mlkit_genai_prompt;

import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.cast.MediaTrack;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public abstract class zzib {
    public zzib() {
    }

    public static zzhz zzu() {
        return new zzhj();
    }

    public abstract String zza();

    public abstract String zzb();

    public abstract Boolean zzc();

    public abstract zzml zzd();

    public abstract zzib zze();

    public abstract Long zzf();

    public abstract Long zzg();

    public abstract zzml zzh();

    public abstract zzmo zzi();

    public abstract Long zzj();

    public abstract Long zzk();

    public abstract Double zzl();

    public abstract Double zzm();

    public abstract Long zzn();

    public abstract Long zzo();

    public abstract String zzp();

    public abstract zzml zzq();

    public abstract zzml zzr();

    public abstract String zzs();

    public abstract int zzt();

    public final JSONObject zzv() {
        JSONObject r0 = new JSONObject();
        int r1 = zzt();
        if (r1 != 0) goto L5;
    L14:
        r0.putOpt(MediaTrack.ROLE_DESCRIPTION, zzb());
        r0.putOpt("format", zza());
        r0.putOpt("nullable", zzc());
        r0.putOpt("title", null);
        zzml r12 = zzd();
        if (r12 != null) goto L17;
    L19:
        r0.putOpt("maxItems", zzf());
        r0.putOpt("minItems", zzg());
        zzib r13 = zze();
        if (r13 == null) goto L22;
        r0.put(PluginsConstants.Settings.ITEMS, r13.zzv());
    L22:
        zzml r14 = zzh();
        if (r14 != null) goto L25;
    L27:
        r0.putOpt("maxProperties", null);
        r0.putOpt("minProperties", null);
        zzmo r15 = zzi();
        if (r15 == null) goto L34;
        JSONObject r3 = new JSONObject();
        zzns r16 = r15.zze().zza();
    L31:
        if (r16.hasNext() == false) goto L33;
        Map.Entry r4 = (Map.Entry) r16.next();
        r3.put((String) r4.getKey(), ((zzib) r4.getValue()).zzv());
        goto L31
    L33:
        r0.put("properties", r3);
    L34:
        r0.putOpt("maximum", zzm());
        r0.putOpt("minimum", zzl());
        r0.putOpt("maxLength", null);
        r0.putOpt("minLength", null);
        r0.putOpt("pattern", null);
        return r0;
    L25:
        if (r14.isEmpty() == true) goto L27;
        r0.put("required", new JSONArray(r14));
        goto L27
    L17:
        if (r12.isEmpty() == true) goto L19;
        r0.put("enum", new JSONArray(r12));
        goto L19
    L5:
        switch((r1 - 1)) {
            case 1: goto L12;
            case 2: goto L11;
            case 3: goto L10;
            case 4: goto L9;
            case 5: goto L8;
            case 6: goto L7;
            default: goto L6;
        };
    L6:
        String r17 = zzia.zza(r1).toLowerCase(Locale.US);
    L13:
        r0.put("type", r17);
        goto L14
    L7:
        r17 = "object";
        goto L13
    L8:
        r17 = "array";
        goto L13
    L9:
        r17 = "boolean";
        goto L13
    L10:
        r17 = "integer";
        goto L13
    L11:
        r17 = "number";
        goto L13
    L12:
        r17 = "string";
        goto L13
    }

    public final zzcp zzw() {
        zzco r0 = zzcp.zzu();
        int r1 = zzt();
        if (r1 == 0) goto L19;
        int r12 = r1 - 1;
        if (r12 == 0) goto L17;
        int r2 = 1;
        if (r12 == 1) goto L18;
        r2 = 2;
        if (r12 == 2) goto L18;
        r2 = 3;
        if (r12 == 3) goto L18;
        r2 = 4;
        if (r12 == 4) goto L18;
        r2 = 5;
        if (r12 == 5) goto L18;
        r2 = 6;
    L18:
        r0.zza(r2);
        goto L19
    L17:
        r2 = 0;
    L19:
        String r13 = zza();
        if (r13 == null) goto L22;
        r0.zzb(r13);
    L22:
        String r14 = zzb();
        if (r14 == null) goto L25;
        r0.zzc(r14);
    L25:
        Boolean r15 = zzc();
        if (r15 == null) goto L28;
        r0.zzd(r15.booleanValue());
    L28:
        zzml r16 = zzd();
        if (r16 == null) goto L31;
        r0 = r0.zzq(r16);
    L31:
        zzib r17 = zze();
        if (r17 == null) goto L34;
        r0.zzf(r17.zzw());
    L34:
        Long r18 = zzf();
        if (r18 == null) goto L37;
        r0.zzg(r18.longValue());
    L37:
        Long r19 = zzg();
        if (r19 == null) goto L40;
        r0.zzh(r19.longValue());
    L40:
        zzml r110 = zzh();
        if (r110 == null) goto L43;
        r0.zzi(r110);
    L43:
        zzmo r111 = zzi();
        if (r111 == null) goto L50;
        zzmn r22 = new zzmn();
        zzns r112 = r111.zze().zza();
    L47:
        if (r112.hasNext() == false) goto L49;
        Map.Entry r3 = (Map.Entry) r112.next();
        r22.zza((String) r3.getKey(), ((zzib) r3.getValue()).zzw());
        goto L47
    L49:
        r0.zzo(r22.zzc());
    L50:
        Double r113 = zzl();
        if (r113 == null) goto L53;
        r0.zzj(r113.doubleValue());
    L53:
        Double r5 = zzm();
        if (r5 == null) goto L57;
        r0.zzk(r5.doubleValue());
    L57:
        return r0.zzp();
    }
}

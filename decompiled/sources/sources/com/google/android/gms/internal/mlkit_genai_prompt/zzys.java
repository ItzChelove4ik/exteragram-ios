package com.google.android.gms.internal.mlkit_genai_prompt;

import android.graphics.Bitmap;
import com.google.mlkit.genai.common.GenAiException;
import com.google.mlkit.genai.common.StreamingCallback;
import com.google.mlkit.genai.prompt.Candidate;
import com.google.mlkit.genai.prompt.Content;
import com.google.mlkit.genai.prompt.CountTokensResponse;
import com.google.mlkit.genai.prompt.GenerateContentRequest;
import com.google.mlkit.genai.prompt.GenerateContentResponse;
import com.google.mlkit.genai.prompt.ImagePart;
import com.google.mlkit.genai.prompt.Part;
import com.google.mlkit.genai.prompt.PromptPrefix;
import com.google.mlkit.genai.prompt.SystemInstruction;
import com.google.mlkit.genai.prompt.TextPart;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.reflect.KClass;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzys {
    public static final zzys zza = null;

    static {
        zza = new zzys();
    }

    private zzys() {
    }

    public static final zzib zzb(KClass r1) {
        return zzyh.zza.zza(r1).zzl();
    }

    public static final String zzc(String r2, zzib r3) {
        String r32 = r3.zzv().toString();
        if (r32.length() != 0) goto L5;
        return r2;
    L5:
        int r0 = String.valueOf(r2).length();
        StringBuilder r1 = new StringBuilder(((r0 + 146) + String.valueOf(r32).length()) + 1);
        r1.append(r2);
        r1.append("\nContext:\nDo NOT generate unsafe content.\nIf contents are safe, you MUST output ONLY in the following JSON schema without any spaces or newlines:\n");
        r1.append(r32);
        r1.append("\n");
        return r1.toString();
    }

    public static final GenerateContentResponse zzd(zzic r7) {
        List r0 = CollectionsKt.sortedWith(r7.zza(), new zzyr());
        HashSet r1 = new HashSet();
        ArrayList r2 = new ArrayList();
        Iterator r02 = r0.iterator();
    L4:
        if (r02.hasNext() == false) goto L8;
        Object r3 = r02.next();
        if (r1.add(((zzhv) r3).zza()) == false) goto L4;
        r2.add(r3);
        goto L4
    L8:
        ArrayList r03 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r2, 10));
        int r32 = r2.size();
        int r4 = 0;
    L9:
        if (r4 >= r32) goto L11;
        Object r5 = r2.get(r4);
        r4 = r4 + 1;
        zzhv r52 = (zzhv) r5;
        r03.add(Candidate.Companion.zza(r52.zza(), Integer.valueOf(zzf(r52.zzc()))));
        goto L9
    L11:
        zzml r72 = r7.zzd();
        if (r72 == null) goto L17;
        List r22 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r72, 10));
        Iterator<E> r73 = r72.iterator();
    L15:
        if (r73.hasNext() == false) goto L19;
        zzhv r12 = (zzhv) r73.next();
        r22.add(Candidate.Companion.zza(r12.zza(), Integer.valueOf(zzf(r12.zzc()))));
    L19:
        return GenerateContentResponse.Companion.zzb(r03, r22);
    L17:
        r22 = CollectionsKt.emptyList();
        goto L19
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v0, types: [int] */
    /* JADX WARN: Type inference failed for: r14v4 */
    /* JADX WARN: Type inference failed for: r14v5, types: [int] */
    /* JADX WARN: Type inference failed for: r14v8 */
    public static final zzhy zze(zzaan r16, StreamingCallback r17, zzhu r18) {
        GenerateContentRequest r2 = r16.zza();
        ArrayList r3 = new ArrayList();
        PromptPrefix r4 = r2.getPromptPrefix();
        if (r4 == null) goto L6;
        String r42 = r4.getTextString();
        if (r42 == null) goto L6;
    L7:
        KClass r6 = r16.zzb();
        if (r6 == null) goto L10;
        zzib r62 = zzb(r6);
    L11:
        SystemInstruction r8 = r2.getSystemInstruction();
        if (r8 == null) goto L15;
        String r82 = r8.getTextString();
        if (r82 == null) goto L15;
    L17:
        if (r82.length() <= 0) goto L19;
        r3.add(zzhs.zzj(2, r82));
    L19:
        Iterator<Content> r9 = r2.getContents().iterator();
        boolean r11 = false;
        int r12 = 0;
    L21:
        if (r9.hasNext() == false) goto L28;
        Iterator<Part> r13 = r9.next().getParts().iterator();
    L24:
        if (r13.hasNext() == false) goto L21;
        Part r14 = r13.next();
        if ((r14 instanceof TextPart) == false) goto L24;
        r12 = r12 + ((TextPart) r14).getTextString().length();
        goto L24
    L28:
        List<Content> r92 = r2.getContents();
        if ((r92 instanceof Collection) == true) goto L31;
    L33:
        Iterator r93 = r92.iterator();
    L35:
        if (r93.hasNext() == false) goto L32;
        List<Part> r132 = ((Content) r93.next()).getParts();
        if ((r132 instanceof Collection) == false) goto L41;
        if (r132.isEmpty() == true) goto L35;
    L41:
        Iterator r133 = r132.iterator();
    L43:
        if (r133.hasNext() == false) goto L35;
        if ((((Part) r133.next()) instanceof TextPart) == false) goto L43;
        boolean r94 = true;
    L48:
        if (((r42.length() + r12) + r82.length()) > 32000) goto L117;
        Iterator<Content> r83 = r2.getContents().iterator();
        TextPart r122 = null;
    L51:
        if (r83.hasNext() == false) goto L58;
        Iterator<Part> r134 = r83.next().getParts().iterator();
    L54:
        if (r134.hasNext() == false) goto L51;
        Part r15 = r134.next();
        if ((r15 instanceof TextPart) == false) goto L54;
        r122 = (TextPart) r15;
        goto L54
    L58:
        if (r94 == false) goto L60;
    L63:
        Iterator<Content> r7 = r2.getContents().iterator();
        boolean r84 = true;
    L65:
        if (r7.hasNext() == false) goto L96;
        Iterator<Part> r95 = r7.next().getParts().iterator();
    L68:
        if (r95.hasNext() == false) goto L65;
        Part r135 = r95.next();
        if ((r135 instanceof TextPart) == true) goto L71;
        if ((r135 instanceof ImagePart) == false) goto L94;
        ImagePart r136 = (ImagePart) r135;
        Bitmap r152 = r136.getBitmap();
        Bitmap.Config r137 = r136.getBitmap().getConfig();
        if (r137 != null) goto L86;
        r137 = Bitmap.Config.ARGB_8888;
    L86:
        Bitmap r138 = r152.copy(r137, r11);
        int r153 = r138.getWidth();
        int r10 = r138.getHeight();
        int r142 = Math.min(r153, r10);
        if (r142 <= 768) goto L92;
        float r112 = r142;
        float r143 = r153;
        float r154 = 768.0f / r112;
        ?? r144 = 0;
        Bitmap r102 = Bitmap.createScaledBitmap(r138, (int) (r143 * r154), (int) (r10 * r154), false);
        if (r102 == r138) goto L91;
        r138.recycle();
    L91:
        r138 = r102;
    L93:
        r3.add(zzhs.zzi(r144, r138));
        boolean r145 = r144;
    L95:
        r11 = r145;
        goto L68
    L92:
        r144 = 0;
        goto L93
    L94:
        r145 = r11 ? 1 : 0;
        goto L95
    L71:
        String r155 = ((TextPart) r135).getTextString();
        if (r84 == false) goto L75;
        if (r18 != null) goto L75;
        r155 = r42.concat(String.valueOf(r155));
        r84 = r11 ? 1 : 0;
    L75:
        if (r135 != r122) goto L80;
        if (r62 == null) goto L80;
        if (r16.zzc() == false) goto L80;
        r155 = zzc(r155, r62);
    L80:
        r3.add(zzhs.zzj(r11 ? 1 : 0, r155));
        goto L68
    L96:
        ?? r146 = r11;
        if (r122 != null) goto L103;
        if (r62 != null) goto L100;
        r62 = null;
        goto L103
    L100:
        if (r16.zzc() == false) goto L103;
        r3.add(zzhs.zzj(r146, zzc(_UrlKt.FRAGMENT_ENCODE_SET, r62)));
    L103:
        if (r17 == null) goto L105;
        zzyk r72 = new zzyk(r17);
    L106:
        zzhx r0 = zzhy.zzx();
        if (r62 == null) goto L109;
        r0.zzr(r62);
    L109:
        r0.zza(CollectionsKt.toList(r3));
        r0.zzb(r2.getTemperature());
        r0.zzc(r2.getTopK());
        r0.zze(CollectionsKt.emptyList());
        r0.zzf(1);
        r0.zzg(r2.getMaxOutputTokens());
        r0.zzk(2);
        r0.zzl(2);
        r0.zzh(r2.getCandidateCount());
        r0.zzn(r2.getSeed());
        r0.zzs(r2.getEnableThinking());
        if (r72 == null) goto L112;
        r0.zzi(r72);
    L112:
        if (r18 == null) goto L115;
        r0.zzq(r18);
    L115:
        return r0.zzu();
    L105:
        r72 = null;
        goto L106
    L60:
        if (r42.length() <= 0) goto L63;
        if (r18 != null) goto L63;
        r3.add(zzhs.zzj(0, r42));
        goto L63
    L117:
        throw new GenAiException("Input text length exceeds the limit. Please check the countTokens API.", null, 12);
    L32:
        r94 = false;
        goto L48
    L31:
        if (r92.isEmpty() == false) goto L33;
    L15:
        r82 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L17
    L10:
        r62 = null;
    L6:
        r42 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L7
    }

    private static final int zzf(int r1) {
        if (r1 != 0) goto L4;
        return 0;
    L4:
        if (r1 == 1) goto L7;
        return -100;
    L7:
        return 1;
    }

    public final CountTokensResponse zza(zzdz r1) {
        return new CountTokensResponse(r1.zza());
    }
}

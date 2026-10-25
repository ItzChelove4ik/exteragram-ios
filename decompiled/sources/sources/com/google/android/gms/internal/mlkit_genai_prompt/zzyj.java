package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.mlkit.genai.prompt.Candidate;
import com.google.mlkit.genai.prompt.Content;
import com.google.mlkit.genai.prompt.GenerateContentRequest;
import com.google.mlkit.genai.prompt.GenerateContentResponse;
import com.google.mlkit.genai.prompt.GenerationConfig;
import com.google.mlkit.genai.prompt.ImagePart;
import com.google.mlkit.genai.prompt.Part;
import com.google.mlkit.genai.prompt.PromptPrefix;
import com.google.mlkit.genai.prompt.TextPart;
import j$.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.reflect.KClass;

/* loaded from: classes4.dex */
public final class zzyj implements zzaty {
    private final zzaam zza;
    private final zzaro zzb;
    private final zzavc zzc;

    public zzyj(GenerationConfig r1, zzaam r2) {
        this.zza = r2;
        zzarm r12 = new zzarm();
        r12.zza(zzarn.zzc);
        this.zzb = r12.zzb();
        this.zzc = zzavk.zza("genai-prompt");
    }

    private final int zzl(GenerateContentRequest r7) {
        PromptPrefix r0 = r7.getPromptPrefix();
        if (r0 == null) goto L7;
        String r02 = r0.getTextString();
        if (r02 == null) goto L7;
        int r03 = r02.length();
    L8:
        Iterator<Content> r72 = r7.getContents().iterator();
        int r2 = 0;
    L10:
        if (r72.hasNext() == false) goto L17;
        Iterator<Part> r3 = r72.next().getParts().iterator();
    L13:
        if (r3.hasNext() == false) goto L10;
        Part r4 = r3.next();
        if ((r4 instanceof TextPart) == false) goto L13;
        r2 = r2 + ((TextPart) r4).getTextString().length();
        goto L13
    L17:
        zzaam r6 = this.zza;
        zzaam r73 = zzaam.zza;
        zzyi r74 = zzyi.zza;
        int r62 = r6.ordinal();
        if (r62 == 0) goto L25;
        if (r62 == 1) goto L25;
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L25:
        return r03 + r2;
    L7:
        r03 = 0;
        goto L8
    }

    private static final zzanu zzm(zzaam r4, zzyi r5, boolean r6) {
        zzaam r0 = zzaam.zza;
        zzyi r02 = zzyi.zza;
        int r42 = r4.ordinal();
        if (r42 == 0) goto L28;
        if (r42 != 1) goto L26;
        int r43 = r5.ordinal();
        if (r43 == 0) goto L25;
        if (r43 == 1) goto L19;
        if (r43 == 2) goto L14;
        if (r43 == 3) goto L11;
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L11:
        return zzanu.zzfa;
    L14:
        if (r6 == false) goto L18;
        return zzanu.zzfe;
    L18:
        return zzanu.zzfb;
    L19:
        if (r6 == false) goto L23;
        return zzanu.zzfd;
    L23:
        return zzanu.zzeY;
    L25:
        return zzanu.zzeZ;
    L26:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L28:
        int r44 = r5.ordinal();
        if (r44 == 0) goto L48;
        if (r44 == 1) goto L42;
        if (r44 == 2) goto L37;
        if (r44 == 3) goto L34;
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L34:
        return zzanu.zzeU;
    L37:
        if (r6 == false) goto L41;
        return zzanu.zzfe;
    L41:
        return zzanu.zzfb;
    L42:
        if (r6 == false) goto L46;
        return zzanu.zzfc;
    L46:
        return zzanu.zzeS;
    L48:
        return zzanu.zzeT;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzaty
    public final void zza() {
        zzanv r0 = new zzanv();
        zzarg r1 = new zzarg();
        r1.zza(this.zzb);
        r0.zzd(r1.zzb());
        zzauu r02 = zzavd.zze(r0);
        zzanu r12 = zzm(this.zza, zzyi.zza, false);
        this.zzc.zza(r02, r12);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzaty
    public final void zzb(int r2, int r3) {
        zze(null, r2, r3);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzaty
    public final void zzc() {
        zzanv r0 = new zzanv();
        zzarc r1 = new zzarc();
        r1.zza(this.zzb);
        r0.zze(r1.zzb());
        zzauu r02 = zzavd.zze(r0);
        zzanu r12 = zzm(this.zza, zzyi.zzd, false);
        this.zzc.zza(r02, r12);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzaty
    public final /* bridge */ /* synthetic */ void zzd(zzars r11, Object r12, Object r13) {
        zzaan r122 = (zzaan) r12;
        GenerateContentResponse r132 = (GenerateContentResponse) r13;
        GenerateContentRequest r0 = r122.zza();
        ArrayList r1 = new ArrayList();
        Iterator<Content> r2 = r0.getContents().iterator();
    L4:
        if (r2.hasNext() == false) goto L11;
        Iterator<Part> r3 = r2.next().getParts().iterator();
    L7:
        if (r3.hasNext() == false) goto L4;
        Part r4 = r3.next();
        if ((r4 instanceof ImagePart) == false) goto L7;
        ImagePart r42 = (ImagePart) r4;
        int r5 = r42.getBitmap().getHeight();
        int r43 = r42.getBitmap().getWidth();
        zzaqz r6 = new zzaqz();
        r6.zza(zzara.zzc);
        r6.zzb(Long.valueOf(r5 * r43));
        r1.add(r6.zzd());
        goto L7
    L11:
        zzaqz r22 = new zzaqz();
        r22.zza(zzara.zzb);
        r22.zzb(Long.valueOf(zzl(r0)));
        PromptPrefix r32 = r0.getPromptPrefix();
        boolean r44 = false;
        if (r32 == null) goto L16;
        String r33 = r32.getTextString();
        if (r33 == null) goto L16;
        int r34 = r33.length();
    L17:
        Iterator<Content> r52 = r0.getContents().iterator();
        int r62 = 0;
    L19:
        if (r52.hasNext() == false) goto L26;
        Iterator<Part> r7 = r52.next().getParts().iterator();
    L22:
        if (r7.hasNext() == false) goto L19;
        Part r8 = r7.next();
        if ((r8 instanceof TextPart) == false) goto L22;
        r62 = r62 + ((TextPart) r8).getTextString().length();
        goto L22
    L26:
        zzasg r53 = new zzasg();
        zzaam r72 = this.zza;
        zzaam r82 = zzaam.zza;
        zzyi r83 = zzyi.zza;
        int r84 = r72.ordinal();
        if (r84 == 0) goto L32;
        if (r84 == 1) goto L32;
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return;
    L32:
        r53.zza(Integer.valueOf(r62 + r34));
        r53.zzb(Integer.valueOf(r34));
        r22.zzc(r53.zzc());
        r1.add(r22.zzd());
        zzavc r23 = this.zzc;
        zzanv r35 = new zzanv();
        zzari r54 = new zzari();
        r54.zza(this.zzb);
        zzark r63 = new zzark();
        r63.zza(Float.valueOf(r0.getTemperature()));
        r63.zzb(Integer.valueOf(r0.getSeed()));
        r63.zzc(Integer.valueOf(r0.getTopK()));
        r63.zzd(Integer.valueOf(r0.getCandidateCount()));
        r63.zze(Integer.valueOf(r0.getMaxOutputTokens()));
        r63.zzf(zzml.zzq(r1));
        r54.zzb(r63.zzg());
        r11.zzd(Integer.valueOf(zzl(r0)));
        Iterator<T> r10 = r132.getCandidates().iterator();
        if (r10.hasNext() == true) goto L35;
        Integer r102 = null;
    L50:
        if (r102 == null) goto L52;
        int r103 = r102.intValue();
    L53:
        r11.zze(Integer.valueOf(r103));
        r54.zzc(r11.zzh());
        r35.zzc(r54.zzd());
        zzauu r104 = zzavd.zze(r35);
        zzyi r112 = zzyi.zzb;
        if (r122.zzb() == null) goto L56;
        r44 = true;
    L56:
        r23.zza(r104, zzm(r72, r112, r44));
        return;
    L52:
        r103 = 0;
        goto L53
    L35:
        String r133 = ((Candidate) r10.next()).getText();
        if (r133 == null) goto L38;
        int r134 = r133.length();
    L39:
        Integer r135 = Integer.valueOf(r134);
    L41:
        if (r10.hasNext() == false) goto L49;
        String r02 = ((Candidate) r10.next()).getText();
        if (r02 == null) goto L45;
        int r03 = r02.length();
    L46:
        Integer r04 = Integer.valueOf(r03);
        if (r135.compareTo(r04) >= 0) goto L41;
        r135 = r04;
        goto L41
    L45:
        r03 = 0;
        goto L46
    L49:
        r102 = r135;
        goto L50
    L38:
        r134 = 0;
    L16:
        r34 = 0;
        goto L17
    }

    public final void zze(zzaan r4, int r5, int r6) {
        zzanv r0 = new zzanv();
        zzari r1 = new zzari();
        r1.zza(this.zzb);
        zzars r2 = new zzars();
        r2.zza(Integer.valueOf(r5));
        r2.zzb(zzaty.zzk(r6));
        r1.zzc(r2.zzh());
        r0.zzc(r1.zzd());
        zzauu r52 = zzavd.zze(r0);
        zzyi r62 = zzyi.zzb;
        if (r4 == null) goto L5;
        KClass r42 = r4.zzb();
    L6:
        zzaam r02 = this.zza;
        zzavc r3 = this.zzc;
        if (r42 == null) goto L9;
        boolean r43 = true;
    L10:
        r3.zza(r52, zzm(r02, r62, r43));
        return;
    L9:
        r43 = false;
        goto L10
    L5:
        r42 = null;
        goto L6
    }

    public final void zzf(zzaan r4, int r5, int r6) {
        zzanv r0 = new zzanv();
        zzare r1 = new zzare();
        r1.zza(this.zzb);
        zzars r2 = new zzars();
        r2.zza(Integer.valueOf(r5));
        r2.zzb(zzaty.zzk(r6));
        r1.zzb(r2.zzh());
        r0.zzf(r1.zzd());
        zzauu r52 = zzavd.zze(r0);
        zzyi r62 = zzyi.zzc;
        if (r4 == null) goto L5;
        KClass r42 = r4.zzb();
    L6:
        zzaam r02 = this.zza;
        zzavc r3 = this.zzc;
        if (r42 == null) goto L9;
        boolean r43 = true;
    L10:
        r3.zza(r52, zzm(r02, r62, r43));
        return;
    L9:
        r43 = false;
        goto L10
    L5:
        r42 = null;
        goto L6
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzaty
    public final void zzg(int r2, int r3) {
        zzf(null, r2, r3);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzaty
    public final /* bridge */ /* synthetic */ void zzh(Object r1, int r2, int r3) {
        zze((zzaan) r1, r2, r3);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzaty
    public final /* bridge */ /* synthetic */ void zzi(zzars r4, Object r5, int r6, Duration r7) {
        zzaan r52 = (zzaan) r5;
        if (r7 == null) goto L5;
        r4.zzf(Long.valueOf(r7.toMillis()));
    L5:
        zzavc r72 = this.zzc;
        zzanv r0 = new zzanv();
        zzare r1 = new zzare();
        r1.zza(this.zzb);
        r1.zzc(Integer.valueOf(r6));
        r4.zzd(Integer.valueOf(zzl(r52.zza())));
        r1.zzb(r4.zzh());
        r0.zzf(r1.zzd());
        zzauu r42 = zzavd.zze(r0);
        zzaam r3 = this.zza;
        zzyi r62 = zzyi.zzc;
        if (r52.zzb() == null) goto L8;
        boolean r53 = true;
    L9:
        r72.zza(r42, zzm(r3, r62, r53));
        return;
    L8:
        r53 = false;
        goto L9
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzaty
    public final /* bridge */ /* synthetic */ void zzj(Object r1, int r2, int r3) {
        zzf((zzaan) r1, r2, r3);
    }
}

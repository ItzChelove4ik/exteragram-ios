package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.stream.Collectors;

/* loaded from: classes4.dex */
public final class zzbt extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbt> CREATOR = null;
    private final byte[] zzA;
    private final int zzB;
    private final int zzC;
    private final long zzD;
    private final long zzE;
    private final int zzF;
    private final int zzG;
    private final int zzH;
    private final int zzI;
    private final int zzJ;
    private final int zzK;
    private final int zzL;
    private final int zzM;
    private final long zzN;
    private final int[] zzO;
    private final int zzP;
    private final String zzQ;
    private final byte[] zzR;
    private final int zzS;
    private final long zzT;
    private final byte[] zzU;
    private final int zzV;
    private final long[] zzW;
    private final long zzX;
    private final long zzY;
    private final int zzZ;
    private final int zza;
    private final int zzaa;
    private final long zzab;
    private final long zzac;
    private final byte[] zzad;
    private final byte[] zzae;
    private final long zzb;
    private final int zzc;
    private final int zzd;
    private final int zze;
    private final long zzf;
    private final int zzg;
    private final int zzh;
    private final int zzi;
    private final long zzj;
    private final long zzk;
    private final long zzl;
    private final long zzm;
    private final long zzn;
    private final long zzo;
    private final int zzp;
    private final int zzq;
    private final int zzr;
    private final int zzs;
    private final boolean zzt;
    private final int zzu;
    private final long zzv;
    private final double zzw;
    private final double zzx;
    private final int zzy;
    private final float zzz;

    static {
        CREATOR = new zzbu();
    }

    public zzbt(int r3, int r4, int r5, int r6, long r7, int r9, int r10, int r11, long r12, long r14, long r16, long r18, long r20, long r22, int r24, int r25, int r26, int r27, boolean r28, int r29, long r30, double r32, double r34, int r36, float r37, byte[] r38, int r39, int r40, long r41, long r43, int r45, int r46, int r47, int r48, int r49, int r50, int r51, int r52, long r53, long r55, int[] r57, int r58, String r59, byte[] r60, int r61, long r62, byte[] r64, int r65, long[] r66, long r67, long r69, int r71, int r72, long r73, long r75, byte[] r77, byte[] r78) {
        this.zza = r3;
        this.zzb = r55;
        this.zzc = r4;
        this.zzd = r5;
        this.zze = r6;
        this.zzf = r7;
        this.zzg = r9;
        this.zzh = r10;
        this.zzi = r11;
        this.zzj = r12;
        this.zzk = r14;
        this.zzl = r16;
        this.zzm = r18;
        this.zzn = r20;
        this.zzo = r22;
        this.zzp = r24;
        this.zzq = r25;
        this.zzr = r26;
        this.zzs = r27;
        this.zzt = r28;
        this.zzu = r29;
        this.zzv = r30;
        this.zzw = r32;
        this.zzx = r34;
        this.zzy = r36;
        this.zzz = r37;
        this.zzA = r38;
        this.zzB = r39;
        this.zzC = r40;
        this.zzD = r41;
        this.zzE = r43;
        this.zzY = r69;
        this.zzF = r45;
        this.zzG = r46;
        this.zzH = r47;
        this.zzI = r48;
        this.zzJ = r49;
        this.zzZ = r71;
        this.zzaa = r72;
        this.zzK = r50;
        this.zzL = r51;
        this.zzM = r52;
        this.zzN = r53;
        this.zzO = r57;
        this.zzP = r58;
        this.zzQ = r59;
        this.zzR = r60;
        this.zzS = r61;
        this.zzT = r62;
        this.zzU = r64;
        this.zzV = r65;
        this.zzW = r66;
        this.zzab = r73;
        this.zzac = r75;
        this.zzX = r67;
        this.zzad = r77;
        this.zzae = r78;
    }

    private static String zza(byte[] r2) {
        zzd r0 = null;
        if (r2 != null) goto L12;
    L8:
        return zzki.zza(r0);
    L10:
        return "InvalidProto";
    L12:
        if (r2.length == 0) goto L8;
        r0 = zzd.zzo(r2, zzawl.zzb());     // Catch: zzaxm -> L10
        goto L8
    }

    public final String toString() {
        zzmn r1 = new zzmn();
        r1.zza("customerId", Integer.valueOf(this.zza));
        r1.zza("callingUserId", Long.valueOf(this.zzb));
        r1.zza("featureType", Integer.valueOf(this.zzc));
        r1.zza("featureVariant", Integer.valueOf(this.zzd));
        r1.zza("status", Integer.valueOf(this.zze));
        r1.zza("inferenceLatencyTotalMillis", Long.valueOf(this.zzf));
        r1.zza("numInputTokens", Integer.valueOf(this.zzg));
        r1.zza("numOutputTokens", Integer.valueOf(this.zzh));
        r1.zza("numDecodeSteps", Integer.valueOf(this.zzi));
        r1.zza("inferenceServiceStartLatencyMillis", Long.valueOf(this.zzj));
        r1.zza("inferenceApiCallHandlingLatencyMillis", Long.valueOf(this.zzk));
        r1.zza("inferenceInputSafetyCheckLatencyMillis", Long.valueOf(this.zzl));
        r1.zza("inferenceInputEncodingLatencyMillis", Long.valueOf(this.zzm));
        r1.zza("inferenceOverallOutputLatencyMillis", Long.valueOf(this.zzn));
        r1.zza("inferenceOutputSafetyCheckLatencyMillis", Long.valueOf(this.zzo));
        r1.zza("inputSafetyCheckPolicyViolationType", Integer.valueOf(this.zzp));
        r1.zza("inputSafetyCheckSuggestedActionType", Integer.valueOf(this.zzq));
        r1.zza("outputSafetyCheckPolicyViolationType", Integer.valueOf(this.zzr));
        r1.zza("outputSafetyCheckSuggestedActionType", Integer.valueOf(this.zzs));
        r1.zza("isModelLoaded", Boolean.valueOf(this.zzt));
        r1.zza("featureId", Integer.valueOf(this.zzu));
        r1.zza("featureVersion", Integer.valueOf(this.zzM));
        r1.zza("modelInferenceLatencyMillis", Long.valueOf(this.zzv));
        r1.zza("outputTokensPerSecond", Double.valueOf(this.zzw));
        r1.zza("inputTokensPerSecond", Double.valueOf(this.zzx));
        r1.zza("numSamples", Integer.valueOf(this.zzy));
        r1.zza("cannedResponsesRatio", Float.valueOf(this.zzz));
        zzje r2 = zzje.zzf(this.zzA, zzawl.zzc());     // Catch: zzaxm -> L5
    L7:
        r1.zza("speculativeDecodeStatistics", "{drafter_id: " + r2.zza() + ", drafter_time: " + r2.zzd().zza() + ", block_efficiency: " + r2.zze() + ", drafter_guesses_per_position: " + ((String) r2.zzc().stream().map(zzbs.zza).collect(Collectors.joining(", "))) + "}");
        r1.zza("numSuffixScoreFiltered", Integer.valueOf(this.zzB));
        r1.zza("numPostDeduped", Integer.valueOf(this.zzC));
        r1.zza("inferenceStatefulSuspensionLatencyMillis", Long.valueOf(this.zzD));
        r1.zza("inferenceStatelessSuspensionLatencyMillis", Long.valueOf(this.zzE));
        r1.zza("inferenceTurnWaitingLatencyMillis", Long.valueOf(this.zzY));
        r1.zza("topCannedResponseIndex", Integer.valueOf(this.zzF));
        r1.zza("inferenceStatefulSuspensionCount", Integer.valueOf(this.zzG));
        r1.zza("inferenceStatefulResumptionCount", Integer.valueOf(this.zzH));
        r1.zza("inferenceStatelessSuspensionCount", Integer.valueOf(this.zzI));
        r1.zza("inferenceStatelessResumptionCount", Integer.valueOf(this.zzJ));
        r1.zza("inferenceTurnWaitingStartedCount", Integer.valueOf(this.zzZ));
        r1.zza("inferenceTurnWaitingCompletedCount", Integer.valueOf(this.zzaa));
        r1.zza("requestKind", Integer.valueOf(this.zzK));
        r1.zza("initialQueuePosition", Integer.valueOf(this.zzL));
        r1.zza("inferenceStartTimestampMillis", Long.valueOf(this.zzN));
        r1.zza("auxiliaryFileTypes", this.zzO);
        r1.zza("callingAppUid", Integer.valueOf(this.zzP));
        r1.zza("baseModelPublicVersionName", this.zzQ);
        zzf r3 = zzf.zzd(this.zzR, zzawl.zzc());     // Catch: zzaxm -> L10
    L12:
        StringBuilder r4 = new StringBuilder("{");
        if (r3.zza() == false) goto L15;
        zzj r32 = r3.zzc();
        r4.append("quota_exceeded_details: {quota_type: ");
        r4.append(r32.zzg() - 1);
        r4.append(", quota_scope: ");
        r4.append(r32.zzh() - 1);
        r4.append(", quota_check_stage: ");
        r4.append(r32.zzi() - 1);
        r4.append(", period_seconds: ");
        r4.append(r32.zza());
        r4.append(", cost_limit: ");
        r4.append(r32.zzc());
        r4.append(", try_again_seconds: ");
        r4.append(r32.zzd());
        r4.append("}");
    L15:
        r4.append("}");
        r1.zza("errorDetails", r4.toString());
        r1.zza("hintType", Integer.valueOf(this.zzS));
        r1.zza("hintLatencyTotalMillis", Long.valueOf(this.zzT));
        zzh r0 = zzh.zzc(this.zzU, zzawl.zzc());     // Catch: zzaxm -> L18
    L20:
        r1.zza("introspectionDetails", "{is_introspection_applied: " + r0.zza() + "}");
        r1.zza("inferenceModelType", Integer.valueOf(this.zzV));
        r1.zza("timeToFirstTokenMillisArray", this.zzW);
        r1.zza("nativeStartTimeNanos", Long.valueOf(this.zzX));
        r1.zza("timeIntervalFromPreviousFeatureUseMillis", Long.valueOf(this.zzab));
        r1.zza("timeIntervalFromPreviousModelUseMillis", Long.valueOf(this.zzac));
        r1.zza("parallelLoadingLatencyStats", zza(this.zzad));
        r1.zza("serialLoadingLatencyStats", zza(this.zzae));
        return "InferenceEventTraceResult\n".concat(r1.zzc().toString());
    L18:
        e = move-exception;
        Log.e("InferenceEventTraceResult", "Failed to parse IntrospectionDetails proto", e);
        r0 = zzh.zzd();
    L10:
        e = move-exception;
        Log.e("InferenceEventTraceResult", "Failed to parse ErrorDetails proto", e);
        r3 = zzf.zze();
    L5:
        e = move-exception;
        Log.e("InferenceEventTraceResult", "Failed to parse SpeculativeDecodeStatistics proto", e);
        r2 = zzje.zzg();
        goto L7
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r6, int r7) {
        int r72 = this.zza;
        int r0 = SafeParcelWriter.beginObjectHeader(r6);
        SafeParcelWriter.writeInt(r6, 1, r72);
        SafeParcelWriter.writeInt(r6, 2, this.zzc);
        SafeParcelWriter.writeInt(r6, 3, this.zzd);
        SafeParcelWriter.writeInt(r6, 4, this.zze);
        SafeParcelWriter.writeLong(r6, 5, this.zzf);
        SafeParcelWriter.writeInt(r6, 6, this.zzg);
        SafeParcelWriter.writeInt(r6, 7, this.zzh);
        SafeParcelWriter.writeInt(r6, 8, this.zzi);
        SafeParcelWriter.writeLong(r6, 9, this.zzj);
        SafeParcelWriter.writeLong(r6, 10, this.zzk);
        SafeParcelWriter.writeLong(r6, 11, this.zzl);
        SafeParcelWriter.writeLong(r6, 12, this.zzm);
        SafeParcelWriter.writeLong(r6, 13, this.zzn);
        SafeParcelWriter.writeLong(r6, 14, this.zzo);
        SafeParcelWriter.writeInt(r6, 15, this.zzp);
        SafeParcelWriter.writeInt(r6, 16, this.zzq);
        SafeParcelWriter.writeInt(r6, 17, this.zzr);
        SafeParcelWriter.writeInt(r6, 18, this.zzs);
        SafeParcelWriter.writeBoolean(r6, 19, this.zzt);
        SafeParcelWriter.writeInt(r6, 20, this.zzu);
        SafeParcelWriter.writeLong(r6, 21, this.zzv);
        SafeParcelWriter.writeDouble(r6, 22, this.zzw);
        SafeParcelWriter.writeDouble(r6, 23, this.zzx);
        SafeParcelWriter.writeInt(r6, 24, this.zzy);
        SafeParcelWriter.writeFloat(r6, 25, this.zzz);
        SafeParcelWriter.writeByteArray(r6, 26, this.zzA, false);
        SafeParcelWriter.writeInt(r6, 27, this.zzB);
        SafeParcelWriter.writeInt(r6, 28, this.zzC);
        SafeParcelWriter.writeLong(r6, 29, this.zzD);
        SafeParcelWriter.writeLong(r6, 30, this.zzE);
        SafeParcelWriter.writeInt(r6, 31, this.zzF);
        SafeParcelWriter.writeInt(r6, 32, this.zzG);
        SafeParcelWriter.writeInt(r6, 33, this.zzH);
        SafeParcelWriter.writeInt(r6, 34, this.zzI);
        SafeParcelWriter.writeInt(r6, 35, this.zzJ);
        SafeParcelWriter.writeInt(r6, 36, this.zzK);
        SafeParcelWriter.writeInt(r6, 37, this.zzL);
        SafeParcelWriter.writeInt(r6, 38, this.zzM);
        SafeParcelWriter.writeLong(r6, 39, this.zzN);
        SafeParcelWriter.writeLong(r6, 40, this.zzb);
        SafeParcelWriter.writeIntArray(r6, 41, this.zzO, false);
        SafeParcelWriter.writeInt(r6, 42, this.zzP);
        SafeParcelWriter.writeString(r6, 43, this.zzQ, false);
        SafeParcelWriter.writeByteArray(r6, 44, this.zzR, false);
        SafeParcelWriter.writeInt(r6, 45, this.zzS);
        SafeParcelWriter.writeLong(r6, 46, this.zzT);
        SafeParcelWriter.writeByteArray(r6, 47, this.zzU, false);
        SafeParcelWriter.writeInt(r6, 48, this.zzV);
        SafeParcelWriter.writeLongArray(r6, 50, this.zzW, false);
        SafeParcelWriter.writeLong(r6, 51, this.zzX);
        SafeParcelWriter.writeLong(r6, 52, this.zzY);
        SafeParcelWriter.writeInt(r6, 53, this.zzZ);
        SafeParcelWriter.writeInt(r6, 54, this.zzaa);
        SafeParcelWriter.writeLong(r6, 55, this.zzab);
        SafeParcelWriter.writeLong(r6, 56, this.zzac);
        SafeParcelWriter.writeByteArray(r6, 57, this.zzad, false);
        SafeParcelWriter.writeByteArray(r6, 58, this.zzae, false);
        SafeParcelWriter.finishObjectHeader(r6, r0);
    }
}

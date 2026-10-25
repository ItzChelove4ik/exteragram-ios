package com.google.android.gms.internal.mlkit_language_id_common;

import com.android.dx.io.Opcodes;
import com.google.android.gms.cast.MediaError;

/* loaded from: classes4.dex */
public enum zzhx extends Enum implements zzak {
    public static final zzhx zzA = null;
    public static final zzhx zzB = null;
    public static final zzhx zzC = null;
    public static final zzhx zzD = null;
    public static final zzhx zzE = null;
    public static final zzhx zzF = null;
    public static final zzhx zzG = null;
    public static final zzhx zzH = null;
    public static final zzhx zzI = null;
    public static final zzhx zzJ = null;
    public static final zzhx zzK = null;
    public static final zzhx zzL = null;
    public static final zzhx zzM = null;
    public static final zzhx zzN = null;
    public static final zzhx zzO = null;
    public static final zzhx zzP = null;
    public static final zzhx zzQ = null;
    public static final zzhx zzR = null;
    public static final zzhx zzS = null;
    public static final zzhx zzT = null;
    public static final zzhx zzU = null;
    public static final zzhx zzV = null;
    private static final /* synthetic */ zzhx[] zzW = null;
    public static final zzhx zza = null;
    public static final zzhx zzb = null;
    public static final zzhx zzc = null;
    public static final zzhx zzd = null;
    public static final zzhx zze = null;
    public static final zzhx zzf = null;
    public static final zzhx zzg = null;
    public static final zzhx zzh = null;
    public static final zzhx zzi = null;
    public static final zzhx zzj = null;
    public static final zzhx zzk = null;
    public static final zzhx zzl = null;
    public static final zzhx zzm = null;
    public static final zzhx zzn = null;
    public static final zzhx zzo = null;
    public static final zzhx zzp = null;
    public static final zzhx zzq = null;
    public static final zzhx zzr = null;
    public static final zzhx zzs = null;
    public static final zzhx zzt = null;
    public static final zzhx zzu = null;
    public static final zzhx zzv = null;
    public static final zzhx zzw = null;
    public static final zzhx zzx = null;
    public static final zzhx zzy = null;
    public static final zzhx zzz = null;
    private final int zzX;

    static {
        zzhx r1 = new zzhx("NO_ERROR", 0, 0);
        zza = r1;
        zzhx r2 = new zzhx("INCOMPATIBLE_INPUT", 1, 1);
        zzb = r2;
        zzhx r3 = new zzhx("INCOMPATIBLE_OUTPUT", 2, 2);
        zzc = r3;
        zzhx r4 = new zzhx("INCOMPATIBLE_TFLITE_VERSION", 3, 3);
        zzd = r4;
        zzhx r5 = new zzhx("MISSING_OP", 4, 4);
        zze = r5;
        zzhx r6 = new zzhx("DATA_TYPE_ERROR", 5, 6);
        zzf = r6;
        zzhx r0 = new zzhx("TFLITE_INTERNAL_ERROR", 6, 7);
        zzg = r0;
        zzhx r8 = new zzhx("TFLITE_UNKNOWN_ERROR", 7, 8);
        zzh = r8;
        zzhx r9 = new zzhx("MEDIAPIPE_ERROR", 8, 9);
        zzi = r9;
        zzhx r10 = new zzhx("TIME_OUT_FETCHING_MODEL_METADATA", 9, 5);
        zzj = r10;
        zzhx r11 = new zzhx("MODEL_NOT_DOWNLOADED", 10, 100);
        zzk = r11;
        zzhx r12 = new zzhx("URI_EXPIRED", 11, 101);
        zzl = r12;
        zzhx r13 = new zzhx("NO_NETWORK_CONNECTION", 12, 102);
        zzm = r13;
        zzhx r14 = new zzhx("METERED_NETWORK", 13, 103);
        zzn = r14;
        zzhx r15 = new zzhx("DOWNLOAD_FAILED", 14, 104);
        zzo = r15;
        zzhx r02 = new zzhx("MODEL_INFO_DOWNLOAD_UNSUCCESSFUL_HTTP_STATUS", 15, 105);
        zzp = r02;
        zzhx r16 = new zzhx("MODEL_INFO_DOWNLOAD_NO_HASH", 16, 106);
        zzq = r16;
        zzhx r03 = new zzhx("MODEL_INFO_DOWNLOAD_CONNECTION_FAILED", 17, 107);
        zzr = r03;
        zzhx r17 = new zzhx("NO_VALID_MODEL", 18, 108);
        zzs = r17;
        zzhx r04 = new zzhx("LOCAL_MODEL_INVALID", 19, 109);
        zzt = r04;
        zzhx r18 = new zzhx("REMOTE_MODEL_INVALID", 20, 110);
        zzu = r18;
        zzhx r05 = new zzhx("REMOTE_MODEL_LOADER_ERROR", 21, 111);
        zzv = r05;
        zzhx r19 = new zzhx("REMOTE_MODEL_LOADER_LOADS_NO_MODEL", 22, 112);
        zzw = r19;
        zzhx r06 = new zzhx("SMART_REPLY_LANG_ID_DETECTAION_FAILURE", 23, 113);
        zzx = r06;
        zzhx r110 = new zzhx("MODEL_NOT_REGISTERED", 24, 114);
        zzy = r110;
        zzhx r07 = new zzhx("MODEL_TYPE_MISUSE", 25, 115);
        zzz = r07;
        zzhx r111 = new zzhx("MODEL_HASH_MISMATCH", 26, 116);
        zzA = r111;
        zzhx r08 = new zzhx("OPTIONAL_MODULE_NOT_AVAILABLE", 27, 201);
        zzB = r08;
        zzhx r112 = new zzhx("OPTIONAL_MODULE_INIT_ERROR", 28, 202);
        zzC = r112;
        zzhx r09 = new zzhx("OPTIONAL_MODULE_INFERENCE_ERROR", 29, 203);
        zzD = r09;
        zzhx r113 = new zzhx("OPTIONAL_MODULE_RELEASE_ERROR", 30, Opcodes.SUB_DOUBLE_2ADDR);
        zzE = r113;
        zzhx r010 = new zzhx("OPTIONAL_TFLITE_MODULE_INIT_ERROR", 31, Opcodes.MUL_DOUBLE_2ADDR);
        zzF = r010;
        zzhx r114 = new zzhx("NATIVE_LIBRARY_LOAD_ERROR", 32, Opcodes.DIV_DOUBLE_2ADDR);
        zzG = r114;
        zzhx r011 = new zzhx("OPTIONAL_MODULE_CREATE_ERROR", 33, Opcodes.REM_DOUBLE_2ADDR);
        zzH = r011;
        zzhx r115 = new zzhx("CAMERAX_SOURCE_ERROR", 34, MediaError.DetailedErrorCode.SEGMENT_NETWORK);
        zzI = r115;
        zzhx r012 = new zzhx("CAMERA1_SOURCE_CANT_START_ERROR", 35, 302);
        zzJ = r012;
        zzhx r116 = new zzhx("CAMERA1_SOURCE_NO_SUITABLE_SIZE_ERROR", 36, 303);
        zzK = r116;
        zzhx r013 = new zzhx("CAMERA1_SOURCE_NO_SUITABLE_FPS_ERROR", 37, 304);
        zzL = r013;
        zzhx r117 = new zzhx("CAMERA1_SOURCE_NO_BYTE_SOURCE_FOUND_ERROR", 38, 305);
        zzM = r117;
        zzhx r014 = new zzhx("CODE_SCANNER_UNAVAILABLE", 39, MediaError.DetailedErrorCode.MANIFEST_UNKNOWN);
        zzN = r014;
        zzhx r118 = new zzhx("CODE_SCANNER_CANCELLED", 40, 401);
        zzO = r118;
        zzhx r015 = new zzhx("CODE_SCANNER_CAMERA_PERMISSION_NOT_GRANTED", 41, 402);
        zzP = r015;
        zzhx r119 = new zzhx("CODE_SCANNER_APP_NAME_UNAVAILABLE", 42, 403);
        zzQ = r119;
        zzhx r016 = new zzhx("CODE_SCANNER_TASK_IN_PROGRESS", 43, 404);
        zzR = r016;
        zzhx r120 = new zzhx("CODE_SCANNER_PIPELINE_INITIALIZATION_ERROR", 44, 405);
        zzS = r120;
        zzhx r017 = new zzhx("CODE_SCANNER_PIPELINE_INFERENCE_ERROR", 45, 406);
        zzT = r017;
        zzhx r121 = new zzhx("CODE_SCANNER_GOOGLE_PLAY_SERVICES_VERSION_TOO_OLD", 46, 407);
        zzU = r121;
        zzhx r018 = new zzhx("UNKNOWN_ERROR", 47, 9999);
        zzV = r018;
        zzW = new zzhx[]{r1, r2, r3, r4, r5, r6, r0, r8, r9, r10, r11, r12, r13, r14, r15, r02, r16, r03, r17, r04, r18, r05, r19, r06, r110, r07, r111, r08, r112, r09, r113, r010, r114, r011, r115, r012, r116, r013, r117, r014, r118, r015, r119, r016, r120, r017, r121, r018};
    }

    zzhx(String r1, int r2, int r3) {
        this.zzX = r3;
    }

    public static zzhx[] values() {
        return (zzhx[]) zzW.clone();
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzak
    public final int zza() {
        return this.zzX;
    }
}

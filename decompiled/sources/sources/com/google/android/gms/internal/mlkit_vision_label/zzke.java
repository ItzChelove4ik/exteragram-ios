package com.google.android.gms.internal.mlkit_vision_label;

import com.android.dx.io.Opcodes;
import com.google.android.gms.cast.MediaError;

/* loaded from: classes4.dex */
public enum zzke extends Enum implements zzck {
    public static final zzke zzA = null;
    public static final zzke zzB = null;
    public static final zzke zzC = null;
    public static final zzke zzD = null;
    public static final zzke zzE = null;
    public static final zzke zzF = null;
    public static final zzke zzG = null;
    public static final zzke zzH = null;
    public static final zzke zzI = null;
    public static final zzke zzJ = null;
    public static final zzke zzK = null;
    public static final zzke zzL = null;
    public static final zzke zzM = null;
    public static final zzke zzN = null;
    public static final zzke zzO = null;
    public static final zzke zzP = null;
    public static final zzke zzQ = null;
    public static final zzke zzR = null;
    public static final zzke zzS = null;
    public static final zzke zzT = null;
    public static final zzke zzU = null;
    public static final zzke zzV = null;
    private static final /* synthetic */ zzke[] zzW = null;
    public static final zzke zza = null;
    public static final zzke zzb = null;
    public static final zzke zzc = null;
    public static final zzke zzd = null;
    public static final zzke zze = null;
    public static final zzke zzf = null;
    public static final zzke zzg = null;
    public static final zzke zzh = null;
    public static final zzke zzi = null;
    public static final zzke zzj = null;
    public static final zzke zzk = null;
    public static final zzke zzl = null;
    public static final zzke zzm = null;
    public static final zzke zzn = null;
    public static final zzke zzo = null;
    public static final zzke zzp = null;
    public static final zzke zzq = null;
    public static final zzke zzr = null;
    public static final zzke zzs = null;
    public static final zzke zzt = null;
    public static final zzke zzu = null;
    public static final zzke zzv = null;
    public static final zzke zzw = null;
    public static final zzke zzx = null;
    public static final zzke zzy = null;
    public static final zzke zzz = null;
    private final int zzX;

    static {
        zzke r1 = new zzke("NO_ERROR", 0, 0);
        zza = r1;
        zzke r2 = new zzke("INCOMPATIBLE_INPUT", 1, 1);
        zzb = r2;
        zzke r3 = new zzke("INCOMPATIBLE_OUTPUT", 2, 2);
        zzc = r3;
        zzke r4 = new zzke("INCOMPATIBLE_TFLITE_VERSION", 3, 3);
        zzd = r4;
        zzke r5 = new zzke("MISSING_OP", 4, 4);
        zze = r5;
        zzke r6 = new zzke("DATA_TYPE_ERROR", 5, 6);
        zzf = r6;
        zzke r0 = new zzke("TFLITE_INTERNAL_ERROR", 6, 7);
        zzg = r0;
        zzke r8 = new zzke("TFLITE_UNKNOWN_ERROR", 7, 8);
        zzh = r8;
        zzke r9 = new zzke("MEDIAPIPE_ERROR", 8, 9);
        zzi = r9;
        zzke r10 = new zzke("TIME_OUT_FETCHING_MODEL_METADATA", 9, 5);
        zzj = r10;
        zzke r11 = new zzke("MODEL_NOT_DOWNLOADED", 10, 100);
        zzk = r11;
        zzke r12 = new zzke("URI_EXPIRED", 11, 101);
        zzl = r12;
        zzke r13 = new zzke("NO_NETWORK_CONNECTION", 12, 102);
        zzm = r13;
        zzke r14 = new zzke("METERED_NETWORK", 13, 103);
        zzn = r14;
        zzke r15 = new zzke("DOWNLOAD_FAILED", 14, 104);
        zzo = r15;
        zzke r02 = new zzke("MODEL_INFO_DOWNLOAD_UNSUCCESSFUL_HTTP_STATUS", 15, 105);
        zzp = r02;
        zzke r16 = new zzke("MODEL_INFO_DOWNLOAD_NO_HASH", 16, 106);
        zzq = r16;
        zzke r03 = new zzke("MODEL_INFO_DOWNLOAD_CONNECTION_FAILED", 17, 107);
        zzr = r03;
        zzke r17 = new zzke("NO_VALID_MODEL", 18, 108);
        zzs = r17;
        zzke r04 = new zzke("LOCAL_MODEL_INVALID", 19, 109);
        zzt = r04;
        zzke r18 = new zzke("REMOTE_MODEL_INVALID", 20, 110);
        zzu = r18;
        zzke r05 = new zzke("REMOTE_MODEL_LOADER_ERROR", 21, 111);
        zzv = r05;
        zzke r19 = new zzke("REMOTE_MODEL_LOADER_LOADS_NO_MODEL", 22, 112);
        zzw = r19;
        zzke r06 = new zzke("SMART_REPLY_LANG_ID_DETECTAION_FAILURE", 23, 113);
        zzx = r06;
        zzke r110 = new zzke("MODEL_NOT_REGISTERED", 24, 114);
        zzy = r110;
        zzke r07 = new zzke("MODEL_TYPE_MISUSE", 25, 115);
        zzz = r07;
        zzke r111 = new zzke("MODEL_HASH_MISMATCH", 26, 116);
        zzA = r111;
        zzke r08 = new zzke("OPTIONAL_MODULE_NOT_AVAILABLE", 27, 201);
        zzB = r08;
        zzke r112 = new zzke("OPTIONAL_MODULE_INIT_ERROR", 28, 202);
        zzC = r112;
        zzke r09 = new zzke("OPTIONAL_MODULE_INFERENCE_ERROR", 29, 203);
        zzD = r09;
        zzke r113 = new zzke("OPTIONAL_MODULE_RELEASE_ERROR", 30, Opcodes.SUB_DOUBLE_2ADDR);
        zzE = r113;
        zzke r010 = new zzke("OPTIONAL_TFLITE_MODULE_INIT_ERROR", 31, Opcodes.MUL_DOUBLE_2ADDR);
        zzF = r010;
        zzke r114 = new zzke("NATIVE_LIBRARY_LOAD_ERROR", 32, Opcodes.DIV_DOUBLE_2ADDR);
        zzG = r114;
        zzke r011 = new zzke("OPTIONAL_MODULE_CREATE_ERROR", 33, Opcodes.REM_DOUBLE_2ADDR);
        zzH = r011;
        zzke r115 = new zzke("CAMERAX_SOURCE_ERROR", 34, MediaError.DetailedErrorCode.SEGMENT_NETWORK);
        zzI = r115;
        zzke r012 = new zzke("CAMERA1_SOURCE_CANT_START_ERROR", 35, 302);
        zzJ = r012;
        zzke r116 = new zzke("CAMERA1_SOURCE_NO_SUITABLE_SIZE_ERROR", 36, 303);
        zzK = r116;
        zzke r013 = new zzke("CAMERA1_SOURCE_NO_SUITABLE_FPS_ERROR", 37, 304);
        zzL = r013;
        zzke r117 = new zzke("CAMERA1_SOURCE_NO_BYTE_SOURCE_FOUND_ERROR", 38, 305);
        zzM = r117;
        zzke r014 = new zzke("CODE_SCANNER_UNAVAILABLE", 39, MediaError.DetailedErrorCode.MANIFEST_UNKNOWN);
        zzN = r014;
        zzke r118 = new zzke("CODE_SCANNER_CANCELLED", 40, 401);
        zzO = r118;
        zzke r015 = new zzke("CODE_SCANNER_CAMERA_PERMISSION_NOT_GRANTED", 41, 402);
        zzP = r015;
        zzke r119 = new zzke("CODE_SCANNER_APP_NAME_UNAVAILABLE", 42, 403);
        zzQ = r119;
        zzke r016 = new zzke("CODE_SCANNER_TASK_IN_PROGRESS", 43, 404);
        zzR = r016;
        zzke r120 = new zzke("CODE_SCANNER_PIPELINE_INITIALIZATION_ERROR", 44, 405);
        zzS = r120;
        zzke r017 = new zzke("CODE_SCANNER_PIPELINE_INFERENCE_ERROR", 45, 406);
        zzT = r017;
        zzke r121 = new zzke("CODE_SCANNER_GOOGLE_PLAY_SERVICES_VERSION_TOO_OLD", 46, 407);
        zzU = r121;
        zzke r018 = new zzke("UNKNOWN_ERROR", 47, 9999);
        zzV = r018;
        zzW = new zzke[]{r1, r2, r3, r4, r5, r6, r0, r8, r9, r10, r11, r12, r13, r14, r15, r02, r16, r03, r17, r04, r18, r05, r19, r06, r110, r07, r111, r08, r112, r09, r113, r010, r114, r011, r115, r012, r116, r013, r117, r014, r118, r015, r119, r016, r120, r017, r121, r018};
    }

    zzke(String r1, int r2, int r3) {
        this.zzX = r3;
    }

    public static zzke[] values() {
        return (zzke[]) zzW.clone();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_label.zzck
    public final int zza() {
        return this.zzX;
    }
}

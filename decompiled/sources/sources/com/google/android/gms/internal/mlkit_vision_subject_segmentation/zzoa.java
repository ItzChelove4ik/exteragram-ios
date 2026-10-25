package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import com.android.dx.io.Opcodes;
import com.google.android.gms.cast.MediaError;

/* loaded from: classes4.dex */
public enum zzoa extends Enum implements zzcb {
    public static final zzoa zzA = null;
    public static final zzoa zzB = null;
    public static final zzoa zzC = null;
    public static final zzoa zzD = null;
    public static final zzoa zzE = null;
    public static final zzoa zzF = null;
    public static final zzoa zzG = null;
    public static final zzoa zzH = null;
    public static final zzoa zzI = null;
    public static final zzoa zzJ = null;
    public static final zzoa zzK = null;
    public static final zzoa zzL = null;
    public static final zzoa zzM = null;
    public static final zzoa zzN = null;
    public static final zzoa zzO = null;
    public static final zzoa zzP = null;
    public static final zzoa zzQ = null;
    public static final zzoa zzR = null;
    public static final zzoa zzS = null;
    public static final zzoa zzT = null;
    public static final zzoa zzU = null;
    public static final zzoa zzV = null;
    public static final zzoa zzW = null;
    public static final zzoa zzX = null;
    public static final zzoa zzY = null;
    public static final zzoa zzZ = null;
    public static final zzoa zza = null;
    public static final zzoa zzaa = null;
    public static final zzoa zzab = null;
    private static final /* synthetic */ zzoa[] zzac = null;
    public static final zzoa zzb = null;
    public static final zzoa zzc = null;
    public static final zzoa zzd = null;
    public static final zzoa zze = null;
    public static final zzoa zzf = null;
    public static final zzoa zzg = null;
    public static final zzoa zzh = null;
    public static final zzoa zzi = null;
    public static final zzoa zzj = null;
    public static final zzoa zzk = null;
    public static final zzoa zzl = null;
    public static final zzoa zzm = null;
    public static final zzoa zzn = null;
    public static final zzoa zzo = null;
    public static final zzoa zzp = null;
    public static final zzoa zzq = null;
    public static final zzoa zzr = null;
    public static final zzoa zzs = null;
    public static final zzoa zzt = null;
    public static final zzoa zzu = null;
    public static final zzoa zzv = null;
    public static final zzoa zzw = null;
    public static final zzoa zzx = null;
    public static final zzoa zzy = null;
    public static final zzoa zzz = null;
    private final int zzad;

    static {
        zzoa r1 = new zzoa("NO_ERROR", 0, 0);
        zza = r1;
        zzoa r2 = new zzoa("INCOMPATIBLE_INPUT", 1, 1);
        zzb = r2;
        zzoa r3 = new zzoa("INCOMPATIBLE_OUTPUT", 2, 2);
        zzc = r3;
        zzoa r4 = new zzoa("INCOMPATIBLE_TFLITE_VERSION", 3, 3);
        zzd = r4;
        zzoa r5 = new zzoa("MISSING_OP", 4, 4);
        zze = r5;
        zzoa r6 = new zzoa("DATA_TYPE_ERROR", 5, 6);
        zzf = r6;
        zzoa r0 = new zzoa("TFLITE_INTERNAL_ERROR", 6, 7);
        zzg = r0;
        zzoa r8 = new zzoa("TFLITE_UNKNOWN_ERROR", 7, 8);
        zzh = r8;
        zzoa r9 = new zzoa("MEDIAPIPE_ERROR", 8, 9);
        zzi = r9;
        zzoa r10 = new zzoa("TIME_OUT_FETCHING_MODEL_METADATA", 9, 5);
        zzj = r10;
        zzoa r11 = new zzoa("MODEL_NOT_DOWNLOADED", 10, 100);
        zzk = r11;
        zzoa r12 = new zzoa("URI_EXPIRED", 11, 101);
        zzl = r12;
        zzoa r13 = new zzoa("NO_NETWORK_CONNECTION", 12, 102);
        zzm = r13;
        zzoa r14 = new zzoa("METERED_NETWORK", 13, 103);
        zzn = r14;
        zzoa r15 = new zzoa("DOWNLOAD_FAILED", 14, 104);
        zzo = r15;
        zzoa r02 = new zzoa("MODEL_INFO_DOWNLOAD_UNSUCCESSFUL_HTTP_STATUS", 15, 105);
        zzp = r02;
        zzoa r16 = new zzoa("MODEL_INFO_DOWNLOAD_NO_HASH", 16, 106);
        zzq = r16;
        zzoa r03 = new zzoa("MODEL_INFO_DOWNLOAD_CONNECTION_FAILED", 17, 107);
        zzr = r03;
        zzoa r17 = new zzoa("NO_VALID_MODEL", 18, 108);
        zzs = r17;
        zzoa r04 = new zzoa("LOCAL_MODEL_INVALID", 19, 109);
        zzt = r04;
        zzoa r18 = new zzoa("REMOTE_MODEL_INVALID", 20, 110);
        zzu = r18;
        zzoa r05 = new zzoa("REMOTE_MODEL_LOADER_ERROR", 21, 111);
        zzv = r05;
        zzoa r19 = new zzoa("REMOTE_MODEL_LOADER_LOADS_NO_MODEL", 22, 112);
        zzw = r19;
        zzoa r06 = new zzoa("SMART_REPLY_LANG_ID_DETECTAION_FAILURE", 23, 113);
        zzx = r06;
        zzoa r110 = new zzoa("MODEL_NOT_REGISTERED", 24, 114);
        zzy = r110;
        zzoa r07 = new zzoa("MODEL_TYPE_MISUSE", 25, 115);
        zzz = r07;
        zzoa r111 = new zzoa("MODEL_HASH_MISMATCH", 26, 116);
        zzA = r111;
        zzoa r08 = new zzoa("OPTIONAL_MODULE_NOT_AVAILABLE", 27, 201);
        zzB = r08;
        zzoa r112 = new zzoa("OPTIONAL_MODULE_INIT_ERROR", 28, 202);
        zzC = r112;
        zzoa r09 = new zzoa("OPTIONAL_MODULE_INFERENCE_ERROR", 29, 203);
        zzD = r09;
        zzoa r113 = new zzoa("OPTIONAL_MODULE_RELEASE_ERROR", 30, Opcodes.SUB_DOUBLE_2ADDR);
        zzE = r113;
        zzoa r010 = new zzoa("OPTIONAL_TFLITE_MODULE_INIT_ERROR", 31, Opcodes.MUL_DOUBLE_2ADDR);
        zzF = r010;
        zzoa r114 = new zzoa("NATIVE_LIBRARY_LOAD_ERROR", 32, Opcodes.DIV_DOUBLE_2ADDR);
        zzG = r114;
        zzoa r011 = new zzoa("OPTIONAL_MODULE_CREATE_ERROR", 33, Opcodes.REM_DOUBLE_2ADDR);
        zzH = r011;
        zzoa r115 = new zzoa("CAMERAX_SOURCE_ERROR", 34, MediaError.DetailedErrorCode.SEGMENT_NETWORK);
        zzI = r115;
        zzoa r012 = new zzoa("CAMERA1_SOURCE_CANT_START_ERROR", 35, 302);
        zzJ = r012;
        zzoa r116 = new zzoa("CAMERA1_SOURCE_NO_SUITABLE_SIZE_ERROR", 36, 303);
        zzK = r116;
        zzoa r013 = new zzoa("CAMERA1_SOURCE_NO_SUITABLE_FPS_ERROR", 37, 304);
        zzL = r013;
        zzoa r117 = new zzoa("CAMERA1_SOURCE_NO_BYTE_SOURCE_FOUND_ERROR", 38, 305);
        zzM = r117;
        zzoa r014 = new zzoa("CODE_SCANNER_UNAVAILABLE", 39, MediaError.DetailedErrorCode.MANIFEST_UNKNOWN);
        zzN = r014;
        zzoa r118 = new zzoa("CODE_SCANNER_CANCELLED", 40, 401);
        zzO = r118;
        zzoa r015 = new zzoa("CODE_SCANNER_CAMERA_PERMISSION_NOT_GRANTED", 41, 402);
        zzP = r015;
        zzoa r119 = new zzoa("CODE_SCANNER_APP_NAME_UNAVAILABLE", 42, 403);
        zzQ = r119;
        zzoa r016 = new zzoa("CODE_SCANNER_TASK_IN_PROGRESS", 43, 404);
        zzR = r016;
        zzoa r120 = new zzoa("CODE_SCANNER_PIPELINE_INITIALIZATION_ERROR", 44, 405);
        zzS = r120;
        zzoa r017 = new zzoa("CODE_SCANNER_PIPELINE_INFERENCE_ERROR", 45, 406);
        zzT = r017;
        zzoa r121 = new zzoa("CODE_SCANNER_GOOGLE_PLAY_SERVICES_VERSION_TOO_OLD", 46, 407);
        zzU = r121;
        zzoa r018 = new zzoa("LOW_LIGHT_AUTO_EXPOSURE_COMPUTATION_FAILURE", 47, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);
        zzV = r018;
        zzoa r122 = new zzoa("LOW_LIGHT_IMAGE_CAPTURE_PROCESSING_FAILURE", 48, 501);
        zzW = r122;
        zzoa r019 = new zzoa("PERMISSION_DENIED", 49, MediaError.DetailedErrorCode.TEXT_UNKNOWN);
        zzX = r019;
        zzoa r123 = new zzoa("CANCELLED", 50, 601);
        zzY = r123;
        zzoa r020 = new zzoa("GOOGLE_PLAY_SERVICES_VERSION_TOO_OLD", 51, 602);
        zzZ = r020;
        zzoa r124 = new zzoa("LOW_MEMORY", 52, 603);
        zzaa = r124;
        zzoa r021 = new zzoa("UNKNOWN_ERROR", 53, 9999);
        zzab = r021;
        zzac = new zzoa[]{r1, r2, r3, r4, r5, r6, r0, r8, r9, r10, r11, r12, r13, r14, r15, r02, r16, r03, r17, r04, r18, r05, r19, r06, r110, r07, r111, r08, r112, r09, r113, r010, r114, r011, r115, r012, r116, r013, r117, r014, r118, r015, r119, r016, r120, r017, r121, r018, r122, r019, r123, r020, r124, r021};
    }

    zzoa(String r1, int r2, int r3) {
        this.zzad = r3;
    }

    public static zzoa[] values() {
        return (zzoa[]) zzac.clone();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzcb
    public final int zza() {
        return this.zzad;
    }
}

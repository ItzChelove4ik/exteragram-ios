package com.google.mlkit.common.sdkinternal;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.OptionalModuleApi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.moduleinstall.ModuleAvailabilityResponse;
import com.google.android.gms.common.moduleinstall.ModuleInstall;
import com.google.android.gms.common.moduleinstall.ModuleInstallRequest;
import com.google.android.gms.internal.mlkit_common.zzaf;
import com.google.android.gms.internal.mlkit_common.zzah;
import com.google.android.gms.internal.mlkit_common.zzai;
import com.google.android.gms.tasks.Tasks;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public abstract class OptionalModuleUtils {
    public static final Feature[] EMPTY_FEATURES = null;
    public static final Feature FEATURE_BARCODE = null;
    public static final Feature FEATURE_CUSTOM_ICA = null;
    public static final Feature FEATURE_DOCSCAN_CROP = null;
    public static final Feature FEATURE_DOCSCAN_DETECT = null;
    public static final Feature FEATURE_DOCSCAN_ENHANCE = null;
    public static final Feature FEATURE_DOCSCAN_SHADOW_REMOVAL = null;
    public static final Feature FEATURE_DOCSCAN_STAIN_REMOVAL = null;
    public static final Feature FEATURE_DOCSCAN_UI = null;
    public static final Feature FEATURE_FACE = null;
    public static final Feature FEATURE_ICA = null;
    public static final Feature FEATURE_IMAGE_CAPTION = null;
    public static final Feature FEATURE_IMAGE_QUALITY_AESTHETIC = null;
    public static final Feature FEATURE_IMAGE_QUALITY_TECHNICAL = null;
    public static final Feature FEATURE_LANGID = null;
    public static final Feature FEATURE_MLKIT_BARCODE_UI = null;
    public static final Feature FEATURE_NLCLASSIFIER = null;
    public static final Feature FEATURE_OCR = null;
    public static final Feature FEATURE_OCR_CHINESE = null;
    public static final Feature FEATURE_OCR_COMMON = null;
    public static final Feature FEATURE_OCR_DEVANAGARI = null;
    public static final Feature FEATURE_OCR_JAPANESE = null;
    public static final Feature FEATURE_OCR_KOREAN = null;
    public static final Feature FEATURE_SMART_REPLY = null;
    public static final Feature FEATURE_SUBJECT_SEGMENTATION = null;
    public static final Feature FEATURE_TFLITE_DYNAMITE = null;
    private static final zzai zza = null;
    private static final zzai zzb = null;

    static {
        EMPTY_FEATURES = new Feature[0];
        Feature r0 = new Feature("vision.barcode", 1);
        FEATURE_BARCODE = r0;
        Feature r1 = new Feature("vision.custom.ica", 1);
        FEATURE_CUSTOM_ICA = r1;
        Feature r4 = new Feature("vision.face", 1);
        FEATURE_FACE = r4;
        Feature r5 = new Feature("vision.ica", 1);
        FEATURE_ICA = r5;
        Feature r6 = new Feature("vision.ocr", 1);
        FEATURE_OCR = r6;
        FEATURE_OCR_CHINESE = new Feature("mlkit.ocr.chinese", 1);
        FEATURE_OCR_COMMON = new Feature("mlkit.ocr.common", 1);
        FEATURE_OCR_DEVANAGARI = new Feature("mlkit.ocr.devanagari", 1);
        FEATURE_OCR_JAPANESE = new Feature("mlkit.ocr.japanese", 1);
        FEATURE_OCR_KOREAN = new Feature("mlkit.ocr.korean", 1);
        Feature r7 = new Feature("mlkit.langid", 1);
        FEATURE_LANGID = r7;
        Feature r8 = new Feature("mlkit.nlclassifier", 1);
        FEATURE_NLCLASSIFIER = r8;
        Feature r9 = new Feature("tflite_dynamite", 1);
        FEATURE_TFLITE_DYNAMITE = r9;
        Feature r11 = new Feature("mlkit.barcode.ui", 1);
        FEATURE_MLKIT_BARCODE_UI = r11;
        Feature r12 = new Feature("mlkit.smartreply", 1);
        FEATURE_SMART_REPLY = r12;
        FEATURE_IMAGE_CAPTION = new Feature("mlkit.image.caption", 1);
        FEATURE_DOCSCAN_DETECT = new Feature("mlkit.docscan.detect", 1);
        FEATURE_DOCSCAN_CROP = new Feature("mlkit.docscan.crop", 1);
        FEATURE_DOCSCAN_ENHANCE = new Feature("mlkit.docscan.enhance", 1);
        FEATURE_DOCSCAN_UI = new Feature("mlkit.docscan.ui", 1);
        FEATURE_DOCSCAN_STAIN_REMOVAL = new Feature("mlkit.docscan.stain", 1);
        FEATURE_DOCSCAN_SHADOW_REMOVAL = new Feature("mlkit.docscan.shadow", 1);
        FEATURE_IMAGE_QUALITY_AESTHETIC = new Feature("mlkit.quality.aesthetic", 1);
        FEATURE_IMAGE_QUALITY_TECHNICAL = new Feature("mlkit.quality.technical", 1);
        FEATURE_SUBJECT_SEGMENTATION = new Feature("mlkit.segmentation.subject", 1);
        zzah r2 = new zzah();
        r2.zza("barcode", r0);
        r2.zza("custom_ica", r1);
        r2.zza("face", r4);
        r2.zza("ica", r5);
        r2.zza("ocr", r6);
        r2.zza("langid", r7);
        r2.zza("nlclassifier", r8);
        r2.zza("tflite_dynamite", r9);
        r2.zza("barcode_ui", r11);
        r2.zza("smart_reply", r12);
        zza = r2.zzb();
        zzah r22 = new zzah();
        r22.zza("com.google.android.gms.vision.barcode", r0);
        r22.zza("com.google.android.gms.vision.custom.ica", r1);
        r22.zza("com.google.android.gms.vision.face", r4);
        r22.zza("com.google.android.gms.vision.ica", r5);
        r22.zza("com.google.android.gms.vision.ocr", r6);
        r22.zza("com.google.android.gms.mlkit.langid", r7);
        r22.zza("com.google.android.gms.mlkit.nlclassifier", r8);
        r22.zza("com.google.android.gms.tflite_dynamite", r9);
        r22.zza("com.google.android.gms.mlkit_smartreply", r12);
        zzb = r22.zzb();
    }

    public static boolean areAllRequiredModulesAvailable(Context r2, Feature[] r3) {
        return ((ModuleAvailabilityResponse) Tasks.await(ModuleInstall.getClient(r2).areModulesAvailable(new OptionalModuleApi[]{new zzq(r3)}).addOnFailureListener(new zzr()))).areModulesAvailable();
    L7:
        e = move-exception;
        Log.e("OptionalModuleUtils", "Failed to complete the task of features availability check", e);
        return false;
    }

    @Deprecated
    public static void requestDownload(Context r0, String r1) {
        requestDownload(r0, zzaf.zzh(r1));
    }

    @Deprecated
    public static void requestDownload(Context r3, List<String> r4) {
        if (GoogleApiAvailabilityLight.getInstance().getApkVersion(r3) < 221500000) goto L6;
        requestDownload(r3, zza(zza, r4));
        return;
    L6:
        Intent r0 = new Intent();
        r0.setClassName("com.google.android.gms", "com.google.android.gms.vision.DependencyBroadcastReceiverProxy");
        r0.setAction("com.google.android.gms.vision.DEPENDENCY");
        r0.putExtra("com.google.android.gms.vision.DEPENDENCIES", TextUtils.join(",", r4));
        r0.putExtra("requester_app_package", r3.getApplicationInfo().packageName);
        r3.sendBroadcast(r0);
    }

    public static void requestDownload(Context r2, Feature[] r3) {
        ModuleInstallRequest r32 = ModuleInstallRequest.newBuilder().addApi(new zzo(r3)).build();
        ModuleInstall.getClient(r2).installModules(r32).addOnFailureListener(new zzp());
    }

    private static Feature[] zza(Map r3, List r4) {
        Feature[] r0 = new Feature[r4.size()];
        int r1 = 0;
    L4:
        if (r1 >= r4.size()) goto L6;
        r0[r1] = (Feature) Preconditions.checkNotNull((Feature) r3.get(r4.get(r1)));
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }
}

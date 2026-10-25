package com.google.firebase;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.heartbeatinfo.DefaultHeartBeatController;
import com.google.firebase.platforminfo.DefaultUserAgentPublisher;
import com.google.firebase.platforminfo.KotlinDetector;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.util.ArrayList;
import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public class FirebaseCommonRegistrar implements ComponentRegistrar {
    /* renamed from: $r8$lambda$4HU-LsSJXjiWd9yqSIuIvr69gmw, reason: not valid java name */
    public static /* synthetic */ String m1918$r8$lambda$4HULsSJXjiWd9yqSIuIvr69gmw(Context r1) {
        String r12 = r1.getPackageManager().getInstallerPackageName(r1.getPackageName());
        if (r12 != null) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        return safeValue(r12);
    }

    public static /* synthetic */ String $r8$lambda$6KbLzjDGzlo31N3xe2HtYAtvoWM(Context r0) {
        ApplicationInfo r02 = r0.getApplicationInfo();
        if (r02 != null) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        return String.valueOf(r02.minSdkVersion);
    }

    public static /* synthetic */ String $r8$lambda$JRfXgWruDOlX8_YsmPkDH9kjxAM(Context r0) {
        ApplicationInfo r02 = r0.getApplicationInfo();
        if (r02 != null) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        return String.valueOf(r02.targetSdkVersion);
    }

    public static /* synthetic */ String $r8$lambda$i32xAjD3ZPnT8CWh_N09uYXbvXY(Context r2) {
        if (r2.getPackageManager().hasSystemFeature("android.hardware.type.television") == false) goto L7;
        return "tv";
    L7:
        if (r2.getPackageManager().hasSystemFeature("android.hardware.type.watch") == false) goto L11;
        return "watch";
    L11:
        if (r2.getPackageManager().hasSystemFeature("android.hardware.type.automotive") == false) goto L15;
        return "auto";
    L15:
        if (Build.VERSION.SDK_INT >= 26) goto L17;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L17:
        if (r2.getPackageManager().hasSystemFeature("android.hardware.type.embedded") == false) goto L22;
        return "embedded";
    L22:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public FirebaseCommonRegistrar() {
    }

    private static String safeValue(String r2) {
        return r2.replace(' ', '_').replace('/', '_');
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<Component<?>> getComponents() {
        ArrayList r2 = new ArrayList();
        r2.add(DefaultUserAgentPublisher.component());
        r2.add(DefaultHeartBeatController.component());
        r2.add(LibraryVersionComponent.create("fire-android", String.valueOf(Build.VERSION.SDK_INT)));
        r2.add(LibraryVersionComponent.create("fire-core", "22.2.0"));
        r2.add(LibraryVersionComponent.create("device-name", safeValue(Build.PRODUCT)));
        r2.add(LibraryVersionComponent.create("device-model", safeValue(Build.DEVICE)));
        r2.add(LibraryVersionComponent.create("device-brand", safeValue(Build.BRAND)));
        r2.add(LibraryVersionComponent.fromContext("android-target-sdk", new FirebaseCommonRegistrar$$ExternalSyntheticLambda0()));
        r2.add(LibraryVersionComponent.fromContext("android-min-sdk", new FirebaseCommonRegistrar$$ExternalSyntheticLambda1()));
        r2.add(LibraryVersionComponent.fromContext("android-platform", new FirebaseCommonRegistrar$$ExternalSyntheticLambda2()));
        r2.add(LibraryVersionComponent.fromContext("android-installer", new FirebaseCommonRegistrar$$ExternalSyntheticLambda3()));
        String r0 = KotlinDetector.detectVersion();
        if (r0 == null) goto L5;
        r2.add(LibraryVersionComponent.create("kotlin", r0));
    L5:
        return r2;
    }
}

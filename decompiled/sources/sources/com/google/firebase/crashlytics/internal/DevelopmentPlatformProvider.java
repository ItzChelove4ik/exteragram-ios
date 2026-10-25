package com.google.firebase.crashlytics.internal;

import android.content.Context;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class DevelopmentPlatformProvider {
    private final Context context;
    private DevelopmentPlatform developmentPlatform;

    /* renamed from: com.google.firebase.crashlytics.internal.DevelopmentPlatformProvider$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public class DevelopmentPlatform {
        private final String developmentPlatform;
        private final String developmentPlatformVersion;
        final /* synthetic */ DevelopmentPlatformProvider this$0;

        private DevelopmentPlatform(DevelopmentPlatformProvider r4) {
            this.this$0 = r4;
            int r0 = CommonUtils.getResourcesIdentifier(DevelopmentPlatformProvider.access$300(r4), "com.google.firebase.crashlytics.unity_version", "string");
            if (r0 == 0) goto L7;
            this.developmentPlatform = "Unity";
            String r42 = DevelopmentPlatformProvider.access$300(r4).getResources().getString(r0);
            this.developmentPlatformVersion = r42;
            Logger.getLogger().v("Unity Editor version is: " + r42);
            return;
        L7:
            if (DevelopmentPlatformProvider.access$400(r4, "flutter_assets/NOTICES.Z") == false) goto L10;
            this.developmentPlatform = "Flutter";
            this.developmentPlatformVersion = null;
            Logger.getLogger().v("Development platform is: Flutter");
            return;
        L10:
            this.developmentPlatform = null;
            this.developmentPlatformVersion = null;
        }

        public /* synthetic */ DevelopmentPlatform(DevelopmentPlatformProvider r1, AnonymousClass1 r2) {
            this(r1);
        }

        public static /* synthetic */ String access$000(DevelopmentPlatform r0) {
            return r0.developmentPlatform;
        }

        public static /* synthetic */ String access$100(DevelopmentPlatform r0) {
            return r0.developmentPlatformVersion;
        }
    }

    public DevelopmentPlatformProvider(Context r1) {
        this.context = r1;
        this.developmentPlatform = null;
    }

    public static /* synthetic */ Context access$300(DevelopmentPlatformProvider r0) {
        return r0.context;
    }

    public static /* synthetic */ boolean access$400(DevelopmentPlatformProvider r0, String r1) {
        return r0.assetFileExists(r1);
    }

    private boolean assetFileExists(String r3) {
        if (this.context.getAssets() != null) goto L10;
        return false;
    L10:
        InputStream r2 = this.context.getAssets().open(r3);     // Catch: IOException -> L9
        if (r2 == null) goto L8;
        r2.close();     // Catch: IOException -> L9
    L8:
        return true;
    L9:
        return false;
    }

    private DevelopmentPlatform initDevelopmentPlatform() {
        if (this.developmentPlatform != null) goto L6;
        this.developmentPlatform = new DevelopmentPlatform(this, null);
    L6:
        return this.developmentPlatform;
    }

    public String getDevelopmentPlatform() {
        return DevelopmentPlatform.access$000(initDevelopmentPlatform());
    }

    public String getDevelopmentPlatformVersion() {
        return DevelopmentPlatform.access$100(initDevelopmentPlatform());
    }
}

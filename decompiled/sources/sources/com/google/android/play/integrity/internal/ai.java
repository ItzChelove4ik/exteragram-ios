package com.google.android.play.integrity.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes5.dex */
public abstract class ai {

    /* renamed from: a, reason: collision with root package name */
    private static final s f108a = null;

    static {
        f108a = new s("PhoneskyVerificationUtils");
    }

    public static int a(Context r2) {
        PackageInfo r22 = r2.getPackageManager().getPackageInfo("com.android.vending", 64);     // Catch: PackageManager.NameNotFoundException -> L14
        ApplicationInfo r0 = r22.applicationInfo;
        if (r0 != null) goto L6;
        return 0;
    L6:
        if (r0.enabled == true) goto L8;
        return 0;
    L8:
        if (c(r22.signatures) == true) goto L11;
        return 0;
    L11:
        return r22.versionCode;
    L19:
        return 0;
    }

    private static boolean c(Signature[] r7) {
        if (r7 == null) goto L27;
        int r1 = r7.length;
        if (r1 == 0) goto L27;
        ArrayList r2 = new ArrayList();
        int r3 = 0;
    L8:
        if (r3 >= r1) goto L20;
        String r4 = ah.a(r7[r3].toByteArray());
        r2.add(r4);
        if ("8P1sW0EPJcslw7UzRsiXL64w-O50Ed-RBICtay1g24M".equals(r4) == true) goto L35;
        String r5 = Build.TAGS;
        if (r5.contains("dev-keys") == true) goto L16;
        if (r5.contains("test-keys") == true) goto L16;
    L17:
        r3 = r3 + 1;
    L16:
        if ("GXWy8XF3vIml3_MfnmSmyuKBpT3B0dWbHRR_4cgq-gA".equals(r4) == false) goto L17;
        return true;
    L35:
        return true;
    L20:
        s r72 = f108a;
        StringBuilder r12 = new StringBuilder();
        Iterator r22 = r2.iterator();
        if (r22.hasNext() == false) goto L25;
    L22:
        r12.append((CharSequence) r22.next());
        if (r22.hasNext() == false) goto L25;
        r12.append(", ");
    L25:
        r72.e(String.format("Play Store package certs are not valid. Found these sha256 certs: [%s].", new Object[]{r12.toString()}), new Object[0]);
        return false;
    L27:
        f108a.e("Play Store package is not signed -- possibly self-built package. Could not verify.", new Object[0]);
        return false;
    }
}

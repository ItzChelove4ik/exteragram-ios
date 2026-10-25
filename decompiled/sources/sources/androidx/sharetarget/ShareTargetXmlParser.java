package androidx.sharetarget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import androidx.sharetarget.ShareTargetCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import okio.Buffer$$ExternalSyntheticBUOutline4;

/* loaded from: classes4.dex */
abstract class ShareTargetXmlParser {
    private static final Object GET_INSTANCE_LOCK = null;
    private static volatile ArrayList<ShareTargetCompat> sShareTargets;

    static {
        GET_INSTANCE_LOCK = new Object();
    }

    private static String getAttributeValue(XmlResourceParser r1, String r2) {
        String r0 = r1.getAttributeValue("http://schemas.android.com/apk/res/android", r2);
        if (r0 == null) goto L5;
        return r0;
    L5:
        return r1.getAttributeValue(null, r2);
    }

    public static ArrayList<ShareTargetCompat> getShareTargets(Context r2) {
        if (sShareTargets != null) goto L16;
        Object r0 = GET_INSTANCE_LOCK;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L7:
        if (sShareTargets != null) goto L11;
        sShareTargets = parseShareTargets(r2);     // Catch: Throwable -> L9
    L11:
        monitor-exit(r0);     // Catch: Throwable -> L9
    L16:
        return sShareTargets;
    }

    private static XmlResourceParser getXmlResourceParser(Context r1, ActivityInfo r2) {
        XmlResourceParser r12 = r2.loadXmlMetaData(r1.getPackageManager(), "android.app.shortcuts");
        if (r12 == null) goto L5;
        return r12;
    L5:
        Buffer$$ExternalSyntheticBUOutline4.m("Failed to open android.app.shortcuts meta-data resource of ", r2.name);
        return null;
    }

    private static ShareTargetCompat parseShareTarget(XmlResourceParser r5) {
        String r0 = getAttributeValue(r5, "targetClass");
        ArrayList r1 = new ArrayList();
        ArrayList r2 = new ArrayList();
    L3:
        int r3 = r5.next();
        if (r3 == 1) goto L19;
        if (r3 == 2) goto L7;
        if (r3 != 3) goto L3;
        if (r5.getName().equals("share-target") == false) goto L3;
    L7:
        String r32 = r5.getName();
        r32.getClass();
        if (r32.equals("data") == false) goto L10;
        r1.add(parseTargetData(r5));
        goto L3
    L10:
        if (r32.equals("category") == false) goto L3;
        r2.add(getAttributeValue(r5, "name"));
    L19:
        if (r1.isEmpty() == true) goto L26;
        if (r0 != null) goto L22;
        return null;
    L22:
        if (r2.isEmpty() == false) goto L25;
        return null;
    L25:
        return new ShareTargetCompat((ShareTargetCompat.TargetData[]) r1.toArray(new ShareTargetCompat.TargetData[r1.size()]), r0, (String[]) r2.toArray(new String[r2.size()]));
    L26:
        return null;
    }

    private static ArrayList<ShareTargetCompat> parseShareTargets(Context r5) {
        ArrayList<ShareTargetCompat> r0 = new ArrayList();
        Intent r1 = new Intent("android.intent.action.MAIN");
        r1.addCategory("android.intent.category.LAUNCHER");
        r1.setPackage(r5.getPackageName());
        List<ResolveInfo> r12 = r5.getPackageManager().queryIntentActivities(r1, 128);
        if (r12 == null) goto L13;
        Iterator<ResolveInfo> r13 = r12.iterator();
    L7:
        if (r13.hasNext() == false) goto L13;
        ActivityInfo r2 = r13.next().activityInfo;
        Bundle r3 = r2.metaData;
        if (r3 == null) goto L7;
        if (r3.containsKey("android.app.shortcuts") == false) goto L7;
        r0.addAll(parseShareTargets(r5, r2));
    L13:
        return r0;
    }

    private static ArrayList<ShareTargetCompat> parseShareTargets(Context r3, ActivityInfo r4) {
        ArrayList<ShareTargetCompat> r0 = new ArrayList();
        XmlResourceParser r32 = getXmlResourceParser(r3, r4);
    L17:
        int r42 = r32.next();     // Catch: Exception -> L13
        if (r42 == 1) goto L15;
        if (r42 != 2) goto L17;
        if (r32.getName().equals("share-target") == false) goto L17;
        ShareTargetCompat r43 = parseShareTarget(r32);     // Catch: Exception -> L13
        if (r43 == null) goto L17;
        r0.add(r43);     // Catch: Exception -> L13
    L15:
        r32.close();
        return r0;
    L13:
        e = move-exception;
        Log.e("ShareTargetXmlParser", "Failed to parse the Xml resource: ", e);
        goto L15
    }

    private static ShareTargetCompat.TargetData parseTargetData(XmlResourceParser r9) {
        return new ShareTargetCompat.TargetData(getAttributeValue(r9, "scheme"), getAttributeValue(r9, "host"), getAttributeValue(r9, "port"), getAttributeValue(r9, "path"), getAttributeValue(r9, "pathPattern"), getAttributeValue(r9, "pathPrefix"), getAttributeValue(r9, "mimeType"));
    }
}

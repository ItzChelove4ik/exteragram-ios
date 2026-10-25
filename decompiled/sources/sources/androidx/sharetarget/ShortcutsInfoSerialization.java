package androidx.sharetarget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import androidx.collection.ArrayMap;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.util.AtomicFile;
import androidx.room.util.KClassUtil$$ExternalSyntheticBUOutline0;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

/* loaded from: classes.dex */
abstract class ShortcutsInfoSerialization {

    public static class ShortcutContainer {
        final String mBitmapPath;
        final String mResourceName;
        final ShortcutInfoCompat mShortcutInfo;

        public ShortcutContainer(ShortcutInfoCompat r1, String r2, String r3) {
            this.mShortcutInfo = r1;
            this.mResourceName = r2;
            this.mBitmapPath = r3;
        }
    }

    private static String getAttributeValue(XmlPullParser r1, String r2) {
        String r0 = r1.getAttributeValue("http://schemas.android.com/apk/res/android", r2);
        if (r0 == null) goto L5;
        return r0;
    L5:
        return r1.getAttributeValue(null, r2);
    }

    public static Map<String, ShortcutContainer> loadFromXml(File r5, Context r6) {
        ArrayMap r0 = new ArrayMap();
        FileInputStream r1 = new FileInputStream(r5);     // Catch: Exception -> L8
    L23:
        th = move-exception;
        r1.close();     // Catch: Throwable -> L26
    L28:
        throw th;     // Catch: Exception -> L8
    L26:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L8
        goto L28
    L5:
        if (r5.exists() == true) goto L10;
    L6:
        r1.close();     // Catch: Exception -> L8
        return r0;
    L10:
        XmlPullParser r2 = Xml.newPullParser();     // Catch: Throwable -> L23
        r2.setInput(r1, "UTF_8");     // Catch: Throwable -> L23
    L11:
        int r3 = r2.next();     // Catch: Throwable -> L23
        if (r3 == 1) goto L6;
        if (r3 != 2) goto L11;
        if (r2.getName().equals("target") == false) goto L11;
        ShortcutContainer r32 = parseShortcutContainer(r2, r6);     // Catch: Throwable -> L23
        if (r32 == null) goto L11;
        ShortcutInfoCompat r4 = r32.mShortcutInfo;     // Catch: Throwable -> L23
        if (r4 == null) goto L11;
        r0.put(r4.getId(), r32);     // Catch: Throwable -> L23
        goto L11
    L8:
        e = move-exception;
        r5.delete();
        Log.e("ShortcutInfoCompatSaver", "Failed to load saved values from file " + r5.getAbsolutePath() + ". Old state removed, new added", e);
        return r0;
    }

    private static ComponentName parseComponentName(XmlPullParser r1) {
        String r12 = getAttributeValue(r1, "component");
        if (TextUtils.isEmpty(r12) == false) goto L7;
        return null;
    L7:
        return ComponentName.unflattenFromString(r12);
    }

    private static Intent parseIntent(XmlPullParser r3) {
        String r0 = getAttributeValue(r3, "action");
        String r1 = getAttributeValue(r3, "targetPackage");
        String r32 = getAttributeValue(r3, "targetClass");
        if (r0 != null) goto L6;
        return null;
    L6:
        Intent r2 = new Intent(r0);
        if (TextUtils.isEmpty(r1) == false) goto L9;
    L11:
        return r2;
    L9:
        if (TextUtils.isEmpty(r32) == true) goto L11;
        r2.setClassName(r1, r32);
        goto L11
    }

    private static ShortcutContainer parseShortcutContainer(XmlPullParser r13, Context r14) {
        if (r13.getName().equals("target") == true) goto L5;
        return null;
    L5:
        String r0 = getAttributeValue(r13, "id");
        String r3 = getAttributeValue(r13, "short_label");
        if (TextUtils.isEmpty(r0) == false) goto L8;
    L46:
        return null;
    L8:
        if (TextUtils.isEmpty(r3) == true) goto L46;
        int r2 = Integer.parseInt(getAttributeValue(r13, "rank"));
        String r4 = getAttributeValue(r13, "long_label");
        String r5 = getAttributeValue(r13, "disabled_message");
        ComponentName r6 = parseComponentName(r13);
        String r7 = getAttributeValue(r13, "icon_resource_name");
        String r8 = getAttributeValue(r13, "icon_bitmap_path");
        ArrayList r9 = new ArrayList();
        HashSet r10 = new HashSet();
    L11:
        int r11 = r13.next();
        if (r11 == 1) goto L30;
        if (r11 == 2) goto L15;
        if (r11 != 3) goto L11;
        if (r13.getName().equals("target") == false) goto L11;
    L15:
        String r112 = r13.getName();
        r112.getClass();
        if (r112.equals("intent") == false) goto L18;
        Intent r113 = parseIntent(r13);
        if (r113 == null) goto L11;
        r9.add(r113);
        goto L11
    L18:
        if (r112.equals("categories") == false) goto L11;
        String r114 = getAttributeValue(r13, "name");
        if (TextUtils.isEmpty(r114) == true) goto L11;
        r10.add(r114);
    L30:
        ShortcutInfoCompat.Builder r132 = new ShortcutInfoCompat.Builder(r14, r0).setShortLabel(r3).setRank(r2);
        if (TextUtils.isEmpty(r4) == true) goto L34;
        r132.setLongLabel(r4);
    L34:
        if (TextUtils.isEmpty(r5) == true) goto L36;
        r132.setDisabledMessage(r5);
    L36:
        if (r6 == null) goto L39;
        r132.setActivity(r6);
    L39:
        if (r9.isEmpty() == true) goto L42;
        r132.setIntents((Intent[]) r9.toArray(new Intent[0]));
    L42:
        if (r10.isEmpty() == true) goto L45;
        r132.setCategories(r10);
    L45:
        return new ShortcutContainer(r132.build(), r7, r8);
    }

    public static void saveAsXml(List<ShortcutContainer> r6, File r7) {
        AtomicFile r1 = new AtomicFile(r7);
        FileOutputStream r72 = null;
        FileOutputStream r2 = r1.startWrite();     // Catch: Exception -> L12
        BufferedOutputStream r3 = new BufferedOutputStream(r2);     // Catch: Exception -> L8
        XmlSerializer r4 = Xml.newSerializer();     // Catch: Exception -> L8
        r4.setOutput(r3, "UTF_8");     // Catch: Exception -> L8
        r4.startDocument(null, Boolean.TRUE);     // Catch: Exception -> L8
        r4.startTag(null, "share_targets");     // Catch: Exception -> L8
        Iterator<ShortcutContainer> r62 = r6.iterator();     // Catch: Exception -> L8
    L6:
        if (r62.hasNext() == false) goto L10;
        serializeShortcutContainer(r4, r62.next());     // Catch: Exception -> L8
        goto L6
    L10:
        r4.endTag(null, "share_targets");     // Catch: Exception -> L8
        r4.endDocument();     // Catch: Exception -> L8
        r3.flush();     // Catch: Exception -> L8
        r2.flush();     // Catch: Exception -> L8
        r1.finishWrite(r2);     // Catch: Exception -> L8
        return;
    L8:
        e = e;
        r72 = r2;
    L13:
        Log.e("ShortcutInfoCompatSaver", "Failed to write to file " + r1.getBaseFile(), e);
        r1.failWrite(r72);
        KClassUtil$$ExternalSyntheticBUOutline0.m("Failed to write to file ", r1.getBaseFile(), e);
        return;
    L12:
        e = e;
        goto L13
    }

    private static void serializeAttribute(XmlSerializer r1, String r2, String r3) {
        if (TextUtils.isEmpty(r3) == false) goto L5;
        return;
    L5:
        r1.attribute(null, r2, r3);
    }

    private static void serializeCategory(XmlSerializer r3, String r4) {
        if (TextUtils.isEmpty(r4) == false) goto L5;
        return;
    L5:
        r3.startTag(null, "categories");
        serializeAttribute(r3, "name", r4);
        r3.endTag(null, "categories");
    }

    private static void serializeIntent(XmlSerializer r4, Intent r5) {
        r4.startTag(null, "intent");
        serializeAttribute(r4, "action", r5.getAction());
        if (r5.getComponent() == null) goto L5;
        serializeAttribute(r4, "targetPackage", r5.getComponent().getPackageName());
        serializeAttribute(r4, "targetClass", r5.getComponent().getClassName());
    L5:
        r4.endTag(null, "intent");
    }

    private static void serializeShortcutContainer(XmlSerializer r6, ShortcutContainer r7) {
        r6.startTag(null, "target");
        ShortcutInfoCompat r2 = r7.mShortcutInfo;
        serializeAttribute(r6, "id", r2.getId());
        serializeAttribute(r6, "short_label", r2.getShortLabel().toString());
        serializeAttribute(r6, "rank", Integer.toString(r2.getRank()));
        if (TextUtils.isEmpty(r2.getLongLabel()) == true) goto L6;
        serializeAttribute(r6, "long_label", r2.getLongLabel().toString());
    L6:
        if (TextUtils.isEmpty(r2.getDisabledMessage()) == true) goto L9;
        serializeAttribute(r6, "disabled_message", r2.getDisabledMessage().toString());
    L9:
        if (r2.getActivity() == null) goto L12;
        serializeAttribute(r6, "component", r2.getActivity().flattenToString());
    L12:
        if (TextUtils.isEmpty(r7.mResourceName) == true) goto L15;
        serializeAttribute(r6, "icon_resource_name", r7.mResourceName);
    L15:
        if (TextUtils.isEmpty(r7.mBitmapPath) == true) goto L17;
        serializeAttribute(r6, "icon_bitmap_path", r7.mBitmapPath);
    L17:
        Intent[] r72 = r2.getIntents();
        int r3 = r72.length;
        int r4 = 0;
    L18:
        if (r4 >= r3) goto L20;
        serializeIntent(r6, r72[r4]);
        r4 = r4 + 1;
        goto L18
    L20:
        Iterator<String> r73 = r2.getCategories().iterator();
    L22:
        if (r73.hasNext() == false) goto L24;
        serializeCategory(r6, r73.next());
        goto L22
    L24:
        r6.endTag(null, "target");
    }
}

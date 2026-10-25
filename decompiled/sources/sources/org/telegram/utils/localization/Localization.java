package org.telegram.utils.localization;

import android.content.Context;
import android.util.SparseArray;
import android.util.SparseIntArray;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import org.telegram.tgnet.SerializedData;

/* loaded from: classes6.dex */
public class Localization {
    public static final Localization EMPTY = null;
    private static SparseIntArray rawResBindings;
    private final SparseArray<String> localizations;

    public static class Builder {
        private SparseArray<String> localizations;

        /* renamed from: -$$Nest$fgetlocalizations, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SparseArray m22232$$Nest$fgetlocalizations(Builder r0) {
            return r0.localizations;
        }

        public Builder() {
        }

        public Builder addLocalization(HashMap<String, String> r4) {
            if (this.localizations != null) goto L5;
            this.localizations = new SparseArray(r4.size());
        L5:
            Iterator<Map.Entry<String, String>> r42 = r4.entrySet().iterator();
        L7:
            if (r42.hasNext() == false) goto L9;
            Map.Entry<String, String> r0 = r42.next();
            this.localizations.put(r0.getKey().hashCode(), r0.getValue());
            goto L7
        L9:
            return this;
        }

        public Builder addLocalization(Localization r6) {
            if (this.localizations != null) goto L6;
            this.localizations = Localization.m22230$$Nest$fgetlocalizations(r6).clone();
            return this;
        L6:
            int r0 = Localization.m22230$$Nest$fgetlocalizations(r6).size();
            int r1 = 0;
        L7:
            if (r1 >= r0) goto L9;
            int r2 = Localization.m22230$$Nest$fgetlocalizations(r6).keyAt(r1);
            String r3 = (String) Localization.m22230$$Nest$fgetlocalizations(r6).valueAt(r1);
            this.localizations.put(r2, r3);
            r1 = r1 + 1;
            goto L7
        L9:
            return this;
        }

        public Builder addResLocalization(Context r2, String r3) {
            this.localizations = Localization.m22231$$Nest$smload(r2, r3, this.localizations);     // Catch: Exception -> L4
        L3:
            return this;
        }

        public Localization build() {
            return new Localization(this, null);
        }
    }

    /* renamed from: -$$Nest$fgetlocalizations, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SparseArray m22230$$Nest$fgetlocalizations(Localization r0) {
        return r0.localizations;
    }

    /* renamed from: -$$Nest$smload, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SparseArray m22231$$Nest$smload(Context r0, String r1, SparseArray r2) {
        return load(r0, r1, r2);
    }

    static {
        EMPTY = new Localization();
    }

    private Localization() {
        this.localizations = new SparseArray();
    }

    private Localization(Builder r2) {
        if (Builder.m22232$$Nest$fgetlocalizations(r2) == null) goto L5;
        SparseArray<String> r22 = Builder.m22232$$Nest$fgetlocalizations(r2);
    L6:
        this.localizations = r22;
        return;
    L5:
        r22 = new SparseArray();
        goto L6
    }

    public /* synthetic */ Localization(Builder r1, LocalizationIA r2) {
        this(r1);
    }

    private static SparseArray<String> load(Context r5, String r6, SparseArray<String> r7) {
        BufferedInputStream r0 = new BufferedInputStream(r5.getAssets().open(r6));
        SerializedData r52 = new SerializedData(r0);     // Catch: Throwable -> L8
        int r1 = r52.readInt32(true);     // Catch: Throwable -> L8
        int r2 = 0;
        if (r7 != null) goto L10;
        r7 = new SparseArray(r1);     // Catch: Throwable -> L8
    L6:
        if (r2 >= r1) goto L13;
        r7.append(r52.readInt32(true), r52.readString(true));     // Catch: Throwable -> L8
        r2 = r2 + 1;     // Catch: Throwable -> L8
    L13:
        r0.close();
        return r7;
    L10:
        if (r2 >= r1) goto L13;
        r7.put(r52.readInt32(true), r52.readString(true));     // Catch: Throwable -> L8
        r2 = r2 + 1;
    L8:
        th = move-exception;
        r0.close();     // Catch: Throwable -> L17
    L19:
        throw th;
    L17:
        th = move-exception;
        th.addSuppressed(th);
        goto L19
    }

    private static SparseIntArray loadStringResBindings(Context r7) {
        BufferedInputStream r0 = new BufferedInputStream(r7.getResources().getAssets().open("string_resource_ids.bin"));     // Catch: IOException -> L16
        SerializedData r72 = new SerializedData(r0);     // Catch: Throwable -> L7
        int r2 = r72.readInt32(true);     // Catch: Throwable -> L7
        SparseIntArray r3 = new SparseIntArray(r2);     // Catch: Throwable -> L7
        int r4 = 0;
    L4:
        if (r4 >= r2) goto L9;
        r3.append(r72.readInt32(true), r72.readInt32(true));     // Catch: Throwable -> L7
        r4 = r4 + 1;
        goto L4
    L9:
        r0.close();     // Catch: IOException -> L16
        return r3;
    L7:
        th = move-exception;
        r0.close();     // Catch: Throwable -> L13
    L15:
        throw th;     // Catch: IOException -> L16
    L13:
        th = move-exception;
        th.addSuppressed(th);     // Catch: IOException -> L16
    L16:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    public String get(int r1) {
        return this.localizations.get(r1);
    }

    public String getByResId(Context r3, int r4) {
        if (r3 == null) goto L14;
        if (r4 == 0) goto L14;
        if (rawResBindings != null) goto L9;
        rawResBindings = loadStringResBindings(r3);
    L9:
        int r32 = rawResBindings.get(r4);
        if (r32 != 0) goto L13;
        return null;
    L13:
        return get(r32);
    L14:
        return null;
    }

    public String getByResName(String r1) {
        if (r1 != null) goto L4;
        return null;
    L4:
        return get(r1.hashCode());
    }

    public String getByResNameOrResId(Context r1, String r2, int r3) {
        if (r2 == null) goto L4;
        String r22 = getByResName(r2);
    L5:
        if (r22 != null) goto L9;
        if (r3 == 0) goto L9;
        return getByResId(r1, r3);
    L9:
        return r22;
    L4:
        r22 = null;
        goto L5
    }
}

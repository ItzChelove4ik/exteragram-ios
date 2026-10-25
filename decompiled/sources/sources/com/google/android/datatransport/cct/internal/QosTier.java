package com.google.android.datatransport.cct.internal;

import android.util.SparseArray;
import com.exteragram.messenger.plugins.PluginsConstants;

/* loaded from: classes4.dex */
public enum QosTier extends Enum<QosTier> {
    private static final /* synthetic */ QosTier[] $VALUES = null;
    public static final QosTier DEFAULT = null;
    public static final QosTier FAST_IF_RADIO_AWAKE = null;
    public static final QosTier NEVER = null;
    public static final QosTier UNMETERED_ONLY = null;
    public static final QosTier UNMETERED_OR_DAILY = null;
    public static final QosTier UNRECOGNIZED = null;
    private static final SparseArray<QosTier> valueMap = null;
    private final int value;

    static {
        QosTier r0 = new QosTier(PluginsConstants.Strategy.DEFAULT, 0, 0);
        DEFAULT = r0;
        QosTier r1 = new QosTier("UNMETERED_ONLY", 1, 1);
        UNMETERED_ONLY = r1;
        QosTier r2 = new QosTier("UNMETERED_OR_DAILY", 2, 2);
        UNMETERED_OR_DAILY = r2;
        QosTier r3 = new QosTier("FAST_IF_RADIO_AWAKE", 3, 3);
        FAST_IF_RADIO_AWAKE = r3;
        QosTier r4 = new QosTier("NEVER", 4, 4);
        NEVER = r4;
        QosTier r5 = new QosTier("UNRECOGNIZED", 5, -1);
        UNRECOGNIZED = r5;
        $VALUES = new QosTier[]{r0, r1, r2, r3, r4, r5};
        SparseArray<QosTier> r11 = new SparseArray();
        valueMap = r11;
        r11.put(0, r0);
        r11.put(1, r1);
        r11.put(2, r2);
        r11.put(3, r3);
        r11.put(4, r4);
        r11.put(-1, r5);
    }

    QosTier(String r1, int r2, int r3) {
        this.value = r3;
    }

    public static QosTier valueOf(String r1) {
        return (QosTier) Enum.valueOf(QosTier.class, r1);
    }

    public static QosTier[] values() {
        return (QosTier[]) $VALUES.clone();
    }
}

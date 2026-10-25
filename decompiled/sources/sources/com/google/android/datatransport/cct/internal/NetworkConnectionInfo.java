package com.google.android.datatransport.cct.internal;

import android.util.SparseArray;
import com.google.android.datatransport.cct.internal.AutoValue_NetworkConnectionInfo;

/* loaded from: classes4.dex */
public abstract class NetworkConnectionInfo {

    public static abstract class Builder {
        public Builder() {
        }

        public abstract NetworkConnectionInfo build();

        public abstract Builder setMobileSubtype(MobileSubtype r1);

        public abstract Builder setNetworkType(NetworkType r1);
    }

    public enum MobileSubtype extends Enum<MobileSubtype> {
        private static final /* synthetic */ MobileSubtype[] $VALUES = null;
        public static final MobileSubtype CDMA = null;
        public static final MobileSubtype COMBINED = null;
        public static final MobileSubtype EDGE = null;
        public static final MobileSubtype EHRPD = null;
        public static final MobileSubtype EVDO_0 = null;
        public static final MobileSubtype EVDO_A = null;
        public static final MobileSubtype EVDO_B = null;
        public static final MobileSubtype GPRS = null;
        public static final MobileSubtype GSM = null;
        public static final MobileSubtype HSDPA = null;
        public static final MobileSubtype HSPA = null;
        public static final MobileSubtype HSPAP = null;
        public static final MobileSubtype HSUPA = null;
        public static final MobileSubtype IDEN = null;
        public static final MobileSubtype IWLAN = null;
        public static final MobileSubtype LTE = null;
        public static final MobileSubtype LTE_CA = null;
        public static final MobileSubtype RTT = null;
        public static final MobileSubtype TD_SCDMA = null;
        public static final MobileSubtype UMTS = null;
        public static final MobileSubtype UNKNOWN_MOBILE_SUBTYPE = null;
        private static final SparseArray<MobileSubtype> valueMap = null;
        private final int value;

        static {
            MobileSubtype r1 = new MobileSubtype("UNKNOWN_MOBILE_SUBTYPE", 0, 0);
            UNKNOWN_MOBILE_SUBTYPE = r1;
            MobileSubtype r2 = new MobileSubtype("GPRS", 1, 1);
            GPRS = r2;
            MobileSubtype r3 = new MobileSubtype("EDGE", 2, 2);
            EDGE = r3;
            MobileSubtype r4 = new MobileSubtype("UMTS", 3, 3);
            UMTS = r4;
            MobileSubtype r5 = new MobileSubtype("CDMA", 4, 4);
            CDMA = r5;
            MobileSubtype r6 = new MobileSubtype("EVDO_0", 5, 5);
            EVDO_0 = r6;
            MobileSubtype r7 = new MobileSubtype("EVDO_A", 6, 6);
            EVDO_A = r7;
            MobileSubtype r8 = new MobileSubtype("RTT", 7, 7);
            RTT = r8;
            MobileSubtype r9 = new MobileSubtype("HSDPA", 8, 8);
            HSDPA = r9;
            MobileSubtype r10 = new MobileSubtype("HSUPA", 9, 9);
            HSUPA = r10;
            MobileSubtype r11 = new MobileSubtype("HSPA", 10, 10);
            HSPA = r11;
            MobileSubtype r12 = new MobileSubtype("IDEN", 11, 11);
            IDEN = r12;
            MobileSubtype r13 = new MobileSubtype("EVDO_B", 12, 12);
            EVDO_B = r13;
            MobileSubtype r14 = new MobileSubtype("LTE", 13, 13);
            LTE = r14;
            MobileSubtype r15 = new MobileSubtype("EHRPD", 14, 14);
            EHRPD = r15;
            MobileSubtype r0 = new MobileSubtype("HSPAP", 15, 15);
            HSPAP = r0;
            MobileSubtype r16 = new MobileSubtype("GSM", 16, 16);
            GSM = r16;
            MobileSubtype r22 = new MobileSubtype("TD_SCDMA", 17, 17);
            TD_SCDMA = r22;
            MobileSubtype r02 = new MobileSubtype("IWLAN", 18, 18);
            IWLAN = r02;
            MobileSubtype r17 = new MobileSubtype("LTE_CA", 19, 19);
            LTE_CA = r17;
            MobileSubtype r23 = new MobileSubtype("COMBINED", 20, 100);
            COMBINED = r23;
            $VALUES = new MobileSubtype[]{r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r0, r16, r22, r02, r17, r23};
            SparseArray<MobileSubtype> r03 = new SparseArray();
            valueMap = r03;
            r03.put(0, r1);
            r03.put(1, r2);
            r03.put(2, r3);
            r03.put(3, r4);
            r03.put(4, r5);
            r03.put(5, r6);
            r03.put(6, r7);
            r03.put(7, r8);
            r03.put(8, r9);
            r03.put(9, r10);
            r03.put(10, r11);
            r03.put(11, r12);
            r03.put(12, r13);
            r03.put(13, r14);
            r03.put(14, r15);
            r03.put(15, r0);
            r03.put(16, r16);
            r03.put(17, r22);
            r03.put(18, r02);
            r03.put(19, r17);
        }

        MobileSubtype(String r1, int r2, int r3) {
            this.value = r3;
        }

        public static MobileSubtype forNumber(int r1) {
            return valueMap.get(r1);
        }

        public static MobileSubtype valueOf(String r1) {
            return (MobileSubtype) Enum.valueOf(MobileSubtype.class, r1);
        }

        public static MobileSubtype[] values() {
            return (MobileSubtype[]) $VALUES.clone();
        }

        public int getValue() {
            return this.value;
        }
    }

    public enum NetworkType extends Enum<NetworkType> {
        private static final /* synthetic */ NetworkType[] $VALUES = null;
        public static final NetworkType BLUETOOTH = null;
        public static final NetworkType DUMMY = null;
        public static final NetworkType ETHERNET = null;
        public static final NetworkType MOBILE = null;
        public static final NetworkType MOBILE_CBS = null;
        public static final NetworkType MOBILE_DUN = null;
        public static final NetworkType MOBILE_EMERGENCY = null;
        public static final NetworkType MOBILE_FOTA = null;
        public static final NetworkType MOBILE_HIPRI = null;
        public static final NetworkType MOBILE_IA = null;
        public static final NetworkType MOBILE_IMS = null;
        public static final NetworkType MOBILE_MMS = null;
        public static final NetworkType MOBILE_SUPL = null;
        public static final NetworkType NONE = null;
        public static final NetworkType PROXY = null;
        public static final NetworkType VPN = null;
        public static final NetworkType WIFI = null;
        public static final NetworkType WIFI_P2P = null;
        public static final NetworkType WIMAX = null;
        private static final SparseArray<NetworkType> valueMap = null;
        private final int value;

        static {
            NetworkType r1 = new NetworkType("MOBILE", 0, 0);
            MOBILE = r1;
            NetworkType r2 = new NetworkType("WIFI", 1, 1);
            WIFI = r2;
            NetworkType r3 = new NetworkType("MOBILE_MMS", 2, 2);
            MOBILE_MMS = r3;
            NetworkType r4 = new NetworkType("MOBILE_SUPL", 3, 3);
            MOBILE_SUPL = r4;
            NetworkType r5 = new NetworkType("MOBILE_DUN", 4, 4);
            MOBILE_DUN = r5;
            NetworkType r6 = new NetworkType("MOBILE_HIPRI", 5, 5);
            MOBILE_HIPRI = r6;
            NetworkType r7 = new NetworkType("WIMAX", 6, 6);
            WIMAX = r7;
            NetworkType r8 = new NetworkType("BLUETOOTH", 7, 7);
            BLUETOOTH = r8;
            NetworkType r9 = new NetworkType("DUMMY", 8, 8);
            DUMMY = r9;
            NetworkType r10 = new NetworkType("ETHERNET", 9, 9);
            ETHERNET = r10;
            NetworkType r11 = new NetworkType("MOBILE_FOTA", 10, 10);
            MOBILE_FOTA = r11;
            NetworkType r12 = new NetworkType("MOBILE_IMS", 11, 11);
            MOBILE_IMS = r12;
            NetworkType r13 = new NetworkType("MOBILE_CBS", 12, 12);
            MOBILE_CBS = r13;
            NetworkType r14 = new NetworkType("WIFI_P2P", 13, 13);
            WIFI_P2P = r14;
            NetworkType r15 = new NetworkType("MOBILE_IA", 14, 14);
            MOBILE_IA = r15;
            NetworkType r0 = new NetworkType("MOBILE_EMERGENCY", 15, 15);
            MOBILE_EMERGENCY = r0;
            NetworkType r16 = new NetworkType("PROXY", 16, 16);
            PROXY = r16;
            NetworkType r22 = new NetworkType("VPN", 17, 17);
            VPN = r22;
            NetworkType r02 = new NetworkType("NONE", 18, -1);
            NONE = r02;
            $VALUES = new NetworkType[]{r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r0, r16, r22, r02};
            SparseArray<NetworkType> r03 = new SparseArray();
            valueMap = r03;
            r03.put(0, r1);
            r03.put(1, r2);
            r03.put(2, r3);
            r03.put(3, r4);
            r03.put(4, r5);
            r03.put(5, r6);
            r03.put(6, r7);
            r03.put(7, r8);
            r03.put(8, r9);
            r03.put(9, r10);
            r03.put(10, r11);
            r03.put(11, r12);
            r03.put(12, r13);
            r03.put(13, r14);
            r03.put(14, r15);
            r03.put(15, r0);
            r03.put(16, r16);
            r03.put(17, r22);
            r03.put(-1, r02);
        }

        NetworkType(String r1, int r2, int r3) {
            this.value = r3;
        }

        public static NetworkType forNumber(int r1) {
            return valueMap.get(r1);
        }

        public static NetworkType valueOf(String r1) {
            return (NetworkType) Enum.valueOf(NetworkType.class, r1);
        }

        public static NetworkType[] values() {
            return (NetworkType[]) $VALUES.clone();
        }

        public int getValue() {
            return this.value;
        }
    }

    public NetworkConnectionInfo() {
    }

    public static Builder builder() {
        return new AutoValue_NetworkConnectionInfo.Builder();
    }

    public abstract MobileSubtype getMobileSubtype();

    public abstract NetworkType getNetworkType();
}

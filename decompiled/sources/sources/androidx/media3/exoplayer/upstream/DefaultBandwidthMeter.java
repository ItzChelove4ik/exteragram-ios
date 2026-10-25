package androidx.media3.exoplayer.upstream;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BackgroundExecutor;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.NetworkTypeObserver;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.Map;
import kotlin.text.Typography;
import org.mvel2.asm.signature.SignatureVisitor;

/* loaded from: classes4.dex */
public final class DefaultBandwidthMeter implements BandwidthMeter, TransferListener {
    public static final ImmutableList<Long> DEFAULT_INITIAL_BITRATE_ESTIMATES_2G = null;
    public static final ImmutableList<Long> DEFAULT_INITIAL_BITRATE_ESTIMATES_3G = null;
    public static final ImmutableList<Long> DEFAULT_INITIAL_BITRATE_ESTIMATES_4G = null;
    public static final ImmutableList<Long> DEFAULT_INITIAL_BITRATE_ESTIMATES_5G_NSA = null;
    public static final ImmutableList<Long> DEFAULT_INITIAL_BITRATE_ESTIMATES_5G_SA = null;
    public static final ImmutableList<Long> DEFAULT_INITIAL_BITRATE_ESTIMATES_WIFI = null;

    @SuppressLint({"NonFinalStaticField", "StaticFieldLeak"})
    private static DefaultBandwidthMeter singletonInstance;
    private volatile long bitrateEstimate;
    private final Clock clock;
    private final Context context;
    private String countryCode;
    private final BandwidthMeter.EventListener.EventDispatcher eventDispatcher;
    private final ImmutableMap<Integer, Long> initialBitrateEstimates;
    private long lastReportedBitrateEstimate;
    private int networkType;
    private int networkTypeOverride;
    private boolean networkTypeOverrideSet;
    private final boolean resetOnNetworkTypeChange;
    private long sampleBytesTransferred;
    private long sampleStartTimeMs;
    private final SlidingPercentile slidingPercentile;
    private int streamCount;
    private long totalBytesTransferred;
    private long totalElapsedTimeMs;

    /* renamed from: androidx.media3.exoplayer.upstream.DefaultBandwidthMeter$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder {
        private Clock clock;
        private final Context context;
        private final Map<Integer, Long> initialBitrateEstimates;
        private boolean resetOnNetworkTypeChange;
        private int slidingWindowMaxWeight;

        public Builder(Context r4) {
            if (r4 != null) goto L5;
            Context r42 = null;
        L6:
            this.context = r42;
            this.slidingWindowMaxWeight = CastStatusCodes.AUTHENTICATION_FAILED;
            this.clock = Clock.DEFAULT;
            this.resetOnNetworkTypeChange = true;
            HashMap r43 = new HashMap(8);
            this.initialBitrateEstimates = r43;
            r43.put(0, 1000000L);
            r43.put(2, -9223372036854775807L);
            r43.put(3, -9223372036854775807L);
            r43.put(4, -9223372036854775807L);
            r43.put(5, -9223372036854775807L);
            r43.put(10, -9223372036854775807L);
            r43.put(9, -9223372036854775807L);
            r43.put(7, -9223372036854775807L);
            return;
        L5:
            r42 = r4.getApplicationContext();
            goto L6
        }

        public DefaultBandwidthMeter build() {
            return new DefaultBandwidthMeter(this.context, this.initialBitrateEstimates, this.slidingWindowMaxWeight, this.clock, this.resetOnNetworkTypeChange, null);
        }
    }

    public static /* synthetic */ void $r8$lambda$pO_odySZ98SgTVF8TZTXVnU5Mq8(DefaultBandwidthMeter r0, int r1) {
        r0.onNetworkTypeChanged(r1);
    }

    static {
        DEFAULT_INITIAL_BITRATE_ESTIMATES_WIFI = ImmutableList.of(4300000L, 3200000L, 2400000L, 1700000L, 860000L);
        DEFAULT_INITIAL_BITRATE_ESTIMATES_2G = ImmutableList.of(1500000L, 980000L, 750000L, 520000L, 290000L);
        DEFAULT_INITIAL_BITRATE_ESTIMATES_3G = ImmutableList.of(2000000L, 1300000L, 1000000L, 860000L, 610000L);
        DEFAULT_INITIAL_BITRATE_ESTIMATES_4G = ImmutableList.of(2500000L, 1700000L, 1200000L, 970000L, 680000L);
        DEFAULT_INITIAL_BITRATE_ESTIMATES_5G_NSA = ImmutableList.of(4700000L, 2800000L, 2100000L, 1700000L, 980000L);
        DEFAULT_INITIAL_BITRATE_ESTIMATES_5G_SA = ImmutableList.of(2700000L, 2000000L, 1600000L, 1300000L, 1000000L);
    }

    private DefaultBandwidthMeter(Context r2, Map<Integer, Long> r3, int r4, Clock r5, boolean r6) {
        if (r2 != null) goto L5;
        Context r0 = null;
    L6:
        this.context = r0;
        this.initialBitrateEstimates = ImmutableMap.copyOf(r3);
        this.eventDispatcher = new BandwidthMeter.EventListener.EventDispatcher();
        this.slidingPercentile = new SlidingPercentile(r4);
        this.clock = r5;
        this.resetOnNetworkTypeChange = r6;
        if (r2 == null) goto L10;
        NetworkTypeObserver r22 = NetworkTypeObserver.getInstance(r2);
        int r32 = r22.getNetworkType();
        this.networkType = r32;
        this.bitrateEstimate = getInitialBitrateEstimateForNetworkType(r32);
        r22.register(new DefaultBandwidthMeter$$ExternalSyntheticLambda0(this), BackgroundExecutor.get());
        return;
    L10:
        this.networkType = 0;
        this.bitrateEstimate = 1000000;
        return;
    L5:
        r0 = r2.getApplicationContext();
        goto L6
    }

    public /* synthetic */ DefaultBandwidthMeter(Context r1, Map r2, int r3, Clock r4, boolean r5, AnonymousClass1 r6) {
        this(r1, r2, r3, r4, r5);
    }

    private static int[] getInitialBitrateCountryGroupAssignment(String r3) {
        r3.getClass();
        char r2 = 65535;
        switch(r3.hashCode()) {
            case 2083: goto L958;
            case 2084: goto L954;
            case 2085: goto L950;
            case 2086: goto L946;
            case 2088: goto L942;
            case 2091: goto L938;
            case 2092: goto L934;
            case 2094: goto L930;
            case 2096: goto L926;
            case 2097: goto L922;
            case 2098: goto L918;
            case 2099: goto L914;
            case 2100: goto L910;
            case 2102: goto L906;
            case 2103: goto L902;
            case 2105: goto L898;
            case 2111: goto L894;
            case 2112: goto L890;
            case 2114: goto L886;
            case 2115: goto L882;
            case 2116: goto L878;
            case 2117: goto L874;
            case 2118: goto L870;
            case 2119: goto L866;
            case 2120: goto L862;
            case 2122: goto L858;
            case 2123: goto L854;
            case 2124: goto L850;
            case 2125: goto L846;
            case 2127: goto L842;
            case 2128: goto L838;
            case 2129: goto L834;
            case 2130: goto L830;
            case 2133: goto L826;
            case 2135: goto L822;
            case 2136: goto L818;
            case 2142: goto L814;
            case 2145: goto L810;
            case 2147: goto L806;
            case 2148: goto L802;
            case 2149: goto L798;
            case 2150: goto L794;
            case 2152: goto L790;
            case 2153: goto L786;
            case 2154: goto L782;
            case 2155: goto L778;
            case 2156: goto L774;
            case 2159: goto L770;
            case 2162: goto L766;
            case 2163: goto L762;
            case 2164: goto L758;
            case 2165: goto L754;
            case 2166: goto L750;
            case 2167: goto L746;
            case 2177: goto L742;
            case 2182: goto L738;
            case 2183: goto L734;
            case 2185: goto L730;
            case 2187: goto L726;
            case 2198: goto L722;
            case 2206: goto L718;
            case 2208: goto L714;
            case 2210: goto L710;
            case 2221: goto L706;
            case 2222: goto L702;
            case 2223: goto L698;
            case 2243: goto L694;
            case 2244: goto L690;
            case 2245: goto L686;
            case 2247: goto L682;
            case 2249: goto L678;
            case 2252: goto L674;
            case 2266: goto L670;
            case 2267: goto L666;
            case 2269: goto L662;
            case 2270: goto L658;
            case 2271: goto L654;
            case 2272: goto L650;
            case 2273: goto L646;
            case 2274: goto L642;
            case 2277: goto L638;
            case 2278: goto L634;
            case 2279: goto L630;
            case 2281: goto L626;
            case 2282: goto L622;
            case 2283: goto L618;
            case 2285: goto L614;
            case 2286: goto L610;
            case 2288: goto L606;
            case 2290: goto L602;
            case 2307: goto L598;
            case 2314: goto L594;
            case 2316: goto L590;
            case 2317: goto L586;
            case 2331: goto L582;
            case 2332: goto L578;
            case 2339: goto L574;
            case 2340: goto L570;
            case 2341: goto L566;
            case 2342: goto L562;
            case 2344: goto L558;
            case 2345: goto L554;
            case 2346: goto L550;
            case 2347: goto L546;
            case 2363: goto L542;
            case 2371: goto L538;
            case 2373: goto L534;
            case 2374: goto L530;
            case 2394: goto L526;
            case 2396: goto L522;
            case 2397: goto L518;
            case 2398: goto L514;
            case 2402: goto L510;
            case 2403: goto L506;
            case 2407: goto L502;
            case 2412: goto L498;
            case 2414: goto L494;
            case 2415: goto L490;
            case 2421: goto L486;
            case 2422: goto L482;
            case 2423: goto L478;
            case 2429: goto L474;
            case 2431: goto L470;
            case 2438: goto L466;
            case 2439: goto L462;
            case 2440: goto L458;
            case 2441: goto L454;
            case 2442: goto L450;
            case 2445: goto L446;
            case 2452: goto L442;
            case 2454: goto L438;
            case 2455: goto L434;
            case 2456: goto L430;
            case 2457: goto L426;
            case 2458: goto L422;
            case 2459: goto L418;
            case 2462: goto L414;
            case 2463: goto L410;
            case 2464: goto L406;
            case 2465: goto L402;
            case 2466: goto L398;
            case 2467: goto L394;
            case 2468: goto L390;
            case 2469: goto L386;
            case 2470: goto L382;
            case 2471: goto L378;
            case 2472: goto L374;
            case 2473: goto L370;
            case 2474: goto L366;
            case 2475: goto L362;
            case 2476: goto L358;
            case 2477: goto L354;
            case 2483: goto L350;
            case 2485: goto L346;
            case 2487: goto L342;
            case 2488: goto L338;
            case 2489: goto L334;
            case 2491: goto L330;
            case 2494: goto L326;
            case 2497: goto L322;
            case 2498: goto L318;
            case 2500: goto L314;
            case 2503: goto L310;
            case 2508: goto L306;
            case 2526: goto L302;
            case 2545: goto L298;
            case 2549: goto L294;
            case 2550: goto L290;
            case 2551: goto L286;
            case 2552: goto L282;
            case 2555: goto L278;
            case 2556: goto L274;
            case 2557: goto L270;
            case 2562: goto L266;
            case 2563: goto L262;
            case 2564: goto L258;
            case 2567: goto L254;
            case 2569: goto L250;
            case 2576: goto L246;
            case 2611: goto L242;
            case 2621: goto L238;
            case 2625: goto L234;
            case 2627: goto L230;
            case 2629: goto L226;
            case 2638: goto L222;
            case 2639: goto L218;
            case 2640: goto L214;
            case 2641: goto L210;
            case 2642: goto L206;
            case 2644: goto L202;
            case 2645: goto L198;
            case 2646: goto L194;
            case 2647: goto L190;
            case 2648: goto L186;
            case 2649: goto L182;
            case 2650: goto L178;
            case 2651: goto L174;
            case 2652: goto L170;
            case 2655: goto L166;
            case 2656: goto L162;
            case 2657: goto L158;
            case 2659: goto L154;
            case 2661: goto L150;
            case 2662: goto L146;
            case 2663: goto L142;
            case 2671: goto L138;
            case 2672: goto L134;
            case 2675: goto L130;
            case 2676: goto L126;
            case 2678: goto L122;
            case 2680: goto L118;
            case 2681: goto L114;
            case 2682: goto L110;
            case 2683: goto L106;
            case 2686: goto L102;
            case 2688: goto L98;
            case 2690: goto L94;
            case 2691: goto L90;
            case 2694: goto L86;
            case 2700: goto L82;
            case 2706: goto L78;
            case 2718: goto L74;
            case 2724: goto L70;
            case 2725: goto L66;
            case 2731: goto L62;
            case 2733: goto L58;
            case 2735: goto L54;
            case 2737: goto L50;
            case 2739: goto L46;
            case 2744: goto L42;
            case 2751: goto L38;
            case 2767: goto L34;
            case 2780: goto L30;
            case 2803: goto L26;
            case 2828: goto L22;
            case 2843: goto L18;
            case 2855: goto L14;
            case 2867: goto L10;
            case 2877: goto L6;
            default: goto L961;
        };
    L961:
        switch(r2) {
            case 0: goto L1293;
            case 1: goto L1291;
            case 2: goto L1289;
            case 3: goto L1287;
            case 4: goto L1293;
            case 5: goto L1285;
            case 6: goto L1283;
            case 7: goto L1281;
            case 8: goto L1279;
            case 9: goto L1277;
            case 10: goto L1275;
            case 11: goto L1273;
            case 12: goto L1271;
            case 13: goto L1269;
            case 14: goto L1267;
            case 15: goto L1265;
            case 16: goto L1263;
            case 17: goto L1293;
            case 18: goto L1261;
            case 19: goto L1259;
            case 20: goto L1257;
            case 21: goto L1255;
            case 22: goto L1253;
            case 23: goto L1251;
            case 24: goto L1249;
            case 25: goto L1247;
            case 26: goto L1245;
            case 27: goto L1243;
            case 28: goto L1241;
            case 29: goto L1293;
            case 30: goto L1239;
            case 31: goto L1237;
            case 32: goto L1235;
            case 33: goto L1233;
            case 34: goto L1231;
            case 35: goto L1229;
            case 36: goto L1227;
            case 37: goto L1225;
            case 38: goto L1223;
            case 39: goto L1221;
            case 40: goto L1219;
            case 41: goto L1287;
            case 42: goto L1229;
            case 43: goto L1217;
            case 44: goto L1215;
            case 45: goto L1213;
            case 46: goto L1211;
            case 47: goto L1209;
            case 48: goto L1207;
            case 49: goto L1205;
            case 50: goto L1293;
            case 51: goto L1267;
            case 52: goto L1203;
            case 53: goto L1201;
            case 54: goto L1199;
            case 55: goto L1265;
            case 56: goto L1197;
            case 57: goto L1293;
            case 58: goto L1195;
            case 59: goto L1193;
            case 60: goto L1191;
            case 61: goto L1273;
            case 62: goto L1221;
            case 63: goto L1279;
            case 64: goto L1189;
            case 65: goto L1187;
            case 66: goto L1185;
            case 67: goto L1183;
            case 68: goto L1181;
            case 69: goto L1179;
            case 70: goto L1177;
            case 71: goto L1175;
            case 72: goto L1173;
            case 73: goto L1171;
            case 74: goto L1169;
            case 75: goto L1167;
            case 76: goto L1165;
            case 77: goto L1163;
            case 78: goto L1161;
            case 79: goto L1159;
            case 80: goto L1157;
            case 81: goto L1155;
            case 82: goto L1153;
            case 83: goto L1151;
            case 84: goto L1251;
            case 85: goto L1149;
            case 86: goto L1147;
            case 87: goto L1145;
            case 88: goto L1143;
            case 89: goto L1141;
            case 90: goto L1139;
            case 91: goto L1137;
            case 92: goto L1251;
            case 93: goto L1273;
            case 94: goto L1135;
            case 95: goto L1133;
            case 96: goto L1131;
            case 97: goto L1159;
            case 98: goto L1129;
            case 99: goto L1127;
            case 100: goto L1125;
            case 101: goto L1123;
            case 102: goto L1273;
            case 103: goto L1121;
            case 104: goto L1159;
            case 105: goto L1119;
            case 106: goto L1263;
            case 107: goto L1117;
            case 108: goto L1115;
            case 109: goto L1113;
            case 110: goto L1111;
            case 111: goto L1207;
            case 112: goto L1109;
            case 113: goto L1293;
            case 114: goto L1107;
            case 115: goto L1137;
            case 116: goto L1293;
            case 117: goto L1105;
            case 118: goto L1103;
            case 119: goto L1101;
            case 120: goto L1099;
            case 121: goto L1267;
            case 122: goto L1097;
            case 123: goto L1195;
            case 124: goto L1095;
            case 125: goto L1093;
            case 126: goto L1091;
            case 127: goto L1273;
            case 128: goto L1265;
            case 129: goto L1089;
            case 130: goto L1157;
            case 131: goto L1087;
            case 132: goto L1085;
            case 133: goto L1083;
            case 134: goto L1221;
            case 135: goto L1081;
            case 136: goto L1079;
            case 137: goto L1225;
            case 138: goto L1097;
            case 139: goto L1077;
            case 140: goto L1075;
            case 141: goto L1247;
            case 142: goto L1073;
            case 143: goto L1215;
            case 144: goto L1267;
            case 145: goto L1273;
            case 146: goto L1071;
            case 147: goto L1069;
            case 148: goto L1067;
            case 149: goto L1065;
            case 150: goto L1063;
            case 151: goto L1061;
            case 152: goto L1059;
            case 153: goto L1057;
            case 154: goto L1251;
            case 155: goto L1181;
            case 156: goto L1055;
            case 157: goto L1209;
            case 158: goto L1053;
            case 159: goto L1051;
            case 160: goto L1049;
            case 161: goto L1207;
            case 162: goto L1279;
            case 163: goto L1047;
            case 164: goto L1045;
            case 165: goto L1283;
            case 166: goto L1043;
            case 167: goto L1041;
            case 168: goto L1095;
            case 169: goto L1039;
            case 170: goto L1037;
            case 171: goto L1035;
            case 172: goto L1267;
            case 173: goto L1033;
            case 174: goto L1031;
            case 175: goto L1255;
            case 176: goto L1029;
            case 177: goto L1247;
            case 178: goto L1027;
            case 179: goto L1025;
            case 180: goto L1023;
            case 181: goto L1021;
            case 182: goto L1019;
            case 183: goto L1017;
            case 184: goto L1015;
            case 185: goto L1013;
            case 186: goto L1279;
            case 187: goto L1257;
            case 188: goto L1273;
            case 189: goto L1011;
            case 190: goto L1279;
            case 191: goto L1255;
            case 192: goto L1181;
            case 193: goto L1009;
            case 194: goto L1265;
            case 195: goto L1267;
            case 196: goto L1007;
            case 197: goto L1005;
            case 198: goto L1003;
            case 199: goto L1155;
            case 200: goto L1001;
            case 201: goto L999;
            case 202: goto L1293;
            case 203: goto L1257;
            case 204: goto L1289;
            case 205: goto L997;
            case 206: goto L1257;
            case 207: goto L995;
            case 208: goto L1217;
            case 209: goto L1193;
            case 210: goto L1207;
            case 211: goto L1081;
            case 212: goto L993;
            case 213: goto L991;
            case 214: goto L1263;
            case 215: goto L989;
            case 216: goto L1081;
            case 217: goto L987;
            case 218: goto L985;
            case 219: goto L1227;
            case 220: goto L983;
            case 221: goto L981;
            case 222: goto L979;
            case 223: goto L977;
            case 224: goto L1267;
            case 225: goto L1293;
            case 226: goto L1251;
            case 227: goto L975;
            case 228: goto L973;
            case 229: goto L971;
            case 230: goto L1109;
            case 231: goto L1081;
            case 232: goto L1061;
            case 233: goto L969;
            case 234: goto L1251;
            case 235: goto L1057;
            case 236: goto L967;
            case 237: goto L965;
            case 238: goto L1013;
            default: goto L963;
        };
    L963:
        return new int[]{2, 2, 2, 2, 2, 2};
    L965:
        return new int[]{4, 4, 4, 3, 2, 2};
    L967:
        return new int[]{2, 4, 2, 1, 1, 2};
    L969:
        return new int[]{1, 2, 1, 1, 2, 2};
    L971:
        return new int[]{0, 0, 1, 2, 2, 2};
    L973:
        return new int[]{0, 2, 1, 2, 2, 2};
    L975:
        return new int[]{2, 2, 1, 1, 2, 4};
    L977:
        return new int[]{1, 2, 3, 4, 3, 2};
    L979:
        return new int[]{2, 1, 1, 2, 1, 2};
    L981:
        return new int[]{2, 2, 4, 1, 3, 1};
    L983:
        return new int[]{3, 3, 2, 3, 4, 2};
    L985:
        return new int[]{3, 4, 2, 1, 3, 2};
    L987:
        return new int[]{0, 0, 0, 0, 0, 0};
    L989:
        return new int[]{2, 4, 1, 0, 2, 2};
    L991:
        return new int[]{3, 2, 4, 3, 2, 2};
    L993:
        return new int[]{3, 1, 1, 1, 2, 2};
    L995:
        return new int[]{3, 4, 1, 0, 2, 2};
    L997:
        return new int[]{3, 2, 1, 2, 2, 2};
    L999:
        return new int[]{2, 3, 2, 1, 2, 2};
    L1001:
        return new int[]{2, 2, 1, 2, 2, 2};
    L1003:
        return new int[]{2, 4, 4, 1, 2, 2};
    L1005:
        return new int[]{2, 2, 3, 4, 4, 2};
    L1007:
        return new int[]{4, 4, 3, 2, 2, 2};
    L1009:
        return new int[]{0, 1, 1, 1, 2, 2};
    L1011:
        return new int[]{2, 3, 3, 3, 1, 1};
    L1013:
        return new int[]{4, 2, 4, 3, 2, 2};
    L1015:
        return new int[]{3, 1, 1, 2, 2, 0};
    L1017:
        return new int[]{3, 3, 2, 0, 2, 2};
    L1019:
        return new int[]{1, 0, 0, 1, 3, 3};
    L1021:
        return new int[]{1, 0, 0, 1, 2, 2};
    L1023:
        return new int[]{0, 0, 1, 1, 3, 2};
    L1025:
        return new int[]{0, 3, 2, 3, 1, 2};
    L1027:
        return new int[]{1, 4, 4, 4, 4, 2};
    L1029:
        return new int[]{2, 2, 4, 1, 2, 2};
    L1031:
        return new int[]{3, 4, 1, 3, 2, 2};
    L1033:
        return new int[]{2, 0, 2, 1, 2, 0};
    L1035:
        return new int[]{1, 0, 2, 2, 4, 4};
    L1037:
        return new int[]{3, 3, 3, 3, 2, 2};
    L1039:
        return new int[]{2, 1, 2, 3, 2, 1};
    L1041:
        return new int[]{2, 2, 3, 1, 2, 2};
    L1043:
        return new int[]{1, 2, 4, 4, 3, 2};
    L1045:
        return new int[]{2, 3, 1, 2, 4, 2};
    L1047:
        return new int[]{0, 0, 1, 2, 4, 2};
    L1049:
        return new int[]{2, 2, 4, 3, 2, 2};
    L1051:
        return new int[]{0, 0, 3, 0, 0, 2};
    L1053:
        return new int[]{2, 1, 4, 3, 0, 4};
    L1055:
        return new int[]{3, 4, 2, 1, 2, 2};
    L1057:
        return new int[]{2, 3, 3, 4, 2, 2};
    L1059:
        return new int[]{3, 4, 3, 2, 2, 2};
    L1061:
        return new int[]{3, 1, 2, 2, 2, 2};
    L1063:
        return new int[]{1, 0, 4, 1, 1, 0};
    L1065:
        return new int[]{2, 4, 4, 4, 3, 2};
    L1067:
        return new int[]{3, 2, 2, 1, 2, 2};
    L1069:
        return new int[]{3, 2, 1, 3, 4, 2};
    L1071:
        return new int[]{3, 1, 0, 2, 2, 2};
    L1073:
        return new int[]{2, 1, 2, 3, 2, 2};
    L1075:
        return new int[]{0, 2, 4, 4, 3, 1};
    L1077:
        return new int[]{2, 0, 2, 2, 2, 2};
    L1079:
        return new int[]{1, 0, 0, 1, 3, 2};
    L1081:
        return new int[]{4, 2, 2, 4, 2, 2};
    L1083:
        return new int[]{1, 2, 2, 3, 2, 2};
    L1085:
        return new int[]{2, 0, 0, 1, 3, 2};
    L1087:
        return new int[]{1, 0, 0, 0, 2, 2};
    L1089:
        return new int[]{3, 3, 1, 1, 2, 2};
    L1091:
        return new int[]{4, 0, 3, 2, 1, 3};
    L1093:
        return new int[]{0, 1, 0, 1, 0, 2};
    L1095:
        return new int[]{4, 3, 3, 3, 2, 2};
    L1097:
        return new int[]{3, 2, 3, 3, 4, 2};
    L1099:
        return new int[]{2, 2, 1, 1, 2, 2};
    L1101:
        return new int[]{3, 1, 1, 2, 2, 2};
    L1103:
        return new int[]{1, 2, 1, 3, 2, 2};
    L1105:
        return new int[]{2, 1, 2, 2, 3, 2};
    L1107:
        return new int[]{0, 2, 2, 4, 4, 4};
    L1109:
        return new int[]{4, 3, 3, 2, 2, 2};
    L1111:
        return new int[]{1, 0, 4, 2, 2, 2};
    L1113:
        return new int[]{2, 1, 1, 2, 2, 2};
    L1115:
        return new int[]{3, 2, 1, 1, 1, 2};
    L1117:
        return new int[]{0, 3, 2, 3, 4, 2};
    L1119:
        return new int[]{2, 4, 3, 1, 2, 2};
    L1121:
        return new int[]{0, 1, 1, 2, 1, 2};
    L1123:
        return new int[]{4, 2, 3, 3, 4, 3};
    L1125:
        return new int[]{3, 2, 3, 2, 2, 2};
    L1127:
        return new int[]{3, 2, 2, 0, 2, 2};
    L1129:
        return new int[]{1, 1, 3, 2, 2, 3};
    L1131:
        return new int[]{1, 2, 2, 3, 4, 2};
    L1133:
        return new int[]{1, 1, 1, 1, 1, 2};
    L1135:
        return new int[]{3, 1, 3, 3, 2, 4};
    L1137:
        return new int[]{1, 0, 0, 0, 0, 2};
    L1139:
        return new int[]{0, 1, 0, 1, 1, 0};
    L1141:
        return new int[]{3, 1, 1, 3, 2, 2};
    L1143:
        return new int[]{4, 4, 1, 2, 2, 2};
    L1145:
        return new int[]{2, 2, 4, 3, 3, 2};
    L1147:
        return new int[]{2, 1, 2, 1, 2, 2};
    L1149:
        return new int[]{1, 0, 0, 0, 1, 2};
    L1151:
        return new int[]{2, 1, 1, 3, 2, 2};
    L1153:
        return new int[]{3, 4, 4, 2, 2, 2};
    L1155:
        return new int[]{4, 3, 2, 4, 2, 2};
    L1157:
        return new int[]{1, 2, 2, 0, 2, 2};
    L1159:
        return new int[]{0, 2, 0, 1, 2, 2};
    L1161:
        return new int[]{3, 3, 3, 2, 2, 2};
    L1163:
        return new int[]{0, 2, 1, 1, 2, 2};
    L1165:
        return new int[]{3, 2, 3, 3, 2, 2};
    L1167:
        return new int[]{1, 1, 0, 2, 2, 2};
    L1169:
        return new int[]{2, 2, 0, 0, 2, 2};
    L1171:
        return new int[]{1, 1, 3, 2, 2, 2};
    L1173:
        return new int[]{3, 4, 0, 0, 2, 2};
    L1175:
        return new int[]{1, 1, 1, 1, 0, 2};
    L1177:
        return new int[]{0, 2, 2, 0, 2, 2};
    L1179:
        return new int[]{4, 2, 4, 0, 2, 2};
    L1181:
        return new int[]{3, 2, 2, 2, 2, 2};
    L1183:
        return new int[]{3, 2, 2, 3, 2, 2};
    L1185:
        return new int[]{0, 0, 0, 1, 0, 2};
    L1187:
        return new int[]{4, 3, 4, 4, 4, 2};
    L1189:
        return new int[]{0, 0, 0, 0, 1, 0};
    L1191:
        return new int[]{1, 3, 2, 1, 2, 2};
    L1193:
        return new int[]{3, 3, 4, 4, 2, 2};
    L1195:
        return new int[]{3, 4, 4, 4, 2, 2};
    L1197:
        return new int[]{0, 0, 2, 0, 0, 2};
    L1199:
        return new int[]{0, 1, 4, 2, 2, 1};
    L1201:
        return new int[]{0, 0, 2, 0, 1, 2};
    L1203:
        return new int[]{1, 0, 1, 0, 0, 2};
    L1205:
        return new int[]{2, 3, 0, 1, 2, 2};
    L1207:
        return new int[]{4, 2, 4, 4, 2, 2};
    L1209:
        return new int[]{2, 4, 4, 4, 2, 2};
    L1211:
        return new int[]{2, 3, 3, 2, 2, 2};
    L1213:
        return new int[]{2, 0, 1, 1, 3, 1};
    L1215:
        return new int[]{4, 3, 3, 4, 2, 2};
    L1217:
        return new int[]{0, 1, 2, 2, 2, 2};
    L1219:
        return new int[]{0, 1, 0, 0, 0, 2};
    L1221:
        return new int[]{3, 4, 3, 3, 2, 2};
    L1223:
        return new int[]{4, 2, 4, 2, 2, 2};
    L1225:
        return new int[]{3, 3, 2, 2, 2, 2};
    L1227:
        return new int[]{0, 2, 1, 2, 3, 3};
    L1229:
        return new int[]{2, 2, 2, 1, 2, 2};
    L1231:
        return new int[]{1, 2, 3, 3, 2, 2};
    L1233:
        return new int[]{3, 2, 1, 0, 2, 2};
    L1235:
        return new int[]{3, 1, 2, 2, 3, 2};
    L1237:
        return new int[]{3, 2, 1, 1, 2, 2};
    L1239:
        return new int[]{1, 1, 1, 1, 2, 4};
    L1241:
        return new int[]{1, 2, 4, 4, 2, 2};
    L1243:
        return new int[]{3, 2, 0, 0, 2, 2};
    L1245:
        return new int[]{0, 2, 0, 0, 2, 2};
    L1247:
        return new int[]{1, 2, 2, 2, 2, 2};
    L1249:
        return new int[]{4, 4, 2, 3, 2, 2};
    L1251:
        return new int[]{4, 4, 4, 4, 2, 2};
    L1253:
        return new int[]{1, 3, 1, 3, 4, 2};
    L1255:
        return new int[]{0, 0, 0, 0, 1, 2};
    L1257:
        return new int[]{4, 3, 4, 4, 2, 2};
    L1259:
        return new int[]{0, 0, 1, 0, 1, 2};
    L1261:
        return new int[]{2, 1, 3, 2, 4, 2};
    L1263:
        return new int[]{1, 1, 1, 1, 2, 2};
    L1265:
        return new int[]{4, 2, 3, 3, 2, 2};
    L1267:
        return new int[]{0, 2, 2, 2, 2, 2};
    L1269:
        return new int[]{2, 2, 3, 4, 2, 2};
    L1271:
        return new int[]{0, 3, 1, 1, 3, 0};
    L1273:
        return new int[]{0, 0, 0, 0, 0, 2};
    L1275:
        return new int[]{2, 2, 3, 3, 2, 2};
    L1277:
        return new int[]{2, 2, 2, 2, 1, 2};
    L1279:
        return new int[]{4, 2, 2, 2, 2, 2};
    L1281:
        return new int[]{3, 4, 4, 3, 2, 2};
    L1283:
        return new int[]{2, 3, 2, 3, 2, 2};
    L1285:
        return new int[]{1, 1, 1, 2, 2, 2};
    L1287:
        return new int[]{2, 4, 3, 4, 2, 2};
    L1289:
        return new int[]{4, 4, 3, 4, 2, 2};
    L1291:
        return new int[]{1, 4, 2, 3, 4, 1};
    L1293:
        return new int[]{1, 2, 0, 0, 2, 2};
    L6:
        if (r3.equals("ZW") == false) goto L961;
        r2 = 238;
        goto L961
    L10:
        if (r3.equals("ZM") == false) goto L961;
        r2 = 237;
        goto L961
    L14:
        if (r3.equals("ZA") == false) goto L961;
        r2 = 236;
        goto L961
    L18:
        if (r3.equals("YT") == false) goto L961;
        r2 = 235;
        goto L961
    L22:
        if (r3.equals("YE") == false) goto L961;
        r2 = 234;
        goto L961
    L26:
        if (r3.equals("XK") == false) goto L961;
        r2 = 233;
        goto L961
    L30:
        if (r3.equals("WS") == false) goto L961;
        r2 = 232;
        goto L961
    L34:
        if (r3.equals("WF") == false) goto L961;
        r2 = 231;
        goto L961
    L38:
        if (r3.equals("VU") == false) goto L961;
        r2 = 230;
        goto L961
    L42:
        if (r3.equals("VN") == false) goto L961;
        r2 = 229;
        goto L961
    L46:
        if (r3.equals("VI") == false) goto L961;
        r2 = 228;
        goto L961
    L50:
        if (r3.equals("VG") == false) goto L961;
        r2 = 227;
        goto L961
    L54:
        if (r3.equals("VE") == false) goto L961;
        r2 = 226;
        goto L961
    L58:
        if (r3.equals("VC") == false) goto L961;
        r2 = 225;
        goto L961
    L62:
        if (r3.equals("VA") == false) goto L961;
        r2 = 224;
        goto L961
    L66:
        if (r3.equals("UZ") == false) goto L961;
        r2 = 223;
        goto L961
    L70:
        if (r3.equals("UY") == false) goto L961;
        r2 = 222;
        goto L961
    L74:
        if (r3.equals("US") == false) goto L961;
        r2 = 221;
        goto L961
    L78:
        if (r3.equals("UG") == false) goto L961;
        r2 = 220;
        goto L961
    L82:
        if (r3.equals("UA") == false) goto L961;
        r2 = 219;
        goto L961
    L86:
        if (r3.equals("TZ") == false) goto L961;
        r2 = 218;
        goto L961
    L90:
        if (r3.equals("TW") == false) goto L961;
        r2 = 217;
        goto L961
    L94:
        if (r3.equals("TV") == false) goto L961;
        r2 = 216;
        goto L961
    L98:
        if (r3.equals("TT") == false) goto L961;
        r2 = Typography.times;
        goto L961
    L102:
        if (r3.equals("TR") == false) goto L961;
        r2 = 214;
        goto L961
    L106:
        if (r3.equals("TO") == false) goto L961;
        r2 = 213;
        goto L961
    L110:
        if (r3.equals("TN") == false) goto L961;
        r2 = 212;
        goto L961
    L114:
        if (r3.equals("TM") == false) goto L961;
        r2 = 211;
        goto L961
    L118:
        if (r3.equals("TL") == false) goto L961;
        r2 = 210;
        goto L961
    L122:
        if (r3.equals("TJ") == false) goto L961;
        r2 = 209;
        goto L961
    L126:
        if (r3.equals("TH") == false) goto L961;
        r2 = 208;
        goto L961
    L130:
        if (r3.equals("TG") == false) goto L961;
        r2 = 207;
        goto L961
    L134:
        if (r3.equals("TD") == false) goto L961;
        r2 = 206;
        goto L961
    L138:
        if (r3.equals("TC") == false) goto L961;
        r2 = 205;
        goto L961
    L142:
        if (r3.equals("SZ") == false) goto L961;
        r2 = 204;
        goto L961
    L146:
        if (r3.equals("SY") == false) goto L961;
        r2 = 203;
        goto L961
    L150:
        if (r3.equals("SX") == false) goto L961;
        r2 = 202;
        goto L961
    L154:
        if (r3.equals("SV") == false) goto L961;
        r2 = 201;
        goto L961
    L158:
        if (r3.equals("ST") == false) goto L961;
        r2 = 200;
        goto L961
    L162:
        if (r3.equals("SS") == false) goto L961;
        r2 = 199;
        goto L961
    L166:
        if (r3.equals("SR") == false) goto L961;
        r2 = 198;
        goto L961
    L170:
        if (r3.equals("SO") == false) goto L961;
        r2 = 197;
        goto L961
    L174:
        if (r3.equals("SN") == false) goto L961;
        r2 = 196;
        goto L961
    L178:
        if (r3.equals("SM") == false) goto L961;
        r2 = 195;
        goto L961
    L182:
        if (r3.equals("SL") == false) goto L961;
        r2 = 194;
        goto L961
    L186:
        if (r3.equals("SK") == false) goto L961;
        r2 = 193;
        goto L961
    L190:
        if (r3.equals("SJ") == false) goto L961;
        r2 = 192;
        goto L961
    L194:
        if (r3.equals("SI") == false) goto L961;
        r2 = 191;
        goto L961
    L198:
        if (r3.equals("SH") == false) goto L961;
        r2 = 190;
        goto L961
    L202:
        if (r3.equals("SG") == false) goto L961;
        r2 = Typography.half;
        goto L961
    L206:
        if (r3.equals("SE") == false) goto L961;
        r2 = 188;
        goto L961
    L210:
        if (r3.equals("SD") == false) goto L961;
        r2 = 187;
        goto L961
    L214:
        if (r3.equals("SC") == false) goto L961;
        r2 = 186;
        goto L961
    L218:
        if (r3.equals("SB") == false) goto L961;
        r2 = 185;
        goto L961
    L222:
        if (r3.equals("SA") == false) goto L961;
        r2 = 184;
        goto L961
    L226:
        if (r3.equals("RW") == false) goto L961;
        r2 = Typography.middleDot;
        goto L961
    L230:
        if (r3.equals("RU") == false) goto L961;
        r2 = Typography.paragraph;
        goto L961
    L234:
        if (r3.equals("RS") == false) goto L961;
        r2 = 181;
        goto L961
    L238:
        if (r3.equals("RO") == false) goto L961;
        r2 = 180;
        goto L961
    L242:
        if (r3.equals("RE") == false) goto L961;
        r2 = 179;
        goto L961
    L246:
        if (r3.equals("QA") == false) goto L961;
        r2 = 178;
        goto L961
    L250:
        if (r3.equals("PY") == false) goto L961;
        r2 = Typography.plusMinus;
        goto L961
    L254:
        if (r3.equals("PW") == false) goto L961;
        r2 = Typography.degree;
        goto L961
    L258:
        if (r3.equals("PT") == false) goto L961;
        r2 = 175;
        goto L961
    L262:
        if (r3.equals("PS") == false) goto L961;
        r2 = Typography.registered;
        goto L961
    L266:
        if (r3.equals("PR") == false) goto L961;
        r2 = 173;
        goto L961
    L270:
        if (r3.equals("PM") == false) goto L961;
        r2 = 172;
        goto L961
    L274:
        if (r3.equals("PL") == false) goto L961;
        r2 = 171;
        goto L961
    L278:
        if (r3.equals("PK") == false) goto L961;
        r2 = 170;
        goto L961
    L282:
        if (r3.equals("PH") == false) goto L961;
        r2 = Typography.copyright;
        goto L961
    L286:
        if (r3.equals("PG") == false) goto L961;
        r2 = 168;
        goto L961
    L290:
        if (r3.equals("PF") == false) goto L961;
        r2 = Typography.section;
        goto L961
    L294:
        if (r3.equals("PE") == false) goto L961;
        r2 = 166;
        goto L961
    L298:
        if (r3.equals("PA") == false) goto L961;
        r2 = 165;
        goto L961
    L302:
        if (r3.equals("OM") == false) goto L961;
        r2 = 164;
        goto L961
    L306:
        if (r3.equals("NZ") == false) goto L961;
        r2 = Typography.pound;
        goto L961
    L310:
        if (r3.equals("NU") == false) goto L961;
        r2 = Typography.cent;
        goto L961
    L314:
        if (r3.equals("NR") == false) goto L961;
        r2 = 161;
        goto L961
    L318:
        if (r3.equals("NP") == false) goto L961;
        r2 = Typography.nbsp;
        goto L961
    L322:
        if (r3.equals("NO") == false) goto L961;
        r2 = 159;
        goto L961
    L326:
        if (r3.equals("NL") == false) goto L961;
        r2 = 158;
        goto L961
    L330:
        if (r3.equals("NI") == false) goto L961;
        r2 = 157;
        goto L961
    L334:
        if (r3.equals("NG") == false) goto L961;
        r2 = 156;
        goto L961
    L338:
        if (r3.equals("NF") == false) goto L961;
        r2 = 155;
        goto L961
    L342:
        if (r3.equals("NE") == false) goto L961;
        r2 = 154;
        goto L961
    L346:
        if (r3.equals("NC") == false) goto L961;
        r2 = 153;
        goto L961
    L350:
        if (r3.equals("NA") == false) goto L961;
        r2 = 152;
        goto L961
    L354:
        if (r3.equals("MZ") == false) goto L961;
        r2 = 151;
        goto L961
    L358:
        if (r3.equals("MY") == false) goto L961;
        r2 = 150;
        goto L961
    L362:
        if (r3.equals("MX") == false) goto L961;
        r2 = 149;
        goto L961
    L366:
        if (r3.equals("MW") == false) goto L961;
        r2 = 148;
        goto L961
    L370:
        if (r3.equals("MV") == false) goto L961;
        r2 = 147;
        goto L961
    L374:
        if (r3.equals("MU") == false) goto L961;
        r2 = 146;
        goto L961
    L378:
        if (r3.equals("MT") == false) goto L961;
        r2 = 145;
        goto L961
    L382:
        if (r3.equals("MS") == false) goto L961;
        r2 = 144;
        goto L961
    L386:
        if (r3.equals("MR") == false) goto L961;
        r2 = 143;
        goto L961
    L390:
        if (r3.equals("MQ") == false) goto L961;
        r2 = 142;
        goto L961
    L394:
        if (r3.equals("MP") == false) goto L961;
        r2 = 141;
        goto L961
    L398:
        if (r3.equals("MO") == false) goto L961;
        r2 = 140;
        goto L961
    L402:
        if (r3.equals("MN") == false) goto L961;
        r2 = 139;
        goto L961
    L406:
        if (r3.equals("MM") == false) goto L961;
        r2 = 138;
        goto L961
    L410:
        if (r3.equals("ML") == false) goto L961;
        r2 = 137;
        goto L961
    L414:
        if (r3.equals("MK") == false) goto L961;
        r2 = 136;
        goto L961
    L418:
        if (r3.equals("MH") == false) goto L961;
        r2 = 135;
        goto L961
    L422:
        if (r3.equals("MG") == false) goto L961;
        r2 = 134;
        goto L961
    L426:
        if (r3.equals("MF") == false) goto L961;
        r2 = 133;
        goto L961
    L430:
        if (r3.equals("ME") == false) goto L961;
        r2 = 132;
        goto L961
    L434:
        if (r3.equals("MD") == false) goto L961;
        r2 = 131;
        goto L961
    L438:
        if (r3.equals("MC") == false) goto L961;
        r2 = 130;
        goto L961
    L442:
        if (r3.equals("MA") == false) goto L961;
        r2 = 129;
        goto L961
    L446:
        if (r3.equals("LY") == false) goto L961;
        r2 = 128;
        goto L961
    L450:
        if (r3.equals("LV") == false) goto L961;
        r2 = 127;
        goto L961
    L454:
        if (r3.equals("LU") == false) goto L961;
        r2 = '~';
        goto L961
    L458:
        if (r3.equals("LT") == false) goto L961;
        r2 = '}';
        goto L961
    L462:
        if (r3.equals("LS") == false) goto L961;
        r2 = '|';
        goto L961
    L466:
        if (r3.equals("LR") == false) goto L961;
        r2 = '{';
        goto L961
    L470:
        if (r3.equals("LK") == false) goto L961;
        r2 = 'z';
        goto L961
    L474:
        if (r3.equals("LI") == false) goto L961;
        r2 = 'y';
        goto L961
    L478:
        if (r3.equals("LC") == false) goto L961;
        r2 = 'x';
        goto L961
    L482:
        if (r3.equals("LB") == false) goto L961;
        r2 = 'w';
        goto L961
    L486:
        if (r3.equals("LA") == false) goto L961;
        r2 = 'v';
        goto L961
    L490:
        if (r3.equals("KZ") == false) goto L961;
        r2 = 'u';
        goto L961
    L494:
        if (r3.equals("KY") == false) goto L961;
        r2 = 't';
        goto L961
    L498:
        if (r3.equals("KW") == false) goto L961;
        r2 = 's';
        goto L961
    L502:
        if (r3.equals("KR") == false) goto L961;
        r2 = 'r';
        goto L961
    L506:
        if (r3.equals("KN") == false) goto L961;
        r2 = 'q';
        goto L961
    L510:
        if (r3.equals("KM") == false) goto L961;
        r2 = 'p';
        goto L961
    L514:
        if (r3.equals("KI") == false) goto L961;
        r2 = 'o';
        goto L961
    L518:
        if (r3.equals("KH") == false) goto L961;
        r2 = 'n';
        goto L961
    L522:
        if (r3.equals("KG") == false) goto L961;
        r2 = 'm';
        goto L961
    L526:
        if (r3.equals("KE") == false) goto L961;
        r2 = 'l';
        goto L961
    L530:
        if (r3.equals("JP") == false) goto L961;
        r2 = 'k';
        goto L961
    L534:
        if (r3.equals("JO") == false) goto L961;
        r2 = 'j';
        goto L961
    L538:
        if (r3.equals("JM") == false) goto L961;
        r2 = 'i';
        goto L961
    L542:
        if (r3.equals("JE") == false) goto L961;
        r2 = 'h';
        goto L961
    L546:
        if (r3.equals("IT") == false) goto L961;
        r2 = 'g';
        goto L961
    L550:
        if (r3.equals("IS") == false) goto L961;
        r2 = 'f';
        goto L961
    L554:
        if (r3.equals("IR") == false) goto L961;
        r2 = 'e';
        goto L961
    L558:
        if (r3.equals("IQ") == false) goto L961;
        r2 = 'd';
        goto L961
    L562:
        if (r3.equals("IO") == false) goto L961;
        r2 = 'c';
        goto L961
    L566:
        if (r3.equals("IN") == false) goto L961;
        r2 = 'b';
        goto L961
    L570:
        if (r3.equals("IM") == false) goto L961;
        r2 = 'a';
        goto L961
    L574:
        if (r3.equals("IL") == false) goto L961;
        r2 = '`';
        goto L961
    L578:
        if (r3.equals("IE") == false) goto L961;
        r2 = '_';
        goto L961
    L582:
        if (r3.equals("ID") == false) goto L961;
        r2 = '^';
        goto L961
    L586:
        if (r3.equals("HU") == false) goto L961;
        r2 = ']';
        goto L961
    L590:
        if (r3.equals("HT") == false) goto L961;
        r2 = '\\';
        goto L961
    L594:
        if (r3.equals("HR") == false) goto L961;
        r2 = '[';
        goto L961
    L598:
        if (r3.equals("HK") == false) goto L961;
        r2 = 'Z';
        goto L961
    L602:
        if (r3.equals("GY") == false) goto L961;
        r2 = 'Y';
        goto L961
    L606:
        if (r3.equals("GW") == false) goto L961;
        r2 = 'X';
        goto L961
    L610:
        if (r3.equals("GU") == false) goto L961;
        r2 = 'W';
        goto L961
    L614:
        if (r3.equals("GT") == false) goto L961;
        r2 = 'V';
        goto L961
    L618:
        if (r3.equals("GR") == false) goto L961;
        r2 = 'U';
        goto L961
    L622:
        if (r3.equals("GQ") == false) goto L961;
        r2 = 'T';
        goto L961
    L626:
        if (r3.equals("GP") == false) goto L961;
        r2 = 'S';
        goto L961
    L630:
        if (r3.equals("GN") == false) goto L961;
        r2 = 'R';
        goto L961
    L634:
        if (r3.equals("GM") == false) goto L961;
        r2 = 'Q';
        goto L961
    L638:
        if (r3.equals("GL") == false) goto L961;
        r2 = 'P';
        goto L961
    L642:
        if (r3.equals("GI") == false) goto L961;
        r2 = 'O';
        goto L961
    L646:
        if (r3.equals("GH") == false) goto L961;
        r2 = 'N';
        goto L961
    L650:
        if (r3.equals("GG") == false) goto L961;
        r2 = 'M';
        goto L961
    L654:
        if (r3.equals("GF") == false) goto L961;
        r2 = 'L';
        goto L961
    L658:
        if (r3.equals("GE") == false) goto L961;
        r2 = 'K';
        goto L961
    L662:
        if (r3.equals("GD") == false) goto L961;
        r2 = 'J';
        goto L961
    L666:
        if (r3.equals("GB") == false) goto L961;
        r2 = 'I';
        goto L961
    L670:
        if (r3.equals("GA") == false) goto L961;
        r2 = 'H';
        goto L961
    L674:
        if (r3.equals("FR") == false) goto L961;
        r2 = 'G';
        goto L961
    L678:
        if (r3.equals("FO") == false) goto L961;
        r2 = 'F';
        goto L961
    L682:
        if (r3.equals("FM") == false) goto L961;
        r2 = 'E';
        goto L961
    L686:
        if (r3.equals("FK") == false) goto L961;
        r2 = 'D';
        goto L961
    L690:
        if (r3.equals("FJ") == false) goto L961;
        r2 = 'C';
        goto L961
    L694:
        if (r3.equals("FI") == false) goto L961;
        r2 = 'B';
        goto L961
    L698:
        if (r3.equals("ET") == false) goto L961;
        r2 = 'A';
        goto L961
    L702:
        if (r3.equals("ES") == false) goto L961;
        r2 = '@';
        goto L961
    L706:
        if (r3.equals("ER") == false) goto L961;
        r2 = '?';
        goto L961
    L710:
        if (r3.equals("EG") == false) goto L961;
        r2 = Typography.greater;
        goto L961
    L714:
        if (r3.equals("EE") == false) goto L961;
        r2 = SignatureVisitor.INSTANCEOF;
        goto L961
    L718:
        if (r3.equals("EC") == false) goto L961;
        r2 = Typography.less;
        goto L961
    L722:
        if (r3.equals("DZ") == false) goto L961;
        r2 = ';';
        goto L961
    L726:
        if (r3.equals("DO") == false) goto L961;
        r2 = ':';
        goto L961
    L730:
        if (r3.equals("DM") == false) goto L961;
        r2 = '9';
        goto L961
    L734:
        if (r3.equals("DK") == false) goto L961;
        r2 = '8';
        goto L961
    L738:
        if (r3.equals("DJ") == false) goto L961;
        r2 = '7';
        goto L961
    L742:
        if (r3.equals("DE") == false) goto L961;
        r2 = '6';
        goto L961
    L746:
        if (r3.equals("CZ") == false) goto L961;
        r2 = '5';
        goto L961
    L750:
        if (r3.equals("CY") == false) goto L961;
        r2 = '4';
        goto L961
    L754:
        if (r3.equals("CX") == false) goto L961;
        r2 = '3';
        goto L961
    L758:
        if (r3.equals("CW") == false) goto L961;
        r2 = '2';
        goto L961
    L762:
        if (r3.equals("CV") == false) goto L961;
        r2 = '1';
        goto L961
    L766:
        if (r3.equals("CU") == false) goto L961;
        r2 = '0';
        goto L961
    L770:
        if (r3.equals("CR") == false) goto L961;
        r2 = '/';
        goto L961
    L774:
        if (r3.equals("CO") == false) goto L961;
        r2 = '.';
        goto L961
    L778:
        if (r3.equals("CN") == false) goto L961;
        r2 = SignatureVisitor.SUPER;
        goto L961
    L782:
        if (r3.equals("CM") == false) goto L961;
        r2 = ',';
        goto L961
    L786:
        if (r3.equals("CL") == false) goto L961;
        r2 = SignatureVisitor.EXTENDS;
        goto L961
    L790:
        if (r3.equals("CK") == false) goto L961;
        r2 = '*';
        goto L961
    L794:
        if (r3.equals("CI") == false) goto L961;
        r2 = ')';
        goto L961
    L798:
        if (r3.equals("CH") == false) goto L961;
        r2 = '(';
        goto L961
    L802:
        if (r3.equals("CG") == false) goto L961;
        r2 = '\'';
        goto L961
    L806:
        if (r3.equals("CF") == false) goto L961;
        r2 = Typography.amp;
        goto L961
    L810:
        if (r3.equals("CD") == false) goto L961;
        r2 = '%';
        goto L961
    L814:
        if (r3.equals("CA") == false) goto L961;
        r2 = Typography.dollar;
        goto L961
    L818:
        if (r3.equals("BZ") == false) goto L961;
        r2 = '#';
        goto L961
    L822:
        if (r3.equals("BY") == false) goto L961;
        r2 = Typography.quote;
        goto L961
    L826:
        if (r3.equals("BW") == false) goto L961;
        r2 = '!';
        goto L961
    L830:
        if (r3.equals("BT") == false) goto L961;
        r2 = ' ';
        goto L961
    L834:
        if (r3.equals("BS") == false) goto L961;
        r2 = 31;
        goto L961
    L838:
        if (r3.equals("BR") == false) goto L961;
        r2 = 30;
        goto L961
    L842:
        if (r3.equals("BQ") == false) goto L961;
        r2 = 29;
        goto L961
    L846:
        if (r3.equals("BO") == false) goto L961;
        r2 = 28;
        goto L961
    L850:
        if (r3.equals("BN") == false) goto L961;
        r2 = 27;
        goto L961
    L854:
        if (r3.equals("BM") == false) goto L961;
        r2 = 26;
        goto L961
    L858:
        if (r3.equals("BL") == false) goto L961;
        r2 = 25;
        goto L961
    L862:
        if (r3.equals("BJ") == false) goto L961;
        r2 = 24;
        goto L961
    L866:
        if (r3.equals("BI") == false) goto L961;
        r2 = 23;
        goto L961
    L870:
        if (r3.equals("BH") == false) goto L961;
        r2 = 22;
        goto L961
    L874:
        if (r3.equals("BG") == false) goto L961;
        r2 = 21;
        goto L961
    L878:
        if (r3.equals("BF") == false) goto L961;
        r2 = 20;
        goto L961
    L882:
        if (r3.equals("BE") == false) goto L961;
        r2 = 19;
        goto L961
    L886:
        if (r3.equals("BD") == false) goto L961;
        r2 = 18;
        goto L961
    L890:
        if (r3.equals("BB") == false) goto L961;
        r2 = 17;
        goto L961
    L894:
        if (r3.equals("BA") == false) goto L961;
        r2 = 16;
        goto L961
    L898:
        if (r3.equals("AZ") == false) goto L961;
        r2 = 15;
        goto L961
    L902:
        if (r3.equals("AX") == false) goto L961;
        r2 = 14;
        goto L961
    L906:
        if (r3.equals("AW") == false) goto L961;
        r2 = '\r';
        goto L961
    L910:
        if (r3.equals("AU") == false) goto L961;
        r2 = '\f';
        goto L961
    L914:
        if (r3.equals("AT") == false) goto L961;
        r2 = 11;
        goto L961
    L918:
        if (r3.equals("AS") == false) goto L961;
        r2 = '\n';
        goto L961
    L922:
        if (r3.equals("AR") == false) goto L961;
        r2 = '\t';
        goto L961
    L926:
        if (r3.equals("AQ") == false) goto L961;
        r2 = '\b';
        goto L961
    L930:
        if (r3.equals("AO") == false) goto L961;
        r2 = 7;
        goto L961
    L934:
        if (r3.equals("AM") == false) goto L961;
        r2 = 6;
        goto L961
    L938:
        if (r3.equals("AL") == false) goto L961;
        r2 = 5;
        goto L961
    L942:
        if (r3.equals("AI") == false) goto L961;
        r2 = 4;
        goto L961
    L946:
        if (r3.equals("AG") == false) goto L961;
        r2 = 3;
        goto L961
    L950:
        if (r3.equals("AF") == false) goto L961;
        r2 = 2;
        goto L961
    L954:
        if (r3.equals("AE") == false) goto L961;
        r2 = 1;
        goto L961
    L958:
        if (r3.equals("AD") == false) goto L961;
        r2 = 0;
        goto L961
    }

    private long getInitialBitrateEstimateForNetworkType(int r6) {
        Long r0 = this.initialBitrateEstimates.get(Integer.valueOf(r6));
        if (r0 != null) goto L6;
        r0 = this.initialBitrateEstimates.get(0);
    L8:
        if (r0 != null) goto L11;
        r0 = 1000000L;
    L11:
        return r0.longValue();
    L6:
        if (r0.longValue() != (-9223372036854775807L)) goto L8;
        r0 = Long.valueOf(getInitialBitrateEstimatesForCountry(this.countryCode, r6));
        goto L8
    }

    private static long getInitialBitrateEstimatesForCountry(String r3, int r4) {
        int[] r32 = getInitialBitrateCountryGroupAssignment(Strings.nullToEmpty(r3));
        if (r4 == 2) goto L29;
        if (r4 == 3) goto L27;
        if (r4 == 4) goto L25;
        if (r4 == 5) goto L23;
        if (r4 == 7) goto L29;
        if (r4 == 9) goto L21;
        if (r4 == 10) goto L19;
        return 1000000;
    L19:
        return DEFAULT_INITIAL_BITRATE_ESTIMATES_5G_NSA.get(r32[4]).longValue();
    L21:
        return DEFAULT_INITIAL_BITRATE_ESTIMATES_5G_SA.get(r32[5]).longValue();
    L23:
        return DEFAULT_INITIAL_BITRATE_ESTIMATES_4G.get(r32[3]).longValue();
    L25:
        return DEFAULT_INITIAL_BITRATE_ESTIMATES_3G.get(r32[2]).longValue();
    L27:
        return DEFAULT_INITIAL_BITRATE_ESTIMATES_2G.get(r32[1]).longValue();
    L29:
        return DEFAULT_INITIAL_BITRATE_ESTIMATES_WIFI.get(r32[0]).longValue();
    }

    public static synchronized DefaultBandwidthMeter getSingletonInstance(Context r2) {
        monitor-enter(DefaultBandwidthMeter.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (singletonInstance != null) goto L9;
        singletonInstance = new Builder(r2).build();     // Catch: Throwable -> L7
    L9:
        DefaultBandwidthMeter r22 = singletonInstance;     // Catch: Throwable -> L7
        monitor-exit(DefaultBandwidthMeter.class);
        return r22;
    }

    private static boolean isTransferAtFullNetworkSpeed(DataSpec r0, boolean r1) {
        if (r1 == false) goto L8;
        if (r0 != null) goto L5;
        return true;
    L5:
        if (r0.isFlagSet(8) == true) goto L11;
        return true;
    L11:
        return false;
    L8:
        return false;
    }

    private void maybeNotifyBandwidthSample(int r3, long r4, long r6) {
        if (r3 == 0) goto L4;
    L8:
        this.lastReportedBitrateEstimate = r6;
        this.eventDispatcher.bandwidthSample(r3, r4, r6);
        return;
    L4:
        if (r4 != 0) goto L8;
        if (r6 != this.lastReportedBitrateEstimate) goto L8;
    }

    private synchronized void onNetworkTypeChanged(int r9) {
        monitor-enter(this);
        int r0 = this.networkType;     // Catch: Throwable -> L41
        if (r0 == 0) goto L12;
    L9:
        th = move-exception;
        Throwable r92 = th;
        DefaultBandwidthMeter r2 = this;
    L45:
        monitor-exit(r2);     // Catch: Throwable -> L39
        throw r92;
    L6:
        if (this.resetOnNetworkTypeChange == true) goto L12;
        monitor-exit(this);
        return;
    L12:
        if (this.networkTypeOverrideSet == false) goto L14;
        r9 = this.networkTypeOverride;     // Catch: Throwable -> L9
    L14:
        if (r0 == r9) goto L16;
    L19:
        this.networkType = r9;     // Catch: Throwable -> L41
        if (r9 == 1) goto L43;
        if (r9 == 0) goto L43;
        if (r9 == 8) goto L43;
        if (this.countryCode != null) goto L28;
        this.countryCode = Util.getCountryCode(this.context);     // Catch: Throwable -> L9
    L28:
        this.bitrateEstimate = getInitialBitrateEstimateForNetworkType(r9);     // Catch: Throwable -> L41
        long r02 = this.clock.elapsedRealtime();     // Catch: Throwable -> L41
        if (this.streamCount <= 0) goto L33;
        int r93 = (int) (r02 - this.sampleStartTimeMs);
    L34:
        maybeNotifyBandwidthSample(r93, this.sampleBytesTransferred, this.bitrateEstimate);     // Catch: Throwable -> L39
        this.sampleStartTimeMs = r02;     // Catch: Throwable -> L39
        this.sampleBytesTransferred = 0;     // Catch: Throwable -> L39
        this.totalBytesTransferred = 0;     // Catch: Throwable -> L39
        this.totalElapsedTimeMs = 0;     // Catch: Throwable -> L39
        this.slidingPercentile.reset();     // Catch: Throwable -> L39
        monitor-exit(this);
        return;
    L33:
        r93 = 0;
    L43:
        monitor-exit(this);
        return;
    L16:
        if (this.countryCode == null) goto L19;
        monitor-exit(this);
        return;
    L39:
        th = th;
    L40:
        r92 = th;
    L41:
        th = th;
        r2 = this;
        goto L40
    }

    @Override // androidx.media3.exoplayer.upstream.BandwidthMeter
    public void addEventListener(Handler r1, BandwidthMeter.EventListener r2) {
        Assertions.checkNotNull(r1);
        Assertions.checkNotNull(r2);
        this.eventDispatcher.addListener(r1, r2);
    }

    @Override // androidx.media3.exoplayer.upstream.BandwidthMeter
    public synchronized long getBitrateEstimate() {
        monitor-enter(this);
        long r0 = this.bitrateEstimate;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    @Override // androidx.media3.exoplayer.upstream.BandwidthMeter
    public TransferListener getTransferListener() {
        return this;
    }

    @Override // androidx.media3.datasource.TransferListener
    public synchronized void onBytesTransferred(DataSource r1, DataSpec r2, boolean r3, int r4) {
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L4:
        if (isTransferAtFullNetworkSpeed(r2, r3) == true) goto L7;
        monitor-exit(this);
        return;
    L7:
        this.sampleBytesTransferred += r4;
        monitor-exit(this);
    }

    public synchronized void onTransfer(long r14, long r16) {
        monitor-enter(this);
        long r7 = this.clock.elapsedRealtime();     // Catch: Throwable -> L13
        this.totalElapsedTimeMs += (int) (r7 - this.sampleStartTimeMs);
        this.totalBytesTransferred += r14;
        if (r16 > 0) goto L7;
    L17:
        monitor-exit(this);
        return;
    L7:
        if (r14 <= 0) goto L17;
        this.slidingPercentile.addSample((int) Math.sqrt(r14), (r14 * 8000.0f) / r16);     // Catch: Throwable -> L13
        if (this.totalElapsedTimeMs < 2000) goto L11;
    L15:
        this.bitrateEstimate = (long) this.slidingPercentile.getPercentile(0.5f);     // Catch: Throwable -> L13
    L16:
        maybeNotifyBandwidthSample((int) r16, r14, this.bitrateEstimate);     // Catch: Throwable -> L13
        this.sampleStartTimeMs = r7;     // Catch: Throwable -> L13
        this.sampleBytesTransferred = 0;     // Catch: Throwable -> L13
        goto L17
    L11:
        if (this.totalBytesTransferred < 524288) goto L16;
    L13:
        th = move-exception;
        throw th;
    }

    @Override // androidx.media3.datasource.TransferListener
    public synchronized void onTransferEnd(DataSource r11, DataSpec r12, boolean r13) {
        monitor-enter(this);
    L28:
        th = th;
        DefaultBandwidthMeter r4 = this;
    L27:
        Throwable r112 = th;
    L34:
        monitor-exit(r4);     // Catch: Throwable -> L26
        throw r112;
    L4:
        if (isTransferAtFullNetworkSpeed(r12, r13) == true) goto L8;
        monitor-exit(this);
        return;
    L8:
        if (this.streamCount <= 0) goto L10;
        boolean r113 = true;
    L11:
        Assertions.checkState(r113);     // Catch: Throwable -> L28
        long r0 = this.clock.elapsedRealtime();     // Catch: Throwable -> L28
        int r5 = (int) (r0 - this.sampleStartTimeMs);     // Catch: Throwable -> L28
        this.totalElapsedTimeMs += r5;
        long r2 = this.totalBytesTransferred;     // Catch: Throwable -> L28
        long r6 = this.sampleBytesTransferred;     // Catch: Throwable -> L28
        this.totalBytesTransferred = r2 + r6;     // Catch: Throwable -> L28
        if (r5 <= 0) goto L30;
        this.slidingPercentile.addSample((int) Math.sqrt(r6), (r6 * 8000.0f) / r5);     // Catch: Throwable -> L28
        if (this.totalElapsedTimeMs < 2000) goto L36;
    L22:
        this.bitrateEstimate = (long) this.slidingPercentile.getPercentile(0.5f);     // Catch: Throwable -> L28
    L23:
        DefaultBandwidthMeter r42 = this;
        r42.maybeNotifyBandwidthSample(r5, this.sampleBytesTransferred, this.bitrateEstimate);     // Catch: Throwable -> L26
        r42.sampleStartTimeMs = r0;     // Catch: Throwable -> L26
        r42.sampleBytesTransferred = 0;     // Catch: Throwable -> L26
    L31:
        r42.streamCount--;
        monitor-exit(r42);
        return;
    L36:
        if (this.totalBytesTransferred < 524288) goto L23;
    L20:
        th = move-exception;
        r112 = th;
        r4 = this;
        goto L34
    L30:
        r42 = this;
        goto L31
    L10:
        r113 = false;
    L26:
        th = th;
        goto L27
    }

    @Override // androidx.media3.datasource.TransferListener
    public void onTransferInitializing(DataSource r1, DataSpec r2, boolean r3) {
    }

    @Override // androidx.media3.datasource.TransferListener
    public synchronized void onTransferStart(DataSource r1, DataSpec r2, boolean r3) {
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L4:
        if (isTransferAtFullNetworkSpeed(r2, r3) == true) goto L8;
        monitor-exit(this);
        return;
    L8:
        if (this.streamCount != 0) goto L12;
        this.sampleStartTimeMs = this.clock.elapsedRealtime();     // Catch: Throwable -> L10
    L12:
        this.streamCount++;
        monitor-exit(this);
    }

    @Override // androidx.media3.exoplayer.upstream.BandwidthMeter
    public void removeEventListener(BandwidthMeter.EventListener r1) {
        this.eventDispatcher.removeListener(r1);
    }
}

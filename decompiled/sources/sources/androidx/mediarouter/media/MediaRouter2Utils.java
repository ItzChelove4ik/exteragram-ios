package androidx.mediarouter.media;

import android.content.IntentFilter;
import android.media.MediaRoute2Info;
import android.media.RouteDiscoveryPreference;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.Log;
import androidx.media3.exoplayer.DefaultSuitableOutputChecker$ImplApi35$$ExternalSyntheticApiModelOutline0;
import androidx.media3.exoplayer.DefaultSuitableOutputChecker$ImplApi35$$ExternalSyntheticApiModelOutline1;
import androidx.media3.exoplayer.DefaultSuitableOutputChecker$ImplApi35$$ExternalSyntheticApiModelOutline3;
import androidx.mediarouter.media.MediaRouteDescriptor;
import androidx.mediarouter.media.MediaRouteSelector;
import com.google.android.gms.cast.CastStatusCodes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import okhttp3.internal.ws.WebSocketProtocol;

/* loaded from: classes4.dex */
class MediaRouter2Utils {
    static final String FEATURE_EMPTY = "android.media.route.feature.EMPTY";
    private static final String FEATURE_REMOTE_DYNAMIC_GROUP_ROUTE = "android.media.route.feature.REMOTE_DYNAMIC_GROUP_ROUTE";
    private static final String FEATURE_REMOTE_GROUP_PLAYBACK = "android.media.route.feature.REMOTE_GROUP_PLAYBACK";
    static final String KEY_CONTROL_FILTERS = "androidx.mediarouter.media.KEY_CONTROL_FILTERS";
    static final String KEY_DEVICE_TYPE = "androidx.mediarouter.media.KEY_DEVICE_TYPE";
    static final String KEY_EXTRAS = "androidx.mediarouter.media.KEY_EXTRAS";
    private static final String KEY_GROUP_MEMBER_IDS = "androidx.mediarouter.media.KEY_GROUP_MEMBER_IDS";
    static final String KEY_GROUP_ROUTE = "androidx.mediarouter.media.KEY_GROUP_ROUTE";
    static final String KEY_MESSENGER = "androidx.mediarouter.media.KEY_MESSENGER";
    static final String KEY_ORIGINAL_ROUTE_ID = "androidx.mediarouter.media.KEY_ORIGINAL_ROUTE_ID";
    static final String KEY_PLAYBACK_TYPE = "androidx.mediarouter.media.KEY_PLAYBACK_TYPE";
    static final String KEY_SESSION_NAME = "androidx.mediarouter.media.KEY_SESSION_NAME";
    private static final String TAG = "MediaRouter2Utils";
    private static final int TYPE_HDMI_ARC = 10;
    private static final int TYPE_HDMI_EARC = 29;
    private static final int TYPE_REMOTE_CAR = 1008;
    private static final int TYPE_REMOTE_COMPUTER = 1006;
    private static final int TYPE_REMOTE_GAME_CONSOLE = 1007;
    private static final int TYPE_REMOTE_SMARTPHONE = 1010;
    private static final int TYPE_REMOTE_SMARTWATCH = 1009;
    private static final int TYPE_REMOTE_TABLET = 1004;
    private static final int TYPE_REMOTE_TABLET_DOCKED = 1005;

    public static final class Api34Impl {
        private Api34Impl() {
        }

        public static void copyDescriptorVisibilityToBuilder(MediaRoute2Info.Builder r1, MediaRouteDescriptor r2) {
            if (r2.isVisibilityPublic() == false) goto L6;
            r1.setVisibilityPublic();
            return;
        L6:
            r1.setVisibilityRestricted(r2.getAllowedPackages());
        }

        public static Set<String> getDeduplicationIds(MediaRoute2Info r0) {
            return r0.getDeduplicationIds();
        }

        public static int getType(MediaRoute2Info r0) {
            return r0.getType();
        }

        public static void setDeduplicationIds(MediaRoute2Info.Builder r0, Set<String> r1) {
            r0.setDeduplicationIds(r1);
        }

        public static void setDeviceType(MediaRoute2Info.Builder r0, int r1) {
            r0.setType(r1);
        }
    }

    private MediaRouter2Utils() {
    }

    private static int androidXDeviceTypeToFwkDeviceType(int r1) {
        if (r1 == 1000) goto L54;
        switch(r1) {
            case 1: goto L52;
            case 2: goto L50;
            case 3: goto L48;
            case 4: goto L46;
            case 5: goto L44;
            case 6: goto L42;
            case 7: goto L40;
            case 8: goto L38;
            case 9: goto L36;
            case 10: goto L34;
            case 11: goto L32;
            case 12: goto L30;
            case 13: goto L28;
            case 14: goto L26;
            default: goto L5;
        };
    L5:
        switch(r1) {
            case 16: goto L24;
            case 17: goto L22;
            case 18: goto L20;
            case 19: goto L18;
            case 20: goto L16;
            case 21: goto L14;
            case 22: goto L12;
            case 23: goto L10;
            case 24: goto L8;
            default: goto L6;
        };
    L6:
        return 0;
    L8:
        return 29;
    L10:
        return 10;
    L12:
        return 26;
    L14:
        return 23;
    L16:
        return 22;
    L18:
        return 13;
    L20:
        return 12;
    L22:
        return 11;
    L24:
        return 9;
    L26:
        return 4;
    L28:
        return 3;
    L30:
        return 2;
    L32:
        return TYPE_REMOTE_SMARTPHONE;
    L34:
        return TYPE_REMOTE_SMARTWATCH;
    L36:
        return TYPE_REMOTE_CAR;
    L38:
        return TYPE_REMOTE_GAME_CONSOLE;
    L40:
        return TYPE_REMOTE_COMPUTER;
    L42:
        return 1005;
    L44:
        return TYPE_REMOTE_TABLET;
    L46:
        return 1003;
    L48:
        return 8;
    L50:
        return 1002;
    L52:
        return WebSocketProtocol.CLOSE_CLIENT_GOING_AWAY;
    L54:
        return CastStatusCodes.AUTHENTICATION_FAILED;
    }

    private static int fwkDeviceTypeToAndroidXDeviceType(int r6) {
        if (r6 != 2) goto L5;
        return 12;
    L5:
        if (r6 != 3) goto L7;
        return 13;
    L7:
        if (r6 != 4) goto L9;
        return 14;
    L9:
        if (r6 != 22) goto L11;
        return 20;
    L11:
        if (r6 != 23) goto L13;
        return 21;
    L13:
        if (r6 != 26) goto L15;
        return 22;
    L15:
        if (r6 != 29) goto L17;
        return 24;
    L17:
        if (r6 == 2000) goto L50;
        switch(r6) {
            case 8: goto L49;
            case 9: goto L47;
            case 10: goto L46;
            case 11: goto L44;
            case 12: goto L42;
            case 13: goto L40;
            default: goto L19;
        };
    L19:
        switch(r6) {
            case 1001: goto L38;
            case 1002: goto L37;
            case 1003: goto L36;
            case 1004: goto L34;
            case 1005: goto L32;
            case 1006: goto L30;
            case 1007: goto L28;
            case 1008: goto L26;
            case 1009: goto L24;
            case 1010: goto L22;
            default: goto L20;
        };
    L20:
        return 0;
    L22:
        return 11;
    L24:
        return 10;
    L26:
        return 9;
    L28:
        return 8;
    L30:
        return 7;
    L32:
        return 6;
    L34:
        return 5;
    L36:
        return 4;
    L37:
        return 2;
    L38:
        return 1;
    L40:
        return 19;
    L42:
        return 18;
    L44:
        return 17;
    L46:
        return 23;
    L47:
        return 16;
    L49:
        return 3;
    L50:
        return 1000;
    }

    public static List<String> getRouteIds(List<MediaRoute2Info> r2) {
        if (r2 == null) goto L4;
        ArrayList r0 = new ArrayList();
        Iterator<MediaRoute2Info> r22 = r2.iterator();
    L7:
        if (r22.hasNext() == false) goto L12;
        MediaRoute2Info r1 = DefaultSuitableOutputChecker$ImplApi35$$ExternalSyntheticApiModelOutline3.m(r22.next());
        if (r1 == null) goto L7;
        r0.add(r1.getId());
        goto L7
    L12:
        return r0;
    L4:
        return new ArrayList();
    }

    public static String toControlCategory(String r2) {
        r2.getClass();
        char r1 = 65535;
        switch(r2.hashCode()) {
            case -1282314761: goto L22;
            case -1190404366: goto L18;
            case 94496206: goto L14;
            case 1328964233: goto L10;
            case 1348000558: goto L6;
            default: goto L25;
        };
    L25:
        switch(r1) {
            case 0: goto L35;
            case 1: goto L33;
            case 2: goto L31;
            case 3: goto L29;
            case 4: goto L27;
            default: goto L26;
        };
    L26:
        return r2;
    L27:
        return MediaControlIntent.CATEGORY_LIVE_VIDEO;
    L29:
        return MediaControlIntent.CATEGORY_LIVE_AUDIO;
    L31:
        return MediaControlIntent.CATEGORY_REMOTE_PLAYBACK;
    L33:
        return MediaControlIntent.CATEGORY_REMOTE_VIDEO_PLAYBACK;
    L35:
        return MediaControlIntent.CATEGORY_REMOTE_AUDIO_PLAYBACK;
    L6:
        if (r2.equals("android.media.route.feature.LIVE_VIDEO") == false) goto L25;
        r1 = 4;
        goto L25
    L10:
        if (r2.equals("android.media.route.feature.LIVE_AUDIO") == false) goto L25;
        r1 = 3;
        goto L25
    L14:
        if (r2.equals("android.media.route.feature.REMOTE_PLAYBACK") == false) goto L25;
        r1 = 2;
        goto L25
    L18:
        if (r2.equals("android.media.route.feature.REMOTE_VIDEO_PLAYBACK") == false) goto L25;
        r1 = 1;
        goto L25
    L22:
        if (r2.equals("android.media.route.feature.REMOTE_AUDIO_PLAYBACK") == false) goto L25;
        r1 = 0;
        goto L25
    }

    public static List<IntentFilter> toControlFilters(Collection<String> r4) {
        if (r4 == null) goto L4;
        ArrayList r0 = new ArrayList();
        ArraySet r1 = new ArraySet();
        Iterator<String> r42 = r4.iterator();
    L7:
        if (r42.hasNext() == false) goto L12;
        String r2 = r42.next();
        if (r1.contains(r2) == true) goto L7;
        r1.add(r2);
        IntentFilter r3 = new IntentFilter();
        r3.addCategory(toControlCategory(r2));
        r0.add(r3);
        goto L7
    L12:
        return r0;
    L4:
        return new ArrayList();
    }

    public static RouteDiscoveryPreference toDiscoveryPreference(MediaRouteDiscoveryRequest r3) {
        if (r3 != null) goto L4;
    L12:
        DefaultSuitableOutputChecker$ImplApi35$$ExternalSyntheticApiModelOutline1.m();
        return DefaultSuitableOutputChecker$ImplApi35$$ExternalSyntheticApiModelOutline0.m(new ArrayList(), false).build();
    L4:
        if (r3.isValid() == false) goto L12;
        boolean r0 = r3.isActiveScan();
        ArrayList r1 = new ArrayList();
        Iterator<String> r32 = r3.getSelector().getControlCategories().iterator();
    L8:
        if (r32.hasNext() == false) goto L11;
        r1.add(toRouteFeature(r32.next()));
        goto L8
    L11:
        return DefaultSuitableOutputChecker$ImplApi35$$ExternalSyntheticApiModelOutline0.m(r1, r0).build();
    }

    public static Collection<String> toFeatures(List<IntentFilter> r5) {
        HashSet r0 = new HashSet();
        Iterator<IntentFilter> r52 = r5.iterator();
    L4:
        if (r52.hasNext() == false) goto L8;
        IntentFilter r1 = r52.next();
        int r2 = r1.countCategories();
        int r3 = 0;
    L6:
        if (r3 >= r2) goto L4;
        r0.add(toRouteFeature(r1.getCategory(r3)));
        r3 = r3 + 1;
        goto L6
    L8:
        return r0;
    }

    public static MediaRoute2Info toFwkMediaRoute2Info(MediaRouteDescriptor r4) {
        if (r4 != null) goto L6;
        return null;
    L6:
        if (TextUtils.isEmpty(r4.getId()) == false) goto L8;
    L31:
        return null;
    L8:
        if (TextUtils.isEmpty(r4.getName()) == true) goto L31;
        MediaRouter2Utils$$ExternalSyntheticApiModelOutline1.m();
        MediaRoute2Info.Builder r0 = MediaRouter2Utils$$ExternalSyntheticApiModelOutline0.m(r4.getId(), r4.getName()).setDescription(r4.getDescription()).setConnectionState(r4.getConnectionState()).setVolumeHandling(r4.getVolumeHandling()).setVolume(r4.getVolume()).setVolumeMax(r4.getVolumeMax()).addFeatures(toFeatures(r4.getControlFilters())).setIconUri(r4.getIconUri());
        if (Build.VERSION.SDK_INT < 34) goto L13;
        Api34Impl.setDeduplicationIds(r0, r4.getDeduplicationIds());
        Api34Impl.copyDescriptorVisibilityToBuilder(r0, r4);
        Api34Impl.setDeviceType(r0, androidXDeviceTypeToFwkDeviceType(r4.getDeviceType()));
    L13:
        int r1 = r4.getDeviceType();
        if (r1 != 1) goto L16;
        r0.addFeature("android.media.route.feature.REMOTE_VIDEO_PLAYBACK");
    L19:
        r0.addFeature("android.media.route.feature.REMOTE_AUDIO_PLAYBACK");
    L21:
        if (r4.isDynamicGroupRoute() == false) goto L23;
        r0.addFeature(FEATURE_REMOTE_DYNAMIC_GROUP_ROUTE);
    L23:
        Bundle r12 = new Bundle();
        r12.putBundle(KEY_EXTRAS, r4.getExtras());
        r12.putParcelableArrayList(KEY_CONTROL_FILTERS, new ArrayList(r4.getControlFilters()));
        r12.putInt(KEY_DEVICE_TYPE, r4.getDeviceType());
        r12.putInt(KEY_PLAYBACK_TYPE, r4.getPlaybackType());
        r12.putString(KEY_ORIGINAL_ROUTE_ID, r4.getId());
        if (r4.getGroupMemberIds().isEmpty() == true) goto L26;
        r0.addFeature(FEATURE_REMOTE_GROUP_PLAYBACK);
        r12.putStringArrayList(KEY_GROUP_MEMBER_IDS, new ArrayList(r4.getGroupMemberIds()));
    L26:
        r0.setExtras(r12);
        if (r4.getControlFilters().isEmpty() == false) goto L30;
        r0.addFeature(FEATURE_EMPTY);
    L30:
        return r0.build();
    L16:
        if (r1 == 2) goto L19;
        goto L19
    }

    public static MediaRouteDescriptor toMediaRouteDescriptor(MediaRoute2Info r10) {
        if (r10 != null) goto L5;
        return null;
    L5:
        MediaRouteDescriptor.Builder r1 = new MediaRouteDescriptor.Builder(r10.getId(), r10.getName().toString()).setConnectionState(r10.getConnectionState()).setVolumeHandling(r10.getVolumeHandling()).setVolumeMax(r10.getVolumeMax()).setVolume(r10.getVolume()).setExtras(r10.getExtras()).setEnabled(true).setCanDisconnect(false);
        if (Build.VERSION.SDK_INT < 34) goto L8;
        r1.setDeduplicationIds(Api34Impl.getDeduplicationIds(r10));
        int r4 = fwkDeviceTypeToAndroidXDeviceType(Api34Impl.getType(r10));
    L9:
        CharSequence r5 = r10.getDescription();
        if (r5 == null) goto L12;
        r1.setDescription(r5.toString());
    L12:
        Uri r52 = r10.getIconUri();
        if (r52 == null) goto L15;
        r1.setIconUri(r52);
    L15:
        Bundle r53 = r10.getExtras();
        if (r53 != null) goto L18;
    L45:
        return null;
    L18:
        if (r53.containsKey(KEY_EXTRAS) == false) goto L45;
        if (r53.containsKey(KEY_DEVICE_TYPE) == false) goto L45;
        if (r53.containsKey(KEY_CONTROL_FILTERS) == false) goto L45;
        r1.setExtras(r53.getBundle(KEY_EXTRAS));
        if (r4 != 0) goto L28;
        r4 = r53.getInt(KEY_DEVICE_TYPE, 0);
    L28:
        r1.setDeviceType(r4);
        r1.setPlaybackType(r53.getInt(KEY_PLAYBACK_TYPE, 1));
        ArrayList r0 = r53.getParcelableArrayList(KEY_CONTROL_FILTERS);
        if (r0 == null) goto L31;
        r1.addControlFilters(r0);
    L31:
        List<String> r102 = r10.getFeatures();
        if (r102.contains(FEATURE_REMOTE_DYNAMIC_GROUP_ROUTE) == false) goto L35;
        r1.setIsDynamicGroupRoute(true);
    L35:
        if (r102.contains(FEATURE_REMOTE_GROUP_PLAYBACK) == false) goto L44;
        ArrayList<String> r103 = r53.getStringArrayList(KEY_GROUP_MEMBER_IDS);
        if (r103 != null) goto L39;
    L42:
        Log.w(TAG, "Invalid feature of a group without members");
        goto L44
    L39:
        if (r103.isEmpty() == true) goto L42;
        r1.addGroupMemberIds(r103);
    L44:
        return r1.build();
    L8:
        r4 = 0;
        goto L9
    }

    public static MediaRouteDiscoveryRequest toMediaRouteDiscoveryRequest(RouteDiscoveryPreference r3) {
        ArrayList r0 = new ArrayList();
        Iterator<String> r1 = r3.getPreferredFeatures().iterator();
    L4:
        if (r1.hasNext() == false) goto L7;
        r0.add(toControlCategory(r1.next()));
        goto L4
    L7:
        return new MediaRouteDiscoveryRequest(new MediaRouteSelector.Builder().addControlCategories(r0).build(), r3.shouldPerformActiveScan());
    }

    public static String toRouteFeature(String r2) {
        r2.getClass();
        char r1 = 65535;
        switch(r2.hashCode()) {
            case -2065577523: goto L22;
            case 956939050: goto L18;
            case 975975375: goto L14;
            case 1601181366: goto L10;
            case 1693091761: goto L6;
            default: goto L25;
        };
    L25:
        switch(r1) {
            case 0: goto L35;
            case 1: goto L33;
            case 2: goto L31;
            case 3: goto L29;
            case 4: goto L27;
            default: goto L26;
        };
    L26:
        return r2;
    L27:
        return "android.media.route.feature.REMOTE_VIDEO_PLAYBACK";
    L29:
        return "android.media.route.feature.REMOTE_AUDIO_PLAYBACK";
    L31:
        return "android.media.route.feature.LIVE_VIDEO";
    L33:
        return "android.media.route.feature.LIVE_AUDIO";
    L35:
        return "android.media.route.feature.REMOTE_PLAYBACK";
    L6:
        if (r2.equals(MediaControlIntent.CATEGORY_REMOTE_VIDEO_PLAYBACK) == false) goto L25;
        r1 = 4;
        goto L25
    L10:
        if (r2.equals(MediaControlIntent.CATEGORY_REMOTE_AUDIO_PLAYBACK) == false) goto L25;
        r1 = 3;
        goto L25
    L14:
        if (r2.equals(MediaControlIntent.CATEGORY_LIVE_VIDEO) == false) goto L25;
        r1 = 2;
        goto L25
    L18:
        if (r2.equals(MediaControlIntent.CATEGORY_LIVE_AUDIO) == false) goto L25;
        r1 = 1;
        goto L25
    L22:
        if (r2.equals(MediaControlIntent.CATEGORY_REMOTE_PLAYBACK) == false) goto L25;
        r1 = 0;
        goto L25
    }
}

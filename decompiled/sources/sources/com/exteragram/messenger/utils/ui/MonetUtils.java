package com.exteragram.messenger.utils.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import androidx.core.graphics.ColorUtils;
import com.google.android.gms.cast.MediaError;
import com.google.android.material.color.MaterialColors;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001,B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000e\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\tH\u0007¢\u0006\u0004\b\u000e\u0010\u000fJ\u0019\u0010\u0011\u001a\u00020\u00062\b\b\u0001\u0010\u0010\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u0015H\u0007¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u0015H\u0007¢\u0006\u0004\b\u001a\u0010\u0019J\u000f\u0010\u001b\u001a\u00020\u0017H\u0003¢\u0006\u0004\b\u001b\u0010\u0003R0\u0010\u001e\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u001cj\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0006`\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u001a\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00040 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"R\u001c\u0010%\u001a\n $*\u0004\u0018\u00010#0#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010&R\u0014\u0010(\u001a\u00020'8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010)R\u0016\u0010*\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b*\u0010+¨\u0006-"}, d2 = {"Lcom/exteragram/messenger/utils/ui/MonetUtils;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "colorString", _UrlKt.FRAGMENT_ENCODE_SET, "getColor", "(Ljava/lang/String;)I", _UrlKt.FRAGMENT_ENCODE_SET, "isSupported", "()Z", "index", "isDark", "getSystemAccentColor", "(IZ)I", "color", "harmonize", "(I)I", "getHarmonizeContextColor", "()I", "Landroid/content/Context;", "context", _UrlKt.FRAGMENT_ENCODE_SET, "registerReceiver", "(Landroid/content/Context;)V", "unregisterReceiver", "initSystemColors", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "COLOR_MAP", "Ljava/util/HashMap;", _UrlKt.FRAGMENT_ENCODE_SET, "ACCENT_PREFIXES", "[Ljava/lang/String;", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "PARAM_PATTERN", "Ljava/util/regex/Pattern;", "Lcom/exteragram/messenger/utils/ui/MonetUtils$OverlayChangeReceiver;", "overlayChangeReceiver", "Lcom/exteragram/messenger/utils/ui/MonetUtils$OverlayChangeReceiver;", "harmonizeContextColor", "I", "OverlayChangeReceiver", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MonetUtils {
    private static final String[] ACCENT_PREFIXES = null;
    private static final HashMap<String, Integer> COLOR_MAP = null;
    public static final MonetUtils INSTANCE = null;
    private static final Pattern PARAM_PATTERN = null;
    private static volatile int harmonizeContextColor;
    private static final OverlayChangeReceiver overlayChangeReceiver = null;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0018\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/exteragram/messenger/utils/ui/MonetUtils$OverlayChangeReceiver;", "Landroid/content/BroadcastReceiver;", "<init>", "()V", "isRegistered", _UrlKt.FRAGMENT_ENCODE_SET, "register", _UrlKt.FRAGMENT_ENCODE_SET, "context", "Landroid/content/Context;", "unregister", "onReceive", "intent", "Landroid/content/Intent;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class OverlayChangeReceiver extends BroadcastReceiver {
        private boolean isRegistered;

        public OverlayChangeReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context r1, Intent r2) {
            if (Intrinsics.areEqual("android.intent.action.OVERLAY_CHANGED", r2.getAction()) == false) goto L10;
            MonetUtils.access$setHarmonizeContextColor$p(0);
            Theme.refreshMonetColors();
            if (Theme.isCurrentThemeMonet() == false) goto L7;
        L8:
            Theme.applyTheme(Theme.getActiveTheme(), Theme.isCurrentThemeNight());
            return;
        L7:
            if (Theme.isCurrentAccentMonet() == true) goto L8;
            return;
        }

        public final void register(Context r4) {
            if (this.isRegistered == false) goto L5;
            return;
        L5:
            IntentFilter r0 = new IntentFilter("android.intent.action.OVERLAY_CHANGED");
            r0.addDataScheme("package");
            r0.addDataSchemeSpecificPart("android", 0);
            r4.registerReceiver(this, r0);
            this.isRegistered = true;
        }

        public final void unregister(Context r2) {
            if (this.isRegistered == true) goto L5;
            return;
        L5:
            r2.unregisterReceiver(this);
            this.isRegistered = false;
        }
    }

    static {
        MonetUtils r0 = new MonetUtils();
        INSTANCE = r0;
        HashMap<String, Integer> r1 = new HashMap();
        COLOR_MAP = r1;
        ACCENT_PREFIXES = new String[]{"a1_", "a2_", "a3_"};
        PARAM_PATTERN = Pattern.compile("^([^(]+)\\(([^)]+)\\)?$");
        overlayChangeReceiver = new OverlayChangeReceiver();
        r1.put("mBlack", Integer.valueOf(R.color.black));
        r1.put("mWhite", Integer.valueOf(R.color.white));
        r1.put("mRed200", Integer.valueOf(R.color.mRed200));
        r1.put("mRed500", Integer.valueOf(R.color.mRed500));
        r1.put("mRed800", Integer.valueOf(R.color.mRed800));
        r1.put("mGreen200", Integer.valueOf(R.color.mGreen200));
        r1.put("mGreen500", Integer.valueOf(R.color.mGreen500));
        r1.put("mGreen800", Integer.valueOf(R.color.mGreen800));
        if (isSupported() == false) goto L6;
        r0.initSystemColors();
        return;
    }

    private MonetUtils() {
    }

    public static final /* synthetic */ void access$setHarmonizeContextColor$p(int r0) {
        harmonizeContextColor = r0;
    }

    @JvmStatic
    public static final int getColor(String r15) {
        if (r15.length() != 0) goto L64;
        return 0;
    L64:
        Matcher r0 = PARAM_PATTERN.matcher(r15);     // Catch: Exception -> L13
        if (r0.find() == false) goto L41;
        String r5 = r0.group(1);     // Catch: Exception -> L13
        if (r5 == null) goto L15;
        String r52 = StringsKt.trim(r5).toString();     // Catch: Exception -> L13
        if (r52 == null) goto L15;
        r15 = r52;
    L15:
        String r53 = r0.group(2);     // Catch: Exception -> L13
        if (r53 == null) goto L41;
        Iterator r02 = StringsKt.split$default(r53, new String[]{","}, false, 0, 6, null).iterator();     // Catch: Exception -> L13
        int r54 = 100;
        int r6 = 100;
        int r7 = 100;
    L19:
        if (r02.hasNext() == false) goto L42;
        List r8 = StringsKt.split$default((String) r02.next(), new String[]{"="}, false, 0, 6, null);     // Catch: Exception -> L13
        if (r8.size() != 2) goto L19;
        String r9 = StringsKt.trim((String) r8.get(0)).toString();     // Catch: Exception -> L13 NumberFormatException -> L63
        int r82 = Integer.parseInt(StringsKt.trim((String) r8.get(1)).toString());     // Catch: Exception -> L13 NumberFormatException -> L63
        int r10 = r9.hashCode();     // Catch: Exception -> L13 NumberFormatException -> L63
        if (r10 != 97) goto L25;
        if (r9.equals("a") == false) goto L19;
        r54 = r82;
        goto L19
    L25:
        if (r10 != 108) goto L27;
        if (r9.equals("l") == false) goto L19;
        r7 = r82;
        goto L19
    L27:
        if (r10 != 115) goto L19;
        if (r9.equals("s") == false) goto L19;
        r6 = r82;
    L42:
        Integer r03 = COLOR_MAP.get(r15);     // Catch: Exception -> L13
        if (r03 != null) goto L45;
    L60:
        return 0;
    L45:
        if (r03.intValue() == 0) goto L60;
        int r04 = ApplicationLoader.applicationContext.getColor(r03.intValue());     // Catch: Exception -> L13
        if (r6 == 100) goto L50;
        r04 = ColorUtils.blendARGB(-1, r04, r6 / 100.0f);     // Catch: Exception -> L13
    L50:
        if (r7 == 100) goto L52;
        r04 = ColorUtils.blendARGB(-16777216, r04, r7 / 100.0f);     // Catch: Exception -> L13
    L52:
        if (r54 == 100) goto L55;
        r04 = ColorUtils.setAlphaComponent(r04, (int) (r54 * 2.55f));     // Catch: Exception -> L13
    L55:
        if (StringsKt.startsWith$default(r15, "mR", false, 2, null) == false) goto L57;
    L58:
        return harmonize(r04);
    L57:
        if (StringsKt.startsWith$default(r15, "mG", false, 2, null) == true) goto L58;
        return r04;
    L41:
        r54 = 100;
        r6 = 100;
        r7 = 100;
    L13:
        e = move-exception;
        FileLog.e(e);
        return 0;
    }

    @JvmStatic
    public static final int getHarmonizeContextColor() {
        if (isSupported() == true) goto L6;
        return 0;
    L6:
        int r0 = harmonizeContextColor;
        if (r0 != 0) goto L10;
        int r02 = ApplicationLoader.applicationContext.getColor(android.R.color.system_accent1_600);
        harmonizeContextColor = r02;
        return r02;
    L10:
        return r0;
    }

    @JvmStatic
    public static final int getSystemAccentColor(int r2, boolean r3) {
        if (isSupported() == false) goto L12;
        if (r2 < 0) goto L14;
        String[] r0 = ACCENT_PREFIXES;
        if (r2 >= r0.length) goto L15;
        if (r3 == false) goto L9;
        int r32 = 200;
    L11:
        return getColor(r0[r2] + r32);
    L9:
        r32 = MediaError.DetailedErrorCode.TEXT_UNKNOWN;
        goto L11
    L15:
        return 0;
    L14:
        return 0;
    L12:
        return 0;
    }

    @JvmStatic
    public static final int harmonize(int r1) {
        int r0 = getHarmonizeContextColor();
        if (r0 != 0) goto L6;
        return r1;
    L6:
        return MaterialColors.harmonize(r1, r0);
    }

    private final void initSystemColors() {
        HashMap<String, Integer> r2 = COLOR_MAP;
        r2.put("a1_10", Integer.valueOf(android.R.color.system_accent1_10));
        r2.put("a1_50", Integer.valueOf(android.R.color.system_accent1_50));
        r2.put("a1_100", Integer.valueOf(android.R.color.system_accent1_100));
        r2.put("a1_200", Integer.valueOf(android.R.color.system_accent1_200));
        r2.put("a1_300", Integer.valueOf(android.R.color.system_accent1_300));
        r2.put("a1_400", Integer.valueOf(android.R.color.system_accent1_400));
        r2.put("a1_500", Integer.valueOf(android.R.color.system_accent1_500));
        r2.put("a1_600", Integer.valueOf(android.R.color.system_accent1_600));
        r2.put("a1_700", Integer.valueOf(android.R.color.system_accent1_700));
        r2.put("a1_800", Integer.valueOf(android.R.color.system_accent1_800));
        r2.put("a1_900", Integer.valueOf(android.R.color.system_accent1_900));
        r2.put("a2_10", Integer.valueOf(android.R.color.system_accent2_10));
        r2.put("a2_50", Integer.valueOf(android.R.color.system_accent2_50));
        r2.put("a2_100", Integer.valueOf(android.R.color.system_accent2_100));
        r2.put("a2_200", Integer.valueOf(android.R.color.system_accent2_200));
        r2.put("a2_300", Integer.valueOf(android.R.color.system_accent2_300));
        r2.put("a2_400", Integer.valueOf(android.R.color.system_accent2_400));
        r2.put("a2_500", Integer.valueOf(android.R.color.system_accent2_500));
        r2.put("a2_600", Integer.valueOf(android.R.color.system_accent2_600));
        r2.put("a2_700", Integer.valueOf(android.R.color.system_accent2_700));
        r2.put("a2_800", Integer.valueOf(android.R.color.system_accent2_800));
        r2.put("a2_900", Integer.valueOf(android.R.color.system_accent2_900));
        r2.put("a3_10", Integer.valueOf(android.R.color.system_accent3_10));
        r2.put("a3_50", Integer.valueOf(android.R.color.system_accent3_50));
        r2.put("a3_100", Integer.valueOf(android.R.color.system_accent3_100));
        r2.put("a3_200", Integer.valueOf(android.R.color.system_accent3_200));
        r2.put("a3_300", Integer.valueOf(android.R.color.system_accent3_300));
        r2.put("a3_400", Integer.valueOf(android.R.color.system_accent3_400));
        r2.put("a3_500", Integer.valueOf(android.R.color.system_accent3_500));
        r2.put("a3_600", Integer.valueOf(android.R.color.system_accent3_600));
        r2.put("a3_700", Integer.valueOf(android.R.color.system_accent3_700));
        r2.put("a3_800", Integer.valueOf(android.R.color.system_accent3_800));
        r2.put("a3_900", Integer.valueOf(android.R.color.system_accent3_900));
        r2.put("n1_10", Integer.valueOf(android.R.color.system_neutral1_10));
        r2.put("n1_50", Integer.valueOf(android.R.color.system_neutral1_50));
        r2.put("n1_100", Integer.valueOf(android.R.color.system_neutral1_100));
        r2.put("n1_200", Integer.valueOf(android.R.color.system_neutral1_200));
        r2.put("n1_300", Integer.valueOf(android.R.color.system_neutral1_300));
        r2.put("n1_400", Integer.valueOf(android.R.color.system_neutral1_400));
        r2.put("n1_500", Integer.valueOf(android.R.color.system_neutral1_500));
        r2.put("n1_600", Integer.valueOf(android.R.color.system_neutral1_600));
        r2.put("n1_700", Integer.valueOf(android.R.color.system_neutral1_700));
        r2.put("n1_800", Integer.valueOf(android.R.color.system_neutral1_800));
        r2.put("n1_900", Integer.valueOf(android.R.color.system_neutral1_900));
        r2.put("n2_10", Integer.valueOf(android.R.color.system_neutral2_10));
        r2.put("n2_50", Integer.valueOf(android.R.color.system_neutral2_50));
        r2.put("n2_100", Integer.valueOf(android.R.color.system_neutral2_100));
        r2.put("n2_200", Integer.valueOf(android.R.color.system_neutral2_200));
        r2.put("n2_300", Integer.valueOf(android.R.color.system_neutral2_300));
        r2.put("n2_400", Integer.valueOf(android.R.color.system_neutral2_400));
        r2.put("n2_500", Integer.valueOf(android.R.color.system_neutral2_500));
        r2.put("n2_600", Integer.valueOf(android.R.color.system_neutral2_600));
        r2.put("n2_700", Integer.valueOf(android.R.color.system_neutral2_700));
        r2.put("n2_800", Integer.valueOf(android.R.color.system_neutral2_800));
        r2.put("n2_900", Integer.valueOf(android.R.color.system_neutral2_900));
    }

    @JvmStatic
    public static final boolean isSupported() {
        if (Build.VERSION.SDK_INT < 31) goto L6;
        return true;
    L6:
        return false;
    }

    @JvmStatic
    public static final void registerReceiver(Context r1) {
        harmonizeContextColor = 0;     // Catch: Exception -> L5
        overlayChangeReceiver.register(r1);     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    @JvmStatic
    public static final void unregisterReceiver(Context r1) {
        overlayChangeReceiver.unregister(r1);     // Catch: Exception -> L4
        return;
    }
}

package com.exteragram.messenger.pillstack.core;

import android.content.Context;
import androidx.annotation.Keep;
import com.android.tools.r8.RecordTag;
import com.exteragram.messenger.ai.network.Client$ImagePayload$$ExternalSyntheticRecord1;
import com.exteragram.messenger.pillstack.ui.pills.BasePill;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.IconBackgroundColors;

/* loaded from: classes.dex */
public class PillRegistry {
    private static boolean batchRegistration;
    private static final Map<Integer, PillInfo> registry = null;

    public interface PillCreator {
        BasePill create(Context r1, Theme.ResourcesProvider r2);
    }

    public static final class PillInfo extends RecordTag {
        private final PillCreator creator;
        private final int iconColorBottom;
        private final int iconColorTop;
        private final int iconRes;
        private final int id;
        private final CharSequence name;

        private /* synthetic */ boolean $record$equals(Object r3) {
            if ((r3 instanceof PillInfo) == false) goto L18;
            PillInfo r32 = (PillInfo) r3;
            if (this.id == r32.id) goto L7;
            return false;
        L7:
            if (this.iconRes == r32.iconRes) goto L9;
            return false;
        L9:
            if (this.iconColorTop == r32.iconColorTop) goto L11;
            return false;
        L11:
            if (this.iconColorBottom == r32.iconColorBottom) goto L13;
            return false;
        L13:
            if (Objects.equals(this.name, r32.name) == true) goto L15;
            return false;
        L15:
            if (Objects.equals(this.creator, r32.creator) == false) goto L25;
            return true;
        L25:
            return false;
        L18:
            return false;
        }

        private /* synthetic */ Object[] $record$getFieldsAsObjects() {
            return new Object[]{Integer.valueOf(this.id), this.name, Integer.valueOf(this.iconRes), Integer.valueOf(this.iconColorTop), Integer.valueOf(this.iconColorBottom), this.creator};
        }

        /* renamed from: -$$Nest$fgetid, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m1511$$Nest$fgetid(PillInfo r0) {
            return r0.id;
        }

        public PillInfo(int r1, CharSequence r2, int r3, int r4, int r5, PillCreator r6) {
            this.id = r1;
            this.name = r2;
            this.iconRes = r3;
            this.iconColorTop = r4;
            this.iconColorBottom = r5;
            this.creator = r6;
        }

        public PillCreator creator() {
            return this.creator;
        }

        public final boolean equals(Object r1) {
            return $record$equals(r1);
        }

        public final int hashCode() {
            return PillRegistry$PillInfo$$ExternalSyntheticRecord0.m(this.id, this.iconRes, this.iconColorTop, this.iconColorBottom, this.name, this.creator);
        }

        public int iconColorBottom() {
            return this.iconColorBottom;
        }

        public int iconColorTop() {
            return this.iconColorTop;
        }

        public int iconRes() {
            return this.iconRes;
        }

        public int id() {
            return this.id;
        }

        public CharSequence name() {
            return this.name;
        }

        public final String toString() {
            return Client$ImagePayload$$ExternalSyntheticRecord1.m($record$getFieldsAsObjects(), PillInfo.class, "id;name;iconRes;iconColorTop;iconColorBottom;creator");
        }
    }

    public static /* synthetic */ void $r8$lambda$8gF5Hej9S1FKAixeCNQQw_qkbkY() {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.pillStackLayoutChanged, new Object[0]);
    }

    public static /* synthetic */ void $r8$lambda$ObPj0mgbFadU_l4l0kCKActpJY8() {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.pillStackLayoutChanged, new Object[0]);
    }

    public static /* synthetic */ void $r8$lambda$ePm8emZNz1po53VFO41mo8g9M_Y() {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.pillStackLayoutChanged, new Object[0]);
    }

    public static /* synthetic */ void $r8$lambda$pyEac_yVzDnar3W8rCYE3sT1ifc() {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.pillStackLayoutChanged, new Object[0]);
    }

    static {
        registry = new LinkedHashMap();
        beginTransaction();
        registerDefaultPills();
        endTransaction();
    }

    @Keep
    public static void activatePill(int r2) {
        if (isRegistered(r2) == true) goto L6;
        return;
    L6:
        if (PillStackConfig.getActivePills().contains(Integer.valueOf(r2)) == true) goto L9;
        PillStackConfig.getHiddenPills().remove(Integer.valueOf(r2));
        PillStackConfig.getActivePills().add(Integer.valueOf(r2));
        PillStackConfig.savePillsLayout();
        AndroidUtilities.runOnUIThread(new PillRegistry$$ExternalSyntheticLambda0());
        return;
    }

    @Keep
    public static void beginTransaction() {
        batchRegistration = true;
    }

    @Keep
    public static void endTransaction() {
        batchRegistration = false;
        if (PillStackConfig.getConfigLoaded() == false) goto L6;
        PillStackConfig.sanitizePills();
        AndroidUtilities.runOnUIThread(new PillRegistry$$ExternalSyntheticLambda1());
        return;
    }

    public static PillInfo getPillInfo(int r1) {
        return registry.get(Integer.valueOf(r1));
    }

    public static Collection<PillInfo> getRegisteredPills() {
        return registry.values();
    }

    public static boolean isRegistered(int r1) {
        return registry.containsKey(Integer.valueOf(r1));
    }

    public static void register(PillInfo r2) {
        registry.put(Integer.valueOf(PillInfo.m1511$$Nest$fgetid(r2)), r2);
        if (batchRegistration == true) goto L6;
        PillStackConfig.sanitizePills();
        AndroidUtilities.runOnUIThread(new PillRegistry$$ExternalSyntheticLambda9());
        return;
    }

    private static void registerDefaultPills() {
        int r1 = PillType.WEATHER.getId();
        String r2 = LocaleController.getString(R.string.WeatherPill);
        int r3 = R.drawable.weather_cloudy;
        IconBackgroundColors r4 = IconBackgroundColors.BLUE_ALT;
        register(new PillInfo(r1, r2, r3, r4.top, r4.bottom, new PillRegistry$$ExternalSyntheticLambda2()));
        int r22 = PillType.GRAM.getId();
        int r42 = R.drawable.settings_gram_24;
        IconBackgroundColors r0 = IconBackgroundColors.BLUE_LIGHT;
        register(new PillInfo(r22, "GRAM", r42, r0.top, r0.bottom, new PillRegistry$$ExternalSyntheticLambda3()));
        int r32 = PillType.BTC.getId();
        int r5 = R.drawable.pillstack_btc_settings;
        IconBackgroundColors r02 = IconBackgroundColors.ORANGE_BRIGHT;
        register(new PillInfo(r32, "BTC", r5, r02.top, r02.bottom, new PillRegistry$$ExternalSyntheticLambda4()));
        int r43 = PillType.USD.getId();
        int r6 = R.drawable.pillstack_usd_settings;
        IconBackgroundColors r03 = IconBackgroundColors.GREEN_DEEP;
        register(new PillInfo(r43, "USD", r6, r03.top, r03.bottom, new PillRegistry$$ExternalSyntheticLambda5()));
        int r52 = PillType.CACHE.getId();
        String r62 = LocaleController.getString(R.string.StorageUsage);
        int r7 = R.drawable.msg_filled_storageusage;
        IconBackgroundColors r04 = IconBackgroundColors.BLUE_DEEP;
        register(new PillInfo(r52, r62, r7, r04.top, r04.bottom, new PillRegistry$$ExternalSyntheticLambda6()));
        int r63 = PillType.PROXY.getId();
        String r72 = LocaleController.getString(R.string.Proxy);
        int r8 = R.drawable.drawer_proxy_on;
        IconBackgroundColors r05 = IconBackgroundColors.GREEN;
        register(new PillInfo(r63, r72, r8, r05.top, r05.bottom, new PillRegistry$$ExternalSyntheticLambda7()));
    }

    @Keep
    public static void unregister(int r1) {
        if (registry.remove(Integer.valueOf(r1)) != null) goto L5;
        return;
    L5:
        if (batchRegistration == true) goto L9;
        PillStackConfig.sanitizePills();
        AndroidUtilities.runOnUIThread(new PillRegistry$$ExternalSyntheticLambda8());
        return;
    }
}

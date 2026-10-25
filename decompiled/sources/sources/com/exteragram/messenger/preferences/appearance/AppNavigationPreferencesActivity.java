package com.exteragram.messenger.preferences.appearance;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.MainMenuItem;
import com.exteragram.messenger.TransitionAnimation;
import com.exteragram.messenger.config.BottomNavigationBar;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.plugins.PluginsController;
import com.exteragram.messenger.preferences.BasePreferencesActivity;
import com.exteragram.messenger.preferences.components.AltSeekbar;
import com.exteragram.messenger.utils.ExtensionsKt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;

@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 @2\u00020\u0001:\u0003?@AB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0017\u001a\u00020\u0018H\u0014J\b\u0010\u0019\u001a\u00020\u0018H\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0014J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0002J\u001e\u0010$\u001a\u00020\u00182\f\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u000f2\u0006\u0010'\u001a\u00020(H\u0014J<\u0010)\u001a\u00020\u00182\f\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u000f2\u0006\u0010'\u001a\u00020(2\u0006\u0010*\u001a\u00020\u001b2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00060\u000f2\u0006\u0010,\u001a\u00020!H\u0002J\u001c\u0010-\u001a\u0004\u0018\u00010&2\u0006\u0010.\u001a\u00020\u00062\b\u0010/\u001a\u0004\u0018\u00010\u0007H\u0002J\u001e\u00100\u001a\u00020\u00182\u0006\u00101\u001a\u00020\u00062\f\u00102\u001a\b\u0012\u0004\u0012\u00020&0\u000fH\u0002J0\u00103\u001a\u00020\u00182\u0006\u00104\u001a\u00020&2\u0006\u00105\u001a\u00020\u001d2\u0006\u00106\u001a\u00020\u00062\u0006\u00107\u001a\u00020#2\u0006\u00108\u001a\u00020#H\u0014J\b\u00109\u001a\u00020\u0018H\u0002J\b\u0010:\u001a\u00020;H\u0002J\u0012\u0010<\u001a\u00020\u00182\b\b\u0002\u0010=\u001a\u00020!H\u0002J\b\u0010>\u001a\u00020\u0018H\u0002R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082.¢\u0006\u0004\n\u0002\u0010\u0014R\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082.¢\u0006\u0004\n\u0002\u0010\u0014R\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082.¢\u0006\u0004\n\u0002\u0010\u0014¨\u0006B"}, d2 = {"Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;", "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;", "<init>", "()V", "itemDetails", "Ljava/util/HashMap;", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;", "reorderIcon", "Landroid/graphics/drawable/Drawable;", "resetItem", "Lorg/telegram/ui/ActionBar/ActionBarMenuItem;", "predictiveBackSeekbar", "Lcom/exteragram/messenger/preferences/components/AltSeekbar;", "stableDividerIds", "Ljava/util/ArrayList;", "nextDividerId", "tabletModeOptions", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "[Ljava/lang/CharSequence;", "bottomNavigationModes", "transitionAnimations", "initializeOptionStrings", _UrlKt.FRAGMENT_ENCODE_SET, "initItemDetails", "getTitle", _UrlKt.FRAGMENT_ENCODE_SET, "createView", "Landroid/view/View;", "context", "Landroid/content/Context;", "isPredictiveBackOff", _UrlKt.FRAGMENT_ENCODE_SET, "value", _UrlKt.FRAGMENT_ENCODE_SET, "fillItems", PluginsConstants.Settings.ITEMS, "Lorg/telegram/ui/Components/UItem;", "adapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "addMenuSection", PluginsConstants.Settings.TYPE_HEADER, "ids", "mainSection", "createMenuItem", "id", "info", "updateConfigFromReorder", "sectionId", "uItems", "onClick", PluginsConstants.Settings.ITEM, PluginsConstants.Settings.VIEW, "position", "x", "y", "saveAndNotify", "refreshEditorList", "Lorg/telegram/ui/Components/UniversalRecyclerView;", "updateResetButtonVisibility", "animated", "resetToDefault", "AppNavigationItem", "Companion", "ItemInfo", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAppNavigationPreferencesActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AppNavigationPreferencesActivity.kt\ncom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 SharedPreferences.kt\nandroidx/core/content/SharedPreferencesKt\n*L\n1#1,425:1\n1#2:426\n41#3,12:427\n41#3,12:439\n*S KotlinDebug\n*F\n+ 1 AppNavigationPreferencesActivity.kt\ncom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity\n*L\n297#1:427,12\n309#1:439,12\n*E\n"})
/* loaded from: classes4.dex */
public final class AppNavigationPreferencesActivity extends BasePreferencesActivity {
    public static final Companion Companion = null;
    private CharSequence[] bottomNavigationModes;
    private final HashMap<Integer, ItemInfo> itemDetails;
    private int nextDividerId;
    private AltSeekbar predictiveBackSeekbar;
    private Drawable reorderIcon;
    private ActionBarMenuItem resetItem;
    private final ArrayList<Integer> stableDividerIds;
    private CharSequence[] tabletModeOptions;
    private CharSequence[] transitionAnimations;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000eB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rj\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u000f"}, d2 = {"Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$AppNavigationItem;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "DRAWER", "IMMERSIVE_ANIMATION", "BOTTOM_NAVIGATION_BAR_MODE", "PREDICTIVE_BACK_ANIMATION", "TRANSITION_ANIMATION", "TABLET_MODE", "id", _UrlKt.FRAGMENT_ENCODE_SET, "getId", "()I", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum AppNavigationItem extends Enum<AppNavigationItem> {
        private static final /* synthetic */ EnumEntries $ENTRIES = null;
        private static final /* synthetic */ AppNavigationItem[] $VALUES = null;
        public static final AppNavigationItem BOTTOM_NAVIGATION_BAR_MODE = null;
        public static final Companion Companion = null;
        public static final AppNavigationItem DRAWER = null;
        public static final AppNavigationItem IMMERSIVE_ANIMATION = null;
        public static final AppNavigationItem PREDICTIVE_BACK_ANIMATION = null;
        public static final AppNavigationItem TABLET_MODE = null;
        public static final AppNavigationItem TRANSITION_ANIMATION = null;

        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$AppNavigationItem$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "BASE_ID", _UrlKt.FRAGMENT_ENCODE_SET, "fromId", "Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$AppNavigationItem;", "id", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            public final AppNavigationItem fromId(int r1) {
                int r12 = r1 - 150;
                if (r12 >= 0) goto L5;
                return null;
            L5:
                if (r12 < AppNavigationItem.getEntries().size()) goto L8;
                return null;
            L8:
                return AppNavigationItem.getEntries().get(r12);
            }
        }

        private static final /* synthetic */ AppNavigationItem[] $values() {
            return new AppNavigationItem[]{DRAWER, IMMERSIVE_ANIMATION, BOTTOM_NAVIGATION_BAR_MODE, PREDICTIVE_BACK_ANIMATION, TRANSITION_ANIMATION, TABLET_MODE};
        }

        static {
            DRAWER = new AppNavigationItem("DRAWER", 0);
            IMMERSIVE_ANIMATION = new AppNavigationItem("IMMERSIVE_ANIMATION", 1);
            BOTTOM_NAVIGATION_BAR_MODE = new AppNavigationItem("BOTTOM_NAVIGATION_BAR_MODE", 2);
            PREDICTIVE_BACK_ANIMATION = new AppNavigationItem("PREDICTIVE_BACK_ANIMATION", 3);
            TRANSITION_ANIMATION = new AppNavigationItem("TRANSITION_ANIMATION", 4);
            TABLET_MODE = new AppNavigationItem("TABLET_MODE", 5);
            AppNavigationItem[] r0 = $values();
            $VALUES = r0;
            $ENTRIES = EnumEntriesKt.enumEntries(r0);
            Companion = new Companion(null);
        }

        AppNavigationItem(String r1, int r2) {
        }

        public static EnumEntries<AppNavigationItem> getEntries() {
            return $ENTRIES;
        }

        public static AppNavigationItem valueOf(String r1) {
            return (AppNavigationItem) Enum.valueOf(AppNavigationItem.class, r1);
        }

        public static AppNavigationItem[] values() {
            return (AppNavigationItem[]) $VALUES.clone();
        }

        public final int getId() {
            return ordinal() + 150;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "DIVIDER_BASE_ID", _UrlKt.FRAGMENT_ENCODE_SET, "PREDICTIVE_BACK_MAX_INTENSITY", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\t\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0014\u001a\u0004\b\u0015\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "name", _UrlKt.FRAGMENT_ENCODE_SET, "iconRes", "<init>", "(Ljava/lang/CharSequence;I)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/CharSequence;", "getName", "()Ljava/lang/CharSequence;", "I", "getIconRes", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ItemInfo {
        private final int iconRes;
        private final CharSequence name;

        public ItemInfo(CharSequence r1, int r2) {
            this.name = r1;
            this.iconRes = r2;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof ItemInfo) == true) goto L8;
            return false;
        L8:
            ItemInfo r52 = (ItemInfo) r5;
            if (Intrinsics.areEqual(this.name, r52.name) == true) goto L12;
            return false;
        L12:
            if (this.iconRes == r52.iconRes) goto L14;
            return false;
        L14:
            return true;
        }

        public final int getIconRes() {
            return this.iconRes;
        }

        public final CharSequence getName() {
            return this.name;
        }

        public int hashCode() {
            return (this.name.hashCode() * 31) + Integer.hashCode(this.iconRes);
        }

        public String toString() {
            return "ItemInfo(name=" + this.name + ", iconRes=" + this.iconRes + ")";
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[AppNavigationItem.values().length];
            r0[AppNavigationItem.DRAWER.ordinal()] = 1;     // Catch: NoSuchFieldError -> L10
        L15:
            r0[AppNavigationItem.IMMERSIVE_ANIMATION.ordinal()] = 2;     // Catch: NoSuchFieldError -> L11
        L23:
            r0[AppNavigationItem.BOTTOM_NAVIGATION_BAR_MODE.ordinal()] = 3;     // Catch: NoSuchFieldError -> L12
        L17:
            r0[AppNavigationItem.TRANSITION_ANIMATION.ordinal()] = 4;     // Catch: NoSuchFieldError -> L13
        L19:
            r0[AppNavigationItem.TABLET_MODE.ordinal()] = 5;     // Catch: NoSuchFieldError -> L14
        L8:
            $EnumSwitchMapping$0 = r0;
        }
    }

    public static /* synthetic */ void $r8$lambda$7__uhjIw6L6gMmRp354aKUrT6go(AppNavigationPreferencesActivity r0, int r1, ArrayList r2) {
        r0.updateConfigFromReorder(r1, r2);
    }

    /* renamed from: $r8$lambda$7b-srUJMVDg8LOhej8PgcZ3VlxY, reason: not valid java name */
    public static void m1653$r8$lambda$7bsrUJMVDg8LOhej8PgcZ3VlxY(boolean r0) {
        ExteraConfig.setImmersiveDrawerAnimation(r0);
    }

    public static boolean $r8$lambda$7iNv2_6uhH1q004nrPLZHfLpo1Q(Function1 r0, Object r1) {
        return ((Boolean) r0.invoke(r1)).booleanValue();
    }

    public static void $r8$lambda$9W76nbjQWdGOuv7NzhtLBfUwV48(AppNavigationPreferencesActivity r3, float r4) {
        boolean r0 = r3.isPredictiveBackOff(ExteraConfig.getPredictiveBackIntensity());
        boolean r1 = r3.isPredictiveBackOff(r4);
        ExteraConfig.setPredictiveBackIntensity(r4);
        AltSeekbar r2 = r3.predictiveBackSeekbar;
        if (r2 == null) goto L5;
        r2.updateHeader(r4);
    L5:
        if (r0 == r1) goto L8;
        r3.showRestartBulletin();
        return;
    }

    public static void $r8$lambda$FKdqUjxjgDeAzPXGJsdoCPgJ80I(AppNavigationPreferencesActivity r0, int r1) {
        ExteraConfig.setTabletMode(r1);
        r0.showRestartBulletin();
    }

    public static void $r8$lambda$OcnwmapLcYeDEa0ramhMi8X2sWc(int r2) {
        ExteraConfig.setTransitionAnimation(TransitionAnimation.getEntries().get(r2));
        if (ExteraConfig.getTransitionAnimation() == TransitionAnimation.DEFAULT) goto L6;
        SharedPreferences.Editor r22 = MessagesController.getGlobalMainSettings().edit();
        r22.putBoolean("view_animations", true);
        r22.apply();
        SharedConfig.setAnimationsEnabled(true);
        return;
    }

    public static boolean $r8$lambda$abRkaPj59RSDKtqDhmMPduco8WY(Integer r1) {
        int r0 = MainMenuItem.DIVIDER.getId();
        if (r1.intValue() != r0) goto L6;
        return true;
    L6:
        return false;
    }

    public static void $r8$lambda$wUuxrEg0ymhtYK31jN2UMNG3nBo(AppNavigationPreferencesActivity r3, int r4) {
        SharedPreferences.Editor r0 = ExteraConfig.getPreferences().edit();
        r0.putInt("bottomNavigationBarMode", r4);
        r0.apply();
        BottomNavigationBar.setMode(r4);
        ExteraConfig.ensureSettingsVisibility();
        r3.getNotificationCenter().postNotificationName(NotificationCenter.mainUserInfoChanged, new Object[0]);
        r3.refreshEditorList();
        updateResetButtonVisibility$default(r3, false, 1, null);
        r3.parentLayout.rebuildFragments(0);
    }

    public static void $r8$lambda$x0uY8g49vyT3wlPwQldxwo343Xc(AppNavigationPreferencesActivity r0, View r1) {
        r0.resetToDefault();
    }

    public static void $r8$lambda$zbZoGl8HqFPZHMddPi5kgoHhzqM(boolean r0) {
        ExteraConfig.setNavigationDrawer(r0);
    }

    static {
        Companion = new Companion(null);
    }

    public AppNavigationPreferencesActivity() {
        this.itemDetails = new HashMap();
        this.stableDividerIds = new ArrayList();
        this.nextDividerId = -2000;
    }

    private final void addMenuSection(ArrayList<UItem> r16, UniversalAdapter r17, String r18, ArrayList<Integer> r19, boolean r20) {
        r17.whiteSectionStart();
        r16.add(UItem.asHeader(r18));
        r17.reorderSectionStart();
        Iterator<Integer> r1 = r19.iterator();
        int r2 = 0;
    L4:
        if (r1.hasNext() == false) goto L29;
        int r3 = r1.next().intValue();
        if (r3 != MainMenuItem.PLUGINS.getId()) goto L10;
        if (PluginsController.Companion.isPluginEngineSupported() == false) goto L4;
    L10:
        MainMenuItem r4 = MainMenuItem.DIVIDER;
        if (r3 == r4.getId()) goto L13;
        ItemInfo r42 = this.itemDetails.get(Integer.valueOf(r3));
        if (r42 == null) goto L4;
        UItem r32 = createMenuItem(r3, r42);
        if (r32 == null) goto L4;
        r16.add(r32);
        goto L4
    L13:
        if (r20 == true) goto L15;
    L19:
        if (r20 == true) goto L23;
        UItem r33 = createMenuItem(r4.getId(), null);
        if (r33 == null) goto L23;
        r16.add(r33);
    L23:
        r2 = r2 + 1;
        goto L4
    L15:
        if (r2 >= this.stableDividerIds.size()) goto L19;
        UItem r34 = createMenuItem(this.stableDividerIds.get(r2).intValue(), null);
        if (r34 == null) goto L23;
        r16.add(r34);
        goto L23
    L29:
        r17.reorderSectionEnd();
        if (r20 == false) goto L32;
        r16.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, -200, LocaleController.getString(R.string.MainMenuAddDivider), R.drawable.msg_add, null, null, null, false, null, false, true, null, 1464, null));
    L32:
        r17.whiteSectionEnd();
    }

    private final UItem createMenuItem(int r16, ItemInfo r17) {
        if (r16 > (-2000)) goto L5;
    L10:
        UItem r1 = BasePreferencesActivity.uButton$TMessagesProj$default(this, r16, LocaleController.getString(R.string.MainMenuDivider), R.drawable.msg_block, null, null, null, false, null, false, false, null, 1976, null);
    L11:
        if (r1 == null) goto L14;
        r1.object2 = this.reorderIcon;
        return r1;
    L14:
        return null;
    L5:
        if (r16 == MainMenuItem.DIVIDER.getId()) goto L10;
        if (r17 == null) goto L9;
        r1 = BasePreferencesActivity.uButton$TMessagesProj$default(this, r16, r17.getName(), r17.getIconRes(), null, null, null, false, null, false, false, null, 1976, null);
        goto L11
    L9:
        r1 = null;
        goto L11
    }

    private final void initItemDetails() {
        this.itemDetails.put(Integer.valueOf(MainMenuItem.PROFILE.getId()), new ItemInfo(LocaleController.getString(R.string.MyProfile), R.drawable.left_status_profile));
        this.itemDetails.put(Integer.valueOf(MainMenuItem.ARCHIVE.getId()), new ItemInfo(LocaleController.getString(R.string.ArchivedChats), R.drawable.msg_archive));
        this.itemDetails.put(Integer.valueOf(MainMenuItem.BOTS.getId()), new ItemInfo(LocaleController.getString(R.string.FilterBots), R.drawable.msg_bot));
        this.itemDetails.put(Integer.valueOf(MainMenuItem.NEW_GROUP.getId()), new ItemInfo(LocaleController.getString(R.string.NewGroup), R.drawable.msg_groups));
        this.itemDetails.put(Integer.valueOf(MainMenuItem.CONTACTS.getId()), new ItemInfo(LocaleController.getString(R.string.Contacts), R.drawable.msg_contacts));
        this.itemDetails.put(Integer.valueOf(MainMenuItem.NEW_CHANNEL.getId()), new ItemInfo(LocaleController.getString(R.string.NewChannel), R.drawable.msg_channel));
        this.itemDetails.put(Integer.valueOf(MainMenuItem.CALLS.getId()), new ItemInfo(LocaleController.getString(R.string.Calls), R.drawable.msg_calls));
        this.itemDetails.put(Integer.valueOf(MainMenuItem.SAVED.getId()), new ItemInfo(LocaleController.getString(R.string.SavedMessages), R.drawable.msg_saved));
        this.itemDetails.put(Integer.valueOf(MainMenuItem.FEED.getId()), new ItemInfo(LocaleController.getString(R.string.Feed), R.drawable.ic_feed));
        this.itemDetails.put(Integer.valueOf(MainMenuItem.SETTINGS.getId()), new ItemInfo(LocaleController.getString(R.string.Settings), R.drawable.msg_settings_old));
        this.itemDetails.put(Integer.valueOf(MainMenuItem.PLUGINS.getId()), new ItemInfo(LocaleController.getString(R.string.Plugins), R.drawable.msg_plugins));
        this.itemDetails.put(Integer.valueOf(MainMenuItem.BROWSER.getId()), new ItemInfo(LocaleController.getString(R.string.BrowserSettingsTitle), R.drawable.msg2_language));
        this.itemDetails.put(Integer.valueOf(MainMenuItem.QR.getId()), new ItemInfo(LocaleController.getString(R.string.AuthAnotherClient), R.drawable.msg_qrcode));
        ExteraConfig.getMainMenuHiddenItems().removeIf(new AppNavigationPreferencesActivity$$ExternalSyntheticLambda1(new AppNavigationPreferencesActivity$$ExternalSyntheticLambda0()));
        this.stableDividerIds.clear();
        this.nextDividerId = -2000;
        Iterator<Integer> r0 = ExteraConfig.getMainMenuLayout().iterator();
    L4:
        if (r0.hasNext() == false) goto L8;
        if (r0.next().intValue() != MainMenuItem.DIVIDER.getId()) goto L4;
        ArrayList<Integer> r1 = this.stableDividerIds;
        int r2 = this.nextDividerId;
        this.nextDividerId = r2 - 1;
        r1.add(Integer.valueOf(r2));
        goto L4
    }

    private final boolean isPredictiveBackOff(float r1) {
        if ((MathKt.roundToInt(r1 * 10.0f) / 10.0f) > 0.0f) goto L6;
        return true;
    L6:
        return false;
    }

    private final UniversalRecyclerView refreshEditorList() {
        UniversalRecyclerView r2 = getListView$TMessagesProj();
        r2.hideSelector(false);
        r2.cancelClickRunnables(false);
        r2.adapter.update(true);
        return r2;
    }

    private final void resetToDefault() {
        ExteraConfig.getMainMenuLayout().clear();
        ExteraConfig.getMainMenuLayout().addAll(ExteraConfig.getDefaultMainMenuLayout());
        ExteraConfig.getMainMenuHiddenItems().clear();
        Iterator<MainMenuItem> r0 = MainMenuItem.getEntries().iterator();
    L4:
        if (r0.hasNext() == false) goto L15;
        MainMenuItem r1 = r0.next();
        if (r1 == MainMenuItem.DIVIDER) goto L4;
        if (ExteraConfig.getMainMenuLayout().contains(Integer.valueOf(r1.getId())) == true) goto L4;
        if (r1 != MainMenuItem.PLUGINS) goto L14;
        if (PluginsController.Companion.isPluginEngineSupported() == false) goto L4;
    L14:
        ExteraConfig.getMainMenuHiddenItems().add(Integer.valueOf(r1.getId()));
        goto L4
    L15:
        this.stableDividerIds.clear();
        this.nextDividerId = -2000;
        Iterator<Integer> r02 = ExteraConfig.getMainMenuLayout().iterator();
    L17:
        if (r02.hasNext() == false) goto L21;
        if (r02.next().intValue() != MainMenuItem.DIVIDER.getId()) goto L17;
        ArrayList<Integer> r12 = this.stableDividerIds;
        int r2 = this.nextDividerId;
        this.nextDividerId = r2 - 1;
        r12.add(Integer.valueOf(r2));
        goto L17
    L21:
        ExteraConfig.saveMainMenuLayout();
        getNotificationCenter().postNotificationName(NotificationCenter.mainUserInfoChanged, new Object[0]);
        refreshEditorList();
        updateResetButtonVisibility$default(this, false, 1, null);
    }

    private final void saveAndNotify() {
        ExteraConfig.saveMainMenuLayout();
        getNotificationCenter().postNotificationName(NotificationCenter.mainUserInfoChanged, new Object[0]);
        refreshEditorList();
        updateResetButtonVisibility$default(this, false, 1, null);
    }

    private final void updateConfigFromReorder(int r7, ArrayList<UItem> r8) {
        ArrayList r0 = new ArrayList();
        ArrayList r1 = new ArrayList();
        Iterator<UItem> r82 = r8.iterator();
    L4:
        if (r82.hasNext() == false) goto L17;
        UItem r2 = r82.next();
        int r3 = r2.id;
        if (r3 <= (-2000)) goto L11;
        if (r3 == MainMenuItem.DIVIDER.getId()) goto L11;
        r0.add(Integer.valueOf(r2.id));
    L11:
        if (r7 != 0) goto L4;
        r0.add(Integer.valueOf(MainMenuItem.DIVIDER.getId()));
        int r22 = r2.id;
        if (r22 <= (-2000)) goto L16;
        r22 = this.nextDividerId;
        this.nextDividerId = r22 - 1;
    L16:
        r1.add(Integer.valueOf(r22));
        goto L4
    L17:
        if (r7 != 0) goto L19;
        this.stableDividerIds.clear();
        this.stableDividerIds.addAll(r1);
        if (BottomNavigationBar.hidden() == false) goto L30;
        int r72 = MainMenuItem.SETTINGS.getId();
        if (r0.contains(Integer.valueOf(r72)) == true) goto L30;
        r0.add(Integer.valueOf(r72));
    L30:
        ExteraConfig.getMainMenuLayout().clear();
        ExteraConfig.getMainMenuLayout().addAll(r0);
    L31:
        saveAndNotify();
        return;
    L19:
        if (r7 != 1) goto L31;
        if (BottomNavigationBar.hidden() == false) goto L24;
        r0.remove(Integer.valueOf(MainMenuItem.SETTINGS.getId()));
    L24:
        ExteraConfig.getMainMenuHiddenItems().clear();
        ExteraConfig.getMainMenuHiddenItems().addAll(r0);
        goto L31
    }

    private final void updateResetButtonVisibility(boolean r3) {
        if (this.resetItem != null) goto L5;
        return;
    L5:
        AndroidUtilities.updateViewVisibilityAnimated(this.resetItem, !Intrinsics.areEqual(ExteraConfig.getMainMenuLayout(), ExteraConfig.getDefaultMainMenuLayout()), 0.5f, r3);
    }

    public static /* synthetic */ void updateResetButtonVisibility$default(AppNavigationPreferencesActivity r0, boolean r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L5;
        r1 = true;
    L5:
        r0.updateResetButtonVisibility(r1);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity, org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r10) {
        View r0 = super.createView(r10);
        if (Build.VERSION.SDK_INT < 34) goto L9;
        AppNavigationPreferencesActivity$createView$seekbar$1 r3 = new AppNavigationPreferencesActivity$createView$seekbar$1(r10, new AppNavigationPreferencesActivity$$ExternalSyntheticLambda2(this), LocaleController.getString(R.string.PredictiveBackIntensity), LocaleController.getString(R.string.BlurOff), LocaleController.getString(R.string.PredictiveBackMax));
        this.predictiveBackSeekbar = r3;
        float r102 = RangesKt.coerceAtMost(ExteraConfig.getPredictiveBackIntensity(), 2.0f);
        if (r102 == ExteraConfig.getPredictiveBackIntensity()) goto L8;
        ExteraConfig.setPredictiveBackIntensity(r102);
    L8:
        r3.setProgress(r102);
    L9:
        ActionBarMenuItem r103 = this.actionBar.createMenu().addItem(0, R.drawable.msg_reset);
        r103.setContentDescription(LocaleController.getString(R.string.Reset));
        r103.setOnClickListener(new AppNavigationPreferencesActivity$$ExternalSyntheticLambda3(this));
        this.resetItem = r103;
        updateResetButtonVisibility(false);
        getListView$TMessagesProj().allowReorder(true);
        getListView$TMessagesProj().listenReorder(new AppNavigationPreferencesActivity$$ExternalSyntheticLambda4(this));
        return r0;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void fillItems(ArrayList<UItem> r18, UniversalAdapter r19) {
        if (Build.VERSION.SDK_INT >= 34) goto L5;
    L8:
        boolean r1 = false;
    L7:
        boolean r15 = r1;
        Drawable r12 = this.reorderIcon;
        if (r12 != null) goto L12;
        r12 = ContextCompat.getDrawable(getContext(), R.drawable.list_reorder);
    L12:
        this.reorderIcon = r12;
        r18.add(UItem.asHeader(LocaleController.getString(R.string.General)));
        int r13 = AppNavigationItem.TABLET_MODE.getId();
        String r2 = LocaleController.getString(R.string.TabletMode);
        CharSequence[] r3 = this.tabletModeOptions;
        if (r3 != null) goto L15;
        r3 = null;
    L15:
        r18.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, r13, r2, 0, r3[ExteraConfig.getTabletMode()], null, null, false, "tabletMode", false, false, null, 1908, null));
        int r14 = AppNavigationItem.BOTTOM_NAVIGATION_BAR_MODE.getId();
        String r22 = LocaleController.getString(R.string.BottomNavigationBarMode);
        CharSequence[] r32 = this.bottomNavigationModes;
        if (r32 != null) goto L18;
        r32 = null;
    L18:
        r18.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, r14, r22, 0, r32[BottomNavigationBar.getMode()], null, null, false, "bottomNavigationBarMode", false, false, null, 1908, null));
        int r16 = AppNavigationItem.TRANSITION_ANIMATION.getId();
        String r23 = LocaleController.getString(R.string.TransitionAnimation);
        CharSequence[] r33 = this.transitionAnimations;
        if (r33 != null) goto L21;
        r33 = null;
    L21:
        r18.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, r16, r23, 0, r33[ExteraConfig.getTransitionAnimation().ordinal()], null, null, false, "transitionAnimation", false, false, null, 1908, null));
        r18.add(UItem.asShadow(LocaleController.getString(R.string.TransitionAnimationInfo)));
        ExtensionsKt.addIf(r18, r15, UItem.asCustom(AppNavigationItem.PREDICTIVE_BACK_ANIMATION.getId(), this.predictiveBackSeekbar).setLinkAlias("predictiveBackAnimation", this));
        ExtensionsKt.addIf(r18, r15, UItem.asShadow(LocaleController.getString(R.string.PredictiveBackInfo)));
        r18.add(UItem.asHeader(LocaleController.getString(R.string.AppNavigation)));
        r18.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, AppNavigationItem.DRAWER.getId(), LocaleController.getString(R.string.NavigationDrawer), ExteraConfig.getNavigationDrawer(), null, 0, false, "navigationDrawer", false, 0, 440, null));
        ExtensionsKt.addIf(r18, ExteraConfig.getNavigationDrawer(), BasePreferencesActivity.uCheck$TMessagesProj$default(this, AppNavigationItem.IMMERSIVE_ANIMATION.getId(), LocaleController.getString(R.string.NavigationDrawerImmersiveAnimation), ExteraConfig.getImmersiveDrawerAnimation(), null, 0, false, "immersiveDrawerAnimation", false, 0, 440, null));
        r18.add(UItem.asShadow(LocaleController.getString(R.string.NavigationDrawerInfo)));
        addMenuSection(r18, r19, LocaleController.getString(R.string.MainMenuItems), ExteraConfig.getMainMenuLayout(), true);
        r18.add(UItem.asShadow(LocaleController.getString(R.string.MainMenuItemsInfo)));
        if (ExteraConfig.getMainMenuHiddenItems().isEmpty() == true) goto L25;
        addMenuSection(r18, r19, LocaleController.getString(R.string.MainMenuHiddenItems), ExteraConfig.getMainMenuHiddenItems(), false);
        r18.add(UItem.asShadow(null));
        return;
    L25:
        return;
    L5:
        if (this.predictiveBackSeekbar == null) goto L8;
        r1 = true;
        goto L7
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public String getTitle() {
        return LocaleController.getString(R.string.AppNavigation);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void initializeOptionStrings() {
        initItemDetails();
        this.tabletModeOptions = new CharSequence[]{LocaleController.getString(R.string.DistanceUnitsAutomatic), LocaleController.getString(R.string.PasswordOn), LocaleController.getString(R.string.PasswordOff)};
        this.bottomNavigationModes = new CharSequence[]{LocaleController.getString(R.string.BottomNavigationModeShow), LocaleController.getString(R.string.BottomNavigationModeHide), LocaleController.getString(R.string.BottomNavigationModeFloating)};
        this.transitionAnimations = new CharSequence[]{LocaleController.getString(R.string.Default), LocaleController.getString(R.string.TransitionAnimationAosp), LocaleController.getString(R.string.TransitionAnimationSpring)};
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void onClick(UItem r7, View r8, int r9, float r10, float r11) {
        int r82 = r7.id;
        AppNavigationItem r92 = AppNavigationItem.Companion.fromId(r82);
        int r102 = 0;
        if (r92 == null) goto L39;
        int r83 = WhenMappings.$EnumSwitchMapping$0[r92.ordinal()];
        if (r83 != 1) goto L7;
        toggleBooleanSettingAndRefresh$TMessagesProj(r7, new AppNavigationPreferencesActivity$$ExternalSyntheticLambda5());
        getNotificationCenter().postNotificationName(NotificationCenter.mainUserInfoChanged, new Object[0]);
        UniversalAdapter r72 = getListView$TMessagesProj().adapter;
        if (r72 == null) goto L36;
        r72.update(true);
    L36:
        this.parentLayout.rebuildFragments(0);
        return;
    L7:
        if (r83 != 2) goto L9;
        toggleBooleanSettingAndRefresh$TMessagesProj(r7, new AppNavigationPreferencesActivity$$ExternalSyntheticLambda6());
        return;
    L9:
        if (r83 != 3) goto L11;
        CharSequence[] r84 = this.bottomNavigationModes;
        if (r84 != null) goto L29;
        r84 = null;
    L29:
        showListDialog(r7, r84, LocaleController.getString(R.string.BottomNavigationBarMode), BottomNavigationBar.getMode(), new AppNavigationPreferencesActivity$$ExternalSyntheticLambda7(this));
        return;
    L11:
        if (r83 != 4) goto L13;
        CharSequence[] r85 = this.transitionAnimations;
        if (r85 != null) goto L24;
        r85 = null;
    L24:
        showListDialog(r7, r85, LocaleController.getString(R.string.TransitionAnimation), ExteraConfig.getTransitionAnimation().ordinal(), new AppNavigationPreferencesActivity$$ExternalSyntheticLambda8());
        return;
    L13:
        if (r83 != 5) goto L92;
        CharSequence[] r86 = this.tabletModeOptions;
        if (r86 != null) goto L18;
        CharSequence[] r2 = null;
    L19:
        showListDialog(r7, r2, LocaleController.getString(R.string.TabletMode), ExteraConfig.getTabletMode(), new AppNavigationPreferencesActivity$$ExternalSyntheticLambda9(this));
        return;
    L18:
        r2 = r86;
        goto L19
    L92:
        return;
    L39:
        if (r82 != (-200)) goto L43;
        ArrayList<Integer> r73 = this.stableDividerIds;
        int r87 = this.nextDividerId;
        this.nextDividerId = r87 - 1;
        r73.add(Integer.valueOf(r87));
        ExteraConfig.getMainMenuLayout().add(Integer.valueOf(MainMenuItem.DIVIDER.getId()));
        saveAndNotify();
        return;
    L43:
        if (r82 > (-2000)) goto L61;
        int r74 = this.stableDividerIds.indexOf(Integer.valueOf(r82));
        if (r74 == (-1)) goto L90;
        int r93 = ExteraConfig.getMainMenuLayout().size();
        int r112 = 0;
    L47:
        if (r102 >= r93) goto L57;
        Integer r0 = ExteraConfig.getMainMenuLayout().get(r102);
        int r1 = MainMenuItem.DIVIDER.getId();
        if (r0 == null) goto L56;
        if (r0.intValue() != r1) goto L56;
        if (r112 == r74) goto L58;
        r112 = r112 + 1;
    L58:
        if (r102 == (-1)) goto L91;
        this.stableDividerIds.remove(r74);
        ExteraConfig.getMainMenuLayout().remove(r102);
        saveAndNotify();
        return;
    L91:
        return;
    L56:
        r102 = r102 + 1;
        goto L47
    L57:
        r102 = -1;
        goto L58
    L90:
        return;
    L61:
        MainMenuItem r75 = MainMenuItem.DIVIDER;
        if (r82 != r75.getId()) goto L66;
        ExteraConfig.getMainMenuHiddenItems().remove(Integer.valueOf(r82));
        saveAndNotify();
        return;
    L66:
        if (BottomNavigationBar.hidden() == false) goto L74;
        if (r82 != MainMenuItem.SETTINGS.getId()) goto L74;
        if (ExteraConfig.getMainMenuLayout().contains(Integer.valueOf(r82)) == false) goto L74;
        BulletinFactory.of(this).createErrorBulletin(LocaleController.getString(R.string.MainMenuRemoveSettingsInfo)).show();
        return;
    L74:
        if (ExteraConfig.getMainMenuLayout().contains(Integer.valueOf(r82)) == false) goto L81;
        ExteraConfig.getMainMenuLayout().remove(Integer.valueOf(r82));
        if (r82 != r75.getId()) goto L78;
    L83:
        saveAndNotify();
        return;
    L78:
        if (ExteraConfig.getMainMenuHiddenItems().contains(Integer.valueOf(r82)) == true) goto L83;
        ExteraConfig.getMainMenuHiddenItems().add(0, Integer.valueOf(r82));
        goto L83
    L81:
        if (ExteraConfig.getMainMenuHiddenItems().contains(Integer.valueOf(r82)) == false) goto L83;
        ExteraConfig.getMainMenuHiddenItems().remove(Integer.valueOf(r82));
        ExteraConfig.getMainMenuLayout().add(Integer.valueOf(r82));
        goto L83
    }
}

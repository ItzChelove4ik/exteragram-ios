package com.exteragram.messenger.preferences;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.api.db.DatabaseHelper;
import com.exteragram.messenger.api.dto.BoostySubscriberDTO;
import com.exteragram.messenger.backup.PreferencesUtils;
import com.exteragram.messenger.badges.BadgesController;
import com.exteragram.messenger.components.BoostyBottomSheet;
import com.exteragram.messenger.components.SupporterBottomSheet;
import com.exteragram.messenger.export.ui.ExportActivity;
import com.exteragram.messenger.plugins.Plugin;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.plugins.PluginsController;
import com.exteragram.messenger.utils.ExtensionsKt;
import com.exteragram.messenger.utils.network.RemoteUtils;
import com.exteragram.messenger.utils.system.VibratorUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LinkifyPort;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0010\u0018\u0000 22\u00020\u0001:\u00042345B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0014J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u001e\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0014J0\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001fH\u0014J0\u0010!\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001fH\u0014JJ\u0010\"\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\b2\u0006\u0010#\u001a\u00020\u00112\u000e\u0010$\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000b0&2\u0006\u0010'\u001a\u00020\u001dH\u0002J\u0012\u0010(\u001a\u0004\u0018\u00010\u00062\u0006\u0010)\u001a\u00020\u001dH\u0002J\b\u0010*\u001a\u00020\u0013H\u0002J\u000f\u0010+\u001a\u0004\u0018\u00010\u0013H\u0002¢\u0006\u0002\u0010,J\b\u0010-\u001a\u00020\u0013H\u0002J\b\u0010.\u001a\u00020\u0013H\u0002J\u0010\u0010/\u001a\u00020\u00132\u0006\u00100\u001a\u00020\u0006H\u0002J\u0018\u00101\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\rH\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00066"}, d2 = {"Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;", "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;", "<init>", "()V", "donates", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Donate;", "subscribers", "Ljava/util/ArrayList;", "Lcom/exteragram/messenger/api/dto/BoostySubscriberDTO;", "defaultDonateIcon", "Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;", "createView", "Landroid/view/View;", "context", "Landroid/content/Context;", "getTitle", _UrlKt.FRAGMENT_ENCODE_SET, "fillItems", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.ITEMS, "Lorg/telegram/ui/Components/UItem;", "adapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "onLongClick", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.ITEM, PluginsConstants.Settings.VIEW, "position", _UrlKt.FRAGMENT_ENCODE_SET, "x", _UrlKt.FRAGMENT_ENCODE_SET, "y", "onClick", "addDonateSection", "headerTitle", "donateList", "donateIcons", _UrlKt.FRAGMENT_ENCODE_SET, "idOffset", "getDonate", "id", "handleCrashlyticsClick", "handleAnalyticsClick", "()Lkotlin/Unit;", "handleResetSettingsClick", "handleDeleteAccountClick", "handleDonateClick", "donate", "handleDonateLongClick", "Companion", "OtherItem", "IconInfo", "Donate", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nOtherPreferencesActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OtherPreferencesActivity.kt\ncom/exteragram/messenger/preferences/OtherPreferencesActivity\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Uri.kt\nandroidx/core/net/UriKt\n*L\n1#1,323:1\n1834#2,4:324\n1924#2,2:329\n296#2,2:331\n1926#2:333\n1#3:328\n29#4:334\n*S KotlinDebug\n*F\n+ 1 OtherPreferencesActivity.kt\ncom/exteragram/messenger/preferences/OtherPreferencesActivity\n*L\n98#1:324,4\n168#1:329,2\n171#1:331,2\n168#1:333\n288#1:334\n*E\n"})
/* loaded from: classes4.dex */
public final class OtherPreferencesActivity extends BasePreferencesActivity {
    public static final Companion Companion = null;
    private final IconInfo defaultDonateIcon;
    private List<Donate> donates;
    private final ArrayList<BoostySubscriberDTO> subscribers;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "getDonates", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Donate;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final List<Donate> getDonates() {
            Set<String> r8 = RemoteUtils.getStringSetConfigValue("donates", SetsKt.emptySet());
            ArrayList r0 = new ArrayList();
            Iterator<String> r82 = r8.iterator();
        L4:
            if (r82.hasNext() == false) goto L8;
            List r1 = StringsKt.split$default(r82.next(), new String[]{"#"}, false, 0, 6, null);
            if (r1.size() != 2) goto L4;
            r0.add(new Donate((String) r1.get(0), (String) r1.get(1)));
            goto L4
        L8:
            return r0;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\u000e\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0010\u001a\u0004\b\u0011\u0010\bR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0010\u001a\u0004\b\u0012\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Donate;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "name", "details", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getName", "getDetails", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Donate {
        private final String details;
        private final String name;

        public Donate(String r1, String r2) {
            this.name = r1;
            this.details = r2;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof Donate) == true) goto L8;
            return false;
        L8:
            Donate r52 = (Donate) r5;
            if (Intrinsics.areEqual(this.name, r52.name) == true) goto L12;
            return false;
        L12:
            if (Intrinsics.areEqual(this.details, r52.details) == true) goto L14;
            return false;
        L14:
            return true;
        }

        public final String getDetails() {
            return this.details;
        }

        public final String getName() {
            return this.name;
        }

        public int hashCode() {
            return (this.name.hashCode() * 31) + this.details.hashCode();
        }

        public String toString() {
            return "Donate(name=" + this.name + ", details=" + this.details + ")";
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\b\u001a\u00020\u0007HÖ\u0001¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\u000e\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0010\u001a\u0004\b\u0011\u0010\u000bR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0010\u001a\u0004\b\u0012\u0010\u000b¨\u0006\u0013"}, d2 = {"Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "iconResId", "iconColor", "<init>", "(II)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "I", "getIconResId", "getIconColor", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class IconInfo {
        private final int iconColor;
        private final int iconResId;

        public IconInfo(int r1, int r2) {
            this.iconResId = r1;
            this.iconColor = r2;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof IconInfo) == true) goto L8;
            return false;
        L8:
            IconInfo r52 = (IconInfo) r5;
            if (this.iconResId == r52.iconResId) goto L12;
            return false;
        L12:
            if (this.iconColor == r52.iconColor) goto L14;
            return false;
        L14:
            return true;
        }

        public final int getIconColor() {
            return this.iconColor;
        }

        public final int getIconResId() {
            return this.iconResId;
        }

        public int hashCode() {
            return (Integer.hashCode(this.iconResId) * 31) + Integer.hashCode(this.iconColor);
        }

        public String toString() {
            return "IconInfo(iconResId=" + this.iconResId + ", iconColor=" + this.iconColor + ")";
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000ej\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000f"}, d2 = {"Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "CRASHLYTICS", "ANALYTICS", "EXPORT_SETTINGS", "EXPORT_DATA", "RESET_SETTINGS", "DELETE_ACCOUNT", "DONATE", "id", _UrlKt.FRAGMENT_ENCODE_SET, "getId", "()I", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum OtherItem extends Enum<OtherItem> {
        private static final /* synthetic */ EnumEntries $ENTRIES = null;
        private static final /* synthetic */ OtherItem[] $VALUES = null;
        public static final OtherItem ANALYTICS = null;
        public static final OtherItem CRASHLYTICS = null;
        public static final OtherItem DELETE_ACCOUNT = null;
        public static final OtherItem DONATE = null;
        public static final OtherItem EXPORT_DATA = null;
        public static final OtherItem EXPORT_SETTINGS = null;
        public static final OtherItem RESET_SETTINGS = null;
        private final int id;

        private static final /* synthetic */ OtherItem[] $values() {
            return new OtherItem[]{CRASHLYTICS, ANALYTICS, EXPORT_SETTINGS, EXPORT_DATA, RESET_SETTINGS, DELETE_ACCOUNT, DONATE};
        }

        static {
            CRASHLYTICS = new OtherItem("CRASHLYTICS", 0);
            ANALYTICS = new OtherItem("ANALYTICS", 1);
            EXPORT_SETTINGS = new OtherItem("EXPORT_SETTINGS", 2);
            EXPORT_DATA = new OtherItem("EXPORT_DATA", 3);
            RESET_SETTINGS = new OtherItem("RESET_SETTINGS", 4);
            DELETE_ACCOUNT = new OtherItem("DELETE_ACCOUNT", 5);
            DONATE = new OtherItem("DONATE", 6);
            OtherItem[] r0 = $values();
            $VALUES = r0;
            $ENTRIES = EnumEntriesKt.enumEntries(r0);
        }

        OtherItem(String r1, int r2) {
            this.id = ordinal() + 1;
        }

        public static EnumEntries<OtherItem> getEntries() {
            return $ENTRIES;
        }

        public static OtherItem valueOf(String r1) {
            return (OtherItem) Enum.valueOf(OtherItem.class, r1);
        }

        public static OtherItem[] values() {
            return (OtherItem[]) $VALUES.clone();
        }

        public final int getId() {
            return this.id;
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[OtherItem.values().length];
            r0[OtherItem.CRASHLYTICS.ordinal()] = 1;     // Catch: NoSuchFieldError -> L11
        L19:
            r0[OtherItem.ANALYTICS.ordinal()] = 2;     // Catch: NoSuchFieldError -> L12
        L27:
            r0[OtherItem.RESET_SETTINGS.ordinal()] = 3;     // Catch: NoSuchFieldError -> L13
        L21:
            r0[OtherItem.DELETE_ACCOUNT.ordinal()] = 4;     // Catch: NoSuchFieldError -> L14
        L23:
            r0[OtherItem.EXPORT_SETTINGS.ordinal()] = 5;     // Catch: NoSuchFieldError -> L15
        L17:
            r0[OtherItem.EXPORT_DATA.ordinal()] = 6;     // Catch: NoSuchFieldError -> L16
        L9:
            $EnumSwitchMapping$0 = r0;
        }
    }

    @Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0014¨\u0006\u0004"}, d2 = {"com/exteragram/messenger/preferences/OtherPreferencesActivity$handleDonateClick$1", "Lcom/exteragram/messenger/components/BoostyBottomSheet;", "onButtonClick", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.preferences.OtherPreferencesActivity$handleDonateClick$1, reason: invalid class name */
    public static final class AnonymousClass1 extends BoostyBottomSheet {
        final /* synthetic */ Donate $donate;
        final /* synthetic */ OtherPreferencesActivity this$0;

        public AnonymousClass1(OtherPreferencesActivity r1, Donate r2, Activity r3, ArrayList<BoostySubscriberDTO> r4) {
            this.this$0 = r1;
            this.$donate = r2;
            super(r3, r4);
        }

        @Override // com.exteragram.messenger.components.BoostyBottomSheet
        public void onButtonClick() {
            Browser.openUrl(this.this$0.getParentActivity(), this.$donate.getDetails());
        }
    }

    public static void $r8$lambda$46eE77krG1ev0fsEgwsM5fDul2U(boolean r0) {
        ExteraConfig.setUseGoogleCrashlytics(r0);
    }

    public static void $r8$lambda$DShbhmj_8_W37UQxRBukG8QD66E(OtherPreferencesActivity r2, AlertDialog r3, int r4) {
        PreferencesUtils.clearPreferences();
        r2.parentLayout.rebuildFragments(0);
        r2.getNotificationCenter().postNotificationName(NotificationCenter.mainUserInfoChanged, new Object[0]);
        r2.getNotificationCenter().postNotificationName(NotificationCenter.dialogFiltersUpdated, new Object[0]);
        LocaleController.getInstance().recreateFormatters();
        Theme.reloadAllResources(r2.getParentActivity());
        BulletinFactory.of(r2).createErrorBulletin(LocaleController.getString(R.string.ResetPreferences), r2.resourceProvider).show();
    }

    public static void $r8$lambda$FdxV3ix9VS2QUhaOOP76BJFpvuI(boolean r0) {
        ExteraConfig.setUseGoogleAnalytics(r0);
    }

    /* renamed from: $r8$lambda$J0iYmoKZXcoJ1hvSxBth-l3w_CE, reason: not valid java name */
    public static void m1649$r8$lambda$J0iYmoKZXcoJ1hvSxBthl3w_CE(OtherPreferencesActivity r1) {
        SupporterBottomSheet.showAlert(r1, null);
    }

    public static void $r8$lambda$dVNaH0qjGRIV502FTtIuADYa9nA(AlertDialog r1, DialogInterface r2) {
        View r12 = r1.getButton(-1);
        if ((r12 instanceof TextView) == false) goto L5;
        TextView r13 = (TextView) r12;
    L6:
        if (r13 != null) goto L8;
        return;
    L8:
        r13.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        r13.setEnabled(false);
        new OtherPreferencesActivity$handleDeleteAccountClick$1$1$1(r13, r13.getText()).start();
        return;
    L5:
        r13 = null;
        goto L6
    }

    public static void $r8$lambda$fcSW2o031d6H2Ez7_0b8lx4cBms(OtherPreferencesActivity r0, List r1) {
        r0.subscribers.addAll(r1);
    }

    public static void $r8$lambda$ilM5cqEH6Q8fGXs5ROPy5rBCyec(OtherPreferencesActivity r3, AlertDialog r4) {
        ConnectionsManager r0 = r3.getConnectionsManager();
        TL_account.deleteAccount r1 = new TL_account.deleteAccount();
        r1.reason = "ЭКСТЕРАГРАМ";
        r0.sendRequest(r1, new OtherPreferencesActivity$$ExternalSyntheticLambda8(r4, r3));
    }

    /* renamed from: $r8$lambda$vTi_wXRb_f1M9is-gtAXKE_w2do, reason: not valid java name */
    public static void m1650$r8$lambda$vTi_wXRb_f1M9isgtAXKE_w2do(OtherPreferencesActivity r1, AlertDialog r2, int r3) {
        AlertDialog r22 = new AlertDialog(r1.getParentActivity(), 3);
        r22.setCanCancel(false);
        r22.show();
        Utilities.globalQueue.postRunnable(new OtherPreferencesActivity$$ExternalSyntheticLambda7(r1, r22), 500);
    }

    public static void $r8$lambda$wafXI4sX6H1dACsVEwON_Y0b8JM(AlertDialog r1, TLObject r2, OtherPreferencesActivity r3, TLRPC.TL_error r4) {
        r1.dismiss();     // Catch: Exception -> L4
    L7:
        if ((r2 instanceof TLRPC.TL_boolTrue) == false) goto L9;
        r3.getMessagesController().performLogout(0);
        return;
    L9:
        if (r4 != null) goto L11;
    L12:
        String r12 = LocaleController.getString(R.string.ErrorOccurred);
        if (r4 == null) goto L17;
        String r42 = r4.text;
        if (r42 == null) goto L17;
        String r43 = "\n".concat(r42);
    L18:
        if (r43 != null) goto L20;
        r43 = _UrlKt.FRAGMENT_ENCODE_SET;
    L20:
        String r13 = r12 + r43;
        AlertDialog.Builder r44 = new AlertDialog.Builder(r3.getParentActivity());
        r44.setTitle(LocaleController.getString(R.string.AppName));
        r44.setMessage(r13);
        r44.setPositiveButton(LocaleController.getString(R.string.OK), null);
        r44.show();
        return;
    L17:
        r43 = null;
        goto L18
    L11:
        if (r4.code != (-1000)) goto L12;
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
        goto L7
    }

    public static void $r8$lambda$yWCQpFPXkqfATo6phc_BXBXCqMQ(AlertDialog r1, OtherPreferencesActivity r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new OtherPreferencesActivity$$ExternalSyntheticLambda9(r1, r3, r2, r4));
    }

    static {
        Companion = new Companion(null);
    }

    public OtherPreferencesActivity() {
        this.donates = CollectionsKt.emptyList();
        this.subscribers = new ArrayList();
        this.defaultDonateIcon = new IconInfo(R.drawable.msg_payment_card, 0);
    }

    private final void addDonateSection(ArrayList<UItem> r20, String r21, List<Donate> r22, Map<String, IconInfo> r23, int r24) {
        List<Donate> r1 = r22;
        if (r1 != null) goto L5;
        return;
    L5:
        if (r1.isEmpty() == true) goto L39;
        r20.add(UItem.asHeader(r21));
        Iterator<T> r15 = r22.iterator();
        boolean r12 = false;
        int r2 = 0;
    L9:
        if (r15.hasNext() == false) goto L30;
        Object r3 = r15.next();
        int r16 = r2 + 1;
        if (r2 >= 0) goto L13;
        CollectionsKt.throwIndexOverflow();
    L13:
        Donate r32 = (Donate) r3;
        String r4 = r32.getName().toLowerCase(Locale.ROOT);
        Iterator<T> r5 = r23.entrySet().iterator();
    L14:
        Object r7 = null;
        if (r5.hasNext() == false) goto L19;
        Object r6 = r5.next();
        if (StringsKt.contains$default(r4, (String) ((Map.Entry) r6).getKey(), r12, 2, null) == false) goto L14;
        r7 = r6;
    L19:
        Map.Entry r72 = (Map.Entry) r7;
        if (r72 == null) goto L24;
        IconInfo r42 = (IconInfo) r72.getValue();
        if (r42 == null) goto L24;
    L23:
        IconInfo r17 = r42;
        boolean r52 = r12;
        UItem r13 = BasePreferencesActivity.uButton$TMessagesProj$default(this, (OtherItem.DONATE.getId() + r24) + r2, r32.getName(), 0, null, null, null, true, null, false, false, null, 1980, null);
        if (r17.getIconColor() != 0) goto L28;
        r13.setIcon(r17.getIconResId());
    L29:
        r20.add(r13);
        r2 = r16;
        r12 = r52;
        goto L9
    L28:
        r13.setColorfulIcon(r17.getIconResId(), r17.getIconColor());
    L24:
        r42 = this.defaultDonateIcon;
        goto L23
    L30:
        r20.add(UItem.asShadow(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.GetBadgeInfo), new OtherPreferencesActivity$$ExternalSyntheticLambda1(this))));
        return;
    }

    private final Donate getDonate(int r2) {
        int r22 = r2 - OtherItem.DONATE.getId();
        if (r22 >= 0) goto L5;
        return null;
    L5:
        if (r22 < this.donates.size()) goto L7;
        return null;
    L7:
        return this.donates.get(r22);
    }

    private final Unit handleAnalyticsClick() {
        FirebaseAnalytics r1 = ApplicationLoader.getFirebaseAnalytics();
        if (r1 == null) goto L9;
        r1.setAnalyticsCollectionEnabled(ExteraConfig.getUseGoogleAnalytics());
        if (ExteraConfig.getUseGoogleAnalytics() == true) goto L8;
        r1.resetAnalyticsData();
    L8:
        return Unit.INSTANCE;
    L9:
        return null;
    }

    private final void handleCrashlyticsClick() {
        FirebaseCrashlytics r1 = ApplicationLoader.getFirebaseCrashlytics();
        if (r1 == null) goto L6;
        r1.setCrashlyticsCollectionEnabled(ExteraConfig.getUseGoogleCrashlytics());
        return;
    }

    private final void handleDeleteAccountClick() {
        AlertDialog.Builder r0 = new AlertDialog.Builder(getParentActivity());
        r0.setMessage(LocaleController.getString(R.string.TosDeclineDeleteAccount));
        r0.setTitle(LocaleController.getString(R.string.DeleteAccount));
        r0.setPositiveButton(LocaleController.getString(R.string.Deactivate), new OtherPreferencesActivity$$ExternalSyntheticLambda5(this));
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r02 = r0.create();
        r02.setOnShowListener(new OtherPreferencesActivity$$ExternalSyntheticLambda6(r02));
        showDialog(r02);
    }

    private final void handleDonateClick(Donate r6) {
        String r0 = r6.getName().toLowerCase(Locale.getDefault());
        if (StringsKt.contains$default(r0, "ton", false, 2, null) == false) goto L13;
        long r02 = UserConfig.getInstance(this.currentAccount).clientUserId;
        String r03 = "ton://transfer/" + r6.getDetails() + "?text=" + r02;
        if (Browser.isInternalUri(Uri.parse(r03), new boolean[1]) == true) goto L9;
        Browser.openUrl(getParentActivity(), r03);
        return;
    L9:
        if (AndroidUtilities.addToClipboard(r6.getDetails()) == false) goto L29;
        BulletinFactory.of(this).createCopyBulletin(LocaleController.getString(R.string.TextCopied)).show();
        return;
    L29:
        return;
    L13:
        if (StringsKt.contains$default(r0, "boosty", false, 2, null) == false) goto L22;
        if (this.subscribers.isEmpty() == true) goto L22;
        if (getParentActivity() == null) goto L31;
        showDialog(new AnonymousClass1(this, r6, getParentActivity(), this.subscribers));
        return;
    L31:
        return;
    L22:
        if (LinkifyPort.WEB_URL.matcher(r6.getDetails()).matches() == false) goto L26;
        Browser.openUrl(getParentActivity(), r6.getDetails());
        return;
    L26:
        if (AndroidUtilities.addToClipboard(r6.getDetails()) == false) goto L30;
        BulletinFactory.of(this).createCopyBulletin(LocaleController.getString(R.string.TextCopied)).show();
        return;
    }

    private final boolean handleDonateLongClick(UItem r1, View r2) {
        Donate r12 = getDonate(r1.id);
        if (r12 != null) goto L5;
    L7:
        r2.performHapticFeedback(VibratorUtils.getType(3), 1);
        return false;
    L5:
        if (AndroidUtilities.addToClipboard(r12.getDetails()) == false) goto L7;
        BulletinFactory.of(this).createCopyBulletin(LocaleController.getString(R.string.TextCopied)).show();
        goto L7
    }

    private final void handleResetSettingsClick() {
        AlertDialog.Builder r0 = new AlertDialog.Builder(getParentActivity());
        r0.setMessage(AndroidUtilities.replaceTags(LocaleController.getString(R.string.ResetPreferencesInfo)));
        r0.setTitle(LocaleController.getString(R.string.ResetSettings));
        TextView r2 = null;
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r0.setPositiveButton(LocaleController.getString(R.string.Reset), new OtherPreferencesActivity$$ExternalSyntheticLambda4(this));
        AlertDialog r02 = r0.create();
        showDialog(r02);
        View r4 = r02.getButton(-1);
        if ((r4 instanceof TextView) == false) goto L5;
        r2 = (TextView) r4;
    L5:
        if (r2 == null) goto L8;
        r2.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity, org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r2) {
        DatabaseHelper.getBoostySubscribers(new OtherPreferencesActivity$$ExternalSyntheticLambda0(this));
        return super.createView(r2);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void fillItems(ArrayList<UItem> r17, UniversalAdapter r18) {
        this.donates = Companion.getDonates();
        Collection<Plugin> r1 = PluginsController.Companion.getInstance().getPlugins().values();
        if (r1.isEmpty() == false) goto L5;
        int r15 = 0;
    L14:
        boolean r12 = Theme.isCurrentThemeDark();
        int r3 = R.drawable.mastercard_icon;
        if (r12 == false) goto L17;
        int r4 = -1;
    L18:
        Pair r2 = TuplesKt.to("mastercard", new IconInfo(r3, r4));
        int r42 = R.drawable.ton_icon;
        if (r12 == false) goto L21;
        int r5 = -14207411;
    L22:
        Pair r32 = TuplesKt.to("tonkeeper", new IconInfo(r42, r5));
        Pair r43 = TuplesKt.to("space", new IconInfo(R.drawable.ton_space_icon, -13587978));
        int r6 = R.drawable.boosty_icon;
        if (r12 == false) goto L25;
        int r13 = -1118482;
    L26:
        addDonateSection(r17, LocaleController.getString(R.string.Support), this.donates, MapsKt.mapOf(new Pair[]{r2, r32, r43, TuplesKt.to("boosty", new IconInfo(r6, r13))}), 0);
        r17.add(UItem.asHeader("Google"));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, OtherItem.CRASHLYTICS, "Crashlytics", ExteraConfig.getUseGoogleCrashlytics(), null, 0, false, "crashlytics", false, R.drawable.msg_report, 184, null));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, OtherItem.ANALYTICS, "Analytics", ExteraConfig.getUseGoogleAnalytics(), null, 0, false, "analytics", false, R.drawable.msg_data, 184, null));
        r17.add(UItem.asShadow(LocaleController.getString(R.string.AnalyticsInfo)));
        r17.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, OtherItem.EXPORT_SETTINGS, LocaleController.getString(R.string.ExportSettings), R.drawable.msg_settings, null, null, null, false, "exportSettings", false, false, null, 1912, null));
        if (BadgesController.isDeveloper$default(BadgesController.INSTANCE, null, 1, null) == false) goto L30;
        if (r15 > 1) goto L30;
        boolean r152 = true;
    L31:
        ExtensionsKt.addIf(r17, r152, BasePreferencesActivity.uButton$TMessagesProj$default(this, OtherItem.EXPORT_DATA, LocaleController.getString(R.string.ExportData), R.drawable.msg_archive, null, null, null, false, "exportData", false, false, null, 1912, null));
        r17.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, OtherItem.RESET_SETTINGS, LocaleController.getString(R.string.ResetSettings), R.drawable.msg_reset, null, null, null, false, "resetSettings", false, false, null, 1912, null));
        r17.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, OtherItem.DELETE_ACCOUNT, LocaleController.getString(R.string.DeleteAccount), R.drawable.msg_clearcache, null, null, null, false, "deleteAccount", true, false, null, 1656, null));
        r17.add(UItem.asShadow());
        return;
    L30:
        r152 = false;
        goto L31
    L25:
        r13 = -14406868;
        goto L26
    L21:
        r5 = -15727073;
        goto L22
    L17:
        r4 = -16777216;
        goto L18
    L5:
        Iterator<T> r14 = r1.iterator();
        int r22 = 0;
    L7:
        if (r14.hasNext() == false) goto L13;
        if (((Plugin) r14.next()).isEnabled() == false) goto L7;
        r22 = r22 + 1;
        if (r22 >= 0) goto L7;
        CollectionsKt.throwCountOverflow();
        goto L7
    L13:
        r15 = r22;
        goto L14
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public String getTitle() {
        return LocaleController.getString(R.string.LocalOther);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void onClick(UItem r1, View r2, int r3, float r4, float r5) {
        int r22 = r1.id;
        OtherItem r32 = OtherItem.DONATE;
        if (r22 >= r32.getId()) goto L5;
    L10:
        if (r22 > 0) goto L12;
        return;
    L12:
        if (r22 < r32.getId()) goto L15;
        return;
    L15:
        switch(WhenMappings.$EnumSwitchMapping$0[OtherItem.getEntries().get(r22 - 1).ordinal()]) {
            case 1: goto L27;
            case 2: goto L25;
            case 3: goto L23;
            case 4: goto L21;
            case 5: goto L19;
            case 6: goto L17;
            default: goto L32;
        };
    L17:
        presentFragment(new ExportActivity(null));
        return;
    L19:
        PreferencesUtils.getInstance().exportSettings(this);
        return;
    L21:
        handleDeleteAccountClick();
        return;
    L23:
        handleResetSettingsClick();
        return;
    L25:
        toggleBooleanSettingAndRefresh$TMessagesProj(r1, new OtherPreferencesActivity$$ExternalSyntheticLambda3());
        handleAnalyticsClick();
        return;
    L27:
        toggleBooleanSettingAndRefresh$TMessagesProj(r1, new OtherPreferencesActivity$$ExternalSyntheticLambda2());
        handleCrashlyticsClick();
        return;
    L32:
        return;
    L5:
        if (r22 >= (r32.getId() + this.donates.size())) goto L10;
        Donate r12 = getDonate(r22);
        if (r12 == null) goto L29;
        handleDonateClick(r12);
        return;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public boolean onLongClick(UItem r7, View r8, int r9, float r10, float r11) {
        int r0 = r7.id;
        OtherItem r1 = OtherItem.DONATE;
        if (r0 < r1.getId()) goto L9;
        if (r0 >= (r1.getId() + this.donates.size())) goto L9;
        return handleDonateLongClick(r7, r8);
    L9:
        return super.onLongClick(r7, r8, r9, r10, r11);
    }
}

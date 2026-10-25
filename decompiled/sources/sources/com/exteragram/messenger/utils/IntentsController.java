package com.exteragram.messenger.utils;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.backup.BackupBottomSheet;
import com.exteragram.messenger.backup.PreferencesUtils;
import com.exteragram.messenger.components.SupporterBottomSheet;
import com.exteragram.messenger.feed.ui.FeedActivity;
import com.exteragram.messenger.icons.IconManager;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.plugins.PluginsController;
import com.exteragram.messenger.preferences.MainPreferencesActivity;
import com.exteragram.messenger.preferences.OtherPreferencesActivity;
import com.exteragram.messenger.preferences.utils.SettingsRegistry;
import com.exteragram.messenger.utils.chats.ChatUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.EmojiPacksAlert;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.ProfileActivity;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\f\u0010\rR,\u0010\u0012\u001a\u001a\u0012\u0004\u0012\u00020\u000f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00110\u00100\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R,\u0010\u0014\u001a\u001a\u0012\u0004\u0012\u00020\u000f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00110\u00100\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0013R,\u0010\u0015\u001a\u001a\u0012\u0004\u0012\u00020\u000f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000b0\u00100\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcom/exteragram/messenger/utils/IntentsController;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroid/net/Uri;", "uri", "Ljava/io/File;", "getTempFileFromIntent", "(Landroid/net/Uri;)Ljava/io/File;", "Landroid/content/Intent;", "intent", _UrlKt.FRAGMENT_ENCODE_SET, "handleIntent", "(Landroid/content/Intent;)Z", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, "deeplinkCallbacks", "Ljava/util/Map;", "callbacks", "actionCallbacks", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nIntentsController.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IntentsController.kt\ncom/exteragram/messenger/utils/IntentsController\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,266:1\n1#2:267\n*E\n"})
/* loaded from: classes.dex */
public final class IntentsController {
    public static final IntentsController INSTANCE = null;
    private static final Map<String, Function1<Intent, Boolean>> actionCallbacks = null;
    private static final Map<String, Function1<Uri, Unit>> callbacks = null;
    private static final Map<String, Function1<Uri, Unit>> deeplinkCallbacks = null;

    public static Unit $r8$lambda$1uuFNPrfg21B6xFQ7L3LToGjAvI(Uri r3) {
        SettingsRegistry.getInstance().handleLink(r3.getQueryParameter("s"), r3.getQueryParameter("p"));
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$4nHeLUQ09Gyn3NZM8J_qAzCpDyA(Uri r0) {
        AndroidUtilities.runOnUIThread(new IntentsController$$ExternalSyntheticLambda13());
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$61dMYQ9T4xe_5cGqXKi4rijA67Q(Uri r5) {
        String r52 = r5.getQueryParameter("id");
        if (r52 == null) goto L10;
        Long r53 = StringsKt.toLongOrNull(r52);
        if (r53 == null) goto L10;
        long r0 = r53.longValue();
        BaseFragment r54 = LaunchActivity.getSafeLastFragment();
        if (r54 == null) goto L10;
        Bundle r2 = new Bundle();
        r2.putLong("chat_id", r0);
        ChatActivity r3 = new ChatActivity(r2);
        AlertDialog r22 = new AlertDialog(r54.getParentActivity(), 3);
        r22.setCanCancel(false);
        r22.show();
        ChatUtils.getInstance().searchChatById(r0, new IntentsController$$ExternalSyntheticLambda12(r22, r3));
    L10:
        return Unit.INSTANCE;
    }

    /* renamed from: $r8$lambda$9XjeTv5CAxJc0C67EC8-OKii4c0, reason: not valid java name */
    public static Unit m1761$r8$lambda$9XjeTv5CAxJc0C67EC8OKii4c0(Uri r0) {
        AndroidUtilities.runOnUIThread(new IntentsController$$ExternalSyntheticLambda14());
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$IW9cVW1_Ls85TWGd_6MsusR_AB8(Uri r0) {
        BaseFragment r02 = LaunchActivity.getSafeLastFragment();
        if (r02 == null) goto L6;
        SupporterBottomSheet.showAlert(r02);
    L6:
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$It8J4Jzm7OhL1Vqi8KopB5SXXqg(Uri r1) {
        BaseFragment r12 = LaunchActivity.getSafeLastFragment();
        if (r12 == null) goto L6;
        PreferencesUtils.getInstance().exportSettings(r12);
    L6:
        return Unit.INSTANCE;
    }

    public static Bulletin $r8$lambda$RzP8914H3_xKVP6V6dTDmqWFhDs(BulletinFactory r1) {
        return r1.createErrorBulletin(LocaleController.getString(R.string.ChatNotFound));
    }

    /* renamed from: $r8$lambda$Tb3Zh3f1N3Upmv_BzDOS_Bf-97k, reason: not valid java name */
    public static boolean m1762$r8$lambda$Tb3Zh3f1N3Upmv_BzDOS_Bf97k(Intent r6) {
        Uri r62 = r6.getData();
        boolean r0 = false;
        if (r62 == null) goto L33;
        String r1 = r62.getPath();
        Boolean r2 = null;
        if (r1 == null) goto L32;
        String r12 = StringsKt.substringAfterLast(r1, '.', _UrlKt.FRAGMENT_ENCODE_SET);
        if (r12 == null) goto L32;
        BaseFragment r3 = LaunchActivity.getSafeLastFragment();
        if (r3 == null) goto L32;
        int r22 = r12.hashCode();
        if (r22 == (-1289044077)) goto L27;
        if (r22 == (-985174221)) goto L23;
        if (r22 == 100029210) goto L18;
    L30:
        r2 = Boolean.valueOf(r0);
        goto L32
    L18:
        if (r12.equals("icons") == false) goto L30;
        IconManager.INSTANCE.handleIconPack(r3, INSTANCE.getTempFileFromIntent(r62).getAbsolutePath());
    L21:
        r0 = true;
        goto L30
    L23:
        if (r12.equals("plugin") == false) goto L30;
        PluginsController.Companion.getInstance().showInstallDialog(r3, INSTANCE.getTempFileFromIntent(r62).getAbsolutePath(), false);
        goto L21
    L27:
        if (r12.equals("extera") == false) goto L30;
        new BackupBottomSheet(r3, INSTANCE.getTempFileFromIntent(r62)).showIfPossible();
    L32:
        return Intrinsics.areEqual(r2, Boolean.TRUE);
    L33:
        return false;
    }

    /* renamed from: $r8$lambda$VdkigUCsWUZfsc-i0HeE9X8aPlM, reason: not valid java name */
    public static Unit m1763$r8$lambda$VdkigUCsWUZfsci0HeE9X8aPlM(Uri r0) {
        AndroidUtilities.runOnUIThread(new IntentsController$$ExternalSyntheticLambda11());
        return Unit.INSTANCE;
    }

    /* renamed from: $r8$lambda$XSQJC898WgFHCQs0er55I82-ZFw, reason: not valid java name */
    public static void m1764$r8$lambda$XSQJC898WgFHCQs0er55I82ZFw() {
        LaunchActivity.instance.presentFragment(new MainPreferencesActivity());
    }

    public static void $r8$lambda$_H6iNG4k37qXssQxtCAlLx56Njk() {
        LaunchActivity.instance.presentFragment(new OtherPreferencesActivity());
    }

    public static void $r8$lambda$eOXKanWMaPJcC477q9yONxM3Xe0(ChatActivity r2) {
        LaunchActivity.instance.presentFragment(r2, false, false);
    }

    public static void $r8$lambda$fMSENhVoH4_aOqMBacbw6n0YDio(AlertDialog r0, ProfileActivity r1, TLRPC.User r2) {
        r0.dismiss();
        if (r2 == null) goto L8;
        AndroidUtilities.runOnUIThread(new IntentsController$$ExternalSyntheticLambda16(r1));
        if (AndroidUtilities.isTablet() == false) goto L10;
        LaunchActivity.instance.actionBarLayout.showLastFragment();
        LaunchActivity.instance.rightActionBarLayout.showLastFragment();
        return;
    L10:
        return;
    L8:
        LaunchActivity.instance.showBulletin(new IntentsController$$ExternalSyntheticLambda17());
    }

    public static Unit $r8$lambda$hO4cczZ8354J7QKLjTpaMAfXDGo(Uri r5) {
        String r52 = r5.getQueryParameter("id");
        if (r52 == null) goto L10;
        Long r53 = StringsKt.toLongOrNull(r52);
        if (r53 == null) goto L10;
        long r0 = r53.longValue();
        BaseFragment r54 = LaunchActivity.getSafeLastFragment();
        if (r54 == null) goto L10;
        Bundle r2 = new Bundle();
        r2.putLong("user_id", r0);
        ProfileActivity r3 = new ProfileActivity(r2);
        AlertDialog r22 = new AlertDialog(r54.getParentActivity(), 3);
        r22.setCanCancel(false);
        r22.show();
        ChatUtils.getInstance().searchUserById(Long.valueOf(r0), new IntentsController$$ExternalSyntheticLambda15(r22, r3));
    L10:
        return Unit.INSTANCE;
    }

    public static void $r8$lambda$jYyv6h6wGupiOt4TPmcghJDTjGw(ProfileActivity r2) {
        LaunchActivity.instance.presentFragment(r2, false, false);
    }

    public static Bulletin $r8$lambda$jq3dLpMsWK6i1twg4yUPVlrYYGw(BulletinFactory r1) {
        return r1.createErrorBulletin(LocaleController.getString(R.string.UserNotFound));
    }

    public static boolean $r8$lambda$kpK16TMwYf4HP_RCzdTWFJUtNbc(Intent r2) {
        ExteraConfig.setPluginsSafeMode(true);
        if (ExteraConfig.getPluginsEngine() == false) goto L7;
        PluginsController.Companion r0 = PluginsController.Companion;
        if (r0.getInstance().isInitialized() == false) goto L7;
        r0.getInstance().restart(true);
    L7:
        return true;
    }

    public static void $r8$lambda$lhotcLqZBvtmStQ6Sg5mx092EVM(AlertDialog r0, ChatActivity r1, TLRPC.Chat r2) {
        r0.dismiss();
        if (r2 == null) goto L8;
        AndroidUtilities.runOnUIThread(new IntentsController$$ExternalSyntheticLambda18(r1));
        if (AndroidUtilities.isTablet() == false) goto L10;
        LaunchActivity.instance.actionBarLayout.showLastFragment();
        LaunchActivity.instance.rightActionBarLayout.showLastFragment();
        return;
    L10:
        return;
    L8:
        LaunchActivity.instance.showBulletin(new IntentsController$$ExternalSyntheticLambda19());
    }

    /* renamed from: $r8$lambda$q-eQESKA4W0dc4owyow1y6F_vKc, reason: not valid java name */
    public static void m1765$r8$lambda$qeQESKA4W0dc4owyow1y6F_vKc() {
        FeedActivity.presentFeed(LaunchActivity.getSafeLastFragment());
    }

    public static Unit $r8$lambda$tBSmwYVaiZjDaRBsFtTPoqLZWTM(Uri r6) {
        String r62 = r6.getQueryParameter("id");
        if (r62 == null) goto L14;
        Long r63 = StringsKt.toLongOrNull(r62);
        if (r63 == null) goto L14;
        long r0 = r63.longValue();
        BaseFragment r64 = LaunchActivity.getSafeLastFragment();
        if (r64 == null) goto L14;
        TLRPC.Document r02 = AnimatedEmojiDrawable.findDocument(UserConfig.selectedAccount, r0);
        if (r02 == null) goto L14;
        TLRPC.InputStickerSet r1 = MessageObject.getInputStickerSet(r02);
        if (r1 == null) goto L14;
        EmojiPacksAlert r2 = new EmojiPacksAlert(r64, r64.getParentActivity(), r64.getResourceProvider(), new ArrayList(Collections.singletonList(r1)));
        r2.setPreviewEmoji(r02);
        r64.showDialog(r2);
    L14:
        return Unit.INSTANCE;
    }

    static {
        INSTANCE = new IntentsController();
        deeplinkCallbacks = MapsKt.mapOf(new Pair[]{TuplesKt.to("extera", new IntentsController$$ExternalSyntheticLambda0()), TuplesKt.to("export", new IntentsController$$ExternalSyntheticLambda2()), TuplesKt.to("feed", new IntentsController$$ExternalSyntheticLambda3()), TuplesKt.to("support", new IntentsController$$ExternalSyntheticLambda4()), TuplesKt.to("donate", new IntentsController$$ExternalSyntheticLambda5()), TuplesKt.to("emoji", new IntentsController$$ExternalSyntheticLambda6()), TuplesKt.to("user", new IntentsController$$ExternalSyntheticLambda7()), TuplesKt.to("chat", new IntentsController$$ExternalSyntheticLambda8())});
        callbacks = MapsKt.mapOf(TuplesKt.to("exteraSettings", new IntentsController$$ExternalSyntheticLambda9()));
        actionCallbacks = MapsKt.mapOf(new Pair[]{TuplesKt.to("com.exteragram.plugins.safemode", new IntentsController$$ExternalSyntheticLambda10()), TuplesKt.to("android.intent.action.VIEW", new IntentsController$$ExternalSyntheticLambda1())});
    }

    private IntentsController() {
    }

    public final File getTempFileFromIntent(Uri r6) {
        File r5 = new File(ApplicationLoader.getFilesDirFixed(), "temp");
        if (r5.exists() == true) goto L5;
        r5.mkdirs();
    L5:
        File r0 = new File(r5, "temp_file_" + System.currentTimeMillis() + PluginsConstants.PLUGINS_EXT);
        InputStream r52 = ApplicationLoader.applicationContext.getContentResolver().openInputStream(r6);
        if (r52 != null) goto L25;
    L23:
        r0.deleteOnExit();
        return r0;
    L25:
        FileOutputStream r62 = new FileOutputStream(r0);     // Catch: Throwable -> L12
        ByteStreamsKt.copyTo$default(r52, r62, 0, 2, null);     // Catch: Throwable -> L14
        CloseableKt.closeFinally(r62, null);     // Catch: Throwable -> L12
        CloseableKt.closeFinally(r52, null);
        goto L23
    L14:
        th = move-exception;
        throw th;     // Catch: Throwable -> L16
    L16:
        th = move-exception;
        CloseableKt.closeFinally(r62, th);     // Catch: Throwable -> L12
        throw th;     // Catch: Throwable -> L12
    L12:
        th = move-exception;
        throw th;     // Catch: Throwable -> L20
    L20:
        th = move-exception;
        CloseableKt.closeFinally(r52, th);
        throw th;
    }

    public final boolean handleIntent(Intent r5) {
        String r4 = r5.getAction();
        if (r4 == null) goto L9;
        Map<String, Function1<Intent, Boolean>> r1 = actionCallbacks;
        if (r1.containsKey(r4) == false) goto L9;
        if (r1.get(r4).invoke(r5).booleanValue() == false) goto L9;
        return true;
    L9:
        Uri r42 = r5.getData();
        if ((r5.getFlags() & 1048576) != 0) goto L61;
        if (r42 == null) goto L61;
        if (r42.getScheme() == null) goto L61;
        if (Intrinsics.areEqual(r5.getAction(), "android.intent.action.VIEW") == false) goto L61;
        String r52 = r42.getScheme();
        if (r52 == null) goto L61;
        int r12 = r52.hashCode();
        if (r12 == 3699) goto L55;
        if (r12 == 3213448) goto L30;
        if (r12 != 99617003) goto L61;
        if (r52.equals("https") == false) goto L61;
    L32:
        String r53 = r42.getHost();
        if (r53 == null) goto L61;
        switch(r53.hashCode()) {
            case -831459633: goto L47;
            case -831448969: goto L44;
            case 3503442: goto L41;
            case 1912841637: goto L38;
            default: goto L61;
        };
    L38:
        if (r53.equals("telegram.me") == false) goto L61;
    L49:
        if (r42.getPathSegments().isEmpty() == true) goto L61;
        String r54 = r42.getPathSegments().get(0);
        Map<String, Function1<Uri, Unit>> r13 = callbacks;
        if (r13.containsKey(r54) == false) goto L61;
        r13.get(r54).invoke(r42);
        return true;
    L41:
        if (r53.equals("t.me") == true) goto L49;
    L44:
        if (r53.equals("telegram.org") == true) goto L49;
    L47:
        if (r53.equals("telegram.dog") == false) goto L61;
    L30:
        if (r52.equals("http") == true) goto L32;
    L55:
        if (r52.equals("tg") == false) goto L61;
        String r55 = r42.getHost();
        Map<String, Function1<Uri, Unit>> r14 = deeplinkCallbacks;
        if (r14.containsKey(r55) == false) goto L61;
        r14.get(r42.getHost()).invoke(r42);
        return true;
    L61:
        return false;
    }
}

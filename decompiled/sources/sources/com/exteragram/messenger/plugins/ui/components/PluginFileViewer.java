package com.exteragram.messenger.plugins.ui.components;

import android.app.Activity;
import android.content.DialogInterface;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.utils.MarkdownUtils;
import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Components.BulletinFactory;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J(\u0010\r\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0007J\u0010\u0010\u0012\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J&\u0010\u0013\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u001a\u0010\u0016\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "MAX_FILE_SIZE", _UrlKt.FRAGMENT_ENCODE_SET, "MAX_BLOCK_LENGTH", "SNIFF_LENGTH", "canOpen", _UrlKt.FRAGMENT_ENCODE_SET, "file", "Ljava/io/File;", "isText", "openExternally", "fragment", "Lorg/telegram/ui/ActionBar/BaseFragment;", "fileName", _UrlKt.FRAGMENT_ENCODE_SET, "isZip", "open", "createMessageObject", "Lorg/telegram/messenger/MessageObject;", "normalizeFileName", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPluginFileViewer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginFileViewer.kt\ncom/exteragram/messenger/plugins/ui/components/PluginFileViewer\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,198:1\n2792#2,3:199\n1#3:202\n*S KotlinDebug\n*F\n+ 1 PluginFileViewer.kt\ncom/exteragram/messenger/plugins/ui/components/PluginFileViewer\n*L\n49#1:199,3\n*E\n"})
/* loaded from: classes4.dex */
public final class PluginFileViewer {
    public static final PluginFileViewer INSTANCE = null;
    private static final int MAX_BLOCK_LENGTH = 8192;
    private static final int MAX_FILE_SIZE = 524288;
    private static final int SNIFF_LENGTH = 8192;

    public static void $r8$lambda$0xZUrpuuyQT7Lkt43HYs6kdYNiE(AlertDialog r0, boolean[] r1, MessageObject r2, BaseFragment r3) {
        r0.dismiss();     // Catch: Throwable -> L10
    L4:
        if (r1[0] == true) goto L14;
        if (r2 == null) goto L8;
        r3.createArticleViewer(false).open(r2);
        return;
    L8:
        BulletinFactory.of(r3).createSimpleBulletin(R.raw.error, LocaleController.getString(R.string.ErrorOccurred)).show();
        return;
    }

    /* renamed from: $r8$lambda$vQwAKkakqkeP6_e0FaokhJK-Wvo, reason: not valid java name */
    public static void m1610$r8$lambda$vQwAKkakqkeP6_e0FaokhJKWvo(File r1, String r2, AlertDialog r3, boolean[] r4, BaseFragment r5) {
        PluginFileViewer r0 = INSTANCE;     // Catch: Throwable -> L4
        MessageObject r12 = r0.createMessageObject(r1, r0.normalizeFileName(r1, r2));     // Catch: Throwable -> L4
    L6:
        AndroidUtilities.runOnUIThread(new PluginFileViewer$$ExternalSyntheticLambda2(r3, r4, r12, r5));
        return;
    L4:
        th = move-exception;
        FileLog.e(th);
        r12 = null;
        goto L6
    }

    /* renamed from: $r8$lambda$vbpziXMO_H-wI7YqbW1Hz2wiHAQ, reason: not valid java name */
    public static void m1611$r8$lambda$vbpziXMO_HwI7YqbW1Hz2wiHAQ(boolean[] r1, DialogInterface r2) {
        r1[0] = true;
    }

    static {
        INSTANCE = new PluginFileViewer();
    }

    private PluginFileViewer() {
    }

    @JvmStatic
    public static final boolean canOpen(File r4) {
        if (r4 != null) goto L4;
        return false;
    L4:
        if (r4.exists() == true) goto L6;
        return false;
    L6:
        if (r4.isFile() == true) goto L8;
        return false;
    L8:
        if (r4.length() <= 524288) goto L11;
        return false;
    L11:
        return INSTANCE.isText(r4);
    }

    private final MessageObject createMessageObject(File r8, String r9) {
        String r7 = FilesKt.readText(r8, Charsets.UTF_8);
        TL_iv.TL_page r0 = new TL_iv.TL_page();
        r0.local = r8;
        r0.url = r9;
        MarkdownUtils.appendPreformattedBlocks(r0.blocks, r7, PluginsConstants.PYTHON, 8192);
        TLRPC.TL_webPage r72 = new TLRPC.TL_webPage();
        r72.id = r8.getAbsolutePath().hashCode();
        r72.url = r9;
        r72.display_url = r9;
        r72.title = r9;
        r72.flags |= 1028;
        r72.cached_page = r0;
        long r02 = UserConfig.getInstance(UserConfig.selectedAccount).clientUserId;
        TLRPC.TL_message r82 = new TLRPC.TL_message();
        r82.id = 0;
        r82.date = (int) (System.currentTimeMillis() / 1000);
        r82.message = r9;
        r82.out = true;
        TLRPC.TL_peerUser r3 = new TLRPC.TL_peerUser();
        r3.user_id = r02;
        r82.peer_id = r3;
        TLRPC.TL_peerUser r32 = new TLRPC.TL_peerUser();
        r32.user_id = r02;
        r82.from_id = r32;
        TLRPC.TL_messageMediaWebPage r03 = new TLRPC.TL_messageMediaWebPage();
        r03.webpage = r72;
        r82.media = r03;
        return new MessageObject(UserConfig.selectedAccount, r82, false, true);
    }

    private final boolean isText(File r3) {
        FileInputStream r0 = new FileInputStream(r3);     // Catch: Throwable -> L25
        byte[] r32 = new byte[8192];     // Catch: Throwable -> L12
        int r1 = r0.read(r32);     // Catch: Throwable -> L12
        if (r1 <= 0) goto L21;
        List<Byte> r33 = ArraysKt.take(r32, r1);     // Catch: Throwable -> L12
        if ((r33 instanceof Collection) == true) goto L10;
    L14:
        Iterator<T> r34 = r33.iterator();     // Catch: Throwable -> L12
    L15:
        if (r34.hasNext() == false) goto L20;
        if (((Number) r34.next()).byteValue() != 0) goto L15;
    L20:
        boolean r35 = true;
    L23:
        CloseableKt.closeFinally(r0, null);     // Catch: Throwable -> L25
        return r35;
    L10:
        if (r33.isEmpty() == false) goto L14;
    L21:
        r35 = false;
    L12:
        th = move-exception;
        throw th;     // Catch: Throwable -> L28
    L28:
        th = move-exception;
        CloseableKt.closeFinally(r0, th);     // Catch: Throwable -> L25
        throw th;     // Catch: Throwable -> L25
    L25:
        th = move-exception;
        FileLog.e(th);
        return false;
    }

    private final boolean isZip(File r6) {
        FileInputStream r0 = new FileInputStream(r6);     // Catch: Throwable -> L10
        byte[] r1 = new byte[4];     // Catch: Throwable -> L24
        if (r0.read(r1) == 4) goto L13;
        CloseableKt.closeFinally(r0, null);     // Catch: Throwable -> L10
        return false;
    L13:
        if (r1[0] != 80) goto L26;
        boolean r62 = true;
        if (r1[1] != 75) goto L26;
        byte r12 = r1[2];     // Catch: Throwable -> L24
    L18:
        if (r12 != 3) goto L20;
    L27:
        CloseableKt.closeFinally(r0, null);     // Catch: Throwable -> L10
        return r62;
    L20:
        if (r12 == 5) goto L27;
        if (r12 == 7) goto L27;
    L26:
        r62 = false;
    L24:
        th = move-exception;
        throw th;     // Catch: Throwable -> L30
    L30:
        th = move-exception;
        CloseableKt.closeFinally(r0, th);     // Catch: Throwable -> L10
        throw th;     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        FileLog.e(th);
        return false;
    }

    private final String normalizeFileName(File r3, String r4) {
        if (r4 != null) goto L5;
    L9:
        r4 = r3.getName();
    L11:
        if (StringsKt.contains$default(r4, '.', false, 2, null) == false) goto L14;
        return r4;
    L14:
        return r4 + PluginsConstants.PLUGINS_EXT;
    L5:
        if (StringsKt.isBlank(r4) == false) goto L8;
        r4 = null;
    L8:
        if (r4 != null) goto L11;
        goto L9
    }

    public static /* synthetic */ boolean open$default(PluginFileViewer r0, BaseFragment r1, File r2, String r3, int r4, Object r5) {
        if ((r4 & 4) == 0) goto L6;
        r3 = null;
    L6:
        return r0.open(r1, r2, r3);
    }

    @JvmStatic
    public static final boolean openExternally(BaseFragment r8, File r9, String r10) {
        if (r8 == null) goto L47;
        Activity r5 = r8.getParentActivity();
        if (r5 == null) goto L47;
        if (r9 == null) goto L47;
        if (r9.exists() == false) goto L47;
        if (r9.isFile() == false) goto L47;
        PluginFileViewer r0 = INSTANCE;
        boolean r2 = r0.isZip(r9);
        if (r2 == false) goto L16;
    L18:
        boolean r02 = false;
    L20:
        if (r10 != null) goto L22;
    L26:
        r10 = r9.getName();
    L28:
        if (StringsKt.contains$default(r10, '.', false, 2, null) == true) goto L35;
        if (r2 == false) goto L31;
        String r3 = ".zip";
    L34:
        r10 = r10 + r3;
        goto L35
    L31:
        if (r02 == false) goto L33;
        r3 = ".txt";
        goto L34
    L33:
        r3 = ".bin";
    L35:
        String r32 = r10;
        if (r2 == false) goto L41;
        String r102 = "application/zip";
    L48:
        return AndroidUtilities.openForView(r9, r32, r102, r5, r8.getResourceProvider(), false);
    L39:
        th = move-exception;
        FileLog.e(th);
        BulletinFactory.of(r8).createSimpleBulletin(R.raw.error, LocaleController.getString(R.string.ErrorOccurred)).show();
        goto L47
    L41:
        if (r02 == false) goto L43;
        r102 = "text/plain";
        goto L48
    L43:
        r102 = "application/octet-stream";
        goto L48
    L22:
        if (StringsKt.isBlank(r10) == false) goto L25;
        r10 = null;
    L25:
        if (r10 != null) goto L28;
    L16:
        if (r0.isText(r9) == false) goto L18;
        r02 = true;
    L47:
        return false;
    }

    public static /* synthetic */ boolean openExternally$default(BaseFragment r0, File r1, String r2, int r3, Object r4) {
        if ((r3 & 4) == 0) goto L6;
        r2 = null;
    L6:
        return openExternally(r0, r1, r2);
    }

    public final boolean open(BaseFragment r8, File r9, String r10) {
        if (r8 == null) goto L19;
        Activity r0 = r8.getParentActivity();
        if (r0 == null) goto L19;
        if (r9 == null) goto L19;
        if (r9.exists() == false) goto L19;
        if (r9.isFile() == false) goto L19;
        if (canOpen(r9) == false) goto L16;
        AlertDialog r3 = new AlertDialog(r0, 3, r8.getResourceProvider());
        r3.setCanceledOnTouchOutside(false);
        boolean[] r4 = {false};
        r3.setOnCancelListener(new PluginFileViewer$$ExternalSyntheticLambda0(r4));
        r3.showDelayed(150);
        Utilities.globalQueue.postRunnable(new PluginFileViewer$$ExternalSyntheticLambda1(r9, r10, r3, r4, r8));
        return true;
    L16:
        return openExternally(r8, r9, r10);
    L19:
        return false;
    }
}

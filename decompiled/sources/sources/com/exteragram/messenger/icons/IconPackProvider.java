package com.exteragram.messenger.icons;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.DisplayMetrics;
import com.android.dx.rop.code.RegisterSpec;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.UByte;
import kotlin.collections.ArraysKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.R;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u0000 )2\u00020\u0001:\u0001)B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u0007H\u0016J\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0002J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\b\u001a\u00020\tH\u0002J\n\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0002J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0012\u001a\u00020\u000fH\u0002J,\u0010\u0013\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0014\u001a\u00020\u00072\u0018\u0010\u0015\u001a\u0014\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00050\u0016H\u0002JO\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\b\u001a\u00020\t2\u0010\u0010\u001b\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0007\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00072\u0010\u0010\u001e\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0007\u0018\u00010\u001c2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0007H\u0016¢\u0006\u0002\u0010 J\u001c\u0010!\u001a\u0004\u0018\u00010\t2\u0006\u0010\b\u001a\u00020\t2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J1\u0010$\u001a\u00020%2\u0006\u0010\b\u001a\u00020\t2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00072\u0010\u0010\u001e\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0007\u0018\u00010\u001cH\u0016¢\u0006\u0002\u0010&J;\u0010'\u001a\u00020%2\u0006\u0010\b\u001a\u00020\t2\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00072\u0010\u0010\u001e\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0007\u0018\u00010\u001cH\u0016¢\u0006\u0002\u0010(¨\u0006*"}, d2 = {"Lcom/exteragram/messenger/icons/IconPackProvider;", "Landroid/content/ContentProvider;", "<init>", "()V", "onCreate", _UrlKt.FRAGMENT_ENCODE_SET, "getType", _UrlKt.FRAGMENT_ENCODE_SET, "uri", "Landroid/net/Uri;", "openFile", "Landroid/os/ParcelFileDescriptor;", "mode", "isIconUri", "resolveSource", "Ljava/io/File;", "getFallbackIcon", "getRasterizedIcon", "source", "materializeCacheFile", "name", "write", "Lkotlin/Function2;", "Landroid/content/Context;", "Ljava/io/FileOutputStream;", "query", "Landroid/database/Cursor;", "projection", _UrlKt.FRAGMENT_ENCODE_SET, "selection", "selectionArgs", "sortOrder", "(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;", "insert", "values", "Landroid/content/ContentValues;", "delete", _UrlKt.FRAGMENT_ENCODE_SET, "(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I", PluginsConstants.UPDATE, "(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nIconPackProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IconPackProvider.kt\ncom/exteragram/messenger/icons/IconPackProvider\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,172:1\n1#2:173\n*E\n"})
/* loaded from: classes.dex */
public final class IconPackProvider extends ContentProvider {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/exteragram/messenger/icons/IconPackProvider$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "PATH_ICON", _UrlKt.FRAGMENT_ENCODE_SET, "getIconUri", "Landroid/net/Uri;", "packId", "resourceName", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final Uri getIconUri(String r7, String r8) {
            File r6 = IconPackStorage.INSTANCE.resolveIconFile(r7, r8);
            if (r6 != null) goto L6;
            return null;
        L6:
            if (SetsKt.setOf(new String[]{"png", "webp", "jpg", "jpeg", "svg"}).contains(FilesKt.getExtension(r6).toLowerCase(Locale.ROOT)) == true) goto L9;
            return null;
        L9:
            return new Uri.Builder().scheme("content").authority(ApplicationLoader.getApplicationId() + ".icon_pack_provider").appendPath("icon").appendPath(r7).appendPath(r8).appendQueryParameter(RegisterSpec.PREFIX, r6.lastModified() + "_" + r6.length()).build();
        }
    }

    public static CharSequence $r8$lambda$BKCbjADtECYsE9lhXAT0ssIGLOM(byte r1) {
        return String.format("%02x", Arrays.copyOf(new Object[]{Integer.valueOf(r1 & UByte.MAX_VALUE)}, 1));
    }

    public static boolean $r8$lambda$S_eHMtdc1mNoB0THEzSCNKhKnks(Context r2, FileOutputStream r3) {
        Bitmap r22 = BitmapFactory.decodeResource(r2.getResources(), R.drawable.notification);
        if (r22 != null) goto L12;
        return false;
    L12:
        boolean r32 = r22.compress(Bitmap.CompressFormat.PNG, 100, r3);     // Catch: Throwable -> L9
        r22.recycle();
        return r32;
    L9:
        th = move-exception;
        r22.recycle();
        throw th;
    }

    /* renamed from: $r8$lambda$Y6KsDB3G-jqO3xh6Cr63Mk7_c14, reason: not valid java name */
    public static boolean m1441$r8$lambda$Y6KsDB3GjqO3xh6Cr63Mk7_c14(File r3, Context r4, FileOutputStream r5) {
        Bitmap r32 = IconManager.INSTANCE.createBitmapFromFile(r3.getAbsolutePath(), R.drawable.notification, r4.getResources().getDisplayMetrics().densityDpi, null);
        if (r32 != null) goto L12;
        return false;
    L12:
        boolean r42 = r32.compress(Bitmap.CompressFormat.PNG, 100, r5);     // Catch: Throwable -> L9
        r32.recycle();
        return r42;
    L9:
        th = move-exception;
        r32.recycle();
        throw th;
    }

    static {
        Companion = new Companion(null);
    }

    public IconPackProvider() {
    }

    private final File getFallbackIcon() {
        Context r0 = getContext();
        if (r0 == null) goto L10;
        Resources r02 = r0.getResources();
        if (r02 == null) goto L12;
        DisplayMetrics r03 = r02.getDisplayMetrics();
        if (r03 != null) goto L9;
        return null;
    L9:
        return materializeCacheFile("default_" + r03.densityDpi + ".png", new IconPackProvider$$ExternalSyntheticLambda2());
    L12:
        return null;
    L10:
        return null;
    }

    private final File getRasterizedIcon(File r12) {
        Context r0 = getContext();
        if (r0 == null) goto L10;
        Resources r02 = r0.getResources();
        if (r02 == null) goto L12;
        DisplayMetrics r03 = r02.getDisplayMetrics();
        if (r03 == null) goto L13;
        int r04 = r03.densityDpi;
        return materializeCacheFile(ArraysKt.joinToString$default(MessageDigest.getInstance("SHA-256").digest((r12.getCanonicalPath() + ":" + r12.lastModified() + ":" + r12.length() + ":" + r04).getBytes(Charsets.UTF_8)), _UrlKt.FRAGMENT_ENCODE_SET, null, null, 0, null, new IconPackProvider$$ExternalSyntheticLambda0(), 30, null) + ".png", new IconPackProvider$$ExternalSyntheticLambda1(r12));
    L13:
        return null;
    L12:
        return null;
    L10:
        return null;
    }

    private final boolean isIconUri(Uri r3) {
        List<String> r2 = r3.getPathSegments();
        if (r2.size() == 3) goto L5;
    L8:
        return false;
    L5:
        if (Intrinsics.areEqual(r2.get(0), "icon") == false) goto L8;
        return true;
    }

    private final File materializeCacheFile(String r10, Function2<? super Context, ? super FileOutputStream, Boolean> r11) {
        Context r9 = getContext();
        File r1 = null;
        if (r9 != null) goto L5;
        return null;
    L5:
        File r0 = new File(r9.getCacheDir(), "notification_icons");
        if (r0.exists() == false) goto L8;
    L10:
        File r4 = new File(r0, r10);
        if (r4.isFile() == false) goto L16;
        if (r4.length() <= 0) goto L16;
        return r4;
    L16:
        monitor-enter(IconPackProvider.class);
    L24:
        th = move-exception;
        throw th;
    L18:
        if (r4.isFile() == true) goto L21;
    L26:
        File r3 = new File(r0, r10 + ".tmp");     // Catch: Throwable -> L24
        FileOutputStream r102 = new FileOutputStream(r3);     // Catch: Throwable -> L40 Exception -> L57
        boolean r92 = r11.invoke(r9, r102).booleanValue();     // Catch: Throwable -> L46
        CloseableKt.closeFinally(r102, null);     // Catch: Throwable -> L40 Exception -> L57
        if (r92 == true) goto L37;
        if (r3.exists() == false) goto L34;
        r3.delete();     // Catch: Throwable -> L24
    L34:
        monitor-exit(IconPackProvider.class);
        return null;
    L37:
        if (r3.renameTo(r4) == true) goto L43;
        FilesKt.copyTo$default(r3, r4, true, 0, 4, null);     // Catch: Throwable -> L40 Exception -> L57
        r3.delete();     // Catch: Throwable -> L40 Exception -> L57
    L43:
        if (r3.exists() == false) goto L45;
        r3.delete();     // Catch: Throwable -> L24
    L45:
        r1 = r4;
    L60:
        monitor-exit(IconPackProvider.class);
        return r1;
    L46:
        th = move-exception;
        throw th;     // Catch: Throwable -> L49
    L49:
        th = move-exception;
        CloseableKt.closeFinally(r102, th);     // Catch: Throwable -> L40 Exception -> L57
        throw th;     // Catch: Throwable -> L40 Exception -> L57
    L40:
        th = move-exception;
        if (r3.exists() == false) goto L70;
        r3.delete();     // Catch: Throwable -> L24
        throw th;     // Catch: Throwable -> L24
    L70:
        throw th;     // Catch: Throwable -> L24
    L58:
        if (r3.exists() == false) goto L60;
        r3.delete();     // Catch: Throwable -> L24
        goto L60
    L21:
        if (r4.length() <= 0) goto L26;
        monitor-exit(IconPackProvider.class);
        return r4;
    L8:
        if (r0.mkdirs() == true) goto L10;
        return null;
    }

    private final File resolveSource(Uri r4) {
        List<String> r0 = r4.getPathSegments();
        if (isIconUri(r4) == true) goto L5;
        return null;
    L5:
        File r3 = IconPackStorage.INSTANCE.resolveIconFile(r0.get(1), r0.get(2));
        if (r3 != null) goto L8;
        return null;
    L8:
        String r02 = FilesKt.getExtension(r3).toLowerCase(Locale.ROOT);
        switch(r02.hashCode()) {
            case 105441: goto L24;
            case 111145: goto L21;
            case 114276: goto L18;
            case 3268712: goto L15;
            case 3645340: goto L12;
            default: goto L25;
        };
    L25:
        return null;
    L12:
        if (r02.equals("webp") == false) goto L25;
    L26:
        return r3;
    L15:
        if (r02.equals("jpeg") == true) goto L26;
    L18:
        if (r02.equals("svg") == true) goto L26;
    L21:
        if (r02.equals("png") == true) goto L26;
    L24:
        if (r02.equals("jpg") == true) goto L26;
        goto L25
    }

    @Override // android.content.ContentProvider
    public int delete(Uri r1, String r2, String[] r3) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri r1) {
        if (isIconUri(r1) == false) goto L6;
        return "image/png";
    L6:
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri r1, ContentValues r2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        Context r1 = getContext();
        if (r1 == null) goto L11;
        Context r12 = r1.getApplicationContext();
        if (r12 != null) goto L7;
        return true;
    L7:
        if (ApplicationLoader.applicationContext != null) goto L13;
        ApplicationLoader.applicationContext = r12;
        return true;
    L13:
        return true;
    L11:
        return true;
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri r3, String r4) {
        if (Intrinsics.areEqual(r4, "r") == false) goto L32;
        if (isIconUri(r3) == false) goto L30;
        Object r42 = null;
        Result.Companion r0 = Result.Companion;     // Catch: Throwable -> L10
        File r02 = resolveSource(r3);     // Catch: Throwable -> L10
        if (r02 == null) goto L12;
        File r03 = getRasterizedIcon(r02);     // Catch: Throwable -> L10
    L13:
        Object r04 = Result.m2018constructorimpl(r03);     // Catch: Throwable -> L10
    L17:
        if (Result.m2024isFailureimpl(r04) == true) goto L20;
        r42 = r04;
    L20:
        File r43 = (File) r42;
        if (r43 != null) goto L28;
        r43 = getFallbackIcon();
        if (r43 != null) goto L28;
        throw new FileNotFoundException(r3.toString());
    L28:
        return ParcelFileDescriptor.open(r43, 268435456);
    L12:
        r03 = null;
    L10:
        th = move-exception;
        Result.Companion r1 = Result.Companion;
        r04 = Result.m2018constructorimpl(ResultKt.createFailure(th));
        goto L17
    L30:
        throw new FileNotFoundException(r3.toString());
    L32:
        throw new SecurityException("Icon pack files are read-only");
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri r1, String[] r2, String r3, String[] r4, String r5) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri r1, ContentValues r2, String r3, String[] r4) {
        return 0;
    }
}

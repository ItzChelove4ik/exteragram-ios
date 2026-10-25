package dev.exterahook.runtime.internal;

import a.a;
import a.c;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.exteragram.messenger.plugins.PluginsConstants;
import dev.exterahook.runtime.bridge.HookBridgeProvider;
import dev.exterahook.runtime.bridge.JniBridgeBindings;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016JO\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0010\u0010\n\u001a\f\u0012\u0006\b\u0001\u0012\u00020\f\u0018\u00010\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\f2\u0010\u0010\u000e\u001a\f\u0012\u0006\b\u0001\u0012\u00020\f\u0018\u00010\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\fH\u0016¢\u0006\u0002\u0010\u0010J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\f2\u0006\u0010\b\u001a\u00020\tH\u0016J\u001c\u0010\u0012\u001a\u0004\u0018\u00010\t2\u0006\u0010\b\u001a\u00020\t2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J1\u0010\u0015\u001a\u00020\u00162\u0006\u0010\b\u001a\u00020\t2\b\u0010\r\u001a\u0004\u0018\u00010\f2\u0010\u0010\u000e\u001a\f\u0012\u0006\b\u0001\u0012\u00020\f\u0018\u00010\u000bH\u0016¢\u0006\u0002\u0010\u0017J;\u0010\u0018\u001a\u00020\u00162\u0006\u0010\b\u001a\u00020\t2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\r\u001a\u0004\u0018\u00010\f2\u0010\u0010\u000e\u001a\f\u0012\u0006\b\u0001\u0012\u00020\f\u0018\u00010\u000bH\u0016¢\u0006\u0002\u0010\u0019¨\u0006\u001a"}, d2 = {"Ldev/exterahook/runtime/internal/ExteraHookInitProvider;", "Landroid/content/ContentProvider;", "<init>", "()V", "onCreate", _UrlKt.FRAGMENT_ENCODE_SET, "query", "Landroid/database/Cursor;", "uri", "Landroid/net/Uri;", "projection", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "selection", "selectionArgs", "sortOrder", "(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;", "getType", "insert", "values", "Landroid/content/ContentValues;", "delete", _UrlKt.FRAGMENT_ENCODE_SET, "(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I", PluginsConstants.UPDATE, "(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I", "exterahook_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class ExteraHookInitProvider extends ContentProvider {
    public ExteraHookInitProvider() {
    }

    @Override // android.content.ContentProvider
    public int delete(Uri r1, String r2, String[] r3) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri r1) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri r1, ContentValues r2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        HookBridgeProvider.Companion.getClass();     // Catch: Throwable -> L8
        if (HookBridgeProvider.access$getUseEmbeddedByDefault$cp() == false) goto L5;
        a r2 = (a) HookBridgeProvider.access$getEmbeddedBridge$delegate$cp().getValue();     // Catch: Throwable -> L8
    L6:
        ((c) r2).a();     // Catch: Throwable -> L8
        JniBridgeBindings.initialize0();     // Catch: Throwable -> L8
        return true;
    L5:
        r2 = (a) HookBridgeProvider.access$getDefaultBridge$delegate$cp().getValue();     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        Log.e("exteraHook-InitProvider", "Early runtime bootstrap failed", th);
        return true;
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

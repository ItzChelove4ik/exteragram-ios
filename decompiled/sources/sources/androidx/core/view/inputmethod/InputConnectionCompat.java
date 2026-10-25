package androidx.core.view.inputmethod;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputContentInfo;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import androidx.core.view.ContentInfoCompat;
import androidx.core.view.ViewCompat;

@SuppressLint({"PrivateConstructorForUtilityClass"})
/* loaded from: classes3.dex */
public abstract class InputConnectionCompat {

    /* renamed from: androidx.core.view.inputmethod.InputConnectionCompat$1, reason: invalid class name */
    public class AnonymousClass1 extends InputConnectionWrapper {
        final /* synthetic */ OnCommitContentListener val$listener;

        public AnonymousClass1(InputConnection r1, boolean r2, OnCommitContentListener r3) {
            this.val$listener = r3;
            super(r1, r2);
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public boolean commitContent(InputContentInfo r3, int r4, Bundle r5) {
            if (this.val$listener.onCommitContent(InputContentInfoCompat.wrap(r3), r4, r5) == false) goto L7;
            return true;
        L7:
            return super.commitContent(r3, r4, r5);
        }
    }

    /* renamed from: androidx.core.view.inputmethod.InputConnectionCompat$2, reason: invalid class name */
    public class AnonymousClass2 extends InputConnectionWrapper {
        final /* synthetic */ OnCommitContentListener val$listener;

        public AnonymousClass2(InputConnection r1, boolean r2, OnCommitContentListener r3) {
            this.val$listener = r3;
            super(r1, r2);
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public boolean performPrivateCommand(String r2, Bundle r3) {
            if (InputConnectionCompat.handlePerformPrivateCommand(r2, r3, this.val$listener) == false) goto L7;
            return true;
        L7:
            return super.performPrivateCommand(r2, r3);
        }
    }

    public interface OnCommitContentListener {
        boolean onCommitContent(InputContentInfoCompat r1, int r2, Bundle r3);
    }

    public static /* synthetic */ boolean $r8$lambda$W5vh3RZmbJZXHbjpKG2oyXMz20Y(View r5, InputContentInfoCompat r6, int r7, Bundle r8) {
        if (Build.VERSION.SDK_INT < 25) goto L16;
        if ((r7 & 1) == 0) goto L16;
        r6.requestPermission();     // Catch: Exception -> L12
        Parcelable r72 = (Parcelable) r6.unwrap();
        if (r8 != null) goto L10;
        r8 = new Bundle();
    L11:
        r8.putParcelable("androidx.core.view.extra.INPUT_CONTENT_INFO", r72);
        goto L16
    L10:
        r8 = new Bundle(r8);
    L12:
        e = move-exception;
        Log.w("InputConnectionCompat", "Can't insert content from IME; requestPermission() failed", e);
        return false;
    L16:
        if (ViewCompat.performReceiveContent(r5, new ContentInfoCompat.Builder(new ClipData(r6.getDescription(), new ClipData.Item(r6.getContentUri())), 2).setLinkUri(r6.getLinkUri()).setExtras(r8).build()) != null) goto L18;
        return true;
    L18:
        return false;
    }

    private static OnCommitContentListener createOnCommitContentListenerUsingPerformReceiveContent(View r1) {
        Preconditions.checkNotNull(r1);
        return new InputConnectionCompat$$ExternalSyntheticLambda0(r1);
    }

    public static InputConnection createWrapper(View r0, InputConnection r1, EditorInfo r2) {
        return createWrapper(r1, r2, createOnCommitContentListenerUsingPerformReceiveContent(r0));
    }

    @Deprecated
    public static InputConnection createWrapper(InputConnection r3, EditorInfo r4, OnCommitContentListener r5) {
        ObjectsCompat.requireNonNull(r3, "inputConnection must be non-null");
        ObjectsCompat.requireNonNull(r4, "editorInfo must be non-null");
        ObjectsCompat.requireNonNull(r5, "onCommitContentListener must be non-null");
        if (Build.VERSION.SDK_INT < 25) goto L7;
        return new AnonymousClass1(r3, false, r5);
    L7:
        if (EditorInfoCompat.getContentMimeTypes(r4).length != 0) goto L10;
        return r3;
    L10:
        return new AnonymousClass2(r3, false, r5);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v3, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    public static boolean handlePerformPrivateCommand(String r7, Bundle r8, OnCommitContentListener r9) {
        ?? r0 = 0;
        r0 = 0;
        if (r8 != null) goto L6;
        return false;
    L6:
        if (TextUtils.equals("androidx.core.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT", r7) == false) goto L9;
        boolean r72 = false;
    L12:
        if (r72 == false) goto L16;
        String r2 = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_RESULT_RECEIVER";
    L52:
        ResultReceiver r22 = (ResultReceiver) r8.getParcelable(r2);     // Catch: Throwable -> L14
        if (r72 == false) goto L22;
        String r3 = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_URI";
    L50:
        Uri r32 = (Uri) r8.getParcelable(r3);     // Catch: Throwable -> L20
        if (r72 == false) goto L26;
        String r4 = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION";
    L27:
        ClipDescription r42 = (ClipDescription) r8.getParcelable(r4);     // Catch: Throwable -> L20
        if (r72 == false) goto L30;
        String r5 = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI";
    L31:
        Uri r52 = (Uri) r8.getParcelable(r5);     // Catch: Throwable -> L20
        if (r72 == false) goto L34;
        String r6 = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS";
    L35:
        int r62 = r8.getInt(r6);     // Catch: Throwable -> L20
        if (r72 == false) goto L38;
        String r73 = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_OPTS";
    L39:
        Bundle r74 = (Bundle) r8.getParcelable(r73);     // Catch: Throwable -> L20
        if (r32 == null) goto L43;
        if (r42 == null) goto L43;
        r0 = r9.onCommitContent(new InputContentInfoCompat(r32, r42, r52), r62, r74);     // Catch: Throwable -> L20
    L43:
        if (r22 == 0) goto L45;
        r22.send(r0, null);
    L45:
        return r0;
    L38:
        r73 = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_OPTS";
        goto L39
    L34:
        r6 = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS";
        goto L35
    L30:
        r5 = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI";
        goto L31
    L26:
        r4 = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION";
    L20:
        th = th;
        ResultReceiver r23 = r22;
    L46:
        if (r23 == null) goto L48;
        r23.send(0, null);
    L48:
        throw th;
    L22:
        r3 = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_URI";
    L14:
        th = th;
        r23 = null;
        goto L46
    L16:
        r2 = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_RESULT_RECEIVER";
        goto L52
    L9:
        if (TextUtils.equals("android.support.v13.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT", r7) == false) goto L49;
        r72 = true;
        goto L12
    L49:
        return false;
    }
}

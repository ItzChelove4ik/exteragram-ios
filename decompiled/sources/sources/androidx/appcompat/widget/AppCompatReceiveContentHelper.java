package androidx.appcompat.widget;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.text.Selection;
import android.text.Spannable;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;
import androidx.core.view.ContentInfoCompat;
import androidx.core.view.ViewCompat;

/* loaded from: classes3.dex */
abstract class AppCompatReceiveContentHelper {

    public static final class OnDropApi24Impl {
        public static boolean onDropForTextView(DragEvent r1, TextView r2, Activity r3) {
            r3.requestDragAndDropPermissions(r1);
            int r32 = r2.getOffsetForPosition(r1.getX(), r1.getY());
            r2.beginBatchEdit();
            Selection.setSelection((Spannable) r2.getText(), r32);     // Catch: Throwable -> L6
            ViewCompat.performReceiveContent(r2, new ContentInfoCompat.Builder(r1.getClipData(), 3).build());     // Catch: Throwable -> L6
            r2.endBatchEdit();
            return true;
        L6:
            th = move-exception;
            r2.endBatchEdit();
            throw th;
        }

        public static boolean onDropForView(DragEvent r1, View r2, Activity r3) {
            r3.requestDragAndDropPermissions(r1);
            ViewCompat.performReceiveContent(r2, new ContentInfoCompat.Builder(r1.getClipData(), 3).build());
            return true;
        }
    }

    public static boolean maybeHandleDragEventViaPerformReceiveContent(View r4, DragEvent r5) {
        if (Build.VERSION.SDK_INT < 31) goto L5;
    L25:
        return false;
    L5:
        if (r5.getLocalState() != null) goto L25;
        if (ViewCompat.getOnReceiveContentMimeTypes(r4) == null) goto L25;
        Activity r0 = tryGetActivity(r4);
        if (r0 != null) goto L14;
        Log.i("ReceiveContent", "Can't handle drop: no activity: view=" + r4);
        return false;
    L14:
        if (r5.getAction() != 1) goto L18;
        return !(r4 instanceof TextView);
    L18:
        if (r5.getAction() != 3) goto L25;
        if ((r4 instanceof TextView) == false) goto L24;
        return OnDropApi24Impl.onDropForTextView(r5, (TextView) r4, r0);
    L24:
        return OnDropApi24Impl.onDropForView(r5, r4, r0);
    }

    public static boolean maybeHandleMenuActionViaPerformReceiveContent(TextView r5, int r6) {
        int r2 = 0;
        if (Build.VERSION.SDK_INT < 31) goto L5;
    L25:
        return false;
    L5:
        if (ViewCompat.getOnReceiveContentMimeTypes(r5) == null) goto L25;
        if (r6 != 16908322) goto L9;
    L11:
        ClipboardManager r1 = (ClipboardManager) r5.getContext().getSystemService("clipboard");
        if (r1 != null) goto L14;
        ClipData r12 = null;
    L16:
        if (r12 != null) goto L18;
    L24:
        return true;
    L18:
        if (r12.getItemCount() <= 0) goto L24;
        ContentInfoCompat.Builder r4 = new ContentInfoCompat.Builder(r12, 1);
        if (r6 == 16908322) goto L23;
        r2 = 1;
    L23:
        ViewCompat.performReceiveContent(r5, r4.setFlags(r2).build());
        goto L24
    L14:
        r12 = r1.getPrimaryClip();
        goto L16
    L9:
        if (r6 == 16908337) goto L11;
        goto L11
    }

    public static Activity tryGetActivity(View r1) {
        Context r12 = r1.getContext();
    L4:
        if ((r12 instanceof ContextWrapper) == false) goto L10;
        if ((r12 instanceof Activity) == true) goto L8;
        r12 = ((ContextWrapper) r12).getBaseContext();
        goto L4
    L8:
        return (Activity) r12;
    L10:
        return null;
    }
}

package androidx.core.view;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: classes3.dex */
public abstract class KeyEventDispatcher {
    private static boolean sActionBarFieldsFetched = false;
    private static Method sActionBarOnMenuKeyMethod = null;
    private static boolean sDialogFieldsFetched = false;
    private static Field sDialogKeyListenerField;

    /* loaded from: classes.dex */
    public interface Component {
        boolean superDispatchKeyEvent(KeyEvent r1);
    }

    static {
    }

    private static boolean actionBarOnMenuKeyEventPre28(ActionBar r3, KeyEvent r4) {
        if (sActionBarFieldsFetched == false) goto L18;
    L6:
        Method r0 = sActionBarOnMenuKeyMethod;
        if (r0 != null) goto L16;
    L13:
        return false;
    L16:
        Object r32 = r0.invoke(r3, new Object[]{r4});     // Catch: Throwable -> L14
        if (r32 != null) goto L11;
        return false;
    L11:
        return ((Boolean) r32).booleanValue();
    L18:
        sActionBarOnMenuKeyMethod = r3.getClass().getMethod("onMenuKeyEvent", new Class[]{KeyEvent.class});     // Catch: NoSuchMethodException -> L15
    L5:
        sActionBarFieldsFetched = true;
        goto L6
    }

    private static boolean activitySuperDispatchKeyEventPre28(Activity r5, KeyEvent r6) {
        r5.onUserInteraction();
        Window r0 = r5.getWindow();
        if (r0.hasFeature(8) == false) goto L11;
        ActionBar r1 = r5.getActionBar();
        if (r6.getKeyCode() != 82) goto L11;
        if (r1 == null) goto L11;
        if (actionBarOnMenuKeyEventPre28(r1, r6) == false) goto L11;
        return true;
    L11:
        if (r0.superDispatchKeyEvent(r6) == false) goto L13;
        return true;
    L13:
        View r02 = r0.getDecorView();
        if (ViewCompat.dispatchUnhandledKeyEventBeforeCallback(r02, r6) == false) goto L16;
        return true;
    L16:
        if (r02 == null) goto L18;
        KeyEvent.DispatcherState r03 = r02.getKeyDispatcherState();
    L20:
        return r6.dispatch(r5, r03, r5);
    L18:
        r03 = null;
        goto L20
    }

    private static boolean dialogSuperDispatchKeyEventPre28(Dialog r3, KeyEvent r4) {
        DialogInterface.OnKeyListener r0 = getDialogKeyListenerPre28(r3);
        if (r0 != null) goto L5;
    L7:
        Window r02 = r3.getWindow();
        if (r02.superDispatchKeyEvent(r4) == false) goto L10;
        return true;
    L10:
        View r03 = r02.getDecorView();
        if (ViewCompat.dispatchUnhandledKeyEventBeforeCallback(r03, r4) == false) goto L13;
        return true;
    L13:
        if (r03 == null) goto L15;
        KeyEvent.DispatcherState r04 = r03.getKeyDispatcherState();
    L17:
        return r4.dispatch(r3, r04, r3);
    L15:
        r04 = null;
        goto L17
    L5:
        if (r0.onKey(r3, r4.getKeyCode(), r4) == false) goto L7;
        return true;
    }

    public static boolean dispatchBeforeHierarchy(View r0, KeyEvent r1) {
        return ViewCompat.dispatchUnhandledKeyEventBeforeHierarchy(r0, r1);
    }

    @SuppressLint({"LambdaLast"})
    public static boolean dispatchKeyEvent(Component r3, View r4, Window.Callback r5, KeyEvent r6) {
        if (r3 != null) goto L6;
        return false;
    L6:
        if (Build.VERSION.SDK_INT < 28) goto L10;
        return r3.superDispatchKeyEvent(r6);
    L10:
        if ((r5 instanceof Activity) == false) goto L14;
        return activitySuperDispatchKeyEventPre28((Activity) r5, r6);
    L14:
        if ((r5 instanceof Dialog) == true) goto L16;
        if (r4 == null) goto L21;
        if (ViewCompat.dispatchUnhandledKeyEventBeforeCallback(r4, r6) == false) goto L21;
        return true;
    L21:
        if (r3.superDispatchKeyEvent(r6) == true) goto L25;
        return false;
    L25:
        return true;
    L16:
        return dialogSuperDispatchKeyEventPre28((Dialog) r5, r6);
    }

    private static DialogInterface.OnKeyListener getDialogKeyListenerPre28(Dialog r3) {
        if (sDialogFieldsFetched == false) goto L17;
    L7:
        Field r0 = sDialogKeyListenerField;
        if (r0 != null) goto L15;
        return null;
    L15:
        return (DialogInterface.OnKeyListener) r0.get(r3);
    L19:
        return null;
    L17:
        Field r1 = Dialog.class.getDeclaredField("mOnKeyListener");     // Catch: NoSuchFieldException -> L13
        sDialogKeyListenerField = r1;     // Catch: NoSuchFieldException -> L13
        r1.setAccessible(true);     // Catch: NoSuchFieldException -> L13
    L6:
        sDialogFieldsFetched = true;
        goto L7
    }
}

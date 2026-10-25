package androidx.appcompat.app;

import android.R;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.appcompat.R$styleable;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.AppCompatToggleButton;
import androidx.appcompat.widget.TintContextWrapper;
import androidx.collection.SimpleArrayMap;
import androidx.core.view.ViewCompat;
import androidx.work.WorkerFactory$$ExternalSyntheticBUOutline0;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.Constants$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public class AppCompatViewInflater {
    private static final String LOG_TAG = "AppCompatViewInflater";
    private static final int[] sAccessibilityHeading = null;
    private static final int[] sAccessibilityPaneTitle = null;
    private static final String[] sClassPrefixList = null;
    private static final SimpleArrayMap<String, Constructor<? extends View>> sConstructorMap = null;
    private static final Class<?>[] sConstructorSignature = null;
    private static final int[] sOnClickAttrs = null;
    private static final int[] sScreenReaderFocusable = null;
    private final Object[] mConstructorArgs;

    public static class DeclaredOnClickListener implements View.OnClickListener {
        private final View mHostView;
        private final String mMethodName;
        private Context mResolvedContext;
        private Method mResolvedMethod;

        public DeclaredOnClickListener(View r1, String r2) {
            this.mHostView = r1;
            this.mMethodName = r2;
        }

        private void resolveMethod(Context r4) {
        L2:
            if (r4 == null) goto L13;
            if (r4.isRestricted() == true) goto L10;
            Method r0 = r4.getClass().getMethod(this.mMethodName, new Class[]{View.class});     // Catch: NoSuchMethodException -> L19
            if (r0 == null) goto L10;
            this.mResolvedMethod = r0;     // Catch: NoSuchMethodException -> L19
            this.mResolvedContext = r4;     // Catch: NoSuchMethodException -> L19
            return;
        L10:
            if ((r4 instanceof ContextWrapper) == true) goto L11;
            r4 = null;
            goto L2
        L11:
            r4 = ((ContextWrapper) r4).getBaseContext();
            goto L2
        L13:
            int r42 = this.mHostView.getId();
            if (r42 != (-1)) goto L16;
            String r43 = _UrlKt.FRAGMENT_ENCODE_SET;
        L17:
            WorkerFactory$$ExternalSyntheticBUOutline0.m("Could not find method ", this.mMethodName, "(View) in a parent or ancestor Context for android:onClick attribute defined on view ", this.mHostView.getClass(), r43);
            return;
        L16:
            r43 = " with id '" + this.mHostView.getContext().getResources().getResourceEntryName(r42) + "'";
            goto L17
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r2) {
            if (this.mResolvedMethod != null) goto L13;
            resolveMethod(this.mHostView.getContext());
        L13:
            this.mResolvedMethod.invoke(this.mResolvedContext, new Object[]{r2});     // Catch: InvocationTargetException -> L7 IllegalAccessException -> L10
            return;
        L10:
            e = move-exception;
            Constants$$ExternalSyntheticBUOutline0.m("Could not execute non-public method for android:onClick", e);
            return;
        L7:
            e = move-exception;
            Constants$$ExternalSyntheticBUOutline0.m("Could not execute method for android:onClick", e);
        }
    }

    static {
        sConstructorSignature = new Class[]{Context.class, AttributeSet.class};
        sOnClickAttrs = new int[]{R.attr.onClick};
        sAccessibilityHeading = new int[]{R.attr.accessibilityHeading};
        sAccessibilityPaneTitle = new int[]{R.attr.accessibilityPaneTitle};
        sScreenReaderFocusable = new int[]{R.attr.screenReaderFocusable};
        sClassPrefixList = new String[]{"android.widget.", "android.view.", "android.webkit."};
        sConstructorMap = new SimpleArrayMap();
    }

    public AppCompatViewInflater() {
        this.mConstructorArgs = new Object[2];
    }

    private void backportAccessibilityAttributes(Context r3, View r4, AttributeSet r5) {
        if (Build.VERSION.SDK_INT <= 28) goto L5;
        return;
    L5:
        TypedArray r2 = r3.obtainStyledAttributes(r5, sAccessibilityHeading);
        if (r2.hasValue(0) == false) goto L8;
        ViewCompat.setAccessibilityHeading(r4, r2.getBoolean(0, false));
    L8:
        r2.recycle();
        TypedArray r22 = r3.obtainStyledAttributes(r5, sAccessibilityPaneTitle);
        if (r22.hasValue(0) == false) goto L11;
        ViewCompat.setAccessibilityPaneTitle(r4, r22.getString(0));
    L11:
        r22.recycle();
        TypedArray r23 = r3.obtainStyledAttributes(r5, sScreenReaderFocusable);
        if (r23.hasValue(0) == false) goto L14;
        ViewCompat.setScreenReaderFocusable(r4, r23.getBoolean(0, false));
    L14:
        r23.recycle();
    }

    private void checkOnClickListener(View r2, AttributeSet r3) {
        Context r1 = r2.getContext();
        if ((r1 instanceof ContextWrapper) == true) goto L5;
        return;
    L5:
        if (r2.hasOnClickListeners() == false) goto L13;
        TypedArray r12 = r1.obtainStyledAttributes(r3, sOnClickAttrs);
        String r32 = r12.getString(0);
        if (r32 == null) goto L10;
        r2.setOnClickListener(new DeclaredOnClickListener(r2, r32));
    L10:
        r12.recycle();
        return;
    }

    private View createViewByPrefix(Context r3, String r4, String r5) {
        SimpleArrayMap<String, Constructor<? extends View>> r0 = sConstructorMap;
        Constructor<? extends View> r1 = r0.get(r4);
        if (r1 != null) goto L8;
        if (r5 != null) goto L12;
        String r52 = r4;
    L7:
        r1 = Class.forName(r52, false, r3.getClassLoader()).asSubclass(View.class).getConstructor(sConstructorSignature);     // Catch: Exception -> L10
        r0.put(r4, r1);     // Catch: Exception -> L10
        goto L8
    L12:
        r52 = r5 + r4;     // Catch: Exception -> L10
    L10:
        return null;
    L8:
        r1.setAccessible(true);     // Catch: Exception -> L10
        return r1.newInstance(this.mConstructorArgs);
    }

    private View createViewFromTag(Context r6, String r7, AttributeSet r8) {
        if (r7.equals(PluginsConstants.Settings.VIEW) == false) goto L27;
        r7 = r8.getAttributeValue(null, "class");
    L27:
        Object[] r3 = this.mConstructorArgs;     // Catch: Throwable -> L16 Exception -> L25
        r3[0] = r6;     // Catch: Throwable -> L16 Exception -> L25
        r3[1] = r8;     // Catch: Throwable -> L16 Exception -> L25
        if ((-1) != r7.indexOf(46)) goto L20;
        int r82 = 0;
    L9:
        String[] r32 = sClassPrefixList;     // Catch: Throwable -> L16 Exception -> L25
        if (r82 >= r32.length) goto L18;
        View r33 = createViewByPrefix(r6, r7, r32[r82]);     // Catch: Throwable -> L16 Exception -> L25
        if (r33 != null) goto L13;
        r82 = r82 + 1;
        goto L9
    L13:
        Object[] r5 = this.mConstructorArgs;
        r5[0] = null;
        r5[1] = null;
        return r33;
    L18:
        Object[] r52 = this.mConstructorArgs;
        r52[0] = null;
        r52[1] = null;
        return null;
    L20:
        View r62 = createViewByPrefix(r6, r7, null);     // Catch: Throwable -> L16 Exception -> L25
        Object[] r53 = this.mConstructorArgs;
        r53[0] = null;
        r53[1] = null;
        return r62;
    L25:
        Object[] r54 = this.mConstructorArgs;
        r54[0] = null;
        r54[1] = null;
        return null;
    L16:
        th = move-exception;
        Object[] r55 = this.mConstructorArgs;
        r55[0] = null;
        r55[1] = null;
        throw th;
    }

    private static Context themifyContext(Context r2, AttributeSet r3, boolean r4, boolean r5) {
        TypedArray r32 = r2.obtainStyledAttributes(r3, R$styleable.View, 0, 0);
        if (r4 == false) goto L5;
        int r42 = r32.getResourceId(R$styleable.View_android_theme, 0);
    L6:
        if (r5 == false) goto L11;
        if (r42 != 0) goto L11;
        r42 = r32.getResourceId(R$styleable.View_theme, 0);
        if (r42 == 0) goto L11;
        Log.i(LOG_TAG, "app:theme is now deprecated. Please move to using android:theme instead.");
    L11:
        r32.recycle();
        if (r42 != 0) goto L14;
    L19:
        return r2;
    L14:
        if ((r2 instanceof ContextThemeWrapper) == false) goto L18;
        if (((ContextThemeWrapper) r2).getThemeResId() == r42) goto L19;
    L18:
        return new ContextThemeWrapper(r2, r42);
    L5:
        r42 = 0;
        goto L6
    }

    private void verifyNotNull(View r2, String r3) {
        if (r2 == null) goto L5;
        return;
    L5:
        throw new IllegalStateException(getClass().getName() + " asked to inflate view for <" + r3 + ">, but returned null");
    }

    public AppCompatAutoCompleteTextView createAutoCompleteTextView(Context r1, AttributeSet r2) {
        return new AppCompatAutoCompleteTextView(r1, r2);
    }

    public AppCompatButton createButton(Context r1, AttributeSet r2) {
        return new AppCompatButton(r1, r2);
    }

    public AppCompatCheckBox createCheckBox(Context r1, AttributeSet r2) {
        return new AppCompatCheckBox(r1, r2);
    }

    public AppCompatCheckedTextView createCheckedTextView(Context r1, AttributeSet r2) {
        return new AppCompatCheckedTextView(r1, r2);
    }

    public AppCompatEditText createEditText(Context r1, AttributeSet r2) {
        return new AppCompatEditText(r1, r2);
    }

    public AppCompatImageButton createImageButton(Context r1, AttributeSet r2) {
        return new AppCompatImageButton(r1, r2);
    }

    public AppCompatImageView createImageView(Context r1, AttributeSet r2) {
        return new AppCompatImageView(r1, r2);
    }

    public AppCompatMultiAutoCompleteTextView createMultiAutoCompleteTextView(Context r1, AttributeSet r2) {
        return new AppCompatMultiAutoCompleteTextView(r1, r2);
    }

    public AppCompatRadioButton createRadioButton(Context r1, AttributeSet r2) {
        return new AppCompatRadioButton(r1, r2);
    }

    public AppCompatRatingBar createRatingBar(Context r1, AttributeSet r2) {
        return new AppCompatRatingBar(r1, r2);
    }

    public AppCompatSeekBar createSeekBar(Context r1, AttributeSet r2) {
        return new AppCompatSeekBar(r1, r2);
    }

    public AppCompatSpinner createSpinner(Context r1, AttributeSet r2) {
        return new AppCompatSpinner(r1, r2);
    }

    public AppCompatTextView createTextView(Context r1, AttributeSet r2) {
        return new AppCompatTextView(r1, r2);
    }

    public AppCompatToggleButton createToggleButton(Context r1, AttributeSet r2) {
        return new AppCompatToggleButton(r1, r2);
    }

    public View createView(Context r1, String r2, AttributeSet r3) {
        return null;
    }

    public final View createView(View r1, String r2, Context r3, AttributeSet r4, boolean r5, boolean r6, boolean r7, boolean r8) {
        if (r5 == false) goto L5;
        if (r1 == null) goto L5;
        Context r12 = r1.getContext();
    L6:
        if (r6 == true) goto L8;
        if (r7 == true) goto L8;
    L9:
        if (r8 == false) goto L11;
        r12 = TintContextWrapper.wrap(r12);
    L11:
        r2.getClass();
        char r62 = 65535;
        switch(r2.hashCode()) {
            case -1946472170: goto L67;
            case -1455429095: goto L63;
            case -1346021293: goto L59;
            case -938935918: goto L55;
            case -937446323: goto L51;
            case -658531749: goto L47;
            case -339785223: goto L43;
            case 776382189: goto L39;
            case 799298502: goto L35;
            case 1125864064: goto L31;
            case 1413872058: goto L27;
            case 1601505219: goto L23;
            case 1666676343: goto L19;
            case 2001146706: goto L15;
            default: goto L70;
        };
    L70:
        switch(r62) {
            case 0: goto L85;
            case 1: goto L84;
            case 2: goto L83;
            case 3: goto L82;
            case 4: goto L81;
            case 5: goto L80;
            case 6: goto L79;
            case 7: goto L78;
            case 8: goto L77;
            case 9: goto L76;
            case 10: goto L75;
            case 11: goto L74;
            case 12: goto L73;
            case 13: goto L72;
            default: goto L71;
        };
    L71:
        View r52 = createView(r12, r2, r4);
    L86:
        if (r52 != null) goto L89;
        if (r3 == r12) goto L89;
        r52 = createViewFromTag(r12, r2, r4);
    L89:
        if (r52 == null) goto L91;
        checkOnClickListener(r52, r4);
        backportAccessibilityAttributes(r12, r52, r4);
    L91:
        return r52;
    L72:
        r52 = createButton(r12, r4);
        verifyNotNull(r52, r2);
        goto L86
    L73:
        r52 = createEditText(r12, r4);
        verifyNotNull(r52, r2);
        goto L86
    L74:
        r52 = createCheckBox(r12, r4);
        verifyNotNull(r52, r2);
        goto L86
    L75:
        r52 = createAutoCompleteTextView(r12, r4);
        verifyNotNull(r52, r2);
        goto L86
    L76:
        r52 = createImageView(r12, r4);
        verifyNotNull(r52, r2);
        goto L86
    L77:
        r52 = createToggleButton(r12, r4);
        verifyNotNull(r52, r2);
        goto L86
    L78:
        r52 = createRadioButton(r12, r4);
        verifyNotNull(r52, r2);
        goto L86
    L79:
        r52 = createSpinner(r12, r4);
        verifyNotNull(r52, r2);
        goto L86
    L80:
        r52 = createSeekBar(r12, r4);
        verifyNotNull(r52, r2);
        goto L86
    L81:
        r52 = createImageButton(r12, r4);
        verifyNotNull(r52, r2);
        goto L86
    L82:
        r52 = createTextView(r12, r4);
        verifyNotNull(r52, r2);
        goto L86
    L83:
        r52 = createMultiAutoCompleteTextView(r12, r4);
        verifyNotNull(r52, r2);
        goto L86
    L84:
        r52 = createCheckedTextView(r12, r4);
        verifyNotNull(r52, r2);
        goto L86
    L85:
        r52 = createRatingBar(r12, r4);
        verifyNotNull(r52, r2);
        goto L86
    L15:
        if (r2.equals("Button") == false) goto L70;
        r62 = '\r';
        goto L70
    L19:
        if (r2.equals("EditText") == false) goto L70;
        r62 = '\f';
        goto L70
    L23:
        if (r2.equals("CheckBox") == false) goto L70;
        r62 = 11;
        goto L70
    L27:
        if (r2.equals("AutoCompleteTextView") == false) goto L70;
        r62 = '\n';
        goto L70
    L31:
        if (r2.equals("ImageView") == false) goto L70;
        r62 = '\t';
        goto L70
    L35:
        if (r2.equals("ToggleButton") == false) goto L70;
        r62 = '\b';
        goto L70
    L39:
        if (r2.equals("RadioButton") == false) goto L70;
        r62 = 7;
        goto L70
    L43:
        if (r2.equals("Spinner") == false) goto L70;
        r62 = 6;
        goto L70
    L47:
        if (r2.equals("SeekBar") == false) goto L70;
        r62 = 5;
        goto L70
    L51:
        if (r2.equals("ImageButton") == false) goto L70;
        r62 = 4;
        goto L70
    L55:
        if (r2.equals("TextView") == false) goto L70;
        r62 = 3;
        goto L70
    L59:
        if (r2.equals("MultiAutoCompleteTextView") == false) goto L70;
        r62 = 2;
        goto L70
    L63:
        if (r2.equals("CheckedTextView") == false) goto L70;
        r62 = 1;
        goto L70
    L67:
        if (r2.equals("RatingBar") == false) goto L70;
        r62 = 0;
    L8:
        r12 = themifyContext(r12, r4, r6, r7);
    L5:
        r12 = r3;
        goto L6
    }
}

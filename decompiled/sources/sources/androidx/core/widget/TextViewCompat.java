package androidx.core.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.icu.text.DecimalFormatSymbols;
import android.os.Build;
import android.text.Editable;
import android.text.PrecomputedText;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.PasswordTransformationMethod;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.core.text.PrecomputedTextCompat;
import androidx.core.util.Preconditions;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes3.dex */
public abstract class TextViewCompat {

    public static class Api24Impl {
        public static DecimalFormatSymbols getInstance(Locale r0) {
            return DecimalFormatSymbols.getInstance(r0);
        }
    }

    public static class Api28Impl {
        public static String[] getDigitStrings(DecimalFormatSymbols r0) {
            return r0.getDigitStrings();
        }

        public static PrecomputedText.Params getTextMetricsParams(TextView r0) {
            return r0.getTextMetricsParams();
        }

        public static void setFirstBaselineToTopHeight(TextView r0, int r1) {
            r0.setFirstBaselineToTopHeight(r1);
        }
    }

    public static class Api34Impl {
        public static void setLineHeight(TextView r0, int r1, float r2) {
            r0.setLineHeight(r1, r2);
        }
    }

    public static class OreoCallback implements ActionMode.Callback {
        private final ActionMode.Callback mCallback;
        private boolean mCanUseMenuBuilderReferences;
        private boolean mInitializedMenuBuilderReferences;
        private Class<?> mMenuBuilderClass;
        private Method mMenuBuilderRemoveItemAtMethod;
        private final TextView mTextView;

        public OreoCallback(ActionMode.Callback r1, TextView r2) {
            this.mCallback = r1;
            this.mTextView = r2;
            this.mInitializedMenuBuilderReferences = false;
        }

        private Intent createProcessTextIntent() {
            return new Intent().setAction("android.intent.action.PROCESS_TEXT").setType("text/plain");
        }

        private Intent createProcessTextIntentForResolveInfo(ResolveInfo r2, TextView r3) {
            Intent r1 = createProcessTextIntent().putExtra("android.intent.extra.PROCESS_TEXT_READONLY", !isEditable(r3));
            ActivityInfo r22 = r2.activityInfo;
            return r1.setClassName(r22.packageName, r22.name);
        }

        private List<ResolveInfo> getSupportedActivities(Context r4, PackageManager r5) {
            ArrayList r0 = new ArrayList();
            if ((r4 instanceof Activity) == false) goto L11;
            Iterator<ResolveInfo> r52 = r5.queryIntentActivities(createProcessTextIntent(), 0).iterator();
        L7:
            if (r52.hasNext() == false) goto L11;
            ResolveInfo r1 = r52.next();
            if (isSupportedActivity(r1, r4) == false) goto L7;
            r0.add(r1);
        L11:
            return r0;
        }

        private boolean isEditable(TextView r1) {
            if ((r1 instanceof Editable) == true) goto L5;
            return false;
        L5:
            if (r1.onCheckIsTextEditor() == true) goto L7;
            return false;
        L7:
            if (r1.isEnabled() == false) goto L13;
            return true;
        L13:
            return false;
        }

        private boolean isSupportedActivity(ResolveInfo r3, Context r4) {
            if (r4.getPackageName().equals(r3.activityInfo.packageName) == false) goto L5;
            return true;
        L5:
            ActivityInfo r2 = r3.activityInfo;
            if (r2.exported == true) goto L8;
            return false;
        L8:
            String r22 = r2.permission;
            if (r22 != null) goto L11;
        L14:
            return true;
        L11:
            if (r4.checkSelfPermission(r22) == 0) goto L14;
            return false;
        }

        private void recomputeProcessTextMenuItems(Menu r9) {
            Context r0 = this.mTextView.getContext();
            PackageManager r1 = r0.getPackageManager();
            boolean r2 = this.mInitializedMenuBuilderReferences;
            Class r3 = Integer.TYPE;
            if (r2 == true) goto L28;
            this.mInitializedMenuBuilderReferences = true;
            Class<?> r22 = Class.forName("com.android.internal.view.menu.MenuBuilder");     // Catch: Throwable -> L7
            this.mMenuBuilderClass = r22;     // Catch: Throwable -> L7
            this.mMenuBuilderRemoveItemAtMethod = r22.getDeclaredMethod("removeItemAt", new Class[]{r3});     // Catch: Throwable -> L7
            this.mCanUseMenuBuilderReferences = true;     // Catch: Throwable -> L7
        L7:
            this.mMenuBuilderClass = null;
            this.mMenuBuilderRemoveItemAtMethod = null;
            this.mCanUseMenuBuilderReferences = false;
        L28:
            if (this.mCanUseMenuBuilderReferences == true) goto L11;
        L13:
            Method r23 = r9.getClass().getDeclaredMethod("removeItemAt", new Class[]{r3});     // Catch: Throwable -> L27
        L14:
            int r32 = r9.size() - 1;     // Catch: Throwable -> L27
        L15:
            if (r32 < 0) goto L22;
            MenuItem r4 = r9.getItem(r32);     // Catch: Throwable -> L27
            if (r4.getIntent() == null) goto L21;
            if ("android.intent.action.PROCESS_TEXT".equals(r4.getIntent().getAction()) == false) goto L21;
            r23.invoke(r9, new Object[]{Integer.valueOf(r32)});     // Catch: Throwable -> L27
        L21:
            r32 = r32 - 1;
            goto L15
        L22:
            List<ResolveInfo> r02 = getSupportedActivities(r0, r1);
            int r24 = 0;
        L24:
            if (r24 >= r02.size()) goto L37;
            ResolveInfo r33 = r02.get(r24);
            r9.add(0, 0, r24 + 100, r33.loadLabel(r1)).setIntent(createProcessTextIntentForResolveInfo(r33, this.mTextView)).setShowAsAction(1);
            r24 = r24 + 1;
            goto L24
        L37:
            return;
        L11:
            if (this.mMenuBuilderClass.isInstance(r9) == false) goto L13;
            r23 = this.mMenuBuilderRemoveItemAtMethod;     // Catch: Throwable -> L27
        }

        public ActionMode.Callback getWrappedCallback() {
            return this.mCallback;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode r1, MenuItem r2) {
            return this.mCallback.onActionItemClicked(r1, r2);
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode r1, Menu r2) {
            return this.mCallback.onCreateActionMode(r1, r2);
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode r1) {
            this.mCallback.onDestroyActionMode(r1);
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode r1, Menu r2) {
            recomputeProcessTextMenuItems(r2);
            return this.mCallback.onPrepareActionMode(r1, r2);
        }
    }

    public static int getFirstBaselineToTopHeight(TextView r1) {
        return r1.getPaddingTop() - r1.getPaint().getFontMetricsInt().top;
    }

    public static int getLastBaselineToBottomHeight(TextView r1) {
        return r1.getPaddingBottom() + r1.getPaint().getFontMetricsInt().bottom;
    }

    private static int getTextDirection(TextDirectionHeuristic r4) {
        TextDirectionHeuristic r0 = TextDirectionHeuristics.FIRSTSTRONG_RTL;
        if (r4 != r0) goto L5;
        return 1;
    L5:
        TextDirectionHeuristic r2 = TextDirectionHeuristics.FIRSTSTRONG_LTR;
        if (r4 != r2) goto L9;
        return 1;
    L9:
        if (r4 != TextDirectionHeuristics.ANYRTL_LTR) goto L13;
        return 2;
    L13:
        if (r4 != TextDirectionHeuristics.LTR) goto L17;
        return 3;
    L17:
        if (r4 != TextDirectionHeuristics.RTL) goto L21;
        return 4;
    L21:
        if (r4 != TextDirectionHeuristics.LOCALE) goto L24;
        return 5;
    L24:
        if (r4 != r2) goto L27;
        return 6;
    L27:
        if (r4 != r0) goto L30;
        return 7;
    L30:
        return 1;
    }

    private static TextDirectionHeuristic getTextDirectionHeuristic(TextView r4) {
        if ((r4.getTransformationMethod() instanceof PasswordTransformationMethod) == true) goto L5;
        boolean r2 = true;
        if (Build.VERSION.SDK_INT < 28) goto L20;
        if ((r4.getInputType() & 15) != 3) goto L20;
        byte r42 = Character.getDirectionality(Api28Impl.getDigitStrings(Api24Impl.getInstance(r4.getTextLocale()))[0].codePointAt(0));
        if (r42 == 1) goto L18;
        if (r42 == 2) goto L18;
        return TextDirectionHeuristics.LTR;
    L18:
        return TextDirectionHeuristics.RTL;
    L20:
        if (r4.getLayoutDirection() == 1) goto L24;
        r2 = false;
    L24:
        switch(r4.getTextDirection()) {
            case 2: goto L41;
            case 3: goto L39;
            case 4: goto L37;
            case 5: goto L35;
            case 6: goto L33;
            case 7: goto L31;
            default: goto L25;
        };
    L25:
        if (r2 == false) goto L29;
        return TextDirectionHeuristics.FIRSTSTRONG_RTL;
    L29:
        return TextDirectionHeuristics.FIRSTSTRONG_LTR;
    L31:
        return TextDirectionHeuristics.FIRSTSTRONG_RTL;
    L33:
        return TextDirectionHeuristics.FIRSTSTRONG_LTR;
    L35:
        return TextDirectionHeuristics.LOCALE;
    L37:
        return TextDirectionHeuristics.RTL;
    L39:
        return TextDirectionHeuristics.LTR;
    L41:
        return TextDirectionHeuristics.ANYRTL_LTR;
    L5:
        return TextDirectionHeuristics.LTR;
    }

    public static PrecomputedTextCompat.Params getTextMetricsParams(TextView r3) {
        if (Build.VERSION.SDK_INT >= 28) goto L5;
        PrecomputedTextCompat.Params.Builder r0 = new PrecomputedTextCompat.Params.Builder(new TextPaint(r3.getPaint()));
        r0.setBreakStrategy(r3.getBreakStrategy());
        r0.setHyphenationFrequency(r3.getHyphenationFrequency());
        r0.setTextDirection(getTextDirectionHeuristic(r3));
        return r0.build();
    L5:
        return new PrecomputedTextCompat.Params(Api28Impl.getTextMetricsParams(r3));
    }

    public static void setCompoundDrawableTintList(TextView r0, ColorStateList r1) {
        Preconditions.checkNotNull(r0);
        r0.setCompoundDrawableTintList(r1);
    }

    public static void setCompoundDrawableTintMode(TextView r0, PorterDuff.Mode r1) {
        Preconditions.checkNotNull(r0);
        r0.setCompoundDrawableTintMode(r1);
    }

    public static void setFirstBaselineToTopHeight(TextView r3, int r4) {
        Preconditions.checkArgumentNonnegative(r4);
        if (Build.VERSION.SDK_INT < 28) goto L6;
        Api28Impl.setFirstBaselineToTopHeight(r3, r4);
        return;
    L6:
        Paint.FontMetricsInt r0 = r3.getPaint().getFontMetricsInt();
        if (r3.getIncludeFontPadding() == false) goto L9;
        int r02 = r0.top;
    L11:
        if (r4 <= Math.abs(r02)) goto L14;
        int r42 = r4 + r02;
        r3.setPadding(r3.getPaddingLeft(), r42, r3.getPaddingRight(), r3.getPaddingBottom());
        return;
    L14:
        return;
    L9:
        r02 = r0.ascent;
        goto L11
    }

    public static void setLastBaselineToBottomHeight(TextView r3, int r4) {
        Preconditions.checkArgumentNonnegative(r4);
        Paint.FontMetricsInt r0 = r3.getPaint().getFontMetricsInt();
        if (r3.getIncludeFontPadding() == false) goto L5;
        int r02 = r0.bottom;
    L7:
        if (r4 <= Math.abs(r02)) goto L10;
        int r42 = r4 - r02;
        r3.setPadding(r3.getPaddingLeft(), r3.getPaddingTop(), r3.getPaddingRight(), r42);
        return;
    L10:
        return;
    L5:
        r02 = r0.descent;
        goto L7
    }

    public static void setLineHeight(TextView r2, int r3) {
        Preconditions.checkArgumentNonnegative(r3);
        if (r3 == r2.getPaint().getFontMetricsInt(null)) goto L6;
        r2.setLineSpacing(r3 - r0, 1.0f);
        return;
    }

    public static void setLineHeight(TextView r2, int r3, float r4) {
        if (Build.VERSION.SDK_INT < 34) goto L6;
        Api34Impl.setLineHeight(r2, r3, r4);
        return;
    L6:
        setLineHeight(r2, Math.round(TypedValue.applyDimension(r3, r4, r2.getResources().getDisplayMetrics())));
    }

    public static void setPrecomputedText(TextView r2, PrecomputedTextCompat r3) {
        if (Build.VERSION.SDK_INT < 29) goto L5;
        throw null;
    L5:
        getTextMetricsParams(r2);
        throw null;
    }

    public static void setTextAppearance(TextView r0, int r1) {
        r0.setTextAppearance(r1);
    }

    public static void setTextMetricsParams(TextView r2, PrecomputedTextCompat.Params r3) {
        r2.setTextDirection(getTextDirection(r3.getTextDirection()));
        r2.getPaint().set(r3.getTextPaint());
        r2.setBreakStrategy(r3.getBreakStrategy());
        r2.setHyphenationFrequency(r3.getHyphenationFrequency());
    }

    public static ActionMode.Callback unwrapCustomSelectionActionModeCallback(ActionMode.Callback r2) {
        if ((r2 instanceof OreoCallback) == true) goto L5;
        return r2;
    L5:
        if (Build.VERSION.SDK_INT >= 26) goto L7;
        return r2;
    L7:
        return ((OreoCallback) r2).getWrappedCallback();
    }

    public static ActionMode.Callback wrapCustomSelectionActionModeCallback(TextView r2, ActionMode.Callback r3) {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 >= 26) goto L5;
    L12:
        return r3;
    L5:
        if (r0 > 27) goto L12;
        if ((r3 instanceof OreoCallback) == true) goto L12;
        if (r3 == null) goto L12;
        return new OreoCallback(r3, r2);
    }
}

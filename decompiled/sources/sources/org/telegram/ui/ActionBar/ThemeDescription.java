package org.telegram.ui.ActionBar;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.text.SpannedString;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedArrowDrawable;
import org.telegram.ui.Components.AudioPlayerAlert;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.ChatBigEmptyView;
import org.telegram.ui.Components.CheckBox;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.ContextProgressView;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.EditTextCaption;
import org.telegram.ui.Components.EditTextEmoji;
import org.telegram.ui.Components.EmptyTextProgressView;
import org.telegram.ui.Components.GroupCreateCheckBox;
import org.telegram.ui.Components.GroupCreateSpan;
import org.telegram.ui.Components.LetterDrawable;
import org.telegram.ui.Components.LineProgressView;
import org.telegram.ui.Components.MessageBackgroundDrawable;
import org.telegram.ui.Components.NumberTextView;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.RadialProgressView;
import org.telegram.ui.Components.RadioButton;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScamDrawable;
import org.telegram.ui.Components.SeekBarView;
import org.telegram.ui.Components.TypefaceSpan;

/* loaded from: classes6.dex */
public class ThemeDescription {
    public static int FLAG_AB_AM_BACKGROUND = 1048576;
    public static int FLAG_AB_AM_ITEMSCOLOR = 512;
    public static int FLAG_AB_AM_SELECTORCOLOR = 4194304;
    public static int FLAG_AB_AM_TOPBACKGROUND = 2097152;
    public static int FLAG_AB_ITEMSCOLOR = 64;
    public static int FLAG_AB_SEARCH = 134217728;
    public static int FLAG_AB_SEARCHPLACEHOLDER = 67108864;
    public static int FLAG_AB_SELECTORCOLOR = 256;
    public static int FLAG_AB_SUBMENUBACKGROUND = Integer.MIN_VALUE;
    public static int FLAG_AB_SUBMENUITEM = 1073741824;
    public static int FLAG_AB_SUBTITLECOLOR = 1024;
    public static int FLAG_AB_TITLECOLOR = 128;
    public static int FLAG_BACKGROUND = 1;
    public static int FLAG_BACKGROUNDFILTER = 32;
    public static int FLAG_CELLBACKGROUNDCOLOR = 16;
    public static int FLAG_CHECKBOX = 8192;
    public static int FLAG_CHECKBOXCHECK = 16384;
    public static int FLAG_CHECKTAG = 262144;
    public static int FLAG_CURSORCOLOR = 16777216;
    public static int FLAG_DRAWABLESELECTEDSTATE = 65536;
    public static int FLAG_FASTSCROLL = 33554432;
    public static int FLAG_HINTTEXTCOLOR = 8388608;
    public static int FLAG_IMAGECOLOR = 8;
    public static int FLAG_LINKCOLOR = 2;
    public static int FLAG_LISTGLOWCOLOR = 32768;
    public static int FLAG_PROGRESSBAR = 2048;
    public static int FLAG_SECTIONS = 524288;
    public static int FLAG_SELECTOR = 4096;
    public static int FLAG_SELECTORWHITE = 268435456;
    public static int FLAG_SERVICEBACKGROUND = 536870912;
    public static int FLAG_TEXTCOLOR = 4;
    public static int FLAG_USEBACKGROUNDDRAWABLE = 131072;
    private int alphaOverride;
    private HashMap<String, Field> cachedFields;
    private int changeFlags;
    private int currentColor;
    private int currentKey;
    private ThemeDescriptionDelegate delegate;
    private Drawable[] drawablesToUpdate;
    private Class[] listClasses;
    private String[] listClassesFieldName;
    private String lottieLayerName;
    private HashMap<String, Boolean> notFoundCachedFields;
    private Paint[] paintToUpdate;
    private int previousColor;
    private boolean[] previousIsDefault;
    public Theme.ResourcesProvider resourcesProvider;
    private View viewToInvalidate;

    public interface ThemeDescriptionDelegate {
        void didSetColor();

        default void onAnimationProgress(float r1) {
        }
    }

    static {
    }

    public ThemeDescription(View r2, int r3, Class[] r4, Paint r5, Drawable[] r6, ThemeDescriptionDelegate r7, int r8) {
        this.alphaOverride = -1;
        this.previousIsDefault = new boolean[1];
        this.currentKey = r8;
        if (r5 == null) goto L5;
        this.paintToUpdate = new Paint[]{r5};
    L5:
        this.drawablesToUpdate = r6;
        this.viewToInvalidate = r2;
        this.changeFlags = r3;
        this.listClasses = RecyclerListView.filterThemeDescription(r3, r4);
        this.delegate = r7;
        View r22 = this.viewToInvalidate;
        if ((r22 instanceof EditTextEmoji) == false) goto L9;
        this.viewToInvalidate = ((EditTextEmoji) r22).getEditText();
        return;
    }

    public ThemeDescription(View r1, int r2, Class[] r3, Paint[] r4, Drawable[] r5, ThemeDescriptionDelegate r6, int r7, Object r8) {
        this.alphaOverride = -1;
        this.previousIsDefault = new boolean[1];
        this.currentKey = r7;
        this.paintToUpdate = r4;
        this.drawablesToUpdate = r5;
        this.viewToInvalidate = r1;
        this.changeFlags = r2;
        this.listClasses = r3;
        this.delegate = r6;
        if ((r1 instanceof EditTextEmoji) == false) goto L6;
        this.viewToInvalidate = ((EditTextEmoji) r1).getEditText();
        return;
    }

    public ThemeDescription(View r2, int r3, Class[] r4, String[] r5, String r6, int r7) {
        this.alphaOverride = -1;
        this.previousIsDefault = new boolean[1];
        this.currentKey = r7;
        this.lottieLayerName = r6;
        this.viewToInvalidate = r2;
        this.changeFlags = r3;
        this.listClasses = r4;
        this.listClassesFieldName = r5;
        this.cachedFields = new HashMap();
        this.notFoundCachedFields = new HashMap();
        View r22 = this.viewToInvalidate;
        if ((r22 instanceof EditTextEmoji) == false) goto L6;
        this.viewToInvalidate = ((EditTextEmoji) r22).getEditText();
        return;
    }

    public ThemeDescription(View r2, int r3, Class[] r4, String[] r5, Paint[] r6, Drawable[] r7, int r8, ThemeDescriptionDelegate r9, int r10) {
        this.previousIsDefault = new boolean[1];
        this.currentKey = r10;
        this.paintToUpdate = r6;
        this.drawablesToUpdate = r7;
        this.viewToInvalidate = r2;
        this.changeFlags = r3;
        this.listClasses = r4;
        this.listClassesFieldName = r5;
        this.alphaOverride = r8;
        this.delegate = r9;
        this.cachedFields = new HashMap();
        this.notFoundCachedFields = new HashMap();
        View r22 = this.viewToInvalidate;
        if ((r22 instanceof EditTextEmoji) == false) goto L6;
        this.viewToInvalidate = ((EditTextEmoji) r22).getEditText();
        return;
    }

    public ThemeDescription(View r11, int r12, Class[] r13, String[] r14, Paint[] r15, Drawable[] r16, ThemeDescriptionDelegate r17, int r18) {
        this(r11, r12, r13, r14, r15, r16, -1, r17, r18);
    }

    public ThemeDescription(View r2, int r3, Class[] r4, RLottieDrawable[] r5, String r6, int r7) {
        this.alphaOverride = -1;
        this.previousIsDefault = new boolean[1];
        this.currentKey = r7;
        this.lottieLayerName = r6;
        this.drawablesToUpdate = r5;
        this.viewToInvalidate = r2;
        this.changeFlags = r3;
        this.listClasses = r4;
        if ((r2 instanceof EditTextEmoji) == false) goto L6;
        this.viewToInvalidate = ((EditTextEmoji) r2).getEditText();
        return;
    }

    private void applyClippingSwitcherColor(Object r7, int r8) {
        int r0 = this.changeFlags;
        if ((FLAG_FASTSCROLL & r0) == 0) goto L23;
        int r6 = 0;
    L5:
        if (r6 >= 2) goto L60;
        AudioPlayerAlert.ClippingTextViewSwitcher r02 = (AudioPlayerAlert.ClippingTextViewSwitcher) r7;
        if (r6 != 0) goto L9;
        TextView r03 = r02.getTextView();
    L10:
        if (r03 == null) goto L21;
        CharSequence r04 = r03.getText();
        if ((r04 instanceof SpannedString) == false) goto L21;
        TypefaceSpan[] r05 = (TypefaceSpan[]) ((SpannedString) r04).getSpans(0, r04.length(), TypefaceSpan.class);
        if (r05 == null) goto L21;
        if (r05.length <= 0) goto L21;
        int r1 = 0;
    L19:
        if (r1 >= r05.length) goto L21;
        r05[r1].setColor(r8);
        r1 = r1 + 1;
    L21:
        r6 = r6 + 1;
        goto L5
    L9:
        r03 = r02.getNextTextView();
        goto L10
    L60:
        return;
    L23:
        if ((FLAG_TEXTCOLOR & r0) != 0) goto L25;
        return;
    L25:
        if ((r0 & FLAG_CHECKTAG) != 0) goto L27;
    L28:
        int r62 = 0;
    L29:
        if (r62 >= 2) goto L61;
        AudioPlayerAlert.ClippingTextViewSwitcher r06 = (AudioPlayerAlert.ClippingTextViewSwitcher) r7;
        if (r62 != 0) goto L33;
        TextView r07 = r06.getTextView();
    L34:
        if (r07 == null) goto L45;
        r07.setTextColor(r8);
        CharSequence r08 = r07.getText();
        if ((r08 instanceof SpannedString) == false) goto L45;
        TypefaceSpan[] r09 = (TypefaceSpan[]) ((SpannedString) r08).getSpans(0, r08.length(), TypefaceSpan.class);
        if (r09 == null) goto L45;
        if (r09.length <= 0) goto L45;
        int r12 = 0;
    L43:
        if (r12 >= r09.length) goto L45;
        r09[r12].setColor(r8);
        r12 = r12 + 1;
    L45:
        r62 = r62 + 1;
        goto L29
    L33:
        r07 = r06.getNextTextView();
        goto L34
    L61:
        return;
    L27:
        if (checkTag(this.currentKey, (View) r7) == true) goto L28;
    }

    private void applyDirectViewColor(View r5, int r6) {
        int r1 = 0;
        boolean r12 = false;
        if (this.listClassesFieldName == null) goto L5;
    L30:
        int r4 = this.changeFlags;
        if ((FLAG_CELLBACKGROUNDCOLOR & r4) == 0) goto L35;
        r5.setBackgroundColor(r6);
        return;
    L35:
        if ((FLAG_TEXTCOLOR & r4) == 0) goto L52;
        if ((r5 instanceof TextView) == false) goto L41;
        ((TextView) r5).setTextColor(r6);
        return;
    L41:
        if ((r5 instanceof AudioPlayerAlert.ClippingTextViewSwitcher) == true) goto L43;
        return;
    L43:
        if (r1 >= 2) goto L72;
        AudioPlayerAlert.ClippingTextViewSwitcher r42 = (AudioPlayerAlert.ClippingTextViewSwitcher) r5;
        if (r1 != 0) goto L47;
        TextView r43 = r42.getTextView();
    L48:
        if (r43 == null) goto L50;
        r43.setTextColor(r6);
    L50:
        r1 = r1 + 1;
        goto L43
    L47:
        r43 = r42.getNextTextView();
        goto L48
    L72:
        return;
    L52:
        if ((FLAG_SERVICEBACKGROUND & r4) == 0) goto L55;
        return;
    L55:
        if ((FLAG_SELECTOR & r4) == 0) goto L59;
        r5.setBackgroundDrawable(Theme.getSelectorDrawable(false));
        return;
    L59:
        if ((r4 & FLAG_SELECTORWHITE) == 0) goto L70;
        r5.setBackgroundDrawable(Theme.getSelectorDrawable(true));
        return;
    L70:
        return;
    L5:
        if ((this.changeFlags & FLAG_BACKGROUNDFILTER) == 0) goto L30;
        Drawable r52 = r5.getBackground();
        if (r52 == null) goto L65;
        int r44 = this.changeFlags;
        if ((FLAG_CELLBACKGROUNDCOLOR & r44) == 0) goto L17;
        if ((r52 instanceof CombinedDrawable) == false) goto L66;
        Drawable r45 = ((CombinedDrawable) r52).getBackground();
        if ((r45 instanceof ColorDrawable) == false) goto L67;
        ((ColorDrawable) r45).setColor(r6);
        return;
    L67:
        return;
    L66:
        return;
    L17:
        if ((r52 instanceof CombinedDrawable) == false) goto L20;
        r52 = ((CombinedDrawable) r52).getIcon();
    L27:
        if (r52 == null) goto L68;
        r52.setColorFilter(new PorterDuffColorFilter(r6, PorterDuff.Mode.MULTIPLY));
        return;
    L68:
        return;
    L20:
        if ((r52 instanceof StateListDrawable) == true) goto L24;
        if ((r52 instanceof RippleDrawable) == false) goto L27;
    L24:
        if ((r44 & FLAG_DRAWABLESELECTEDSTATE) == 0) goto L26;
        r12 = true;
    L26:
        Theme.setSelectorDrawableColor(r52, r6, r12);
        goto L27
    }

    private void applyDrawableColor(Object r2, int r3) {
        if ((r2 instanceof LetterDrawable) == false) goto L11;
        if ((this.changeFlags & FLAG_BACKGROUNDFILTER) == 0) goto L8;
        ((LetterDrawable) r2).setBackgroundColor(r3);
        return;
    L8:
        ((LetterDrawable) r2).setColor(r3);
        return;
    L11:
        if ((r2 instanceof CombinedDrawable) == false) goto L20;
        if ((this.changeFlags & FLAG_BACKGROUNDFILTER) == 0) goto L15;
        Drawable r1 = ((CombinedDrawable) r2).getBackground();
    L16:
        if (r1 == null) goto L36;
        r1.setColorFilter(new PorterDuffColorFilter(r3, PorterDuff.Mode.MULTIPLY));
        return;
    L36:
        return;
    L15:
        r1 = ((CombinedDrawable) r2).getIcon();
        goto L16
    L20:
        if ((r2 instanceof StateListDrawable) == false) goto L22;
    L30:
        Drawable r22 = (Drawable) r2;
        if ((this.changeFlags & FLAG_DRAWABLESELECTEDSTATE) == 0) goto L33;
        boolean r12 = true;
    L34:
        Theme.setSelectorDrawableColor(r22, r3, r12);
        return;
    L33:
        r12 = false;
        goto L34
    L22:
        if ((r2 instanceof RippleDrawable) == true) goto L30;
        if ((r2 instanceof GradientDrawable) == false) goto L28;
        ((GradientDrawable) r2).setColor(r3);
        return;
    L28:
        ((Drawable) r2).setColorFilter(new PorterDuffColorFilter(r3, PorterDuff.Mode.MULTIPLY));
    }

    private void applyReflectedFieldColor(View r3, Class<?> r4, int r5, int r6, boolean r7) {
        String[] r0 = this.listClassesFieldName;
        if (r5 >= r0.length) goto L44;
        String r52 = r0[r5];
        if (r52 == null) goto L49;
        String r02 = r4 + "_" + r52;
        HashMap<String, Boolean> r1 = this.notFoundCachedFields;
        if (r1 != null) goto L10;
    L43:
        HashMap<String, Field> r12 = this.cachedFields;     // Catch: Throwable -> L15
        if (r12 == null) goto L17;
        Field r13 = r12.get(r02);     // Catch: Throwable -> L15
    L18:
        if (r13 != null) goto L24;
        r13 = r4.getDeclaredField(r52);     // Catch: Throwable -> L15
        if (r13 == null) goto L24;
        r13.setAccessible(true);     // Catch: Throwable -> L15
        HashMap<String, Field> r42 = this.cachedFields;     // Catch: Throwable -> L15
        if (r42 == null) goto L24;
        r42.put(r02, r13);     // Catch: Throwable -> L15
    L24:
        if (r13 == null) goto L45;
        Object r43 = r13.get(r3);     // Catch: Throwable -> L15
        if (r43 == null) goto L46;
        if (r7 == true) goto L34;
        if ((r43 instanceof View) == false) goto L34;
        if (checkTag(this.currentKey, (View) r43) == true) goto L34;
        return;
    L34:
        if ((r43 instanceof Integer) == false) goto L37;
        r13.set(r3, Integer.valueOf(r6));     // Catch: Throwable -> L15
        return;
    L37:
        applyReflectedObjectColor(r43, r3, r6);     // Catch: Throwable -> L15
        return;
    L46:
        return;
    L45:
        return;
    L17:
        r13 = null;
    L15:
        th = move-exception;
        FileLog.e(th);
        HashMap<String, Boolean> r2 = this.notFoundCachedFields;
        if (r2 == null) goto L48;
        r2.put(r02, Boolean.TRUE);
        return;
    L48:
        return;
    L10:
        if (r1.containsKey(r02) == false) goto L43;
        return;
    L49:
        return;
    }

    private void applyReflectedObjectColor(Object r4, View r5, int r6) {
        boolean r0 = r4 instanceof View;
        if (r0 == false) goto L5;
        ((View) r4).invalidate();
    L5:
        String r1 = this.lottieLayerName;
        if (r1 == null) goto L11;
        if ((r4 instanceof RLottieImageView) == false) goto L11;
        ((RLottieImageView) r4).setLayerColor(r1, r6);
    L11:
        if ((this.changeFlags & FLAG_USEBACKGROUNDDRAWABLE) == 0) goto L14;
        if (r0 == false) goto L14;
        r4 = ((View) r4).getBackground();
    L14:
        int r02 = this.changeFlags;
        Drawable r2 = null;
        if ((FLAG_BACKGROUND & r02) == 0) goto L25;
        if ((r4 instanceof View) == false) goto L25;
        View r42 = (View) r4;
        Drawable r3 = r42.getBackground();
        if ((r3 instanceof MessageBackgroundDrawable) == false) goto L22;
        MessageBackgroundDrawable r32 = (MessageBackgroundDrawable) r3;
        r32.setColor(r6);
        r32.setCustomPaint(null);
        return;
    L22:
        r42.setBackgroundColor(r6);
        return;
    L25:
        if ((r4 instanceof EditTextCaption) == false) goto L37;
        if ((FLAG_HINTTEXTCOLOR & r02) == 0) goto L31;
        EditTextCaption r43 = (EditTextCaption) r4;
        r43.setHintColor(r6);
        r43.setHintTextColor(r6);
        return;
    L31:
        if ((FLAG_CURSORCOLOR & r02) == 0) goto L34;
        ((EditTextCaption) r4).setCursorColor(r6);
        return;
    L34:
        ((EditTextCaption) r4).setTextColor(r6);
        return;
    L37:
        if ((r4 instanceof SimpleTextView) == false) goto L45;
        if ((FLAG_LINKCOLOR & r02) == 0) goto L42;
        ((SimpleTextView) r4).setLinkTextColor(r6);
        return;
    L42:
        ((SimpleTextView) r4).setTextColor(r6);
        return;
    L45:
        if ((r4 instanceof TextView) == false) goto L49;
        applyTextViewColor(r4, r6);
        return;
    L49:
        if ((r4 instanceof ImageView) == false) goto L62;
        ImageView r44 = (ImageView) r4;
        Drawable r52 = r44.getDrawable();
        if ((r52 instanceof CombinedDrawable) == true) goto L53;
        r44.setColorFilter(new PorterDuffColorFilter(r6, PorterDuff.Mode.MULTIPLY));
        return;
    L53:
        if ((this.changeFlags & FLAG_BACKGROUNDFILTER) == 0) goto L55;
        Drawable r33 = ((CombinedDrawable) r52).getBackground();
    L56:
        if (r33 == null) goto L142;
        r33.setColorFilter(new PorterDuffColorFilter(r6, PorterDuff.Mode.MULTIPLY));
        return;
    L142:
        return;
    L55:
        r33 = ((CombinedDrawable) r52).getIcon();
        goto L56
    L62:
        if ((r4 instanceof BackupImageView) == false) goto L79;
        BackupImageView r45 = (BackupImageView) r4;
        if (r45.getImageReceiver() == null) goto L67;
        r2 = r45.getImageReceiver().getStaticThumb();
    L67:
        if ((r2 instanceof CombinedDrawable) == true) goto L69;
        if (r2 == null) goto L144;
        r2.setColorFilter(new PorterDuffColorFilter(r6, PorterDuff.Mode.MULTIPLY));
        return;
    L144:
        return;
    L69:
        if ((this.changeFlags & FLAG_BACKGROUNDFILTER) == 0) goto L71;
        Drawable r34 = ((CombinedDrawable) r2).getBackground();
    L72:
        if (r34 == null) goto L143;
        r34.setColorFilter(new PorterDuffColorFilter(r6, PorterDuff.Mode.MULTIPLY));
        return;
    L143:
        return;
    L71:
        r34 = ((CombinedDrawable) r2).getIcon();
        goto L72
    L79:
        if ((r4 instanceof Drawable) == false) goto L83;
        applyDrawableColor(r4, r6);
        return;
    L83:
        if ((r4 instanceof CheckBox) == false) goto L93;
        if ((FLAG_CHECKBOX & r02) == 0) goto L89;
        ((CheckBox) r4).setBackgroundColor(r6);
        return;
    L89:
        if ((FLAG_CHECKBOXCHECK & r02) == 0) goto L145;
        ((CheckBox) r4).setCheckColor(r6);
        return;
    L145:
        return;
    L93:
        if ((r4 instanceof GroupCreateCheckBox) == false) goto L97;
        ((GroupCreateCheckBox) r4).updateColors();
        return;
    L97:
        if ((r4 instanceof RadioButton) == false) goto L107;
        if ((FLAG_CHECKBOX & r02) == 0) goto L103;
        RadioButton r46 = (RadioButton) r4;
        r46.setBackgroundColor(r6);
        r46.invalidate();
        return;
    L103:
        if ((FLAG_CHECKBOXCHECK & r02) == 0) goto L146;
        RadioButton r47 = (RadioButton) r4;
        r47.setCheckedColor(r6);
        r47.invalidate();
        return;
    L146:
        return;
    L107:
        if ((r4 instanceof TextPaint) == false) goto L115;
        if ((FLAG_LINKCOLOR & r02) == 0) goto L112;
        ((TextPaint) r4).linkColor = r6;
        return;
    L112:
        ((TextPaint) r4).setColor(r6);
        return;
    L115:
        if ((r4 instanceof LineProgressView) == false) goto L123;
        if ((FLAG_PROGRESSBAR & r02) == 0) goto L120;
        ((LineProgressView) r4).setProgressColor(r6);
        return;
    L120:
        ((LineProgressView) r4).setBackColor(r6);
        return;
    L123:
        if ((r4 instanceof RadialProgressView) == false) goto L127;
        ((RadialProgressView) r4).setProgressColor(r6);
        return;
    L127:
        if ((r4 instanceof Paint) == false) goto L131;
        ((Paint) r4).setColor(r6);
        r5.invalidate();
        return;
    L131:
        if ((r4 instanceof SeekBarView) == false) goto L139;
        if ((FLAG_PROGRESSBAR & r02) == 0) goto L136;
        ((SeekBarView) r4).setOuterColor(r6);
        return;
    L136:
        ((SeekBarView) r4).setInnerColor(r6);
        return;
    L139:
        if ((r4 instanceof AudioPlayerAlert.ClippingTextViewSwitcher) == false) goto L147;
        applyClippingSwitcherColor(r4, r6);
        return;
    }

    private void applyTextViewColor(Object r4, int r5) {
        TextView r42 = (TextView) r4;
        int r3 = this.changeFlags;
        int r1 = 0;
        if ((FLAG_IMAGECOLOR & r3) == 0) goto L13;
        Drawable[] r32 = r42.getCompoundDrawables();
        if (r32 != null) goto L7;
        return;
    L7:
        if (r1 >= r32.length) goto L37;
        Drawable r43 = r32[r1];
        if (r43 == null) goto L11;
        r43.setColorFilter(new PorterDuffColorFilter(r5, PorterDuff.Mode.MULTIPLY));
    L11:
        r1 = r1 + 1;
        goto L7
    L37:
        return;
    L13:
        if ((FLAG_LINKCOLOR & r3) == 0) goto L17;
        r42.getPaint().linkColor = r5;
        r42.invalidate();
        return;
    L17:
        if ((r3 & FLAG_FASTSCROLL) == 0) goto L28;
        CharSequence r33 = r42.getText();
        if ((r33 instanceof SpannedString) == false) goto L34;
        TypefaceSpan[] r34 = (TypefaceSpan[]) ((SpannedString) r33).getSpans(0, r33.length(), TypefaceSpan.class);
        if (r34 != null) goto L23;
        return;
    L23:
        if (r34.length > 0) goto L25;
        return;
    L25:
        if (r1 >= r34.length) goto L38;
        r34[r1].setColor(r5);
        r1 = r1 + 1;
        goto L25
    L38:
        return;
    L34:
        return;
    L28:
        r42.setTextColor(r5);
    }

    private boolean checkTag(int r2, View r3) {
        if (r2 < 0) goto L12;
        if (r3 == null) goto L15;
        Object r32 = r3.getTag();
        if ((r32 instanceof Integer) == true) goto L9;
        return false;
    L9:
        if (((Integer) r32).intValue() != r2) goto L14;
        return true;
    L14:
        return false;
    L15:
        return false;
    L12:
        return false;
    }

    private void processViewColor(View r8, int r9) {
        if (r8 != null) goto L4;
        return;
    L4:
        if (this.listClasses == null) goto L34;
        int r4 = 0;
    L7:
        Class<?>[] r1 = this.listClasses;
        if (r4 >= r1.length) goto L35;
        Class<?> r3 = r1[r4];
        if (r3 != null) goto L13;
    L11:
        ThemeDescription r12 = this;
        View r2 = r8;
        int r5 = r9;
    L27:
        r4 = r4 + 1;
        ThemeDescription this = r12;
        r8 = r2;
        r9 = r5;
        goto L7
    L13:
        if (r3.isInstance(r8) == false) goto L11;
        r8.invalidate();
        if ((this.changeFlags & FLAG_CHECKTAG) != 0) goto L17;
    L20:
        r8.invalidate();
        this.applyDirectViewColor(r8, r9);
        boolean r6 = true;
    L22:
        if (this.listClassesFieldName == null) goto L24;
        r12 = this;
        r2 = r8;
        r5 = r9;
        r12.applyReflectedFieldColor(r2, r3, r4, r5, r6);
        goto L27
    L24:
        r12 = this;
        r2 = r8;
        r5 = r9;
        if ((r2 instanceof GroupCreateSpan) == false) goto L27;
        ((GroupCreateSpan) r2).updateColors();
        goto L27
    L17:
        if (this.checkTag(this.currentKey, r8) == true) goto L20;
        r6 = false;
        goto L22
    L35:
        return;
    }

    public int getCurrentColor() {
        return this.currentColor;
    }

    public int getCurrentKey() {
        return this.currentKey;
    }

    public int getSetColor() {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L5;
        Integer r02 = Integer.valueOf(r0.getColor(this.currentKey));
    L6:
        if (r02 == null) goto L10;
        return r02.intValue();
    L10:
        return Theme.getColor(this.currentKey);
    L5:
        r02 = null;
        goto L6
    }

    public String getTitle() {
        return ThemeColors.getStringName(this.currentKey);
    }

    public void setAnimatedColor(int r2) {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L6;
        r0.setAnimatedColor(getCurrentKey(), r2);
        return;
    L6:
        Theme.setAnimatedColor(getCurrentKey(), r2);
    }

    public void setColor(int r2, boolean r3) {
        setColor(r2, r3, true);
    }

    public void setColor(int r6, boolean r7, boolean r8) {
        if (r8 == false) goto L4;
        Theme.setColor(this.currentKey, r6, r7);
    L4:
        this.currentColor = r6;
        int r72 = this.alphaOverride;
        if (r72 <= 0) goto L7;
        r6 = Color.argb(r72, Color.red(r6), Color.green(r6), Color.blue(r6));
    L7:
        int r82 = 0;
        if (this.paintToUpdate == null) goto L20;
        int r73 = 0;
    L10:
        Paint[] r0 = this.paintToUpdate;
        if (r73 >= r0.length) goto L20;
        if ((this.changeFlags & FLAG_LINKCOLOR) == 0) goto L17;
        Paint r1 = r0[r73];
        if ((r1 instanceof TextPaint) == false) goto L17;
        ((TextPaint) r1).linkColor = r6;
    L18:
        r73 = r73 + 1;
    L17:
        r0[r73].setColor(r6);
    L20:
        if (this.drawablesToUpdate == null) goto L52;
        int r74 = 0;
    L22:
        Drawable[] r02 = this.drawablesToUpdate;
        if (r74 >= r02.length) goto L52;
        Drawable r03 = r02[r74];
        if (r03 == null) goto L51;
        if ((r03 instanceof BackDrawable) == false) goto L31;
        ((BackDrawable) r03).setColor(r6);
        goto L51
    L31:
        if ((r03 instanceof ScamDrawable) == false) goto L34;
        ((ScamDrawable) r03).setColor(r6);
        goto L51
    L34:
        if ((r03 instanceof RLottieDrawable) == false) goto L39;
        String r12 = this.lottieLayerName;
        if (r12 == null) goto L51;
        ((RLottieDrawable) r03).setLayerColor(r12, r6);
        goto L51
    L39:
        if ((r03 instanceof CombinedDrawable) == false) goto L45;
        if ((this.changeFlags & FLAG_BACKGROUNDFILTER) == 0) goto L43;
        ((CombinedDrawable) r03).getBackground().setColorFilter(new PorterDuffColorFilter(r6, PorterDuff.Mode.MULTIPLY));
        goto L51
    L43:
        ((CombinedDrawable) r03).getIcon().setColorFilter(new PorterDuffColorFilter(r6, PorterDuff.Mode.MULTIPLY));
        goto L51
    L45:
        if ((r03 instanceof AvatarDrawable) == false) goto L48;
        ((AvatarDrawable) r03).setColor(r6);
        goto L51
    L48:
        if ((r03 instanceof AnimatedArrowDrawable) == false) goto L50;
        ((AnimatedArrowDrawable) r03).setColor(r6);
        goto L51
    L50:
        r03.setColorFilter(new PorterDuffColorFilter(r6, PorterDuff.Mode.MULTIPLY));
    L51:
        r74 = r74 + 1;
    L52:
        View r75 = this.viewToInvalidate;
        if (r75 != null) goto L55;
    L96:
        View r76 = this.viewToInvalidate;
        if ((r76 instanceof ActionBar) == true) goto L99;
    L138:
        View r77 = this.viewToInvalidate;
        if ((r77 instanceof EmptyTextProgressView) == false) goto L146;
        int r13 = this.changeFlags;
        if ((FLAG_TEXTCOLOR & r13) == 0) goto L144;
        ((EmptyTextProgressView) r77).setTextColor(r6);
        goto L146
    L144:
        if ((r13 & FLAG_PROGRESSBAR) == 0) goto L146;
        ((EmptyTextProgressView) r77).setProgressBarColor(r6);
    L146:
        View r78 = this.viewToInvalidate;
        if ((r78 instanceof RadialProgressView) == false) goto L150;
        ((RadialProgressView) r78).setProgressColor(r6);
    L163:
        int r79 = this.changeFlags;
        if ((FLAG_TEXTCOLOR & r79) == 0) goto L182;
        if ((r79 & FLAG_CHECKTAG) != 0) goto L168;
    L169:
        View r710 = this.viewToInvalidate;
        if ((r710 instanceof TextView) == false) goto L173;
        ((TextView) r710).setTextColor(r6);
        goto L182
    L173:
        if ((r710 instanceof NumberTextView) == false) goto L176;
        ((NumberTextView) r710).setTextColor(r6);
        goto L182
    L176:
        if ((r710 instanceof SimpleTextView) == false) goto L179;
        ((SimpleTextView) r710).setTextColor(r6);
        goto L182
    L179:
        if ((r710 instanceof ChatBigEmptyView) == false) goto L182;
        ((ChatBigEmptyView) r710).setTextColor(r6);
        goto L182
    L168:
        if (checkTag(this.currentKey, this.viewToInvalidate) == true) goto L169;
    L182:
        if ((this.changeFlags & FLAG_CURSORCOLOR) == 0) goto L186;
        View r711 = this.viewToInvalidate;
        if ((r711 instanceof EditTextBoldCursor) == false) goto L186;
        ((EditTextBoldCursor) r711).setCursorColor(r6);
    L186:
        int r712 = this.changeFlags;
        if ((FLAG_HINTTEXTCOLOR & r712) == 0) goto L197;
        View r14 = this.viewToInvalidate;
        if ((r14 instanceof EditTextBoldCursor) == false) goto L195;
        if ((r712 & FLAG_PROGRESSBAR) == 0) goto L193;
        ((EditTextBoldCursor) r14).setHeaderHintColor(r6);
        goto L197
    L193:
        ((EditTextBoldCursor) r14).setHintColor(r6);
        goto L197
    L195:
        if ((r14 instanceof EditText) == false) goto L197;
        ((EditText) r14).setHintTextColor(r6);
    L197:
        View r713 = this.viewToInvalidate;
        int r15 = this.changeFlags;
        if ((FLAG_IMAGECOLOR & r15) != 0) goto L200;
    L234:
        View r714 = this.viewToInvalidate;
        if ((r714 instanceof ScrollView) == true) goto L237;
    L239:
        View r715 = this.viewToInvalidate;
        if ((r715 instanceof ViewPager) == true) goto L242;
    L244:
        View r716 = this.viewToInvalidate;
        if ((r716 instanceof RecyclerListView) == false) goto L272;
        RecyclerListView r717 = (RecyclerListView) r716;
        if ((this.changeFlags & FLAG_SELECTOR) == 0) goto L250;
        r717.setListSelectorColor(Integer.valueOf(r6));
    L250:
        if ((this.changeFlags & FLAG_FASTSCROLL) == 0) goto L253;
        r717.updateFastScrollColors();
    L253:
        if ((this.changeFlags & FLAG_LISTGLOWCOLOR) == 0) goto L256;
        r717.setGlowColor(r6);
    L256:
        if ((this.changeFlags & FLAG_SECTIONS) == 0) goto L284;
        ArrayList<View> r04 = r717.getHeaders();
        if (r04 == null) goto L263;
        int r16 = 0;
    L261:
        if (r16 >= r04.size()) goto L263;
        processViewColor(r04.get(r16), r6);
        r16 = r16 + 1;
    L263:
        ArrayList<View> r05 = r717.getHeadersCache();
        if (r05 == null) goto L269;
        int r17 = 0;
    L267:
        if (r17 >= r05.size()) goto L269;
        processViewColor(r05.get(r17), r6);
        r17 = r17 + 1;
    L269:
        View r718 = r717.getPinnedHeader();
        if (r718 == null) goto L284;
        processViewColor(r718, r6);
    L284:
        if (this.listClasses == null) goto L302;
        View r719 = this.viewToInvalidate;
        if ((r719 instanceof RecyclerListView) == false) goto L296;
        RecyclerListView r720 = (RecyclerListView) r719;
        r720.getRecycledViewPool().clear();
        int r06 = r720.getHiddenChildCount();
        int r18 = 0;
    L288:
        if (r18 >= r06) goto L290;
        processViewColor(r720.getHiddenChildAt(r18), r6);
        r18 = r18 + 1;
        goto L288
    L290:
        int r07 = r720.getCachedChildCount();
        int r19 = 0;
    L291:
        if (r19 >= r07) goto L293;
        processViewColor(r720.getCachedChildAt(r19), r6);
        r19 = r19 + 1;
        goto L291
    L293:
        int r08 = r720.getAttachedScrapChildCount();
        int r110 = 0;
    L294:
        if (r110 >= r08) goto L296;
        processViewColor(r720.getAttachedScrapChildAt(r110), r6);
        r110 = r110 + 1;
    L296:
        View r721 = this.viewToInvalidate;
        if ((r721 instanceof ViewGroup) == false) goto L301;
        ViewGroup r722 = (ViewGroup) r721;
        int r09 = r722.getChildCount();
    L299:
        if (r82 >= r09) goto L301;
        processViewColor(r722.getChildAt(r82), r6);
        r82 = r82 + 1;
    L301:
        processViewColor(this.viewToInvalidate, r6);
    L302:
        ThemeDescriptionDelegate r62 = this.delegate;
        if (r62 == null) goto L305;
        r62.didSetColor();
    L305:
        View r5 = this.viewToInvalidate;
        if (r5 == null) goto L332;
        r5.invalidate();
        return;
    L332:
        return;
    L272:
        if (r716 == null) goto L284;
        Class[] r111 = this.listClasses;
        if (r111 != null) goto L276;
    L277:
        int r112 = this.changeFlags;
        if ((FLAG_SELECTOR & r112) == 0) goto L281;
        r716.setBackgroundDrawable(Theme.getSelectorDrawable(false));
        goto L284
    L281:
        if ((r112 & FLAG_SELECTORWHITE) == 0) goto L284;
        r716.setBackgroundDrawable(Theme.getSelectorDrawable(true));
        goto L284
    L276:
        if (r111.length != 0) goto L284;
    L242:
        if ((this.changeFlags & FLAG_LISTGLOWCOLOR) == 0) goto L244;
        AndroidUtilities.setViewPagerEdgeEffectColor((ViewPager) r715, r6);
        goto L244
    L237:
        if ((this.changeFlags & FLAG_LISTGLOWCOLOR) == 0) goto L239;
        AndroidUtilities.setScrollViewEdgeEffectColor((ScrollView) r714, r6);
        goto L239
    L200:
        if ((r15 & FLAG_CHECKTAG) != 0) goto L202;
    L203:
        View r723 = this.viewToInvalidate;
        if ((r723 instanceof ImageView) == false) goto L218;
        if ((this.changeFlags & FLAG_USEBACKGROUNDDRAWABLE) == 0) goto L216;
        Drawable r724 = ((ImageView) r723).getDrawable();
        if ((r724 instanceof StateListDrawable) == true) goto L212;
        if ((r724 instanceof RippleDrawable) == false) goto L234;
    L212:
        if ((this.changeFlags & FLAG_DRAWABLESELECTEDSTATE) == 0) goto L214;
        boolean r113 = true;
    L215:
        Theme.setSelectorDrawableColor(r724, r6, r113);
        goto L234
    L214:
        r113 = false;
        goto L215
    L216:
        ((ImageView) r723).setColorFilter(new PorterDuffColorFilter(r6, PorterDuff.Mode.MULTIPLY));
        goto L234
    L218:
        if ((r723 instanceof BackupImageView) == true) goto L234;
        if ((r723 instanceof SimpleTextView) == false) goto L224;
        ((SimpleTextView) r723).setSideDrawablesColor(r6);
        goto L234
    L224:
        if ((r723 instanceof TextView) == false) goto L234;
        Drawable[] r725 = ((TextView) r723).getCompoundDrawables();
        if (r725 == null) goto L234;
        int r114 = 0;
    L229:
        if (r114 >= r725.length) goto L234;
        Drawable r2 = r725[r114];
        if (r2 == null) goto L233;
        r2.setColorFilter(new PorterDuffColorFilter(r6, PorterDuff.Mode.MULTIPLY));
    L233:
        r114 = r114 + 1;
        goto L229
    L202:
        if (checkTag(this.currentKey, r713) == false) goto L234;
    L150:
        if ((r78 instanceof LineProgressView) == false) goto L156;
        if ((this.changeFlags & FLAG_PROGRESSBAR) == 0) goto L154;
        ((LineProgressView) r78).setProgressColor(r6);
        goto L163
    L154:
        ((LineProgressView) r78).setBackColor(r6);
        goto L163
    L156:
        if ((r78 instanceof ContextProgressView) == false) goto L159;
        ((ContextProgressView) r78).updateColors();
        goto L163
    L159:
        if ((r78 instanceof SeekBarView) == false) goto L163;
        if ((this.changeFlags & FLAG_PROGRESSBAR) == 0) goto L163;
        ((SeekBarView) r78).setOuterColor(r6);
        goto L163
    L99:
        if ((this.changeFlags & FLAG_AB_ITEMSCOLOR) == 0) goto L102;
        ((ActionBar) r76).setItemsColor(r6, false);
    L102:
        if ((this.changeFlags & FLAG_AB_TITLECOLOR) == 0) goto L105;
        ((ActionBar) this.viewToInvalidate).setTitleColor(r6);
    L105:
        if ((this.changeFlags & FLAG_AB_SELECTORCOLOR) == 0) goto L108;
        ((ActionBar) this.viewToInvalidate).setItemsBackgroundColor(r6, false);
    L108:
        if ((this.changeFlags & FLAG_AB_AM_SELECTORCOLOR) == 0) goto L111;
        ((ActionBar) this.viewToInvalidate).setItemsBackgroundColor(r6, true);
    L111:
        if ((this.changeFlags & FLAG_AB_AM_ITEMSCOLOR) == 0) goto L114;
        ((ActionBar) this.viewToInvalidate).setItemsColor(r6, true);
    L114:
        if ((this.changeFlags & FLAG_AB_SUBTITLECOLOR) == 0) goto L117;
        ((ActionBar) this.viewToInvalidate).setSubtitleColor(r6);
    L117:
        if ((this.changeFlags & FLAG_AB_AM_BACKGROUND) == 0) goto L120;
        ((ActionBar) this.viewToInvalidate).setActionModeColor(r6);
    L120:
        if ((this.changeFlags & FLAG_AB_AM_TOPBACKGROUND) == 0) goto L123;
        ((ActionBar) this.viewToInvalidate).setActionModeTopColor(r6);
    L123:
        if ((this.changeFlags & FLAG_AB_SEARCHPLACEHOLDER) == 0) goto L126;
        ((ActionBar) this.viewToInvalidate).setSearchTextColor(r6, true);
    L126:
        if ((this.changeFlags & FLAG_AB_SEARCH) == 0) goto L128;
        ((ActionBar) this.viewToInvalidate).setSearchTextColor(r6, false);
    L128:
        int r726 = this.changeFlags;
        if ((FLAG_AB_SUBMENUITEM & r726) == 0) goto L136;
        ActionBar r115 = (ActionBar) this.viewToInvalidate;
        if ((r726 & FLAG_IMAGECOLOR) == 0) goto L133;
        boolean r727 = true;
    L134:
        r115.setPopupItemsColor(r6, r727, false);
        goto L136
    L133:
        r727 = false;
    L136:
        if ((this.changeFlags & FLAG_AB_SUBMENUBACKGROUND) == 0) goto L138;
        ((ActionBar) this.viewToInvalidate).setPopupBackgroundColor(r6, false);
        goto L138
    L55:
        if (this.listClasses != null) goto L96;
        if (this.listClassesFieldName != null) goto L96;
        if ((this.changeFlags & FLAG_CHECKTAG) == 0) goto L63;
        if (checkTag(this.currentKey, r75) == false) goto L96;
    L63:
        if ((this.changeFlags & FLAG_BACKGROUND) == 0) goto L68;
        Drawable r728 = this.viewToInvalidate.getBackground();
        if ((r728 instanceof MessageBackgroundDrawable) == false) goto L67;
        MessageBackgroundDrawable r729 = (MessageBackgroundDrawable) r728;
        r729.setColor(r6);
        r729.setCustomPaint(null);
        goto L68
    L67:
        this.viewToInvalidate.setBackgroundColor(r6);
    L68:
        int r730 = this.changeFlags;
        if ((FLAG_BACKGROUNDFILTER & r730) == 0) goto L96;
        int r731 = r730 & FLAG_PROGRESSBAR;
        View r116 = this.viewToInvalidate;
        if (r731 != 0) goto L73;
        Drawable r732 = r116.getBackground();
        if ((r732 instanceof CombinedDrawable) == true) goto L78;
    L81:
        if (r732 == null) goto L96;
        if ((r732 instanceof StateListDrawable) == true) goto L92;
        if ((r732 instanceof RippleDrawable) == true) goto L92;
        if ((r732 instanceof ShapeDrawable) == false) goto L90;
        ((ShapeDrawable) r732).getPaint().setColor(r6);
        goto L96
    L90:
        r732.setColorFilter(new PorterDuffColorFilter(r6, PorterDuff.Mode.MULTIPLY));
    L92:
        if ((this.changeFlags & FLAG_DRAWABLESELECTEDSTATE) == 0) goto L94;
        boolean r117 = true;
    L95:
        Theme.setSelectorDrawableColor(r732, r6, r117);
        goto L96
    L94:
        r117 = false;
        goto L95
    L78:
        if ((this.changeFlags & FLAG_DRAWABLESELECTEDSTATE) == 0) goto L80;
        r732 = ((CombinedDrawable) r732).getBackground();
        goto L81
    L80:
        r732 = ((CombinedDrawable) r732).getIcon();
        goto L81
    L73:
        if ((r116 instanceof EditTextBoldCursor) == false) goto L96;
        ((EditTextBoldCursor) r116).setErrorLineColor(r6);
        goto L96
    }

    public void setDefaultColor() {
        setColor(Theme.getDefaultColor(this.currentKey), true);
    }

    public ThemeDescriptionDelegate setDelegateDisabled() {
        ThemeDescriptionDelegate r0 = this.delegate;
        this.delegate = null;
        return r0;
    }

    public void setPreviousColor() {
        setColor(this.previousColor, this.previousIsDefault[0]);
    }

    public void startEditing() {
        int r0 = Theme.getColor(this.currentKey, this.previousIsDefault);
        this.previousColor = r0;
        this.currentColor = r0;
    }
}

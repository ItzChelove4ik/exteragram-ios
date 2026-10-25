package org.telegram.ui.Components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.PopupWindow;
import java.lang.reflect.Field;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.CompoundEmoji;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class EmojiColorPickerWindow extends PopupWindow {
    private static final ViewTreeObserver.OnScrollChangedListener NOP = null;
    private static Field superListenerField;
    private final int emojiSize;
    private boolean isCompound;
    private ViewTreeObserver.OnScrollChangedListener mSuperScrollListener;
    private ViewTreeObserver mViewTreeObserver;
    public EmojiColorPickerView pickerView;

    public static class EmojiColorPickerView extends View {
        private Drawable arrowDrawable;
        private int arrowX;
        private Drawable backgroundDrawable;
        private boolean both;
        private String currentEmoji;
        private long downStart;
        private Drawable[] drawables;
        private final int emojiSize;
        private boolean ignore;
        private boolean isCompound;
        private int[] lastSelection;
        private Utilities.Callback2<Integer, Integer> onSelectionUpdate;
        private RectF rect;
        private Paint rectPaint;
        private Theme.ResourcesProvider resourcesProvider;
        private int[] selection;
        private AnimatedFloat selection1Animated;
        private AnimatedFloat selection2Animated;
        private int touchY;

        public EmojiColorPickerView(Context r5, Theme.ResourcesProvider r6) {
            super(r5);
            if (AndroidUtilities.isTablet() == false) goto L5;
            float r52 = 40.0f;
        L6:
            this.emojiSize = AndroidUtilities.dp(r52);
            this.drawables = new Drawable[11];
            this.selection = new int[]{0, 0};
            this.lastSelection = new int[]{0, 0};
            this.rectPaint = new Paint(1);
            this.rect = new RectF();
            CubicBezierInterpolator r1 = CubicBezierInterpolator.EASE_OUT_QUINT;
            this.selection1Animated = new AnimatedFloat(this, 125, r1);
            this.selection2Animated = new AnimatedFloat(this, 125, r1);
            this.touchY = -1;
            this.both = true;
            this.resourcesProvider = r6;
            this.backgroundDrawable = getResources().getDrawable(R.drawable.stickers_back_all);
            this.arrowDrawable = getResources().getDrawable(R.drawable.stickers_back_arrow);
            updateColors();
            return;
        L5:
            r52 = 32.0f;
            goto L6
        }

        public String getEmoji() {
            return this.currentEmoji;
        }

        public int getSelection(int r1) {
            return this.selection[r1];
        }

        @Override // android.view.View
        public void onDraw(Canvas r21) {
            float r5 = 2.0f;
            int r6 = 0;
            this.backgroundDrawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight() - AndroidUtilities.dp(2.0f));
            this.backgroundDrawable.draw(r21);
            this.arrowDrawable.setBounds(this.arrowX - AndroidUtilities.dp(9.0f), getMeasuredHeight() - AndroidUtilities.dp(6.34f), this.arrowX + AndroidUtilities.dp(9.0f), getMeasuredHeight());
            this.arrowDrawable.draw(r21);
            if (this.currentEmoji != null) goto L5;
            return;
        L5:
            if (this.isCompound == false) goto L18;
            int r2 = 0;
        L8:
            if (r2 >= 2) goto L16;
            if (r2 != 0) goto L11;
            AnimatedFloat r12 = this.selection1Animated;
        L12:
            float r122 = r12.set(this.selection[r2]);
            float r16 = r5;
            float r52 = Math.max(0.0f, Math.min(1.0f, -r122));
            int r11 = AndroidUtilities.lerp(AndroidUtilities.dp(3.0f) + ((this.emojiSize + AndroidUtilities.dp(1.0f)) * r2), (getMeasuredHeight() - this.emojiSize) / 2, r52);
            int r18 = r6;
            int r62 = this.emojiSize;
            this.rect.set((int) ((this.emojiSize * (r122 + 1.0f)) + AndroidUtilities.dp(((Math.max(0.0f, Math.min(1.0f, r14)) * 3.0f) + 5.0f) + (r14 * 4.0f))), r11, r13 + r62, r11 + r62);
            this.rect.inset(AndroidUtilities.dp(-2.0f), AndroidUtilities.dp(r52 * (-2.0f)));
            this.rectPaint.setColor(Theme.multAlpha(Theme.getColor(Theme.key_listSelector, this.resourcesProvider), AndroidUtilities.lerp(1.0f, 0.5f, r52)));
            r21.drawRoundRect(this.rect, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), this.rectPaint);
            int r53 = r18;
        L13:
            if (r53 >= 5) goto L15;
            r53 = r53 + 1;
            int r63 = (r2 * 5) + r53;
            int r112 = (this.emojiSize * r53) + AndroidUtilities.dp((r53 * 4) + 8);
            int r123 = AndroidUtilities.dp(3.0f) + ((this.emojiSize + AndroidUtilities.dp(1.0f)) * r2);
            Drawable r13 = this.drawables[r63];
            int r14 = this.emojiSize;
            r13.setBounds(r112, r123, r112 + r14, r14 + r123);
            this.drawables[r63].draw(r21);
            goto L13
        L15:
            r2 = r2 + 1;
            r5 = r16;
            r6 = r18;
            goto L8
        L11:
            r12 = this.selection2Animated;
            goto L12
        L16:
            float r162 = r5;
            int r182 = r6;
            this.drawables[r182].setBounds(AndroidUtilities.dp(5.0f), (getMeasuredHeight() - this.emojiSize) / 2, AndroidUtilities.dp(5.0f) + this.emojiSize, (getMeasuredHeight() + this.emojiSize) / 2);
            this.drawables[r182].draw(r21);
            r21.drawRect(AndroidUtilities.dp(8.45f) + this.emojiSize, AndroidUtilities.dp(r162), (AndroidUtilities.dp(8.45f) + this.emojiSize) + 1, getMeasuredHeight() - AndroidUtilities.dp(6.0f), Theme.dividerPaint);
            return;
        L18:
            float r22 = this.selection1Animated.set(this.selection[0]);
            int r64 = AndroidUtilities.dp(5.0f);
            float r124 = r64;
            int r132 = this.emojiSize;
            this.rect.set((int) ((this.emojiSize * r22) + AndroidUtilities.dp((r22 * 4.0f) + 5.0f)), r124, r5 + r132, r132 + r64);
            this.rect.inset(AndroidUtilities.dp(-2.0f), AndroidUtilities.dp(-2.0f));
            this.rectPaint.setColor(Theme.getColor(Theme.key_listSelector, this.resourcesProvider));
            r21.drawRoundRect(this.rect, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), this.rectPaint);
            int r54 = 0;
        L20:
            if (r54 >= 6) goto L31;
            Drawable r7 = this.drawables[r54];
            if (r7 == null) goto L24;
            int r8 = (this.emojiSize * r54) + AndroidUtilities.dp((r54 * 4) + 5);
            float r9 = ((1.0f - (Math.min(0.5f, Math.abs(r54 - r22)) * 2.0f)) * 0.1f) + 0.9f;
            r21.save();
            int r133 = this.emojiSize;
            r21.scale(r9, r9, r8 + (r133 / 2.0f), (r133 / 2.0f) + r124);
            int r92 = this.emojiSize;
            r7.setBounds(r8, r64, r8 + r92, r92 + r64);
            r7.draw(r21);
            r21.restore();
        L24:
            r54 = r54 + 1;
            goto L20
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r14) {
            if (this.ignore == false) goto L7;
            this.ignore = false;
            return false;
        L7:
            if (this.isCompound == false) goto L9;
            int r0 = 0;
        L11:
            Drawable[] r2 = this.drawables;
            if (r0 >= r2.length) goto L30;
            if (r2[r0].getBounds().contains((int) r14.getX(), (int) r14.getY()) == true) goto L32;
            int r22 = this.touchY;
            if (r22 == (-1)) goto L29;
            if (r0 == 0) goto L25;
            if (r22 != 0) goto L21;
            if (r0 < 1) goto L21;
            if (r0 <= 5) goto L25;
        L21:
            if (r22 != 1) goto L29;
            if (r0 < 6) goto L29;
            if (r0 > 10) goto L29;
        L25:
            if (((int) r14.getX()) < this.drawables[r0].getBounds().left) goto L29;
            if (((int) r14.getX()) <= this.drawables[r0].getBounds().right) goto L32;
        L29:
            r0 = r0 + 1;
        L32:
            if (r14.getAction() == 0) goto L40;
            if (r14.getAction() == 2) goto L40;
            if (r14.getAction() == 1) goto L40;
            return false;
        L40:
            if (r14.getAction() != 0) goto L46;
            this.touchY = -1;
            this.downStart = System.currentTimeMillis();
            int[] r23 = this.selection;
            if (r23[0] != r23[1]) goto L44;
            boolean r24 = true;
        L45:
            this.both = r24;
            goto L46
        L44:
            r24 = false;
        L46:
            int[] r25 = this.lastSelection;
            int[] r9 = this.selection;
            r25[0] = r9[0];
            r25[1] = r9[1];
            if ((System.currentTimeMillis() - this.downStart) > 300) goto L49;
        L51:
            boolean r26 = false;
        L52:
            if (r0 != 0) goto L54;
            int[] r02 = this.selection;
            r02[0] = -1;
            r02[1] = -1;
        L76:
            int[] r03 = this.lastSelection;
            int r27 = r03[0];
            int[] r3 = this.selection;
            if (r27 == r3[0]) goto L79;
        L80:
            AndroidUtilities.vibrateCursor(this);
            Utilities.Callback2<Integer, Integer> r04 = this.onSelectionUpdate;
            if (r04 == null) goto L83;
            r04.run(Integer.valueOf(this.selection[0]), Integer.valueOf(this.selection[1]));
        L83:
            invalidate();
            if (r14.getAction() != 1) goto L86;
            this.touchY = -1;
        L86:
            return true;
        L79:
            if (r03[1] == r3[1]) goto L83;
        L54:
            if (r0 < 1) goto L65;
            if (r0 > 5) goto L65;
            int r32 = this.touchY;
            if (r32 == (-1)) goto L59;
            if (r32 != 0) goto L65;
        L59:
            this.touchY = 0;
            int[] r33 = this.selection;
            int r05 = r0 - 1;
            r33[0] = r05;
            if (r33[1] != (-1)) goto L62;
        L64:
            r33[1] = r05;
            goto L76
        L62:
            if (this.both == false) goto L76;
            if (r26 == false) goto L76;
        L65:
            if (r0 < 6) goto L76;
            if (r0 > 10) goto L76;
            int r34 = this.touchY;
            if (r34 == (-1)) goto L70;
            if (r34 != 1) goto L76;
        L70:
            this.touchY = 1;
            int[] r35 = this.selection;
            int r06 = r0 - 6;
            r35[1] = r06;
            if (r35[0] != (-1)) goto L73;
        L75:
            r35[0] = r06;
            goto L76
        L73:
            if (this.both == false) goto L76;
            if (r26 == false) goto L76;
        L49:
            if (r14.getAction() != 2) goto L51;
            r26 = true;
            goto L52
        L30:
            r0 = -1;
            goto L32
        L9:
            return super.onTouchEvent(r14);
        }

        public void setArrowX(int r1) {
            this.arrowX = r1;
            invalidate();
        }

        public void setEmoji(boolean r14, String r15) {
            int r7 = 0;
            boolean r72 = false;
            this.isCompound = r14;
            this.currentEmoji = r15;
            if (r14 == false) goto L11;
            this.drawables[0] = CompoundEmoji.getCompoundEmojiDrawable(r15, -1, -1);
            this.drawables[1] = CompoundEmoji.getCompoundEmojiDrawable(this.currentEmoji, 0, -2);
            this.drawables[2] = CompoundEmoji.getCompoundEmojiDrawable(this.currentEmoji, 1, -2);
            this.drawables[3] = CompoundEmoji.getCompoundEmojiDrawable(this.currentEmoji, 2, -2);
            this.drawables[4] = CompoundEmoji.getCompoundEmojiDrawable(this.currentEmoji, 3, -2);
            this.drawables[5] = CompoundEmoji.getCompoundEmojiDrawable(this.currentEmoji, 4, -2);
            this.drawables[6] = CompoundEmoji.getCompoundEmojiDrawable(this.currentEmoji, -2, 0);
            this.drawables[7] = CompoundEmoji.getCompoundEmojiDrawable(this.currentEmoji, -2, 1);
            this.drawables[8] = CompoundEmoji.getCompoundEmojiDrawable(this.currentEmoji, -2, 2);
            this.drawables[9] = CompoundEmoji.getCompoundEmojiDrawable(this.currentEmoji, -2, 3);
            this.drawables[10] = CompoundEmoji.getCompoundEmojiDrawable(this.currentEmoji, -2, 4);
            Pair<Integer, Integer> r142 = CompoundEmoji.isHandshake(r15);
            if (r142 == null) goto L10;
            setSelection(0, ((Integer) r142.first).intValue());
            setSelection(1, ((Integer) r142.second).intValue());
            int[] r143 = this.selection;
            if (r143[0] != r143[1]) goto L9;
            r72 = true;
        L9:
            this.both = r72;
        L10:
            this.ignore = true;
        L16:
            invalidate();
            return;
        L11:
            if (r7 >= 6) goto L16;
            if (r7 == 0) goto L14;
            String r144 = EmojiView.addColorToCode(r15, CompoundEmoji.skinTones.get(r7 - 1));
        L15:
            this.drawables[r7] = Emoji.getEmojiBigDrawable(r144);
            r7 = r7 + 1;
            goto L11
        L14:
            r144 = r15;
            goto L15
        }

        public void setOnSelectionUpdateListener(Utilities.Callback2<Integer, Integer> r1) {
            this.onSelectionUpdate = r1;
        }

        public void setSelection(int r3, int r4) {
            int[] r0 = this.selection;
            if (r0[r3] != r4) goto L5;
            return;
        L5:
            r0[r3] = r4;
            invalidate();
        }

        public void updateColors() {
            Drawable r0 = this.backgroundDrawable;
            int r1 = Theme.key_dialogBackground;
            Theme.setDrawableColor(r0, Theme.getColor(r1, this.resourcesProvider));
            Theme.setDrawableColor(this.arrowDrawable, Theme.getColor(r1, this.resourcesProvider));
            CompoundEmoji.setPlaceholderColor(Theme.getColor(Theme.key_chat_emojiPanelIcon, this.resourcesProvider));
        }
    }

    public static /* synthetic */ boolean $r8$lambda$0sTmzxlF3nfc74WaJ2ohJ1Qbnho(EmojiColorPickerWindow r0, View r1, int r2, KeyEvent r3) {
        return r0.lambda$new$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$vfYy1CNvgCq4_8BXvYGs6QWAT_8() {
    }

    static {
        NOP = new EmojiColorPickerWindow$$ExternalSyntheticLambda0();
    }

    private EmojiColorPickerWindow(EmojiColorPickerView r2) {
        super(r2);
        if (AndroidUtilities.isTablet() == false) goto L5;
        float r0 = 40.0f;
    L6:
        this.emojiSize = AndroidUtilities.dp(r0);
        this.pickerView = r2;
        setOutsideTouchable(true);
        setClippingEnabled(true);
        setInputMethodMode(2);
        setSoftInputMode(0);
        this.pickerView.setFocusableInTouchMode(true);
        this.pickerView.setOnKeyListener(new EmojiColorPickerWindow$$ExternalSyntheticLambda1(this));
        return;
    L5:
        r0 = 32.0f;
        goto L6
    }

    public static EmojiColorPickerWindow create(Context r1, Theme.ResourcesProvider r2) {
        EmojiColorPickerWindow r12 = new EmojiColorPickerWindow(new EmojiColorPickerView(r1, r2));
        r12.init();
        return r12;
    }

    @SuppressLint({"SoonBlockedPrivateApi"})
    private void init() {
        if (superListenerField == null) goto L21;
    L10:
        Field r0 = superListenerField;
        if (r0 == null) goto L23;
        this.mSuperScrollListener = (ViewTreeObserver.OnScrollChangedListener) r0.get(this);     // Catch: Exception -> L14
        superListenerField.set(this, NOP);     // Catch: Exception -> L14
        return;
    L14:
        this.mSuperScrollListener = null;
        return;
    L23:
        return;
    L21:
        Field r02 = PopupWindow.class.getDeclaredField("mOnScrollChangedListener");     // Catch: Exception -> L8
        r02.setAccessible(true);     // Catch: Exception -> L16
    L9:
        superListenerField = r02;
    L8:
        r02 = null;
        goto L9
    }

    private /* synthetic */ boolean lambda$new$1(View r1, int r2, KeyEvent r3) {
        if (r2 == 82) goto L5;
        return false;
    L5:
        if (r3.getRepeatCount() == 0) goto L7;
        return false;
    L7:
        if (r3.getAction() == 1) goto L9;
        return false;
    L9:
        if (isShowing() == false) goto L16;
        dismiss();
        return true;
    L16:
        return false;
    }

    private void registerListener(View r3) {
        if (this.mSuperScrollListener != null) goto L5;
        return;
    L5:
        if (r3.getWindowToken() == null) goto L7;
        ViewTreeObserver r32 = r3.getViewTreeObserver();
    L8:
        ViewTreeObserver r0 = this.mViewTreeObserver;
        if (r32 == r0) goto L19;
        if (r0 != null) goto L12;
    L14:
        this.mViewTreeObserver = r32;
        if (r32 == null) goto L20;
        r32.addOnScrollChangedListener(this.mSuperScrollListener);
        return;
    L20:
        return;
    L12:
        if (r0.isAlive() == false) goto L14;
        this.mViewTreeObserver.removeOnScrollChangedListener(this.mSuperScrollListener);
        goto L14
    L19:
        return;
    L7:
        r32 = null;
        goto L8
    }

    private void unregisterListener() {
        if (this.mSuperScrollListener == null) goto L11;
        ViewTreeObserver r0 = this.mViewTreeObserver;
        if (r0 != null) goto L7;
        return;
    L7:
        if (r0.isAlive() == false) goto L9;
        this.mViewTreeObserver.removeOnScrollChangedListener(this.mSuperScrollListener);
    L9:
        this.mViewTreeObserver = null;
        return;
    }

    @Override // android.widget.PopupWindow
    public void dismiss() {
        setFocusable(false);
        super.dismiss();     // Catch: Exception -> L6
    L4:
        unregisterListener();
    }

    public int getPopupHeight() {
        if (this.isCompound == false) goto L5;
        float r0 = 11.66f;
    L6:
        int r02 = AndroidUtilities.dp(r0);
        if (this.isCompound == false) goto L9;
        int r1 = 2;
    L11:
        return r02 + (r1 * this.emojiSize);
    L9:
        r1 = 1;
        goto L11
    L5:
        r0 = 15.0f;
        goto L6
    }

    public int getPopupWidth() {
        int r0 = this.emojiSize * 6;
        if (this.isCompound == false) goto L5;
        int r1 = 3;
    L7:
        return r0 + AndroidUtilities.dp(r1 + 30);
    L5:
        r1 = 0;
        goto L7
    }

    public int getSelection() {
        return this.pickerView.getSelection(0);
    }

    public String getSkinTone(int r2) {
        int r1 = this.pickerView.getSelection(r2);
        if (r1 >= 1) goto L5;
        return null;
    L5:
        if (r1 <= 5) goto L8;
        return null;
    L8:
        return CompoundEmoji.skinTones.get(r1 - 1);
    }

    public boolean isCompound() {
        return this.isCompound;
    }

    public void onTouchMove(int r3) {
        if (this.isCompound == true) goto L10;
        int r32 = Math.max(0, Math.min(5, r3 / (this.emojiSize + AndroidUtilities.dp(4.0f))));
        if (getSelection() == r32) goto L9;
        AndroidUtilities.vibrateCursor(this.pickerView);
        setSelection(r32);
        return;
    L9:
        return;
    }

    public void setEmoji(String r3) {
        if (CompoundEmoji.getCompoundEmojiDrawable(r3) == null) goto L5;
        boolean r0 = true;
    L6:
        this.isCompound = r0;
        this.pickerView.setEmoji(r0, r3);
        setWidth(getPopupWidth());
        setHeight(getPopupHeight());
        return;
    L5:
        r0 = false;
        goto L6
    }

    public void setOnSelectionUpdateListener(Utilities.Callback2<Integer, Integer> r1) {
        this.pickerView.setOnSelectionUpdateListener(r1);
    }

    public void setSelection(int r2) {
        this.pickerView.setSelection(0, r2);
    }

    public void setupArrow(int r1) {
        this.pickerView.setArrowX(r1);
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View r1, int r2, int r3) {
        super.showAsDropDown(r1, r2, r3);     // Catch: Exception -> L4
        registerListener(r1);     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    @Override // android.widget.PopupWindow
    public void showAtLocation(View r1, int r2, int r3, int r4) {
        super.showAtLocation(r1, r2, r3, r4);
        unregisterListener();
    }

    @Override // android.widget.PopupWindow
    public void update(View r1, int r2, int r3) {
        super.update(r1, r2, r3);
        registerListener(r1);
    }

    @Override // android.widget.PopupWindow
    public void update(View r1, int r2, int r3, int r4, int r5) {
        super.update(r1, r2, r3, r4, r5);
        registerListener(r1);
    }

    public void updateColors() {
        this.pickerView.updateColors();
    }
}

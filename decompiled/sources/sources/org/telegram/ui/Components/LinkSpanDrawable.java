package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.SystemClock;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LiteMode;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ArticleViewer;
import org.telegram.ui.Components.AnimatedEmojiSpan;

/* loaded from: classes7.dex */
public class LinkSpanDrawable<S extends CharacterStyle> {
    private static final ArrayList<LinkPath> pathCache = null;
    private final Path circlePath;
    private int color;
    private int cornerRadius;
    private final boolean isLite;
    private Rect mBounds;
    private final long mDuration;
    private final long mLongPressDuration;
    private float mMaxRadius;
    private final ArrayList<LinkPath> mPathes;
    private int mPathesCount;
    private long mReleaseStart;
    private final Theme.ResourcesProvider mResourcesProvider;
    private int mRippleAlpha;
    private Paint mRipplePaint;
    private int mSelectionAlpha;
    private Paint mSelectionPaint;
    private final S mSpan;
    private long mStart;
    private final boolean mSupportsLongPress;
    private final float mTouchX;
    private final float mTouchY;
    private final float rippleAlpha;
    private final float selectionAlpha;

    public static class ClickableSmallTextView extends SimpleTextView {
        private final Paint linkBackgroundPaint;
        private final LinkCollector links;
        private LinkSpanDrawable pressedLink;
        private Theme.ResourcesProvider resourcesProvider;

        public static /* synthetic */ void $r8$lambda$Uy_nBx3BsRg2Jij9sGN_SEsWDI0(ClickableSmallTextView r0, LinkSpanDrawable r1) {
            r0.lambda$onTouchEvent$0(r1);
        }

        public ClickableSmallTextView(Context r2) {
            this(r2, null);
        }

        public ClickableSmallTextView(Context r2, Theme.ResourcesProvider r3) {
            super(r2);
            this.links = new LinkCollector(this);
            this.linkBackgroundPaint = new Paint(1);
            this.resourcesProvider = r3;
        }

        private int getLinkColor() {
            return ColorUtils.setAlphaComponent(getTextColor(), (int) (Color.alpha(getTextColor()) * 0.1175f));
        }

        private /* synthetic */ void lambda$onTouchEvent$0(LinkSpanDrawable r2) {
            if (this.pressedLink != r2) goto L6;
            performLongClick();
            this.pressedLink = null;
            this.links.clear();
            return;
        }

        @Override // org.telegram.ui.ActionBar.SimpleTextView, android.view.View
        public void onDraw(Canvas r5) {
            if (isClickable() == false) goto L5;
            RectF r0 = AndroidUtilities.rectTmp;
            r0.set(0.0f, 0.0f, (getPaddingLeft() + getTextWidth()) + getPaddingRight(), getHeight());
            this.linkBackgroundPaint.setColor(getLinkColor());
            r5.drawRoundRect(r0, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), this.linkBackgroundPaint);
        L5:
            super.onDraw(r5);
            if (isClickable() == true) goto L8;
            return;
        L8:
            if (this.links.draw(r5) == false) goto L12;
            invalidate();
            return;
        }

        @Override // org.telegram.ui.ActionBar.SimpleTextView, android.view.View
        public boolean onTouchEvent(MotionEvent r11) {
            if (isClickable() == true) goto L7;
            return super.onTouchEvent(r11);
        L7:
            if (this.links == null) goto L24;
            if (r11.getAction() != 0) goto L13;
            LinkSpanDrawable r0 = new LinkSpanDrawable(null, this.resourcesProvider, r11.getX(), r11.getY());
            r0.setColor(getLinkColor());
            this.pressedLink = r0;
            this.links.addLink(r0);
            LinkPath r4 = this.pressedLink.obtainNewPath();
            r4.setCurrentLayout(null, 0, 0.0f, 0.0f);
            r4.addRect(0.0f, 0.0f, (getPaddingLeft() + getTextWidth()) + getPaddingRight(), getHeight(), Path.Direction.CW);
            AndroidUtilities.runOnUIThread(new LinkSpanDrawable$ClickableSmallTextView$$ExternalSyntheticLambda0(this, r0), ViewConfiguration.getLongPressTimeout());
            return true;
        L13:
            if (r11.getAction() != 1) goto L20;
            this.links.clear();
            if (this.pressedLink == null) goto L17;
            performClick();
        L17:
            this.pressedLink = null;
            return true;
        L20:
            if (r11.getAction() != 3) goto L24;
            this.links.clear();
            this.pressedLink = null;
            return true;
        L24:
            if (this.pressedLink == null) goto L26;
        L29:
            return true;
        L26:
            if (super.onTouchEvent(r11) == true) goto L29;
            return false;
        }
    }

    /* loaded from: classes3.dex */
    public static class LinkCollector {
        private Runnable additionalInvalidate;
        private ArrayList<Pair<LinkSpanDrawable, Object>> mLinks;
        private int mLinksCount;
        private ArrayList<Pair<LoadingDrawable, Object>> mLoading;
        private int mLoadingCount;
        private View mParent;

        public static /* synthetic */ void $r8$lambda$SemeTjq1Uxrj6ZhY02_Ghx9lY9E(LinkCollector r0, LoadingDrawable r1) {
            r0.lambda$removeLoadingAt$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$SpClWc8WdIraSoHf6wHDOOetURY(LinkCollector r0, LinkSpanDrawable r1) {
            r0.lambda$removeLink$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$nvimmzLcg11Dw0MF_hzS2bLRl7w(LinkCollector r0, LinkSpanDrawable r1) {
            r0.lambda$removeLink$1(r1);
        }

        public LinkCollector() {
            this.mLinks = new ArrayList();
            this.mLinksCount = 0;
            this.mLoading = new ArrayList();
            this.mLoadingCount = 0;
        }

        public LinkCollector(View r3) {
            this.mLinks = new ArrayList();
            this.mLinksCount = 0;
            this.mLoading = new ArrayList();
            this.mLoadingCount = 0;
            this.mParent = r3;
        }

        private void invalidate() {
            invalidate(null, true);
        }

        private void invalidate(Object r2) {
            invalidate(r2, true);
        }

        private void invalidate(Object r2, boolean r3) {
            if ((r2 instanceof View) == false) goto L6;
            ((View) r2).invalidate();
        L12:
            Runnable r1 = this.additionalInvalidate;
            if (r1 == null) goto L16;
            r1.run();
            return;
        L16:
            return;
        L6:
            if ((r2 instanceof ArticleViewer.DrawingText) == false) goto L8;
            ((ArticleViewer.DrawingText) r2).invalidateParent();
            goto L12
        L8:
            if (r3 == false) goto L12;
            View r22 = this.mParent;
            if (r22 == null) goto L12;
            r22.invalidate();
            goto L12
        }

        private /* synthetic */ void lambda$removeLink$0(LinkSpanDrawable r2) {
            removeLink(r2, false);
        }

        private /* synthetic */ void lambda$removeLink$1(LinkSpanDrawable r2) {
            removeLink(r2, false);
        }

        private /* synthetic */ void lambda$removeLoadingAt$2(LoadingDrawable r2) {
            removeLoading(r2, false);
        }

        public static LoadingDrawable makeLoading(Layout r4, CharacterStyle r5, float r6) {
            if (r4 == null) goto L9;
            if (r5 != null) goto L5;
            return null;
        L5:
            if ((r4.getText() instanceof Spanned) == false) goto L12;
            Spanned r0 = (Spanned) r4.getText();
            LinkPath r1 = new LinkPath(true);
            int r3 = r0.getSpanStart(r5);
            int r52 = r0.getSpanEnd(r5);
            r1.setCurrentLayout(r4, r3, r6);
            r4.getSelectionPath(r3, r52, r1);
            LoadingDrawable r42 = new LoadingDrawable();
            r42.usePath(r1);
            r42.setAppearByGradient(true);
            r42.setRadiiDp(4.0f);
            r42.updateBounds();
            return r42;
        L12:
            return null;
        L9:
            return null;
        }

        private void removeLink(int r7, boolean r8) {
            if (r7 >= 0) goto L4;
            return;
        L4:
            if (r7 >= this.mLinksCount) goto L16;
            ArrayList<Pair<LinkSpanDrawable, Object>> r0 = this.mLinks;
            if (r8 == false) goto L12;
            Pair<LinkSpanDrawable, Object> r72 = r0.get(r7);
            LinkSpanDrawable r82 = (LinkSpanDrawable) r72.first;
            if (LinkSpanDrawable.m11196$$Nest$fgetmReleaseStart(r82) >= 0) goto L15;
            r82.release();
            invalidate(r72.second);
            long r02 = SystemClock.elapsedRealtime();
            AndroidUtilities.runOnUIThread(new LinkSpanDrawable$LinkCollector$$ExternalSyntheticLambda2(this, r82), Math.max(0, (LinkSpanDrawable.m11196$$Nest$fgetmReleaseStart(r82) - r02) + 175));
            return;
        L15:
            return;
        L12:
            Pair<LinkSpanDrawable, Object> r73 = r0.remove(r7);
            ((LinkSpanDrawable) r73.first).reset();
            this.mLinksCount = this.mLinks.size();
            invalidate(r73.second);
            return;
        }

        private void removeLoadingAt(int r3, boolean r4) {
            if (r3 >= 0) goto L4;
            return;
        L4:
            if (r3 >= this.mLoadingCount) goto L23;
            Pair<LoadingDrawable, Object> r32 = this.mLoading.get(r3);
            if (r32 == null) goto L24;
            LoadingDrawable r0 = (LoadingDrawable) r32.first;
            if (r4 == true) goto L12;
            this.mLoading.remove(r32);
            r0.reset();
            r0.resetDisappear();
            this.mLoadingCount = this.mLoading.size();
            invalidate(r32.second);
            return;
        L12:
            if (r0.isDisappeared() == false) goto L14;
            removeLoading(r0, false);
            return;
        L14:
            if (r0.isDisappearing() == true) goto L16;
            r0.disappear();
        L16:
            AndroidUtilities.runOnUIThread(new LinkSpanDrawable$LinkCollector$$ExternalSyntheticLambda0(this, r0), r0.timeToDisappear());
            return;
        L24:
            return;
        }

        public void addLink(LinkSpanDrawable r2) {
            addLink(r2, null);
        }

        public void addLink(LinkSpanDrawable r3, Object r4) {
            this.mLinks.add(new Pair(r3, r4));
            this.mLinksCount++;
            invalidate(r4);
        }

        public void addLoading(LoadingDrawable r2) {
            addLoading(r2, null);
        }

        public void addLoading(LoadingDrawable r3, Object r4) {
            this.mLoading.add(new Pair(r3, r4));
            this.mLoadingCount++;
            invalidate(r4);
        }

        public void clear() {
            clear(true);
        }

        public void clear(boolean r4) {
            int r0 = 0;
            if (r4 == false) goto L8;
        L5:
            if (r0 >= this.mLinksCount) goto L18;
            removeLink(r0, true);
            r0 = r0 + 1;
            goto L5
        L18:
            return;
        L8:
            if (this.mLinksCount <= 0) goto L17;
            int r42 = 0;
        L10:
            int r1 = this.mLinksCount;
            ArrayList<Pair<LinkSpanDrawable, Object>> r2 = this.mLinks;
            if (r42 >= r1) goto L13;
            ((LinkSpanDrawable) r2.get(r42).first).reset();
            invalidate(this.mLinks.get(r42).second, false);
            r42 = r42 + 1;
            goto L10
        L13:
            r2.clear();
            this.mLinksCount = 0;
            invalidate();
            return;
        }

        public void clearLoading(boolean r4) {
            int r0 = 0;
            if (r4 == false) goto L8;
        L5:
            if (r0 >= this.mLoadingCount) goto L18;
            removeLoadingAt(r0, true);
            r0 = r0 + 1;
            goto L5
        L18:
            return;
        L8:
            if (this.mLoadingCount <= 0) goto L17;
            int r42 = 0;
        L10:
            int r1 = this.mLoadingCount;
            ArrayList<Pair<LoadingDrawable, Object>> r2 = this.mLoading;
            if (r42 >= r1) goto L13;
            ((LoadingDrawable) r2.get(r42).first).reset();
            invalidate(this.mLoading.get(r42).second, false);
            r42 = r42 + 1;
            goto L10
        L13:
            r2.clear();
            this.mLoadingCount = 0;
            invalidate();
            return;
        }

        public boolean draw(Canvas r6) {
            int r1 = 0;
            boolean r2 = false;
        L4:
            if (r1 >= this.mLoadingCount) goto L6;
            ((LoadingDrawable) this.mLoading.get(r1).first).draw(r6);
            r1 = r1 + 1;
            r2 = true;
            goto L4
        L6:
            int r12 = 0;
        L8:
            if (r12 >= this.mLinksCount) goto L16;
            if (((LinkSpanDrawable) this.mLinks.get(r12).first).draw(r6) == true) goto L14;
            if (r2 == true) goto L14;
            r2 = false;
        L15:
            r12 = r12 + 1;
        L14:
            r2 = true;
            goto L15
        L16:
            return r2;
        }

        public boolean draw(Canvas r6, Object r7) {
            int r1 = 0;
            boolean r2 = false;
        L4:
            if (r1 >= this.mLoadingCount) goto L9;
            if (this.mLoading.get(r1).second != r7) goto L8;
            ((LoadingDrawable) this.mLoading.get(r1).first).draw(r6);
            r2 = true;
        L8:
            r1 = r1 + 1;
            goto L4
        L9:
            int r12 = 0;
        L11:
            if (r12 >= this.mLinksCount) goto L21;
            if (this.mLinks.get(r12).second != r7) goto L20;
            if (((LinkSpanDrawable) this.mLinks.get(r12).first).draw(r6) == true) goto L19;
            if (r2 == true) goto L19;
            r2 = false;
        L19:
            r2 = true;
        L20:
            r12 = r12 + 1;
            goto L11
        L21:
            invalidate(r7, false);
            return r2;
        }

        public boolean isEmpty() {
            if (this.mLinksCount > 0) goto L6;
            return true;
        L6:
            return false;
        }

        public void removeLink(LinkSpanDrawable r2) {
            removeLink(r2, true);
        }

        public void removeLink(LinkSpanDrawable r6, boolean r7) {
            if (r6 == null) goto L24;
            int r0 = 0;
        L6:
            if (r0 >= this.mLinksCount) goto L11;
            if (this.mLinks.get(r0).first == r6) goto L9;
            r0 = r0 + 1;
            goto L6
        L9:
            Pair<LinkSpanDrawable, Object> r02 = this.mLinks.get(r0);
        L12:
            if (r02 == null) goto L25;
            if (r7 == true) goto L16;
            this.mLinks.remove(r02);
            r6.reset();
            this.mLinksCount = this.mLinks.size();
            invalidate(r02.second);
            return;
        L16:
            if (LinkSpanDrawable.m11196$$Nest$fgetmReleaseStart(r6) >= 0) goto L23;
            r6.release();
            invalidate(r02.second);
            long r03 = SystemClock.elapsedRealtime();
            AndroidUtilities.runOnUIThread(new LinkSpanDrawable$LinkCollector$$ExternalSyntheticLambda1(this, r6), Math.max(0, (LinkSpanDrawable.m11196$$Nest$fgetmReleaseStart(r6) - r03) + 175));
            return;
        L23:
            return;
        L25:
            return;
        L11:
            r02 = null;
            goto L12
        }

        public void removeLinks(Object r2) {
            removeLinks(r2, true);
        }

        public void removeLinks(Object r3, boolean r4) {
            int r0 = 0;
        L4:
            if (r0 >= this.mLinksCount) goto L9;
            if (this.mLinks.get(r0).second != r3) goto L8;
            removeLink(r0, r4);
        L8:
            r0 = r0 + 1;
            goto L4
        }

        public void removeLoading(LoadingDrawable r3, boolean r4) {
            if (r3 == null) goto L12;
            int r0 = 0;
        L6:
            if (r0 >= this.mLoadingCount) goto L15;
            if (this.mLoading.get(r0).first == r3) goto L9;
            r0 = r0 + 1;
            goto L6
        L9:
            removeLoadingAt(r0, r4);
            return;
        L15:
            return;
        }

        public void setAdditionalInvalidate(Runnable r1) {
            this.additionalInvalidate = r1;
        }

        public void setParent(View r1) {
            this.mParent = r1;
        }
    }

    public static class LinksSimpleTextView extends SimpleTextView {
        private final LinkCollector links;
        private LinkSpanDrawable pressedLink;
        private Theme.ResourcesProvider resourcesProvider;

        public LinksSimpleTextView(Context r1, Theme.ResourcesProvider r2) {
            super(r1);
            this.links = new LinkCollector(this);
            this.resourcesProvider = r2;
        }

        public ClickableSpan hit(int r6, int r7) {
            Layout r0 = getLayout();
            if (r0 != null) goto L5;
            return null;
        L5:
            int r62 = (int) (r6 - getLayoutX());
            int r5 = (int) (r7 - getLayoutY());
            int r72 = r0.getLineForVertical(r5);
            float r63 = r62;
            int r2 = r0.getOffsetForHorizontal(r72, r63);
            float r3 = r0.getLineLeft(r72);
            if (r3 <= r63) goto L8;
        L18:
            return null;
        L8:
            if ((r3 + r0.getLineWidth(r72)) < r63) goto L18;
            if (r5 < 0) goto L18;
            if (r5 > r0.getHeight()) goto L18;
            ClickableSpan[] r52 = (ClickableSpan[]) new SpannableString(r0.getText()).getSpans(r2, r2, ClickableSpan.class);
            if (r52.length == 0) goto L18;
            if (AndroidUtilities.isAccessibilityScreenReaderEnabled() == true) goto L18;
            return r52[0];
        }

        @Override // org.telegram.ui.ActionBar.SimpleTextView, android.view.View
        public void onDraw(Canvas r3) {
            super.onDraw(r3);
            r3.save();
            r3.translate(getLayoutX(), getLayoutY());
            if (this.links.draw(r3) == false) goto L5;
            invalidate();
        L5:
            r3.restore();
        }

        @Override // org.telegram.ui.ActionBar.SimpleTextView, android.view.View
        public boolean onTouchEvent(MotionEvent r7) {
            if (this.links == null) goto L26;
            Layout r0 = getLayout();
            ClickableSpan r2 = hit((int) r7.getX(), (int) r7.getY());
            if (r2 == null) goto L11;
            if (r7.getAction() != 0) goto L11;
            LinkSpanDrawable r3 = new LinkSpanDrawable(r2, this.resourcesProvider, r7.getX(), r7.getY());
            this.pressedLink = r3;
            this.links.addLink(r3);
            SpannableString r72 = new SpannableString(r0.getText());
            int r22 = r72.getSpanStart(this.pressedLink.getSpan());
            int r73 = r72.getSpanEnd(this.pressedLink.getSpan());
            LinkPath r6 = this.pressedLink.obtainNewPath();
            r6.setCurrentLayout(r0, r22, 0.0f);
            r0.getSelectionPath(r22, r73, r6);
            return true;
        L11:
            if (r7.getAction() != 1) goto L23;
            this.links.clear();
            LinkSpanDrawable r02 = this.pressedLink;
            if (r02 != null) goto L15;
        L21:
            this.pressedLink = null;
            goto L23
        L15:
            if (r02.getSpan() != r2) goto L21;
            if ((this.pressedLink.getSpan() instanceof ClickableSpan) == false) goto L19;
            ((ClickableSpan) this.pressedLink.getSpan()).onClick(this);
        L19:
            this.pressedLink = null;
            return true;
        L23:
            if (r7.getAction() != 3) goto L26;
            this.links.clear();
            this.pressedLink = null;
        L26:
            if (this.pressedLink == null) goto L28;
        L32:
            return true;
        L28:
            if (super.onTouchEvent(r7) == true) goto L32;
            return false;
        }
    }

    /* loaded from: classes3.dex */
    public static class LinksTextView extends TextView {
        private static Class editorClass;
        private static Field mEditor;
        private static Method mEditorInvalidateDisplayList;
        private CharacterStyle currentLinkLoading;
        private boolean disablePaddingsOffset;
        private boolean disablePaddingsOffsetX;
        private boolean disablePaddingsOffsetY;
        private Object editor;
        private ColorFilter emojiColorFilter;
        private int emojiColorFilterColor;
        private boolean emojiColorIsLink;
        private boolean isCustomLinkCollector;
        private final LinkCollector links;
        private boolean loggedError;
        private int maxWidth;
        private OnLinkPress onLongPressListener;
        private OnLinkPress onPressListener;
        private LinkSpanDrawable<ClickableSpan> pressedLink;
        private Theme.ResourcesProvider resourcesProvider;
        AnimatedEmojiSpan.EmojiGroupedSpans stack;
        private boolean triedGetInvalidate;

        /* loaded from: classes7.dex */
        public interface OnLinkPress {
            void run(ClickableSpan r1);
        }

        /* renamed from: $r8$lambda$emsyP3OEetdZySKyxo5O8gy-eJ8, reason: not valid java name */
        public static /* synthetic */ void m11197$r8$lambda$emsyP3OEetdZySKyxo5O8gyeJ8(LinksTextView r0, LinkSpanDrawable r1, ClickableSpan r2) {
            r0.lambda$onTouchEvent$0(r1, r2);
        }

        public LinksTextView(Context r2) {
            this(r2, null);
        }

        public LinksTextView(Context r2, Theme.ResourcesProvider r3) {
            super(r2);
            this.loggedError = false;
            this.emojiColorIsLink = true;
            this.isCustomLinkCollector = false;
            this.links = new LinkCollector(this);
            this.resourcesProvider = r3;
        }

        public LinksTextView(Context r1, LinkCollector r2, Theme.ResourcesProvider r3) {
            super(r1);
            this.loggedError = false;
            this.emojiColorIsLink = true;
            this.isCustomLinkCollector = true;
            this.links = r2;
            this.resourcesProvider = r3;
        }

        private /* synthetic */ void lambda$onTouchEvent$0(LinkSpanDrawable r3, ClickableSpan r4) {
            OnLinkPress r0 = this.onLongPressListener;
            if (r0 != null) goto L5;
            return;
        L5:
            if (this.pressedLink != r3) goto L9;
            r0.run(r4);
            this.pressedLink = null;
            this.links.clear();
            return;
        }

        public void clear() {
            this.links.clear(false);
        }

        public int emojiCacheType() {
            return 0;
        }

        public int getTextPaddingTop() {
            int r0 = getPaddingTop();
            if (getGravity() == 17) goto L5;
            return r0;
        L5:
            if (getLayout() != null) goto L7;
            return r0;
        L7:
            return r0 + Math.max(0, (((getHeight() - getPaddingTop()) - getPaddingBottom()) - getLayout().getHeight()) / 2);
        }

        public boolean hasLinks() {
            Layout r3 = getLayout();
            if (r3 != null) goto L5;
            return false;
        L5:
            CharSequence r32 = r3.getText();
            if ((r32 instanceof Spanned) == true) goto L8;
            return false;
        L8:
            Spanned r33 = (Spanned) r32;
            ClickableSpan[] r34 = (ClickableSpan[]) r33.getSpans(0, r33.length(), ClickableSpan.class);
            if (r34 != null) goto L11;
        L14:
            return false;
        L11:
            if (r34.length <= 0) goto L14;
            return true;
        }

        public ClickableSpan hit(int r6, int r7) {
            Layout r0 = getLayout();
            if (r0 != null) goto L5;
            return null;
        L5:
            int r62 = r6 - getPaddingLeft();
            int r72 = r7 - getTextPaddingTop();
            int r63 = r62 + getScrollX();
            int r5 = r0.getLineForVertical(r72);
            float r64 = r63;
            int r2 = r0.getOffsetForHorizontal(r5, r64);
            float r3 = r0.getLineLeft(r5);
            if (r3 <= r64) goto L8;
        L18:
            return null;
        L8:
            if ((r3 + r0.getLineWidth(r5)) < r64) goto L18;
            if (r72 < 0) goto L18;
            if (r72 > r0.getHeight()) goto L18;
            ClickableSpan[] r52 = (ClickableSpan[]) new SpannableString(r0.getText()).getSpans(r2, r2, ClickableSpan.class);
            if (r52.length == 0) goto L18;
            if (AndroidUtilities.isAccessibilityScreenReaderEnabled() == true) goto L18;
            return r52[0];
        }

        @Override // android.view.View
        public void invalidate() {
            if (this.triedGetInvalidate == true) goto L12;
            this.triedGetInvalidate = true;
        L10:
            th = move-exception;
            FileLog.e(th);
            goto L12
        L6:
            if (editorClass != null) goto L12;
            Field r2 = TextView.class.getDeclaredField("mEditor");     // Catch: Throwable -> L10
            mEditor = r2;     // Catch: Throwable -> L10
            r2.setAccessible(true);     // Catch: Throwable -> L10
            Class<?> r22 = Class.forName("android.widget.Editor");     // Catch: Throwable -> L10
            editorClass = r22;     // Catch: Throwable -> L10
            Method r23 = r22.getDeclaredMethod("invalidateTextDisplayList", null);     // Catch: Throwable -> L10 Exception -> L24
            mEditorInvalidateDisplayList = r23;     // Catch: Throwable -> L10 Exception -> L24
            r23.setAccessible(true);     // Catch: Throwable -> L10 Exception -> L24
        L12:
            super.invalidate();
            if (isHardwareAccelerated() == false) goto L33;
            if (mEditorInvalidateDisplayList != null) goto L18;
            return;
        L18:
            if (this.editor != null) goto L20;
            this.editor = mEditor.get(this);     // Catch: Exception -> L25
        L20:
            Object r4 = this.editor;     // Catch: Exception -> L25
            if (r4 == null) goto L32;
            mEditorInvalidateDisplayList.invoke(r4, null);     // Catch: Exception -> L25
            return;
        L32:
            return;
        L34:
            return;
        }

        @Override // android.widget.TextView, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.stack = AnimatedEmojiSpan.update(emojiCacheType(), this, this.stack, new Layout[]{getLayout()});
        }

        @Override // android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            AnimatedEmojiSpan.release(this, this.stack);
        }

        @Override // android.widget.TextView, android.view.View
        public void onDraw(Canvas r14) {
            if (this.isCustomLinkCollector == true) goto L21;
            r14.save();
            if (this.disablePaddingsOffset == false) goto L7;
        L15:
            LinkCollector r0 = this.links;
            if (r0 != null) goto L18;
        L20:
            r14.restore();
            goto L21
        L18:
            if (r0.draw(r14) == false) goto L20;
            invalidate();
            goto L20
        L7:
            if (this.disablePaddingsOffsetX == false) goto L9;
            float r02 = 0.0f;
        L11:
            if (this.disablePaddingsOffsetY == false) goto L13;
            float r2 = 0.0f;
        L14:
            r14.translate(r02, r2);
            goto L15
        L13:
            r2 = getTextPaddingTop();
            goto L14
        L9:
            r02 = getPaddingLeft();
        L21:
            super.onDraw(r14);
            boolean r22 = false;
            Layout r4 = getLayout();     // Catch: Exception -> L26
            if ((getGravity() & 16) == 0) goto L27;
            if (r4 == null) goto L27;
            float r3 = getPaddingTop() + ((((getHeight() - getPaddingTop()) - getPaddingBottom()) - r4.getHeight()) / 2.0f);     // Catch: Exception -> L26
        L29:
            if (r3 == 0.0f) goto L31;
        L32:
            r14.save();     // Catch: Exception -> L26
            r14.translate(getPaddingLeft(), r3);     // Catch: Exception -> L46
            r22 = true;
        L35:
            this.stack = AnimatedEmojiSpan.update(emojiCacheType(), this, this.stack, new Layout[]{getLayout()});     // Catch: Exception -> L26
            if (this.emojiColorIsLink == true) goto L38;
        L42:
            Canvas r32 = r14;
            AnimatedEmojiSpan.drawAnimatedEmojis(r32, r4, this.stack, 0.0f, null, 0.0f, 0.0f, 0.0f, 1.0f, this.emojiColorFilter);     // Catch: Exception -> L51
        L48:
            if (r22 == false) goto L58;
            r32.restore();
            return;
        L58:
            return;
        L47:
            this.loggedError = true;
            goto L48
        L38:
            if (this.emojiColorFilter != null) goto L40;
        L41:
            int r33 = getPaint().linkColor;     // Catch: Exception -> L26
            this.emojiColorFilterColor = r33;     // Catch: Exception -> L26
            this.emojiColorFilter = new PorterDuffColorFilter(r33, PorterDuff.Mode.SRC_IN);     // Catch: Exception -> L26
            goto L42
        L40:
            if (this.emojiColorFilterColor == getPaint().linkColor) goto L42;
        L46:
            r32 = r14;
            r22 = true;
            goto L47
        L31:
            if (getPaddingLeft() == 0) goto L35;
        L27:
            r3 = 0.0f;
        L26:
            r32 = r14;
            goto L47
        }

        @Override // android.widget.TextView, android.view.View
        public void onMeasure(int r3, int r4) {
            int r0 = this.maxWidth;
            if (r0 <= 0) goto L5;
            r3 = View.MeasureSpec.makeMeasureSpec(Math.min(r0, View.MeasureSpec.getSize(r3)), View.MeasureSpec.getMode(r3));
        L5:
            super.onMeasure(r3, r4);
            this.stack = AnimatedEmojiSpan.update(emojiCacheType(), this, this.stack, new Layout[]{getLayout()});
        }

        @Override // android.widget.TextView, android.view.View
        public boolean onTouchEvent(MotionEvent r8) {
            if (this.links == null) goto L29;
            Layout r0 = getLayout();
            ClickableSpan r2 = hit((int) r8.getX(), (int) r8.getY());
            if (r2 == null) goto L11;
            if (r8.getAction() != 0) goto L11;
            LinkSpanDrawable<ClickableSpan> r3 = new LinkSpanDrawable(r2, this.resourcesProvider, r8.getX(), r8.getY());
            r3.setColor(overrideColor());
            this.pressedLink = r3;
            this.links.addLink(r3);
            SpannableString r82 = new SpannableString(r0.getText());
            int r4 = r82.getSpanStart(this.pressedLink.getSpan());
            int r83 = r82.getSpanEnd(this.pressedLink.getSpan());
            LinkPath r5 = this.pressedLink.obtainNewPath();
            r5.setCurrentLayout(r0, r4, getPaddingTop());
            r0.getSelectionPath(r4, r83, r5);
            AndroidUtilities.runOnUIThread(new LinkSpanDrawable$LinksTextView$$ExternalSyntheticLambda0(this, r3, r2), ViewConfiguration.getLongPressTimeout());
            return true;
        L11:
            if (r8.getAction() != 1) goto L26;
            this.links.clear();
            LinkSpanDrawable<ClickableSpan> r02 = this.pressedLink;
            if (r02 != null) goto L15;
        L24:
            this.pressedLink = null;
            goto L26
        L15:
            if (r02.getSpan() != r2) goto L24;
            OnLinkPress r84 = this.onPressListener;
            LinkSpanDrawable<ClickableSpan> r03 = this.pressedLink;
            if (r84 == null) goto L20;
            r84.run((ClickableSpan) r03.getSpan());
        L22:
            this.pressedLink = null;
            return true;
        L20:
            if (r03.getSpan() == null) goto L22;
            ((ClickableSpan) this.pressedLink.getSpan()).onClick(this);
        L26:
            if (r8.getAction() != 3) goto L29;
            this.links.clear();
            this.pressedLink = null;
        L29:
            if (this.pressedLink == null) goto L31;
        L35:
            return true;
        L31:
            if (super.onTouchEvent(r8) == true) goto L35;
            return false;
        }

        public int overrideColor() {
            return Theme.getColor(Theme.key_chat_linkSelectBackground, this.resourcesProvider);
        }

        public int processColor(int r1) {
            return r1;
        }

        public void resetEmojiColor() {
            this.emojiColorIsLink = false;
            this.emojiColorFilter = null;
            invalidate();
        }

        public void setDisablePaddingsOffset(boolean r1) {
            this.disablePaddingsOffset = r1;
        }

        public void setDisablePaddingsOffsetX(boolean r1) {
            this.disablePaddingsOffsetX = r1;
        }

        public void setDisablePaddingsOffsetY(boolean r1) {
            this.disablePaddingsOffsetY = r1;
        }

        public void setEmojiColor(int r3) {
            this.emojiColorIsLink = false;
            this.emojiColorFilter = new PorterDuffColorFilter(r3, PorterDuff.Mode.SRC_IN);
            invalidate();
        }

        public void setLoading(CharacterStyle r6) {
            if (this.currentLinkLoading == r6) goto L8;
            this.links.clearLoading(true);
            this.currentLinkLoading = r6;
            LoadingDrawable r62 = LinkCollector.makeLoading(getLayout(), r6, getPaddingTop());
            if (r62 == null) goto L9;
            int r0 = processColor(Theme.getColor(Theme.key_chat_linkSelectBackground, this.resourcesProvider));
            r62.setColors(Theme.multAlpha(r0, 0.8f), Theme.multAlpha(r0, 1.3f), Theme.multAlpha(r0, 1.0f), Theme.multAlpha(r0, 4.0f));
            r62.strokePaint.setStrokeWidth(AndroidUtilities.dpf2(1.25f));
            this.links.addLoading(r62);
            return;
        L9:
            return;
        }

        @Override // android.widget.TextView
        public void setMaxWidth(int r1) {
            this.maxWidth = r1;
        }

        public void setOnLinkLongPressListener(OnLinkPress r1) {
            this.onLongPressListener = r1;
        }

        public void setOnLinkPressListener(OnLinkPress r1) {
            this.onPressListener = r1;
        }

        @Override // android.widget.TextView
        public void setText(CharSequence r2, TextView.BufferType r3) {
            super.setText(r2, r3);
            this.stack = AnimatedEmojiSpan.update(emojiCacheType(), this, this.stack, new Layout[]{getLayout()});
        }
    }

    /* renamed from: -$$Nest$fgetmReleaseStart, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m11196$$Nest$fgetmReleaseStart(LinkSpanDrawable r2) {
        return r2.mReleaseStart;
    }

    static {
        pathCache = new ArrayList();
    }

    public LinkSpanDrawable(S r7, Theme.ResourcesProvider r8, float r9, float r10) {
        this(r7, r8, r9, r10, true);
    }

    public LinkSpanDrawable(S r3, Theme.ResourcesProvider r4, float r5, float r6, boolean r7) {
        this.mPathes = new ArrayList();
        this.mPathesCount = 0;
        this.circlePath = new Path();
        this.mStart = -1;
        this.mReleaseStart = -1;
        this.selectionAlpha = 0.2f;
        this.rippleAlpha = 0.8f;
        this.isLite = !LiteMode.isEnabled(LiteMode.FLAGS_CHAT);
        this.mSpan = r3;
        this.mResourcesProvider = r4;
        setColor(Theme.getColor(Theme.key_chat_linkSelectBackground, r4));
        this.mTouchX = r5;
        this.mTouchY = r6;
        long r32 = ViewConfiguration.getTapTimeout();
        long r52 = ViewConfiguration.getLongPressTimeout();
        this.mLongPressDuration = r52;
        this.mDuration = (long) Math.min(r32 * 1.8f, r52 * 0.8f);
        this.mSupportsLongPress = false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v10, types: [int] */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15, types: [int] */
    /* JADX WARN: Type inference failed for: r2v65 */
    /* JADX WARN: Type inference failed for: r2v66 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15, types: [int] */
    /* JADX WARN: Type inference failed for: r4v62 */
    public boolean draw(Canvas r21) {
        if (this.isLite == false) goto L5;
        int r2 = 0;
    L7:
        if (this.cornerRadius == r2) goto L9;
        boolean r4 = true;
    L11:
        if (this.mSelectionPaint != null) goto L14;
        Paint r6 = new Paint(1);
        this.mSelectionPaint = r6;
        r6.setStyle(Paint.Style.FILL_AND_STROKE);
        this.mSelectionPaint.setColor(this.color);
        this.mSelectionAlpha = Color.alpha(this.color);
    L14:
        if (this.mRipplePaint != null) goto L16;
        Paint r62 = new Paint(1);
        this.mRipplePaint = r62;
        r62.setStyle(Paint.Style.FILL_AND_STROKE);
        this.mRipplePaint.setColor(this.color);
        this.mRippleAlpha = Color.alpha(this.color);
    L16:
        if (r4 == false) goto L22;
        this.cornerRadius = r2;
        Paint r42 = this.mSelectionPaint;
        if (r2 > 0) goto L20;
        r42.setPathEffect(null);
        this.mRipplePaint.setPathEffect(null);
        goto L22
    L20:
        r42.setPathEffect(new CornerPathEffect(this.cornerRadius));
        this.mRipplePaint.setPathEffect(new CornerPathEffect(this.cornerRadius));
    L22:
        if (this.mBounds != null) goto L31;
        if (this.mPathesCount <= 0) goto L31;
        LinkPath r22 = this.mPathes.get(0);
        RectF r43 = AndroidUtilities.rectTmp;
        r22.computeBounds(r43, false);
        this.mBounds = new Rect((int) r43.left, (int) r43.top, (int) r43.right, (int) r43.bottom);
        int r23 = 1;
    L27:
        if (r23 >= this.mPathesCount) goto L29;
        LinkPath r44 = this.mPathes.get(r23);
        RectF r63 = AndroidUtilities.rectTmp;
        r44.computeBounds(r63, false);
        Rect r45 = this.mBounds;
        r45.left = Math.min(r45.left, (int) r63.left);
        Rect r46 = this.mBounds;
        r46.top = Math.min(r46.top, (int) r63.top);
        Rect r47 = this.mBounds;
        r47.right = Math.max(r47.right, (int) r63.right);
        Rect r48 = this.mBounds;
        r48.bottom = Math.max(r48.bottom, (int) r63.bottom);
        r23 = r23 + 1;
        goto L27
    L29:
        this.mMaxRadius = (float) Math.sqrt(Math.max(Math.max(Math.pow(this.mBounds.left - this.mTouchX, 2.0d) + Math.pow(this.mBounds.top - this.mTouchY, 2.0d), Math.pow(this.mBounds.right - this.mTouchX, 2.0d) + Math.pow(this.mBounds.top - this.mTouchY, 2.0d)), Math.max(Math.pow(this.mBounds.left - this.mTouchX, 2.0d) + Math.pow(this.mBounds.bottom - this.mTouchY, 2.0d), Math.pow(this.mBounds.right - this.mTouchX, 2.0d) + Math.pow(this.mBounds.bottom - this.mTouchY, 2.0d))));
    L31:
        if (this.isLite == false) goto L37;
        int r24 = 0;
    L34:
        if (r24 >= this.mPathesCount) goto L36;
        r21.drawPath(this.mPathes.get(r24), this.mRipplePaint);
        r24 = r24 + 1;
        goto L34
    L36:
        return false;
    L37:
        long r64 = SystemClock.elapsedRealtime();
        if (this.mStart >= 0) goto L40;
        this.mStart = r64;
    L40:
        float r25 = CubicBezierInterpolator.DEFAULT.getInterpolation(Math.min(1.0f, (r64 - this.mStart) / this.mDuration));
        if (this.mReleaseStart >= 0) goto L43;
        float r49 = 0.0f;
    L45:
        if (this.mSupportsLongPress == false) goto L51;
        boolean r13 = false;
        float r18 = r49;
        float r3 = Math.max(0.0f, ((r64 - this.mStart) - (this.mDuration * 2)) / (this.mLongPressDuration - (r14 * 2)));
        if (r3 <= 1.0f) goto L49;
        float r32 = 1.0f - (((r64 - this.mStart) - this.mLongPressDuration) / this.mDuration);
    L50:
        float r33 = r32 * (1.0f - r18);
    L52:
        float r14 = 1.0f - r18;
        this.mSelectionPaint.setAlpha((int) (((this.mSelectionAlpha * 0.2f) * Math.min(1.0f, r25 * 5.0f)) * r14));
        float r34 = 1.0f - r33;
        this.mSelectionPaint.setStrokeWidth(Math.min(1.0f, r34) * AndroidUtilities.dp(5.0f));
        ?? r410 = r13;
    L54:
        if (r410 >= this.mPathesCount) goto L56;
        this.mPathes.get(r410).closeRects();
        r21.drawPath(this.mPathes.get(r410), this.mSelectionPaint);
        r410 = r410 + 1;
        goto L54
    L56:
        this.mRipplePaint.setAlpha((int) ((this.mRippleAlpha * 0.8f) * r14));
        this.mRipplePaint.setStrokeWidth(Math.min(1.0f, r34) * AndroidUtilities.dp(5.0f));
        if (r25 >= 1.0f) goto L63;
        float r26 = r25 * this.mMaxRadius;
        r21.save();
        this.circlePath.reset();
        this.circlePath.addCircle(this.mTouchX, this.mTouchY, r26, Path.Direction.CW);
        r21.clipPath(this.circlePath);
        ?? r27 = r13;
    L60:
        if (r27 >= this.mPathesCount) goto L62;
        r21.drawPath(this.mPathes.get(r27), this.mRipplePaint);
        r27 = r27 + 1;
        goto L60
    L62:
        r21.restore();
    L67:
        if (r25 >= 1.0f) goto L69;
    L76:
        return true;
    L69:
        if (this.mReleaseStart >= 0) goto L76;
        if (this.mSupportsLongPress == true) goto L73;
    L75:
        return r13;
    L73:
        if ((r64 - this.mStart) >= (this.mLongPressDuration + this.mDuration)) goto L75;
    L63:
        ?? r28 = r13;
    L65:
        if (r28 >= this.mPathesCount) goto L67;
        r21.drawPath(this.mPathes.get(r28), this.mRipplePaint);
        r28 = r28 + 1;
        goto L65
    L49:
        r32 = r3 * 0.5f;
        goto L50
    L51:
        r13 = false;
        r18 = r49;
        r33 = 1.0f;
        goto L52
    L43:
        r49 = Math.min(1.0f, Math.max(0.0f, ((r64 - 75) - r12) / 100.0f));
        goto L45
    L9:
        r4 = false;
        goto L11
    L5:
        r2 = AndroidUtilities.dp(4.0f);
        goto L7
    }

    public S getSpan() {
        return this.mSpan;
    }

    public LinkPath obtainNewPath() {
        ArrayList<LinkPath> r0 = pathCache;
        if (r0.isEmpty() == true) goto L5;
        LinkPath r02 = r0.remove(0);
    L6:
        r02.setUseCornerPathImplementation(!this.isLite);
        r02.reset();
        this.mPathes.add(r02);
        this.mPathesCount = this.mPathes.size();
        return r02;
    L5:
        r02 = new LinkPath(true);
        goto L6
    }

    public void release() {
        this.mReleaseStart = Math.max(this.mStart + this.mDuration, SystemClock.elapsedRealtime());
    }

    public void reset() {
        if (this.mPathes.isEmpty() == false) goto L5;
        return;
    L5:
        pathCache.addAll(this.mPathes);
        this.mPathes.clear();
        this.mPathesCount = 0;
    }

    public void setColor(int r2) {
        this.color = r2;
        Paint r0 = this.mSelectionPaint;
        if (r0 == null) goto L5;
        r0.setColor(r2);
        this.mSelectionAlpha = Color.alpha(r2);
    L5:
        Paint r02 = this.mRipplePaint;
        if (r02 == null) goto L9;
        r02.setColor(r2);
        this.mRippleAlpha = Color.alpha(r2);
        return;
    }
}

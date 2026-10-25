package com.exteragram.messenger.debug;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatTextView;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes4.dex */
public class DebugOverlayView extends AppCompatTextView {
    private final ContentBuilder contentBuilder;
    private DataSource dataSource;
    private long updateIntervalMs;
    private final Runnable updater;

    /* renamed from: com.exteragram.messenger.debug.DebugOverlayView$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ DebugOverlayView this$0;

        public AnonymousClass1(DebugOverlayView r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.this$0.isAttachedToWindow() == true) goto L5;
            return;
        L5:
            this.this$0.refresh();
            DebugOverlayView r0 = this.this$0;
            r0.postDelayed(this, DebugOverlayView.m1239$$Nest$fgetupdateIntervalMs(r0));
        }
    }

    public static class ContentBuilder {
        private final StringBuilder stringBuilder;

        public ContentBuilder() {
            this.stringBuilder = new StringBuilder(512);
        }

        public CharSequence build() {
            return this.stringBuilder;
        }

        public ContentBuilder kv(String r2, Object r3) {
            return line(r2 + "=" + r3);
        }

        public ContentBuilder line(CharSequence r3) {
            if (this.stringBuilder.length() <= 0) goto L5;
            this.stringBuilder.append('\n');
        L5:
            this.stringBuilder.append(r3);
            return this;
        }

        public ContentBuilder reset() {
            this.stringBuilder.setLength(0);
            return this;
        }

        public ContentBuilder section(CharSequence r3) {
            if (this.stringBuilder.length() <= 0) goto L5;
            this.stringBuilder.append('\n');
        L5:
            StringBuilder r0 = this.stringBuilder;
            r0.append('[');
            r0.append(r3);
            r0.append(']');
            return this;
        }

        public ContentBuilder title(CharSequence r1) {
            return line(r1);
        }
    }

    public interface DataSource {
        void build(ContentBuilder r1);
    }

    public static /* synthetic */ void $r8$lambda$J51jUXwUIS6u8jVugj_hBtcZtUU(DebugOverlayView r0, View r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: -$$Nest$fgetupdateIntervalMs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m1239$$Nest$fgetupdateIntervalMs(DebugOverlayView r2) {
        return r2.updateIntervalMs;
    }

    public DebugOverlayView(Context r5) {
        super(r5);
        this.contentBuilder = new ContentBuilder();
        this.updater = new AnonymousClass1(this);
        this.updateIntervalMs = 250;
        setTextColor(-1);
        setTextSize(10.0f);
        setTypeface(AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_MONO));
        setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        setGravity(51);
        setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(10.0f), -1342177280));
        setOnClickListener(new DebugOverlayView$$ExternalSyntheticLambda0(this));
        setLongClickable(false);
        setFocusable(false);
        setImportantForAccessibility(2);
    }

    public static FrameLayout.LayoutParams createLayoutParams() {
        FrameLayout.LayoutParams r0 = new FrameLayout.LayoutParams(AndroidUtilities.dp(220.0f), -2, 53);
        r0.setMargins(AndroidUtilities.dp(10.0f), AndroidUtilities.dp(AndroidUtilities.statusBarHeight) - ActionBar.getCurrentActionBarHeight(), AndroidUtilities.dp(10.0f), 0);
        return r0;
    }

    private /* synthetic */ void lambda$new$0(View r2) {
        if (this.dataSource != null) goto L5;
        return;
    L5:
        if (AndroidUtilities.addToClipboard(this.contentBuilder.build()) == true) goto L7;
        return;
    L7:
        if (AndroidUtilities.shouldShowClipboardToast() == false) goto L12;
        Toast.makeText(getContext(), LocaleController.getString(R.string.TextCopied), 0).show();
        return;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        removeCallbacks(this.updater);
        post(this.updater);
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.updater);
    }

    public void refresh() {
        if (this.dataSource != null) goto L6;
        setText(_UrlKt.FRAGMENT_ENCODE_SET);
        return;
    L6:
        this.contentBuilder.reset();
        this.dataSource.build(this.contentBuilder);
        setText(this.contentBuilder.build());
    }

    public void setDataSource(DataSource r1) {
        this.dataSource = r1;
        refresh();
    }

    public void setUpdateInterval(long r3) {
        this.updateIntervalMs = Math.max(16, r3);
    }
}

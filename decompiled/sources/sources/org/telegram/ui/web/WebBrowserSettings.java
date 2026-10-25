package org.telegram.ui.web;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.RadioColorCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.Text;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalFragment;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.web.BrowserHistory;

/* loaded from: classes6.dex */
public class WebBrowserSettings extends UniversalFragment implements NotificationCenter.NotificationCenterDelegate {
    private Drawable addIcon;
    private long cacheSize;
    public int clearCacheRow;
    public int clearCookiesRow;
    public int clearHistoryRow;
    public int clearListRow;
    private long cookiesSize;
    public int enableRow;
    public int historyRow;
    private long historySize;
    public int neverOpenRow;
    public int searchRow;
    private Utilities.Callback<BrowserHistory.Entry> whenHistoryClicked;

    /* renamed from: org.telegram.ui.web.WebBrowserSettings$1, reason: invalid class name */
    public class AnonymousClass1 extends CombinedDrawable {
        final /* synthetic */ WebBrowserSettings this$0;

        public AnonymousClass1(WebBrowserSettings r1, Drawable r2, Drawable r3) {
            this.this$0 = r1;
            super(r2, r3);
            this.translateX = AndroidUtilities.dp(2.0f);
        }

        @Override // org.telegram.ui.Components.CombinedDrawable, android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    public static class WebsiteView extends FrameLayout {
        private AnimatedEmojiDrawable animatedEmojiDrawable;
        private String domain;
        public final ImageView imageView;
        private boolean needDivider;
        public final ImageView optionsView;
        public final TextView subtitleView;
        public final TextView titleView;

        /* renamed from: org.telegram.ui.web.WebBrowserSettings$WebsiteView$1, reason: invalid class name */
        public class AnonymousClass1 extends TextView {
            final /* synthetic */ WebsiteView this$0;

            public AnonymousClass1(WebsiteView r1, Context r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // android.widget.TextView, android.view.View
            public void onMeasure(int r1, int r2) {
                super.onMeasure(r1, r2);
                this.this$0.subtitleView.setPivotY(getMeasuredHeight() / 2.0f);
            }
        }

        /* renamed from: org.telegram.ui.web.WebBrowserSettings$WebsiteView$2, reason: invalid class name */
        public class AnonymousClass2 extends Drawable {
            private final Text text;
            final /* synthetic */ WebsiteView this$0;
            final /* synthetic */ String val$s;

            public AnonymousClass2(WebsiteView r3, String r4) {
                this.this$0 = r3;
                this.val$s = r4;
                this.text = new Text(r4.substring(0, !r4.isEmpty() ? 1 : 0), 14.0f, AndroidUtilities.bold());
            }

            @Override // android.graphics.drawable.Drawable
            public void draw(Canvas r7) {
                this.text.draw(r7, getBounds().centerX() - (this.text.getCurrentWidth() / 2.0f), getBounds().centerY(), Theme.getColor(Theme.key_windowBackgroundWhiteBlackText), 1.0f);
            }

            @Override // android.graphics.drawable.Drawable
            public int getOpacity() {
                return -2;
            }

            @Override // android.graphics.drawable.Drawable
            public void setAlpha(int r1) {
            }

            @Override // android.graphics.drawable.Drawable
            public void setColorFilter(ColorFilter r1) {
            }
        }

        public static class Factory extends UItem.UItemFactory<WebsiteView> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem as(String r1, String r2, long r3) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.text = r1;
                r0.textValue = r2;
                r0.longValue = r3;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r3, UItem r4, boolean r5, UniversalAdapter r6, UniversalRecyclerView r7) {
                ((WebsiteView) r3).set(r4.textValue, (String) r4.text, r4.longValue, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public WebsiteView createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new WebsiteView(r1);
            }
        }

        /* renamed from: -$$Nest$fgetdomain, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m22063$$Nest$fgetdomain(WebsiteView r0) {
            return r0.domain;
        }

        public WebsiteView(Context r11) {
            super(r11);
            ImageView r0 = new ImageView(r11);
            this.imageView = r0;
            addView(r0, LayoutHelper.createFrame(32, 32.0f, 19, 16.0f, 0.0f, 0.0f, 0.0f));
            TextView r02 = new TextView(r11);
            this.titleView = r02;
            r02.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            r02.setTextSize(1, 16.0f);
            r02.setMaxLines(1);
            TextUtils.TruncateAt r1 = TextUtils.TruncateAt.END;
            r02.setEllipsize(r1);
            addView(r02, LayoutHelper.createFrame(-1, -2.0f, 55, 68.0f, 7.0f, 54.0f, 0.0f));
            AnonymousClass1 r03 = new AnonymousClass1(this, r11);
            this.subtitleView = r03;
            r03.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
            r03.setTextSize(1, 13.0f);
            r03.setMaxLines(1);
            r03.setEllipsize(r1);
            r03.setPivotX(0.0f);
            addView(r03, LayoutHelper.createFrame(-1, -2.0f, 55, 68.0f, 30.0f, 54.0f, 0.0f));
            ImageView r04 = new ImageView(r11);
            this.optionsView = r04;
            r04.setScaleType(ImageView.ScaleType.CENTER);
            r04.setImageResource(R.drawable.ic_ab_other);
            r04.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText3), PorterDuff.Mode.SRC_IN));
            addView(r04, LayoutHelper.createFrame(32, 32.0f, 21, 0.0f, 0.0f, 18.0f, 0.0f));
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r8) {
            super.dispatchDraw(r8);
            if (this.needDivider == false) goto L6;
            r8.drawRect(AndroidUtilities.dp(64.0f), getHeight() - 1, getWidth(), getHeight(), Theme.dividerPaint);
            return;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(56.0f), TLObject.FLAG_30));
        }

        public void set(CharSequence r3, String r4, long r5, boolean r7) {
            this.titleView.setText(r3);
            this.subtitleView.setText(r4);
            boolean r0 = TextUtils.isEmpty(r3);
            TextView r1 = this.subtitleView;
            if (r0 == false) goto L5;
            r1.setTranslationY(-AndroidUtilities.dp(14.0f));
            this.subtitleView.setScaleX(1.3f);
            this.subtitleView.setScaleY(1.3f);
        L6:
            this.domain = r4;
            if (TextUtils.isEmpty(r3) == true) goto L9;
        L15:
            String r32 = r3.toString();
            AnimatedEmojiDrawable r42 = this.animatedEmojiDrawable;
            if (r42 == null) goto L19;
            r42.removeView(this.imageView);
            this.animatedEmojiDrawable = null;
        L19:
            if (r5 == 0) goto L21;
            AnimatedEmojiDrawable r33 = AnimatedEmojiDrawable.make(UserConfig.selectedAccount, 1, r5);
            this.animatedEmojiDrawable = r33;
            r33.addView(this.imageView);
            this.imageView.setImageDrawable(this.animatedEmojiDrawable);
        L22:
            this.needDivider = r7;
            invalidate();
            return;
        L21:
            CombinedDrawable r43 = new CombinedDrawable(Theme.createRoundRectDrawable(AndroidUtilities.dp(6.0f), Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText), 0.1f)), new AnonymousClass2(this, r32));
            r43.setCustomSize(AndroidUtilities.dp(28.0f), AndroidUtilities.dp(28.0f));
            this.imageView.setImageDrawable(r43);
            goto L22
        L9:
            if (r4.isEmpty() == false) goto L11;
        L14:
            r3 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L15
        L11:
            if (TextUtils.isEmpty(r4) == true) goto L14;
            r3 = r4;
            goto L15
        L5:
            r1.setTranslationY(0.0f);
            this.subtitleView.setScaleX(1.0f);
            this.subtitleView.setScaleY(1.0f);
            goto L6
        }
    }

    /* renamed from: $r8$lambda$45gN2i-SUMdtJERaxSQNvVxtdyQ, reason: not valid java name */
    public static /* synthetic */ void m22059$r8$lambda$45gN2iSUMdtJERaxSQNvVxtdyQ(WebBrowserSettings r0, AlertDialog r1, int r2) {
        r0.lambda$onClick$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$GepSwmr4kQ43AzK81LMyTi6DmxE(WebBrowserSettings r0, HistoryFragment[] r1, BrowserHistory.Entry r2) {
        r0.lambda$onClick$6(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$IojxBGw8u4cPJJ6lYrSZVXmF2j0(WebBrowserSettings r0, String r1) {
        r0.lambda$onClick$8(r1);
    }

    /* renamed from: $r8$lambda$LR49Oo-2WkOLOw-D-u6n0GyV3q8, reason: not valid java name */
    public static /* synthetic */ void m22060$r8$lambda$LR49Oo2WkOLOwDu6n0GyV3q8(WebBrowserSettings r0, AlertDialog r1, int r2) {
        r0.lambda$onClick$7(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Nq7xKZry4fMLkgCd_NBKU7nQu8w(WebBrowserSettings r0, AlertDialog r1, int r2) {
        r0.lambda$onClick$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$QSGbbQLmD2SrEYN9V0Nl_qW1gDg(WebBrowserSettings r0, ArrayList r1) {
        r0.lambda$loadSizes$0(r1);
    }

    /* renamed from: $r8$lambda$REnl-YXtG6OLq9a2HmPfT2iuuxo, reason: not valid java name */
    public static /* synthetic */ void m22061$r8$lambda$REnlYXtG6OLq9a2HmPfT2iuuxo(WebBrowserSettings r0, boolean r1, String r2) {
        r0.lambda$onClick$10(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Z0O_h7keqXEgjsTwXt8MvJu9pV4(WebBrowserSettings r0, AlertDialog r1, int r2) {
        r0.lambda$onClick$3(r1, r2);
    }

    /* renamed from: $r8$lambda$gflDPD9fbUbhkMtt-bL08jP0MXU, reason: not valid java name */
    public static /* synthetic */ void m22062$r8$lambda$gflDPD9fbUbhkMttbL08jP0MXU(int r0, View r1, AtomicReference r2, View r3) {
        SharedConfig.setSearchEngineType(r0);
        ((TextCell) r1).setValue(SearchEngine.getCurrent().name, true);
        ((Dialog) r2.get()).dismiss();
    }

    public static /* synthetic */ void $r8$lambda$kV0AoX5rlmO7cYmRS5iPOUl7k2k(WebBrowserSettings r0, long r1, long r3) {
        r0.lambda$loadSizes$1(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$w9IiSLaaHdnaA0eQ7WoBhm3AeBY(WebBrowserSettings r0) {
        r0.lambda$loadSizes$2();
    }

    public WebBrowserSettings(Utilities.Callback<BrowserHistory.Entry> r1) {
        this.whenHistoryClicked = r1;
    }

    private static boolean deleteDirectory(File r10, Boolean r11) {
        if (r10 != null) goto L5;
    L31:
        return false;
    L5:
        if (r10.exists() == false) goto L31;
        if (r10.isDirectory() == false) goto L25;
        File[] r1 = r10.listFiles();
        if (r1 == null) goto L22;
        int r4 = r1.length;
        int r5 = 0;
        boolean r6 = true;
    L12:
        if (r5 >= r4) goto L23;
        File r7 = r1[r5];
        if (r11 == null) goto L19;
        if (r11.booleanValue() == r7.getName().startsWith("Cookies")) goto L19;
    L21:
        r5 = r5 + 1;
    L19:
        if (deleteDirectory(r7, r11) == true) goto L21;
        r6 = false;
    L23:
        if (r6 == false) goto L30;
        r10.delete();
    L30:
        return true;
    L22:
        r6 = true;
        goto L23
    L25:
        if (r11 != null) goto L27;
    L29:
        r10.delete();
        goto L30
    L27:
        if (r11.booleanValue() == r10.getName().startsWith("Cookies")) goto L29;
        return false;
    }

    private static long getDirectorySize(File r6, Boolean r7) {
        long r0 = 0;
        if (r6 != null) goto L5;
    L21:
        return 0;
    L5:
        if (r6.exists() == false) goto L21;
        if (r6.isDirectory() == false) goto L15;
        File[] r62 = r6.listFiles();
        if (r62 == null) goto L18;
        int r2 = r62.length;
        int r3 = 0;
    L12:
        if (r3 >= r2) goto L14;
        r0 = r0 + getDirectorySize(r62[r3], r7);
        r3 = r3 + 1;
        goto L12
    L14:
        return r0;
    L18:
        return 0;
    L15:
        if (r7 == null) goto L20;
        if (r7.booleanValue() != r6.getName().startsWith("Cookies")) goto L18;
    L20:
        return r6.length();
    }

    private /* synthetic */ void lambda$loadSizes$0(ArrayList r3) {
        this.historySize = r3.size();
        UniversalRecyclerView r32 = this.listView;
        if (r32 != null) goto L5;
        return;
    L5:
        if (r32.adapter != null) goto L7;
        return;
    L7:
        if (r32.isAttachedToWindow() == false) goto L12;
        this.listView.adapter.update(true);
        return;
    }

    private /* synthetic */ void lambda$loadSizes$1(long r1, long r3) {
        this.cacheSize = r1;
        this.cookiesSize = r3;
        UniversalRecyclerView r12 = this.listView;
        if (r12 != null) goto L5;
        return;
    L5:
        if (r12.adapter != null) goto L7;
        return;
    L7:
        if (r12.isAttachedToWindow() == false) goto L12;
        this.listView.adapter.update(true);
        return;
    }

    private /* synthetic */ void lambda$loadSizes$2() {
        File r0 = ApplicationLoader.applicationContext.getDatabasePath("webview.db");
        long r1 = 0;
        if (r0 != null) goto L5;
    L7:
        long r3 = 0;
    L8:
        File r02 = ApplicationLoader.applicationContext.getDatabasePath("webviewCache.db");
        if (r02 != null) goto L11;
    L13:
        File r03 = new File(ApplicationLoader.applicationContext.getApplicationInfo().dataDir, "app_webview");
        if (r03.exists() == false) goto L16;
        r3 = r3 + getDirectorySize(r03, Boolean.FALSE);
    L16:
        File r04 = new File(ApplicationLoader.applicationContext.getApplicationInfo().dataDir, "cache/WebView");
        if (r04.exists() == false) goto L19;
        r3 = r3 + getDirectorySize(r04, null);
    L19:
        long r9 = r3;
        File r05 = new File(ApplicationLoader.applicationContext.getApplicationInfo().dataDir, "app_webview");
        if (r05.exists() == false) goto L22;
        r1 = getDirectorySize(r05, Boolean.TRUE);
    L22:
        AndroidUtilities.runOnUIThread(new WebBrowserSettings$$ExternalSyntheticLambda10(this, r9, r1));
        return;
    L11:
        if (r02.exists() == false) goto L13;
        r3 = r3 + r02.length();
        goto L13
    L5:
        if (r0.exists() == false) goto L7;
        r3 = r0.length();
        goto L8
    }

    private /* synthetic */ void lambda$onClick$10(boolean r2, String r3) {
        getMessagesController().addWebBrowserException(r3, r2);
        this.listView.adapter.update(true);
    }

    private /* synthetic */ void lambda$onClick$3(AlertDialog r2, int r3) {
        ApplicationLoader.applicationContext.deleteDatabase("webview.db");
        ApplicationLoader.applicationContext.deleteDatabase("webviewCache.db");
        WebStorage.getInstance().deleteAllData();
        WebView r22 = new WebView(getContext());     // Catch: Exception -> L18
        r22.clearCache(true);     // Catch: Exception -> L18
        r22.clearHistory();     // Catch: Exception -> L18
        r22.destroy();     // Catch: Exception -> L18
    L21:
        File r23 = new File(ApplicationLoader.applicationContext.getApplicationInfo().dataDir, "app_webview");     // Catch: Exception -> L8
        if (r23.exists() == false) goto L23;
        deleteDirectory(r23, Boolean.FALSE);     // Catch: Exception -> L8
    L23:
        File r24 = new File(ApplicationLoader.applicationContext.getApplicationInfo().dataDir, "cache/WebView");     // Catch: Exception -> L14
        if (r24.exists() == false) goto L16;
        deleteDirectory(r24, null);     // Catch: Exception -> L14
    L16:
        WebMetadataCache.getInstance().clear();
        loadSizes();
        return;
    L14:
        e = move-exception;
        FileLog.e(e);
    L8:
        e = move-exception;
        FileLog.e(e);
        goto L23
    }

    private /* synthetic */ void lambda$onClick$4(AlertDialog r2, int r3) {
        CookieManager r22 = CookieManager.getInstance();
        r22.removeAllCookies(null);
        r22.flush();
        File r23 = new File(ApplicationLoader.applicationContext.getApplicationInfo().dataDir, "app_webview");     // Catch: Exception -> L7
        if (r23.exists() == false) goto L9;
        deleteDirectory(r23, Boolean.TRUE);     // Catch: Exception -> L7
    L9:
        loadSizes();
        return;
    L7:
        e = move-exception;
        FileLog.e(e);
        goto L9
    }

    private /* synthetic */ void lambda$onClick$5(AlertDialog r1, int r2) {
        BrowserHistory.clearHistory();
        this.historySize = 0;
        this.listView.adapter.update(true);
    }

    private /* synthetic */ void lambda$onClick$6(HistoryFragment[] r2, BrowserHistory.Entry r3) {
        r2[0].finishFragment();
        if (this.whenHistoryClicked == null) goto L6;
        finishFragment();
        this.whenHistoryClicked.run(r3);
        return;
    L6:
        Browser.openUrl(getContext(), r3.url);
    }

    private /* synthetic */ void lambda$onClick$7(AlertDialog r1, int r2) {
        getMessagesController().clearAllWebBrowserExceptions();
        this.listView.adapter.update(true);
    }

    private /* synthetic */ void lambda$onClick$8(String r2) {
        getMessagesController().removeWebBrowserException(r2);
        this.listView.adapter.update(true);
    }

    private void loadSizes() {
        if (BrowserHistory.getHistory(new WebBrowserSettings$$ExternalSyntheticLambda8(this)) == null) goto L11;
        this.historySize = r0.size();
        UniversalRecyclerView r0 = this.listView;
        if (r0 == null) goto L11;
        if (r0.adapter == null) goto L11;
        if (r0.isAttachedToWindow() == false) goto L11;
        this.listView.adapter.update(true);
    L11:
        Utilities.globalQueue.postRunnable(new WebBrowserSettings$$ExternalSyntheticLambda9(this));
    }

    @Override // org.telegram.ui.Components.UniversalFragment, org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r6) {
        Drawable r0 = r6.getResources().getDrawable(R.drawable.poll_add_circle).mutate();
        Drawable r1 = r6.getResources().getDrawable(R.drawable.poll_add_plus).mutate();
        int r3 = getThemedColor(Theme.key_switchTrackChecked);
        PorterDuff.Mode r4 = PorterDuff.Mode.MULTIPLY;
        r0.setColorFilter(new PorterDuffColorFilter(r3, r4));
        r1.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_checkboxCheck), r4));
        this.addIcon = new AnonymousClass1(this, r0, r1);
        this.fragmentView = super.createView(r6);
        this.listView.setSections();
        this.actionBar.setAdaptiveBackground(this.listView);
        return this.fragmentView;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.webBrowserSettingsUpdate) goto L8;
        UniversalRecyclerView r0 = this.listView;
        if (r0 == null) goto L9;
        r0.adapter.update(true);
        return;
    L9:
        return;
    }

    @Override // org.telegram.ui.Components.UniversalFragment
    public void fillItems(ArrayList<UItem> r11, UniversalAdapter r12) {
        this.enableRow = -1;
        this.clearCookiesRow = -1;
        this.clearCacheRow = -1;
        this.historyRow = -1;
        this.clearHistoryRow = -1;
        this.clearListRow = -1;
        this.searchRow = -1;
        boolean r122 = getMessagesController().isWebBrowserInAppEnabled();
        this.enableRow = r11.size();
        r11.add(UItem.asRippleCheck(1, LocaleController.getString(R.string.BrowserSettingsEnable)).setChecked(r122));
        r11.add(UItem.asShadow(LocaleController.getString(R.string.BrowserSettingsEnableInfo)));
        if (r122 == true) goto L12;
        getMessagesController().isWebBrowserUseCustomTabs();
        r11.add(UItem.asCheck(17, LocaleController.getString(R.string.WebBrowserShowCloseButton)).setChecked(getMessagesController().isWebBrowserUseCustomTabs()));
        r11.add(UItem.asShadow(LocaleController.getString(R.string.WebBrowserShowCloseButtonInfo)));
        r11.add(UItem.asHeader(LocaleController.getString(R.string.BrowserSettingsAlwaysOpenInTitle2)));
        this.neverOpenRow = r11.size();
        r11.add(UItem.asButton(16, this.addIcon, LocaleController.getString(R.string.BrowserSettingsNeverOpenInAdd)).accent());
        List<TL_account.WebDomainException> r123 = getMessagesController().getWebBrowserExceptionsList(false);
        Iterator<TL_account.WebDomainException> r1 = r123.iterator();
    L6:
        if (r1.hasNext() == false) goto L8;
        TL_account.WebDomainException r3 = r1.next();
        r11.add(WebsiteView.Factory.as(r3.domain, r3.title, r3.favicon));
        goto L6
    L8:
        r11.add(UItem.asShadow(LocaleController.getString(R.string.BrowserSettingsAlwaysOpenInInfo2)));
        if (r123.isEmpty() == true) goto L35;
        this.clearListRow = r11.size();
        r11.add(UItem.asButton(5, LocaleController.getString(R.string.BrowserSettingsNeverOpenInClearList2)).red());
        r11.add(UItem.asShadow(null));
        return;
    L35:
        return;
    L12:
        this.clearCookiesRow = r11.size();
        int r124 = R.drawable.menu_clear_cookies;
        String r32 = LocaleController.getString(R.string.BrowserSettingsCookiesClear);
        long r4 = this.cookiesSize;
        String r9 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r4 <= 0) goto L15;
        String r42 = AndroidUtilities.formatFileSize(r4);
    L16:
        r11.add(UItem.asButton(3, r124, r32, r42));
        this.clearCacheRow = r11.size();
        int r125 = R.drawable.menu_clear_cache;
        String r33 = LocaleController.getString(R.string.BrowserSettingsCacheClear);
        long r43 = this.cacheSize;
        if (r43 <= 0) goto L19;
        r9 = AndroidUtilities.formatFileSize(r43);
    L19:
        r11.add(UItem.asButton(2, r125, r33, r9));
        r11.add(UItem.asShadow(LocaleController.getString(R.string.BrowserSettingsCookiesInfo)));
        if (this.historySize <= 0) goto L22;
        this.historyRow = r11.size();
        r11.add(UItem.asButton(9, R.drawable.menu_clear_recent, LocaleController.getString(R.string.BrowserSettingsHistoryShow)));
        this.clearHistoryRow = r11.size();
        r11.add(UItem.asButton(7, R.drawable.menu_clear_cache, LocaleController.getString(R.string.BrowserSettingsHistoryClear), LocaleController.formatPluralStringComma("BrowserSettingsHistoryPages", (int) this.historySize, ',')));
        r11.add(UItem.asShadow(null));
    L22:
        r11.add(UItem.asHeader(LocaleController.getString(R.string.BrowserSettingsNeverOpenInTitle2)));
        this.neverOpenRow = r11.size();
        r11.add(UItem.asButton(15, this.addIcon, LocaleController.getString(R.string.BrowserSettingsNeverOpenInAdd)).accent());
        List<TL_account.WebDomainException> r126 = getMessagesController().getWebBrowserExceptionsList(true);
        Iterator<TL_account.WebDomainException> r13 = r126.iterator();
    L24:
        if (r13.hasNext() == false) goto L26;
        TL_account.WebDomainException r34 = r13.next();
        r11.add(WebsiteView.Factory.as(r34.domain, r34.title, r34.favicon));
        goto L24
    L26:
        r11.add(UItem.asShadow(LocaleController.getString(R.string.BrowserSettingsNeverOpenInInfo2)));
        if (r126.isEmpty() == true) goto L29;
        this.clearListRow = r11.size();
        r11.add(UItem.asButton(5, LocaleController.getString(R.string.BrowserSettingsNeverOpenInClearList2)).red());
        r11.add(UItem.asShadow(null));
    L29:
        this.searchRow = r11.size();
        r11.add(UItem.asButton(6, R.drawable.msg_search, LocaleController.getString(R.string.SearchEngine), SearchEngine.getCurrent().name));
        r11.add(UItem.asShadow(LocaleController.getString(R.string.BrowserSettingsSearchEngineInfo)));
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L36;
        r11.add(UItem.asCheck(12, "adaptable colors").setChecked(SharedConfig.adaptableColorInBrowser));
        r11.add(UItem.asCheck(13, "only local IV").setChecked(SharedConfig.onlyLocalInstantView));
        return;
    L36:
        return;
    L15:
        r42 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L16
    }

    @Override // org.telegram.ui.Components.UniversalFragment
    public CharSequence getTitle() {
        return LocaleController.getString(R.string.BrowserSettingsTitle);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isLightStatusBar() {
        return super.isLightStatusBar();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.Components.UniversalFragment
    public void onClick(UItem r11, View r12, int r13, float r14, float r15) {
        int r132 = r11.id;
        if (r132 != 12) goto L7;
        SharedConfig.toggleBrowserAdaptableColors();
        ((TextCheckCell) r12).setChecked(SharedConfig.adaptableColorInBrowser);
        return;
    L7:
        if (r132 != 13) goto L11;
        SharedConfig.toggleLocalInstantView();
        ((TextCheckCell) r12).setChecked(SharedConfig.onlyLocalInstantView);
        return;
    L11:
        if (r132 != 17) goto L14;
        boolean r112 = !getMessagesController().isWebBrowserUseCustomTabs();
        getMessagesController().toggleWebBrowserUseCustomTabs(r112);
        ((TextCheckCell) r12).setChecked(r112);
        this.listView.adapter.update(true);
        return;
    L14:
        if (r132 != 1) goto L22;
        getMessagesController().toggleWebBrowserInAppEnabled();
        boolean r113 = getMessagesController().isWebBrowserInAppEnabled();
        TextCheckCell r122 = (TextCheckCell) r12;
        r122.setChecked(r113);
        if (r113 == false) goto L18;
        int r133 = Theme.key_windowBackgroundChecked;
    L19:
        r122.setBackgroundColorAnimated(r113, Theme.getColor(r133));
        this.listView.adapter.update(true);
        return;
    L18:
        r133 = Theme.key_windowBackgroundUnchecked;
        goto L19
    L22:
        if (r132 != 10) goto L25;
        getMessagesController().toggleWebBrowserUseCustomTabs(true);
        this.listView.adapter.update(true);
        return;
    L25:
        int r0 = 0;
        if (r132 != 11) goto L29;
        getMessagesController().toggleWebBrowserUseCustomTabs(false);
        this.listView.adapter.update(true);
        return;
    L29:
        String r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r132 != 2) goto L38;
        AlertDialog.Builder r114 = new AlertDialog.Builder(getContext(), getResourceProvider()).setTitle(LocaleController.getString(R.string.BrowserSettingsCacheClear));
        int r123 = R.string.BrowserSettingsCacheClearText;
        if (this.cacheSize == 0) goto L35;
        r2 = " (" + AndroidUtilities.formatFileSize(this.cacheSize) + ")";
    L35:
        r114.setMessage(LocaleController.formatString(r123, new Object[]{r2})).setPositiveButton(LocaleController.getString(R.string.Clear), new WebBrowserSettings$$ExternalSyntheticLambda0(this)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).makeRed(-1).show();
        return;
    L38:
        if (r132 != 3) goto L46;
        AlertDialog.Builder r115 = new AlertDialog.Builder(getContext(), getResourceProvider()).setTitle(LocaleController.getString(R.string.BrowserSettingsCookiesClear));
        int r124 = R.string.BrowserSettingsCookiesClearText;
        if (this.cookiesSize == 0) goto L43;
        r2 = " (" + AndroidUtilities.formatFileSize(this.cookiesSize) + ")";
    L43:
        r115.setMessage(LocaleController.formatString(r124, new Object[]{r2})).setPositiveButton(LocaleController.getString(R.string.Clear), new WebBrowserSettings$$ExternalSyntheticLambda1(this)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).makeRed(-1).show();
        return;
    L46:
        if (r132 != 7) goto L53;
        ArrayList<BrowserHistory.Entry> r116 = BrowserHistory.getHistory();
        int r125 = r116.size();
        long r134 = LongCompanionObject.MAX_VALUE;
    L48:
        if (r0 >= r125) goto L50;
        BrowserHistory.Entry r152 = r116.get(r0);
        r0 = r0 + 1;
        r134 = Math.min(r134, r152.time);
        goto L48
    L50:
        new AlertDialog.Builder(getContext(), getResourceProvider()).setTitle(LocaleController.getString(R.string.BrowserSettingsHistoryClear)).setMessage(LocaleController.formatString(R.string.BrowserSettingsHistoryClearText, new Object[]{LocaleController.formatDateChat(r134 / 1000)})).setPositiveButton(LocaleController.getString(R.string.Clear), new WebBrowserSettings$$ExternalSyntheticLambda2(this)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).makeRed(-1).show();
        return;
    L53:
        if (r132 != 9) goto L57;
        HistoryFragment[] r117 = {null};
        HistoryFragment r126 = new HistoryFragment(null, new WebBrowserSettings$$ExternalSyntheticLambda3(this, r117));
        r117[0] = r126;
        presentFragment(r126);
        return;
    L57:
        if (r132 != 5) goto L61;
        new AlertDialog.Builder(getContext(), getResourceProvider()).setTitle(LocaleController.getString(R.string.WebBrowserDeleteAllExceptionsTitle)).setMessage(LocaleController.getString(R.string.WebBrowserDeleteAllExceptionsMessage)).setPositiveButton(LocaleController.getString(R.string.Delete), new WebBrowserSettings$$ExternalSyntheticLambda4(this)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).makeRed(-1).show();
        return;
    L61:
        if (r11.instanceOf(WebsiteView.Factory.class) == false) goto L64;
        WebsiteView r127 = (WebsiteView) r12;
        ItemOptions.makeOptions((ViewGroup) this.fragmentView, r127).setDimAlpha(40).add(R.drawable.menu_delete_old, LocaleController.getString(R.string.Remove), new WebBrowserSettings$$ExternalSyntheticLambda5(this, WebsiteView.m22063$$Nest$fgetdomain(r127))).show();
        return;
    L64:
        int r118 = r11.id;
        if (r118 != 6) goto L79;
        if (getParentActivity() == null) goto L94;
        AtomicReference r119 = new AtomicReference();
        LinearLayout r135 = new LinearLayout(getContext());
        r135.setOrientation(1);
        ArrayList<SearchEngine> r142 = SearchEngine.getSearchEngines();
        int r1 = r142.size();
        CharSequence[] r22 = new CharSequence[r1];
        int r3 = 0;
    L70:
        if (r3 >= r1) goto L76;
        r22[r3] = r142.get(r3).name;
        RadioColorCell r4 = new RadioColorCell(getParentActivity());
        r4.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
        r4.setCheckColor(Theme.getColor(Theme.key_radioBackground), Theme.getColor(Theme.key_dialogRadioBackgroundChecked));
        CharSequence r6 = r22[r3];
        if (r3 != SharedConfig.searchEngineType) goto L74;
        boolean r8 = true;
    L75:
        r4.setTextAndValue(r6, r8);
        r4.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector), 2));
        r135.addView(r4);
        r4.setOnClickListener(new WebBrowserSettings$$ExternalSyntheticLambda6(r3, r12, r119));
        r3 = r3 + 1;
        goto L70
    L74:
        r8 = false;
        goto L75
    L76:
        AlertDialog r128 = new AlertDialog.Builder(getParentActivity()).setTitle(LocaleController.getString(R.string.SearchEngine)).setView(r135).setNegativeButton(LocaleController.getString(R.string.Cancel), null).create();
        r119.set(r128);
        showDialog(r128);
        return;
    L94:
        return;
    L79:
        if (r118 != 15) goto L81;
    L84:
        boolean r1110 = getMessagesController().isWebBrowserInAppEnabled();
        if (getMessagesController().isWebBrowserExceptionsLimitReached(r1110) == false) goto L88;
        AlertsCreator.showSimpleAlert(this, LocaleController.getString(R.string.WebBrowserExceptionsLimitTitle), LocaleController.getString(R.string.WebBrowserExceptionsLimitMessage));
        return;
    L88:
        AlertsCreator.showAddBrowserException(getContext(), getResourceProvider(), r1110, new WebBrowserSettings$$ExternalSyntheticLambda7(this, r1110));
        return;
    L81:
        if (r118 == 16) goto L84;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        loadSizes();
        getNotificationCenter().addObserver(this, NotificationCenter.webBrowserSettingsUpdate);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        getNotificationCenter().removeObserver(this, NotificationCenter.webBrowserSettingsUpdate);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        super.onInsets(r1, r2, r3, r4);
        UniversalRecyclerView r12 = this.listView;
        r12.setPadding(0, r12.getPaddingTop(), 0, r4);
        this.listView.setClipToPadding(false);
    }

    @Override // org.telegram.ui.Components.UniversalFragment
    public boolean onLongClick(UItem r1, View r2, int r3, float r4, float r5) {
        return false;
    }
}

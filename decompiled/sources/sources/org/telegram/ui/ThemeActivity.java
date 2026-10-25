package org.telegram.ui;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.annotation.Keep;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.appicons.AppIcon;
import com.exteragram.messenger.appicons.AppIconController;
import com.exteragram.messenger.appicons.AppIconPreviewDrawable;
import com.exteragram.messenger.appicons.AppIconPreviewLoader;
import com.exteragram.messenger.appicons.ui.AppIconsActivity;
import com.google.android.gms.cast.MediaError;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.time.SunDate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.EmojiThemes;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.MonetAccentHelper;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeColors;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.BrightnessControlCell;
import org.telegram.ui.Cells.ChatListCell;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.NotificationsCheckCell;
import org.telegram.ui.Cells.RadioButtonCell;
import org.telegram.ui.Cells.RadioColorCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Cells.ThemePreviewMessagesCell;
import org.telegram.ui.Cells.ThemeTypeCell;
import org.telegram.ui.Cells.ThemesHorizontalListCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.PermissionRequest;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SeekBarView;
import org.telegram.ui.Components.ShareAlert;
import org.telegram.ui.Components.SimpleThemeDescription;
import org.telegram.ui.Components.SwipeGestureSettingsView;
import org.telegram.ui.Components.TextHelper;
import org.telegram.ui.Components.ThemeEditorView;
import org.telegram.ui.PeerColorActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.bots.BotWebViewSheet;
import org.telegram.ui.bots.WebViewRequestProps;
import org.telegram.ui.web.SearchEngine;
import org.telegram.ui.web.WebBrowserSettings;

/* loaded from: classes6.dex */
public class ThemeActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {

    @Keep
    private int appIconSelectorRow;
    private int appIconShadowRow;
    private int automaticBrightnessInfoRow;
    private int automaticBrightnessRow;
    private int automaticHeaderRow;

    @Keep
    private int backgroundRow;
    private int bluetoothScoRow;
    private int browseThemesRow;

    @Keep
    private int browserRow;
    private int bubbleRadiusHeaderRow;
    private int bubbleRadiusInfoRow;

    @Keep
    private int bubbleRadiusRow;

    @Keep
    private int changeUserColor;
    private int chatBlurRow;
    private int chatListHeaderRow;
    private int chatListInfoRow;
    private int chatListRow;
    private int contactsReimportRow;
    private int contactsSortRow;

    @Keep
    private int createNewThemeRow;
    private int currentType;
    private ArrayList<Theme.ThemeInfo> darkThemes;
    private ArrayList<Theme.ThemeInfo> defaultThemes;
    private int directShareRow;
    private int distanceRow;
    private int editThemeRow;
    private int enableAnimationsRow;
    private GpsLocationListener gpsLocationListener;
    boolean hasThemeAccents;
    private boolean highlightSensitiveRow;
    boolean lastIsDarkTheme;
    private int lastShadowRow;
    private LinearLayoutManager layoutManager;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private int liteModeInfoRow;

    @Keep
    private int liteModeRow;
    private int mediaSoundHeaderRow;
    private int mediaSoundSectionRow;
    private ActionBarMenuItem menuItem;
    private GpsLocationListener networkLocationListener;
    private int newThemeInfoRow;
    private int nextMediaTapRow;
    private int nightAutomaticRow;
    private int nightDisabledRow;
    private int nightScheduledRow;
    private int nightSystemDefaultRow;

    @Keep
    private int nightThemeRow;
    private int nightTypeInfoRow;
    private int otherHeaderRow;
    private int otherSectionRow;

    @Keep
    private int pauseOnMediaRow;

    @Keep
    private int pauseOnRecordRow;
    private int preferedHeaderRow;
    private boolean previousByLocation;
    private int previousUpdatedType;

    @Keep
    private int raiseToListenRow;
    private int raiseToSpeakRow;
    private int rowCount;
    private int saveToGalleryOption1Row;
    private int saveToGalleryOption2Row;
    private int saveToGallerySectionRow;
    private int scheduleFromRow;
    private int scheduleFromToInfoRow;
    private int scheduleHeaderRow;
    private int scheduleLocationInfoRow;
    private int scheduleLocationRow;
    private int scheduleToRow;
    private int scheduleUpdateLocationRow;
    private int searchEngineRow;
    private int selectThemeHeaderRow;
    private int sendByEnterRow;

    @Keep
    private int sensitiveContentRow;
    private int settings2Row;
    private int settingsRow;
    private Theme.ThemeAccent sharingAccent;
    private AlertDialog sharingProgressDialog;
    private Theme.ThemeInfo sharingTheme;
    private int stickersInfoRow;

    @Keep
    private int stickersRow;
    private int stickersSectionRow;
    private RLottieDrawable sunDrawable;
    private int swipeGestureHeaderRow;
    private int swipeGestureInfoRow;
    private int swipeGestureRow;
    private int textSizeHeaderRow;

    @Keep
    private int textSizeRow;
    private int themeAccentListRow;
    private int themeHeaderRow;
    private int themeInfoRow;
    private int themeListRow;
    private int themeListRow2;
    private int themePreviewRow;
    private ThemesHorizontalListCell themesHorizontalListCell;
    private boolean updateDistance;
    private boolean updateRecordViaSco;
    private boolean updateSearchEngine;
    private boolean updatingLocation;

    /* renamed from: org.telegram.ui.ThemeActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ ThemeActivity this$0;

        public static /* synthetic */ void $r8$lambda$K1jFfbkX8WOVFqR7rKRQUUBC10Y(AnonymousClass1 r0, AlertDialog r1, int r2) {
            r0.lambda$onItemClick$0(r1, r2);
        }

        public AnonymousClass1(ThemeActivity r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onItemClick$0(AlertDialog r5, int r6) {
            ThemeActivity r52 = this.this$0;
            if (AndroidUtilities.isTablet() == false) goto L5;
            int r62 = 18;
        L6:
            boolean r53 = ThemeActivity.m20713$$Nest$msetFontSize(r52, r62);
            boolean r1 = true;
            if (ThemeActivity.m20712$$Nest$msetBubbleRadius(this.this$0, 17, true) == false) goto L9;
            r53 = true;
        L9:
            if (r53 == false) goto L12;
            ThemeActivity.m20649$$Nest$fgetlistAdapter(this.this$0).notifyItemChanged(ThemeActivity.m20695$$Nest$fgettextSizeRow(this.this$0), new Object());
            ThemeActivity.m20649$$Nest$fgetlistAdapter(this.this$0).notifyItemChanged(ThemeActivity.m20632$$Nest$fgetbubbleRadiusRow(this.this$0), new Object());
        L12:
            if (ThemeActivity.m20702$$Nest$fgetthemesHorizontalListCell(this.this$0) == null) goto L30;
            Theme.ThemeInfo r54 = Theme.getTheme("Blue");
            Theme.ThemeInfo r63 = Theme.getCurrentTheme();
            Theme.ThemeAccent r0 = r54.themeAccentsMap.get(Theme.DEFALT_THEME_ACCENT_ID);
            if (r0 == null) goto L17;
            Theme.OverrideWallpaperInfo r2 = new Theme.OverrideWallpaperInfo();
            r2.slug = "d";
            r2.fileName = "Blue_99_wp.jpg";
            r2.originalFileName = "Blue_99_wp.jpg";
            r0.overrideWallpaper = r2;
            r54.setOverrideWallpaper(r2);
        L17:
            if (r54 == r63) goto L21;
            r54.setCurrentAccentId(Theme.DEFALT_THEME_ACCENT_ID);
            Theme.saveThemeAccents(r54, true, false, true, false);
            ThemeActivity.m20702$$Nest$fgetthemesHorizontalListCell(this.this$0).selectTheme(r54);
            ThemeActivity.m20702$$Nest$fgetthemesHorizontalListCell(this.this$0).smoothScrollToPosition(0);
            return;
        L21:
            if (r54.currentAccentId == Theme.DEFALT_THEME_ACCENT_ID) goto L28;
            NotificationCenter r55 = NotificationCenter.getGlobalInstance();
            int r22 = NotificationCenter.needSetDayNightTheme;
            if (ThemeActivity.m20641$$Nest$fgetcurrentType(this.this$0) == 1) goto L26;
            r1 = false;
        L26:
            r55.postNotificationName(r22, new Object[]{r63, Boolean.valueOf(r1), null, Integer.valueOf(Theme.DEFALT_THEME_ACCENT_ID)});
            ThemeActivity.m20649$$Nest$fgetlistAdapter(this.this$0).notifyItemChanged(ThemeActivity.m20696$$Nest$fgetthemeAccentListRow(this.this$0));
            return;
        L28:
            Theme.reloadWallpaper(true);
            return;
        L30:
            return;
        L5:
            r62 = 16;
            goto L6
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r12) {
            if (r12 != (-1)) goto L7;
            this.this$0.finishFragment();
            return;
        L7:
            if (r12 != 1) goto L11;
            ThemeActivity.m20709$$Nest$mcreateNewTheme(this.this$0);
            return;
        L11:
            if (r12 != 2) goto L19;
            Theme.ThemeAccent r122 = Theme.getCurrentTheme().getAccent(false);
            TLRPC.TL_theme r0 = r122.info;
            ThemeActivity r1 = this.this$0;
            if (r0 != null) goto L16;
            r1.getMessagesController().saveThemeToServer(r122.parentTheme, r122);
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needShareTheme, new Object[]{r122.parentTheme, r122});
            return;
        L16:
            String r4 = "https://" + r1.getMessagesController().linkPrefix + "/addtheme/" + r122.info.slug;
            this.this$0.showDialog(new ShareAlert(this.this$0.getParentActivity(), null, r4, false, r4, false));
            return;
        L19:
            if (r12 != 3) goto L23;
            ThemeActivity.m20710$$Nest$meditTheme(this.this$0);
            return;
        L23:
            if (r12 != 4) goto L32;
            if (this.this$0.getParentActivity() == null) goto L64;
            AlertDialog.Builder r123 = new AlertDialog.Builder(this.this$0.getParentActivity());
            r123.setTitle(LocaleController.getString("ThemeResetToDefaultsTitle", R.string.ThemeResetToDefaultsTitle));
            r123.setMessage(LocaleController.getString("ThemeResetToDefaultsText", R.string.ThemeResetToDefaultsText));
            r123.setPositiveButton(LocaleController.getString("Reset", R.string.Reset), new ThemeActivity$1$$ExternalSyntheticLambda0(this));
            r123.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
            AlertDialog r124 = r123.create();
            this.this$0.showDialog(r124);
            TextView r11 = (TextView) r124.getButton(-1);
            if (r11 == null) goto L62;
            r11.setTextColor(Theme.getColor(Theme.key_text_RedBold));
            return;
        L62:
            return;
        L64:
            return;
        L32:
            if (r12 != 5) goto L63;
            SharedPreferences r125 = ApplicationLoader.applicationContext.getSharedPreferences("themeconfig", 0);
            String r5 = "Blue";
            String r42 = r125.getString("lastDayTheme", "Blue");
            if (Theme.getTheme(r42) != null) goto L36;
        L37:
            r42 = "Blue";
        L38:
            String r7 = "Dark Blue";
            String r126 = r125.getString("lastDarkTheme", "Dark Blue");
            if (Theme.getTheme(r126) != null) goto L41;
        L42:
            r126 = "Dark Blue";
        L43:
            Theme.ThemeInfo r6 = Theme.getActiveTheme();
            if (r42.equals(r126) == true) goto L46;
            r7 = r126;
        L52:
            r5 = r42;
        L55:
            boolean r127 = r5.equals(r6.getKey());
            if (r127 == false) goto L59;
            Theme.ThemeInfo r43 = Theme.getTheme(r7);
        L58:
            Theme.ThemeInfo r52 = r43;
            int[] r72 = new int[2];
            ThemeActivity.m20655$$Nest$fgetmenuItem(this.this$0).getIconView().getLocationInWindow(r72);
            r72[0] = r72[0] + (ThemeActivity.m20655$$Nest$fgetmenuItem(this.this$0).getIconView().getMeasuredWidth() / 2);
            r72[1] = r72[1] + (ThemeActivity.m20655$$Nest$fgetmenuItem(this.this$0).getIconView().getMeasuredHeight() / 2);
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needSetDayNightTheme, new Object[]{r52, Boolean.FALSE, r72, -1, Boolean.valueOf(r127), ThemeActivity.m20655$$Nest$fgetmenuItem(this.this$0).getIconView()});
            ThemeActivity.m20715$$Nest$mupdateRows(this.this$0, true);
            Theme.turnOffAutoNight(this.this$0);
            return;
        L59:
            r43 = Theme.getTheme(r5);
            goto L58
        L46:
            if (r6.isDark() == false) goto L48;
        L53:
            r7 = r126;
            goto L55
        L48:
            if (r42.equals("Dark Blue") == true) goto L53;
            if (r42.equals("Night") == false) goto L52;
        L41:
            if (Theme.getTheme(r126).isDark() == true) goto L43;
        L36:
            if (Theme.getTheme(r42).isDark() == false) goto L38;
        }
    }

    public static class AccentDividerView extends View {
        public AccentDividerView(Context r1) {
            super(r1);
            setWillNotDraw(false);
        }

        @Override // android.view.View
        public void onDraw(Canvas r9) {
            float r3 = getMeasuredWidth() / 2.0f;
            r9.drawLine(r3, AndroidUtilities.dp(14.0f), r3, getMeasuredHeight() - AndroidUtilities.dp(14.0f), Theme.dividerPaint);
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(12.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(62.0f), TLObject.FLAG_30));
        }
    }

    public class BubbleRadiusCell extends FrameLayout {
        private int endRadius;
        private SeekBarView sizeBar;
        private int startRadius;
        private TextPaint textPaint;
        final /* synthetic */ ThemeActivity this$0;

        /* renamed from: org.telegram.ui.ThemeActivity$BubbleRadiusCell$1, reason: invalid class name */
        public class AnonymousClass1 implements SeekBarView.SeekBarViewDelegate {
            final /* synthetic */ BubbleRadiusCell this$1;
            final /* synthetic */ ThemeActivity val$this$0;

            public AnonymousClass1(BubbleRadiusCell r1, ThemeActivity r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
            public CharSequence getContentDescription() {
                return String.valueOf(Math.round(BubbleRadiusCell.m20719$$Nest$fgetstartRadius(this.this$1) + ((BubbleRadiusCell.m20717$$Nest$fgetendRadius(this.this$1) - BubbleRadiusCell.m20719$$Nest$fgetstartRadius(this.this$1)) * BubbleRadiusCell.m20718$$Nest$fgetsizeBar(this.this$1).getProgress())));
            }

            @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
            public int getStepsCount() {
                return BubbleRadiusCell.m20717$$Nest$fgetendRadius(this.this$1) - BubbleRadiusCell.m20719$$Nest$fgetstartRadius(this.this$1);
            }

            @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
            public void onSeekBarDrag(boolean r3, float r4) {
                ThemeActivity.m20712$$Nest$msetBubbleRadius(this.this$1.this$0, Math.round(BubbleRadiusCell.m20719$$Nest$fgetstartRadius(r3) + ((BubbleRadiusCell.m20717$$Nest$fgetendRadius(this.this$1) - BubbleRadiusCell.m20719$$Nest$fgetstartRadius(this.this$1)) * r4)), false);
            }

            @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
            public void onSeekBarPressed(boolean r1) {
            }
        }

        /* renamed from: -$$Nest$fgetendRadius, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m20717$$Nest$fgetendRadius(BubbleRadiusCell r0) {
            return r0.endRadius;
        }

        /* renamed from: -$$Nest$fgetsizeBar, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SeekBarView m20718$$Nest$fgetsizeBar(BubbleRadiusCell r0) {
            return r0.sizeBar;
        }

        /* renamed from: -$$Nest$fgetstartRadius, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m20719$$Nest$fgetstartRadius(BubbleRadiusCell r0) {
            return r0.startRadius;
        }

        public BubbleRadiusCell(ThemeActivity r8, Context r9) {
            this.this$0 = r8;
            super(r9);
            this.startRadius = 0;
            this.endRadius = 17;
            setWillNotDraw(false);
            TextPaint r0 = new TextPaint(1);
            this.textPaint = r0;
            r0.setTextSize(AndroidUtilities.dp(16.0f));
            SeekBarView r02 = new SeekBarView(r9);
            this.sizeBar = r02;
            r02.setReportChanges(true);
            this.sizeBar.setSeparatorsCount((this.endRadius - this.startRadius) + 1);
            this.sizeBar.setDelegate(new AnonymousClass1(this, r8));
            this.sizeBar.setImportantForAccessibility(2);
            addView(this.sizeBar, LayoutHelper.createFrame(-1, 38.0f, 51, 5.0f, 5.0f, 39.0f, 0.0f));
        }

        @Override // android.view.View
        public void invalidate() {
            super.invalidate();
            this.sizeBar.invalidate();
        }

        @Override // android.view.View
        public void onDraw(Canvas r4) {
            this.textPaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteValueText));
            r4.drawText(_UrlKt.FRAGMENT_ENCODE_SET + SharedConfig.bubbleRadius, getMeasuredWidth() - AndroidUtilities.dp(39.0f), AndroidUtilities.dp(28.0f), this.textPaint);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
            super.onInitializeAccessibilityNodeInfo(r2);
            this.sizeBar.getSeekBarAccessibilityDelegate().onInitializeAccessibilityNodeInfoInternal(this, r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
            SeekBarView r22 = this.sizeBar;
            int r32 = SharedConfig.bubbleRadius;
            int r0 = this.startRadius;
            r22.setProgress((r32 - r0) / (this.endRadius - r0));
        }

        @Override // android.view.View
        public boolean performAccessibilityAction(int r2, Bundle r3) {
            if (super.performAccessibilityAction(r2, r3) == false) goto L5;
            return true;
        L5:
            if (this.sizeBar.getSeekBarAccessibilityDelegate().performAccessibilityActionInternal(this, r2, r3) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    public class GpsLocationListener implements LocationListener {
        final /* synthetic */ ThemeActivity this$0;

        private GpsLocationListener(ThemeActivity r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ GpsLocationListener(ThemeActivity r1, ThemeActivityIA r2) {
            this(r1);
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location r2) {
            if (r2 != null) goto L4;
            return;
        L4:
            ThemeActivity.m20714$$Nest$mstopLocationUpdate(this.this$0);
            ThemeActivity.m20716$$Nest$mupdateSunTime(this.this$0, r2, false);
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String r1) {
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String r1) {
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String r1, int r2, Bundle r3) {
        }
    }

    public static class InnerAccentView extends View {
        private ObjectAnimator checkAnimator;
        private boolean checked;
        private float checkedState;
        private Theme.ThemeAccent currentAccent;
        private Theme.ThemeInfo currentTheme;
        private final Paint paint;

        public InnerAccentView(Context r2) {
            super(r2);
            this.paint = new Paint(1);
        }

        @Keep
        public float getCheckedState() {
            return this.checkedState;
        }

        @Override // android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            updateCheckedState(false);
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            float r0 = AndroidUtilities.dp(20.0f);
            float r1 = getMeasuredWidth() * 0.5f;
            float r3 = getMeasuredHeight() * 0.5f;
            this.paint.setColor(this.currentAccent.accentColor);
            this.paint.setStyle(Paint.Style.STROKE);
            this.paint.setStrokeWidth(AndroidUtilities.dp(3.0f));
            this.paint.setAlpha(Math.round(this.checkedState * 255.0f));
            r8.drawCircle(r1, r3, r0 - (this.paint.getStrokeWidth() * 0.5f), this.paint);
            this.paint.setAlpha(255);
            this.paint.setStyle(Paint.Style.FILL);
            r8.drawCircle(r1, r3, r0 - (AndroidUtilities.dp(5.0f) * this.checkedState), this.paint);
            if (this.checkedState == 0.0f) goto L5;
            this.paint.setColor(-1);
            this.paint.setAlpha(Math.round(this.checkedState * 255.0f));
            r8.drawCircle(r1, r3, AndroidUtilities.dp(2.0f), this.paint);
            r8.drawCircle(r1 - (AndroidUtilities.dp(7.0f) * this.checkedState), r3, AndroidUtilities.dp(2.0f), this.paint);
            r8.drawCircle((AndroidUtilities.dp(7.0f) * this.checkedState) + r1, r3, AndroidUtilities.dp(2.0f), this.paint);
        L5:
            int r02 = this.currentAccent.myMessagesAccentColor;
            if (r02 != 0) goto L8;
            return;
        L8:
            if (this.checkedState == 1.0f) goto L12;
            this.paint.setColor(r02);
            r8.drawCircle(r1, r3, AndroidUtilities.dp(8.0f) * (1.0f - this.checkedState), this.paint);
            return;
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r3) {
            super.onInitializeAccessibilityNodeInfo(r3);
            r3.setText(LocaleController.getString("ColorPickerMainColor", R.string.ColorPickerMainColor));
            r3.setClassName(Button.class.getName());
            r3.setChecked(this.checked);
            r3.setCheckable(true);
            r3.setEnabled(true);
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(62.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(62.0f), TLObject.FLAG_30));
        }

        @Keep
        public void setCheckedState(float r1) {
            this.checkedState = r1;
            invalidate();
        }

        public void setThemeAndColor(Theme.ThemeInfo r1, Theme.ThemeAccent r2) {
            this.currentTheme = r1;
            this.currentAccent = r2;
            updateCheckedState(false);
        }

        public void updateCheckedState(boolean r6) {
            if (this.currentTheme.currentAccentId != this.currentAccent.id) goto L5;
            boolean r0 = true;
        L6:
            this.checked = r0;
            ObjectAnimator r02 = this.checkAnimator;
            if (r02 == null) goto L9;
            r02.cancel();
        L9:
            boolean r03 = this.checked;
            float r1 = 0.0f;
            if (r6 == false) goto L15;
            if (r03 == false) goto L13;
            r1 = 1.0f;
        L13:
            ObjectAnimator r62 = ObjectAnimator.ofFloat(this, "checkedState", new float[]{r1});
            this.checkAnimator = r62;
            r62.setDuration(200);
            this.checkAnimator.start();
            return;
        L15:
            if (r03 == false) goto L17;
            r1 = 1.0f;
        L17:
            setCheckedState(r1);
            return;
        L5:
            r0 = false;
            goto L6
        }
    }

    public static class InnerCustomAccentView extends View {
        private int[] colors;
        private final Paint paint;

        /* renamed from: -$$Nest$msetTheme, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m20720$$Nest$msetTheme(InnerCustomAccentView r0, Theme.ThemeInfo r1) {
            r0.setTheme(r1);
        }

        public InnerCustomAccentView(Context r2) {
            super(r2);
            this.paint = new Paint(1);
            this.colors = new int[7];
        }

        private void setTheme(Theme.ThemeInfo r11) {
            if (r11.defaultAccentCount < 8) goto L6;
            this.colors = new int[]{r11.getAccentColor(6), r11.getAccentColor(4), r11.getAccentColor(7), r11.getAccentColor(2), r11.getAccentColor(0), r11.getAccentColor(5), r11.getAccentColor(3)};
            return;
        L6:
            this.colors = new int[7];
        }

        @Override // android.view.View
        public void onDraw(Canvas r12) {
            float r0 = getMeasuredWidth() * 0.5f;
            float r2 = getMeasuredHeight() * 0.5f;
            float r1 = AndroidUtilities.dp(5.0f);
            float r3 = AndroidUtilities.dp(20.0f) - r1;
            this.paint.setStyle(Paint.Style.FILL);
            int r6 = 0;
            this.paint.setColor(this.colors[0]);
            r12.drawCircle(r0, r2, r1, this.paint);
            double r4 = 0.0d;
        L4:
            if (r6 >= 6) goto L6;
            float r7 = (((float) Math.sin(r4)) * r3) + r0;
            float r8 = r2 - (((float) Math.cos(r4)) * r3);
            r6 = r6 + 1;
            this.paint.setColor(this.colors[r6]);
            r12.drawCircle(r7, r8, r1, this.paint);
            r4 = r4 + 1.0471975511965976d;
            goto L4
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
            super.onInitializeAccessibilityNodeInfo(r2);
            r2.setText(LocaleController.getString("ColorPickerMainColor", R.string.ColorPickerMainColor));
            r2.setClassName(Button.class.getName());
            r2.setEnabled(true);
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(62.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(62.0f), TLObject.FLAG_30));
        }
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private boolean first;
        private Context mContext;
        final /* synthetic */ ThemeActivity this$0;

        /* renamed from: org.telegram.ui.ThemeActivity$ListAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends BrightnessControlCell {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass1(ListAdapter r1, Context r2, int r3) {
                this.this$1 = r1;
                super(r2, r3);
            }

            @Override // org.telegram.ui.Cells.BrightnessControlCell
            public void didChangedValue(float r4) {
                int r0 = (int) (Theme.autoNightBrighnessThreshold * 100.0f);
                int r2 = (int) (r4 * 100.0f);
                Theme.autoNightBrighnessThreshold = r4;
                if (r0 == r2) goto L9;
                RecyclerListView.Holder r3 = (RecyclerListView.Holder) ThemeActivity.m20650$$Nest$fgetlistView(this.this$1.this$0).findViewHolderForAdapterPosition(ThemeActivity.m20623$$Nest$fgetautomaticBrightnessInfoRow(this.this$1.this$0));
                if (r3 == null) goto L7;
                ((TextInfoPrivacyCell) r3.itemView).setText(LocaleController.formatString("AutoNightBrightnessInfo", R.string.AutoNightBrightnessInfo, new Object[]{Integer.valueOf((int) (Theme.autoNightBrighnessThreshold * 100.0f))}));
            L7:
                Theme.checkAutoNightThemeConditions(true);
                return;
            }
        }

        /* renamed from: org.telegram.ui.ThemeActivity$ListAdapter$2, reason: invalid class name */
        public class AnonymousClass2 extends ChatListCell {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass2(ListAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.Cells.ChatListCell
            public void didSelectChatType(boolean r1) {
                SharedConfig.setUseThreeLinesLayout(r1);
            }
        }

        /* renamed from: org.telegram.ui.ThemeActivity$ListAdapter$3, reason: invalid class name */
        public class AnonymousClass3 extends ThemesHorizontalListCell {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass3(ListAdapter r1, Context r2, BaseFragment r3, int r4, ArrayList r5, ArrayList r6) {
                this.this$1 = r1;
                super(r2, r3, r4, r5, r6);
            }

            @Override // org.telegram.ui.Cells.ThemesHorizontalListCell
            public void showOptionsForTheme(Theme.ThemeInfo r1) {
                ListAdapter.m20723$$Nest$mshowOptionsForTheme(ThemeActivity.m20649$$Nest$fgetlistAdapter(this.this$1.this$0), r1);
            }

            @Override // org.telegram.ui.Cells.ThemesHorizontalListCell
            public void updateRows() {
                ThemeActivity.m20715$$Nest$mupdateRows(this.this$1.this$0, false);
            }
        }

        /* renamed from: org.telegram.ui.ThemeActivity$ListAdapter$4, reason: invalid class name */
        public class AnonymousClass4 extends TintRecyclerListView {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass4(ListAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
            public boolean onInterceptTouchEvent(MotionEvent r3) {
                if (getParent() == null) goto L8;
                if (getParent().getParent() == null) goto L8;
                getParent().getParent().requestDisallowInterceptTouchEvent(canScrollHorizontally(-1));
            L8:
                return super.onInterceptTouchEvent(r3);
            }
        }

        /* renamed from: org.telegram.ui.ThemeActivity$ListAdapter$5, reason: invalid class name */
        public class AnonymousClass5 extends ThemePreviewMessagesCell {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass5(ListAdapter r1, Context r2, INavigationLayout r3, int r4) {
                this.this$1 = r1;
                super(r2, r3, r4);
            }

            @Override // org.telegram.ui.Cells.ThemePreviewMessagesCell, android.view.ViewGroup
            public boolean onInterceptTouchEvent(MotionEvent r3) {
                if (getParent() == null) goto L8;
                if (getParent().getParent() == null) goto L8;
                getParent().getParent().requestDisallowInterceptTouchEvent(canScrollHorizontally(-1));
            L8:
                return super.onInterceptTouchEvent(r3);
            }
        }

        /* renamed from: $r8$lambda$5mr-qp0vZCnQvra7n-ElGyyji0w, reason: not valid java name */
        public static /* synthetic */ boolean m20721$r8$lambda$5mrqp0vZCnQvra7nElGyyji0w(ListAdapter r0, ThemeAccentsListAdapter r1, View r2, int r3) {
            return r0.lambda$onCreateViewHolder$5(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$EqDKuDsBE2ocDIDkqPyMwq8kKss(ListAdapter r0, Theme.ThemeInfo r1, DialogInterface r2, int r3) {
            r0.lambda$showOptionsForTheme$1(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$OOom3wTi-Hn1NxdDN-XCe8_9a_M, reason: not valid java name */
        public static /* synthetic */ void m20722$r8$lambda$OOom3wTiHn1NxdDNXCe8_9a_M(ListAdapter r0, Theme.ThemeAccent r1, ThemeAccentsListAdapter r2, DialogInterface r3, int r4) {
            r0.lambda$onCreateViewHolder$4(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$l3usnw9I_Zngp45BQhqlHNnbPqM(ListAdapter r0, ThemeAccentsListAdapter r1, Theme.ThemeAccent r2, AlertDialog r3, int r4) {
            r0.lambda$onCreateViewHolder$3(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$sAj040uBGTC1GoitmlOFkaeDh_Q(ListAdapter r0, ThemeAccentsListAdapter r1, RecyclerListView r2, View r3, int r4) {
            r0.lambda$onCreateViewHolder$2(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$yTq1PaeCDlmt2gxUgGa3hG277WA(ListAdapter r0, Theme.ThemeInfo r1, AlertDialog r2, int r3) {
            r0.lambda$showOptionsForTheme$0(r1, r2, r3);
        }

        /* renamed from: -$$Nest$mshowOptionsForTheme, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m20723$$Nest$mshowOptionsForTheme(ListAdapter r0, Theme.ThemeInfo r1) {
            r0.showOptionsForTheme(r1);
        }

        public ListAdapter(ThemeActivity r1, Context r2) {
            this.this$0 = r1;
            this.first = true;
            this.mContext = r2;
        }

        private /* synthetic */ void lambda$onCreateViewHolder$2(ThemeAccentsListAdapter r10, RecyclerListView r11, View r12, int r13) {
            if (ThemeActivity.m20641$$Nest$fgetcurrentType(this.this$0) != 1) goto L6;
            Theme.ThemeInfo r0 = Theme.getCurrentNightTheme();
        L5:
            Theme.ThemeInfo r3 = r0;
            int r8 = 0;
            if (r13 != ThemeAccentsListAdapter.m20731$$Nest$mgetCustomAccentPosition(r10)) goto L15;
            ThemeActivity r9 = this.this$0;
            if (ThemeActivity.m20641$$Nest$fgetcurrentType(r9) != 1) goto L12;
            boolean r7 = true;
        L13:
            r9.presentFragment(new ThemePreviewActivity(r3, false, 1, false, r7));
        L34:
            int r92 = r12.getLeft();
            int r102 = r12.getRight();
            int r122 = AndroidUtilities.dp(52.0f);
            int r93 = r92 - r122;
            if (r93 >= 0) goto L37;
            r11.smoothScrollBy(r93, 0);
        L40:
            int r94 = r11.getChildCount();
        L41:
            if (r8 >= r94) goto L50;
            View r103 = r11.getChildAt(r8);
            if ((r103 instanceof InnerAccentView) == false) goto L45;
            ((InnerAccentView) r103).updateCheckedState(true);
        L45:
            r8 = r8 + 1;
            goto L41
        L50:
            return;
        L37:
            int r104 = r102 + r122;
            if (r104 <= r11.getMeasuredWidth()) goto L40;
            r11.smoothScrollBy(r104 - r11.getMeasuredWidth(), 0);
            goto L40
        L12:
            r7 = false;
            goto L13
        L15:
            if (ThemeAccentsListAdapter.m20732$$Nest$misDividerPosition(r10, r13) == true) goto L46;
            Theme.ThemeAccent r105 = ThemeAccentsListAdapter.m20730$$Nest$mgetAccent(r10, r13);
            if (MonetAccentHelper.hasRemotePatternWallpaper(r105) == false) goto L23;
            if (r105.id == Theme.DEFALT_THEME_ACCENT_ID) goto L23;
            Theme.PatternsLoader.createLoader(false);
        L23:
            if (r3.currentAccentId == r105.id) goto L29;
            NotificationCenter r132 = NotificationCenter.getGlobalInstance();
            int r02 = NotificationCenter.needSetDayNightTheme;
            if (ThemeActivity.m20641$$Nest$fgetcurrentType(this.this$0) != 1) goto L27;
            boolean r2 = true;
        L28:
            r132.postNotificationName(r02, new Object[]{r3, Boolean.valueOf(r2), null, Integer.valueOf(r105.id)});
            EmojiThemes.saveCustomTheme(r3, r105.id);
            Theme.turnOffAutoNight(this.this$0);
            goto L34
        L27:
            r2 = false;
            goto L28
        L29:
            ThemeActivity r133 = this.this$0;
            boolean r6 = MonetAccentHelper.canEditAccent(r105);
            if (ThemeActivity.m20641$$Nest$fgetcurrentType(this.this$0) != 1) goto L32;
            boolean r72 = true;
        L33:
            r133.presentFragment(new ThemePreviewActivity(r3, false, 1, r6, r72));
            goto L34
        L32:
            r72 = false;
            goto L33
        L46:
            return;
        L6:
            r0 = Theme.getCurrentTheme();
            goto L5
        }

        private /* synthetic */ void lambda$onCreateViewHolder$3(ThemeAccentsListAdapter r2, Theme.ThemeAccent r3, AlertDialog r4, int r5) {
            boolean r42 = true;
            if (Theme.deleteThemeAccent(ThemeAccentsListAdapter.m20728$$Nest$fgetcurrentTheme(r2), r3, true) == false) goto L10;
            Theme.refreshThemeColors();
            NotificationCenter r22 = NotificationCenter.getGlobalInstance();
            int r32 = NotificationCenter.needSetDayNightTheme;
            Theme.ThemeInfo r52 = Theme.getActiveTheme();
            if (ThemeActivity.m20641$$Nest$fgetcurrentType(this.this$0) == 1) goto L8;
            r42 = false;
        L8:
            r22.postNotificationName(r32, new Object[]{r52, Boolean.valueOf(r42), null, -1});
            return;
        }

        private /* synthetic */ void lambda$onCreateViewHolder$4(Theme.ThemeAccent r8, ThemeAccentsListAdapter r9, DialogInterface r10, int r11) {
            if (this.this$0.getParentActivity() == null) goto L34;
            int r102 = 2;
            if (r11 != 0) goto L13;
            ThemeActivity r7 = this.this$0;
            if (r11 == 1) goto L11;
            r102 = 1;
        L11:
            AlertsCreator.createThemeCreateDialog(r7, r102, r8.parentTheme, r8);
            return;
        L13:
            if (r11 != 1) goto L20;
            TLRPC.TL_theme r92 = r8.info;
            ThemeActivity r103 = this.this$0;
            if (r92 != null) goto L18;
            r103.getMessagesController().saveThemeToServer(r8.parentTheme, r8);
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needShareTheme, new Object[]{r8.parentTheme, r8});
            return;
        L18:
            String r3 = "https://" + r103.getMessagesController().linkPrefix + "/addtheme/" + r8.info.slug;
            this.this$0.showDialog(new ShareAlert(this.this$0.getParentActivity(), null, r3, false, r3, false));
            return;
        L20:
            if (r11 != 2) goto L24;
            this.this$0.presentFragment(new ThemeSetUrlActivity(r8.parentTheme, r8, false));
            return;
        L24:
            if (r11 == 3) goto L26;
            return;
        L26:
            if (this.this$0.getParentActivity() == null) goto L35;
            AlertDialog.Builder r104 = new AlertDialog.Builder(this.this$0.getParentActivity());
            r104.setTitle(LocaleController.getString("DeleteThemeTitle", R.string.DeleteThemeTitle));
            r104.setMessage(LocaleController.getString("DeleteThemeAlert", R.string.DeleteThemeAlert));
            r104.setPositiveButton(LocaleController.getString("Delete", R.string.Delete), new ThemeActivity$ListAdapter$$ExternalSyntheticLambda3(this, r9, r8));
            r104.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
            AlertDialog r82 = r104.create();
            this.this$0.showDialog(r82);
            TextView r72 = (TextView) r82.getButton(-1);
            if (r72 == null) goto L33;
            r72.setTextColor(Theme.getColor(Theme.key_text_RedBold));
            return;
        L33:
            return;
        L35:
            return;
        }

        private /* synthetic */ boolean lambda$onCreateViewHolder$5(ThemeAccentsListAdapter r7, View r8, int r9) {
            if (r9 >= 0) goto L5;
            return false;
        L5:
            if (r9 != ThemeAccentsListAdapter.m20731$$Nest$mgetCustomAccentPosition(r7)) goto L7;
            return false;
        L7:
            if (ThemeAccentsListAdapter.m20732$$Nest$misDividerPosition(r7, r9) == true) goto L22;
            Theme.ThemeAccent r92 = ThemeAccentsListAdapter.m20730$$Nest$mgetAccent(r7, r9);
            if (MonetAccentHelper.canEditAccent(r92) == false) goto L21;
            AlertDialog.Builder r0 = new AlertDialog.Builder(this.this$0.getParentActivity());
            String r1 = LocaleController.getString("OpenInEditor", R.string.OpenInEditor);
            String r2 = LocaleController.getString("ShareTheme", R.string.ShareTheme);
            TLRPC.TL_theme r3 = r92.info;
            if (r3 != null) goto L14;
        L16:
            String r32 = null;
        L17:
            r0.setItems(new CharSequence[]{r1, r2, r32, LocaleController.getString("DeleteTheme", R.string.DeleteTheme)}, new int[]{R.drawable.msg_edit, R.drawable.msg_share, R.drawable.msg_link, R.drawable.msg_delete}, new ThemeActivity$ListAdapter$$ExternalSyntheticLambda2(this, r92, r7));
            AlertDialog r72 = r0.create();
            this.this$0.showDialog(r72);
            r72.setItemColor(r72.getItemsCount() - 1, Theme.getColor(Theme.key_text_RedBold), Theme.getColor(Theme.key_text_RedRegular));
            return true;
        L14:
            if (r3.creator == false) goto L16;
            r32 = LocaleController.getString("ThemeSetUrl", R.string.ThemeSetUrl);
            goto L17
        L21:
            return false;
        L22:
            return false;
        }

        private /* synthetic */ void lambda$showOptionsForTheme$0(Theme.ThemeInfo r4, AlertDialog r5, int r6) {
            MessagesController r52 = MessagesController.getInstance(r4.account);
            if (r4 != Theme.getCurrentNightTheme()) goto L5;
            boolean r62 = true;
        L6:
            r52.saveTheme(r4, null, r62, true);
            if (Theme.deleteTheme(r4) == false) goto L9;
            ThemeActivity.access$600(this.this$0).rebuildAllFragmentViews(true, true);
        L9:
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.themeListUpdated, new Object[0]);
            return;
        L5:
            r62 = false;
            goto L6
        }

        private /* synthetic */ void lambda$showOptionsForTheme$1(Theme.ThemeInfo r8, DialogInterface r9, int r10) {
            if (this.this$0.getParentActivity() == null) goto L109;
            FileOutputStream r1 = null;
            if (r10 != 0) goto L13;
            TLRPC.TL_theme r92 = r8.info;
            ThemeActivity r102 = this.this$0;
            if (r92 != null) goto L11;
            r102.getMessagesController().saveThemeToServer(r8, null);
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needShareTheme, new Object[]{r8, null});
            return;
        L11:
            String r3 = "https://" + r102.getMessagesController().linkPrefix + "/addtheme/" + r8.info.slug;
            this.this$0.showDialog(new ShareAlert(this.this$0.getParentActivity(), null, r3, false, r3, false));
            return;
        L13:
            int r0 = 0;
            if (r10 != 1) goto L72;
            if (r8.pathToFile != null) goto L49;
            if (r8.assetName != null) goto L49;
            StringBuilder r103 = new StringBuilder();
            int[] r32 = Theme.getDefaultColors();
        L21:
            if (r0 >= r32.length) goto L23;
            r103.append(ThemeColors.getStringName(r0));
            r103.append("=");
            r103.append(r32[r0]);
            r103.append("\n");
            r0 = r0 + 1;
            goto L21
        L23:
            File r33 = new File(ApplicationLoader.getFilesDirFixed(), "default_theme.attheme");
            FileOutputStream r4 = new FileOutputStream(r33);     // Catch: Throwable -> L34 Exception -> L36
            r4.write(AndroidUtilities.getStringBytes(r103.toString()));     // Catch: Throwable -> L30 Exception -> L32
            r4.close();     // Catch: Exception -> L28
        L92:
        L56:
            String r82 = r8.name;
            if (r82.endsWith(".attheme") == true) goto L59;
            r82 = r82.concat(".attheme");
        L59:
            File r104 = new File(FileLoader.getDirectory(4), FileLoader.fixFileName(r82));
        L69:
            e = move-exception;
            FileLog.e(e);
            return;
        L61:
            if (AndroidUtilities.copyFile(r33, r104) == false) goto L105;
            Intent r83 = new Intent("android.intent.action.SEND");     // Catch: Exception -> L69
            r83.setType("text/xml");     // Catch: Exception -> L69
            r83.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(this.this$0.getParentActivity(), ApplicationLoader.getApplicationId() + ".provider", r104));     // Catch: Exception -> L66
            r83.setFlags(1);     // Catch: Exception -> L66
        L67:
            this.this$0.startActivityForResult(Intent.createChooser(r83, LocaleController.getString("ShareFile", R.string.ShareFile)), MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);     // Catch: Exception -> L69
            return;
        L66:
            r83.putExtra("android.intent.extra.STREAM", Uri.fromFile(r104));     // Catch: Exception -> L69
            goto L67
        L105:
            return;
        L32:
            e = move-exception;
            Exception r105 = e;
            r1 = r4;
        L38:
            FileLog.e(r105);     // Catch: Throwable -> L34
            if (r1 == null) goto L56;
            r1.close();     // Catch: Exception -> L28
        L30:
            th = move-exception;
            Throwable r7 = th;
            r1 = r4;
        L42:
            if (r1 == null) goto L103;
            r1.close();     // Catch: Exception -> L45
            throw r7;
        L45:
            e = move-exception;
            FileLog.e(e);
            throw r7;
        L103:
            throw r7;
        L36:
            e = move-exception;
            r105 = e;
        L34:
            th = move-exception;
            r7 = th;
        L28:
            e = move-exception;
            FileLog.e(e);
        L49:
            if (r8.assetName == null) goto L55;
            Theme.ThemeAccent r106 = r8.getAccent(false);
            if (MonetAccentHelper.canEditAccent(r106) == false) goto L54;
            File r107 = r106.saveToFile();
        L53:
            r33 = r107;
            goto L56
        L54:
            r107 = Theme.getAssetFile(r8.assetName);
            goto L53
        L55:
            r33 = new File(r8.pathToFile);
            goto L56
        L72:
            if (r10 == 2) goto L74;
            ThemeActivity r93 = this.this$0;
            if (r10 != 3) goto L82;
            r93.presentFragment(new ThemeSetUrlActivity(r8, null, false));
            return;
        L82:
            if (r93.getParentActivity() == null) goto L111;
            AlertDialog.Builder r94 = new AlertDialog.Builder(this.this$0.getParentActivity());
            r94.setTitle(LocaleController.getString("DeleteThemeTitle", R.string.DeleteThemeTitle));
            r94.setMessage(LocaleController.getString("DeleteThemeAlert", R.string.DeleteThemeAlert));
            r94.setPositiveButton(LocaleController.getString("Delete", R.string.Delete), new ThemeActivity$ListAdapter$$ExternalSyntheticLambda5(this, r8));
            r94.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
            AlertDialog r84 = r94.create();
            this.this$0.showDialog(r84);
            TextView r72 = (TextView) r84.getButton(-1);
            if (r72 == null) goto L108;
            r72.setTextColor(Theme.getColor(Theme.key_text_RedBold));
            return;
        L108:
            return;
        L111:
            return;
        L74:
            if (ThemeActivity.access$400(this.this$0) == null) goto L107;
            Theme.applyTheme(r8);
            ThemeActivity.access$500(this.this$0).rebuildAllFragmentViews(true, true);
            new ThemeEditorView().show(this.this$0.getParentActivity(), r8);
            return;
        L107:
            return;
        }

        private void showOptionsForTheme(Theme.ThemeInfo r13) {
            if (this.this$0.getParentActivity() != null) goto L5;
            return;
        L5:
            if (r13.info == null) goto L9;
            if (r13.themeLoaded == true) goto L9;
            return;
        L9:
            if (ThemeActivity.m20641$$Nest$fgetcurrentType(this.this$0) == 1) goto L46;
            AlertDialog.Builder r0 = new AlertDialog.Builder(this.this$0.getParentActivity());
            String r5 = null;
            boolean r6 = false;
            if (r13.pathToFile != null) goto L14;
            CharSequence[] r2 = {null, LocaleController.getString("ExportTheme", R.string.ExportTheme)};
            int[] r3 = {0, R.drawable.msg_shareout};
        L39:
            r0.setItems(r2, r3, new ThemeActivity$ListAdapter$$ExternalSyntheticLambda4(this, r13));
            AlertDialog r132 = r0.create();
            this.this$0.showDialog(r132);
            if (r6 == false) goto L45;
            r132.setItemColor(r132.getItemsCount() - 1, Theme.getColor(Theme.key_text_RedBold), Theme.getColor(Theme.key_text_RedRegular));
            return;
        L45:
            return;
        L14:
            TLRPC.TL_theme r22 = r13.info;
            if (r22 != null) goto L17;
        L20:
            boolean r23 = true;
        L21:
            String r7 = LocaleController.getString("ShareFile", R.string.ShareFile);
            String r32 = LocaleController.getString("ExportTheme", R.string.ExportTheme);
            TLRPC.TL_theme r8 = r13.info;
            if (r8 != null) goto L24;
        L29:
            String r82 = LocaleController.getString("Edit", R.string.Edit);
        L30:
            TLRPC.TL_theme r9 = r13.info;
            if (r9 != null) goto L33;
        L35:
            String r92 = null;
        L36:
            if (r23 == false) goto L38;
            r5 = LocaleController.getString("Delete", R.string.Delete);
        L38:
            CharSequence[] r11 = {r7, r32, r82, r92, r5};
            r6 = r23;
            r3 = new int[]{R.drawable.msg_share, R.drawable.msg_shareout, R.drawable.msg_edit, R.drawable.msg_link, R.drawable.msg_delete};
            r2 = r11;
            goto L39
        L33:
            if (r9.creator == false) goto L35;
            r92 = LocaleController.getString("ThemeSetUrl", R.string.ThemeSetUrl);
            goto L36
        L24:
            if (r8.isDefault == false) goto L26;
        L28:
            r82 = null;
            goto L30
        L26:
            if (r8.creator == false) goto L28;
        L17:
            if (r22.isDefault == false) goto L20;
            r23 = false;
            goto L21
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return ThemeActivity.m20671$$Nest$fgetrowCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            if (r3 != ThemeActivity.m20675$$Nest$fgetscheduleFromRow(this.this$0)) goto L5;
        L194:
            return 1;
        L5:
            if (r3 == ThemeActivity.m20645$$Nest$fgetdistanceRow(this.this$0)) goto L194;
            if (r3 == ThemeActivity.m20680$$Nest$fgetscheduleToRow(this.this$0)) goto L194;
            if (r3 == ThemeActivity.m20681$$Nest$fgetscheduleUpdateLocationRow(this.this$0)) goto L194;
            if (r3 == ThemeActivity.m20638$$Nest$fgetcontactsReimportRow(this.this$0)) goto L194;
            if (r3 == ThemeActivity.m20639$$Nest$fgetcontactsSortRow(this.this$0)) goto L194;
            if (r3 == ThemeActivity.m20627$$Nest$fgetbluetoothScoRow(this.this$0)) goto L194;
            if (r3 == ThemeActivity.m20682$$Nest$fgetsearchEngineRow(this.this$0)) goto L194;
            if (r3 == ThemeActivity.m20621$$Nest$fgetappIconSelectorRow(this.this$0)) goto L194;
            if (r3 != ThemeActivity.m20623$$Nest$fgetautomaticBrightnessInfoRow(this.this$0)) goto L24;
            return 2;
        L24:
            if (r3 != ThemeActivity.m20678$$Nest$fgetscheduleLocationInfoRow(this.this$0)) goto L26;
            return 2;
        L26:
            if (r3 != ThemeActivity.m20692$$Nest$fgetswipeGestureInfoRow(this.this$0)) goto L28;
            return 2;
        L28:
            if (r3 != ThemeActivity.m20688$$Nest$fgetstickersInfoRow(this.this$0)) goto L30;
            return 2;
        L30:
            if (r3 != ThemeActivity.m20651$$Nest$fgetliteModeInfoRow(this.this$0)) goto L33;
            return 2;
        L33:
            if (r3 != ThemeActivity.m20698$$Nest$fgetthemeInfoRow(this.this$0)) goto L35;
            return 3;
        L35:
            if (r3 != ThemeActivity.m20663$$Nest$fgetnightTypeInfoRow(this.this$0)) goto L37;
            return 3;
        L37:
            if (r3 != ThemeActivity.m20676$$Nest$fgetscheduleFromToInfoRow(this.this$0)) goto L39;
            return 3;
        L39:
            if (r3 != ThemeActivity.m20686$$Nest$fgetsettings2Row(this.this$0)) goto L41;
            return 3;
        L41:
            if (r3 != ThemeActivity.m20656$$Nest$fgetnewThemeInfoRow(this.this$0)) goto L43;
            return 3;
        L43:
            if (r3 != ThemeActivity.m20636$$Nest$fgetchatListInfoRow(this.this$0)) goto L45;
            return 3;
        L45:
            if (r3 != ThemeActivity.m20631$$Nest$fgetbubbleRadiusInfoRow(this.this$0)) goto L47;
            return 3;
        L47:
            if (r3 != ThemeActivity.m20674$$Nest$fgetsaveToGallerySectionRow(this.this$0)) goto L49;
            return 3;
        L49:
            if (r3 != ThemeActivity.m20622$$Nest$fgetappIconShadowRow(this.this$0)) goto L51;
            return 3;
        L51:
            if (r3 != ThemeActivity.m20648$$Nest$fgetlastShadowRow(this.this$0)) goto L53;
            return 3;
        L53:
            if (r3 != ThemeActivity.m20690$$Nest$fgetstickersSectionRow(this.this$0)) goto L55;
            return 3;
        L55:
            if (r3 != ThemeActivity.m20654$$Nest$fgetmediaSoundSectionRow(this.this$0)) goto L57;
            return 3;
        L57:
            if (r3 != ThemeActivity.m20665$$Nest$fgetotherSectionRow(this.this$0)) goto L60;
            return 3;
        L60:
            if (r3 != ThemeActivity.m20659$$Nest$fgetnightDisabledRow(this.this$0)) goto L62;
            return 4;
        L62:
            if (r3 != ThemeActivity.m20660$$Nest$fgetnightScheduledRow(this.this$0)) goto L64;
            return 4;
        L64:
            if (r3 != ThemeActivity.m20658$$Nest$fgetnightAutomaticRow(this.this$0)) goto L66;
            return 4;
        L66:
            if (r3 != ThemeActivity.m20661$$Nest$fgetnightSystemDefaultRow(this.this$0)) goto L69;
            return 4;
        L69:
            if (r3 != ThemeActivity.m20677$$Nest$fgetscheduleHeaderRow(this.this$0)) goto L71;
            return 5;
        L71:
            if (r3 != ThemeActivity.m20625$$Nest$fgetautomaticHeaderRow(this.this$0)) goto L73;
            return 5;
        L73:
            if (r3 != ThemeActivity.m20668$$Nest$fgetpreferedHeaderRow(this.this$0)) goto L75;
            return 5;
        L75:
            if (r3 != ThemeActivity.m20687$$Nest$fgetsettingsRow(this.this$0)) goto L77;
            return 5;
        L77:
            if (r3 != ThemeActivity.m20697$$Nest$fgetthemeHeaderRow(this.this$0)) goto L79;
            return 5;
        L79:
            if (r3 != ThemeActivity.m20694$$Nest$fgettextSizeHeaderRow(this.this$0)) goto L81;
            return 5;
        L81:
            if (r3 != ThemeActivity.m20635$$Nest$fgetchatListHeaderRow(this.this$0)) goto L83;
            return 5;
        L83:
            if (r3 != ThemeActivity.m20630$$Nest$fgetbubbleRadiusHeaderRow(this.this$0)) goto L85;
            return 5;
        L85:
            if (r3 != ThemeActivity.m20691$$Nest$fgetswipeGestureHeaderRow(this.this$0)) goto L87;
            return 5;
        L87:
            if (r3 != ThemeActivity.m20683$$Nest$fgetselectThemeHeaderRow(this.this$0)) goto L89;
            return 5;
        L89:
            if (r3 != ThemeActivity.m20653$$Nest$fgetmediaSoundHeaderRow(this.this$0)) goto L91;
            return 5;
        L91:
            if (r3 != ThemeActivity.m20664$$Nest$fgetotherHeaderRow(this.this$0)) goto L94;
            return 5;
        L94:
            if (r3 != ThemeActivity.m20624$$Nest$fgetautomaticBrightnessRow(this.this$0)) goto L98;
            return 6;
        L98:
            if (r3 != ThemeActivity.m20679$$Nest$fgetscheduleLocationRow(this.this$0)) goto L100;
            return 7;
        L100:
            if (r3 != ThemeActivity.m20684$$Nest$fgetsendByEnterRow(this.this$0)) goto L102;
            return 7;
        L102:
            if (r3 != ThemeActivity.m20670$$Nest$fgetraiseToSpeakRow(this.this$0)) goto L104;
            return 7;
        L104:
            if (r3 != ThemeActivity.m20669$$Nest$fgetraiseToListenRow(this.this$0)) goto L106;
            return 7;
        L106:
            if (r3 != ThemeActivity.m20667$$Nest$fgetpauseOnRecordRow(this.this$0)) goto L108;
            return 7;
        L108:
            if (r3 != ThemeActivity.m20644$$Nest$fgetdirectShareRow(this.this$0)) goto L110;
            return 7;
        L110:
            if (r3 != ThemeActivity.m20634$$Nest$fgetchatBlurRow(this.this$0)) goto L112;
            return 7;
        L112:
            if (r3 != ThemeActivity.m20666$$Nest$fgetpauseOnMediaRow(this.this$0)) goto L114;
            return 7;
        L114:
            if (r3 != ThemeActivity.m20657$$Nest$fgetnextMediaTapRow(this.this$0)) goto L116;
            return 7;
        L116:
            if (r3 != ThemeActivity.m20685$$Nest$fgetsensitiveContentRow(this.this$0)) goto L119;
            return 7;
        L119:
            if (r3 != ThemeActivity.m20695$$Nest$fgettextSizeRow(this.this$0)) goto L123;
            return 8;
        L123:
            if (r3 != ThemeActivity.m20637$$Nest$fgetchatListRow(this.this$0)) goto L127;
            return 9;
        L127:
            if (r3 != ThemeActivity.m20662$$Nest$fgetnightThemeRow(this.this$0)) goto L129;
            return 10;
        L129:
            if (r3 != ThemeActivity.m20629$$Nest$fgetbrowserRow(this.this$0)) goto L132;
            return 10;
        L132:
            if (r3 != ThemeActivity.m20699$$Nest$fgetthemeListRow(this.this$0)) goto L136;
            return 11;
        L136:
            if (r3 != ThemeActivity.m20696$$Nest$fgetthemeAccentListRow(this.this$0)) goto L140;
            return 12;
        L140:
            if (r3 != ThemeActivity.m20632$$Nest$fgetbubbleRadiusRow(this.this$0)) goto L144;
            return 13;
        L144:
            if (r3 != ThemeActivity.m20626$$Nest$fgetbackgroundRow(this.this$0)) goto L146;
            return 14;
        L146:
            if (r3 != ThemeActivity.m20646$$Nest$fgeteditThemeRow(this.this$0)) goto L148;
            return 14;
        L148:
            if (r3 != ThemeActivity.m20640$$Nest$fgetcreateNewThemeRow(this.this$0)) goto L150;
            return 14;
        L150:
            if (r3 != ThemeActivity.m20652$$Nest$fgetliteModeRow(this.this$0)) goto L152;
            return 14;
        L152:
            if (r3 != ThemeActivity.m20689$$Nest$fgetstickersRow(this.this$0)) goto L154;
            return 14;
        L154:
            if (r3 != ThemeActivity.m20628$$Nest$fgetbrowseThemesRow(this.this$0)) goto L157;
            return 14;
        L157:
            if (r3 != ThemeActivity.m20693$$Nest$fgetswipeGestureRow(this.this$0)) goto L161;
            return 15;
        L161:
            if (r3 != ThemeActivity.m20701$$Nest$fgetthemePreviewRow(this.this$0)) goto L165;
            return 16;
        L165:
            if (r3 != ThemeActivity.m20700$$Nest$fgetthemeListRow2(this.this$0)) goto L169;
            return 17;
        L169:
            if (r3 != ThemeActivity.m20672$$Nest$fgetsaveToGalleryOption1Row(this.this$0)) goto L171;
            return 19;
        L171:
            if (r3 != ThemeActivity.m20673$$Nest$fgetsaveToGalleryOption2Row(this.this$0)) goto L174;
            return 19;
        L174:
            if (r3 != ThemeActivity.m20633$$Nest$fgetchangeUserColor(this.this$0)) goto L177;
            return 21;
        L177:
            return 1;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r2) {
            int r1 = r2.getItemViewType();
            if (r1 == 0) goto L24;
            if (r1 == 1) goto L24;
            if (r1 == 4) goto L24;
            if (r1 == 7) goto L24;
            if (r1 == 10) goto L24;
            if (r1 == 11) goto L24;
            if (r1 == 12) goto L24;
            if (r1 == 14) goto L24;
            if (r1 == 18) goto L24;
            if (r1 == 21) goto L24;
            return false;
        L24:
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r22, int r23) {
            int r3 = r22.getItemViewType();
            boolean r4 = false;
            boolean r5 = true;
            if (r3 != 1) goto L5;
            TextSettingsCell r1 = (TextSettingsCell) r22.itemView;
            int r32 = Theme.key_windowBackgroundWhiteValueText;
            r1.setTextValueColor(Theme.getColor(r32, this.this$0.getResourceProvider()));
            if (r23 != ThemeActivity.m20621$$Nest$fgetappIconSelectorRow(this.this$0)) goto L248;
            AppIcon r2 = AppIconController.getSelectedIcon();
            r1.setTextAndValue(LocaleController.getString(R.string.AppIcon), r2.getTitle(), false);
            r1.setTextValueColor(AppIconPreviewLoader.getAccentTextColor(r2, r32, this.this$0.getResourceProvider()));
            ImageView r0 = r1.getValueImageView();
            r0.setVisibility(0);
            r0.setImageDrawable(new AppIconPreviewDrawable(r2, AndroidUtilities.dp(30.0f)));
            return;
        L248:
            if (r23 != ThemeActivity.m20662$$Nest$fgetnightThemeRow(this.this$0)) goto L259;
            if (Theme.selectedAutoNightType != 0) goto L252;
        L256:
            r1.setTextAndValue(LocaleController.getString(R.string.AutoNightTheme), LocaleController.getString(R.string.AutoNightThemeOff), false);
            return;
        L252:
            if (Theme.getCurrentNightTheme() == null) goto L256;
            r1.setTextAndValue(LocaleController.getString(R.string.AutoNightTheme), Theme.getCurrentNightThemeName(), false);
            return;
        L259:
            if (r23 != ThemeActivity.m20675$$Nest$fgetscheduleFromRow(this.this$0)) goto L263;
            int r02 = Theme.autoNightDayStartTime;
            int r24 = r02 / 60;
            r1.setTextAndValue(LocaleController.getString("AutoNightFrom", R.string.AutoNightFrom), String.format("%02d:%02d", new Object[]{Integer.valueOf(r24), Integer.valueOf(r02 - (r24 * 60))}), true);
            return;
        L263:
            if (r23 != ThemeActivity.m20680$$Nest$fgetscheduleToRow(this.this$0)) goto L267;
            int r03 = Theme.autoNightDayEndTime;
            int r25 = r03 / 60;
            r1.setTextAndValue(LocaleController.getString("AutoNightTo", R.string.AutoNightTo), String.format("%02d:%02d", new Object[]{Integer.valueOf(r25), Integer.valueOf(r03 - (r25 * 60))}), false);
            return;
        L267:
            if (r23 != ThemeActivity.m20681$$Nest$fgetscheduleUpdateLocationRow(this.this$0)) goto L271;
            r1.setTextAndValue(LocaleController.getString("AutoNightUpdateLocation", R.string.AutoNightUpdateLocation), Theme.autoNightCityName, false);
            return;
        L271:
            if (r23 != ThemeActivity.m20639$$Nest$fgetcontactsSortRow(this.this$0)) goto L281;
            int r04 = MessagesController.getGlobalMainSettings().getInt("sortContactsBy", 0);
            if (r04 != 0) goto L275;
            String r05 = LocaleController.getString("Default", R.string.Default);
        L278:
            r1.setTextAndValue(LocaleController.getString("SortBy", R.string.SortBy), r05, true);
            return;
        L275:
            if (r04 != 1) goto L277;
            r05 = LocaleController.getString("FirstName", R.string.SortFirstName);
            goto L278
        L277:
            r05 = LocaleController.getString("LastName", R.string.SortLastName);
            goto L278
        L281:
            if (r23 != ThemeActivity.m20638$$Nest$fgetcontactsReimportRow(this.this$0)) goto L285;
            r1.setText(LocaleController.getString("ImportContacts", R.string.ImportContacts), true);
            return;
        L285:
            if (r23 != ThemeActivity.m20645$$Nest$fgetdistanceRow(this.this$0)) goto L299;
            int r26 = SharedConfig.distanceSystemType;
            if (r26 != 0) goto L289;
            String r27 = LocaleController.getString("DistanceUnitsAutomatic", R.string.DistanceUnitsAutomatic);
        L292:
            String r33 = LocaleController.getString("DistanceUnits", R.string.DistanceUnits);
            boolean r6 = ThemeActivity.m20703$$Nest$fgetupdateDistance(this.this$0);
            if (ThemeActivity.m20665$$Nest$fgetotherSectionRow(this.this$0) >= 0) goto L296;
            r5 = false;
        L296:
            r1.setTextAndValue(r33, r27, r6, r5);
            ThemeActivity.m20707$$Nest$fputupdateDistance(this.this$0, false);
            return;
        L289:
            if (r26 != 1) goto L291;
            r27 = LocaleController.getString("DistanceUnitsKilometers", R.string.DistanceUnitsKilometers);
            goto L292
        L291:
            r27 = LocaleController.getString("DistanceUnitsMiles", R.string.DistanceUnitsMiles);
            goto L292
        L299:
            if (r23 != ThemeActivity.m20682$$Nest$fgetsearchEngineRow(this.this$0)) goto L303;
            r1.setTextAndValue(LocaleController.getString(R.string.SearchEngine), SearchEngine.getCurrent().name, ThemeActivity.m20705$$Nest$fgetupdateSearchEngine(this.this$0), false);
            return;
        L303:
            if (r23 != ThemeActivity.m20627$$Nest$fgetbluetoothScoRow(this.this$0)) goto L317;
            String r28 = LocaleController.getString(R.string.MicrophoneForVoiceMessages);
            if (SharedConfig.recordViaSco == false) goto L307;
            int r34 = R.string.MicrophoneForVoiceMessagesSco;
        L308:
            r1.setTextAndValue(r28, LocaleController.getString(r34), ThemeActivity.m20704$$Nest$fgetupdateRecordViaSco(this.this$0), false);
            ThemeActivity.m20708$$Nest$fputupdateRecordViaSco(this.this$0, false);
            return;
        L307:
            r34 = R.string.MicrophoneForVoiceMessagesBuiltIn;
            goto L308
        L317:
            return;
        L5:
            if (r3 != 2) goto L7;
            TextInfoPrivacyCell r12 = (TextInfoPrivacyCell) r22.itemView;
            r12.setFixedSize(0);
            if (r23 != ThemeActivity.m20623$$Nest$fgetautomaticBrightnessInfoRow(this.this$0)) goto L229;
            r12.setText(LocaleController.formatString("AutoNightBrightnessInfo", R.string.AutoNightBrightnessInfo, new Object[]{Integer.valueOf((int) (Theme.autoNightBrighnessThreshold * 100.0f))}));
            return;
        L229:
            int r35 = ThemeActivity.m20678$$Nest$fgetscheduleLocationInfoRow(this.this$0);
            ThemeActivity r42 = this.this$0;
            if (r23 != r35) goto L234;
            r12.setText(ThemeActivity.m20711$$Nest$mgetLocationSunString(r42));
            return;
        L234:
            if (r23 != ThemeActivity.m20692$$Nest$fgetswipeGestureInfoRow(r42)) goto L238;
            r12.setText(LocaleController.getString("ChatListSwipeGestureInfo", R.string.ChatListSwipeGestureInfo));
            return;
        L238:
            if (r23 != ThemeActivity.m20651$$Nest$fgetliteModeInfoRow(this.this$0)) goto L241;
            r12.setText(LocaleController.getString("LiteModeInfo", R.string.LiteModeInfo));
            return;
        L241:
            r12.setFixedSize(12);
            r12.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            return;
        L7:
            if (r3 != 4) goto L9;
            ThemeTypeCell r13 = (ThemeTypeCell) r22.itemView;
            if (r23 != ThemeActivity.m20659$$Nest$fgetnightDisabledRow(this.this$0)) goto L200;
            String r06 = LocaleController.getString("AutoNightDisabled", R.string.AutoNightDisabled);
            if (Theme.selectedAutoNightType != 0) goto L197;
            r4 = true;
        L197:
            r13.setValue(r06, r4, true);
            return;
        L200:
            if (r23 != ThemeActivity.m20660$$Nest$fgetnightScheduledRow(this.this$0)) goto L207;
            String r07 = LocaleController.getString("AutoNightScheduled", R.string.AutoNightScheduled);
            if (Theme.selectedAutoNightType != 1) goto L204;
            r4 = true;
        L204:
            r13.setValue(r07, r4, true);
            return;
        L207:
            if (r23 != ThemeActivity.m20658$$Nest$fgetnightAutomaticRow(this.this$0)) goto L218;
            String r29 = LocaleController.getString("AutoNightAdaptive", R.string.AutoNightAdaptive);
            if (Theme.selectedAutoNightType != 2) goto L211;
            boolean r36 = true;
        L213:
            if (ThemeActivity.m20661$$Nest$fgetnightSystemDefaultRow(this.this$0) == (-1)) goto L215;
            r4 = true;
        L215:
            r13.setValue(r29, r36, r4);
            return;
        L211:
            r36 = false;
            goto L213
        L218:
            if (r23 != ThemeActivity.m20661$$Nest$fgetnightSystemDefaultRow(this.this$0)) goto L316;
            String r08 = LocaleController.getString("AutoNightSystemDefault", R.string.AutoNightSystemDefault);
            if (Theme.selectedAutoNightType == 3) goto L223;
            r5 = false;
        L223:
            r13.setValue(r08, r5, false);
            return;
        L316:
            return;
        L9:
            if (r3 != 5) goto L11;
            HeaderCell r14 = (HeaderCell) r22.itemView;
            if (r23 != ThemeActivity.m20677$$Nest$fgetscheduleHeaderRow(this.this$0)) goto L145;
            r14.setText(LocaleController.getString("AutoNightSchedule", R.string.AutoNightSchedule));
            return;
        L145:
            if (r23 != ThemeActivity.m20625$$Nest$fgetautomaticHeaderRow(this.this$0)) goto L149;
            r14.setText(LocaleController.getString("AutoNightBrightness", R.string.AutoNightBrightness));
            return;
        L149:
            if (r23 != ThemeActivity.m20668$$Nest$fgetpreferedHeaderRow(this.this$0)) goto L153;
            r14.setText(LocaleController.getString("AutoNightPreferred", R.string.AutoNightPreferred));
            return;
        L153:
            if (r23 != ThemeActivity.m20687$$Nest$fgetsettingsRow(this.this$0)) goto L156;
            r14.setText(LocaleController.getString("SETTINGS", R.string.SETTINGS));
            return;
        L156:
            int r37 = ThemeActivity.m20697$$Nest$fgetthemeHeaderRow(this.this$0);
            ThemeActivity r43 = this.this$0;
            if (r23 != r37) goto L165;
            if (ThemeActivity.m20641$$Nest$fgetcurrentType(r43) != 3) goto L162;
            r14.setText(LocaleController.getString("BuildMyOwnTheme", R.string.BuildMyOwnTheme));
            return;
        L162:
            r14.setText(LocaleController.getString("ColorTheme", R.string.ColorTheme));
            return;
        L165:
            if (r23 != ThemeActivity.m20694$$Nest$fgettextSizeHeaderRow(r43)) goto L169;
            r14.setText(LocaleController.getString("TextSizeHeader", R.string.TextSizeHeader));
            return;
        L169:
            if (r23 != ThemeActivity.m20635$$Nest$fgetchatListHeaderRow(this.this$0)) goto L173;
            r14.setText(LocaleController.getString("ChatList", R.string.ChatList));
            return;
        L173:
            if (r23 != ThemeActivity.m20630$$Nest$fgetbubbleRadiusHeaderRow(this.this$0)) goto L177;
            r14.setText(LocaleController.getString("BubbleRadius", R.string.BubbleRadius));
            return;
        L177:
            if (r23 != ThemeActivity.m20691$$Nest$fgetswipeGestureHeaderRow(this.this$0)) goto L181;
            r14.setText(LocaleController.getString("ChatListSwipeGesture", R.string.ChatListSwipeGesture));
            return;
        L181:
            if (r23 != ThemeActivity.m20683$$Nest$fgetselectThemeHeaderRow(this.this$0)) goto L185;
            r14.setText(LocaleController.getString("SelectTheme", R.string.SelectTheme));
            return;
        L185:
            if (r23 != ThemeActivity.m20664$$Nest$fgetotherHeaderRow(this.this$0)) goto L189;
            r14.setText(LocaleController.getString("OtherSettings", R.string.OtherSettings));
            return;
        L189:
            if (r23 != ThemeActivity.m20653$$Nest$fgetmediaSoundHeaderRow(this.this$0)) goto L315;
            r14.setText(LocaleController.getString("MediaAndSoundSettings", R.string.MediaAndSoundSettings));
            return;
        L315:
            return;
        L11:
            if (r3 != 6) goto L13;
            ((BrightnessControlCell) r22.itemView).setProgress(Theme.autoNightBrighnessThreshold);
            return;
        L13:
            if (r3 != 7) goto L15;
            TextCheckCell r62 = (TextCheckCell) r22.itemView;
            if (r23 != ThemeActivity.m20679$$Nest$fgetscheduleLocationRow(this.this$0)) goto L99;
            r62.setTextAndCheck(LocaleController.getString("AutoNightLocation", R.string.AutoNightLocation), Theme.autoNightScheduleByLocation, true);
            return;
        L99:
            if (r23 != ThemeActivity.m20647$$Nest$fgetenableAnimationsRow(this.this$0)) goto L103;
            r62.setTextAndCheck(LocaleController.getString("EnableAnimations", R.string.EnableAnimations), MessagesController.getGlobalMainSettings().getBoolean("view_animations", true), true);
            return;
        L103:
            if (r23 != ThemeActivity.m20684$$Nest$fgetsendByEnterRow(this.this$0)) goto L107;
            r62.setTextAndCheck(LocaleController.getString("SendByEnter", R.string.SendByEnter), MessagesController.getGlobalMainSettings().getBoolean("send_by_enter", false), true);
            return;
        L107:
            if (r23 != ThemeActivity.m20670$$Nest$fgetraiseToSpeakRow(this.this$0)) goto L111;
            r62.setTextAndValueAndCheck(LocaleController.getString("RaiseToSpeak", R.string.RaiseToSpeak), LocaleController.getString("RaiseToSpeakInfo", R.string.RaiseToSpeakInfo), SharedConfig.raiseToSpeak, true, true);
            return;
        L111:
            if (r23 != ThemeActivity.m20669$$Nest$fgetraiseToListenRow(this.this$0)) goto L115;
            r62.setTextAndValueAndCheck(LocaleController.getString("RaiseToListen", R.string.RaiseToListen), LocaleController.getString("RaiseToListenInfo", R.string.RaiseToListenInfo), SharedConfig.raiseToListen, true, true);
            return;
        L115:
            if (r23 != ThemeActivity.m20657$$Nest$fgetnextMediaTapRow(this.this$0)) goto L119;
            r62.setTextAndValueAndCheck(LocaleController.getString("NextMediaTap", R.string.NextMediaTap), LocaleController.getString("NextMediaTapInfo", R.string.NextMediaTapInfo), SharedConfig.nextMediaTap, true, true);
            return;
        L119:
            if (r23 != ThemeActivity.m20667$$Nest$fgetpauseOnRecordRow(this.this$0)) goto L123;
            r62.setTextAndValueAndCheck(LocaleController.getString(R.string.PauseMusicOnRecord), LocaleController.getString("PauseMusicOnRecordInfo", R.string.PauseMusicOnRecordInfo), SharedConfig.pauseMusicOnRecord, true, true);
            return;
        L123:
            if (r23 != ThemeActivity.m20666$$Nest$fgetpauseOnMediaRow(this.this$0)) goto L127;
            r62.setTextAndCheck(LocaleController.getString(R.string.PauseMusicOnMedia), SharedConfig.pauseMusicOnMedia, true);
            return;
        L127:
            if (r23 != ThemeActivity.m20644$$Nest$fgetdirectShareRow(this.this$0)) goto L131;
            r62.setTextAndValueAndCheck(LocaleController.getString("DirectShare", R.string.DirectShare), LocaleController.getString("DirectShareInfo", R.string.DirectShareInfo), SharedConfig.directShare, false, true);
            return;
        L131:
            if (r23 != ThemeActivity.m20685$$Nest$fgetsensitiveContentRow(this.this$0)) goto L135;
            r62.setTextAndValueAndCheck(LocaleController.getString(R.string.ShowSensitiveContent), LocaleController.getString(R.string.ShowSensitiveContentInfo), this.this$0.getMessagesController().showSensitiveContent(), true, true);
            return;
        L135:
            if (r23 != ThemeActivity.m20634$$Nest$fgetchatBlurRow(this.this$0)) goto L314;
            r62.setTextAndCheck(LocaleController.getString("BlurInChat", R.string.BlurInChat), SharedConfig.chatBlurEnabled(), true);
            return;
        L314:
            return;
        L15:
            if (r3 != 14) goto L17;
            TextCell r15 = (TextCell) r22.itemView;
            r15.heightDp = 48;
            if (r23 != ThemeActivity.m20626$$Nest$fgetbackgroundRow(this.this$0)) goto L75;
            r15.setSubtitle(null);
            int r210 = Theme.key_windowBackgroundWhiteBlueText4;
            r15.setColors(r210, r210);
            String r211 = LocaleController.getString(R.string.ChangeChatBackground);
            int r38 = R.drawable.msg_background;
            if (ThemeActivity.m20633$$Nest$fgetchangeUserColor(this.this$0) < 0) goto L72;
            r4 = true;
        L72:
            r15.setTextAndIcon(r211, r38, r4);
            return;
        L75:
            if (r23 != ThemeActivity.m20646$$Nest$fgeteditThemeRow(this.this$0)) goto L79;
            r15.setSubtitle(null);
            int r09 = Theme.key_windowBackgroundWhiteBlueText4;
            r15.setColors(r09, r09);
            r15.setTextAndIcon(LocaleController.getString(R.string.EditCurrentTheme), R.drawable.msg_theme, true);
            return;
        L79:
            if (r23 != ThemeActivity.m20640$$Nest$fgetcreateNewThemeRow(this.this$0)) goto L83;
            r15.setSubtitle(null);
            int r010 = Theme.key_windowBackgroundWhiteBlueText4;
            r15.setColors(r010, r010);
            r15.setTextAndIcon(LocaleController.getString(R.string.CreateNewTheme), R.drawable.msg_colors, false);
            return;
        L83:
            if (r23 != ThemeActivity.m20652$$Nest$fgetliteModeRow(this.this$0)) goto L87;
            r15.setColors(Theme.key_dialogIcon, Theme.key_windowBackgroundWhiteBlackText);
            r15.setTextAndIcon(LocaleController.getString(R.string.LiteMode), R.drawable.msg2_animations, true);
            r15.setSubtitle(LocaleController.getString(R.string.LiteModeInfo));
            r15.heightDp = 60;
            r15.offsetFromImage = 64;
            r15.imageLeft = 20;
            return;
        L87:
            if (r23 != ThemeActivity.m20628$$Nest$fgetbrowseThemesRow(this.this$0)) goto L91;
            r15.setSubtitle(null);
            int r011 = Theme.key_windowBackgroundWhiteBlueText4;
            r15.setColors(r011, r011);
            r15.setTextAndIcon(LocaleController.getString(R.string.SettingsBrowseThemes), R.drawable.msg_colors, false);
            return;
        L91:
            if (r23 != ThemeActivity.m20689$$Nest$fgetstickersRow(this.this$0)) goto L313;
            r15.setColors(Theme.key_dialogIcon, Theme.key_windowBackgroundWhiteBlackText);
            r15.setTextAndIcon(LocaleController.getString(R.string.StickersName), R.drawable.msg2_sticker, false);
            r15.setSubtitle(LocaleController.getString(R.string.StickersNameInfo2));
            r15.offsetFromImage = 64;
            r15.heightDp = 60;
            r15.imageLeft = 20;
            return;
        L313:
            return;
        L17:
            if (r3 != 17) goto L19;
            ((DefaultThemesPreviewCell) r22.itemView).updateDayNightMode();
            return;
        L19:
            if (r3 != 19) goto L21;
            RadioButtonCell r16 = (RadioButtonCell) r22.itemView;
            if (r23 != ThemeActivity.m20672$$Nest$fgetsaveToGalleryOption1Row(this.this$0)) goto L63;
            r16.setTextAndValue("save media only from peer chats", _UrlKt.FRAGMENT_ENCODE_SET, true, false);
            return;
        L63:
            r16.setTextAndValue("save media from all chats", _UrlKt.FRAGMENT_ENCODE_SET, true, false);
            return;
        L21:
            if (r3 == 21) goto L57;
            switch(r3) {
                case 10: goto L34;
                case 11: goto L31;
                case 12: goto L24;
                default: goto L318;
            };
        L24:
            RecyclerListView r17 = (RecyclerListView) r22.itemView;
            ThemeAccentsListAdapter r212 = (ThemeAccentsListAdapter) r17.getAdapter();
            r212.notifyDataSetChanged();
            int r39 = ThemeAccentsListAdapter.m20729$$Nest$mfindCurrentAccent(r212);
            if (r39 != (-1)) goto L27;
            r39 = r212.getItemCount() - 1;
        L27:
            if (r39 == (-1)) goto L310;
            ((LinearLayoutManager) r17.getLayoutManager()).scrollToPositionWithOffset(r39, (ThemeActivity.m20650$$Nest$fgetlistView(this.this$0).getMeasuredWidth() / 2) - AndroidUtilities.dp(42.0f));
            return;
        L310:
            return;
        L34:
            NotificationsCheckCell r132 = (NotificationsCheckCell) r22.itemView;
            if (r23 != ThemeActivity.m20662$$Nest$fgetnightThemeRow(this.this$0)) goto L54;
            if (Theme.selectedAutoNightType == 0) goto L39;
            boolean r172 = true;
        L40:
            if (r172 == false) goto L42;
            String r012 = Theme.getCurrentNightThemeName();
        L43:
            if (r172 == false) goto L51;
            int r18 = Theme.selectedAutoNightType;
            if (r18 != 1) goto L47;
            String r19 = LocaleController.getString("AutoNightScheduled", R.string.AutoNightScheduled);
        L50:
            r012 = r19 + " " + r012;
            goto L51
        L47:
            if (r18 != 3) goto L49;
            r19 = LocaleController.getString("AutoNightSystemDefault", R.string.AutoNightSystemDefault);
            goto L50
        L49:
            r19 = LocaleController.getString("AutoNightAdaptive", R.string.AutoNightAdaptive);
        L51:
            r132.setTextAndValueAndIconAndCheck(LocaleController.getString("AutoNightTheme", R.string.AutoNightTheme), r012, R.drawable.menu_night_mode_24, r172, 0, false, true);
            return;
        L42:
            r012 = LocaleController.getString("AutoNightThemeOff", R.string.AutoNightThemeOff);
            goto L43
        L39:
            r172 = false;
            goto L40
        L54:
            if (r23 != ThemeActivity.m20629$$Nest$fgetbrowserRow(this.this$0)) goto L312;
            r132.setTextAndValueAndIconAndCheck(LocaleController.getString(R.string.InappBrowser), LocaleController.getString(R.string.InappBrowserInfo), R.drawable.msg2_language, this.this$0.getMessagesController().isWebBrowserInAppEnabled(), 0, false, true);
            return;
        L312:
            return;
        L318:
            return;
        L31:
            if (this.first == false) goto L311;
            ThemeActivity.m20702$$Nest$fgetthemesHorizontalListCell(this.this$0).scrollToCurrentTheme(ThemeActivity.m20650$$Nest$fgetlistView(this.this$0).getMeasuredWidth(), false);
            this.first = false;
            return;
        L311:
            return;
        L57:
            ((PeerColorActivity.ChangeNameColorCell) r22.itemView).set(this.this$0.getUserConfig().getCurrentUser());
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r10, int r11) {
            switch(r11) {
                case 1: goto L22;
                case 2: goto L21;
                case 3: goto L20;
                case 4: goto L19;
                case 5: goto L18;
                case 6: goto L17;
                case 7: goto L16;
                case 8: goto L15;
                case 9: goto L14;
                case 10: goto L13;
                case 11: goto L12;
                case 12: goto L11;
                case 13: goto L10;
                case 14: goto L4;
                case 15: goto L9;
                case 16: goto L8;
                case 17: goto L7;
                case 18: goto L4;
                case 19: goto L6;
                case 20: goto L4;
                case 21: goto L5;
                default: goto L4;
            };
        L4:
            View r102 = new TextCell(this.mContext);
        L24:
            return new RecyclerListView.Holder(r102);
        L5:
            r102 = new PeerColorActivity.ChangeNameColorCell(ThemeActivity.access$300(this.this$0), 0, this.mContext, this.this$0.getResourceProvider());
            goto L24
        L6:
            r102 = new RadioButtonCell(this.mContext);
            goto L24
        L7:
            Context r112 = this.mContext;
            ThemeActivity r9 = this.this$0;
            View r103 = new DefaultThemesPreviewCell(r112, r9, ThemeActivity.m20641$$Nest$fgetcurrentType(r9));
            r103.setFocusable(false);
            r103.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            r102 = r103;
            goto L24
        L8:
            View r104 = new AnonymousClass5(this, this.mContext, ThemeActivity.access$200(this.this$0), 0);
            r104.setImportantForAccessibility(4);
            r102 = r104;
            goto L24
        L9:
            r102 = new SwipeGestureSettingsView(this.mContext, ThemeActivity.access$100(this.this$0));
            goto L24
        L10:
            r102 = new BubbleRadiusCell(this.this$0, this.mContext);
            goto L24
        L11:
            AnonymousClass4 r105 = new AnonymousClass4(this, this.mContext);
            r105.setFocusable(false);
            r105.setItemAnimator(null);
            r105.setLayoutAnimation(null);
            r105.setPadding(AndroidUtilities.dp(11.0f), 0, AndroidUtilities.dp(11.0f), 0);
            r105.setClipToPadding(false);
            LinearLayoutManager r113 = new LinearLayoutManager(this.mContext);
            r113.setOrientation(0);
            r105.setLayoutManager(r113);
            ThemeAccentsListAdapter r114 = new ThemeAccentsListAdapter(this.this$0, this.mContext);
            r105.setAdapter(r114);
            r105.setOnItemClickListener(new ThemeActivity$ListAdapter$$ExternalSyntheticLambda0(this, r114, r105));
            r105.setOnItemLongClickListener(new ThemeActivity$ListAdapter$$ExternalSyntheticLambda1(this, r114));
            r105.setLayoutParams(new RecyclerView.LayoutParams(-1, AndroidUtilities.dp(62.0f)));
            r102 = r105;
            goto L24
        L12:
            this.first = true;
            ThemeActivity r106 = this.this$0;
            Context r4 = this.mContext;
            ThemeActivity r5 = this.this$0;
            ThemeActivity.m20706$$Nest$fputthemesHorizontalListCell(r106, new AnonymousClass3(this, r4, r5, ThemeActivity.m20641$$Nest$fgetcurrentType(r5), ThemeActivity.m20643$$Nest$fgetdefaultThemes(this.this$0), ThemeActivity.m20642$$Nest$fgetdarkThemes(this.this$0)));
            ThemeActivity.m20702$$Nest$fgetthemesHorizontalListCell(this.this$0).setDrawDivider(this.this$0.hasThemeAccents);
            ThemeActivity.m20702$$Nest$fgetthemesHorizontalListCell(this.this$0).setFocusable(false);
            View r107 = ThemeActivity.m20702$$Nest$fgetthemesHorizontalListCell(this.this$0);
            r107.setLayoutParams(new RecyclerView.LayoutParams(-1, AndroidUtilities.dp(148.0f)));
            r102 = r107;
            goto L24
        L13:
            r102 = new NotificationsCheckCell(this.mContext, 21, 60, true);
            goto L24
        L14:
            r102 = new AnonymousClass2(this, this.mContext);
            goto L24
        L15:
            r102 = new TextSizeCell(this.this$0, this.mContext);
            goto L24
        L16:
            r102 = new TextCheckCell(this.mContext);
            goto L24
        L17:
            r102 = new AnonymousClass1(this, this.mContext, 0);
            goto L24
        L18:
            r102 = new HeaderCell(this.mContext);
            goto L24
        L19:
            r102 = new ThemeTypeCell(this.mContext);
            goto L24
        L20:
            r102 = new ShadowSectionCell(this.mContext);
            goto L24
        L21:
            r102 = new TextInfoPrivacyCell(this.mContext);
            goto L24
        L22:
            r102 = new TextSettingsCell(this.mContext);
            goto L24
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewAttachedToWindow(RecyclerView.ViewHolder r2) {
            if (r2.getItemViewType() != 4) goto L10;
            ThemeTypeCell r1 = (ThemeTypeCell) r2.itemView;
            if (r2.getAdapterPosition() != Theme.selectedAutoNightType) goto L7;
            boolean r22 = true;
        L8:
            r1.setTypeChecked(r22);
            return;
        L7:
            r22 = false;
            goto L8
        }
    }

    public class TextSizeCell extends FrameLayout {
        private int endFontSize;
        private int lastWidth;
        private ThemePreviewMessagesCell messagesCell;
        private SeekBarView sizeBar;
        private int startFontSize;
        private TextPaint textPaint;
        final /* synthetic */ ThemeActivity this$0;

        /* renamed from: org.telegram.ui.ThemeActivity$TextSizeCell$1, reason: invalid class name */
        public class AnonymousClass1 implements SeekBarView.SeekBarViewDelegate {
            final /* synthetic */ TextSizeCell this$1;
            final /* synthetic */ ThemeActivity val$this$0;

            public AnonymousClass1(TextSizeCell r1, ThemeActivity r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
            public CharSequence getContentDescription() {
                return String.valueOf(Math.round(TextSizeCell.m20727$$Nest$fgetstartFontSize(this.this$1) + ((TextSizeCell.m20724$$Nest$fgetendFontSize(this.this$1) - TextSizeCell.m20727$$Nest$fgetstartFontSize(this.this$1)) * TextSizeCell.m20726$$Nest$fgetsizeBar(this.this$1).getProgress())));
            }

            @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
            public int getStepsCount() {
                return TextSizeCell.m20724$$Nest$fgetendFontSize(this.this$1) - TextSizeCell.m20727$$Nest$fgetstartFontSize(this.this$1);
            }

            @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
            public void onSeekBarDrag(boolean r3, float r4) {
                ThemeActivity.m20713$$Nest$msetFontSize(this.this$1.this$0, Math.round(TextSizeCell.m20727$$Nest$fgetstartFontSize(r3) + ((TextSizeCell.m20724$$Nest$fgetendFontSize(this.this$1) - TextSizeCell.m20727$$Nest$fgetstartFontSize(this.this$1)) * r4)));
            }

            @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
            public void onSeekBarPressed(boolean r1) {
            }
        }

        /* renamed from: -$$Nest$fgetendFontSize, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m20724$$Nest$fgetendFontSize(TextSizeCell r0) {
            return r0.endFontSize;
        }

        /* renamed from: -$$Nest$fgetmessagesCell, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ThemePreviewMessagesCell m20725$$Nest$fgetmessagesCell(TextSizeCell r0) {
            return r0.messagesCell;
        }

        /* renamed from: -$$Nest$fgetsizeBar, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SeekBarView m20726$$Nest$fgetsizeBar(TextSizeCell r0) {
            return r0.sizeBar;
        }

        /* renamed from: -$$Nest$fgetstartFontSize, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m20727$$Nest$fgetstartFontSize(TextSizeCell r0) {
            return r0.startFontSize;
        }

        public TextSizeCell(ThemeActivity r10, Context r11) {
            this.this$0 = r10;
            super(r11);
            this.startFontSize = 12;
            this.endFontSize = 30;
            setWillNotDraw(false);
            TextPaint r1 = new TextPaint(1);
            this.textPaint = r1;
            r1.setTextSize(AndroidUtilities.dp(16.0f));
            SeekBarView r12 = new SeekBarView(r11);
            this.sizeBar = r12;
            r12.setReportChanges(true);
            this.sizeBar.setSeparatorsCount((this.endFontSize - this.startFontSize) + 1);
            this.sizeBar.setDelegate(new AnonymousClass1(this, r10));
            this.sizeBar.setImportantForAccessibility(2);
            addView(this.sizeBar, LayoutHelper.createFrame(-1, 38.0f, 51, 5.0f, 5.0f, 39.0f, 0.0f));
            ThemePreviewMessagesCell r13 = new ThemePreviewMessagesCell(r11, ThemeActivity.access$000(r10), 0);
            this.messagesCell = r13;
            r13.setImportantForAccessibility(4);
            addView(this.messagesCell, LayoutHelper.createFrame(-1, -2.0f, 51, 0.0f, 53.0f, 0.0f, 0.0f));
        }

        @Override // android.view.View
        public void invalidate() {
            super.invalidate();
            this.messagesCell.invalidate();
            this.sizeBar.invalidate();
        }

        @Override // android.view.View
        public void onDraw(Canvas r4) {
            this.textPaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteValueText));
            r4.drawText(_UrlKt.FRAGMENT_ENCODE_SET + SharedConfig.fontSize, getMeasuredWidth() - AndroidUtilities.dp(39.0f), AndroidUtilities.dp(28.0f), this.textPaint);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
            super.onInitializeAccessibilityNodeInfo(r2);
            this.sizeBar.getSeekBarAccessibilityDelegate().onInitializeAccessibilityNodeInfoInternal(this, r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r4, int r5) {
            super.onMeasure(r4, r5);
            int r42 = View.MeasureSpec.getSize(r4);
            if (this.lastWidth == r42) goto L6;
            SeekBarView r52 = this.sizeBar;
            int r0 = SharedConfig.fontSize;
            int r1 = this.startFontSize;
            r52.setProgress((r0 - r1) / (this.endFontSize - r1));
            this.lastWidth = r42;
            return;
        }

        @Override // android.view.View
        public boolean performAccessibilityAction(int r2, Bundle r3) {
            if (super.performAccessibilityAction(r2, r3) == false) goto L5;
            return true;
        L5:
            if (this.sizeBar.getSeekBarAccessibilityDelegate().performAccessibilityActionInternal(this, r2, r3) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    public class ThemeAccentsListAdapter extends RecyclerListView.SelectionAdapter {
        private Theme.ThemeInfo currentTheme;
        private Context mContext;
        private int monetAccentsCount;
        private ArrayList<Theme.ThemeAccent> themeAccents;
        final /* synthetic */ ThemeActivity this$0;

        /* renamed from: -$$Nest$fgetcurrentTheme, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Theme.ThemeInfo m20728$$Nest$fgetcurrentTheme(ThemeAccentsListAdapter r0) {
            return r0.currentTheme;
        }

        /* renamed from: -$$Nest$mfindCurrentAccent, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m20729$$Nest$mfindCurrentAccent(ThemeAccentsListAdapter r0) {
            return r0.findCurrentAccent();
        }

        /* renamed from: -$$Nest$mgetAccent, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Theme.ThemeAccent m20730$$Nest$mgetAccent(ThemeAccentsListAdapter r0, int r1) {
            return r0.getAccent(r1);
        }

        /* renamed from: -$$Nest$mgetCustomAccentPosition, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m20731$$Nest$mgetCustomAccentPosition(ThemeAccentsListAdapter r0) {
            return r0.getCustomAccentPosition();
        }

        /* renamed from: -$$Nest$misDividerPosition, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m20732$$Nest$misDividerPosition(ThemeAccentsListAdapter r0, int r1) {
            return r0.isDividerPosition(r1);
        }

        public ThemeAccentsListAdapter(ThemeActivity r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
            notifyDataSetChanged();
        }

        private int findCurrentAccent() {
            int r0 = this.themeAccents.indexOf(this.currentTheme.getAccent(false));
            if (r0 != (-1)) goto L6;
            return -1;
        L6:
            if (hasDivider() == true) goto L8;
            return r0;
        L8:
            if (r0 >= this.monetAccentsCount) goto L10;
            return r0;
        L10:
            return r0 + 1;
        }

        private Theme.ThemeAccent getAccent(int r3) {
            ArrayList<Theme.ThemeAccent> r0 = this.themeAccents;
            if (hasDivider() == true) goto L5;
        L7:
            int r2 = 0;
        L9:
            return r0.get(r3 - r2);
        L5:
            if (r3 <= this.monetAccentsCount) goto L7;
            r2 = 1;
            goto L9
        }

        private int getCustomAccentPosition() {
            return getItemCount() - 1;
        }

        private boolean hasDivider() {
            int r0 = this.monetAccentsCount;
            if (r0 > 0) goto L5;
            return false;
        L5:
            if (r0 >= this.themeAccents.size()) goto L10;
            return true;
        L10:
            return false;
        }

        private boolean isDividerPosition(int r2) {
            if (hasDivider() == true) goto L5;
            return false;
        L5:
            if (r2 != this.monetAccentsCount) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            if (this.themeAccents.isEmpty() == false) goto L7;
            return 0;
        L7:
            return (this.themeAccents.size() + 1) + (hasDivider() ? 1 : 0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (r2 != getCustomAccentPosition()) goto L7;
            return 1;
        L7:
            if (isDividerPosition(r2) == false) goto L10;
            return 2;
        L10:
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            if (ThemeActivity.m20641$$Nest$fgetcurrentType(this.this$0) != 1) goto L5;
            Theme.ThemeInfo r0 = Theme.getCurrentNightTheme();
        L6:
            this.currentTheme = r0;
            ArrayList<Theme.ThemeAccent> r02 = new ArrayList(this.currentTheme.themeAccents);
            this.themeAccents = r02;
            this.monetAccentsCount = MonetAccentHelper.countLeadingMonetAccents(r02);
            super.notifyDataSetChanged();
            return;
        L5:
            r0 = Theme.getCurrentTheme();
            goto L6
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r2, int r3) {
            int r0 = getItemViewType(r3);
            if (r0 != 0) goto L5;
            ((InnerAccentView) r2.itemView).setThemeAndColor(this.currentTheme, getAccent(r3));
            return;
        L5:
            if (r0 == 1) goto L7;
            return;
        L7:
            InnerCustomAccentView.m20720$$Nest$msetTheme((InnerCustomAccentView) r2.itemView, this.currentTheme);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r1, int r2) {
            if (r2 == 0) goto L10;
            if (r2 == 2) goto L8;
            return new RecyclerListView.Holder(new InnerCustomAccentView(this.mContext));
        L8:
            return new RecyclerListView.Holder(new AccentDividerView(this.mContext));
        L10:
            return new RecyclerListView.Holder(new InnerAccentView(this.mContext));
        }
    }

    public static abstract class TintRecyclerListView extends RecyclerListView {
        public TintRecyclerListView(Context r1) {
            super(r1);
        }
    }

    /* renamed from: $r8$lambda$-Kgq9_5Nx8c8tMsTcVS06ovpOmQ, reason: not valid java name */
    public static /* synthetic */ int m20608$r8$lambda$Kgq9_5Nx8c8tMsTcVS06ovpOmQ(ThemeActivity r0) {
        return r0.lambda$createView$14();
    }

    public static /* synthetic */ void $r8$lambda$688AsBZjZXDcD1S0Yj_iDabMF5Q(ButtonWithCounterView r22, MessagesController r23, int r24, Context r25, Theme.ResourcesProvider r26, int r27, Utilities.Callback r28, BottomSheet[] r29, Long r30) {
        if (r30 != null) goto L6;
        r22.setLoading(false);
        return;
    L6:
        TLRPC.User r18 = r23.getUser(r30);
        if (r18 != null) goto L10;
        r22.setLoading(false);
        return;
    L10:
        BaseFragment r3 = LaunchActivity.getSafeLastFragment();
        if (r3 != null) goto L14;
        r22.setLoading(false);
        return;
    L14:
        WebViewRequestProps r32 = WebViewRequestProps.of(r24, r30.longValue(), r30.longValue(), null, null, 4, 0, 0, false, null, false, null, r18, 0, false, false);
        BotWebViewSheet r4 = new BotWebViewSheet(r25, r26);
        r4.setOnVerifiedAge(new ThemeActivity$$ExternalSyntheticLambda24(r27, r4, r28));
        r4.setDefaultFullsize(true);
        r4.setNeedsContext(false);
        r4.setParentActivity(r3.getParentActivity());
        r4.requestWebView(r3, r32);
        r4.show();
        r22.setLoading(false);
        r29[0].dismiss();
    }

    public static /* synthetic */ void $r8$lambda$6xCzFOLYRwOPG2f28Zb0Nx0xjmE(ThemeActivity r0, AtomicReference r1, Boolean r2) {
        r0.lambda$createView$6(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$AWraVCUPGbfKTn69F6LcJtveDKE(ThemeActivity r0, String r1) {
        r0.lambda$updateSunTime$22(r1);
    }

    public static /* synthetic */ void $r8$lambda$E6HqbLONEuxB220ELwLeVeSEavg(ThemeActivity r0, Context r1, View r2, int r3, float r4, float r5) {
        r0.lambda$createView$13(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$PHXx51spWHd5j-Air8eh4R4ieak, reason: not valid java name */
    public static /* synthetic */ void m20609$r8$lambda$PHXx51spWHd5jAir8eh4R4ieak(ThemeActivity r0, Runnable r1, AlertDialog r2, int r3) {
        r0.lambda$createView$10(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$RDYnJnFitcwfnyCjEBWrLOKA-qw, reason: not valid java name */
    public static /* synthetic */ void m20610$r8$lambda$RDYnJnFitcwfnyCjEBWrLOKAqw(int r0, BotWebViewSheet r1, Utilities.Callback r2, Boolean r3, Double r4, String r5, Double r6) {
        if (r4 != null) goto L4;
        boolean r02 = r3.booleanValue();
    L8:
        r1.dismiss();
        r2.run(Boolean.valueOf(r02));
        BaseFragment r12 = LaunchActivity.getSafeLastFragment();
        if (r02 == false) goto L13;
        if (r12 == null) goto L14;
        BulletinFactory.of(r12).createSimpleBulletin(R.raw.contact_check, LocaleController.getString(R.string.AgeVerificationPassedTitle)).show();
        return;
    L14:
        return;
    L13:
        return;
    L4:
        if (r4.doubleValue() < r0) goto L6;
        r02 = true;
        goto L8
    L6:
        r02 = false;
        goto L8
    }

    public static /* synthetic */ void $r8$lambda$T5OFxL7Z5J0q0QRT0aEa9deNX5Y(ThemeActivity r0, AtomicReference r1, View r2) {
        r0.lambda$createView$5(r1, r2);
    }

    public static /* synthetic */ int $r8$lambda$TMrq7uOpSA_dP3Fw6PdT2JJeFIA(Theme.ThemeInfo r0, Theme.ThemeInfo r1) {
        return Integer.compare(r0.sortIndex, r1.sortIndex);
    }

    public static /* synthetic */ void $r8$lambda$TTkE90dXmVq61KxqIqb429nH58M(ThemeActivity r0) {
        r0.lambda$updateSunTime$23();
    }

    public static /* synthetic */ void $r8$lambda$UnJHKCudOq6YyLodlI2_0EGVUds(ThemeActivity r0, AlertDialog r1, int r2) {
        r0.lambda$createNewTheme$16(r1, r2);
    }

    /* renamed from: $r8$lambda$WyYr4iW3AE3-vZwuyZlSiOH2emE, reason: not valid java name */
    public static /* synthetic */ void m20611$r8$lambda$WyYr4iW3AE3vZwuyZlSiOH2emE(ThemeActivity r0, View r1) {
        r0.lambda$createView$8(r1);
    }

    /* renamed from: $r8$lambda$XlJxHor5V-0Uy8jknfANjs6yLaU, reason: not valid java name */
    public static /* synthetic */ void m20612$r8$lambda$XlJxHor5V0Uy8jknfANjs6yLaU(ButtonWithCounterView r10, MessagesController r11, String r12, int r13, Context r14, Theme.ResourcesProvider r15, int r16, Utilities.Callback r17, BottomSheet[] r18, Boolean r19) {
        if (r19.booleanValue() == true) goto L6;
        r10.setLoading(false);
        return;
    L6:
        r11.getUserNameResolver().resolve(r12, new ThemeActivity$$ExternalSyntheticLambda22(r10, r11, r13, r14, r15, r16, r17, r18));
    }

    /* renamed from: $r8$lambda$XtWUm7x3837pWOxzWoH-3FqSe30, reason: not valid java name */
    public static /* synthetic */ void m20613$r8$lambda$XtWUm7x3837pWOxzWoH3FqSe30(ThemeActivity r0, AtomicReference r1, View r2) {
        r0.lambda$createView$7(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ZAcMtcfW3iKFkSGlrBZ1Q4n6gGQ(ThemeActivity r0, Runnable r1, Boolean r2) {
        r0.lambda$createView$9(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Ztl4Fwxp0664fxoajR0BsGe0Jg8(ThemeActivity r0, int r1, TextSettingsCell r2, TimePicker r3, int r4, int r5) {
        r0.lambda$createView$12(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$b9Y8bCXHenBG2d5WuVwpv8VA6aI(ThemeActivity r0, int r1, AtomicReference r2, View r3) {
        r0.lambda$createView$3(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$dJrupWSEOjssQZh1tvtJuEhJrSU(ThemeActivity r0, DialogInterface r1) {
        r0.lambda$didReceivedNotification$1(r1);
    }

    /* renamed from: $r8$lambda$dPuQfH-oO_GXT2diBk0hGdxy-Wo, reason: not valid java name */
    public static /* synthetic */ void m20614$r8$lambda$dPuQfHoO_GXT2diBk0hGdxyWo(ThemeActivity r0) {
        r0.lambda$createView$15();
    }

    /* renamed from: $r8$lambda$kVEExf-0iogEn2Tl1X5PM8dmxqE, reason: not valid java name */
    public static /* synthetic */ void m20615$r8$lambda$kVEExf0iogEn2Tl1X5PM8dmxqE(ThemeActivity r0, TL_account.contentSettings r1) {
        r0.lambda$createView$2(r1);
    }

    /* renamed from: $r8$lambda$umMO89BLLiOS2o0-hnirbP_KoG8, reason: not valid java name */
    public static /* synthetic */ void m20616$r8$lambda$umMO89BLLiOS2o0hnirbP_KoG8(ThemeActivity r0) {
        r0.lambda$getThemeDescriptions$24();
    }

    /* renamed from: $r8$lambda$uozHz-ELxr3wVaxAYbSzijnvu_w, reason: not valid java name */
    public static /* synthetic */ void m20617$r8$lambda$uozHzELxr3wVaxAYbSzijnvu_w(ButtonWithCounterView r12, MessagesController r13, String r14, int r15, Context r16, Theme.ResourcesProvider r17, int r18, Utilities.Callback r19, BottomSheet[] r20, View r21) {
        if (r12.isLoading() == false) goto L5;
        return;
    L5:
        r12.setLoading(true);
        PermissionRequest.ensurePermission(R.raw.permission_request_camera, R.string.AgeVerificationNeedCameraPermission, "android.permission.CAMERA", new ThemeActivity$$ExternalSyntheticLambda19(r12, r13, r14, r15, r16, r17, r18, r19, r20));
    }

    /* renamed from: $r8$lambda$vZj-H2-2FR-YsVKk4Y2VuF63rWQ, reason: not valid java name */
    public static /* synthetic */ void m20618$r8$lambda$vZjH22FRYsVKk4Y2VuF63rWQ(ThemeActivity r0, AlertDialog r1, int r2) {
        r0.lambda$updateSunTime$21(r1, r2);
    }

    /* renamed from: $r8$lambda$xZEvEPfpTpbXjX-SX8Dc2N2Zp8s, reason: not valid java name */
    public static /* synthetic */ void m20619$r8$lambda$xZEvEPfpTpbXjXSX8Dc2N2Zp8s(ThemeActivity r0, int r1, DialogInterface r2, int r3) {
        r0.lambda$createView$11(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$z-uHzyAPALDyqL4As_prkjFlpGQ, reason: not valid java name */
    public static /* synthetic */ void m20620$r8$lambda$zuHzyAPALDyqL4As_prkjFlpGQ(ThemeActivity r0, int r1, AtomicReference r2, View r3) {
        r0.lambda$createView$4(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetappIconSelectorRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20621$$Nest$fgetappIconSelectorRow(ThemeActivity r0) {
        return r0.appIconSelectorRow;
    }

    /* renamed from: -$$Nest$fgetappIconShadowRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20622$$Nest$fgetappIconShadowRow(ThemeActivity r0) {
        return r0.appIconShadowRow;
    }

    /* renamed from: -$$Nest$fgetautomaticBrightnessInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20623$$Nest$fgetautomaticBrightnessInfoRow(ThemeActivity r0) {
        return r0.automaticBrightnessInfoRow;
    }

    /* renamed from: -$$Nest$fgetautomaticBrightnessRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20624$$Nest$fgetautomaticBrightnessRow(ThemeActivity r0) {
        return r0.automaticBrightnessRow;
    }

    /* renamed from: -$$Nest$fgetautomaticHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20625$$Nest$fgetautomaticHeaderRow(ThemeActivity r0) {
        return r0.automaticHeaderRow;
    }

    /* renamed from: -$$Nest$fgetbackgroundRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20626$$Nest$fgetbackgroundRow(ThemeActivity r0) {
        return r0.backgroundRow;
    }

    /* renamed from: -$$Nest$fgetbluetoothScoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20627$$Nest$fgetbluetoothScoRow(ThemeActivity r0) {
        return r0.bluetoothScoRow;
    }

    /* renamed from: -$$Nest$fgetbrowseThemesRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20628$$Nest$fgetbrowseThemesRow(ThemeActivity r0) {
        return r0.browseThemesRow;
    }

    /* renamed from: -$$Nest$fgetbrowserRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20629$$Nest$fgetbrowserRow(ThemeActivity r0) {
        return r0.browserRow;
    }

    /* renamed from: -$$Nest$fgetbubbleRadiusHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20630$$Nest$fgetbubbleRadiusHeaderRow(ThemeActivity r0) {
        return r0.bubbleRadiusHeaderRow;
    }

    /* renamed from: -$$Nest$fgetbubbleRadiusInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20631$$Nest$fgetbubbleRadiusInfoRow(ThemeActivity r0) {
        return r0.bubbleRadiusInfoRow;
    }

    /* renamed from: -$$Nest$fgetbubbleRadiusRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20632$$Nest$fgetbubbleRadiusRow(ThemeActivity r0) {
        return r0.bubbleRadiusRow;
    }

    /* renamed from: -$$Nest$fgetchangeUserColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20633$$Nest$fgetchangeUserColor(ThemeActivity r0) {
        return r0.changeUserColor;
    }

    /* renamed from: -$$Nest$fgetchatBlurRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20634$$Nest$fgetchatBlurRow(ThemeActivity r0) {
        return r0.chatBlurRow;
    }

    /* renamed from: -$$Nest$fgetchatListHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20635$$Nest$fgetchatListHeaderRow(ThemeActivity r0) {
        return r0.chatListHeaderRow;
    }

    /* renamed from: -$$Nest$fgetchatListInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20636$$Nest$fgetchatListInfoRow(ThemeActivity r0) {
        return r0.chatListInfoRow;
    }

    /* renamed from: -$$Nest$fgetchatListRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20637$$Nest$fgetchatListRow(ThemeActivity r0) {
        return r0.chatListRow;
    }

    /* renamed from: -$$Nest$fgetcontactsReimportRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20638$$Nest$fgetcontactsReimportRow(ThemeActivity r0) {
        return r0.contactsReimportRow;
    }

    /* renamed from: -$$Nest$fgetcontactsSortRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20639$$Nest$fgetcontactsSortRow(ThemeActivity r0) {
        return r0.contactsSortRow;
    }

    /* renamed from: -$$Nest$fgetcreateNewThemeRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20640$$Nest$fgetcreateNewThemeRow(ThemeActivity r0) {
        return r0.createNewThemeRow;
    }

    /* renamed from: -$$Nest$fgetcurrentType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20641$$Nest$fgetcurrentType(ThemeActivity r0) {
        return r0.currentType;
    }

    /* renamed from: -$$Nest$fgetdarkThemes, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m20642$$Nest$fgetdarkThemes(ThemeActivity r0) {
        return r0.darkThemes;
    }

    /* renamed from: -$$Nest$fgetdefaultThemes, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m20643$$Nest$fgetdefaultThemes(ThemeActivity r0) {
        return r0.defaultThemes;
    }

    /* renamed from: -$$Nest$fgetdirectShareRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20644$$Nest$fgetdirectShareRow(ThemeActivity r0) {
        return r0.directShareRow;
    }

    /* renamed from: -$$Nest$fgetdistanceRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20645$$Nest$fgetdistanceRow(ThemeActivity r0) {
        return r0.distanceRow;
    }

    /* renamed from: -$$Nest$fgeteditThemeRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20646$$Nest$fgeteditThemeRow(ThemeActivity r0) {
        return r0.editThemeRow;
    }

    /* renamed from: -$$Nest$fgetenableAnimationsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20647$$Nest$fgetenableAnimationsRow(ThemeActivity r0) {
        return r0.enableAnimationsRow;
    }

    /* renamed from: -$$Nest$fgetlastShadowRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20648$$Nest$fgetlastShadowRow(ThemeActivity r0) {
        return r0.lastShadowRow;
    }

    /* renamed from: -$$Nest$fgetlistAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ListAdapter m20649$$Nest$fgetlistAdapter(ThemeActivity r0) {
        return r0.listAdapter;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m20650$$Nest$fgetlistView(ThemeActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetliteModeInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20651$$Nest$fgetliteModeInfoRow(ThemeActivity r0) {
        return r0.liteModeInfoRow;
    }

    /* renamed from: -$$Nest$fgetliteModeRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20652$$Nest$fgetliteModeRow(ThemeActivity r0) {
        return r0.liteModeRow;
    }

    /* renamed from: -$$Nest$fgetmediaSoundHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20653$$Nest$fgetmediaSoundHeaderRow(ThemeActivity r0) {
        return r0.mediaSoundHeaderRow;
    }

    /* renamed from: -$$Nest$fgetmediaSoundSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20654$$Nest$fgetmediaSoundSectionRow(ThemeActivity r0) {
        return r0.mediaSoundSectionRow;
    }

    /* renamed from: -$$Nest$fgetmenuItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m20655$$Nest$fgetmenuItem(ThemeActivity r0) {
        return r0.menuItem;
    }

    /* renamed from: -$$Nest$fgetnewThemeInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20656$$Nest$fgetnewThemeInfoRow(ThemeActivity r0) {
        return r0.newThemeInfoRow;
    }

    /* renamed from: -$$Nest$fgetnextMediaTapRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20657$$Nest$fgetnextMediaTapRow(ThemeActivity r0) {
        return r0.nextMediaTapRow;
    }

    /* renamed from: -$$Nest$fgetnightAutomaticRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20658$$Nest$fgetnightAutomaticRow(ThemeActivity r0) {
        return r0.nightAutomaticRow;
    }

    /* renamed from: -$$Nest$fgetnightDisabledRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20659$$Nest$fgetnightDisabledRow(ThemeActivity r0) {
        return r0.nightDisabledRow;
    }

    /* renamed from: -$$Nest$fgetnightScheduledRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20660$$Nest$fgetnightScheduledRow(ThemeActivity r0) {
        return r0.nightScheduledRow;
    }

    /* renamed from: -$$Nest$fgetnightSystemDefaultRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20661$$Nest$fgetnightSystemDefaultRow(ThemeActivity r0) {
        return r0.nightSystemDefaultRow;
    }

    /* renamed from: -$$Nest$fgetnightThemeRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20662$$Nest$fgetnightThemeRow(ThemeActivity r0) {
        return r0.nightThemeRow;
    }

    /* renamed from: -$$Nest$fgetnightTypeInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20663$$Nest$fgetnightTypeInfoRow(ThemeActivity r0) {
        return r0.nightTypeInfoRow;
    }

    /* renamed from: -$$Nest$fgetotherHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20664$$Nest$fgetotherHeaderRow(ThemeActivity r0) {
        return r0.otherHeaderRow;
    }

    /* renamed from: -$$Nest$fgetotherSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20665$$Nest$fgetotherSectionRow(ThemeActivity r0) {
        return r0.otherSectionRow;
    }

    /* renamed from: -$$Nest$fgetpauseOnMediaRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20666$$Nest$fgetpauseOnMediaRow(ThemeActivity r0) {
        return r0.pauseOnMediaRow;
    }

    /* renamed from: -$$Nest$fgetpauseOnRecordRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20667$$Nest$fgetpauseOnRecordRow(ThemeActivity r0) {
        return r0.pauseOnRecordRow;
    }

    /* renamed from: -$$Nest$fgetpreferedHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20668$$Nest$fgetpreferedHeaderRow(ThemeActivity r0) {
        return r0.preferedHeaderRow;
    }

    /* renamed from: -$$Nest$fgetraiseToListenRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20669$$Nest$fgetraiseToListenRow(ThemeActivity r0) {
        return r0.raiseToListenRow;
    }

    /* renamed from: -$$Nest$fgetraiseToSpeakRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20670$$Nest$fgetraiseToSpeakRow(ThemeActivity r0) {
        return r0.raiseToSpeakRow;
    }

    /* renamed from: -$$Nest$fgetrowCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20671$$Nest$fgetrowCount(ThemeActivity r0) {
        return r0.rowCount;
    }

    /* renamed from: -$$Nest$fgetsaveToGalleryOption1Row, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20672$$Nest$fgetsaveToGalleryOption1Row(ThemeActivity r0) {
        return r0.saveToGalleryOption1Row;
    }

    /* renamed from: -$$Nest$fgetsaveToGalleryOption2Row, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20673$$Nest$fgetsaveToGalleryOption2Row(ThemeActivity r0) {
        return r0.saveToGalleryOption2Row;
    }

    /* renamed from: -$$Nest$fgetsaveToGallerySectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20674$$Nest$fgetsaveToGallerySectionRow(ThemeActivity r0) {
        return r0.saveToGallerySectionRow;
    }

    /* renamed from: -$$Nest$fgetscheduleFromRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20675$$Nest$fgetscheduleFromRow(ThemeActivity r0) {
        return r0.scheduleFromRow;
    }

    /* renamed from: -$$Nest$fgetscheduleFromToInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20676$$Nest$fgetscheduleFromToInfoRow(ThemeActivity r0) {
        return r0.scheduleFromToInfoRow;
    }

    /* renamed from: -$$Nest$fgetscheduleHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20677$$Nest$fgetscheduleHeaderRow(ThemeActivity r0) {
        return r0.scheduleHeaderRow;
    }

    /* renamed from: -$$Nest$fgetscheduleLocationInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20678$$Nest$fgetscheduleLocationInfoRow(ThemeActivity r0) {
        return r0.scheduleLocationInfoRow;
    }

    /* renamed from: -$$Nest$fgetscheduleLocationRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20679$$Nest$fgetscheduleLocationRow(ThemeActivity r0) {
        return r0.scheduleLocationRow;
    }

    /* renamed from: -$$Nest$fgetscheduleToRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20680$$Nest$fgetscheduleToRow(ThemeActivity r0) {
        return r0.scheduleToRow;
    }

    /* renamed from: -$$Nest$fgetscheduleUpdateLocationRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20681$$Nest$fgetscheduleUpdateLocationRow(ThemeActivity r0) {
        return r0.scheduleUpdateLocationRow;
    }

    /* renamed from: -$$Nest$fgetsearchEngineRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20682$$Nest$fgetsearchEngineRow(ThemeActivity r0) {
        return r0.searchEngineRow;
    }

    /* renamed from: -$$Nest$fgetselectThemeHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20683$$Nest$fgetselectThemeHeaderRow(ThemeActivity r0) {
        return r0.selectThemeHeaderRow;
    }

    /* renamed from: -$$Nest$fgetsendByEnterRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20684$$Nest$fgetsendByEnterRow(ThemeActivity r0) {
        return r0.sendByEnterRow;
    }

    /* renamed from: -$$Nest$fgetsensitiveContentRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20685$$Nest$fgetsensitiveContentRow(ThemeActivity r0) {
        return r0.sensitiveContentRow;
    }

    /* renamed from: -$$Nest$fgetsettings2Row, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20686$$Nest$fgetsettings2Row(ThemeActivity r0) {
        return r0.settings2Row;
    }

    /* renamed from: -$$Nest$fgetsettingsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20687$$Nest$fgetsettingsRow(ThemeActivity r0) {
        return r0.settingsRow;
    }

    /* renamed from: -$$Nest$fgetstickersInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20688$$Nest$fgetstickersInfoRow(ThemeActivity r0) {
        return r0.stickersInfoRow;
    }

    /* renamed from: -$$Nest$fgetstickersRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20689$$Nest$fgetstickersRow(ThemeActivity r0) {
        return r0.stickersRow;
    }

    /* renamed from: -$$Nest$fgetstickersSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20690$$Nest$fgetstickersSectionRow(ThemeActivity r0) {
        return r0.stickersSectionRow;
    }

    /* renamed from: -$$Nest$fgetswipeGestureHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20691$$Nest$fgetswipeGestureHeaderRow(ThemeActivity r0) {
        return r0.swipeGestureHeaderRow;
    }

    /* renamed from: -$$Nest$fgetswipeGestureInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20692$$Nest$fgetswipeGestureInfoRow(ThemeActivity r0) {
        return r0.swipeGestureInfoRow;
    }

    /* renamed from: -$$Nest$fgetswipeGestureRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20693$$Nest$fgetswipeGestureRow(ThemeActivity r0) {
        return r0.swipeGestureRow;
    }

    /* renamed from: -$$Nest$fgettextSizeHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20694$$Nest$fgettextSizeHeaderRow(ThemeActivity r0) {
        return r0.textSizeHeaderRow;
    }

    /* renamed from: -$$Nest$fgettextSizeRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20695$$Nest$fgettextSizeRow(ThemeActivity r0) {
        return r0.textSizeRow;
    }

    /* renamed from: -$$Nest$fgetthemeAccentListRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20696$$Nest$fgetthemeAccentListRow(ThemeActivity r0) {
        return r0.themeAccentListRow;
    }

    /* renamed from: -$$Nest$fgetthemeHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20697$$Nest$fgetthemeHeaderRow(ThemeActivity r0) {
        return r0.themeHeaderRow;
    }

    /* renamed from: -$$Nest$fgetthemeInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20698$$Nest$fgetthemeInfoRow(ThemeActivity r0) {
        return r0.themeInfoRow;
    }

    /* renamed from: -$$Nest$fgetthemeListRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20699$$Nest$fgetthemeListRow(ThemeActivity r0) {
        return r0.themeListRow;
    }

    /* renamed from: -$$Nest$fgetthemeListRow2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20700$$Nest$fgetthemeListRow2(ThemeActivity r0) {
        return r0.themeListRow2;
    }

    /* renamed from: -$$Nest$fgetthemePreviewRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20701$$Nest$fgetthemePreviewRow(ThemeActivity r0) {
        return r0.themePreviewRow;
    }

    /* renamed from: -$$Nest$fgetthemesHorizontalListCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ThemesHorizontalListCell m20702$$Nest$fgetthemesHorizontalListCell(ThemeActivity r0) {
        return r0.themesHorizontalListCell;
    }

    /* renamed from: -$$Nest$fgetupdateDistance, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20703$$Nest$fgetupdateDistance(ThemeActivity r0) {
        return r0.updateDistance;
    }

    /* renamed from: -$$Nest$fgetupdateRecordViaSco, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20704$$Nest$fgetupdateRecordViaSco(ThemeActivity r0) {
        return r0.updateRecordViaSco;
    }

    /* renamed from: -$$Nest$fgetupdateSearchEngine, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20705$$Nest$fgetupdateSearchEngine(ThemeActivity r0) {
        return r0.updateSearchEngine;
    }

    /* renamed from: -$$Nest$fputthemesHorizontalListCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20706$$Nest$fputthemesHorizontalListCell(ThemeActivity r0, ThemesHorizontalListCell r1) {
        r0.themesHorizontalListCell = r1;
    }

    /* renamed from: -$$Nest$fputupdateDistance, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20707$$Nest$fputupdateDistance(ThemeActivity r0, boolean r1) {
        r0.updateDistance = r1;
    }

    /* renamed from: -$$Nest$fputupdateRecordViaSco, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20708$$Nest$fputupdateRecordViaSco(ThemeActivity r0, boolean r1) {
        r0.updateRecordViaSco = r1;
    }

    /* renamed from: -$$Nest$mcreateNewTheme, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20709$$Nest$mcreateNewTheme(ThemeActivity r0) {
        r0.createNewTheme();
    }

    /* renamed from: -$$Nest$meditTheme, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20710$$Nest$meditTheme(ThemeActivity r0) {
        r0.editTheme();
    }

    /* renamed from: -$$Nest$mgetLocationSunString, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m20711$$Nest$mgetLocationSunString(ThemeActivity r0) {
        return r0.getLocationSunString();
    }

    /* renamed from: -$$Nest$msetBubbleRadius, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20712$$Nest$msetBubbleRadius(ThemeActivity r0, int r1, boolean r2) {
        return r0.setBubbleRadius(r1, r2);
    }

    /* renamed from: -$$Nest$msetFontSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20713$$Nest$msetFontSize(ThemeActivity r0, int r1) {
        return r0.setFontSize(r1);
    }

    /* renamed from: -$$Nest$mstopLocationUpdate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20714$$Nest$mstopLocationUpdate(ThemeActivity r0) {
        r0.stopLocationUpdate();
    }

    /* renamed from: -$$Nest$mupdateRows, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20715$$Nest$mupdateRows(ThemeActivity r0, boolean r1) {
        r0.updateRows(r1);
    }

    /* renamed from: -$$Nest$mupdateSunTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20716$$Nest$mupdateSunTime(ThemeActivity r0, Location r1, boolean r2) {
        r0.updateSunTime(r1, r2);
    }

    public ThemeActivity(int r3) {
        this.darkThemes = new ArrayList();
        this.defaultThemes = new ArrayList();
        this.gpsLocationListener = new GpsLocationListener(this, null);
        this.networkLocationListener = new GpsLocationListener(this, null);
        this.currentType = r3;
        updateRows(true);
    }

    public static /* synthetic */ INavigationLayout access$000(ThemeActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ int access$100(ThemeActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ INavigationLayout access$200(ThemeActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ int access$300(ThemeActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ INavigationLayout access$400(ThemeActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ INavigationLayout access$500(ThemeActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ INavigationLayout access$600(ThemeActivity r0) {
        return r0.parentLayout;
    }

    private void createNewTheme() {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        AlertDialog.Builder r0 = new AlertDialog.Builder(getParentActivity());
        r0.setTitle(LocaleController.getString("NewTheme", R.string.NewTheme));
        r0.setMessage(LocaleController.getString("CreateNewThemeAlert", R.string.CreateNewThemeAlert));
        r0.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
        r0.setPositiveButton(LocaleController.getString("CreateTheme", R.string.CreateTheme), new ThemeActivity$$ExternalSyntheticLambda10(this));
        showDialog(r0.create());
    }

    private void editTheme() {
        Theme.ThemeInfo r1 = Theme.getCurrentTheme();
        boolean r4 = MonetAccentHelper.canEditAccent(r1.getAccent(false));
        boolean r5 = true;
        if (this.currentType == 1) goto L6;
        r5 = false;
    L6:
        presentFragment(new ThemePreviewActivity(r1, false, 1, r4, r5));
    }

    private String getLocationSunString() {
        int r4 = Theme.autoNightSunriseTime;
        int r0 = r4 / 60;
        int r42 = r4 - (r0 * 60);
        String r43 = String.format("%02d:%02d", new Object[]{Integer.valueOf(r0), Integer.valueOf(r42)});
        int r1 = Theme.autoNightSunsetTime;
        int r2 = r1 / 60;
        int r12 = r1 - (r2 * 60);
        String r02 = String.format("%02d:%02d", new Object[]{Integer.valueOf(r2), Integer.valueOf(r12)});
        return LocaleController.formatString("AutoNightUpdateLocationInfo", R.string.AutoNightUpdateLocationInfo, new Object[]{r02, r43});
    }

    private /* synthetic */ void lambda$createNewTheme$16(AlertDialog r1, int r2) {
        AlertsCreator.createThemeCreateDialog(this, 0, null, null);
    }

    private /* synthetic */ void lambda$createView$10(Runnable r2, AlertDialog r3, int r4) {
        verifyAge(getContext(), this.currentAccount, new ThemeActivity$$ExternalSyntheticLambda23(this, r2), getResourceProvider());
    }

    private /* synthetic */ void lambda$createView$11(int r2, DialogInterface r3, int r4) {
        SharedPreferences.Editor r32 = MessagesController.getGlobalMainSettings().edit();
        r32.putInt("sortContactsBy", r4);
        r32.apply();
        ListAdapter r1 = this.listAdapter;
        if (r1 == null) goto L6;
        r1.notifyItemChanged(r2);
        return;
    }

    private /* synthetic */ void lambda$createView$12(int r3, TextSettingsCell r4, TimePicker r5, int r6, int r7) {
        int r52 = (r6 * 60) + r7;
        if (r3 != this.scheduleFromRow) goto L6;
        Theme.autoNightDayStartTime = r52;
        r4.setTextAndValue(LocaleController.getString("AutoNightFrom", R.string.AutoNightFrom), String.format("%02d:%02d", new Object[]{Integer.valueOf(r6), Integer.valueOf(r7)}), true);
        return;
    L6:
        Theme.autoNightDayEndTime = r52;
        r4.setTextAndValue(LocaleController.getString("AutoNightTo", R.string.AutoNightTo), String.format("%02d:%02d", new Object[]{Integer.valueOf(r6), Integer.valueOf(r7)}), true);
    }

    private /* synthetic */ void lambda$createView$13(Context r19, View r20, int r21, float r22, float r23) {
        if (r21 != this.enableAnimationsRow) goto L9;
        SharedPreferences r0 = MessagesController.getGlobalMainSettings();
        boolean r3 = r0.getBoolean("view_animations", true);
        SharedPreferences.Editor r02 = r0.edit();
        r02.putBoolean("view_animations", !r3);
        SharedConfig.setAnimationsEnabled(!r3);
        r02.apply();
        if ((r20 instanceof TextCheckCell) == false) goto L253;
        ((TextCheckCell) r20).setChecked(!r3);
        return;
    L253:
        return;
    L9:
        if (r21 != this.appIconSelectorRow) goto L13;
        presentFragment(new AppIconsActivity());
        return;
    L13:
        if (r21 != this.backgroundRow) goto L17;
        presentFragment(new WallpapersListActivity(0));
        return;
    L17:
        if (r21 != this.browseThemesRow) goto L21;
        presentFragment(new ThemeActivity(3));
        return;
    L21:
        if (r21 != this.changeUserColor) goto L25;
        presentFragment(new PeerColorActivity(0).setOnApplied(this));
        return;
    L25:
        if (r21 != this.sendByEnterRow) goto L31;
        SharedPreferences r03 = MessagesController.getGlobalMainSettings();
        boolean r32 = r03.getBoolean("send_by_enter", false);
        SharedPreferences.Editor r04 = r03.edit();
        r04.putBoolean("send_by_enter", !r32);
        r04.apply();
        if ((r20 instanceof TextCheckCell) == false) goto L271;
        ((TextCheckCell) r20).setChecked(!r32);
        return;
    L271:
        return;
    L31:
        if (r21 != this.raiseToSpeakRow) goto L37;
        SharedConfig.toggleRaiseToSpeak();
        if ((r20 instanceof TextCheckCell) == false) goto L272;
        ((TextCheckCell) r20).setChecked(SharedConfig.raiseToSpeak);
        return;
    L272:
        return;
    L37:
        if (r21 != this.nextMediaTapRow) goto L43;
        SharedConfig.toggleNextMediaTap();
        if ((r20 instanceof TextCheckCell) == false) goto L273;
        ((TextCheckCell) r20).setChecked(SharedConfig.nextMediaTap);
        return;
    L273:
        return;
    L43:
        if (r21 != this.raiseToListenRow) goto L63;
        SharedConfig.toggleRaiseToListen();
        if ((r20 instanceof TextCheckCell) == false) goto L48;
        ((TextCheckCell) r20).setChecked(SharedConfig.raiseToListen);
    L48:
        if (SharedConfig.raiseToListen == false) goto L50;
    L60:
        updateRows(false);
        return;
    L50:
        if (this.raiseToSpeakRow == (-1)) goto L60;
        int r1 = 0;
    L53:
        if (r1 >= this.listView.getChildCount()) goto L60;
        View r2 = this.listView.getChildAt(r1);
        if ((r2 instanceof TextCheckCell) == false) goto L59;
        if (this.listView.getChildAdapterPosition(r2) != this.raiseToSpeakRow) goto L59;
        ((TextCheckCell) r2).setChecked(false);
    L59:
        r1 = r1 + 1;
        goto L53
    L63:
        if (r21 != this.pauseOnRecordRow) goto L69;
        SharedConfig.togglePauseMusicOnRecord();
        if ((r20 instanceof TextCheckCell) == false) goto L274;
        ((TextCheckCell) r20).setChecked(SharedConfig.pauseMusicOnRecord);
        return;
    L274:
        return;
    L69:
        if (r21 != this.pauseOnMediaRow) goto L75;
        SharedConfig.togglePauseMusicOnMedia();
        if ((r20 instanceof TextCheckCell) == false) goto L275;
        ((TextCheckCell) r20).setChecked(SharedConfig.pauseMusicOnMedia);
        return;
    L275:
        return;
    L75:
        if (r21 != this.distanceRow) goto L89;
        if (getParentActivity() == null) goto L281;
        AtomicReference r24 = new AtomicReference();
        LinearLayout r33 = new LinearLayout(r19);
        r33.setOrientation(1);
        CharSequence[] r13 = {LocaleController.getString("DistanceUnitsAutomatic", R.string.DistanceUnitsAutomatic), LocaleController.getString("DistanceUnitsKilometers", R.string.DistanceUnitsKilometers), LocaleController.getString("DistanceUnitsMiles", R.string.DistanceUnitsMiles)};
        int r12 = 0;
    L80:
        if (r12 >= 3) goto L86;
        RadioColorCell r4 = new RadioColorCell(getParentActivity());
        r4.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
        r4.setCheckColor(Theme.getColor(Theme.key_radioBackground), Theme.getColor(Theme.key_dialogRadioBackgroundChecked));
        CharSequence r122 = r13[r12];
        if (r12 != SharedConfig.distanceSystemType) goto L84;
        boolean r14 = true;
    L85:
        r4.setTextAndValue(r122, r14);
        r4.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector), 2));
        r33.addView(r4);
        r4.setOnClickListener(new ThemeActivity$$ExternalSyntheticLambda11(this, r12, r24));
        r12 = r12 + 1;
        goto L80
    L84:
        r14 = false;
        goto L85
    L86:
        AlertDialog r15 = new AlertDialog.Builder(getParentActivity()).setTitle(LocaleController.getString("DistanceUnitsTitle", R.string.DistanceUnitsTitle)).setView(r33).setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null).create();
        r24.set(r15);
        showDialog(r15);
        return;
    L281:
        return;
    L89:
        if (r21 != this.searchEngineRow) goto L103;
        if (getParentActivity() == null) goto L282;
        AtomicReference r25 = new AtomicReference();
        LinearLayout r34 = new LinearLayout(r19);
        r34.setOrientation(1);
        ArrayList<SearchEngine> r16 = SearchEngine.getSearchEngines();
        int r42 = r16.size();
        CharSequence[] r7 = new CharSequence[r42];
        int r123 = 0;
    L94:
        if (r123 >= r42) goto L100;
        r7[r123] = r16.get(r123).name;
        RadioColorCell r132 = new RadioColorCell(getParentActivity());
        r132.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
        r132.setCheckColor(Theme.getColor(Theme.key_radioBackground), Theme.getColor(Theme.key_dialogRadioBackgroundChecked));
        CharSequence r142 = r7[r123];
        if (r123 != SharedConfig.searchEngineType) goto L98;
        boolean r152 = true;
    L99:
        r132.setTextAndValue(r142, r152);
        r132.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector), 2));
        r34.addView(r132);
        r132.setOnClickListener(new ThemeActivity$$ExternalSyntheticLambda12(this, r123, r25));
        r123 = r123 + 1;
        goto L94
    L98:
        r152 = false;
        goto L99
    L100:
        AlertDialog r17 = new AlertDialog.Builder(getParentActivity()).setTitle(LocaleController.getString(R.string.SearchEngine)).setView(r34).setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null).create();
        r25.set(r17);
        showDialog(r17);
        return;
    L282:
        return;
    L103:
        if (r21 != this.bluetoothScoRow) goto L110;
        if (getParentActivity() == null) goto L283;
        AtomicReference r26 = new AtomicReference();
        LinearLayout r35 = new LinearLayout(r19);
        r35.setOrientation(1);
        RadioColorCell r18 = new RadioColorCell(getParentActivity());
        r18.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
        int r43 = Theme.key_radioBackground;
        int r72 = Theme.getColor(r43);
        int r124 = Theme.key_dialogRadioBackgroundChecked;
        r18.setCheckColor(r72, Theme.getColor(r124));
        r18.setTextAndValue(LocaleController.getString(R.string.MicrophoneForVoiceMessagesBuiltIn), true ^ SharedConfig.recordViaSco);
        int r5 = Theme.key_listSelector;
        r18.setBackground(Theme.createSelectorDrawable(Theme.getColor(r5), 2));
        r35.addView(r18);
        r18.setOnClickListener(new ThemeActivity$$ExternalSyntheticLambda13(this, r26));
        RadioColorCell r110 = new RadioColorCell(getParentActivity());
        r110.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
        r110.setCheckColor(Theme.getColor(r43), Theme.getColor(r124));
        r110.setTextAndText2AndValue(LocaleController.getString(R.string.MicrophoneForVoiceMessagesScoIfConnected), LocaleController.getString(R.string.MicrophoneForVoiceMessagesScoHint), SharedConfig.recordViaSco);
        r110.setBackground(Theme.createSelectorDrawable(Theme.getColor(r5), 2));
        r35.addView(r110);
        r110.setOnClickListener(new ThemeActivity$$ExternalSyntheticLambda14(this, r26));
        AlertDialog r111 = new AlertDialog.Builder(getParentActivity()).setTitle(LocaleController.getString(R.string.MicrophoneForVoiceMessages)).setView(r35).setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null).create();
        r26.set(r111);
        showDialog(r111);
        return;
    L283:
        return;
    L110:
        if (r21 != this.directShareRow) goto L116;
        SharedConfig.toggleDirectShare();
        if ((r20 instanceof TextCheckCell) == false) goto L276;
        ((TextCheckCell) r20).setChecked(SharedConfig.directShare);
        return;
    L276:
        return;
    L116:
        if (r21 != this.sensitiveContentRow) goto L126;
        if (getMessagesController().showSensitiveContent() == true) goto L121;
        showDialog(new AlertDialog.Builder(r19, this.resourceProvider).setTitle(LocaleController.getString(R.string.ConfirmSensitiveContentTitle)).setMessage(LocaleController.getString(R.string.ConfirmSensitiveContentText)).setPositiveButton(LocaleController.getString(R.string.Confirm), new ThemeActivity$$ExternalSyntheticLambda16(this, new ThemeActivity$$ExternalSyntheticLambda15(this, r20))).setNegativeButton(LocaleController.getString(R.string.Cancel), null).create());
        return;
    L121:
        getMessagesController().setContentSettings(false);
        if ((r20 instanceof TextCheckCell) == false) goto L277;
        ((TextCheckCell) r20).setChecked(getMessagesController().showSensitiveContent());
        return;
    L277:
        return;
    L126:
        if (r21 != this.contactsReimportRow) goto L129;
        return;
    L129:
        if (r21 != this.contactsSortRow) goto L136;
        if (getParentActivity() == null) goto L285;
        AlertDialog.Builder r112 = new AlertDialog.Builder(getParentActivity());
        r112.setTitle(LocaleController.getString("SortBy", R.string.SortBy));
        r112.setItems(new CharSequence[]{LocaleController.getString("Default", R.string.Default), LocaleController.getString("SortFirstName", R.string.SortFirstName), LocaleController.getString("SortLastName", R.string.SortLastName)}, new ThemeActivity$$ExternalSyntheticLambda17(this, r21));
        r112.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
        showDialog(r112.create());
        return;
    L285:
        return;
    L136:
        if (r21 != this.chatBlurRow) goto L142;
        SharedConfig.toggleChatBlur();
        if ((r20 instanceof TextCheckCell) == false) goto L278;
        ((TextCheckCell) r20).setChecked(SharedConfig.chatBlurEnabled());
        return;
    L278:
        return;
    L142:
        if (r21 != this.nightThemeRow) goto L175;
        if (LocaleController.isRTL == false) goto L148;
        if (r22 > AndroidUtilities.dp(76.0f)) goto L148;
    L151:
        NotificationsCheckCell r10 = (NotificationsCheckCell) r20;
        if (Theme.selectedAutoNightType != 0) goto L154;
        Theme.selectedAutoNightType = 2;
        r10.setChecked(true);
    L155:
        Theme.saveAutoNightThemeConfig();
        Theme.checkAutoNightThemeConditions(true);
        if (Theme.selectedAutoNightType == 0) goto L158;
        boolean r143 = true;
    L159:
        if (r143 == false) goto L161;
        String r05 = Theme.getCurrentNightThemeName();
    L162:
        if (r143 == false) goto L170;
        int r113 = Theme.selectedAutoNightType;
        if (r113 != 1) goto L166;
        String r114 = LocaleController.getString("AutoNightScheduled", R.string.AutoNightScheduled);
    L169:
        r05 = r114 + " " + r05;
        goto L170
    L166:
        if (r113 != 3) goto L168;
        r114 = LocaleController.getString("AutoNightSystemDefault", R.string.AutoNightSystemDefault);
        goto L169
    L168:
        r114 = LocaleController.getString("AutoNightAdaptive", R.string.AutoNightAdaptive);
    L170:
        r10.setTextAndValueAndIconAndCheck(LocaleController.getString("AutoNightTheme", R.string.AutoNightTheme), r05, R.drawable.menu_night_mode_24, r143, 0, false, true);
        return;
    L161:
        r05 = LocaleController.getString("AutoNightThemeOff", R.string.AutoNightThemeOff);
        goto L162
    L158:
        r143 = false;
        goto L159
    L154:
        Theme.selectedAutoNightType = 0;
        r10.setChecked(false);
    L148:
        if (LocaleController.isRTL == false) goto L150;
    L172:
        presentFragment(new ThemeActivity(1));
        return;
    L150:
        if (r22 < (r20.getMeasuredWidth() - AndroidUtilities.dp(76.0f))) goto L172;
    L175:
        if (r21 != this.browserRow) goto L189;
        if (LocaleController.isRTL == false) goto L181;
        if (r22 > AndroidUtilities.dp(76.0f)) goto L181;
    L184:
        getMessagesController().toggleWebBrowserInAppEnabled();
        ((NotificationsCheckCell) r20).setChecked(getMessagesController().isWebBrowserInAppEnabled());
        return;
    L181:
        if (LocaleController.isRTL == false) goto L183;
    L186:
        presentFragment(new WebBrowserSettings(null));
        return;
    L183:
        if (r22 < (r20.getMeasuredWidth() - AndroidUtilities.dp(76.0f))) goto L186;
    L189:
        if (r21 != this.nightDisabledRow) goto L196;
        if (Theme.selectedAutoNightType == 0) goto L286;
        Theme.selectedAutoNightType = 0;
        updateRows(true);
        Theme.checkAutoNightThemeConditions();
        return;
    L286:
        return;
    L196:
        if (r21 != this.nightScheduledRow) goto L206;
        if (Theme.selectedAutoNightType == 1) goto L287;
        Theme.selectedAutoNightType = 1;
        if (Theme.autoNightScheduleByLocation == false) goto L203;
        updateSunTime(null, true);
    L203:
        updateRows(true);
        Theme.checkAutoNightThemeConditions();
        return;
    L287:
        return;
    L206:
        if (r21 != this.nightAutomaticRow) goto L213;
        if (Theme.selectedAutoNightType == 2) goto L288;
        Theme.selectedAutoNightType = 2;
        updateRows(true);
        Theme.checkAutoNightThemeConditions();
        return;
    L288:
        return;
    L213:
        if (r21 != this.nightSystemDefaultRow) goto L220;
        if (Theme.selectedAutoNightType == 3) goto L289;
        Theme.selectedAutoNightType = 3;
        updateRows(true);
        Theme.checkAutoNightThemeConditions();
        return;
    L289:
        return;
    L220:
        if (r21 != this.scheduleLocationRow) goto L227;
        boolean r115 = !Theme.autoNightScheduleByLocation;
        Theme.autoNightScheduleByLocation = r115;
        ((TextCheckCell) r20).setChecked(r115);
        updateRows(true);
        if (Theme.autoNightScheduleByLocation == false) goto L224;
        updateSunTime(null, true);
    L224:
        Theme.checkAutoNightThemeConditions();
        return;
    L227:
        if (r21 == this.scheduleFromRow) goto L252;
        if (r21 == this.scheduleToRow) goto L252;
        if (r21 != this.scheduleUpdateLocationRow) goto L236;
        updateSunTime(null, true);
        return;
    L236:
        if (r21 != this.createNewThemeRow) goto L240;
        createNewTheme();
        return;
    L240:
        if (r21 != this.editThemeRow) goto L244;
        editTheme();
        return;
    L244:
        if (r21 != this.stickersRow) goto L248;
        presentFragment(new StickersActivity(0, null));
        return;
    L248:
        if (r21 != this.liteModeRow) goto L279;
        presentFragment(new LiteModeSettingsActivity());
        return;
    L279:
        return;
    L252:
        if (getParentActivity() != null) goto L255;
        return;
    L255:
        if (r21 != this.scheduleFromRow) goto L258;
        int r116 = Theme.autoNightDayStartTime;
        int r44 = r116 / 60;
    L259:
        showDialog(new TimePickerDialog(getParentActivity(), new ThemeActivity$$ExternalSyntheticLambda18(this, r21, (TextSettingsCell) r20), r44, r116 - (r44 * 60), true));
        return;
    L258:
        r116 = Theme.autoNightDayEndTime;
        r44 = r116 / 60;
        goto L259
    }

    private /* synthetic */ int lambda$createView$14() {
        return this.sensitiveContentRow;
    }

    private /* synthetic */ void lambda$createView$15() {
        this.listView.highlightRow(new ThemeActivity$$ExternalSyntheticLambda7(this));
    }

    private /* synthetic */ void lambda$createView$2(TL_account.contentSettings r6) {
        RecyclerListView r0 = this.listView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isAttachedToWindow() == false) goto L22;
        ListAdapter r02 = this.listAdapter;
        if (r02 == null) goto L23;
        int r1 = this.sensitiveContentRow;
        boolean r2 = false;
        if (r1 < 0) goto L11;
        boolean r4 = true;
    L12:
        if (r6 != null) goto L14;
    L16:
        if (r4 != r2) goto L19;
        r02.notifyItemChanged(r1);
        return;
    L19:
        updateRows(true);
        return;
    L14:
        if (r6.sensitive_can_change == false) goto L16;
        r2 = true;
        goto L16
    L11:
        r4 = false;
        goto L12
    L23:
        return;
    }

    private /* synthetic */ void lambda$createView$3(int r1, AtomicReference r2, View r3) {
        SharedConfig.setDistanceSystemType(r1);
        this.updateDistance = true;
        RecyclerView.ViewHolder r12 = this.listView.findViewHolderForAdapterPosition(this.distanceRow);
        if (r12 == null) goto L5;
        this.listAdapter.onBindViewHolder(r12, this.distanceRow);
    L5:
        ((Dialog) r2.get()).dismiss();
    }

    private /* synthetic */ void lambda$createView$4(int r1, AtomicReference r2, View r3) {
        SharedConfig.setSearchEngineType(r1);
        this.updateSearchEngine = true;
        RecyclerView.ViewHolder r12 = this.listView.findViewHolderForAdapterPosition(this.searchEngineRow);
        if (r12 == null) goto L5;
        this.listAdapter.onBindViewHolder(r12, this.searchEngineRow);
    L5:
        ((Dialog) r2.get()).dismiss();
    }

    private /* synthetic */ void lambda$createView$5(AtomicReference r1, View r2) {
        SharedConfig.recordViaSco = false;
        SharedConfig.saveConfig();
        this.updateRecordViaSco = true;
        ((Dialog) r1.get()).dismiss();
        RecyclerView.ViewHolder r12 = this.listView.findViewHolderForAdapterPosition(this.bluetoothScoRow);
        if (r12 == null) goto L6;
        this.listAdapter.onBindViewHolder(r12, this.bluetoothScoRow);
        return;
    }

    private /* synthetic */ void lambda$createView$6(AtomicReference r1, Boolean r2) {
        if (r2.booleanValue() == true) goto L12;
        SharedConfig.recordViaSco = false;
        SharedConfig.saveConfig();
        this.updateRecordViaSco = true;
        ((Dialog) r1.get()).dismiss();
        RecyclerListView r12 = this.listView;
        if (r12 != null) goto L7;
        return;
    L7:
        if (r12.isAttachedToWindow() == false) goto L14;
        RecyclerView.ViewHolder r13 = this.listView.findViewHolderForAdapterPosition(this.bluetoothScoRow);
        if (r13 == null) goto L15;
        this.listAdapter.onBindViewHolder(r13, this.bluetoothScoRow);
        return;
    L15:
        return;
    L14:
        return;
    }

    private /* synthetic */ void lambda$createView$7(AtomicReference r4, View r5) {
        PermissionRequest.ensurePermission(R.raw.permission_request_microphone, R.string.PermissionNoBluetoothWithHint, "android.permission.BLUETOOTH_CONNECT", new ThemeActivity$$ExternalSyntheticLambda21(this, r4));
        SharedConfig.recordViaSco = true;
        SharedConfig.saveConfig();
        this.updateRecordViaSco = true;
        ((Dialog) r4.get()).dismiss();
        RecyclerView.ViewHolder r42 = this.listView.findViewHolderForAdapterPosition(this.bluetoothScoRow);
        if (r42 == null) goto L6;
        this.listAdapter.onBindViewHolder(r42, this.bluetoothScoRow);
        return;
    }

    private /* synthetic */ void lambda$createView$8(View r3) {
        getMessagesController().setContentSettings(true);
        if ((r3 instanceof TextCheckCell) == false) goto L6;
        ((TextCheckCell) r3).setChecked(getMessagesController().showSensitiveContent());
        return;
    }

    private /* synthetic */ void lambda$createView$9(Runnable r2, Boolean r3) {
        if (r3.booleanValue() == false) goto L6;
        r2.run();
        return;
    L6:
        BulletinFactory.of(this).createSimpleBulletin(R.raw.error, LocaleController.getString(R.string.AgeVerificationFailedTitle), LocaleController.getString(R.string.AgeVerificationFailedText)).show();
    }

    private /* synthetic */ void lambda$didReceivedNotification$1(DialogInterface r1) {
        this.sharingProgressDialog = null;
        this.sharingTheme = null;
        this.sharingAccent = null;
    }

    private /* synthetic */ void lambda$getThemeDescriptions$24() {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r1 >= this.listView.getChildCount()) goto L9;
        View r2 = this.listView.getChildAt(r1);
        if ((r2 instanceof PeerColorActivity.ChangeNameColorCell) == false) goto L8;
        ((PeerColorActivity.ChangeNameColorCell) r2).updateColors();
    L8:
        updateAppIconValueColor(r2);
        r1 = r1 + 1;
        goto L4
    L9:
        int r12 = 0;
    L11:
        if (r12 >= this.listView.getCachedChildCount()) goto L16;
        View r22 = this.listView.getCachedChildAt(r12);
        if ((r22 instanceof PeerColorActivity.ChangeNameColorCell) == false) goto L15;
        ((PeerColorActivity.ChangeNameColorCell) r22).updateColors();
    L15:
        updateAppIconValueColor(r22);
        r12 = r12 + 1;
        goto L11
    L16:
        int r13 = 0;
    L18:
        if (r13 >= this.listView.getHiddenChildCount()) goto L24;
        View r23 = this.listView.getHiddenChildAt(r13);
        if ((r23 instanceof PeerColorActivity.ChangeNameColorCell) == false) goto L22;
        ((PeerColorActivity.ChangeNameColorCell) r23).updateColors();
    L22:
        updateAppIconValueColor(r23);
        r13 = r13 + 1;
    L24:
        if (r0 >= this.listView.getAttachedScrapChildCount()) goto L29;
        View r14 = this.listView.getAttachedScrapChildAt(r0);
        if ((r14 instanceof PeerColorActivity.ChangeNameColorCell) == false) goto L28;
        ((PeerColorActivity.ChangeNameColorCell) r14).updateColors();
    L28:
        updateAppIconValueColor(r14);
        r0 = r0 + 1;
        goto L24
    }

    private /* synthetic */ void lambda$updateSunTime$21(AlertDialog r1, int r2) {
        if (getParentActivity() == null) goto L10;
        getParentActivity().startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));     // Catch: Exception -> L7
        return;
    L11:
        return;
    }

    private /* synthetic */ void lambda$updateSunTime$22(String r3) {
        Theme.autoNightCityName = r3;
        if (r3 != null) goto L5;
        Theme.autoNightCityName = String.format("(%.06f, %.06f)", new Object[]{Double.valueOf(Theme.autoNightLocationLatitude), Double.valueOf(Theme.autoNightLocationLongitude)});
    L5:
        Theme.saveAutoNightThemeConfig();
        RecyclerListView r32 = this.listView;
        if (r32 == null) goto L13;
        RecyclerListView.Holder r2 = (RecyclerListView.Holder) r32.findViewHolderForAdapterPosition(this.scheduleUpdateLocationRow);
        if (r2 == null) goto L14;
        View r22 = r2.itemView;
        if ((r22 instanceof TextSettingsCell) == false) goto L15;
        ((TextSettingsCell) r22).setTextAndValue(LocaleController.getString("AutoNightUpdateLocation", R.string.AutoNightUpdateLocation), Theme.autoNightCityName, false);
        return;
    L15:
        return;
    L14:
        return;
    }

    private /* synthetic */ void lambda$updateSunTime$23() {
        String r0 = null;
        List<Address> r1 = new Geocoder(ApplicationLoader.applicationContext, Locale.getDefault()).getFromLocation(Theme.autoNightLocationLatitude, Theme.autoNightLocationLongitude, 1);     // Catch: Exception -> L8
        if (r1.size() <= 0) goto L6;
        r0 = r1.get(0).getLocality();     // Catch: Exception -> L8
    L6:
        AndroidUtilities.runOnUIThread(new ThemeActivity$$ExternalSyntheticLambda20(this, r0));
    }

    private boolean setBubbleRadius(int r4, boolean r5) {
        int r1 = 0;
        if (r4 == SharedConfig.bubbleRadius) goto L23;
        SharedConfig.bubbleRadius = r4;
        SharedPreferences.Editor r42 = MessagesController.getGlobalMainSettings().edit();
        r42.putInt("bubbleRadius", SharedConfig.bubbleRadius);
        r42.apply();
        RecyclerView.ViewHolder r43 = this.listView.findViewHolderForAdapterPosition(this.textSizeRow);
        if (r43 == null) goto L13;
        View r44 = r43.itemView;
        if ((r44 instanceof TextSizeCell) == false) goto L13;
        TextSizeCell r45 = (TextSizeCell) r44;
        ChatMessageCell[] r0 = TextSizeCell.m20725$$Nest$fgetmessagesCell(r45).getCells();
    L10:
        if (r1 >= r0.length) goto L12;
        r0[r1].getMessageObject().resetLayout();
        r0[r1].requestLayout();
        r1 = r1 + 1;
        goto L10
    L12:
        r45.invalidate();
    L13:
        RecyclerView.ViewHolder r46 = this.listView.findViewHolderForAdapterPosition(this.bubbleRadiusRow);
        if (r46 == null) goto L21;
        View r47 = r46.itemView;
        if ((r47 instanceof BubbleRadiusCell) == false) goto L21;
        BubbleRadiusCell r48 = (BubbleRadiusCell) r47;
        if (r5 == false) goto L20;
        r48.requestLayout();
        goto L21
    L20:
        r48.invalidate();
    L21:
        updateMenuItem();
        return true;
    L23:
        return false;
    }

    private boolean setFontSize(int r4) {
        int r1 = 0;
        if (r4 == SharedConfig.fontSize) goto L17;
        SharedConfig.fontSize = r4;
        SharedConfig.fontSizeIsDefault = false;
        SharedPreferences r42 = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
        if (r42 != null) goto L7;
        return false;
    L7:
        SharedPreferences.Editor r43 = r42.edit();
        r43.putInt("fons_size", SharedConfig.fontSize);
        r43.apply();
        Theme.createCommonMessageResources();
        RecyclerView.ViewHolder r44 = this.listView.findViewHolderForAdapterPosition(this.textSizeRow);
        if (r44 == null) goto L15;
        View r45 = r44.itemView;
        if ((r45 instanceof TextSizeCell) == false) goto L15;
        ChatMessageCell[] r46 = TextSizeCell.m20725$$Nest$fgetmessagesCell((TextSizeCell) r45).getCells();
    L13:
        if (r1 >= r46.length) goto L15;
        r46[r1].getMessageObject().resetLayout();
        r46[r1].requestLayout();
        r1 = r1 + 1;
    L15:
        updateMenuItem();
        return true;
    L17:
        return false;
    }

    private void startLocationUpdate() {
        if (this.updatingLocation == true) goto L19;
        this.updatingLocation = true;
        LocationManager r1 = (LocationManager) ApplicationLoader.applicationContext.getSystemService("location");
        r1.requestLocationUpdates("gps", 1, 0.0f, this.gpsLocationListener);     // Catch: Exception -> L8
    L17:
        r1.requestLocationUpdates("network", 1, 0.0f, this.networkLocationListener);     // Catch: Exception -> L12
        return;
    L12:
        e = move-exception;
        FileLog.e(e);
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
        goto L17
    }

    private void stopLocationUpdate() {
        this.updatingLocation = false;
        LocationManager r0 = (LocationManager) ApplicationLoader.applicationContext.getSystemService("location");
        r0.removeUpdates(this.gpsLocationListener);
        r0.removeUpdates(this.networkLocationListener);
    }

    private void updateAppIconValueColor(View r3) {
        if ((r3 instanceof TextSettingsCell) == false) goto L10;
        TextSettingsCell r32 = (TextSettingsCell) r3;
        if (r32.getValueImageView().getVisibility() == 0) goto L7;
        return;
    L7:
        if ((r32.getValueImageView().getDrawable() instanceof AppIconPreviewDrawable) == false) goto L12;
        r32.setTextValueColor(AppIconPreviewLoader.getAccentTextColor(AppIconController.getSelectedIcon(), Theme.key_windowBackgroundWhiteValueText, getResourceProvider()));
        return;
    L12:
        return;
    }

    private void updateMenuItem() {
        if (this.menuItem != null) goto L5;
        return;
    L5:
        Theme.ThemeInfo r0 = Theme.getCurrentTheme();
        Theme.ThemeAccent r1 = r0.getAccent(false);
        ArrayList<Theme.ThemeAccent> r02 = r0.themeAccents;
        if (r02 != null) goto L8;
    L12:
        this.menuItem.hideSubItem(2);
        this.menuItem.hideSubItem(3);
    L14:
        if (AndroidUtilities.isTablet() == false) goto L16;
        int r03 = 18;
    L17:
        Theme.ThemeInfo r2 = Theme.getCurrentTheme();
        if (SharedConfig.fontSize == r03) goto L20;
    L33:
        this.menuItem.showSubItem(4);
        return;
    L20:
        if (SharedConfig.bubbleRadius != 17) goto L33;
        if (r2.firstAccentIsDefault == false) goto L33;
        if (r2.currentAccentId != Theme.DEFALT_THEME_ACCENT_ID) goto L33;
        if (r1 == null) goto L31;
        Theme.OverrideWallpaperInfo r04 = r1.overrideWallpaper;
        if (r04 == null) goto L31;
        if ("d".equals(r04.slug) == false) goto L33;
    L31:
        this.menuItem.hideSubItem(4);
        return;
    L16:
        r03 = 16;
        goto L17
    L8:
        if (r02.isEmpty() == true) goto L12;
        if (MonetAccentHelper.canEditAccent(r1) == false) goto L12;
        this.menuItem.showSubItem(2);
        this.menuItem.showSubItem(3);
        goto L14
    }

    private void updateRows(boolean r17) {
        int r1 = this.rowCount;
        int r2 = this.themeAccentListRow;
        int r3 = this.editThemeRow;
        int r4 = this.raiseToSpeakRow;
        this.rowCount = 0;
        this.contactsReimportRow = -1;
        this.contactsSortRow = -1;
        this.scheduleLocationRow = -1;
        this.scheduleUpdateLocationRow = -1;
        this.scheduleLocationInfoRow = -1;
        this.nightDisabledRow = -1;
        this.nightScheduledRow = -1;
        this.nightAutomaticRow = -1;
        this.nightSystemDefaultRow = -1;
        this.nightTypeInfoRow = -1;
        this.scheduleHeaderRow = -1;
        this.nightThemeRow = -1;
        this.browserRow = -1;
        this.newThemeInfoRow = -1;
        this.scheduleFromRow = -1;
        this.scheduleToRow = -1;
        this.scheduleFromToInfoRow = -1;
        this.themeListRow = -1;
        this.themeListRow2 = -1;
        this.browseThemesRow = -1;
        this.themeAccentListRow = -1;
        this.themeInfoRow = -1;
        this.preferedHeaderRow = -1;
        this.automaticHeaderRow = -1;
        this.automaticBrightnessRow = -1;
        this.automaticBrightnessInfoRow = -1;
        this.textSizeHeaderRow = -1;
        this.themeHeaderRow = -1;
        this.bubbleRadiusHeaderRow = -1;
        this.bubbleRadiusRow = -1;
        this.bubbleRadiusInfoRow = -1;
        this.chatListHeaderRow = -1;
        this.chatListRow = -1;
        this.chatListInfoRow = -1;
        this.chatBlurRow = -1;
        this.pauseOnRecordRow = -1;
        this.pauseOnMediaRow = -1;
        this.stickersRow = -1;
        this.stickersInfoRow = -1;
        this.stickersSectionRow = -1;
        this.mediaSoundHeaderRow = -1;
        this.otherHeaderRow = -1;
        this.mediaSoundSectionRow = -1;
        this.otherSectionRow = -1;
        this.liteModeRow = -1;
        this.liteModeInfoRow = -1;
        this.textSizeRow = -1;
        this.backgroundRow = -1;
        this.changeUserColor = -1;
        this.settingsRow = -1;
        this.directShareRow = -1;
        this.sensitiveContentRow = -1;
        this.enableAnimationsRow = -1;
        this.raiseToSpeakRow = -1;
        this.raiseToListenRow = -1;
        this.nextMediaTapRow = -1;
        this.sendByEnterRow = -1;
        this.saveToGalleryOption1Row = -1;
        this.saveToGalleryOption2Row = -1;
        this.saveToGallerySectionRow = -1;
        this.distanceRow = -1;
        this.searchEngineRow = -1;
        this.bluetoothScoRow = -1;
        this.settings2Row = -1;
        this.swipeGestureHeaderRow = -1;
        this.swipeGestureRow = -1;
        this.swipeGestureInfoRow = -1;
        this.selectThemeHeaderRow = -1;
        this.themePreviewRow = -1;
        this.editThemeRow = -1;
        this.createNewThemeRow = -1;
        this.appIconSelectorRow = -1;
        this.appIconShadowRow = -1;
        this.lastShadowRow = -1;
        this.defaultThemes.clear();
        this.darkThemes.clear();
        int r7 = Theme.themes.size();
        int r8 = 0;
    L3:
        int r9 = 3;
        if (r8 >= r7) goto L20;
        Theme.ThemeInfo r10 = Theme.themes.get(r8);
        int r11 = this.currentType;
        if (r11 == 0) goto L16;
        if (r11 == 3) goto L16;
        if (r10.isLight() == true) goto L19;
        TLRPC.TL_theme r92 = r10.info;
        if (r92 == null) goto L16;
        if (r92.document != null) goto L16;
    L19:
        r8 = r8 + 1;
    L16:
        if (r10.pathToFile == null) goto L18;
        this.darkThemes.add(r10);
        goto L19
    L18:
        this.defaultThemes.add(r10);
        goto L19
    L20:
        Collections.sort(this.defaultThemes, new ThemeActivity$$ExternalSyntheticLambda0());
        int r72 = this.currentType;
        int r82 = 5;
        if (r72 != 3) goto L36;
        int r73 = this.rowCount;
        this.selectThemeHeaderRow = r73;
        this.themeListRow2 = r73 + 1;
        this.chatListInfoRow = r73 + 2;
        this.themePreviewRow = r73 + 3;
        this.themeHeaderRow = r73 + 4;
        this.rowCount = r73 + 6;
        this.themeListRow = r73 + 5;
        boolean r74 = Theme.getCurrentTheme().hasAccentColors();
        this.hasThemeAccents = r74;
        ThemesHorizontalListCell r13 = this.themesHorizontalListCell;
        if (r13 == null) goto L26;
        r13.setDrawDivider(r74);
    L26:
        if (this.hasThemeAccents == false) goto L28;
        int r75 = this.rowCount;
        this.rowCount = r75 + 1;
        this.themeAccentListRow = r75;
    L28:
        int r76 = this.rowCount;
        this.rowCount = r76 + 1;
        this.bubbleRadiusInfoRow = r76;
        Theme.ThemeInfo r77 = Theme.getCurrentTheme();
        Theme.ThemeAccent r132 = r77.getAccent(false);
        ArrayList<Theme.ThemeAccent> r78 = r77.themeAccents;
        if (r78 != null) goto L31;
    L35:
        int r79 = this.rowCount;
        this.createNewThemeRow = r79;
        this.rowCount = r79 + 2;
        this.lastShadowRow = r79 + 1;
    L67:
        ThemesHorizontalListCell r710 = this.themesHorizontalListCell;
        if (r710 == null) goto L70;
        r710.notifyDataSetChanged(this.listView.getWidth());
    L70:
        ListAdapter r711 = this.listAdapter;
        if (r711 == null) goto L177;
        if (this.currentType != 1) goto L142;
        int r133 = this.previousUpdatedType;
        int r14 = Theme.selectedAutoNightType;
        if (r133 == r14) goto L142;
        if (r133 == (-1)) goto L142;
        int r22 = this.nightTypeInfoRow;
        int r32 = r22 + 1;
        if (r133 == r14) goto L132;
        int r23 = 0;
    L81:
        if (r23 >= 4) goto L93;
        RecyclerListView.Holder r42 = (RecyclerListView.Holder) this.listView.findViewHolderForAdapterPosition(r23);
        if (r42 == null) goto L92;
        View r43 = r42.itemView;
        if ((r43 instanceof ThemeTypeCell) == false) goto L92;
        ThemeTypeCell r44 = (ThemeTypeCell) r43;
        if (r23 != Theme.selectedAutoNightType) goto L90;
        boolean r6 = true;
    L91:
        r44.setTypeChecked(r6);
        goto L92
    L90:
        r6 = false;
    L92:
        r23 = r23 + 1;
        goto L81
    L93:
        int r24 = Theme.selectedAutoNightType;
        if (r24 != 0) goto L96;
        this.listAdapter.notifyItemRangeRemoved(r32, r1 - r32);
        goto L177
    L96:
        if (r24 != 1) goto L110;
        int r12 = this.previousUpdatedType;
        if (r12 != 0) goto L100;
        this.listAdapter.notifyItemRangeInserted(r32, this.rowCount - r32);
        goto L177
    L100:
        if (r12 != 2) goto L105;
        this.listAdapter.notifyItemRangeRemoved(r32, 3);
        ListAdapter r15 = this.listAdapter;
        if (Theme.autoNightScheduleByLocation == false) goto L104;
        r82 = 4;
    L104:
        r15.notifyItemRangeInserted(r32, r82);
        goto L177
    L105:
        if (r12 != 3) goto L177;
        ListAdapter r16 = this.listAdapter;
        if (Theme.autoNightScheduleByLocation == false) goto L109;
        r82 = 4;
    L109:
        r16.notifyItemRangeInserted(r32, r82);
        goto L177
    L110:
        if (r24 != 2) goto L121;
        int r18 = this.previousUpdatedType;
        if (r18 != 0) goto L114;
        this.listAdapter.notifyItemRangeInserted(r32, this.rowCount - r32);
        goto L177
    L114:
        if (r18 != 1) goto L119;
        ListAdapter r19 = this.listAdapter;
        if (Theme.autoNightScheduleByLocation == false) goto L118;
        r82 = 4;
    L118:
        r19.notifyItemRangeRemoved(r32, r82);
        this.listAdapter.notifyItemRangeInserted(r32, 3);
        goto L177
    L119:
        if (r18 != 3) goto L177;
        this.listAdapter.notifyItemRangeInserted(r32, 3);
        goto L177
    L121:
        if (r24 != 3) goto L177;
        int r110 = this.previousUpdatedType;
        if (r110 != 0) goto L125;
        this.listAdapter.notifyItemRangeInserted(r32, this.rowCount - r32);
        goto L177
    L125:
        if (r110 != 2) goto L127;
        this.listAdapter.notifyItemRangeRemoved(r32, 3);
        goto L177
    L127:
        if (r110 != 1) goto L177;
        ListAdapter r111 = this.listAdapter;
        if (Theme.autoNightScheduleByLocation == false) goto L131;
        r82 = 4;
    L131:
        r111.notifyItemRangeRemoved(r32, r82);
        goto L177
    L132:
        boolean r112 = this.previousByLocation;
        boolean r33 = Theme.autoNightScheduleByLocation;
        if (r112 == r33) goto L177;
        int r25 = r22 + 3;
        if (r33 == false) goto L137;
        int r113 = 3;
    L138:
        r711.notifyItemRangeRemoved(r25, r113);
        ListAdapter r114 = this.listAdapter;
        if (Theme.autoNightScheduleByLocation == false) goto L141;
        r9 = 2;
    L141:
        r114.notifyItemRangeInserted(r25, r9);
        goto L177
    L137:
        r113 = 2;
    L142:
        if (r17 == false) goto L144;
    L175:
        r711.notifyDataSetChanged();
        goto L177
    L144:
        if (this.previousUpdatedType == (-1)) goto L175;
        if (r2 != (-1)) goto L150;
        int r115 = this.themeAccentListRow;
        if (r115 == (-1)) goto L150;
        r711.notifyItemInserted(r115);
    L159:
        if (r3 != (-1)) goto L163;
        int r116 = this.editThemeRow;
        if (r116 == (-1)) goto L163;
        this.listAdapter.notifyItemInserted(r116);
    L167:
        if (r4 != (-1)) goto L171;
        int r117 = this.raiseToSpeakRow;
        if (r117 == (-1)) goto L171;
        this.listAdapter.notifyItemInserted(r117);
    L171:
        if (r4 == (-1)) goto L177;
        if (this.raiseToSpeakRow != (-1)) goto L177;
        this.listAdapter.notifyItemRemoved(r4);
    L163:
        if (r3 == (-1)) goto L167;
        if (this.editThemeRow != (-1)) goto L167;
        this.listAdapter.notifyItemRemoved(r3);
    L150:
        if (r2 != (-1)) goto L152;
    L156:
        int r118 = this.themeAccentListRow;
        if (r118 == (-1)) goto L159;
        r711.notifyItemChanged(r118);
        goto L159
    L152:
        if (this.themeAccentListRow != (-1)) goto L156;
        r711.notifyItemRemoved(r2);
        if (r3 == (-1)) goto L159;
        r3 = r3 - 1;
    L177:
        if (this.currentType != 1) goto L179;
        this.previousByLocation = Theme.autoNightScheduleByLocation;
        this.previousUpdatedType = Theme.selectedAutoNightType;
    L179:
        updateMenuItem();
        return;
    L31:
        if (r78.isEmpty() == true) goto L35;
        if (MonetAccentHelper.canEditAccent(r132) == false) goto L35;
        int r712 = this.rowCount;
        this.rowCount = r712 + 1;
        this.editThemeRow = r712;
        goto L35
    L36:
        int r134 = this.rowCount;
        if (r72 != 0) goto L47;
        this.textSizeHeaderRow = r134;
        this.textSizeRow = r134 + 1;
        this.backgroundRow = r134 + 2;
        this.changeUserColor = r134 + 3;
        this.newThemeInfoRow = r134 + 4;
        this.themeHeaderRow = r134 + 5;
        this.themeListRow2 = r134 + 6;
        this.browseThemesRow = r134 + 7;
        this.themeInfoRow = r134 + 8;
        this.bubbleRadiusHeaderRow = r134 + 9;
        this.bubbleRadiusRow = r134 + 10;
        this.bubbleRadiusInfoRow = r134 + 11;
        this.chatListHeaderRow = r134 + 12;
        this.chatListRow = r134 + 13;
        this.chatListInfoRow = r134 + 14;
        this.appIconSelectorRow = r134 + 15;
        this.appIconShadowRow = r134 + 16;
        this.swipeGestureHeaderRow = r134 + 17;
        this.swipeGestureRow = r134 + 18;
        this.swipeGestureInfoRow = r134 + 19;
        this.nightThemeRow = r134 + 20;
        this.browserRow = r134 + 21;
        this.liteModeRow = r134 + 22;
        this.stickersRow = r134 + 23;
        this.stickersSectionRow = r134 + 24;
        this.mediaSoundHeaderRow = r134 + 25;
        this.nextMediaTapRow = r134 + 26;
        int r152 = r134 + 28;
        this.rowCount = r152;
        this.raiseToListenRow = r134 + 27;
        if (SharedConfig.raiseToListen == false) goto L41;
        this.rowCount = r134 + 29;
        this.raiseToSpeakRow = r152;
    L41:
        int r713 = this.rowCount;
        this.pauseOnRecordRow = r713;
        this.pauseOnMediaRow = r713 + 1;
        this.bluetoothScoRow = r713 + 2;
        this.mediaSoundSectionRow = r713 + 3;
        this.otherHeaderRow = r713 + 4;
        this.rowCount = r713 + 6;
        this.directShareRow = r713 + 5;
        TL_account.contentSettings r714 = getMessagesController().getContentSettings();
        if (r714 != null) goto L44;
    L46:
        int r715 = this.rowCount;
        this.sendByEnterRow = r715;
        this.distanceRow = r715 + 1;
        this.rowCount = r715 + 3;
        this.otherSectionRow = r715 + 2;
        goto L67
    L44:
        if (r714.sensitive_can_change == false) goto L46;
        int r716 = this.rowCount;
        this.rowCount = r716 + 1;
        this.sensitiveContentRow = r716;
        goto L46
    L47:
        this.nightDisabledRow = r134;
        this.nightScheduledRow = r134 + 1;
        int r717 = r134 + 3;
        this.rowCount = r717;
        this.nightAutomaticRow = r134 + 2;
        if (Build.VERSION.SDK_INT < 29) goto L50;
        this.rowCount = r134 + 4;
        this.nightSystemDefaultRow = r717;
    L50:
        int r718 = this.rowCount;
        int r135 = r718 + 1;
        this.rowCount = r135;
        this.nightTypeInfoRow = r718;
        int r142 = Theme.selectedAutoNightType;
        if (r142 != 1) goto L56;
        this.scheduleHeaderRow = r135;
        int r136 = r718 + 3;
        this.rowCount = r136;
        this.scheduleLocationRow = r718 + 2;
        if (Theme.autoNightScheduleByLocation == false) goto L55;
        this.scheduleUpdateLocationRow = r136;
        this.rowCount = r718 + 5;
        this.scheduleLocationInfoRow = r718 + 4;
    L59:
        if (Theme.selectedAutoNightType == 0) goto L67;
        int r719 = this.rowCount;
        this.preferedHeaderRow = r719;
        this.rowCount = r719 + 2;
        this.themeListRow = r719 + 1;
        boolean r720 = Theme.getCurrentNightTheme().hasAccentColors();
        this.hasThemeAccents = r720;
        ThemesHorizontalListCell r137 = this.themesHorizontalListCell;
        if (r137 == null) goto L64;
        r137.setDrawDivider(r720);
    L64:
        if (this.hasThemeAccents == false) goto L66;
        int r721 = this.rowCount;
        this.rowCount = r721 + 1;
        this.themeAccentListRow = r721;
    L66:
        int r722 = this.rowCount;
        this.rowCount = r722 + 1;
        this.themeInfoRow = r722;
        goto L67
    L55:
        this.scheduleFromRow = r136;
        this.scheduleToRow = r718 + 4;
        this.rowCount = r718 + 6;
        this.scheduleFromToInfoRow = r718 + 5;
        goto L59
    L56:
        if (r142 != 2) goto L59;
        this.automaticHeaderRow = r135;
        this.automaticBrightnessRow = r718 + 2;
        this.rowCount = r718 + 4;
        this.automaticBrightnessInfoRow = r718 + 3;
        goto L59
    }

    private void updateSunTime(Location r9, boolean r10) {
        LocationManager r0 = (LocationManager) ApplicationLoader.applicationContext.getSystemService("location");
        Activity r2 = getParentActivity();
        if (r2 == null) goto L9;
        if (r2.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0) goto L9;
        r2.requestPermissions(new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}, 2);
        return;
    L9:
        if (getParentActivity() != null) goto L11;
    L44:
        r9 = r0.getLastKnownLocation("gps");     // Catch: Exception -> L22
        if (r9 != null) goto L24;
        r9 = r0.getLastKnownLocation("network");     // Catch: Exception -> L22
    L24:
        if (r9 != null) goto L28;
        r9 = r0.getLastKnownLocation("passive");     // Catch: Exception -> L22
    L28:
        if (r9 == null) goto L30;
        if (r10 == true) goto L30;
    L33:
        Theme.autoNightLocationLatitude = r9.getLatitude();
        Theme.autoNightLocationLongitude = r9.getLongitude();
        int[] r92 = SunDate.calculateSunriseSunset(Theme.autoNightLocationLatitude, Theme.autoNightLocationLongitude);
        Theme.autoNightSunriseTime = r92[0];
        Theme.autoNightSunsetTime = r92[1];
        Theme.autoNightCityName = null;
        Calendar r93 = Calendar.getInstance();
        r93.setTimeInMillis(System.currentTimeMillis());
        Theme.autoNightLastSunCheckDay = r93.get(5);
        Utilities.globalQueue.postRunnable(new ThemeActivity$$ExternalSyntheticLambda9(this));
        RecyclerListView.Holder r94 = (RecyclerListView.Holder) this.listView.findViewHolderForAdapterPosition(this.scheduleLocationInfoRow);
        if (r94 == null) goto L39;
        View r95 = r94.itemView;
        if ((r95 instanceof TextInfoPrivacyCell) == false) goto L39;
        ((TextInfoPrivacyCell) r95).setText(getLocationSunString());
    L39:
        if (Theme.autoNightScheduleByLocation == true) goto L41;
        return;
    L41:
        if (Theme.selectedAutoNightType != 1) goto L49;
        Theme.checkAutoNightThemeConditions();
        return;
    L49:
        return;
    L30:
        startLocationUpdate();
        if (r9 != null) goto L33;
        return;
    L22:
        e = move-exception;
        FileLog.e(e);
        goto L28
    L11:
        if (getParentActivity().getPackageManager().hasSystemFeature("android.hardware.location.gps") == false) goto L50;
    L17:
        e = move-exception;
        FileLog.e(e);
        goto L44
    L14:
        if (((LocationManager) ApplicationLoader.applicationContext.getSystemService("location")).isProviderEnabled("gps") == true) goto L44;
        AlertDialog.Builder r1 = new AlertDialog.Builder(getParentActivity());     // Catch: Exception -> L17
        r1.setTopAnimation(R.raw.permission_request_location, 72, false, Theme.getColor(Theme.key_dialogTopBackground));     // Catch: Exception -> L17
        r1.setMessage(LocaleController.getString("GpsDisabledAlertText", R.string.GpsDisabledAlertText));     // Catch: Exception -> L17
        r1.setPositiveButton(LocaleController.getString("ConnectingToProxyEnable", R.string.ConnectingToProxyEnable), new ThemeActivity$$ExternalSyntheticLambda8(this));     // Catch: Exception -> L17
        r1.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);     // Catch: Exception -> L17
        showDialog(r1.create());     // Catch: Exception -> L17
        return;
    }

    public static void verifyAge(Context r22, int r23, Utilities.Callback<Boolean> r24, Theme.ResourcesProvider r25) {
        MessagesController r2 = MessagesController.getInstance(r23);
        String r3 = r2.verifyAgeBotUsername;
        String r0 = r2.verifyAgeCountry;
        int r7 = r2.verifyAgeMin;
        if (TextUtils.isEmpty(r3) == false) goto L5;
    L9:
        r24.run(Boolean.TRUE);
        return;
    L5:
        if (r2.config.needAgeVideoVerification.get() == false) goto L9;
        BottomSheet.Builder r10 = new BottomSheet.Builder(r22, false, r25);
        LinearLayout r12 = new LinearLayout(r22);
        r12.setOrientation(1);
        r12.setPadding(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), 0);
        r12.setClipChildren(false);
        r12.setClipToPadding(false);
        r10.setCustomView(r12);
        FrameLayout r4 = new FrameLayout(r22);
        r4.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(80.0f), Theme.getColor(Theme.key_featuredStickers_addButton, r25)));
        ImageView r8 = new ImageView(r22);
        r8.setScaleType(ImageView.ScaleType.CENTER);
        r8.setImageResource(R.drawable.filled_verify_age);
        r4.addView(r8, LayoutHelper.createFrame(50, 50, 17));
        r12.addView(r4, LayoutHelper.createLinear(80, 80, 1, 0, 20, 0, 8));
        int r42 = Theme.key_dialogTextBlack;
        TextView r1 = TextHelper.makeTextView(r22, 20.0f, r42, true, r25);
        r1.setText(LocaleController.getString(R.string.AgeVerificationTitle));
        r1.setGravity(17);
        r12.addView(r1, LayoutHelper.createLinear(-1, -2, 7, 24, 8, 24, 8));
        TextView r13 = TextHelper.makeTextView(r22, 14.0f, r42, false, r25);
        r13.setText(AndroidUtilities.replaceTags(LocaleController.getString("AgeVerificationText" + r0)));
        r13.setGravity(17);
        r12.addView(r13, LayoutHelper.createLinear(-1, -2, 7, 24, 0, 24, 0));
        ButtonWithCounterView r14 = new ButtonWithCounterView(r22, r25);
        r14.setText(LocaleController.getString(R.string.AgeVerificationButton), false);
        r14.setOnClickListener(new ThemeActivity$$ExternalSyntheticLambda6(r14, r2, r3, r23, r22, r25, r7, r24, r9));
        r12.addView(r14, LayoutHelper.createLinear(-1, 48, 7, 2, 29, 2, 14));
        BottomSheet r02 = r10.show();
        BottomSheet[] r9 = {r02};
        r02.fixNavigationBar();
    }

    public void checkCurrentDayNight() {
        if (this.currentType != 3) goto L24;
        boolean r0 = Theme.isCurrentThemeDay();
        boolean r1 = !r0;
        int r3 = 0;
        if (this.lastIsDarkTheme == r1) goto L13;
        this.lastIsDarkTheme = r1;
        RLottieDrawable r12 = this.sunDrawable;
        if (r0 == true) goto L10;
        int r02 = r12.getFramesCount() - 1;
    L11:
        r12.setCustomEndFrame(r02);
        this.menuItem.getIconView().playAnimation();
        goto L13
    L10:
        r02 = 0;
    L13:
        if (this.themeListRow2 >= 0) goto L15;
        return;
    L15:
        if (r3 >= this.listView.getChildCount()) goto L25;
        if ((this.listView.getChildAt(r3) instanceof DefaultThemesPreviewCell) == false) goto L19;
        ((DefaultThemesPreviewCell) this.listView.getChildAt(r3)).updateDayNightMode();
    L19:
        r3 = r3 + 1;
        goto L15
    L25:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r10) {
        this.lastIsDarkTheme = !Theme.isCurrentThemeDay();
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(false);
        int r0 = this.currentType;
        if (r0 != 3) goto L9;
        this.actionBar.setTitle(LocaleController.getString("BrowseThemes", R.string.BrowseThemes));
        ActionBarMenu r02 = this.actionBar.createMenu();
        RLottieDrawable r3 = new RLottieDrawable(R.raw.sun, AndroidUtilities.dp(28.0f), AndroidUtilities.dp(28.0f), true, null);
        this.sunDrawable = r3;
        if (this.lastIsDarkTheme == false) goto L7;
        r3.setCurrentFrame(r3.getFramesCount() - 1);
    L8:
        this.sunDrawable.setPlayInDirectionOfCustomEndFrame(true);
        this.menuItem = r02.addItem(5, this.sunDrawable);
    L15:
        INavigationLayout r03 = this.parentLayout;
        if (r03 != null) goto L18;
    L20:
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        this.listAdapter = new ListAdapter(this, r10);
        FrameLayout r04 = new FrameLayout(r10);
        r04.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        this.fragmentView = r04;
        RecyclerListView r32 = new RecyclerListView(r10);
        this.listView = r32;
        r32.setSections();
        RecyclerListView r33 = this.listView;
        LinearLayoutManager r4 = new LinearLayoutManager(r10, 1, false);
        this.layoutManager = r4;
        r33.setLayoutManager(r4);
        this.listView.setVerticalScrollBarEnabled(false);
        this.listView.setAdapter(this.listAdapter);
        ((DefaultItemAnimator) this.listView.getItemAnimator()).setDelayAnimations(false);
        r04.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.actionBar.setAdaptiveBackground(this.listView);
        this.listView.setOnItemClickListener(new ThemeActivity$$ExternalSyntheticLambda3(this, r10));
        if (this.currentType != 0) goto L24;
        DefaultItemAnimator r102 = new DefaultItemAnimator();
        r102.setDurations(350);
        r102.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r102.setDelayAnimations(false);
        r102.setSupportsChangeAnimations(false);
        this.listView.setItemAnimator(r102);
    L24:
        if (this.highlightSensitiveRow == false) goto L27;
        updateRows(false);
        this.highlightSensitiveRow = false;
        this.listView.scrollToPosition(this.listAdapter.getItemCount() - 1);
        AndroidUtilities.runOnUIThread(new ThemeActivity$$ExternalSyntheticLambda4(this), 200);
    L27:
        return this.fragmentView;
    L18:
        if (r03.isRightLayout() == false) goto L20;
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_close);
        goto L20
    L7:
        r3.setCurrentFrame(0);
        goto L8
    L9:
        ActionBar r42 = this.actionBar;
        if (r0 != 0) goto L14;
        r42.setTitle(LocaleController.getString("ChatSettings", R.string.ChatSettings));
        ActionBarMenuItem r05 = this.actionBar.createMenu().addItem(0, R.drawable.ic_ab_other);
        this.menuItem = r05;
        r05.setContentDescription(LocaleController.getString("AccDescrMoreOptions", R.string.AccDescrMoreOptions));
        this.menuItem.addSubItem(2, R.drawable.msg_share, LocaleController.getString("ShareTheme", R.string.ShareTheme));
        this.menuItem.addSubItem(3, R.drawable.msg_edit, LocaleController.getString("EditThemeColors", R.string.EditThemeColors));
        this.menuItem.addSubItem(1, R.drawable.msg_palette, LocaleController.getString("CreateNewThemeMenu", R.string.CreateNewThemeMenu));
        this.menuItem.addSubItem(4, R.drawable.msg_reset, LocaleController.getString("ThemeResetToDefaults", R.string.ThemeResetToDefaults));
        if (getMessagesController().getContentSettings() != null) goto L15;
        getMessagesController().getContentSettings(new ThemeActivity$$ExternalSyntheticLambda2(this));
        goto L15
    L14:
        r42.setTitle(LocaleController.getString(R.string.AutoNightTheme));
        goto L15
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r8, int r9, Object... r10) {
        if (r8 != NotificationCenter.locationPermissionGranted) goto L7;
        updateSunTime(null, true);
        return;
    L7:
        if (r8 != NotificationCenter.didSetNewWallpapper) goto L9;
    L83:
        RecyclerListView r82 = this.listView;
        if (r82 == null) goto L86;
        r82.invalidateViews();
    L86:
        updateMenuItem();
        return;
    L9:
        if (r8 == NotificationCenter.emojiLoaded) goto L83;
        if (r8 != NotificationCenter.webBrowserSettingsUpdate) goto L20;
        ListAdapter r83 = this.listAdapter;
        if (r83 == null) goto L88;
        int r7 = this.browserRow;
        if (r7 == (-1)) goto L89;
        r83.notifyItemChanged(r7);
        return;
    L89:
        return;
    L88:
        return;
    L20:
        if (r8 != NotificationCenter.themeAccentListUpdated) goto L28;
        ListAdapter r84 = this.listAdapter;
        if (r84 == null) goto L90;
        int r72 = this.themeAccentListRow;
        if (r72 == (-1)) goto L91;
        r84.notifyItemChanged(r72, new Object());
        return;
    L91:
        return;
    L90:
        return;
    L28:
        if (r8 != NotificationCenter.themeListUpdated) goto L32;
        updateRows(true);
        return;
    L32:
        if (r8 != NotificationCenter.themeUploadedToServer) goto L47;
        Theme.ThemeInfo r85 = (Theme.ThemeInfo) r10[0];
        Theme.ThemeAccent r92 = (Theme.ThemeAccent) r10[1];
        if (r85 == this.sharingTheme) goto L36;
        return;
    L36:
        if (r92 != this.sharingAccent) goto L93;
        StringBuilder r102 = new StringBuilder("https://");
        r102.append(getMessagesController().linkPrefix);
        r102.append("/addtheme/");
        if (r92 == null) goto L41;
        TLRPC.TL_theme r86 = r92.info;
    L42:
        r102.append(r86.slug);
        String r3 = r102.toString();
        showDialog(new ShareAlert(getParentActivity(), null, r3, false, r3, false));
        AlertDialog r73 = this.sharingProgressDialog;
        if (r73 == null) goto L94;
        r73.dismiss();
        return;
    L94:
        return;
    L41:
        r86 = r85.info;
        goto L42
    L93:
        return;
    L47:
        if (r8 != NotificationCenter.themeUploadError) goto L57;
        Theme.ThemeInfo r87 = (Theme.ThemeInfo) r10[0];
        Theme.ThemeAccent r93 = (Theme.ThemeAccent) r10[1];
        if (r87 == this.sharingTheme) goto L51;
        return;
    L51:
        if (r93 != this.sharingAccent) goto L96;
        AlertDialog r74 = this.sharingProgressDialog;
        if (r74 != null) goto L97;
        r74.dismiss();
        return;
    L97:
        return;
    L96:
        return;
    L57:
        if (r8 != NotificationCenter.needShareTheme) goto L66;
        if (getParentActivity() != null) goto L61;
        return;
    L61:
        if (this.isPaused == true) goto L102;
        this.sharingTheme = (Theme.ThemeInfo) r10[0];
        this.sharingAccent = (Theme.ThemeAccent) r10[1];
        AlertDialog r88 = new AlertDialog(getParentActivity(), 3);
        this.sharingProgressDialog = r88;
        r88.setCanCancel(true);
        showDialog(this.sharingProgressDialog, new ThemeActivity$$ExternalSyntheticLambda1(this));
        return;
    L102:
        return;
    L66:
        if (r8 != NotificationCenter.needSetDayNightTheme) goto L70;
        updateMenuItem();
        checkCurrentDayNight();
        return;
    L70:
        if (r8 != NotificationCenter.emojiPreviewThemesChanged) goto L76;
        int r89 = this.themeListRow2;
        if (r89 < 0) goto L99;
        this.listAdapter.notifyItemChanged(r89);
        return;
    L99:
        return;
    L76:
        if (r8 != NotificationCenter.contentSettingsLoaded) goto L78;
    L79:
        int r810 = this.sensitiveContentRow;
        if (r810 < 0) goto L101;
        this.listAdapter.notifyItemChanged(r810);
        return;
    L101:
        return;
    L78:
        if (r8 == NotificationCenter.appConfigUpdated) goto L79;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{TextSettingsCell.class, TextCheckCell.class, HeaderCell.class, BrightnessControlCell.class, ThemeTypeCell.class, TextSizeCell.class, BubbleRadiusCell.class, ChatListCell.class, NotificationsCheckCell.class, ThemesHorizontalListCell.class, TintRecyclerListView.class, TextCell.class, PeerColorActivity.ChangeNameColorCell.class, SwipeGestureSettingsView.class, DefaultThemesPreviewCell.class}, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SUBMENUBACKGROUND, null, null, null, null, Theme.key_actionBarDefaultSubmenuBackground));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SUBMENUITEM, null, null, null, null, Theme.key_actionBarDefaultSubmenuItem));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SUBMENUITEM | ThemeDescription.FLAG_IMAGECOLOR, null, null, null, null, Theme.key_actionBarDefaultSubmenuItemIcon));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        int r18 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, r18));
        int r14 = Theme.key_windowBackgroundWhiteValueText;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"valueTextView"}, null, null, null, r14));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueHeader));
        int r16 = Theme.key_windowBackgroundWhiteBlueText4;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCell.class}, new String[]{"textView"}, null, null, null, r16));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCell.class}, new String[]{"imageView"}, null, null, null, r16));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"textView"}, null, null, null, r18));
        int r162 = Theme.key_switchTrack;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"checkBox"}, null, null, null, r162));
        int r163 = Theme.key_switchTrackChecked;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"checkBox"}, null, null, null, r163));
        int r17 = Theme.key_windowBackgroundWhiteGrayIcon;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_IMAGECOLOR, new Class[]{BrightnessControlCell.class}, new String[]{"leftImageView"}, null, null, null, r17));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_IMAGECOLOR, new Class[]{BrightnessControlCell.class}, new String[]{"rightImageView"}, null, null, null, r17));
        int r45 = Theme.key_player_progressBackground;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{BrightnessControlCell.class}, new String[]{"seekBarView"}, null, null, null, r45));
        int r54 = Theme.key_player_progress;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_PROGRESSBAR, new Class[]{BrightnessControlCell.class}, new String[]{"seekBarView"}, null, null, null, r54));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ThemeTypeCell.class}, new String[]{"textView"}, null, null, null, r18));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ThemeTypeCell.class}, new String[]{"checkImage"}, null, null, null, Theme.key_featuredStickers_addedIcon));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_PROGRESSBAR, new Class[]{TextSizeCell.class}, new String[]{"sizeBar"}, null, null, null, r54));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSizeCell.class}, new String[]{"sizeBar"}, null, null, null, r45));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_PROGRESSBAR, new Class[]{BubbleRadiusCell.class}, new String[]{"sizeBar"}, null, null, null, r54));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{BubbleRadiusCell.class}, new String[]{"sizeBar"}, null, null, null, r45));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ChatListCell.class}, null, null, null, Theme.key_radioBackground));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ChatListCell.class}, null, null, null, Theme.key_radioBackgroundChecked));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{NotificationsCheckCell.class}, new String[]{"textView"}, null, null, null, r18));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{NotificationsCheckCell.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText2));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{NotificationsCheckCell.class}, new String[]{"checkBox"}, null, null, null, r162));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{NotificationsCheckCell.class}, new String[]{"checkBox"}, null, null, null, r163));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSizeCell.class}, null, new Drawable[]{Theme.chat_msgInDrawable, Theme.chat_msgInMediaDrawable}, null, Theme.key_chat_inBubble));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSizeCell.class}, null, new Drawable[]{Theme.chat_msgInSelectedDrawable, Theme.chat_msgInMediaSelectedDrawable}, null, Theme.key_chat_inBubbleSelected));
        Drawable[] r12 = Theme.chat_msgInDrawable.getShadowDrawables();
        int r24 = Theme.key_chat_inBubbleShadow;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSizeCell.class}, null, r12, null, r24));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSizeCell.class}, null, Theme.chat_msgInMediaDrawable.getShadowDrawables(), null, r24));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSizeCell.class}, null, new Drawable[]{Theme.chat_msgOutDrawable, Theme.chat_msgOutMediaDrawable}, null, Theme.key_chat_outBubble));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSizeCell.class}, null, new Drawable[]{Theme.chat_msgOutDrawable, Theme.chat_msgOutMediaDrawable}, null, Theme.key_chat_outBubbleGradient1));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSizeCell.class}, null, new Drawable[]{Theme.chat_msgOutDrawable, Theme.chat_msgOutMediaDrawable}, null, Theme.key_chat_outBubbleGradient2));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSizeCell.class}, null, new Drawable[]{Theme.chat_msgOutDrawable, Theme.chat_msgOutMediaDrawable}, null, Theme.key_chat_outBubbleGradient3));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSizeCell.class}, null, new Drawable[]{Theme.chat_msgOutSelectedDrawable, Theme.chat_msgOutMediaSelectedDrawable}, null, Theme.key_chat_outBubbleSelected));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSizeCell.class}, null, new Drawable[]{Theme.chat_msgOutDrawable, Theme.chat_msgOutMediaDrawable}, null, Theme.key_chat_outBubbleShadow));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSizeCell.class}, null, new Drawable[]{Theme.chat_msgInDrawable, Theme.chat_msgInMediaDrawable}, null, r24));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSizeCell.class}, null, null, null, Theme.key_chat_messageTextIn));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSizeCell.class}, null, null, null, Theme.key_chat_messageTextOut));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSizeCell.class}, null, new Drawable[]{Theme.chat_msgOutCheckDrawable}, null, Theme.key_chat_outSentCheck));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSizeCell.class}, null, new Drawable[]{Theme.chat_msgOutCheckSelectedDrawable}, null, Theme.key_chat_outSentCheckSelected));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSizeCell.class}, null, new Drawable[]{Theme.chat_msgOutCheckReadDrawable, Theme.chat_msgOutHalfCheckDrawable}, null, Theme.key_chat_outSentCheckRead));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSizeCell.class}, null, new Drawable[]{Theme.chat_msgOutCheckReadSelectedDrawable, Theme.chat_msgOutHalfCheckSelectedDrawable}, null, Theme.key_chat_outSentCheckReadSelected));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSizeCell.class}, null, new Drawable[]{Theme.chat_msgMediaCheckDrawable, Theme.chat_msgMediaHalfCheckDrawable}, null, Theme.key_chat_mediaSentCheck));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSizeCell.class}, null, null, null, Theme.key_chat_inReplyLine));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSizeCell.class}, null, null, null, Theme.key_chat_outReplyLine));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSizeCell.class}, null, null, null, Theme.key_chat_inReplyNameText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSizeCell.class}, null, null, null, Theme.key_chat_outReplyNameText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSizeCell.class}, null, null, null, Theme.key_chat_inReplyMessageText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSizeCell.class}, null, null, null, Theme.key_chat_outReplyMessageText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSizeCell.class}, null, null, null, Theme.key_chat_inReplyMediaMessageSelectedText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSizeCell.class}, null, null, null, Theme.key_chat_outReplyMediaMessageSelectedText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSizeCell.class}, null, null, null, Theme.key_chat_inTimeText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSizeCell.class}, null, null, null, Theme.key_chat_outTimeText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSizeCell.class}, null, null, null, Theme.key_chat_inTimeSelectedText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSizeCell.class}, null, null, null, Theme.key_chat_outTimeSelectedText));
        r1.addAll(SimpleThemeDescription.createThemeDescriptions(new ThemeActivity$$ExternalSyntheticLambda5(this), new int[]{Theme.key_windowBackgroundWhiteHintText, r18, r14}));
        return r1;
    }

    public ThemeActivity highlightSensitiveRow() {
        this.highlightSensitiveRow = true;
        return this;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.locationPermissionGranted);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.didSetNewWallpapper);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.themeListUpdated);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.themeAccentListUpdated);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiLoaded);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.needShareTheme);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.needSetDayNightTheme);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiPreviewThemesChanged);
        getNotificationCenter().addObserver(this, NotificationCenter.appConfigUpdated);
        getNotificationCenter().addObserver(this, NotificationCenter.contentSettingsLoaded);
        getNotificationCenter().addObserver(this, NotificationCenter.themeUploadedToServer);
        getNotificationCenter().addObserver(this, NotificationCenter.themeUploadError);
        getNotificationCenter().addObserver(this, NotificationCenter.webBrowserSettingsUpdate);
        if (this.currentType != 0) goto L6;
        Theme.loadRemoteThemes(this.currentAccount, true);
        Theme.checkCurrentRemoteTheme(true);
    L6:
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        stopLocationUpdate();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.locationPermissionGranted);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didSetNewWallpapper);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.themeListUpdated);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.themeAccentListUpdated);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiLoaded);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.needShareTheme);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.needSetDayNightTheme);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiPreviewThemesChanged);
        getNotificationCenter().removeObserver(this, NotificationCenter.appConfigUpdated);
        getNotificationCenter().removeObserver(this, NotificationCenter.contentSettingsLoaded);
        getNotificationCenter().removeObserver(this, NotificationCenter.themeUploadedToServer);
        getNotificationCenter().removeObserver(this, NotificationCenter.themeUploadError);
        getNotificationCenter().removeObserver(this, NotificationCenter.webBrowserSettingsUpdate);
        Theme.saveAutoNightThemeConfig();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.listView.setPadding(0, 0, 0, r4);
        this.listView.setClipToPadding(false);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        if (this.listAdapter == null) goto L6;
        updateRows(true);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationEnd(boolean r1, boolean r2) {
        if (r1 == false) goto L5;
        AndroidUtilities.requestAdjustResize(getParentActivity(), this.classGuid);
        AndroidUtilities.setAdjustResizeToNothing(getParentActivity(), this.classGuid);
        return;
    }
}

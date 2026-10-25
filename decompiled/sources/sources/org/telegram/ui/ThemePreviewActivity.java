package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.SystemClock;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Property;
import android.util.SparseIntArray;
import android.util.StateSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;
import androidx.collection.LongSparseArray;
import androidx.core.graphics.ColorUtils;
import androidx.mediarouter.media.GlobalMediaRouter;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.android.dx.io.Opcodes;
import com.google.android.gms.cast.MediaError;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ChannelBoostsController;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ChatThemeController;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.MenuDrawable;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.ChatActionCell;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Cells.DialogCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.LoadingCell;
import org.telegram.ui.Cells.PatternCell;
import org.telegram.ui.Cells.PatternCell$$ExternalSyntheticApiModelOutline0;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.BackgroundGradientDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CircularProgressDrawable;
import org.telegram.ui.Components.ColorPicker;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.Easings;
import org.telegram.ui.Components.FragmentFloatingButton;
import org.telegram.ui.Components.GestureDetector2;
import org.telegram.ui.Components.HintView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.MotionBackgroundDrawable;
import org.telegram.ui.Components.Premium.LimitReachedBottomSheet;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.SeekBarView;
import org.telegram.ui.Components.ShareAlert;
import org.telegram.ui.Components.Text;
import org.telegram.ui.Components.UndoView;
import org.telegram.ui.Components.WallpaperCheckBoxView;
import org.telegram.ui.Components.WallpaperParallaxEffect;
import org.telegram.ui.PhotoViewer;
import org.telegram.ui.Stories.recorder.SliderView;
import org.telegram.ui.WallpapersListActivity;

/* loaded from: classes6.dex */
public class ThemePreviewActivity extends BaseFragment implements DownloadController.FileDownloadProgressListener, NotificationCenter.NotificationCenterDelegate {
    private int TAG;
    private Theme.ThemeAccent accent;
    private ActionBar actionBar2;
    private HintView animationHint;
    private BlurButton applyButton1;
    private BlurButton applyButton2;
    private Runnable applyColorAction;
    private boolean applyColorScheduled;
    private Theme.ThemeInfo applyingTheme;
    private FrameLayout backgroundButtonsContainer;
    private WallpaperCheckBoxView[] backgroundCheckBoxView;
    private int backgroundColor;
    private int backgroundGradientColor1;
    private int backgroundGradientColor2;
    private int backgroundGradientColor3;
    private BackgroundGradientDrawable.Disposable backgroundGradientDisposable;
    private BackgroundView backgroundImage;
    private BackgroundView[] backgroundImages;
    private ImageView backgroundPlayAnimationImageView;
    private FrameLayout backgroundPlayAnimationView;
    private AnimatorSet backgroundPlayViewAnimator;
    private int backgroundRotation;
    private int backupAccentColor;
    private int backupAccentColor2;
    private long backupBackgroundGradientOverrideColor1;
    private long backupBackgroundGradientOverrideColor2;
    private long backupBackgroundGradientOverrideColor3;
    private long backupBackgroundOverrideColor;
    private int backupBackgroundRotation;
    private float backupIntensity;
    private int backupMyMessagesAccentColor;
    private boolean backupMyMessagesAnimated;
    private int backupMyMessagesGradientAccentColor1;
    private int backupMyMessagesGradientAccentColor2;
    private int backupMyMessagesGradientAccentColor3;
    private String backupSlug;
    private final PorterDuff.Mode blendMode;
    private Bitmap blurredBitmap;
    private BitmapDrawable blurredDrawable;
    public TL_stories.TL_premium_boostsStatus boostsStatus;
    private FrameLayout bottomOverlayChat;
    private TextView cancelButton;
    private View changeDayNightView;
    private ValueAnimator changeDayNightViewAnimator;
    private ValueAnimator changeDayNightViewAnimator2;
    private float changeDayNightViewProgress;
    private int checkColor;
    private boolean checkedBoostsLevel;
    private boolean checkingBoostsLevel;
    private ColorPicker colorPicker;
    private int colorType;
    float croppedWidth;
    private float currentIntensity;
    float currentScrollOffset;
    private Object currentWallpaper;
    private Bitmap currentWallpaperBitmap;
    private ActionBarMenuItem dayNightItem;
    float defaultScrollOffset;
    private WallpaperActivityDelegate delegate;
    private boolean deleteOnCancel;
    long dialogId;
    private DialogsAdapter dialogsAdapter;
    private float dimAmount;
    private SliderView dimmingSlider;
    private FrameLayout dimmingSliderContainer;
    private TextView doneButton;
    private View dotsContainer;
    private TextView dropDown;
    private ActionBarMenuItem dropDownContainer;
    private boolean editingTheme;
    private FragmentFloatingButton floatingButton;
    private FrameLayout frameLayout;
    GestureDetector2 gestureDetector2;
    private boolean hasScrollingBackground;
    private String imageFilter;
    private HeaderCell intensityCell;
    private SeekBarView intensitySeekBar;
    private boolean isBlurred;
    private boolean isMotion;
    private WeakReference<Drawable> lastDrawableToBlur;
    private int lastPickedColor;
    private int lastPickedColorNum;
    private TLRPC.TL_wallPaper lastSelectedPattern;
    private int lastSizeHash;
    private RecyclerListView listView;
    private RecyclerListView listView2;
    private String loadingFile;
    private File loadingFileObject;
    private TLRPC.PhotoSize loadingSize;
    private ColoredImageSpan lockSpan;
    float maxScrollOffset;
    private int maxWallpaperSize;
    private MessagesAdapter messagesAdapter;
    private FrameLayout messagesButtonsContainer;
    private WallpaperCheckBoxView[] messagesCheckBoxView;
    private ImageView messagesPlayAnimationImageView;
    private FrameLayout messagesPlayAnimationView;
    private AnimatorSet messagesPlayViewAnimator;
    private AnimatorSet motionAnimation;
    org.telegram.ui.ActionBar.MessageDrawable msgOutDrawable;
    org.telegram.ui.ActionBar.MessageDrawable msgOutDrawableSelected;
    org.telegram.ui.ActionBar.MessageDrawable msgOutMediaDrawable;
    org.telegram.ui.ActionBar.MessageDrawable msgOutMediaDrawableSelected;
    private boolean nightTheme;
    private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
    DayNightSwitchDelegate onSwitchDayNightDelegate;
    private Bitmap originalBitmap;
    private FrameLayout page1;
    private FrameLayout page2;
    private WallpaperParallaxEffect parallaxEffect;
    private float parallaxScale;
    private int patternColor;
    private FrameLayout[] patternLayout;
    private TextView patternTitleView;
    private AnimatorSet patternViewAnimation;
    private ArrayList<Object> patterns;
    private PatternsAdapter patternsAdapter;
    private FrameLayout[] patternsButtonsContainer;
    private TextView[] patternsCancelButton;
    private HashMap<Long, Object> patternsDict;
    private LinearLayoutManager patternsLayoutManager;
    private RecyclerListView patternsListView;
    private TextView[] patternsSaveButton;
    private int previousBackgroundColor;
    private int previousBackgroundGradientColor1;
    private int previousBackgroundGradientColor2;
    private int previousBackgroundGradientColor3;
    private int previousBackgroundRotation;
    private float previousIntensity;
    private TLRPC.TL_wallPaper previousSelectedPattern;
    private float progressToDarkTheme;
    private boolean progressVisible;
    private boolean removeBackgroundOverride;
    private boolean rotatePreview;
    private FrameLayout saveButtonsContainer;
    private ActionBarMenuItem saveItem;
    private final int screenType;
    private Scroller scroller;
    private TLRPC.TL_wallPaper selectedPattern;
    boolean self;
    MessageObject serverWallpaper;
    private boolean setupFinished;
    private Drawable sheetDrawable;
    private boolean shouldShowBrightnessControll;
    private boolean shouldShowDayNightIcon;
    private boolean showColor;
    private RLottieDrawable sunDrawable;
    public final ThemeDelegate themeDelegate;
    private List<ThemeDescription> themeDescriptions;
    private UndoView undoView;
    public boolean useDefaultThemeForButtons;
    private ValueAnimator valueAnimator;
    private ViewPager viewPager;
    private boolean wasScroll;
    private long watchForKeyboardEndTime;

    /* renamed from: org.telegram.ui.ThemePreviewActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ThemeDelegate {
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass1(ThemePreviewActivity r1) {
            this.this$0 = r1;
            super(r1);
        }

        @Override // org.telegram.ui.ThemePreviewActivity.ThemeDelegate, org.telegram.ui.ActionBar.Theme.ResourcesProvider
        public boolean isDark() {
            DayNightSwitchDelegate r0 = this.this$0.onSwitchDayNightDelegate;
            if (r0 == null) goto L7;
            return r0.isDark();
        L7:
            return super.isDark();
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$10, reason: invalid class name */
    public class AnonymousClass10 extends RecyclerListView {
        float lastX;
        float lastY;
        boolean scrollingBackground;
        float startX;
        float startY;
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass10(ThemePreviewActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        private void checkMotionEvent(MotionEvent r3) {
            if (r3.getAction() == 1) goto L5;
            return;
        L5:
            if (ThemePreviewActivity.m20812$$Nest$fgetwasScroll(this.this$0) == false) goto L7;
        L11:
            ThemePreviewActivity.m20832$$Nest$fputwasScroll(this.this$0, false);
            return;
        L7:
            if ((ThemePreviewActivity.m20772$$Nest$fgetcurrentWallpaper(this.this$0) instanceof WallpapersListActivity.ColorWallpaper) == false) goto L11;
            if (ThemePreviewActivity.m20799$$Nest$fgetpatternLayout(this.this$0)[0].getVisibility() != 0) goto L11;
            ThemePreviewActivity.m20843$$Nest$mshowPatternsView(this.this$0, 0, false, true);
            goto L11
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public boolean allowSelectChildAtPosition(View r3) {
            RecyclerView.ViewHolder r0 = ThemePreviewActivity.m20787$$Nest$fgetlistView2(this.this$0).findContainingViewHolder(r3);
            if (r0 == null) goto L9;
            if (r0.getItemViewType() != 2) goto L9;
            return false;
        L9:
            return super.allowSelectChildAtPosition(r3);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean drawChild(Canvas r8, View r9, long r10) {
            boolean r102 = super.drawChild(r8, r9, r10);
            if ((r9 instanceof ChatMessageCell) == false) goto L43;
            ChatMessageCell r11 = (ChatMessageCell) r9;
            r11.getMessageObject();
            ImageReceiver r0 = r11.getAvatarImage();
            if (r0 == null) goto L43;
            int r1 = r9.getTop();
            if (r11.isPinnedBottom() == false) goto L14;
            RecyclerView.ViewHolder r2 = ThemePreviewActivity.m20787$$Nest$fgetlistView2(this.this$0).getChildViewHolder(r9);
            if (r2 == null) goto L14;
            if (ThemePreviewActivity.m20787$$Nest$fgetlistView2(this.this$0).findViewHolderForAdapterPosition(r2.getAdapterPosition() - 1) == null) goto L14;
            r0.setImageY(-AndroidUtilities.dp(1000.0f));
            r0.draw(r8);
            return r102;
        L14:
            float r22 = r11.getTranslationX();
            int r3 = r9.getTop() + r11.getLayoutHeight();
            int r4 = ThemePreviewActivity.m20787$$Nest$fgetlistView2(this.this$0).getMeasuredHeight() - ThemePreviewActivity.m20787$$Nest$fgetlistView2(this.this$0).getPaddingBottom();
            if (r3 <= r4) goto L18;
            r3 = r4;
        L18:
            if (r11.isPinnedTop() == false) goto L35;
            RecyclerView.ViewHolder r92 = ThemePreviewActivity.m20787$$Nest$fgetlistView2(this.this$0).getChildViewHolder(r9);
            if (r92 == null) goto L35;
            int r112 = 0;
        L23:
            if (r112 >= 20) goto L35;
            r112 = r112 + 1;
            r92 = ThemePreviewActivity.m20787$$Nest$fgetlistView2(this.this$0).findViewHolderForAdapterPosition(r92.getAdapterPosition() + 1);
            if (r92 == null) goto L35;
            r1 = r92.itemView.getTop();
            if ((r3 - AndroidUtilities.dp(48.0f)) >= r92.itemView.getBottom()) goto L30;
            r22 = Math.min(r92.itemView.getTranslationX(), r22);
        L30:
            View r5 = r92.itemView;
            if ((r5 instanceof ChatMessageCell) == false) goto L35;
            if (((ChatMessageCell) r5).isPinnedTop() == true) goto L23;
        L35:
            if ((r3 - AndroidUtilities.dp(48.0f)) >= r1) goto L38;
            r3 = r1 + AndroidUtilities.dp(48.0f);
        L38:
            if (r22 == 0.0f) goto L40;
            r8.save();
            r8.translate(r22, 0.0f);
        L40:
            r0.setImageY(r3 - AndroidUtilities.dp(44.0f));
            r0.draw(r8);
            if (r22 == 0.0f) goto L43;
            r8.restore();
        L43:
            return r102;
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public void onChildPressed(View r2, float r3, float r4, boolean r5) {
            if (r5 == true) goto L4;
        L8:
            super.onChildPressed(r2, r3, r4, r5);
            return;
        L4:
            if ((r2 instanceof ChatMessageCell) == false) goto L8;
            if (((ChatMessageCell) r2).isInsideBackground(r3, r4) == true) goto L8;
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            ThemePreviewActivity.m20836$$Nest$minvalidateBlur(this.this$0);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public boolean onTouchEvent(MotionEvent r5) {
            checkMotionEvent(r5);
            if (ThemePreviewActivity.m20782$$Nest$fgethasScrollingBackground(this.this$0) == false) goto L29;
            if (r5.getAction() != 0) goto L11;
            float r0 = r5.getX();
            this.startX = r0;
            this.lastX = r0;
            float r02 = r5.getY();
            this.startY = r02;
            this.lastY = r02;
            if (getParent() == null) goto L9;
            getParent().requestDisallowInterceptTouchEvent(true);
        L9:
            this.scrollingBackground = true;
        L27:
            this.this$0.gestureDetector2.onTouchEvent(r5);
            goto L29
        L11:
            if (r5.getAction() != 2) goto L21;
            if (this.scrollingBackground == true) goto L27;
            if (Math.abs(this.startX - r5.getX()) <= AndroidUtilities.touchSlop) goto L27;
            if (getParent() == null) goto L19;
            getParent().requestDisallowInterceptTouchEvent(true);
        L19:
            this.scrollingBackground = true;
            goto L27
        L21:
            if (r5.getAction() != 3) goto L23;
        L24:
            this.scrollingBackground = false;
            if (getParent() == null) goto L27;
            getParent().requestDisallowInterceptTouchEvent(false);
            goto L27
        L23:
            if (r5.getAction() != 1) goto L27;
        L29:
            if (this.scrollingBackground == false) goto L31;
        L34:
            return true;
        L31:
            if (super.onTouchEvent(r5) == true) goto L34;
            return false;
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.View
        public void setTranslationY(float r3) {
            super.setTranslationY(r3);
            int r0 = 0;
            if (ThemePreviewActivity.m20753$$Nest$fgetbackgroundCheckBoxView(this.this$0) == null) goto L9;
            int r32 = 0;
        L6:
            if (r32 >= ThemePreviewActivity.m20753$$Nest$fgetbackgroundCheckBoxView(this.this$0).length) goto L9;
            ThemePreviewActivity.m20753$$Nest$fgetbackgroundCheckBoxView(this.this$0)[r32].invalidate();
            r32 = r32 + 1;
        L9:
            if (ThemePreviewActivity.m20790$$Nest$fgetmessagesCheckBoxView(this.this$0) == null) goto L14;
        L11:
            if (r0 >= ThemePreviewActivity.m20790$$Nest$fgetmessagesCheckBoxView(this.this$0).length) goto L14;
            ThemePreviewActivity.m20790$$Nest$fgetmessagesCheckBoxView(this.this$0)[r0].invalidate();
            r0 = r0 + 1;
        L14:
            if (ThemePreviewActivity.m20761$$Nest$fgetbackgroundPlayAnimationView(this.this$0) == null) goto L17;
            ThemePreviewActivity.m20761$$Nest$fgetbackgroundPlayAnimationView(this.this$0).invalidate();
        L17:
            if (ThemePreviewActivity.m20792$$Nest$fgetmessagesPlayAnimationView(this.this$0) == null) goto L22;
            ThemePreviewActivity.m20792$$Nest$fgetmessagesPlayAnimationView(this.this$0).invalidate();
            return;
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$11, reason: invalid class name */
    public class AnonymousClass11 extends DefaultItemAnimator {
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass11(ThemePreviewActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void onMoveAnimationUpdate(RecyclerView.ViewHolder r1) {
            ThemePreviewActivity.m20787$$Nest$fgetlistView2(this.this$0).invalidateViews();
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$12, reason: invalid class name */
    public class AnonymousClass12 extends RecyclerView.OnScrollListener {
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass12(ThemePreviewActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            if (r2 != 0) goto L5;
            ThemePreviewActivity.m20832$$Nest$fputwasScroll(this.this$0, false);
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            ThemePreviewActivity.m20787$$Nest$fgetlistView2(this.this$0).invalidateViews();
            ThemePreviewActivity.m20832$$Nest$fputwasScroll(this.this$0, true);
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$13, reason: invalid class name */
    public class AnonymousClass13 extends FrameLayout {
        private final ColorFilter colorFilter;
        private LinearGradient gradient;
        private int gradientHeight;
        private final Paint gradientPaint;
        final /* synthetic */ ThemePreviewActivity this$0;
        final /* synthetic */ boolean val$drawShadow;

        public AnonymousClass13(ThemePreviewActivity r1, Context r2, boolean r3) {
            this.this$0 = r1;
            this.val$drawShadow = r3;
            super(r2);
            Paint r12 = new Paint(3);
            this.gradientPaint = r12;
            r12.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            ColorMatrix r13 = new ColorMatrix();
            AndroidUtilities.adjustSaturationColorMatrix(r13, 0.4f);
            AndroidUtilities.multiplyBrightnessColorMatrix(r13, 0.65f);
            this.colorFilter = new ColorMatrixColorFilter(r13);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r14) {
            if (this.val$drawShadow == false) goto L23;
            RectF r0 = AndroidUtilities.rectTmp;
            r0.set(0.0f, 0.0f, getWidth(), getHeight());
            Canvas r4 = r14;
            r4.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), 255, 31);
            Theme.applyServiceShaderMatrixForView(this, ThemePreviewActivity.m20758$$Nest$fgetbackgroundImage(this.this$0), this.this$0.themeDelegate);
            Paint r142 = this.this$0.themeDelegate.getPaint("paintChatActionBackground");
            ColorFilter r1 = r142.getColorFilter();
            r142.setColorFilter(this.colorFilter);
            float r5 = 1.0f;
            if (ThemePreviewActivity.m20758$$Nest$fgetbackgroundImage(this.this$0) != null) goto L7;
        L12:
            int r2 = r142.getAlpha();
            r142.setAlpha((int) (r2 * r5));
            r4.drawRect(r0, r142);
            r142.setAlpha(r2);
            r142.setColorFilter(r1);
            if (ThemePreviewActivity.m20808$$Nest$fgetshouldShowBrightnessControll(this.this$0) == true) goto L15;
        L17:
            r4.save();
            if (this.gradient != null) goto L20;
        L21:
            int r143 = getHeight();
            this.gradientHeight = r143;
            LinearGradient r52 = new LinearGradient(0.0f, 0.0f, 0.0f, r143, new int[]{-1, 0}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
            this.gradient = r52;
            this.gradientPaint.setShader(r52);
        L22:
            r4.drawRect(r0, this.gradientPaint);
            r4.restore();
            r4.restore();
        L24:
            super.dispatchDraw(r4);
            return;
        L20:
            if (this.gradientHeight == getHeight()) goto L22;
        L15:
            if (ThemePreviewActivity.m20775$$Nest$fgetdimAmount(this.this$0) <= 0.0f) goto L17;
            r4.drawColor(ColorUtils.setAlphaComponent(-16777216, (int) ((ThemePreviewActivity.m20775$$Nest$fgetdimAmount(this.this$0) * 255.0f) * ThemePreviewActivity.m20801$$Nest$fgetprogressToDarkTheme(this.this$0))));
            goto L17
        L7:
            if ((ThemePreviewActivity.m20758$$Nest$fgetbackgroundImage(this.this$0).getBackground() instanceof MotionBackgroundDrawable) == false) goto L12;
            if (ThemePreviewActivity.m20771$$Nest$fgetcurrentIntensity(this.this$0) >= 0.0f) goto L12;
            r5 = 0.33f;
            goto L12
        L23:
            r4 = r14;
            goto L24
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r4, int r5) {
            super.onMeasure(r4, r5);
            int r42 = 0;
        L4:
            if (r42 >= getChildCount()) goto L9;
            View r52 = getChildAt(r42);
            if (r52.getMeasuredWidth() <= AndroidUtilities.dp(420.0f)) goto L8;
            r52.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(420.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r52.getMeasuredHeight(), TLObject.FLAG_30));
        L8:
            r42 = r42 + 1;
            goto L4
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$14, reason: invalid class name */
    public class AnonymousClass14 extends FrameLayout {
        private final Paint dimPaint;
        private final Paint dimPaint2;
        private final Paint shadowPaint;
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass14(ThemePreviewActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.shadowPaint = new Paint(1);
            this.dimPaint = new Paint(1);
            this.dimPaint2 = new Paint(1);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r9) {
            RectF r0 = AndroidUtilities.rectTmp;
            r0.set(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
            float r1 = AndroidUtilities.dp(8.0f);
            this.shadowPaint.setColor(0);
            this.shadowPaint.setShadowLayer(AndroidUtilities.dpf2(1.0f), 0.0f, AndroidUtilities.dpf2(0.33f), ColorUtils.setAlphaComponent(-16777216, (int) (ThemePreviewActivity.m20776$$Nest$fgetdimmingSlider(this.this$0).getAlpha() * 27.0f)));
            r9.drawRoundRect(r0, r1, r1, this.shadowPaint);
            Theme.applyServiceShaderMatrixForView(this, ThemePreviewActivity.m20758$$Nest$fgetbackgroundImage(this.this$0), this.this$0.themeDelegate);
            Paint r2 = this.this$0.themeDelegate.getPaint("paintChatActionBackground");
            int r3 = r2.getAlpha();
            r2.setAlpha((int) (r3 * ThemePreviewActivity.m20776$$Nest$fgetdimmingSlider(this.this$0).getAlpha()));
            r9.drawRoundRect(r0, r1, r1, r2);
            r2.setAlpha(r3);
            if (ThemePreviewActivity.m20808$$Nest$fgetshouldShowBrightnessControll(this.this$0) == true) goto L5;
        L7:
            this.dimPaint.setColor(520093695);
            this.dimPaint.setAlpha((int) (ThemePreviewActivity.m20776$$Nest$fgetdimmingSlider(this.this$0).getAlpha() * 30.0f));
            r9.drawRoundRect(r0, r1, r1, this.dimPaint);
            super.dispatchDraw(r9);
            return;
        L5:
            if (ThemePreviewActivity.m20775$$Nest$fgetdimAmount(this.this$0) <= 0.0f) goto L7;
            this.dimPaint2.setColor(ColorUtils.setAlphaComponent(-16777216, (int) ((ThemePreviewActivity.m20775$$Nest$fgetdimAmount(this.this$0) * 255.0f) * ThemePreviewActivity.m20801$$Nest$fgetprogressToDarkTheme(this.this$0))));
            r9.drawRoundRect(r0, r1, r1, this.dimPaint2);
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$15, reason: invalid class name */
    public class AnonymousClass15 extends SliderView {
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass15(ThemePreviewActivity r1, Context r2, int r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Stories.recorder.SliderView, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r3) {
            if (getParent() == null) goto L6;
            getParent().requestDisallowInterceptTouchEvent(true);
        L6:
            return super.dispatchTouchEvent(r3);
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$16, reason: invalid class name */
    public class AnonymousClass16 extends FrameLayout {
        private RectF rect;
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass16(ThemePreviewActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.rect = new RectF();
        }

        @Override // android.view.View
        public void onDraw(Canvas r6) {
            this.rect.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
            Theme.applyServiceShaderMatrixForView(ThemePreviewActivity.m20761$$Nest$fgetbackgroundPlayAnimationView(this.this$0), ThemePreviewActivity.m20758$$Nest$fgetbackgroundImage(this.this$0), this.this$0.themeDelegate);
            r6.drawRoundRect(this.rect, getMeasuredHeight() / 2, getMeasuredHeight() / 2, this.this$0.themeDelegate.getPaint("paintChatActionBackground"));
            if (Theme.hasGradientService() == false) goto L6;
            r6.drawRoundRect(this.rect, getMeasuredHeight() / 2, getMeasuredHeight() / 2, this.this$0.themeDelegate.getPaint("paintChatActionBackgroundDarken"));
            return;
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$17, reason: invalid class name */
    public class AnonymousClass17 implements View.OnClickListener {
        int rotation;
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass17(ThemePreviewActivity r1) {
            this.this$0 = r1;
            this.rotation = 0;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r3) {
            ThemePreviewActivity.m20760$$Nest$fgetbackgroundPlayAnimationImageView(this.this$0).setRotation(this.rotation);
            this.rotation -= 45;
            ThemePreviewActivity.m20760$$Nest$fgetbackgroundPlayAnimationImageView(this.this$0).animate().rotationBy(-45.0f).setDuration(300).setInterpolator(CubicBezierInterpolator.EASE_OUT).start();
            if (ThemePreviewActivity.m20759$$Nest$fgetbackgroundImages(this.this$0)[0] == null) goto L9;
            Drawable r32 = ThemePreviewActivity.m20759$$Nest$fgetbackgroundImages(this.this$0)[0].getBackground();
            if ((r32 instanceof MotionBackgroundDrawable) == false) goto L7;
            ((MotionBackgroundDrawable) r32).switchToNextPosition();
            goto L9
        L7:
            ThemePreviewActivity.m20837$$Nest$monColorsRotate(this.this$0);
        L9:
            if (ThemePreviewActivity.m20759$$Nest$fgetbackgroundImages(this.this$0)[1] == null) goto L14;
            Drawable r2 = ThemePreviewActivity.m20759$$Nest$fgetbackgroundImages(this.this$0)[1].getBackground();
            if ((r2 instanceof MotionBackgroundDrawable) == false) goto L15;
            ((MotionBackgroundDrawable) r2).switchToNextPosition();
            return;
        L15:
            return;
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$18, reason: invalid class name */
    public class AnonymousClass18 extends FrameLayout {
        private RectF rect;
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass18(ThemePreviewActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.rect = new RectF();
        }

        @Override // android.view.View
        public void onDraw(Canvas r6) {
            this.rect.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
            Theme.applyServiceShaderMatrixForView(ThemePreviewActivity.m20792$$Nest$fgetmessagesPlayAnimationView(this.this$0), ThemePreviewActivity.m20758$$Nest$fgetbackgroundImage(this.this$0), this.this$0.themeDelegate);
            r6.drawRoundRect(this.rect, getMeasuredHeight() / 2, getMeasuredHeight() / 2, this.this$0.themeDelegate.getPaint("paintChatActionBackground"));
            if (Theme.hasGradientService() == false) goto L6;
            r6.drawRoundRect(this.rect, getMeasuredHeight() / 2, getMeasuredHeight() / 2, this.this$0.themeDelegate.getPaint("paintChatActionBackgroundDarken"));
            return;
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$19, reason: invalid class name */
    public class AnonymousClass19 implements View.OnClickListener {
        int rotation;
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass19(ThemePreviewActivity r1) {
            this.this$0 = r1;
            this.rotation = 0;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r6) {
            ThemePreviewActivity.m20791$$Nest$fgetmessagesPlayAnimationImageView(this.this$0).setRotation(this.rotation);
            this.rotation -= 45;
            ThemePreviewActivity.m20791$$Nest$fgetmessagesPlayAnimationImageView(this.this$0).animate().rotationBy(-45.0f).setDuration(300).setInterpolator(CubicBezierInterpolator.EASE_OUT).start();
            boolean r62 = ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).myMessagesAnimated;
            ThemePreviewActivity r0 = this.this$0;
            if (r62 == true) goto L5;
            int r63 = ThemePreviewActivity.m20749$$Nest$fgetaccent(r0).myMessagesGradientAccentColor3;
            ThemePreviewActivity r02 = this.this$0;
            if (r63 == 0) goto L15;
            int r64 = ThemePreviewActivity.m20749$$Nest$fgetaccent(r02).myMessagesAccentColor;
            ThemePreviewActivity r03 = this.this$0;
            if (r64 == 0) goto L13;
            int r65 = ThemePreviewActivity.m20749$$Nest$fgetaccent(r03).myMessagesAccentColor;
        L14:
            ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).myMessagesAccentColor = ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).myMessagesGradientAccentColor1;
            ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).myMessagesGradientAccentColor1 = ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).myMessagesGradientAccentColor2;
            ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).myMessagesGradientAccentColor2 = ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).myMessagesGradientAccentColor3;
            ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).myMessagesGradientAccentColor3 = r65;
        L20:
            ThemePreviewActivity.m20769$$Nest$fgetcolorPicker(this.this$0).setColor(ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).myMessagesGradientAccentColor3, 3);
            ThemePreviewActivity.m20769$$Nest$fgetcolorPicker(this.this$0).setColor(ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).myMessagesGradientAccentColor2, 2);
            ThemePreviewActivity.m20769$$Nest$fgetcolorPicker(this.this$0).setColor(ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).myMessagesGradientAccentColor1, 1);
            ColorPicker r66 = ThemePreviewActivity.m20769$$Nest$fgetcolorPicker(this.this$0);
            int r04 = ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).myMessagesAccentColor;
            ThemePreviewActivity r4 = this.this$0;
            if (r04 == 0) goto L23;
            int r05 = ThemePreviewActivity.m20749$$Nest$fgetaccent(r4).myMessagesAccentColor;
        L24:
            r66.setColor(r05, 0);
            ThemePreviewActivity.m20790$$Nest$fgetmessagesCheckBoxView(this.this$0)[1].setColor(0, ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).myMessagesAccentColor);
            ThemePreviewActivity.m20790$$Nest$fgetmessagesCheckBoxView(this.this$0)[1].setColor(1, ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).myMessagesGradientAccentColor1);
            ThemePreviewActivity.m20790$$Nest$fgetmessagesCheckBoxView(this.this$0)[1].setColor(2, ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).myMessagesGradientAccentColor2);
            ThemePreviewActivity.m20790$$Nest$fgetmessagesCheckBoxView(this.this$0)[1].setColor(3, ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).myMessagesGradientAccentColor3);
            Theme.refreshThemeColors(true, true);
            ThemePreviewActivity.m20787$$Nest$fgetlistView2(this.this$0).invalidateViews();
            return;
        L23:
            r05 = ThemePreviewActivity.m20749$$Nest$fgetaccent(r4).accentColor;
            goto L24
        L13:
            r65 = ThemePreviewActivity.m20749$$Nest$fgetaccent(r03).accentColor;
            goto L14
        L15:
            int r67 = ThemePreviewActivity.m20749$$Nest$fgetaccent(r02).myMessagesAccentColor;
            ThemePreviewActivity r06 = this.this$0;
            if (r67 == 0) goto L18;
            int r68 = ThemePreviewActivity.m20749$$Nest$fgetaccent(r06).myMessagesAccentColor;
        L19:
            ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).myMessagesAccentColor = ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).myMessagesGradientAccentColor1;
            ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).myMessagesGradientAccentColor1 = ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).myMessagesGradientAccentColor2;
            ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).myMessagesGradientAccentColor2 = r68;
            goto L20
        L18:
            r68 = ThemePreviewActivity.m20749$$Nest$fgetaccent(r06).accentColor;
            goto L19
        L5:
            if (r0.msgOutDrawable.getMotionBackgroundDrawable() == null) goto L26;
            this.this$0.msgOutDrawable.getMotionBackgroundDrawable().switchToNextPosition();
            return;
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$2, reason: invalid class name */
    public class AnonymousClass2 implements GestureDetector2.OnGestureListener {
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass2(ThemePreviewActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.GestureDetector2.OnGestureListener
        public boolean onDown(MotionEvent r1) {
            if (ThemePreviewActivity.m20804$$Nest$fgetscroller(this.this$0) == null) goto L7;
            ThemePreviewActivity.m20804$$Nest$fgetscroller(this.this$0).abortAnimation();
            return true;
        L7:
            return true;
        }

        @Override // org.telegram.ui.Components.GestureDetector2.OnGestureListener
        public boolean onFling(MotionEvent r10, MotionEvent r11, float r12, float r13) {
            if (ThemePreviewActivity.m20804$$Nest$fgetscroller(this.this$0) == null) goto L7;
            ThemePreviewActivity.m20804$$Nest$fgetscroller(this.this$0).abortAnimation();
            ThemePreviewActivity.m20804$$Nest$fgetscroller(this.this$0).fling((int) this.this$0.currentScrollOffset, 0, Math.round(-r12), Math.round(r13), 0, (int) this.this$0.maxScrollOffset, 0, Integer.MAX_VALUE);
            ThemePreviewActivity.m20758$$Nest$fgetbackgroundImage(this.this$0).postInvalidate();
            return true;
        L7:
            return true;
        }

        @Override // org.telegram.ui.Components.GestureDetector2.OnGestureListener
        public void onLongPress(MotionEvent r1) {
        }

        @Override // org.telegram.ui.Components.GestureDetector2.OnGestureListener
        public boolean onScroll(MotionEvent r1, MotionEvent r2, float r3, float r4) {
            if (ThemePreviewActivity.m20804$$Nest$fgetscroller(this.this$0) == null) goto L5;
            ThemePreviewActivity.m20804$$Nest$fgetscroller(this.this$0).abortAnimation();
        L5:
            ThemePreviewActivity r12 = this.this$0;
            r12.currentScrollOffset = Utilities.clamp(r12.currentScrollOffset + r3, r12.maxScrollOffset, 0.0f);
            ThemePreviewActivity.m20836$$Nest$minvalidateBlur(this.this$0);
            ThemePreviewActivity.m20758$$Nest$fgetbackgroundImage(this.this$0).invalidate();
            return true;
        }

        @Override // org.telegram.ui.Components.GestureDetector2.OnGestureListener
        public void onShowPress(MotionEvent r1) {
        }

        @Override // org.telegram.ui.Components.GestureDetector2.OnGestureListener
        public boolean onSingleTapUp(MotionEvent r1) {
            return false;
        }

        @Override // org.telegram.ui.Components.GestureDetector2.OnGestureListener
        public void onUp(MotionEvent r1) {
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$20, reason: invalid class name */
    public class AnonymousClass20 extends FrameLayout {
        final /* synthetic */ ThemePreviewActivity this$0;
        final /* synthetic */ int val$num;
        final /* synthetic */ Rect val$paddings;

        public AnonymousClass20(ThemePreviewActivity r1, Context r2, int r3, Rect r4) {
            this.this$0 = r1;
            this.val$num = r3;
            this.val$paddings = r4;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r6) {
            int r0 = this.val$num;
            ThemePreviewActivity r1 = this.this$0;
            if (r0 != 0) goto L5;
            ThemePreviewActivity.m20807$$Nest$fgetsheetDrawable(r1).setBounds(ThemePreviewActivity.m20769$$Nest$fgetcolorPicker(this.this$0).getLeft() - this.val$paddings.left, 0, ThemePreviewActivity.m20769$$Nest$fgetcolorPicker(this.this$0).getRight() + this.val$paddings.right, getMeasuredHeight());
        L6:
            ThemePreviewActivity.m20807$$Nest$fgetsheetDrawable(this.this$0).draw(r6);
            return;
        L5:
            ThemePreviewActivity.m20807$$Nest$fgetsheetDrawable(r1).setBounds(-this.val$paddings.left, 0, getMeasuredWidth() + this.val$paddings.right, getMeasuredHeight());
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$21, reason: invalid class name */
    public class AnonymousClass21 extends FrameLayout {
        Paint paint;
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass21(ThemePreviewActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.paint = new Paint();
        }

        @Override // android.view.View
        public void onDraw(Canvas r10) {
            int r0 = Theme.chat_composeShadowDrawable.getIntrinsicHeight();
            Theme.chat_composeShadowDrawable.setBounds(0, 0, getMeasuredWidth(), r0);
            Theme.chat_composeShadowDrawable.draw(r10);
            this.paint.setColor(this.this$0.getThemedColor(Theme.key_chat_messagePanelBackground));
            r10.drawRect(0.0f, r0, getMeasuredWidth(), getMeasuredHeight(), this.paint);
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$22, reason: invalid class name */
    public class AnonymousClass22 extends RecyclerListView {
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass22(ThemePreviewActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public boolean onTouchEvent(MotionEvent r3) {
            if (r3.getAction() != 0) goto L6;
            getParent().requestDisallowInterceptTouchEvent(true);
        L6:
            return super.onTouchEvent(r3);
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$23, reason: invalid class name */
    public class AnonymousClass23 extends RecyclerView.ItemDecoration {
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass23(ThemePreviewActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect r1, View r2, RecyclerView r3, RecyclerView.State r4) {
            int r0 = r3.getChildAdapterPosition(r2);
            r1.left = AndroidUtilities.dp(12.0f);
            r1.top = 0;
            r1.bottom = 0;
            if (r0 != (r4.getItemCount() - 1)) goto L6;
            r1.right = AndroidUtilities.dp(12.0f);
            return;
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$24, reason: invalid class name */
    public class AnonymousClass24 extends SeekBarView {
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass24(ThemePreviewActivity r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.SeekBarView, android.view.View
        public boolean onTouchEvent(MotionEvent r3) {
            if (r3.getAction() != 0) goto L6;
            getParent().requestDisallowInterceptTouchEvent(true);
        L6:
            return super.onTouchEvent(r3);
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$25, reason: invalid class name */
    public class AnonymousClass25 implements SeekBarView.SeekBarViewDelegate {
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass25(ThemePreviewActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public void onSeekBarDrag(boolean r1, float r2) {
            ThemePreviewActivity.m20820$$Nest$fputcurrentIntensity(this.this$0, r2);
            ThemePreviewActivity.m20845$$Nest$mupdateIntensity(this.this$0);
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public void onSeekBarPressed(boolean r1) {
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$26, reason: invalid class name */
    public class AnonymousClass26 implements ColorPicker.ColorPickerDelegate {
        final /* synthetic */ ThemePreviewActivity this$0;

        /* renamed from: $r8$lambda$W-PMtYj84ZLOJuOes70vqOuoOrU, reason: not valid java name */
        public static /* synthetic */ void m20846$r8$lambda$WPMtYj84ZLOJuOes70vqOuoOrU(AnonymousClass26 r0, AlertDialog r1, int r2) {
            r0.lambda$deleteTheme$0(r1, r2);
        }

        public AnonymousClass26(ThemePreviewActivity r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$deleteTheme$0(AlertDialog r5, int r6) {
            Theme.deleteThemeAccent(ThemePreviewActivity.m20751$$Nest$fgetapplyingTheme(this.this$0), ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0), true);
            Theme.applyPreviousTheme();
            Theme.refreshThemeColors();
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needSetDayNightTheme, new Object[]{ThemePreviewActivity.m20751$$Nest$fgetapplyingTheme(this.this$0), Boolean.valueOf(ThemePreviewActivity.m20793$$Nest$fgetnightTheme(this.this$0)), null, -1});
            this.this$0.finishFragment();
        }

        @Override // org.telegram.ui.Components.ColorPicker.ColorPickerDelegate
        public void deleteTheme() {
            if (this.this$0.getParentActivity() == null) goto L10;
            AlertDialog.Builder r0 = new AlertDialog.Builder(this.this$0.getParentActivity());
            r0.setTitle(LocaleController.getString(R.string.DeleteThemeTitle));
            r0.setMessage(LocaleController.getString(R.string.DeleteThemeAlert));
            r0.setPositiveButton(LocaleController.getString(R.string.Delete), new ThemePreviewActivity$26$$ExternalSyntheticLambda0(this));
            r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
            AlertDialog r02 = r0.create();
            this.this$0.showDialog(r02);
            TextView r03 = (TextView) r02.getButton(-1);
            if (r03 == null) goto L9;
            r03.setTextColor(this.this$0.getThemedColor(Theme.key_text_RedBold));
            return;
        L9:
            return;
        }

        @Override // org.telegram.ui.Components.ColorPicker.ColorPickerDelegate
        public int getDefaultColor(int r4) {
            if (ThemePreviewActivity.m20770$$Nest$fgetcolorType(this.this$0) == 3) goto L5;
        L11:
            return 0;
        L5:
            if (ThemePreviewActivity.m20751$$Nest$fgetapplyingTheme(this.this$0).firstAccentIsDefault == false) goto L11;
            if (r4 != 0) goto L11;
            Theme.ThemeAccent r3 = ThemePreviewActivity.m20751$$Nest$fgetapplyingTheme(this.this$0).themeAccentsMap.get(Theme.DEFALT_THEME_ACCENT_ID);
            if (r3 == null) goto L11;
            return r3.myMessagesAccentColor;
        }

        @Override // org.telegram.ui.Components.ColorPicker.ColorPickerDelegate
        public void openThemeCreate(boolean r9) {
            ThemePreviewActivity r0 = this.this$0;
            if (r9 == false) goto L10;
            TLRPC.TL_theme r92 = ThemePreviewActivity.m20749$$Nest$fgetaccent(r0).info;
            ThemePreviewActivity r02 = this.this$0;
            if (r92 != null) goto L8;
            r02.finishFragment();
            MessagesController.getInstance(ThemePreviewActivity.access$1100(this.this$0)).saveThemeToServer(ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).parentTheme, ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0));
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needShareTheme, new Object[]{ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).parentTheme, ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0)});
            return;
        L8:
            String r4 = "https://" + MessagesController.getInstance(ThemePreviewActivity.access$1200(r02)).linkPrefix + "/addtheme/" + ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).info.slug;
            this.this$0.showDialog(new ShareAlert(this.this$0.getParentActivity(), null, r4, false, r4, false));
            return;
        L10:
            AlertsCreator.createThemeCreateDialog(r0, 1, null, null);
        }

        @Override // org.telegram.ui.Components.ColorPicker.ColorPickerDelegate
        public void setColor(int r3, int r4, boolean r5) {
            int r0 = ThemePreviewActivity.m20803$$Nest$fgetscreenType(this.this$0);
            ThemePreviewActivity r2 = this.this$0;
            if (r0 != 2) goto L6;
            ThemePreviewActivity.m20841$$Nest$msetBackgroundColor(r2, r3, r4, r5, true);
            return;
        L6:
            ThemePreviewActivity.m20839$$Nest$mscheduleApplyColor(r2, r3, r4, r5);
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$27, reason: invalid class name */
    public class AnonymousClass27 extends FrameLayout {
        private int[] loc;
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass27(ThemePreviewActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.loc = new int[2];
        }

        @Override // android.view.View
        public void invalidate() {
            super.invalidate();
            if (ThemePreviewActivity.m20795$$Nest$fgetpage2(this.this$0) == null) goto L6;
            ThemePreviewActivity.m20795$$Nest$fgetpage2(this.this$0).invalidate();
            return;
        }

        @Override // android.view.View
        public void onDraw(Canvas r5) {
            if (AndroidUtilities.usingHardwareInput == true) goto L11;
            getLocationInWindow(this.loc);
            if (ThemePreviewActivity.m20750$$Nest$fgetactionBar2(this.this$0).getTranslationY() == this.loc[1]) goto L8;
            ThemePreviewActivity.m20750$$Nest$fgetactionBar2(this.this$0).setTranslationY(-this.loc[1]);
            ThemePreviewActivity.m20795$$Nest$fgetpage2(this.this$0).invalidate();
        L8:
            if (SystemClock.elapsedRealtime() >= ThemePreviewActivity.m20813$$Nest$fgetwatchForKeyboardEndTime(this.this$0)) goto L12;
            invalidate();
            return;
        L12:
            return;
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$28, reason: invalid class name */
    public class AnonymousClass28 implements ViewPager.OnPageChangeListener {
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass28(ThemePreviewActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int r1) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int r1, float r2, int r3) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int r1) {
            ThemePreviewActivity.m20778$$Nest$fgetdotsContainer(this.this$0).invalidate();
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$29, reason: invalid class name */
    public class AnonymousClass29 extends PagerAdapter {
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass29(ThemePreviewActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(ViewGroup r1, int r2, Object r3) {
            r1.removeView((View) r3);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            if (ThemePreviewActivity.m20803$$Nest$fgetscreenType(this.this$0) == 0) goto L6;
            return 1;
        L6:
            return 2;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getItemPosition(Object r1) {
            return -1;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Object instantiateItem(ViewGroup r1, int r2) {
            ThemePreviewActivity r0 = this.this$0;
            if (r2 != 0) goto L5;
            FrameLayout r02 = ThemePreviewActivity.m20795$$Nest$fgetpage2(r0);
        L6:
            r1.addView(r02);
            return r02;
        L5:
            r02 = ThemePreviewActivity.m20794$$Nest$fgetpage1(r0);
            goto L6
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean isViewFromObject(View r1, Object r2) {
            if (r2 != r1) goto L5;
            return true;
        L5:
            return false;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void unregisterDataSetObserver(DataSetObserver r1) {
            if (r1 == null) goto L5;
            super.unregisterDataSetObserver(r1);
            return;
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends ThemePreviewActivity {
        final /* synthetic */ ChatActivity val$chatActivity;
        final /* synthetic */ boolean val$initialIsDark;

        public AnonymousClass3(Object r1, Bitmap r2, boolean r3, boolean r4, ChatActivity r5, boolean r6) {
            this.val$chatActivity = r5;
            this.val$initialIsDark = r6;
            super(r1, r2, r3, r4);
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public void onFragmentClosed() {
            super.onFragmentClosed();
            ChatActivity.ThemeDelegate r0 = this.val$chatActivity.themeDelegate;
            r0.setCurrentTheme(r0.getCurrentTheme(), this.val$chatActivity.themeDelegate.getCurrentWallpaper(), false, Boolean.valueOf(this.val$initialIsDark));
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$30, reason: invalid class name */
    public class AnonymousClass30 extends View {
        private Paint paint;
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass30(ThemePreviewActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.paint = new Paint(1);
        }

        @Override // android.view.View
        public void onDraw(Canvas r7) {
            int r0 = ThemePreviewActivity.m20811$$Nest$fgetviewPager(this.this$0).getCurrentItem();
            this.paint.setColor(ThemePreviewActivity.m20835$$Nest$mgetButtonsColor(this.this$0, Theme.key_chat_fieldOverlayText));
            int r1 = 0;
        L4:
            if (r1 >= 2) goto L10;
            Paint r2 = this.paint;
            if (r1 != r0) goto L8;
            int r3 = 255;
        L9:
            r2.setAlpha(r3);
            r7.drawCircle(AndroidUtilities.dp((r1 * 15) + 3), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(3.0f), this.paint);
            r1 = r1 + 1;
            goto L4
        L8:
            r3 = 127;
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$31, reason: invalid class name */
    public class AnonymousClass31 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass31(ThemePreviewActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r1) {
            float r12 = ((Float) r1.getAnimatedValue()).floatValue();
            ThemePreviewActivity.m20758$$Nest$fgetbackgroundImage(this.this$0).setAlpha(r12);
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$32, reason: invalid class name */
    public class AnonymousClass32 extends AnimatorListenerAdapter {
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass32(ThemePreviewActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            super.onAnimationEnd(r2);
            ThemePreviewActivity.m20758$$Nest$fgetbackgroundImage(this.this$0).invalidate();
            ThemePreviewActivity.m20759$$Nest$fgetbackgroundImages(this.this$0)[1].setVisibility(8);
            ThemePreviewActivity.m20831$$Nest$fputvalueAnimator(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$33, reason: invalid class name */
    public class AnonymousClass33 extends AnimatorListenerAdapter {
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass33(ThemePreviewActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            WallpaperCheckBoxView[] r12 = ThemePreviewActivity.m20753$$Nest$fgetbackgroundCheckBoxView(this.this$0);
            if (ThemePreviewActivity.m20805$$Nest$fgetselectedPattern(this.this$0) == null) goto L5;
            char r0 = 0;
        L6:
            r12[r0].setVisibility(4);
            return;
        L5:
            r0 = 2;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$34, reason: invalid class name */
    public class AnonymousClass34 extends AnimatorListenerAdapter {
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass34(ThemePreviewActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            if (ThemePreviewActivity.m20805$$Nest$fgetselectedPattern(this.this$0) != null) goto L6;
            ThemePreviewActivity.m20753$$Nest$fgetbackgroundCheckBoxView(this.this$0)[0].setVisibility(4);
            return;
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$35, reason: invalid class name */
    public class AnonymousClass35 extends AnimatorListenerAdapter {
        final /* synthetic */ ThemePreviewActivity this$0;
        final /* synthetic */ int val$num;
        final /* synthetic */ int val$otherNum;
        final /* synthetic */ boolean val$show;
        final /* synthetic */ boolean val$showMotion;

        public AnonymousClass35(ThemePreviewActivity r1, boolean r2, int r3, int r4, boolean r5) {
            this.this$0 = r1;
            this.val$show = r2;
            this.val$otherNum = r3;
            this.val$num = r4;
            this.val$showMotion = r5;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            ThemePreviewActivity.m20828$$Nest$fputpatternViewAnimation(this.this$0, null);
            if (this.val$show == false) goto L8;
            if (ThemePreviewActivity.m20799$$Nest$fgetpatternLayout(this.this$0)[this.val$otherNum].getVisibility() != 0) goto L8;
            ThemePreviewActivity.m20799$$Nest$fgetpatternLayout(this.this$0)[this.val$otherNum].setAlpha(1.0f);
            ThemePreviewActivity.m20799$$Nest$fgetpatternLayout(this.this$0)[this.val$otherNum].setVisibility(4);
        L10:
            char r1 = 2;
            if (ThemePreviewActivity.m20803$$Nest$fgetscreenType(this.this$0) != 1) goto L13;
        L19:
            WallpaperCheckBoxView[] r42 = ThemePreviewActivity.m20753$$Nest$fgetbackgroundCheckBoxView(this.this$0);
            if (this.val$showMotion == false) goto L22;
            r1 = 0;
        L22:
            r42[r1].setVisibility(4);
            return;
        L13:
            if (ThemePreviewActivity.m20803$$Nest$fgetscreenType(this.this$0) == 2) goto L19;
            if (this.val$num != 1) goto L24;
            ThemePreviewActivity.m20799$$Nest$fgetpatternLayout(this.this$0)[this.val$otherNum].setAlpha(0.0f);
            return;
        L24:
            return;
        L8:
            if (this.val$show == true) goto L10;
            ThemePreviewActivity.m20799$$Nest$fgetpatternLayout(this.this$0)[this.val$num].setVisibility(4);
            goto L10
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$36, reason: invalid class name */
    public class AnonymousClass36 extends AnimatorListenerAdapter {
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass36(ThemePreviewActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            ThemePreviewActivity.m20826$$Nest$fputmotionAnimation(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$37, reason: invalid class name */
    public class AnonymousClass37 extends AnimatorListenerAdapter {
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass37(ThemePreviewActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (ThemePreviewActivity.m20761$$Nest$fgetbackgroundPlayAnimationView(this.this$0).getTag() != null) goto L5;
            ThemePreviewActivity.m20761$$Nest$fgetbackgroundPlayAnimationView(this.this$0).setVisibility(4);
        L5:
            ThemePreviewActivity.m20814$$Nest$fputbackgroundPlayViewAnimator(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$38, reason: invalid class name */
    public class AnonymousClass38 extends AnimatorListenerAdapter {
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass38(ThemePreviewActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (ThemePreviewActivity.m20792$$Nest$fgetmessagesPlayAnimationView(this.this$0).getTag() != null) goto L5;
            ThemePreviewActivity.m20792$$Nest$fgetmessagesPlayAnimationView(this.this$0).setVisibility(4);
        L5:
            ThemePreviewActivity.m20825$$Nest$fputmessagesPlayViewAnimator(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$39, reason: invalid class name */
    public class AnonymousClass39 extends BackgroundGradientDrawable.ListenerAdapter {
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass39(ThemePreviewActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.BackgroundGradientDrawable.ListenerAdapter, org.telegram.ui.Components.BackgroundGradientDrawable.Listener
        public void onSizeReady(int r5, int r6) {
            Point r0 = AndroidUtilities.displaySize;
            boolean r2 = false;
            if (r0.x > r0.y) goto L5;
            boolean r02 = true;
        L6:
            if (r5 > r6) goto L8;
            r2 = true;
        L8:
            if (r02 != r2) goto L11;
            ThemePreviewActivity.m20758$$Nest$fgetbackgroundImage(this.this$0).invalidate();
            return;
        L11:
            return;
        L5:
            r02 = false;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$4, reason: invalid class name */
    public class AnonymousClass4 implements DayNightSwitchDelegate {
        boolean forceDark;
        final /* synthetic */ ChatActivity val$chatActivity;
        final /* synthetic */ boolean val$initialIsDark;

        public AnonymousClass4(boolean r1, ChatActivity r2) {
            this.val$initialIsDark = r1;
            this.val$chatActivity = r2;
            this.forceDark = r1;
        }

        @Override // org.telegram.ui.ThemePreviewActivity.DayNightSwitchDelegate
        public boolean isDark() {
            return this.forceDark;
        }

        @Override // org.telegram.ui.ThemePreviewActivity.DayNightSwitchDelegate
        public boolean supportsAnimation() {
            return true;
        }

        @Override // org.telegram.ui.ThemePreviewActivity.DayNightSwitchDelegate
        public void switchDayNight(boolean r4) {
            this.forceDark = !this.forceDark;
            ChatActivity.ThemeDelegate r0 = this.val$chatActivity.themeDelegate;
            r0.setCurrentTheme(r0.getCurrentTheme(), this.val$chatActivity.themeDelegate.getCurrentWallpaper(), r4, Boolean.valueOf(this.forceDark));
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$40, reason: invalid class name */
    public class AnonymousClass40 extends View {
        final /* synthetic */ ThemePreviewActivity this$0;
        final /* synthetic */ Bitmap val$bitmap;
        final /* synthetic */ Canvas val$bitmapCanvas;
        final /* synthetic */ Paint val$bitmapPaint;
        final /* synthetic */ float val$cx;
        final /* synthetic */ float val$cy;
        final /* synthetic */ float val$r;
        final /* synthetic */ float val$x;
        final /* synthetic */ Paint val$xRefPaint;
        final /* synthetic */ float val$y;

        public AnonymousClass40(ThemePreviewActivity r1, Context r2, Canvas r3, float r4, float r5, float r6, Paint r7, Bitmap r8, Paint r9, float r10, float r11) {
            this.this$0 = r1;
            this.val$bitmapCanvas = r3;
            this.val$cx = r4;
            this.val$cy = r5;
            this.val$r = r6;
            this.val$xRefPaint = r7;
            this.val$bitmap = r8;
            this.val$bitmapPaint = r9;
            this.val$x = r10;
            this.val$y = r11;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r7) {
            super.onDraw(r7);
            if (this.this$0.themeDelegate.isDark() == true) goto L5;
            r7.drawCircle(this.val$cx, this.val$cy, this.val$r * (1.0f - ThemePreviewActivity.m20767$$Nest$fgetchangeDayNightViewProgress(this.this$0)), this.val$bitmapPaint);
        L9:
            r7.save();
            r7.translate(this.val$x, this.val$y);
            ThemePreviewActivity.m20774$$Nest$fgetdayNightItem(this.this$0).draw(r7);
            r7.restore();
            return;
        L5:
            if (ThemePreviewActivity.m20767$$Nest$fgetchangeDayNightViewProgress(this.this$0) <= 0.0f) goto L7;
            this.val$bitmapCanvas.drawCircle(this.val$cx, this.val$cy, this.val$r * ThemePreviewActivity.m20767$$Nest$fgetchangeDayNightViewProgress(this.this$0), this.val$xRefPaint);
        L7:
            r7.drawBitmap(this.val$bitmap, 0.0f, 0.0f, this.val$bitmapPaint);
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$41, reason: invalid class name */
    public class AnonymousClass41 implements ValueAnimator.AnimatorUpdateListener {
        boolean changedNavigationBarColor;
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass41(ThemePreviewActivity r1) {
            this.this$0 = r1;
            this.changedNavigationBarColor = false;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r2) {
            ThemePreviewActivity.m20819$$Nest$fputchangeDayNightViewProgress(this.this$0, ((Float) r2.getAnimatedValue()).floatValue());
            ThemePreviewActivity.m20765$$Nest$fgetchangeDayNightView(this.this$0).invalidate();
            if (this.changedNavigationBarColor == false) goto L5;
            return;
        L5:
            if (ThemePreviewActivity.m20767$$Nest$fgetchangeDayNightViewProgress(this.this$0) <= 0.5f) goto L9;
            this.changedNavigationBarColor = true;
            return;
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$42, reason: invalid class name */
    public class AnonymousClass42 extends AnimatorListenerAdapter {
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass42(ThemePreviewActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            if (ThemePreviewActivity.m20765$$Nest$fgetchangeDayNightView(this.this$0) != null) goto L5;
        L8:
            ThemePreviewActivity.m20817$$Nest$fputchangeDayNightViewAnimator(this.this$0, null);
            super.onAnimationEnd(r4);
            return;
        L5:
            if (ThemePreviewActivity.m20765$$Nest$fgetchangeDayNightView(this.this$0).getParent() == null) goto L7;
            ((ViewGroup) ThemePreviewActivity.m20765$$Nest$fgetchangeDayNightView(this.this$0).getParent()).removeView(ThemePreviewActivity.m20765$$Nest$fgetchangeDayNightView(this.this$0));
        L7:
            ThemePreviewActivity.m20816$$Nest$fputchangeDayNightView(this.this$0, null);
            goto L8
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$43, reason: invalid class name */
    public class AnonymousClass43 extends AnimatorListenerAdapter {
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass43(ThemePreviewActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            if (this.this$0.onSwitchDayNightDelegate.isDark() == true) goto L6;
            ThemePreviewActivity.m20776$$Nest$fgetdimmingSlider(this.this$0).setVisibility(8);
            return;
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends ActionBarMenuItem.ActionBarMenuItemSearchListener {
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass5(ThemePreviewActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public boolean canCollapseSearch() {
            return true;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchCollapse() {
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchExpand() {
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onTextChanged(EditText r1) {
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$6, reason: invalid class name */
    public class AnonymousClass6 extends FrameLayout {
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass6(ThemePreviewActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r1, View r2, long r3) {
            boolean r32 = super.drawChild(r1, r2, r3);
            if (r2 == ThemePreviewActivity.access$300(this.this$0)) goto L5;
        L11:
            return r32;
        L5:
            if (ThemePreviewActivity.access$400(this.this$0) == null) goto L11;
            INavigationLayout r22 = ThemePreviewActivity.access$700(this.this$0);
            if (ThemePreviewActivity.access$500(this.this$0).getVisibility() != 0) goto L9;
            int r0 = ThemePreviewActivity.access$600(this.this$0).getMeasuredHeight();
        L10:
            r22.drawHeaderShadow(r1, r0);
            goto L11
        L9:
            r0 = 0;
            goto L10
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r9, int r10) {
            int r6 = View.MeasureSpec.getSize(r9);
            int r7 = View.MeasureSpec.getSize(r10);
            setMeasuredDimension(r6, r7);
            measureChildWithMargins(ThemePreviewActivity.access$000(this.this$0), r9, 0, r10, 0);
            int r1 = ThemePreviewActivity.access$100(this.this$0).getMeasuredHeight();
            if (ThemePreviewActivity.access$200(this.this$0).getVisibility() != 0) goto L5;
            r7 = r7 - r1;
        L5:
            ((FrameLayout.LayoutParams) ThemePreviewActivity.m20786$$Nest$fgetlistView(this.this$0).getLayoutParams()).topMargin = r1;
            ThemePreviewActivity.m20786$$Nest$fgetlistView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r6, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r7, TLObject.FLAG_30));
            measureChildWithMargins(ThemePreviewActivity.m20781$$Nest$fgetfloatingButton(this.this$0), r9, 0, r10, 0);
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$7, reason: invalid class name */
    public class AnonymousClass7 extends FrameLayout {
        private boolean ignoreLayout;
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass7(ThemePreviewActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r1, View r2, long r3) {
            boolean r32 = super.drawChild(r1, r2, r3);
            if (r2 == ThemePreviewActivity.m20750$$Nest$fgetactionBar2(this.this$0)) goto L5;
        L11:
            return r32;
        L5:
            if (ThemePreviewActivity.access$800(this.this$0) == null) goto L11;
            INavigationLayout r22 = ThemePreviewActivity.access$900(this.this$0);
            if (ThemePreviewActivity.m20750$$Nest$fgetactionBar2(this.this$0).getVisibility() != 0) goto L9;
            int r0 = (int) (ThemePreviewActivity.m20750$$Nest$fgetactionBar2(this.this$0).getMeasuredHeight() + ThemePreviewActivity.m20750$$Nest$fgetactionBar2(this.this$0).getTranslationY());
        L10:
            r22.drawHeaderShadow(r1, r0);
            goto L11
        L9:
            r0 = 0;
            goto L10
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r16, int r17) {
            int r6 = View.MeasureSpec.getSize(r16);
            int r7 = View.MeasureSpec.getSize(r17);
            setMeasuredDimension(r6, r7);
            if (ThemePreviewActivity.m20780$$Nest$fgetdropDownContainer(this.this$0) == null) goto L14;
            this.ignoreLayout = true;
            if (AndroidUtilities.isTablet() == true) goto L8;
            FrameLayout.LayoutParams r2 = (FrameLayout.LayoutParams) ThemePreviewActivity.m20780$$Nest$fgetdropDownContainer(this.this$0).getLayoutParams();
            r2.topMargin = AndroidUtilities.statusBarHeight;
            ThemePreviewActivity.m20780$$Nest$fgetdropDownContainer(this.this$0).setLayoutParams(r2);
        L8:
            if (AndroidUtilities.isTablet() == false) goto L10;
        L12:
            ThemePreviewActivity.m20779$$Nest$fgetdropDown(this.this$0).setTextSize(1, 20.0f);
        L13:
            this.ignoreLayout = false;
            goto L14
        L10:
            if (ApplicationLoader.applicationContext.getResources().getConfiguration().orientation != 2) goto L12;
            ThemePreviewActivity.m20779$$Nest$fgetdropDown(this.this$0).setTextSize(1, 18.0f);
        L14:
            measureChildWithMargins(ThemePreviewActivity.m20750$$Nest$fgetactionBar2(this.this$0), r16, 0, r17, 0);
            int r1 = ThemePreviewActivity.m20750$$Nest$fgetactionBar2(this.this$0).getMeasuredHeight();
            if (ThemePreviewActivity.m20750$$Nest$fgetactionBar2(this.this$0).getVisibility() != 0) goto L17;
            r7 = r7 - r1;
        L17:
            FrameLayout.LayoutParams r22 = (FrameLayout.LayoutParams) ThemePreviewActivity.m20787$$Nest$fgetlistView2(this.this$0).getLayoutParams();
            r22.topMargin = r1;
            int r4 = 58;
            if (ThemePreviewActivity.m20803$$Nest$fgetscreenType(this.this$0) != 2) goto L30;
            RecyclerListView r3 = ThemePreviewActivity.m20787$$Nest$fgetlistView2(this.this$0);
            int r12 = AndroidUtilities.dp(4.0f);
            ThemePreviewActivity r13 = this.this$0;
            if (r13.self == false) goto L22;
        L24:
            int r132 = 0;
        L25:
            int r133 = AndroidUtilities.dp(r132 + 72) - 12;
            if (this.this$0.insideBottomSheet() == false) goto L28;
            int r14 = AndroidUtilities.navigationBarHeight;
        L29:
            r3.setPadding(0, r12, 0, r133 + r14);
            goto L30
        L28:
            r14 = 0;
            goto L29
        L22:
            if (r13.dialogId <= 0) goto L24;
            r132 = 58;
        L30:
            ThemePreviewActivity.m20787$$Nest$fgetlistView2(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r6, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r7 - r22.bottomMargin, TLObject.FLAG_30));
            ((FrameLayout.LayoutParams) ThemePreviewActivity.m20758$$Nest$fgetbackgroundImage(this.this$0).getLayoutParams()).topMargin = r1;
            ThemePreviewActivity.m20758$$Nest$fgetbackgroundImage(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r6, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r7, TLObject.FLAG_30));
            if (ThemePreviewActivity.m20777$$Nest$fgetdimmingSliderContainer(this.this$0) == null) goto L34;
            ((FrameLayout.LayoutParams) ThemePreviewActivity.m20777$$Nest$fgetdimmingSliderContainer(this.this$0).getLayoutParams()).topMargin = r1;
            ThemePreviewActivity.m20777$$Nest$fgetdimmingSliderContainer(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(222.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(76.0f), TLObject.FLAG_30));
        L34:
            if (ThemePreviewActivity.m20764$$Nest$fgetbottomOverlayChat(this.this$0) == null) goto L51;
            FrameLayout r15 = ThemePreviewActivity.m20764$$Nest$fgetbottomOverlayChat(this.this$0);
            int r23 = AndroidUtilities.dp(12.0f);
            int r32 = AndroidUtilities.dp(12.0f);
            int r72 = AndroidUtilities.dp(12.0f);
            int r122 = AndroidUtilities.dp(12.0f);
            if (this.this$0.insideBottomSheet() == false) goto L38;
            int r134 = AndroidUtilities.navigationBarHeight;
        L39:
            r15.setPadding(r23, r32, r72, r122 + r134);
            FrameLayout.LayoutParams r18 = (FrameLayout.LayoutParams) ThemePreviewActivity.m20764$$Nest$fgetbottomOverlayChat(this.this$0).getLayoutParams();
            ThemePreviewActivity r24 = this.this$0;
            if (r24.self == false) goto L42;
        L44:
            r4 = 0;
        L45:
            int r25 = AndroidUtilities.dp(72 + r4);
            if (this.this$0.insideBottomSheet() == false) goto L48;
            int r33 = AndroidUtilities.navigationBarHeight;
        L49:
            r18.height = r25 + r33;
            measureChildWithMargins(ThemePreviewActivity.m20764$$Nest$fgetbottomOverlayChat(this.this$0), r16, 0, r17, 0);
            goto L51
        L48:
            r33 = 0;
            goto L49
        L42:
            if (r24.dialogId <= 0) goto L44;
        L38:
            r134 = 0;
        L51:
            if (ThemePreviewActivity.m20807$$Nest$fgetsheetDrawable(this.this$0) == null) goto L53;
            ThemePreviewActivity.m20807$$Nest$fgetsheetDrawable(this.this$0).getPadding(AndroidUtilities.rectTmp2);
        L53:
            int r73 = 0;
        L55:
            if (r73 >= ThemePreviewActivity.m20799$$Nest$fgetpatternLayout(this.this$0).length) goto L81;
            if (ThemePreviewActivity.m20799$$Nest$fgetpatternLayout(this.this$0)[r73] == null) goto L80;
            FrameLayout.LayoutParams r19 = (FrameLayout.LayoutParams) ThemePreviewActivity.m20799$$Nest$fgetpatternLayout(this.this$0)[r73].getLayoutParams();
            if (r73 == 0) goto L61;
            float r26 = 316.0f;
        L66:
            r19.height = AndroidUtilities.dp(r26);
            if (this.this$0.insideBottomSheet() == false) goto L69;
            r19.height += AndroidUtilities.navigationBarHeight;
        L69:
            if (r73 != 0) goto L71;
            r19.height += AndroidUtilities.dp(12.0f) + AndroidUtilities.rectTmp2.top;
        L71:
            FrameLayout r110 = ThemePreviewActivity.m20799$$Nest$fgetpatternLayout(this.this$0)[r73];
            if (r73 != 0) goto L74;
            int r27 = AndroidUtilities.dp(12.0f) + AndroidUtilities.rectTmp2.top;
        L76:
            if (this.this$0.insideBottomSheet() == false) goto L78;
            int r34 = AndroidUtilities.navigationBarHeight;
        L79:
            r110.setPadding(0, r27, 0, r34);
            measureChildWithMargins(ThemePreviewActivity.m20799$$Nest$fgetpatternLayout(this.this$0)[r73], r16, 0, r17, 0);
            goto L80
        L78:
            r34 = 0;
            goto L79
        L74:
            r27 = 0;
            goto L76
        L61:
            if (ThemePreviewActivity.m20803$$Nest$fgetscreenType(this.this$0) != 2) goto L63;
            int r28 = MediaError.DetailedErrorCode.DASH_NETWORK;
        L64:
            r26 = r28;
            goto L66
        L63:
            r28 = 273;
        L80:
            r73 = r73 + 1;
            goto L55
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$8, reason: invalid class name */
    public class AnonymousClass8 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ ThemePreviewActivity this$0;

        /* renamed from: org.telegram.ui.ThemePreviewActivity$8$1, reason: invalid class name */
        public class AnonymousClass1 extends ShareAlert {
            final /* synthetic */ AnonymousClass8 this$1;

            public AnonymousClass1(AnonymousClass8 r1, Context r2, ArrayList r3, String r4, boolean r5, String r6, boolean r7) {
                this.this$1 = r1;
                super(r2, r3, r4, r5, r6, r7);
            }

            @Override // org.telegram.ui.Components.ShareAlert
            public void onSend(LongSparseArray<TLRPC.Dialog> r9, int r10, TLRPC.TL_forumTopic r11, boolean r12) {
                if (r12 == true) goto L4;
                return;
            L4:
                int r112 = r9.size();
                AnonymousClass8 r8 = this.this$1;
                if (r112 != 1) goto L8;
                ThemePreviewActivity.m20810$$Nest$fgetundoView(r8.this$0).showWithAction(r9.valueAt(0).id, 61, Integer.valueOf(r10));
                return;
            L8:
                ThemePreviewActivity.m20810$$Nest$fgetundoView(r8.this$0).showWithAction(0, 61, Integer.valueOf(r10), Integer.valueOf(r9.size()), null, null);
            }
        }

        /* renamed from: org.telegram.ui.ThemePreviewActivity$8$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass8 this$1;

            public AnonymousClass2(AnonymousClass8 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                if (this.this$1.this$0.onSwitchDayNightDelegate.isDark() == true) goto L6;
                ThemePreviewActivity.m20776$$Nest$fgetdimmingSlider(this.this$1.this$0).setVisibility(8);
                return;
            }
        }

        /* renamed from: org.telegram.ui.ThemePreviewActivity$8$3, reason: invalid class name */
        public class AnonymousClass3 extends PhotoViewer.EmptyPhotoViewerProvider {
            final /* synthetic */ AnonymousClass8 this$1;
            final /* synthetic */ MediaController.PhotoEntry val$photoEntry;

            public AnonymousClass3(AnonymousClass8 r1, MediaController.PhotoEntry r2) {
                this.this$1 = r1;
                this.val$photoEntry = r2;
            }

            @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
            public boolean allowCaption() {
                return false;
            }

            @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
            public void sendButtonPressed(int r1, VideoEditedInfo r2, boolean r3, int r4, int r5, boolean r6) {
                if (this.val$photoEntry.imagePath == null) goto L13;
                File r12 = new File(FileLoader.getDirectory(4), Utilities.random.nextInt() + ".jpg");
                Point r22 = AndroidUtilities.getRealScreenSize();
                Bitmap r23 = ImageLoader.loadBitmap(this.val$photoEntry.imagePath, null, (float) r22.x, (float) r22.y, true);
                r23.compress(Bitmap.CompressFormat.JPEG, 87, new FileOutputStream(r12));     // Catch: FileNotFoundException -> L7
            L9:
                File r13 = new File(this.val$photoEntry.imagePath);
                ThemePreviewActivity.m20821$$Nest$fputcurrentWallpaper(this.this$1.this$0, new WallpapersListActivity.FileWallpaper(_UrlKt.FRAGMENT_ENCODE_SET, r13, r13));
                ThemePreviewActivity.m20822$$Nest$fputcurrentWallpaperBitmap(this.this$1.this$0, r23);
                ThemePreviewActivity.m20824$$Nest$fputlastSizeHash(this.this$1.this$0, 0);
                ThemePreviewActivity.m20758$$Nest$fgetbackgroundImage(this.this$1.this$0).requestLayout();
                ThemePreviewActivity.m20842$$Nest$msetCurrentImage(this.this$1.this$0, false);
                ThemePreviewActivity.m20815$$Nest$fputblurredBitmap(this.this$1.this$0, null);
                ThemePreviewActivity.m20844$$Nest$mupdateBlurred(this.this$1.this$0);
                return;
            L7:
                e = move-exception;
                e.printStackTrace();
                goto L9
            }
        }

        public static /* synthetic */ void $r8$lambda$EPJMLwwqbb1Ks5aN5JxsvZGgu0Q(AnonymousClass8 r0, ValueAnimator r1) {
            r0.lambda$onItemClick$0(r1);
        }

        public AnonymousClass8(ThemePreviewActivity r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onItemClick$0(ValueAnimator r2) {
            ThemePreviewActivity.m20829$$Nest$fputprogressToDarkTheme(this.this$0, ((Float) r2.getAnimatedValue()).floatValue());
            ThemePreviewActivity.m20758$$Nest$fgetbackgroundImage(this.this$0).invalidate();
            ThemePreviewActivity.m20764$$Nest$fgetbottomOverlayChat(this.this$0).invalidate();
            ThemePreviewActivity.m20776$$Nest$fgetdimmingSlider(this.this$0).setAlpha(ThemePreviewActivity.m20801$$Nest$fgetprogressToDarkTheme(this.this$0));
            ThemePreviewActivity.m20777$$Nest$fgetdimmingSliderContainer(this.this$0).invalidate();
            ThemePreviewActivity.m20836$$Nest$minvalidateBlur(this.this$0);
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r21) {
            int r3 = 0;
            if (r21 != (-1)) goto L9;
            if (ThemePreviewActivity.m20834$$Nest$mcheckDiscard(this.this$0, true) == false) goto L126;
            ThemePreviewActivity.m20833$$Nest$mcancelThemeApply(this.this$0, false);
            return;
        L126:
            return;
        L9:
            if (r21 < 1) goto L14;
            if (r21 > 3) goto L14;
            ThemePreviewActivity.m20840$$Nest$mselectColorType(this.this$0, r21);
            return;
        L14:
            if (r21 != 4) goto L40;
            if (ThemePreviewActivity.m20802$$Nest$fgetremoveBackgroundOverride(this.this$0) == false) goto L18;
            Theme.resetCustomWallpaper(false);
        L18:
            File r0 = ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).getPathToWallpaper();
            if (r0 == null) goto L21;
            r0.delete();
        L21:
            Theme.ThemeAccent r02 = ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0);
            if (ThemePreviewActivity.m20805$$Nest$fgetselectedPattern(this.this$0) == null) goto L24;
            String r5 = ThemePreviewActivity.m20805$$Nest$fgetselectedPattern(this.this$0).slug;
        L25:
            r02.patternSlug = r5;
            ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).patternIntensity = ThemePreviewActivity.m20771$$Nest$fgetcurrentIntensity(this.this$0);
            ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).patternMotion = ThemePreviewActivity.m20784$$Nest$fgetisMotion(this.this$0);
            if (((int) ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).backgroundOverrideColor) != 0) goto L29;
            ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).backgroundOverrideColor = 4294967296L;
        L29:
            if (((int) ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).backgroundGradientOverrideColor1) != 0) goto L32;
            ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).backgroundGradientOverrideColor1 = 4294967296L;
        L32:
            if (((int) ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).backgroundGradientOverrideColor2) != 0) goto L35;
            ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).backgroundGradientOverrideColor2 = 4294967296L;
        L35:
            if (((int) ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).backgroundGradientOverrideColor3) != 0) goto L37;
            ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).backgroundGradientOverrideColor3 = 4294967296L;
        L37:
            ThemePreviewActivity.m20838$$Nest$msaveAccentWallpaper(this.this$0);
            NotificationCenter.getGlobalInstance().removeObserver(this.this$0, NotificationCenter.wallpapersDidLoad);
            Theme.saveThemeAccents(ThemePreviewActivity.m20751$$Nest$fgetapplyingTheme(this.this$0), true, false, false, true);
            Theme.applyPreviousTheme();
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needSetDayNightTheme, new Object[]{ThemePreviewActivity.m20751$$Nest$fgetapplyingTheme(this.this$0), Boolean.valueOf(ThemePreviewActivity.m20793$$Nest$fgetnightTheme(this.this$0)), null, -1});
            this.this$0.finishFragment();
            return;
        L24:
            r5 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L25
        L40:
            if (r21 != 5) goto L83;
            if (this.this$0.getParentActivity() == null) goto L133;
            StringBuilder r03 = new StringBuilder();
            if (ThemePreviewActivity.m20783$$Nest$fgetisBlurred(this.this$0) == false) goto L48;
            r03.append("blur");
        L48:
            if (ThemePreviewActivity.m20784$$Nest$fgetisMotion(this.this$0) == true) goto L50;
        L53:
            boolean r2 = ThemePreviewActivity.m20772$$Nest$fgetcurrentWallpaper(this.this$0) instanceof TLRPC.TL_wallPaper;
            ThemePreviewActivity r4 = this.this$0;
            if (r2 == false) goto L60;
            String r22 = "https://" + MessagesController.getInstance(ThemePreviewActivity.access$1000(this.this$0)).linkPrefix + "/bg/" + ((TLRPC.TL_wallPaper) ThemePreviewActivity.m20772$$Nest$fgetcurrentWallpaper(r4)).slug;
            if (r03.length() <= 0) goto L58;
            r22 = r22 + "?mode=" + r03.toString();
        L58:
            String r42 = r22;
            this.this$0.showDialog(new AnonymousClass1(this, this.this$0.getParentActivity(), null, r42, false, r42, false));
            return;
        L60:
            if ((ThemePreviewActivity.m20772$$Nest$fgetcurrentWallpaper(r4) instanceof WallpapersListActivity.ColorWallpaper) == false) goto L68;
            if (ThemePreviewActivity.m20805$$Nest$fgetselectedPattern(this.this$0) == null) goto L65;
            String r04 = ThemePreviewActivity.m20805$$Nest$fgetselectedPattern(this.this$0).slug;
        L66:
            WallpapersListActivity.ColorWallpaper r43 = new WallpapersListActivity.ColorWallpaper(r04, ThemePreviewActivity.m20754$$Nest$fgetbackgroundColor(this.this$0), ThemePreviewActivity.m20755$$Nest$fgetbackgroundGradientColor1(this.this$0), ThemePreviewActivity.m20756$$Nest$fgetbackgroundGradientColor2(this.this$0), ThemePreviewActivity.m20757$$Nest$fgetbackgroundGradientColor3(this.this$0), ThemePreviewActivity.m20762$$Nest$fgetbackgroundRotation(this.this$0), ThemePreviewActivity.m20771$$Nest$fgetcurrentIntensity(this.this$0), ThemePreviewActivity.m20784$$Nest$fgetisMotion(this.this$0), null);
            r43.pattern = ThemePreviewActivity.m20805$$Nest$fgetselectedPattern(this.this$0);
            r22 = r43.getUrl();
            goto L58
        L65:
            r04 = "c";
            goto L66
        L68:
            if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L127;
            Theme.ThemeAccent r05 = Theme.getActiveTheme().getAccent(false);
            if (r05 == null) goto L128;
            WallpapersListActivity.ColorWallpaper r44 = new WallpapersListActivity.ColorWallpaper(r05.patternSlug, (int) r05.backgroundOverrideColor, (int) r05.backgroundGradientOverrideColor1, (int) r05.backgroundGradientOverrideColor2, (int) r05.backgroundGradientOverrideColor3, r05.backgroundRotation, r05.patternIntensity, r05.patternMotion, null);
            int r23 = ThemePreviewActivity.m20800$$Nest$fgetpatterns(this.this$0).size();
        L72:
            if (r3 >= r23) goto L79;
            TLRPC.TL_wallPaper r52 = (TLRPC.TL_wallPaper) ThemePreviewActivity.m20800$$Nest$fgetpatterns(this.this$0).get(r3);
            if (r52.pattern == false) goto L78;
            if (r05.patternSlug.equals(r52.slug) == false) goto L78;
            r44.pattern = r52;
        L78:
            r3 = r3 + 1;
        L79:
            r22 = r44.getUrl();
            goto L58
        L128:
            return;
        L127:
            return;
        L50:
            if (r03.length() <= 0) goto L52;
            r03.append("+");
        L52:
            r03.append("motion");
            goto L53
        L133:
            return;
        L83:
            if (r21 != 6) goto L114;
            if (SharedConfig.dayNightWallpaperSwitchHint > 3) goto L87;
            SharedConfig.dayNightWallpaperSwitchHint = 10;
            SharedConfig.increaseDayNightWallpaperSiwtchHint();
        L87:
            boolean r06 = this.this$0.onSwitchDayNightDelegate.isDark();
            DayNightSwitchDelegate r45 = this.this$0.onSwitchDayNightDelegate;
            if (r45 == null) goto L129;
            boolean r46 = r45.supportsAnimation();
            ThemePreviewActivity r53 = this.this$0;
            if (r46 == true) goto L93;
            r53.toggleTheme();
            return;
        L93:
            r53.onSwitchDayNightDelegate.switchDayNight(true);
            ThemePreviewActivity.m20809$$Nest$fgetsunDrawable(this.this$0).setPlayInDirectionOfCustomEndFrame(true);
            ThemePreviewActivity r47 = this.this$0;
            if (r06 == false) goto L96;
            ThemePreviewActivity.m20809$$Nest$fgetsunDrawable(r47).setCustomEndFrame(0);
        L97:
            ThemePreviewActivity.m20809$$Nest$fgetsunDrawable(this.this$0).start();
            if (ThemePreviewActivity.m20808$$Nest$fgetshouldShowBrightnessControll(this.this$0) == false) goto L130;
            DayNightSwitchDelegate r07 = this.this$0.onSwitchDayNightDelegate;
            float r48 = 0.0f;
            if (r07 != null) goto L102;
        L104:
            ThemePreviewActivity.m20776$$Nest$fgetdimmingSlider(this.this$0).animateValueTo(0.0f);
        L106:
            if (ThemePreviewActivity.m20766$$Nest$fgetchangeDayNightViewAnimator2(this.this$0) == null) goto L108;
            ThemePreviewActivity.m20766$$Nest$fgetchangeDayNightViewAnimator2(this.this$0).removeAllListeners();
            ThemePreviewActivity.m20766$$Nest$fgetchangeDayNightViewAnimator2(this.this$0).cancel();
        L108:
            ThemePreviewActivity r08 = this.this$0;
            float r54 = ThemePreviewActivity.m20801$$Nest$fgetprogressToDarkTheme(r08);
            if (this.this$0.onSwitchDayNightDelegate.isDark() == false) goto L111;
            r48 = 1.0f;
        L111:
            ThemePreviewActivity.m20818$$Nest$fputchangeDayNightViewAnimator2(r08, ValueAnimator.ofFloat(new float[]{r54, r48}));
            ThemePreviewActivity.m20766$$Nest$fgetchangeDayNightViewAnimator2(this.this$0).addUpdateListener(new ThemePreviewActivity$8$$ExternalSyntheticLambda0(this));
            ThemePreviewActivity.m20766$$Nest$fgetchangeDayNightViewAnimator2(this.this$0).addListener(new AnonymousClass2(this));
            ThemePreviewActivity.m20766$$Nest$fgetchangeDayNightViewAnimator2(this.this$0).setDuration(250);
            ThemePreviewActivity.m20766$$Nest$fgetchangeDayNightViewAnimator2(this.this$0).setInterpolator(CubicBezierInterpolator.DEFAULT);
            ThemePreviewActivity.m20766$$Nest$fgetchangeDayNightViewAnimator2(this.this$0).start();
            return;
        L102:
            if (r07.isDark() == false) goto L104;
            ThemePreviewActivity.m20776$$Nest$fgetdimmingSlider(this.this$0).setVisibility(0);
            ThemePreviewActivity.m20776$$Nest$fgetdimmingSlider(this.this$0).animateValueTo(ThemePreviewActivity.m20775$$Nest$fgetdimAmount(this.this$0));
            goto L106
        L130:
            return;
        L96:
            ThemePreviewActivity.m20809$$Nest$fgetsunDrawable(r47).setCustomEndFrame(36);
            goto L97
        L129:
            return;
        L114:
            if (r21 == 7) goto L116;
            return;
        L116:
            if ((ThemePreviewActivity.m20772$$Nest$fgetcurrentWallpaper(this.this$0) instanceof WallpapersListActivity.FileWallpaper) == false) goto L132;
            File r09 = ((WallpapersListActivity.FileWallpaper) ThemePreviewActivity.m20772$$Nest$fgetcurrentWallpaper(this.this$0)).originalPath;
            if (r09 == null) goto L134;
            MediaController.PhotoEntry r8 = new MediaController.PhotoEntry(0, 0, 0, r09.getAbsolutePath(), 0, false, 0, 0, 0);
            r8.isVideo = false;
            r8.thumbPath = null;
            ArrayList<Object> r10 = new ArrayList();
            r10.add(r8);
            PhotoViewer.getInstance().setParentActivity(this.this$0.getParentActivity());
            PhotoViewer.getInstance().openPhotoForSelect(r10, 0, 3, false, new AnonymousClass3(this, r8), null);
            return;
        L134:
            return;
        }
    }

    /* renamed from: org.telegram.ui.ThemePreviewActivity$9, reason: invalid class name */
    public class AnonymousClass9 extends ActionBarMenuItem {
        final /* synthetic */ ThemePreviewActivity this$0;

        public AnonymousClass9(ThemePreviewActivity r1, Context r2, ActionBarMenu r3, int r4, int r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem, android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
            super.onInitializeAccessibilityNodeInfo(r1);
            r1.setText(ThemePreviewActivity.m20779$$Nest$fgetdropDown(this.this$0).getText());
        }
    }

    public class BackgroundView extends BackupImageView {
        public Drawable background;
        boolean drawBackground;
        final /* synthetic */ ThemePreviewActivity this$0;
        public float tx;
        public float ty;

        public BackgroundView(ThemePreviewActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.drawBackground = true;
        }

        @Override // android.view.View
        public Drawable getBackground() {
            return this.background;
        }

        @Override // org.telegram.ui.Components.BackupImageView, android.view.View
        public void onDraw(Canvas r8) {
            this.tx = 0.0f;
            this.ty = 0.0f;
            if (this.drawBackground == false) goto L19;
            Drawable r1 = this.background;
            if ((r1 instanceof ColorDrawable) == false) goto L7;
        L17:
            r1.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            this.background.draw(r8);
            goto L19
        L7:
            if ((r1 instanceof GradientDrawable) == true) goto L17;
            if ((r1 instanceof MotionBackgroundDrawable) == true) goto L17;
            if ((r1 instanceof BitmapDrawable) == false) goto L19;
            if (((BitmapDrawable) r1).getTileModeX() != Shader.TileMode.REPEAT) goto L16;
            r8.save();
            float r12 = 2.0f / AndroidUtilities.density;
            r8.scale(r12, r12);
            this.background.setBounds(0, 0, (int) Math.ceil(getMeasuredWidth() / r12), (int) Math.ceil(getMeasuredHeight() / r12));
            this.background.draw(r8);
            r8.restore();
            goto L19
        L16:
            int r13 = getMeasuredHeight();
            float r2 = Math.max(getMeasuredWidth() / this.background.getIntrinsicWidth(), r13 / this.background.getIntrinsicHeight());
            int r3 = (int) Math.ceil((this.background.getIntrinsicWidth() * r2) * ThemePreviewActivity.m20797$$Nest$fgetparallaxScale(this.this$0));
            int r22 = (int) Math.ceil((this.background.getIntrinsicHeight() * r2) * ThemePreviewActivity.m20797$$Nest$fgetparallaxScale(this.this$0));
            int r4 = (getMeasuredWidth() - r3) / 2;
            int r14 = (r13 - r22) / 2;
            this.ty = r14;
            this.background.setBounds(r4, r14, r3 + r4, r22 + r14);
            this.background.draw(r8);
        L19:
            if (ThemePreviewActivity.m20782$$Nest$fgethasScrollingBackground(this.this$0) == true) goto L21;
            super.onDraw(r8);
        L33:
            if (ThemePreviewActivity.m20808$$Nest$fgetshouldShowBrightnessControll(this.this$0) == true) goto L35;
            return;
        L35:
            if (ThemePreviewActivity.m20775$$Nest$fgetdimAmount(this.this$0) <= 0.0f) goto L39;
            r8.drawColor(ColorUtils.setAlphaComponent(-16777216, (int) ((ThemePreviewActivity.m20775$$Nest$fgetdimAmount(this.this$0) * 255.0f) * ThemePreviewActivity.m20801$$Nest$fgetprogressToDarkTheme(this.this$0))));
            return;
        L39:
            return;
        L21:
            if (ThemePreviewActivity.m20804$$Nest$fgetscroller(this.this$0).isFinished() == false) goto L23;
        L30:
            r8.save();
            float r15 = this.this$0.currentScrollOffset;
            this.tx = -r15;
            r8.translate(-r15, 0.0f);
            super.onDraw(r8);
            r8.restore();
            goto L33
        L23:
            if (ThemePreviewActivity.m20804$$Nest$fgetscroller(this.this$0).computeScrollOffset() == false) goto L30;
            float r16 = ThemePreviewActivity.m20804$$Nest$fgetscroller(this.this$0).getStartX();
            ThemePreviewActivity r23 = this.this$0;
            if (r16 < r23.maxScrollOffset) goto L27;
        L29:
            ThemePreviewActivity.m20836$$Nest$minvalidateBlur(this.this$0);
            invalidate();
            goto L30
        L27:
            if (ThemePreviewActivity.m20804$$Nest$fgetscroller(r23).getStartX() <= 0) goto L29;
            this.this$0.currentScrollOffset = ThemePreviewActivity.m20804$$Nest$fgetscroller(r1).getCurrX();
            goto L29
        }

        @Override // android.view.View
        public void onMeasure(int r7, int r8) {
            super.onMeasure(r7, r8);
            ThemePreviewActivity r72 = this.this$0;
            ThemePreviewActivity.m20827$$Nest$fputparallaxScale(r72, ThemePreviewActivity.m20796$$Nest$fgetparallaxEffect(r72).getScale(getMeasuredWidth(), getMeasuredHeight()));
            if (ThemePreviewActivity.m20784$$Nest$fgetisMotion(this.this$0) == false) goto L5;
            setScaleX(ThemePreviewActivity.m20797$$Nest$fgetparallaxScale(this.this$0));
            setScaleY(ThemePreviewActivity.m20797$$Nest$fgetparallaxScale(this.this$0));
        L5:
            ThemePreviewActivity r73 = this.this$0;
            if (ThemePreviewActivity.m20803$$Nest$fgetscreenType(r73) == 2) goto L8;
        L10:
            boolean r82 = false;
        L11:
            ThemePreviewActivity.m20830$$Nest$fputprogressVisible(r73, r82);
            int r74 = getMeasuredWidth() + (getMeasuredHeight() << 16);
            if (ThemePreviewActivity.m20785$$Nest$fgetlastSizeHash(this.this$0) == r74) goto L21;
            ThemePreviewActivity.m20823$$Nest$fputhasScrollingBackground(this.this$0, false);
            if (ThemePreviewActivity.m20773$$Nest$fgetcurrentWallpaperBitmap(this.this$0) == null) goto L19;
            int r83 = (int) (ThemePreviewActivity.m20773$$Nest$fgetcurrentWallpaperBitmap(this.this$0).getWidth() * (getMeasuredHeight() / ThemePreviewActivity.m20773$$Nest$fgetcurrentWallpaperBitmap(this.this$0).getHeight()));
            if ((r83 - getMeasuredWidth()) <= 100) goto L19;
            ThemePreviewActivity.m20823$$Nest$fputhasScrollingBackground(this.this$0, true);
            this.this$0.croppedWidth = (int) (getMeasuredWidth() * (ThemePreviewActivity.m20773$$Nest$fgetcurrentWallpaperBitmap(this.this$0).getHeight() / getMeasuredHeight()));
            ThemePreviewActivity r0 = this.this$0;
            float r3 = (r83 - getMeasuredWidth()) / 2.0f;
            r0.currentScrollOffset = r3;
            r0.defaultScrollOffset = r3;
            ThemePreviewActivity r02 = this.this$0;
            r02.maxScrollOffset = r02.currentScrollOffset * 2.0f;
            setSize(r83, getMeasuredHeight());
            this.drawFromStart = true;
            ThemePreviewActivity.m20836$$Nest$minvalidateBlur(this.this$0);
        L19:
            if (ThemePreviewActivity.m20782$$Nest$fgethasScrollingBackground(this.this$0) == true) goto L21;
            setSize(-1, -1);
            this.drawFromStart = false;
        L21:
            ThemePreviewActivity.m20824$$Nest$fputlastSizeHash(this.this$0, r74);
            return;
        L8:
            if (getMeasuredWidth() > getMeasuredHeight()) goto L10;
            r82 = true;
            goto L11
        }

        @Override // android.view.View
        public void setBackground(Drawable r1) {
            this.background = r1;
            if (r1 == null) goto L6;
            r1.setCallback(this);
            return;
        }

        @Override // org.telegram.ui.Components.BackupImageView, android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (this.background != r2) goto L5;
            return true;
        L5:
            if (super.verifyDrawable(r2) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    public class BlurButton extends View {
        private final ColorFilter colorFilter;
        private final Paint dimPaint;
        private final Paint dimPaint2;
        private CircularProgressDrawable loadingDrawable;
        private float loadingT;
        private final Drawable rippleDrawable;
        private Text subtext;
        private boolean subtextShown;
        private AnimatedFloat subtextShownT;
        private Text text;
        final /* synthetic */ ThemePreviewActivity this$0;

        /* renamed from: -$$Nest$fgettext, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Text m20847$$Nest$fgettext(BlurButton r0) {
            return r0.text;
        }

        public BlurButton(ThemePreviewActivity r8, Context r9) {
            this.this$0 = r8;
            super(r9);
            this.subtextShownT = new AnimatedFloat(this, 0, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
            Drawable r7 = Theme.createRadSelectorDrawable(285212671, 8, 8);
            this.rippleDrawable = r7;
            this.dimPaint = new Paint(1);
            this.dimPaint2 = new Paint(1);
            this.loadingT = 0.0f;
            r7.setCallback(this);
            ColorMatrix r72 = new ColorMatrix();
            AndroidUtilities.adjustSaturationColorMatrix(r72, 0.35f);
            AndroidUtilities.multiplyBrightnessColorMatrix(r72, 0.9f);
            this.colorFilter = new ColorMatrixColorFilter(r72);
        }

        public CharSequence getText() {
            Text r0 = this.text;
            if (r0 != null) goto L5;
            return null;
        L5:
            return r0.getText();
        }

        @Override // android.view.View
        public void onDraw(Canvas r15) {
            float r1 = getHeight() / 2.0f;
            RectF r3 = AndroidUtilities.rectTmp;
            r3.set(0.0f, 0.0f, getWidth(), getHeight());
            Theme.applyServiceShaderMatrixForView(this, ThemePreviewActivity.m20758$$Nest$fgetbackgroundImage(this.this$0), this.this$0.themeDelegate);
            Paint r4 = this.this$0.themeDelegate.getPaint("paintChatActionBackground");
            ColorFilter r5 = r4.getColorFilter();
            r4.setColorFilter(this.colorFilter);
            r15.drawRoundRect(r3, r1, r1, r4);
            r4.setColorFilter(r5);
            if (ThemePreviewActivity.m20808$$Nest$fgetshouldShowBrightnessControll(this.this$0) == true) goto L5;
        L7:
            this.dimPaint.setColor(520093695);
            r15.drawRoundRect(r3, r1, r1, this.dimPaint);
            if (this.loadingT > 0.0f) goto L10;
        L13:
            float r11 = this.subtextShownT.set(this.subtextShown);
            if (this.loadingT >= 1.0f) goto L19;
            Text r12 = this.text;
            if (r12 == null) goto L19;
            r12.ellipsize(getWidth() - AndroidUtilities.dp(14.0f)).draw(r15, (getWidth() - this.text.getWidth()) / 2.0f, ((getHeight() / 2.0f) + (this.loadingT * AndroidUtilities.dp(24.0f))) - (AndroidUtilities.dp(7.0f) * r11), -1, 1.0f - this.loadingT);
        L19:
            if (this.loadingT < 1.0f) goto L21;
        L23:
            this.rippleDrawable.setBounds(0, 0, getWidth(), getHeight());
            this.rippleDrawable.draw(r15);
            return;
        L21:
            if (this.subtext == null) goto L23;
            r15.save();
            r15.scale(r11, r11, getWidth() / 2.0f, (getHeight() / 2.0f) + AndroidUtilities.dp(11.0f));
            this.subtext.ellipsize(getWidth() - AndroidUtilities.dp(14.0f)).draw(r15, (getWidth() - this.subtext.getWidth()) / 2.0f, AndroidUtilities.dp(11.0f) + ((getHeight() / 2.0f) + (this.loadingT * AndroidUtilities.dp(24.0f))), Theme.multAlpha(-1, 0.75f), 1.0f - this.loadingT);
            r15.restore();
            goto L23
        L10:
            if (this.loadingDrawable != null) goto L12;
            this.loadingDrawable = new CircularProgressDrawable(-1);
        L12:
            int r13 = (int) ((1.0f - this.loadingT) * AndroidUtilities.dp(-24.0f));
            this.loadingDrawable.setBounds(0, r13, getWidth(), getHeight() + r13);
            this.loadingDrawable.setAlpha((int) (this.loadingT * 255.0f));
            this.loadingDrawable.draw(r15);
            invalidate();
            goto L13
        L5:
            if (ThemePreviewActivity.m20775$$Nest$fgetdimAmount(this.this$0) <= 0.0f) goto L7;
            this.dimPaint2.setColor(ColorUtils.setAlphaComponent(-16777216, (int) ((ThemePreviewActivity.m20775$$Nest$fgetdimAmount(this.this$0) * 255.0f) * ThemePreviewActivity.m20801$$Nest$fgetprogressToDarkTheme(this.this$0))));
            r15.drawRoundRect(r3, r1, r1, this.dimPaint2);
            goto L7
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r6) {
            if (r6.getAction() != 0) goto L6;
            this.rippleDrawable.setHotspot(r6.getX(), r6.getY());
            this.rippleDrawable.setState(new int[]{android.R.attr.state_enabled, android.R.attr.state_pressed});
            boolean r0 = true;
        L12:
            if (super.onTouchEvent(r6) == true) goto L16;
            if (r0 == true) goto L16;
            return false;
        L16:
            return true;
        L6:
            if (r6.getAction() != 1) goto L8;
        L9:
            this.rippleDrawable.setState(StateSet.NOTHING);
        L10:
            r0 = false;
            goto L12
        L8:
            if (r6.getAction() != 3) goto L10;
            goto L9
        }

        public void setSubText(CharSequence r3, boolean r4) {
            if (r3 == null) goto L5;
            this.subtext = new Text(r3, 12.0f);
        L5:
            if (r3 == null) goto L7;
            boolean r32 = true;
        L8:
            this.subtextShown = r32;
            if (r4 == true) goto L11;
            this.subtextShownT.set(r32, true);
        L11:
            invalidate();
            return;
        L7:
            r32 = false;
            goto L8
        }

        public void setText(CharSequence r4) {
            this.text = new Text(r4, 14.0f, AndroidUtilities.bold());
        }

        @Override // android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (r2 != this.rippleDrawable) goto L5;
            return true;
        L5:
            if (super.verifyDrawable(r2) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    public interface DayNightSwitchDelegate {
        boolean isDark();

        boolean supportsAnimation();

        void switchDayNight(boolean r1);
    }

    public static class DialogsAdapter extends RecyclerListView.SelectionAdapter {
        private ArrayList<DialogCell.CustomDialog> dialogs;
        private Context mContext;

        public DialogsAdapter(Context r8) {
            this.mContext = r8;
            this.dialogs = new ArrayList();
            int r82 = (int) (System.currentTimeMillis() / 1000);
            DialogCell.CustomDialog r0 = new DialogCell.CustomDialog();
            r0.name = LocaleController.getString(R.string.ThemePreviewDialog1);
            r0.message = LocaleController.getString(R.string.ThemePreviewDialogMessage1);
            r0.id = 0;
            r0.unread_count = 0;
            r0.pinned = true;
            r0.muted = false;
            r0.type = 0;
            r0.date = r82;
            r0.verified = false;
            r0.isMedia = false;
            r0.sent = 2;
            this.dialogs.add(r0);
            DialogCell.CustomDialog r02 = new DialogCell.CustomDialog();
            r02.name = LocaleController.getString(R.string.ThemePreviewDialog2);
            r02.message = LocaleController.getString(R.string.ThemePreviewDialogMessage2);
            r02.id = 1;
            r02.unread_count = 2;
            r02.pinned = false;
            r02.muted = false;
            r02.type = 0;
            r02.date = r82 - 3600;
            r02.verified = false;
            r02.isMedia = false;
            r02.sent = -1;
            this.dialogs.add(r02);
            DialogCell.CustomDialog r03 = new DialogCell.CustomDialog();
            r03.name = LocaleController.getString(R.string.ThemePreviewDialog3);
            r03.message = LocaleController.getString(R.string.ThemePreviewDialogMessage3);
            r03.id = 2;
            r03.unread_count = 3;
            r03.pinned = false;
            r03.muted = true;
            r03.type = 0;
            r03.date = r82 - 7200;
            r03.verified = false;
            r03.isMedia = true;
            r03.sent = -1;
            this.dialogs.add(r03);
            DialogCell.CustomDialog r04 = new DialogCell.CustomDialog();
            r04.name = LocaleController.getString(R.string.ThemePreviewDialog4);
            r04.message = LocaleController.getString(R.string.ThemePreviewDialogMessage4);
            r04.id = 3;
            r04.unread_count = 0;
            r04.pinned = false;
            r04.muted = false;
            r04.type = 2;
            r04.date = r82 - 10800;
            r04.verified = false;
            r04.isMedia = false;
            r04.sent = -1;
            this.dialogs.add(r04);
            DialogCell.CustomDialog r05 = new DialogCell.CustomDialog();
            r05.name = LocaleController.getString(R.string.ThemePreviewDialog5);
            r05.message = LocaleController.getString(R.string.ThemePreviewDialogMessage5);
            r05.id = 4;
            r05.unread_count = 0;
            r05.pinned = false;
            r05.muted = false;
            r05.type = 1;
            r05.date = r82 - 14400;
            r05.verified = false;
            r05.isMedia = false;
            r05.sent = 2;
            this.dialogs.add(r05);
            DialogCell.CustomDialog r06 = new DialogCell.CustomDialog();
            r06.name = LocaleController.getString(R.string.ThemePreviewDialog6);
            r06.message = LocaleController.getString(R.string.ThemePreviewDialogMessage6);
            r06.id = 5;
            r06.unread_count = 0;
            r06.pinned = false;
            r06.muted = false;
            r06.type = 0;
            r06.date = r82 - 18000;
            r06.verified = false;
            r06.isMedia = false;
            r06.sent = -1;
            this.dialogs.add(r06);
            DialogCell.CustomDialog r07 = new DialogCell.CustomDialog();
            r07.name = LocaleController.getString(R.string.ThemePreviewDialog7);
            r07.message = LocaleController.getString(R.string.ThemePreviewDialogMessage7);
            r07.id = 6;
            r07.unread_count = 0;
            r07.pinned = false;
            r07.muted = false;
            r07.type = 0;
            r07.date = r82 - 21600;
            r07.verified = true;
            r07.isMedia = false;
            r07.sent = -1;
            this.dialogs.add(r07);
            DialogCell.CustomDialog r08 = new DialogCell.CustomDialog();
            r08.name = LocaleController.getString(R.string.ThemePreviewDialog8);
            r08.message = LocaleController.getString(R.string.ThemePreviewDialogMessage8);
            r08.id = 0;
            r08.unread_count = 0;
            r08.pinned = false;
            r08.muted = false;
            r08.type = 0;
            r08.date = r82 - 25200;
            r08.verified = true;
            r08.isMedia = false;
            r08.sent = -1;
            this.dialogs.add(r08);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.dialogs.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            if (r1 != this.dialogs.size()) goto L6;
            return 1;
        L6:
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() == 1) goto L5;
            return true;
        L5:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r3, int r4) {
            if (r3.getItemViewType() != 0) goto L10;
            DialogCell r32 = (DialogCell) r3.itemView;
            boolean r1 = true;
            if (r4 != (getItemCount() - 1)) goto L8;
            r1 = false;
        L8:
            r32.useSeparator = r1;
            r32.setDialog(this.dialogs.get(r4));
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            if (r3 != 0) goto L4;
            View r22 = new DialogCell(null, this.mContext, false, false);
        L5:
            r22.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r22);
        L4:
            r22 = new LoadingCell(this.mContext);
            goto L5
        }
    }

    public class MessageDrawable extends org.telegram.ui.ActionBar.MessageDrawable {
        final /* synthetic */ ThemePreviewActivity this$0;

        public MessageDrawable(ThemePreviewActivity r1, int r2, boolean r3, boolean r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.ActionBar.MessageDrawable
        public void setTop(int r2, int r3, int r4, int r5, int r6, int r7, boolean r8, boolean r9) {
            if (ThemePreviewActivity.m20806$$Nest$fgetsetupFinished(this.this$0) == false) goto L5;
            return;
        L5:
            super.setTop(r2, r3, r4, r5, r6, r7, r8, r9);
        }

        @Override // org.telegram.ui.ActionBar.MessageDrawable
        public void setTop(int r2, int r3, int r4, boolean r5, boolean r6) {
            if (ThemePreviewActivity.m20806$$Nest$fgetsetupFinished(this.this$0) == false) goto L5;
            return;
        L5:
            super.setTop(r2, r3, r4, r5, r6);
        }
    }

    public class MessagesAdapter extends RecyclerListView.SelectionAdapter {
        private Context mContext;
        private ArrayList<MessageObject> messages;
        private boolean showSecretMessages;
        final /* synthetic */ ThemePreviewActivity this$0;

        /* renamed from: org.telegram.ui.ThemePreviewActivity$MessagesAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends MessageObject {
            final /* synthetic */ MessagesAdapter this$1;
            final /* synthetic */ ThemePreviewActivity val$this$0;

            public AnonymousClass1(MessagesAdapter r1, int r2, TLRPC.Message r3, boolean r4, boolean r5, ThemePreviewActivity r6) {
                this.this$1 = r1;
                this.val$this$0 = r6;
                super(r2, r3, r4, r5);
            }

            @Override // org.telegram.messenger.MessageObject
            public boolean needDrawAvatar() {
                return false;
            }
        }

        /* renamed from: org.telegram.ui.ThemePreviewActivity$MessagesAdapter$2, reason: invalid class name */
        public class AnonymousClass2 extends MessageObject {
            final /* synthetic */ MessagesAdapter this$1;
            final /* synthetic */ ThemePreviewActivity val$this$0;

            public AnonymousClass2(MessagesAdapter r1, int r2, TLRPC.Message r3, boolean r4, boolean r5, ThemePreviewActivity r6) {
                this.this$1 = r1;
                this.val$this$0 = r6;
                super(r2, r3, r4, r5);
            }

            @Override // org.telegram.messenger.MessageObject
            public boolean needDrawAvatar() {
                return false;
            }
        }

        /* renamed from: org.telegram.ui.ThemePreviewActivity$MessagesAdapter$3, reason: invalid class name */
        public class AnonymousClass3 extends MessageObject {
            final /* synthetic */ MessagesAdapter this$1;
            final /* synthetic */ ThemePreviewActivity val$this$0;

            public AnonymousClass3(MessagesAdapter r1, int r2, TLRPC.Message r3, MessageObject r4, boolean r5, boolean r6, ThemePreviewActivity r7) {
                this.this$1 = r1;
                this.val$this$0 = r7;
                super(r2, r3, r4, r5, r6);
            }

            @Override // org.telegram.messenger.MessageObject
            public boolean needDrawAvatar() {
                return false;
            }
        }

        /* renamed from: org.telegram.ui.ThemePreviewActivity$MessagesAdapter$4, reason: invalid class name */
        public class AnonymousClass4 implements Theme.ResourcesProvider {
            final /* synthetic */ MessagesAdapter this$1;

            public AnonymousClass4(MessagesAdapter r1) {
                this.this$1 = r1;
            }

            @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
            public void applyServiceShaderMatrix(int r2, int r3, float r4, float r5) {
                ThemeDelegate r0 = this.this$1.this$0.themeDelegate;
                if (r0 == null) goto L6;
                r0.applyServiceShaderMatrix(r2, r3, r4, r5);
                return;
            L6:
                super.applyServiceShaderMatrix(r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
            public int getColor(int r1) {
                return this.this$1.this$0.themeDelegate.getColor(r1);
            }

            @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
            public int getColorOrDefault(int r1) {
                return this.this$1.this$0.themeDelegate.getColorOrDefault(r1);
            }

            @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
            public int getCurrentColor(int r1) {
                return this.this$1.this$0.themeDelegate.getCurrentColor(r1);
            }

            @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
            public Drawable getDrawable(String r2) {
                if (r2.equals("drawableMsgOut") == false) goto L7;
                return this.this$1.this$0.msgOutDrawable;
            L7:
                if (r2.equals("drawableMsgOutSelected") == false) goto L11;
                return this.this$1.this$0.msgOutDrawableSelected;
            L11:
                if (r2.equals("drawableMsgOutMedia") == true) goto L13;
                boolean r0 = r2.equals("drawableMsgOutMediaSelected");
                MessagesAdapter r1 = this.this$1;
                if (r0 == true) goto L17;
                ThemeDelegate r12 = r1.this$0.themeDelegate;
                if (r12 == null) goto L23;
                return r12.getDrawable(r2);
            L23:
                return Theme.getThemeDrawable(r2);
            L17:
                return r1.this$0.msgOutMediaDrawableSelected;
            L13:
                return this.this$1.this$0.msgOutMediaDrawable;
            }

            @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
            public Paint getPaint(String r1) {
                return this.this$1.this$0.themeDelegate.getPaint(r1);
            }

            @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
            public boolean hasGradientService() {
                return this.this$1.this$0.themeDelegate.hasGradientService();
            }

            @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
            public boolean isDark() {
                return this.this$1.this$0.themeDelegate.isDark();
            }
        }

        /* renamed from: org.telegram.ui.ThemePreviewActivity$MessagesAdapter$5, reason: invalid class name */
        public class AnonymousClass5 implements ChatMessageCell.ChatMessageCellDelegate {
            final /* synthetic */ MessagesAdapter this$1;

            public AnonymousClass5(MessagesAdapter r1) {
                this.this$1 = r1;
            }
        }

        /* renamed from: org.telegram.ui.ThemePreviewActivity$MessagesAdapter$6, reason: invalid class name */
        public class AnonymousClass6 implements ChatActionCell.ChatActionCellDelegate {
            final /* synthetic */ MessagesAdapter this$1;

            public AnonymousClass6(MessagesAdapter r1) {
                this.this$1 = r1;
            }
        }

        /* renamed from: org.telegram.ui.ThemePreviewActivity$MessagesAdapter$7, reason: invalid class name */
        public class AnonymousClass7 extends FrameLayout {
            final /* synthetic */ MessagesAdapter this$1;

            public AnonymousClass7(MessagesAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.widget.FrameLayout, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(60.0f), TLObject.FLAG_30));
            }
        }

        /* renamed from: org.telegram.ui.ThemePreviewActivity$MessagesAdapter$8, reason: invalid class name */
        public class AnonymousClass8 extends View {
            final /* synthetic */ MessagesAdapter this$1;

            public AnonymousClass8(MessagesAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(4.0f), TLObject.FLAG_30));
            }
        }

        /* renamed from: org.telegram.ui.ThemePreviewActivity$MessagesAdapter$9, reason: invalid class name */
        public class AnonymousClass9 extends FrameLayout {
            final /* synthetic */ MessagesAdapter this$1;

            public AnonymousClass9(MessagesAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.widget.FrameLayout, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(60.0f), TLObject.FLAG_30));
            }
        }

        /* renamed from: -$$Nest$fgetshowSecretMessages, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m20848$$Nest$fgetshowSecretMessages(MessagesAdapter r0) {
            return r0.showSecretMessages;
        }

        public MessagesAdapter(ThemePreviewActivity r22, Context r23) {
            this.this$0 = r22;
            if (ThemePreviewActivity.m20803$$Nest$fgetscreenType(r22) == 0) goto L5;
        L7:
            boolean r0 = false;
        L8:
            this.showSecretMessages = r0;
            this.mContext = r23;
            this.messages = new ArrayList();
            int r7 = (int) (System.currentTimeMillis() / 1000);
            int r02 = r7 - 3600;
            if (ThemePreviewActivity.m20803$$Nest$fgetscreenType(r22) != 2) goto L45;
            if (r22.dialogId < 0) goto L17;
            TLRPC.TL_message r3 = new TLRPC.TL_message();
            if ((ThemePreviewActivity.m20772$$Nest$fgetcurrentWallpaper(r22) instanceof WallpapersListActivity.ColorWallpaper) == false) goto L15;
            r3.message = LocaleController.getString(R.string.BackgroundColorSinglePreviewLine2);
        L16:
            r3.date = r7 - 3540;
            r3.dialog_id = 1;
            r3.flags = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED;
            r3.id = 1;
            r3.media = new TLRPC.TL_messageMediaEmpty();
            r3.out = true;
            TLRPC.TL_peerUser r03 = new TLRPC.TL_peerUser();
            r3.from_id = r03;
            r03.user_id = UserConfig.getInstance(ThemePreviewActivity.access$1300(r22)).getClientUserId();
            TLRPC.TL_peerUser r04 = new TLRPC.TL_peerUser();
            r3.peer_id = r04;
            r04.user_id = UserConfig.getInstance(ThemePreviewActivity.access$1400(r22)).getClientUserId();
            AnonymousClass1 r05 = new AnonymousClass1(this, ThemePreviewActivity.access$1500(r22), r3, true, false, r22);
            r05.eventId = 1;
            r05.resetLayout();
            this.messages.add(r05);
            goto L17
        L15:
            r3.message = LocaleController.getString(R.string.BackgroundPreviewLine2);
        L17:
            TLRPC.TL_message r06 = new TLRPC.TL_message();
            if (r22.dialogId >= 0) goto L20;
            TLRPC.Chat r2 = r22.getMessagesController().getChat(Long.valueOf(-r22.dialogId));
        L21:
            if (r2 == null) goto L23;
            r06.message = LocaleController.getString(R.string.ChannelBackgroundMessagePreview);
            TLRPC.TL_message r32 = new TLRPC.TL_message();
            r32.message = LocaleController.getString(R.string.ChannelBackgroundMessageReplyText);
            TLRPC.Chat r5 = r2;
            long r19 = 0;
            TLRPC.TL_message r15 = r06;
            AnonymousClass2 r07 = new AnonymousClass2(this, ThemePreviewActivity.access$1600(r22), r32, true, false, r22);
            TLRPC.TL_peerChannel r1 = new TLRPC.TL_peerChannel();
            r15.from_id = r1;
            r1.channel_id = r5.id;
            TLRPC.TL_peerChannel r12 = new TLRPC.TL_peerChannel();
            r15.peer_id = r12;
            r12.channel_id = r5.id;
            AnonymousClass2 r4 = r07;
        L31:
            int r11 = r7 - 3540;
            r15.date = r11;
            r15.dialog_id = 1;
            r15.flags = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CONNECTED;
            r15.id = 1;
            r15.media = new TLRPC.TL_messageMediaEmpty();
            r15.out = false;
            AnonymousClass3 r08 = new AnonymousClass3(this, ThemePreviewActivity.access$1800(r22), r15, r4, true, false, r22);
            if (r4 == null) goto L34;
            r08.customReplyName = LocaleController.getString(R.string.ChannelBackgroundMessageReplyName);
        L34:
            r08.eventId = 1;
            r08.resetLayout();
            this.messages.add(r08);
            if (r22.dialogId != r19) goto L37;
            return;
        L37:
            if (r22.serverWallpaper != null) goto L63;
            TLRPC.User r09 = r22.getMessagesController().getUser(Long.valueOf(r22.dialogId));
            TLRPC.TL_message r24 = new TLRPC.TL_message();
            r24.message = _UrlKt.FRAGMENT_ENCODE_SET;
            MessageObject r33 = new MessageObject(ThemePreviewActivity.access$1900(r22), r24, true, false);
            r33.eventId = 1;
            r33.contentType = 5;
            this.messages.add(r33);
            TLRPC.TL_message r25 = new TLRPC.TL_message();
            if (r09 == null) goto L41;
            r25.message = LocaleController.formatString(R.string.ChatBackgroundHint, new Object[]{UserObject.getFirstName(r09)});
        L42:
            r25.date = r11;
            r25.dialog_id = 1;
            r25.flags = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CONNECTED;
            r25.from_id = new TLRPC.TL_peerUser();
            r25.id = 1;
            r25.media = new TLRPC.TL_messageMediaEmpty();
            r25.out = false;
            TLRPC.TL_peerUser r010 = new TLRPC.TL_peerUser();
            r25.peer_id = r010;
            r010.user_id = UserConfig.getInstance(ThemePreviewActivity.access$2000(r22)).getClientUserId();
            MessageObject r011 = new MessageObject(ThemePreviewActivity.access$2100(r22), r25, true, false);
            r011.eventId = 1;
            r011.resetLayout();
            r011.contentType = 1;
            this.messages.add(r011);
            return;
        L41:
            r25.message = LocaleController.getString(R.string.ChannelBackgroundHint);
            goto L42
        L63:
            return;
        L23:
            r19 = 0;
            r15 = r06;
            if (r22.dialogId == 0) goto L27;
            r15.message = LocaleController.getString(R.string.BackgroundColorSinglePreviewLine3);
        L30:
            r15.from_id = new TLRPC.TL_peerUser();
            TLRPC.TL_peerUser r012 = new TLRPC.TL_peerUser();
            r15.peer_id = r012;
            r012.user_id = UserConfig.getInstance(ThemePreviewActivity.access$1700(r22)).getClientUserId();
            r4 = null;
            goto L31
        L27:
            if ((ThemePreviewActivity.m20772$$Nest$fgetcurrentWallpaper(r22) instanceof WallpapersListActivity.ColorWallpaper) == false) goto L29;
            r15.message = LocaleController.getString(R.string.BackgroundColorSinglePreviewLine1);
            goto L30
        L29:
            r15.message = LocaleController.getString(R.string.BackgroundPreviewLine1);
            goto L30
        L20:
            r2 = null;
            goto L21
        L45:
            if (ThemePreviewActivity.m20803$$Nest$fgetscreenType(r22) != 1) goto L57;
            TLRPC.TL_message r013 = new TLRPC.TL_message();
            TLRPC.TL_messageMediaDocument r26 = new TLRPC.TL_messageMediaDocument();
            r013.media = r26;
            r26.document = new TLRPC.TL_document();
            TLRPC.Document r27 = r013.media.document;
            r27.mime_type = "audio/mp3";
            r27.file_reference = new byte[0];
            r27.id = -2147483648L;
            r27.size = 2621440;
            r27.dc_id = Integer.MIN_VALUE;
            TLRPC.TL_documentAttributeFilename r28 = new TLRPC.TL_documentAttributeFilename();
            r28.file_name = LocaleController.getString(R.string.NewThemePreviewReply2) + ".mp3";
            r013.media.document.attributes.add(r28);
            int r29 = r7 + (-3540);
            r013.date = r29;
            r013.dialog_id = 1;
            r013.flags = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED;
            TLRPC.TL_peerUser r52 = new TLRPC.TL_peerUser();
            r013.from_id = r52;
            r52.user_id = UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId();
            r013.id = 1;
            r013.out = true;
            TLRPC.TL_peerUser r34 = new TLRPC.TL_peerUser();
            r013.peer_id = r34;
            r34.user_id = 0;
            MessageObject r42 = new MessageObject(UserConfig.selectedAccount, r013, true, false);
            if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L49;
            TLRPC.TL_message r014 = new TLRPC.TL_message();
            r014.message = "this is very very long text\nthis is very very long text\nthis is very very long text\nthis is very very long text\nthis is very very long text\nthis is very very long text\nthis is very very long text\nthis is very very long text\nthis is very very long text\nthis is very very long text\nthis is very very long text\nthis is very very long text\nthis is very very long text\nthis is very very long text\nthis is very very long text\nthis is very very long text";
            r014.date = r7 - 2640;
            r014.dialog_id = 1;
            r014.flags = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED;
            TLRPC.TL_peerUser r53 = new TLRPC.TL_peerUser();
            r014.from_id = r53;
            MessageObject r20 = r42;
            r53.user_id = UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId();
            r014.id = 1;
            r014.media = new TLRPC.TL_messageMediaEmpty();
            r014.out = true;
            TLRPC.TL_peerUser r35 = new TLRPC.TL_peerUser();
            r014.peer_id = r35;
            r35.user_id = 0;
            MessageObject r36 = new MessageObject(UserConfig.selectedAccount, r014, true, false);
            r36.resetLayout();
            r36.eventId = 1;
            this.messages.add(r36);
        L50:
            TLRPC.TL_message r015 = new TLRPC.TL_message();
            String r37 = LocaleController.getString(R.string.NewThemePreviewLine3);
            StringBuilder r43 = new StringBuilder(r37);
            int r6 = r37.indexOf(42);
            int r38 = r37.lastIndexOf(42);
            if (r6 == (-1)) goto L54;
            if (r38 == (-1)) goto L54;
            r43.replace(r38, r38 + 1, _UrlKt.FRAGMENT_ENCODE_SET);
            r43.replace(r6, r6 + 1, _UrlKt.FRAGMENT_ENCODE_SET);
            TLRPC.TL_messageEntityTextUrl r54 = new TLRPC.TL_messageEntityTextUrl();
            r54.offset = r6;
            r54.length = (r38 - r6) - 1;
            r54.url = "https://telegram.org";
            r015.entities.add(r54);
        L54:
            r015.message = r43.toString();
            r015.date = r7 - 2640;
            r015.dialog_id = 1;
            r015.flags = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED;
            TLRPC.TL_peerUser r39 = new TLRPC.TL_peerUser();
            r015.from_id = r39;
            r39.user_id = UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId();
            r015.id = 1;
            r015.media = new TLRPC.TL_messageMediaEmpty();
            r015.out = true;
            TLRPC.TL_peerUser r310 = new TLRPC.TL_peerUser();
            r015.peer_id = r310;
            r310.user_id = 0;
            MessageObject r311 = new MessageObject(UserConfig.selectedAccount, r015, true, false);
            r311.resetLayout();
            r311.eventId = 1;
            this.messages.add(r311);
            TLRPC.TL_message r016 = new TLRPC.TL_message();
            r016.message = LocaleController.getString(R.string.NewThemePreviewLine1);
            r016.date = r29;
            r016.dialog_id = 1;
            r016.flags = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CONNECTED;
            r016.from_id = new TLRPC.TL_peerUser();
            r016.id = 1;
            TLRPC.TL_messageReplyHeader r210 = new TLRPC.TL_messageReplyHeader();
            r016.reply_to = r210;
            r210.flags |= 16;
            r210.reply_to_msg_id = 5;
            r016.media = new TLRPC.TL_messageMediaEmpty();
            r016.out = false;
            TLRPC.TL_peerUser r211 = new TLRPC.TL_peerUser();
            r016.peer_id = r211;
            r211.user_id = UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId();
            MessageObject r212 = new MessageObject(UserConfig.selectedAccount, r016, true, false);
            r212.customReplyName = LocaleController.getString(R.string.NewThemePreviewName);
            r311.customReplyName = "Test User";
            r212.eventId = 1;
            r212.resetLayout();
            MessageObject r017 = r20;
            r212.replyMessageObject = r017;
            r311.replyMessageObject = r212;
            this.messages.add(r212);
            this.messages.add(r017);
            TLRPC.TL_message r018 = new TLRPC.TL_message();
            r018.date = r7 - 3480;
            r018.dialog_id = 1;
            r018.flags = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED;
            r018.out = false;
            r018.from_id = new TLRPC.TL_peerUser();
            r018.id = 1;
            TLRPC.TL_messageMediaDocument r213 = new TLRPC.TL_messageMediaDocument();
            r018.media = r213;
            r213.flags |= 3;
            r213.document = new TLRPC.TL_document();
            TLRPC.Document r214 = r018.media.document;
            r214.mime_type = "audio/ogg";
            r214.file_reference = new byte[0];
            TLRPC.TL_documentAttributeAudio r215 = new TLRPC.TL_documentAttributeAudio();
            r215.flags = 1028;
            r215.duration = 3.0d;
            r215.voice = true;
            r215.waveform = new byte[]{0, 4, 17, -50, -93, 86, -103, -45, -12, -26, 63, -25, -3, 109, -114, -54, -4, -1, -1, -1, -1, -29, -1, -1, -25, -1, -1, -97, -43, 57, -57, -108, 1, -91, -4, -47, 21, 99, 10, 97, 43, 45, 115, -112, -77, 51, -63, 66, 40, 34, -122, -116, 48, -124, 16, 66, -120, 16, 68, 16, 33, 4, 1};
            r018.media.document.attributes.add(r215);
            r018.out = true;
            TLRPC.TL_peerUser r216 = new TLRPC.TL_peerUser();
            r018.peer_id = r216;
            r216.user_id = 0;
            MessageObject r217 = new MessageObject(ThemePreviewActivity.access$2200(r22), r018, true, false);
            r217.audioProgressSec = 1;
            r217.audioProgress = 0.3f;
            r217.useCustomPhoto = true;
            this.messages.add(r217);
            return;
        L49:
            r20 = r42;
            goto L50
        L57:
            if (this.showSecretMessages == false) goto L60;
            TLRPC.TL_user r019 = new TLRPC.TL_user();
            r019.id = 2147483647L;
            r019.first_name = "Me";
            TLRPC.TL_user r218 = new TLRPC.TL_user();
            r218.id = 2147483646;
            r218.first_name = "Serj";
            ArrayList<TLRPC.User> r312 = new ArrayList();
            r312.add(r019);
            r312.add(r218);
            MessagesController.getInstance(ThemePreviewActivity.access$2300(r22)).putUsers(r312, true);
            TLRPC.TL_message r313 = new TLRPC.TL_message();
            r313.message = "Guess why Half-Life 3 was never released.";
            int r44 = r7 - 2640;
            r313.date = r44;
            r313.dialog_id = -1;
            r313.flags = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED;
            r313.id = 2147483646;
            r313.media = new TLRPC.TL_messageMediaEmpty();
            r313.out = false;
            TLRPC.TL_peerChat r10 = new TLRPC.TL_peerChat();
            r313.peer_id = r10;
            r10.chat_id = 1;
            TLRPC.TL_peerUser r102 = new TLRPC.TL_peerUser();
            r313.from_id = r102;
            r102.user_id = r218.id;
            this.messages.add(new MessageObject(ThemePreviewActivity.access$2400(r22), r313, true, false));
            TLRPC.TL_message r314 = new TLRPC.TL_message();
            r314.message = "No.\nAnd every unnecessary ping of the dev delays the release for 10 days.\nEvery request for ETA delays the release for 2 weeks.";
            r314.date = r44;
            r314.dialog_id = -1;
            r314.flags = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED;
            r314.id = 1;
            r314.media = new TLRPC.TL_messageMediaEmpty();
            r314.out = false;
            TLRPC.TL_peerChat r45 = new TLRPC.TL_peerChat();
            r314.peer_id = r45;
            r45.chat_id = 1;
            TLRPC.TL_peerUser r46 = new TLRPC.TL_peerUser();
            r314.from_id = r46;
            r46.user_id = r218.id;
            this.messages.add(new MessageObject(ThemePreviewActivity.access$2500(r22), r314, true, false));
            TLRPC.TL_message r219 = new TLRPC.TL_message();
            r219.message = "Is source code for Android coming anytime soon?";
            r219.date = r7 - 3000;
            r219.dialog_id = -1;
            r219.flags = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED;
            r219.id = 1;
            r219.media = new TLRPC.TL_messageMediaEmpty();
            r219.out = false;
            TLRPC.TL_peerChat r315 = new TLRPC.TL_peerChat();
            r219.peer_id = r315;
            r315.chat_id = 1;
            TLRPC.TL_peerUser r316 = new TLRPC.TL_peerUser();
            r219.from_id = r316;
            r316.user_id = r019.id;
            this.messages.add(new MessageObject(ThemePreviewActivity.access$2600(r22), r219, true, false));
            return;
        L60:
            TLRPC.TL_message r220 = new TLRPC.TL_message();
            r220.message = LocaleController.getString(R.string.ThemePreviewLine1);
            int r317 = r7 - 3540;
            r220.date = r317;
            r220.dialog_id = 1;
            r220.flags = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED;
            TLRPC.TL_peerUser r47 = new TLRPC.TL_peerUser();
            r220.from_id = r47;
            r47.user_id = UserConfig.getInstance(ThemePreviewActivity.access$2700(r22)).getClientUserId();
            r220.id = 1;
            r220.media = new TLRPC.TL_messageMediaEmpty();
            r220.out = true;
            TLRPC.TL_peerUser r48 = new TLRPC.TL_peerUser();
            r220.peer_id = r48;
            r48.user_id = 0;
            MessageObject r49 = new MessageObject(ThemePreviewActivity.access$2800(r22), r220, true, false);
            TLRPC.TL_message r221 = new TLRPC.TL_message();
            r221.message = LocaleController.getString(R.string.ThemePreviewLine2);
            r221.date = r7 - 2640;
            r221.dialog_id = 1;
            r221.flags = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED;
            TLRPC.TL_peerUser r55 = new TLRPC.TL_peerUser();
            r221.from_id = r55;
            r55.user_id = UserConfig.getInstance(ThemePreviewActivity.access$2900(r22)).getClientUserId();
            r221.id = 1;
            r221.media = new TLRPC.TL_messageMediaEmpty();
            r221.out = true;
            TLRPC.TL_peerUser r56 = new TLRPC.TL_peerUser();
            r221.peer_id = r56;
            r56.user_id = 0;
            this.messages.add(new MessageObject(ThemePreviewActivity.access$3000(r22), r221, true, false));
            TLRPC.TL_message r222 = new TLRPC.TL_message();
            r222.date = r7 - 3470;
            r222.dialog_id = 1;
            r222.flags = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED;
            r222.from_id = new TLRPC.TL_peerUser();
            r222.id = 5;
            TLRPC.TL_messageMediaDocument r57 = new TLRPC.TL_messageMediaDocument();
            r222.media = r57;
            r57.flags |= 3;
            r57.document = new TLRPC.TL_document();
            TLRPC.Document r58 = r222.media.document;
            r58.mime_type = "audio/mp4";
            r58.file_reference = new byte[0];
            TLRPC.TL_documentAttributeAudio r59 = new TLRPC.TL_documentAttributeAudio();
            r59.duration = 243.0d;
            r59.performer = LocaleController.getString(R.string.ThemePreviewSongPerformer);
            r59.title = LocaleController.getString(R.string.ThemePreviewSongTitle);
            r222.media.document.attributes.add(r59);
            r222.out = false;
            TLRPC.TL_peerUser r510 = new TLRPC.TL_peerUser();
            r222.peer_id = r510;
            r510.user_id = UserConfig.getInstance(ThemePreviewActivity.access$3100(r22)).getClientUserId();
            this.messages.add(new MessageObject(ThemePreviewActivity.access$3200(r22), r222, true, false));
            TLRPC.TL_message r223 = new TLRPC.TL_message();
            r223.message = LocaleController.getString(R.string.ThemePreviewLine3);
            r223.date = r317;
            r223.dialog_id = 1;
            r223.flags = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CONNECTED;
            r223.from_id = new TLRPC.TL_peerUser();
            r223.id = 1;
            TLRPC.TL_messageReplyHeader r318 = new TLRPC.TL_messageReplyHeader();
            r223.reply_to = r318;
            r318.flags |= 16;
            r318.reply_to_msg_id = 5;
            r223.media = new TLRPC.TL_messageMediaEmpty();
            r223.out = false;
            TLRPC.TL_peerUser r319 = new TLRPC.TL_peerUser();
            r223.peer_id = r319;
            r319.user_id = UserConfig.getInstance(ThemePreviewActivity.access$3300(r22)).getClientUserId();
            MessageObject r320 = new MessageObject(ThemePreviewActivity.access$3400(r22), r223, true, false);
            r320.customReplyName = LocaleController.getString(R.string.ThemePreviewLine3Reply);
            r320.replyMessageObject = r49;
            this.messages.add(r320);
            TLRPC.TL_message r224 = new TLRPC.TL_message();
            r224.date = r7 - 3480;
            r224.dialog_id = 1;
            r224.flags = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED;
            TLRPC.TL_peerUser r321 = new TLRPC.TL_peerUser();
            r224.from_id = r321;
            r321.user_id = UserConfig.getInstance(ThemePreviewActivity.access$3500(r22)).getClientUserId();
            r224.id = 1;
            TLRPC.TL_messageMediaDocument r322 = new TLRPC.TL_messageMediaDocument();
            r224.media = r322;
            r322.flags |= 3;
            r322.document = new TLRPC.TL_document();
            TLRPC.Document r323 = r224.media.document;
            r323.mime_type = "audio/ogg";
            r323.file_reference = new byte[0];
            TLRPC.TL_documentAttributeAudio r324 = new TLRPC.TL_documentAttributeAudio();
            r324.flags = 1028;
            r324.duration = 3.0d;
            r324.voice = true;
            r324.waveform = new byte[]{0, 4, 17, -50, -93, 86, -103, -45, -12, -26, 63, -25, -3, 109, -114, -54, -4, -1, -1, -1, -1, -29, -1, -1, -25, -1, -1, -97, -43, 57, -57, -108, 1, -91, -4, -47, 21, 99, 10, 97, 43, 45, 115, -112, -77, 51, -63, 66, 40, 34, -122, -116, 48, -124, 16, 66, -120, 16, 68, 16, 33, 4, 1};
            r224.media.document.attributes.add(r324);
            r224.out = true;
            TLRPC.TL_peerUser r325 = new TLRPC.TL_peerUser();
            r224.peer_id = r325;
            r325.user_id = 0;
            MessageObject r326 = new MessageObject(ThemePreviewActivity.access$3600(r22), r224, true, false);
            r326.audioProgressSec = 1;
            r326.audioProgress = 0.3f;
            r326.useCustomPhoto = true;
            this.messages.add(r326);
            this.messages.add(r49);
            TLRPC.TL_message r225 = new TLRPC.TL_message();
            r225.date = r7 - 3590;
            r225.dialog_id = 1;
            r225.flags = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_ADDED;
            r225.from_id = new TLRPC.TL_peerUser();
            r225.id = 1;
            TLRPC.TL_messageMediaPhoto r327 = new TLRPC.TL_messageMediaPhoto();
            r225.media = r327;
            r327.flags |= 3;
            r327.photo = new TLRPC.TL_photo();
            TLRPC.Photo r328 = r225.media.photo;
            r328.file_reference = new byte[0];
            r328.has_stickers = false;
            r328.id = 1;
            r328.access_hash = 0;
            r328.date = r02;
            TLRPC.TL_photoSize r020 = new TLRPC.TL_photoSize();
            r020.size = 0;
            r020.w = MediaError.DetailedErrorCode.SEGMENT_UNKNOWN;
            r020.h = 302;
            r020.type = "s";
            r020.location = new TLRPC.TL_fileLocationUnavailable();
            r225.media.photo.sizes.add(r020);
            r225.message = LocaleController.getString(R.string.ThemePreviewLine4);
            r225.out = false;
            TLRPC.TL_peerUser r021 = new TLRPC.TL_peerUser();
            r225.peer_id = r021;
            r021.user_id = UserConfig.getInstance(ThemePreviewActivity.access$3700(r22)).getClientUserId();
            MessageObject r022 = new MessageObject(ThemePreviewActivity.access$3800(r22), r225, true, false);
            r022.useCustomPhoto = true;
            this.messages.add(r022);
            return;
        L5:
            if (Utilities.random.nextInt(100) > 1) goto L7;
            r0 = true;
            goto L8
        }

        private boolean hasButtons() {
            if (ThemePreviewActivity.m20789$$Nest$fgetmessagesButtonsContainer(this.this$0) == null) goto L11;
            if (ThemePreviewActivity.m20803$$Nest$fgetscreenType(this.this$0) != 1) goto L11;
            if (ThemePreviewActivity.m20770$$Nest$fgetcolorType(this.this$0) != 3) goto L11;
            if (ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).myMessagesGradientAccentColor2 == 0) goto L11;
        L18:
            return true;
        L11:
            if (ThemePreviewActivity.m20752$$Nest$fgetbackgroundButtonsContainer(this.this$0) != null) goto L13;
            return false;
        L13:
            if (ThemePreviewActivity.m20803$$Nest$fgetscreenType(this.this$0) == 2) goto L18;
            if (ThemePreviewActivity.m20803$$Nest$fgetscreenType(this.this$0) == 1) goto L17;
            return false;
        L17:
            if (ThemePreviewActivity.m20770$$Nest$fgetcolorType(this.this$0) == 2) goto L18;
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            int r0 = this.messages.size();
            if (hasButtons() == true) goto L5;
            return r0;
        L5:
            return r0 + 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (hasButtons() == false) goto L11;
            if (r2 == 0) goto L6;
            r2 = r2 - 1;
            goto L11
        L6:
            if (ThemePreviewActivity.m20770$$Nest$fgetcolorType(this.this$0) != 3) goto L8;
            return 3;
        L8:
            return 2;
        L11:
            if (r2 >= 0) goto L13;
            return 4;
        L13:
            if (r2 < this.messages.size()) goto L15;
            return 4;
        L15:
            return this.messages.get(r2).contentType;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r11, int r12) {
            int r0 = r11.getItemViewType();
            if (r0 != 2) goto L5;
            return;
        L5:
            if (r0 != 3) goto L7;
            return;
        L7:
            if (hasButtons() == false) goto L9;
            r12 = r12 - 1;
        L9:
            MessageObject r2 = this.messages.get(r12);
            View r02 = r11.itemView;
            if ((r02 instanceof ChatMessageCell) == false) goto L43;
            ChatMessageCell r1 = (ChatMessageCell) r02;
            boolean r03 = false;
            r1.isChat = false;
            int r3 = r12 - 1;
            int r4 = getItemViewType(r3);
            int r122 = r12 + 1;
            int r6 = getItemViewType(r122);
            if ((r2.messageOwner.reply_markup instanceof TLRPC.TL_replyInlineMarkup) == false) goto L14;
        L22:
            boolean r42 = false;
        L24:
            if (r6 == r11.getItemViewType()) goto L26;
        L34:
            boolean r112 = false;
        L36:
            if (this.showSecretMessages == false) goto L38;
        L39:
            r03 = true;
        L40:
            r1.isChat = r03;
            r1.setFullyDraw(true);
            r1.setMessageObject(r2, null, r42, r112, false);
            return;
        L38:
            if (this.this$0.dialogId >= 0) goto L40;
        L26:
            if (r122 >= this.messages.size()) goto L34;
            MessageObject r113 = this.messages.get(r122);
            if ((r113.messageOwner.reply_markup instanceof TLRPC.TL_replyInlineMarkup) == true) goto L34;
            if (r113.isOutOwner() != r2.isOutOwner()) goto L34;
            if (Math.abs(r113.messageOwner.date - r2.messageOwner.date) > 300) goto L34;
            r112 = true;
            goto L36
        L14:
            if (r4 != r11.getItemViewType()) goto L22;
            MessageObject r32 = this.messages.get(r3);
            if (r32.isOutOwner() == r2.isOutOwner()) goto L18;
        L20:
            boolean r33 = false;
        L21:
            r42 = r33;
            goto L24
        L18:
            if (Math.abs(r32.messageOwner.date - r2.messageOwner.date) > 300) goto L20;
            r33 = true;
            goto L21
        L43:
            if ((r02 instanceof ChatActionCell) == false) goto L48;
            ChatActionCell r04 = (ChatActionCell) r02;
            r04.setMessageObject(r2);
            r04.setAlpha(1.0f);
            ThemePreviewActivity.m20836$$Nest$minvalidateBlur(this.this$0);
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r7, int r8) {
            if (r8 != 0) goto L6;
            ChatMessageCell r0 = new ChatMessageCell(this.mContext, ThemePreviewActivity.access$3900(this.this$0), false, null, new AnonymousClass4(this));
            r0.setDelegate(new AnonymousClass5(this));
            View r02 = r0;
        L21:
            r02.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r02);
        L6:
            if (r8 != 1) goto L9;
            ChatActionCell r03 = new ChatActionCell(this.mContext, false, this.this$0.themeDelegate);
            r03.setDelegate(new AnonymousClass6(this));
            r02 = r03;
            goto L21
        L9:
            if (r8 != 2) goto L15;
            if (ThemePreviewActivity.m20752$$Nest$fgetbackgroundButtonsContainer(this.this$0).getParent() == null) goto L13;
            ((ViewGroup) ThemePreviewActivity.m20752$$Nest$fgetbackgroundButtonsContainer(this.this$0).getParent()).removeView(ThemePreviewActivity.m20752$$Nest$fgetbackgroundButtonsContainer(this.this$0));
        L13:
            AnonymousClass7 r04 = new AnonymousClass7(this, this.mContext);
            r04.addView(ThemePreviewActivity.m20752$$Nest$fgetbackgroundButtonsContainer(this.this$0), LayoutHelper.createFrame(-1, 76, 17));
            r02 = r04;
            goto L21
        L15:
            if (r8 != 5) goto L18;
            r02 = new AnonymousClass8(this, this.this$0.getContext());
            goto L21
        L18:
            if (ThemePreviewActivity.m20789$$Nest$fgetmessagesButtonsContainer(this.this$0).getParent() == null) goto L20;
            ((ViewGroup) ThemePreviewActivity.m20789$$Nest$fgetmessagesButtonsContainer(this.this$0).getParent()).removeView(ThemePreviewActivity.m20789$$Nest$fgetmessagesButtonsContainer(this.this$0));
        L20:
            AnonymousClass9 r05 = new AnonymousClass9(this, this.mContext);
            r05.addView(ThemePreviewActivity.m20789$$Nest$fgetmessagesButtonsContainer(this.this$0), LayoutHelper.createFrame(-1, 76, 17));
            r02 = r05;
            goto L21
        }
    }

    public class PatternsAdapter extends RecyclerListView.SelectionAdapter {
        private Context mContext;
        final /* synthetic */ ThemePreviewActivity this$0;

        /* renamed from: org.telegram.ui.ThemePreviewActivity$PatternsAdapter$1, reason: invalid class name */
        public class AnonymousClass1 implements PatternCell.PatternCellDelegate {
            final /* synthetic */ PatternsAdapter this$1;

            public AnonymousClass1(PatternsAdapter r1) {
                this.this$1 = r1;
            }

            @Override // org.telegram.ui.Cells.PatternCell.PatternCellDelegate
            public int getBackgroundColor() {
                if (ThemePreviewActivity.m20803$$Nest$fgetscreenType(this.this$1.this$0) == 2) goto L5;
                int r0 = Theme.getDefaultAccentColor(Theme.key_chat_wallpaper);
                int r3 = (int) ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$1.this$0).backgroundOverrideColor;
                if (r3 == 0) goto L9;
                return r3;
            L9:
                return r0;
            L5:
                return ThemePreviewActivity.m20754$$Nest$fgetbackgroundColor(this.this$1.this$0);
            }

            @Override // org.telegram.ui.Cells.PatternCell.PatternCellDelegate
            public int getBackgroundGradientAngle() {
                int r0 = ThemePreviewActivity.m20803$$Nest$fgetscreenType(this.this$1.this$0);
                PatternsAdapter r2 = this.this$1;
                if (r0 != 2) goto L7;
                return ThemePreviewActivity.m20762$$Nest$fgetbackgroundRotation(r2.this$0);
            L7:
                return ThemePreviewActivity.m20749$$Nest$fgetaccent(r2.this$0).backgroundRotation;
            }

            @Override // org.telegram.ui.Cells.PatternCell.PatternCellDelegate
            public int getBackgroundGradientColor1() {
                if (ThemePreviewActivity.m20803$$Nest$fgetscreenType(this.this$1.this$0) == 2) goto L5;
                int r0 = Theme.getDefaultAccentColor(Theme.key_chat_wallpaper_gradient_to1);
                int r3 = (int) ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$1.this$0).backgroundGradientOverrideColor1;
                if (r3 == 0) goto L9;
                return r3;
            L9:
                return r0;
            L5:
                return ThemePreviewActivity.m20755$$Nest$fgetbackgroundGradientColor1(this.this$1.this$0);
            }

            @Override // org.telegram.ui.Cells.PatternCell.PatternCellDelegate
            public int getBackgroundGradientColor2() {
                if (ThemePreviewActivity.m20803$$Nest$fgetscreenType(this.this$1.this$0) == 2) goto L5;
                int r0 = Theme.getDefaultAccentColor(Theme.key_chat_wallpaper_gradient_to2);
                int r3 = (int) ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$1.this$0).backgroundGradientOverrideColor2;
                if (r3 == 0) goto L9;
                return r3;
            L9:
                return r0;
            L5:
                return ThemePreviewActivity.m20756$$Nest$fgetbackgroundGradientColor2(this.this$1.this$0);
            }

            @Override // org.telegram.ui.Cells.PatternCell.PatternCellDelegate
            public int getBackgroundGradientColor3() {
                if (ThemePreviewActivity.m20803$$Nest$fgetscreenType(this.this$1.this$0) == 2) goto L5;
                int r0 = Theme.getDefaultAccentColor(Theme.key_chat_wallpaper_gradient_to3);
                int r3 = (int) ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$1.this$0).backgroundGradientOverrideColor3;
                if (r3 == 0) goto L9;
                return r3;
            L9:
                return r0;
            L5:
                return ThemePreviewActivity.m20757$$Nest$fgetbackgroundGradientColor3(this.this$1.this$0);
            }

            @Override // org.telegram.ui.Cells.PatternCell.PatternCellDelegate
            public int getCheckColor() {
                return ThemePreviewActivity.m20768$$Nest$fgetcheckColor(this.this$1.this$0);
            }

            @Override // org.telegram.ui.Cells.PatternCell.PatternCellDelegate
            public float getIntensity() {
                return ThemePreviewActivity.m20771$$Nest$fgetcurrentIntensity(this.this$1.this$0);
            }

            @Override // org.telegram.ui.Cells.PatternCell.PatternCellDelegate
            public int getPatternColor() {
                return ThemePreviewActivity.m20798$$Nest$fgetpatternColor(this.this$1.this$0);
            }

            @Override // org.telegram.ui.Cells.PatternCell.PatternCellDelegate
            public TLRPC.TL_wallPaper getSelectedPattern() {
                return ThemePreviewActivity.m20805$$Nest$fgetselectedPattern(this.this$1.this$0);
            }
        }

        public PatternsAdapter(ThemePreviewActivity r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            if (ThemePreviewActivity.m20800$$Nest$fgetpatterns(this.this$0) != null) goto L5;
            return 0;
        L5:
            return ThemePreviewActivity.m20800$$Nest$fgetpatterns(this.this$0).size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r7, int r8) {
            PatternCell r72 = (PatternCell) r7.itemView;
            r72.setPattern((TLRPC.TL_wallPaper) ThemePreviewActivity.m20800$$Nest$fgetpatterns(this.this$0).get(r8));
            r72.getImageReceiver().setColorFilter(new PorterDuffColorFilter(ThemePreviewActivity.m20798$$Nest$fgetpatternColor(this.this$0), ThemePreviewActivity.m20763$$Nest$fgetblendMode(this.this$0)));
            if (Build.VERSION.SDK_INT < 29) goto L24;
            int r1 = 0;
            if (ThemePreviewActivity.m20803$$Nest$fgetscreenType(this.this$0) != 1) goto L15;
            int r82 = Theme.getDefaultAccentColor(Theme.key_chat_wallpaper_gradient_to2);
            int r0 = (int) ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).backgroundGradientOverrideColor2;
            if (r0 == 0) goto L9;
        L11:
            if (r0 == 0) goto L13;
            r1 = r0;
        L17:
            if (r1 != 0) goto L19;
        L22:
            r72.getImageReceiver().setBlendMode(null);
            return;
        L19:
            if (ThemePreviewActivity.m20771$$Nest$fgetcurrentIntensity(this.this$0) < 0.0f) goto L22;
            ThemePreviewActivity.m20758$$Nest$fgetbackgroundImage(this.this$0).getImageReceiver().setBlendMode(PatternCell$$ExternalSyntheticApiModelOutline0.m());
            return;
        L13:
            r1 = r82;
            goto L17
        L9:
            if (ThemePreviewActivity.m20749$$Nest$fgetaccent(this.this$0).backgroundGradientOverrideColor2 == 0) goto L11;
        L15:
            if ((ThemePreviewActivity.m20772$$Nest$fgetcurrentWallpaper(this.this$0) instanceof WallpapersListActivity.ColorWallpaper) == false) goto L17;
            r1 = ThemePreviewActivity.m20756$$Nest$fgetbackgroundGradientColor2(this.this$0);
            goto L17
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            return new RecyclerListView.Holder(new PatternCell(this.mContext, ThemePreviewActivity.m20788$$Nest$fgetmaxWallpaperSize(this.this$0), new AnonymousClass1(this)));
        }
    }

    public class ThemeDelegate implements Theme.ResourcesProvider {
        public final Paint chat_actionBackgroundGradientDarkenPaint;
        public final Paint chat_actionBackgroundPaint;
        public final Paint chat_actionBackgroundSelectedPaint;
        public final TextPaint chat_actionTextPaint;
        public final TextPaint chat_actionTextPaint2;
        public final TextPaint chat_botButtonPaint;
        private final SparseIntArray currentColors;
        public Theme.ResourcesProvider parentProvider;
        private Bitmap serviceBitmap;
        private Matrix serviceBitmapMatrix;
        public BitmapShader serviceBitmapShader;
        final /* synthetic */ ThemePreviewActivity this$0;

        /* renamed from: -$$Nest$fgetserviceBitmap, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Bitmap m20849$$Nest$fgetserviceBitmap(ThemeDelegate r0) {
            return r0.serviceBitmap;
        }

        public ThemeDelegate(ThemePreviewActivity r5) {
            this.this$0 = r5;
            this.currentColors = new SparseIntArray();
            this.chat_actionBackgroundPaint = new Paint(3);
            this.chat_actionBackgroundSelectedPaint = new Paint(3);
            Paint r52 = new Paint(3);
            this.chat_actionBackgroundGradientDarkenPaint = r52;
            TextPaint r0 = new TextPaint();
            this.chat_actionTextPaint = r0;
            TextPaint r1 = new TextPaint();
            this.chat_actionTextPaint2 = r1;
            TextPaint r2 = new TextPaint();
            this.chat_botButtonPaint = r2;
            r0.setTextSize(AndroidUtilities.dp(Math.max(16, SharedConfig.fontSize) - 2));
            r1.setTextSize(AndroidUtilities.dp(Math.max(16, SharedConfig.fontSize) - 2));
            r2.setTextSize(AndroidUtilities.dp(15.0f));
            r2.setTypeface(AndroidUtilities.bold());
            r52.setColor(352321536);
        }

        public void applyChatServiceMessageColor() {
            applyChatServiceMessageColor(null, null, null, null);
        }

        public void applyChatServiceMessageColor(int[] r7, Drawable r8, Drawable r9, Float r10) {
            int r72 = Theme.key_chat_serviceBackground;
            int r0 = getColor(r72);
            int r1 = getColor(Theme.key_chat_serviceBackgroundSelected);
            if (r8 != null) goto L6;
            r8 = r9;
        L6:
            boolean r92 = r8 instanceof MotionBackgroundDrawable;
            if (r92 == true) goto L11;
            if ((r8 instanceof BitmapDrawable) == true) goto L11;
        L26:
            this.serviceBitmap = null;
            this.serviceBitmapShader = null;
            TextPaint r3 = this.chat_actionTextPaint;
            int r4 = Theme.key_chat_serviceText;
            r3.setColor(getColor(r4));
            this.chat_actionTextPaint2.setColor(getColor(r4));
            this.chat_actionTextPaint.linkColor = getColor(Theme.key_chat_serviceLink);
        L27:
            this.chat_actionBackgroundPaint.setColor(r0);
            this.chat_actionBackgroundSelectedPaint.setColor(r1);
            if (this.serviceBitmapShader != null) goto L30;
        L74:
            this.chat_actionBackgroundPaint.setColorFilter(null);
            this.chat_actionBackgroundPaint.setShader(null);
            this.chat_actionBackgroundSelectedPaint.setColorFilter(null);
            this.chat_actionBackgroundSelectedPaint.setShader(null);
            return;
        L30:
            if (this.currentColors.indexOfKey(r72) < 0) goto L34;
            if (r92 == true) goto L34;
            if ((r8 instanceof BitmapDrawable) == false) goto L74;
        L34:
            ColorMatrix r73 = new ColorMatrix();
            if (r92 == false) goto L54;
            float r5 = -0.06f;
            if (((MotionBackgroundDrawable) r8).getIntensity() < 0.0f) goto L46;
            r73.setSaturation(1.6f);
            if (isDark() == false) goto L41;
            float r42 = 0.97f;
        L42:
            AndroidUtilities.multiplyBrightnessColorMatrix(r73, r42);
            if (isDark() == false) goto L45;
            r5 = 0.12f;
        L45:
            AndroidUtilities.adjustBrightnessColorMatrix(r73, r5);
        L63:
            if (r92 == false) goto L71;
            float r82 = ((MotionBackgroundDrawable) r8).getIntensity();
            if (r10 == null) goto L68;
            r82 = r10.floatValue();
        L68:
            if (r82 < 0.0f) goto L70;
            r73.setSaturation(1.8f);
            AndroidUtilities.multiplyBrightnessColorMatrix(r73, 0.97f);
            AndroidUtilities.adjustBrightnessColorMatrix(r73, 0.03f);
        L72:
            this.chat_actionBackgroundPaint.setShader(this.serviceBitmapShader);
            this.chat_actionBackgroundPaint.setColorFilter(new ColorMatrixColorFilter(r73));
            this.chat_actionBackgroundPaint.setAlpha(255);
            this.chat_actionBackgroundSelectedPaint.setShader(this.serviceBitmapShader);
            ColorMatrix r83 = new ColorMatrix(r73);
            AndroidUtilities.multiplyBrightnessColorMatrix(r83, 0.85f);
            this.chat_actionBackgroundSelectedPaint.setColorFilter(new ColorMatrixColorFilter(r83));
            this.chat_actionBackgroundSelectedPaint.setAlpha(255);
            return;
        L70:
            r73.setSaturation(0.5f);
            AndroidUtilities.multiplyBrightnessColorMatrix(r73, 0.35f);
            AndroidUtilities.adjustBrightnessColorMatrix(r73, 0.03f);
            goto L72
        L71:
            r73.setSaturation(1.6f);
            AndroidUtilities.multiplyBrightnessColorMatrix(r73, 0.97f);
            AndroidUtilities.adjustBrightnessColorMatrix(r73, 0.06f);
            goto L72
        L41:
            r42 = 0.92f;
            goto L42
        L46:
            r73.setSaturation(1.1f);
            if (isDark() == false) goto L49;
            float r43 = 0.4f;
        L50:
            AndroidUtilities.multiplyBrightnessColorMatrix(r73, r43);
            if (isDark() == false) goto L53;
            r5 = 0.08f;
        L53:
            AndroidUtilities.adjustBrightnessColorMatrix(r73, r5);
            goto L63
        L49:
            r43 = 0.8f;
            goto L50
        L54:
            r73.setSaturation(1.6f);
            if (isDark() == false) goto L57;
            float r44 = 0.9f;
        L58:
            AndroidUtilities.multiplyBrightnessColorMatrix(r73, r44);
            if (isDark() == false) goto L61;
            float r45 = -0.04f;
        L62:
            AndroidUtilities.adjustBrightnessColorMatrix(r73, r45);
            goto L63
        L61:
            r45 = 0.06f;
            goto L62
        L57:
            r44 = 0.84f;
        L11:
            if (SharedConfig.getDevicePerformanceClass() == 0) goto L26;
            if (LiteMode.isEnabled(32) == false) goto L26;
            if (r92 == false) goto L17;
            Bitmap r32 = ((MotionBackgroundDrawable) r8).getBitmap();
        L21:
            if (this.serviceBitmap == r32) goto L25;
            this.serviceBitmap = r32;
            Bitmap r46 = this.serviceBitmap;
            Shader.TileMode r52 = Shader.TileMode.CLAMP;
            this.serviceBitmapShader = new BitmapShader(r46, r52, r52);
            if (this.serviceBitmapMatrix != null) goto L25;
            this.serviceBitmapMatrix = new Matrix();
        L25:
            this.chat_actionTextPaint.setColor(-1);
            this.chat_actionTextPaint2.setColor(-1);
            this.chat_actionTextPaint.linkColor = -1;
            this.chat_botButtonPaint.setColor(-1);
            goto L27
        L17:
            if ((r8 instanceof BitmapDrawable) == false) goto L19;
            r32 = ((BitmapDrawable) r8).getBitmap();
            goto L21
        L19:
            r32 = null;
            goto L21
        }

        @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
        public void applyServiceShaderMatrix(int r8, int r9, float r10, float r11) {
            Bitmap r0 = this.serviceBitmap;
            if (r0 == null) goto L9;
            BitmapShader r1 = this.serviceBitmapShader;
            if (r1 == null) goto L9;
            Theme.applyServiceShaderMatrix(r0, r1, this.serviceBitmapMatrix, r8, r9, r10, r11);
            return;
        L9:
            super.applyServiceShaderMatrix(r8, r9, r10, r11);
        }

        @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
        public int getColor(int r1) {
            Theme.ResourcesProvider r0 = this.parentProvider;
            if (r0 == null) goto L7;
            return r0.getColor(r1);
        L7:
            return Theme.getColor(r1);
        }

        @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
        public int getCurrentColor(int r2) {
            Theme.ResourcesProvider r0 = this.parentProvider;
            if (r0 == null) goto L7;
            return r0.getCurrentColor(r2);
        L7:
            return super.getCurrentColor(r2);
        }

        @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
        public Drawable getDrawable(String r1) {
            Theme.ResourcesProvider r0 = this.parentProvider;
            if (r0 == null) goto L7;
            return r0.getDrawable(r1);
        L7:
            return Theme.getThemeDrawable(r1);
        }

        @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
        public Paint getPaint(String r3) {
            r3.getClass();
            char r1 = 65535;
            switch(r3.hashCode()) {
                case -1490966183: goto L26;
                case 561929466: goto L22;
                case 1712385955: goto L18;
                case 1790254137: goto L14;
                case 1897339317: goto L10;
                case 2030114297: goto L6;
                default: goto L29;
            };
        L29:
            switch(r1) {
                case 0: goto L47;
                case 1: goto L45;
                case 2: goto L43;
                case 3: goto L41;
                case 4: goto L39;
                case 5: goto L37;
                default: goto L30;
            };
        L30:
            Theme.ResourcesProvider r0 = this.parentProvider;
            if (r0 == null) goto L35;
            return r0.getPaint(r3);
        L35:
            return super.getPaint(r3);
        L37:
            return this.chat_actionTextPaint;
        L39:
            return this.chat_actionBackgroundSelectedPaint;
        L41:
            return this.chat_actionBackgroundGradientDarkenPaint;
        L43:
            return this.chat_botButtonPaint;
        L45:
            return this.chat_actionBackgroundPaint;
        L47:
            return this.chat_actionTextPaint2;
        L6:
            if (r3.equals("paintChatActionText") == false) goto L29;
            r1 = 5;
            goto L29
        L10:
            if (r3.equals("paintChatActionBackgroundSelected") == false) goto L29;
            r1 = 4;
            goto L29
        L14:
            if (r3.equals("paintChatActionBackgroundDarken") == false) goto L29;
            r1 = 3;
            goto L29
        L18:
            if (r3.equals("paintChatBotButton") == false) goto L29;
            r1 = 2;
            goto L29
        L22:
            if (r3.equals("paintChatActionBackground") == false) goto L29;
            r1 = 1;
            goto L29
        L26:
            if (r3.equals("paintChatActionText2") == false) goto L29;
            r1 = 0;
            goto L29
        }

        @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
        public boolean hasGradientService() {
            Theme.ResourcesProvider r0 = this.parentProvider;
            if (r0 == null) goto L7;
            return r0.hasGradientService();
        L7:
            return Theme.hasGradientService();
        }

        @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
        public boolean isDark() {
            DayNightSwitchDelegate r0 = this.this$0.onSwitchDayNightDelegate;
            if (r0 != null) goto L5;
            Theme.ResourcesProvider r1 = this.parentProvider;
            if (r1 == null) goto L11;
            return r1.isDark();
        L11:
            return Theme.isCurrentThemeDark();
        L5:
            return r0.isDark();
        }
    }

    public interface WallpaperActivityDelegate {
        void didSetNewBackground(TLRPC.WallPaper r1);
    }

    /* renamed from: $r8$lambda$-ACOyVZG5VBFwoHksbwD6lqFjks, reason: not valid java name */
    public static /* synthetic */ void m20733$r8$lambda$ACOyVZG5VBFwoHksbwD6lqFjks(ThemePreviewActivity r0) {
        r0.lambda$createView$16();
    }

    public static /* synthetic */ void $r8$lambda$1m_hOflpM6xj9C82btMnIOrCxE4() {
        Theme.setChangingWallpaper(false);
    }

    /* renamed from: $r8$lambda$2i4CDrimH8j2VLE_CYXrB74-oqk, reason: not valid java name */
    public static /* synthetic */ void m20734$r8$lambda$2i4CDrimH8j2VLE_CYXrB74oqk() {
    }

    /* renamed from: $r8$lambda$5zUOmjimJ-uEM8A4lDAuDF5K2g0, reason: not valid java name */
    public static /* synthetic */ void m20735$r8$lambda$5zUOmjimJuEM8A4lDAuDF5K2g0(ThemePreviewActivity r0) {
        r0.lambda$getThemeDescriptionsInternal$33();
    }

    public static /* synthetic */ void $r8$lambda$6v2qeXkA9uQMHaheGToXZeLTfTQ(View r0, int r1) {
    }

    public static /* synthetic */ void $r8$lambda$9CndiGLgcOvWV7DdVMXjDL7vHjs(ThemePreviewActivity r0, View r1) {
        r0.lambda$createView$18(r1);
    }

    /* renamed from: $r8$lambda$9oNssPFQdXy4HI-ptqzqSbAXKDQ, reason: not valid java name */
    public static /* synthetic */ void m20736$r8$lambda$9oNssPFQdXy4HIptqzqSbAXKDQ(ThemePreviewActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$didReceivedNotification$31(r1, r2);
    }

    /* renamed from: $r8$lambda$FfNA8j-dRe3pd1UtJbh_Lpm0SUg, reason: not valid java name */
    public static /* synthetic */ void m20737$r8$lambda$FfNA8jdRe3pd1UtJbh_Lpm0SUg(ThemePreviewActivity r0, AlertDialog r1, int r2) {
        r0.lambda$selectColorType$23(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Jrf7LQFeLCtTQdOhgZoPCwZlMro(ThemePreviewActivity r0, Float r1) {
        r0.lambda$createView$10(r1);
    }

    public static /* synthetic */ void $r8$lambda$LgDxrugw2sqJx2mD_HCjD9n6P6s(ThemePreviewActivity r0) {
        r0.lambda$applyWallpaperBackground$19();
    }

    /* renamed from: $r8$lambda$ODDu1PTfy_r-BhBdxRUxKgunw8w, reason: not valid java name */
    public static /* synthetic */ void m20738$r8$lambda$ODDu1PTfy_rBhBdxRUxKgunw8w(ThemePreviewActivity r0, ImageReceiver r1, boolean r2, boolean r3, boolean r4) {
        r0.lambda$createView$4(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$PmqsLJc9hN4w7gkzzONT-LOWyh4, reason: not valid java name */
    public static /* synthetic */ void m20739$r8$lambda$PmqsLJc9hN4w7gkzzONTLOWyh4(ThemePreviewActivity r0, AlertDialog r1, int r2) {
        r0.lambda$checkDiscard$26(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$TAm2aut7nPW9IVCqpfQjcSG1v6Y(ThemePreviewActivity r0, AlertDialog r1, int r2) {
        r0.lambda$checkDiscard$25(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$UYOdEjRe3UKn1rqeQAWtxUUelZ0(ThemePreviewActivity r0, TLObject r1) {
        r0.lambda$didReceivedNotification$28(r1);
    }

    public static /* synthetic */ void $r8$lambda$WF1uNa_7OLo1e5NvSsnqsMOIIZc(ThemePreviewActivity r0, View r1) {
        r0.lambda$createView$8(r1);
    }

    /* renamed from: $r8$lambda$X-gbsCHsFjgX5pAYfQ00x2VccHc, reason: not valid java name */
    public static /* synthetic */ void m20740$r8$lambda$XgbsCHsFjgX5pAYfQ00x2VccHc(ThemePreviewActivity r0, TLObject r1) {
        r0.lambda$didReceivedNotification$30(r1);
    }

    public static /* synthetic */ void $r8$lambda$ZNGVRbvgteQKu7rfIUs0bjYqMmw(ThemePreviewActivity r0, ValueAnimator r1) {
        r0.lambda$toggleTheme$35(r1);
    }

    /* renamed from: $r8$lambda$cQ49yO9Z-7N7ffo_32cCQVCTV2w, reason: not valid java name */
    public static /* synthetic */ void m20741$r8$lambda$cQ49yO9Z7N7ffo_32cCQVCTV2w(ThemePreviewActivity r0, AlertDialog r1, int r2) {
        r0.lambda$selectColorType$24(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$eN6Wd6WQ1U5d0IVM84xqy70wnzY(ThemePreviewActivity r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$eQGz67kK8cYfmoAeDs7lxNfQXEY(ThemePreviewActivity r0, int r1, View r2) {
        r0.lambda$createView$14(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$hWvzzZDZdQ_opVzV_U26jqflIRc(ThemePreviewActivity r0) {
        r0.lambda$createView$2();
    }

    public static /* synthetic */ boolean $r8$lambda$hgI32JY_2MnOr_EA_PQkThVFdaQ(View r0, MotionEvent r1) {
        return true;
    }

    /* renamed from: $r8$lambda$ipjh1OxkT-m5VTu3mDHERg4aQwI, reason: not valid java name */
    public static /* synthetic */ void m20742$r8$lambda$ipjh1OxkTm5VTu3mDHERg4aQwI(ThemePreviewActivity r0, View r1) {
        r0.lambda$createView$9(r1);
    }

    /* renamed from: $r8$lambda$jMt-z685LVQSGMj78lfnltpm4U0, reason: not valid java name */
    public static /* synthetic */ void m20743$r8$lambda$jMtz685LVQSGMj78lfnltpm4U0(ThemePreviewActivity r0, int r1, WallpaperCheckBoxView r2, View r3) {
        r0.lambda$createView$11(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$jibPTDi3SEZ34inSfdMR1Dqj8h4(ThemePreviewActivity r0, int r1, int r2, float r3) {
        r0.lambda$createView$7(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$luSZKSVvLRvS3jUDF3GT6_Iri6Q(ThemePreviewActivity r0, int r1, WallpaperCheckBoxView r2, View r3) {
        r0.lambda$createView$12(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$oM48hqlOpSsZNO_E8dfxAyhq-_o, reason: not valid java name */
    public static /* synthetic */ void m20744$r8$lambda$oM48hqlOpSsZNO_E8dfxAyhq_o(ThemePreviewActivity r0, SharedPreferences r1) {
        r0.lambda$showAnimationHint$32(r1);
    }

    public static /* synthetic */ void $r8$lambda$oZ8BcyFicI7Yd8L0S0PO1jn8l5w(ThemePreviewActivity r0, int r1, View r2) {
        r0.lambda$createView$13(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$qT8hRfoRDjeYAAdOvMIoTPnqIFA(ThemePreviewActivity r0, ChannelBoostsController.CanApplyBoost r1) {
        r0.lambda$applyWallpaperBackground$20(r1);
    }

    /* renamed from: $r8$lambda$rr_HemYuKl8aZQk-SnWOrxAFpkY, reason: not valid java name */
    public static /* synthetic */ void m20745$r8$lambda$rr_HemYuKl8aZQkSnWOrxAFpkY(ThemePreviewActivity r0, View r1, int r2) {
        r0.lambda$createView$15(r1, r2);
    }

    /* renamed from: $r8$lambda$siNqULYLOmvwVrnWfQilT7G-DpE, reason: not valid java name */
    public static /* synthetic */ void m20746$r8$lambda$siNqULYLOmvwVrnWfQilT7GDpE(ThemePreviewActivity r0, View r1) {
        r0.lambda$createView$17(r1);
    }

    public static /* synthetic */ void $r8$lambda$t9SJqUYZpErMTbgPzlJzCuHQT7I(ThemePreviewActivity r0, View r1) {
        r0.lambda$createView$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$u2ELSTr2vuX3i0bbdl0bbHGIs9g(ThemePreviewActivity r0, View r1, int r2, float r3, float r4) {
        r0.lambda$createView$6(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$vpn-MyeM1gNMXWrB4jArJyBvHbs, reason: not valid java name */
    public static /* synthetic */ void m20747$r8$lambda$vpnMyeM1gNMXWrB4jArJyBvHbs(ThemePreviewActivity r0, AlertDialog r1, int r2) {
        r0.lambda$selectColorType$22(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$wHypfTDQ2kSM8AeOgGTi_z5_Kuw(ThemePreviewActivity r0, TL_stories.TL_premium_boostsStatus r1) {
        r0.lambda$checkBoostsLevel$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$x0DYndGU7O8YISkRUzZFVi9kxM8(ThemePreviewActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$didReceivedNotification$29(r1, r2);
    }

    /* renamed from: $r8$lambda$ydmop0is-YANtOIiEqv73aKH6I8, reason: not valid java name */
    public static /* synthetic */ void m20748$r8$lambda$ydmop0isYANtOIiEqv73aKH6I8(ThemePreviewActivity r0) {
        r0.lambda$toggleTheme$36();
    }

    /* renamed from: -$$Nest$fgetaccent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ThemeAccent m20749$$Nest$fgetaccent(ThemePreviewActivity r0) {
        return r0.accent;
    }

    /* renamed from: -$$Nest$fgetactionBar2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBar m20750$$Nest$fgetactionBar2(ThemePreviewActivity r0) {
        return r0.actionBar2;
    }

    /* renamed from: -$$Nest$fgetapplyingTheme, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ThemeInfo m20751$$Nest$fgetapplyingTheme(ThemePreviewActivity r0) {
        return r0.applyingTheme;
    }

    /* renamed from: -$$Nest$fgetbackgroundButtonsContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m20752$$Nest$fgetbackgroundButtonsContainer(ThemePreviewActivity r0) {
        return r0.backgroundButtonsContainer;
    }

    /* renamed from: -$$Nest$fgetbackgroundCheckBoxView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WallpaperCheckBoxView[] m20753$$Nest$fgetbackgroundCheckBoxView(ThemePreviewActivity r0) {
        return r0.backgroundCheckBoxView;
    }

    /* renamed from: -$$Nest$fgetbackgroundColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20754$$Nest$fgetbackgroundColor(ThemePreviewActivity r0) {
        return r0.backgroundColor;
    }

    /* renamed from: -$$Nest$fgetbackgroundGradientColor1, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20755$$Nest$fgetbackgroundGradientColor1(ThemePreviewActivity r0) {
        return r0.backgroundGradientColor1;
    }

    /* renamed from: -$$Nest$fgetbackgroundGradientColor2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20756$$Nest$fgetbackgroundGradientColor2(ThemePreviewActivity r0) {
        return r0.backgroundGradientColor2;
    }

    /* renamed from: -$$Nest$fgetbackgroundGradientColor3, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20757$$Nest$fgetbackgroundGradientColor3(ThemePreviewActivity r0) {
        return r0.backgroundGradientColor3;
    }

    /* renamed from: -$$Nest$fgetbackgroundImage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BackgroundView m20758$$Nest$fgetbackgroundImage(ThemePreviewActivity r0) {
        return r0.backgroundImage;
    }

    /* renamed from: -$$Nest$fgetbackgroundImages, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BackgroundView[] m20759$$Nest$fgetbackgroundImages(ThemePreviewActivity r0) {
        return r0.backgroundImages;
    }

    /* renamed from: -$$Nest$fgetbackgroundPlayAnimationImageView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m20760$$Nest$fgetbackgroundPlayAnimationImageView(ThemePreviewActivity r0) {
        return r0.backgroundPlayAnimationImageView;
    }

    /* renamed from: -$$Nest$fgetbackgroundPlayAnimationView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m20761$$Nest$fgetbackgroundPlayAnimationView(ThemePreviewActivity r0) {
        return r0.backgroundPlayAnimationView;
    }

    /* renamed from: -$$Nest$fgetbackgroundRotation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20762$$Nest$fgetbackgroundRotation(ThemePreviewActivity r0) {
        return r0.backgroundRotation;
    }

    /* renamed from: -$$Nest$fgetblendMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PorterDuff.Mode m20763$$Nest$fgetblendMode(ThemePreviewActivity r0) {
        return r0.blendMode;
    }

    /* renamed from: -$$Nest$fgetbottomOverlayChat, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m20764$$Nest$fgetbottomOverlayChat(ThemePreviewActivity r0) {
        return r0.bottomOverlayChat;
    }

    /* renamed from: -$$Nest$fgetchangeDayNightView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m20765$$Nest$fgetchangeDayNightView(ThemePreviewActivity r0) {
        return r0.changeDayNightView;
    }

    /* renamed from: -$$Nest$fgetchangeDayNightViewAnimator2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m20766$$Nest$fgetchangeDayNightViewAnimator2(ThemePreviewActivity r0) {
        return r0.changeDayNightViewAnimator2;
    }

    /* renamed from: -$$Nest$fgetchangeDayNightViewProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m20767$$Nest$fgetchangeDayNightViewProgress(ThemePreviewActivity r0) {
        return r0.changeDayNightViewProgress;
    }

    /* renamed from: -$$Nest$fgetcheckColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20768$$Nest$fgetcheckColor(ThemePreviewActivity r0) {
        return r0.checkColor;
    }

    /* renamed from: -$$Nest$fgetcolorPicker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ColorPicker m20769$$Nest$fgetcolorPicker(ThemePreviewActivity r0) {
        return r0.colorPicker;
    }

    /* renamed from: -$$Nest$fgetcolorType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20770$$Nest$fgetcolorType(ThemePreviewActivity r0) {
        return r0.colorType;
    }

    /* renamed from: -$$Nest$fgetcurrentIntensity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m20771$$Nest$fgetcurrentIntensity(ThemePreviewActivity r0) {
        return r0.currentIntensity;
    }

    /* renamed from: -$$Nest$fgetcurrentWallpaper, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Object m20772$$Nest$fgetcurrentWallpaper(ThemePreviewActivity r0) {
        return r0.currentWallpaper;
    }

    /* renamed from: -$$Nest$fgetcurrentWallpaperBitmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Bitmap m20773$$Nest$fgetcurrentWallpaperBitmap(ThemePreviewActivity r0) {
        return r0.currentWallpaperBitmap;
    }

    /* renamed from: -$$Nest$fgetdayNightItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m20774$$Nest$fgetdayNightItem(ThemePreviewActivity r0) {
        return r0.dayNightItem;
    }

    /* renamed from: -$$Nest$fgetdimAmount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m20775$$Nest$fgetdimAmount(ThemePreviewActivity r0) {
        return r0.dimAmount;
    }

    /* renamed from: -$$Nest$fgetdimmingSlider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SliderView m20776$$Nest$fgetdimmingSlider(ThemePreviewActivity r0) {
        return r0.dimmingSlider;
    }

    /* renamed from: -$$Nest$fgetdimmingSliderContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m20777$$Nest$fgetdimmingSliderContainer(ThemePreviewActivity r0) {
        return r0.dimmingSliderContainer;
    }

    /* renamed from: -$$Nest$fgetdotsContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m20778$$Nest$fgetdotsContainer(ThemePreviewActivity r0) {
        return r0.dotsContainer;
    }

    /* renamed from: -$$Nest$fgetdropDown, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m20779$$Nest$fgetdropDown(ThemePreviewActivity r0) {
        return r0.dropDown;
    }

    /* renamed from: -$$Nest$fgetdropDownContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m20780$$Nest$fgetdropDownContainer(ThemePreviewActivity r0) {
        return r0.dropDownContainer;
    }

    /* renamed from: -$$Nest$fgetfloatingButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FragmentFloatingButton m20781$$Nest$fgetfloatingButton(ThemePreviewActivity r0) {
        return r0.floatingButton;
    }

    /* renamed from: -$$Nest$fgethasScrollingBackground, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20782$$Nest$fgethasScrollingBackground(ThemePreviewActivity r0) {
        return r0.hasScrollingBackground;
    }

    /* renamed from: -$$Nest$fgetisBlurred, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20783$$Nest$fgetisBlurred(ThemePreviewActivity r0) {
        return r0.isBlurred;
    }

    /* renamed from: -$$Nest$fgetisMotion, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20784$$Nest$fgetisMotion(ThemePreviewActivity r0) {
        return r0.isMotion;
    }

    /* renamed from: -$$Nest$fgetlastSizeHash, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20785$$Nest$fgetlastSizeHash(ThemePreviewActivity r0) {
        return r0.lastSizeHash;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m20786$$Nest$fgetlistView(ThemePreviewActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetlistView2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m20787$$Nest$fgetlistView2(ThemePreviewActivity r0) {
        return r0.listView2;
    }

    /* renamed from: -$$Nest$fgetmaxWallpaperSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20788$$Nest$fgetmaxWallpaperSize(ThemePreviewActivity r0) {
        return r0.maxWallpaperSize;
    }

    /* renamed from: -$$Nest$fgetmessagesButtonsContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m20789$$Nest$fgetmessagesButtonsContainer(ThemePreviewActivity r0) {
        return r0.messagesButtonsContainer;
    }

    /* renamed from: -$$Nest$fgetmessagesCheckBoxView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WallpaperCheckBoxView[] m20790$$Nest$fgetmessagesCheckBoxView(ThemePreviewActivity r0) {
        return r0.messagesCheckBoxView;
    }

    /* renamed from: -$$Nest$fgetmessagesPlayAnimationImageView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m20791$$Nest$fgetmessagesPlayAnimationImageView(ThemePreviewActivity r0) {
        return r0.messagesPlayAnimationImageView;
    }

    /* renamed from: -$$Nest$fgetmessagesPlayAnimationView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m20792$$Nest$fgetmessagesPlayAnimationView(ThemePreviewActivity r0) {
        return r0.messagesPlayAnimationView;
    }

    /* renamed from: -$$Nest$fgetnightTheme, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20793$$Nest$fgetnightTheme(ThemePreviewActivity r0) {
        return r0.nightTheme;
    }

    /* renamed from: -$$Nest$fgetpage1, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m20794$$Nest$fgetpage1(ThemePreviewActivity r0) {
        return r0.page1;
    }

    /* renamed from: -$$Nest$fgetpage2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m20795$$Nest$fgetpage2(ThemePreviewActivity r0) {
        return r0.page2;
    }

    /* renamed from: -$$Nest$fgetparallaxEffect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WallpaperParallaxEffect m20796$$Nest$fgetparallaxEffect(ThemePreviewActivity r0) {
        return r0.parallaxEffect;
    }

    /* renamed from: -$$Nest$fgetparallaxScale, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m20797$$Nest$fgetparallaxScale(ThemePreviewActivity r0) {
        return r0.parallaxScale;
    }

    /* renamed from: -$$Nest$fgetpatternColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20798$$Nest$fgetpatternColor(ThemePreviewActivity r0) {
        return r0.patternColor;
    }

    /* renamed from: -$$Nest$fgetpatternLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout[] m20799$$Nest$fgetpatternLayout(ThemePreviewActivity r0) {
        return r0.patternLayout;
    }

    /* renamed from: -$$Nest$fgetpatterns, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m20800$$Nest$fgetpatterns(ThemePreviewActivity r0) {
        return r0.patterns;
    }

    /* renamed from: -$$Nest$fgetprogressToDarkTheme, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m20801$$Nest$fgetprogressToDarkTheme(ThemePreviewActivity r0) {
        return r0.progressToDarkTheme;
    }

    /* renamed from: -$$Nest$fgetremoveBackgroundOverride, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20802$$Nest$fgetremoveBackgroundOverride(ThemePreviewActivity r0) {
        return r0.removeBackgroundOverride;
    }

    /* renamed from: -$$Nest$fgetscreenType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20803$$Nest$fgetscreenType(ThemePreviewActivity r0) {
        return r0.screenType;
    }

    /* renamed from: -$$Nest$fgetscroller, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Scroller m20804$$Nest$fgetscroller(ThemePreviewActivity r0) {
        return r0.scroller;
    }

    /* renamed from: -$$Nest$fgetselectedPattern, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.TL_wallPaper m20805$$Nest$fgetselectedPattern(ThemePreviewActivity r0) {
        return r0.selectedPattern;
    }

    /* renamed from: -$$Nest$fgetsetupFinished, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20806$$Nest$fgetsetupFinished(ThemePreviewActivity r0) {
        return r0.setupFinished;
    }

    /* renamed from: -$$Nest$fgetsheetDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m20807$$Nest$fgetsheetDrawable(ThemePreviewActivity r0) {
        return r0.sheetDrawable;
    }

    /* renamed from: -$$Nest$fgetshouldShowBrightnessControll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20808$$Nest$fgetshouldShowBrightnessControll(ThemePreviewActivity r0) {
        return r0.shouldShowBrightnessControll;
    }

    /* renamed from: -$$Nest$fgetsunDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RLottieDrawable m20809$$Nest$fgetsunDrawable(ThemePreviewActivity r0) {
        return r0.sunDrawable;
    }

    /* renamed from: -$$Nest$fgetundoView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UndoView m20810$$Nest$fgetundoView(ThemePreviewActivity r0) {
        return r0.undoView;
    }

    /* renamed from: -$$Nest$fgetviewPager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewPager m20811$$Nest$fgetviewPager(ThemePreviewActivity r0) {
        return r0.viewPager;
    }

    /* renamed from: -$$Nest$fgetwasScroll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20812$$Nest$fgetwasScroll(ThemePreviewActivity r0) {
        return r0.wasScroll;
    }

    /* renamed from: -$$Nest$fgetwatchForKeyboardEndTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m20813$$Nest$fgetwatchForKeyboardEndTime(ThemePreviewActivity r2) {
        return r2.watchForKeyboardEndTime;
    }

    /* renamed from: -$$Nest$fputbackgroundPlayViewAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20814$$Nest$fputbackgroundPlayViewAnimator(ThemePreviewActivity r0, AnimatorSet r1) {
        r0.backgroundPlayViewAnimator = r1;
    }

    /* renamed from: -$$Nest$fputblurredBitmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20815$$Nest$fputblurredBitmap(ThemePreviewActivity r0, Bitmap r1) {
        r0.blurredBitmap = r1;
    }

    /* renamed from: -$$Nest$fputchangeDayNightView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20816$$Nest$fputchangeDayNightView(ThemePreviewActivity r0, View r1) {
        r0.changeDayNightView = r1;
    }

    /* renamed from: -$$Nest$fputchangeDayNightViewAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20817$$Nest$fputchangeDayNightViewAnimator(ThemePreviewActivity r0, ValueAnimator r1) {
        r0.changeDayNightViewAnimator = r1;
    }

    /* renamed from: -$$Nest$fputchangeDayNightViewAnimator2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20818$$Nest$fputchangeDayNightViewAnimator2(ThemePreviewActivity r0, ValueAnimator r1) {
        r0.changeDayNightViewAnimator2 = r1;
    }

    /* renamed from: -$$Nest$fputchangeDayNightViewProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20819$$Nest$fputchangeDayNightViewProgress(ThemePreviewActivity r0, float r1) {
        r0.changeDayNightViewProgress = r1;
    }

    /* renamed from: -$$Nest$fputcurrentIntensity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20820$$Nest$fputcurrentIntensity(ThemePreviewActivity r0, float r1) {
        r0.currentIntensity = r1;
    }

    /* renamed from: -$$Nest$fputcurrentWallpaper, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20821$$Nest$fputcurrentWallpaper(ThemePreviewActivity r0, Object r1) {
        r0.currentWallpaper = r1;
    }

    /* renamed from: -$$Nest$fputcurrentWallpaperBitmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20822$$Nest$fputcurrentWallpaperBitmap(ThemePreviewActivity r0, Bitmap r1) {
        r0.currentWallpaperBitmap = r1;
    }

    /* renamed from: -$$Nest$fputhasScrollingBackground, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20823$$Nest$fputhasScrollingBackground(ThemePreviewActivity r0, boolean r1) {
        r0.hasScrollingBackground = r1;
    }

    /* renamed from: -$$Nest$fputlastSizeHash, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20824$$Nest$fputlastSizeHash(ThemePreviewActivity r0, int r1) {
        r0.lastSizeHash = r1;
    }

    /* renamed from: -$$Nest$fputmessagesPlayViewAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20825$$Nest$fputmessagesPlayViewAnimator(ThemePreviewActivity r0, AnimatorSet r1) {
        r0.messagesPlayViewAnimator = r1;
    }

    /* renamed from: -$$Nest$fputmotionAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20826$$Nest$fputmotionAnimation(ThemePreviewActivity r0, AnimatorSet r1) {
        r0.motionAnimation = r1;
    }

    /* renamed from: -$$Nest$fputparallaxScale, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20827$$Nest$fputparallaxScale(ThemePreviewActivity r0, float r1) {
        r0.parallaxScale = r1;
    }

    /* renamed from: -$$Nest$fputpatternViewAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20828$$Nest$fputpatternViewAnimation(ThemePreviewActivity r0, AnimatorSet r1) {
        r0.patternViewAnimation = r1;
    }

    /* renamed from: -$$Nest$fputprogressToDarkTheme, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20829$$Nest$fputprogressToDarkTheme(ThemePreviewActivity r0, float r1) {
        r0.progressToDarkTheme = r1;
    }

    /* renamed from: -$$Nest$fputprogressVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20830$$Nest$fputprogressVisible(ThemePreviewActivity r0, boolean r1) {
        r0.progressVisible = r1;
    }

    /* renamed from: -$$Nest$fputvalueAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20831$$Nest$fputvalueAnimator(ThemePreviewActivity r0, ValueAnimator r1) {
        r0.valueAnimator = r1;
    }

    /* renamed from: -$$Nest$fputwasScroll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20832$$Nest$fputwasScroll(ThemePreviewActivity r0, boolean r1) {
        r0.wasScroll = r1;
    }

    /* renamed from: -$$Nest$mcancelThemeApply, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20833$$Nest$mcancelThemeApply(ThemePreviewActivity r0, boolean r1) {
        r0.cancelThemeApply(r1);
    }

    /* renamed from: -$$Nest$mcheckDiscard, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20834$$Nest$mcheckDiscard(ThemePreviewActivity r0, boolean r1) {
        return r0.checkDiscard(r1);
    }

    /* renamed from: -$$Nest$mgetButtonsColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20835$$Nest$mgetButtonsColor(ThemePreviewActivity r0, int r1) {
        return r0.getButtonsColor(r1);
    }

    /* renamed from: -$$Nest$minvalidateBlur, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20836$$Nest$minvalidateBlur(ThemePreviewActivity r0) {
        r0.invalidateBlur();
    }

    /* renamed from: -$$Nest$monColorsRotate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20837$$Nest$monColorsRotate(ThemePreviewActivity r0) {
        r0.onColorsRotate();
    }

    /* renamed from: -$$Nest$msaveAccentWallpaper, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20838$$Nest$msaveAccentWallpaper(ThemePreviewActivity r0) {
        r0.saveAccentWallpaper();
    }

    /* renamed from: -$$Nest$mscheduleApplyColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20839$$Nest$mscheduleApplyColor(ThemePreviewActivity r0, int r1, int r2, boolean r3) {
        r0.scheduleApplyColor(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mselectColorType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20840$$Nest$mselectColorType(ThemePreviewActivity r0, int r1) {
        r0.selectColorType(r1);
    }

    /* renamed from: -$$Nest$msetBackgroundColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20841$$Nest$msetBackgroundColor(ThemePreviewActivity r0, int r1, int r2, boolean r3, boolean r4) {
        r0.setBackgroundColor(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$msetCurrentImage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20842$$Nest$msetCurrentImage(ThemePreviewActivity r0, boolean r1) {
        r0.setCurrentImage(r1);
    }

    /* renamed from: -$$Nest$mshowPatternsView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20843$$Nest$mshowPatternsView(ThemePreviewActivity r0, int r1, boolean r2, boolean r3) {
        r0.showPatternsView(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mupdateBlurred, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20844$$Nest$mupdateBlurred(ThemePreviewActivity r0) {
        r0.updateBlurred();
    }

    /* renamed from: -$$Nest$mupdateIntensity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20845$$Nest$mupdateIntensity(ThemePreviewActivity r0) {
        r0.updateIntensity();
    }

    public ThemePreviewActivity(Object r2, Bitmap r3) {
        this(r2, r3, false, false);
    }

    public ThemePreviewActivity(Object r8, Bitmap r9, boolean r10, boolean r11) {
        this.themeDelegate = new AnonymousClass1(this);
        this.useDefaultThemeForButtons = true;
        this.colorType = 1;
        this.msgOutDrawable = new MessageDrawable(this, 0, true, false);
        this.msgOutDrawableSelected = new MessageDrawable(this, 0, true, true);
        this.msgOutMediaDrawable = new MessageDrawable(this, 1, true, false);
        this.msgOutMediaDrawableSelected = new MessageDrawable(this, 1, true, true);
        this.lastPickedColorNum = -1;
        this.applyColorAction = new ThemePreviewActivity$$ExternalSyntheticLambda0(this);
        this.backgroundImages = new BackgroundView[2];
        this.patternLayout = new FrameLayout[2];
        this.patternsCancelButton = new TextView[2];
        this.patternsSaveButton = new TextView[2];
        this.patternsButtonsContainer = new FrameLayout[2];
        this.patternsDict = new HashMap();
        this.currentIntensity = 0.5f;
        this.dimAmount = 0.0f;
        this.blendMode = PorterDuff.Mode.SRC_IN;
        this.parallaxScale = 1.0f;
        this.loadingFile = null;
        this.loadingFileObject = null;
        this.loadingSize = null;
        this.imageFilter = "640_360";
        this.maxWallpaperSize = 1920;
        this.self = true;
        this.gestureDetector2 = new GestureDetector2(getContext(), new AnonymousClass2(this));
        this.checkingBoostsLevel = false;
        this.checkedBoostsLevel = false;
        this.screenType = 2;
        this.showColor = r11;
        this.currentWallpaper = r8;
        this.currentWallpaperBitmap = r9;
        this.rotatePreview = r10;
        if ((r8 instanceof WallpapersListActivity.ColorWallpaper) == false) goto L11;
        WallpapersListActivity.ColorWallpaper r82 = (WallpapersListActivity.ColorWallpaper) r8;
        this.isMotion = r82.motion;
        TLRPC.TL_wallPaper r92 = r82.pattern;
        this.selectedPattern = r92;
        if (r92 == null) goto L11;
        float r83 = r82.intensity;
        this.currentIntensity = r83;
        if (r83 >= 0.0f) goto L11;
        if (Theme.getActiveTheme().isDark() == true) goto L11;
        this.currentIntensity *= -1.0f;
    L11:
        this.msgOutDrawable.themePreview = true;
        this.msgOutMediaDrawable.themePreview = true;
        this.msgOutDrawableSelected.themePreview = true;
        this.msgOutMediaDrawableSelected.themePreview = true;
    }

    public ThemePreviewActivity(Theme.ThemeInfo r7) {
        this(r7, false, 0, false, false);
    }

    public ThemePreviewActivity(Theme.ThemeInfo r6, boolean r7, int r8, boolean r9, boolean r10) {
        this.themeDelegate = new AnonymousClass1(this);
        this.useDefaultThemeForButtons = true;
        this.colorType = 1;
        this.msgOutDrawable = new MessageDrawable(this, 0, true, false);
        this.msgOutDrawableSelected = new MessageDrawable(this, 0, true, true);
        this.msgOutMediaDrawable = new MessageDrawable(this, 1, true, false);
        this.msgOutMediaDrawableSelected = new MessageDrawable(this, 1, true, true);
        this.lastPickedColorNum = -1;
        this.applyColorAction = new ThemePreviewActivity$$ExternalSyntheticLambda0(this);
        this.backgroundImages = new BackgroundView[2];
        this.patternLayout = new FrameLayout[2];
        this.patternsCancelButton = new TextView[2];
        this.patternsSaveButton = new TextView[2];
        this.patternsButtonsContainer = new FrameLayout[2];
        this.patternsDict = new HashMap();
        this.currentIntensity = 0.5f;
        this.dimAmount = 0.0f;
        this.blendMode = PorterDuff.Mode.SRC_IN;
        this.parallaxScale = 1.0f;
        this.loadingFile = null;
        this.loadingFileObject = null;
        this.loadingSize = null;
        this.imageFilter = "640_360";
        this.maxWallpaperSize = 1920;
        this.self = true;
        this.gestureDetector2 = new GestureDetector2(getContext(), new AnonymousClass2(this));
        this.checkingBoostsLevel = false;
        this.checkedBoostsLevel = false;
        this.screenType = r8;
        this.nightTheme = r10;
        this.applyingTheme = r6;
        this.deleteOnCancel = r7;
        this.editingTheme = r9;
        if (r8 != 1) goto L7;
        Theme.ThemeAccent r62 = r6.getAccent(!r9);
        this.accent = r62;
        if (r62 == null) goto L12;
        this.useDefaultThemeForButtons = false;
        this.backupAccentColor = r62.accentColor;
        this.backupAccentColor2 = r62.accentColor2;
        this.backupMyMessagesAccentColor = r62.myMessagesAccentColor;
        this.backupMyMessagesGradientAccentColor1 = r62.myMessagesGradientAccentColor1;
        this.backupMyMessagesGradientAccentColor2 = r62.myMessagesGradientAccentColor2;
        this.backupMyMessagesGradientAccentColor3 = r62.myMessagesGradientAccentColor3;
        this.backupMyMessagesAnimated = r62.myMessagesAnimated;
        this.backupBackgroundOverrideColor = r62.backgroundOverrideColor;
        this.backupBackgroundGradientOverrideColor1 = r62.backgroundGradientOverrideColor1;
        this.backupBackgroundGradientOverrideColor2 = r62.backgroundGradientOverrideColor2;
        this.backupBackgroundGradientOverrideColor3 = r62.backgroundGradientOverrideColor3;
        this.backupIntensity = r62.patternIntensity;
        this.backupSlug = r62.patternSlug;
        this.backupBackgroundRotation = r62.backgroundRotation;
    L12:
        Theme.ThemeAccent r63 = this.accent;
        if (r63 == null) goto L18;
        this.isMotion = r63.patternMotion;
        if (TextUtils.isEmpty(r63.patternSlug) == true) goto L17;
        this.currentIntensity = this.accent.patternIntensity;
    L17:
        Theme.applyThemeTemporary(this.applyingTheme, true);
    L18:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.goingToPreviewTheme, new Object[0]);
        this.msgOutDrawable.themePreview = true;
        this.msgOutMediaDrawable.themePreview = true;
        this.msgOutDrawableSelected.themePreview = true;
        this.msgOutMediaDrawableSelected.themePreview = true;
        return;
    L7:
        if (r8 != 0) goto L9;
        this.useDefaultThemeForButtons = false;
    L9:
        Theme.ThemeAccent r64 = r6.getAccent(false);
        this.accent = r64;
        if (r64 == null) goto L12;
        this.selectedPattern = r64.pattern;
        goto L12
    }

    public static /* synthetic */ ActionBar access$000(ThemePreviewActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$100(ThemePreviewActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ int access$1000(ThemePreviewActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1100(ThemePreviewActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1200(ThemePreviewActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1300(ThemePreviewActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1400(ThemePreviewActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1500(ThemePreviewActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1600(ThemePreviewActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1700(ThemePreviewActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1800(ThemePreviewActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1900(ThemePreviewActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ ActionBar access$200(ThemePreviewActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ int access$2000(ThemePreviewActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2100(ThemePreviewActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2200(ThemePreviewActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2300(ThemePreviewActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2400(ThemePreviewActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2500(ThemePreviewActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2600(ThemePreviewActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2700(ThemePreviewActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2800(ThemePreviewActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2900(ThemePreviewActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ ActionBar access$300(ThemePreviewActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ int access$3000(ThemePreviewActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3100(ThemePreviewActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3200(ThemePreviewActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3300(ThemePreviewActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3400(ThemePreviewActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3500(ThemePreviewActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3600(ThemePreviewActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3700(ThemePreviewActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3800(ThemePreviewActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3900(ThemePreviewActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ INavigationLayout access$400(ThemePreviewActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ ActionBar access$500(ThemePreviewActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$600(ThemePreviewActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ INavigationLayout access$700(ThemePreviewActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ INavigationLayout access$800(ThemePreviewActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ INavigationLayout access$900(ThemePreviewActivity r0) {
        return r0.parentLayout;
    }

    private void animateMotionChange() {
        AnimatorSet r0 = this.motionAnimation;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        AnimatorSet r02 = new AnimatorSet();
        this.motionAnimation = r02;
        boolean r1 = this.isMotion;
        BackgroundView r2 = this.backgroundImage;
        if (r1 == false) goto L8;
        r02.playTogether(new Animator[]{ObjectAnimator.ofFloat(r2, View.SCALE_X, new float[]{this.parallaxScale}), ObjectAnimator.ofFloat(this.backgroundImage, View.SCALE_Y, new float[]{this.parallaxScale})});
    L9:
        this.motionAnimation.setInterpolator(CubicBezierInterpolator.EASE_OUT);
        this.motionAnimation.addListener(new AnonymousClass36(this));
        this.motionAnimation.start();
        return;
    L8:
        r02.playTogether(new Animator[]{ObjectAnimator.ofFloat(r2, View.SCALE_X, new float[]{1.0f}), ObjectAnimator.ofFloat(this.backgroundImage, View.SCALE_Y, new float[]{1.0f}), ObjectAnimator.ofFloat(this.backgroundImage, View.TRANSLATION_X, new float[]{0.0f}), ObjectAnimator.ofFloat(this.backgroundImage, View.TRANSLATION_Y, new float[]{0.0f})});
        goto L9
    }

    private void applyColor(int r8, int r9) {
        int r0 = this.colorType;
        if (r0 != 1) goto L9;
        if (r9 != 0) goto L6;
        this.accent.accentColor = r8;
        Theme.refreshThemeColors();
    L53:
        int r82 = this.themeDescriptions.size();
        int r92 = 0;
    L54:
        if (r92 >= r82) goto L56;
        ThemeDescription r02 = this.themeDescriptions.get(r92);
        r02.setColor(getThemedColor(r02.getCurrentKey()), false, false);
        r92 = r92 + 1;
        goto L54
    L56:
        this.listView.invalidateViews();
        this.listView2.invalidateViews();
        View r7 = this.dotsContainer;
        if (r7 == null) goto L61;
        r7.invalidate();
        return;
    L61:
        return;
    L6:
        if (r9 != 1) goto L53;
        this.accent.accentColor2 = r8;
        Theme.refreshThemeColors(true, true);
        this.listView2.invalidateViews();
        this.colorPicker.setHasChanges(hasChanges(this.colorType));
        updatePlayAnimationView(true);
        goto L53
    L9:
        if (r0 == 2) goto L11;
        if (r0 != 3) goto L53;
        int r93 = this.lastPickedColorNum;
        if (r93 != 0) goto L37;
        this.accent.myMessagesAccentColor = r8;
    L49:
        int r94 = this.lastPickedColorNum;
        if (r94 < 0) goto L52;
        this.messagesCheckBoxView[1].setColor(r94, r8);
    L52:
        Theme.refreshThemeColors(true, true);
        this.listView2.invalidateViews();
        this.colorPicker.setHasChanges(hasChanges(this.colorType));
        updatePlayAnimationView(true);
        goto L53
    L37:
        if (r93 != 1) goto L39;
        this.accent.myMessagesGradientAccentColor1 = r8;
        goto L49
    L39:
        Theme.ThemeAccent r03 = this.accent;
        if (r93 != 2) goto L48;
        int r95 = r03.myMessagesGradientAccentColor2;
        r03.myMessagesGradientAccentColor2 = r8;
        if (r95 == 0) goto L45;
        if (r8 != 0) goto L45;
        this.messagesAdapter.notifyItemRemoved(0);
    L45:
        if (r95 != 0) goto L49;
        if (r8 == 0) goto L49;
        this.messagesAdapter.notifyItemInserted(0);
        showAnimationHint();
        goto L49
    L48:
        r03.myMessagesGradientAccentColor3 = r8;
        goto L49
    L11:
        if (this.lastPickedColorNum != 0) goto L14;
        this.accent.backgroundOverrideColor = r8;
    L32:
        Theme.refreshThemeColors(true, false);
        this.colorPicker.setHasChanges(hasChanges(this.colorType));
        updatePlayAnimationView(true);
        goto L53
    L14:
        if (r9 != 1) goto L20;
        int r96 = Theme.getDefaultAccentColor(Theme.key_chat_wallpaper_gradient_to1);
        if (r8 != 0) goto L19;
        if (r96 == 0) goto L19;
        this.accent.backgroundGradientOverrideColor1 = 4294967296L;
    L19:
        this.accent.backgroundGradientOverrideColor1 = r8;
        goto L32
    L20:
        if (r9 != 2) goto L26;
        int r97 = Theme.getDefaultAccentColor(Theme.key_chat_wallpaper_gradient_to2);
        if (r8 != 0) goto L25;
        if (r97 == 0) goto L25;
        this.accent.backgroundGradientOverrideColor2 = 4294967296L;
    L25:
        this.accent.backgroundGradientOverrideColor2 = r8;
        goto L32
    L26:
        if (r9 != 3) goto L32;
        int r98 = Theme.getDefaultAccentColor(Theme.key_chat_wallpaper_gradient_to3);
        if (r8 != 0) goto L31;
        if (r98 == 0) goto L31;
        this.accent.backgroundGradientOverrideColor3 = 4294967296L;
    L31:
        this.accent.backgroundGradientOverrideColor3 = r8;
        goto L32
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void applyWallpaperBackground(boolean r32) {
        if (this.dialogId >= 0) goto L14;
        TL_stories.TL_premium_boostsStatus r0 = this.boostsStatus;
        if (r0 == null) goto L11;
        if (r0.level >= getCustomWallpaperLevelMin()) goto L11;
        getMessagesController().getBoostsController().userCanBoostChannel(this.dialogId, this.boostsStatus, new ThemePreviewActivity$$ExternalSyntheticLambda26(this));
        return;
    L11:
        if (this.boostsStatus != null) goto L14;
        return;
    L14:
        if (getUserConfig().isPremium() == true) goto L18;
        if (r32 == false) goto L18;
        showDialog(new PremiumFeatureBottomSheet(this, 22, true));
        return;
    L18:
        Theme.ThemeInfo r02 = Theme.getActiveTheme();
        File r7 = null;
        String r4 = r02.generateWallpaperName(null, this.isBlurred);
        int r9 = 0;
        if (this.isBlurred == false) goto L21;
        String r8 = r02.generateWallpaperName(null, false);
    L22:
        File r10 = new File(ApplicationLoader.getFilesDirFixed(), r4);
        Object r03 = this.currentWallpaper;
        if ((r03 instanceof TLRPC.TL_wallPaper) == true) goto L25;
        long r16 = 0;
        if ((r03 instanceof WallpapersListActivity.ColorWallpaper) == false) goto L77;
        if (this.selectedPattern != null) goto L248;
    L74:
        boolean r3 = false;
    L75:
        boolean r04 = true;
    L117:
        if (this.isBlurred == true) goto L252;
    L122:
        Object r5 = this.currentWallpaper;
        int r19 = 45;
        if ((r5 instanceof TLRPC.TL_wallPaper) == false) goto L126;
        TLRPC.TL_wallPaper r52 = (TLRPC.TL_wallPaper) r5;
        boolean r192 = r04;
        int r05 = 45;
        boolean r28 = r3;
        File r23 = r7;
        int r12 = 0;
        int r20 = 0;
        float r21 = 0.0f;
        String r72 = r52.slug;
        int r15 = 0;
        TLRPC.TL_wallPaper r6 = r52;
        int r53 = 0;
    L149:
        Theme.OverrideWallpaperInfo r33 = new Theme.OverrideWallpaperInfo();
        r33.fileName = r8;
        r33.originalFileName = r4;
        r33.slug = r72;
        r33.isBlurred = this.isBlurred;
        r33.isMotion = this.isMotion;
        r33.color = r12;
        r33.gradientColor1 = r15;
        r33.gradientColor2 = r9;
        r33.gradientColor3 = r53;
        r33.rotation = r05;
        if (this.shouldShowBrightnessControll == false) goto L154;
        float r42 = this.dimAmount;
        if (r42 < r21) goto L154;
        r33.intensity = r42;
    L155:
        Object r43 = this.currentWallpaper;
        if ((r43 instanceof WallpapersListActivity.ColorWallpaper) == false) goto L189;
        WallpapersListActivity.ColorWallpaper r44 = (WallpapersListActivity.ColorWallpaper) r43;
        if ("c".equals(r72) == false) goto L160;
    L164:
        String r82 = null;
    L165:
        float r11 = r44.intensity;
        if (r11 >= r21) goto L171;
        if (Theme.getActiveTheme().isDark() == true) goto L171;
        r11 = r11 * (-1.0f);
    L171:
        if (r44.parentWallpaper == null) goto L189;
        if (r44.color != r12) goto L189;
        if (r44.gradientColor1 != r15) goto L189;
        if (r44.gradientColor2 != r9) goto L189;
        if (r44.gradientColor3 != r53) goto L189;
        if (TextUtils.equals(r44.slug, r82) == false) goto L189;
        if (r44.gradientRotation != r05) goto L189;
        if (this.selectedPattern != null) goto L187;
    L188:
        TLRPC.WallPaper r06 = r44.parentWallpaper;
        r33.wallpaperId = r06.id;
        r33.accessHash = r06.access_hash;
        goto L189
    L187:
        if (Math.abs(r11 - this.currentIntensity) >= 0.001f) goto L189;
    L160:
        if ("t".equals(r72) == true) goto L164;
        if ("d".equals(r72) == true) goto L164;
        r82 = r72;
    L189:
        long r45 = this.dialogId;
        r33.dialogId = r45;
        if (r45 == r16) goto L194;
        TLRPC.UserFull r07 = getMessagesController().getUserFull(this.dialogId);
        if (r07 == null) goto L194;
        r33.prevUserWallpaper = r07.wallpaper;
    L194:
        r33.forBoth = r32;
        MessagesController r22 = MessagesController.getInstance(this.currentAccount);
        if (r72 != null) goto L197;
    L199:
        boolean r25 = r20;
    L200:
        r22.saveWallpaperToServer(r23, r33, r25, 0);
        if (r192 == true) goto L203;
        int r18 = 1;
    L233:
        int r34 = r18;
    L234:
        if (r34 == 0) goto L260;
        WallpaperActivityDelegate r08 = this.delegate;
        if (r08 == null) goto L238;
        r08.didSetNewBackground(r6);
    L238:
        finishFragment();
        return;
    L260:
        return;
    L203:
        if (this.dialogId == r16) goto L225;
        if (r23 != null) goto L206;
    L220:
        ChatThemeController.getInstance(this.currentAccount).setWallpaperToPeer(this.dialogId, null, r33, this.serverWallpaper, new ThemePreviewActivity$$ExternalSyntheticLambda27());
    L221:
        this.setupFinished = true;
        WallpaperActivityDelegate r09 = this.delegate;
        if (r09 == null) goto L224;
        r09.didSetNewBackground(r6);
    L224:
        finishFragment();
        r34 = r20;
        goto L234
    L206:
        if (getMessagesController().uploadingWallpaperInfo != r33) goto L220;
        r6 = new TLRPC.TL_wallPaper();
        TLRPC.TL_wallPaperSettings r35 = new TLRPC.TL_wallPaperSettings();
        r6.settings = r35;
        r35.intensity = (int) (r33.intensity * 100.0f);
        r35.blur = r33.isBlurred;
        r35.motion = r33.isMotion;
        r6.uploadingImage = r23.getAbsolutePath();
        Bitmap r010 = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
        Canvas r36 = new Canvas(r010);
        float r46 = Math.max(50.0f / this.backgroundImage.getMeasuredWidth(), 50.0f / this.backgroundImage.getMeasuredHeight());
        r36.scale(r46, r46);
        int r47 = this.backgroundImage.getMeasuredHeight();
        int r54 = this.backgroundImage.getMeasuredWidth();
        BackgroundView r73 = this.backgroundImage;
        if (r47 <= r54) goto L210;
        float r55 = r21;
        r36.translate(r55, (-(r73.getMeasuredHeight() - this.backgroundImage.getMeasuredWidth())) / 2.0f);
    L211:
        float r48 = this.dimAmount;
        this.dimAmount = r55;
        this.backgroundImage.draw(r36);
        this.dimAmount = r48;
        Utilities.blurBitmap(r010, 3);
        r6.stripedThumb = r010;
        createServiceMessageLocal(r6, r32);
        if (this.dialogId < r16) goto L216;
        TLRPC.UserFull r011 = getMessagesController().getUserFull(this.dialogId);
        if (r011 == null) goto L221;
        r011.wallpaper = r6;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.userInfoDidLoad, new Object[]{Long.valueOf(this.dialogId), r011});
        goto L221
    L216:
        TLRPC.ChatFull r012 = getMessagesController().getChatFull(-this.dialogId);
        if (r012 == null) goto L221;
        r012.wallpaper = r6;
        NotificationCenter r2 = NotificationCenter.getInstance(this.currentAccount);
        int r37 = NotificationCenter.chatInfoDidLoad;
        Integer r49 = Integer.valueOf(r20);
        Boolean r56 = Boolean.FALSE;
        r2.postNotificationName(r37, new Object[]{r012, r49, r56, r56});
        goto L221
    L210:
        r55 = r21;
        r36.translate((-(r73.getMeasuredWidth() - this.backgroundImage.getMeasuredHeight())) / 2.0f, r55);
        goto L211
    L225:
        Theme.serviceMessageColorBackup = getThemedColor(Theme.key_chat_serviceBackground);
        if ("t".equals(r33.slug) == false) goto L228;
        Theme.OverrideWallpaperInfo r74 = null;
    L229:
        Theme.getActiveTheme().setOverrideWallpaper(r74);
        r18 = 1;
        Theme.reloadWallpaper(true);
        if (r28 == true) goto L233;
        ImageLoader.getInstance().removeImage(ImageLoader.getHttpFileName(r10.getAbsolutePath()) + "@100_100");
        goto L233
    L228:
        r74 = r33;
        goto L229
    L197:
        if (this.dialogId != r16) goto L199;
        r25 = 1;
    L154:
        r33.intensity = this.currentIntensity;
        goto L155
    L126:
        if ((r5 instanceof WallpapersListActivity.ColorWallpaper) == true) goto L128;
        r20 = 0;
        r21 = 0.0f;
        if ((r5 instanceof WallpapersListActivity.FileWallpaper) == false) goto L142;
        WallpapersListActivity.FileWallpaper r57 = (WallpapersListActivity.FileWallpaper) r5;
        String r62 = r57.slug;
        File r58 = r57.path;
    L140:
        r192 = r04;
        r05 = 45;
        r28 = r3;
        r23 = r58;
        r72 = r62;
        r53 = 0;
        r9 = 0;
        r12 = 0;
        r15 = 0;
    L136:
        r6 = null;
        goto L149
    L142:
        if ((r5 instanceof MediaController.SearchImage) == false) goto L148;
        MediaController.SearchImage r59 = (MediaController.SearchImage) r5;
        TLRPC.Photo r63 = r59.photo;
        if (r63 == null) goto L146;
        r58 = FileLoader.getInstance(this.currentAccount).getPathToAttach(FileLoader.getClosestPhotoSizeWithSize(r63.sizes, this.maxWallpaperSize, true), true);
    L147:
        r62 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L140
    L146:
        r58 = ImageLoader.getHttpFilePath(r59.imageUrl, "jpg");
        goto L147
    L148:
        r192 = r04;
        r05 = 45;
        r28 = r3;
        r72 = "d";
        r53 = 0;
        r9 = 0;
        r12 = 0;
        r15 = 0;
        r6 = null;
        r23 = null;
        goto L149
    L128:
        if ("d".equals(((WallpapersListActivity.ColorWallpaper) r5).slug) == false) goto L130;
        r53 = 0;
        int r75 = 0;
        int r122 = 0;
        r20 = 0;
        String r64 = "d";
        r21 = 0.0f;
    L135:
        int r152 = r19;
        r192 = r04;
        r05 = r152;
        r28 = r3;
        r15 = r122;
        r23 = null;
        r12 = r9;
        r9 = r75;
        r72 = r64;
        goto L136
    L130:
        TLRPC.TL_wallPaper r510 = this.selectedPattern;
        if (r510 == null) goto L133;
        String r511 = r510.slug;
    L134:
        int r65 = this.backgroundColor;
        r122 = this.backgroundGradientColor1;
        r75 = this.backgroundGradientColor2;
        r20 = 0;
        r21 = 0.0f;
        r64 = r511;
        r53 = this.backgroundGradientColor3;
        r9 = r65;
        r19 = this.backgroundRotation;
        goto L135
    L133:
        r511 = "c";
        goto L134
    L252:
        FileOutputStream r512 = new FileOutputStream(new File(ApplicationLoader.getFilesDirFixed(), r8));     // Catch: Throwable -> L120
        this.blurredBitmap.compress(Bitmap.CompressFormat.JPEG, 87, r512);     // Catch: Throwable -> L120
        r512.close();     // Catch: Throwable -> L120
        r04 = true;
    L120:
        th = move-exception;
        FileLog.e(th);
        r04 = false;
        goto L122
    L248:
        WallpapersListActivity.ColorWallpaper r013 = (WallpapersListActivity.ColorWallpaper) r03;     // Catch: Throwable -> L63
        Bitmap r014 = this.backgroundImage.getImageReceiver().getBitmap();     // Catch: Throwable -> L63
        Bitmap r513 = Bitmap.createBitmap(r014.getWidth(), r014.getHeight(), Bitmap.Config.ARGB_8888);     // Catch: Throwable -> L63
        Canvas r66 = new Canvas(r513);     // Catch: Throwable -> L63
        if (this.backgroundGradientColor2 == 0) goto L61;
    L66:
        Paint r38 = new Paint(2);     // Catch: Throwable -> L63
        r38.setColorFilter(new PorterDuffColorFilter(this.patternColor, this.blendMode));     // Catch: Throwable -> L63
        r38.setAlpha((int) (Math.abs(this.currentIntensity) * 255.0f));     // Catch: Throwable -> L63
        r66.drawBitmap(r014, 0.0f, 0.0f, r38);     // Catch: Throwable -> L63
        FileOutputStream r015 = new FileOutputStream(r10);     // Catch: Throwable -> L63
        if (this.backgroundGradientColor2 == 0) goto L69;
        r513.compress(Bitmap.CompressFormat.PNG, 100, r015);     // Catch: Throwable -> L63
    L70:
        r015.close();     // Catch: Throwable -> L63
        r3 = false;
        r04 = true;
    L72:
        r7 = null;
        goto L117
    L69:
        r513.compress(Bitmap.CompressFormat.JPEG, 87, r015);     // Catch: Throwable -> L63
        goto L70
    L61:
        if (this.backgroundGradientColor1 == 0) goto L65;
        GradientDrawable r112 = new GradientDrawable(BackgroundGradientDrawable.getGradientOrientation(this.backgroundRotation), new int[]{this.backgroundColor, this.backgroundGradientColor1});     // Catch: Throwable -> L63
        r112.setBounds(0, 0, r513.getWidth(), r513.getHeight());     // Catch: Throwable -> L63
        r112.draw(r66);     // Catch: Throwable -> L63
        goto L66
    L65:
        r66.drawColor(this.backgroundColor);     // Catch: Throwable -> L63
    L63:
        th = move-exception;
        FileLog.e(th);
        r04 = false;
        r3 = false;
        goto L72
    L77:
        if ((r03 instanceof WallpapersListActivity.FileWallpaper) == false) goto L105;
        WallpapersListActivity.FileWallpaper r016 = (WallpapersListActivity.FileWallpaper) r03;
        if (r016.resId == 0) goto L81;
    L82:
        r7 = null;
        goto L74
    L81:
        if ("t".equals(r016.slug) == true) goto L82;
    L92:
        e = e;
        r7 = null;
    L91:
        r3 = false;
    L104:
        FileLog.e(e);
        r04 = false;
        goto L117
    L254:
        if (this.hasScrollingBackground == true) goto L86;
    L94:
        r7 = null;
        File r39 = r016.originalPath;     // Catch: Exception -> L90
        if (r39 == null) goto L97;
        File r017 = r39;
    L98:
        r3 = r017.equals(r10);     // Catch: Exception -> L90
        if (r3 == true) goto L75;
        r04 = AndroidUtilities.copyFile(r017, r10);     // Catch: Exception -> L103
    L103:
        e = e;
        goto L104
    L97:
        r017 = r016.path;     // Catch: Exception -> L90
        goto L98
    L86:
        if (this.currentScrollOffset == this.defaultScrollOffset) goto L94;
        Bitmap r310 = Bitmap.createBitmap((int) this.croppedWidth, this.currentWallpaperBitmap.getHeight(), Bitmap.Config.ARGB_8888);     // Catch: Exception -> L92
        Canvas r514 = new Canvas(r310);     // Catch: Exception -> L92
        r514.translate(-((this.currentScrollOffset / this.maxScrollOffset) * (this.currentWallpaperBitmap.getWidth() - r310.getWidth())), 0.0f);     // Catch: Exception -> L92
        r7 = null;
        r514.drawBitmap(this.currentWallpaperBitmap, 0.0f, 0.0f, null);     // Catch: Exception -> L90
        r016.path = new File(FileLoader.getDirectory(4), Utilities.random.nextInt() + ".jpg");     // Catch: Exception -> L90
        FileOutputStream r515 = new FileOutputStream(r016.path);     // Catch: Exception -> L90
        r310.compress(Bitmap.CompressFormat.JPEG, 87, r515);     // Catch: Exception -> L90
        r515.close();     // Catch: Exception -> L90
        r310.recycle();     // Catch: Exception -> L90
        r017 = r016.path;     // Catch: Exception -> L90
    L90:
        e = e;
        goto L91
    L105:
        r7 = null;
        if ((r03 instanceof MediaController.SearchImage) == false) goto L115;
        MediaController.SearchImage r018 = (MediaController.SearchImage) r03;
        TLRPC.Photo r311 = r018.photo;
        if (r311 == null) goto L110;
        File r019 = FileLoader.getInstance(this.currentAccount).getPathToAttach(FileLoader.getClosestPhotoSizeWithSize(r311.sizes, this.maxWallpaperSize, true), true);
    L256:
        r04 = AndroidUtilities.copyFile(r019, r10);     // Catch: Exception -> L113
    L52:
        r3 = false;
    L113:
        e = move-exception;
        FileLog.e(e);
    L51:
        r04 = false;
        goto L52
    L110:
        r019 = ImageLoader.getHttpFilePath(r018.imageUrl, "jpg");
        goto L256
    L115:
        r04 = false;
        r3 = false;
        goto L117
    L25:
        if (this.originalBitmap != null) goto L258;
        r16 = 0;
        ImageReceiver r020 = this.backgroundImage.getImageReceiver();
        if (r020.hasNotThumb() == false) goto L39;
    L40:
        Bitmap r021 = r020.getBitmap();
        FileOutputStream r516 = new FileOutputStream(r10);     // Catch: Exception -> L43
        r021.compress(Bitmap.CompressFormat.JPEG, 87, r516);     // Catch: Exception -> L43
        r516.close();     // Catch: Exception -> L43
    L29:
        r04 = true;
    L45:
        if (r04 == true) goto L52;
        r04 = AndroidUtilities.copyFile(FileLoader.getInstance(this.currentAccount).getPathToAttach(((TLRPC.TL_wallPaper) this.currentWallpaper).document, true), r10);     // Catch: Exception -> L49
    L49:
        e = move-exception;
        FileLog.e(e);
    L43:
        e = move-exception;
        FileLog.e(e);
    L35:
        r04 = false;
        goto L45
    L39:
        if (r020.hasStaticThumb() == false) goto L35;
    L258:
        FileOutputStream r022 = new FileOutputStream(r10);     // Catch: Exception -> L32
        r16 = 0;
        this.originalBitmap.compress(Bitmap.CompressFormat.JPEG, 87, r022);     // Catch: Exception -> L30
        r022.close();     // Catch: Exception -> L30
    L30:
        e = e;
    L34:
        FileLog.e(e);
    L32:
        e = e;
        r16 = 0;
        goto L34
    L21:
        r8 = r4;
        goto L22
    }

    private void cancelThemeApply(boolean r6) {
        if (this.screenType != 2) goto L7;
        if (r6 == true) goto L26;
        finishFragment();
        return;
    L26:
        return;
    L7:
        Theme.applyPreviousTheme();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didSetNewWallpapper);
        if (this.screenType != 1) goto L14;
        if (this.editingTheme == false) goto L12;
        Theme.ThemeAccent r0 = this.accent;
        r0.accentColor = this.backupAccentColor;
        r0.accentColor2 = this.backupAccentColor2;
        r0.myMessagesAccentColor = this.backupMyMessagesAccentColor;
        r0.myMessagesGradientAccentColor1 = this.backupMyMessagesGradientAccentColor1;
        r0.myMessagesGradientAccentColor2 = this.backupMyMessagesGradientAccentColor2;
        r0.myMessagesGradientAccentColor3 = this.backupMyMessagesGradientAccentColor3;
        r0.myMessagesAnimated = this.backupMyMessagesAnimated;
        r0.backgroundOverrideColor = this.backupBackgroundOverrideColor;
        r0.backgroundGradientOverrideColor1 = this.backupBackgroundGradientOverrideColor1;
        r0.backgroundGradientOverrideColor2 = this.backupBackgroundGradientOverrideColor2;
        r0.backgroundGradientOverrideColor3 = this.backupBackgroundGradientOverrideColor3;
        r0.backgroundRotation = this.backupBackgroundRotation;
        r0.patternSlug = this.backupSlug;
        r0.patternIntensity = this.backupIntensity;
    L12:
        Theme.saveThemeAccents(this.applyingTheme, false, true, false, false);
    L23:
        if (r6 == true) goto L27;
        finishFragment();
        return;
    L27:
        return;
    L14:
        if (this.accent == null) goto L16;
        Theme.saveThemeAccents(this.applyingTheme, false, this.deleteOnCancel, false, false);
    L16:
        this.parentLayout.rebuildAllFragmentViews(false, false);
        if (this.deleteOnCancel == false) goto L23;
        Theme.ThemeInfo r02 = this.applyingTheme;
        if (r02.pathToFile == null) goto L23;
        if (Theme.isThemeInstalled(r02) == true) goto L23;
        new File(this.applyingTheme.pathToFile).delete();
        goto L23
    }

    private BitmapDrawable checkBlur(Drawable r5) {
        WeakReference<Drawable> r0 = this.lastDrawableToBlur;
        if (r0 != null) goto L5;
    L8:
        WeakReference<Drawable> r02 = this.lastDrawableToBlur;
        if (r02 == null) goto L11;
        r02.clear();
    L11:
        this.lastDrawableToBlur = null;
        if (r5 != null) goto L14;
    L20:
        this.blurredDrawable = null;
        return null;
    L14:
        if (r5.getIntrinsicWidth() == 0) goto L20;
        if (r5.getIntrinsicHeight() == 0) goto L20;
        this.lastDrawableToBlur = new WeakReference(r5);
        int r03 = (int) ((r5.getIntrinsicWidth() / r5.getIntrinsicHeight()) * 24.0f);
        Bitmap r1 = Bitmap.createBitmap(r03, 24, Bitmap.Config.ARGB_8888);
        r5.setBounds(0, 0, r03, 24);
        ColorFilter r04 = r5.getColorFilter();
        ColorMatrix r2 = new ColorMatrix();
        r2.setSaturation(1.3f);
        AndroidUtilities.multiplyBrightnessColorMatrix(r2, 0.94f);
        r5.setColorFilter(new ColorMatrixColorFilter(r2));
        r5.draw(new Canvas(r1));
        r5.setColorFilter(r04);
        Utilities.blurBitmap(r1, 3);
        BitmapDrawable r52 = new BitmapDrawable(getContext().getResources(), r1);
        this.blurredDrawable = r52;
        r52.setFilterBitmap(true);
        return this.blurredDrawable;
    L5:
        if (r0.get() != r5) goto L8;
        return this.blurredDrawable;
    }

    private void checkBoostsLevel() {
        if (this.dialogId < 0) goto L5;
        return;
    L5:
        if (this.checkingBoostsLevel == false) goto L7;
        return;
    L7:
        if (this.checkedBoostsLevel == false) goto L9;
        return;
    L9:
        if (this.boostsStatus != null) goto L16;
        this.checkingBoostsLevel = true;
        getMessagesController().getBoostsController().getBoostsStats(this.dialogId, new ThemePreviewActivity$$ExternalSyntheticLambda25(this));
        return;
    }

    private boolean checkDiscard(boolean r7) {
        if (this.screenType != 1) goto L47;
        Theme.ThemeAccent r0 = this.accent;
        if (r0.accentColor == this.backupAccentColor) goto L7;
    L43:
        if (r7 == false) goto L48;
        AlertDialog.Builder r72 = new AlertDialog.Builder(getParentActivity());
        r72.setTitle(LocaleController.getString(R.string.SaveChangesAlertTitle));
        r72.setMessage(LocaleController.getString(R.string.SaveChangesAlertText));
        r72.setPositiveButton(LocaleController.getString(R.string.Save), new ThemePreviewActivity$$ExternalSyntheticLambda21(this));
        r72.setNegativeButton(LocaleController.getString(R.string.PassportDiscard), new ThemePreviewActivity$$ExternalSyntheticLambda22(this));
        showDialog(r72.create());
        return false;
    L48:
        return false;
    L7:
        if (r0.accentColor2 != this.backupAccentColor2) goto L43;
        if (r0.myMessagesAccentColor != this.backupMyMessagesAccentColor) goto L43;
        if (r0.myMessagesGradientAccentColor1 != this.backupMyMessagesGradientAccentColor1) goto L43;
        if (r0.myMessagesGradientAccentColor2 != this.backupMyMessagesGradientAccentColor2) goto L43;
        if (r0.myMessagesGradientAccentColor3 != this.backupMyMessagesGradientAccentColor3) goto L43;
        if (r0.myMessagesAnimated != this.backupMyMessagesAnimated) goto L43;
        if (r0.backgroundOverrideColor != this.backupBackgroundOverrideColor) goto L43;
        if (r0.backgroundGradientOverrideColor1 != this.backupBackgroundGradientOverrideColor1) goto L43;
        if (r0.backgroundGradientOverrideColor2 != this.backupBackgroundGradientOverrideColor2) goto L43;
        if (r0.backgroundGradientOverrideColor3 != this.backupBackgroundGradientOverrideColor3) goto L43;
        if (Math.abs(r0.patternIntensity - this.backupIntensity) > 0.001f) goto L43;
        Theme.ThemeAccent r02 = this.accent;
        if (r02.backgroundRotation != this.backupBackgroundRotation) goto L43;
        String r03 = r02.patternSlug;
        TLRPC.TL_wallPaper r2 = this.selectedPattern;
        if (r2 == null) goto L33;
        String r22 = r2.slug;
    L35:
        if (r03.equals(r22) == false) goto L43;
        TLRPC.TL_wallPaper r04 = this.selectedPattern;
        if (r04 != null) goto L39;
    L40:
        if (r04 == null) goto L47;
        if (this.accent.patternIntensity == this.currentIntensity) goto L47;
    L39:
        if (this.accent.patternMotion != this.isMotion) goto L43;
    L33:
        r22 = _UrlKt.FRAGMENT_ENCODE_SET;
    L47:
        return true;
    }

    private void createServiceMessageLocal(TLRPC.WallPaper r6, boolean r7) {
        TLRPC.TL_messageService r0 = new TLRPC.TL_messageService();
        r0.random_id = SendMessagesHelper.getInstance(this.currentAccount).getNextRandomId();
        r0.dialog_id = this.dialogId;
        r0.unread = true;
        r0.out = true;
        int r1 = getUserConfig().getNewMessageId();
        r0.id = r1;
        r0.local_id = r1;
        TLRPC.Chat r12 = getMessagesController().getChat(Long.valueOf(-this.dialogId));
        if (ChatObject.isChannel(r12) == false) goto L5;
        TLRPC.TL_peerChannel r2 = new TLRPC.TL_peerChannel();
        r0.from_id = r2;
        r2.channel_id = r12.id;
        TLRPC.TL_peerChannel r22 = new TLRPC.TL_peerChannel();
        r0.peer_id = r22;
        r22.channel_id = r12.id;
    L6:
        r0.flags |= 256;
        r0.date = getConnectionsManager().getCurrentTime();
        TLRPC.TL_messageActionSetChatWallPaper r13 = new TLRPC.TL_messageActionSetChatWallPaper();
        r0.action = r13;
        r13.wallpaper = r6;
        r13.for_both = r7;
        ArrayList<MessageObject> r62 = new ArrayList();
        r62.add(new MessageObject(this.currentAccount, r0, false, false));
        new ArrayList().add(r0);
        MessagesController.getInstance(this.currentAccount).updateInterfaceWithMessages(this.dialogId, r62, 0);
        return;
    L5:
        TLRPC.TL_peerUser r14 = new TLRPC.TL_peerUser();
        r0.from_id = r14;
        r14.user_id = getUserConfig().getClientUserId();
        TLRPC.TL_peerUser r15 = new TLRPC.TL_peerUser();
        r0.peer_id = r15;
        r15.user_id = this.dialogId;
        goto L6
    }

    private int getButtonsColor(int r2) {
        if (this.useDefaultThemeForButtons == false) goto L7;
        return Theme.getDefaultColor(r2);
    L7:
        return getThemedColor(r2);
    }

    private int getCustomWallpaperLevelMin() {
        if (ChatObject.isChannelAndNotMegaGroup(-this.dialogId, this.currentAccount) == false) goto L7;
        return getMessagesController().channelCustomWallpaperLevelMin;
    L7:
        return getMessagesController().groupCustomWallpaperLevelMin;
    }

    private boolean hasChanges(int r11) {
        if (this.editingTheme == false) goto L6;
        return false;
    L6:
        if (r11 != 1) goto L8;
    L9:
        long r3 = this.backupBackgroundOverrideColor;
        if (r3 != 0) goto L12;
        int r32 = Theme.getDefaultAccentColor(Theme.key_chat_wallpaper);
        int r4 = (int) this.accent.backgroundOverrideColor;
        if (r4 != 0) goto L17;
        r4 = r32;
    L17:
        if (r4 == r32) goto L19;
        return true;
    L19:
        long r33 = this.backupBackgroundGradientOverrideColor1;
        if (r33 == 0) goto L22;
    L43:
        Theme.ThemeAccent r5 = this.accent;
        if (r33 == r5.backgroundGradientOverrideColor1) goto L46;
    L96:
        return true;
    L46:
        if (this.backupBackgroundGradientOverrideColor2 != r5.backgroundGradientOverrideColor2) goto L96;
        if (this.backupBackgroundGradientOverrideColor3 != r5.backgroundGradientOverrideColor3) goto L96;
    L51:
        if (this.accent.backgroundRotation == this.backupBackgroundRotation) goto L53;
        return true;
    L53:
        if (r11 == 1) goto L55;
        if (r11 == 3) goto L55;
    L95:
        return false;
    L55:
        int r112 = this.backupAccentColor;
        Theme.ThemeAccent r0 = this.accent;
        if (r112 == r0.accentColor2) goto L58;
        return true;
    L58:
        int r113 = this.backupMyMessagesAccentColor;
        if (r113 != 0) goto L61;
        int r114 = r0.myMessagesAccentColor;
        if (r114 != 0) goto L66;
    L68:
        int r115 = this.backupMyMessagesGradientAccentColor1;
        if (r115 == 0) goto L74;
        if (r115 == r0.myMessagesGradientAccentColor1) goto L76;
        return true;
    L76:
        int r116 = this.backupMyMessagesGradientAccentColor2;
        if (r116 == 0) goto L82;
        if (r116 == r0.myMessagesGradientAccentColor2) goto L84;
        return true;
    L84:
        int r117 = this.backupMyMessagesGradientAccentColor3;
        if (r117 == 0) goto L90;
        if (r117 == r0.myMessagesGradientAccentColor3) goto L93;
        return true;
    L93:
        if (this.backupMyMessagesAnimated == r0.myMessagesAnimated) goto L95;
        return true;
    L90:
        if (r0.myMessagesGradientAccentColor3 == 0) goto L93;
        return true;
    L82:
        if (r0.myMessagesGradientAccentColor2 == 0) goto L84;
        return true;
    L74:
        if (r0.myMessagesGradientAccentColor1 == 0) goto L76;
        return true;
    L66:
        if (r114 == r0.accentColor) goto L68;
        return true;
    L61:
        if (r113 == r0.myMessagesAccentColor) goto L68;
        return true;
    L22:
        if (this.backupBackgroundGradientOverrideColor2 != 0) goto L43;
        if (this.backupBackgroundGradientOverrideColor3 != 0) goto L43;
        int r34 = 0;
    L27:
        if (r34 >= 3) goto L51;
        if (r34 != 0) goto L30;
        int r42 = Theme.getDefaultAccentColor(Theme.key_chat_wallpaper_gradient_to1);
        long r7 = this.accent.backgroundGradientOverrideColor1;
    L33:
        int r9 = (int) r7;
        if (r9 == 0) goto L36;
    L38:
        if (r9 != 0) goto L40;
        r9 = r42;
    L40:
        if (r9 != r42) goto L41;
        r34 = r34 + 1;
        goto L27
    L41:
        return true;
    L36:
        if (r7 == 0) goto L38;
        r9 = 0;
        goto L40
    L30:
        if (r34 != 1) goto L32;
        r42 = Theme.getDefaultAccentColor(Theme.key_chat_wallpaper_gradient_to2);
        r7 = this.accent.backgroundGradientOverrideColor2;
        goto L33
    L32:
        r42 = Theme.getDefaultAccentColor(Theme.key_chat_wallpaper_gradient_to3);
        r7 = this.accent.backgroundGradientOverrideColor3;
        goto L33
    L12:
        if (r3 == this.accent.backgroundOverrideColor) goto L19;
        return true;
    L8:
        if (r11 != 2) goto L53;
        goto L9
    }

    private void invalidateBlur() {
        FrameLayout r0 = this.dimmingSliderContainer;
        if (r0 == null) goto L5;
        r0.invalidate();
    L5:
        FrameLayout r02 = this.backgroundButtonsContainer;
        int r1 = 0;
        if (r02 == null) goto L10;
        int r03 = r02.getChildCount();
        int r2 = 0;
    L8:
        if (r2 >= r03) goto L10;
        this.backgroundButtonsContainer.getChildAt(r2).invalidate();
        r2 = r2 + 1;
    L10:
        FrameLayout r04 = this.messagesButtonsContainer;
        if (r04 == null) goto L16;
        int r05 = r04.getChildCount();
        int r22 = 0;
    L13:
        if (r22 >= r05) goto L16;
        this.messagesButtonsContainer.getChildAt(r22).invalidate();
        r22 = r22 + 1;
    L16:
        if (this.backgroundCheckBoxView == null) goto L29;
        int r06 = 0;
    L18:
        WallpaperCheckBoxView[] r23 = this.backgroundCheckBoxView;
        if (r06 >= r23.length) goto L29;
        WallpaperCheckBoxView r24 = r23[r06];
        if (r24 == null) goto L27;
        if (this.shouldShowBrightnessControll == false) goto L25;
        float r3 = this.dimAmount * this.progressToDarkTheme;
    L26:
        r24.setDimAmount(r3);
        this.backgroundCheckBoxView[r06].invalidate();
        goto L27
    L25:
        r3 = 0.0f;
    L27:
        r06 = r06 + 1;
    L29:
        if (this.listView == null) goto L38;
        int r07 = 0;
    L32:
        if (r07 >= this.listView.getChildCount()) goto L38;
        View r25 = this.listView.getChildAt(r07);
        if ((r25 instanceof ChatActionCell) == false) goto L36;
        setVisiblePart((ChatActionCell) r25);
        r25.invalidate();
    L36:
        r07 = r07 + 1;
    L38:
        if (this.listView2 != null) goto L40;
    L45:
        BlurButton r08 = this.applyButton1;
        if (r08 == null) goto L48;
        r08.invalidate();
    L48:
        BlurButton r09 = this.applyButton2;
        if (r09 == null) goto L51;
        r09.invalidate();
    L51:
        FrameLayout r5 = this.bottomOverlayChat;
        if (r5 == null) goto L66;
        r5.invalidate();
        return;
    L66:
        return;
    L40:
        if (r1 >= this.listView2.getChildCount()) goto L45;
        View r010 = this.listView2.getChildAt(r1);
        if ((r010 instanceof ChatActionCell) == false) goto L44;
        setVisiblePart((ChatActionCell) r010);
        r010.invalidate();
    L44:
        r1 = r1 + 1;
        goto L40
    }

    private /* synthetic */ void lambda$applyWallpaperBackground$19() {
        presentFragment(StatisticActivity.create(getMessagesController().getChat(Long.valueOf(-this.dialogId))));
    }

    private /* synthetic */ void lambda$applyWallpaperBackground$20(ChannelBoostsController.CanApplyBoost r8) {
        if (getContext() != null) goto L5;
        return;
    L5:
        LimitReachedBottomSheet r1 = new LimitReachedBottomSheet(this, getContext(), 23, this.currentAccount, getResourceProvider());
        r1.setCanApplyBoost(r8);
        r1.setBoostsStats(this.boostsStatus, true);
        r1.setDialogId(this.dialogId);
        if (insideBottomSheet() == true) goto L8;
        r1.showStatisticButtonInLink(new ThemePreviewActivity$$ExternalSyntheticLambda31(this));
    L8:
        showDialog(r1);
    }

    private /* synthetic */ void lambda$checkBoostsLevel$1(TL_stories.TL_premium_boostsStatus r1) {
        this.boostsStatus = r1;
        this.checkedBoostsLevel = true;
        updateApplyButton1(true);
        this.checkingBoostsLevel = false;
    }

    private /* synthetic */ void lambda$checkDiscard$25(AlertDialog r1, int r2) {
        this.actionBar2.getActionBarMenuOnItemClick().onItemClick(4);
    }

    private /* synthetic */ void lambda$checkDiscard$26(AlertDialog r1, int r2) {
        cancelThemeApply(false);
    }

    private /* synthetic */ void lambda$createView$10(Float r1) {
        this.dimAmount = r1.floatValue();
        this.backgroundImage.invalidate();
        invalidateBlur();
    }

    private /* synthetic */ void lambda$createView$11(int r4, WallpaperCheckBoxView r5, View r6) {
        if (this.backgroundButtonsContainer.getAlpha() == 1.0f) goto L5;
        return;
    L5:
        if (this.patternViewAnimation != null) goto L68;
        int r62 = this.screenType;
        if (r62 == 1) goto L12;
        if ((this.currentWallpaper instanceof WallpapersListActivity.ColorWallpaper) == true) goto L12;
    L15:
        boolean r1 = false;
        if (r4 != 1) goto L50;
        if (r62 == 1) goto L21;
        if ((this.currentWallpaper instanceof WallpapersListActivity.ColorWallpaper) == false) goto L50;
    L21:
        if (this.backgroundCheckBoxView[1].isChecked() == false) goto L33;
        this.lastSelectedPattern = this.selectedPattern;
        this.backgroundImage.setImageDrawable(null);
        this.selectedPattern = null;
        this.isMotion = false;
        updateButtonState(false, true);
        animateMotionChange();
        if (this.patternLayout[1].getVisibility() == 0) goto L25;
    L44:
        WallpaperCheckBoxView r42 = this.backgroundCheckBoxView[1];
        if (this.selectedPattern == null) goto L47;
        r1 = true;
    L47:
        r42.setChecked(r1, true);
        updateSelectedPattern(true);
        this.patternsListView.invalidateViews();
        updateMotionButton();
        return;
    L25:
        if (this.screenType != 1) goto L28;
        showPatternsView(0, true, true);
        goto L44
    L28:
        if (this.patternLayout[r4].getVisibility() == 0) goto L30;
        boolean r52 = true;
    L31:
        showPatternsView(r4, r52, true);
        goto L44
    L30:
        r52 = false;
        goto L31
    L33:
        if (this.lastSelectedPattern == null) goto L35;
        int r53 = -1;
    L36:
        selectPattern(r53);
        if (this.screenType != 1) goto L40;
        showPatternsView(1, true, true);
        goto L44
    L40:
        if (this.patternLayout[r4].getVisibility() == 0) goto L42;
        boolean r54 = true;
    L43:
        showPatternsView(r4, r54, true);
        goto L44
    L42:
        r54 = false;
        goto L43
    L35:
        r53 = 0;
    L50:
        if ((this.currentWallpaper instanceof WallpapersListActivity.ColorWallpaper) == true) goto L52;
        if (r62 == 1) goto L67;
        r5.setChecked(!r5.isChecked(), true);
        if (r4 != 0) goto L64;
        boolean r43 = r5.isChecked();
        this.isBlurred = r43;
        if (r43 == false) goto L62;
        this.backgroundImage.getImageReceiver().setForceCrossfade(true);
    L62:
        updateBlurred();
        return;
    L64:
        boolean r44 = r5.isChecked();
        this.isMotion = r44;
        this.parallaxEffect.setEnabled(r44);
        animateMotionChange();
        return;
    L67:
        return;
    L52:
        if (this.patternLayout[r4].getVisibility() == 0) goto L54;
        r1 = true;
    L54:
        showPatternsView(r4, r1, true);
        return;
    L12:
        if (r4 != 2) goto L15;
        r5.setChecked(!r5.isChecked(), true);
        boolean r45 = r5.isChecked();
        this.isMotion = r45;
        this.parallaxEffect.setEnabled(r45);
        animateMotionChange();
        return;
    }

    private /* synthetic */ void lambda$createView$12(int r2, WallpaperCheckBoxView r3, View r4) {
        if (this.messagesButtonsContainer.getAlpha() != 1.0f) goto L9;
        if (r2 != 0) goto L8;
        r3.setChecked(!r3.isChecked(), true);
        this.accent.myMessagesAnimated = r3.isChecked();
        Theme.refreshThemeColors(true, true);
        this.listView2.invalidateViews();
        return;
    L8:
        return;
    }

    private /* synthetic */ void lambda$createView$13(int r14, View r15) {
        if (this.patternViewAnimation == null) goto L6;
        return;
    L6:
        if (r14 != 0) goto L8;
        this.backgroundRotation = this.previousBackgroundRotation;
        setBackgroundColor(this.previousBackgroundGradientColor3, 3, true, true);
        setBackgroundColor(this.previousBackgroundGradientColor2, 2, true, true);
        setBackgroundColor(this.previousBackgroundGradientColor1, 1, true, true);
        setBackgroundColor(this.previousBackgroundColor, 0, true, true);
    L18:
        if (this.screenType != 2) goto L22;
        showPatternsView(r14, false, true);
        return;
    L22:
        if (this.selectedPattern == null) goto L24;
    L27:
        showPatternsView(0, true, true);
        return;
    L24:
        if (this.isMotion == false) goto L26;
        this.isMotion = false;
        this.backgroundCheckBoxView[0].setChecked(false, true);
        animateMotionChange();
    L26:
        updateMotionButton();
        goto L27
    L8:
        TLRPC.TL_wallPaper r2 = this.previousSelectedPattern;
        this.selectedPattern = r2;
        BackgroundView r3 = this.backgroundImage;
        if (r2 != null) goto L11;
        r3.setImageDrawable(null);
    L12:
        WallpaperCheckBoxView r22 = this.backgroundCheckBoxView[1];
        if (this.selectedPattern == null) goto L15;
        boolean r32 = true;
    L16:
        r22.setChecked(r32, false);
        float r23 = this.previousIntensity;
        this.currentIntensity = r23;
        this.intensitySeekBar.setProgress(r23);
        this.backgroundImage.getImageReceiver().setAlpha(this.currentIntensity);
        updateButtonState(false, true);
        updateSelectedPattern(true);
        goto L18
    L15:
        r32 = false;
        goto L16
    L11:
        ImageLocation r4 = ImageLocation.getForDocument(r2.document);
        String r5 = this.imageFilter;
        TLRPC.TL_wallPaper r12 = this.selectedPattern;
        r3.setImage(r4, r5, null, null, "jpg", r12.document.size, 1, r12);
        goto L12
    }

    private /* synthetic */ void lambda$createView$14(int r4, View r5) {
        if (this.patternViewAnimation == null) goto L6;
        return;
    L6:
        if (this.screenType != 2) goto L9;
        showPatternsView(r4, false, true);
        return;
    L9:
        showPatternsView(0, true, true);
    }

    private /* synthetic */ void lambda$createView$15(View r4, int r5) {
        if (this.selectedPattern == null) goto L5;
        boolean r0 = true;
    L6:
        selectPattern(r5);
        if (this.selectedPattern != null) goto L9;
        boolean r52 = true;
    L10:
        if (r0 != r52) goto L12;
        animateMotionChange();
        updateMotionButton();
    L12:
        updateSelectedPattern(true);
        WallpaperCheckBoxView r53 = this.backgroundCheckBoxView[1];
        if (this.selectedPattern == null) goto L15;
        boolean r02 = true;
    L16:
        r53.setChecked(r02, true);
        this.patternsListView.invalidateViews();
        int r54 = r4.getLeft();
        int r42 = r4.getRight();
        int r03 = AndroidUtilities.dp(52.0f);
        int r55 = r54 - r03;
        RecyclerListView r2 = this.patternsListView;
        if (r55 >= 0) goto L20;
        r2.smoothScrollBy(r55, 0);
        return;
    L20:
        int r43 = r42 + r03;
        if (r43 <= r2.getMeasuredWidth()) goto L24;
        RecyclerListView r3 = this.patternsListView;
        r3.smoothScrollBy(r43 - r3.getMeasuredWidth(), 0);
        return;
    L24:
        return;
    L15:
        r02 = false;
        goto L16
    L9:
        r52 = false;
        goto L10
    L5:
        r0 = false;
        goto L6
    }

    private /* synthetic */ void lambda$createView$16() {
        this.watchForKeyboardEndTime = SystemClock.elapsedRealtime() + 1500;
        this.frameLayout.invalidate();
    }

    private /* synthetic */ void lambda$createView$17(View r1) {
        cancelThemeApply(false);
    }

    private /* synthetic */ void lambda$createView$18(View r6) {
        Theme.ThemeInfo r62 = Theme.getPreviousTheme();
        if (r62 != null) goto L5;
        return;
    L5:
        int r0 = r62.prevAccentId;
        if (r0 < 0) goto L8;
        Theme.ThemeAccent r02 = r62.themeAccentsMap.get(r0);
    L10:
        if (this.accent == null) goto L12;
        saveAccentWallpaper();
        Theme.saveThemeAccents(this.applyingTheme, true, false, false, false);
        Theme.clearPreviousTheme();
        Theme.applyTheme(this.applyingTheme, this.nightTheme);
        this.parentLayout.rebuildAllFragmentViews(false, false);
    L13:
        BaseFragment r1 = getParentLayout().getFragmentStack().get(Math.max(0, getParentLayout().getFragmentStack().size() - 2));
        finishFragment();
        if (this.screenType != 0) goto L16;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didApplyNewTheme, new Object[]{r62, r02, Boolean.valueOf(this.deleteOnCancel)});
    L16:
        Theme.turnOffAutoNight(r1);
        return;
    L12:
        this.parentLayout.rebuildAllFragmentViews(false, false);
        File r2 = new File(this.applyingTheme.pathToFile);
        Theme.ThemeInfo r3 = this.applyingTheme;
        Theme.applyThemeFile(r2, r3.name, r3.info, false);
        MessagesController.getInstance(this.applyingTheme.account).saveTheme(this.applyingTheme, null, false, false);
        SharedPreferences.Editor r22 = ApplicationLoader.applicationContext.getSharedPreferences("themeconfig", 0).edit();
        r22.putString("lastDayTheme", this.applyingTheme.getKey());
        r22.apply();
        goto L13
    L8:
        r02 = r62.getAccent(false);
        goto L10
    }

    private /* synthetic */ void lambda$createView$2() {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        if (getContext() == null) goto L14;
        SharedConfig.increaseDayNightWallpaperSiwtchHint();
        HintView r0 = new HintView(getContext(), 7, true);
        r0.setAlpha(0.0f);
        r0.setVisibility(4);
        r0.setShowingDuration(4000);
        this.frameLayout.addView(r0, LayoutHelper.createFrame(-2, -2.0f, 51, 4.0f, 0.0f, 4.0f, 0.0f));
        if (this.onSwitchDayNightDelegate.isDark() == false) goto L10;
        r0.setText(LocaleController.getString(R.string.PreviewWallpaperDay));
    L11:
        r0.setBackgroundColor(-366530760, -1);
        r0.showForView(this.dayNightItem, true);
        r0.setExtraTranslationY(-AndroidUtilities.dp(14.0f));
        return;
    L10:
        r0.setText(LocaleController.getString(R.string.PreviewWallpaperNight));
        goto L11
    }

    private /* synthetic */ void lambda$createView$4(ImageReceiver r3, boolean r4, boolean r5, boolean r6) {
        if ((this.currentWallpaper instanceof WallpapersListActivity.ColorWallpaper) == true) goto L16;
        Drawable r32 = r3.getDrawable();
        if (r4 == false) goto L17;
        if (r32 == null) goto L18;
        this.themeDelegate.applyChatServiceMessageColor(AndroidUtilities.calcDrawableColor(r32), checkBlur(r32), r32, Float.valueOf(this.currentIntensity));
        if (r5 == false) goto L10;
    L14:
        invalidateBlur();
        return;
    L10:
        if (this.isBlurred == false) goto L14;
        if (this.blurredBitmap != null) goto L14;
        this.backgroundImage.getImageReceiver().setCrossfadeWithOldImage(false);
        updateBlurred();
        this.backgroundImage.getImageReceiver().setCrossfadeWithOldImage(true);
        goto L14
    L18:
        return;
    L17:
        return;
    }

    private /* synthetic */ void lambda$createView$5(View r1) {
        this.dropDownContainer.toggleSubMenu();
    }

    private /* synthetic */ void lambda$createView$6(View r1, int r2, float r3, float r4) {
        if ((r1 instanceof ChatMessageCell) == false) goto L14;
        ChatMessageCell r12 = (ChatMessageCell) r1;
        if (r12.isInsideBackground(r3, r4) == true) goto L7;
        selectColorType(2);
        return;
    L7:
        if (r12.getMessageObject().isOutOwner() == false) goto L10;
        selectColorType(3);
        return;
    L10:
        selectColorType(1);
        return;
    }

    private /* synthetic */ void lambda$createView$7(int r3, int r4, float r5) {
        if (this.isMotion == true) goto L5;
        return;
    L5:
        this.backgroundImage.getBackground();
        float r0 = 1.0f;
        if (this.motionAnimation == null) goto L8;
        r0 = (this.backgroundImage.getScaleX() - 1.0f) / (this.parallaxScale - 1.0f);
    L8:
        this.backgroundImage.setTranslationX(r3 * r0);
        this.backgroundImage.setTranslationY(r4 * r0);
    }

    private /* synthetic */ void lambda$createView$8(View r1) {
        applyWallpaperBackground(false);
    }

    private /* synthetic */ void lambda$createView$9(View r1) {
        applyWallpaperBackground(true);
    }

    private /* synthetic */ void lambda$didReceivedNotification$28(TLObject r3) {
        if ((r3 instanceof TLRPC.TL_wallPaper) == false) goto L10;
        TLRPC.TL_wallPaper r32 = (TLRPC.TL_wallPaper) r3;
        if (r32.pattern == false) goto L11;
        this.selectedPattern = r32;
        setCurrentImage(false);
        updateButtonState(false, false);
        this.patterns.add(0, this.selectedPattern);
        PatternsAdapter r2 = this.patternsAdapter;
        if (r2 == null) goto L12;
        r2.notifyDataSetChanged();
        return;
    L12:
        return;
    L11:
        return;
    }

    private /* synthetic */ void lambda$didReceivedNotification$29(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new ThemePreviewActivity$$ExternalSyntheticLambda36(this, r1));
    }

    private /* synthetic */ void lambda$didReceivedNotification$30(TLObject r11) {
        if ((r11 instanceof TL_account.TL_wallPapers) == false) goto L44;
        TL_account.TL_wallPapers r112 = (TL_account.TL_wallPapers) r11;
        this.patterns.clear();
        this.patternsDict.clear();
        int r0 = r112.wallpapers.size();
        int r2 = 0;
        boolean r3 = false;
    L6:
        if (r2 >= r0) goto L35;
        if ((r112.wallpapers.get(r2) instanceof TLRPC.TL_wallPaper) == false) goto L34;
        TLRPC.TL_wallPaper r5 = (TLRPC.TL_wallPaper) r112.wallpapers.get(r2);
        if (r5.pattern == false) goto L34;
        TLRPC.Document r6 = r5.document;
        if (r6 != null) goto L15;
    L17:
        Theme.ThemeAccent r62 = this.accent;
        if (r62 == null) goto L26;
        String r63 = r62.patternSlug;
        if (r63 == null) goto L26;
        if (r63.equals(r5.slug) == false) goto L26;
        this.selectedPattern = r5;
        setCurrentImage(false);
        updateButtonState(false, false);
    L24:
        r3 = true;
    L26:
        if (this.accent != null) goto L34;
        TLRPC.TL_wallPaper r64 = this.selectedPattern;
        if (r64 == null) goto L34;
        String r65 = r64.slug;
        if (r65 == null) goto L34;
        if (r65.equals(r5.slug) == false) goto L34;
    L15:
        if (this.patternsDict.containsKey(Long.valueOf(r6.id)) == true) goto L17;
        this.patterns.add(r5);
        this.patternsDict.put(Long.valueOf(r5.document.id), r5);
    L34:
        r2 = r2 + 1;
        goto L6
    L35:
        if (r3 == true) goto L39;
        TLRPC.TL_wallPaper r02 = this.selectedPattern;
        if (r02 == null) goto L39;
        this.patterns.add(0, r02);
    L39:
        PatternsAdapter r03 = this.patternsAdapter;
        if (r03 == null) goto L42;
        r03.notifyDataSetChanged();
    L42:
        MessagesStorage.getInstance(this.currentAccount).putWallpapers(r112.wallpapers, 1);
    L44:
        if (this.selectedPattern != null) goto L59;
        Theme.ThemeAccent r113 = this.accent;
        if (r113 != null) goto L48;
        return;
    L48:
        if (TextUtils.isEmpty(r113.patternSlug) == true) goto L61;
        TL_account.getWallPaper r114 = new TL_account.getWallPaper();
        TLRPC.TL_inputWallPaperSlug r04 = new TLRPC.TL_inputWallPaperSlug();
        r04.slug = this.accent.patternSlug;
        r114.wallpaper = r04;
        ConnectionsManager.getInstance(this.currentAccount).bindRequestToGuid(getConnectionsManager().sendRequest(r114, new ThemePreviewActivity$$ExternalSyntheticLambda34(this)), this.classGuid);
        return;
    L61:
        return;
    }

    private /* synthetic */ void lambda$didReceivedNotification$31(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new ThemePreviewActivity$$ExternalSyntheticLambda24(this, r1));
    }

    private /* synthetic */ void lambda$getThemeDescriptionsInternal$33() {
        ActionBarMenuItem r0 = this.dropDownContainer;
        int r1 = 0;
        if (r0 == null) goto L5;
        r0.redrawPopup(getThemedColor(Theme.key_actionBarDefaultSubmenuBackground));
        this.dropDownContainer.setPopupItemsColor(getThemedColor(Theme.key_actionBarDefaultSubmenuItem), false);
    L5:
        Drawable r02 = this.sheetDrawable;
        if (r02 == null) goto L8;
        r02.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_windowBackgroundWhite), PorterDuff.Mode.MULTIPLY));
    L8:
        FrameLayout r03 = this.bottomOverlayChat;
        if (r03 == null) goto L12;
        r03.invalidate();
    L12:
        if (this.onSwitchDayNightDelegate == null) goto L24;
        INavigationLayout r04 = this.parentLayout;
        if (r04 != null) goto L16;
    L22:
        setNavigationBarColor(getThemedColor(Theme.key_dialogBackground));
        goto L24
    L16:
        if (r04.getBottomSheet() == null) goto L22;
        this.parentLayout.getBottomSheet().fixNavigationBar(getThemedColor(Theme.key_dialogBackground));
        if (this.screenType != 2) goto L24;
        if (this.dialogId == 0) goto L24;
        this.parentLayout.getBottomSheet().setOverlayNavBarColor(-16777216);
    L24:
        if (this.backgroundCheckBoxView == null) goto L33;
        int r05 = 0;
    L26:
        WallpaperCheckBoxView[] r2 = this.backgroundCheckBoxView;
        if (r05 >= r2.length) goto L33;
        WallpaperCheckBoxView r22 = r2[r05];
        if (r22 == null) goto L31;
        r22.invalidate();
    L31:
        r05 = r05 + 1;
    L33:
        if (this.messagesCheckBoxView == null) goto L40;
    L34:
        WallpaperCheckBoxView[] r06 = this.messagesCheckBoxView;
        if (r1 >= r06.length) goto L40;
        WallpaperCheckBoxView r07 = r06[r1];
        if (r07 == null) goto L39;
        r07.invalidate();
    L39:
        r1 = r1 + 1;
    L40:
        TextView r08 = this.patternTitleView;
        if (r08 == null) goto L43;
        r08.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
    L43:
        ColorPicker r09 = this.colorPicker;
        if (r09 == null) goto L46;
        r09.invalidate();
    L46:
        FragmentFloatingButton r6 = this.floatingButton;
        if (r6 == null) goto L56;
        r6.updateColors();
        return;
    }

    private /* synthetic */ void lambda$new$0() {
        this.applyColorScheduled = false;
        applyColor(this.lastPickedColor, this.lastPickedColorNum);
        this.lastPickedColorNum = -1;
    }

    private /* synthetic */ void lambda$selectColorType$22(AlertDialog r5, int r6) {
        Theme.ThemeAccent r52 = this.accent;
        if (r52.backgroundOverrideColor != 4294967296L) goto L5;
        r52.backgroundOverrideColor = 0;
        r52.backgroundGradientOverrideColor1 = 0;
        r52.backgroundGradientOverrideColor2 = 0;
        r52.backgroundGradientOverrideColor3 = 0;
        updatePlayAnimationView(false);
        Theme.refreshThemeColors();
    L5:
        this.removeBackgroundOverride = true;
        Theme.resetCustomWallpaper(true);
        selectColorType(2, false);
    }

    private /* synthetic */ void lambda$selectColorType$23(AlertDialog r7, int r8) {
        if (Theme.isCustomWallpaperColor() == false) goto L22;
        Theme.ThemeAccent r72 = this.accent;
        Theme.OverrideWallpaperInfo r2 = r72.overrideWallpaper;
        r72.backgroundOverrideColor = r2.color;
        r72.backgroundGradientOverrideColor1 = r2.gradientColor1;
        r72.backgroundGradientOverrideColor2 = r2.gradientColor2;
        r72.backgroundGradientOverrideColor3 = r2.gradientColor3;
        r72.backgroundRotation = r2.rotation;
        String r3 = r2.slug;
        r72.patternSlug = r3;
        float r22 = r2.intensity;
        r72.patternIntensity = r22;
        this.currentIntensity = r22;
        if (r3 != null) goto L7;
    L16:
        this.selectedPattern = null;
    L17:
        this.removeBackgroundOverride = true;
        WallpaperCheckBoxView r73 = this.backgroundCheckBoxView[1];
        if (this.selectedPattern == null) goto L20;
        boolean r23 = true;
    L21:
        r73.setChecked(r23, true);
        updatePlayAnimationView(false);
        Theme.refreshThemeColors();
        goto L22
    L20:
        r23 = false;
        goto L21
    L7:
        if ("c".equals(r3) == true) goto L16;
        int r74 = this.patterns.size();
        int r24 = 0;
    L9:
        if (r24 >= r74) goto L17;
        TLRPC.TL_wallPaper r32 = (TLRPC.TL_wallPaper) this.patterns.get(r24);
        if (r32.pattern == false) goto L15;
        if (this.accent.patternSlug.equals(r32.slug) == false) goto L15;
        this.selectedPattern = r32;
    L15:
        r24 = r24 + 1;
    L22:
        Drawable r75 = this.backgroundImage.getBackground();
        if ((r75 instanceof MotionBackgroundDrawable) == false) goto L35;
        MotionBackgroundDrawable r76 = (MotionBackgroundDrawable) r75;
        r76.setPatternBitmap(100, null);
        boolean r82 = Theme.getActiveTheme().isDark();
        float r25 = this.currentIntensity;
        if (r82 == false) goto L33;
        if (r25 >= 0.0f) goto L29;
        this.backgroundImage.getImageReceiver().setGradientBitmap(r76.getBitmap());
    L29:
        SeekBarView r77 = this.intensitySeekBar;
        if (r77 == null) goto L35;
        r77.setTwoSided(true);
        goto L35
    L33:
        if (r25 >= 0.0f) goto L35;
        this.currentIntensity = -r25;
    L35:
        SeekBarView r78 = this.intensitySeekBar;
        if (r78 == null) goto L38;
        r78.setProgress(this.currentIntensity);
    L38:
        Theme.resetCustomWallpaper(true);
        selectColorType(2, false);
    }

    private /* synthetic */ void lambda$selectColorType$24(AlertDialog r5, int r6) {
        Theme.ThemeAccent r52 = this.accent;
        if (r52.backgroundOverrideColor != 4294967296L) goto L5;
        r52.backgroundOverrideColor = 0;
        r52.backgroundGradientOverrideColor1 = 0;
        r52.backgroundGradientOverrideColor2 = 0;
        r52.backgroundGradientOverrideColor3 = 0;
        updatePlayAnimationView(false);
        Theme.refreshThemeColors();
    L5:
        this.removeBackgroundOverride = true;
        Theme.resetCustomWallpaper(true);
        selectColorType(2, false);
    }

    private /* synthetic */ void lambda$showAnimationHint$32(SharedPreferences r3) {
        if (this.colorType == 3) goto L5;
        return;
    L5:
        r3.edit().putBoolean("bganimationhint", true).apply();
        this.animationHint.showForView(this.messagesCheckBoxView[0], true);
    }

    private /* synthetic */ void lambda$toggleTheme$35(ValueAnimator r2) {
        this.progressToDarkTheme = ((Float) r2.getAnimatedValue()).floatValue();
        this.backgroundImage.invalidate();
        this.bottomOverlayChat.invalidate();
        this.dimmingSlider.setAlpha(this.progressToDarkTheme);
        this.dimmingSliderContainer.invalidate();
        invalidateBlur();
    }

    private /* synthetic */ void lambda$toggleTheme$36() {
        this.onSwitchDayNightDelegate.switchDayNight(false);
        setForceDark(this.themeDelegate.isDark(), true);
        setCurrentImage(false);
        invalidateBlur();
        updateBlurred();
        if (this.themeDescriptions == null) goto L9;
        int r0 = 0;
    L6:
        if (r0 >= this.themeDescriptions.size()) goto L9;
        this.themeDescriptions.get(r0).setColor(getThemedColor(this.themeDescriptions.get(r0).getCurrentKey()), false, false);
        r0 = r0 + 1;
    L9:
        if (this.shouldShowBrightnessControll == false) goto L25;
        DayNightSwitchDelegate r02 = this.onSwitchDayNightDelegate;
        float r3 = 0.0f;
        if (r02 != null) goto L13;
    L15:
        this.dimmingSlider.animateValueTo(0.0f);
    L16:
        ValueAnimator r03 = this.changeDayNightViewAnimator2;
        if (r03 == null) goto L19;
        r03.removeAllListeners();
        this.changeDayNightViewAnimator2.cancel();
    L19:
        float r04 = this.progressToDarkTheme;
        if (this.onSwitchDayNightDelegate.isDark() == false) goto L22;
        r3 = 1.0f;
    L22:
        ValueAnimator r05 = ValueAnimator.ofFloat(new float[]{r04, r3});
        this.changeDayNightViewAnimator2 = r05;
        r05.addUpdateListener(new ThemePreviewActivity$$ExternalSyntheticLambda35(this));
        this.changeDayNightViewAnimator2.addListener(new AnonymousClass43(this));
        this.changeDayNightViewAnimator2.setDuration(250);
        this.changeDayNightViewAnimator2.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.changeDayNightViewAnimator2.start();
        return;
    L13:
        if (r02.isDark() == false) goto L15;
        this.dimmingSlider.setVisibility(0);
        this.dimmingSlider.animateValueTo(this.dimAmount);
        goto L16
    }

    private void onColorsRotate() {
        if (this.screenType != 2) goto L10;
        this.backgroundRotation += 45;
    L5:
        int r0 = this.backgroundRotation;
        if (r0 < 360) goto L8;
        this.backgroundRotation = r0 - 360;
        goto L5
    L8:
        setBackgroundColor(this.backgroundColor, 0, true, true);
        return;
    L10:
        Theme.ThemeAccent r02 = this.accent;
        if (r02 == null) goto L20;
        r02.backgroundRotation += 45;
    L13:
        Theme.ThemeAccent r03 = this.accent;
        int r1 = r03.backgroundRotation;
        if (r1 < 360) goto L16;
        r03.backgroundRotation = r1 - 360;
        goto L13
    L16:
        Theme.refreshThemeColors();
        return;
    }

    private void saveAccentWallpaper() {
        Theme.ThemeAccent r0 = this.accent;
        if (r0 != null) goto L5;
        return;
    L5:
        if (TextUtils.isEmpty(r0.patternSlug) == true) goto L18;
        File r02 = this.accent.getPathToWallpaper();     // Catch: Throwable -> L13
        Drawable r1 = this.backgroundImage.getBackground();     // Catch: Throwable -> L13
        Bitmap r2 = this.backgroundImage.getImageReceiver().getBitmap();     // Catch: Throwable -> L13
        if ((r1 instanceof MotionBackgroundDrawable) == false) goto L11;
        FileOutputStream r9 = new FileOutputStream(r02);     // Catch: Throwable -> L13
        r2.compress(Bitmap.CompressFormat.PNG, 87, r9);     // Catch: Throwable -> L13
        r9.close();     // Catch: Throwable -> L13
        return;
    L11:
        Bitmap r3 = Bitmap.createBitmap(r2.getWidth(), r2.getHeight(), Bitmap.Config.ARGB_8888);     // Catch: Throwable -> L13
        Canvas r5 = new Canvas(r3);     // Catch: Throwable -> L13
        r1.setBounds(0, 0, r2.getWidth(), r2.getHeight());     // Catch: Throwable -> L13
        r1.draw(r5);     // Catch: Throwable -> L13
        Paint r12 = new Paint(2);     // Catch: Throwable -> L13
        r12.setColorFilter(new PorterDuffColorFilter(this.patternColor, this.blendMode));     // Catch: Throwable -> L13
        r12.setAlpha((int) (this.currentIntensity * 255.0f));     // Catch: Throwable -> L13
        r5.drawBitmap(r2, 0.0f, 0.0f, r12);     // Catch: Throwable -> L13
        FileOutputStream r92 = new FileOutputStream(r02);     // Catch: Throwable -> L13
        r3.compress(Bitmap.CompressFormat.JPEG, 87, r92);     // Catch: Throwable -> L13
        r92.close();     // Catch: Throwable -> L13
        return;
    L13:
        th = move-exception;
        FileLog.e(th);
        return;
    }

    private void scheduleApplyColor(int r12, int r13, boolean r14) {
        if (r13 != (-1)) goto L65;
        int r122 = this.colorType;
        if (r122 == 1) goto L7;
        if (r122 == 2) goto L7;
    L38:
        int r123 = this.colorType;
        if (r123 == 1) goto L41;
        if (r123 == 3) goto L41;
    L63:
        Theme.refreshThemeColors();
        this.listView2.invalidateViews();
        return;
    L41:
        int r2 = this.backupMyMessagesAccentColor;
        Theme.ThemeAccent r3 = this.accent;
        if (r2 == 0) goto L44;
        r3.myMessagesAccentColor = r2;
    L45:
        int r22 = this.backupMyMessagesGradientAccentColor1;
        Theme.ThemeAccent r32 = this.accent;
        if (r22 == 0) goto L48;
        r32.myMessagesGradientAccentColor1 = r22;
    L49:
        int r23 = this.backupMyMessagesGradientAccentColor2;
        Theme.ThemeAccent r33 = this.accent;
        if (r23 == 0) goto L52;
        r33.myMessagesGradientAccentColor2 = r23;
    L53:
        int r24 = this.backupMyMessagesGradientAccentColor3;
        Theme.ThemeAccent r34 = this.accent;
        if (r24 == 0) goto L56;
        r34.myMessagesGradientAccentColor3 = r24;
    L57:
        if (r123 != 3) goto L63;
        this.colorPicker.setColor(this.accent.myMessagesGradientAccentColor3, 3);
        this.colorPicker.setColor(this.accent.myMessagesGradientAccentColor2, 2);
        this.colorPicker.setColor(this.accent.myMessagesGradientAccentColor1, 1);
        ColorPicker r124 = this.colorPicker;
        Theme.ThemeAccent r132 = this.accent;
        int r142 = r132.myMessagesAccentColor;
        if (r142 != 0) goto L62;
        r142 = r132.accentColor;
    L62:
        r124.setColor(r142, 0);
        goto L63
    L56:
        r34.myMessagesGradientAccentColor3 = 0;
        goto L57
    L52:
        r33.myMessagesGradientAccentColor2 = 0;
        goto L53
    L48:
        r32.myMessagesGradientAccentColor1 = 0;
        goto L49
    L44:
        r3.myMessagesAccentColor = 0;
    L7:
        long r25 = this.backupBackgroundOverrideColor;
        Theme.ThemeAccent r7 = this.accent;
        if (r25 == 0) goto L10;
        r7.backgroundOverrideColor = r25;
    L11:
        long r26 = this.backupBackgroundGradientOverrideColor1;
        Theme.ThemeAccent r72 = this.accent;
        if (r26 == 0) goto L14;
        r72.backgroundGradientOverrideColor1 = r26;
    L15:
        long r27 = this.backupBackgroundGradientOverrideColor2;
        Theme.ThemeAccent r73 = this.accent;
        if (r27 == 0) goto L18;
        r73.backgroundGradientOverrideColor2 = r27;
    L19:
        long r28 = this.backupBackgroundGradientOverrideColor3;
        Theme.ThemeAccent r74 = this.accent;
        if (r28 == 0) goto L22;
        r74.backgroundGradientOverrideColor3 = r28;
    L23:
        Theme.ThemeAccent r29 = this.accent;
        r29.backgroundRotation = this.backupBackgroundRotation;
        if (r122 != 2) goto L38;
        int r125 = Theme.getDefaultAccentColor(Theme.key_chat_wallpaper);
        int r210 = Theme.getDefaultAccentColor(Theme.key_chat_wallpaper_gradient_to1);
        int r35 = Theme.getDefaultAccentColor(Theme.key_chat_wallpaper_gradient_to2);
        int r4 = Theme.getDefaultAccentColor(Theme.key_chat_wallpaper_gradient_to3);
        Theme.ThemeAccent r5 = this.accent;
        int r6 = (int) r5.backgroundGradientOverrideColor1;
        int r75 = (int) r5.backgroundGradientOverrideColor2;
        int r8 = (int) r5.backgroundGradientOverrideColor3;
        int r52 = (int) r5.backgroundOverrideColor;
        ColorPicker r9 = this.colorPicker;
        if (r8 == 0) goto L28;
        r4 = r8;
    L28:
        r9.setColor(r4, 3);
        ColorPicker r42 = this.colorPicker;
        if (r75 == 0) goto L31;
        r35 = r75;
    L31:
        r42.setColor(r35, 2);
        ColorPicker r36 = this.colorPicker;
        if (r6 == 0) goto L34;
        r210 = r6;
    L34:
        r36.setColor(r210, 1);
        ColorPicker r211 = this.colorPicker;
        if (r52 == 0) goto L37;
        r125 = r52;
    L37:
        r211.setColor(r125, 0);
        goto L38
    L22:
        r74.backgroundGradientOverrideColor3 = 0;
        goto L23
    L18:
        r73.backgroundGradientOverrideColor2 = 0;
        goto L19
    L14:
        r72.backgroundGradientOverrideColor1 = 0;
        goto L15
    L10:
        r7.backgroundOverrideColor = 0;
        goto L11
    L65:
        int r212 = this.lastPickedColorNum;
        if (r212 == (-1)) goto L69;
        if (r212 == r13) goto L69;
        this.applyColorAction.run();
    L69:
        this.lastPickedColor = r12;
        this.lastPickedColorNum = r13;
        if (r14 == false) goto L74;
        this.applyColorAction.run();
        return;
    L74:
        if (this.applyColorScheduled == true) goto L77;
        this.applyColorScheduled = true;
        this.fragmentView.postDelayed(this.applyColorAction, 16);
        return;
    }

    private void selectColorType(int r2) {
        selectColorType(r2, true);
    }

    private void selectColorType(int r27, boolean r28) {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        if (this.colorType != r27) goto L7;
        return;
    L7:
        if (this.patternViewAnimation == null) goto L9;
        return;
    L9:
        if (this.accent != null) goto L12;
        return;
    L12:
        if (r28 == false) goto L27;
        if (r27 != 2) goto L27;
        if (Theme.hasCustomWallpaper() == false) goto L17;
    L18:
        AlertDialog.Builder r1 = new AlertDialog.Builder(getParentActivity());
        r1.setTitle(LocaleController.getString(R.string.ChangeChatBackground));
        if (Theme.hasCustomWallpaper() == true) goto L21;
    L24:
        r1.setMessage(LocaleController.getString(R.string.ChangeColorToColor));
        r1.setPositiveButton(LocaleController.getString(R.string.Reset), new ThemePreviewActivity$$ExternalSyntheticLambda28(this));
        r1.setNegativeButton(LocaleController.getString(R.string.Continue), new ThemePreviewActivity$$ExternalSyntheticLambda29(this));
    L25:
        showDialog(r1.create());
        return;
    L21:
        if (Theme.isCustomWallpaperColor() == true) goto L24;
        r1.setMessage(LocaleController.getString(R.string.ChangeWallpaperToColor));
        r1.setPositiveButton(LocaleController.getString(R.string.Change), new ThemePreviewActivity$$ExternalSyntheticLambda30(this));
        r1.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        goto L25
    L17:
        if (this.accent.backgroundOverrideColor == 4294967296L) goto L18;
    L27:
        int r3 = this.colorType;
        this.colorType = r27;
        if (r27 != 1) goto L30;
        this.dropDown.setText(LocaleController.getString(R.string.ColorPickerMainColor));
        if (this.accent.accentColor2 == 0) goto L123;
        int r11 = 2;
    L124:
        this.colorPicker.setType(1, hasChanges(1), 2, r11, false, 0, false);
        this.colorPicker.setColor(this.accent.accentColor, 0);
        int r5 = this.accent.accentColor2;
        if (r5 == 0) goto L127;
        this.colorPicker.setColor(r5, 1);
    L127:
        if (r3 == 2) goto L131;
        if (r3 == 3) goto L130;
    L133:
        if (r27 == 1) goto L138;
        if (r27 == 3) goto L138;
        this.colorPicker.setMinBrightness(0.0f);
        this.colorPicker.setMaxBrightness(1.0f);
        return;
    L138:
        if (r3 == 2) goto L140;
    L142:
        if (r27 != 1) goto L149;
        boolean r12 = this.applyingTheme.isDark();
        ColorPicker r2 = this.colorPicker;
        if (r12 == false) goto L147;
        r2.setMinBrightness(0.2f);
        return;
    L147:
        r2.setMinBrightness(0.05f);
        this.colorPicker.setMaxBrightness(0.8f);
        return;
    L149:
        this.colorPicker.setMinBrightness(0.0f);
        this.colorPicker.setMaxBrightness(1.0f);
        return;
    L140:
        if (this.patternLayout[1].getVisibility() != 0) goto L142;
        showPatternsView(0, true, true);
        goto L142
    L130:
        if (this.accent.myMessagesGradientAccentColor2 == 0) goto L133;
    L131:
        this.messagesAdapter.notifyItemRemoved(0);
        goto L133
    L123:
        r11 = 1;
        goto L124
    L30:
        if (r27 == 2) goto L56;
        if (r27 != 3) goto L133;
        this.dropDown.setText(LocaleController.getString(R.string.ColorPickerMyMessages));
        Theme.ThemeAccent r9 = this.accent;
        if (r9.myMessagesGradientAccentColor1 != 0) goto L36;
        int r13 = 1;
    L43:
        this.colorPicker.setType(2, hasChanges(3), 4, r13, true, 0, false);
        this.colorPicker.setColor(this.accent.myMessagesGradientAccentColor3, 3);
        this.colorPicker.setColor(this.accent.myMessagesGradientAccentColor2, 2);
        this.colorPicker.setColor(this.accent.myMessagesGradientAccentColor1, 1);
        ColorPicker r8 = this.colorPicker;
        Theme.ThemeAccent r92 = this.accent;
        int r10 = r92.myMessagesAccentColor;
        if (r10 != 0) goto L47;
        r10 = r92.accentColor;
    L47:
        r8.setColor(r10, 0);
        this.messagesCheckBoxView[1].setColor(0, this.accent.myMessagesAccentColor);
        this.messagesCheckBoxView[1].setColor(1, this.accent.myMessagesGradientAccentColor1);
        this.messagesCheckBoxView[1].setColor(2, this.accent.myMessagesGradientAccentColor2);
        this.messagesCheckBoxView[1].setColor(3, this.accent.myMessagesGradientAccentColor3);
        if (this.accent.myMessagesGradientAccentColor2 == 0) goto L53;
        MessagesAdapter r82 = this.messagesAdapter;
        if (r3 != 1) goto L52;
        r82.notifyItemInserted(0);
    L55:
        this.listView2.smoothScrollBy(0, AndroidUtilities.dp(60.0f));
        showAnimationHint();
        goto L133
    L52:
        r82.notifyItemChanged(0);
        goto L55
    L53:
        if (r3 != 2) goto L55;
        this.messagesAdapter.notifyItemRemoved(0);
        goto L55
    L36:
        if (r9.myMessagesGradientAccentColor3 == 0) goto L39;
        r13 = 4;
        goto L43
    L39:
        if (r9.myMessagesGradientAccentColor2 == 0) goto L41;
        r13 = 3;
        goto L43
    L41:
        r13 = 2;
        goto L43
    L56:
        this.dropDown.setText(LocaleController.getString(R.string.ColorPickerBackground));
        int r93 = getThemedColor(Theme.key_chat_wallpaper);
        int r102 = Theme.key_chat_wallpaper_gradient_to1;
        if (Theme.hasThemeKey(r102) == false) goto L59;
        int r103 = getThemedColor(r102);
    L60:
        int r112 = Theme.key_chat_wallpaper_gradient_to2;
        if (Theme.hasThemeKey(r112) == false) goto L63;
        int r113 = getThemedColor(r112);
    L64:
        int r122 = Theme.key_chat_wallpaper_gradient_to3;
        if (Theme.hasThemeKey(r122) == false) goto L67;
        int r123 = getThemedColor(r122);
    L68:
        Theme.ThemeAccent r132 = this.accent;
        long r14 = r132.backgroundGradientOverrideColor1;
        int r7 = (int) r14;
        if (r7 == 0) goto L71;
    L73:
        long r142 = r132.backgroundGradientOverrideColor2;
        int r83 = (int) r142;
        if (r83 == 0) goto L76;
    L78:
        long r143 = r132.backgroundGradientOverrideColor3;
        int r52 = (int) r143;
        if (r52 == 0) goto L81;
    L83:
        int r133 = (int) r132.backgroundOverrideColor;
        if (r7 != 0) goto L88;
        if (r103 != 0) goto L88;
        int r22 = 1;
    L97:
        this.colorPicker.setType(2, hasChanges(2), 4, r22, false, this.accent.backgroundRotation, false);
        ColorPicker r144 = this.colorPicker;
        if (r52 != 0) goto L101;
        r52 = r123;
    L101:
        r144.setColor(r52, 3);
        ColorPicker r53 = this.colorPicker;
        if (r83 != 0) goto L105;
        r83 = r113;
    L105:
        r53.setColor(r83, 2);
        ColorPicker r54 = this.colorPicker;
        if (r7 != 0) goto L109;
        r7 = r103;
    L109:
        r54.setColor(r7, 1);
        ColorPicker r55 = this.colorPicker;
        if (r133 == 0) goto L112;
        r93 = r133;
    L112:
        r55.setColor(r93, 0);
        if (r3 != 1) goto L115;
    L118:
        this.messagesAdapter.notifyItemInserted(0);
    L119:
        this.listView2.smoothScrollBy(0, AndroidUtilities.dp(60.0f));
        goto L133
    L115:
        if (this.accent.myMessagesGradientAccentColor2 == 0) goto L118;
        this.messagesAdapter.notifyItemChanged(0);
    L88:
        if (r52 != 0) goto L96;
        if (r123 != 0) goto L96;
        if (r83 != 0) goto L95;
        if (r113 != 0) goto L95;
        r22 = 2;
    L95:
        r22 = 3;
    L96:
        r22 = 4;
        goto L97
    L81:
        if (r143 == 0) goto L83;
        r123 = 0;
        goto L83
    L76:
        if (r142 == 0) goto L78;
        r113 = 0;
        goto L78
    L71:
        if (r14 == 0) goto L73;
        r103 = 0;
        goto L73
    L67:
        r123 = 0;
        goto L68
    L63:
        r113 = 0;
        goto L64
    L59:
        r103 = 0;
        goto L60
    }

    private void selectPattern(int r14) {
        if (r14 >= 0) goto L4;
    L7:
        TLRPC.TL_wallPaper r142 = this.lastSelectedPattern;
    L6:
        TLRPC.TL_wallPaper r9 = r142;
        if (r9 != null) goto L10;
        return;
    L10:
        ValueAnimator r143 = this.valueAnimator;
        if (r143 == null) goto L13;
        r143.removeAllListeners();
        this.valueAnimator.cancel();
    L13:
        BackgroundView[] r144 = this.backgroundImages;
        BackgroundView r0 = r144[0];
        BackgroundView r1 = r144[1];
        r144[0] = r1;
        r144[1] = r0;
        this.page2.removeView(r1);
        int r145 = this.page2.indexOfChild(this.backgroundImages[1]);
        this.page2.addView(this.backgroundImages[0], r145 + 1);
        BackgroundView[] r146 = this.backgroundImages;
        BackgroundView r02 = r146[0];
        this.backgroundImage = r02;
        r02.setBackground(r146[1].getBackground());
        updateIntensity();
        this.backgroundImages[1].setVisibility(0);
        this.backgroundImages[1].setAlpha(1.0f);
        this.backgroundImage.setVisibility(0);
        ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.valueAnimator = r03;
        r03.addUpdateListener(new AnonymousClass31(this));
        this.valueAnimator.addListener(new AnonymousClass32(this));
        this.valueAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.valueAnimator.setDuration(300);
        this.valueAnimator.start();
        this.backgroundImage.getImageReceiver().setCrossfadeDuration(300);
        this.backgroundImage.getImageReceiver().setImage(ImageLocation.getForDocument(r9.document), this.imageFilter, null, null, null, r9.document.size, "jpg", r9, 1);
        this.backgroundImage.onNewImageSet();
        this.selectedPattern = r9;
        this.isMotion = this.backgroundCheckBoxView[2].isChecked();
        updateButtonState(false, true);
        return;
    L4:
        if (r14 >= this.patterns.size()) goto L7;
        r142 = (TLRPC.TL_wallPaper) this.patterns.get(r14);
        goto L6
    }

    private void setBackgroundColor(int r4, int r5, boolean r6, boolean r7) {
        if (r5 != 0) goto L5;
        this.backgroundColor = r4;
    L13:
        updatePlayAnimationView(r7);
        int r0 = 0;
        if (this.backgroundCheckBoxView == null) goto L23;
        int r72 = 0;
    L16:
        WallpaperCheckBoxView[] r1 = this.backgroundCheckBoxView;
        if (r72 >= r1.length) goto L23;
        WallpaperCheckBoxView r12 = r1[r72];
        if (r12 == null) goto L21;
        r12.setColor(r5, r4);
    L21:
        r72 = r72 + 1;
    L23:
        if (this.backgroundGradientColor2 == 0) goto L37;
        if (this.intensitySeekBar != null) goto L27;
    L29:
        Drawable r42 = this.backgroundImage.getBackground();
        if ((r42 instanceof MotionBackgroundDrawable) == false) goto L32;
        MotionBackgroundDrawable r43 = (MotionBackgroundDrawable) r42;
    L35:
        r43.setColors(this.backgroundColor, this.backgroundGradientColor1, this.backgroundGradientColor2, this.backgroundGradientColor3);
        this.backgroundImage.setBackground(r43);
        this.patternColor = r43.getPatternColor();
        this.checkColor = 754974720;
    L40:
        int r44 = Theme.key_chat_serviceBackground;
        if (Theme.hasThemeKey(r44) == true) goto L43;
    L48:
        ThemeDelegate r45 = this.themeDelegate;
        int r52 = this.checkColor;
        r45.applyChatServiceMessageColor(new int[]{r52, r52, r52, r52}, this.backgroundImage.getBackground(), this.backgroundImage.getBackground(), Float.valueOf(this.currentIntensity));
    L49:
        ImageView r46 = this.backgroundPlayAnimationImageView;
        if (r46 == null) goto L52;
        r46.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_chat_serviceText), PorterDuff.Mode.MULTIPLY));
    L52:
        ImageView r47 = this.messagesPlayAnimationImageView;
        if (r47 == null) goto L55;
        r47.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_chat_serviceText), PorterDuff.Mode.MULTIPLY));
    L55:
        BackgroundView r48 = this.backgroundImage;
        if (r48 == null) goto L73;
        r48.getImageReceiver().setColorFilter(new PorterDuffColorFilter(this.patternColor, this.blendMode));
        this.backgroundImage.getImageReceiver().setAlpha(Math.abs(this.currentIntensity));
        this.backgroundImage.invalidate();
        if (Theme.getActiveTheme().isDark() == true) goto L60;
    L67:
        this.backgroundImage.getImageReceiver().setGradientBitmap(null);
        SeekBarView r49 = this.intensitySeekBar;
        if (r49 == null) goto L70;
        r49.setTwoSided(false);
    L70:
        SeekBarView r410 = this.intensitySeekBar;
        if (r410 == null) goto L73;
        r410.setProgress(this.currentIntensity);
        goto L73
    L60:
        if ((this.backgroundImage.getBackground() instanceof MotionBackgroundDrawable) == false) goto L67;
        SeekBarView r411 = this.intensitySeekBar;
        if (r411 == null) goto L65;
        r411.setTwoSided(true);
    L65:
        if (this.currentIntensity >= 0.0f) goto L70;
        this.backgroundImage.getImageReceiver().setGradientBitmap(((MotionBackgroundDrawable) this.backgroundImage.getBackground()).getBitmap());
    L73:
        RecyclerListView r412 = this.listView2;
        if (r412 == null) goto L76;
        r412.invalidateViews();
    L76:
        FrameLayout r413 = this.backgroundButtonsContainer;
        if (r413 == null) goto L81;
        int r414 = r413.getChildCount();
        int r53 = 0;
    L79:
        if (r53 >= r414) goto L81;
        this.backgroundButtonsContainer.getChildAt(r53).invalidate();
        r53 = r53 + 1;
    L81:
        FrameLayout r415 = this.messagesButtonsContainer;
        if (r415 == null) goto L86;
        int r416 = r415.getChildCount();
    L84:
        if (r0 >= r416) goto L92;
        this.messagesButtonsContainer.getChildAt(r0).invalidate();
        r0 = r0 + 1;
        goto L84
    L92:
        return;
    L86:
        return;
    L43:
        if ((this.backgroundImage.getBackground() instanceof MotionBackgroundDrawable) == true) goto L48;
        if ((Theme.getCachedWallpaperNonBlocking() instanceof MotionBackgroundDrawable) == false) goto L49;
        int r417 = getThemedColor(r44);
        this.themeDelegate.applyChatServiceMessageColor(new int[]{r417, r417, r417, r417}, this.backgroundImage.getBackground(), this.backgroundImage.getBackground(), Float.valueOf(this.currentIntensity));
        goto L49
    L32:
        r43 = new MotionBackgroundDrawable();
        r43.setParentView(this.backgroundImage);
        if (this.rotatePreview == false) goto L35;
        r43.rotatePreview(false);
        goto L35
    L27:
        if (Theme.getActiveTheme().isDark() == false) goto L29;
        this.intensitySeekBar.setTwoSided(true);
        goto L29
    L37:
        if (this.backgroundGradientColor1 == 0) goto L39;
        this.backgroundImage.setBackground(new GradientDrawable(BackgroundGradientDrawable.getGradientOrientation(this.backgroundRotation), new int[]{this.backgroundColor, this.backgroundGradientColor1}));
        int r418 = AndroidUtilities.getPatternColor(AndroidUtilities.getAverageColor(this.backgroundColor, this.backgroundGradientColor1));
        this.checkColor = r418;
        this.patternColor = r418;
        goto L40
    L39:
        this.backgroundImage.setBackgroundColor(this.backgroundColor);
        int r419 = AndroidUtilities.getPatternColor(this.backgroundColor);
        this.checkColor = r419;
        this.patternColor = r419;
        goto L40
    L5:
        if (r5 != 1) goto L8;
        this.backgroundGradientColor1 = r4;
        goto L13
    L8:
        if (r5 != 2) goto L11;
        this.backgroundGradientColor2 = r4;
        goto L13
    L11:
        if (r5 != 3) goto L13;
        this.backgroundGradientColor3 = r4;
        goto L13
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void setCurrentImage(boolean r22) {
        int r1 = this.screenType;
        if (r1 == 0) goto L5;
    L7:
        TLRPC.PhotoSize r5 = null;
        BitmapDrawable r52 = null;
        if (r1 != 2) goto L65;
        Object r12 = this.currentWallpaper;
        if ((r12 instanceof TLRPC.TL_wallPaper) == false) goto L20;
        TLRPC.TL_wallPaper r13 = (TLRPC.TL_wallPaper) r12;
        if (r22 == false) goto L14;
        TLRPC.PhotoSize r3 = FileLoader.getClosestPhotoSizeWithSize(r13.document.thumbs, 100);
    L16:
        if ((r3 instanceof TLRPC.TL_photoStrippedSize) == false) goto L18;
        r52 = new BitmapDrawable(ImageLoader.getStrippedPhotoBitmap(r3.bytes, "b"));
    L18:
        this.backgroundImage.setImage(ImageLocation.getForDocument(r13.document), this.imageFilter, ImageLocation.getForDocument(r3, r13.document), "100_100_b", r52, "jpg", r13.document.size, 1, r13);
    L154:
        this.rotatePreview = false;
        return;
    L14:
        r3 = null;
        goto L16
    L20:
        if ((r12 instanceof WallpapersListActivity.ColorWallpaper) == false) goto L35;
        WallpapersListActivity.ColorWallpaper r14 = (WallpapersListActivity.ColorWallpaper) r12;
        this.backgroundRotation = r14.gradientRotation;
        setBackgroundColor(r14.color, 0, true, false);
        int r53 = r14.gradientColor1;
        if (r53 == 0) goto L24;
        setBackgroundColor(r53, 1, true, false);
    L24:
        setBackgroundColor(r14.gradientColor2, 2, true, false);
        setBackgroundColor(r14.gradientColor3, 3, true, false);
        TLRPC.TL_wallPaper r32 = this.selectedPattern;
        if (r32 == null) goto L28;
        BackgroundView r4 = this.backgroundImage;
        ImageLocation r54 = ImageLocation.getForDocument(r32.document);
        String r6 = this.imageFilter;
        TLRPC.TL_wallPaper r132 = this.selectedPattern;
        r4.setImage(r54, r6, null, null, "jpg", r132.document.size, 1, r132);
        goto L154
    L28:
        if ("d".equals(r14.slug) == false) goto L154;
        Point r33 = AndroidUtilities.displaySize;
        int r34 = Math.min(r33.x, r33.y);
        Point r42 = AndroidUtilities.displaySize;
        int r43 = Math.max(r42.x, r42.y);
        if (Build.VERSION.SDK_INT < 29) goto L32;
        int r15 = 1459617792;
    L33:
        this.backgroundImage.setImageBitmap(SvgHelper.getBitmap(R.raw.default_pattern, r34, r43, r15));
        goto L154
    L32:
        r15 = MotionBackgroundDrawable.getPatternColor(r14.color, r14.gradientColor1, r14.gradientColor2, r14.gradientColor3);
        goto L33
    L35:
        if ((r12 instanceof WallpapersListActivity.FileWallpaper) == false) goto L52;
        Bitmap r35 = this.currentWallpaperBitmap;
        if (r35 == null) goto L39;
        this.backgroundImage.setImageBitmap(r35);
        goto L154
    L39:
        WallpapersListActivity.FileWallpaper r16 = (WallpapersListActivity.FileWallpaper) r12;
        File r36 = r16.originalPath;
        if (r36 == null) goto L42;
        this.backgroundImage.setImage(r36.getAbsolutePath(), this.imageFilter, null);
        goto L154
    L42:
        File r37 = r16.path;
        if (r37 == null) goto L46;
        this.backgroundImage.setImage(r37.getAbsolutePath(), this.imageFilter, null);
        goto L154
    L46:
        if ("t".equals(r16.slug) == false) goto L48;
        BackgroundView r17 = this.backgroundImage;
        r17.setImageDrawable(Theme.getThemedWallpaper(false, r17));
        goto L154
    L48:
        int r18 = r16.resId;
        if (r18 == 0) goto L154;
        this.backgroundImage.setImageResource(r18);
        goto L154
    L52:
        if ((r12 instanceof MediaController.SearchImage) == false) goto L154;
        MediaController.SearchImage r19 = (MediaController.SearchImage) r12;
        TLRPC.Photo r38 = r19.photo;
        if (r38 == null) goto L63;
        TLRPC.PhotoSize r39 = FileLoader.getClosestPhotoSizeWithSize(r38.sizes, 100);
        TLRPC.PhotoSize r44 = FileLoader.getClosestPhotoSizeWithSize(r19.photo.sizes, this.maxWallpaperSize, true);
        if (r44 == r39) goto L59;
        r5 = r44;
    L59:
        if (r5 == null) goto L61;
        int r45 = r5.size;
    L62:
        this.backgroundImage.setImage(ImageLocation.getForPhoto(r5, r19.photo), this.imageFilter, ImageLocation.getForPhoto(r39, r19.photo), "100_100_b", "jpg", r45, 1, r19);
        goto L154
    L61:
        r45 = 0;
        goto L62
    L63:
        this.backgroundImage.setImage(r19.imageUrl, this.imageFilter, r19.thumbUrl, "100_100_b");
        goto L154
    L65:
        if (this.accent != null) goto L67;
        this.backgroundImage.setBackground(Theme.getCachedWallpaper());
        goto L154
    L67:
        BackgroundGradientDrawable.Disposable r110 = this.backgroundGradientDisposable;
        if (r110 == null) goto L70;
        r110.dispose();
        this.backgroundGradientDisposable = null;
    L70:
        int r111 = Theme.getDefaultAccentColor(Theme.key_chat_wallpaper);
        int r7 = (int) this.accent.backgroundOverrideColor;
        if (r7 == 0) goto L73;
        r111 = r7;
    L73:
        int r72 = Theme.getDefaultAccentColor(Theme.key_chat_wallpaper_gradient_to1);
        long r8 = this.accent.backgroundGradientOverrideColor1;
        int r10 = (int) r8;
        if (r10 == 0) goto L76;
    L78:
        if (r10 == 0) goto L80;
        r72 = r10;
    L80:
        int r82 = Theme.getDefaultAccentColor(Theme.key_chat_wallpaper_gradient_to2);
        long r9 = this.accent.backgroundGradientOverrideColor2;
        int r133 = (int) r9;
        if (r133 == 0) goto L83;
    L85:
        if (r133 == 0) goto L87;
        r82 = r133;
    L87:
        int r92 = Theme.getDefaultAccentColor(Theme.key_chat_wallpaper_gradient_to3);
        Theme.ThemeAccent r102 = this.accent;
        long r134 = r102.backgroundGradientOverrideColor3;
        int r152 = (int) r134;
        if (r152 == 0) goto L90;
    L92:
        if (r152 == 0) goto L95;
        r92 = r152;
    L95:
        if (TextUtils.isEmpty(r102.patternSlug) == false) goto L97;
    L112:
        Drawable r103 = Theme.getCachedWallpaperNonBlocking();
        if (r103 != null) goto L115;
    L118:
        if (r72 != 0) goto L120;
        int r104 = AndroidUtilities.getPatternColor(r111);
        this.checkColor = r104;
        this.patternColor = r104;
    L123:
        BackgroundView r105 = this.backgroundImage;
        if (r105 == null) goto L142;
        r105.getImageReceiver().setColorFilter(new PorterDuffColorFilter(this.patternColor, this.blendMode));
        this.backgroundImage.getImageReceiver().setAlpha(Math.abs(this.currentIntensity));
        this.backgroundImage.invalidate();
        if (Theme.getActiveTheme().isDark() == true) goto L128;
    L135:
        this.backgroundImage.getImageReceiver().setGradientBitmap(null);
        SeekBarView r55 = this.intensitySeekBar;
        if (r55 == null) goto L138;
        r55.setTwoSided(false);
    L138:
        SeekBarView r56 = this.intensitySeekBar;
        if (r56 == null) goto L142;
        r56.setProgress(this.currentIntensity);
        goto L142
    L128:
        if ((this.backgroundImage.getBackground() instanceof MotionBackgroundDrawable) == false) goto L135;
        SeekBarView r57 = this.intensitySeekBar;
        if (r57 == null) goto L133;
        r57.setTwoSided(true);
    L133:
        if (this.currentIntensity >= 0.0f) goto L138;
        this.backgroundImage.getImageReceiver().setGradientBitmap(((MotionBackgroundDrawable) this.backgroundImage.getBackground()).getBitmap());
    L142:
        if (this.backgroundCheckBoxView == null) goto L147;
        int r58 = 0;
    L144:
        WallpaperCheckBoxView[] r106 = this.backgroundCheckBoxView;
        if (r58 >= r106.length) goto L147;
        r106[r58].setColor(0, r111);
        this.backgroundCheckBoxView[r58].setColor(1, r72);
        this.backgroundCheckBoxView[r58].setColor(2, r82);
        this.backgroundCheckBoxView[r58].setColor(3, r92);
        r58 = r58 + 1;
    L147:
        ImageView r112 = this.backgroundPlayAnimationImageView;
        if (r112 == null) goto L150;
        r112.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_chat_serviceText), PorterDuff.Mode.MULTIPLY));
    L150:
        ImageView r113 = this.messagesPlayAnimationImageView;
        if (r113 == null) goto L153;
        r113.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_chat_serviceText), PorterDuff.Mode.MULTIPLY));
    L153:
        invalidateBlur();
        goto L154
    L120:
        if (r82 == 0) goto L122;
        this.patternColor = MotionBackgroundDrawable.getPatternColor(r111, r72, r82, r92);
        this.checkColor = 754974720;
        goto L123
    L122:
        int r107 = AndroidUtilities.getPatternColor(AndroidUtilities.getAverageColor(r111, r72));
        this.checkColor = r107;
        this.patternColor = r107;
        goto L123
    L115:
        if ((r103 instanceof MotionBackgroundDrawable) == false) goto L117;
        ((MotionBackgroundDrawable) r103).setParentView(this.backgroundImage);
    L117:
        this.backgroundImage.setBackground(r103);
        goto L118
    L97:
        if (Theme.hasCustomWallpaper() == true) goto L112;
        if (r82 == 0) goto L106;
        Drawable r108 = this.backgroundImage.getBackground();
        if ((r108 instanceof MotionBackgroundDrawable) == false) goto L102;
        MotionBackgroundDrawable r109 = (MotionBackgroundDrawable) r108;
    L105:
        r109.setColors(r111, r72, r82, r92);
        ColorDrawable r1010 = r109;
    L109:
        this.backgroundImage.setBackground(r1010);
        TLRPC.TL_wallPaper r1011 = this.selectedPattern;
        if (r1011 == null) goto L118;
        BackgroundView r11 = this.backgroundImage;
        ImageLocation r122 = ImageLocation.getForDocument(r1011.document);
        String r135 = this.imageFilter;
        TLRPC.TL_wallPaper r1012 = this.selectedPattern;
        r11.setImage(r122, r135, null, null, "jpg", r1012.document.size, 1, r1012);
        goto L118
    L102:
        MotionBackgroundDrawable r1013 = new MotionBackgroundDrawable();
        r1013.setParentView(this.backgroundImage);
        r109 = r1013;
        if (this.rotatePreview == false) goto L105;
        r1013.rotatePreview(false);
        r109 = r1013;
        goto L105
    L106:
        if (r72 == 0) goto L108;
        BackgroundGradientDrawable r114 = new BackgroundGradientDrawable(BackgroundGradientDrawable.getGradientOrientation(this.accent.backgroundRotation), new int[]{r111, r72});
        this.backgroundGradientDisposable = r114.startDithering(BackgroundGradientDrawable.Sizes.ofDeviceScreen(), new AnonymousClass39(this), 100);
        r1010 = r114;
        goto L109
    L108:
        r1010 = new ColorDrawable(r111);
        goto L109
    L90:
        if (r134 == 0) goto L92;
        r92 = 0;
        goto L95
    L83:
        if (r9 == 0) goto L85;
        r82 = 0;
        goto L87
    L76:
        if (r8 == 0) goto L78;
        r72 = 0;
        goto L80
    L5:
        if (this.accent != null) goto L7;
        this.backgroundImage.setBackground(Theme.getCachedWallpaper());
        goto L154
    }

    private void setCurrentServerWallpaper(MessageObject r1) {
        this.serverWallpaper = r1;
    }

    private void setVisiblePart(ChatActionCell r6) {
        BackgroundView r0 = this.backgroundImage;
        if (r0 != null) goto L5;
        return;
    L5:
        float r1 = 0.0f;
        if (r0 != null) goto L8;
        float r02 = 0.0f;
        float r2 = 0.0f;
    L13:
        float r3 = r6.getY() - r02;
        int r03 = this.backgroundImage.getMeasuredHeight();
        if (this.shouldShowBrightnessControll == false) goto L16;
        r1 = this.dimAmount * this.progressToDarkTheme;
    L16:
        r6.setVisiblePart(r3, r2, r03, r1);
        return;
    L8:
        if (ThemeDelegate.m20849$$Nest$fgetserviceBitmap(this.themeDelegate) == null) goto L10;
        float r04 = ThemeDelegate.m20849$$Nest$fgetserviceBitmap(this.themeDelegate).getWidth();
        r2 = (((this.backgroundImage.getMeasuredWidth() - (r04 * Math.max(this.backgroundImage.getMeasuredWidth() / r04, this.backgroundImage.getMeasuredHeight() / ThemeDelegate.m20849$$Nest$fgetserviceBitmap(this.themeDelegate).getHeight()))) / 2.0f) + this.currentScrollOffset) + 0.0f;
    L11:
        r02 = (-this.backgroundImage.ty) + 0.0f;
        goto L13
    L10:
        r2 = this.currentScrollOffset + 0.0f;
        goto L11
    }

    private void showAnimationHint() {
        if (this.page2 != null) goto L5;
        return;
    L5:
        if (this.messagesCheckBoxView != null) goto L7;
        return;
    L7:
        if (this.accent.myMessagesGradientAccentColor2 == 0) goto L19;
        SharedPreferences r0 = MessagesController.getGlobalMainSettings();
        if (r0.getBoolean("bganimationhint", false) == false) goto L13;
        return;
    L13:
        if (this.animationHint != null) goto L15;
        HintView r1 = new HintView(getParentActivity(), 8);
        this.animationHint = r1;
        r1.setShowingDuration(5000);
        this.animationHint.setAlpha(0.0f);
        this.animationHint.setVisibility(4);
        this.animationHint.setText(LocaleController.getString(R.string.BackgroundAnimateInfo));
        this.animationHint.setExtraTranslationY(AndroidUtilities.dp(6.0f));
        this.frameLayout.addView(this.animationHint, LayoutHelper.createFrame(-2, -2.0f, 51, 10.0f, 0.0f, 10.0f, 0.0f));
    L15:
        AndroidUtilities.runOnUIThread(new ThemePreviewActivity$$ExternalSyntheticLambda23(this, r0), 500);
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void showFor(ChatActivity r13, MessageObject r14) {
        TLRPC.MessageAction r0 = r14.messageOwner.action;
        if ((r0 instanceof TLRPC.TL_messageActionSetChatWallPaper) == false) goto L19;
        TLRPC.WallPaper r02 = ((TLRPC.TL_messageActionSetChatWallPaper) r0).wallpaper;
        if (r02.pattern == false) goto L7;
    L10:
        String r4 = r02.slug;
        TLRPC.WallPaperSettings r1 = r02.settings;
        WallpapersListActivity.ColorWallpaper r3 = new WallpapersListActivity.ColorWallpaper(r4, r1.background_color, r1.second_background_color, r1.third_background_color, r1.fourth_background_color, AndroidUtilities.getWallpaperRotation(r1.rotation, false), r1.intensity / 100.0f, r02.settings.motion, null);
        if ((r02 instanceof TLRPC.TL_wallPaper) == false) goto L13;
        r3.pattern = (TLRPC.TL_wallPaper) r02;
    L13:
        TLRPC.WallPaper r5 = r3;
    L14:
        boolean r10 = Theme.getActiveTheme().isDark();
        AnonymousClass3 r42 = new AnonymousClass3(r5, null, true, false, r13, r10);
        TLRPC.WallPaperSettings r132 = r02.settings;
        if (r132 == null) goto L17;
        r42.setInitialModes(r132.blur, r132.motion, r132.intensity / 100.0f);
    L17:
        r42.setCurrentServerWallpaper(r14);
        r42.setDialogId(r14.getDialogId());
        r42.setResourceProvider(r13.themeDelegate);
        r42.setOnSwitchDayNightDelegate(new AnonymousClass4(r10, r13));
        r13.presentFragment(r42);
        return;
    L7:
        if (r02.document == null) goto L10;
        r5 = r02;
        goto L14
    }

    private void showPatternsView(int r19, boolean r20, boolean r21) {
        char r2 = 0;
        if (r20 == false) goto L8;
        if (r19 != 1) goto L8;
        if (this.selectedPattern == null) goto L8;
        boolean r5 = true;
    L10:
        if (r20 == false) goto L35;
        if (r19 == 0) goto L13;
        this.previousSelectedPattern = this.selectedPattern;
        this.previousIntensity = this.currentIntensity;
        this.patternsAdapter.notifyDataSetChanged();
        ArrayList<Object> r7 = this.patterns;
        if (r7 == null) goto L35;
        TLRPC.TL_wallPaper r8 = this.selectedPattern;
        if (r8 != null) goto L29;
        int r72 = 0;
    L34:
        this.patternsLayoutManager.scrollToPositionWithOffset(r72, (this.patternsListView.getMeasuredWidth() - AndroidUtilities.dp(124.0f)) / 2);
        goto L35
    L29:
        int r73 = r7.indexOf(r8);
        if (this.screenType != 2) goto L32;
        int r82 = 1;
    L33:
        r72 = r73 + r82;
        goto L34
    L32:
        r82 = 0;
        goto L33
    L13:
        if (this.screenType != 2) goto L35;
        this.previousBackgroundColor = this.backgroundColor;
        int r74 = this.backgroundGradientColor1;
        this.previousBackgroundGradientColor1 = r74;
        int r83 = this.backgroundGradientColor2;
        this.previousBackgroundGradientColor2 = r83;
        int r9 = this.backgroundGradientColor3;
        this.previousBackgroundGradientColor3 = r9;
        int r10 = this.backupBackgroundRotation;
        this.previousBackgroundRotation = r10;
        if (r9 == 0) goto L18;
        int r14 = 4;
    L17:
        int r16 = r10;
    L23:
        this.colorPicker.setType(0, false, 4, r14, false, r16, false);
        this.colorPicker.setColor(this.backgroundGradientColor3, 3);
        this.colorPicker.setColor(this.backgroundGradientColor2, 2);
        this.colorPicker.setColor(this.backgroundGradientColor1, 1);
        this.colorPicker.setColor(this.backgroundColor, 0);
        goto L35
    L18:
        if (r83 == 0) goto L20;
        r16 = r10;
        r14 = 3;
        goto L23
    L20:
        if (r74 == 0) goto L22;
        r14 = 2;
        goto L17
    L22:
        r14 = 1;
    L35:
        int r75 = this.screenType;
        if (r75 == 1) goto L38;
        if (r75 == 2) goto L38;
    L44:
        if (r19 == 1) goto L46;
    L50:
        int r84 = 58;
        float r102 = 1.0f;
        if (r21 == false) goto L104;
        this.patternViewAnimation = new AnimatorSet();
        ArrayList r3 = new ArrayList();
        if (r19 != 0) goto L55;
        int r11 = 1;
    L56:
        if (r20 == false) goto L101;
        this.patternLayout[r19].setVisibility(0);
        int r12 = this.screenType;
        if (r12 != 1) goto L76;
        RecyclerListView r85 = this.listView2;
        Property r122 = View.TRANSLATION_Y;
        if (r19 != 1) goto L62;
        float r92 = -AndroidUtilities.dp(21.0f);
    L63:
        r3.add(ObjectAnimator.ofFloat(r85, r122, new float[]{r92}));
        WallpaperCheckBoxView r86 = this.backgroundCheckBoxView[2];
        Property r93 = View.ALPHA;
        if (r5 == false) goto L66;
        float r123 = 1.0f;
    L67:
        r3.add(ObjectAnimator.ofFloat(r86, r93, new float[]{r123}));
        WallpaperCheckBoxView r87 = this.backgroundCheckBoxView[0];
        if (r5 == false) goto L70;
        float r124 = 0.0f;
    L71:
        r3.add(ObjectAnimator.ofFloat(r87, r93, new float[]{r124}));
        FrameLayout[] r88 = this.patternLayout;
        if (r19 != 1) goto L74;
        r3.add(ObjectAnimator.ofFloat(r88[r19], r93, new float[]{0.0f, 1.0f}));
    L75:
        this.colorPicker.hideKeyboard();
    L102:
        this.patternViewAnimation.playTogether(r3);
        this.patternViewAnimation.addListener(new AnonymousClass35(this, r20, r11, r19, r5));
        this.patternViewAnimation.setInterpolator(CubicBezierInterpolator.EASE_OUT);
        this.patternViewAnimation.setDuration(200);
        this.patternViewAnimation.start();
        return;
    L74:
        r88[r19].setAlpha(1.0f);
        r3.add(ObjectAnimator.ofFloat(this.patternLayout[r11], r93, new float[]{0.0f}));
        goto L75
    L70:
        r124 = 1.0f;
        goto L71
    L66:
        r123 = 0.0f;
        goto L67
    L62:
        r92 = 0.0f;
        goto L63
    L76:
        if (r12 != 2) goto L96;
        RecyclerListView r94 = this.listView2;
        Property r125 = View.TRANSLATION_Y;
        int r13 = -this.patternLayout[r19].getMeasuredHeight();
        if (this.applyButton2 != null) goto L81;
        r84 = 0;
    L81:
        int r132 = r13 + AndroidUtilities.dp(r84 + 72);
        if (insideBottomSheet() == false) goto L84;
        int r89 = AndroidUtilities.navigationBarHeight;
    L85:
        r3.add(ObjectAnimator.ofFloat(r94, r125, new float[]{r132 + r89}));
        WallpaperCheckBoxView r810 = this.backgroundCheckBoxView[2];
        Property r95 = View.ALPHA;
        if (r5 == false) goto L88;
        float r133 = 1.0f;
    L89:
        r3.add(ObjectAnimator.ofFloat(r810, r95, new float[]{r133}));
        WallpaperCheckBoxView r811 = this.backgroundCheckBoxView[0];
        if (r5 == false) goto L92;
        r102 = 0.0f;
    L92:
        r3.add(ObjectAnimator.ofFloat(r811, r95, new float[]{r102}));
        int r812 = this.patternLayout[r11].getVisibility();
        FrameLayout[] r103 = this.patternLayout;
        if (r812 != 0) goto L95;
        r3.add(ObjectAnimator.ofFloat(r103[r11], r95, new float[]{0.0f}));
        r3.add(ObjectAnimator.ofFloat(this.patternLayout[r19], r95, new float[]{0.0f, 1.0f}));
        this.patternLayout[r19].setTranslationY(0.0f);
        goto L102
    L95:
        r3.add(ObjectAnimator.ofFloat(r103[r19], r125, new float[]{r8.getMeasuredHeight(), 0.0f}));
        goto L102
    L88:
        r133 = 0.0f;
        goto L89
    L84:
        r89 = 0;
        goto L85
    L96:
        FrameLayout[] r813 = this.patternLayout;
        if (r19 != 1) goto L99;
        r3.add(ObjectAnimator.ofFloat(r813[r19], View.ALPHA, new float[]{0.0f, 1.0f}));
    L100:
        this.colorPicker.hideKeyboard();
        goto L102
    L99:
        r813[r19].setAlpha(1.0f);
        r3.add(ObjectAnimator.ofFloat(this.patternLayout[r11], View.ALPHA, new float[]{0.0f}));
        goto L100
    L101:
        RecyclerListView r814 = this.listView2;
        Property r96 = View.TRANSLATION_Y;
        r3.add(ObjectAnimator.ofFloat(r814, r96, new float[]{0.0f}));
        r3.add(ObjectAnimator.ofFloat(this.patternLayout[r19], r96, new float[]{r8.getMeasuredHeight()}));
        WallpaperCheckBoxView r815 = this.backgroundCheckBoxView[0];
        Property r97 = View.ALPHA;
        r3.add(ObjectAnimator.ofFloat(r815, r97, new float[]{1.0f}));
        r3.add(ObjectAnimator.ofFloat(this.backgroundCheckBoxView[2], r97, new float[]{0.0f}));
        r3.add(ObjectAnimator.ofFloat(this.backgroundImage, r97, new float[]{1.0f}));
        goto L102
    L55:
        r11 = 0;
        goto L56
    L104:
        if (r19 != 0) goto L106;
        char r112 = 1;
    L107:
        if (r20 == false) goto L157;
        this.patternLayout[r19].setVisibility(0);
        int r126 = this.screenType;
        if (r126 != 1) goto L127;
        RecyclerListView r816 = this.listView2;
        if (r19 != 1) goto L113;
        float r98 = -AndroidUtilities.dp(21.0f);
    L114:
        r816.setTranslationY(r98);
        WallpaperCheckBoxView r817 = this.backgroundCheckBoxView[2];
        if (r5 == false) goto L117;
        float r99 = 1.0f;
    L118:
        r817.setAlpha(r99);
        WallpaperCheckBoxView r818 = this.backgroundCheckBoxView[0];
        if (r5 == false) goto L121;
        float r910 = 0.0f;
    L122:
        r818.setAlpha(r910);
        FrameLayout[] r819 = this.patternLayout;
        if (r19 != 1) goto L125;
        r819[r19].setAlpha(1.0f);
    L126:
        this.colorPicker.hideKeyboard();
    L158:
        if (r20 == true) goto L160;
    L162:
        if (r20 == true) goto L164;
        this.patternLayout[r19].setVisibility(4);
    L164:
        int r820 = this.screenType;
        if (r820 == 1) goto L171;
        if (r820 == 2) goto L171;
        if (r19 != 1) goto L177;
        this.patternLayout[r112].setAlpha(0.0f);
        return;
    L177:
        return;
    L171:
        WallpaperCheckBoxView[] r0 = this.backgroundCheckBoxView;
        if (r5 == true) goto L175;
        r2 = 2;
    L175:
        r0[r2].setVisibility(4);
        return;
    L160:
        if (this.patternLayout[r112].getVisibility() != 0) goto L162;
        this.patternLayout[r112].setAlpha(1.0f);
        this.patternLayout[r112].setVisibility(4);
        goto L164
    L125:
        r819[r19].setAlpha(1.0f);
        this.patternLayout[r112].setAlpha(0.0f);
        goto L126
    L121:
        r910 = 1.0f;
        goto L122
    L117:
        r99 = 0.0f;
        goto L118
    L113:
        r98 = 0.0f;
        goto L114
    L127:
        if (r126 != 2) goto L152;
        RecyclerListView r911 = this.listView2;
        if (r19 != 0) goto L131;
        float r127 = 343.0f;
    L132:
        int r128 = -AndroidUtilities.dp(r127);
        if (this.applyButton2 != null) goto L136;
        r84 = 0;
    L136:
        int r129 = r128 + AndroidUtilities.dp(r84 + 72);
        if (insideBottomSheet() == false) goto L139;
        int r821 = AndroidUtilities.navigationBarHeight;
    L140:
        r911.setTranslationY(r129 + r821);
        WallpaperCheckBoxView r822 = this.backgroundCheckBoxView[2];
        if (r5 == false) goto L143;
        float r912 = 1.0f;
    L144:
        r822.setAlpha(r912);
        WallpaperCheckBoxView r823 = this.backgroundCheckBoxView[0];
        if (r5 == false) goto L147;
        float r913 = 0.0f;
    L148:
        r823.setAlpha(r913);
        int r824 = this.patternLayout[r112].getVisibility();
        FrameLayout[] r914 = this.patternLayout;
        if (r824 != 0) goto L151;
        r914[r112].setAlpha(0.0f);
        this.patternLayout[r19].setAlpha(1.0f);
        this.patternLayout[r19].setTranslationY(0.0f);
        goto L158
    L151:
        r914[r19].setTranslationY(0.0f);
        goto L158
    L147:
        r913 = 1.0f;
        goto L148
    L143:
        r912 = 0.0f;
        goto L144
    L139:
        r821 = 0;
        goto L140
    L131:
        r127 = 316.0f;
        goto L132
    L152:
        FrameLayout[] r825 = this.patternLayout;
        if (r19 != 1) goto L155;
        r825[r19].setAlpha(1.0f);
    L156:
        this.colorPicker.hideKeyboard();
        goto L158
    L155:
        r825[r19].setAlpha(1.0f);
        this.patternLayout[r112].setAlpha(0.0f);
        goto L156
    L157:
        this.listView2.setTranslationY(0.0f);
        this.patternLayout[r19].setTranslationY(r8.getMeasuredHeight());
        this.backgroundCheckBoxView[0].setAlpha(1.0f);
        this.backgroundCheckBoxView[2].setAlpha(1.0f);
        this.backgroundImage.setAlpha(1.0f);
        goto L158
    L106:
        r112 = 0;
        goto L107
    L46:
        if (this.intensitySeekBar.isTwoSided() == true) goto L50;
        float r826 = this.currentIntensity;
        if (r826 >= 0.0f) goto L50;
        float r827 = -r826;
        this.currentIntensity = r827;
        this.intensitySeekBar.setProgress(r827);
    L38:
        WallpaperCheckBoxView[] r76 = this.backgroundCheckBoxView;
        if (r5 == false) goto L41;
        char r828 = 2;
    L42:
        r76[r828].setVisibility(0);
        goto L44
    L41:
        r828 = 0;
    L8:
        r5 = false;
        goto L10
    }

    private void updateApplyButton1(boolean r6) {
        long r0 = this.dialogId;
        if (r0 <= 0) goto L7;
        this.applyButton1.setText(LocaleController.getString(R.string.ApplyWallpaperForMe));
        return;
    L7:
        if (r0 >= 0) goto L27;
        TLRPC.Chat r02 = getMessagesController().getChat(Long.valueOf(-this.dialogId));
        BlurButton r1 = this.applyButton1;
        if (r02 == null) goto L25;
        r1.setText(LocaleController.formatString(R.string.ApplyWallpaperForChannel, new Object[]{r02.title}));
        TL_stories.TL_premium_boostsStatus r03 = this.boostsStatus;
        if (r03 == null) goto L20;
        if (r03.level >= getCustomWallpaperLevelMin()) goto L20;
        SpannableStringBuilder r04 = new SpannableStringBuilder("l");
        if (this.lockSpan != null) goto L17;
        ColoredImageSpan r12 = new ColoredImageSpan(R.drawable.mini_switch_lock);
        this.lockSpan = r12;
        r12.setTopOffset(1);
    L17:
        r04.setSpan(this.lockSpan, 0, 1, 33);
        r04.append(" ").append(LocaleController.formatPluralString("ReactionLevelRequiredBtn", getCustomWallpaperLevelMin(), new Object[0]));
        this.applyButton1.setSubText(r04, r6);
        return;
    L20:
        if (this.boostsStatus != null) goto L23;
        checkBoostsLevel();
        return;
    L23:
        this.applyButton1.setSubText(null, r6);
        return;
    L25:
        r1.setText(LocaleController.formatString(R.string.ApplyWallpaperForChannel, new Object[]{LocaleController.getString(R.string.AccDescrChannel).toLowerCase()}));
        return;
    L27:
        this.applyButton1.setText(LocaleController.getString(R.string.ApplyWallpaper));
    }

    private void updateBlurred() {
        if (this.isBlurred == false) goto L15;
        if (this.blurredBitmap != null) goto L15;
        Bitmap r0 = this.currentWallpaperBitmap;
        if (r0 == null) goto L9;
        this.originalBitmap = r0;
        this.blurredBitmap = Utilities.blurWallpaper(r0);
        goto L15
    L9:
        ImageReceiver r02 = this.backgroundImage.getImageReceiver();
        if (r02.hasNotThumb() == false) goto L12;
    L13:
        this.originalBitmap = r02.getBitmap();
        this.blurredBitmap = Utilities.blurWallpaper(r02.getBitmap());
        goto L15
    L12:
        if (r02.hasStaticThumb() == true) goto L13;
    L15:
        if (this.isBlurred == false) goto L20;
        Bitmap r03 = this.blurredBitmap;
        if (r03 == null) goto L22;
        this.backgroundImage.setImageBitmap(r03);
        return;
    L22:
        return;
    L20:
        setCurrentImage(false);
    }

    private void updateButtonState(boolean r11, boolean r12) {
        Object r112 = this.selectedPattern;
        if (r112 != null) goto L6;
        r112 = this.currentWallpaper;
    L6:
        boolean r122 = r112 instanceof TLRPC.TL_wallPaper;
        if (r122 == true) goto L11;
        if ((r112 instanceof MediaController.SearchImage) == true) goto L11;
        return;
    L11:
        if (r122 == false) goto L16;
        TLRPC.TL_wallPaper r113 = (TLRPC.TL_wallPaper) r112;
        String r123 = FileLoader.getAttachFileName(r113.document);
        if (TextUtils.isEmpty(r123) == true) goto L75;
        File r0 = FileLoader.getInstance(this.currentAccount).getPathToAttach(r113.document, true);
        long r1 = r113.document.size;
    L24:
        boolean r114 = r0.exists();
        int r02 = this.currentAccount;
        if (r114 == false) goto L34;
        DownloadController.getInstance(r02).removeLoadingFileObserver(this);
        this.backgroundImage.invalidate();
        if (this.screenType == 2) goto L29;
    L40:
        float r03 = 0.5f;
        if (this.selectedPattern != null) goto L48;
        FrameLayout r124 = this.backgroundButtonsContainer;
        if (r124 == null) goto L48;
        if (r114 == false) goto L46;
        float r2 = 1.0f;
    L47:
        r124.setAlpha(r2);
        goto L48
    L46:
        r2 = 0.5f;
    L48:
        int r125 = this.screenType;
        if (r125 != 0) goto L55;
        this.doneButton.setEnabled(r114);
        TextView r10 = this.doneButton;
        if (r114 == false) goto L53;
        r03 = 1.0f;
    L53:
        r10.setAlpha(r03);
        return;
    L55:
        if (r125 != 2) goto L68;
        this.bottomOverlayChat.setEnabled(r114);
        BlurButton r126 = this.applyButton1;
        if (r126 == null) goto L62;
        if (r114 == false) goto L60;
        float r22 = 1.0f;
    L61:
        r126.setAlpha(r22);
        goto L62
    L60:
        r22 = 0.5f;
    L62:
        BlurButton r102 = this.applyButton2;
        if (r102 == null) goto L74;
        if (r114 == false) goto L66;
        r03 = 1.0f;
    L66:
        r102.setAlpha(r03);
        return;
    L74:
        return;
    L68:
        this.saveItem.setEnabled(r114);
        ActionBarMenuItem r103 = this.saveItem;
        if (r114 == false) goto L71;
        r03 = 1.0f;
    L71:
        r103.setAlpha(r03);
        return;
    L29:
        if (r1 != 0) goto L31;
    L33:
        this.actionBar2.setSubtitle(null);
        goto L40
    L31:
        if (this.dialogId != 0) goto L33;
        this.actionBar2.setSubtitle(AndroidUtilities.formatFileSize(r1));
        goto L40
    L34:
        DownloadController.getInstance(r02).addLoadingFileObserver(r123, null, this);
        if (this.screenType == 2) goto L37;
    L39:
        this.backgroundImage.invalidate();
        goto L40
    L37:
        if (this.dialogId != 0) goto L39;
        this.actionBar2.setSubtitle(LocaleController.getString(R.string.LoadingFullImage));
        goto L39
    L75:
        return;
    L16:
        MediaController.SearchImage r115 = (MediaController.SearchImage) r112;
        TLRPC.Photo r127 = r115.photo;
        if (r127 == null) goto L20;
        TLRPC.PhotoSize r116 = FileLoader.getClosestPhotoSizeWithSize(r127.sizes, this.maxWallpaperSize, true);
        File r128 = FileLoader.getInstance(this.currentAccount).getPathToAttach(r116, true);
        String r04 = FileLoader.getAttachFileName(r116);
        int r117 = r116.size;
    L19:
        r1 = r117;
        String r9 = r04;
        r0 = r128;
        r123 = r9;
        if (TextUtils.isEmpty(r123) == false) goto L24;
        return;
    L20:
        r128 = ImageLoader.getHttpFilePath(r115.imageUrl, "jpg");
        r04 = r128.getName();
        r117 = r115.size;
        goto L19
    }

    private void updateIntensity() {
        this.backgroundImage.getImageReceiver().setAlpha(Math.abs(this.currentIntensity));
        this.backgroundImage.invalidate();
        this.patternsListView.invalidateViews();
        if (this.currentIntensity < 0.0f) goto L6;
        this.backgroundImage.getImageReceiver().setGradientBitmap(null);
    L11:
        ThemeDelegate r0 = this.themeDelegate;
        int r1 = this.checkColor;
        r0.applyChatServiceMessageColor(new int[]{r1, r1, r1, r1}, this.backgroundImage.getBackground(), this.backgroundImage.getBackground(), Float.valueOf(this.currentIntensity));
        invalidateBlur();
        return;
    L6:
        if (Build.VERSION.SDK_INT < 29) goto L9;
        this.backgroundImage.getImageReceiver().setBlendMode(null);
    L9:
        if ((this.backgroundImage.getBackground() instanceof MotionBackgroundDrawable) == false) goto L11;
        MotionBackgroundDrawable r02 = (MotionBackgroundDrawable) this.backgroundImage.getBackground();
        this.backgroundImage.getImageReceiver().setGradientBitmap(r02.getBitmap());
        goto L11
    }

    private void updateMotionButton() {
        int r0 = this.screenType;
        float r3 = 1.0f;
        float r4 = 0.0f;
        if (r0 == 1) goto L36;
        if (r0 == 2) goto L36;
        boolean r02 = this.backgroundCheckBoxView[0].isEnabled();
        TLRPC.TL_wallPaper r8 = this.selectedPattern;
        if (r8 == null) goto L9;
        boolean r9 = true;
    L10:
        if (r02 != r9) goto L12;
        return;
    L12:
        if (r8 != null) goto L14;
        this.backgroundCheckBoxView[0].setChecked(false, true);
    L14:
        WallpaperCheckBoxView r03 = this.backgroundCheckBoxView[0];
        if (this.selectedPattern == null) goto L17;
        boolean r82 = true;
    L18:
        r03.setEnabled(r82);
        if (this.selectedPattern == null) goto L21;
        this.backgroundCheckBoxView[0].setVisibility(0);
    L21:
        FrameLayout.LayoutParams r04 = (FrameLayout.LayoutParams) this.backgroundCheckBoxView[1].getLayoutParams();
        AnimatorSet r83 = new AnimatorSet();
        int r05 = (r04.width + AndroidUtilities.dp(9.0f)) / 2;
        WallpaperCheckBoxView r5 = this.backgroundCheckBoxView[0];
        Property r92 = View.ALPHA;
        if (this.selectedPattern != null) goto L25;
        r3 = 0.0f;
    L25:
        r83.playTogether(new Animator[]{ObjectAnimator.ofFloat(r5, r92, new float[]{r3})});
        WallpaperCheckBoxView r32 = this.backgroundCheckBoxView[0];
        Property r52 = View.TRANSLATION_X;
        if (this.selectedPattern == null) goto L28;
        float r93 = 0.0f;
    L29:
        r83.playTogether(new Animator[]{ObjectAnimator.ofFloat(r32, r52, new float[]{r93})});
        WallpaperCheckBoxView r33 = this.backgroundCheckBoxView[1];
        if (this.selectedPattern != null) goto L33;
        r4 = -r05;
    L33:
        r83.playTogether(new Animator[]{ObjectAnimator.ofFloat(r33, r52, new float[]{r4})});
        r83.setInterpolator(CubicBezierInterpolator.EASE_OUT);
        r83.setDuration(200);
        r83.addListener(new AnonymousClass34(this));
        r83.start();
        return;
    L28:
        r93 = r05;
        goto L29
    L17:
        r82 = false;
        goto L18
    L9:
        r9 = false;
    L36:
        if (this.selectedPattern == null) goto L38;
    L40:
        WallpaperCheckBoxView[] r06 = this.backgroundCheckBoxView;
        if (this.selectedPattern == null) goto L43;
        char r84 = 2;
    L44:
        r06[r84].setVisibility(0);
        AnimatorSet r07 = new AnimatorSet();
        WallpaperCheckBoxView r85 = this.backgroundCheckBoxView[2];
        Property r94 = View.ALPHA;
        if (this.selectedPattern == null) goto L47;
        float r10 = 1.0f;
    L48:
        ObjectAnimator r86 = ObjectAnimator.ofFloat(r85, r94, new float[]{r10});
        WallpaperCheckBoxView r102 = this.backgroundCheckBoxView[0];
        if (this.selectedPattern == null) goto L51;
        r3 = 0.0f;
    L51:
        r07.playTogether(new Animator[]{r86, ObjectAnimator.ofFloat(r102, r94, new float[]{r3})});
        r07.addListener(new AnonymousClass33(this));
        r07.setInterpolator(CubicBezierInterpolator.EASE_OUT);
        r07.setDuration(200);
        r07.start();
        return;
    L47:
        r10 = 0.0f;
        goto L48
    L43:
        r84 = 0;
        goto L44
    L38:
        if ((this.currentWallpaper instanceof WallpapersListActivity.ColorWallpaper) == false) goto L40;
        this.backgroundCheckBoxView[2].setChecked(false, true);
        goto L40
    }

    private void updatePlayAnimationView(boolean r23) {
        if (Build.VERSION.SDK_INT < 29) goto L28;
        int r3 = this.screenType;
        if (r3 != 0) goto L10;
        Theme.ThemeAccent r32 = this.accent;
        if (r32 == null) goto L9;
        int r33 = (int) r32.backgroundGradientOverrideColor2;
    L22:
        if (r33 != 0) goto L24;
    L26:
        this.backgroundImage.getImageReceiver().setBlendMode(null);
        goto L28
    L24:
        if (this.currentIntensity < 0.0f) goto L26;
        this.backgroundImage.getImageReceiver().setBlendMode(PatternCell$$ExternalSyntheticApiModelOutline0.m());
        goto L28
    L9:
        r33 = getThemedColor(Theme.key_chat_wallpaper_gradient_to2);
        goto L22
    L10:
        if (r3 != 1) goto L18;
        r33 = Theme.getDefaultAccentColor(Theme.key_chat_wallpaper_gradient_to2);
        long r10 = this.accent.backgroundGradientOverrideColor2;
        int r4 = (int) r10;
        if (r4 == 0) goto L14;
    L16:
        if (r4 == 0) goto L22;
        r33 = r4;
        goto L22
    L14:
        if (r10 == 0) goto L16;
    L21:
        r33 = 0;
        goto L22
    L18:
        Object r34 = this.currentWallpaper;
        if ((r34 instanceof WallpapersListActivity.ColorWallpaper) == false) goto L21;
        WallpapersListActivity.ColorWallpaper r35 = (WallpapersListActivity.ColorWallpaper) r34;
        r33 = this.backgroundGradientColor2;
    L28:
        if (this.backgroundPlayAnimationView == null) goto L116;
        int r36 = this.screenType;
        if (r36 == 2) goto L32;
        if (r36 != 1) goto L44;
        int r37 = Theme.getDefaultAccentColor(Theme.key_chat_wallpaper_gradient_to1);
        long r6 = this.accent.backgroundGradientOverrideColor1;
        int r8 = (int) r6;
        if (r8 == 0) goto L38;
    L40:
        if (r8 == 0) goto L42;
        r37 = r8;
    L42:
        if (r37 == 0) goto L44;
    L33:
        boolean r38 = true;
    L46:
        if (this.backgroundPlayAnimationView.getTag() == null) goto L48;
        boolean r62 = true;
    L49:
        FrameLayout r7 = this.backgroundPlayAnimationView;
        if (r38 == false) goto L52;
        Integer r82 = 1;
    L53:
        r7.setTag(r82);
        if (r62 == r38) goto L116;
        if (r38 == false) goto L57;
        this.backgroundPlayAnimationView.setVisibility(0);
    L57:
        AnimatorSet r63 = this.backgroundPlayViewAnimator;
        if (r63 == null) goto L60;
        r63.cancel();
    L60:
        if (r23 == false) goto L91;
        AnimatorSet r64 = new AnimatorSet();
        this.backgroundPlayViewAnimator = r64;
        FrameLayout r72 = this.backgroundPlayAnimationView;
        Property r83 = View.ALPHA;
        if (r38 == false) goto L65;
        float r16 = 1.0f;
    L64:
        char r17 = 4;
        ObjectAnimator r73 = ObjectAnimator.ofFloat(r72, r83, new float[]{r16});
        FrameLayout r84 = this.backgroundPlayAnimationView;
        Property r102 = View.SCALE_X;
        if (r38 == false) goto L70;
        float r162 = 1.0f;
    L69:
        char r18 = 3;
        ObjectAnimator r85 = ObjectAnimator.ofFloat(r84, r102, new float[]{r162});
        FrameLayout r103 = this.backgroundPlayAnimationView;
        Property r11 = View.SCALE_Y;
        if (r38 == false) goto L75;
        float r163 = 1.0f;
    L74:
        char r19 = 2;
        ObjectAnimator r104 = ObjectAnimator.ofFloat(r103, r11, new float[]{r163});
        WallpaperCheckBoxView r112 = this.backgroundCheckBoxView[0];
        Property r14 = View.TRANSLATION_X;
        float r164 = 34.0f;
        if (r38 == false) goto L80;
        float r15 = AndroidUtilities.dp(34.0f);
    L79:
        float r20 = 1.0f;
        ObjectAnimator r5 = ObjectAnimator.ofFloat(r112, r14, new float[]{r15});
        WallpaperCheckBoxView r113 = this.backgroundCheckBoxView[1];
        if (r38 == false) goto L85;
        float r152 = -AndroidUtilities.dp(34.0f);
    L84:
        int r21 = 5;
        ObjectAnimator r42 = ObjectAnimator.ofFloat(r113, r14, new float[]{r152});
        WallpaperCheckBoxView r114 = this.backgroundCheckBoxView[2];
        if (r38 == false) goto L89;
        float r39 = AndroidUtilities.dp(34.0f);
    L90:
        r64.playTogether(new Animator[]{r73, r85, r104, r5, r42, ObjectAnimator.ofFloat(r114, r14, new float[]{r39})});
        this.backgroundPlayViewAnimator.setDuration(180);
        this.backgroundPlayViewAnimator.addListener(new AnonymousClass37(this));
        this.backgroundPlayViewAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT);
        this.backgroundPlayViewAnimator.start();
    L117:
        FrameLayout r310 = this.messagesPlayAnimationView;
        if (r310 != null) goto L120;
        return;
    L120:
        if (r310.getTag() == null) goto L122;
        boolean r311 = true;
    L123:
        this.messagesPlayAnimationView.setTag(1);
        if (r311 == true) goto L134;
        this.messagesPlayAnimationView.setVisibility(0);
        AnimatorSet r2 = this.messagesPlayViewAnimator;
        if (r2 == null) goto L128;
        r2.cancel();
    L128:
        if (r23 == false) goto L131;
        AnimatorSet r22 = new AnimatorSet();
        this.messagesPlayViewAnimator = r22;
        ObjectAnimator r312 = ObjectAnimator.ofFloat(this.messagesPlayAnimationView, View.ALPHA, new float[]{r20});
        ObjectAnimator r43 = ObjectAnimator.ofFloat(this.messagesPlayAnimationView, View.SCALE_X, new float[]{r20});
        ObjectAnimator r52 = ObjectAnimator.ofFloat(this.messagesPlayAnimationView, View.SCALE_Y, new float[]{r20});
        WallpaperCheckBoxView r65 = this.messagesCheckBoxView[0];
        Property r74 = View.TRANSLATION_X;
        ObjectAnimator r66 = ObjectAnimator.ofFloat(r65, r74, new float[]{-AndroidUtilities.dp(r164)});
        ObjectAnimator r75 = ObjectAnimator.ofFloat(this.messagesCheckBoxView[1], r74, new float[]{AndroidUtilities.dp(r164)});
        Animator[] r86 = new Animator[r21];
        r86[0] = r312;
        r86[1] = r43;
        r86[r19] = r52;
        r86[r18] = r66;
        r86[r17] = r75;
        r22.playTogether(r86);
        this.messagesPlayViewAnimator.setDuration(180);
        this.messagesPlayViewAnimator.addListener(new AnonymousClass38(this));
        this.messagesPlayViewAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT);
        this.messagesPlayViewAnimator.start();
        return;
    L131:
        float r313 = r20;
        this.messagesPlayAnimationView.setAlpha(r313);
        this.messagesPlayAnimationView.setScaleX(r313);
        this.messagesPlayAnimationView.setScaleY(r313);
        this.messagesCheckBoxView[0].setTranslationX(-AndroidUtilities.dp(r164));
        this.messagesCheckBoxView[1].setTranslationX(AndroidUtilities.dp(r164));
        return;
    L134:
        return;
    L122:
        r311 = false;
        goto L123
    L89:
        r39 = 0.0f;
        goto L90
    L85:
        r152 = 0.0f;
        goto L84
    L80:
        r15 = 0.0f;
        goto L79
    L75:
        r163 = 0.0f;
        goto L74
    L70:
        r162 = 0.0f;
        goto L69
    L65:
        r16 = 0.0f;
        goto L64
    L91:
        r19 = 2;
        r164 = 34.0f;
        r17 = 4;
        r18 = 3;
        r20 = 1.0f;
        r21 = 5;
        FrameLayout r44 = this.backgroundPlayAnimationView;
        if (r38 == false) goto L94;
        float r53 = 1.0f;
    L95:
        r44.setAlpha(r53);
        FrameLayout r45 = this.backgroundPlayAnimationView;
        if (r38 == false) goto L98;
        float r54 = 1.0f;
    L99:
        r45.setScaleX(r54);
        FrameLayout r46 = this.backgroundPlayAnimationView;
        if (r38 == false) goto L102;
        float r55 = 1.0f;
    L103:
        r46.setScaleY(r55);
        WallpaperCheckBoxView r47 = this.backgroundCheckBoxView[0];
        if (r38 == false) goto L106;
        float r56 = AndroidUtilities.dp(34.0f);
    L107:
        r47.setTranslationX(r56);
        WallpaperCheckBoxView r48 = this.backgroundCheckBoxView[1];
        if (r38 == false) goto L110;
        float r57 = -AndroidUtilities.dp(34.0f);
    L111:
        r48.setTranslationX(r57);
        WallpaperCheckBoxView r49 = this.backgroundCheckBoxView[2];
        if (r38 == false) goto L114;
        float r87 = AndroidUtilities.dp(34.0f);
    L115:
        r49.setTranslationX(r87);
        goto L117
    L114:
        r87 = 0.0f;
        goto L115
    L110:
        r57 = 0.0f;
        goto L111
    L106:
        r56 = 0.0f;
        goto L107
    L102:
        r55 = 0.0f;
        goto L103
    L98:
        r54 = 0.0f;
        goto L99
    L94:
        r53 = 0.0f;
        goto L95
    L52:
        r82 = null;
        goto L53
    L48:
        r62 = false;
        goto L49
    L38:
        if (r6 == 0) goto L40;
        r37 = 0;
    L44:
        r38 = false;
        goto L46
    L32:
        if (this.backgroundGradientColor1 == 0) goto L44;
    L116:
        r19 = 2;
        r164 = 34.0f;
        r17 = 4;
        r18 = 3;
        r20 = 1.0f;
        r21 = 5;
        goto L117
    }

    private void updateSelectedPattern(boolean r5) {
        int r0 = this.patternsListView.getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        View r2 = this.patternsListView.getChildAt(r1);
        if ((r2 instanceof PatternCell) == false) goto L7;
        ((PatternCell) r2).updateSelected(r5);
    L7:
        r1 = r1 + 1;
        goto L3
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    @SuppressLint({"Recycle"})
    public View createView(Context r45) {
        this.msgOutDrawable.setResourceProvider(getResourceProvider());
        this.msgOutDrawableSelected.setResourceProvider(getResourceProvider());
        this.msgOutMediaDrawable.setResourceProvider(getResourceProvider());
        this.msgOutMediaDrawableSelected.setResourceProvider(getResourceProvider());
        int r6 = 1;
        this.hasOwnBackground = true;
        DayNightSwitchDelegate r0 = this.onSwitchDayNightDelegate;
        if (r0 != null) goto L5;
    L7:
        boolean r3 = false;
    L8:
        this.shouldShowDayNightIcon = r3;
        if (r3 == false) goto L19;
        Object r32 = this.currentWallpaper;
        if ((r32 instanceof WallpapersListActivity.FileWallpaper) == false) goto L13;
    L18:
        boolean r33 = true;
    L20:
        this.shouldShowBrightnessControll = r33;
        if (r33 == true) goto L23;
    L27:
        INavigationLayout r02 = this.parentLayout;
        if (r02 != null) goto L30;
    L32:
        this.page1 = new FrameLayout(r45);
        if (this.shouldShowBrightnessControll == true) goto L35;
    L37:
        this.actionBar.createMenu().addItem(0, R.drawable.outline_header_search).setIsSearchField(true).setActionBarMenuItemSearchListener(new AnonymousClass5(this)).setSearchFieldHint(LocaleController.getString(R.string.Search));
        this.actionBar.setBackButtonDrawable(new MenuDrawable());
        this.actionBar.setAddToContainer(false);
        this.actionBar.setTitle(LocaleController.getString(R.string.ThemePreview));
        AnonymousClass6 r03 = new AnonymousClass6(this, r45);
        this.page1 = r03;
        r03.setBackgroundColor(getThemedColor(Theme.key_windowBackgroundWhite));
        this.page1.addView(this.actionBar, LayoutHelper.createFrame(-1, -2.0f));
        RecyclerListView r04 = new RecyclerListView(r45);
        this.listView = r04;
        r04.setVerticalScrollBarEnabled(true);
        this.listView.setItemAnimator(null);
        this.listView.setLayoutAnimation(null);
        this.listView.setLayoutManager(new LinearLayoutManager(r45, 1, false));
        RecyclerListView r05 = this.listView;
        if (LocaleController.isRTL == false) goto L40;
        int r34 = 1;
    L41:
        r05.setVerticalScrollbarPosition(r34);
        RecyclerListView r06 = this.listView;
        if (this.screenType == 0) goto L44;
        float r35 = 12.0f;
    L45:
        r06.setPadding(0, 0, 0, AndroidUtilities.dp(r35));
        this.listView.setOnItemClickListener(new ThemePreviewActivity$$ExternalSyntheticLambda12());
        this.page1.addView(this.listView, LayoutHelper.createFrame(-1, -1, 51));
        FragmentFloatingButton r07 = new FragmentFloatingButton(r45, this.resourceProvider);
        this.floatingButton = r07;
        r07.setImageResource(R.drawable.floating_pencil);
        this.page1.addView(this.floatingButton, FragmentFloatingButton.createDefaultLayoutParams());
        DialogsAdapter r08 = new DialogsAdapter(r45);
        this.dialogsAdapter = r08;
        this.listView.setAdapter(r08);
        this.page2 = new AnonymousClass7(this, r45);
        this.messagesAdapter = new MessagesAdapter(this, r45);
        this.actionBar2 = createActionBar(r45);
        if (AndroidUtilities.isTablet() == false) goto L48;
        this.actionBar2.setOccupyStatusBar(false);
    L48:
        this.actionBar2.setBackButtonDrawable(new BackDrawable(false));
        this.actionBar2.setActionBarMenuOnItemClick(new AnonymousClass8(this));
        int r09 = 0;
    L49:
        BackgroundView[] r36 = this.backgroundImages;
        if (r09 >= 2) goto L52;
        r36[r09] = new BackgroundView(this, getContext());
        this.page2.addView(this.backgroundImages[r09], LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 0.0f, 0.0f, 48.0f));
        r09 = r09 + 1;
        goto L49
    L52:
        BackgroundView r010 = r36[0];
        this.backgroundImage = r010;
        r010.setVisibility(0);
        this.backgroundImages[1].setVisibility(8);
        if (this.screenType != 2) goto L56;
        this.backgroundImage.getImageReceiver().setDelegate(new ThemePreviewActivity$$ExternalSyntheticLambda13(this));
    L56:
        if (MessagesAdapter.m20848$$Nest$fgetshowSecretMessages(this.messagesAdapter) == false) goto L59;
        this.actionBar2.setTitle("Telegram Beta Chat");
        this.actionBar2.setSubtitle(LocaleController.formatPluralString("Members", 505, new Object[0]));
        int r7 = 2;
    L58:
        int r10 = 51;
    L114:
        this.listView2 = new AnonymousClass10(this, r45);
        AnonymousClass11 r011 = new AnonymousClass11(this);
        r011.setDelayAnimations(false);
        this.listView2.setItemAnimator(r011);
        this.listView2.setVerticalScrollBarEnabled(true);
        this.listView2.setOverScrollMode(r7);
        int r012 = this.screenType;
        if (r012 != r7) goto L127;
        RecyclerListView r013 = this.listView2;
        int r4 = AndroidUtilities.dp(4.0f);
        if (this.self == false) goto L119;
    L121:
        int r11 = 0;
    L122:
        int r112 = AndroidUtilities.dp(72 + r11) - 12;
        if (insideBottomSheet() == false) goto L125;
        int r12 = AndroidUtilities.navigationBarHeight;
    L126:
        r013.setPadding(0, r4, 0, r112 + r12);
    L131:
        this.listView2.setClipToPadding(false);
        this.listView2.setLayoutManager(new LinearLayoutManager(r45, 1, true));
        RecyclerListView r014 = this.listView2;
        if (LocaleController.isRTL == false) goto L134;
        int r42 = 1;
    L135:
        r014.setVerticalScrollbarPosition(r42);
        int r015 = this.screenType;
        FrameLayout r43 = this.page2;
        if (r015 != 1) goto L138;
        r43.addView(this.listView2, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 0.0f, 0.0f, 273.0f));
        this.listView2.setOnItemClickListener(new ThemePreviewActivity$$ExternalSyntheticLambda15(this));
    L139:
        this.listView2.setOnScrollListener(new AnonymousClass12(this));
        this.page2.addView(this.actionBar2, LayoutHelper.createFrame(-1, -2.0f));
        WallpaperParallaxEffect r016 = new WallpaperParallaxEffect(r45);
        this.parallaxEffect = r016;
        r016.setCallback(new ThemePreviewActivity$$ExternalSyntheticLambda16(this));
        int r017 = this.screenType;
        if (r017 == 1) goto L144;
        if (r017 == r7) goto L144;
        float r72 = 0.0f;
        float r28 = 14.0f;
    L397:
        this.listView2.setAdapter(this.messagesAdapter);
        AnonymousClass27 r018 = new AnonymousClass27(this, r45);
        this.frameLayout = r018;
        r018.setWillNotDraw(false);
        FrameLayout r019 = this.frameLayout;
        this.fragmentView = r019;
        ViewTreeObserver r020 = r019.getViewTreeObserver();
        ThemePreviewActivity$$ExternalSyntheticLambda9 r37 = new ThemePreviewActivity$$ExternalSyntheticLambda9(this);
        this.onGlobalLayoutListener = r37;
        r020.addOnGlobalLayoutListener(r37);
        ViewPager r021 = new ViewPager(r45);
        this.viewPager = r021;
        r021.addOnPageChangeListener(new AnonymousClass28(this));
        this.viewPager.setAdapter(new AnonymousClass29(this));
        AndroidUtilities.setViewPagerEdgeEffectColor(this.viewPager, getThemedColor(Theme.key_actionBarDefault));
        FrameLayout r022 = this.frameLayout;
        ViewPager r38 = this.viewPager;
        if (this.screenType != 0) goto L400;
        float r16 = 48.0f;
    L401:
        r022.addView(r38, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 0.0f, 0.0f, r16));
        UndoView r023 = new UndoView(r45, this);
        this.undoView = r023;
        r023.setAdditionalTranslationY(AndroidUtilities.dp(51.0f));
        this.frameLayout.addView(this.undoView, LayoutHelper.createFrame(-1, -2.0f, 83, 8.0f, 0.0f, 8.0f, 8.0f));
        if (this.screenType != 0) goto L405;
        View r024 = new View(r45);
        r024.setBackgroundColor(getThemedColor(Theme.key_dialogShadowLine));
        FrameLayout.LayoutParams r39 = new FrameLayout.LayoutParams(-1, 1, 83);
        r39.bottomMargin = AndroidUtilities.dp(48.0f);
        this.frameLayout.addView(r024, r39);
        FrameLayout r025 = new FrameLayout(r45);
        this.saveButtonsContainer = r025;
        r025.setBackgroundColor(getButtonsColor(Theme.key_windowBackgroundWhite));
        this.frameLayout.addView(this.saveButtonsContainer, LayoutHelper.createFrame(-1, 48, 83));
        AnonymousClass30 r026 = new AnonymousClass30(this, r45);
        this.dotsContainer = r026;
        this.saveButtonsContainer.addView(r026, LayoutHelper.createFrame(22, 8, 17));
        TextView r027 = new TextView(r45);
        this.cancelButton = r027;
        r027.setTextSize(1, r28);
        TextView r028 = this.cancelButton;
        int r310 = Theme.key_chat_fieldOverlayText;
        r028.setTextColor(getButtonsColor(r310));
        this.cancelButton.setGravity(17);
        this.cancelButton.setBackgroundDrawable(Theme.createSelectorDrawable(251658240, 0));
        this.cancelButton.setPadding(AndroidUtilities.dp(29.0f), 0, AndroidUtilities.dp(29.0f), 0);
        this.cancelButton.setText(LocaleController.getString(R.string.Cancel));
        this.cancelButton.setTypeface(AndroidUtilities.bold());
        this.saveButtonsContainer.addView(this.cancelButton, LayoutHelper.createFrame(-2, -1, 51));
        this.cancelButton.setOnClickListener(new ThemePreviewActivity$$ExternalSyntheticLambda10(this));
        TextView r029 = new TextView(r45);
        this.doneButton = r029;
        r029.setTextSize(1, 14.0f);
        this.doneButton.setTextColor(getButtonsColor(r310));
        this.doneButton.setGravity(17);
        this.doneButton.setBackgroundDrawable(Theme.createSelectorDrawable(251658240, 0));
        this.doneButton.setPadding(AndroidUtilities.dp(29.0f), 0, AndroidUtilities.dp(29.0f), 0);
        this.doneButton.setText(LocaleController.getString(R.string.ApplyTheme));
        this.doneButton.setTypeface(AndroidUtilities.bold());
        this.saveButtonsContainer.addView(this.doneButton, LayoutHelper.createFrame(-2, -1, 53));
        this.doneButton.setOnClickListener(new ThemePreviewActivity$$ExternalSyntheticLambda11(this));
    L405:
        if (this.screenType == 1) goto L407;
    L413:
        this.themeDescriptions = getThemeDescriptionsInternal();
        setCurrentImage(true);
        updatePlayAnimationView(false);
        if (this.showColor == false) goto L416;
        showPatternsView(0, true, false);
    L416:
        this.scroller = new Scroller(getContext());
        INavigationLayout r030 = this.parentLayout;
        if (r030 == null) goto L426;
        if (r030.getBottomSheet() == null) goto L426;
        this.parentLayout.getBottomSheet().fixNavigationBar(getThemedColor(Theme.key_dialogBackground));
        if (this.screenType != 2) goto L426;
        if (this.dialogId == 0) goto L426;
        this.parentLayout.getBottomSheet().setOverlayNavBarColor(-16777216);
    L426:
        return this.fragmentView;
    L407:
        if (Theme.hasCustomWallpaper() == true) goto L413;
        Theme.ThemeAccent r031 = this.accent;
        if (r031 == null) goto L413;
        if (r031.backgroundOverrideColor == 4294967296L) goto L413;
        selectColorType(2);
        goto L413
    L400:
        r16 = r72;
    L144:
        if (r017 != r7) goto L179;
        r28 = 14.0f;
        AnonymousClass13 r122 = new AnonymousClass13(this, r45, insideBottomSheet());
        this.bottomOverlayChat = r122;
        r122.setWillNotDraw(false);
        FrameLayout r032 = this.bottomOverlayChat;
        int r123 = AndroidUtilities.dp(12.0f);
        int r44 = AndroidUtilities.dp(12.0f);
        int r113 = AndroidUtilities.dp(12.0f);
        int r31 = AndroidUtilities.dp(12.0f);
        if (insideBottomSheet() == false) goto L148;
        int r322 = AndroidUtilities.navigationBarHeight;
    L149:
        r032.setPadding(r123, r44, r113, r31 + r322);
        this.page2.addView(this.bottomOverlayChat, LayoutHelper.createFrame(-1, 0, 81));
        BlurButton r033 = new BlurButton(this, r45);
        this.applyButton1 = r033;
        ScaleStateListAnimator.apply(r033, 0.033f, 1.2f);
        updateApplyButton1(false);
        this.applyButton1.setOnClickListener(new ThemePreviewActivity$$ExternalSyntheticLambda17(this));
        if (this.dialogId > 0) goto L152;
    L161:
        this.bottomOverlayChat.addView(this.applyButton1, LayoutHelper.createFrame(-1, 48.0f, 81, 0.0f, 0.0f, 0.0f, 0.0f));
    L163:
        if (this.shouldShowBrightnessControll == false) goto L180;
        AnonymousClass14 r034 = new AnonymousClass14(this, getContext());
        this.dimmingSliderContainer = r034;
        r034.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
        this.page2.addView(this.dimmingSliderContainer, LayoutHelper.createFrame(Opcodes.OR_INT_LIT8, 76, 49));
        AnonymousClass15 r035 = new AnonymousClass15(this, getContext(), 3);
        this.dimmingSlider = r035;
        r035.setValue(this.dimAmount);
        this.dimmingSlider.setMinMax(0.0f, 0.9f);
        this.dimmingSlider.setOnValueChange(new ThemePreviewActivity$$ExternalSyntheticLambda19(this));
        this.dimmingSliderContainer.addView(this.dimmingSlider);
        DayNightSwitchDelegate r036 = this.onSwitchDayNightDelegate;
        if (r036 == null) goto L180;
        SliderView r46 = this.dimmingSlider;
        if (r036.isDark() == false) goto L169;
        int r037 = 0;
    L170:
        r46.setVisibility(r037);
        SliderView r038 = this.dimmingSlider;
        if (this.onSwitchDayNightDelegate.isDark() == false) goto L173;
        float r47 = 1.0f;
    L174:
        r038.setAlpha(r47);
        SliderView r039 = this.dimmingSlider;
        if (this.onSwitchDayNightDelegate.isDark() == false) goto L177;
        float r48 = this.dimAmount;
    L178:
        r039.setValue(r48);
        goto L180
    L177:
        r48 = 0.0f;
        goto L178
    L173:
        r47 = 0.0f;
        goto L174
    L169:
        r037 = 8;
    L180:
        Rect r040 = new Rect();
        Drawable r49 = r45.getResources().getDrawable(R.drawable.sheet_shadow_round).mutate();
        this.sheetDrawable = r49;
        r49.getPadding(r040);
        this.sheetDrawable.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_windowBackgroundWhite), PorterDuff.Mode.MULTIPLY));
        TextPaint r410 = new TextPaint(1);
        r410.setTextSize(AndroidUtilities.dp(r28));
        r410.setTypeface(AndroidUtilities.bold());
        Object r102 = this.currentWallpaper;
        if (this.screenType == 1) goto L192;
        if ((r102 instanceof WallpapersListActivity.ColorWallpaper) == true) goto L192;
        if ((r102 instanceof WallpapersListActivity.FileWallpaper) == true) goto L188;
    L190:
        int r8 = r7;
    L197:
        String[] r103 = new String[r8];
        int[] r114 = new int[r8];
        this.backgroundCheckBoxView = new WallpaperCheckBoxView[r8];
        if (r8 == 0) goto L230;
        this.backgroundButtonsContainer = new FrameLayout(r45);
        if (this.screenType != 1) goto L202;
    L205:
        r103[0] = LocaleController.getString(R.string.BackgroundColors);
        r103[1] = LocaleController.getString(R.string.BackgroundPattern);
        r103[r7] = LocaleController.getString(R.string.BackgroundMotion);
    L206:
        int r124 = 0;
        int r13 = 0;
    L207:
        if (r124 >= r8) goto L209;
        int r332 = r6;
        int r62 = (int) Math.ceil(r410.measureText(r103[r124]));
        r114[r124] = r62;
        r13 = Math.max(r13, r62);
        r124 = r124 + 1;
        r6 = r332;
        goto L207
    L209:
        int r333 = r6;
        AnonymousClass16 r63 = new AnonymousClass16(this, r45);
        this.backgroundPlayAnimationView = r63;
        r63.setWillNotDraw(false);
        FrameLayout r64 = this.backgroundPlayAnimationView;
        if (this.backgroundGradientColor1 == 0) goto L212;
        int r73 = 0;
    L213:
        r64.setVisibility(r73);
        FrameLayout r65 = this.backgroundPlayAnimationView;
        if (this.backgroundGradientColor1 == 0) goto L216;
        float r74 = 1.0f;
    L217:
        r65.setScaleX(r74);
        FrameLayout r66 = this.backgroundPlayAnimationView;
        if (this.backgroundGradientColor1 == 0) goto L220;
        float r75 = 1.0f;
    L221:
        r66.setScaleY(r75);
        FrameLayout r67 = this.backgroundPlayAnimationView;
        if (this.backgroundGradientColor1 == 0) goto L224;
        float r76 = 1.0f;
    L225:
        r67.setAlpha(r76);
        FrameLayout r68 = this.backgroundPlayAnimationView;
        if (this.backgroundGradientColor1 == 0) goto L228;
        Integer r77 = Integer.valueOf(r333);
    L229:
        r68.setTag(r77);
        this.backgroundButtonsContainer.addView(this.backgroundPlayAnimationView, LayoutHelper.createFrame(48, 48, 17));
        this.backgroundPlayAnimationView.setOnClickListener(new AnonymousClass17(this));
        ImageView r69 = new ImageView(r45);
        this.backgroundPlayAnimationImageView = r69;
        r69.setScaleType(ImageView.ScaleType.CENTER);
        this.backgroundPlayAnimationImageView.setImageResource(R.drawable.bg_rotate_large);
        this.backgroundPlayAnimationView.addView(this.backgroundPlayAnimationImageView, LayoutHelper.createFrame(-2, -2, 17));
    L231:
        int r610 = 0;
    L232:
        if (r610 >= r8) goto L282;
        WallpaperCheckBoxView[] r78 = this.backgroundCheckBoxView;
        if (this.screenType != r333) goto L236;
    L237:
        if (r610 == 0) goto L239;
    L238:
        boolean r311 = true;
    L240:
        r78[r610] = new WallpaperCheckBoxView(r45, r311, this.backgroundImage, this.themeDelegate);
        this.backgroundCheckBoxView[r610].setBackgroundColor(this.backgroundColor);
        this.backgroundCheckBoxView[r610].setText(r103[r610], r114[r610], r13);
        if (this.screenType != 1) goto L243;
        int r312 = 1;
    L251:
        if (r610 != r312) goto L263;
        WallpaperCheckBoxView r313 = this.backgroundCheckBoxView[r610];
        if (this.selectedPattern != null) goto L260;
        Theme.ThemeAccent r79 = this.accent;
        if (r79 != null) goto L257;
    L259:
        boolean r710 = false;
    L261:
        r313.setChecked(r710, false);
    L265:
        int r314 = AndroidUtilities.dp(56.0f) + r13;
        FrameLayout.LayoutParams r711 = new FrameLayout.LayoutParams(r314, -2);
        r711.gravity = 17;
        if (r8 != 3) goto L274;
        if (r610 != 0) goto L269;
    L272:
        r711.leftMargin = (r314 / 2) + AndroidUtilities.dp(10.0f);
    L277:
        this.backgroundButtonsContainer.addView(this.backgroundCheckBoxView[r610], r711);
        WallpaperCheckBoxView r315 = this.backgroundCheckBoxView[r610];
        r315.setOnClickListener(new ThemePreviewActivity$$ExternalSyntheticLambda20(this, r610, r315));
        if (r610 != 2) goto L281;
        this.backgroundCheckBoxView[r610].setAlpha(0.0f);
        this.backgroundCheckBoxView[r610].setVisibility(4);
    L281:
        r610 = r610 + 1;
        r333 = 1;
        goto L232
    L269:
        if (r610 == 2) goto L272;
        r711.rightMargin = (r314 / 2) + AndroidUtilities.dp(10.0f);
        goto L277
    L274:
        if (r610 != 1) goto L276;
        r711.leftMargin = (r314 / 2) + AndroidUtilities.dp(10.0f);
        goto L277
    L276:
        r711.rightMargin = (r314 / 2) + AndroidUtilities.dp(10.0f);
        goto L277
    L257:
        if (TextUtils.isEmpty(r79.patternSlug) == true) goto L259;
    L260:
        r710 = true;
        goto L261
    L263:
        if (r610 != 2) goto L265;
        this.backgroundCheckBoxView[r610].setChecked(this.isMotion, false);
        goto L265
    L243:
        if ((this.currentWallpaper instanceof WallpapersListActivity.ColorWallpaper) == false) goto L245;
        r312 = 1;
        goto L251
    L245:
        WallpaperCheckBoxView r316 = this.backgroundCheckBoxView[r610];
        if (r610 != 0) goto L248;
        boolean r712 = this.isBlurred;
    L249:
        r316.setChecked(r712, false);
        goto L265
    L248:
        r712 = this.isMotion;
        goto L249
    L239:
        r311 = false;
        goto L240
    L236:
        if ((this.currentWallpaper instanceof WallpapersListActivity.ColorWallpaper) == false) goto L238;
    L282:
        r72 = 0.0f;
        if (this.screenType != 1) goto L321;
        int r14 = 2;
        int[] r317 = new int[2];
        this.messagesCheckBoxView = new WallpaperCheckBoxView[2];
        this.messagesButtonsContainer = new FrameLayout(r45);
        String[] r611 = {LocaleController.getString(R.string.BackgroundAnimate), LocaleController.getString(R.string.BackgroundColors)};
        int r82 = 0;
        int r104 = 0;
    L285:
        if (r82 >= r14) goto L288;
        int r115 = (int) Math.ceil(r410.measureText(r611[r82]));
        r317[r82] = r115;
        r104 = Math.max(r104, r115);
        r82 = r82 + 1;
        r14 = 2;
        goto L285
    L288:
        if (this.accent == null) goto L321;
        AnonymousClass18 r411 = new AnonymousClass18(this, r45);
        this.messagesPlayAnimationView = r411;
        r411.setWillNotDraw(false);
        FrameLayout r412 = this.messagesPlayAnimationView;
        if (this.accent.myMessagesGradientAccentColor1 == 0) goto L292;
        int r83 = 0;
    L293:
        r412.setVisibility(r83);
        FrameLayout r413 = this.messagesPlayAnimationView;
        if (this.accent.myMessagesGradientAccentColor1 == 0) goto L296;
        float r84 = 1.0f;
    L297:
        r413.setScaleX(r84);
        FrameLayout r414 = this.messagesPlayAnimationView;
        if (this.accent.myMessagesGradientAccentColor1 == 0) goto L300;
        float r125 = 1.0f;
    L301:
        r414.setScaleY(r125);
        FrameLayout r415 = this.messagesPlayAnimationView;
        if (this.accent.myMessagesGradientAccentColor1 == 0) goto L304;
        float r85 = 1.0f;
    L305:
        r415.setAlpha(r85);
        this.messagesButtonsContainer.addView(this.messagesPlayAnimationView, LayoutHelper.createFrame(48, 48, 17));
        this.messagesPlayAnimationView.setOnClickListener(new AnonymousClass19(this));
        ImageView r416 = new ImageView(r45);
        this.messagesPlayAnimationImageView = r416;
        r416.setScaleType(ImageView.ScaleType.CENTER);
        this.messagesPlayAnimationImageView.setImageResource(R.drawable.bg_rotate_large);
        this.messagesPlayAnimationView.addView(this.messagesPlayAnimationImageView, LayoutHelper.createFrame(-2, -2, 17));
        int r417 = 0;
    L307:
        if (r417 >= 2) goto L321;
        WallpaperCheckBoxView[] r86 = this.messagesCheckBoxView;
        if (r417 != 0) goto L311;
        boolean r126 = true;
    L312:
        r86[r417] = new WallpaperCheckBoxView(r45, r126, this.backgroundImage, this.themeDelegate);
        this.messagesCheckBoxView[r417].setText(r611[r417], r317[r417], r104);
        if (r417 != 0) goto L315;
        this.messagesCheckBoxView[r417].setChecked(this.accent.myMessagesAnimated, false);
    L315:
        int r87 = AndroidUtilities.dp(56.0f) + r104;
        FrameLayout.LayoutParams r116 = new FrameLayout.LayoutParams(r87, -2);
        r116.gravity = 17;
        if (r417 != 1) goto L318;
        r116.leftMargin = (r87 / 2) + AndroidUtilities.dp(10.0f);
    L319:
        this.messagesButtonsContainer.addView(this.messagesCheckBoxView[r417], r116);
        WallpaperCheckBoxView r88 = this.messagesCheckBoxView[r417];
        r88.setOnClickListener(new ThemePreviewActivity$$ExternalSyntheticLambda5(this, r417, r88));
        r417 = r417 + 1;
        goto L307
    L318:
        r116.rightMargin = (r87 / 2) + AndroidUtilities.dp(10.0f);
        goto L319
    L311:
        r126 = false;
        goto L312
    L304:
        r85 = 0.0f;
        goto L305
    L300:
        r125 = 0.1f;
        goto L301
    L296:
        r84 = 0.1f;
        goto L297
    L292:
        r83 = 4;
    L321:
        if (this.screenType != 1) goto L323;
    L324:
        this.isBlurred = false;
        int r318 = 0;
    L326:
        if (r318 >= 2) goto L389;
        this.patternLayout[r318] = new AnonymousClass20(this, r45, r318, r040);
        if (r318 != 1) goto L330;
    L333:
        this.patternLayout[r318].setVisibility(4);
    L334:
        this.patternLayout[r318].setWillNotDraw(false);
        int r418 = this.screenType;
        int r612 = 273;
        int r89 = MediaError.DetailedErrorCode.HLS_SEGMENT_PARSING;
        if (r418 != 2) goto L340;
        if (r318 != 0) goto L338;
        r89 = 321;
    L338:
        FrameLayout.LayoutParams r810 = LayoutHelper.createFrame(-1, r89, 83);
    L343:
        if (r318 == 0) goto L345;
        float r419 = 316.0f;
    L349:
        r810.height = AndroidUtilities.dp(r419);
        if (insideBottomSheet() == false) goto L352;
        r810.height += AndroidUtilities.navigationBarHeight;
    L352:
        if (r318 != 0) goto L354;
        Drawable r420 = this.sheetDrawable;
        Rect r613 = AndroidUtilities.rectTmp2;
        r420.getPadding(r613);
        r810.height += AndroidUtilities.dp(12.0f) + r613.top;
    L354:
        FrameLayout r421 = this.patternLayout[r318];
        if (r318 != 0) goto L357;
        int r614 = AndroidUtilities.dp(12.0f) + r040.top;
    L359:
        if (insideBottomSheet() == false) goto L361;
        int r105 = AndroidUtilities.navigationBarHeight;
    L362:
        r421.setPadding(0, r614, 0, r105);
        this.page2.addView(this.patternLayout[r318], r810);
        if (r318 != 1) goto L365;
    L369:
        this.patternsButtonsContainer[r318] = new AnonymousClass21(this, r45);
        this.patternsButtonsContainer[r318].setWillNotDraw(false);
        this.patternsButtonsContainer[r318].setPadding(0, AndroidUtilities.dp(3.0f), 0, 0);
        this.patternsButtonsContainer[r318].setClickable(true);
        this.patternLayout[r318].addView(this.patternsButtonsContainer[r318], LayoutHelper.createFrame(-1, 51, 80));
        this.patternsCancelButton[r318] = new TextView(r45);
        this.patternsCancelButton[r318].setTextSize(1, 15.0f);
        this.patternsCancelButton[r318].setTypeface(AndroidUtilities.bold());
        TextView r615 = this.patternsCancelButton[r318];
        int r106 = Theme.key_chat_fieldOverlayText;
        r615.setTextColor(getThemedColor(r106));
        this.patternsCancelButton[r318].setText(LocaleController.getString(R.string.Cancel));
        this.patternsCancelButton[r318].setGravity(17);
        this.patternsCancelButton[r318].setPadding(AndroidUtilities.dp(21.0f), 0, AndroidUtilities.dp(21.0f), 0);
        TextView r616 = this.patternsCancelButton[r318];
        int r117 = Theme.key_listSelector;
        r616.setBackgroundDrawable(Theme.createSelectorDrawable(getThemedColor(r117), 0));
        float r19 = 21.0f;
        this.patternsButtonsContainer[r318].addView(this.patternsCancelButton[r318], LayoutHelper.createFrame(-2, -1, 51));
        this.patternsCancelButton[r318].setOnClickListener(new ThemePreviewActivity$$ExternalSyntheticLambda6(this, r318));
        this.patternsSaveButton[r318] = new TextView(r45);
        this.patternsSaveButton[r318].setTextSize(1, 15.0f);
        this.patternsSaveButton[r318].setTypeface(AndroidUtilities.bold());
        this.patternsSaveButton[r318].setTextColor(getThemedColor(r106));
        this.patternsSaveButton[r318].setText(LocaleController.getString(R.string.ApplyTheme));
        this.patternsSaveButton[r318].setGravity(17);
        this.patternsSaveButton[r318].setPadding(AndroidUtilities.dp(21.0f), 0, AndroidUtilities.dp(21.0f), 0);
        this.patternsSaveButton[r318].setBackgroundDrawable(Theme.createSelectorDrawable(getThemedColor(r117), 0));
        this.patternsButtonsContainer[r318].addView(this.patternsSaveButton[r318], LayoutHelper.createFrame(-2, -1, 53));
        this.patternsSaveButton[r318].setOnClickListener(new ThemePreviewActivity$$ExternalSyntheticLambda7(this, r318));
    L370:
        if (r318 != 1) goto L372;
        TextView r422 = new TextView(r45);
        this.patternTitleView = r422;
        r422.setLines(1);
        this.patternTitleView.setSingleLine(true);
        this.patternTitleView.setText(LocaleController.getString(R.string.BackgroundChoosePattern));
        this.patternTitleView.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
        this.patternTitleView.setTextSize(1, 20.0f);
        this.patternTitleView.setTypeface(AndroidUtilities.bold());
        this.patternTitleView.setPadding(AndroidUtilities.dp(r19), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(r19), AndroidUtilities.dp(8.0f));
        this.patternTitleView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        this.patternTitleView.setGravity(16);
        this.patternLayout[r318].addView(this.patternTitleView, LayoutHelper.createFrame(-1, 48.0f, 51, 0.0f, 21.0f, 0.0f, 0.0f));
        AnonymousClass22 r423 = new AnonymousClass22(this, r45);
        this.patternsListView = r423;
        LinearLayoutManager r617 = new LinearLayoutManager(r45, 0, false);
        this.patternsLayoutManager = r617;
        r423.setLayoutManager(r617);
        RecyclerListView r424 = this.patternsListView;
        PatternsAdapter r618 = new PatternsAdapter(this, r45);
        this.patternsAdapter = r618;
        r424.setAdapter(r618);
        this.patternsListView.addItemDecoration(new AnonymousClass23(this));
        this.patternLayout[r318].addView(this.patternsListView, LayoutHelper.createFrame(-1, 100.0f, 51, 0.0f, 76.0f, 0.0f, 0.0f));
        this.patternsListView.setOnItemClickListener(new ThemePreviewActivity$$ExternalSyntheticLambda8(this));
        HeaderCell r425 = new HeaderCell(r45);
        this.intensityCell = r425;
        r425.setText(LocaleController.getString(R.string.BackgroundIntensity));
        this.patternLayout[r318].addView(this.intensityCell, LayoutHelper.createFrame(-1, -2.0f, 51, 0.0f, 175.0f, 0.0f, 0.0f));
        AnonymousClass24 r426 = new AnonymousClass24(this, r45, getResourceProvider());
        this.intensitySeekBar = r426;
        r426.setProgress(this.currentIntensity);
        this.intensitySeekBar.setReportChanges(true);
        this.intensitySeekBar.setDelegate(new AnonymousClass25(this));
        this.patternLayout[r318].addView(this.intensitySeekBar, LayoutHelper.createFrame(-1, 38.0f, 51, 5.0f, 211.0f, 5.0f, 0.0f));
    L388:
        r318 = r318 + 1;
        goto L326
    L372:
        ColorPicker r427 = new ColorPicker(r45, this.editingTheme, new AnonymousClass26(this));
        this.colorPicker = r427;
        r427.setResourcesProvider(getResourceProvider());
        int r428 = this.screenType;
        FrameLayout[] r619 = this.patternLayout;
        if (r428 != 1) goto L387;
        r619[r318].addView(this.colorPicker, LayoutHelper.createFrame(-1, -1, 1));
        boolean r429 = this.applyingTheme.isDark();
        ColorPicker r620 = this.colorPicker;
        if (r429 == false) goto L377;
        r620.setMinBrightness(0.2f);
    L378:
        Theme.ThemeAccent r430 = this.accent;
        if (r430 == null) goto L388;
        if (r430.accentColor2 == 0) goto L383;
        int r40 = 2;
    L384:
        this.colorPicker.setType(1, hasChanges(1), 2, r40, false, 0, false);
        this.colorPicker.setColor(this.accent.accentColor, 0);
        int r431 = this.accent.accentColor2;
        if (r431 == 0) goto L388;
        this.colorPicker.setColor(r431, 1);
        goto L388
    L383:
        r40 = 1;
        goto L384
    L377:
        r620.setMinBrightness(0.05f);
        this.colorPicker.setMaxBrightness(0.8f);
        goto L378
    L387:
        r619[r318].addView(this.colorPicker, LayoutHelper.createFrame(-1, -1.0f, 1, 0.0f, 0.0f, 0.0f, 48.0f));
        goto L388
    L365:
        if (this.screenType == 2) goto L369;
        r19 = 21.0f;
        goto L370
    L361:
        r105 = 0;
        goto L362
    L357:
        r614 = 0;
        goto L359
    L345:
        if (this.screenType != 2) goto L347;
        r612 = 321;
    L347:
        r419 = r612;
        goto L349
    L340:
        if (r318 != 0) goto L342;
        r89 = 273;
    L342:
        r810 = LayoutHelper.createFrame(-1, r89, 83);
        goto L343
    L330:
        if (this.screenType != 2) goto L334;
    L389:
        updateButtonState(false, false);
        if (this.backgroundImage.getImageReceiver().hasBitmapImage() == true) goto L393;
        this.page2.setBackgroundColor(-16777216);
    L393:
        if (this.screenType == 1) goto L397;
        if ((this.currentWallpaper instanceof WallpapersListActivity.ColorWallpaper) == true) goto L397;
        this.backgroundImage.getImageReceiver().setCrossfadeWithOldImage(true);
        goto L397
    L323:
        if ((this.currentWallpaper instanceof WallpapersListActivity.ColorWallpaper) == false) goto L389;
    L228:
        r77 = null;
        goto L229
    L224:
        r76 = 0.0f;
        goto L225
    L220:
        r75 = 0.1f;
        goto L221
    L216:
        r74 = 0.1f;
        goto L217
    L212:
        r73 = 4;
        goto L213
    L202:
        if ((this.currentWallpaper instanceof WallpapersListActivity.ColorWallpaper) == true) goto L205;
        r103[0] = LocaleController.getString(R.string.BackgroundBlurred);
        r103[1] = LocaleController.getString(R.string.BackgroundMotion);
        goto L206
    L230:
        r333 = 1;
        r13 = 0;
        goto L231
    L188:
        if ("t".equals(((WallpapersListActivity.FileWallpaper) r102).slug) == false) goto L190;
    L189:
        r8 = 0;
    L192:
        if ((r102 instanceof WallpapersListActivity.ColorWallpaper) == true) goto L194;
    L196:
        r8 = 3;
        goto L197
    L194:
        if ("d".equals(((WallpapersListActivity.ColorWallpaper) r102).slug) == false) goto L196;
    L152:
        if (this.self == true) goto L161;
        if (this.serverWallpaper != null) goto L161;
        BlurButton r041 = new BlurButton(this, r45);
        this.applyButton2 = r041;
        ScaleStateListAnimator.apply(r041, 0.033f, 1.2f);
        TLRPC.User r042 = getMessagesController().getUser(Long.valueOf(this.dialogId));
        SpannableStringBuilder r432 = new SpannableStringBuilder(_UrlKt.FRAGMENT_ENCODE_SET);
        if (getUserConfig().isPremium() == true) goto L158;
        r432.append("l ");
        r432.setSpan(new ColoredImageSpan(R.drawable.msg_mini_lock3), 0, 1, 33);
    L158:
        r432.append(LocaleController.formatString(R.string.ApplyWallpaperForMeAndPeer, new Object[]{UserObject.getUserName(r042)}));
        this.applyButton2.setText(r432);
        BlurButton r043 = this.applyButton2;     // Catch: Exception -> L427
        r043.setText(Emoji.replaceEmoji(r043.getText(), BlurButton.m20847$$Nest$fgettext(this.applyButton2).getFontMetricsInt(), false));     // Catch: Exception -> L427
    L160:
        this.applyButton2.setOnClickListener(new ThemePreviewActivity$$ExternalSyntheticLambda18(this));
        this.bottomOverlayChat.addView(this.applyButton1, LayoutHelper.createFrame(-1, 48.0f, 81, 0.0f, 0.0f, 0.0f, 58.0f));
        this.bottomOverlayChat.addView(this.applyButton2, LayoutHelper.createFrame(-1, 48.0f, 81, 0.0f, 0.0f, 0.0f, 0.0f));
        goto L163
    L148:
        r322 = 0;
        goto L149
    L179:
        r28 = 14.0f;
        goto L180
    L138:
        r43.addView(this.listView2, LayoutHelper.createFrame(-1, -1, r10));
        goto L139
    L134:
        r42 = r7;
        goto L135
    L125:
        r12 = 0;
        goto L126
    L119:
        if (this.dialogId <= 0) goto L121;
        r11 = 58;
        goto L122
    L127:
        RecyclerListView r433 = this.listView2;
        if (r012 != 1) goto L130;
        r433.setPadding(0, AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(16.0f));
        goto L131
    L130:
        r433.setPadding(0, AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f));
        goto L131
    L59:
        int r044 = this.screenType;
        if (r044 != 2) goto L95;
        long r434 = this.dialogId;
        ActionBar r435 = this.actionBar2;
        if (r434 == 0) goto L64;
        r435.setTitle(LocaleController.getString(R.string.WallpaperPreview));
    L65:
        ActionBarMenu r045 = this.actionBar2.createMenu();
        Object r436 = this.currentWallpaper;
        if ((r436 instanceof WallpapersListActivity.FileWallpaper) == false) goto L71;
        if (((WallpapersListActivity.FileWallpaper) r436).originalPath == null) goto L71;
        r045.addItem(7, R.drawable.msg_header_draw);
    L71:
        if (this.dialogId != 0) goto L84;
        if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L75;
    L76:
        Object r437 = this.currentWallpaper;
        if ((r437 instanceof WallpapersListActivity.ColorWallpaper) == false) goto L81;
        if ("d".equals(((WallpapersListActivity.ColorWallpaper) r437).slug) == true) goto L81;
    L82:
        r045.addItem(5, R.drawable.msg_header_share);
    L81:
        if ((this.currentWallpaper instanceof TLRPC.TL_wallPaper) == false) goto L84;
    L75:
        if (Theme.getActiveTheme().getAccent(false) != null) goto L82;
    L84:
        if (this.dialogId != 0) goto L86;
    L94:
        r7 = 2;
        goto L58
    L86:
        if (this.shouldShowDayNightIcon == false) goto L94;
        RLottieDrawable r25 = new RLottieDrawable(R.raw.sun, AndroidUtilities.dp(28.0f), AndroidUtilities.dp(28.0f), true, null);
        this.sunDrawable = r25;
        this.dayNightItem = r045.addItem(6, r25);
        this.sunDrawable.setPlayInDirectionOfCustomEndFrame(true);
        DayNightSwitchDelegate r046 = this.onSwitchDayNightDelegate;
        if (r046 != null) goto L90;
    L92:
        this.sunDrawable.setCurrentFrame(35);
        this.sunDrawable.setCustomEndFrame(36);
    L93:
        this.sunDrawable.beginApplyLayerColors();
        int r047 = Theme.getColor(Theme.key_chats_menuName);
        this.sunDrawable.setLayerColor("Sunny", r047);
        this.sunDrawable.setLayerColor("Path 6", r047);
        this.sunDrawable.setLayerColor("Path", r047);
        this.sunDrawable.setLayerColor("Path 5", r047);
        this.sunDrawable.commitApplyLayerColors();
        goto L94
    L90:
        if (r046.isDark() == true) goto L92;
        this.sunDrawable.setCustomEndFrame(0);
        this.sunDrawable.setCurrentFrame(0);
        goto L93
    L64:
        r435.setTitle(LocaleController.getString(R.string.BackgroundPreview));
        goto L65
    L95:
        if (r044 != 1) goto L101;
        ActionBarMenu r048 = this.actionBar2.createMenu();
        this.saveItem = r048.addItem(4, LocaleController.getString(R.string.Save));
        r7 = 2;
        r10 = 51;
        AnonymousClass9 r049 = new AnonymousClass9(this, r45, r048, 0, 0);
        this.dropDownContainer = r049;
        r049.setSubMenuOpenSide(1);
        this.dropDownContainer.addSubItem(2, LocaleController.getString(R.string.ColorPickerBackground));
        this.dropDownContainer.addSubItem(1, LocaleController.getString(R.string.ColorPickerMainColor));
        this.dropDownContainer.addSubItem(3, LocaleController.getString(R.string.ColorPickerMyMessages));
        this.dropDownContainer.setAllowCloseAnimation(false);
        this.dropDownContainer.setForceSmoothKeyboard(true);
        ActionBar r050 = this.actionBar2;
        ActionBarMenuItem r319 = this.dropDownContainer;
        if (AndroidUtilities.isTablet() == false) goto L99;
        float r282 = 64.0f;
    L100:
        r050.addView(r319, LayoutHelper.createFrame(-2, -1.0f, 51, r282, 0.0f, 40.0f, 0.0f));
        this.dropDownContainer.setOnClickListener(new ThemePreviewActivity$$ExternalSyntheticLambda14(this));
        TextView r051 = new TextView(r45);
        this.dropDown = r051;
        r051.setImportantForAccessibility(2);
        this.dropDown.setGravity(3);
        this.dropDown.setSingleLine(true);
        this.dropDown.setLines(1);
        this.dropDown.setMaxLines(1);
        this.dropDown.setEllipsize(TextUtils.TruncateAt.END);
        TextView r052 = this.dropDown;
        int r320 = Theme.key_actionBarDefaultTitle;
        r052.setTextColor(getThemedColor(r320));
        this.dropDown.setTypeface(AndroidUtilities.bold());
        this.dropDown.setText(LocaleController.getString(R.string.ColorPickerMainColor));
        Drawable r053 = r45.getResources().getDrawable(R.drawable.ic_arrow_drop_down).mutate();
        r053.setColorFilter(new PorterDuffColorFilter(getThemedColor(r320), PorterDuff.Mode.MULTIPLY));
        this.dropDown.setCompoundDrawablesWithIntrinsicBounds(null, null, r053, null);
        this.dropDown.setCompoundDrawablePadding(AndroidUtilities.dp(4.0f));
        this.dropDown.setPadding(0, 0, AndroidUtilities.dp(10.0f), 0);
        this.dropDownContainer.addView(this.dropDown, LayoutHelper.createFrame(-2, -2.0f, 16, 16.0f, 0.0f, 0.0f, 1.0f));
        goto L114
    L99:
        r282 = 56.0f;
        goto L100
    L101:
        r7 = 2;
        r10 = 51;
        Theme.ThemeInfo r054 = this.applyingTheme;
        TLRPC.TL_theme r321 = r054.info;
        if (r321 == null) goto L104;
        String r055 = r321.title;
    L105:
        int r323 = r055.lastIndexOf(".attheme");
        if (r323 < 0) goto L108;
        r055 = r055.substring(0, r323);
    L108:
        this.actionBar2.setTitle(r055);
        TLRPC.TL_theme r056 = this.applyingTheme.info;
        if (r056 == null) goto L113;
        int r057 = r056.installs_count;
        if (r057 <= 0) goto L113;
        this.actionBar2.setSubtitle(LocaleController.formatPluralString("ThemeInstallCount", r057, new Object[0]));
    L113:
        this.actionBar2.setSubtitle(LocaleController.formatDateOnline((System.currentTimeMillis() / 1000) - 3600, null));
        goto L114
    L104:
        r055 = r054.getName();
        goto L105
    L44:
        r35 = 0.0f;
        goto L45
    L40:
        r34 = 2;
        goto L41
    L35:
        if (SharedConfig.dayNightWallpaperSwitchHint >= 3) goto L37;
        AndroidUtilities.runOnUIThread(new ThemePreviewActivity$$ExternalSyntheticLambda4(this), 2000);
        goto L37
    L30:
        if (r02.isLayersLayout() == false) goto L32;
        this.actionBar.setOccupyStatusBar(false);
        goto L32
    L23:
        if (r0.isDark() == false) goto L25;
        float r058 = 1.0f;
    L26:
        this.progressToDarkTheme = r058;
        goto L27
    L25:
        r058 = 0.0f;
        goto L26
    L13:
        if ((r32 instanceof TLRPC.TL_wallPaper) == false) goto L19;
        if (((TLRPC.TL_wallPaper) r32).document == null) goto L19;
        if (((TLRPC.TL_wallPaper) r32).pattern == false) goto L18;
    L19:
        r33 = false;
        goto L20
    L5:
        if (this.dialogId == 0) goto L7;
        r3 = true;
        goto L8
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r11, int r12, Object... r13) {
        int r1 = 0;
        if (r11 != NotificationCenter.chatWasBoostedByUser) goto L9;
        if (this.dialogId != ((Long) r13[2]).longValue()) goto L105;
        this.boostsStatus = (TL_stories.TL_premium_boostsStatus) r13[0];
        updateApplyButton1(true);
        return;
    L105:
        return;
    L9:
        if (r11 != NotificationCenter.emojiLoaded) goto L20;
        RecyclerListView r112 = this.listView;
        if (r112 == null) goto L112;
        int r113 = r112.getChildCount();
        int r122 = 0;
    L14:
        if (r122 >= r113) goto L113;
        View r132 = this.listView.getChildAt(r122);
        if ((r132 instanceof DialogCell) == false) goto L18;
        ((DialogCell) r132).update(0);
    L18:
        r122 = r122 + 1;
        goto L14
    L113:
        return;
    L112:
        return;
    L20:
        if (r11 != NotificationCenter.invalidateMotionBackground) goto L26;
        RecyclerListView r10 = this.listView2;
        if (r10 == null) goto L106;
        r10.invalidateViews();
        return;
    L106:
        return;
    L26:
        if (r11 != NotificationCenter.didSetNewWallpapper) goto L32;
        if (this.page2 == null) goto L107;
        setCurrentImage(true);
        return;
    L107:
        return;
    L32:
        if (r11 != NotificationCenter.wallpapersNeedReload) goto L39;
        Object r102 = this.currentWallpaper;
        if ((r102 instanceof WallpapersListActivity.FileWallpaper) == false) goto L108;
        WallpapersListActivity.FileWallpaper r103 = (WallpapersListActivity.FileWallpaper) r102;
        if (r103.slug != null) goto L109;
        r103.slug = (String) r13[0];
        return;
    L109:
        return;
    L108:
        return;
    L39:
        long r2 = 0;
        if (r11 != NotificationCenter.wallpapersDidLoad) goto L86;
        ArrayList r114 = (ArrayList) r13[0];
        this.patterns.clear();
        this.patternsDict.clear();
        int r123 = r114.size();
        int r133 = 0;
        boolean r4 = false;
    L42:
        if (r133 >= r123) goto L70;
        TLRPC.WallPaper r5 = (TLRPC.WallPaper) r114.get(r133);
        if ((r5 instanceof TLRPC.TL_wallPaper) == false) goto L69;
        if (r5.pattern == false) goto L69;
        TLRPC.Document r6 = r5.document;
        if (r6 != null) goto L50;
    L52:
        Theme.ThemeAccent r62 = this.accent;
        if (r62 == null) goto L61;
        String r63 = r62.patternSlug;
        if (r63 == null) goto L61;
        if (r63.equals(r5.slug) == false) goto L61;
        this.selectedPattern = (TLRPC.TL_wallPaper) r5;
        setCurrentImage(false);
        updateButtonState(false, false);
    L59:
        r4 = true;
    L61:
        if (this.accent != null) goto L69;
        TLRPC.TL_wallPaper r64 = this.selectedPattern;
        if (r64 == null) goto L69;
        String r65 = r64.slug;
        if (r65 == null) goto L69;
        if (r65.equals(r5.slug) == false) goto L69;
    L50:
        if (this.patternsDict.containsKey(Long.valueOf(r6.id)) == true) goto L52;
        this.patterns.add(r5);
        this.patternsDict.put(Long.valueOf(r5.document.id), r5);
    L69:
        r133 = r133 + 1;
        goto L42
    L70:
        if (r4 == true) goto L74;
        TLRPC.TL_wallPaper r124 = this.selectedPattern;
        if (r124 == null) goto L74;
        this.patterns.add(0, r124);
    L74:
        PatternsAdapter r125 = this.patternsAdapter;
        if (r125 == null) goto L77;
        r125.notifyDataSetChanged();
    L77:
        int r126 = r114.size();
    L78:
        if (r1 >= r126) goto L83;
        TLRPC.WallPaper r134 = (TLRPC.WallPaper) r114.get(r1);
        if ((r134 instanceof TLRPC.TL_wallPaper) == false) goto L82;
        r2 = MediaDataController.calcHash(r2, r134.id);
    L82:
        r1 = r1 + 1;
        goto L78
    L83:
        TL_account.getWallPapers r115 = new TL_account.getWallPapers();
        r115.hash = r2;
        ConnectionsManager.getInstance(this.currentAccount).bindRequestToGuid(ConnectionsManager.getInstance(this.currentAccount).sendRequest(r115, new ThemePreviewActivity$$ExternalSyntheticLambda3(this)), this.classGuid);
        return;
    L86:
        if (r11 == NotificationCenter.wallpaperSettedToUser) goto L88;
        return;
    L88:
        if (this.dialogId == 0) goto L111;
        finishFragment();
        return;
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public int getObserverTag() {
        return this.TAG;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public Theme.ResourcesProvider getResourceProvider() {
        return this.themeDelegate;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        if (this.shouldShowDayNightIcon == false) goto L7;
        return getThemeDescriptionsInternal();
    L7:
        return super.getThemeDescriptions();
    }

    public ArrayList<ThemeDescription> getThemeDescriptionsInternal() {
        ThemePreviewActivity$$ExternalSyntheticLambda1 r8 = new ThemePreviewActivity$$ExternalSyntheticLambda1(this);
        ArrayList<ThemeDescription> r10 = new ArrayList();
        FrameLayout r2 = this.page1;
        int r3 = ThemeDescription.FLAG_BACKGROUND;
        int r18 = Theme.key_windowBackgroundWhite;
        r10.add(new ThemeDescription(r2, r3, null, null, null, r8, r18));
        ViewPager r1 = this.viewPager;
        int r21 = ThemeDescription.FLAG_LISTGLOWCOLOR;
        int r29 = Theme.key_actionBarDefault;
        r10.add(new ThemeDescription(r1, r21, null, null, null, null, r29));
        r10.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r29));
        ActionBar r12 = this.actionBar;
        int r212 = ThemeDescription.FLAG_AB_SELECTORCOLOR;
        int r37 = Theme.key_actionBarDefaultSelector;
        r10.add(new ThemeDescription(r12, r212, null, null, null, null, r37));
        ActionBar r13 = this.actionBar;
        int r213 = ThemeDescription.FLAG_AB_TITLECOLOR;
        int r26 = Theme.key_actionBarDefaultTitle;
        r10.add(new ThemeDescription(r13, r213, null, null, null, null, r26));
        r10.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SEARCH, null, null, null, null, Theme.key_actionBarDefaultSearch));
        r10.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SEARCHPLACEHOLDER, null, null, null, null, Theme.key_actionBarDefaultSearchPlaceholder));
        r10.add(new ThemeDescription(this.actionBar2, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r29));
        r10.add(new ThemeDescription(this.actionBar2, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, r26));
        r10.add(new ThemeDescription(this.actionBar2, ThemeDescription.FLAG_AB_SUBTITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultSubtitle));
        r10.add(new ThemeDescription(this.actionBar2, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, r37));
        r10.add(new ThemeDescription(this.actionBar2, ThemeDescription.FLAG_AB_SUBMENUBACKGROUND, null, null, null, r8, Theme.key_actionBarDefaultSubmenuBackground));
        r10.add(new ThemeDescription(this.actionBar2, ThemeDescription.FLAG_AB_SUBMENUITEM, null, null, null, r8, Theme.key_actionBarDefaultSubmenuItem));
        r10.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, r29));
        r10.add(new ThemeDescription(this.listView2, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, r29));
        if (this.useDefaultThemeForButtons == true) goto L5;
        r10.add(new ThemeDescription(this.saveButtonsContainer, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r18));
        TextView r132 = this.cancelButton;
        int r14 = ThemeDescription.FLAG_TEXTCOLOR;
        int r19 = Theme.key_chat_fieldOverlayText;
        r10.add(new ThemeDescription(r132, r14, null, null, null, null, r19));
        r10.add(new ThemeDescription(this.doneButton, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r19));
    L5:
        ColorPicker r15 = this.colorPicker;
        if (r15 == null) goto L8;
        r15.provideThemeDescriptions(r10);
    L8:
        int r11 = 0;
        if (this.patternLayout == null) goto L27;
        int r16 = 0;
    L12:
        if (r16 >= this.patternLayout.length) goto L14;
        r10.add(new ThemeDescription(this.patternLayout[r16], 0, null, null, new Drawable[]{Theme.chat_composeShadowDrawable}, null, Theme.key_chat_messagePanelShadow));
        r10.add(new ThemeDescription(this.patternLayout[r16], 0, null, Theme.chat_composeBackgroundPaint, null, null, Theme.key_chat_messagePanelBackground));
        r16 = r16 + 1;
        goto L12
    L14:
        int r17 = 0;
    L16:
        if (r17 >= this.patternsButtonsContainer.length) goto L18;
        r10.add(new ThemeDescription(this.patternsButtonsContainer[r17], 0, null, null, new Drawable[]{Theme.chat_composeShadowDrawable}, null, Theme.key_chat_messagePanelShadow));
        r10.add(new ThemeDescription(this.patternsButtonsContainer[r17], 0, null, Theme.chat_composeBackgroundPaint, null, null, Theme.key_chat_messagePanelBackground));
        r17 = r17 + 1;
        goto L16
    L18:
        r10.add(new ThemeDescription(this.bottomOverlayChat, 0, null, null, new Drawable[]{Theme.chat_composeShadowDrawable}, null, Theme.key_chat_messagePanelShadow));
        r10.add(new ThemeDescription(this.bottomOverlayChat, 0, null, Theme.chat_composeBackgroundPaint, null, null, Theme.key_chat_messagePanelBackground));
        int r110 = 0;
    L20:
        if (r110 >= this.patternsSaveButton.length) goto L22;
        r10.add(new ThemeDescription(this.patternsSaveButton[r110], ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_chat_fieldOverlayText));
        r110 = r110 + 1;
        goto L20
    L22:
        int r111 = 0;
    L24:
        if (r111 >= this.patternsCancelButton.length) goto L26;
        r10.add(new ThemeDescription(this.patternsCancelButton[r111], ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_chat_fieldOverlayText));
        r111 = r111 + 1;
        goto L24
    L26:
        r10.add(new ThemeDescription(this.intensitySeekBar, 0, new Class[]{SeekBarView.class}, new String[]{"innerPaint1"}, null, null, null, Theme.key_player_progressBackground));
        r10.add(new ThemeDescription(this.intensitySeekBar, 0, new Class[]{SeekBarView.class}, new String[]{"outerPaint1"}, null, null, null, Theme.key_player_progress));
        r10.add(new ThemeDescription(this.intensityCell, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueHeader));
        r10.add(new ThemeDescription(this.listView2, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgInDrawable, Theme.chat_msgInMediaDrawable}, null, Theme.key_chat_inBubble));
        r10.add(new ThemeDescription(this.listView2, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgInSelectedDrawable, Theme.chat_msgInMediaSelectedDrawable}, null, Theme.key_chat_inBubbleSelected));
        Drawable[] r172 = Theme.chat_msgInDrawable.getShadowDrawables();
        int r192 = Theme.key_chat_inBubbleShadow;
        r10.add(new ThemeDescription(this.listView2, 0, new Class[]{ChatMessageCell.class}, null, r172, null, r192));
        r10.add(new ThemeDescription(this.listView2, 0, new Class[]{ChatMessageCell.class}, null, Theme.chat_msgInMediaDrawable.getShadowDrawables(), null, r192));
        r10.add(new ThemeDescription(this.listView2, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{this.msgOutDrawable, this.msgOutMediaDrawable}, null, Theme.key_chat_outBubble));
        r10.add(new ThemeDescription(this.listView2, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{this.msgOutDrawable, this.msgOutMediaDrawable}, null, Theme.key_chat_outBubbleGradient1));
        r10.add(new ThemeDescription(this.listView2, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{this.msgOutDrawable, this.msgOutMediaDrawable}, null, Theme.key_chat_outBubbleGradient2));
        r10.add(new ThemeDescription(this.listView2, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{this.msgOutDrawable, this.msgOutMediaDrawable}, null, Theme.key_chat_outBubbleGradient3));
        r10.add(new ThemeDescription(this.listView2, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgOutSelectedDrawable, Theme.chat_msgOutMediaSelectedDrawable}, null, Theme.key_chat_outBubbleSelected));
        Drawable[] r173 = Theme.chat_msgOutDrawable.getShadowDrawables();
        int r193 = Theme.key_chat_outBubbleShadow;
        r10.add(new ThemeDescription(this.listView2, 0, new Class[]{ChatMessageCell.class}, null, r173, null, r193));
        r10.add(new ThemeDescription(this.listView2, 0, new Class[]{ChatMessageCell.class}, null, Theme.chat_msgOutMediaDrawable.getShadowDrawables(), null, r193));
        r10.add(new ThemeDescription(this.listView2, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_messageTextIn));
        r10.add(new ThemeDescription(this.listView2, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_messageTextOut));
        r10.add(new ThemeDescription(this.listView2, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgOutCheckDrawable}, null, Theme.key_chat_outSentCheck));
        r10.add(new ThemeDescription(this.listView2, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgOutCheckSelectedDrawable}, null, Theme.key_chat_outSentCheckSelected));
        r10.add(new ThemeDescription(this.listView2, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgOutCheckReadDrawable, Theme.chat_msgOutHalfCheckDrawable}, null, Theme.key_chat_outSentCheckRead));
        r10.add(new ThemeDescription(this.listView2, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgOutCheckReadSelectedDrawable, Theme.chat_msgOutHalfCheckSelectedDrawable}, null, Theme.key_chat_outSentCheckReadSelected));
        r10.add(new ThemeDescription(this.listView2, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgMediaCheckDrawable, Theme.chat_msgMediaHalfCheckDrawable}, null, Theme.key_chat_mediaSentCheck));
        r10.add(new ThemeDescription(this.listView2, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inReplyLine));
        r10.add(new ThemeDescription(this.listView2, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outReplyLine));
        r10.add(new ThemeDescription(this.listView2, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inReplyNameText));
        r10.add(new ThemeDescription(this.listView2, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outReplyNameText));
        r10.add(new ThemeDescription(this.listView2, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inReplyMessageText));
        r10.add(new ThemeDescription(this.listView2, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outReplyMessageText));
        r10.add(new ThemeDescription(this.listView2, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inReplyMediaMessageSelectedText));
        r10.add(new ThemeDescription(this.listView2, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outReplyMediaMessageSelectedText));
        r10.add(new ThemeDescription(this.listView2, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inTimeText));
        r10.add(new ThemeDescription(this.listView2, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outTimeText));
        r10.add(new ThemeDescription(this.listView2, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inTimeSelectedText));
        r10.add(new ThemeDescription(this.listView2, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outTimeSelectedText));
    L27:
        r10.add(new ThemeDescription(null, 0, null, null, null, null, r8, Theme.key_divider));
        r10.add(new ThemeDescription(null, 0, null, null, null, null, r8, Theme.key_dialogBackground));
        r10.add(new ThemeDescription(null, 0, null, null, null, null, r8, Theme.key_windowBackgroundWhiteBlackText));
        r10.add(new ThemeDescription(null, 0, null, null, null, null, r8, Theme.key_dialogBackgroundGray));
    L29:
        if (r11 >= r10.size()) goto L31;
        r10.get(r11).resourcesProvider = getResourceProvider();
        r11 = r11 + 1;
        goto L29
    L31:
        return r10;
    }

    public boolean insideBottomSheet() {
        INavigationLayout r0 = this.parentLayout;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.getBottomSheet() == null) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSwipeBackEnabled(MotionEvent r4) {
        if (this.screenType == 2) goto L6;
        return false;
    L6:
        if (this.hasScrollingBackground == false) goto L11;
        if (r4 != null) goto L9;
        return true;
    L9:
        if (r4.getY() <= (AndroidUtilities.statusBarHeight + ActionBar.getCurrentActionBarHeight())) goto L14;
        return false;
    L14:
        return true;
    L11:
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r2) {
        if (checkDiscard(r2) == true) goto L6;
        return false;
    L6:
        cancelThemeApply(true);
        return super.onBackPressed(r2);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onBottomSheetCreated() {
        super.onBottomSheetCreated();
        INavigationLayout r0 = this.parentLayout;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getBottomSheet() == null) goto L13;
        this.parentLayout.getBottomSheet().fixNavigationBar(getThemedColor(Theme.key_dialogBackground));
        if (this.screenType == 2) goto L9;
        return;
    L9:
        if (this.dialogId == 0) goto L15;
        this.parentLayout.getBottomSheet().setOverlayNavBarColor(-16777216);
        return;
    L15:
        return;
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onFailedDownload(String r1, boolean r2) {
        updateButtonState(true, r2);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.chatWasBoostedByUser);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiLoaded);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.invalidateMotionBackground);
        getNotificationCenter().addObserver(this, NotificationCenter.wallpaperSettedToUser);
        int r0 = this.screenType;
        if (r0 == 1) goto L5;
        if (r0 == 0) goto L5;
    L6:
        int r02 = this.screenType;
        if (r02 == 2) goto L9;
        if (r02 == 1) goto L9;
    L11:
        if (this.screenType == 0) goto L13;
    L16:
        Point r03 = AndroidUtilities.displaySize;
        int r04 = Math.min(r03.x, r03.y);
        Point r1 = AndroidUtilities.displaySize;
        this.imageFilter = ((int) (r04 / AndroidUtilities.density)) + "_" + ((int) (Math.max(r1.x, r1.y) / AndroidUtilities.density)) + "_f";
        Point r05 = AndroidUtilities.displaySize;
        this.maxWallpaperSize = Math.max(r05.x, r05.y);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.wallpapersNeedReload);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.wallpapersDidLoad);
        this.TAG = DownloadController.getInstance(this.currentAccount).generateObserverTag();
        if (this.patterns != null) goto L20;
        this.patterns = new ArrayList();
        MessagesStorage.getInstance(this.currentAccount).getWallpapers();
    L20:
        return super.onFragmentCreate();
    L13:
        if (this.accent != null) goto L16;
        this.isMotion = Theme.isWallpaperMotion();
    L9:
        Theme.setChangingWallpaper(true);
    L5:
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.didSetNewWallpapper);
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.chatWasBoostedByUser);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiLoaded);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.invalidateMotionBackground);
        getNotificationCenter().removeObserver(this, NotificationCenter.wallpaperSettedToUser);
        FrameLayout r0 = this.frameLayout;
        if (r0 != null) goto L5;
    L7:
        int r02 = this.screenType;
        if (r02 == 2) goto L11;
        if (r02 == 1) goto L11;
    L13:
        int r03 = this.screenType;
        if (r03 != 2) goto L19;
        Bitmap r04 = this.blurredBitmap;
        if (r04 == null) goto L18;
        r04.recycle();
        this.blurredBitmap = null;
    L18:
        this.themeDelegate.applyChatServiceMessageColor();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didSetNewWallpapper, new Object[0]);
    L23:
        if (this.screenType == 0) goto L25;
    L26:
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.wallpapersNeedReload);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.wallpapersDidLoad);
    L27:
        super.onFragmentDestroy();
        checkBlur(null);
        return;
    L25:
        if (this.accent == null) goto L27;
    L19:
        if (r03 == 1) goto L21;
        if (r03 != 0) goto L23;
    L21:
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didSetNewWallpapper);
    L11:
        if (this.onSwitchDayNightDelegate != null) goto L13;
        AndroidUtilities.runOnUIThread(new ThemePreviewActivity$$ExternalSyntheticLambda2());
        goto L13
    L5:
        if (this.onGlobalLayoutListener == null) goto L7;
        r0.getViewTreeObserver().removeOnGlobalLayoutListener(this.onGlobalLayoutListener);
        goto L7
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        if (this.isMotion == false) goto L5;
        this.parallaxEffect.setEnabled(false);
    L5:
        Theme.disallowChangeServiceMessageColor = false;
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onProgressDownload(String r1, long r2, long r4) {
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onProgressUpload(String r1, long r2, long r4, boolean r6) {
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        DialogsAdapter r0 = this.dialogsAdapter;
        if (r0 == null) goto L5;
        r0.notifyDataSetChanged();
    L5:
        MessagesAdapter r02 = this.messagesAdapter;
        if (r02 == null) goto L9;
        r02.notifyDataSetChanged();
    L9:
        if (this.isMotion == false) goto L11;
        this.parallaxEffect.setEnabled(true);
    L11:
        Theme.disallowChangeServiceMessageColor = true;
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onSuccessDownload(String r2) {
        updateButtonState(false, true);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationStart(boolean r1, boolean r2) {
        super.onTransitionAnimationStart(r1, r2);
        if (r1 == false) goto L5;
        return;
    L5:
        if (this.screenType != 2) goto L9;
        this.themeDelegate.applyChatServiceMessageColor();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didSetNewWallpapper, new Object[0]);
        return;
    }

    public void setDelegate(WallpaperActivityDelegate r1) {
        this.delegate = r1;
    }

    public void setDialogId(long r3) {
        this.dialogId = r3;
        if (r3 != 0) goto L5;
    L8:
        boolean r32 = true;
    L9:
        this.self = r32;
        return;
    L5:
        if (r3 == getUserConfig().getClientUserId()) goto L8;
        r32 = false;
        goto L9
    }

    public void setForceDark(boolean r3, boolean r4) {
        int r0 = 0;
        if (r4 == false) goto L12;
        RLottieDrawable r42 = this.sunDrawable;
        if (r3 == false) goto L7;
        r0 = r42.getFramesCount();
    L7:
        r42.setCustomEndFrame(r0);
        RLottieDrawable r2 = this.sunDrawable;
        if (r2 == null) goto L19;
        r2.start();
        return;
    L19:
        return;
    L12:
        if (r3 == false) goto L14;
        int r32 = this.sunDrawable.getFramesCount() - 1;
    L15:
        this.sunDrawable.setCurrentFrame(r32, false, true);
        this.sunDrawable.setCustomEndFrame(r32);
        ActionBarMenuItem r22 = this.dayNightItem;
        if (r22 == null) goto L20;
        r22.invalidate();
        return;
    L20:
        return;
    L14:
        r32 = 0;
        goto L15
    }

    public void setInitialModes(boolean r1, boolean r2, float r3) {
        this.isBlurred = r1;
        this.isMotion = r2;
        this.dimAmount = r3;
    }

    public void setOnSwitchDayNightDelegate(DayNightSwitchDelegate r1) {
        this.onSwitchDayNightDelegate = r1;
    }

    public void setPatterns(ArrayList<Object> r8) {
        this.patterns = r8;
        if (this.screenType != 1) goto L5;
    L6:
        WallpapersListActivity.ColorWallpaper r0 = (WallpapersListActivity.ColorWallpaper) this.currentWallpaper;
        if (r0.patternId == 0) goto L19;
        int r82 = r8.size();
        int r1 = 0;
    L9:
        if (r1 >= r82) goto L14;
        TLRPC.TL_wallPaper r2 = (TLRPC.TL_wallPaper) this.patterns.get(r1);
        if (r2.id == r0.patternId) goto L12;
        r1 = r1 + 1;
        goto L9
    L12:
        this.selectedPattern = r2;
    L14:
        this.currentIntensity = r0.intensity;
        return;
    L19:
        return;
    L5:
        if ((this.currentWallpaper instanceof WallpapersListActivity.ColorWallpaper) == true) goto L6;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void setResourceProvider(Theme.ResourcesProvider r1) {
        this.themeDelegate.parentProvider = r1;
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public void toggleTheme() {
        if (this.changeDayNightView == null) goto L6;
        return;
    L6:
        if (insideBottomSheet() == false) goto L9;
        Window r0 = this.parentLayout.getBottomSheet().getWindow();
    L8:
        FrameLayout r02 = (FrameLayout) r0.getDecorView();
        Bitmap r8 = Bitmap.createBitmap(r02.getWidth(), r02.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas r3 = new Canvas(r8);
        this.dayNightItem.setAlpha(0.0f);
        r02.draw(r3);
        this.dayNightItem.setAlpha(1.0f);
        Paint r7 = new Paint(1);
        r7.setColor(-16777216);
        r7.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        Paint r9 = new Paint(1);
        r9.setFilterBitmap(true);
        int[] r2 = new int[2];
        this.dayNightItem.getLocationInWindow(r2);
        float r10 = r2[0];
        float r11 = r2[1];
        float r6 = Math.max(r8.getHeight(), r8.getWidth()) + AndroidUtilities.navigationBarHeight;
        Shader.TileMode r22 = Shader.TileMode.CLAMP;
        r9.setShader(new BitmapShader(r8, r22, r22));
        AnonymousClass40 r03 = new AnonymousClass40(this, getContext(), r3, r10 + (this.dayNightItem.getMeasuredWidth() / 2.0f), r11 + (this.dayNightItem.getMeasuredHeight() / 2.0f), r6, r7, r8, r9, r10, r11);
        this.changeDayNightView = r03;
        r03.setOnTouchListener(new ThemePreviewActivity$$ExternalSyntheticLambda32());
        this.changeDayNightViewProgress = 0.0f;
        ValueAnimator r04 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.changeDayNightViewAnimator = r04;
        r04.addUpdateListener(new AnonymousClass41(this));
        this.changeDayNightViewAnimator.addListener(new AnonymousClass42(this));
        this.changeDayNightViewAnimator.setDuration(400);
        this.changeDayNightViewAnimator.setInterpolator(Easings.easeInOutQuad);
        this.changeDayNightViewAnimator.start();
        r02.addView(this.changeDayNightView, new ViewGroup.LayoutParams(-1, -1));
        AndroidUtilities.runOnUIThread(new ThemePreviewActivity$$ExternalSyntheticLambda33(this));
        return;
    L9:
        r0 = getParentActivity().getWindow();
        goto L8
    }
}

package org.telegram.ui.web;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.URLSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import j$.net.URLDecoder;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Collections;
import okhttp3.internal.url._UrlKt;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BottomSheetTabs;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.Text;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.WrappedResourceProvider;
import org.telegram.ui.web.BrowserHistory;
import org.telegram.ui.web.WebMetadataCache;

/* loaded from: classes6.dex */
public class AddressBarList extends FrameLayout {
    private int backgroundColor;
    private final BookmarksList bookmarksList;
    public final int currentAccount;
    public final FrameLayout currentContainer;
    public final Drawable currentCopyBackground;
    public final ImageView currentCopyView;
    public final ImageView currentIconView;
    public final TextView currentLinkView;
    public final LinearLayout currentTextContainer;
    public final TextView currentTitleView;
    public final FrameLayout currentView;
    private final Drawable currentViewBackground;
    private int grayBackgroundColor;
    public boolean hideCurrent;
    private float[] hsv;
    private AsyncTask<String, Void, String> lastTask;
    private int listBackgroundColor;
    public UniversalRecyclerView listView;
    private Runnable onCurrentClick;
    private Utilities.Callback<String> onQueryClick;
    private Utilities.Callback<String> onQueryInsertClick;
    private Utilities.Callback<String> onURLClick;
    private float openProgress;
    public boolean opened;
    public final WrappedResourceProvider resourceProvider;
    private int rippleColor;
    public final View space;
    public final ArrayList<String> suggestions;
    private int textColor;

    /* renamed from: org.telegram.ui.web.AddressBarList$1, reason: invalid class name */
    public class AnonymousClass1 extends UniversalRecyclerView {
        final /* synthetic */ AddressBarList this$0;

        public AnonymousClass1(AddressBarList r1, Context r2, int r3, int r4, Utilities.Callback2 r5, Utilities.Callback5 r6, Utilities.Callback5Return r7, Theme.ResourcesProvider r8) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7, r8);
        }

        @Override // androidx.recyclerview.widget.RecyclerView
        public void onScrolled(int r1, int r2) {
            super.onScrolled(r1, r2);
            if (canScrollVertically(1) == false) goto L5;
            return;
        L5:
            if (AddressBarList.m21941$$Nest$fgetbookmarksList(this.this$0) != null) goto L7;
            return;
        L7:
            if (BookmarksList.m21947$$Nest$fgetattached(AddressBarList.m21941$$Nest$fgetbookmarksList(this.this$0)) == false) goto L12;
            AddressBarList.m21941$$Nest$fgetbookmarksList(this.this$0).load();
            return;
        }
    }

    /* renamed from: org.telegram.ui.web.AddressBarList$2, reason: invalid class name */
    public class AnonymousClass2 extends View {
        final /* synthetic */ AddressBarList this$0;

        public AnonymousClass2(AddressBarList r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(6.0f), TLObject.FLAG_30));
        }
    }

    public static class Address2View extends FrameLayout {
        private final Paint dividerPaint;
        public final ImageView iconView;
        public final ImageView insertView;
        private boolean needDivider;
        public final TextView textView;

        public static class Factory extends UItem.UItemFactory<Address2View> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem as(int r1, String r2, View.OnClickListener r3, boolean r4, boolean r5, AddressBarList r6) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.intValue = r1;
                r0.text = r2;
                r0.clickCallback = r3;
                r0.accent = r4;
                r0.red = r5;
                r0.object = Boolean.TRUE;
                r0.object2 = r6;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r9, UItem r10, boolean r11, UniversalAdapter r12, UniversalRecyclerView r13) {
                Address2View r0 = (Address2View) r9;
                if (r10.object != null) goto L6;
                r0.setAsShowMore((AddressBarList) r10.object2);
                return;
            L6:
                r0.set(r10.intValue, r10.text.toString(), r10.clickCallback, r10.accent, r10.red, (AddressBarList) r10.object2, r11);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public Address2View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new Address2View(r1);
            }
        }

        public Address2View(Context r12) {
            super(r12);
            this.dividerPaint = new Paint(1);
            ImageView r0 = new ImageView(r12);
            this.iconView = r0;
            ImageView.ScaleType r2 = ImageView.ScaleType.CENTER;
            r0.setScaleType(r2);
            r0.setImageResource(R.drawable.menu_clear_recent);
            addView(r0, LayoutHelper.createFrame(32, 32.0f, 19, 10.0f, 8.0f, 8.0f, 8.0f));
            TextView r02 = new TextView(r12);
            this.textView = r02;
            r02.setTextSize(1, 16.0f);
            addView(r02, LayoutHelper.createFrame(-1, -2.0f, 19, 64.0f, 8.0f, 64.0f, 8.0f));
            ImageView r03 = new ImageView(r12);
            this.insertView = r03;
            r03.setScaleType(r2);
            r03.setImageResource(R.drawable.menu_browser_arrowup);
            addView(r03, LayoutHelper.createFrame(32, 32.0f, 21, 8.0f, 8.0f, 10.0f, 8.0f));
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            super.onDraw(r8);
            if (this.needDivider == false) goto L6;
            r8.drawRect(AndroidUtilities.dp(64.0f), getHeight() - Math.max(AndroidUtilities.dp(0.66f), 1), getWidth(), getHeight(), this.dividerPaint);
            return;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
        }

        public void set(int r3, String r4, View.OnClickListener r5, boolean r6, boolean r7, AddressBarList r8, boolean r9) {
            this.iconView.setVisibility(0);
            setColors(AddressBarList.m21943$$Nest$fgetlistBackgroundColor(r8), AddressBarList.m21945$$Nest$fgettextColor(r8));
            ImageView r0 = this.iconView;
            if (r3 != 0) goto L5;
            int r32 = R.drawable.msg_clear_recent;
        L6:
            r0.setImageResource(r32);
            this.textView.setText(r4);
            this.insertView.setOnClickListener(r5);
            setTopBottom(AddressBarList.m21942$$Nest$fgetgrayBackgroundColor(r8), AddressBarList.m21944$$Nest$fgetrippleColor(r8), r6, r7);
            this.dividerPaint.setColor(Theme.multAlpha(AddressBarList.m21945$$Nest$fgettextColor(r8), 0.1f));
            this.needDivider = r9;
            setWillNotDraw(!r9);
            return;
        L5:
            r32 = R.drawable.msg_search;
            goto L6
        }

        public void setAsShowMore(AddressBarList r3) {
            this.iconView.setImageResource(R.drawable.arrow_more);
            this.iconView.setColorFilter(new PorterDuffColorFilter(AddressBarList.m21945$$Nest$fgettextColor(r3), PorterDuff.Mode.SRC_IN));
        }

        public void setColors(int r5, int r6) {
            this.textView.setTextColor(r6);
            ImageView r52 = this.iconView;
            int r2 = Theme.multAlpha(r6, 0.6f);
            PorterDuff.Mode r3 = PorterDuff.Mode.SRC_IN;
            r52.setColorFilter(new PorterDuffColorFilter(r2, r3));
            this.insertView.setColorFilter(new PorterDuffColorFilter(Theme.multAlpha(r6, 0.6f), r3));
            this.insertView.setBackground(Theme.createRadSelectorDrawable(0, Theme.multAlpha(r6, 0.15f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f)));
        }

        public void setTopBottom(int r1, int r2, boolean r3, boolean r4) {
        }
    }

    public static class BookmarkView extends FrameLayout implements Theme.Colorable {
        public final CheckBox2 checkBox;
        private final Paint dividerPaint;
        public final BackupImageView iconView;
        public final ImageView insertView;
        private boolean needDivider;
        private final Theme.ResourcesProvider resourcesProvider;
        public final TextView subtextView;
        private int textColor;
        public final LinearLayout textLayout;
        public final FrameLayout.LayoutParams textLayoutParams;
        public final TextView textView;
        public final TextView timeView;

        /* renamed from: org.telegram.ui.web.AddressBarList$BookmarkView$1, reason: invalid class name */
        public class AnonymousClass1 extends CheckBox2 {
            final /* synthetic */ BookmarkView this$0;

            public AnonymousClass1(BookmarkView r1, Context r2, int r3, Theme.ResourcesProvider r4) {
                this.this$0 = r1;
                super(r2, r3, r4);
            }

            @Override // android.view.View
            public void invalidate() {
                super.invalidate();
                this.this$0.invalidate();
            }
        }

        /* renamed from: org.telegram.ui.web.AddressBarList$BookmarkView$2, reason: invalid class name */
        public class AnonymousClass2 extends Drawable {
            private final Text text;
            final /* synthetic */ BookmarkView this$0;
            final /* synthetic */ String val$firstLetter;

            public AnonymousClass2(BookmarkView r3, String r4) {
                this.this$0 = r3;
                this.val$firstLetter = r4;
                this.text = new Text(r4, 14.0f, AndroidUtilities.bold());
            }

            @Override // android.graphics.drawable.Drawable
            public void draw(Canvas r7) {
                this.text.draw(r7, getBounds().centerX() - (this.text.getCurrentWidth() / 2.0f), getBounds().centerY(), BookmarkView.m21946$$Nest$fgettextColor(this.this$0), 1.0f);
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

        /* renamed from: org.telegram.ui.web.AddressBarList$BookmarkView$3, reason: invalid class name */
        public class AnonymousClass3 extends Drawable {
            private final Text text;
            final /* synthetic */ BookmarkView this$0;
            final /* synthetic */ String val$firstLetter;

            public AnonymousClass3(BookmarkView r3, String r4) {
                this.this$0 = r3;
                this.val$firstLetter = r4;
                this.text = new Text(r4, 14.0f, AndroidUtilities.bold());
            }

            @Override // android.graphics.drawable.Drawable
            public void draw(Canvas r7) {
                this.text.draw(r7, getBounds().centerX() - (this.text.getCurrentWidth() / 2.0f), getBounds().centerY(), BookmarkView.m21946$$Nest$fgettextColor(this.this$0), 1.0f);
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

        public static class Factory extends UItem.UItemFactory<BookmarkView> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem as(MessageObject r2, boolean r3) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.intValue = 3;
                r0.accent = r3;
                r0.object2 = r2;
                return r0;
            }

            public static UItem as(MessageObject r2, boolean r3, String r4) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.intValue = 3;
                r0.accent = r3;
                r0.object2 = r2;
                r0.subtext = r4;
                return r0;
            }

            public static UItem as(BrowserHistory.Entry r2, String r3) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.intValue = 3;
                r0.accent = false;
                r0.object2 = r2;
                r0.subtext = r3;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r3, UItem r4, boolean r5, UniversalAdapter r6, UniversalRecyclerView r7) {
                BookmarkView r2 = (BookmarkView) r3;
                Object r32 = r4.object2;
                String r72 = null;
                if ((r32 instanceof MessageObject) == false) goto L11;
                MessageObject r33 = (MessageObject) r32;
                boolean r42 = r4.accent;
                CharSequence r0 = r4.subtext;
                if (r0 == null) goto L8;
                r72 = r0.toString();
            L8:
                String r1 = r72;
                r2.set(r33, r42, r1, r4.checked, r5);
                return;
            L11:
                if ((r32 instanceof BrowserHistory.Entry) == false) goto L18;
                BrowserHistory.Entry r34 = (BrowserHistory.Entry) r32;
                CharSequence r52 = r4.subtext;
                if (r52 == null) goto L16;
                r72 = r52.toString();
            L16:
                r2.set(r34, r72, r5);
                return;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public boolean contentsEquals(UItem r2, UItem r3) {
                if (r2.object2 == r3.object2) goto L5;
                return false;
            L5:
                if (TextUtils.equals(r2.subtext, r3.subtext) == false) goto L10;
                return true;
            L10:
                return false;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public BookmarkView createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new BookmarkView(r1, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public boolean equals(UItem r2, UItem r3) {
                if (r2.object2 == r3.object2) goto L5;
                return false;
            L5:
                if (TextUtils.isEmpty(r2.subtext) != TextUtils.isEmpty(r3.subtext)) goto L10;
                return true;
            L10:
                return false;
            }
        }

        /* renamed from: -$$Nest$fgettextColor, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m21946$$Nest$fgettextColor(BookmarkView r0) {
            return r0.textColor;
        }

        public BookmarkView(Context r18, Theme.ResourcesProvider r19) {
            super(r18);
            this.dividerPaint = new Paint(1);
            this.resourcesProvider = r19;
            ScaleStateListAnimator.apply(this, 0.03f, 1.25f);
            BackupImageView r3 = new BackupImageView(r18);
            this.iconView = r3;
            r3.setRoundRadius(AndroidUtilities.dp(6.0f));
            addView(r3, LayoutHelper.createFrame(32, 32.0f, 19, 10.0f, 8.0f, 8.0f, 8.0f));
            LinearLayout r32 = new LinearLayout(r18);
            this.textLayout = r32;
            r32.setOrientation(1);
            TextView r5 = new TextView(r18);
            this.textView = r5;
            r5.setTextSize(1, 16.0f);
            r5.setMaxLines(1);
            TextUtils.TruncateAt r6 = TextUtils.TruncateAt.END;
            r5.setEllipsize(r6);
            r32.addView(r5, LayoutHelper.createLinear(-1, -2, 51));
            TextView r52 = new TextView(r18);
            this.subtextView = r52;
            r52.setTextSize(1, 13.0f);
            r52.setMaxLines(1);
            r52.setEllipsize(r6);
            r32.addView(r52, LayoutHelper.createLinear(-1, -2, 51, 0, 3, 0, 0));
            FrameLayout.LayoutParams r53 = LayoutHelper.createFrame(-1, -2.0f, 19, 64.0f, 0.0f, 70.0f, 0.0f);
            this.textLayoutParams = r53;
            addView(r32, r53);
            TextView r33 = new TextView(r18);
            this.timeView = r33;
            r33.setTextSize(1, 13.0f);
            r33.setMaxLines(1);
            r33.setEllipsize(r6);
            r33.setGravity(5);
            r33.setTextAlignment(6);
            addView(r33, LayoutHelper.createFrame(-2, -2.0f, 21, 64.0f, -10.0f, 12.0f, 0.0f));
            ImageView r34 = new ImageView(r18);
            this.insertView = r34;
            r34.setScaleType(ImageView.ScaleType.CENTER);
            r34.setImageResource(R.drawable.attach_arrow_right);
            addView(r34, LayoutHelper.createFrame(32, 32.0f, 21, 8.0f, 8.0f, 8.0f, 8.0f));
            AnonymousClass1 r1 = new AnonymousClass1(this, getContext(), 21, r19);
            this.checkBox = r1;
            r1.setColor(-1, Theme.key_windowBackgroundWhite, Theme.key_checkboxCheck);
            r1.setDrawUnchecked(false);
            r1.setDrawBackgroundAsArc(3);
            addView(r1, LayoutHelper.createFrame(24, 24.0f, 19, 26.0f, 12.0f, 0.0f, 0.0f));
        }

        @Override // org.telegram.ui.ActionBar.Theme.Colorable
        public /* bridge */ /* synthetic */ int[] getColorKeys() {
            return super.getColorKeys();
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            super.onDraw(r8);
            if (this.needDivider == false) goto L6;
            r8.drawRect(AndroidUtilities.dp(59.0f), getHeight() - Math.max(AndroidUtilities.dp(0.66f), 1), getWidth(), getHeight(), this.dividerPaint);
            return;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(56.0f), TLObject.FLAG_30));
        }

        public void set(MessageObject r18, boolean r19, String r20, boolean r21, boolean r22) {
            updateColors();
            if (MessageObject.getMedia(r18) == null) goto L5;
            TLRPC.WebPage r0 = MessageObject.getMedia(r18).webpage;
        L6:
            if (r0 == null) goto L8;
            String r5 = r0.url;
        L9:
            WebMetadataCache.WebMetadata r7 = WebMetadataCache.getInstance().get(AndroidUtilities.getHostAuthority(r5, true));
            String r8 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r0 == null) goto L14;
            String r10 = r0.title;
            if (r10 == null) goto L14;
            this.textView.setText(r10);
        L29:
            this.iconView.clearImage();
            if (r7 == null) goto L34;
            Bitmap r52 = r7.favicon;
            if (r52 == null) goto L34;
            this.iconView.setImageBitmap(r52);
        L47:
            int r72 = 8;
            this.timeView.setVisibility(8);
            ImageView r53 = this.insertView;
            if (r19 == false) goto L50;
            r72 = 0;
        L50:
            r53.setVisibility(r72);
            if (r0 == null) goto L54;
            String r02 = r0.url;
        L53:
            String r54 = r02;
            Uri r03 = Uri.parse(r54);     // Catch: Exception -> L57
            r54 = Browser.replaceHostname(r03, Browser.IDN_toUnicode(r03.getHost()), null);     // Catch: Exception -> L57
        L59:
            r54 = URLDecoder.decode(r54.replaceAll("\\+", "%2b"), "UTF-8");     // Catch: Exception -> L61
            String r04 = BottomSheetTabs.urlWithoutFragment(r54);     // Catch: Exception -> L61
        L63:
            this.subtextView.setText(r04);
            if (TextUtils.isEmpty(r20) == true) goto L66;
            TextView r05 = this.textView;
            r05.setText(AndroidUtilities.highlightText(r05.getText(), r20, this.resourcesProvider));
            TextView r06 = this.subtextView;
            r06.setText(AndroidUtilities.highlightText(r06.getText(), r20, this.resourcesProvider));
        L66:
            TextView r07 = this.textView;
            r07.setText(Emoji.replaceEmoji(r07.getText(), this.textView.getPaint().getFontMetricsInt(), false));
            TextView r08 = this.subtextView;
            r08.setText(Emoji.replaceEmoji(r08.getText(), this.subtextView.getPaint().getFontMetricsInt(), false));
            this.checkBox.setChecked(r21, false);
            this.textLayoutParams.rightMargin = AndroidUtilities.dp(52.0f);
            this.textLayout.setLayoutParams(this.textLayoutParams);
            this.needDivider = r22;
            setWillNotDraw(!r22);
            return;
        L61:
            e = move-exception;
            FileLog.e(e);
            r04 = r54;
        L57:
            e = move-exception;
            FileLog.e(e);     // Catch: Exception -> L61
            goto L59
        L54:
            r02 = AddressBarList.getLink(r18);
        L34:
            if (r0 == null) goto L39;
            TLRPC.Photo r55 = r0.photo;
            if (r55 == null) goto L39;
            this.iconView.setImage(ImageLocation.getForPhoto(FileLoader.getClosestPhotoSizeWithSize(r55.sizes, AndroidUtilities.dp(32.0f), true, null, true), r0.photo), AndroidUtilities.dp(32.0f) + "_" + AndroidUtilities.dp(32.0f), ImageLocation.getForPhoto(FileLoader.getClosestPhotoSizeWithSize(r0.photo.sizes, AndroidUtilities.dp(32.0f), true, null, false), r0.photo), AndroidUtilities.dp(32.0f) + "_" + AndroidUtilities.dp(32.0f), 0, r18);
        L39:
            if (this.textView.getText() != null) goto L41;
            String r56 = _UrlKt.FRAGMENT_ENCODE_SET;
        L42:
            BreakIterator r73 = BreakIterator.getCharacterInstance();
            r73.setText(r56);
            if (r56.isEmpty() == true) goto L46;
            r8 = r56.substring(r73.first(), r73.next());
        L46:
            CombinedDrawable r57 = new CombinedDrawable(Theme.createRoundRectDrawable(AndroidUtilities.dp(6.0f), Theme.multAlpha(this.textColor, 0.1f)), new AnonymousClass2(this, r8));
            r57.setCustomSize(AndroidUtilities.dp(28.0f), AndroidUtilities.dp(28.0f));
            this.iconView.setImageDrawable(r57);
            goto L47
        L41:
            r56 = this.textView.getText().toString();
        L14:
            if (r0 == null) goto L18;
            String r102 = r0.site_name;
            if (r102 == null) goto L18;
            this.textView.setText(r102);
        L18:
            if (r7 != null) goto L20;
        L22:
            if (r7 != null) goto L24;
        L71:
            String r58 = Uri.parse(r5).getHost().split("\\.")[r5.length - 2];     // Catch: Exception -> L28
            this.textView.setText(r58.substring(0, 1).toUpperCase() + r58.substring(1));     // Catch: Exception -> L28
        L28:
            this.textView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            goto L29
        L24:
            if (TextUtils.isEmpty(r7.sitename) == true) goto L71;
            this.textView.setText(r7.sitename);
            goto L29
        L20:
            if (TextUtils.isEmpty(r7.title) == true) goto L22;
            this.textView.setText(r7.title);
            goto L29
        L8:
            r5 = AddressBarList.getLink(r18);
            goto L9
        L5:
            r0 = null;
            goto L6
        }

        public void set(BrowserHistory.Entry r10, String r11, boolean r12) {
            updateColors();
            if (r10 != null) goto L5;
            return;
        L5:
            String r0 = r10.url;
            WebMetadataCache.WebMetadata r1 = r10.meta;
            String r2 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r1 != null) goto L8;
        L10:
            if (r1 != null) goto L12;
        L47:
            String r5 = Uri.parse(r0).getHost().split("\\.")[r5.length - 2];     // Catch: Exception -> L16
            this.textView.setText(r5.substring(0, 1).toUpperCase() + r5.substring(1));     // Catch: Exception -> L16
        L17:
            if (r1 == null) goto L22;
            Bitmap r13 = r1.favicon;
            if (r13 == null) goto L22;
            this.iconView.setImageBitmap(r13);
        L30:
            this.insertView.setVisibility(8);
            Uri r14 = Uri.parse(r0);     // Catch: Exception -> L33
            r0 = Browser.replaceHostname(r14, Browser.IDN_toUnicode(r14.getHost()), null);     // Catch: Exception -> L33
        L35:
            r0 = URLDecoder.decode(r0.replaceAll("\\+", "%2b"), "UTF-8");     // Catch: Exception -> L37
        L39:
            this.subtextView.setText(r0);
            if (TextUtils.isEmpty(r11) == true) goto L42;
            TextView r02 = this.textView;
            r02.setText(AndroidUtilities.highlightText(r02.getText(), r11, this.resourcesProvider));
            TextView r03 = this.subtextView;
            r03.setText(AndroidUtilities.highlightText(r03.getText(), r11, this.resourcesProvider));
        L42:
            TextView r112 = this.textView;
            r112.setText(Emoji.replaceEmoji(r112.getText(), this.textView.getPaint().getFontMetricsInt(), false));
            TextView r113 = this.subtextView;
            r113.setText(Emoji.replaceEmoji(r113.getText(), this.subtextView.getPaint().getFontMetricsInt(), false));
            this.timeView.setText(LocaleController.getInstance().getFormatterDay().format(r10.time));
            this.checkBox.setChecked(false, false);
            this.textLayoutParams.rightMargin = AndroidUtilities.dp(70.0f);
            this.textLayout.setLayoutParams(this.textLayoutParams);
            this.needDivider = r12;
            setWillNotDraw(!r12);
            return;
        L37:
            e = move-exception;
            FileLog.e(e);
        L33:
            e = move-exception;
            FileLog.e(e);     // Catch: Exception -> L37
        L22:
            if (this.textView.getText() != null) goto L24;
            String r15 = _UrlKt.FRAGMENT_ENCODE_SET;
        L25:
            BreakIterator r52 = BreakIterator.getCharacterInstance();
            r52.setText(r15);
            if (r15.isEmpty() == true) goto L29;
            r2 = r15.substring(r52.first(), r52.next());
        L29:
            CombinedDrawable r16 = new CombinedDrawable(Theme.createRoundRectDrawable(AndroidUtilities.dp(6.0f), Theme.multAlpha(this.textColor, 0.1f)), new AnonymousClass3(this, r2));
            r16.setCustomSize(AndroidUtilities.dp(28.0f), AndroidUtilities.dp(28.0f));
            this.iconView.setImageDrawable(r16);
            goto L30
        L24:
            r15 = this.textView.getText().toString();
        L16:
            this.textView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            goto L17
        L12:
            if (TextUtils.isEmpty(r1.sitename) == true) goto L47;
            this.textView.setText(r1.sitename);
            goto L17
        L8:
            if (TextUtils.isEmpty(r1.title) == true) goto L10;
            this.textView.setText(r1.title);
            goto L17
        }

        public void setChecked(boolean r2) {
            this.checkBox.setChecked(r2, true);
        }

        public void setColors(int r4, int r5) {
            this.textColor = r5;
            this.textView.setTextColor(r5);
            this.subtextView.setTextColor(Theme.blendOver(r4, Theme.multAlpha(r5, 0.55f)));
            this.timeView.setTextColor(Theme.multAlpha(r5, 0.55f));
            this.insertView.setColorFilter(new PorterDuffColorFilter(Theme.multAlpha(r5, 0.6f), PorterDuff.Mode.SRC_IN));
        }

        @Override // org.telegram.ui.ActionBar.Theme.Colorable
        public void updateColors() {
            int r0 = Theme.getColor(Theme.key_windowBackgroundWhite, this.resourcesProvider);
            int r1 = Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider);
            setColors(r0, r1);
            this.dividerPaint.setColor(Theme.multAlpha(r1, 0.1f));
            this.iconView.invalidate();
        }
    }

    public static class BookmarksList implements NotificationCenter.NotificationCenterDelegate {
        private boolean attached;
        private final int currentAccount;
        public boolean endReached;
        private int guid;
        public final ArrayList<MessageObject> links;
        private boolean loading;
        private final String query;
        private final Runnable whenUpdated;

        /* renamed from: -$$Nest$fgetattached, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m21947$$Nest$fgetattached(BookmarksList r0) {
            return r0.attached;
        }

        public BookmarksList(int r2, Runnable r3) {
            this(r2, null, r3);
        }

        public BookmarksList(int r2, String r3, Runnable r4) {
            this.links = new ArrayList();
            this.guid = ConnectionsManager.generateClassGuid();
            this.currentAccount = r2;
            this.query = r3;
            this.whenUpdated = r4;
        }

        public void attach() {
            if (this.attached == true) goto L10;
            this.attached = true;
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.mediaDidLoad);
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.bookmarkAdded);
            if (TextUtils.isEmpty(this.query) == false) goto L9;
            load();
            return;
        L9:
            return;
        }

        public void delete(ArrayList<Integer> r3) {
            int r0 = 0;
        L4:
            if (r0 >= this.links.size()) goto L9;
            if (r3.contains(Integer.valueOf(this.links.get(r0).getId())) == false) goto L8;
            this.links.remove(r0);
            r0 = r0 - 1;
        L8:
            r0 = r0 + 1;
            goto L4
        }

        public void detach() {
            if (this.attached == true) goto L5;
            return;
        L5:
            this.attached = false;
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.mediaDidLoad);
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.bookmarkAdded);
            ConnectionsManager.getInstance(this.currentAccount).cancelRequestsForGuid(this.guid);
            this.loading = false;
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r2, int r3, Object... r4) {
            if (r2 != NotificationCenter.mediaDidLoad) goto L9;
            if (((Integer) r4[3]).intValue() != this.guid) goto L12;
            this.loading = false;
            ArrayList r22 = (ArrayList) r4[2];
            this.endReached = ((Boolean) r4[5]).booleanValue();
            this.links.addAll(r22);
            this.whenUpdated.run();
            return;
        L12:
            return;
        L9:
            if (r2 != NotificationCenter.bookmarkAdded) goto L13;
            MessageObject r23 = (MessageObject) r4[0];
            this.links.add(0, r23);
            return;
        }

        public void load() {
            if (this.loading == false) goto L5;
            return;
        L5:
            if (this.endReached == true) goto L21;
            this.loading = true;
            long r2 = UserConfig.getInstance(this.currentAccount).getClientUserId();
            int r4 = 0;
            int r5 = Integer.MAX_VALUE;
        L9:
            if (r4 >= this.links.size()) goto L11;
            r5 = Math.min(r5, this.links.get(r4).getId());
            r4 = r4 + 1;
            goto L9
        L11:
            MediaDataController r42 = MediaDataController.getInstance(this.currentAccount);
            if (this.links.isEmpty() == false) goto L14;
            int r6 = 30;
        L15:
            if (r5 != Integer.MAX_VALUE) goto L17;
            r5 = 0;
        L17:
            r42.loadMedia(r2, r6, r5, 0, 3, 0, 1, this.guid, 0, null, this.query);
            return;
        L14:
            r6 = 50;
            goto L15
        }
    }

    public static class QueryEntry {
        public long lastUsage;
        public final String query;
        public double rank;

        public QueryEntry(String r1, long r2) {
            this.query = r1;
            this.lastUsage = r2;
        }
    }

    public static /* synthetic */ int $r8$lambda$A9HCdiFsN75Rds5ZIjcYL6l3kAw(QueryEntry r2, QueryEntry r3) {
        return (int) (r3.rank - r2.rank);
    }

    /* renamed from: $r8$lambda$GJRZvu6IKhTOgcqxDrwO7HwU-eI, reason: not valid java name */
    public static /* synthetic */ void m21936$r8$lambda$GJRZvu6IKhTOgcqxDrwO7HwUeI(AddressBarList r0, AlertDialog r1, int r2) {
        r0.lambda$clearRecentSearches$1(r1, r2);
    }

    /* renamed from: $r8$lambda$GaVD-CWMgjche6YXYisJmEXPLH4, reason: not valid java name */
    public static /* synthetic */ void m21937$r8$lambda$GaVDCWMgjche6YXYisJmEXPLH4(AddressBarList r0, String r1, View r2) {
        r0.lambda$fillItems$3(r1, r2);
    }

    /* renamed from: $r8$lambda$M1RWMsq_-khurYUX4HFPRF86Yew, reason: not valid java name */
    public static /* synthetic */ int m21938$r8$lambda$M1RWMsq_khurYUX4HFPRF86Yew(QueryEntry r2, QueryEntry r3) {
        return (int) (r3.rank - r2.rank);
    }

    /* renamed from: $r8$lambda$Rqrl2580uTSbNARZ02KFIMj-JOo, reason: not valid java name */
    public static /* synthetic */ void m21939$r8$lambda$Rqrl2580uTSbNARZ02KFIMjJOo(AddressBarList r0, String r1, View r2) {
        r0.lambda$fillItems$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$e37wj0dJJQim6ZUHI3XlcZi4BYE(AddressBarList r0, String r1, boolean r2) {
        r0.lambda$setInput$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$lidUvy6QOwbQPpmgBah8HKujtN8(AddressBarList r0, View r1) {
        r0.clearRecentSearches(r1);
    }

    public static /* synthetic */ void $r8$lambda$tMfSsD2wUG_mVI4Rq6y019yCXUY(AddressBarList r0, boolean r1, String r2) {
        r0.lambda$setInput$6(r1, r2);
    }

    /* renamed from: $r8$lambda$xCw-6DPxXsN4OO1VxQOrZdL7340, reason: not valid java name */
    public static /* synthetic */ void m21940$r8$lambda$xCw6DPxXsN4OO1VxQOrZdL7340(AddressBarList r0, Runnable r1, View r2) {
        r0.lambda$setCurrent$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$yB7ThO3xkwInLJ72zQvDtgqG83s(AddressBarList r0) {
        r0.lambda$new$0();
    }

    /* renamed from: -$$Nest$fgetbookmarksList, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BookmarksList m21941$$Nest$fgetbookmarksList(AddressBarList r0) {
        return r0.bookmarksList;
    }

    /* renamed from: -$$Nest$fgetgrayBackgroundColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21942$$Nest$fgetgrayBackgroundColor(AddressBarList r0) {
        return r0.grayBackgroundColor;
    }

    /* renamed from: -$$Nest$fgetlistBackgroundColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21943$$Nest$fgetlistBackgroundColor(AddressBarList r0) {
        return r0.listBackgroundColor;
    }

    /* renamed from: -$$Nest$fgetrippleColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21944$$Nest$fgetrippleColor(AddressBarList r0) {
        return r0.rippleColor;
    }

    /* renamed from: -$$Nest$fgettextColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21945$$Nest$fgettextColor(AddressBarList r0) {
        return r0.textColor;
    }

    public AddressBarList(Context r21) {
        super(r21);
        int r9 = UserConfig.selectedAccount;
        this.currentAccount = r9;
        this.suggestions = new ArrayList();
        this.openProgress = 0.0f;
        this.hsv = new float[3];
        setWillNotDraw(false);
        int r3 = UserConfig.selectedAccount;
        AddressBarList$$ExternalSyntheticLambda1 r5 = new AddressBarList$$ExternalSyntheticLambda1(this);
        AddressBarList$$ExternalSyntheticLambda2 r6 = new AddressBarList$$ExternalSyntheticLambda2(this);
        WrappedResourceProvider r8 = new WrappedResourceProvider(null);
        this.resourceProvider = r8;
        AnonymousClass1 r0 = new AnonymousClass1(this, r21, r3, 0, r5, r6, null, r8);
        this.listView = r0;
        r0.adapter.setApplyBackground(false);
        this.listView.setOverScrollMode(2);
        this.listView.setPadding(0, 0, 0, 0);
        int r4 = -1;
        addView(this.listView, LayoutHelper.createFrame(-1, -1, 119));
        FrameLayout r02 = new FrameLayout(r21);
        this.currentContainer = r02;
        FrameLayout r32 = new FrameLayout(r21);
        this.currentView = r32;
        Drawable r52 = Theme.createRadSelectorDrawable(this.grayBackgroundColor, this.rippleColor, 15, 15);
        this.currentViewBackground = r52;
        r32.setBackground(r52);
        ScaleStateListAnimator.apply(r32, 0.04f, 1.25f);
        r02.addView(r32, LayoutHelper.createFrame(-1, -2.0f, 7, 12.0f, 0.0f, 12.0f, 15.0f));
        ImageView r03 = new ImageView(r21);
        this.currentIconView = r03;
        r32.addView(r03, LayoutHelper.createFrame(24, 24.0f, 19, 16.0f, 16.0f, 16.0f, 16.0f));
        ImageView r04 = new ImageView(r21);
        this.currentCopyView = r04;
        ScaleStateListAnimator.apply(r04);
        r04.setScaleType(ImageView.ScaleType.CENTER);
        r04.setImageResource(R.drawable.msg_copy);
        Drawable r53 = Theme.createRadSelectorDrawable(0, 0, 6, 6);
        this.currentCopyBackground = r53;
        r04.setBackground(r53);
        r32.addView(r04, LayoutHelper.createFrame(32, 32.0f, 53, 14.0f, 14.0f, 14.0f, 14.0f));
        LinearLayout r05 = new LinearLayout(r21);
        this.currentTextContainer = r05;
        r05.setOrientation(1);
        r32.addView(r05, LayoutHelper.createFrame(-1, -2.0f, 16, 54.0f, 9.0f, 54.0f, 9.0f));
        TextView r33 = new TextView(r21);
        this.currentTitleView = r33;
        r33.setTextSize(1, 16.0f);
        r33.setTypeface(AndroidUtilities.bold());
        r33.setMaxLines(4);
        r33.setEllipsize(TextUtils.TruncateAt.END);
        r05.addView(r33, LayoutHelper.createLinear(-1, -2, 55, 0, 0, 0, 2));
        TextView r34 = new TextView(r21);
        this.currentLinkView = r34;
        r34.setTextSize(1, 14.0f);
        r34.setMaxLines(3);
        r34.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        r05.addView(r34, LayoutHelper.createLinear(-1, -2, 55, 0, 0, 0, 0));
        this.bookmarksList = new BookmarksList(r9, new AddressBarList$$ExternalSyntheticLambda3(this));
        this.space = new AnonymousClass2(this, r21);
        int r06 = Theme.key_iv_background;
        int r2 = Theme.getColor(r06);
        if (AndroidUtilities.computePerceivedBrightness(Theme.getColor(r06)) < 0.721f) goto L5;
        r4 = -16777216;
    L5:
        setColors(r2, r4);
        setOpenProgress(0.0f);
        setImportantForAccessibility(4);
    }

    public static void clearRecentSearches(Context r2) {
        r2.getSharedPreferences("webhistory", 0).edit().remove("queries_json").apply();
    }

    private void clearRecentSearches(View r3) {
        new AlertDialog.Builder(getContext()).setTitle(LocaleController.getString(R.string.WebRecentClearTitle)).setMessage(LocaleController.getString(R.string.WebRecentClearText)).setPositiveButton(LocaleController.getString(R.string.OK), new AddressBarList$$ExternalSyntheticLambda11(this)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).show();
    }

    public static String getLink(MessageObject r3) {
        TLRPC.Message r0 = r3.messageOwner;
        if (r0 == null) goto L8;
        TLRPC.MessageMedia r02 = r0.media;
        if ((r02 instanceof TLRPC.TL_messageMediaWebPage) == false) goto L8;
        return r02.webpage.url;
    L8:
        CharSequence r03 = r3.messageText;
        if (r03 != null) goto L11;
        return null;
    L11:
        if (r03.length() <= 0) goto L33;
        SpannableStringBuilder r04 = new SpannableStringBuilder(r3.messageText);
        int r2 = 0;
        URLSpan[] r32 = (URLSpan[]) r04.getSpans(0, r04.length(), URLSpan.class);
    L14:
        if (r2 >= r32.length) goto L34;
        String r05 = r32[r2].getURL();
        if (r05 == null) goto L24;
        if (r05.startsWith("@") == true) goto L24;
        if (r05.startsWith("#") == true) goto L24;
        if (r05.startsWith("$") == true) goto L24;
        return r05;
    L24:
        r2 = r2 + 1;
        goto L14
    L34:
        return null;
    L33:
        return null;
    }

    public static ArrayList<String> getRecentSearches(Context r10) {
        int r1 = 0;
        SharedPreferences r102 = r10.getSharedPreferences("webhistory", 0);
        ArrayList<String> r0 = new ArrayList();
        String r103 = r102.getString("queries_json", null);
        if (r103 != null) goto L17;
    L15:
        return r0;
    L17:
        ArrayList r2 = new ArrayList();     // Catch: Exception -> L16
        JSONArray r3 = new JSONArray(r103);     // Catch: Exception -> L16
        int r104 = 0;
    L6:
        if (r104 >= r3.length()) goto L8;
        JSONObject r4 = r3.getJSONObject(r104);     // Catch: Exception -> L16
        QueryEntry r5 = new QueryEntry(r4.optString("name"), r4.optLong("usage", System.currentTimeMillis()));     // Catch: Exception -> L16
        r5.rank = r4.optDouble("rank", 0.0d);     // Catch: Exception -> L16
        r2.add(r5);     // Catch: Exception -> L16
        r104 = r104 + 1;     // Catch: Exception -> L16
        goto L6
    L8:
        Collections.sort(r2, new AddressBarList$$ExternalSyntheticLambda10());     // Catch: Exception -> L16
        int r105 = r2.size();     // Catch: Exception -> L16
    L9:
        if (r1 >= r105) goto L15;
        Object r32 = r2.get(r1);     // Catch: Exception -> L16
        r1 = r1 + 1;     // Catch: Exception -> L16
        QueryEntry r33 = (QueryEntry) r32;     // Catch: Exception -> L16
        if (r0.size() >= 20) goto L15;
        r0.add(r33.query);     // Catch: Exception -> L16
        goto L9
    }

    private /* synthetic */ void lambda$clearRecentSearches$1(AlertDialog r1, int r2) {
        clearRecentSearches(getContext());
        this.listView.adapter.update(true);
    }

    private /* synthetic */ void lambda$fillItems$2(String r1, View r2) {
        Utilities.Callback<String> r0 = this.onQueryInsertClick;
        if (r0 == null) goto L6;
        r0.run(r1);
        return;
    }

    private /* synthetic */ void lambda$fillItems$3(String r1, View r2) {
        Utilities.Callback<String> r0 = this.onQueryInsertClick;
        if (r0 == null) goto L6;
        r0.run(r1);
        return;
    }

    private /* synthetic */ void lambda$new$0() {
        this.listView.adapter.update(true);
    }

    private /* synthetic */ void lambda$setCurrent$4(Runnable r1, View r2) {
        this.hideCurrent = true;
        if (r1 == null) goto L5;
        r1.run();
    L5:
        this.listView.adapter.update(true);
    }

    private /* synthetic */ void lambda$setInput$5(String r3, boolean r4) {
        this.suggestions.clear();
        this.suggestions.addAll(SearchEngine.getCurrent().extractSuggestions(r3));
        this.listView.adapter.update(true);
        if (r4 == (!this.suggestions.isEmpty())) goto L6;
        this.listView.layoutManager.scrollToPositionWithOffset(0, 0);
        return;
    }

    private /* synthetic */ void lambda$setInput$6(boolean r2, String r3) {
        AndroidUtilities.runOnUIThread(new AddressBarList$$ExternalSyntheticLambda8(this, r3, r2));
    }

    public static void pushRecentSearch(Context r16, String r17) {
        int r2 = 0;
        SharedPreferences r3 = r16.getSharedPreferences("webhistory", 0);
        QueryEntry r5 = null;
        String r0 = r3.getString("queries_json", null);
        ArrayList r6 = new ArrayList();
        if (r0 != null) goto L33;
    L13:
        int r02 = 0;
    L35:
    L30:
        e = move-exception;
        FileLog.e(e);
        return;
    L15:
        if (r02 >= r6.size()) goto L20;
        QueryEntry r10 = (QueryEntry) r6.get(r02);     // Catch: Exception -> L30
        if (TextUtils.equals(r10.query, r17) == true) goto L18;
        r02 = r02 + 1;     // Catch: Exception -> L30
        goto L35
    L18:
        r5 = r10;
    L20:
        long r102 = System.currentTimeMillis();     // Catch: Exception -> L30
        if (r5 == null) goto L23;
        r5.rank += Math.exp((r102 - r5.lastUsage) / 2419200.0d);
    L24:
        r5.lastUsage = r102;     // Catch: Exception -> L30
        JSONArray r03 = new JSONArray();     // Catch: Exception -> L30
    L26:
        if (r2 >= Math.min(r6.size(), 20)) goto L28;
        QueryEntry r1 = (QueryEntry) r6.get(r2);     // Catch: Exception -> L30
        JSONObject r52 = new JSONObject();     // Catch: Exception -> L30
        r52.put("name", r1.query);     // Catch: Exception -> L30
        r52.put("rank", r1.rank);     // Catch: Exception -> L30
        r52.put("usage", r1.lastUsage);     // Catch: Exception -> L30
        r03.put(r52);     // Catch: Exception -> L30
        r2 = r2 + 1;     // Catch: Exception -> L30
        goto L26
    L28:
        r3.edit().putString("queries_json", r03.toString()).apply();     // Catch: Exception -> L30
        return;
    L23:
        r5 = new QueryEntry(r17, r102);     // Catch: Exception -> L30
        r6.add(r5);     // Catch: Exception -> L30
        goto L24
    L33:
        JSONArray r103 = new JSONArray(r0);     // Catch: Exception -> L8
        int r04 = 0;
    L6:
        if (r04 >= r103.length()) goto L10;
        JSONObject r11 = r103.getJSONObject(r04);     // Catch: Exception -> L8
        QueryEntry r12 = new QueryEntry(r11.optString("name"), r11.optLong("usage", System.currentTimeMillis()));     // Catch: Exception -> L8
        r12.rank = r11.optDouble("rank", 0.0d);     // Catch: Exception -> L8
        r6.add(r12);     // Catch: Exception -> L8
        r04 = r04 + 1;     // Catch: Exception -> L8
        goto L6
    L10:
        Collections.sort(r6, new AddressBarList$$ExternalSyntheticLambda4());     // Catch: Exception -> L8
    L8:
        e = move-exception;
        FileLog.e(e);
        goto L13
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r4) {
        r4.save();
        r4.clipRect(0.0f, 0.0f, getWidth(), getHeight() * this.openProgress);
        r4.drawColor(this.listBackgroundColor);
        super.dispatchDraw(r4);
        r4.restore();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r3) {
        if (this.openProgress >= 0.3f) goto L7;
        return false;
    L7:
        return super.dispatchTouchEvent(r3);
    }

    public void fillItems(ArrayList<UItem> r11, UniversalAdapter r12) {
        if (this.hideCurrent == false) goto L5;
    L7:
        ArrayList<String> r122 = getRecentSearches(getContext());
        this.suggestions.size();
        r122.size();
        if (this.suggestions.isEmpty() == true) goto L10;
        r11.add(UItem.asCustom(this.space));
    L10:
        int r0 = 0;
        int r1 = 0;
    L12:
        if (r1 >= this.suggestions.size()) goto L22;
        String r5 = this.suggestions.get(r1);
        if (r1 != 0) goto L16;
        boolean r7 = true;
    L18:
        if (r1 != (this.suggestions.size() - 1)) goto L20;
        boolean r8 = true;
    L21:
        AddressBarList r9 = this;
        r11.add(Address2View.Factory.as(1, r5, new AddressBarList$$ExternalSyntheticLambda5(this, r5), r7, r8, r9));
        r1 = r1 + 1;
        AddressBarList this = r9;
        goto L12
    L20:
        r8 = false;
        goto L21
    L16:
        r7 = false;
        goto L18
    L22:
        AddressBarList r92 = this;
        if (r122.isEmpty() == true) goto L36;
        r11.add(UItem.asGraySection(LocaleController.getString(R.string.WebSectionRecent), LocaleController.getString(R.string.WebRecentClear), new AddressBarList$$ExternalSyntheticLambda6(r92)));
        int r10 = 0;
    L26:
        if (r10 >= r122.size()) goto L36;
        String r52 = r122.get(r10);
        if (r10 != 0) goto L30;
        boolean r72 = true;
    L32:
        if (r10 != (r122.size() - 1)) goto L34;
        boolean r82 = true;
    L35:
        r11.add(Address2View.Factory.as(0, r52, new AddressBarList$$ExternalSyntheticLambda7(r92, r52), r72, r82, r92));
        r10 = r10 + 1;
        goto L26
    L34:
        r82 = false;
        goto L35
    L30:
        r72 = false;
    L36:
        BookmarksList r102 = r92.bookmarksList;
        if (r102 != null) goto L39;
        return;
    L39:
        if (r102.links.isEmpty() == true) goto L62;
        r11.add(UItem.asGraySection(LocaleController.getString(R.string.WebSectionBookmarks)));
    L41:
        int r103 = r92.bookmarksList.links.size();
        BookmarksList r123 = r92.bookmarksList;
        if (r0 >= r103) goto L49;
        MessageObject r104 = r123.links.get(r0);
        if (TextUtils.isEmpty(getLink(r104)) == true) goto L47;
        r11.add(BookmarkView.Factory.as(r104, true));
    L47:
        r0 = r0 + 1;
        goto L41
    L49:
        if (r123.endReached == true) goto L63;
        r11.add(UItem.asFlicker(r11.size(), 32));
        r11.add(UItem.asFlicker(r11.size(), 32));
        r11.add(UItem.asFlicker(r11.size(), 32));
        return;
    L63:
        return;
    L62:
        return;
    L5:
        if (this.suggestions.isEmpty() == false) goto L7;
        r11.add(UItem.asCustom(this.currentContainer));
        goto L7
    }

    public void itemClick(UItem r1, View r2, int r3, float r4, float r5) {
        if (r1.instanceOf(Address2View.Factory.class) == false) goto L9;
        String r12 = r1.text.toString();
        Utilities.Callback<String> r0 = this.onQueryClick;
        if (r0 == null) goto L19;
        r0.run(r12);
        return;
    L19:
        return;
    L9:
        if (r1.instanceOf(BookmarkView.Factory.class) == false) goto L20;
        Utilities.Callback<String> r02 = this.onURLClick;
        if (r02 == null) goto L21;
        r02.run(getLink((MessageObject) r1.object2));     // Catch: Exception -> L14
        return;
    L14:
        e = move-exception;
        FileLog.e(e);
        return;
    L21:
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        BookmarksList r0 = this.bookmarksList;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.opened == false) goto L9;
        r0.attach();
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        BookmarksList r0 = this.bookmarksList;
        if (r0 == null) goto L6;
        r0.detach();
        return;
    }

    public void setColors(int r8, int r9) {
        if (this.backgroundColor == r8) goto L5;
        this.backgroundColor = r8;
        invalidate();
    L5:
        this.textColor = r9;
        if (AndroidUtilities.computePerceivedBrightness(r8) < 0.721f) goto L8;
        float r0 = 0.0f;
    L9:
        this.grayBackgroundColor = ColorUtils.blendARGB(r8, r9, AndroidUtilities.lerp(0.05f, 0.12f, r0));
        this.listBackgroundColor = r8;
        this.rippleColor = ColorUtils.blendARGB(r8, r9, AndroidUtilities.lerp(0.12f, 0.22f, r0));
        Theme.setSelectorDrawableColor(this.currentViewBackground, this.grayBackgroundColor, false);
        Theme.setSelectorDrawableColor(this.currentViewBackground, this.rippleColor, true);
        this.currentView.invalidate();
        this.currentTitleView.setTextColor(r9);
        this.currentLinkView.setTextColor(Theme.multAlpha(r9, 0.6f));
        if (this.currentIconView.getColorFilter() == null) goto L12;
        this.currentIconView.setColorFilter(new PorterDuffColorFilter(r9, PorterDuff.Mode.SRC_IN));
    L12:
        this.currentCopyView.setColorFilter(new PorterDuffColorFilter(r9, PorterDuff.Mode.SRC_IN));
        Theme.setSelectorDrawableColor(this.currentCopyBackground, Theme.multAlpha(this.rippleColor, 1.5f), true);
        int r3 = Theme.blendOver(r8, Theme.multAlpha(r9, 0.05f));
        int r82 = Theme.blendOver(r8, Theme.multAlpha(r9, 0.55f));
        this.resourceProvider.sparseIntArray.put(Theme.key_windowBackgroundWhite, this.listBackgroundColor);
        this.resourceProvider.sparseIntArray.put(Theme.key_windowBackgroundWhiteBlackText, r9);
        this.resourceProvider.sparseIntArray.put(Theme.key_graySection, r3);
        this.resourceProvider.sparseIntArray.put(Theme.key_graySectionText, r82);
        this.resourceProvider.sparseIntArray.put(Theme.key_actionBarDefaultSubmenuBackground, Theme.multAlpha(r9, 0.2f));
        this.resourceProvider.sparseIntArray.put(Theme.key_listSelector, Theme.multAlpha(r9, AndroidUtilities.lerp(0.05f, 0.12f, r0)));
        this.listView.invalidateViews();
        return;
    L8:
        r0 = 1.0f;
        goto L9
    }

    public void setCurrent(Bitmap r5, String r6, String r7, Runnable r8, Utilities.Callback<String> r9, Utilities.Callback<String> r10, Utilities.Callback<String> r11, View.OnClickListener r12) {
        ImageView r0 = this.currentIconView;
        if (r5 != null) goto L5;
        r0.setImageResource(R.drawable.msg_language);
        this.currentIconView.setColorFilter(new PorterDuffColorFilter(this.textColor, PorterDuff.Mode.SRC_IN));
    L6:
        TextView r52 = this.currentTitleView;
        r52.setText(Emoji.replaceEmoji(r6, r52.getPaint().getFontMetricsInt(), false));
        Uri r53 = Uri.parse(r7);     // Catch: Exception -> L9
        r7 = Browser.replaceHostname(r53, Browser.IDN_toUnicode(r53.getHost()), null);     // Catch: Exception -> L9
    L11:
        String r54 = URLDecoder.decode(r7.replaceAll("\\+", "%2b"), "UTF-8");     // Catch: Exception -> L13
    L15:
        TextView r62 = this.currentLinkView;
        r62.setText(Emoji.replaceEmoji(r54, r62.getPaint().getFontMetricsInt(), false));
        this.onCurrentClick = r8;
        this.onQueryClick = r9;
        this.onQueryInsertClick = r10;
        this.onURLClick = r11;
        this.currentView.setOnClickListener(new AddressBarList$$ExternalSyntheticLambda9(this, r8));
        this.currentCopyView.setOnClickListener(r12);
        this.hideCurrent = false;
        setInput(null);
        this.listView.adapter.update(true);
        this.listView.scrollToPosition(0);
        return;
    L13:
        e = move-exception;
        FileLog.e(e);
        r54 = r7;
    L9:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L13
        goto L11
    L5:
        r0.setImageDrawable(new BitmapDrawable(getContext().getResources(), r5));
        this.currentIconView.setColorFilter(null);
        goto L6
    }

    public void setInput(String r4) {
        AsyncTask<String, Void, String> r0 = this.lastTask;
        if (r0 == null) goto L5;
        r0.cancel(true);
        this.lastTask = null;
    L5:
        boolean r02 = !this.suggestions.isEmpty();
        if (TextUtils.isEmpty(r4) == false) goto L11;
        this.suggestions.clear();
        this.listView.adapter.update(true);
        if (r02 == (!this.suggestions.isEmpty())) goto L13;
        this.listView.layoutManager.scrollToPositionWithOffset(0, 0);
        return;
    L13:
        return;
    L11:
        this.lastTask = new HttpGetTask(new AddressBarList$$ExternalSyntheticLambda0(this, r02)).execute(new String[]{SearchEngine.getCurrent().getAutocompleteURL(r4)});
    }

    public void setOpenProgress(float r3) {
        if (Math.abs(this.openProgress - r3) <= 1.0E-4f) goto L13;
        this.openProgress = r3;
        if (r3 > 1.0E-4f) goto L7;
        int r32 = 4;
    L9:
        if (getImportantForAccessibility() == r32) goto L11;
        setImportantForAccessibility(r32);
    L11:
        invalidate();
        return;
    L7:
        r32 = 0;
        goto L9
    }

    public void setOpened(boolean r1) {
        if (r1 == true) goto L4;
    L6:
        boolean r12 = false;
    L7:
        this.opened = r12;
        if (r12 == false) goto L11;
        this.bookmarksList.attach();
        return;
    L11:
        return;
    L4:
        if (this.bookmarksList == null) goto L6;
        r12 = true;
        goto L7
    }
}

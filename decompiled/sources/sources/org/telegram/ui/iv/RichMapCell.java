package org.telegram.ui.iv;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.WebFile;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextSelectionHelper;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.iv.RichCaptionController;

/* loaded from: classes7.dex */
public class RichMapCell extends RichBlockCell implements Theme.Colorable, TextSelectionHelper.ArticleSelectableView, RichCaptionHost {
    private final Paint backgroundPaint;
    private final RichCaptionController caption;
    private final View clickView;
    private final int currentAccount;
    private int currentMapProvider;
    private Delegate delegate;
    private final TextPaint hintPaint;
    private final ImageReceiver imageReceiver;
    private String loadedKey;
    private int mapImageH;
    private final Drawable placeholderIcon;
    private final Paint placeholderPaint;
    private Drawable redPinIcon;
    private final Theme.ResourcesProvider resourcesProvider;
    private final Paint selectionPaint;

    /* renamed from: org.telegram.ui.iv.RichMapCell$1, reason: invalid class name */
    public class AnonymousClass1 implements RichCaptionController.Host {
        final /* synthetic */ RichMapCell this$0;

        public AnonymousClass1(RichMapCell r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public TextSelectionHelper.ArticleSelectableView cell() {
            return this.this$0;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public BlockRow currentRow() {
            return this.this$0.currentRow;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public void onCaptionChanged() {
            if (RichMapCell.m21881$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichMapCell.m21881$$Nest$fgetdelegate(this.this$0).onCaptionChanged(this.this$0.currentRow);
            return;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public void onCaptionEnter() {
            if (RichMapCell.m21881$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichMapCell.m21881$$Nest$fgetdelegate(this.this$0).onCaptionEnter(this.this$0.currentRow);
            return;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public void onCaptionLockedInsert(CharSequence r2) {
            if (RichMapCell.m21881$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichMapCell.m21881$$Nest$fgetdelegate(this.this$0).onCaptionLockedInsert(r2);
            return;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public boolean onCaptionSelectAll() {
            if (RichMapCell.m21881$$Nest$fgetdelegate(this.this$0) != null) goto L5;
            return false;
        L5:
            if (RichMapCell.m21881$$Nest$fgetdelegate(this.this$0).onCaptionSelectAll(this.this$0.currentRow) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public void onCaptionSpansChanged() {
            if (RichMapCell.m21881$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichMapCell.m21881$$Nest$fgetdelegate(this.this$0).onCaptionSpansChanged(this.this$0.currentRow);
            return;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public void onCaptionWillChange(int r2, int r3) {
            if (RichMapCell.m21881$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichMapCell.m21881$$Nest$fgetdelegate(this.this$0).onCaptionWillChange(this.this$0.currentRow, r2, r3);
            return;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public void onRequestWindowFocusable(RichEditText r2, boolean r3) {
            if (RichMapCell.m21881$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichMapCell.m21881$$Nest$fgetdelegate(this.this$0).onRequestWindowFocusable(r2, r3);
            return;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public TextSelectionHelper.ArticleTextSelectionHelper selectionHelper() {
            if (RichMapCell.m21881$$Nest$fgetdelegate(this.this$0) != null) goto L5;
            return null;
        L5:
            return RichMapCell.m21881$$Nest$fgetdelegate(this.this$0).getSelectionHelper();
        }
    }

    public interface Delegate {
        TextSelectionHelper.ArticleTextSelectionHelper getSelectionHelper();

        void onCaptionChanged(BlockRow r1);

        void onCaptionEnter(BlockRow r1);

        void onCaptionLockedInsert(CharSequence r1);

        boolean onCaptionSelectAll(BlockRow r1);

        void onCaptionSpansChanged(BlockRow r1);

        void onCaptionWillChange(BlockRow r1, int r2, int r3);

        void onPickLocation(BlockRow r1);

        void onRequestWindowFocusable(RichEditText r1, boolean r2);
    }

    public static final class Factory extends UItem.UItemFactory<RichMapCell> {
        static {
            UItem.UItemFactory.setup(new Factory());
        }

        public Factory() {
        }

        public static UItem of(BlockRow r1, Delegate r2) {
            UItem r0 = UItem.ofFactory(Factory.class);
            r0.object = r1;
            r0.object2 = r2;
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
            ((RichMapCell) r1).bind((BlockRow) r2.object, (Delegate) r2.object2);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public RichMapCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return new RichMapCell(r1, r3, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean isClickable() {
            return false;
        }
    }

    public static /* synthetic */ void $r8$lambda$1EHGU3h4rFZ9o2eiqrI2Y0eLFFU(RichMapCell r0, View r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Delegate m21881$$Nest$fgetdelegate(RichMapCell r0) {
        return r0.delegate;
    }

    public RichMapCell(Context r4, int r5, Theme.ResourcesProvider r6) {
        super(r4);
        this.backgroundPaint = new Paint(1);
        this.placeholderPaint = new Paint(1);
        this.selectionPaint = new Paint(1);
        TextPaint r0 = new TextPaint(1);
        this.hintPaint = r0;
        this.currentAccount = r5;
        this.resourcesProvider = r6;
        setWillNotDraw(false);
        r0.setTextSize(AndroidUtilities.dp(15.0f));
        r0.setTextAlign(Paint.Align.CENTER);
        r0.setTypeface(AndroidUtilities.regular());
        this.imageReceiver = new ImageReceiver(this);
        this.placeholderIcon = getContext().getResources().getDrawable(R.drawable.msg_map).mutate();
        setBlockPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(4.0f));
        View r52 = new View(r4);
        this.clickView = r52;
        r52.setOnClickListener(new RichMapCell$$ExternalSyntheticLambda0(this));
        addView(r52, LayoutHelper.createFrame(-1, -2, 51));
        RichCaptionController r53 = new RichCaptionController(r4, r6, new AnonymousClass1(this));
        this.caption = r53;
        addView(r53.editText, LayoutHelper.createFrame(-2, -2, 51));
        updateColors();
    }

    private TL_iv.pageBlockMap getMap() {
        BlockRow r1 = this.currentRow;
        if (r1 == null) goto L8;
        TL_iv.PageBlock r12 = r1.block;
        if ((r12 instanceof TL_iv.pageBlockMap) == true) goto L7;
        return null;
    L7:
        return (TL_iv.pageBlockMap) r12;
    L8:
        return null;
    }

    public static boolean hasGeo(TL_iv.pageBlockMap r0) {
        if (r0 != null) goto L4;
        return false;
    L4:
        if ((r0.geo instanceof TLRPC.TL_geoPoint) == false) goto L9;
        return true;
    L9:
        return false;
    }

    private boolean hasLocation() {
        return hasGeo(getMap());
    }

    private boolean isCellSelected() {
        Delegate r0 = this.delegate;
        if (r0 != null) goto L5;
        return false;
    L5:
        TextSelectionHelper.ArticleTextSelectionHelper r02 = r0.getSelectionHelper();
        if (r02 != null) goto L8;
    L22:
        return false;
    L8:
        if (r02.isInSelectionMode() == false) goto L22;
        if ((getParent() instanceof RecyclerView) == true) goto L13;
        return false;
    L13:
        int r3 = ((RecyclerView) getParent()).getChildAdapterPosition(this);
        if (r3 >= 0) goto L17;
        return false;
    L17:
        if (r3 <= r02.getStartCell()) goto L22;
        if (r3 > r02.getEndCell()) goto L22;
        return true;
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        BlockRow r12 = this.currentRow;
        if (r12 == null) goto L8;
        Delegate r0 = this.delegate;
        if (r0 == null) goto L9;
        r0.onPickLocation(r12);
        return;
    L9:
        return;
    }

    private void loadMapImage() {
        TL_iv.pageBlockMap r1 = getMap();
        if (hasGeo(r1) == true) goto L6;
        this.imageReceiver.setImageBitmap(null);
        this.loadedKey = null;
        return;
    L6:
        int r2 = getMeasuredWidth();
        int r3 = this.mapImageH;
        if (r2 <= 0) goto L19;
        if (r3 <= 0) goto L20;
        float r4 = AndroidUtilities.density;
        int r10 = (int) (r2 / r4);
        int r11 = (int) (r3 / r4);
        String r22 = r1.geo.lat + "_" + r1.geo._long + "_" + r10 + "x" + r11;
        if (r22.equals(this.loadedKey) == true) goto L21;
        this.loadedKey = r22;
        int r23 = MessagesController.getInstance(this.currentAccount).mapProvider;
        this.currentMapProvider = r23;
        if (r23 != 2) goto L17;
        this.imageReceiver.setImage(ImageLocation.getForWebFile(WebFile.createWithGeoPoint(r1.geo, r10, r11, 15, Math.min(2, (int) Math.ceil(AndroidUtilities.density)))), null, null, null, null, 0);
        return;
    L17:
        int r5 = this.currentAccount;
        TLRPC.GeoPoint r12 = r1.geo;
        this.imageReceiver.setImage(AndroidUtilities.formapMapUrl(r5, r12.lat, r12._long, r10, r11, true, 15, -1), null, null, null, 0);
        return;
    L21:
        return;
    L20:
        return;
    }

    public void bind(BlockRow r1, Delegate r2) {
        this.currentRow = r1;
        this.delegate = r2;
        bindBlockInset(r1);
        this.loadedKey = null;
        this.caption.bind();
        loadMapImage();
        requestLayout();
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r1) {
        super.dispatchDraw(r1);
        this.caption.drawSelection(r1);
    }

    @Override // org.telegram.ui.Cells.TextSelectionHelper.ArticleSelectableView
    public void fillTextLayoutBlocks(ArrayList<TextSelectionHelper.TextLayoutBlock> r1) {
        this.caption.fillTextLayoutBlocks(r1);
    }

    @Override // org.telegram.ui.iv.RichCaptionHost
    public RichEditText getCaptionEditText() {
        return this.caption.editText;
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    @Override // org.telegram.ui.iv.RichCaptionHost
    public BlockRow getRow() {
        return this.currentRow;
    }

    @Override // org.telegram.ui.iv.RichCaptionHost
    public boolean isPressOnCaption(int r1, int r2) {
        return this.caption.isPressOnCaption(r1, r2);
    }

    @Override // org.telegram.ui.iv.RichBlockCell
    public int nestedContentMargin() {
        return 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.imageReceiver.onAttachedToWindow();
        this.loadedKey = null;
        loadMapImage();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.imageReceiver.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas r14) {
        if (getMap() == null) goto L17;
        Canvas r1 = r14;
        r1.drawRect(this.imageReceiver.getImageX(), this.imageReceiver.getImageY(), this.imageReceiver.getImageX2(), this.imageReceiver.getImageY2(), this.backgroundPaint);
        int r142 = (int) this.imageReceiver.getCenterX();
        int r0 = (int) this.imageReceiver.getCenterY();
        Drawable r2 = this.placeholderIcon;
        if (r2 == null) goto L8;
        int r143 = r142 - (r2.getIntrinsicWidth() / 2);
        int r02 = r0 - (this.placeholderIcon.getIntrinsicHeight() / 2);
        Drawable r22 = this.placeholderIcon;
        r22.setBounds(r143, r02, r22.getIntrinsicWidth() + r143, this.placeholderIcon.getIntrinsicHeight() + r02);
        this.placeholderIcon.draw(r1);
    L8:
        if (hasLocation() == false) goto L19;
        this.imageReceiver.draw(r1);
        if (this.currentMapProvider != 2) goto L19;
        if (this.imageReceiver.hasNotThumb() == false) goto L19;
        if (this.redPinIcon != null) goto L16;
        this.redPinIcon = getContext().getResources().getDrawable(R.drawable.map_pin).mutate();
    L16:
        int r144 = (int) (this.redPinIcon.getIntrinsicWidth() * 0.8f);
        int r03 = (int) (this.redPinIcon.getIntrinsicHeight() * 0.8f);
        int r23 = (int) (this.imageReceiver.getCenterX() - (r144 / 2.0f));
        int r3 = (int) (this.imageReceiver.getCenterY() - r03);
        this.redPinIcon.setAlpha((int) (this.imageReceiver.getCurrentAlpha() * 255.0f));
        this.redPinIcon.setBounds(r23, r3, r144 + r23, r03 + r3);
        this.redPinIcon.draw(r1);
    L19:
        if (isCellSelected() == false) goto L22;
        r1.drawRect(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getPaddingTop() + this.mapImageH, this.selectionPaint);
        return;
    L22:
        return;
    L17:
        r1 = r14;
        goto L19
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r5, int r6, int r7, int r8, int r9) {
        int r52 = getPaddingLeft();
        int r72 = getPaddingRight();
        int r82 = r8 - r6;
        int r62 = Math.max(0, (r82 - r52) - r72);
        this.imageReceiver.setImageCoords(r52, getPaddingTop(), r62, this.mapImageH);
        this.clickView.layout(r52, getPaddingTop(), r62 + r52, getPaddingTop() + this.mapImageH);
        this.caption.layout(r52, r72, r82, getPaddingTop() + this.mapImageH);
        loadMapImage();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r8, int r9) {
        int r82 = View.MeasureSpec.getSize(r8);
        int r92 = getPaddingLeft();
        int r0 = getPaddingRight();
        int r1 = Math.max(0, (r82 - r92) - r0);
        TL_iv.pageBlockMap r2 = getMap();
        if (r2 != null) goto L5;
    L9:
        this.mapImageH = (AndroidUtilities.dp(200.0f) - getPaddingTop()) - getPaddingBottom();
    L10:
        int r93 = this.caption.measure(r92, r0, r82);
        this.clickView.measure(View.MeasureSpec.makeMeasureSpec(r1, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(this.mapImageH, TLObject.FLAG_30));
        setMeasuredDimension(r82, ((getPaddingTop() + this.mapImageH) + r93) + getPaddingBottom());
        return;
    L5:
        if (r2.w <= 0) goto L9;
        if (r2.h <= 0) goto L9;
        this.mapImageH = Math.max(Math.min((int) (((r1 - AndroidUtilities.dp(32.0f)) * r2.h) / r2.w), AndroidUtilities.dp(420.0f)), AndroidUtilities.dp(120.0f));
        goto L10
    }

    @Override // org.telegram.ui.iv.RichCaptionHost
    public void persistCaption() {
        this.caption.persist();
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        this.backgroundPaint.setColor(Theme.getColor(Theme.key_chat_inLocationBackground, this.resourcesProvider));
        this.placeholderPaint.setColor(Theme.getColor(Theme.key_chat_inFileBackground, this.resourcesProvider));
        this.selectionPaint.setColor(Theme.getColor(Theme.key_chat_inTextSelectionHighlight, this.resourcesProvider));
        TextPaint r0 = this.hintPaint;
        int r1 = Theme.key_windowBackgroundWhiteBlackText;
        r0.setColor(Theme.multAlpha(Theme.getColor(r1, this.resourcesProvider), 0.5f));
        this.placeholderIcon.setColorFilter(new PorterDuffColorFilter(Theme.multAlpha(Theme.getColor(r1, this.resourcesProvider), 0.5f), PorterDuff.Mode.SRC_IN));
        RichCaptionController r5 = this.caption;
        if (r5 == null) goto L6;
        r5.applyColors();
        return;
    }
}

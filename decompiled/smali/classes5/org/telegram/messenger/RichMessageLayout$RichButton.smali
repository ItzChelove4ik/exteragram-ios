.class public Lorg/telegram/messenger/RichMessageLayout$RichButton;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/messenger/RichMessageLayout;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "RichButton"
.end annotation


# static fields
.field private static final ICON_OFFSET_X:F = 12.33f

.field private static final ICON_OFFSET_Y:F = 11.66f

.field private static final ICON_OFFSET_Y_INLINE:F = 9.33f

.field public static final INLINE_PADDING_HORIZONTAL:I = 0x7

.field private static final MIN_PADDING:I = 0x8

.field private static final PADDING:I = 0x14

.field private static final PADDING_WITH_ICON:I = 0x1a

.field private static final PRESS_SCALE:F = 0.04f

.field private static final PRESS_SCALE_INLINE:F = 0.09f

.field private static final SRC_OUT:Landroid/graphics/Xfermode;

.field private static final SRC_OUT_PAINT:Landroid/graphics/Paint;


# instance fields
.field public backgroundColor:I

.field public final backgroundPaint:Landroid/graphics/Paint;

.field public backgroundPressedColor:I

.field private final colorSpan:Lorg/telegram/ui/Components/ForegroundColorSpanThemable;

.field private final emojiFirst:Z

.field private final emojiLast:Z

.field private iconDrawable:Landroid/graphics/drawable/Drawable;

.field private final inline:Z

.field public final invalidateRunnable:Ljava/lang/Runnable;

.field public final isDisabled:Z

.field private lastLinkColorFilterColor:I

.field private final layout:Lorg/telegram/messenger/RichMessageLayout;

.field private final link:Z

.field private linkColorFilter:Landroid/graphics/ColorFilter;

.field private loading:Z

.field public loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

.field private final loadingRect:Landroid/graphics/RectF;

.field private final maxWidth:I

.field private needSaveLayer:Z

.field private final out:Z

.field public final pageButton:Lorg/telegram/tgnet/tl/TL_keyboard$PageButton;

.field public pressAnimator:Landroid/animation/ValueAnimator;

.field public pressT:F

.field public pressed:Z

.field public final style:Lorg/telegram/tgnet/tl/TL_keyboard$RichButtonStyle;

.field private final styleKeys:Lorg/telegram/ui/ActionBar/Theme$IvButtonColors;

.field public final text:Lorg/telegram/messenger/RichMessageLayout$Text;

.field public textColor:I

.field private textColorFilter:Landroid/graphics/ColorFilter;

.field public textColorKey:I

.field private final textFadeRect:Landroid/graphics/RectF;

.field public final type:Lorg/telegram/tgnet/tl/TL_keyboard$InlineButtonType;

.field public width:I

.field public x:I


# direct methods
.method public static synthetic $r8$lambda$ebSeZRyUTf31EtrjqvOj67Iq4YI(Lorg/telegram/messenger/RichMessageLayout$RichButton;Landroid/animation/ValueAnimator;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->lambda$setPressed$0(Landroid/animation/ValueAnimator;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$fgetlink(Lorg/telegram/messenger/RichMessageLayout$RichButton;)Z
    .locals 0

    .line 0
    iget-boolean p0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->link:Z

    return p0
.end method

.method static constructor <clinit>()V
    .locals 2

    .line 5167
    new-instance v0, Landroid/graphics/PorterDuffXfermode;

    sget-object v1, Landroid/graphics/PorterDuff$Mode;->SRC_OUT:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v0, v1}, Landroid/graphics/PorterDuffXfermode;-><init>(Landroid/graphics/PorterDuff$Mode;)V

    sput-object v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->SRC_OUT:Landroid/graphics/Xfermode;

    .line 5168
    new-instance v1, Landroid/graphics/Paint;

    invoke-direct {v1}, Landroid/graphics/Paint;-><init>()V

    sput-object v1, Lorg/telegram/messenger/RichMessageLayout$RichButton;->SRC_OUT_PAINT:Landroid/graphics/Paint;

    .line 5171
    invoke-virtual {v1, v0}, Landroid/graphics/Paint;->setXfermode(Landroid/graphics/Xfermode;)Landroid/graphics/Xfermode;

    return-void
.end method

.method public constructor <init>(Lorg/telegram/messenger/RichMessageLayout;ILjava/lang/CharSequence;Lorg/telegram/tgnet/tl/TL_keyboard$PageButton;Lorg/telegram/tgnet/tl/TL_keyboard$InlineButtonType;Lorg/telegram/tgnet/tl/TL_keyboard$RichButtonStyle;ZZZZZZLjava/lang/Boolean;Ljava/lang/Runnable;)V
    .locals 5

    move/from16 v1, p11

    move/from16 v2, p12

    .line 5247
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 5203
    new-instance v3, Landroid/graphics/Paint;

    const/4 v4, 0x1

    invoke-direct {v3, v4}, Landroid/graphics/Paint;-><init>(I)V

    iput-object v3, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->backgroundPaint:Landroid/graphics/Paint;

    .line 5227
    new-instance v3, Landroid/graphics/RectF;

    invoke-direct {v3}, Landroid/graphics/RectF;-><init>()V

    iput-object v3, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->loadingRect:Landroid/graphics/RectF;

    .line 5228
    new-instance v3, Landroid/graphics/RectF;

    invoke-direct {v3}, Landroid/graphics/RectF;-><init>()V

    iput-object v3, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->textFadeRect:Landroid/graphics/RectF;

    .line 5248
    iput-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->layout:Lorg/telegram/messenger/RichMessageLayout;

    .line 5249
    invoke-static {v4, p2}, Ljava/lang/Math;->max(II)I

    move-result p2

    iput p2, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->maxWidth:I

    move-object/from16 p2, p14

    .line 5250
    iput-object p2, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->invalidateRunnable:Ljava/lang/Runnable;

    .line 5251
    iput-object p4, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->pageButton:Lorg/telegram/tgnet/tl/TL_keyboard$PageButton;

    .line 5252
    iput-object p5, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->type:Lorg/telegram/tgnet/tl/TL_keyboard$InlineButtonType;

    .line 5253
    iput-object p6, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->style:Lorg/telegram/tgnet/tl/TL_keyboard$RichButtonStyle;

    .line 5254
    iput-boolean p7, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->isDisabled:Z

    .line 5255
    iput-boolean p9, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->emojiFirst:Z

    .line 5256
    iput-boolean p10, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->emojiLast:Z

    .line 5257
    iput-boolean v2, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->inline:Z

    .line 5258
    iput-boolean v1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->link:Z

    if-eqz p13, :cond_0

    .line 5259
    invoke-virtual/range {p13 .. p13}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p2

    goto :goto_0

    :cond_0
    invoke-virtual {p1}, Lorg/telegram/messenger/RichMessageLayout;->isOut()Z

    move-result p2

    :goto_0
    iput-boolean p2, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->out:Z

    .line 5261
    invoke-static {p6}, Lorg/telegram/ui/ActionBar/Theme$IvButtonColors;->of(Lorg/telegram/tgnet/tl/TL_keyboard$RichButtonStyle;)Lorg/telegram/ui/ActionBar/Theme$IvButtonColors;

    move-result-object p4

    .line 5262
    sget-object p6, Lorg/telegram/ui/ActionBar/Theme$IvButtonColors;->DEFAULT:Lorg/telegram/ui/ActionBar/Theme$IvButtonColors;

    if-ne p4, p6, :cond_1

    if-eqz v2, :cond_1

    .line 5263
    sget-object p4, Lorg/telegram/ui/ActionBar/Theme$IvButtonColors;->DEFAULT_IN_TEXT:Lorg/telegram/ui/ActionBar/Theme$IvButtonColors;

    .line 5265
    :cond_1
    iput-object p4, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->styleKeys:Lorg/telegram/ui/ActionBar/Theme$IvButtonColors;

    .line 5267
    new-instance p6, Lorg/telegram/ui/Components/ForegroundColorSpanThemable;

    invoke-virtual {p4, p2}, Lorg/telegram/ui/ActionBar/Theme$IvButtonColors;->getTextKey(Z)I

    move-result p2

    invoke-direct {p6, p2}, Lorg/telegram/ui/Components/ForegroundColorSpanThemable;-><init>(I)V

    iput-object p6, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->colorSpan:Lorg/telegram/ui/Components/ForegroundColorSpanThemable;

    if-eqz p7, :cond_2

    const/high16 p2, 0x3f000000    # 0.5f

    goto :goto_1

    :cond_2
    const/high16 p2, 0x3f800000    # 1.0f

    .line 5268
    :goto_1
    invoke-virtual {p6, p2}, Lorg/telegram/ui/Components/ForegroundColorSpanThemable;->setAlpha(F)V

    .line 5269
    new-instance p2, Landroid/text/SpannableStringBuilder;

    invoke-direct {p2, p3}, Landroid/text/SpannableStringBuilder;-><init>(Ljava/lang/CharSequence;)V

    const/16 p3, 0x21

    const/4 p4, 0x0

    if-eqz v1, :cond_3

    .line 5272
    new-instance p6, Lorg/telegram/ui/Components/URLSpanNoUnderline;

    const-string v0, ""

    invoke-direct {p6, v0}, Lorg/telegram/ui/Components/URLSpanNoUnderline;-><init>(Ljava/lang/String;)V

    invoke-virtual {p2}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v0

    invoke-virtual {p2, p6, p4, v0, p3}, Landroid/text/SpannableStringBuilder;->setSpan(Ljava/lang/Object;III)V

    goto :goto_2

    .line 5274
    :cond_3
    invoke-virtual {p2}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v0

    invoke-virtual {p2, p6, p4, v0, p3}, Landroid/text/SpannableStringBuilder;->setSpan(Ljava/lang/Object;III)V

    .line 5276
    :goto_2
    invoke-static {p2}, Lorg/telegram/messenger/AndroidUtilities;->replaceNewLines(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    .line 5279
    iget-object p3, p1, Lorg/telegram/messenger/RichMessageLayout;->textPaint:Landroid/text/TextPaint;

    .line 5280
    invoke-static {p2, p3}, Landroid/text/Layout;->getDesiredWidth(Ljava/lang/CharSequence;Landroid/text/TextPaint;)F

    move-result p3

    float-to-double v0, p3

    invoke-static {v0, v1}, Ljava/lang/Math;->ceil(D)D

    move-result-wide v0

    double-to-int p3, v0

    const/high16 p6, 0x40000000    # 2.0f

    invoke-static {p6}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p6

    add-int/2addr p3, p6

    .line 5279
    invoke-static {v4, p3}, Ljava/lang/Math;->max(II)I

    .line 5281
    new-instance p3, Lorg/telegram/messenger/RichMessageLayout$Text;

    const p6, 0x186a0

    sget-object v0, Landroid/text/Layout$Alignment;->ALIGN_CENTER:Landroid/text/Layout$Alignment;

    invoke-direct {p3, p1, p2, p6, v0}, Lorg/telegram/messenger/RichMessageLayout$Text;-><init>(Lorg/telegram/messenger/RichMessageLayout;Ljava/lang/CharSequence;ILandroid/text/Layout$Alignment;)V

    iput-object p3, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->text:Lorg/telegram/messenger/RichMessageLayout$Text;

    .line 5282
    invoke-virtual {p3, v4}, Lorg/telegram/messenger/RichMessageLayout$Text;->setDrawAtOrigin(Z)V

    .line 5283
    iput-boolean v4, p3, Lorg/telegram/messenger/RichMessageLayout$Text;->doNotInvalidateEmojiInParent:Z

    if-eqz p8, :cond_4

    .line 5285
    invoke-static {p5}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getButtonIcon(Lorg/telegram/tgnet/tl/TL_keyboard$InlineButtonType;)I

    move-result p4

    :cond_4
    if-eqz p4, :cond_5

    .line 5287
    sget-object p1, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    invoke-virtual {p1, p4}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object p1

    invoke-virtual {p1}, Landroid/graphics/drawable/Drawable;->mutate()Landroid/graphics/drawable/Drawable;

    move-result-object p1

    iput-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->iconDrawable:Landroid/graphics/drawable/Drawable;

    .line 5290
    :cond_5
    invoke-virtual {p0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getPreferredWidth()I

    move-result p1

    iput p1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->width:I

    .line 5291
    invoke-virtual {p0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->updateColors()V

    return-void
.end method

.method public constructor <init>(Lorg/telegram/messenger/RichMessageLayout;ILorg/telegram/tgnet/tl/TL_keyboard$PageButton;Ljava/lang/Runnable;)V
    .locals 15

    move-object/from16 v4, p3

    .line 5233
    iget-object v0, v4, Lorg/telegram/tgnet/tl/TL_keyboard$PageButton;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    const/16 v1, 0x10

    const/16 v2, 0xd

    invoke-static {v1, v2}, Lorg/telegram/messenger/RichMessageLayout;->setBlockFlags(II)I

    move-result v1

    move-object/from16 v2, p1

    invoke-virtual {v2, v0, v1}, Lorg/telegram/messenger/RichMessageLayout;->formatText(Lorg/telegram/tgnet/tl/TL_iv$RichText;I)Ljava/lang/CharSequence;

    move-result-object v3

    iget-object v5, v4, Lorg/telegram/tgnet/tl/TL_keyboard$PageButton;->type:Lorg/telegram/tgnet/tl/TL_keyboard$InlineButtonType;

    iget-object v6, v4, Lorg/telegram/tgnet/tl/TL_keyboard$PageButton;->style:Lorg/telegram/tgnet/tl/TL_keyboard$RichButtonStyle;

    const-class v0, Lorg/telegram/tgnet/tl/TL_keyboard$TL_inlineButtonTypeDisabled;

    invoke-static {v4, v0}, Lorg/telegram/messenger/utils/tlutils/TLKeyboardHelper;->isType(Lorg/telegram/tgnet/tl/TL_keyboard$KeyboardButtonProto;Ljava/lang/Class;)Z

    move-result v7

    const/4 v12, 0x0

    const/4 v13, 0x0

    const/4 v8, 0x1

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    move-object v0, p0

    move-object/from16 v14, p4

    move-object v1, v2

    move/from16 v2, p2

    invoke-direct/range {v0 .. v14}, Lorg/telegram/messenger/RichMessageLayout$RichButton;-><init>(Lorg/telegram/messenger/RichMessageLayout;ILjava/lang/CharSequence;Lorg/telegram/tgnet/tl/TL_keyboard$PageButton;Lorg/telegram/tgnet/tl/TL_keyboard$InlineButtonType;Lorg/telegram/tgnet/tl/TL_keyboard$RichButtonStyle;ZZZZZZLjava/lang/Boolean;Ljava/lang/Runnable;)V

    return-void
.end method

.method private drawLoading(Landroid/graphics/Canvas;)V
    .locals 6

    .line 5533
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->pageButton:Lorg/telegram/tgnet/tl/TL_keyboard$PageButton;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->layout:Lorg/telegram/messenger/RichMessageLayout;

    invoke-static {v0}, Lorg/telegram/messenger/RichMessageLayout;->-$$Nest$fgetcell(Lorg/telegram/messenger/RichMessageLayout;)Lorg/telegram/ui/Cells/ChatMessageCell;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->layout:Lorg/telegram/messenger/RichMessageLayout;

    invoke-static {v0}, Lorg/telegram/messenger/RichMessageLayout;->-$$Nest$fgetcell(Lorg/telegram/messenger/RichMessageLayout;)Lorg/telegram/ui/Cells/ChatMessageCell;

    move-result-object v0

    iget-object v1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->pageButton:Lorg/telegram/tgnet/tl/TL_keyboard$PageButton;

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Cells/ChatMessageCell;->drawButtonProgress(Lorg/telegram/tgnet/tl/TL_keyboard$KeyboardButtonProto;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    .line 5534
    :goto_0
    invoke-virtual {p0, v0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->setLoading(Z)V

    .line 5535
    iget-object v1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    if-eqz v1, :cond_2

    if-nez v0, :cond_1

    invoke-virtual {v1}, Lorg/telegram/ui/Components/LoadingDrawable;->isDisappearing()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 5537
    :cond_1
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    iget-object v0, v0, Lorg/telegram/ui/Components/LoadingDrawable;->strokePaint:Landroid/graphics/Paint;

    invoke-virtual {v0}, Landroid/graphics/Paint;->getStrokeWidth()F

    move-result v0

    .line 5538
    iget-object v1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->loadingRect:Landroid/graphics/RectF;

    iget v2, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->width:I

    int-to-float v2, v2

    invoke-virtual {p0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getHeight()I

    move-result v3

    int-to-float v3, v3

    const/4 v4, 0x0

    invoke-virtual {v1, v4, v4, v2, v3}, Landroid/graphics/RectF;->set(FFFF)V

    .line 5539
    iget-object v1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->loadingRect:Landroid/graphics/RectF;

    const/high16 v2, 0x40000000    # 2.0f

    div-float/2addr v0, v2

    invoke-virtual {v1, v0, v0}, Landroid/graphics/RectF;->inset(FF)V

    .line 5540
    iget-object v1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    invoke-virtual {p0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getHeight()I

    move-result v3

    int-to-float v3, v3

    div-float/2addr v3, v2

    sub-float/2addr v3, v0

    invoke-virtual {v1, v3}, Lorg/telegram/ui/Components/LoadingDrawable;->setRadii(F)V

    .line 5541
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    iget-object v1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->loadingRect:Landroid/graphics/RectF;

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/LoadingDrawable;->setBounds(Landroid/graphics/RectF;)V

    .line 5542
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    iget v1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->textColor:I

    const v2, 0x3d8f5c29    # 0.07f

    .line 5543
    invoke-static {v1, v2}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result v1

    iget v2, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->textColor:I

    const v3, 0x3e333333    # 0.175f

    .line 5544
    invoke-static {v2, v3}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result v2

    iget v4, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->textColor:I

    .line 5545
    invoke-static {v4, v3}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result v3

    iget v4, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->textColor:I

    const v5, 0x3ed70a3d    # 0.42f

    .line 5546
    invoke-static {v4, v5}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result v4

    .line 5542
    invoke-virtual {v0, v1, v2, v3, v4}, Lorg/telegram/ui/Components/LoadingDrawable;->setColors(IIII)V

    .line 5548
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    const/16 v1, 0xff

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/LoadingDrawable;->setAlpha(I)V

    .line 5549
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    invoke-virtual {v0, p1}, Lorg/telegram/ui/Components/LoadingDrawable;->draw(Landroid/graphics/Canvas;)V

    .line 5550
    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->invalidateRunnable:Ljava/lang/Runnable;

    invoke-interface {p0}, Ljava/lang/Runnable;->run()V

    :cond_2
    return-void
.end method

.method private static getButtonIcon(Lorg/telegram/tgnet/tl/TL_keyboard$InlineButtonType;)I
    .locals 1

    .line 5634
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_keyboard$TL_inlineButtonTypeCopy;

    if-eqz v0, :cond_0

    .line 5635
    sget p0, Lorg/telegram/messenger/R$drawable;->mini_inline_copy_16:I

    return p0

    .line 5636
    :cond_0
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_keyboard$TL_inlineButtonTypeUrlAuth;

    if-eqz v0, :cond_1

    .line 5637
    sget p0, Lorg/telegram/messenger/R$drawable;->mini_inline_arrow_16:I

    return p0

    .line 5638
    :cond_1
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_keyboard$TL_inlineButtonTypeUrl;

    if-eqz v0, :cond_3

    .line 5639
    check-cast p0, Lorg/telegram/tgnet/tl/TL_keyboard$TL_inlineButtonTypeUrl;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_keyboard$TL_inlineButtonTypeUrl;->url:Ljava/lang/String;

    .line 5640
    invoke-static {p0}, Lorg/telegram/ui/LinkManager;->isWebAppLink(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_2

    .line 5641
    sget p0, Lorg/telegram/messenger/R$drawable;->bot_webview:I

    return p0

    .line 5645
    :cond_2
    sget p0, Lorg/telegram/messenger/R$drawable;->mini_inline_arrow_16:I

    return p0

    .line 5647
    :cond_3
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_keyboard$TL_inlineButtonTypeWebView;

    if-eqz v0, :cond_4

    .line 5648
    sget p0, Lorg/telegram/messenger/R$drawable;->bot_webview:I

    return p0

    .line 5649
    :cond_4
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_keyboard$TL_inlineButtonTypeSwitchInline;

    if-eqz v0, :cond_5

    .line 5650
    sget p0, Lorg/telegram/messenger/R$drawable;->mini_inline_switch_16:I

    return p0

    .line 5651
    :cond_5
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_keyboard$TL_inlineButtonTypeBuy;

    if-eqz v0, :cond_6

    .line 5652
    sget p0, Lorg/telegram/messenger/R$drawable;->bot_card:I

    return p0

    .line 5653
    :cond_6
    instance-of p0, p0, Lorg/telegram/tgnet/tl/TL_keyboard$TL_inlineButtonTypeUserProfile;

    if-eqz p0, :cond_7

    .line 5654
    sget p0, Lorg/telegram/messenger/R$drawable;->mini_inline_profile_16:I

    return p0

    :cond_7
    const/4 p0, 0x0

    return p0
.end method

.method private getTextAvailableWidth()I
    .locals 1

    .line 5435
    invoke-direct {p0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getTextViewportRight()I

    move-result v0

    invoke-direct {p0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getTextViewportLeft()I

    move-result p0

    sub-int/2addr v0, p0

    const/4 p0, 0x1

    invoke-static {p0, v0}, Ljava/lang/Math;->max(II)I

    move-result p0

    return p0
.end method

.method private getTextViewportLeft()I
    .locals 1

    .line 5421
    iget-boolean v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->link:Z

    if-eqz v0, :cond_0

    const/4 p0, 0x0

    return p0

    :cond_0
    iget-boolean v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->inline:Z

    if-eqz v0, :cond_1

    invoke-virtual {p0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getPaddingLeft()I

    move-result p0

    return p0

    :cond_1
    const/high16 p0, 0x41000000    # 8.0f

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p0

    return p0
.end method

.method private getTextViewportRight()I
    .locals 2

    .line 5425
    iget-boolean v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->link:Z

    if-eqz v0, :cond_0

    .line 5426
    iget p0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->width:I

    return p0

    .line 5428
    :cond_0
    iget-boolean v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->inline:Z

    .line 5431
    iget v1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->width:I

    if-eqz v0, :cond_1

    .line 5429
    invoke-virtual {p0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getPaddingRight()I

    move-result p0

    :goto_0
    sub-int/2addr v1, p0

    return v1

    :cond_1
    const/high16 v0, 0x41000000    # 8.0f

    .line 5431
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    invoke-virtual {p0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getIconReserve()I

    move-result p0

    invoke-static {v0, p0}, Ljava/lang/Math;->max(II)I

    move-result p0

    goto :goto_0
.end method

.method private synthetic lambda$setPressed$0(Landroid/animation/ValueAnimator;)V
    .locals 0

    .line 5593
    invoke-virtual {p1}, Landroid/animation/ValueAnimator;->getAnimatedValue()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Float;

    invoke-virtual {p1}, Ljava/lang/Float;->floatValue()F

    move-result p1

    iput p1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->pressT:F

    .line 5594
    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->invalidateRunnable:Ljava/lang/Runnable;

    invoke-interface {p0}, Ljava/lang/Runnable;->run()V

    return-void
.end method


# virtual methods
.method public attach(Landroid/view/View;)V
    .locals 0

    .line 5625
    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->text:Lorg/telegram/messenger/RichMessageLayout$Text;

    invoke-virtual {p0, p1}, Lorg/telegram/messenger/RichMessageLayout$Text;->attach(Landroid/view/View;)V

    return-void
.end method

.method public contains(F)Z
    .locals 2

    .line 5621
    iget v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->x:I

    int-to-float v1, v0

    cmpl-float v1, p1, v1

    if-ltz v1, :cond_0

    iget p0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->width:I

    add-int/2addr v0, p0

    int-to-float p0, v0

    cmpg-float p0, p1, p0

    if-gtz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public detach(Landroid/view/View;)V
    .locals 0

    .line 5629
    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->text:Lorg/telegram/messenger/RichMessageLayout$Text;

    invoke-virtual {p0, p1}, Lorg/telegram/messenger/RichMessageLayout$Text;->detach(Landroid/view/View;)V

    return-void
.end method

.method public draw(Landroid/graphics/Canvas;)V
    .locals 18

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    .line 5449
    invoke-virtual {v0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getPressScale()F

    move-result v2

    .line 5450
    invoke-virtual {v1}, Landroid/graphics/Canvas;->save()I

    const/high16 v11, 0x3f800000    # 1.0f

    cmpl-float v3, v2, v11

    const/high16 v12, 0x40000000    # 2.0f

    if-eqz v3, :cond_0

    .line 5453
    iget v3, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->width:I

    int-to-float v3, v3

    div-float/2addr v3, v12

    invoke-virtual {v0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getHeight()I

    move-result v4

    int-to-float v4, v4

    div-float/2addr v4, v12

    invoke-virtual {v1, v2, v2, v3, v4}, Landroid/graphics/Canvas;->scale(FFFF)V

    .line 5455
    :cond_0
    iget-boolean v2, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->link:Z

    .line 5456
    iget-boolean v3, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->needSaveLayer:Z

    const/4 v9, 0x0

    const/4 v10, 0x1

    if-eqz v3, :cond_1

    if-nez v2, :cond_1

    move v13, v10

    goto :goto_0

    :cond_1
    move v13, v9

    :goto_0
    if-eqz v13, :cond_2

    .line 5458
    iget v2, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->width:I

    int-to-float v4, v2

    invoke-virtual {v0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getHeight()I

    move-result v2

    int-to-float v5, v2

    const/4 v6, 0x0

    const/4 v2, 0x0

    const/4 v3, 0x0

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->saveLayer(FFFFLandroid/graphics/Paint;)I

    goto :goto_2

    .line 5460
    :cond_2
    iget-object v1, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->backgroundPaint:Landroid/graphics/Paint;

    iget-boolean v3, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->pressed:Z

    if-eqz v3, :cond_3

    iget v3, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->backgroundPressedColor:I

    goto :goto_1

    :cond_3
    iget v3, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->backgroundColor:I

    :goto_1
    invoke-virtual {v1, v3}, Landroid/graphics/Paint;->setColor(I)V

    if-nez v2, :cond_4

    .line 5462
    iget v1, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->width:I

    int-to-float v4, v1

    invoke-virtual {v0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getHeight()I

    move-result v1

    int-to-float v5, v1

    invoke-virtual {v0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getHeight()I

    move-result v1

    int-to-float v1, v1

    div-float v6, v1, v12

    invoke-virtual {v0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getHeight()I

    move-result v1

    int-to-float v1, v1

    div-float v7, v1, v12

    iget-object v8, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->backgroundPaint:Landroid/graphics/Paint;

    const/4 v2, 0x0

    const/4 v3, 0x0

    move-object/from16 v1, p1

    invoke-virtual/range {v1 .. v8}, Landroid/graphics/Canvas;->drawRoundRect(FFFFFFLandroid/graphics/Paint;)V

    .line 5464
    :cond_4
    invoke-direct/range {p0 .. p1}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->drawLoading(Landroid/graphics/Canvas;)V

    .line 5467
    :goto_2
    invoke-virtual/range {p1 .. p1}, Landroid/graphics/Canvas;->save()I

    .line 5468
    invoke-direct {v0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getTextViewportLeft()I

    move-result v7

    .line 5469
    invoke-direct {v0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getTextViewportRight()I

    move-result v14

    .line 5470
    invoke-direct {v0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getTextAvailableWidth()I

    move-result v1

    .line 5471
    invoke-virtual {v0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getTextWidth()I

    move-result v2

    if-le v2, v1, :cond_5

    move v15, v10

    goto :goto_3

    :cond_5
    move v15, v9

    :goto_3
    const/high16 v8, 0x41200000    # 10.0f

    if-eqz v15, :cond_6

    int-to-float v2, v7

    .line 5473
    invoke-static {v8}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    neg-int v1, v1

    int-to-float v3, v1

    int-to-float v4, v14

    invoke-virtual {v0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getHeight()I

    move-result v1

    invoke-static {v8}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    add-int/2addr v1, v5

    int-to-float v5, v1

    const/4 v6, 0x0

    move-object/from16 v1, p1

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->saveLayer(FFFFLandroid/graphics/Paint;)I

    goto :goto_4

    :cond_6
    move-object/from16 v1, p1

    .line 5475
    :goto_4
    invoke-static {v8}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    neg-int v2, v2

    invoke-virtual {v0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getHeight()I

    move-result v3

    invoke-static {v8}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    add-int/2addr v3, v4

    invoke-virtual {v1, v7, v2, v14, v3}, Landroid/graphics/Canvas;->clipRect(IIII)Z

    .line 5476
    invoke-virtual {v1}, Landroid/graphics/Canvas;->save()I

    .line 5478
    iget-object v2, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->text:Lorg/telegram/messenger/RichMessageLayout$Text;

    invoke-virtual {v2}, Lorg/telegram/messenger/RichMessageLayout$Text;->getBaseline()I

    move-result v2

    .line 5479
    iget-object v3, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->text:Lorg/telegram/messenger/RichMessageLayout$Text;

    invoke-virtual {v3}, Lorg/telegram/messenger/RichMessageLayout$Text;->getEmojiOnlyCount()I

    move-result v3

    if-lez v3, :cond_7

    move v9, v10

    :cond_7
    if-eqz v9, :cond_8

    .line 5484
    invoke-virtual {v0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getTextX()F

    move-result v2

    iget-object v3, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->text:Lorg/telegram/messenger/RichMessageLayout$Text;

    iget v3, v3, Lorg/telegram/messenger/RichMessageLayout$Text;->left:I

    int-to-float v3, v3

    sub-float/2addr v2, v3

    invoke-virtual {v0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getHeight()I

    move-result v3

    iget-object v4, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->text:Lorg/telegram/messenger/RichMessageLayout$Text;

    invoke-virtual {v4}, Lorg/telegram/messenger/RichMessageLayout$Text;->getHeight()I

    move-result v4

    sub-int/2addr v3, v4

    int-to-float v3, v3

    div-float/2addr v3, v12

    invoke-virtual {v1, v2, v3}, Landroid/graphics/Canvas;->translate(FF)V

    goto :goto_5

    :cond_8
    if-lez v2, :cond_a

    .line 5486
    iget-boolean v3, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->inline:Z

    if-eqz v3, :cond_9

    .line 5487
    invoke-virtual {v0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getHeight()I

    move-result v3

    mul-int/lit8 v3, v3, 0x2b

    div-int/lit8 v3, v3, 0x38

    .line 5488
    invoke-virtual {v0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getTextX()F

    move-result v4

    iget-object v5, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->text:Lorg/telegram/messenger/RichMessageLayout$Text;

    iget v5, v5, Lorg/telegram/messenger/RichMessageLayout$Text;->left:I

    int-to-float v5, v5

    sub-float/2addr v4, v5

    neg-int v2, v2

    add-int/2addr v2, v3

    int-to-float v2, v2

    invoke-virtual {v1, v4, v2}, Landroid/graphics/Canvas;->translate(FF)V

    goto :goto_5

    .line 5490
    :cond_9
    invoke-virtual {v0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getHeight()I

    move-result v3

    mul-int/lit8 v3, v3, 0x41

    div-int/lit8 v3, v3, 0x66

    .line 5491
    invoke-virtual {v0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getTextX()F

    move-result v4

    iget-object v5, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->text:Lorg/telegram/messenger/RichMessageLayout$Text;

    iget v5, v5, Lorg/telegram/messenger/RichMessageLayout$Text;->left:I

    int-to-float v5, v5

    sub-float/2addr v4, v5

    neg-int v2, v2

    add-int/2addr v2, v3

    int-to-float v2, v2

    invoke-virtual {v1, v4, v2}, Landroid/graphics/Canvas;->translate(FF)V

    goto :goto_5

    .line 5494
    :cond_a
    invoke-virtual {v0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getTextX()F

    move-result v2

    iget-object v3, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->text:Lorg/telegram/messenger/RichMessageLayout$Text;

    iget v3, v3, Lorg/telegram/messenger/RichMessageLayout$Text;->left:I

    int-to-float v3, v3

    sub-float/2addr v2, v3

    invoke-virtual {v0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getHeight()I

    move-result v3

    iget-object v4, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->text:Lorg/telegram/messenger/RichMessageLayout$Text;

    invoke-virtual {v4}, Lorg/telegram/messenger/RichMessageLayout$Text;->getHeight()I

    move-result v4

    sub-int/2addr v3, v4

    int-to-float v3, v3

    div-float/2addr v3, v12

    invoke-static {v11}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    int-to-float v4, v4

    sub-float/2addr v3, v4

    invoke-virtual {v1, v2, v3}, Landroid/graphics/Canvas;->translate(FF)V

    .line 5496
    :goto_5
    iget-object v2, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->text:Lorg/telegram/messenger/RichMessageLayout$Text;

    invoke-virtual {v2, v1}, Lorg/telegram/messenger/RichMessageLayout$Text;->draw(Landroid/graphics/Canvas;)V

    .line 5497
    iget-object v2, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->layout:Lorg/telegram/messenger/RichMessageLayout;

    invoke-virtual {v2}, Lorg/telegram/messenger/RichMessageLayout;->isOverlayActive()Z

    move-result v2

    const/4 v3, 0x0

    if-eqz v2, :cond_10

    if-eqz v9, :cond_b

    move v2, v3

    goto :goto_6

    .line 5498
    :cond_b
    iget-boolean v2, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->link:Z

    if-eqz v2, :cond_c

    const v2, 0x3f28f5c3    # 0.66f

    goto :goto_6

    :cond_c
    move v2, v12

    :goto_6
    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    int-to-float v2, v2

    invoke-virtual {v1, v3, v2}, Landroid/graphics/Canvas;->translate(FF)V

    .line 5500
    iget-boolean v2, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->link:Z

    .line 5508
    iget-object v4, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->text:Lorg/telegram/messenger/RichMessageLayout$Text;

    if-eqz v2, :cond_f

    .line 5501
    iget-object v2, v4, Lorg/telegram/messenger/RichMessageLayout$Text;->layout:Landroid/text/StaticLayout;

    invoke-virtual {v2}, Landroid/text/Layout;->getPaint()Landroid/text/TextPaint;

    move-result-object v2

    iget v2, v2, Landroid/text/TextPaint;->linkColor:I

    .line 5502
    iget v4, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->lastLinkColorFilterColor:I

    if-ne v4, v2, :cond_d

    iget-object v4, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->linkColorFilter:Landroid/graphics/ColorFilter;

    if-nez v4, :cond_e

    .line 5503
    :cond_d
    iput v2, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->lastLinkColorFilterColor:I

    .line 5504
    new-instance v4, Landroid/graphics/PorterDuffColorFilter;

    sget-object v5, Landroid/graphics/PorterDuff$Mode;->SRC_IN:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v4, v2, v5}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    iput-object v4, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->linkColorFilter:Landroid/graphics/ColorFilter;

    .line 5506
    :cond_e
    iget-object v2, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->text:Lorg/telegram/messenger/RichMessageLayout$Text;

    iget-object v4, v2, Lorg/telegram/messenger/RichMessageLayout$Text;->layout:Landroid/text/StaticLayout;

    move v5, v3

    iget-object v3, v2, Lorg/telegram/messenger/RichMessageLayout$Text;->animatedEmojiStack:Lorg/telegram/ui/Components/AnimatedEmojiSpan$EmojiGroupedSpans;

    iget-object v2, v2, Lorg/telegram/messenger/RichMessageLayout$Text;->spoilers:Ljava/util/List;

    const/high16 v9, 0x3f800000    # 1.0f

    iget-object v10, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->linkColorFilter:Landroid/graphics/ColorFilter;

    move v6, v5

    move-object v5, v2

    move-object v2, v4

    const/4 v4, 0x0

    move v7, v6

    const/4 v6, 0x0

    move v8, v7

    const/4 v7, 0x0

    move/from16 v16, v8

    const/4 v8, 0x0

    move/from16 v17, v12

    move/from16 v12, v16

    invoke-static/range {v1 .. v10}, Lorg/telegram/ui/Components/AnimatedEmojiSpan;->drawAnimatedEmojis(Landroid/graphics/Canvas;Landroid/text/Layout;Lorg/telegram/ui/Components/AnimatedEmojiSpan$EmojiGroupedSpans;FLjava/util/List;FFFFLandroid/graphics/ColorFilter;)V

    move-object/from16 v1, p1

    goto :goto_7

    :cond_f
    move/from16 v17, v12

    move v12, v3

    .line 5508
    iget-object v2, v4, Lorg/telegram/messenger/RichMessageLayout$Text;->layout:Landroid/text/StaticLayout;

    iget-object v3, v4, Lorg/telegram/messenger/RichMessageLayout$Text;->animatedEmojiStack:Lorg/telegram/ui/Components/AnimatedEmojiSpan$EmojiGroupedSpans;

    iget-object v5, v4, Lorg/telegram/messenger/RichMessageLayout$Text;->spoilers:Ljava/util/List;

    const/high16 v9, 0x3f800000    # 1.0f

    iget-object v10, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->textColorFilter:Landroid/graphics/ColorFilter;

    const/4 v4, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v8, 0x0

    move-object/from16 v1, p1

    invoke-static/range {v1 .. v10}, Lorg/telegram/ui/Components/AnimatedEmojiSpan;->drawAnimatedEmojis(Landroid/graphics/Canvas;Landroid/text/Layout;Lorg/telegram/ui/Components/AnimatedEmojiSpan$EmojiGroupedSpans;FLjava/util/List;FFFFLandroid/graphics/ColorFilter;)V

    goto :goto_7

    :cond_10
    move/from16 v17, v12

    move v12, v3

    .line 5512
    :goto_7
    invoke-virtual {v1}, Landroid/graphics/Canvas;->restore()V

    if-eqz v15, :cond_11

    .line 5514
    iget-object v2, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->textFadeRect:Landroid/graphics/RectF;

    const/high16 v3, 0x41000000    # 8.0f

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    sub-int v3, v14, v3

    int-to-float v3, v3

    int-to-float v4, v14

    invoke-virtual {v0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getHeight()I

    move-result v5

    int-to-float v5, v5

    invoke-virtual {v2, v3, v12, v4, v5}, Landroid/graphics/RectF;->set(FFFF)V

    .line 5515
    iget-object v2, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->layout:Lorg/telegram/messenger/RichMessageLayout;

    iget-object v2, v2, Lorg/telegram/messenger/RichMessageLayout;->clip:Lorg/telegram/ui/GradientClip;

    iget-object v3, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->textFadeRect:Landroid/graphics/RectF;

    const/4 v4, 0x2

    invoke-virtual {v2, v1, v3, v4, v11}, Lorg/telegram/ui/GradientClip;->draw(Landroid/graphics/Canvas;Landroid/graphics/RectF;IF)V

    .line 5516
    invoke-virtual {v1}, Landroid/graphics/Canvas;->restore()V

    .line 5518
    :cond_11
    invoke-virtual {v1}, Landroid/graphics/Canvas;->restore()V

    .line 5519
    iget-object v2, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->iconDrawable:Landroid/graphics/drawable/Drawable;

    if-eqz v2, :cond_13

    .line 5520
    iget v3, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->width:I

    const v4, 0x414547ae    # 12.33f

    invoke-static {v4}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    sub-int/2addr v3, v4

    int-to-float v3, v3

    iget-boolean v4, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->inline:Z

    if-eqz v4, :cond_12

    const v4, 0x411547ae    # 9.33f

    goto :goto_8

    :cond_12
    const v4, 0x413a8f5c    # 11.66f

    :goto_8
    invoke-static {v4}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    int-to-float v4, v4

    const/16 v5, 0x11

    invoke-static {v2, v3, v4, v5}, Lorg/telegram/messenger/utils/DrawableUtils;->setBounds(Landroid/graphics/drawable/Drawable;FFI)V

    .line 5521
    iget-object v2, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->iconDrawable:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v2, v1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    :cond_13
    if-eqz v13, :cond_15

    .line 5524
    sget-object v8, Lorg/telegram/messenger/RichMessageLayout$RichButton;->SRC_OUT_PAINT:Landroid/graphics/Paint;

    iget-boolean v2, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->pressed:Z

    if-eqz v2, :cond_14

    iget v2, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->backgroundPressedColor:I

    goto :goto_9

    :cond_14
    iget v2, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->backgroundColor:I

    :goto_9
    invoke-virtual {v8, v2}, Landroid/graphics/Paint;->setColor(I)V

    .line 5525
    iget v2, v0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->width:I

    int-to-float v4, v2

    invoke-virtual {v0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getHeight()I

    move-result v2

    int-to-float v5, v2

    invoke-virtual {v0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getHeight()I

    move-result v2

    int-to-float v2, v2

    div-float v6, v2, v17

    invoke-virtual {v0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getHeight()I

    move-result v2

    int-to-float v2, v2

    div-float v7, v2, v17

    const/4 v2, 0x0

    const/4 v3, 0x0

    invoke-virtual/range {v1 .. v8}, Landroid/graphics/Canvas;->drawRoundRect(FFFFFFLandroid/graphics/Paint;)V

    .line 5526
    invoke-virtual/range {p1 .. p1}, Landroid/graphics/Canvas;->restore()V

    .line 5527
    invoke-direct/range {p0 .. p1}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->drawLoading(Landroid/graphics/Canvas;)V

    .line 5529
    :cond_15
    invoke-virtual/range {p1 .. p1}, Landroid/graphics/Canvas;->restore()V

    return-void
.end method

.method public getHeight()I
    .locals 1

    .line 5365
    iget-boolean v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->inline:Z

    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->layout:Lorg/telegram/messenger/RichMessageLayout;

    if-eqz v0, :cond_0

    invoke-static {p0}, Lorg/telegram/messenger/RichMessageLayout;->-$$Nest$fgetfontSize(Lorg/telegram/messenger/RichMessageLayout;)I

    move-result p0

    int-to-float p0, p0

    const v0, 0x3f955550

    mul-float/2addr p0, v0

    :goto_0
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p0

    return p0

    :cond_0
    invoke-static {p0}, Lorg/telegram/messenger/RichMessageLayout;->-$$Nest$fgetfontSize(Lorg/telegram/messenger/RichMessageLayout;)I

    move-result p0

    add-int/lit8 p0, p0, 0x12

    int-to-float p0, p0

    goto :goto_0
.end method

.method public getIconReserve()I
    .locals 1

    .line 5399
    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->iconDrawable:Landroid/graphics/drawable/Drawable;

    if-eqz p0, :cond_0

    const p0, 0x414547ae    # 12.33f

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p0

    const v0, 0x413a8f5c    # 11.66f

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    div-int/lit8 v0, v0, 0x2

    add-int/2addr p0, v0

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public getMinWidth()I
    .locals 2

    .line 5445
    invoke-virtual {p0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getPreferredWidth()I

    move-result v0

    invoke-virtual {p0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getHeight()I

    move-result v1

    invoke-virtual {p0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getIconReserve()I

    move-result p0

    add-int/2addr v1, p0

    invoke-static {v0, v1}, Ljava/lang/Math;->min(II)I

    move-result p0

    return p0
.end method

.method public getPaddingLeft()I
    .locals 1

    .line 5371
    iget-boolean v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->link:Z

    if-eqz v0, :cond_0

    const/4 p0, 0x0

    return p0

    .line 5374
    :cond_0
    iget-boolean v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->emojiFirst:Z

    if-eqz v0, :cond_1

    iget-boolean v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->inline:Z

    if-eqz v0, :cond_1

    const/high16 p0, 0x40800000    # 4.0f

    .line 5375
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p0

    return p0

    .line 5377
    :cond_1
    iget-boolean v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->inline:Z

    if-eqz v0, :cond_2

    const/high16 p0, 0x40e00000    # 7.0f

    .line 5378
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p0

    return p0

    .line 5380
    :cond_2
    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->iconDrawable:Landroid/graphics/drawable/Drawable;

    if-eqz p0, :cond_3

    const/high16 p0, 0x41d00000    # 26.0f

    goto :goto_0

    :cond_3
    const/high16 p0, 0x41a00000    # 20.0f

    :goto_0
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p0

    return p0
.end method

.method public getPaddingRight()I
    .locals 3

    .line 5384
    iget-boolean v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->link:Z

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    return v1

    .line 5387
    :cond_0
    iget-boolean v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->emojiLast:Z

    const/16 v2, 0xe

    if-eqz v0, :cond_2

    iget-boolean v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->inline:Z

    if-eqz v0, :cond_2

    .line 5388
    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->iconDrawable:Landroid/graphics/drawable/Drawable;

    if-eqz p0, :cond_1

    move v1, v2

    :cond_1
    add-int/lit8 v1, v1, 0x4

    int-to-float p0, v1

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p0

    return p0

    .line 5390
    :cond_2
    iget-boolean v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->inline:Z

    .line 5393
    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->iconDrawable:Landroid/graphics/drawable/Drawable;

    if-eqz v0, :cond_4

    if-eqz p0, :cond_3

    move v1, v2

    :cond_3
    add-int/lit8 v1, v1, 0x7

    int-to-float p0, v1

    .line 5391
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p0

    return p0

    :cond_4
    if-eqz p0, :cond_5

    const/high16 p0, 0x41d00000    # 26.0f

    goto :goto_0

    :cond_5
    const/high16 p0, 0x41a00000    # 20.0f

    .line 5393
    :goto_0
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p0

    return p0
.end method

.method public getPreferredWidth()I
    .locals 3

    .line 5440
    iget v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->maxWidth:I

    invoke-virtual {p0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getTextWidth()I

    move-result v1

    invoke-virtual {p0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getPaddingLeft()I

    move-result v2

    add-int/2addr v1, v2

    invoke-virtual {p0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getPaddingRight()I

    move-result p0

    add-int/2addr v1, p0

    invoke-static {v0, v1}, Ljava/lang/Math;->min(II)I

    move-result p0

    return p0
.end method

.method public getPressScale()F
    .locals 4

    .line 5611
    iget-boolean v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->pressed:Z

    const/high16 v1, 0x3f800000    # 1.0f

    if-eqz v0, :cond_0

    iget v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->pressT:F

    cmpl-float v2, v0, v1

    if-eqz v2, :cond_0

    const/high16 v2, 0x447a0000    # 1000.0f

    .line 5612
    sget v3, Lorg/telegram/messenger/AndroidUtilities;->screenRefreshRate:F

    div-float/2addr v2, v3

    const/high16 v3, 0x42200000    # 40.0f

    invoke-static {v3, v2}, Ljava/lang/Math;->min(FF)F

    move-result v2

    const/high16 v3, 0x42c80000    # 100.0f

    div-float/2addr v2, v3

    add-float/2addr v0, v2

    iput v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->pressT:F

    const/4 v2, 0x0

    .line 5613
    invoke-static {v0, v1, v2}, Lorg/telegram/messenger/Utilities;->clamp(FFF)F

    move-result v0

    iput v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->pressT:F

    .line 5614
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->invalidateRunnable:Ljava/lang/Runnable;

    invoke-interface {v0}, Ljava/lang/Runnable;->run()V

    .line 5616
    :cond_0
    iget-boolean v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->inline:Z

    if-eqz v0, :cond_1

    const v0, 0x3db851ec    # 0.09f

    goto :goto_0

    :cond_1
    const v0, 0x3d23d70a    # 0.04f

    :goto_0
    sub-float v2, v1, v0

    .line 5617
    iget p0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->pressT:F

    sub-float/2addr v1, p0

    mul-float/2addr v0, v1

    add-float/2addr v2, v0

    return v2
.end method

.method public getTextWidth()I
    .locals 1

    .line 5360
    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->text:Lorg/telegram/messenger/RichMessageLayout$Text;

    iget v0, p0, Lorg/telegram/messenger/RichMessageLayout$Text;->right:I

    iget p0, p0, Lorg/telegram/messenger/RichMessageLayout$Text;->left:I

    sub-int/2addr v0, p0

    return v0
.end method

.method public getTextX()F
    .locals 3

    .line 5404
    iget-boolean v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->inline:Z

    if-eqz v0, :cond_0

    .line 5405
    invoke-virtual {p0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getPaddingLeft()I

    move-result p0

    int-to-float p0, p0

    return p0

    .line 5408
    :cond_0
    invoke-virtual {p0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getTextWidth()I

    move-result v0

    invoke-direct {p0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getTextAvailableWidth()I

    move-result v1

    if-le v0, v1, :cond_1

    .line 5409
    invoke-direct {p0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getTextViewportLeft()I

    move-result p0

    int-to-float p0, p0

    return p0

    .line 5412
    :cond_1
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->iconDrawable:Landroid/graphics/drawable/Drawable;

    .line 5415
    iget v1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->width:I

    const/high16 v2, 0x40000000    # 2.0f

    if-nez v0, :cond_2

    .line 5413
    invoke-virtual {p0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getTextWidth()I

    move-result p0

    sub-int/2addr v1, p0

    int-to-float p0, v1

    div-float/2addr p0, v2

    return p0

    .line 5415
    :cond_2
    invoke-virtual {p0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getTextWidth()I

    move-result v0

    sub-int/2addr v1, v0

    int-to-float v0, v1

    div-float/2addr v0, v2

    .line 5416
    iget v1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->width:I

    invoke-virtual {p0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getIconReserve()I

    move-result v2

    sub-int/2addr v1, v2

    invoke-virtual {p0}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->getTextWidth()I

    move-result p0

    sub-int/2addr v1, p0

    int-to-float p0, v1

    invoke-static {v0, p0}, Ljava/lang/Math;->min(FF)F

    move-result p0

    const/high16 v0, 0x41000000    # 8.0f

    .line 5417
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    int-to-float v0, v0

    invoke-static {p0, v0}, Ljava/lang/Math;->max(FF)F

    move-result p0

    return p0
.end method

.method public isLoading()Z
    .locals 0

    .line 5575
    iget-boolean p0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->loading:Z

    return p0
.end method

.method public setLoading(Z)V
    .locals 1

    .line 5555
    iget-boolean v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->loading:Z

    if-ne v0, p1, :cond_0

    return-void

    .line 5558
    :cond_0
    iput-boolean p1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->loading:Z

    .line 5568
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    if-eqz p1, :cond_2

    if-nez v0, :cond_1

    .line 5561
    new-instance p1, Lorg/telegram/ui/Components/LoadingDrawable;

    invoke-direct {p1}, Lorg/telegram/ui/Components/LoadingDrawable;-><init>()V

    iput-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    const/4 v0, 0x1

    .line 5562
    invoke-virtual {p1, v0}, Lorg/telegram/ui/Components/LoadingDrawable;->setAppearByGradient(Z)V

    .line 5563
    iget-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    iget-object p1, p1, Lorg/telegram/ui/Components/LoadingDrawable;->strokePaint:Landroid/graphics/Paint;

    const/high16 v0, 0x3fa00000    # 1.25f

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v0

    invoke-virtual {p1, v0}, Landroid/graphics/Paint;->setStrokeWidth(F)V

    goto :goto_0

    .line 5565
    :cond_1
    invoke-virtual {v0}, Lorg/telegram/ui/Components/LoadingDrawable;->reset()V

    .line 5566
    iget-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    invoke-virtual {p1}, Lorg/telegram/ui/Components/LoadingDrawable;->resetDisappear()V

    goto :goto_0

    :cond_2
    if-eqz v0, :cond_3

    .line 5569
    invoke-virtual {v0}, Lorg/telegram/ui/Components/LoadingDrawable;->disappear()V

    .line 5571
    :cond_3
    :goto_0
    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->invalidateRunnable:Ljava/lang/Runnable;

    invoke-interface {p0}, Ljava/lang/Runnable;->run()V

    return-void
.end method

.method public setPressed(Z)V
    .locals 3

    .line 5579
    iget-boolean v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->pressed:Z

    if-ne v0, p1, :cond_0

    goto :goto_2

    .line 5582
    :cond_0
    iput-boolean p1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->pressed:Z

    .line 5583
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->invalidateRunnable:Ljava/lang/Runnable;

    invoke-interface {v0}, Ljava/lang/Runnable;->run()V

    if-eqz p1, :cond_1

    .line 5585
    iget-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->pressAnimator:Landroid/animation/ValueAnimator;

    if-eqz p1, :cond_4

    .line 5586
    invoke-virtual {p1}, Landroid/animation/Animator;->removeAllListeners()V

    .line 5587
    iget-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->pressAnimator:Landroid/animation/ValueAnimator;

    invoke-virtual {p1}, Landroid/animation/ValueAnimator;->cancel()V

    const/4 p1, 0x0

    .line 5588
    iput-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->pressAnimator:Landroid/animation/ValueAnimator;

    return-void

    .line 5590
    :cond_1
    iget p1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->pressT:F

    const/4 v0, 0x0

    cmpl-float v1, p1, v0

    if-eqz v1, :cond_4

    const/4 v1, 0x2

    .line 5591
    new-array v1, v1, [F

    const/4 v2, 0x0

    aput p1, v1, v2

    const/4 p1, 0x1

    aput v0, v1, p1

    invoke-static {v1}, Landroid/animation/ValueAnimator;->ofFloat([F)Landroid/animation/ValueAnimator;

    move-result-object p1

    iput-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->pressAnimator:Landroid/animation/ValueAnimator;

    .line 5592
    new-instance v0, Lorg/telegram/messenger/RichMessageLayout$RichButton$$ExternalSyntheticLambda0;

    invoke-direct {v0, p0}, Lorg/telegram/messenger/RichMessageLayout$RichButton$$ExternalSyntheticLambda0;-><init>(Lorg/telegram/messenger/RichMessageLayout$RichButton;)V

    invoke-virtual {p1, v0}, Landroid/animation/ValueAnimator;->addUpdateListener(Landroid/animation/ValueAnimator$AnimatorUpdateListener;)V

    .line 5596
    iget-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->pressAnimator:Landroid/animation/ValueAnimator;

    new-instance v0, Lorg/telegram/messenger/RichMessageLayout$RichButton$1;

    invoke-direct {v0, p0}, Lorg/telegram/messenger/RichMessageLayout$RichButton$1;-><init>(Lorg/telegram/messenger/RichMessageLayout$RichButton;)V

    invoke-virtual {p1, v0}, Landroid/animation/Animator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 5603
    iget-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->pressAnimator:Landroid/animation/ValueAnimator;

    new-instance v0, Landroid/view/animation/OvershootInterpolator;

    iget-boolean v1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->inline:Z

    if-eqz v1, :cond_2

    const/high16 v1, 0x40600000    # 3.5f

    goto :goto_0

    :cond_2
    const/high16 v1, 0x40000000    # 2.0f

    :goto_0
    invoke-direct {v0, v1}, Landroid/view/animation/OvershootInterpolator;-><init>(F)V

    invoke-virtual {p1, v0}, Landroid/animation/ValueAnimator;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    .line 5604
    iget-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->pressAnimator:Landroid/animation/ValueAnimator;

    iget-boolean v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->inline:Z

    if-eqz v0, :cond_3

    const-wide/16 v0, 0x1a4

    goto :goto_1

    :cond_3
    const-wide/16 v0, 0x15e

    :goto_1
    invoke-virtual {p1, v0, v1}, Landroid/animation/ValueAnimator;->setDuration(J)Landroid/animation/ValueAnimator;

    .line 5605
    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->pressAnimator:Landroid/animation/ValueAnimator;

    invoke-virtual {p0}, Landroid/animation/ValueAnimator;->start()V

    :cond_4
    :goto_2
    return-void
.end method

.method public setTextColorKey(I)V
    .locals 3

    .line 5343
    iput p1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->textColorKey:I

    .line 5344
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->layout:Lorg/telegram/messenger/RichMessageLayout;

    invoke-static {v0, p1}, Lorg/telegram/messenger/RichMessageLayout;->-$$Nest$mgetThemedColor(Lorg/telegram/messenger/RichMessageLayout;I)I

    move-result v0

    .line 5345
    iget-boolean v1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->isDisabled:Z

    if-eqz v1, :cond_0

    const/high16 v1, 0x3f000000    # 0.5f

    .line 5346
    invoke-static {v0, v1}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result v0

    .line 5348
    :cond_0
    iget v1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->textColor:I

    if-ne v0, v1, :cond_1

    iget-object v1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->textColorFilter:Landroid/graphics/ColorFilter;

    if-nez v1, :cond_2

    .line 5349
    :cond_1
    iput v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->textColor:I

    .line 5350
    new-instance v0, Landroid/graphics/PorterDuffColorFilter;

    iget v1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->textColor:I

    sget-object v2, Landroid/graphics/PorterDuff$Mode;->SRC_IN:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v0, v1, v2}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    iput-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->textColorFilter:Landroid/graphics/ColorFilter;

    .line 5352
    :cond_2
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->colorSpan:Lorg/telegram/ui/Components/ForegroundColorSpanThemable;

    invoke-virtual {v0, p1}, Lorg/telegram/ui/Components/ForegroundColorSpanThemable;->setColorKey(I)V

    .line 5353
    iget-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->invalidateRunnable:Ljava/lang/Runnable;

    invoke-interface {p1}, Ljava/lang/Runnable;->run()V

    .line 5354
    iget-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->iconDrawable:Landroid/graphics/drawable/Drawable;

    if-eqz p1, :cond_3

    .line 5355
    new-instance v0, Landroid/graphics/PorterDuffColorFilter;

    iget p0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->textColor:I

    sget-object v1, Landroid/graphics/PorterDuff$Mode;->MULTIPLY:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v0, p0, v1}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {p1, v0}, Landroid/graphics/drawable/Drawable;->setColorFilter(Landroid/graphics/ColorFilter;)V

    :cond_3
    return-void
.end method

.method public updateColors()V
    .locals 8

    .line 5295
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->layout:Lorg/telegram/messenger/RichMessageLayout;

    iget-object v0, v0, Lorg/telegram/messenger/RichMessageLayout;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    if-eqz v0, :cond_0

    .line 5296
    invoke-interface {v0}, Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;->isDark()Z

    move-result v0

    goto :goto_0

    :cond_0
    invoke-static {}, Lorg/telegram/ui/ActionBar/Theme;->isCurrentThemeDark()Z

    move-result v0

    .line 5299
    :goto_0
    iget-boolean v1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->out:Z

    const/4 v2, 0x1

    const/4 v3, 0x0

    if-eqz v1, :cond_2

    if-eqz v0, :cond_2

    .line 5300
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->layout:Lorg/telegram/messenger/RichMessageLayout;

    sget v1, Lorg/telegram/ui/ActionBar/Theme;->key_chat_outBubbleGradient1:I

    invoke-static {v0, v1}, Lorg/telegram/messenger/RichMessageLayout;->-$$Nest$mgetThemedColor(Lorg/telegram/messenger/RichMessageLayout;I)I

    move-result v0

    .line 5301
    iget-object v1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->layout:Lorg/telegram/messenger/RichMessageLayout;

    sget v4, Lorg/telegram/ui/ActionBar/Theme;->key_chat_outBubbleGradient2:I

    invoke-static {v1, v4}, Lorg/telegram/messenger/RichMessageLayout;->-$$Nest$mgetThemedColor(Lorg/telegram/messenger/RichMessageLayout;I)I

    move-result v1

    .line 5302
    iget-object v4, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->layout:Lorg/telegram/messenger/RichMessageLayout;

    sget v5, Lorg/telegram/ui/ActionBar/Theme;->key_chat_outBubbleGradient3:I

    invoke-static {v4, v5}, Lorg/telegram/messenger/RichMessageLayout;->-$$Nest$mgetThemedColor(Lorg/telegram/messenger/RichMessageLayout;I)I

    move-result v4

    if-nez v0, :cond_1

    if-nez v1, :cond_1

    if-eqz v4, :cond_2

    :cond_1
    move v0, v2

    goto :goto_1

    :cond_2
    move v0, v3

    .line 5312
    :goto_1
    iget-object v1, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->styleKeys:Lorg/telegram/ui/ActionBar/Theme$IvButtonColors;

    if-eqz v0, :cond_3

    .line 5313
    sget-object v4, Lorg/telegram/ui/ActionBar/Theme$IvButtonColors;->PRIMARY:Lorg/telegram/ui/ActionBar/Theme$IvButtonColors;

    if-eq v1, v4, :cond_3

    .line 5314
    sget-object v1, Lorg/telegram/ui/ActionBar/Theme$IvButtonColors;->DEFAULT:Lorg/telegram/ui/ActionBar/Theme$IvButtonColors;

    .line 5317
    :cond_3
    iget-boolean v4, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->out:Z

    invoke-virtual {v1, v4}, Lorg/telegram/ui/ActionBar/Theme$IvButtonColors;->getBackgroundKey(Z)I

    move-result v4

    .line 5318
    iget-boolean v5, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->out:Z

    invoke-virtual {v1, v5}, Lorg/telegram/ui/ActionBar/Theme$IvButtonColors;->getBackgroundPressedKey(Z)I

    move-result v5

    .line 5319
    iget-boolean v6, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->out:Z

    invoke-virtual {v1, v6}, Lorg/telegram/ui/ActionBar/Theme$IvButtonColors;->getTextKey(Z)I

    move-result v6

    .line 5321
    iget-object v7, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->layout:Lorg/telegram/messenger/RichMessageLayout;

    invoke-static {v7, v6}, Lorg/telegram/messenger/RichMessageLayout;->-$$Nest$mgetThemedColor(Lorg/telegram/messenger/RichMessageLayout;I)I

    move-result v7

    .line 5323
    iput-boolean v3, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->needSaveLayer:Z

    if-eqz v0, :cond_4

    .line 5324
    sget-object v0, Lorg/telegram/ui/ActionBar/Theme$IvButtonColors;->PRIMARY:Lorg/telegram/ui/ActionBar/Theme$IvButtonColors;

    if-ne v1, v0, :cond_4

    .line 5325
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->layout:Lorg/telegram/messenger/RichMessageLayout;

    invoke-static {v0, v6}, Lorg/telegram/messenger/RichMessageLayout;->-$$Nest$mgetThemedColor(Lorg/telegram/messenger/RichMessageLayout;I)I

    move-result v0

    iput v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->backgroundPressedColor:I

    iput v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->backgroundColor:I

    .line 5326
    iput-boolean v2, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->needSaveLayer:Z

    goto :goto_3

    .line 5327
    :cond_4
    invoke-static {v4}, Lorg/telegram/ui/ActionBar/Theme;->hasThemeKey(I)Z

    move-result v0

    const v2, 0x3dcccccd    # 0.1f

    if-nez v0, :cond_7

    sget-object v0, Lorg/telegram/ui/ActionBar/Theme$IvButtonColors;->PRIMARY:Lorg/telegram/ui/ActionBar/Theme$IvButtonColors;

    if-ne v1, v0, :cond_5

    goto :goto_2

    .line 5335
    :cond_5
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->layout:Lorg/telegram/messenger/RichMessageLayout;

    invoke-static {v0, v6}, Lorg/telegram/messenger/RichMessageLayout;->-$$Nest$mgetThemedColor(Lorg/telegram/messenger/RichMessageLayout;I)I

    move-result v0

    sget-object v3, Lorg/telegram/ui/ActionBar/Theme$IvButtonColors;->DEFAULT:Lorg/telegram/ui/ActionBar/Theme$IvButtonColors;

    if-ne v1, v3, :cond_6

    const v2, 0x3da3d70a    # 0.08f

    :cond_6
    invoke-static {v0, v2}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result v0

    iput v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->backgroundColor:I

    .line 5336
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->layout:Lorg/telegram/messenger/RichMessageLayout;

    invoke-static {v0, v6}, Lorg/telegram/messenger/RichMessageLayout;->-$$Nest$mgetThemedColor(Lorg/telegram/messenger/RichMessageLayout;I)I

    move-result v0

    const v1, 0x3e23d70a    # 0.16f

    invoke-static {v0, v1}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result v0

    iput v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->backgroundPressedColor:I

    goto :goto_3

    .line 5328
    :cond_7
    :goto_2
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->layout:Lorg/telegram/messenger/RichMessageLayout;

    invoke-static {v0, v4}, Lorg/telegram/messenger/RichMessageLayout;->-$$Nest$mgetThemedColor(Lorg/telegram/messenger/RichMessageLayout;I)I

    move-result v0

    iput v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->backgroundColor:I

    .line 5329
    invoke-static {v5}, Lorg/telegram/ui/ActionBar/Theme;->hasThemeKey(I)Z

    move-result v0

    if-eqz v0, :cond_8

    .line 5330
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->layout:Lorg/telegram/messenger/RichMessageLayout;

    invoke-static {v0, v5}, Lorg/telegram/messenger/RichMessageLayout;->-$$Nest$mgetThemedColor(Lorg/telegram/messenger/RichMessageLayout;I)I

    move-result v0

    iput v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->backgroundPressedColor:I

    goto :goto_3

    .line 5332
    :cond_8
    iget v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->backgroundColor:I

    invoke-static {v0, v7, v2}, Landroidx/core/graphics/ColorUtils;->blendARGB(IIF)I

    move-result v0

    iput v0, p0, Lorg/telegram/messenger/RichMessageLayout$RichButton;->backgroundPressedColor:I

    .line 5338
    :goto_3
    invoke-virtual {p0, v6}, Lorg/telegram/messenger/RichMessageLayout$RichButton;->setTextColorKey(I)V

    return-void
.end method

.class public Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;
.super Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;
.source "SourceFile"


# annotations
.annotation build Landroid/annotation/SuppressLint;
    value = {
        "ViewConstructor"
    }
.end annotation


# static fields
.field private static final BLOCKED_COUNT_SAMPLES:[I


# instance fields
.field private final adBlocker:Lcom/exteragram/messenger/adblock/WebAdBlocker;

.field private final options:Lorg/telegram/ui/Components/ItemOptions;

.field private subtext:Ljava/lang/CharSequence;

.field private final switchView:Lorg/telegram/ui/Components/Switch;

.field private toggled:Z

.field private final updateRunnable:Ljava/lang/Runnable;


# direct methods
.method public static synthetic $r8$lambda$Ad8TY09JwGm62uEFw2JZaqQzXJA(Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;Landroid/view/View;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;->lambda$new$0(Landroid/view/View;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$fgettoggled(Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;)Z
    .locals 0

    .line 0
    iget-boolean p0, p0, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;->toggled:Z

    return p0
.end method

.method public static bridge synthetic -$$Nest$mupdate(Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;->update()V

    return-void
.end method

.method static constructor <clinit>()V
    .locals 3

    const/16 v0, 0x3e0

    const/16 v1, 0x3e7

    const/16 v2, 0x3df

    .line 35
    filled-new-array {v2, v0, v1}, [I

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;->BLOCKED_COUNT_SAMPLES:[I

    return-void
.end method

.method private constructor <init>(Landroid/content/Context;Lcom/exteragram/messenger/adblock/WebAdBlocker;Lorg/telegram/ui/Components/ItemOptions;)V
    .locals 2

    const/4 v0, 0x0

    const/4 v1, 0x0

    .line 79
    invoke-direct {p0, p1, v1, v1, v0}, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;-><init>(Landroid/content/Context;ZZLorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 54
    new-instance v0, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem$1;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem$1;-><init>(Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;)V

    iput-object v0, p0, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;->updateRunnable:Ljava/lang/Runnable;

    .line 80
    iput-object p2, p0, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;->adBlocker:Lcom/exteragram/messenger/adblock/WebAdBlocker;

    .line 81
    iput-object p3, p0, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;->options:Lorg/telegram/ui/Components/ItemOptions;

    .line 83
    sget p2, Lorg/telegram/messenger/R$string;->BlockAds:I

    invoke-static {p2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p2

    sget p3, Lorg/telegram/messenger/R$drawable;->msg_policy:I

    invoke-virtual {p0, p2, p3}, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->setTextAndIcon(Ljava/lang/CharSequence;I)V

    .line 84
    invoke-virtual {p0, v1}, Landroid/view/ViewGroup;->setClipToPadding(Z)V

    .line 86
    new-instance p2, Lorg/telegram/ui/Components/Switch;

    invoke-direct {p2, p1}, Lorg/telegram/ui/Components/Switch;-><init>(Landroid/content/Context;)V

    iput-object p2, p0, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;->switchView:Lorg/telegram/ui/Components/Switch;

    .line 87
    sget p1, Lorg/telegram/ui/ActionBar/Theme;->key_switchTrack:I

    sget p3, Lorg/telegram/ui/ActionBar/Theme;->key_switchTrackChecked:I

    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhite:I

    invoke-virtual {p2, p1, p3, v0, v0}, Lorg/telegram/ui/Components/Switch;->setColors(IIII)V

    const/4 p1, 0x2

    .line 88
    invoke-virtual {p2, p1}, Landroid/view/View;->setImportantForAccessibility(I)V

    .line 89
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getEnableAdBlock()Z

    move-result p1

    invoke-virtual {p2, p1, v1}, Lorg/telegram/ui/Components/Switch;->setChecked(ZZ)V

    .line 90
    invoke-static {}, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->isMaterial3SwitchStyle()Z

    move-result p1

    if-eqz p1, :cond_0

    const p1, 0x3f59999a    # 0.85f

    .line 91
    invoke-virtual {p2, p1}, Landroid/view/View;->setScaleX(F)V

    .line 92
    invoke-virtual {p2, p1}, Landroid/view/View;->setScaleY(F)V

    .line 94
    :cond_0
    sget-boolean p1, Lorg/telegram/messenger/LocaleController;->isRTL:Z

    if-eqz p1, :cond_1

    const/4 p1, 0x3

    goto :goto_0

    :cond_1
    const/4 p1, 0x5

    :goto_0
    or-int/lit8 p1, p1, 0x10

    const/16 p3, 0x2c

    const/16 v0, 0x1c

    invoke-static {p3, v0, p1}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(III)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object p1

    invoke-virtual {p0, p2, p1}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 96
    new-instance p1, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem$$ExternalSyntheticLambda0;

    invoke-direct {p1, p0}, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;)V

    invoke-virtual {p0, p1}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 97
    invoke-direct {p0}, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;->update()V

    return-void
.end method

.method public static addTo(Lorg/telegram/ui/Components/ItemOptions;Lcom/exteragram/messenger/adblock/WebAdBlocker;)V
    .locals 2

    if-eqz p1, :cond_2

    .line 65
    invoke-static {}, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->isAvailable()Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 68
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getEnableAdBlock()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-static {}, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->isActive()Z

    move-result v0

    if-nez v0, :cond_1

    .line 69
    invoke-static {}, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->initialize()V

    .line 71
    :cond_1
    new-instance v0, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;

    invoke-virtual {p0}, Lorg/telegram/ui/Components/ItemOptions;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-direct {v0, v1, p1, p0}, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;-><init>(Landroid/content/Context;Lcom/exteragram/messenger/adblock/WebAdBlocker;Lorg/telegram/ui/Components/ItemOptions;)V

    .line 72
    invoke-virtual {p0, v0}, Lorg/telegram/ui/Components/ItemOptions;->add(Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;)Lorg/telegram/ui/Components/ItemOptions;

    .line 73
    invoke-direct {v0}, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;->alignSwitch()V

    .line 74
    invoke-direct {v0}, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;->fitWidth()V

    .line 75
    invoke-virtual {p0}, Lorg/telegram/ui/Components/ItemOptions;->addGap()Lorg/telegram/ui/Components/ItemOptions;

    :cond_2
    :goto_0
    return-void
.end method

.method private alignSwitch()V
    .locals 6

    .line 103
    invoke-static {}, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->isMaterial3SwitchStyle()Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    .line 104
    new-instance v0, Landroid/graphics/RectF;

    invoke-direct {v0}, Landroid/graphics/RectF;-><init>()V

    .line 105
    invoke-static {v0, v1, v1}, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->setTrackBounds(Landroid/graphics/RectF;II)V

    .line 106
    invoke-virtual {v0}, Landroid/graphics/RectF;->width()F

    move-result v2

    const v3, 0x3f59999a    # 0.85f

    mul-float/2addr v2, v3

    .line 107
    invoke-virtual {v0}, Landroid/graphics/RectF;->height()F

    move-result v0

    mul-float/2addr v0, v3

    goto :goto_0

    :cond_0
    const/high16 v0, 0x42140000    # 37.0f

    .line 109
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    int-to-float v2, v0

    const/high16 v0, 0x41a00000    # 20.0f

    .line 110
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    int-to-float v0, v0

    .line 112
    :goto_0
    iget-object v3, p0, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;->subtext:Ljava/lang/CharSequence;

    if-eqz v3, :cond_1

    const/high16 v3, 0x42600000    # 56.0f

    goto :goto_1

    :cond_1
    const/high16 v3, 0x42400000    # 48.0f

    :goto_1
    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    int-to-float v3, v3

    sub-float/2addr v3, v0

    const/high16 v0, 0x40000000    # 2.0f

    div-float/2addr v3, v0

    .line 113
    sget-boolean v4, Lorg/telegram/messenger/LocaleController;->isRTL:Z

    if-eqz v4, :cond_2

    invoke-virtual {p0}, Landroid/view/View;->getPaddingLeft()I

    move-result v4

    goto :goto_2

    :cond_2
    invoke-virtual {p0}, Landroid/view/View;->getPaddingRight()I

    move-result v4

    :goto_2
    const/high16 v5, 0x42300000    # 44.0f

    .line 114
    invoke-static {v5}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    int-to-float v5, v5

    sub-float v5, v2, v5

    div-float/2addr v5, v0

    add-float/2addr v5, v3

    int-to-float v0, v4

    sub-float/2addr v5, v0

    invoke-static {v5}, Ljava/lang/Math;->round(F)I

    move-result v4

    add-float/2addr v3, v2

    const/high16 v2, 0x41400000    # 12.0f

    .line 115
    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    int-to-float v2, v2

    add-float/2addr v3, v2

    sub-float/2addr v3, v0

    invoke-static {v3}, Ljava/lang/Math;->round(F)I

    move-result v0

    .line 117
    iget-object v2, p0, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;->switchView:Lorg/telegram/ui/Components/Switch;

    invoke-virtual {v2}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v2

    check-cast v2, Landroid/widget/FrameLayout$LayoutParams;

    .line 118
    sget-boolean v3, Lorg/telegram/messenger/LocaleController;->isRTL:Z

    if-eqz v3, :cond_3

    .line 119
    iput v4, v2, Landroid/widget/FrameLayout$LayoutParams;->leftMargin:I

    .line 120
    iget-object v2, p0, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->textView:Lorg/telegram/ui/Components/AnimatedEmojiSpan$TextViewEmojis;

    invoke-virtual {v2}, Landroid/view/View;->getPaddingRight()I

    move-result v3

    invoke-virtual {v2, v0, v1, v3, v1}, Landroid/view/View;->setPadding(IIII)V

    goto :goto_3

    .line 122
    :cond_3
    iput v4, v2, Landroid/widget/FrameLayout$LayoutParams;->rightMargin:I

    .line 123
    iget-object v2, p0, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->textView:Lorg/telegram/ui/Components/AnimatedEmojiSpan$TextViewEmojis;

    invoke-virtual {v2}, Landroid/view/View;->getPaddingLeft()I

    move-result v3

    invoke-virtual {v2, v3, v1, v0, v1}, Landroid/view/View;->setPadding(IIII)V

    .line 125
    :goto_3
    iget-object v0, p0, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->subtextView:Landroid/widget/TextView;

    if-eqz v0, :cond_4

    .line 126
    iget-object v2, p0, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->textView:Lorg/telegram/ui/Components/AnimatedEmojiSpan$TextViewEmojis;

    invoke-virtual {v2}, Landroid/view/View;->getPaddingLeft()I

    move-result v2

    iget-object p0, p0, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->textView:Lorg/telegram/ui/Components/AnimatedEmojiSpan$TextViewEmojis;

    invoke-virtual {p0}, Landroid/view/View;->getPaddingRight()I

    move-result p0

    invoke-virtual {v0, v2, v1, p0, v1}, Landroid/widget/TextView;->setPadding(IIII)V

    :cond_4
    return-void
.end method

.method private fitWidth()V
    .locals 10

    .line 131
    invoke-virtual {p0}, Landroid/view/View;->getMinimumWidth()I

    move-result v0

    if-gtz v0, :cond_0

    goto/16 :goto_2

    .line 135
    :cond_0
    new-instance v1, Landroid/text/TextPaint;

    const/4 v2, 0x1

    invoke-direct {v1, v2}, Landroid/text/TextPaint;-><init>(I)V

    const/high16 v2, 0x41500000    # 13.0f

    .line 136
    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    int-to-float v2, v2

    invoke-virtual {v1, v2}, Landroid/graphics/Paint;->setTextSize(F)V

    .line 137
    sget v2, Lorg/telegram/messenger/R$string;->AdBlockFiltersLoading:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Landroid/graphics/Paint;->measureText(Ljava/lang/String;)F

    move-result v2

    .line 138
    sget-object v3, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;->BLOCKED_COUNT_SAMPLES:[I

    array-length v4, v3

    const/4 v5, 0x0

    move v6, v5

    :goto_0
    if-ge v6, v4, :cond_1

    aget v7, v3, v6

    .line 139
    const-string v8, "BlockedRequests"

    new-array v9, v5, [Ljava/lang/Object;

    invoke-static {v8, v7, v9}, Lorg/telegram/messenger/LocaleController;->formatPluralString(Ljava/lang/String;I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v1, v7}, Landroid/graphics/Paint;->measureText(Ljava/lang/String;)F

    move-result v7

    invoke-static {v2, v7}, Ljava/lang/Math;->max(FF)F

    move-result v2

    add-int/lit8 v6, v6, 0x1

    goto :goto_0

    .line 141
    :cond_1
    iget-object v1, p0, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->textView:Lorg/telegram/ui/Components/AnimatedEmojiSpan$TextViewEmojis;

    invoke-virtual {v1}, Landroid/widget/TextView;->getPaint()Landroid/text/TextPaint;

    move-result-object v1

    iget-object v3, p0, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->textView:Lorg/telegram/ui/Components/AnimatedEmojiSpan$TextViewEmojis;

    invoke-virtual {v3}, Landroid/widget/TextView;->getText()Ljava/lang/CharSequence;

    move-result-object v3

    invoke-interface {v3}, Ljava/lang/CharSequence;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v3}, Landroid/graphics/Paint;->measureText(Ljava/lang/String;)F

    move-result v1

    invoke-static {v1, v2}, Ljava/lang/Math;->max(FF)F

    move-result v1

    .line 143
    invoke-virtual {p0}, Landroid/view/View;->getPaddingLeft()I

    move-result v2

    invoke-virtual {p0}, Landroid/view/View;->getPaddingRight()I

    move-result v3

    add-int/2addr v2, v3

    iget-object v3, p0, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->textView:Lorg/telegram/ui/Components/AnimatedEmojiSpan$TextViewEmojis;

    invoke-virtual {v3}, Landroid/view/View;->getPaddingLeft()I

    move-result v3

    add-int/2addr v2, v3

    iget-object v3, p0, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->textView:Lorg/telegram/ui/Components/AnimatedEmojiSpan$TextViewEmojis;

    invoke-virtual {v3}, Landroid/view/View;->getPaddingRight()I

    move-result v3

    add-int/2addr v2, v3

    float-to-double v3, v1

    invoke-static {v3, v4}, Ljava/lang/Math;->ceil(D)D

    move-result-wide v3

    double-to-int v1, v3

    add-int/2addr v2, v1

    sget-object v1, Lorg/telegram/messenger/AndroidUtilities;->displaySize:Landroid/graphics/Point;

    iget v1, v1, Landroid/graphics/Point;->x:I

    const/high16 v3, 0x42000000    # 32.0f

    .line 144
    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    sub-int/2addr v1, v3

    .line 142
    invoke-static {v2, v1}, Ljava/lang/Math;->min(II)I

    move-result v1

    if-le v1, v0, :cond_3

    int-to-float v0, v1

    .line 147
    sget v1, Lorg/telegram/messenger/AndroidUtilities;->density:F

    div-float/2addr v0, v1

    float-to-double v0, v0

    invoke-static {v0, v1}, Ljava/lang/Math;->ceil(D)D

    move-result-wide v0

    double-to-int v0, v0

    .line 148
    iget-object v1, p0, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;->options:Lorg/telegram/ui/Components/ItemOptions;

    invoke-virtual {v1, v0}, Lorg/telegram/ui/Components/ItemOptions;->setMinWidth(I)Lorg/telegram/ui/Components/ItemOptions;

    .line 149
    :goto_1
    iget-object v1, p0, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;->options:Lorg/telegram/ui/Components/ItemOptions;

    invoke-virtual {v1}, Lorg/telegram/ui/Components/ItemOptions;->getItemsCount()I

    move-result v1

    if-ge v5, v1, :cond_3

    .line 150
    iget-object v1, p0, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;->options:Lorg/telegram/ui/Components/ItemOptions;

    invoke-virtual {v1, v5}, Lorg/telegram/ui/Components/ItemOptions;->getItemAt(I)Landroid/view/View;

    move-result-object v1

    .line 151
    instance-of v2, v1, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;

    if-eqz v2, :cond_2

    int-to-float v2, v0

    .line 152
    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    invoke-virtual {v1, v3}, Landroid/view/View;->setMinimumWidth(I)V

    .line 153
    invoke-virtual {v1}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v1

    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    iput v2, v1, Landroid/view/ViewGroup$LayoutParams;->width:I

    :cond_2
    add-int/lit8 v5, v5, 0x1

    goto :goto_1

    :cond_3
    :goto_2
    return-void
.end method

.method private synthetic lambda$new$0(Landroid/view/View;)V
    .locals 0

    .line 96
    invoke-direct {p0}, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;->toggle()V

    return-void
.end method

.method private toggle()V
    .locals 4

    .line 175
    iget-boolean v0, p0, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;->toggled:Z

    if-eqz v0, :cond_0

    return-void

    :cond_0
    const/4 v0, 0x1

    .line 178
    iput-boolean v0, p0, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;->toggled:Z

    .line 179
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getEnableAdBlock()Z

    move-result v1

    xor-int/lit8 v2, v1, 0x1

    .line 180
    iget-object v3, p0, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;->switchView:Lorg/telegram/ui/Components/Switch;

    invoke-virtual {v3, v2, v0}, Lorg/telegram/ui/Components/Switch;->setChecked(ZZ)V

    if-nez v1, :cond_1

    .line 182
    iget-object v1, p0, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;->adBlocker:Lcom/exteragram/messenger/adblock/WebAdBlocker;

    invoke-static {v1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    new-instance v2, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem$$ExternalSyntheticLambda1;

    invoke-direct {v2, v1}, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem$$ExternalSyntheticLambda1;-><init>(Lcom/exteragram/messenger/adblock/WebAdBlocker;)V

    invoke-static {v0, v2}, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->setEnabled(ZLjava/lang/Runnable;)V

    goto :goto_0

    :cond_1
    const/4 v0, 0x0

    const/4 v1, 0x0

    .line 184
    invoke-static {v0, v1}, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->setEnabled(ZLjava/lang/Runnable;)V

    .line 185
    iget-object v0, p0, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;->adBlocker:Lcom/exteragram/messenger/adblock/WebAdBlocker;

    invoke-virtual {v0}, Lcom/exteragram/messenger/adblock/WebAdBlocker;->reload()V

    .line 187
    :goto_0
    iget-object p0, p0, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;->options:Lorg/telegram/ui/Components/ItemOptions;

    invoke-static {p0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    new-instance v0, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem$$ExternalSyntheticLambda2;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem$$ExternalSyntheticLambda2;-><init>(Lorg/telegram/ui/Components/ItemOptions;)V

    const-wide/16 v1, 0xc8

    invoke-static {v0, v1, v2}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    return-void
.end method

.method private update()V
    .locals 3

    .line 161
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getEnableAdBlock()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 162
    invoke-static {}, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->isActive()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 163
    iget-object v0, p0, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;->adBlocker:Lcom/exteragram/messenger/adblock/WebAdBlocker;

    invoke-virtual {v0}, Lcom/exteragram/messenger/adblock/WebAdBlocker;->getBlockedCount()I

    move-result v0

    const/4 v1, 0x0

    new-array v1, v1, [Ljava/lang/Object;

    const-string v2, "BlockedRequests"

    invoke-static {v2, v0, v1}, Lorg/telegram/messenger/LocaleController;->formatPluralString(Ljava/lang/String;I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    .line 164
    :cond_0
    sget v0, Lorg/telegram/messenger/R$string;->AdBlockFiltersLoading:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    :cond_1
    const/4 v0, 0x0

    .line 166
    :goto_0
    iget-object v1, p0, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->subtextView:Landroid/widget/TextView;

    if-eqz v1, :cond_2

    iget-object v1, p0, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;->subtext:Ljava/lang/CharSequence;

    invoke-static {v1, v0}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_2

    return-void

    .line 169
    :cond_2
    iput-object v0, p0, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;->subtext:Ljava/lang/CharSequence;

    if-eqz v0, :cond_3

    const/16 v0, 0x38

    goto :goto_1

    :cond_3
    const/16 v0, 0x30

    .line 170
    :goto_1
    invoke-virtual {p0, v0}, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->setItemHeight(I)V

    .line 171
    iget-object v0, p0, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;->subtext:Ljava/lang/CharSequence;

    invoke-virtual {p0, v0}, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->setSubtext(Ljava/lang/CharSequence;)V

    return-void
.end method


# virtual methods
.method public onAttachedToWindow()V
    .locals 2

    .line 192
    invoke-super {p0}, Landroid/view/View;->onAttachedToWindow()V

    .line 193
    iget-object p0, p0, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;->updateRunnable:Ljava/lang/Runnable;

    const-wide/16 v0, 0x1f4

    invoke-static {p0, v0, v1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    return-void
.end method

.method public onDetachedFromWindow()V
    .locals 0

    .line 198
    invoke-super {p0}, Landroid/view/View;->onDetachedFromWindow()V

    .line 199
    iget-object p0, p0, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;->updateRunnable:Ljava/lang/Runnable;

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->cancelRunOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public onInitializeAccessibilityNodeInfo(Landroid/view/accessibility/AccessibilityNodeInfo;)V
    .locals 1

    .line 204
    invoke-super {p0, p1}, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->onInitializeAccessibilityNodeInfo(Landroid/view/accessibility/AccessibilityNodeInfo;)V

    .line 205
    const-string v0, "android.widget.Switch"

    invoke-virtual {p1, v0}, Landroid/view/accessibility/AccessibilityNodeInfo;->setClassName(Ljava/lang/CharSequence;)V

    const/4 v0, 0x1

    .line 206
    invoke-virtual {p1, v0}, Landroid/view/accessibility/AccessibilityNodeInfo;->setCheckable(Z)V

    .line 207
    iget-object p0, p0, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;->switchView:Lorg/telegram/ui/Components/Switch;

    invoke-virtual {p0}, Lorg/telegram/ui/Components/Switch;->isChecked()Z

    move-result p0

    invoke-virtual {p1, p0}, Landroid/view/accessibility/AccessibilityNodeInfo;->setChecked(Z)V

    return-void
.end method

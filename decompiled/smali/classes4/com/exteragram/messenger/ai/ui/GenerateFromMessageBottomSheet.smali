.class public Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;
.super Lorg/telegram/ui/ActionBar/BottomSheet;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet$GenerationData;
    }
.end annotation


# instance fields
.field private includeImage:Z

.field parentFragment:Lorg/telegram/ui/ActionBar/BaseFragment;

.field private final promptCell:Lorg/telegram/ui/Cells/EditTextCell;

.field private useHistory:Z


# direct methods
.method public static synthetic $r8$lambda$BYDEYSncZIfkJCn8ozS0OQgvjFQ(Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;Landroid/view/View;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;->lambda$new$0(Landroid/view/View;)V

    return-void
.end method

.method public static synthetic $r8$lambda$HZgW2HHdfcC2aKfV676D4zoIiws(Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;Ljava/lang/Boolean;)Lkotlin/Unit;
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;->lambda$new$2(Ljava/lang/Boolean;)Lkotlin/Unit;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic $r8$lambda$bHGnMO_DqR8YWTfdFYEnwSJur5U(Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;Ljava/lang/String;Lorg/telegram/messenger/Utilities$Callback;Landroid/view/View;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;->lambda$new$4(Ljava/lang/String;Lorg/telegram/messenger/Utilities$Callback;Landroid/view/View;)V

    return-void
.end method

.method public static synthetic $r8$lambda$laNJ0iHDAsTlRULt4okWVi3GNfs(Landroid/view/View;Landroid/view/MotionEvent;)Z
    .locals 2

    .line 112
    invoke-virtual {p0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    const/4 v1, 0x1

    invoke-interface {v0, v1}, Landroid/view/ViewParent;->requestDisallowInterceptTouchEvent(Z)V

    .line 113
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getAction()I

    move-result p1

    and-int/lit16 p1, p1, 0xff

    const/4 v0, 0x0

    if-ne p1, v1, :cond_0

    .line 114
    invoke-virtual {p0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object p0

    invoke-interface {p0, v0}, Landroid/view/ViewParent;->requestDisallowInterceptTouchEvent(Z)V

    :cond_0
    return v0
.end method

.method public static synthetic $r8$lambda$y7a9dkx1qe6qfr2ry1Uu9Fn6JmM(Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;->lambda$show$5()V

    return-void
.end method

.method public static synthetic $r8$lambda$yzFJS6MgHV5CP3_XizKVS38e4-0(Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;Ljava/lang/Boolean;)Lkotlin/Unit;
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;->lambda$new$3(Ljava/lang/Boolean;)Lkotlin/Unit;

    move-result-object p0

    return-object p0
.end method

.method public constructor <init>(Ljava/lang/String;Ljava/lang/String;Lorg/telegram/ui/ActionBar/BaseFragment;Landroid/content/Context;Lorg/telegram/messenger/Utilities$Callback;)V
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            "Lorg/telegram/ui/ActionBar/BaseFragment;",
            "Landroid/content/Context;",
            "Lorg/telegram/messenger/Utilities$Callback<",
            "Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet$GenerationData;",
            ">;)V"
        }
    .end annotation

    .line 61
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getSaveHistory()Z

    move-result v6

    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    move-object v3, p3

    move-object v4, p4

    move-object v5, p5

    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;-><init>(Ljava/lang/String;Ljava/lang/String;Lorg/telegram/ui/ActionBar/BaseFragment;Landroid/content/Context;Lorg/telegram/messenger/Utilities$Callback;Z)V

    return-void
.end method

.method public constructor <init>(Ljava/lang/String;Ljava/lang/String;Lorg/telegram/ui/ActionBar/BaseFragment;Landroid/content/Context;Lorg/telegram/messenger/Utilities$Callback;Z)V
    .locals 20
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "ClickableViewAccessibility"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            "Lorg/telegram/ui/ActionBar/BaseFragment;",
            "Landroid/content/Context;",
            "Lorg/telegram/messenger/Utilities$Callback<",
            "Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet$GenerationData;",
            ">;Z)V"
        }
    .end annotation

    move-object/from16 v0, p0

    move-object/from16 v2, p4

    .line 66
    invoke-virtual/range {p3 .. p3}, Lorg/telegram/ui/ActionBar/BaseFragment;->getResourceProvider()Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    move-result-object v1

    const/4 v8, 0x1

    invoke-direct {v0, v2, v8, v1}, Lorg/telegram/ui/ActionBar/BottomSheet;-><init>(Landroid/content/Context;ZLorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 67
    sget v1, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundGray:I

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v3

    invoke-virtual {v0, v3}, Lorg/telegram/ui/ActionBar/BottomSheet;->setBackgroundColor(I)V

    .line 68
    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v1

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/BottomSheet;->fixNavigationBar(I)V

    const/4 v9, 0x0

    .line 69
    invoke-virtual {v0, v9}, Lorg/telegram/ui/ActionBar/BottomSheet;->setApplyTopPadding(Z)V

    .line 70
    iput-boolean v8, v0, Lorg/telegram/ui/ActionBar/BottomSheet;->smoothKeyboardAnimationEnabled:Z

    move-object/from16 v1, p3

    .line 71
    iput-object v1, v0, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;->parentFragment:Lorg/telegram/ui/ActionBar/BaseFragment;

    .line 72
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getSaveHistory()Z

    move-result v1

    iput-boolean v1, v0, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;->useHistory:Z

    .line 73
    invoke-static/range {p2 .. p2}, Lcom/exteragram/messenger/ai/AiController;->canSendImage(Ljava/lang/String;)Z

    move-result v1

    iput-boolean v1, v0, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;->includeImage:Z

    .line 74
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->warmup()V

    .line 76
    new-instance v10, Landroid/widget/ScrollView;

    invoke-direct {v10, v2}, Landroid/widget/ScrollView;-><init>(Landroid/content/Context;)V

    .line 78
    new-instance v11, Landroid/widget/LinearLayout;

    invoke-direct {v11, v2}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    .line 79
    invoke-virtual {v11, v8}, Landroid/widget/LinearLayout;->setOrientation(I)V

    .line 80
    invoke-virtual {v11, v9}, Landroid/view/ViewGroup;->setClipChildren(Z)V

    .line 81
    invoke-virtual {v11, v9}, Landroid/view/ViewGroup;->setClipToPadding(Z)V

    const/4 v1, -0x2

    const/16 v3, 0x33

    const/4 v4, -0x1

    .line 82
    invoke-static {v4, v1, v3}, Lorg/telegram/ui/Components/LayoutHelper;->createScroll(III)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v1

    invoke-virtual {v10, v11, v1}, Landroid/widget/ScrollView;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 84
    new-instance v1, Landroid/widget/FrameLayout;

    invoke-direct {v1, v2}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;)V

    .line 86
    new-instance v3, Landroid/widget/TextView;

    invoke-direct {v3, v2}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    .line 87
    sget v5, Lorg/telegram/messenger/R$string;->Generate:I

    invoke-static {v5}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v3, v5}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    const/high16 v12, 0x41a00000    # 20.0f

    .line 88
    invoke-virtual {v3, v8, v12}, Landroid/widget/TextView;->setTextSize(IF)V

    .line 89
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->bold()Landroid/graphics/Typeface;

    move-result-object v5

    invoke-virtual {v3, v5}, Landroid/widget/TextView;->setTypeface(Landroid/graphics/Typeface;)V

    .line 90
    sget v5, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteBlackText:I

    invoke-virtual {v0, v5}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v6

    invoke-virtual {v3, v6}, Landroid/widget/TextView;->setTextColor(I)V

    const/16 v6, 0x13

    .line 91
    invoke-virtual {v3, v6}, Landroid/widget/TextView;->setGravity(I)V

    const/high16 v18, 0x42600000    # 56.0f

    const/16 v19, 0x0

    const/4 v13, -0x1

    const/high16 v14, 0x42600000    # 56.0f

    const/16 v15, 0x33

    const/high16 v16, 0x41b00000    # 22.0f

    const/high16 v17, 0x40c00000    # 6.0f

    .line 92
    invoke-static/range {v13 .. v19}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IFIFFFF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v6

    invoke-virtual {v1, v3, v6}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 94
    new-instance v3, Landroid/widget/ImageView;

    invoke-direct {v3, v2}, Landroid/widget/ImageView;-><init>(Landroid/content/Context;)V

    .line 95
    sget v6, Lorg/telegram/messenger/R$drawable;->ic_close_white:I

    invoke-virtual {v3, v6}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 96
    sget-object v6, Landroid/widget/ImageView$ScaleType;->CENTER:Landroid/widget/ImageView$ScaleType;

    invoke-virtual {v3, v6}, Landroid/widget/ImageView;->setScaleType(Landroid/widget/ImageView$ScaleType;)V

    .line 97
    new-instance v6, Landroid/graphics/PorterDuffColorFilter;

    invoke-virtual {v0, v5}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v5

    sget-object v7, Landroid/graphics/PorterDuff$Mode;->SRC_IN:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v6, v5, v7}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {v3, v6}, Landroid/widget/ImageView;->setColorFilter(Landroid/graphics/ColorFilter;)V

    .line 98
    invoke-static {v3}, Lorg/telegram/ui/Components/ScaleStateListAnimator;->apply(Landroid/view/View;)V

    .line 99
    new-instance v5, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet$$ExternalSyntheticLambda0;

    invoke-direct {v5, v0}, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;)V

    invoke-virtual {v3, v5}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    const/high16 v18, 0x41400000    # 12.0f

    const/16 v13, 0x30

    const/high16 v14, 0x42400000    # 48.0f

    const/16 v15, 0x35

    const/16 v16, 0x0

    const/high16 v17, 0x41200000    # 10.0f

    .line 100
    invoke-static/range {v13 .. v19}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IFIFFFF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v5

    invoke-virtual {v1, v3, v5}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    const/16 v3, 0x46

    .line 102
    invoke-static {v4, v3}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(II)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v3

    invoke-virtual {v11, v1, v3}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 104
    new-instance v1, Lorg/telegram/ui/Cells/EditTextCell;

    sget v3, Lorg/telegram/messenger/R$string;->RolePrompt:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    const/4 v6, -0x1

    iget-object v7, v0, Lorg/telegram/ui/ActionBar/BottomSheet;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    const/4 v4, 0x1

    const/4 v5, 0x0

    invoke-direct/range {v1 .. v7}, Lorg/telegram/ui/Cells/EditTextCell;-><init>(Landroid/content/Context;Ljava/lang/String;ZZILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    iput-object v1, v0, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;->promptCell:Lorg/telegram/ui/Cells/EditTextCell;

    .line 105
    iget-object v3, v1, Lorg/telegram/ui/Cells/EditTextCell;->editText:Lorg/telegram/ui/Components/EditTextCaption;

    const/4 v4, 0x6

    invoke-virtual {v3, v4}, Landroid/widget/TextView;->setImeOptions(I)V

    .line 106
    iget-object v3, v1, Lorg/telegram/ui/Cells/EditTextCell;->editText:Lorg/telegram/ui/Components/EditTextCaption;

    const/16 v4, 0x8

    invoke-virtual {v3, v4}, Landroid/widget/TextView;->setMaxLines(I)V

    .line 107
    invoke-static {v12}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    sget v4, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhite:I

    invoke-virtual {v0, v4}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v4

    invoke-static {v3, v4}, Lorg/telegram/ui/ActionBar/Theme;->createRoundRectDrawable(II)Landroid/graphics/drawable/ShapeDrawable;

    move-result-object v3

    invoke-virtual {v1, v3}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V

    .line 108
    invoke-static/range {p1 .. p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v3

    if-nez v3, :cond_0

    move-object/from16 v3, p1

    .line 109
    invoke-virtual {v1, v3}, Lorg/telegram/ui/Cells/EditTextCell;->setText(Ljava/lang/CharSequence;)V

    .line 111
    :cond_0
    iget-object v3, v1, Lorg/telegram/ui/Cells/EditTextCell;->editText:Lorg/telegram/ui/Components/EditTextCaption;

    new-instance v4, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet$$ExternalSyntheticLambda1;

    invoke-direct {v4}, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet$$ExternalSyntheticLambda1;-><init>()V

    invoke-virtual {v3, v4}, Landroid/view/View;->setOnTouchListener(Landroid/view/View$OnTouchListener;)V

    const/high16 v16, 0x41400000    # 12.0f

    const/16 v17, 0x0

    const/4 v12, -0x1

    const/4 v13, -0x2

    const/high16 v14, 0x41400000    # 12.0f

    const/4 v15, 0x0

    .line 118
    invoke-static/range {v12 .. v17}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIFFFF)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v3

    invoke-virtual {v11, v1, v3}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 120
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getSaveHistory()Z

    move-result v1

    if-eqz v1, :cond_1

    if-eqz p6, :cond_1

    move v1, v8

    goto :goto_0

    :cond_1
    move v1, v9

    .line 121
    :goto_0
    invoke-static/range {p2 .. p2}, Lcom/exteragram/messenger/ai/AiController;->canSendImage(Ljava/lang/String;)Z

    move-result v3

    if-nez v1, :cond_3

    if-eqz v3, :cond_2

    goto :goto_1

    :cond_2
    const/4 v1, 0x0

    goto/16 :goto_2

    .line 124
    :cond_3
    :goto_1
    new-instance v4, Landroid/widget/LinearLayout;

    invoke-direct {v4, v2}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    .line 125
    invoke-virtual {v4, v9}, Landroid/widget/LinearLayout;->setOrientation(I)V

    .line 126
    invoke-virtual {v4, v8}, Landroid/widget/LinearLayout;->setGravity(I)V

    if-eqz v1, :cond_4

    .line 129
    new-instance v1, Lcom/exteragram/messenger/components/CheckBoxRow;

    sget v5, Lorg/telegram/messenger/R$string;->GenerateWithHistory:I

    invoke-static {v5}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v5

    iget-boolean v6, v0, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;->useHistory:Z

    iget-object v7, v0, Lorg/telegram/ui/ActionBar/BottomSheet;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-direct {v1, v2, v5, v6, v7}, Lcom/exteragram/messenger/components/CheckBoxRow;-><init>(Landroid/content/Context;Ljava/lang/CharSequence;ZLorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 130
    new-instance v5, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet$$ExternalSyntheticLambda2;

    invoke-direct {v5, v0}, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet$$ExternalSyntheticLambda2;-><init>(Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;)V

    invoke-virtual {v1, v5}, Lcom/exteragram/messenger/components/CheckBoxRow;->setOnCheckedChange(Lkotlin/jvm/functions/Function1;)V

    const/high16 v16, 0x40800000    # 4.0f

    const/16 v17, 0x0

    const/4 v12, -0x2

    const/4 v13, -0x2

    const/high16 v14, 0x40800000    # 4.0f

    const/4 v15, 0x0

    .line 134
    invoke-static/range {v12 .. v17}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIFFFF)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v5

    invoke-virtual {v4, v1, v5}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    :cond_4
    if-eqz v3, :cond_5

    .line 137
    new-instance v1, Lcom/exteragram/messenger/components/CheckBoxRow;

    sget v3, Lorg/telegram/messenger/R$string;->GenerateWithPhoto:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    iget-boolean v5, v0, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;->includeImage:Z

    iget-object v6, v0, Lorg/telegram/ui/ActionBar/BottomSheet;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-direct {v1, v2, v3, v5, v6}, Lcom/exteragram/messenger/components/CheckBoxRow;-><init>(Landroid/content/Context;Ljava/lang/CharSequence;ZLorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 138
    new-instance v3, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet$$ExternalSyntheticLambda3;

    invoke-direct {v3, v0}, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet$$ExternalSyntheticLambda3;-><init>(Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;)V

    invoke-virtual {v1, v3}, Lcom/exteragram/messenger/components/CheckBoxRow;->setOnCheckedChange(Lkotlin/jvm/functions/Function1;)V

    .line 142
    new-instance v3, Lorg/telegram/ui/Components/BackupImageView;

    invoke-direct {v3, v2}, Lorg/telegram/ui/Components/BackupImageView;-><init>(Landroid/content/Context;)V

    .line 143
    invoke-virtual {v3}, Lorg/telegram/ui/Components/BackupImageView;->getImageReceiver()Lorg/telegram/messenger/ImageReceiver;

    move-result-object v12

    const/16 v16, 0x0

    const-wide/16 v17, 0x0

    const-string v14, "24_24"

    const/4 v15, 0x0

    move-object/from16 v13, p2

    invoke-virtual/range {v12 .. v18}, Lorg/telegram/messenger/ImageReceiver;->setImage(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/drawable/Drawable;Ljava/lang/String;J)V

    const/high16 v5, 0x40800000    # 4.0f

    .line 144
    invoke-static {v5}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    invoke-virtual {v3, v5}, Lorg/telegram/ui/Components/BackupImageView;->setRoundRadius(I)V

    const/16 v17, 0x0

    const/16 v18, 0x0

    const/16 v12, 0x18

    const/16 v13, 0x18

    const/16 v14, 0x10

    const/16 v15, 0x9

    const/16 v16, 0x0

    .line 145
    invoke-static/range {v12 .. v18}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIIIIII)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v5

    invoke-virtual {v1, v3, v5}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    const/high16 v16, 0x40800000    # 4.0f

    const/16 v17, 0x0

    const/4 v12, -0x2

    const/4 v13, -0x2

    const/high16 v14, 0x40800000    # 4.0f

    const/4 v15, 0x0

    .line 146
    invoke-static/range {v12 .. v17}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIFFFF)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v3

    invoke-virtual {v4, v1, v3}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    :cond_5
    move-object v1, v4

    .line 150
    :goto_2
    new-instance v3, Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;

    iget-object v4, v0, Lorg/telegram/ui/ActionBar/BottomSheet;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-direct {v3, v2, v4}, Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    invoke-virtual {v3}, Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;->setRound()Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;

    move-result-object v2

    .line 151
    sget v3, Lorg/telegram/messenger/R$string;->Proceed:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3, v9}, Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;->setText(Ljava/lang/CharSequence;Z)V

    .line 152
    new-instance v3, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet$$ExternalSyntheticLambda4;

    move-object/from16 v13, p2

    move-object/from16 v4, p5

    invoke-direct {v3, v0, v13, v4}, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet$$ExternalSyntheticLambda4;-><init>(Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;Ljava/lang/String;Lorg/telegram/messenger/Utilities$Callback;)V

    invoke-virtual {v2, v3}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    if-eqz v1, :cond_6

    const/16 v3, 0x10

    :goto_3
    move/from16 v18, v3

    goto :goto_4

    :cond_6
    const/16 v3, 0xc

    goto :goto_3

    :goto_4
    const/4 v12, -0x1

    const/16 v13, 0x30

    const/4 v14, 0x0

    const/16 v15, 0xc

    const/16 v16, 0xc

    const/16 v17, 0xc

    .line 170
    invoke-static/range {v12 .. v18}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIIIIII)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v3

    invoke-virtual {v11, v2, v3}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    if-eqz v1, :cond_7

    const/4 v2, 0x0

    const/high16 v3, 0x41000000    # 8.0f

    const/4 v4, -0x1

    const/4 v5, -0x2

    const/4 v6, 0x0

    const/4 v7, 0x0

    move/from16 p5, v2

    move/from16 p6, v3

    move/from16 p1, v4

    move/from16 p2, v5

    move/from16 p3, v6

    move/from16 p4, v7

    .line 172
    invoke-static/range {p1 .. p6}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIFFFF)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v2

    invoke-virtual {v11, v1, v2}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 175
    :cond_7
    invoke-virtual {v0, v10}, Lorg/telegram/ui/ActionBar/BottomSheet;->setCustomView(Landroid/view/View;)V

    return-void
.end method

.method public constructor <init>(Lorg/telegram/messenger/MessageObject;Lorg/telegram/messenger/MessageObject$GroupedMessages;Lorg/telegram/ui/ActionBar/BaseFragment;Landroid/content/Context;Lorg/telegram/messenger/Utilities$Callback;)V
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lorg/telegram/messenger/MessageObject;",
            "Lorg/telegram/messenger/MessageObject$GroupedMessages;",
            "Lorg/telegram/ui/ActionBar/BaseFragment;",
            "Landroid/content/Context;",
            "Lorg/telegram/messenger/Utilities$Callback<",
            "Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet$GenerationData;",
            ">;)V"
        }
    .end annotation

    .line 53
    invoke-static {}, Lcom/exteragram/messenger/utils/chats/ChatUtils;->getInstance()Lcom/exteragram/messenger/utils/chats/ChatUtils;

    move-result-object v0

    invoke-virtual {v0, p1, p2}, Lcom/exteragram/messenger/utils/chats/ChatUtils;->getMessageText(Lorg/telegram/messenger/MessageObject;Lorg/telegram/messenger/MessageObject$GroupedMessages;)Ljava/lang/CharSequence;

    move-result-object p2

    const-string v0, ""

    invoke-static {p2, v0}, Ljava/util/Objects;->toString(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/utils/chats/ChatUtils;->getInstance()Lcom/exteragram/messenger/utils/chats/ChatUtils;

    move-result-object p2

    invoke-virtual {p2, p1}, Lcom/exteragram/messenger/utils/chats/ChatUtils;->getPathToMessage(Lorg/telegram/messenger/MessageObject;)Ljava/lang/String;

    move-result-object v3

    move-object v1, p0

    move-object v4, p3

    move-object v5, p4

    move-object v6, p5

    invoke-direct/range {v1 .. v6}, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;-><init>(Ljava/lang/String;Ljava/lang/String;Lorg/telegram/ui/ActionBar/BaseFragment;Landroid/content/Context;Lorg/telegram/messenger/Utilities$Callback;)V

    return-void
.end method

.method public constructor <init>(Lorg/telegram/ui/ActionBar/BaseFragment;Landroid/content/Context;Lorg/telegram/messenger/Utilities$Callback;Z)V
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lorg/telegram/ui/ActionBar/BaseFragment;",
            "Landroid/content/Context;",
            "Lorg/telegram/messenger/Utilities$Callback<",
            "Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet$GenerationData;",
            ">;Z)V"
        }
    .end annotation

    .line 57
    const-string v1, ""

    const-string v2, ""

    move-object v0, p0

    move-object v3, p1

    move-object v4, p2

    move-object v5, p3

    move v6, p4

    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;-><init>(Ljava/lang/String;Ljava/lang/String;Lorg/telegram/ui/ActionBar/BaseFragment;Landroid/content/Context;Lorg/telegram/messenger/Utilities$Callback;Z)V

    return-void
.end method

.method private synthetic lambda$new$0(Landroid/view/View;)V
    .locals 0

    .line 99
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BottomSheet;->dismiss()V

    return-void
.end method

.method private synthetic lambda$new$2(Ljava/lang/Boolean;)Lkotlin/Unit;
    .locals 0

    .line 131
    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    iput-boolean p1, p0, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;->useHistory:Z

    .line 132
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method private synthetic lambda$new$3(Ljava/lang/Boolean;)Lkotlin/Unit;
    .locals 0

    .line 139
    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    iput-boolean p1, p0, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;->includeImage:Z

    .line 140
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method private synthetic lambda$new$4(Ljava/lang/String;Lorg/telegram/messenger/Utilities$Callback;Landroid/view/View;)V
    .locals 2

    .line 153
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;->promptCell:Lorg/telegram/ui/Cells/EditTextCell;

    iget-object v0, v0, Lorg/telegram/ui/Cells/EditTextCell;->editText:Lorg/telegram/ui/Components/EditTextCaption;

    invoke-virtual {v0}, Lcom/exteragram/messenger/components/ReceiveContentEditText;->getText()Landroid/text/Editable;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    .line 155
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_1

    .line 156
    iget-boolean v0, p0, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;->includeImage:Z

    if-nez v0, :cond_0

    .line 157
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;->promptCell:Lorg/telegram/ui/Cells/EditTextCell;

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->shakeViewSpring(Landroid/view/View;)V

    const/4 p0, 0x0

    .line 158
    invoke-virtual {p3, p0}, Landroid/view/View;->performHapticFeedback(I)Z

    return-void

    .line 161
    :cond_0
    sget p3, Lorg/telegram/messenger/R$string;->AttachPhoto:I

    invoke-static {p3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    .line 165
    :cond_1
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BottomSheet;->dismiss()V

    .line 166
    iget-boolean p3, p0, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;->includeImage:Z

    if-eqz p3, :cond_2

    goto :goto_0

    :cond_2
    const/4 p1, 0x0

    .line 167
    :goto_0
    new-instance p3, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet$GenerationData;

    iget-boolean p0, p0, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;->useHistory:Z

    invoke-direct {p3, v0, p0, p1}, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet$GenerationData;-><init>(Ljava/lang/String;ZLjava/lang/String;)V

    .line 168
    invoke-interface {p2, p3}, Lorg/telegram/messenger/Utilities$Callback;->run(Ljava/lang/Object;)V

    return-void
.end method

.method private synthetic lambda$show$5()V
    .locals 2

    .line 182
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;->promptCell:Lorg/telegram/ui/Cells/EditTextCell;

    iget-object v0, v0, Lorg/telegram/ui/Cells/EditTextCell;->editText:Lorg/telegram/ui/Components/EditTextCaption;

    invoke-virtual {v0}, Landroid/widget/TextView;->length()I

    move-result v1

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/EditTextBoldCursor;->setSelection(I)V

    .line 183
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;->promptCell:Lorg/telegram/ui/Cells/EditTextCell;

    iget-object p0, p0, Lorg/telegram/ui/Cells/EditTextCell;->editText:Lorg/telegram/ui/Components/EditTextCaption;

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->showKeyboard(Landroid/view/View;)Z

    return-void
.end method


# virtual methods
.method public show()V
    .locals 3

    .line 180
    invoke-super {p0}, Lorg/telegram/ui/ActionBar/BottomSheet;->show()V

    .line 181
    new-instance v0, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet$$ExternalSyntheticLambda5;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet$$ExternalSyntheticLambda5;-><init>(Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;)V

    const-wide/16 v1, 0xc8

    invoke-static {v0, v1, v2}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    return-void
.end method

.class Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;
.super Landroid/widget/FrameLayout;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/ai/ui/AiResponseAlert;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = "HeaderView"
.end annotation


# instance fields
.field private final backButton:Landroid/widget/ImageView;

.field public final insertButton:Landroid/widget/ImageView;

.field private final modelSelector:Lorg/telegram/ui/Components/AnimatedTextView;

.field public final optionsButton:Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

.field private final shadow:Landroid/view/View;

.field private final subtitleView:Landroid/widget/LinearLayout;

.field final synthetic this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

.field private final titleTextView:Landroid/widget/TextView;


# direct methods
.method public static synthetic $r8$lambda$046odY6OZ-pKcRwXnfwGmKJi1DQ(Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;Lorg/telegram/ui/Components/ItemOptions;Lcom/exteragram/messenger/ai/data/Service;Landroid/view/View;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->lambda$openModelSelect$8(Lorg/telegram/ui/Components/ItemOptions;Lcom/exteragram/messenger/ai/data/Service;Landroid/view/View;)V

    return-void
.end method

.method public static synthetic $r8$lambda$3ENC3lU9oGr8T6nVzn6SgsG4dIU(Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;Landroid/view/View;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->lambda$new$6(Landroid/view/View;)V

    return-void
.end method

.method public static synthetic $r8$lambda$6gfZ3Cwr9k9tbYNANIh3GZrI6tg(Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet$GenerationData;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->lambda$new$4(Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet$GenerationData;)V

    return-void
.end method

.method public static synthetic $r8$lambda$FL8CcuL4__QV18xF7A157PNbpgo(Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;Landroid/view/View;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->lambda$new$2(Landroid/view/View;)V

    return-void
.end method

.method public static synthetic $r8$lambda$PBGelh1Vxdd9b69A1c8Q2bIPgA0(Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;Landroid/view/View;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->lambda$new$7(Landroid/view/View;)V

    return-void
.end method

.method public static synthetic $r8$lambda$ehzWdDWzPj5NKShetGW0oLusx_c(Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet$GenerationData;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->lambda$new$3(Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet$GenerationData;)V

    return-void
.end method

.method public static synthetic $r8$lambda$rDmc9u9E9S2EyrGPrE9wSdKcSug(Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;Landroid/view/View;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->lambda$new$1(Landroid/view/View;)V

    return-void
.end method

.method public static synthetic $r8$lambda$xFippfwMossz3PIQ6Sp2ZXlvWZw(Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;I)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->lambda$new$5(I)V

    return-void
.end method

.method public static synthetic $r8$lambda$xThZGze-zacKTsjhO-GiQ2GrGxQ(Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;Landroid/view/View;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->lambda$new$0(Landroid/view/View;)V

    return-void
.end method

.method public static synthetic $r8$lambda$ybsKk5QYNDoHkII7Go609LdxeCE(Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;Lcom/exteragram/messenger/ai/data/Role;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->lambda$openRoleSelect$9(Lcom/exteragram/messenger/ai/data/Role;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$fgettitleTextView(Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;)Landroid/widget/TextView;
    .locals 0

    .line 0
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->titleTextView:Landroid/widget/TextView;

    return-object p0
.end method

.method public constructor <init>(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;Landroid/content/Context;)V
    .locals 26

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    move-object/from16 v3, p2

    .line 1084
    iput-object v1, v0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    .line 1085
    invoke-direct {v0, v3}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;)V

    .line 1087
    new-instance v2, Landroid/view/View;

    invoke-direct {v2, v3}, Landroid/view/View;-><init>(Landroid/content/Context;)V

    .line 1088
    sget v4, Lorg/telegram/ui/ActionBar/Theme;->key_dialogBackground:I

    invoke-static {v1, v4}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->access$500(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;I)I

    move-result v4

    invoke-virtual {v2, v4}, Landroid/view/View;->setBackgroundColor(I)V

    const/4 v10, 0x0

    const/4 v11, 0x0

    const/4 v5, -0x1

    const/high16 v6, 0x42300000    # 44.0f

    const/16 v7, 0x37

    const/4 v8, 0x0

    const/high16 v9, 0x41400000    # 12.0f

    .line 1089
    invoke-static/range {v5 .. v11}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IFIFFFF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v4

    invoke-virtual {v0, v2, v4}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 1091
    new-instance v9, Landroid/widget/ImageView;

    invoke-direct {v9, v3}, Landroid/widget/ImageView;-><init>(Landroid/content/Context;)V

    iput-object v9, v0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->backButton:Landroid/widget/ImageView;

    .line 1092
    sget-object v2, Landroid/widget/ImageView$ScaleType;->CENTER:Landroid/widget/ImageView$ScaleType;

    invoke-virtual {v9, v2}, Landroid/widget/ImageView;->setScaleType(Landroid/widget/ImageView$ScaleType;)V

    .line 1093
    sget v4, Lorg/telegram/messenger/R$drawable;->ic_ab_back:I

    invoke-virtual {v9, v4}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 1094
    new-instance v4, Landroid/graphics/PorterDuffColorFilter;

    sget v10, Lorg/telegram/ui/ActionBar/Theme;->key_dialogTextBlack:I

    invoke-static {v1, v10}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->access$600(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;I)I

    move-result v5

    sget-object v11, Landroid/graphics/PorterDuff$Mode;->MULTIPLY:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v4, v5, v11}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {v9, v4}, Landroid/widget/ImageView;->setColorFilter(Landroid/graphics/ColorFilter;)V

    .line 1095
    sget v12, Lorg/telegram/ui/ActionBar/Theme;->key_listSelector:I

    invoke-static {v1, v12}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->access$700(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;I)I

    move-result v4

    invoke-static {v4}, Lorg/telegram/ui/ActionBar/Theme;->createSelectorDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v4

    invoke-virtual {v9, v4}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V

    const/4 v13, 0x0

    .line 1096
    invoke-virtual {v9, v13}, Landroid/view/View;->setAlpha(F)V

    .line 1097
    new-instance v4, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView$$ExternalSyntheticLambda1;

    invoke-direct {v4, v0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView$$ExternalSyntheticLambda1;-><init>(Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;)V

    invoke-virtual {v9, v4}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    const/high16 v19, 0x3f800000    # 1.0f

    const/high16 v20, 0x3f800000    # 1.0f

    const/16 v14, 0x36

    const/high16 v15, 0x42580000    # 54.0f

    const/16 v16, 0x30

    const/high16 v17, 0x3f800000    # 1.0f

    const/high16 v18, 0x3f800000    # 1.0f

    .line 1098
    invoke-static/range {v14 .. v20}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IFIFFFF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v4

    invoke-virtual {v0, v9, v4}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 1100
    new-instance v14, Landroid/widget/ImageView;

    invoke-direct {v14, v3}, Landroid/widget/ImageView;-><init>(Landroid/content/Context;)V

    iput-object v14, v0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->insertButton:Landroid/widget/ImageView;

    const v4, 0x3e19999a    # 0.15f

    const/high16 v5, 0x3fc00000    # 1.5f

    .line 1101
    invoke-static {v14, v4, v5}, Lorg/telegram/ui/Components/ScaleStateListAnimator;->apply(Landroid/view/View;FF)V

    .line 1102
    invoke-virtual {v14, v2}, Landroid/widget/ImageView;->setScaleType(Landroid/widget/ImageView$ScaleType;)V

    .line 1103
    sget v2, Lorg/telegram/messenger/R$drawable;->msg_send:I

    invoke-virtual {v14, v2}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 1104
    new-instance v2, Landroid/graphics/PorterDuffColorFilter;

    sget v6, Lorg/telegram/ui/ActionBar/Theme;->key_player_actionBarSubtitle:I

    invoke-static {v1, v6}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->access$800(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;I)I

    move-result v4

    invoke-direct {v2, v4, v11}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {v14, v2}, Landroid/widget/ImageView;->setColorFilter(Landroid/graphics/ColorFilter;)V

    .line 1105
    invoke-static {v1, v12}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->access$900(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;I)I

    move-result v2

    invoke-static {v2}, Lorg/telegram/ui/ActionBar/Theme;->createSelectorDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v2

    invoke-virtual {v14, v2}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V

    const/16 v15, 0x8

    .line 1106
    invoke-virtual {v14, v15}, Landroid/widget/ImageView;->setVisibility(I)V

    .line 1107
    new-instance v2, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView$$ExternalSyntheticLambda2;

    invoke-direct {v2, v0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView$$ExternalSyntheticLambda2;-><init>(Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;)V

    invoke-virtual {v14, v2}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    const/high16 v21, 0x42800000    # 64.0f

    const/high16 v22, 0x3f800000    # 1.0f

    const/high16 v17, 0x42580000    # 54.0f

    const/16 v18, 0x35

    .line 1111
    invoke-static/range {v16 .. v22}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IFIFFFF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v2

    invoke-virtual {v0, v14, v2}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 1113
    new-instance v2, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    const/4 v7, 0x0

    invoke-static {v1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->access$1000(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    move-result-object v8

    const/4 v4, 0x0

    const/4 v5, 0x0

    invoke-direct/range {v2 .. v8}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/ActionBarMenu;IIZLorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    iput-object v2, v0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->optionsButton:Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    const/4 v4, 0x0

    .line 1114
    invoke-virtual {v2, v4}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->setLongClickEnabled(Z)V

    .line 1115
    invoke-virtual {v2, v4}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->setShowSubmenuByMove(Z)V

    .line 1116
    sget v5, Lorg/telegram/messenger/R$drawable;->ic_ab_other:I

    invoke-virtual {v2, v5}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->setIcon(I)V

    const/4 v5, 0x2

    .line 1117
    invoke-virtual {v2, v5}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->setSubMenuOpenSide(I)V

    .line 1118
    invoke-virtual {v2, v15}, Landroid/view/View;->setVisibility(I)V

    .line 1119
    invoke-static {v1, v12}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->access$1100(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;I)I

    move-result v7

    const/high16 v8, 0x41900000    # 18.0f

    invoke-static {v8}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v8

    const/4 v12, 0x1

    invoke-static {v7, v12, v8}, Lorg/telegram/ui/ActionBar/Theme;->createSelectorDrawable(III)Landroid/graphics/drawable/Drawable;

    move-result-object v7

    invoke-virtual {v2, v7}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V

    const/high16 v20, 0x41800000    # 16.0f

    const/high16 v21, 0x3f800000    # 1.0f

    const/16 v15, 0x30

    const/high16 v16, 0x42580000    # 54.0f

    const/16 v17, 0x35

    const/high16 v18, 0x3f800000    # 1.0f

    .line 1120
    invoke-static/range {v15 .. v21}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IFIFFFF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v7

    invoke-virtual {v0, v2, v7}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 1121
    sget v7, Lorg/telegram/messenger/R$drawable;->msg_copy:I

    sget v8, Lorg/telegram/messenger/R$string;->Copy:I

    invoke-static {v8}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v2, v12, v7, v8}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->addSubItem(IILjava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;

    .line 1122
    sget v7, Lorg/telegram/messenger/R$drawable;->msg_retry:I

    sget v8, Lorg/telegram/messenger/R$string;->Retry:I

    invoke-static {v8}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v2, v5, v7, v8}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->addSubItem(IILjava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;

    .line 1123
    sget v5, Lorg/telegram/messenger/R$drawable;->msg_edit:I

    sget v7, Lorg/telegram/messenger/R$string;->Edit:I

    invoke-static {v7}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v7

    const/4 v8, 0x3

    invoke-virtual {v2, v8, v5, v7}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->addSubItem(IILjava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;

    .line 1124
    sget v5, Lorg/telegram/messenger/R$drawable;->msg_discuss:I

    sget v7, Lorg/telegram/messenger/R$string;->AIAskMore:I

    invoke-static {v7}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v7

    const/4 v15, 0x4

    invoke-virtual {v2, v15, v5, v7}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->addSubItem(IILjava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;

    .line 1125
    invoke-virtual {v2, v15, v4}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->setSubItemShown(IZ)V

    .line 1126
    invoke-virtual {v2, v4}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->setShowedFromBottom(Z)V

    .line 1127
    new-instance v5, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView$$ExternalSyntheticLambda3;

    invoke-direct {v5, v0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView$$ExternalSyntheticLambda3;-><init>(Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;)V

    invoke-virtual {v2, v5}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 1128
    new-instance v5, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView$$ExternalSyntheticLambda4;

    invoke-direct {v5, v0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView$$ExternalSyntheticLambda4;-><init>(Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;)V

    invoke-virtual {v2, v5}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->setDelegate(Lorg/telegram/ui/ActionBar/ActionBarMenuItem$ActionBarMenuItemDelegate;)V

    .line 1154
    sget v5, Lorg/telegram/messenger/R$string;->AccDescrMoreOptions:I

    invoke-static {v5}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v2, v5}, Landroid/view/View;->setContentDescription(Ljava/lang/CharSequence;)V

    .line 1156
    new-instance v5, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView$1;

    invoke-direct {v5, v0, v3, v1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView$1;-><init>(Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;Landroid/content/Context;Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)V

    iput-object v5, v0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->titleTextView:Landroid/widget/TextView;

    .line 1165
    invoke-static {v1, v10}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->access$1200(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;I)I

    move-result v7

    invoke-virtual {v5, v7}, Landroid/widget/TextView;->setTextColor(I)V

    const/high16 v7, 0x41a00000    # 20.0f

    .line 1166
    invoke-virtual {v5, v12, v7}, Landroid/widget/TextView;->setTextSize(IF)V

    .line 1167
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->bold()Landroid/graphics/Typeface;

    move-result-object v7

    invoke-virtual {v5, v7}, Landroid/widget/TextView;->setTypeface(Landroid/graphics/Typeface;)V

    .line 1168
    invoke-virtual {v5, v12}, Landroid/widget/TextView;->setSingleLine(Z)V

    .line 1169
    sget-object v7, Landroid/text/TextUtils$TruncateAt;->END:Landroid/text/TextUtils$TruncateAt;

    invoke-virtual {v5, v7}, Landroid/widget/TextView;->setEllipsize(Landroid/text/TextUtils$TruncateAt;)V

    .line 1170
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getSelectedRole()Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v5, v7}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 1171
    invoke-virtual {v5, v4}, Landroid/widget/TextView;->setIncludeFontPadding(Z)V

    .line 1172
    sget-boolean v7, Lorg/telegram/messenger/LocaleController;->isRTL:Z

    const/4 v12, 0x5

    if-eqz v7, :cond_0

    move v7, v12

    goto :goto_0

    :cond_0
    move v7, v8

    :goto_0
    invoke-virtual {v5, v7}, Landroid/widget/TextView;->setGravity(I)V

    .line 1173
    sget-object v7, Lorg/telegram/messenger/AndroidUtilities;->displaySize:Landroid/graphics/Point;

    iget v7, v7, Landroid/graphics/Point;->x:I

    const/high16 v15, 0x41b00000    # 22.0f

    invoke-static {v15}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v16

    sub-int v7, v7, v16

    const/high16 v16, 0x42e00000    # 112.0f

    invoke-static/range {v16 .. v16}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v16

    sub-int v7, v7, v16

    const/high16 v16, 0x40000000    # 2.0f

    invoke-static/range {v16 .. v16}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v17

    sub-int v7, v7, v17

    invoke-virtual {v5, v7}, Landroid/widget/TextView;->setMaxWidth(I)V

    .line 1174
    invoke-virtual {v0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v7

    sget v8, Lorg/telegram/messenger/R$drawable;->ic_arrow_drop_down:I

    invoke-static {v7, v8}, Landroidx/core/content/ContextCompat;->getDrawable(Landroid/content/Context;I)Landroid/graphics/drawable/Drawable;

    move-result-object v7

    if-eqz v7, :cond_2

    .line 1176
    invoke-virtual {v7}, Landroid/graphics/drawable/Drawable;->mutate()Landroid/graphics/drawable/Drawable;

    move-result-object v7

    const/high16 v18, 0x3f800000    # 1.0f

    .line 1177
    new-instance v8, Landroid/graphics/PorterDuffColorFilter;

    invoke-static {v1, v10}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->access$1300(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;I)I

    move-result v10

    invoke-direct {v8, v10, v11}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {v7, v8}, Landroid/graphics/drawable/Drawable;->setColorFilter(Landroid/graphics/ColorFilter;)V

    .line 1178
    invoke-static/range {v18 .. v18}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v8

    neg-int v8, v8

    invoke-static {v15}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v10

    const/high16 v11, 0x41a80000    # 21.0f

    invoke-static {v11}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v11

    invoke-virtual {v7, v4, v8, v10, v11}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 1179
    sget-boolean v8, Lorg/telegram/messenger/LocaleController;->isRTL:Z

    const/4 v10, 0x0

    if-eqz v8, :cond_1

    .line 1180
    invoke-virtual {v5, v7, v10, v10, v10}, Landroid/widget/TextView;->setCompoundDrawables(Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;)V

    goto :goto_1

    .line 1182
    :cond_1
    invoke-virtual {v5, v10, v10, v7, v10}, Landroid/widget/TextView;->setCompoundDrawables(Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;)V

    goto :goto_1

    :cond_2
    const/high16 v18, 0x3f800000    # 1.0f

    .line 1185
    :goto_1
    invoke-static/range {v16 .. v16}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v7

    invoke-virtual {v5, v7}, Landroid/widget/TextView;->setCompoundDrawablePadding(I)V

    .line 1186
    invoke-virtual {v5, v4, v4, v4, v4}, Landroid/widget/TextView;->setPadding(IIII)V

    .line 1187
    new-instance v4, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView$$ExternalSyntheticLambda5;

    invoke-direct {v4, v0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView$$ExternalSyntheticLambda5;-><init>(Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;)V

    invoke-virtual {v5, v4}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 1188
    invoke-virtual {v5, v13}, Landroid/view/View;->setPivotX(F)V

    .line 1189
    invoke-virtual {v5, v13}, Landroid/view/View;->setPivotY(F)V

    .line 1190
    sget-boolean v4, Lorg/telegram/messenger/LocaleController;->isRTL:Z

    if-eqz v4, :cond_3

    move v8, v12

    goto :goto_2

    :cond_3
    const/4 v8, 0x3

    :goto_2
    or-int/lit8 v21, v8, 0x30

    const/high16 v24, 0x42e00000    # 112.0f

    const/16 v25, 0x0

    const/16 v19, -0x2

    const/high16 v20, -0x40000000    # -2.0f

    const/high16 v22, 0x41b00000    # 22.0f

    const/high16 v23, 0x41a00000    # 20.0f

    invoke-static/range {v19 .. v25}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IFIFFFF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v4

    invoke-virtual {v0, v5, v4}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 1192
    new-instance v4, Landroid/widget/LinearLayout;

    invoke-direct {v4, v3}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    iput-object v4, v0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->subtitleView:Landroid/widget/LinearLayout;

    .line 1193
    invoke-virtual {v4, v13}, Landroid/view/View;->setPivotX(F)V

    .line 1194
    invoke-virtual {v4, v13}, Landroid/view/View;->setPivotY(F)V

    .line 1196
    new-instance v5, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView$2;

    invoke-direct {v5, v0, v3, v1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView$2;-><init>(Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;Landroid/content/Context;Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)V

    iput-object v5, v0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->modelSelector:Lorg/telegram/ui/Components/AnimatedTextView;

    const-wide/16 v23, 0x15e

    .line 1235
    sget-object v25, Lorg/telegram/ui/Components/CubicBezierInterpolator;->EASE_OUT_QUINT:Lorg/telegram/ui/Components/CubicBezierInterpolator;

    const/high16 v20, 0x3e800000    # 0.25f

    const-wide/16 v21, 0x0

    move-object/from16 v19, v5

    invoke-virtual/range {v19 .. v25}, Lorg/telegram/ui/Components/AnimatedTextView;->setAnimationProperties(FJJLandroid/animation/TimeInterpolator;)V

    .line 1236
    invoke-static {v1, v6}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->access$1700(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;I)I

    move-result v6

    invoke-virtual {v5, v6}, Lorg/telegram/ui/Components/AnimatedTextView;->setTextColor(I)V

    const/high16 v6, 0x41600000    # 14.0f

    .line 1237
    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    int-to-float v6, v6

    invoke-virtual {v5, v6}, Lorg/telegram/ui/Components/AnimatedTextView;->setTextSize(F)V

    .line 1238
    invoke-static {}, Lcom/exteragram/messenger/ai/AiController;->getInstance()Lcom/exteragram/messenger/ai/AiController;

    move-result-object v6

    invoke-virtual {v6}, Lcom/exteragram/messenger/ai/AiController;->getSelected()Lcom/exteragram/messenger/ai/data/Service;

    move-result-object v6

    invoke-virtual {v6}, Lcom/exteragram/messenger/ai/data/Service;->getShortModel()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v6}, Lorg/telegram/ui/Components/AnimatedTextView;->setText(Ljava/lang/CharSequence;)V

    const/high16 v6, 0x40800000    # 4.0f

    .line 1239
    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v7

    invoke-static/range {v16 .. v16}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v8

    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    invoke-static/range {v16 .. v16}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v10

    invoke-virtual {v5, v7, v8, v6, v10}, Landroid/view/View;->setPadding(IIII)V

    .line 1240
    new-instance v6, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView$$ExternalSyntheticLambda6;

    invoke-direct {v6, v0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView$$ExternalSyntheticLambda6;-><init>(Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;)V

    invoke-virtual {v5, v6}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    const/16 v24, 0x3

    const/16 v25, 0x0

    const/16 v19, -0x2

    const/16 v20, -0x2

    const/16 v21, 0x10

    const/16 v22, 0x0

    const/16 v23, 0x0

    .line 1242
    invoke-static/range {v19 .. v25}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIIIIII)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v6

    invoke-virtual {v4, v5, v6}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    const/high16 v24, 0x41b00000    # 22.0f

    const/16 v25, 0x0

    const/16 v19, -0x1

    const/high16 v20, -0x40000000    # -2.0f

    const/16 v21, 0x37

    const/high16 v22, 0x41b00000    # 22.0f

    const/high16 v23, 0x422c0000    # 43.0f

    .line 1244
    invoke-static/range {v19 .. v25}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IFIFFFF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v5

    invoke-virtual {v0, v4, v5}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 1246
    new-instance v4, Landroid/view/View;

    invoke-direct {v4, v3}, Landroid/view/View;-><init>(Landroid/content/Context;)V

    iput-object v4, v0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->shadow:Landroid/view/View;

    .line 1247
    sget v3, Lorg/telegram/ui/ActionBar/Theme;->key_divider:I

    invoke-static {v1, v3}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->access$1800(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;I)I

    move-result v1

    invoke-virtual {v4, v1}, Landroid/view/View;->setBackgroundColor(I)V

    .line 1248
    invoke-virtual {v4, v13}, Landroid/view/View;->setAlpha(F)V

    .line 1249
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->getShadowHeight()I

    move-result v1

    int-to-float v1, v1

    invoke-static/range {v18 .. v18}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v3

    div-float v16, v1, v3

    const/16 v20, 0x0

    const/16 v21, 0x0

    const/4 v15, -0x1

    const/16 v17, 0x37

    const/16 v18, 0x0

    const/high16 v19, 0x42600000    # 56.0f

    invoke-static/range {v15 .. v21}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IFIFFFF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v1

    invoke-virtual {v0, v4, v1}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 1251
    invoke-virtual {v9}, Landroid/view/View;->bringToFront()V

    .line 1252
    invoke-virtual {v14}, Landroid/view/View;->bringToFront()V

    .line 1253
    invoke-virtual {v2}, Landroid/view/View;->bringToFront()V

    return-void
.end method

.method private synthetic lambda$new$0(Landroid/view/View;)V
    .locals 0

    .line 1097
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->dismiss()V

    return-void
.end method

.method private synthetic lambda$new$1(Landroid/view/View;)V
    .locals 3

    .line 1108
    iget-object p1, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {p1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$fgetonInsertPress(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)Lorg/telegram/messenger/Utilities$Callback3;

    move-result-object p1

    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {v0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$fgetprompt(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {v1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$mgetInsertResponse(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)Ljava/lang/CharSequence;

    move-result-object v1

    iget-object v2, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {v2}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$fgetcurrentRichMessage(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)Lorg/telegram/tgnet/tl/TL_iv$RichMessage;

    move-result-object v2

    invoke-interface {p1, v0, v1, v2}, Lorg/telegram/messenger/Utilities$Callback3;->run(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V

    .line 1109
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->dismiss()V

    return-void
.end method

.method private synthetic lambda$new$2(Landroid/view/View;)V
    .locals 0

    .line 1127
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->optionsButton:Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->toggleSubMenu()V

    return-void
.end method

.method private synthetic lambda$new$3(Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet$GenerationData;)V
    .locals 2

    .line 1138
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {v0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$mdropLastTurnFromHistory(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)V

    .line 1139
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet$GenerationData;->prompt()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->setPrompt(Ljava/lang/String;)V

    .line 1140
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet$GenerationData;->imagePath()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$fputimagePath(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;Ljava/lang/String;)V

    .line 1141
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet$GenerationData;->useHistory()Z

    move-result p1

    invoke-static {v0, p1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$fputuseHistory(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;Z)V

    .line 1142
    iget-object p1, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {p1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$mshowLoadingView(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)V

    .line 1143
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {p0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$mgenerate(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)V

    return-void
.end method

.method private synthetic lambda$new$4(Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet$GenerationData;)V
    .locals 2

    .line 1146
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet$GenerationData;->prompt()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->setPrompt(Ljava/lang/String;)V

    .line 1147
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$fputimagePath(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;Ljava/lang/String;)V

    .line 1148
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet$GenerationData;->useHistory()Z

    move-result p1

    invoke-static {v0, p1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$fputuseHistory(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;Z)V

    .line 1149
    iget-object p1, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {p1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$mshowLoadingView(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)V

    .line 1150
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {p0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$mgenerate(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)V

    return-void
.end method

.method private synthetic lambda$new$5(I)V
    .locals 10

    const/4 v0, 0x1

    if-eq p1, v0, :cond_3

    const/4 v0, 0x2

    if-eq p1, v0, :cond_2

    const/4 v0, 0x3

    if-eq p1, v0, :cond_1

    const/4 v0, 0x4

    if-eq p1, v0, :cond_0

    goto :goto_0

    .line 1145
    :cond_0
    new-instance p1, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;

    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {v0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$fgetfragment(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)Lorg/telegram/ui/ActionBar/BaseFragment;

    move-result-object v0

    invoke-virtual {p0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v1

    new-instance v2, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView$$ExternalSyntheticLambda8;

    invoke-direct {v2, p0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView$$ExternalSyntheticLambda8;-><init>(Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;)V

    const/4 p0, 0x0

    invoke-direct {p1, v0, v1, v2, p0}, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;-><init>(Lorg/telegram/ui/ActionBar/BaseFragment;Landroid/content/Context;Lorg/telegram/messenger/Utilities$Callback;Z)V

    .line 1151
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;->show()V

    return-void

    .line 1137
    :cond_1
    new-instance v3, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;

    iget-object p1, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {p1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$fgetprompt(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)Ljava/lang/String;

    move-result-object v4

    iget-object p1, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {p1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$fgetimagePath(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)Ljava/lang/String;

    move-result-object v5

    iget-object p1, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {p1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$fgetfragment(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)Lorg/telegram/ui/ActionBar/BaseFragment;

    move-result-object v6

    invoke-virtual {p0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v7

    new-instance v8, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView$$ExternalSyntheticLambda7;

    invoke-direct {v8, p0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView$$ExternalSyntheticLambda7;-><init>(Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;)V

    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {p0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$fgetuseHistory(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)Z

    move-result v9

    invoke-direct/range {v3 .. v9}, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;-><init>(Ljava/lang/String;Ljava/lang/String;Lorg/telegram/ui/ActionBar/BaseFragment;Landroid/content/Context;Lorg/telegram/messenger/Utilities$Callback;Z)V

    .line 1144
    invoke-virtual {v3}, Lcom/exteragram/messenger/ai/ui/GenerateFromMessageBottomSheet;->show()V

    return-void

    .line 1135
    :cond_2
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {p0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$mregenerate(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)V

    return-void

    .line 1131
    :cond_3
    iget-object p1, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {p1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$fgetcurrentResponse(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)Ljava/lang/CharSequence;

    move-result-object v0

    invoke-static {p1, v0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$mgetRawResponse(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object p1

    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->addToClipboard(Ljava/lang/CharSequence;)Z

    move-result p1

    if-eqz p1, :cond_4

    .line 1132
    iget-object p1, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {p1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->access$2800(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)Landroid/view/ViewGroup;

    move-result-object p1

    check-cast p1, Landroid/widget/FrameLayout;

    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {p0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->access$2900(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    move-result-object p0

    invoke-static {p1, p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Landroid/widget/FrameLayout;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    sget p1, Lorg/telegram/messenger/R$string;->TextCopied:I

    invoke-static {p1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/BulletinFactory;->createCopyBulletin(Ljava/lang/String;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    :cond_4
    :goto_0
    return-void
.end method

.method private synthetic lambda$new$6(Landroid/view/View;)V
    .locals 0

    .line 1187
    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->openRoleSelect()V

    return-void
.end method

.method private synthetic lambda$new$7(Landroid/view/View;)V
    .locals 0

    .line 1240
    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->openModelSelect()V

    return-void
.end method

.method private synthetic lambda$openModelSelect$8(Lorg/telegram/ui/Components/ItemOptions;Lcom/exteragram/messenger/ai/data/Service;Landroid/view/View;)V
    .locals 0

    .line 1277
    invoke-virtual {p1}, Lorg/telegram/ui/Components/ItemOptions;->dismiss()V

    .line 1279
    invoke-virtual {p2}, Lcom/exteragram/messenger/ai/data/Service;->isSelected()Z

    move-result p1

    if-eqz p1, :cond_0

    return-void

    .line 1283
    :cond_0
    iget-object p1, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {p1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$mdropLastTurnFromHistory(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)V

    .line 1284
    invoke-static {p2}, Lcom/exteragram/messenger/ai/AiConfig;->setSelectedServices(Lcom/exteragram/messenger/ai/data/Service;)V

    .line 1285
    iget-object p1, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->modelSelector:Lorg/telegram/ui/Components/AnimatedTextView;

    invoke-virtual {p2}, Lcom/exteragram/messenger/ai/data/Service;->getShortModel()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Lorg/telegram/ui/Components/AnimatedTextView;->setText(Ljava/lang/CharSequence;)V

    .line 1286
    iget-object p1, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {p1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$mshowLoadingView(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)V

    .line 1287
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {p0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$mgenerate(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)V

    return-void
.end method

.method private synthetic lambda$openRoleSelect$9(Lcom/exteragram/messenger/ai/data/Role;)V
    .locals 2

    .line 1316
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Role;->isSelected()Z

    move-result v0

    if-eqz v0, :cond_0

    return-void

    .line 1320
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {v0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$mdropLastTurnFromHistory(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)V

    .line 1321
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->titleTextView:Landroid/widget/TextView;

    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Role;->getName()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 1322
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Role;->getName()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Lcom/exteragram/messenger/ai/AiConfig;->setSelectedRole(Ljava/lang/String;)V

    .line 1323
    iget-object p1, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {p1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$mshowLoadingView(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)V

    .line 1324
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {p0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$mgenerate(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)V

    return-void
.end method


# virtual methods
.method public onAttachedToWindow()V
    .locals 0

    .line 1374
    invoke-super {p0}, Landroid/view/View;->onAttachedToWindow()V

    .line 1375
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->ellipsizeSpanAnimator:Lorg/telegram/ui/Components/EllipsizeSpanAnimator;

    if-eqz p0, :cond_0

    .line 1376
    invoke-virtual {p0}, Lorg/telegram/ui/Components/EllipsizeSpanAnimator;->onAttachedToWindow()V

    :cond_0
    return-void
.end method

.method public onDetachedFromWindow()V
    .locals 0

    .line 1382
    invoke-super {p0}, Landroid/view/View;->onDetachedFromWindow()V

    .line 1383
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->ellipsizeSpanAnimator:Lorg/telegram/ui/Components/EllipsizeSpanAnimator;

    if-eqz p0, :cond_0

    .line 1384
    invoke-virtual {p0}, Lorg/telegram/ui/Components/EllipsizeSpanAnimator;->onDetachedFromWindow()V

    :cond_0
    return-void
.end method

.method public onMeasure(II)V
    .locals 1

    .line 1367
    invoke-static {p1}, Landroid/view/View$MeasureSpec;->getSize(I)I

    move-result p1

    const/high16 p2, 0x40000000    # 2.0f

    invoke-static {p1, p2}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result p1

    const/high16 v0, 0x429c0000    # 78.0f

    .line 1368
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    invoke-static {v0, p2}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result p2

    .line 1366
    invoke-super {p0, p1, p2}, Landroid/widget/FrameLayout;->onMeasure(II)V

    return-void
.end method

.method public openModelSelect()V
    .locals 12

    .line 1257
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {v0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$fgetclient(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)Lcom/exteragram/messenger/ai/network/Client;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/ai/network/Client;->isGenerating()Z

    move-result v0

    if-eqz v0, :cond_0

    return-void

    .line 1261
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {v0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->access$1900(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)Landroid/view/ViewGroup;

    move-result-object v1

    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {v0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->access$2000(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    move-result-object v2

    iget-object v3, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->modelSelector:Lorg/telegram/ui/Components/AnimatedTextView;

    const/4 v5, 0x0

    const/4 v6, 0x1

    const/4 v4, 0x0

    invoke-static/range {v1 .. v6}, Lorg/telegram/ui/Components/ItemOptions;->makeOptions(Landroid/view/ViewGroup;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Landroid/view/View;ZZZ)Lorg/telegram/ui/Components/ItemOptions;

    move-result-object v0

    const/4 v1, 0x0

    .line 1262
    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/ItemOptions;->setDrawScrim(Z)Lorg/telegram/ui/Components/ItemOptions;

    move-result-object v0

    .line 1263
    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/ItemOptions;->setDimAlpha(I)Lorg/telegram/ui/Components/ItemOptions;

    move-result-object v0

    const/high16 v2, 0x43a80000    # 336.0f

    .line 1264
    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    invoke-virtual {v0, v2}, Lorg/telegram/ui/Components/ItemOptions;->setMaxHeight(I)Lorg/telegram/ui/Components/ItemOptions;

    move-result-object v0

    .line 1265
    sget-boolean v2, Lorg/telegram/messenger/LocaleController;->isRTL:Z

    if-eqz v2, :cond_1

    const/4 v2, 0x5

    goto :goto_0

    :cond_1
    const/4 v2, 0x3

    :goto_0
    invoke-virtual {v0, v2}, Lorg/telegram/ui/Components/ItemOptions;->setGravity(I)Lorg/telegram/ui/Components/ItemOptions;

    move-result-object v0

    .line 1266
    sget-boolean v2, Lorg/telegram/messenger/LocaleController;->isRTL:Z

    const/high16 v3, 0x41000000    # 8.0f

    if-eqz v2, :cond_2

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    :goto_1
    int-to-float v2, v2

    goto :goto_2

    :cond_2
    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    neg-int v2, v2

    goto :goto_1

    :goto_2
    const/4 v3, 0x0

    invoke-virtual {v0, v2, v3}, Lorg/telegram/ui/Components/ItemOptions;->translate(FF)Lorg/telegram/ui/Components/ItemOptions;

    move-result-object v0

    .line 1268
    iget-object v2, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {v2}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$mavailableServices(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)Ljava/util/ArrayList;

    move-result-object v2

    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result v3

    move v4, v1

    :goto_3
    if-ge v4, v3, :cond_5

    invoke-virtual {v2, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v5

    add-int/lit8 v4, v4, 0x1

    check-cast v5, Lcom/exteragram/messenger/ai/data/Service;

    .line 1269
    new-instance v6, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;

    invoke-virtual {p0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v7

    iget-object v8, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {v8}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->access$2100(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    move-result-object v11

    const/4 v8, 0x2

    const/4 v9, 0x0

    const/4 v10, 0x0

    invoke-direct/range {v6 .. v11}, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;-><init>(Landroid/content/Context;IZZLorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 1270
    invoke-virtual {v5}, Lcom/exteragram/messenger/ai/data/Service;->getModel()Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v6, v7}, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->setText(Ljava/lang/CharSequence;)V

    .line 1271
    invoke-virtual {v5}, Lcom/exteragram/messenger/ai/data/Service;->isOnDevice()Z

    move-result v7

    if-eqz v7, :cond_3

    sget v7, Lorg/telegram/messenger/R$string;->AIOnDevice:I

    invoke-static {v7}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v7

    goto :goto_4

    :cond_3
    invoke-virtual {v5}, Lcom/exteragram/messenger/ai/data/Service;->getUrl()Ljava/lang/String;

    move-result-object v7

    :goto_4
    invoke-virtual {v6, v7}, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->setSubtext(Ljava/lang/CharSequence;)V

    .line 1272
    iget-object v7, v6, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->subtextView:Landroid/widget/TextView;

    invoke-virtual {v5}, Lcom/exteragram/messenger/ai/data/Service;->isSelected()Z

    move-result v8

    if-eqz v8, :cond_4

    const/high16 v8, 0x42080000    # 34.0f

    invoke-static {v8}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v8

    goto :goto_5

    :cond_4
    move v8, v1

    :goto_5
    invoke-virtual {v7, v1, v1, v8, v1}, Landroid/widget/TextView;->setPadding(IIII)V

    const/high16 v7, 0x43440000    # 196.0f

    .line 1273
    invoke-static {v7}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v7

    invoke-virtual {v6, v7}, Landroid/view/View;->setMinimumWidth(I)V

    const/16 v7, 0x38

    .line 1274
    invoke-virtual {v6, v7}, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->setItemHeight(I)V

    .line 1275
    invoke-virtual {v5}, Lcom/exteragram/messenger/ai/data/Service;->isSelected()Z

    move-result v7

    invoke-virtual {v6, v7}, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->setChecked(Z)V

    .line 1276
    new-instance v7, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView$$ExternalSyntheticLambda0;

    invoke-direct {v7, p0, v0, v5}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;Lorg/telegram/ui/Components/ItemOptions;Lcom/exteragram/messenger/ai/data/Service;)V

    invoke-virtual {v6, v7}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 1289
    invoke-virtual {v0, v6}, Lorg/telegram/ui/Components/ItemOptions;->add(Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;)Lorg/telegram/ui/Components/ItemOptions;

    goto :goto_3

    .line 1292
    :cond_5
    invoke-virtual {v0}, Lorg/telegram/ui/Components/ItemOptions;->show()Lorg/telegram/ui/Components/ItemOptions;

    return-void
.end method

.method public openRoleSelect()V
    .locals 8

    .line 1296
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {v0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$fgetclient(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)Lcom/exteragram/messenger/ai/network/Client;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/ai/network/Client;->isGenerating()Z

    move-result v0

    if-eqz v0, :cond_0

    return-void

    .line 1300
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {v0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->access$2200(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)Landroid/view/ViewGroup;

    move-result-object v0

    iget-object v1, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {v1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->access$2300(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    move-result-object v1

    iget-object v2, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->titleTextView:Landroid/widget/TextView;

    invoke-static {v0, v1, v2}, Lorg/telegram/ui/Components/ItemOptions;->makeOptions(Landroid/view/ViewGroup;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Landroid/view/View;)Lorg/telegram/ui/Components/ItemOptions;

    move-result-object v0

    const/4 v1, 0x0

    .line 1301
    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/ItemOptions;->setDrawScrim(Z)Lorg/telegram/ui/Components/ItemOptions;

    move-result-object v0

    .line 1302
    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/ItemOptions;->setDimAlpha(I)Lorg/telegram/ui/Components/ItemOptions;

    move-result-object v0

    .line 1303
    sget-boolean v2, Lorg/telegram/messenger/LocaleController;->isRTL:Z

    if-eqz v2, :cond_1

    const/4 v2, 0x5

    goto :goto_0

    :cond_1
    const/4 v2, 0x3

    :goto_0
    invoke-virtual {v0, v2}, Lorg/telegram/ui/Components/ItemOptions;->setGravity(I)Lorg/telegram/ui/Components/ItemOptions;

    move-result-object v0

    .line 1304
    sget-boolean v2, Lorg/telegram/messenger/LocaleController;->isRTL:Z

    const/high16 v3, 0x41000000    # 8.0f

    if-eqz v2, :cond_2

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    :goto_1
    int-to-float v2, v2

    goto :goto_2

    :cond_2
    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    neg-int v2, v2

    goto :goto_1

    :goto_2
    const/4 v3, 0x0

    invoke-virtual {v0, v2, v3}, Lorg/telegram/ui/Components/ItemOptions;->translate(FF)Lorg/telegram/ui/Components/ItemOptions;

    move-result-object v0

    .line 1306
    new-instance v2, Ljava/util/ArrayList;

    invoke-static {}, Lcom/exteragram/messenger/ai/AiController;->getInstance()Lcom/exteragram/messenger/ai/AiController;

    move-result-object v3

    invoke-virtual {v3}, Lcom/exteragram/messenger/ai/AiController;->getSuggestedRoles()Ljava/util/List;

    move-result-object v3

    invoke-direct {v2, v3}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    .line 1307
    invoke-static {}, Lcom/exteragram/messenger/ai/AiController;->getInstance()Lcom/exteragram/messenger/ai/AiController;

    move-result-object v3

    invoke-virtual {v3}, Lcom/exteragram/messenger/ai/AiController;->getRoles()Ljava/util/List;

    move-result-object v3

    invoke-interface {v3}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v3

    :cond_3
    :goto_3
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_4

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/exteragram/messenger/ai/data/Role;

    .line 1308
    invoke-virtual {v2, v4}, Ljava/util/ArrayList;->contains(Ljava/lang/Object;)Z

    move-result v5

    if-nez v5, :cond_3

    .line 1309
    invoke-virtual {v2, v4}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_3

    .line 1313
    :cond_4
    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result v3

    :goto_4
    if-ge v1, v3, :cond_5

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    add-int/lit8 v1, v1, 0x1

    check-cast v4, Lcom/exteragram/messenger/ai/data/Role;

    .line 1315
    invoke-virtual {v4}, Lcom/exteragram/messenger/ai/data/Role;->isSelected()Z

    move-result v5

    invoke-virtual {v4}, Lcom/exteragram/messenger/ai/data/Role;->getName()Ljava/lang/String;

    move-result-object v6

    new-instance v7, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView$$ExternalSyntheticLambda9;

    invoke-direct {v7, p0, v4}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView$$ExternalSyntheticLambda9;-><init>(Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;Lcom/exteragram/messenger/ai/data/Role;)V

    invoke-virtual {v0, v5, v6, v7}, Lorg/telegram/ui/Components/ItemOptions;->addChecked(ZLjava/lang/CharSequence;Ljava/lang/Runnable;)Lorg/telegram/ui/Components/ItemOptions;

    goto :goto_4

    .line 1328
    :cond_5
    invoke-virtual {v0}, Lorg/telegram/ui/Components/ItemOptions;->show()Lorg/telegram/ui/Components/ItemOptions;

    return-void
.end method

.method public setTranslationY(F)V
    .locals 10

    .line 1333
    invoke-super {p0, p1}, Landroid/view/View;->setTranslationY(F)V

    .line 1335
    sget v0, Lorg/telegram/messenger/AndroidUtilities;->statusBarHeight:I

    int-to-float v0, v0

    sub-float/2addr p1, v0

    const/high16 v0, 0x42800000    # 64.0f

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    int-to-float v0, v0

    div-float/2addr p1, v0

    const/4 v0, 0x0

    const/high16 v1, 0x3f800000    # 1.0f

    invoke-static {p1, v0, v1}, Landroidx/core/math/MathUtils;->clamp(FFF)F

    move-result p1

    .line 1336
    iget-object v2, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {v2}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$mhasEnoughHeight(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)Z

    move-result v2

    if-nez v2, :cond_0

    move p1, v1

    .line 1339
    :cond_0
    sget-object v2, Lorg/telegram/ui/Components/CubicBezierInterpolator;->EASE_OUT:Lorg/telegram/ui/Components/CubicBezierInterpolator;

    invoke-virtual {v2, p1}, Lorg/telegram/ui/Components/CubicBezierInterpolator;->getInterpolation(F)F

    move-result p1

    .line 1341
    iget-object v2, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->titleTextView:Landroid/widget/TextView;

    const v3, 0x3f59999a    # 0.85f

    invoke-static {v3, v1, p1}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v4

    invoke-virtual {v2, v4}, Landroid/view/View;->setScaleX(F)V

    .line 1342
    iget-object v2, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->titleTextView:Landroid/widget/TextView;

    invoke-static {v3, v1, p1}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v3

    invoke-virtual {v2, v3}, Landroid/view/View;->setScaleY(F)V

    .line 1343
    iget-object v2, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->titleTextView:Landroid/widget/TextView;

    const/high16 v3, -0x3ec00000    # -12.0f

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v3

    invoke-static {v3, v0, p1}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v3

    invoke-virtual {v2, v3}, Landroid/view/View;->setTranslationY(F)V

    .line 1344
    iget-object v2, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->titleTextView:Landroid/widget/TextView;

    const/high16 v3, 0x42480000    # 50.0f

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v4

    invoke-static {v4, v0, p1}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v4

    invoke-virtual {v2, v4}, Landroid/view/View;->setTranslationX(F)V

    .line 1345
    iget-object v2, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->subtitleView:Landroid/widget/LinearLayout;

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v3

    invoke-static {v3, v0, p1}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v3

    invoke-virtual {v2, v3}, Landroid/view/View;->setTranslationX(F)V

    .line 1347
    iget-object v2, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->subtitleView:Landroid/widget/LinearLayout;

    const/high16 v3, -0x3e500000    # -22.0f

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v3

    invoke-static {v3, v0, p1}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v3

    invoke-virtual {v2, v3}, Landroid/view/View;->setTranslationY(F)V

    .line 1349
    iget-object v2, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->backButton:Landroid/widget/ImageView;

    const/high16 v3, -0x3e380000    # -25.0f

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v3

    invoke-static {v0, v3, p1}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v3

    invoke-virtual {v2, v3}, Landroid/view/View;->setTranslationX(F)V

    .line 1350
    iget-object v2, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->backButton:Landroid/widget/ImageView;

    sub-float/2addr v1, p1

    invoke-virtual {v2, v1}, Landroid/view/View;->setAlpha(F)V

    .line 1352
    iget-object v2, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->insertButton:Landroid/widget/ImageView;

    const/high16 v3, 0x41600000    # 14.0f

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v4

    const/high16 v5, 0x41000000    # 8.0f

    invoke-static {v5}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v6

    invoke-static {v4, v6, p1}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v4

    invoke-virtual {v2, v4}, Landroid/view/View;->setTranslationX(F)V

    .line 1353
    iget-object v2, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->insertButton:Landroid/widget/ImageView;

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v4

    const/high16 v6, 0x41800000    # 16.0f

    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v7

    invoke-static {v4, v7, p1}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v4

    invoke-virtual {v2, v4}, Landroid/view/View;->setTranslationY(F)V

    .line 1354
    iget-object v2, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->insertButton:Landroid/widget/ImageView;

    iget-object v4, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    sget v7, Lorg/telegram/ui/ActionBar/Theme;->key_dialogTextBlack:I

    invoke-static {v4, v7}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->access$2400(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;I)I

    move-result v4

    iget-object v8, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    sget v9, Lorg/telegram/ui/ActionBar/Theme;->key_player_actionBarSubtitle:I

    invoke-static {v8, v9}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->access$2500(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;I)I

    move-result v8

    invoke-static {v4, v8, p1}, Landroidx/core/graphics/ColorUtils;->blendARGB(IIF)I

    move-result v4

    sget-object v8, Landroid/graphics/PorterDuff$Mode;->MULTIPLY:Landroid/graphics/PorterDuff$Mode;

    invoke-virtual {v2, v4, v8}, Landroid/widget/ImageView;->setColorFilter(ILandroid/graphics/PorterDuff$Mode;)V

    .line 1356
    iget-object v2, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->optionsButton:Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v3

    invoke-static {v5}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v4

    invoke-static {v3, v4, p1}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v3

    invoke-virtual {v2, v3}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->setTranslationX(F)V

    .line 1357
    iget-object v2, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->optionsButton:Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v3

    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v4

    invoke-static {v3, v4, p1}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v3

    invoke-virtual {v2, v3}, Landroid/view/View;->setTranslationY(F)V

    .line 1358
    iget-object v2, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->optionsButton:Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    iget-object v3, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {v3, v7}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->access$2600(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;I)I

    move-result v3

    iget-object v4, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {v4, v9}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->access$2700(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;I)I

    move-result v4

    invoke-static {v3, v4, p1}, Landroidx/core/graphics/ColorUtils;->blendARGB(IIF)I

    move-result v3

    invoke-virtual {v2, v3}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->setIconColor(I)V

    .line 1360
    iget-object v2, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->shadow:Landroid/view/View;

    const/high16 v3, 0x41b00000    # 22.0f

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v3

    invoke-static {v0, v3, p1}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result p1

    invoke-virtual {v2, p1}, Landroid/view/View;->setTranslationY(F)V

    .line 1361
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->shadow:Landroid/view/View;

    invoke-virtual {p0, v1}, Landroid/view/View;->setAlpha(F)V

    return-void
.end method

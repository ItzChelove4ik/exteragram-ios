.class public final Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet;
.super Lorg/telegram/ui/ActionBar/BottomSheet;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet$InstallDelegate;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0002\u0018\u00002\u00020\u0001:\u0001\u000cB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0004\u0008\u0008\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"
    }
    d2 = {
        "Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet;",
        "Lorg/telegram/ui/ActionBar/BottomSheet;",
        "context",
        "Landroid/content/Context;",
        "iconPack",
        "Lcom/exteragram/messenger/icons/IconPack;",
        "installDelegate",
        "Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet$InstallDelegate;",
        "<init>",
        "(Landroid/content/Context;Lcom/exteragram/messenger/icons/IconPack;Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet$InstallDelegate;)V",
        "createView",
        "Landroid/view/View;",
        "InstallDelegate",
        "TMessagesProj"
    }
    k = 0x1
    mv = {
        0x2,
        0x2,
        0x0
    }
    xi = 0x30
.end annotation


# instance fields
.field private final iconPack:Lcom/exteragram/messenger/icons/IconPack;

.field private final installDelegate:Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet$InstallDelegate;


# direct methods
.method public static $r8$lambda$16Mca43Svex8CFfxTTCwGRlDHWU(Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet;Lcom/exteragram/messenger/components/CheckBoxRow;ZLandroid/view/View;)V
    .locals 0

    .line 184
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BottomSheet;->dismiss()V

    .line 185
    new-instance p3, Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet$$ExternalSyntheticLambda5;

    invoke-direct {p3, p0, p1, p2}, Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet$$ExternalSyntheticLambda5;-><init>(Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet;Lcom/exteragram/messenger/components/CheckBoxRow;Z)V

    const-wide/16 p0, 0xc8

    invoke-static {p3, p0, p1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    return-void
.end method

.method public static $r8$lambda$DgxMV-L65kbfZyL8piBwuZ2k21o(Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet;Lcom/exteragram/messenger/components/CheckBoxRow;Z)V
    .locals 2

    .line 186
    iget-object p0, p0, Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet;->installDelegate:Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet$InstallDelegate;

    const/4 v0, 0x0

    if-eqz p1, :cond_0

    invoke-virtual {p1}, Lcom/exteragram/messenger/components/CheckBoxRow;->isChecked()Z

    move-result p1

    const/4 v1, 0x1

    if-ne p1, v1, :cond_0

    move v0, v1

    :cond_0
    invoke-interface {p0, v0, p2}, Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet$InstallDelegate;->onInstall(ZZ)V

    return-void
.end method

.method public static $r8$lambda$ZgxHQQ4XbVm18tEb0C1btImd-l0(Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet;)V
    .locals 3

    .line 43
    new-instance v0, Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet$$ExternalSyntheticLambda1;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet$$ExternalSyntheticLambda1;-><init>(Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet;)V

    const-wide/16 v1, 0x3e8

    invoke-static {v0, v1, v2}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    return-void
.end method

.method public static $r8$lambda$lYsEcB3VdRolPxkK_mP_jzm3nus(Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet;)V
    .locals 0

    .line 45
    iget-object p0, p0, Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet;->iconPack:Lcom/exteragram/messenger/icons/IconPack;

    invoke-virtual {p0}, Lcom/exteragram/messenger/icons/IconPack;->getLocation()Ljava/io/File;

    move-result-object p0

    if-eqz p0, :cond_0

    invoke-static {p0}, Lkotlin/io/FilesKt;->deleteRecursively(Ljava/io/File;)Z

    :cond_0
    return-void
.end method

.method public static $r8$lambda$sCAZGxaJOeaMSR2OcFHWcAF6CWM(Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet;)V
    .locals 2

    .line 44
    sget-object v0, Lorg/telegram/messenger/Utilities;->globalQueue:Lorg/telegram/messenger/DispatchQueue;

    new-instance v1, Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet$$ExternalSyntheticLambda4;

    invoke-direct {v1, p0}, Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet$$ExternalSyntheticLambda4;-><init>(Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet;)V

    invoke-virtual {v0, v1}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Lcom/exteragram/messenger/icons/IconPack;Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet$InstallDelegate;)V
    .locals 1

    const/4 v0, 0x0

    .line 33
    invoke-direct {p0, p1, v0}, Lorg/telegram/ui/ActionBar/BottomSheet;-><init>(Landroid/content/Context;Z)V

    .line 31
    iput-object p2, p0, Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet;->iconPack:Lcom/exteragram/messenger/icons/IconPack;

    .line 32
    iput-object p3, p0, Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet;->installDelegate:Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet$InstallDelegate;

    .line 40
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BottomSheet;->fixNavigationBar()V

    .line 41
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet;->createView(Landroid/content/Context;)Landroid/view/View;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/BottomSheet;->setCustomView(Landroid/view/View;)V

    .line 42
    new-instance p1, Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet$$ExternalSyntheticLambda0;

    invoke-direct {p1, p0}, Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet;)V

    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/BottomSheet;->setOnDismissListener(Ljava/lang/Runnable;)V

    return-void
.end method

.method private final createView(Landroid/content/Context;)Landroid/view/View;
    .locals 14

    .line 52
    new-instance v0, Landroid/widget/LinearLayout;

    invoke-direct {v0, p1}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    const/4 v1, 0x1

    .line 53
    invoke-virtual {v0, v1}, Landroid/widget/LinearLayout;->setOrientation(I)V

    const/high16 v2, 0x41800000    # 16.0f

    .line 54
    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    const/4 v3, 0x0

    invoke-virtual {v0, v3, v2, v3, v3}, Landroid/view/View;->setPadding(IIII)V

    .line 56
    new-instance v2, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;

    invoke-direct {v2, p1}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;-><init>(Landroid/content/Context;)V

    .line 57
    invoke-virtual {v2, v1}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->setCircularMode(Z)V

    const/16 v4, 0xbb8

    .line 58
    invoke-virtual {v2, v4}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->setRefreshTime(I)V

    .line 59
    iget-object v4, p0, Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet;->iconPack:Lcom/exteragram/messenger/icons/IconPack;

    invoke-virtual {v2, v4}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->setIconPack(Lcom/exteragram/messenger/icons/IconPack;)V

    const/4 v10, 0x0

    const/16 v11, 0x10

    const/4 v5, -0x1

    const/4 v6, -0x2

    const/4 v7, 0x1

    const/4 v8, 0x0

    const/4 v9, 0x0

    .line 63
    invoke-static/range {v5 .. v11}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIIIIII)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v4

    .line 61
    invoke-virtual {v0, v2, v4}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 74
    new-instance v2, Landroid/widget/TextView;

    invoke-direct {v2, p1}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    const/high16 v4, 0x41a00000    # 20.0f

    .line 75
    invoke-virtual {v2, v1, v4}, Landroid/widget/TextView;->setTextSize(IF)V

    .line 76
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->bold()Landroid/graphics/Typeface;

    move-result-object v4

    invoke-virtual {v2, v4}, Landroid/widget/TextView;->setTypeface(Landroid/graphics/Typeface;)V

    const/16 v4, 0x11

    .line 77
    invoke-virtual {v2, v4}, Landroid/widget/TextView;->setGravity(I)V

    .line 78
    sget v4, Lorg/telegram/ui/ActionBar/Theme;->key_dialogTextBlack:I

    invoke-static {v4}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I)I

    move-result v4

    invoke-virtual {v2, v4}, Landroid/widget/TextView;->setTextColor(I)V

    .line 79
    iget-object v4, p0, Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet;->iconPack:Lcom/exteragram/messenger/icons/IconPack;

    invoke-virtual {v4}, Lcom/exteragram/messenger/icons/IconPack;->getName()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v2, v4}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    const/high16 v9, 0x41c00000    # 24.0f

    const/4 v10, 0x0

    const/high16 v7, 0x41c00000    # 24.0f

    const/4 v8, 0x0

    .line 82
    invoke-static/range {v5 .. v10}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIFFFF)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v4

    .line 80
    invoke-virtual {v0, v2, v4}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 92
    invoke-static {}, Lorg/telegram/ui/LaunchActivity;->getSafeLastFragment()Lorg/telegram/ui/ActionBar/BaseFragment;

    move-result-object v2

    .line 93
    sget-object v4, Lcom/exteragram/messenger/icons/IconManager;->INSTANCE:Lcom/exteragram/messenger/icons/IconManager;

    iget-object v5, p0, Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet;->iconPack:Lcom/exteragram/messenger/icons/IconPack;

    invoke-virtual {v5}, Lcom/exteragram/messenger/icons/IconPack;->getId()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4, v5}, Lcom/exteragram/messenger/icons/IconManager;->findPackById(Ljava/lang/String;)Lcom/exteragram/messenger/icons/IconPack;

    move-result-object v4

    if-eqz v4, :cond_0

    move v5, v1

    goto :goto_0

    :cond_0
    move v5, v3

    .line 95
    :goto_0
    new-instance v6, Lorg/telegram/ui/Components/EffectsTextView;

    iget-object v7, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-direct {v6, p1, v7}, Lorg/telegram/ui/Components/EffectsTextView;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 96
    invoke-virtual {v6, v1}, Landroid/widget/TextView;->setGravity(I)V

    .line 97
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->regular()Landroid/graphics/Typeface;

    move-result-object v7

    invoke-virtual {v6, v7}, Landroid/widget/TextView;->setTypeface(Landroid/graphics/Typeface;)V

    .line 98
    new-instance v7, Lorg/telegram/messenger/AndroidUtilities$LinkMovementMethodMy;

    invoke-direct {v7}, Lorg/telegram/messenger/AndroidUtilities$LinkMovementMethodMy;-><init>()V

    invoke-virtual {v6, v7}, Landroid/widget/TextView;->setMovementMethod(Landroid/text/method/MovementMethod;)V

    .line 99
    sget v7, Lorg/telegram/ui/ActionBar/Theme;->key_dialogTextLink:I

    invoke-virtual {p0, v7}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v7

    invoke-virtual {v6, v7}, Landroid/widget/TextView;->setLinkTextColor(I)V

    const/high16 v7, 0x41600000    # 14.0f

    .line 100
    invoke-virtual {v6, v1, v7}, Landroid/widget/TextView;->setTextSize(IF)V

    .line 101
    sget v7, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteGrayText:I

    invoke-virtual {p0, v7}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v7

    invoke-virtual {v6, v7}, Lorg/telegram/ui/Components/spoilers/SpoilersTextView;->setTextColor(I)V

    .line 103
    new-instance v7, Landroid/text/SpannableStringBuilder;

    sget v8, Lorg/telegram/messenger/R$string;->PluginVersion:I

    invoke-static {v8}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v8

    invoke-direct {v7, v8}, Landroid/text/SpannableStringBuilder;-><init>(Ljava/lang/CharSequence;)V

    const-string v8, " "

    invoke-virtual {v7, v8}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    move-result-object v7

    .line 104
    invoke-virtual {v7}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v8

    if-eqz v4, :cond_1

    .line 106
    invoke-virtual {v4}, Lcom/exteragram/messenger/icons/IconPack;->getVersion()Ljava/lang/String;

    move-result-object v4

    .line 107
    iget-object v9, p0, Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet;->iconPack:Lcom/exteragram/messenger/icons/IconPack;

    invoke-virtual {v9}, Lcom/exteragram/messenger/icons/IconPack;->getVersion()Ljava/lang/String;

    move-result-object v9

    .line 108
    invoke-virtual {v7, v4}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    move-result-object v10

    const-string v11, " -> "

    invoke-virtual {v10, v11}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    move-result-object v10

    invoke-virtual {v10, v9}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    .line 109
    sget v9, Lorg/telegram/messenger/R$drawable;->msg_mini_arrow_mediathin:I

    invoke-static {p1, v7, v9}, Lcom/exteragram/messenger/utils/text/LocaleUtils;->replaceArrows(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/text/SpannableStringBuilder;

    move-result-object v7

    .line 111
    new-instance v9, Landroid/text/style/StrikethroughSpan;

    invoke-direct {v9}, Landroid/text/style/StrikethroughSpan;-><init>()V

    .line 113
    invoke-virtual {v4}, Ljava/lang/String;->length()I

    move-result v4

    add-int/2addr v4, v8

    const/16 v10, 0x21

    .line 110
    invoke-virtual {v7, v9, v8, v4, v10}, Landroid/text/SpannableStringBuilder;->setSpan(Ljava/lang/Object;III)V

    goto :goto_1

    .line 116
    :cond_1
    iget-object v4, p0, Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet;->iconPack:Lcom/exteragram/messenger/icons/IconPack;

    invoke-virtual {v4}, Lcom/exteragram/messenger/icons/IconPack;->getVersion()Ljava/lang/String;

    move-result-object v4

    invoke-static {v4}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v4

    if-nez v4, :cond_2

    .line 117
    iget-object v4, p0, Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet;->iconPack:Lcom/exteragram/messenger/icons/IconPack;

    invoke-virtual {v4}, Lcom/exteragram/messenger/icons/IconPack;->getVersion()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v7, v4}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    .line 119
    :cond_2
    :goto_1
    iget-object v4, p0, Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet;->iconPack:Lcom/exteragram/messenger/icons/IconPack;

    invoke-virtual {v4}, Lcom/exteragram/messenger/icons/IconPack;->getAuthor()Ljava/lang/String;

    move-result-object v4

    invoke-static {v4}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v4

    if-nez v4, :cond_3

    .line 120
    const-string v4, " \u2022 "

    invoke-virtual {v7, v4}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    move-result-object v4

    .line 123
    iget-object v8, p0, Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet;->iconPack:Lcom/exteragram/messenger/icons/IconPack;

    invoke-virtual {v8}, Lcom/exteragram/messenger/icons/IconPack;->getAuthor()Ljava/lang/String;

    move-result-object v8

    .line 125
    new-instance v9, Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet$$ExternalSyntheticLambda2;

    invoke-direct {v9, p0}, Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet$$ExternalSyntheticLambda2;-><init>(Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet;)V

    .line 122
    invoke-static {v8, v2, v9}, Lcom/exteragram/messenger/utils/text/LocaleUtils;->formatWithUsernames(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/BaseFragment;Ljava/lang/Runnable;)Ljava/lang/CharSequence;

    move-result-object v2

    .line 121
    invoke-virtual {v4, v2}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    .line 129
    :cond_3
    invoke-virtual {v6, v7}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    const/high16 v12, 0x41c00000    # 24.0f

    const/high16 v13, 0x41c00000    # 24.0f

    const/4 v8, -0x1

    const/4 v9, -0x2

    const/high16 v10, 0x41c00000    # 24.0f

    const/high16 v11, 0x40800000    # 4.0f

    .line 132
    invoke-static/range {v8 .. v13}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIFFFF)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v2

    .line 130
    invoke-virtual {v0, v6, v2}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 144
    new-instance v2, Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;

    iget-object v4, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-direct {v2, p1, v1, v4}, Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;-><init>(Landroid/content/Context;ZLorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 145
    invoke-virtual {v2}, Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;->setRound()Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;

    .line 147
    iget-object v4, p0, Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet;->iconPack:Lcom/exteragram/messenger/icons/IconPack;

    invoke-virtual {v4}, Lcom/exteragram/messenger/icons/IconPack;->getIcons()Ljava/util/Map;

    move-result-object v4

    invoke-interface {v4}, Ljava/util/Map;->size()I

    move-result v4

    new-array v6, v3, [Ljava/lang/Object;

    const-string v7, "IconCount"

    invoke-static {v7, v4, v6}, Lorg/telegram/messenger/LocaleController;->formatPluralString(Ljava/lang/String;I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v4

    .line 146
    invoke-virtual {v2, v4, v3}, Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;->setCount(Ljava/lang/String;Z)V

    if-eqz v5, :cond_4

    .line 151
    sget v4, Lorg/telegram/messenger/R$string;->UpdatePack:I

    invoke-static {v4}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v2, v4, v3}, Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;->setText(Ljava/lang/CharSequence;Z)V

    goto :goto_2

    .line 153
    :cond_4
    sget v4, Lorg/telegram/messenger/R$string;->InstallPack:I

    invoke-static {v4}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v2, v4, v3}, Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;->setText(Ljava/lang/CharSequence;Z)V

    :goto_2
    const/16 v11, 0x10

    const/16 v12, 0x10

    const/4 v6, -0x1

    const/16 v7, 0x30

    const/4 v8, 0x0

    const/16 v9, 0x10

    const/4 v10, 0x0

    .line 157
    invoke-static/range {v6 .. v12}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIIIIII)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v3

    .line 155
    invoke-virtual {v0, v2, v3}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 160
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getIconPacksLayout()Ljava/util/ArrayList;

    move-result-object v3

    iget-object v4, p0, Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet;->iconPack:Lcom/exteragram/messenger/icons/IconPack;

    invoke-virtual {v4}, Lcom/exteragram/messenger/icons/IconPack;->getId()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/util/ArrayList;->contains(Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_5

    .line 161
    new-instance v3, Lcom/exteragram/messenger/components/CheckBoxRow;

    .line 163
    sget v4, Lorg/telegram/messenger/R$string;->EnableAfterInstallation:I

    invoke-static {v4}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    .line 165
    iget-object v6, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    .line 161
    invoke-direct {v3, p1, v4, v1, v6}, Lcom/exteragram/messenger/components/CheckBoxRow;-><init>(Landroid/content/Context;Ljava/lang/CharSequence;ZLorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    const/4 v12, 0x0

    const/16 v13, 0x8

    const/4 v7, -0x2

    const/4 v8, -0x2

    const/4 v9, 0x1

    const/4 v10, 0x0

    const/4 v11, 0x0

    .line 169
    invoke-static/range {v7 .. v13}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIIIIII)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object p1

    .line 167
    invoke-virtual {v0, v3, p1}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    goto :goto_3

    :cond_5
    const/4 v3, 0x0

    .line 183
    :goto_3
    new-instance p1, Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet$$ExternalSyntheticLambda3;

    invoke-direct {p1, p0, v3, v5}, Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet$$ExternalSyntheticLambda3;-><init>(Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet;Lcom/exteragram/messenger/components/CheckBoxRow;Z)V

    invoke-virtual {v2, p1}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    return-object v0
.end method

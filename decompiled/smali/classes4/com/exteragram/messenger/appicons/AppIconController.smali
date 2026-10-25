.class public abstract Lcom/exteragram/messenger/appicons/AppIconController;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static allIcons:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/appicons/AppIcon;",
            ">;"
        }
    .end annotation
.end field

.field private static selectedIcon:Lcom/exteragram/messenger/appicons/AppIcon;


# direct methods
.method public static synthetic $r8$lambda$-OlWDDNnew4nPmFUo8i45cwfaNo(Lcom/exteragram/messenger/appicons/AppIcon;)V
    .locals 0

    .line 113
    sput-object p0, Lcom/exteragram/messenger/appicons/AppIconController;->selectedIcon:Lcom/exteragram/messenger/appicons/AppIcon;

    return-void
.end method

.method public static synthetic $r8$lambda$5uIc0UfuYc-xsOt72Pr_KfKeTK8(Ljava/util/List;Lcom/exteragram/messenger/appicons/AppIcon;)V
    .locals 3

    .line 111
    invoke-interface {p0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_2

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/exteragram/messenger/appicons/AppIcon;

    .line 112
    invoke-static {v1}, Lcom/exteragram/messenger/appicons/AppIconController;->isEnabled(Lcom/exteragram/messenger/appicons/AppIcon;)Z

    move-result v2

    if-eqz v2, :cond_0

    .line 113
    new-instance v0, Lcom/exteragram/messenger/appicons/AppIconController$$ExternalSyntheticLambda1;

    invoke-direct {v0, v1}, Lcom/exteragram/messenger/appicons/AppIconController$$ExternalSyntheticLambda1;-><init>(Lcom/exteragram/messenger/appicons/AppIcon;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    .line 114
    invoke-virtual {v1}, Lcom/exteragram/messenger/appicons/AppIcon;->isMonet()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 115
    invoke-static {p1, p0}, Lcom/exteragram/messenger/appicons/AppIconController;->applyIcon(Lcom/exteragram/messenger/appicons/AppIcon;Ljava/util/List;)V

    .line 116
    invoke-static {v1, p0}, Lcom/exteragram/messenger/appicons/AppIconController;->applyIcon(Lcom/exteragram/messenger/appicons/AppIcon;Ljava/util/List;)V

    :cond_1
    return-void

    .line 121
    :cond_2
    new-instance v0, Lcom/exteragram/messenger/appicons/AppIconController$$ExternalSyntheticLambda2;

    invoke-direct {v0, p1}, Lcom/exteragram/messenger/appicons/AppIconController$$ExternalSyntheticLambda2;-><init>(Lcom/exteragram/messenger/appicons/AppIcon;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    .line 122
    invoke-static {p1, p0}, Lcom/exteragram/messenger/appicons/AppIconController;->applyIcon(Lcom/exteragram/messenger/appicons/AppIcon;Ljava/util/List;)V

    return-void
.end method

.method public static synthetic $r8$lambda$W5UL3Z5w24Mq929fjLlgCr0zt7k(Lcom/exteragram/messenger/appicons/AppIcon;)V
    .locals 0

    .line 121
    sput-object p0, Lcom/exteragram/messenger/appicons/AppIconController;->selectedIcon:Lcom/exteragram/messenger/appicons/AppIcon;

    return-void
.end method

.method public static synthetic $r8$lambda$e50DBscmx5OEPuYBDGfVTLU4Vmo(Lcom/exteragram/messenger/appicons/AppIcon;Ljava/util/List;)V
    .locals 0

    .line 90
    invoke-static {p0, p1}, Lcom/exteragram/messenger/appicons/AppIconController;->applyIcon(Lcom/exteragram/messenger/appicons/AppIcon;Ljava/util/List;)V

    return-void
.end method

.method private static applyIcon(Lcom/exteragram/messenger/appicons/AppIcon;Ljava/util/List;)V
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/exteragram/messenger/appicons/AppIcon;",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/appicons/AppIcon;",
            ">;)V"
        }
    .end annotation

    .line 94
    sget-object v0, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    .line 95
    invoke-virtual {v0}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v1

    .line 96
    invoke-virtual {p0, v0}, Lcom/exteragram/messenger/appicons/AppIcon;->getComponentName(Landroid/content/Context;)Landroid/content/ComponentName;

    move-result-object v2

    const/4 v3, 0x1

    invoke-virtual {v1, v2, v3, v3}, Landroid/content/pm/PackageManager;->setComponentEnabledSetting(Landroid/content/ComponentName;II)V

    .line 98
    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_1

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/exteragram/messenger/appicons/AppIcon;

    if-ne v2, p0, :cond_0

    goto :goto_0

    .line 102
    :cond_0
    invoke-virtual {v2, v0}, Lcom/exteragram/messenger/appicons/AppIcon;->getComponentName(Landroid/content/Context;)Landroid/content/ComponentName;

    move-result-object v2

    const/4 v4, 0x2

    invoke-virtual {v1, v2, v4, v3}, Landroid/content/pm/PackageManager;->setComponentEnabledSetting(Landroid/content/ComponentName;II)V

    goto :goto_0

    :cond_1
    return-void
.end method

.method public static findById(Ljava/lang/String;)Lcom/exteragram/messenger/appicons/AppIcon;
    .locals 3

    .line 52
    invoke-static {}, Lcom/exteragram/messenger/appicons/AppIconController;->getAllIcons()Ljava/util/List;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/exteragram/messenger/appicons/AppIcon;

    .line 53
    iget-object v2, v1, Lcom/exteragram/messenger/appicons/AppIcon;->id:Ljava/lang/String;

    invoke-virtual {v2, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_0

    return-object v1

    :cond_1
    const/4 p0, 0x0

    return-object p0
.end method

.method public static fixLauncherIconIfNeeded()V
    .locals 4

    .line 108
    invoke-static {}, Lcom/exteragram/messenger/appicons/AppIconController;->getAllIcons()Ljava/util/List;

    move-result-object v0

    .line 109
    invoke-static {}, Lcom/exteragram/messenger/appicons/AppIconController;->getDefaultIcon()Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v1

    .line 110
    sget-object v2, Lorg/telegram/messenger/Utilities;->globalQueue:Lorg/telegram/messenger/DispatchQueue;

    new-instance v3, Lcom/exteragram/messenger/appicons/AppIconController$$ExternalSyntheticLambda0;

    invoke-direct {v3, v0, v1}, Lcom/exteragram/messenger/appicons/AppIconController$$ExternalSyntheticLambda0;-><init>(Ljava/util/List;Lcom/exteragram/messenger/appicons/AppIcon;)V

    invoke-virtual {v2, v3}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    return-void
.end method

.method public static getAllIcons()Ljava/util/List;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/appicons/AppIcon;",
            ">;"
        }
    .end annotation

    .line 35
    sget-object v0, Lcom/exteragram/messenger/appicons/AppIconController;->allIcons:Ljava/util/List;

    if-nez v0, :cond_0

    .line 36
    invoke-static {}, Lcom/exteragram/messenger/appicons/GeneratedAppIcons;->create()[Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v0

    invoke-static {v0}, Ljava/util/Arrays;->asList([Ljava/lang/Object;)Ljava/util/List;

    move-result-object v0

    invoke-static {v0}, Ljava/util/Collections;->unmodifiableList(Ljava/util/List;)Ljava/util/List;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/appicons/AppIconController;->allIcons:Ljava/util/List;

    .line 38
    :cond_0
    sget-object v0, Lcom/exteragram/messenger/appicons/AppIconController;->allIcons:Ljava/util/List;

    return-object v0
.end method

.method public static getAvailableIcons()Ljava/util/List;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/appicons/AppIcon;",
            ">;"
        }
    .end annotation

    .line 42
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 43
    invoke-static {}, Lcom/exteragram/messenger/appicons/AppIconController;->getAllIcons()Ljava/util/List;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_0
    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_1

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/exteragram/messenger/appicons/AppIcon;

    .line 44
    invoke-virtual {v2}, Lcom/exteragram/messenger/appicons/AppIcon;->isAvailable()Z

    move-result v3

    if-eqz v3, :cond_0

    .line 45
    invoke-interface {v0, v2}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_1
    return-object v0
.end method

.method public static getDefaultIcon()Lcom/exteragram/messenger/appicons/AppIcon;
    .locals 2

    .line 61
    const-string v0, "default"

    invoke-static {v0}, Lcom/exteragram/messenger/appicons/AppIconController;->findById(Ljava/lang/String;)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v0

    if-eqz v0, :cond_0

    return-object v0

    .line 62
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/appicons/AppIconController;->getAllIcons()Ljava/util/List;

    move-result-object v0

    const/4 v1, 0x0

    invoke-interface {v0, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/exteragram/messenger/appicons/AppIcon;

    return-object v0
.end method

.method public static getSelectedIcon()Lcom/exteragram/messenger/appicons/AppIcon;
    .locals 3

    .line 73
    sget-object v0, Lcom/exteragram/messenger/appicons/AppIconController;->selectedIcon:Lcom/exteragram/messenger/appicons/AppIcon;

    if-nez v0, :cond_2

    .line 74
    invoke-static {}, Lcom/exteragram/messenger/appicons/AppIconController;->getAllIcons()Ljava/util/List;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/exteragram/messenger/appicons/AppIcon;

    .line 75
    invoke-static {v1}, Lcom/exteragram/messenger/appicons/AppIconController;->isEnabled(Lcom/exteragram/messenger/appicons/AppIcon;)Z

    move-result v2

    if-eqz v2, :cond_0

    .line 76
    sput-object v1, Lcom/exteragram/messenger/appicons/AppIconController;->selectedIcon:Lcom/exteragram/messenger/appicons/AppIcon;

    .line 80
    :cond_1
    sget-object v0, Lcom/exteragram/messenger/appicons/AppIconController;->selectedIcon:Lcom/exteragram/messenger/appicons/AppIcon;

    if-nez v0, :cond_2

    .line 81
    invoke-static {}, Lcom/exteragram/messenger/appicons/AppIconController;->getDefaultIcon()Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/appicons/AppIconController;->selectedIcon:Lcom/exteragram/messenger/appicons/AppIcon;

    .line 84
    :cond_2
    sget-object v0, Lcom/exteragram/messenger/appicons/AppIconController;->selectedIcon:Lcom/exteragram/messenger/appicons/AppIcon;

    return-object v0
.end method

.method public static isEnabled(Lcom/exteragram/messenger/appicons/AppIcon;)Z
    .locals 2

    .line 66
    sget-object v0, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    .line 67
    invoke-virtual {v0}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v1

    invoke-virtual {p0, v0}, Lcom/exteragram/messenger/appicons/AppIcon;->getComponentName(Landroid/content/Context;)Landroid/content/ComponentName;

    move-result-object v0

    invoke-virtual {v1, v0}, Landroid/content/pm/PackageManager;->getComponentEnabledSetting(Landroid/content/ComponentName;)I

    move-result v0

    const/4 v1, 0x1

    if-eq v0, v1, :cond_1

    if-nez v0, :cond_0

    .line 69
    invoke-virtual {p0}, Lcom/exteragram/messenger/appicons/AppIcon;->isDefault()Z

    move-result p0

    if-eqz p0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    return p0

    :cond_1
    :goto_0
    return v1
.end method

.method public static setIcon(Lcom/exteragram/messenger/appicons/AppIcon;)V
    .locals 3

    .line 88
    sput-object p0, Lcom/exteragram/messenger/appicons/AppIconController;->selectedIcon:Lcom/exteragram/messenger/appicons/AppIcon;

    .line 89
    invoke-static {}, Lcom/exteragram/messenger/appicons/AppIconController;->getAllIcons()Ljava/util/List;

    move-result-object v0

    .line 90
    sget-object v1, Lorg/telegram/messenger/Utilities;->globalQueue:Lorg/telegram/messenger/DispatchQueue;

    new-instance v2, Lcom/exteragram/messenger/appicons/AppIconController$$ExternalSyntheticLambda3;

    invoke-direct {v2, p0, v0}, Lcom/exteragram/messenger/appicons/AppIconController$$ExternalSyntheticLambda3;-><init>(Lcom/exteragram/messenger/appicons/AppIcon;Ljava/util/List;)V

    invoke-virtual {v1, v2}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    return-void
.end method

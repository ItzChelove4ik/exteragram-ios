.class public final Lcom/exteragram/messenger/appicons/AppIcon;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field public final authorName:Ljava/lang/String;

.field public final authorUsername:Ljava/lang/String;

.field public final availability:Ljava/lang/String;

.field public final background:I

.field public final backgroundIsColor:Z

.field public final color:I

.field public final component:Ljava/lang/String;

.field private componentName:Landroid/content/ComponentName;

.field public final description:Ljava/lang/String;

.field public final descriptionRes:I

.field public final foreground:I

.field public final id:Ljava/lang/String;

.field public final monochrome:I

.field public final title:Ljava/lang/String;

.field public final titleRes:I


# direct methods
.method private constructor <init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)V
    .locals 0

    .line 52
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 53
    iput-object p1, p0, Lcom/exteragram/messenger/appicons/AppIcon;->id:Ljava/lang/String;

    .line 54
    iput-object p2, p0, Lcom/exteragram/messenger/appicons/AppIcon;->component:Ljava/lang/String;

    .line 55
    iput-object p3, p0, Lcom/exteragram/messenger/appicons/AppIcon;->availability:Ljava/lang/String;

    .line 56
    iput p4, p0, Lcom/exteragram/messenger/appicons/AppIcon;->titleRes:I

    .line 57
    iput-object p5, p0, Lcom/exteragram/messenger/appicons/AppIcon;->title:Ljava/lang/String;

    .line 58
    iput p6, p0, Lcom/exteragram/messenger/appicons/AppIcon;->descriptionRes:I

    .line 59
    iput-object p7, p0, Lcom/exteragram/messenger/appicons/AppIcon;->description:Ljava/lang/String;

    .line 60
    iput p8, p0, Lcom/exteragram/messenger/appicons/AppIcon;->color:I

    .line 61
    iput-object p9, p0, Lcom/exteragram/messenger/appicons/AppIcon;->authorUsername:Ljava/lang/String;

    .line 62
    iput-object p10, p0, Lcom/exteragram/messenger/appicons/AppIcon;->authorName:Ljava/lang/String;

    .line 63
    iput p11, p0, Lcom/exteragram/messenger/appicons/AppIcon;->background:I

    .line 64
    iput-boolean p12, p0, Lcom/exteragram/messenger/appicons/AppIcon;->backgroundIsColor:Z

    .line 65
    iput p13, p0, Lcom/exteragram/messenger/appicons/AppIcon;->foreground:I

    .line 66
    iput p14, p0, Lcom/exteragram/messenger/appicons/AppIcon;->monochrome:I

    return-void
.end method

.method public static of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)Lcom/exteragram/messenger/appicons/AppIcon;
    .locals 15

    .line 72
    new-instance v0, Lcom/exteragram/messenger/appicons/AppIcon;

    move-object v1, p0

    move-object/from16 v2, p1

    move-object/from16 v3, p2

    move/from16 v4, p3

    move-object/from16 v5, p4

    move/from16 v6, p5

    move-object/from16 v7, p6

    move/from16 v8, p7

    move-object/from16 v9, p8

    move-object/from16 v10, p9

    move/from16 v11, p10

    move/from16 v12, p11

    move/from16 v13, p12

    move/from16 v14, p13

    invoke-direct/range {v0 .. v14}, Lcom/exteragram/messenger/appicons/AppIcon;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)V

    return-object v0
.end method


# virtual methods
.method public getAuthor()Ljava/lang/String;
    .locals 2

    .line 125
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/AppIcon;->authorName:Ljava/lang/String;

    if-eqz v0, :cond_0

    return-object v0

    .line 128
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/AppIcon;->authorUsername:Ljava/lang/String;

    if-eqz v0, :cond_2

    const-string v1, "exteraGram"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    goto :goto_0

    .line 131
    :cond_1
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "@"

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object p0, p0, Lcom/exteragram/messenger/appicons/AppIcon;->authorUsername:Ljava/lang/String;

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_2
    :goto_0
    const/4 p0, 0x0

    return-object p0
.end method

.method public getBackground()I
    .locals 1

    .line 102
    invoke-virtual {p0}, Lcom/exteragram/messenger/appicons/AppIcon;->isDefault()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-static {}, Lorg/telegram/messenger/BuildVars;->isBetaApp()Z

    move-result v0

    if-eqz v0, :cond_0

    sget p0, Lorg/telegram/messenger/R$mipmap;->ic_launcher_beta_background:I

    return p0

    :cond_0
    iget p0, p0, Lcom/exteragram/messenger/appicons/AppIcon;->background:I

    return p0
.end method

.method public getComponentName(Landroid/content/Context;)Landroid/content/ComponentName;
    .locals 2

    .line 77
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/AppIcon;->componentName:Landroid/content/ComponentName;

    if-nez v0, :cond_0

    .line 78
    new-instance v0, Landroid/content/ComponentName;

    invoke-virtual {p1}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object p1

    iget-object v1, p0, Lcom/exteragram/messenger/appicons/AppIcon;->component:Ljava/lang/String;

    invoke-direct {v0, p1, v1}, Landroid/content/ComponentName;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    iput-object v0, p0, Lcom/exteragram/messenger/appicons/AppIcon;->componentName:Landroid/content/ComponentName;

    .line 80
    :cond_0
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/AppIcon;->componentName:Landroid/content/ComponentName;

    return-object p0
.end method

.method public getDescription()Ljava/lang/CharSequence;
    .locals 1

    .line 118
    iget v0, p0, Lcom/exteragram/messenger/appicons/AppIcon;->descriptionRes:I

    if-eqz v0, :cond_0

    .line 119
    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 121
    :cond_0
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/AppIcon;->description:Ljava/lang/String;

    return-object p0
.end method

.method public getForeground()I
    .locals 1

    .line 110
    invoke-virtual {p0}, Lcom/exteragram/messenger/appicons/AppIcon;->isDefault()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-static {}, Lorg/telegram/messenger/BuildVars;->isBetaApp()Z

    move-result v0

    if-eqz v0, :cond_0

    sget p0, Lorg/telegram/messenger/R$mipmap;->ic_launcher_beta_foreground:I

    return p0

    :cond_0
    iget p0, p0, Lcom/exteragram/messenger/appicons/AppIcon;->foreground:I

    return p0
.end method

.method public getTitle()Ljava/lang/CharSequence;
    .locals 1

    .line 114
    iget v0, p0, Lcom/exteragram/messenger/appicons/AppIcon;->titleRes:I

    if-eqz v0, :cond_0

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_0
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/AppIcon;->title:Ljava/lang/String;

    return-object p0
.end method

.method public isAvailable()Z
    .locals 2

    .line 92
    const-string v0, "winter"

    iget-object v1, p0, Lcom/exteragram/messenger/appicons/AppIcon;->availability:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 93
    invoke-static {}, Lcom/exteragram/messenger/utils/AppUtils;->isWinter()Z

    move-result p0

    return p0

    .line 95
    :cond_0
    invoke-virtual {p0}, Lcom/exteragram/messenger/appicons/AppIcon;->isMonet()Z

    move-result p0

    const/4 v0, 0x1

    if-eqz p0, :cond_2

    .line 96
    sget p0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x1f

    if-lt p0, v1, :cond_1

    const/16 v1, 0x20

    if-gt p0, v1, :cond_1

    return v0

    :cond_1
    const/4 p0, 0x0

    return p0

    :cond_2
    return v0
.end method

.method public isBackgroundColor()Z
    .locals 1

    .line 106
    iget-boolean v0, p0, Lcom/exteragram/messenger/appicons/AppIcon;->backgroundIsColor:Z

    if-eqz v0, :cond_1

    invoke-virtual {p0}, Lcom/exteragram/messenger/appicons/AppIcon;->isDefault()Z

    move-result p0

    if-eqz p0, :cond_0

    invoke-static {}, Lorg/telegram/messenger/BuildVars;->isBetaApp()Z

    move-result p0

    if-nez p0, :cond_1

    :cond_0
    const/4 p0, 0x1

    return p0

    :cond_1
    const/4 p0, 0x0

    return p0
.end method

.method public isDefault()Z
    .locals 1

    .line 84
    const-string v0, "default"

    iget-object p0, p0, Lcom/exteragram/messenger/appicons/AppIcon;->id:Ljava/lang/String;

    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    return p0
.end method

.method public isMonet()Z
    .locals 1

    .line 88
    const-string v0, "monet"

    iget-object p0, p0, Lcom/exteragram/messenger/appicons/AppIcon;->availability:Ljava/lang/String;

    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    return p0
.end method

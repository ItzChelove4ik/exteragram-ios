.class Lcom/exteragram/messenger/ai/ui/AiResponseAlert$5;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->createRichResourcesProvider()Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

.field final synthetic val$parent:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;


# direct methods
.method public constructor <init>(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 288
    iput-object p1, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$5;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    iput-object p2, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$5;->val$parent:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public applyServiceShaderMatrix(IIFF)V
    .locals 0

    .line 321
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$5;->val$parent:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    if-nez p0, :cond_0

    .line 322
    invoke-static {p1, p2, p3, p4}, Lorg/telegram/ui/ActionBar/Theme;->applyServiceShaderMatrix(IIFF)V

    return-void

    .line 324
    :cond_0
    invoke-interface {p0, p1, p2, p3, p4}, Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;->applyServiceShaderMatrix(IIFF)V

    return-void
.end method

.method public getColor(I)I
    .locals 1

    .line 291
    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_chat_messageTextIn:I

    if-eq p1, v0, :cond_2

    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_chat_messageTextOut:I

    if-ne p1, v0, :cond_0

    goto :goto_0

    .line 293
    :cond_0
    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_chat_messageLinkIn:I

    if-eq p1, v0, :cond_1

    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_chat_messageLinkOut:I

    if-ne p1, v0, :cond_3

    .line 294
    :cond_1
    sget p1, Lorg/telegram/ui/ActionBar/Theme;->key_dialogTextLink:I

    goto :goto_1

    .line 292
    :cond_2
    :goto_0
    sget p1, Lorg/telegram/ui/ActionBar/Theme;->key_dialogTextBlack:I

    .line 296
    :cond_3
    :goto_1
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$5;->val$parent:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {p1, p0}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result p0

    return p0
.end method

.method public getDrawable(Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    .locals 0

    .line 301
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$5;->val$parent:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    if-nez p0, :cond_0

    const/4 p0, 0x0

    return-object p0

    :cond_0
    invoke-interface {p0, p1}, Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;->getDrawable(Ljava/lang/String;)Landroid/graphics/drawable/Drawable;

    move-result-object p0

    return-object p0
.end method

.method public getPaint(Ljava/lang/String;)Landroid/graphics/Paint;
    .locals 0

    .line 306
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$5;->val$parent:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    if-nez p0, :cond_0

    invoke-static {p1}, Lorg/telegram/ui/ActionBar/Theme;->getThemePaint(Ljava/lang/String;)Landroid/graphics/Paint;

    move-result-object p0

    return-object p0

    :cond_0
    invoke-interface {p0, p1}, Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;->getPaint(Ljava/lang/String;)Landroid/graphics/Paint;

    move-result-object p0

    return-object p0
.end method

.method public hasGradientService()Z
    .locals 0

    .line 311
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$5;->val$parent:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    if-eqz p0, :cond_0

    invoke-interface {p0}, Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;->hasGradientService()Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public isDark()Z
    .locals 0

    .line 316
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$5;->val$parent:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    if-nez p0, :cond_0

    invoke-static {}, Lorg/telegram/ui/ActionBar/Theme;->isCurrentThemeDark()Z

    move-result p0

    return p0

    :cond_0
    invoke-interface {p0}, Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;->isDark()Z

    move-result p0

    return p0
.end method

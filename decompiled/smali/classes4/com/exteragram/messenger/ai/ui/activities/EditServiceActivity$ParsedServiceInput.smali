.class final Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "ParsedServiceInput"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\u0008\t\n\u0002\u0010\u000b\n\u0002\u0008\u0005\u0008\u0002\u0018\u00002\u00020\u0001B%\u0012\u0008\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0008\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0008\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0004\u0008\u0006\u0010\u0007J\u0006\u0010\u000c\u001a\u00020\rJ\u001e\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0003R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0008\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\n\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u000b\u0010\t\u00a8\u0006\u0012"
    }
    d2 = {
        "Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;",
        "",
        "url",
        "",
        "model",
        "key",
        "<init>",
        "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V",
        "getUrl",
        "()Ljava/lang/String;",
        "getModel",
        "getKey",
        "hasServiceFields",
        "",
        "differsFrom",
        "currentUrl",
        "currentModel",
        "currentKey",
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
.field private final key:Ljava/lang/String;

.field private final model:Ljava/lang/String;

.field private final url:Ljava/lang/String;


# direct methods
.method public constructor <init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    .locals 0

    .line 645
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;->url:Ljava/lang/String;

    iput-object p2, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;->model:Ljava/lang/String;

    iput-object p3, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;->key:Ljava/lang/String;

    return-void
.end method


# virtual methods
.method public final differsFrom(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    .locals 1

    .line 649
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;->url:Ljava/lang/String;

    if-eqz v0, :cond_1

    invoke-interface {v0}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;->url:Ljava/lang/String;

    invoke-static {v0, p1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_5

    .line 650
    :cond_1
    :goto_0
    iget-object p1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;->model:Ljava/lang/String;

    if-eqz p1, :cond_3

    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result p1

    if-nez p1, :cond_2

    goto :goto_1

    :cond_2
    iget-object p1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;->model:Ljava/lang/String;

    invoke-static {p1, p2}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_5

    .line 651
    :cond_3
    :goto_1
    iget-object p1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;->key:Ljava/lang/String;

    if-eqz p1, :cond_6

    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result p1

    if-nez p1, :cond_4

    goto :goto_2

    :cond_4
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;->key:Ljava/lang/String;

    invoke-static {p0, p3}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_6

    :cond_5
    const/4 p0, 0x1

    return p0

    :cond_6
    :goto_2
    const/4 p0, 0x0

    return p0
.end method

.method public final getKey()Ljava/lang/String;
    .locals 0

    .line 645
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;->key:Ljava/lang/String;

    return-object p0
.end method

.method public final getModel()Ljava/lang/String;
    .locals 0

    .line 645
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;->model:Ljava/lang/String;

    return-object p0
.end method

.method public final getUrl()Ljava/lang/String;
    .locals 0

    .line 645
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;->url:Ljava/lang/String;

    return-object p0
.end method

.method public final hasServiceFields()Z
    .locals 1

    .line 646
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;->url:Ljava/lang/String;

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_1

    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;->model:Ljava/lang/String;

    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p0

    if-nez p0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    return p0

    :cond_1
    :goto_0
    const/4 p0, 0x1

    return p0
.end method

.class public final Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$handleDeleteAccountClick$1$1$1;
.super Landroid/os/CountDownTimer;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;->handleDeleteAccountClick()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = null
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0008\u0002*\u0001\u0000\u0008\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0008\u0010\u0006\u001a\u00020\u0003H\u0016\u00a8\u0006\u0007"
    }
    d2 = {
        "com/exteragram/messenger/preferences/OtherPreferencesActivity$handleDeleteAccountClick$1$1$1",
        "Landroid/os/CountDownTimer;",
        "onTick",
        "",
        "millisUntilFinished",
        "",
        "onFinish",
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
.field final synthetic $button:Landroid/widget/TextView;

.field final synthetic $buttonText:Ljava/lang/CharSequence;


# direct methods
.method public constructor <init>(Landroid/widget/TextView;Ljava/lang/CharSequence;)V
    .locals 2

    iput-object p1, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$handleDeleteAccountClick$1$1$1;->$button:Landroid/widget/TextView;

    iput-object p2, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$handleDeleteAccountClick$1$1$1;->$buttonText:Ljava/lang/CharSequence;

    const-wide/16 p1, 0x7530

    const-wide/16 v0, 0x64

    .line 267
    invoke-direct {p0, p1, p2, v0, v1}, Landroid/os/CountDownTimer;-><init>(JJ)V

    return-void
.end method


# virtual methods
.method public onFinish()V
    .locals 2

    .line 273
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$handleDeleteAccountClick$1$1$1;->$button:Landroid/widget/TextView;

    iget-object v1, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$handleDeleteAccountClick$1$1$1;->$buttonText:Ljava/lang/CharSequence;

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 274
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$handleDeleteAccountClick$1$1$1;->$button:Landroid/widget/TextView;

    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Landroid/widget/TextView;->setEnabled(Z)V

    return-void
.end method

.method public onTick(J)V
    .locals 4

    .line 269
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$handleDeleteAccountClick$1$1$1;->$button:Landroid/widget/TextView;

    sget-object v1, Lkotlin/jvm/internal/StringCompanionObject;->INSTANCE:Lkotlin/jvm/internal/StringCompanionObject;

    invoke-static {}, Ljava/util/Locale;->getDefault()Ljava/util/Locale;

    move-result-object v1

    iget-object p0, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$handleDeleteAccountClick$1$1$1;->$buttonText:Ljava/lang/CharSequence;

    const-wide/16 v2, 0x3e8

    div-long/2addr p1, v2

    const-wide/16 v2, 0x1

    add-long/2addr p1, v2

    invoke-static {p1, p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    filled-new-array {p0, p1}, [Ljava/lang/Object;

    move-result-object p0

    const/4 p1, 0x2

    invoke-static {p0, p1}, Ljava/util/Arrays;->copyOf([Ljava/lang/Object;I)[Ljava/lang/Object;

    move-result-object p0

    const-string p1, "%s \u2022 %d"

    invoke-static {v1, p1, p0}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    return-void
.end method

.class public final Landroidx/media3/session/SessionError;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final FIELD_CODE:Ljava/lang/String;

.field private static final FIELD_EXTRAS:Ljava/lang/String;

.field private static final FIELD_MESSAGE:Ljava/lang/String;


# instance fields
.field public code:I

.field public extras:Landroid/os/Bundle;

.field public message:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    const/4 v0, 0x0

    .line 234
    invoke-static {v0}, Landroidx/media3/common/util/Util;->intToStringMaxRadix(I)Ljava/lang/String;

    move-result-object v0

    sput-object v0, Landroidx/media3/session/SessionError;->FIELD_CODE:Ljava/lang/String;

    const/4 v0, 0x1

    .line 235
    invoke-static {v0}, Landroidx/media3/common/util/Util;->intToStringMaxRadix(I)Ljava/lang/String;

    move-result-object v0

    sput-object v0, Landroidx/media3/session/SessionError;->FIELD_MESSAGE:Ljava/lang/String;

    const/4 v0, 0x2

    .line 236
    invoke-static {v0}, Landroidx/media3/common/util/Util;->intToStringMaxRadix(I)Ljava/lang/String;

    move-result-object v0

    sput-object v0, Landroidx/media3/session/SessionError;->FIELD_EXTRAS:Ljava/lang/String;

    return-void
.end method

.method public constructor <init>(ILjava/lang/String;)V
    .locals 1

    .line 196
    sget-object v0, Landroid/os/Bundle;->EMPTY:Landroid/os/Bundle;

    invoke-direct {p0, p1, p2, v0}, Landroidx/media3/session/SessionError;-><init>(ILjava/lang/String;Landroid/os/Bundle;)V

    return-void
.end method

.method public constructor <init>(ILjava/lang/String;Landroid/os/Bundle;)V
    .locals 1

    .line 207
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x1

    if-ltz p1, :cond_1

    if-ne p1, v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    .line 208
    :cond_1
    :goto_0
    invoke-static {v0}, Landroidx/media3/common/util/Assertions;->checkArgument(Z)V

    .line 209
    iput p1, p0, Landroidx/media3/session/SessionError;->code:I

    .line 210
    iput-object p2, p0, Landroidx/media3/session/SessionError;->message:Ljava/lang/String;

    .line 211
    iput-object p3, p0, Landroidx/media3/session/SessionError;->extras:Landroid/os/Bundle;

    return-void
.end method

.method public static fromBundle(Landroid/os/Bundle;)Landroidx/media3/session/SessionError;
    .locals 3

    .line 251
    sget-object v0, Landroidx/media3/session/SessionError;->FIELD_CODE:Ljava/lang/String;

    const/16 v1, 0x3e8

    .line 252
    invoke-virtual {p0, v0, v1}, Landroid/os/BaseBundle;->getInt(Ljava/lang/String;I)I

    move-result v0

    .line 253
    sget-object v1, Landroidx/media3/session/SessionError;->FIELD_MESSAGE:Ljava/lang/String;

    const-string v2, ""

    invoke-virtual {p0, v1, v2}, Landroid/os/BaseBundle;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 254
    sget-object v2, Landroidx/media3/session/SessionError;->FIELD_EXTRAS:Ljava/lang/String;

    invoke-virtual {p0, v2}, Landroid/os/Bundle;->getBundle(Ljava/lang/String;)Landroid/os/Bundle;

    move-result-object p0

    .line 255
    new-instance v2, Landroidx/media3/session/SessionError;

    if-nez p0, :cond_0

    sget-object p0, Landroid/os/Bundle;->EMPTY:Landroid/os/Bundle;

    :cond_0
    invoke-direct {v2, v0, v1, p0}, Landroidx/media3/session/SessionError;-><init>(ILjava/lang/String;Landroid/os/Bundle;)V

    return-object v2
.end method


# virtual methods
.method public equals(Ljava/lang/Object;)Z
    .locals 4

    const/4 v0, 0x1

    if-ne p0, p1, :cond_0

    return v0

    .line 220
    :cond_0
    instance-of v1, p1, Landroidx/media3/session/SessionError;

    const/4 v2, 0x0

    if-nez v1, :cond_1

    return v2

    .line 223
    :cond_1
    check-cast p1, Landroidx/media3/session/SessionError;

    .line 224
    iget v1, p0, Landroidx/media3/session/SessionError;->code:I

    iget v3, p1, Landroidx/media3/session/SessionError;->code:I

    if-ne v1, v3, :cond_2

    iget-object p0, p0, Landroidx/media3/session/SessionError;->message:Ljava/lang/String;

    iget-object p1, p1, Landroidx/media3/session/SessionError;->message:Ljava/lang/String;

    invoke-static {p0, p1}, Ljava/util/Objects;->equals(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_2

    return v0

    :cond_2
    return v2
.end method

.method public hashCode()I
    .locals 1

    .line 229
    iget v0, p0, Landroidx/media3/session/SessionError;->code:I

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    iget-object p0, p0, Landroidx/media3/session/SessionError;->message:Ljava/lang/String;

    filled-new-array {v0, p0}, [Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Ljava/util/Objects;->hash([Ljava/lang/Object;)I

    move-result p0

    return p0
.end method

.method public toBundle()Landroid/os/Bundle;
    .locals 3

    .line 240
    new-instance v0, Landroid/os/Bundle;

    invoke-direct {v0}, Landroid/os/Bundle;-><init>()V

    .line 241
    sget-object v1, Landroidx/media3/session/SessionError;->FIELD_CODE:Ljava/lang/String;

    iget v2, p0, Landroidx/media3/session/SessionError;->code:I

    invoke-virtual {v0, v1, v2}, Landroid/os/BaseBundle;->putInt(Ljava/lang/String;I)V

    .line 242
    sget-object v1, Landroidx/media3/session/SessionError;->FIELD_MESSAGE:Ljava/lang/String;

    iget-object v2, p0, Landroidx/media3/session/SessionError;->message:Ljava/lang/String;

    invoke-virtual {v0, v1, v2}, Landroid/os/BaseBundle;->putString(Ljava/lang/String;Ljava/lang/String;)V

    .line 243
    iget-object v1, p0, Landroidx/media3/session/SessionError;->extras:Landroid/os/Bundle;

    invoke-virtual {v1}, Landroid/os/BaseBundle;->isEmpty()Z

    move-result v1

    if-nez v1, :cond_0

    .line 244
    sget-object v1, Landroidx/media3/session/SessionError;->FIELD_EXTRAS:Ljava/lang/String;

    iget-object p0, p0, Landroidx/media3/session/SessionError;->extras:Landroid/os/Bundle;

    invoke-virtual {v0, v1, p0}, Landroid/os/Bundle;->putBundle(Ljava/lang/String;Landroid/os/Bundle;)V

    :cond_0
    return-object v0
.end method

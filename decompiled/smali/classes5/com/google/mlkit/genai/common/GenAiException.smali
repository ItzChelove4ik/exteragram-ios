.class public Lcom/google/mlkit/genai/common/GenAiException;
.super Ljava/lang/Exception;
.source "SourceFile"


# instance fields
.field private final zza:I

.field private final zzb:Lj$/time/Duration;


# direct methods
.method public constructor <init>(Ljava/lang/String;Ljava/lang/Throwable;I)V
    .locals 1

    .line 1
    sget-object v0, Lj$/time/Duration;->ZERO:Lj$/time/Duration;

    invoke-direct {p0, p1, p2, p3, v0}, Lcom/google/mlkit/genai/common/GenAiException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;ILj$/time/Duration;)V

    return-void
.end method

.method public constructor <init>(Ljava/lang/String;Ljava/lang/Throwable;ILj$/time/Duration;)V
    .locals 0

    .line 2
    invoke-direct {p0, p1, p2}, Ljava/lang/Exception;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    iput p3, p0, Lcom/google/mlkit/genai/common/GenAiException;->zza:I

    iput-object p4, p0, Lcom/google/mlkit/genai/common/GenAiException;->zzb:Lj$/time/Duration;

    return-void
.end method

.method public constructor <init>(Ljava/lang/Throwable;I)V
    .locals 1

    .line 3
    sget-object v0, Lj$/time/Duration;->ZERO:Lj$/time/Duration;

    invoke-direct {p0, p1, p2, v0}, Lcom/google/mlkit/genai/common/GenAiException;-><init>(Ljava/lang/Throwable;ILj$/time/Duration;)V

    return-void
.end method

.method public constructor <init>(Ljava/lang/Throwable;ILj$/time/Duration;)V
    .locals 4

    const/16 v0, -0x6b

    if-eq p2, v0, :cond_e

    const/4 v0, 0x4

    if-eq p2, v0, :cond_d

    const/16 v0, 0x1e

    if-eq p2, v0, :cond_c

    const/16 v0, 0x1f5

    if-eq p2, v0, :cond_b

    const/16 v0, 0x25c

    if-eq p2, v0, :cond_a

    const/4 v0, 0x7

    if-eq p2, v0, :cond_9

    const/16 v0, 0x8

    if-eq p2, v0, :cond_8

    const/16 v0, 0x9

    if-eq p2, v0, :cond_7

    const/16 v0, 0xb

    if-eq p2, v0, :cond_6

    const/16 v0, 0xc

    if-eq p2, v0, :cond_5

    const/16 v0, 0xf

    if-eq p2, v0, :cond_4

    const/16 v0, 0x10

    if-eq p2, v0, :cond_3

    const/16 v0, 0x1b

    if-eq p2, v0, :cond_2

    const/16 v0, 0x1c

    if-eq p2, v0, :cond_1

    packed-switch p2, :pswitch_data_0

    if-eqz p1, :cond_0

    .line 4
    invoke-virtual {p1}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v0

    if-nez v0, :cond_f

    const-string v0, ""

    goto :goto_0

    .line 6
    :cond_0
    const-string v0, "Unknown error."

    goto :goto_0

    :pswitch_0
    const-string v0, "Request too small. Please provide a longer input."

    goto :goto_0

    :pswitch_1
    const-string v0, "AICore is either not installed or the installed version is too low."

    goto :goto_0

    :pswitch_2
    const-string v0, "Invalid input image. It can be due to invalid image URL, failure to decode to bitmap, invalid mime type or other image processing failures."

    goto :goto_0

    :cond_1
    const-string v0, "A long-duration (for example per-day) quota for the device has been exceeded. Use of AICore (from any app) has been restricted to preserve battery."

    goto :goto_0

    :cond_2
    const-string v0, "A long-duration (for example per-day) quota for the calling app\'s uid has been exceeded. Use of AICore from this app has been restricted to preserve battery."

    goto :goto_0

    :cond_3
    const-string v0, "This functionality is not implemented nor supported by the inference engine."

    goto :goto_0

    :cond_4
    const-string v0, "Couldn\'t generate a response due to policy check failure. Please try a different input."

    goto :goto_0

    :cond_5
    const-string v0, "Request too large. Please shorten your input."

    goto :goto_0

    :cond_6
    const-string v0, "Generated response doesn\'t pass certain policy check. Please try a different input."

    goto :goto_0

    :cond_7
    const-string v0, "Request cannot be processed. Either your app is out of usage quota (try again later) or the request is from disallowed background usage (use the API while the app is in the foreground)."

    goto :goto_0

    :cond_8
    const-string v0, "This feature is not available on this device. It is due to either the device not supporting it or the latest configuration file has not been downloaded."

    goto :goto_0

    :cond_9
    const-string v0, "The request is canceled."

    goto :goto_0

    :cond_a
    const-string v0, "Android version is too low. Please update."

    goto :goto_0

    :cond_b
    const-string v0, "Not enough storage. Please clear some space."

    goto :goto_0

    :cond_c
    const-string v0, "Background usage is blocked. Please use the API when your app is in the foreground instead."

    goto :goto_0

    :cond_d
    const-string v0, "Request doesn\'t pass certain policy check. Please try a different input."

    goto :goto_0

    :cond_e
    const-string v0, "Audio buffer overflow. The audio input rate is too fast."

    .line 5
    :cond_f
    :goto_0
    invoke-static {p2}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v1

    new-instance v2, Ljava/lang/StringBuilder;

    add-int/lit8 v1, v1, 0xd

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v3

    add-int/2addr v1, v3

    invoke-direct {v2, v1}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string v1, "[ErrorCode "

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v1, "] "

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 6
    invoke-direct {p0, v0, p1}, Ljava/lang/Exception;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    iput p2, p0, Lcom/google/mlkit/genai/common/GenAiException;->zza:I

    iput-object p3, p0, Lcom/google/mlkit/genai/common/GenAiException;->zzb:Lj$/time/Duration;

    return-void

    nop

    :pswitch_data_0
    .packed-switch -0x66
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method


# virtual methods
.method public getErrorCode()I
    .locals 1

    iget p0, p0, Lcom/google/mlkit/genai/common/GenAiException;->zza:I

    const/16 v0, 0x1c

    if-ne p0, v0, :cond_0

    const/16 p0, 0x9

    :cond_0
    return p0
.end method

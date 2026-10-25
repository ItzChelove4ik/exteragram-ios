.class public final Lcom/google/android/recaptcha/internal/zzfd;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzev;


# instance fields
.field private final zza:Lcom/google/android/recaptcha/internal/zzfp;

.field private zzb:Z

.field private final zzc:Lcom/google/android/recaptcha/internal/zzhr;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzhu;Lcom/google/android/play/core/integrity/StandardIntegrityManager;Lcom/google/android/recaptcha/internal/zzhr;Lcom/google/android/recaptcha/internal/zzfp;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzfd;->zzc:Lcom/google/android/recaptcha/internal/zzhr;

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzfd;->zza:Lcom/google/android/recaptcha/internal/zzfp;

    const/4 p1, 0x1

    iput-boolean p1, p0, Lcom/google/android/recaptcha/internal/zzfd;->zzb:Z

    return-void
.end method

.method public static final synthetic zzi(Lcom/google/android/recaptcha/internal/zzfd;)Lcom/google/android/recaptcha/internal/zzfp;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzfd;->zza:Lcom/google/android/recaptcha/internal/zzfp;

    return-object p0
.end method

.method public static final synthetic zzj(Lcom/google/android/recaptcha/internal/zzfd;)Lcom/google/android/recaptcha/internal/zzhr;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzfd;->zzc:Lcom/google/android/recaptcha/internal/zzhr;

    return-object p0
.end method


# virtual methods
.method public final zza()I
    .locals 0

    const/4 p0, 0x2

    return p0
.end method

.method public final zzb()Z
    .locals 0

    iget-boolean p0, p0, Lcom/google/android/recaptcha/internal/zzfd;->zzb:Z

    return p0
.end method

.method public final zzc(Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzaka;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 0

    .line 1
    new-instance p1, Lcom/google/android/recaptcha/internal/zzfb;

    const/4 p3, 0x0

    invoke-direct {p1, p2, p0, p3}, Lcom/google/android/recaptcha/internal/zzfb;-><init>(Lcom/google/android/recaptcha/internal/zzaka;Lcom/google/android/recaptcha/internal/zzfd;Lkotlin/coroutines/Continuation;)V

    new-instance p0, Lcom/google/android/recaptcha/internal/zzmc;

    invoke-direct {p0, p1}, Lcom/google/android/recaptcha/internal/zzmc;-><init>(Lkotlin/jvm/functions/Function2;)V

    return-object p0
.end method

.method public final zzd(Ljava/lang/Exception;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 0

    .line 1
    invoke-virtual {p1}, Ljava/lang/Throwable;->getCause()Ljava/lang/Throwable;

    move-result-object p2

    if-nez p2, :cond_0

    goto :goto_0

    :cond_0
    move-object p1, p2

    .line 2
    :goto_0
    nop

    instance-of p2, p1, Lcom/google/android/play/core/integrity/StandardIntegrityException;

    if-eqz p2, :cond_6

    .line 3
    check-cast p1, Lcom/google/android/play/core/integrity/StandardIntegrityException;

    invoke-virtual {p1}, Lcom/google/android/play/core/integrity/StandardIntegrityException;->getErrorCode()I

    move-result p1

    const/16 p2, -0x64

    if-eq p1, p2, :cond_5

    const/16 p2, -0xc

    if-eq p1, p2, :cond_4

    const/4 p2, -0x3

    if-eq p1, p2, :cond_3

    const/4 p2, -0x2

    if-eq p1, p2, :cond_2

    const/4 p2, -0x1

    if-eq p1, p2, :cond_1

    packed-switch p1, :pswitch_data_0

    packed-switch p1, :pswitch_data_1

    const/4 p1, 0x2

    goto :goto_1

    :pswitch_0
    const/16 p1, 0x20

    goto :goto_1

    :pswitch_1
    const/16 p1, 0x21

    goto :goto_1

    :pswitch_2
    const/16 p1, 0x22

    goto :goto_1

    :pswitch_3
    const/16 p1, 0x23

    goto :goto_1

    :pswitch_4
    const/16 p1, 0x24

    goto :goto_1

    :pswitch_5
    const/16 p1, 0x29

    goto :goto_1

    :pswitch_6
    const/16 p1, 0x2a

    goto :goto_1

    :pswitch_7
    const/16 p1, 0x2b

    goto :goto_1

    :pswitch_8
    const/16 p1, 0x34

    goto :goto_1

    :pswitch_9
    const/16 p1, 0x35

    goto :goto_1

    :pswitch_a
    const/16 p1, 0x36

    goto :goto_1

    :cond_1
    const/16 p1, 0x1c

    goto :goto_1

    :cond_2
    const/16 p1, 0x1d

    goto :goto_1

    :cond_3
    const/16 p1, 0x1e

    goto :goto_1

    :cond_4
    const/16 p1, 0x27

    goto :goto_1

    :cond_5
    const/16 p1, 0x2c

    goto :goto_1

    :cond_6
    const/16 p1, 0x2d

    .line 5
    :goto_1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzalx;->zza()Lcom/google/android/recaptcha/internal/zzalw;

    move-result-object p2

    .line 6
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzalw;->zzc(I)Lcom/google/android/recaptcha/internal/zzalw;

    const/16 p1, 0xf

    .line 7
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzalw;->zzb(I)Lcom/google/android/recaptcha/internal/zzalw;

    .line 8
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p1

    check-cast p1, Lcom/google/android/recaptcha/internal/zzalx;

    .line 4
    invoke-static {p0, p1}, Lcom/google/android/recaptcha/internal/zzew;->zzb(Lcom/google/android/recaptcha/internal/zzev;Lcom/google/android/recaptcha/internal/zzalx;)Lcom/google/android/recaptcha/internal/zzex;

    move-result-object p0

    return-object p0

    nop

    :pswitch_data_0
    .packed-switch -0x13
        :pswitch_a
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
    .end packed-switch

    :pswitch_data_1
    .packed-switch -0x9
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public final zze(Z)V
    .locals 0

    const/4 p1, 0x0

    iput-boolean p1, p0, Lcom/google/android/recaptcha/internal/zzfd;->zzb:Z

    return-void
.end method

.method public final zzk(Lcom/google/android/recaptcha/internal/zzakk;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 1

    .line 1
    new-instance p2, Lcom/google/android/recaptcha/internal/zzfc;

    const/4 v0, 0x0

    invoke-direct {p2, p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzfc;-><init>(Lcom/google/android/recaptcha/internal/zzfd;Lcom/google/android/recaptcha/internal/zzakk;Lkotlin/coroutines/Continuation;)V

    new-instance p0, Lcom/google/android/recaptcha/internal/zzmc;

    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzmc;-><init>(Lkotlin/jvm/functions/Function2;)V

    return-object p0
.end method

.class public final Lcom/google/android/recaptcha/internal/zzea;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzev;


# instance fields
.field private final zza:Landroid/content/ContentResolver;


# direct methods
.method public constructor <init>(Landroid/content/ContentResolver;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzea;->zza:Landroid/content/ContentResolver;

    return-void
.end method

.method public static final synthetic zze(Lcom/google/android/recaptcha/internal/zzea;)Landroid/content/ContentResolver;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzea;->zza:Landroid/content/ContentResolver;

    return-object p0
.end method


# virtual methods
.method public final zza()I
    .locals 0

    const/16 p0, 0x11

    return p0
.end method

.method public final zzb()Z
    .locals 0

    const/4 p0, 0x1

    return p0
.end method

.method public final zzc(Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzaka;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 0

    .line 1
    new-instance p1, Lcom/google/android/recaptcha/internal/zzdz;

    const/4 p2, 0x0

    invoke-direct {p1, p0, p2}, Lcom/google/android/recaptcha/internal/zzdz;-><init>(Lcom/google/android/recaptcha/internal/zzea;Lkotlin/coroutines/Continuation;)V

    new-instance p0, Lcom/google/android/recaptcha/internal/zzmc;

    invoke-direct {p0, p1}, Lcom/google/android/recaptcha/internal/zzmc;-><init>(Lkotlin/jvm/functions/Function2;)V

    return-object p0
.end method

.method public final zzd(Ljava/lang/Exception;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 1

    .line 1
    sget p1, Landroid/os/Build$VERSION;->SDK_INT:I

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzalx;->zza()Lcom/google/android/recaptcha/internal/zzalw;

    move-result-object p2

    const/16 v0, 0x10

    .line 2
    invoke-virtual {p2, v0}, Lcom/google/android/recaptcha/internal/zzalw;->zzb(I)Lcom/google/android/recaptcha/internal/zzalw;

    const/16 v0, 0x22

    if-le p1, v0, :cond_0

    const/16 p1, 0x3b

    goto :goto_0

    :cond_0
    const/16 p1, 0x3a

    .line 3
    :goto_0
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzalw;->zzc(I)Lcom/google/android/recaptcha/internal/zzalw;

    .line 4
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p1

    check-cast p1, Lcom/google/android/recaptcha/internal/zzalx;

    invoke-static {p0, p1}, Lcom/google/android/recaptcha/internal/zzew;->zzb(Lcom/google/android/recaptcha/internal/zzev;Lcom/google/android/recaptcha/internal/zzalx;)Lcom/google/android/recaptcha/internal/zzex;

    move-result-object p0

    return-object p0
.end method

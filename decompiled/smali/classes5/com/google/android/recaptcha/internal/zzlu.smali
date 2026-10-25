.class public final Lcom/google/android/recaptcha/internal/zzlu;
.super Ljava/util/TimerTask;
.source "SourceFile"


# instance fields
.field final synthetic zza:Lcom/google/android/recaptcha/internal/zzlx;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzlx;)V
    .locals 0

    .line 1
    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzlu;->zza:Lcom/google/android/recaptcha/internal/zzlx;

    invoke-direct {p0}, Ljava/util/TimerTask;-><init>()V

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 1

    .line 1
    :try_start_0
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzlu;->zza:Lcom/google/android/recaptcha/internal/zzlx;

    const/4 v0, 0x0

    .line 2
    invoke-static {p0, v0}, Lcom/google/android/recaptcha/internal/zzlx;->zze(Lcom/google/android/recaptcha/internal/zzlx;I)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    :catch_0
    return-void
.end method

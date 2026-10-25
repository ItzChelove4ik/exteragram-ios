.class final synthetic Lcom/google/android/recaptcha/internal/zzij;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzip;


# instance fields
.field private final synthetic zza:Landroid/app/Application;

.field private final synthetic zzb:Lcom/google/android/recaptcha/internal/zzhr;


# direct methods
.method public synthetic constructor <init>(Landroid/app/Application;Lcom/google/android/recaptcha/internal/zzhr;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzij;->zza:Landroid/app/Application;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzij;->zzb:Lcom/google/android/recaptcha/internal/zzhr;

    return-void
.end method


# virtual methods
.method public final synthetic zza()Lcom/google/android/recaptcha/internal/zzcl;
    .locals 3

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzdy;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzij;->zzb:Lcom/google/android/recaptcha/internal/zzhr;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzij;->zza:Landroid/app/Application;

    const/4 v2, 0x0

    invoke-direct {v0, p0, v1, v2}, Lcom/google/android/recaptcha/internal/zzdy;-><init>(Landroid/app/Application;Lcom/google/android/recaptcha/internal/zzhr;Lcom/google/android/gms/recaptchabase/RecaptchaBaseClient;)V

    return-object v0
.end method

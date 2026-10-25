.class public final Lcom/google/android/recaptcha/internal/zzbd;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final zza:Landroid/view/Window;

.field private final zzb:Ljava/lang/String;

.field private final zzc:Lcom/google/android/recaptcha/internal/zzav;


# direct methods
.method public constructor <init>(Landroid/view/Window;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzav;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzbd;->zza:Landroid/view/Window;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzbd;->zzb:Ljava/lang/String;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzbd;->zzc:Lcom/google/android/recaptcha/internal/zzav;

    return-void
.end method

.method public static final synthetic zzb(Lcom/google/android/recaptcha/internal/zzbd;)Lcom/google/android/recaptcha/internal/zzav;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzbd;->zzc:Lcom/google/android/recaptcha/internal/zzav;

    return-object p0
.end method

.method public static final synthetic zzc(Lcom/google/android/recaptcha/internal/zzbd;)Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzbd;->zzb:Ljava/lang/String;

    return-object p0
.end method


# virtual methods
.method public final zza()V
    .locals 3

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzbd;->zza:Landroid/view/Window;

    invoke-virtual {v0}, Landroid/view/Window;->getCallback()Landroid/view/Window$Callback;

    move-result-object v1

    new-instance v2, Lcom/google/android/recaptcha/internal/zzbc;

    invoke-direct {v2, v1, p0}, Lcom/google/android/recaptcha/internal/zzbc;-><init>(Landroid/view/Window$Callback;Lcom/google/android/recaptcha/internal/zzbd;)V

    .line 3
    invoke-virtual {v0, v2}, Landroid/view/Window;->setCallback(Landroid/view/Window$Callback;)V

    return-void
.end method

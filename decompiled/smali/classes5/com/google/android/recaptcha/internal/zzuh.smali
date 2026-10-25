.class final Lcom/google/android/recaptcha/internal/zzuh;
.super Lcom/google/android/recaptcha/internal/zzsl;
.source "SourceFile"


# instance fields
.field private final zza:Ljava/lang/String;

.field private final zzb:Lcom/google/android/recaptcha/internal/zzso;


# direct methods
.method public synthetic constructor <init>(Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzso;[B)V
    .locals 0

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzsl;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzuh;->zza:Ljava/lang/String;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzuh;->zzb:Lcom/google/android/recaptcha/internal/zzso;

    return-void
.end method


# virtual methods
.method public final toString()Ljava/lang/String;
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzuh;->zza:Ljava/lang/String;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzuh;->zzb:Lcom/google/android/recaptcha/internal/zzso;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzso;->toString()Ljava/lang/String;

    move-result-object p0

    filled-new-array {v0, p0}, [Ljava/lang/Object;

    move-result-object p0

    const-string v0, "(typeUrl=%s, outputPrefixType=%s)"

    invoke-static {v0, p0}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

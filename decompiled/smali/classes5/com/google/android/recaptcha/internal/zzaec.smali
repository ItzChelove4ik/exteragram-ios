.class public abstract Lcom/google/android/recaptcha/internal/zzaec;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzagx;


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<MessageType:",
        "Lcom/google/android/recaptcha/internal/zzaec<",
        "TMessageType;TBuilderType;>;BuilderType:",
        "Lcom/google/android/recaptcha/internal/zzaeb<",
        "TMessageType;TBuilderType;>;>",
        "Ljava/lang/Object;",
        "Lcom/google/android/recaptcha/internal/zzagx;"
    }
.end annotation


# instance fields
.field protected transient zzb:I


# direct methods
.method public constructor <init>()V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x0

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzaec;->zzb:I

    return-void
.end method

.method public static zzy(Ljava/lang/Iterable;Ljava/util/List;)V
    .locals 0

    .line 1
    invoke-static {p0, p1}, Lcom/google/android/recaptcha/internal/zzaeb;->zzj(Ljava/lang/Iterable;Ljava/util/List;)V

    return-void
.end method


# virtual methods
.method public final zzv()Lcom/google/android/recaptcha/internal/zzaer;
    .locals 5

    const/4 v0, 0x0

    .line 1
    :try_start_0
    invoke-interface {p0}, Lcom/google/android/recaptcha/internal/zzagx;->zzJ()I

    move-result v1

    sget-object v2, Lcom/google/android/recaptcha/internal/zzaer;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    .line 2
    new-array v2, v1, [B

    new-instance v3, Lcom/google/android/recaptcha/internal/zzaex;

    const/4 v4, 0x0

    .line 3
    invoke-direct {v3, v2, v4, v1, v0}, Lcom/google/android/recaptcha/internal/zzaex;-><init>([BIILcom/google/android/recaptcha/internal/zzaey;)V

    .line 4
    invoke-interface {p0, v3}, Lcom/google/android/recaptcha/internal/zzagx;->zzI(Lcom/google/android/recaptcha/internal/zzafa;)V

    .line 5
    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzafa;->zzw()V

    .line 6
    new-instance v1, Lcom/google/android/recaptcha/internal/zzaep;

    invoke-direct {v1, v2}, Lcom/google/android/recaptcha/internal/zzaep;-><init>([B)V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    return-object v1

    :catch_0
    move-exception v1

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object p0

    .line 7
    invoke-virtual {p0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v2

    add-int/lit8 v2, v2, 0x48

    const-string v3, " to a ByteString threw an IOException (should never happen)."

    .line 8
    invoke-static {v2, p0, v3, v1}, Lcom/google/android/gms/internal/cast/zzwz$$ExternalSyntheticBUOutline0;->m(ILjava/lang/Object;Ljava/lang/Object;Ljava/lang/Throwable;)V

    return-object v0
.end method

.method public final zzw()[B
    .locals 5

    const/4 v0, 0x0

    .line 1
    :try_start_0
    invoke-interface {p0}, Lcom/google/android/recaptcha/internal/zzagx;->zzJ()I

    move-result v1

    new-array v2, v1, [B

    new-instance v3, Lcom/google/android/recaptcha/internal/zzaex;

    const/4 v4, 0x0

    .line 2
    invoke-direct {v3, v2, v4, v1, v0}, Lcom/google/android/recaptcha/internal/zzaex;-><init>([BIILcom/google/android/recaptcha/internal/zzaey;)V

    .line 3
    invoke-interface {p0, v3}, Lcom/google/android/recaptcha/internal/zzagx;->zzI(Lcom/google/android/recaptcha/internal/zzafa;)V

    .line 4
    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzafa;->zzw()V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    return-object v2

    :catch_0
    move-exception v1

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object p0

    .line 5
    invoke-virtual {p0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v2

    add-int/lit8 v2, v2, 0x48

    const-string v3, " to a byte array threw an IOException (should never happen)."

    .line 6
    invoke-static {v2, p0, v3, v1}, Lcom/google/android/gms/internal/cast/zzwz$$ExternalSyntheticBUOutline0;->m(ILjava/lang/Object;Ljava/lang/Object;Ljava/lang/Throwable;)V

    return-object v0
.end method

.method public abstract zzx(Lcom/google/android/recaptcha/internal/zzahn;)I
.end method

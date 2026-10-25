.class public final Lcom/google/android/recaptcha/internal/zzafh;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field static final zza:Lcom/google/android/recaptcha/internal/zzafh;


# instance fields
.field private final zzd:Ljava/util/Map;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzafh;

    const/4 v1, 0x1

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzafh;-><init>(Z)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzafh;->zza:Lcom/google/android/recaptcha/internal/zzafh;

    return-void
.end method

.method public constructor <init>(Z)V
    .locals 0

    .line 2
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    sget-object p1, Ljava/util/Collections;->EMPTY_MAP:Ljava/util/Map;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzafh;->zzd:Ljava/util/Map;

    return-void
.end method

.method public static zzb()Lcom/google/android/recaptcha/internal/zzafh;
    .locals 1

    .line 1
    sget v0, Lcom/google/android/recaptcha/internal/zzaef;->$r8$clinit:I

    sget-object v0, Lcom/google/android/recaptcha/internal/zzafh;->zza:Lcom/google/android/recaptcha/internal/zzafh;

    return-object v0
.end method


# virtual methods
.method public final zzc(Lcom/google/android/recaptcha/internal/zzagx;I)Lcom/google/android/recaptcha/internal/zzafu;
    .locals 1

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzafg;

    invoke-direct {v0, p1, p2}, Lcom/google/android/recaptcha/internal/zzafg;-><init>(Ljava/lang/Object;I)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafh;->zzd:Ljava/util/Map;

    invoke-interface {p0, v0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    const/4 p0, 0x0

    return-object p0
.end method

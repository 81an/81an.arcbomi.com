package io.grpc.internal;

/* loaded from: classes2.dex */
public interface BackoffPolicy {

    /* loaded from: classes2.dex */
    public interface Provider {
        BackoffPolicy get();
    }

    long nextBackoffNanos();
}

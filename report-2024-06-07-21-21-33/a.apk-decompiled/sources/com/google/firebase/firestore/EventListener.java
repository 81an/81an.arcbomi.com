package com.google.firebase.firestore;

/* loaded from: classes.dex */
public interface EventListener<T> {
    void onEvent(T t, FirebaseFirestoreException firebaseFirestoreException);
}

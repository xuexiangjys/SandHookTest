#include <jni.h>
#include <string>
#include <android/log.h>

extern "C" JNIEXPORT jstring
JNICALL
Java_com_xuexiang_sandhooktest_core_entity_JNIApi_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}


/*
 * Copyright (C) 2020 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.xuexiang.sandhooktest.core.entity;

import android.util.Log;

/**
 * 测试类
 *
 * @author xuexiang
 * @since 2020/3/6 2:00 AM
 */
public class TestClass {

    private int a;

    protected String b;

    float c;

    public TestClass() {

    }

    public TestClass(int a, String b, float c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }


    public float getC() {
        return c;
    }


    /**
     * 抛出异常的方法
     */
    public void errorMethod () {
        a++;
        throw new RuntimeException("test exception");
    }

    /**
     * Hook 静态方法
     *
     * @param arg1
     * @param arg2
     * @return
     */
    public static int staticMethodHook(int arg1, int arg2) {
        int total = arg1 + arg2;
        Log.e("TestClass", "call staticMethodHook origin");
        return total;
    }

    @Override
    public String toString() {
        super.toString();
        return "TestClass{" +
                "a=" + a +
                ", b='" + b + '\'' +
                ", c=" + c +
                '}';
    }
}

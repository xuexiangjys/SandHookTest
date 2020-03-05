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

package com.xuexiang.sandhooktest.core.hook;

import android.util.Log;

import com.swift.sandhook.annotation.HookClass;
import com.swift.sandhook.annotation.HookMethod;
import com.swift.sandhook.annotation.HookMethodBackup;
import com.swift.sandhook.annotation.HookMode;
import com.swift.sandhook.wrapper.HookWrapper;
import com.xuexiang.sandhooktest.utils.XToastUtils;

/**
 * Hook Object 对象，这里HookClass是Object， 因此只能hook Object的toString方法，子类重写的toString方法无法Hook
 *
 * @author xuexiang
 * @since 2020/3/6 1:54 AM
 */
@HookClass(Object.class)
public class ObjectHooker {

    @HookMethodBackup("toString")
    static HookWrapper.HookEntity toStringBackup;

    @HookMethod("toString")
    @HookMode(HookMode.INLINE)
    public static String toStringHook(Object object) throws Throwable {
        String result = (String) toStringBackup.callOrigin(object);
        Log.e("ObjectHooker", "hooked success :" + result);
        XToastUtils.toast("Object toString hooked success :" + result);
        return result;
    }

}

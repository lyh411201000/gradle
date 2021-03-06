/*
 * Copyright 2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle.util

/**
 * @author Hans Dockter
 */
class ReflectionUtil {
    static Object invoke(Object object, String method, Object... params) {
        object.invokeMethod(method, params)
    }

    static Object getProperty(Object object, String property) {
        object."$property"
    }

    static void setProperty(Object object, String property, Object value) {
        object."$property" = value
    }

    static boolean hasProperty(Object object, String property) {
        object.metaClass.hasProperty(object, property) != null
    }

    static boolean isClassAvailable(String className) {
        try {
            ReflectionUtil.classLoader.loadClass(className)
            return true
        } catch (ClassNotFoundException e) {
            return false
        }
    }

    static Class<?> getWrapperTypeForPrimitiveType(Class<?> type) {
        if (type == Boolean.TYPE) {
            return Boolean.class;
        } else if (type == Long.TYPE) {
            return Long.class;
        } else if (type == Integer.TYPE) {
            return Integer.class;
        } else if (type == Short.TYPE) {
            return Short.class;
        } else if (type == Byte.TYPE) {
            return Byte.class;
        } else if (type == Float.TYPE) {
            return Float.class;
        } else if (type == Double.TYPE) {
            return Double.class;
        }
        throw new IllegalArgumentException(String.format("Don't know how wrapper type for primitive type %s.", type));
    }
}